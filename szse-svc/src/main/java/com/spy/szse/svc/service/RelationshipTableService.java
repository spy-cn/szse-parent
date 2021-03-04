package com.spy.szse.svc.service;

import com.spy.szse.svc.request.UpdateRelationRequest;
import com.spy.szse.svc.response.DeleteResp;
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

    /**
     * 更新产品节点的上下游关系
     *
     * @param request
     * @return
     */
    List<RelationshipNodeResp> updateRelationship(UpdateRelationRequest request);

    /**
     * 删除产品节点的上下游关系
     *
     * @param username
     * @param headNodeCode
     * @param tailNodeCode
     * @return
     */
    DeleteResp deleteRelationship(String username, String headNodeCode, String tailNodeCode);
}
