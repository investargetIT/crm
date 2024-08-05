package com.cyanrocks.wms.dao;

import com.cyanrocks.wms.dao.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderDao extends JpaRepository<SalesOrder,Integer> {
}
