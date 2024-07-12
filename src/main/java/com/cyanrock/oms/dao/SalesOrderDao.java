package com.cyanrock.oms.dao;

import com.cyanrock.oms.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderDao extends JpaRepository<SalesOrder,Integer> {
}
