package com.cyanrocks.wms.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyanrocks.wms.entity.InventoryConfig;
import com.cyanrocks.wms.entity.InventoryValidgoods;
import com.cyanrocks.wms.mapper.InventoryConfigMapper;
import com.cyanrocks.wms.mapper.InventoryValidGoodsMapper;
import com.cyanrocks.wms.vo.request.InventoryConfigReq;
import com.cyanrocks.wms.vo.response.InventoryWaringDTO;
import com.cyanrocks.wms.vo.response.ValidityWaringDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author wjq
 * @Date 2024/7/24 9:31
 */
@Service
public class InventoryService extends ServiceImpl<InventoryValidGoodsMapper, InventoryValidgoods> {

    private static final String SPLIT_TAG = "#/#";

    @Autowired
    private InventoryConfigMapper configMapper;

    public void setInfo(List<InventoryValidgoods> reqs){
        baseMapper.deleteAll();
        reqs.forEach(req->{
            baseMapper.insert(req);
        });
    }

    public List<InventoryConfig> getConfig(){
        return configMapper.selectAll();
    }

    @Transactional
    public void setConfig(List<InventoryConfigReq> reqs){
        configMapper.deleteAll();
        reqs.forEach(req -> {
            InventoryConfig inventoryConfig = new InventoryConfig();
            inventoryConfig.setType(req.getType());
            inventoryConfig.setFields(req.getFields());
            inventoryConfig.setValue(req.getValue());
            configMapper.insert(inventoryConfig);
        });

    }

    public List<InventoryWaringDTO> inventoryWaring(){
        return baseMapper.getInventoryWaringRes(buildConfigFilterSql());
    }

    public List<ValidityWaringDTO> validityWaring(){
        return baseMapper.getValidityWaringRes(buildConfigFilterSql());
    }

    private String buildConfigFilterSql(){
        StringBuilder sb = new StringBuilder();

        List<InventoryConfig> deleteConfigs = configMapper.selectList(Wrappers.<InventoryConfig>lambdaQuery().eq(InventoryConfig::getType,"delete"));
        if (CollectionUtil.isNotEmpty(deleteConfigs)){
            deleteConfigs.forEach(deleteConfig->{
                sb.append(deleteConfig.getFields()).append(" not in ('");
                String[] values = deleteConfig.getValue().split(SPLIT_TAG);
                for (int i = 0; i < values.length; i++){
                    sb.append(values[i]).append("','");
                }
                sb.delete(sb.length()-2, sb.length());
                sb.append(")");
                sb.append(" and ");
            });
        }

        List<InventoryConfig> selectConfigs = configMapper.selectList(Wrappers.<InventoryConfig>lambdaQuery().eq(InventoryConfig::getType,"select"));
        if (CollectionUtil.isNotEmpty(selectConfigs)){
            selectConfigs.forEach(selectConfig->{
                sb.append(selectConfig.getFields()).append(" in ('");
                String[] values = selectConfig.getValue().split(SPLIT_TAG);
                for (int i = 0; i < values.length; i++){
                    sb.append(values[i]).append("','");
                }
                sb.delete(sb.length()-2, sb.length());
                sb.append(")");
                sb.append(" and ");
            });
        }
        sb.delete(sb.length()-4, sb.length());
        return sb.toString();
    }

    private void executeDelete(String fields, String[] value){
        switch (fields){
            case "仓库":
                baseMapper.delete(new QueryWrapper<InventoryValidgoods>()
                        .lambda()
                        .in(InventoryValidgoods::getWareHouse, Arrays.asList(value)));
                break;

            case "分类名称":
                baseMapper.delete(new QueryWrapper<InventoryValidgoods>()
                        .lambda()
                        .in(InventoryValidgoods::getGoodsType, Arrays.asList(value)));
                break;

            case "货品名称":
                baseMapper.delete(new QueryWrapper<InventoryValidgoods>()
                        .lambda()
                        .in(InventoryValidgoods::getGoodsName, Arrays.asList(value)));
                break;
            default:throw new RuntimeException("error fields");
        }
    }

    private void executeSelect(String fields, String[] value){
        switch (fields){
            case "品牌":
                baseMapper.delete(new QueryWrapper<InventoryValidgoods>()
                        .lambda()
                        .notIn(InventoryValidgoods::getBrandName, Arrays.asList(value)));
        }
    }
}
