package com.spy.szse.svc.controller;

import com.spy.szse.svc.request.UpdateRelationReq;
import com.spy.szse.svc.request.UpdateRelationRequest;
import com.spy.szse.svc.response.RelationshipNodeResp;
import com.spy.szse.svc.service.RelationshipTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.spy.szse.common.util.SzseUtil.isBlankException;
import static com.spy.szse.common.util.SzseUtil.anyBlank;


/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
@Api(tags = "产品上下游关系接口")
@RestController
@RequestMapping("/relationship")
public class RelationshipTableController {
    @Resource
    private RelationshipTableService relationshipTableService;

    @ApiOperation("更新产品与产品之间的上下游关系")
    @PutMapping("/updateRelation")
    public ResponseEntity updateRelationship(@ApiParam("更新请求对象实体") @RequestBody UpdateRelationRequest request) {
        List<RelationshipNodeResp> respList = relationshipTableService.updateRelationship(request);
        return ResponseEntity.ok(respList);
    }

    @ApiOperation("查看产品与产品之间上下游关系")
    @GetMapping("/")
    public ResponseEntity getRelationship(@ApiParam("产品Code") @RequestParam String productCode,
                                          @ApiParam("目标产品Code") @RequestParam String targetCode,
                                          @ApiParam("-1上游 1下游") @RequestParam Integer direction) {
        isBlankException(anyBlank(productCode, targetCode), "productCode and targetCode");
        List<RelationshipNodeResp> respList = relationshipTableService.getRelationship(productCode, targetCode, direction);
        return ResponseEntity.ok(respList);
    }
}
