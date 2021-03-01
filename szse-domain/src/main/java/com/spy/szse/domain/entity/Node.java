package com.spy.szse.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
@ApiModel("产品节点描述信息实体")
@Data
public class Node {
    @ApiModelProperty("产品中文名称")
    private String name;
    @ApiModelProperty("产品编码")
    private String productCode;
    @ApiModelProperty("产品的下游产品数量")
    private Integer downStreamCount;
    @ApiModelProperty("产品的上游产品数量")
    private Integer upStreamCount;
}
