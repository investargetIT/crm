package com.cyanrocks.wms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyanrocks.wms.entity.InventoryConfig;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InventoryConfigMapper extends BaseMapper<InventoryConfig> {

    @Delete("delete from inventory_config")
    void deleteAll();

    @Select("select * from inventory_config")
    List<InventoryConfig> selectAll();
}
