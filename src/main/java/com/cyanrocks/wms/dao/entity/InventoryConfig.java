package com.cyanrocks.wms.dao.entity;

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
    @Comment("类型：select-选择显示字段；delete-去除显示字段")
    private String type;

    @Column(length = 50, name = "fields")
    @Comment("字段")
    private String fields;

    @Column(length = 255, name = "value")
    @Comment("变量  #/#分割")
    private String value;

}
