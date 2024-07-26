package com.cyanrocks.wms.vo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wjq
 * @Date 2024/7/25 13:28
 */
@Data
public class ValidityWaringDTO implements Serializable {

    private String brandName;//品牌
    private String specNo;//商家编码
    private String goodsName;//货品名称
    private String specName;//规格名称
    private String groupType;//分类
    private Integer waring1Num;
    private Integer waring2Num;
    private Integer waring3Num;
    private Integer inventoryNum;//库存数量
}
