package com.spy.szse.svc.service.impl;

import com.spy.szse.common.constant.SzseConstant;
import com.spy.szse.common.enums.ErrorEnum;
import com.spy.szse.common.enums.StatusEnum;
import com.spy.szse.common.exception.SzseException;
import com.spy.szse.common.util.SzseHttpRequestUtil;
import com.spy.szse.domain.entity.Node;
import com.spy.szse.domain.entity.NodeTable;
import com.spy.szse.domain.entity.RelationshipTable;
import com.spy.szse.svc.mapper.szse.RelationshipTableMapper;
import com.spy.szse.svc.mapper.szse.NodeTableMapper;
import com.spy.szse.svc.request.UpdateRelationRequest;
import com.spy.szse.svc.response.DeleteResp;
import com.spy.szse.svc.response.RelationshipNodeResp;
import com.spy.szse.svc.service.RelationshipTableService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.spy.szse.common.util.SzseUtil.illegalException;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
@Service
public class RelationshipTableServiceImpl implements RelationshipTableService {
    @Resource
    private NodeTableMapper nodeTableMapper;
    @Resource
    private RelationshipTableMapper relationshipTableMapper;

    /**
     * 查询产品节点的上下游关系
     *
     * @param productCode
     * @param targetCode
     * @param direction
     * @return
     */
    @Override
    public List<RelationshipNodeResp> getRelationship(String productCode, String targetCode, Integer direction) {
        illegalException(!SzseConstant.UP_DIRECTION.equals(direction) && !SzseConstant.DOWN_DIRECTION.equals(direction), "direction");
        NodeTable primaryNodeTable = nodeTableMapper.getByCode(productCode, StatusEnum.VALID.getValue());
        illegalException(Objects.isNull(primaryNodeTable), "productCodeTable");
        NodeTable targetNodeTable = nodeTableMapper.getByCode(targetCode, StatusEnum.VALID.getValue());
        illegalException(Objects.isNull(targetNodeTable), "targetNodeTable");

        List<RelationshipTable> primaryRelation = relationshipTableMapper.getRelationship(productCode, targetCode, direction, StatusEnum.VALID.getValue());
        List<RelationshipTable> targetRelation = relationshipTableMapper.getRelationship(productCode, targetCode, -1 * direction, StatusEnum.VALID.getValue());
        List<RelationshipTable> tables = new ArrayList<>(primaryRelation);
        tables.addAll(targetRelation);
        if (CollectionUtils.isEmpty(tables)) {
            return Collections.emptyList();
        }
        List<RelationshipTable> primaryNodes = relationshipTableMapper.getProductStreamByUpstream(primaryNodeTable.getCode());
        Map<Integer, Long> primaryCountMap = primaryNodes.stream().collect(Collectors.groupingBy(RelationshipTable::getRelationship, Collectors.counting()));
        List<RelationshipTable> targetNodes = relationshipTableMapper.getProductStreamByUpstream(targetNodeTable.getCode());
        Map<Integer, Long> targetCountMap = targetNodes.stream().collect(Collectors.groupingBy(RelationshipTable::getRelationship, Collectors.counting()));

        int primaryDownCount = 0, primaryUpCount = 0, targetDownCount = 0, targetUpCount = 0;
        Long primaryDownValue, primaryUpValue, targetDownValue, targetUpValue;

        if ((primaryDownValue = primaryCountMap.get(SzseConstant.DOWN_DIRECTION)) != null) {
            primaryDownCount = primaryDownValue.intValue();
        }
        if ((primaryUpValue = primaryCountMap.get(SzseConstant.UP_DIRECTION)) != null) {
            primaryUpCount = primaryUpValue.intValue();
        }
        if ((targetDownValue = targetCountMap.get(SzseConstant.DOWN_DIRECTION)) != null) {
            targetDownCount = targetDownValue.intValue();
        }
        if ((targetUpValue = targetCountMap.get(SzseConstant.UP_DIRECTION)) != null) {
            targetUpCount = targetUpValue.intValue();
        }
        Node primaryNode = new Node(primaryNodeTable.getName(), primaryNodeTable.getCode(), primaryDownCount, primaryUpCount);
        Node targetNode = new Node(targetNodeTable.getName(), targetNodeTable.getCode(), targetDownCount, targetUpCount);
        List<RelationshipNodeResp> respList = tables.stream().map(table -> {
            RelationshipNodeResp nodeResp = new RelationshipNodeResp();
            if (direction.equals(table.getRelationship())) {
                nodeResp.setNodeA(primaryNode);
                nodeResp.setNodeB(targetNode);
            } else {
                nodeResp.setNodeA(targetNode);
                nodeResp.setNodeB(primaryNode);
            }
            nodeResp.setDirection(table.getRelationship());
            nodeResp.setRelationType(table.getHeadType());
            nodeResp.setWeight(table.getWeight());
            return nodeResp;
        }).collect(Collectors.toList());
        return respList;
    }

