package com.dw.jdbcapp.repository.iface;


import com.dw.jdbcapp.model.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrders();
    public Order getOrderByNumber(String number);
    public List<Order> getOrderProductNumber(String number, String id);
    int saveOrder(Order order);
}