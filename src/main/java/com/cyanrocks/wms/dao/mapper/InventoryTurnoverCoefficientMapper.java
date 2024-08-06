package com.cyanrocks.wms.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyanrocks.wms.dao.entity.InventoryTurnoverCoefficient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InventoryTurnoverCoefficientMapper extends BaseMapper<InventoryTurnoverCoefficient> {

    @Delete("delete from inventory_turnover_coefficient")
    void deleteAll();

    @Select("select * from inventory_turnover_coefficient")
    List<InventoryTurnoverCoefficient> selectAll();
}