    /**
     * 更新产品的上下游关系
     *
     * @param request
     * @return
     */
    @Override
    public List<RelationshipNodeResp> updateRelationship(UpdateRelationRequest request) {

        String productCode = request.getProductCode();
        boolean isDownDirection = SzseConstant.DOWN_DIRECTION.equals(request.getRelationship());
        //组装Relationship对象
        RelationshipTable downRelationshipTable = generatorRelationNode(request, request.getDownRelation());
        RelationshipTable upRelationshipTable = generatorRelationNode(request, request.getUpRelation());
        downRelationshipTable.setHeadType(upRelationshipTable.getTailType());
        upRelationshipTable.setTailType(downRelationshipTable.getTailType());

        RelationshipTable oldDownRelationship = getRelationshipTable(downRelationshipTable);
        if (ObjectUtils.isEmpty(oldDownRelationship)) {
            throw new SzseException(ErrorEnum.ERROR_NOT_EMPTY, oldDownRelationship);
        }
        int updateCount = 0;
        if (existsChange(oldDownRelationship, downRelationshipTable)) {
            updateCount++;
        }
        RelationshipTable oldUpRelationship = getRelationshipTable(upRelationshipTable);
        if (ObjectUtils.isEmpty(oldUpRelationship)) {
            throw new SzseException(ErrorEnum.ERROR_NOT_EMPTY, upRelationshipTable);
        }
        if (existsChange(oldUpRelationship, upRelationshipTable)) {
            updateCount++;
        }
        if (updateCount > 0) {
            List<RelationshipTable> updateList = new ArrayList<>();
            updateList.add(setWeightAndType(oldUpRelationship, upRelationshipTable));
            updateList.add(setWeightAndType(oldUpRelationship, downRelationshipTable));
            if (!isUpdateRelation(updateList)) {
                throw new SzseException(ErrorEnum.ERROR_UPDATE_STREAM, productCode, request.getTargetCode());
            }
        }
        return getRelationship(productCode, request.getTargetCode(), request.getRelationship());
    }

    /**
     * 删除产品节点的上下游关系
     *
     * @param username
     * @param headNodeCode
     * @param tailNodeCode
     * @return
     */
    @Override
    public DeleteResp deleteRelationship(String username, String headNodeCode, String tailNodeCode) {
        DeleteResp resp = new DeleteResp();
        StopWatch stopWatch = StopWatch.createStarted();
        List<RelationshipTable> relationshipTables = new ArrayList<>();
        RelationshipTable relation1 = new RelationshipTable();
        relation1.setHeadNodeCode(headNodeCode);
        relation1.setTailNodeCode(tailNodeCode);
        relationshipTables.add(relation1);
        RelationshipTable relation2 = new RelationshipTable();
        relation2.setHeadNodeCode(tailNodeCode);
        relation2.setTailNodeCode(headNodeCode);
        relationshipTables.add(relation2);
        Integer result = relationshipTableMapper.deleteByHeadNodeCodeAndTailNodeCode(relationshipTables, username);
        stopWatch.stop();
        resp.setTook(stopWatch.getTime(TimeUnit.SECONDS));
        resp.setResult(result);
        return resp;
    }

