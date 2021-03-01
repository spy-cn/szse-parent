package com.spy.szse.svc.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
@ApiModel("客户产品树查询返回对象")
@Data
public class ProductTreeResp {
    @ApiModelProperty("产品返回节点列表信息")
    private List<ProductResp> productNodes;
}
