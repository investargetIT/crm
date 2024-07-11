package com.cyanrock.oms.dao;

import com.cyanrock.oms.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderRepository  extends JpaRepository<SalesOrder,Integer> {
}
