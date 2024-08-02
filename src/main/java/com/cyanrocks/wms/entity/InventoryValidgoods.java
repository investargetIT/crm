package com.cyanrocks.wms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author wjq
 * @Date 2024/7/23 14:23
 */
@Entity
@Table(name = "inventory_validgoods")
@Data
public class InventoryValidgoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,  // strategy 设置使用数据库主键自增策略；
            generator = "JDBC")
    private Long id;

    @Column(length = 50, name = "owner")
    @Comment("货主")
    private String owner;

    @Column(length = 100, name = "ware_house")
    @Comment("仓库")
    private String wareHouse;

    @Column(length = 40, name = "spec_no")
    @Comment("商家编码")
    private String specNo;

    @Column(length = 255, name = "goods_name")
    @Comment("货品名称")
    private String goodsName;

    @Column(length = 255, name = "goods_short_name")
    @Comment("货品简称")
    private String goodsShortName;

    @Column(length = 100, name = "goods_no")
    @Comment("货品编号")
    private String goodsNo;

    @Column(length = 40, name = "spec_id")
    @Comment("规格码")
    private String specId;

    @Column(length = 255, name = "spec_name")
    @Comment("规格名称")
    private String spacName;

    @Column(length = 50, name = "barcode")
    @Comment("条形码")
    private String barcode;

    @Column(length = 100, name = "brand_name")
    @Comment("品牌")
    private String brandName;

    @Column(length = 100, name = "goods_type")
    @Comment("分类名称")
    private String goodsType;

    @Column(precision = 19, scale = 4, name = "inventory_num")
    @Comment("库存数量")
    private Double inventoryNum;

    @Column(name = "shelf_life")
    @Comment("保质期")
    private Long shelfLife;

    @JsonFormat
    @Column(name = "product_time")
    @Comment("生产日期")
    private LocalDateTime productTime;

    @JsonFormat
    @Column(length = 100, name = "validity_time")
    @Comment("有效期")
    private LocalDateTime validityTime;

    @Column(length = 50, name = "unit_name")
    @Comment("基本单位")
    private String unitName;

    @Column(length = 50, name = "case_specification")
    @Comment("箱规")
    private String caseSpecification;

    @Column(precision = 19, scale = 4, name = "case_num")
    @Comment("箱数")
    private Double case_num;

    @Column(name = "due_days")
    @Comment("距离到期天数")
    private Long dueDays;

    @Column(length = 10, name = "due_scale")
    @Comment("到期比例")
    private String dueScale;

    @Column(name = "critical_days")
    @Comment("临期天数")
    private Long criticalDays;

    @Column(length = 10, name = "late_critical_days")
    @Comment("距离临期天数")
    private String lateCriticalDays;

}
