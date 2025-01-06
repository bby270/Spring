package com.dw.companyapp.repository;

import com.dw.companyapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("select o from Order o where o.customer.id = :customerId and o.orderId " +
            "in (select orderId from OrderDetail od where od.product.Id =:productNumber )")
    List<Order> findByProductNumberPosition(int productNumber, String customerId);
}