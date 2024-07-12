package com.cyanrock.oms.controller;

import com.cyanrock.oms.dao.SalesOrderDao;
import com.cyanrock.oms.entity.SalesOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class SalesOrderController {
    private SalesOrderDao salesOrderRepository;

    @GetMapping("/all")
    public @ResponseBody Iterable<SalesOrder> getAllSalesOrder() {
        return salesOrderRepository.findAll();
    }

}