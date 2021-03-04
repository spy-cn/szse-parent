package com.spy.szse.svc.mapper.szse;

import com.spy.szse.domain.entity.RelationshipTable;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
@Mapper
public interface RelationshipTableMapper {
    /**
     * 获取对应关系
     *
     * @param productCode
     * @param targetCode
     * @param value
     * @return
     */
    List<RelationshipTable> getRelationship(@Param("productCode") String productCode,
                                            @Param("targetCode") String targetCode,
                                            @Param("direction") Integer direction,
                                            @Param("value") Integer value);

    /**
     * 获取产品上游关系
     *
     * @param code
     * @return
     */
    List<RelationshipTable> getProductStreamByUpstream(@Param("code") String code);

    /**
     * 查询
     *
     * @param headNodeCode
     * @param tailNodeCode
     * @param relationship
     * @return
     */
    List<RelationshipTable> getByHeadCodeAndTailCodeAndRelationship(@Param("headNodeCode") String headNodeCode,
                                                                    @Param("tailNodeCode") String tailNodeCode,
                                                                    @Param("relationship") Integer relationship,
                                                                    @Param("status") Integer status);

    /**
     * 更新关系
     *
     * @param relationshipTable
     * @return
     */
    int updateRelation(RelationshipTable relationshipTable);
}
