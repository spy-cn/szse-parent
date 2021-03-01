package com.spy.szse.svc.mapper.szse;

import com.spy.szse.domain.entity.NodeTable;
import com.spy.szse.svc.response.ProductResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 获取产品树
     *
     * @return
     */
    List<NodeTable> getProductAll();

    /**
     * 根据关键字查询客户产品
     *
     * @param keywordLike  产品中文名称关键字
     * @param keywordLike1 产品编码关键字
     * @return
     */
    List<NodeTable> getProductByKeyword(@Param("name") String keywordLike, @Param("code") String keywordLike1);

    /**
     * @param productCode
     * @param value
     * @return
     */
    NodeTable getByCode(@Param("productCode") String productCode, @Param("value") Integer value);
}
