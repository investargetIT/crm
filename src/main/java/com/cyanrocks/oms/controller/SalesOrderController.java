package com.cyanrocks.oms.controller;

import com.cyanrocks.common.vo.api.ApiData;
import com.cyanrocks.oms.dao.SalesOrderDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class SalesOrderController {
    @Resource
    private SalesOrderDao salesOrderRepository;

    @GetMapping("/all")
    public ApiData getAllSalesOrder() {
       // return salesOrderRepository.findAll();
        return new ApiData(444, "接口临时关闭,等候开放..");
    }

}