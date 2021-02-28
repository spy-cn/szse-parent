package com.spy.szse.svc.controller;

import com.spy.szse.svc.mapper.szse.NodeTableMapper;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author lei.zhao_ext
 * @Date 2021/2/26
 */
@Api(tags = "测试")
@RestController
@RequestMapping("/node")
public class NodeTableController {
    @Resource
    private NodeTableMapper nodeTableMapper;

    @ApiOperation(value = "根据ID插询",notes = "id查询接口")
    @GetMapping("/{id}")
    public ResponseEntity getNodeTableById(@ApiParam("id") @PathVariable("id") Long id) {
        return ResponseEntity.ok(nodeTableMapper.getById(id));
    }
}
