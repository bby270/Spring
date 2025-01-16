package com.dw.driverapp.repository;

import com.dw.driverapp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
