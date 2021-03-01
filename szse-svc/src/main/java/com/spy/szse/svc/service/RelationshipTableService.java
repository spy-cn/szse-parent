package com.spy.szse.svc.service;

import com.spy.szse.svc.response.RelationshipNodeResp;

import java.util.List;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
public interface RelationshipTableService {
    /**
     * 查询产品节点上下游关系
     *
     * @param productCode
     * @param targetCode
     * @param direction
     * @return
     */
    List<RelationshipNodeResp> getRelationship(String productCode, String targetCode, Integer direction);
}
