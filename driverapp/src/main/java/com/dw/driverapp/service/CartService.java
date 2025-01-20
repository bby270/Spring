package com.dw.driverapp.service;

import com.dw.driverapp.dto.CartDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.model.Cart;
import com.dw.driverapp.model.Subject;
import com.dw.driverapp.model.User;
import com.dw.driverapp.repository.CartRepository;
import com.dw.driverapp.repository.SubjectRepository;
import com.dw.driverapp.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubjectRepository subjectRepository;


    // 유저 -> 모든 장바구니 목록 조회
    public List<CartDTO> getAllCart() {
        return cartRepository.findAll().stream().map(Cart::ToDto).toList();
    }

    // 유저 -> 특정 유저 장부구니 조회
    public List<CartDTO> findUserName(String username) {
        return cartRepository.findByUserUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("없음"))
                .stream()
                .map(Cart::ToDto)
                .toList();
    }
    public CartDTO addSubjectToCart(String username, Long subjectId) {
        User user = userRepository.findById(username).orElseThrow(() -> new ResourceNotFoundException("해당 유저가 존재하지 않습니다."));
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("해당 과목이 존재하지 않습니다."));
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setSubject(subject);
        Cart savedCart = cartRepository.save(cart);
        return savedCart.ToDto();
    }



}