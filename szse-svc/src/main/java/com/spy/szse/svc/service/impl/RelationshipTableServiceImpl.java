package com.spy.szse.svc.service.impl;

import com.spy.szse.common.constant.SzseConstant;
import com.spy.szse.common.enums.StatusEnum;
import com.spy.szse.domain.entity.Node;
import com.spy.szse.domain.entity.NodeTable;
import com.spy.szse.domain.entity.RelationshipTable;
import com.spy.szse.svc.mapper.szse.RelationshipTableMapper;
import com.spy.szse.svc.mapper.szse.NodeTableMapper;
import com.spy.szse.svc.response.RelationshipNodeResp;
import com.spy.szse.svc.service.RelationshipTableService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
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
}
