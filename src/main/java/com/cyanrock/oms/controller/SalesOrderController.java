package com.cyanrock.oms.controller;

import com.cyanrock.oms.dao.SalesOrderDao;
import com.cyanrock.oms.entity.SalesOrder;

import  com.cyanrock.oms.common.vo.api.ApiData;
import com.cyanrock.oms.common.constant.OrderServiceNames;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class SalesOrderController {
    @Resource
    private SalesOrderDao salesOrderRepository;

    @GetMapping("/all")
    public @ResponseBody Iterable<SalesOrder> getAllSalesOrder() {
        return salesOrderRepository.findAll();
    }

}