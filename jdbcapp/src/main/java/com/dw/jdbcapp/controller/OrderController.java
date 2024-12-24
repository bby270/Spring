package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.dto.OrderRequestDTO;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/orders")
    public ResponseEntity<OrderRequestDTO> saveOrder(
            @RequestBody OrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(
        orderService.saveOrder(orderRequestDTO),
                HttpStatus.OK);
    }

    @PutMapping("/orders/update")
    public ResponseEntity<String> getOrderByUpdate (@RequestParam String id,@RequestParam String date) {
        return new ResponseEntity<>(orderService.getOrderByUpdate(id,date),
                HttpStatus.OK);
    }

    @GetMapping("/orders/city/orderamount/{limit}")
    public ResponseEntity<List<Map<String,Integer>>> getTopCitiesByTotalOrderAmount(@PathVariable int limit){
        return new ResponseEntity<>(orderService.getTopCitiesByTotalOrderAmount(limit),
                HttpStatus.OK);
    }

    @GetMapping("/orders/ordercount/year/{city}")
    public ResponseEntity<List<Map<String,Integer>>> getOrderCountByYearForCity(@PathVariable String city) {
        return new ResponseEntity<>(orderService.getOrderCountByYearForCity(city),
                HttpStatus.OK);
    }

    @PostMapping("/post/orders")
    public ResponseEntity<OrderRequestDTO> getsaveOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return new ResponseEntity<>(
                orderService.getsaveOrder(orderRequestDTO),HttpStatus.OK
        );
    }
}