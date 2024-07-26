package com.cyanrocks.wms.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

/**
 * @Author wjq
 * @Date 2024/7/24 15:12
 */
@Entity
@Table(name = "inventory_config")
@Data
public class InventoryConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,  // strategy 设置使用数据库主键自增策略；
            generator = "JDBC")
    private Long id;

    @Column(length = 50, name = "type" )
    @Comment("类型：select-选择显示字段；delete-去除显示字段；inventoryLabel1-库存黄色预警；inventoryLabel2-库存红色预警；validityLabel1-1/3有效期预警比例;；validityLabel2-2/3有效期预警比例" +
            "turnoverCoefficient-库存周转系数:group-分类")
    private String type;

    @Column(length = 50, name = "fields")
    @Comment("字段 仓库-ware_house;分类名称-goods_type;货品名称-goods_name;品牌-brand_name;分类;商家编码")
    private String fields;

    @Column(length = 255, name = "value")
    @Comment("变量  #/#分割")
    private String value;

}
