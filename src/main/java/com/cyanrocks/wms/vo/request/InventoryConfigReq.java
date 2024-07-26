package com.cyanrocks.wms.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author wjq
 * @Date 2024/7/23 16:48
 */
@Data
@ApiModel(description = "库存配置请求参数")
public class InventoryConfigReq {

    @ApiModelProperty(value = "字段 仓库-ware_house;分类名称-goods_type;货品名称-goods_name;品牌-brand_name;wrong;error")
    private String fields;

    @ApiModelProperty(value = "类型 delete;select;inventoryWaring;validityWaring;turnoverCoefficient")
    private String type;

    @ApiModelProperty(value = "字段值 #/#分割")
    private String value;


}
