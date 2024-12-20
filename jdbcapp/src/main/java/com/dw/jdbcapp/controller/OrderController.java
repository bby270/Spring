package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/find-all-orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(),
                HttpStatus.OK);
    }
    @GetMapping("/orders/{orderNumber}")
    public ResponseEntity<Order> getOrderByNumber(@PathVariable String orderNumber) {
        return new ResponseEntity<>(orderService.getOrderByNumber(orderNumber),
                HttpStatus.OK);
    }
    @GetMapping("/orders/{productNumber}/{customerId}")
    public ResponseEntity<List<Order>> getOrderProductNumber (@PathVariable String productNumber, @PathVariable String customerId) {
        return new ResponseEntity<>(orderService.getOrderProductNumber (productNumber, customerId),
                HttpStatus.OK);
    }
}