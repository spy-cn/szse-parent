package com.spy.szse.svc.response;

import com.spy.szse.domain.entity.Node;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
@Data
public class RelationshipNodeResp {
    @ApiModelProperty("上游产品节点")
    private Node nodeA;
    @ApiModelProperty("下游产品节点")
    private Node nodeB;
    @ApiModelProperty("方向")
    private Integer weight;
    @ApiModelProperty("方向")
    private Integer direction;
    @ApiModelProperty("关系类型")
    private String relationType;
}
