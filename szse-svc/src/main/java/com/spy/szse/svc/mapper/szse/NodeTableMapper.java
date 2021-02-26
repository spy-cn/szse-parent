package com.spy.szse.svc.mapper.szse;

import com.spy.szse.domain.entity.NodeTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author lei.zhao_ext
 * @Date 2021/2/26
 */
@Mapper
public interface NodeTableMapper {
    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    NodeTable getById(@Param("id") Long id);
}
