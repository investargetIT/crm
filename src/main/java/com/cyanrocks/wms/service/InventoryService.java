package com.cyanrocks.wms.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyanrocks.wms.constants.ErrorCodeEnum;
import com.cyanrocks.wms.constants.InventoryFieldsEnum;
import com.cyanrocks.wms.dao.entity.InventoryConfig;
import com.cyanrocks.wms.dao.entity.InventoryTurnoverCoefficient;
import com.cyanrocks.wms.dao.entity.InventoryValidgoods;
import com.cyanrocks.wms.dao.mapper.InventoryConfigMapper;
import com.cyanrocks.wms.dao.mapper.InventoryTurnoverCoefficientMapper;
import com.cyanrocks.wms.dao.mapper.InventoryValidGoodsMapper;
import com.cyanrocks.wms.exception.BusinessException;
import com.cyanrocks.wms.vo.request.InventoryConfigReq;
import com.cyanrocks.wms.vo.request.SearchReq;
import com.cyanrocks.wms.vo.request.TurnoverCoefficientReq;
import com.cyanrocks.wms.vo.request.SortReq;
import com.cyanrocks.wms.vo.response.InventoryConfigVO;
import com.cyanrocks.wms.vo.response.InventoryWaringDTO;
import com.cyanrocks.wms.vo.response.ValidityWaringDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            try {
                baseMapper.insert(req);
            }catch (Exception e) {
                // 捕捉其他异常
                System.out.println("发生异常: "+e.getMessage());
                throw new RuntimeException("插入有效期商品失败, specNo："+req.getSpecNo());
            }
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
            turnoverCoefficient.setSpecName(req.getSpecName());
            turnoverCoefficient.setGoodsType(req.getGoodsType());
            turnoverCoefficient.setComment(req.getComment());
            turnoverCoefficient.setTurnoverCoefficient(req.getTurnoverCoefficient());
            if (null != turnoverCoefficientMapper.selectOne(Wrappers.<InventoryTurnoverCoefficient>lambdaQuery()
                    .eq(InventoryTurnoverCoefficient::getSpecNo,req.getSpecNo())
                    .eq(InventoryTurnoverCoefficient::getGoodsName,req.getGoodsName())
                    .eq(InventoryTurnoverCoefficient::getSpecName,req.getSpecName()))){
                throw new BusinessException(ErrorCodeEnum.REPEAT_PARAM.getCode(), "商家编码，货品名称，规格名称存在重复");
            }
            if (null != turnoverCoefficientMapper.selectOne(Wrappers.<InventoryTurnoverCoefficient>lambdaQuery()
                    .eq(InventoryTurnoverCoefficient::getSpecNo,req.getSpecNo()))){
                throw new BusinessException(ErrorCodeEnum.REPEAT_PARAM.getCode(), "商家编码存在重复");
            }
            turnoverCoefficientMapper.insert(turnoverCoefficient);
        });
    }

    public List<InventoryConfigVO> getConfig(){
        List<String> sql = Arrays.asList("select","delete");
        List<InventoryConfig> configs = configMapper.selectList(Wrappers.<InventoryConfig>lambdaQuery().in(InventoryConfig::getType,sql));
        List<InventoryConfigVO> result = new ArrayList<>();
        configs.forEach(config->{
            InventoryConfigVO vo = new InventoryConfigVO();
            vo.setFields(config.getFields());
            vo.setType(config.getType());
            vo.setValue(config.getValue().split(SPLIT_TAG));
            vo.setLabel(InventoryFieldsEnum.getCnByEn(config.getFields()));
            result.add(vo);
        });
        return result;
    }

    @Transactional
    public void setConfig(List<InventoryConfigReq> reqs){
        List<String> sql = Arrays.asList("validityLabel1","validityLabel2","inventoryLabel1","inventoryLabel2");
        configMapper.delete(Wrappers.<InventoryConfig>lambdaQuery().notIn(InventoryConfig::getType,sql));
        reqs.forEach(req -> {
            InventoryConfig inventoryConfig = new InventoryConfig();
            inventoryConfig.setType(req.getType());
            inventoryConfig.setFields(req.getFields());
//            if (req.getValue() instanceof String[]){
//                String[] strings = (String[]) req.getValue();
//                inventoryConfig.setValue(String.join("#/#",strings));
//            }
            inventoryConfig.setValue(String.join(SPLIT_TAG,req.getValue()));
            configMapper.insert(inventoryConfig);
        });
    }

    public List<InventoryWaringDTO> inventoryWaring(List<SortReq> sortReqs, List<SearchReq> searchReqs){
        String filter = buildConfigFilterSql();
        String sort = buildSortSql(sortReqs);
        String search = buildSearchSql(searchReqs);
        return baseMapper.getInventoryWaringRes(filter,sort,search);
    }

    public List<ValidityWaringDTO> validityWaring(List<SortReq> sortReqs, List<SearchReq> searchReqs){
        String filter = buildConfigFilterSql();
        String sort = buildSortSql(sortReqs);
        String search = buildSearchSql(searchReqs);
        return baseMapper.getValidityWaringRes(filter,sort,search);
    }

    private String buildSearchSql(List<SearchReq> reqs){
        if (CollectionUtil.isEmpty(reqs)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("where ");
        reqs.forEach(req->{
            switch (req.getSearchName()){
                case "brandName":{
                    buildSearch(req, "brand_name", sb);
                    break;
                }
                case "specNo":{
                    buildSearch(req, "spec_no", sb);
                    break;
                }
                case "goodsName":{
                    buildSearch(req, "goods_name", sb);
                    break;
                }
                case "specName":{
                    buildSearch(req, "spec_name", sb);
                    break;
                }
                case "inventoryNum":{
                    buildSearch(req, "inventoryNum", sb);
                    break;
                }
                case "turnoverDays":{
                    buildSearch(req, "turnoverDays", sb);
                    break;
                }
                case "groupType":{
                    buildSearch(req, "groupType", sb);
                    break;
                }
                case "waringLevel":{
                    buildSearch(req, "waringLevel", sb);
                    break;
                }
                case "waring1Num":{
                    buildSearch(req, "waring1Num", sb);
                    break;
                }
                case "waring2Num":{
                    buildSearch(req, "waring2Num", sb);
                    break;
                }
                case "waring3Num":{
                    buildSearch(req, "waring3Num", sb);
                    break;
                }
                default:throw new RuntimeException("error searchName");
            }
        });
        sb.delete(sb.length()-3, sb.length());
        return sb.toString();
    }

    private void buildSearch(SearchReq req, String field, StringBuilder sb){
        if ("like".equals(req.getSearchType())){
            sb.append(" "+field+" like '%"+req.getSearchValue()+"%'");
        }else if ("between".equals(req.getSearchType())){
            String[] value = req.getSearchValue().split(SPLIT_TAG);
            sb.append(" "+field+" <= "+value[1]+" and "+field+" >= "+value[0]);
        }
        sb.append(" and");
    }

    private String buildSortSql(List<SortReq> reqs){
        if (CollectionUtil.isEmpty(reqs)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("order by");
        reqs.forEach(req->{
            switch (req.getSortName()){
                case "brandName":{
                    sb.append(" brand_name ").append(req.getSortType()).append(",");
                    break;
                }
                case "specNo":{
                    sb.append(" spec_no ").append(req.getSortType()).append(",");
                    break;
                }
                case "goodsName":{
                    sb.append(" goods_name ").append(req.getSortType()).append(",");
                    break;
                }
                case "specName":{
                    sb.append(" spec_name ").append(req.getSortType()).append(",");
                    break;
                }
                case "inventoryNum":{
                    sb.append(" inventoryNum ").append(req.getSortType()).append(",");
                    break;
                }
                case "turnoverDays":{
                    sb.append(" turnoverDays ").append(req.getSortType()).append(",");
                    break;
                }
                case "groupType":{
                    sb.append(" groupType ").append(req.getSortType()).append(",");
                    break;
                }
                case "waringLevel":{
                    sb.append(" FIELD(waringLevel, 'green', 'yellow', 'red') ").append(req.getSortType()).append(",");
                    break;
                }
                case "waring1Num":{
                    sb.append(" waring1Num ").append(req.getSortType()).append(",");
                    break;
                }
                case "waring2Num":{
                    sb.append(" waring2Num ").append(req.getSortType()).append(",");
                    break;
                }
                case "waring3Num":{
                    sb.append(" waring3Num ").append(req.getSortType()).append(",");
                    break;
                }
                default:throw new RuntimeException("error sortName");
        }
        });
        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
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
