package com.spy.szse.svc.controller;

import com.spy.szse.svc.mapper.szse.NodeTableMapper;
import com.spy.szse.svc.response.ProductKeywordResp;
import com.spy.szse.svc.response.ProductTreeResp;
import com.spy.szse.svc.service.NodeTableService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

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


    @ApiOperation("根据关键字(中文或编码)查询客户产品")
    @GetMapping("/")
    public ResponseEntity getProductByKeyword(@ApiParam(value = "关键字产品中文名称或者英文编码") @RequestParam String keyword,
                                              @ApiParam(value = "每页数量") @RequestParam(required = false, defaultValue = "20") Integer limit) {
        isBlankException(isBlank(keyword), keyword);
        List<ProductKeywordResp> resp = nodeTableService.getProductByKeyword(keyword, limit);
        return ResponseEntity.ok(resp);
    }

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