    /**
     * 更新是否成功
     *
     * @param updateList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean isUpdateRelation(List<RelationshipTable> updateList) {
        if (CollectionUtils.isEmpty(updateList)) {
            return true;
        }
        //定义影响的行数
        int affectedRows = 0;
        for (RelationshipTable relationshipTable : updateList) {
            affectedRows += relationshipTableMapper.updateRelation(relationshipTable);
        }
        boolean isSuccess = (affectedRows == updateList.size());
        if (!isSuccess) {
            throw new SzseException(ErrorEnum.ERROR_UPDATE_STREAM_FAIL);
        }
        return true;
    }


    private RelationshipTable setWeightAndType(RelationshipTable oldUpRelationship, RelationshipTable upRelationshipTable) {
        //oldUpRelationship.setHeadNodeCode(upRelationshipTable.getHeadNodeCode());
        oldUpRelationship.setHeadType(upRelationshipTable.getHeadType());
        //oldUpRelationship.setTailNodeCode(upRelationshipTable.getTailNodeCode());
        oldUpRelationship.setTailType(upRelationshipTable.getTailType());
        oldUpRelationship.setWeight(upRelationshipTable.getWeight());
        oldUpRelationship.setUpdatedBy(SzseHttpRequestUtil.getUsername());
        return oldUpRelationship;
    }

    private boolean existsChange(RelationshipTable oldRelationship, RelationshipTable newRelationship) {
        if (!Objects.equals(oldRelationship.getWeight(), newRelationship.getWeight())) {
            return true;
        }
        if (!Objects.equals(oldRelationship.getTailType(), newRelationship.getTailType())) {
            return true;
        }
        return false;
    }

    private RelationshipTable getRelationshipTable(RelationshipTable relationshipTable) {
        String headNodeCode = relationshipTable.getHeadNodeCode();
        String tailNodeCode = relationshipTable.getTailNodeCode();
        Integer relationship = relationshipTable.getRelationship();
        List<RelationshipTable> relationshipTableList = relationshipTableMapper.getByHeadCodeAndTailCodeAndRelationship(headNodeCode, tailNodeCode, relationship, StatusEnum.VALID.getValue());
        if (CollectionUtils.isEmpty(relationshipTableList)) {
            return null;
        }
        return relationshipTableList.get(0);
    }

    /**
     * 组装上下游关系
     *
     * @param request
     * @return
     */
    private RelationshipTable generatorRelationNode(UpdateRelationRequest request, UpdateRelationRequest.RelationMetadata relation) {
        boolean isDownDirection = SzseConstant.DOWN_DIRECTION.equals(request.getRelationship());
        //UpdateRelationRequest.RelationMetadata downRelation1 = request.getDownRelation();
        String productCode = request.getProductCode();
        String targetCode = request.getTargetCode();
        Integer direction = request.getRelationship();
        //UpdateRelationRequest.RelationMetadata downRelation = request.getDownRelation();
        Integer weight = relation.getWeight();
        String relationType = relation.getRelationType();
        RelationshipTable relationshipTable = new RelationshipTable();
        //更新人
        relationshipTable.setUpdatedBy(SzseHttpRequestUtil.getUsername());
        //状态
        relationshipTable.setStatus(SzseConstant.DEFAULT_STATUS);
        //节点类型
        relationshipTable.setTailType(relationType);
        //关系
        relationshipTable.setRelationship(request.getRelationship());
        //权重
        relationshipTable.setWeight(weight);
        if (SzseConstant.DOWN_DIRECTION.equals(direction)) {
            relationshipTable.setHeadNodeCode(isDownDirection ? productCode : targetCode);
            relationshipTable.setTailNodeCode(isDownDirection ? targetCode : productCode);
        } else {
            relationshipTable.setHeadNodeCode(isDownDirection ? targetCode : productCode);
            relationshipTable.setTailNodeCode(isDownDirection ? productCode : targetCode);
        }
        return relationshipTable;
    }
}
