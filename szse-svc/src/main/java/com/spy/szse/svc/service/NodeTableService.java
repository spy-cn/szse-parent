package com.spy.szse.svc.service;


import com.spy.szse.svc.response.ProductKeywordResp;
import com.spy.szse.svc.response.ProductTreeResp;

import java.util.List;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
public interface NodeTableService {
    /**
     * 获取产品树
     *
     * @return
     */
    ProductTreeResp getProductTree();

    /**
     * 根据关键字查询客户产品
     *
     * @param keyword 关键字产品中文名称或者产品编码
     * @param limit   每页数量 默认20
     * @return
     */
    List<ProductKeywordResp> getProductByKeyword(String keyword, Integer limit);
}
