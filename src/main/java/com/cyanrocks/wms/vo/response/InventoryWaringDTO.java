package com.cyanrocks.wms.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wjq
 * @Date 2024/7/24 16:49
 */
@Data
public class InventoryWaringDTO implements Serializable {

    private String brandName;//品牌
    private String specNo;//商家编码
    private String goodsName;//货品名称
    private String specName;//规格名称
    private Double inventoryNum;//库存数量
    private Double turnoverDays;//预计可周转天数
    private String groupType;//分类
    private String waringLevel;//库存预警

}
