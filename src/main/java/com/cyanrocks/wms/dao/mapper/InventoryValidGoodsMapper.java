package com.cyanrocks.wms.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyanrocks.wms.dao.entity.InventoryValidgoods;
import com.cyanrocks.wms.vo.response.InventoryWaringDTO;
import com.cyanrocks.wms.vo.response.ValidityWaringDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InventoryValidGoodsMapper extends BaseMapper<InventoryValidgoods> {

    List<InventoryWaringDTO> getInventoryWaringRes(@Param("filter") String filter, @Param("sort") String sort, @Param("search") String search);

    List<ValidityWaringDTO> getValidityWaringRes(@Param("filter") String filter, @Param("sort") String sort, @Param("search") String search);

    @Delete("delete from inventory_validgoods")
    void deleteAll();

}
