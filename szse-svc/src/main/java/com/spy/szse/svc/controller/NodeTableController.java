package com.spy.szse.svc.controller;

import com.spy.szse.svc.mapper.szse.NodeTableMapper;
import com.spy.szse.svc.response.ProductTreeResp;
import com.spy.szse.svc.service.NodeTableService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.spy.szse.common.util.SzseUtil.isBlankException;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @Author lei.zhao_ext
 * @Date 2021/2/26
 */
@Api(tags = "产品接口")
@RestController
@RequestMapping("/node")
public class NodeTableController {
    @Resource
    private NodeTableMapper nodeTableMapper;

    @Resource
    private NodeTableService nodeTableService;

    @ApiOperation("客户产品树查询")
    @GetMapping("/tree")
    public ResponseEntity getProductTree() {
        ProductTreeResp resp = nodeTableService.getProductTree();
        return ResponseEntity.ok(resp);
    }

    /**
     * 获取产品描述和映射信息
     *
     * @param productCode
     * @return
     */
    @ApiOperation("获取产品描述和映射信息")
    @GetMapping("/{productCode}")
    public ResponseEntity getNodeInfo(@ApiParam("产品编码") @PathVariable("productCode") String productCode) {
        isBlankException(isBlank(productCode), productCode);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "根据ID插询", notes = "id查询接口")
    @GetMapping("/id/{id}")
    public ResponseEntity getNodeTableById(@ApiParam("id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(nodeTableMapper.getById(id));
    }
}
