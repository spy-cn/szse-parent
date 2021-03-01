package com.spy.szse.svc.controller;

import com.spy.szse.svc.response.RelationshipNodeResp;
import com.spy.szse.svc.service.RelationshipTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;

import static com.spy.szse.common.util.SzseUtil.isBlankException;
import static com.spy.szse.common.util.SzseUtil.anyBlank;


/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
@Api(tags = "产品关系接口")
@RestController
@RequestMapping("/relationship")
public class RelationshipTableController {
    @Resource
    private RelationshipTableService relationshipTableService;

    @ApiOperation("查看产品与产品之间上下游关系")
    @GetMapping("/")
    public ResponseEntity getRelationship(@ApiParam("产品Code") @RequestParam String productCode,
                                          @ApiParam("目标产品Code") @RequestParam String targetCode,
                                          @ApiParam("-1上游 1下游") @RequestParam Integer direction) {
        isBlankException(anyBlank(productCode, targetCode), "productCode and targetCode");
        List<RelationshipNodeResp> respList = relationshipTableService.getRelationship(productCode,targetCode,direction);
        return ResponseEntity.ok(respList);
    }
}
