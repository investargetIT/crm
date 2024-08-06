package com.cyanrocks.wms.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyanrocks.wms.constants.ErrorCodeEnum;
import com.cyanrocks.wms.dao.entity.InventoryConfig;
import com.cyanrocks.wms.dao.entity.InventoryTurnoverCoefficient;
import com.cyanrocks.wms.dao.entity.InventoryValidgoods;
import com.cyanrocks.wms.dao.mapper.InventoryConfigMapper;
import com.cyanrocks.wms.dao.mapper.InventoryTurnoverCoefficientMapper;
import com.cyanrocks.wms.dao.mapper.InventoryValidGoodsMapper;
import com.cyanrocks.wms.exception.WmsBusinessException;
import com.cyanrocks.wms.vo.request.InventoryConfigReq;
import com.cyanrocks.wms.vo.request.TurnoverCoefficientReq;
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

    @Autowired
    private InventoryTurnoverCoefficientMapper turnoverCoefficientMapper;

    public void setInfo(List<InventoryValidgoods> reqs){
        baseMapper.deleteAll();
        reqs.forEach(req->{
            baseMapper.insert(req);
        });
    }

    public List<InventoryTurnoverCoefficient> getTurnoverCoefficient(){
        return turnoverCoefficientMapper.selectAll();
    }

    @Transactional
    public void setTurnoverCoefficient(List<TurnoverCoefficientReq> reqs){
        turnoverCoefficientMapper.deleteAll();
        reqs.forEach(req -> {
            InventoryTurnoverCoefficient turnoverCoefficient = new InventoryTurnoverCoefficient();
            turnoverCoefficient.setSpecNo(req.getSpecNo());
            turnoverCoefficient.setGoodsName(req.getGoodsName());
            turnoverCoefficient.setSpacName(req.getSpacName());
            turnoverCoefficient.setGoodsType(req.getGoodsType());
            turnoverCoefficient.setComment(req.getComment());
            turnoverCoefficient.setTurnoverCoefficient(req.getTurnoverCoefficient());
            if (null != turnoverCoefficientMapper.selectOne(Wrappers.<InventoryTurnoverCoefficient>lambdaQuery()
                    .eq(InventoryTurnoverCoefficient::getSpecNo,req.getSpecNo())
                    .eq(InventoryTurnoverCoefficient::getGoodsName,req.getGoodsName())
                    .eq(InventoryTurnoverCoefficient::getSpacName,req.getSpacName()))){
                throw new WmsBusinessException(ErrorCodeEnum.REPEAT_PARAM.getCode(), "商家编码，货品名称，规格名称存在重复");
            }
            if (null != turnoverCoefficientMapper.selectOne(Wrappers.<InventoryTurnoverCoefficient>lambdaQuery()
                    .eq(InventoryTurnoverCoefficient::getSpecNo,req.getSpecNo()))){
                throw new WmsBusinessException(ErrorCodeEnum.REPEAT_PARAM.getCode(), "商家编码存在重复");
            }
            turnoverCoefficientMapper.insert(turnoverCoefficient);
        });
    }

    public List<InventoryConfig> getConfig(){
        return configMapper.selectAll();
    }

    @Transactional
    public void setConfig(InventoryConfigReq reqs){
        if (null == reqs.getConfigType()){
            configMapper.deleteAll();
        }else {
            configMapper.delete(Wrappers.<InventoryConfig>lambdaQuery().eq(InventoryConfig::getType,reqs.getConfigType()));
        }
        reqs.getConfigList().forEach(req -> {
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
