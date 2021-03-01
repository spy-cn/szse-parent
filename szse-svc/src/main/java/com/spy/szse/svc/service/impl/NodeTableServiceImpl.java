package com.spy.szse.svc.service.impl;

import com.spy.szse.domain.entity.NodeTable;
import com.spy.szse.svc.mapper.szse.NodeTableMapper;
import com.spy.szse.svc.response.ProductResp;
import com.spy.szse.svc.response.ProductTreeResp;
import com.spy.szse.svc.service.NodeTableService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
@Service
public class NodeTableServiceImpl implements NodeTableService {
    @Resource
    private NodeTableMapper nodeTableMapper;

    /**
     * 获取产品树
     *
     * @return
     */
    @Override
    public ProductTreeResp getProductTree() {
        ProductTreeResp resp = new ProductTreeResp();
        List<NodeTable> nodeTables = nodeTableMapper.getProductAll();
        if (CollectionUtils.isEmpty(nodeTables)) {
            return resp;
        }
        List<ProductResp> list = nodeTables.stream().map(nodeTable -> {
            ProductResp productResp = new ProductResp();
            productResp.setProductName(nodeTable.getName());
            productResp.setProductCode(nodeTable.getCode());
            productResp.setParentCode(nodeTable.getParent());
            productResp.setLevel(nodeTable.getLevel());
            productResp.setVersionId(nodeTable.getVersionId());
            productResp.setSequence(nodeTable.getSequence());
            productResp.setSource(nodeTable.getSource());
            return productResp;
        }).collect(Collectors.toList());
        resp.setProductNodes(list);
        return resp;
    }
}
