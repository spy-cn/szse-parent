package com.spy.szse.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: spy
 * @Date: 2021/2/28 18:39
 */
@ApiModel("日志表")
@Data
public class Audit {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("原表主键")
    private Long objectId;
    @ApiModelProperty("")
    private String fieldName;
    @ApiModelProperty("原数据")
    private String beforeValue;
    @ApiModelProperty("修改后数据")
    private String afterValue;
    @ApiModelProperty("修改类型 1新增 2修改 3删除")
    private Integer operateType;
    @ApiModelProperty("创建人")
    private String  createdBy;
    @ApiModelProperty("创建时间")
    private Date createdTime;
    @ApiModelProperty("值类型")
    private String valueType;
    @ApiModelProperty("对象类型")
    private String objectType;
}
