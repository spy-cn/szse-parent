package com.spy.szse.svc.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
@ApiModel(value = "客户查询产品树返回对象")
@Data
public class ProductResp {
    @ApiModelProperty("产品名称")
    private String productName;
    @ApiModelProperty("产品Code")
    private String productCode;
    @ApiModelProperty("父节点code")
    private String parentCode;
    @ApiModelProperty("层级")
    private Integer level;
    @ApiModelProperty("来源")
    private String source;
    @ApiModelProperty("版本")
    private Integer versionId;
    @ApiModelProperty("节点顺序")
    private Integer sequence;
}
