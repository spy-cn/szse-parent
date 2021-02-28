package com.spy.szse.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: spy
 * @Date: 2021/2/28 18:20
 */
@ApiModel(value = "关系表")
@Data
public class RelationshipTable {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "上游产品节点编码")
    private String headNodeCode;
    @ApiModelProperty(value = "下游产品节点编码")
    private String tailNodeCode;
    @ApiModelProperty(value = "权重")
    private Integer weight;
    @ApiModelProperty(value = "上游产品类型")
    private String headType;
    @ApiModelProperty(value = "下游产品类型")
    private String tailType;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    @ApiModelProperty(value = "创建人")
    private String createdBy;
    @ApiModelProperty(value = "更新人")
    private String updatedBy;
    @ApiModelProperty(value = "关系")
    private Integer relationship;
    @ApiModelProperty(value = "状态")
    private Integer status;


}
