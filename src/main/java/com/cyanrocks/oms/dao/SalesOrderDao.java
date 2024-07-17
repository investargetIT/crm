package com.cyanrocks.oms.dao;

import com.cyanrocks.oms.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderDao extends JpaRepository<SalesOrder,Integer> {
}
