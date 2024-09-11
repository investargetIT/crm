package com.cyanrocks.wms.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyanrocks.wms.constants.InventoryFieldsEnum;
import com.cyanrocks.wms.dao.entity.InventoryTurnoverCoefficient;
import com.cyanrocks.wms.dao.entity.InventoryValidgoods;
import com.cyanrocks.wms.service.InventoryService;
import com.cyanrocks.wms.vo.request.InventoryConfigReq;
import com.cyanrocks.wms.vo.request.SearchReq;
import com.cyanrocks.wms.vo.request.TurnoverCoefficientReq;
import com.cyanrocks.wms.vo.request.SortReq;
import com.cyanrocks.wms.vo.response.FieldsDTO;
import com.cyanrocks.wms.vo.response.InventoryConfigVO;
import com.cyanrocks.wms.vo.response.InventoryWaringDTO;
import com.cyanrocks.wms.vo.response.ValidityWaringDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wjq
 * @Date 2024/7/23 16:03
 */
@RestController
@RequestMapping("/inventory")
@Api(tags = {"库存接口"})
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/config")
    @ApiOperation(value = "获取参数")
    public List<InventoryConfigVO> getConfig() {
        return service.getConfig();
    }

    @PostMapping("/config")
    @ApiOperation(value = "配置参数")
    public void setConfig(@RequestBody List<InventoryConfigReq> reqs) {
        service.setConfig(reqs);
    }

    @GetMapping("/turnoverCoefficient")
    @ApiOperation(value = "获取库存周转系数")
    public List<InventoryTurnoverCoefficient> getTurnoverCoefficient() {
        return service.getTurnoverCoefficient();
    }


    @PostMapping("/turnoverCoefficient")
    @ApiOperation(value = "配置库存周转系数")
    public void setTurnoverCoefficient(@RequestBody List<TurnoverCoefficientReq> reqs) {
        service.setTurnoverCoefficient(reqs);
    }

    @GetMapping("/inventory-waring")
    @ApiOperation(value = "库存预警列表")
    public List<InventoryWaringDTO> inventoryWaring(@RequestParam(value="sortStr",required=false) String sortStr,
                                                    @RequestParam(value="searchStr",required=false) String searchStr){
        List<SortReq> sortReqs = JSONObject.parseArray(sortStr, SortReq.class);
        List<SearchReq> searchReqs = JSONObject.parseArray(searchStr, SearchReq.class);
        return service.inventoryWaring(sortReqs,searchReqs);
    }

    @GetMapping("/validity-waring")
    @ApiOperation(value = "有效期预警列表")
    public List<ValidityWaringDTO> validityWaring(@RequestParam(value="sortStr",required=false) String sortStr,
                                                  @RequestParam(value="searchStr",required=false) String searchStr){
        List<SortReq> reqs = JSONObject.parseArray(sortStr, SortReq.class);
        List<SearchReq> searchReqs = JSONObject.parseArray(searchStr, SearchReq.class);
        return service.validityWaring(reqs,searchReqs);
    }

    @GetMapping("/fields")
    @ApiOperation(value = "字段列表")
    public List<FieldsDTO> getFields(){
        List<FieldsDTO> dtos = new ArrayList<>();
        for (InventoryFieldsEnum fieldsEnum : InventoryFieldsEnum.values()) {
            FieldsDTO dto = new FieldsDTO();
            dto.setLabel(fieldsEnum.getFieldsCn());
            dto.setValue(fieldsEnum.getFieldsEn());
            dtos.add(dto);
        }
        return dtos;
    }

    @PostMapping("/info")
    @ApiOperation(value = "新增信息")
    public void setInfo(@RequestBody List<InventoryValidgoods> reqs) {
        service.setInfo(reqs);
    }
}
