package com.asiainfo.order.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {

    public List<Map<String,Object>> queryOrderList(Map map);

    public int updateOrderState(Map map);

    public int addOrder(Map map);
}
