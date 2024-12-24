package com.dw.jdbcapp.repository.iface;


import com.dw.jdbcapp.dto.OrderRequestDTO;
import com.dw.jdbcapp.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    List<Order> getAllOrders();
    public Order getOrderByNumber(String number);
    public List<Order> getOrderProductNumber(String number, String id);
    int saveOrder(Order order);
    String getOrderByUpdate (String id,String date);
    List<Map<String,Integer>> getTopCitiesByTotalOrderAmount(int limit);
    List<Map<String,Object>>getOrderCountByYearForCity(String city);
  }