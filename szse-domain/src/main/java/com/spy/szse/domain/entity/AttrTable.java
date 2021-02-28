package com.spy.szse.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: spy
 * @Date: 2021/2/28 18:10
 */
@ApiModel(value = "公司属性表")
@Data
public class AttrTable {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "产品编码")
    private String productCode;
    @ApiModelProperty(value = "公司编码")
    private String companyCode;
    @ApiModelProperty(value = "起始有效时间")
    private Date startValidDate;
    @ApiModelProperty(value = "终止有效时间")
    private Date failureDate;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "公布日期")
    private Date publishDate;
    @ApiModelProperty(value = "报表日期")
    private Date reportDate;
    @ApiModelProperty(value = "录入日期")
    private Date entryDate;

}
