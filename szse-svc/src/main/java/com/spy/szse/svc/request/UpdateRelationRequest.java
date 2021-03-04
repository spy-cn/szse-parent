package com.spy.szse.svc.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/3
 */
@Data
@ApiModel("更新上下游关系请求实体")
public class UpdateRelationRequest {
    @ApiModelProperty("产品Code")
    private String productCode;
    @ApiModelProperty("目标产品Code")
    private String targetCode;
    @ApiModelProperty("上下游关系 -1上游 1下游")
    private Integer relationship;
    @ApiModelProperty("上游关系对象")
    private RelationMetadata upRelation;
    @ApiModelProperty("下游关系对象")
    private RelationMetadata downRelation;
    //@ApiModelProperty("备注")
    //private String remark;

    @ApiModel("")
    @Data
    public static class RelationMetadata {
        @ApiModelProperty("类型")
        private String relationType;
        @ApiModelProperty("权重")
        private Integer weight;
    }
}
