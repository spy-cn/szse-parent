package com.spy.szse.svc.service.impl;

import com.spy.szse.common.constant.SzseConstant;
import com.spy.szse.common.enums.StatusEnum;
import com.spy.szse.domain.entity.NodeTable;
import com.spy.szse.domain.entity.RelationshipTable;
import com.spy.szse.svc.mapper.szse.RelationshipTableMapper;
import com.spy.szse.svc.mapper.szse.NodeTableMapper;
import com.spy.szse.svc.response.RelationshipNodeResp;
import com.spy.szse.svc.service.RelationshipTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
        NodeTable primaryNode = nodeTableMapper.getByCode(productCode, StatusEnum.VALID.getValue());
        illegalException(Objects.isNull(primaryNode), "productCode");
        NodeTable targetNode = nodeTableMapper.getByCode(targetCode, StatusEnum.VALID.getValue());
        illegalException(Objects.isNull(targetNode), "targetNode");

        List<RelationshipTable> primaryRelation = relationshipTableMapper.getRelationship(productCode, targetCode, StatusEnum.VALID.getValue());

        return null;
    }
}
