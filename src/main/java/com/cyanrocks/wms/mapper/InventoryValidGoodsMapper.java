package com.cyanrocks.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyanrocks.wms.entity.InventoryValidgoods;
import com.cyanrocks.wms.vo.response.InventoryWaringDTO;
import com.cyanrocks.wms.vo.response.ValidityWaringDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InventoryValidGoodsMapper extends BaseMapper<InventoryValidgoods> {

    List<InventoryWaringDTO> getInventoryWaringRes(@Param("sql") String sql);

    List<ValidityWaringDTO> getValidityWaringRes(@Param("sql") String sql);

    @Delete("delete from inventory_validgoods")
    void deleteAll();

}
