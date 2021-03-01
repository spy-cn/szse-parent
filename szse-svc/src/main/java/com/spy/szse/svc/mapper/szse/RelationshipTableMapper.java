package com.spy.szse.svc.mapper.szse;

import com.spy.szse.domain.entity.RelationshipTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
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
                                            @Param("value") Integer value);
}
