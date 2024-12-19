package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.service.CustomerService;
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
    public ResponseEntity <List<Order>> getAllOrders() {
        return new ResponseEntity<>( orderService.getAllOrders(),
                HttpStatus.OK);
    }

    // 과제 1-2 주문번호를 기준으로 주문 정보를 조회하는 API
    @GetMapping("/orders/{orderNumber}")
    public  ResponseEntity<Order> getOrderById(@PathVariable String orderNumber) {
        return  new ResponseEntity<>(orderService.getOrderById(orderNumber),
                HttpStatus.ACCEPTED);
    }

    // 과제 1-4 제품번호와 고객번호를 기준으로 해당 제품을 주문한 특정 고객의 주문 내역을 조회하는 API
    @GetMapping("/orders/{productNumber}/{customerId}")
    public ResponseEntity <List<Order>> getOrderByIdAndCustomer(@PathVariable int productNumber, @PathVariable String customerId) {
        return  new ResponseEntity<>(orderService.getOrderByIdAndCustomer(productNumber, customerId),
                HttpStatus.ALREADY_REPORTED);
    }
}