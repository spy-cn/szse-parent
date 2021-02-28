package com.spy.szse.svc.controller;

import com.spy.szse.common.exception.SzseException;
import com.spy.szse.common.util.SzseUtil;
import com.spy.szse.svc.mapper.szse.NodeTableMapper;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    /**
     * 获取产品描述和映射信息
     *
     * @param productCode
     * @return
     */
    @ApiOperation("获取产品描述和映射信息")
    @GetMapping("/{productCode}")
    public ResponseEntity getNodeInfo(@ApiParam("产品编码") @PathVariable("productCode") String productCode) {
        SzseUtil.isBlankException(isBlank(productCode), productCode);
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "根据ID插询", notes = "id查询接口")
    @GetMapping("/{id}")
    public ResponseEntity getNodeTableById(@ApiParam("id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(nodeTableMapper.getById(id));
    }
}
