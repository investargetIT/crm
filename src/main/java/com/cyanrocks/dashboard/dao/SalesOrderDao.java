package com.cyanrocks.dashboard.dao;

import com.cyanrocks.dashboard.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderDao extends JpaRepository<SalesOrder,Integer> {
}
