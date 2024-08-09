package com.cyanrocks.wms.dao.entity;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

/**
 * @Author wjq
 * @Date 2024/8/6 15:40
 */
@Entity
@Table(name = "inventory_turnover_coefficient", indexes= {@Index(name="specNo_index", columnList="spec_no", unique=true)},
        uniqueConstraints={@UniqueConstraint(columnNames={"spec_no","goods_name","spec_name"},name="UC_NO_NAME")})
@Data
public class InventoryTurnoverCoefficient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,  // strategy 设置使用数据库主键自增策略；
            generator = "JDBC")
    private Long id;

    @Column(length = 40, name = "spec_no")
    @Comment("商家编码")
    private String specNo;

    @Column(length = 255, name = "goods_name")
    @Comment("货品名称")
    private String goodsName;

    @Column(length = 255, name = "spec_name")
    @Comment("规格名称")
    private String specName;

    @Column(length = 100, name = "goods_type")
    @Comment("分类名称")
    private String goodsType;

    @Column(length = 255, name = "comment")
    @Comment("备注")
    private String comment;

    @Column(length = 40, name = "turnover_coefficient")
    @Comment("库存周转系数")
    private String turnoverCoefficient;
}
