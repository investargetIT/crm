package com.cyanrocks.wms.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author wjq
 * @Date 2024/8/6 15:28
 */
@Data
@ApiModel(description = "库存周转系数请求参数")
public class TurnoverCoefficientReq {

    @ApiModelProperty(value = "商家编码")
    private String specNo;

    @ApiModelProperty(value = "货品名称")
    private String goodsName;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "规格名称")
    private String goodsType;

    @ApiModelProperty(value = "规格名称")
    private String comment;

    @ApiModelProperty(value = "规格名称")
    private String turnoverCoefficient;

}
