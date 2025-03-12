package com.dw.driverapp.controller;

import com.dw.driverapp.dto.CartDTO;
import com.dw.driverapp.dto.EnrollmentDTO;
import com.dw.driverapp.exception.ResourceNotFoundException;
import com.dw.driverapp.exception.UnauthorizedUserException;
import com.dw.driverapp.model.Cart;
import com.dw.driverapp.model.Enrollment;
import com.dw.driverapp.model.User;
import com.dw.driverapp.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class CartController {
    @Autowired
    CartService cartService;


    // 유저 -> 로그인한 회원 장바구니 목록 조회
    @GetMapping("/cart/all")
    public ResponseEntity<List<CartDTO>> getAllCart(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new ResourceNotFoundException("로그인한 사용자만 장바구니 조회가 가능합니다.");
        }
        return new ResponseEntity<>(cartService.getAllCart(), HttpStatus.OK);
    }

    // 유저 -> 로그인한 회원이 특정 유저 장바구니 조회
    @GetMapping("/cart/user/{username}")
    private ResponseEntity<List<CartDTO>> findUserName(@PathVariable String username, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new ResourceNotFoundException("로그인한 사용자만 장바구니 조회가 가능합니다.");
        }
        return new ResponseEntity<>(cartService.findUserName(username), HttpStatus.OK);
    }

    // 유저 - 로그인 중인 사용자의 이름으로 장바구니 추가
    @PostMapping("/user/add")
    public ResponseEntity<CartDTO> addSubjectToCart(@RequestParam String username, @RequestParam Long subjectId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new ResourceNotFoundException("로그인한 사용자만 장바구니에 과목을 추가할 수 있습니다.");
        }
        CartDTO cartDTO = cartService.addSubjectToCart(username, subjectId);
        return new ResponseEntity<>(cartDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/delete/cart-items")
    public ResponseEntity<String> deleteCartItems(@RequestBody List<Long> cartIds, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new ResourceNotFoundException("로그인한 사용자만 장바구니에서 과목을 삭제할 수 있습니다.");
        }

        String username = (String) session.getAttribute("username");

        // 여러 장바구니 항목 삭제 처리
        cartService.deleteCartItems(cartIds, username);

        return new ResponseEntity<>("삭제가 완료되었습니다.", HttpStatus.OK);
    }



    @PostMapping("/cart/enrollment")
    public ResponseEntity<String> cartEnrollment(@RequestBody List<Long> cartIds, HttpServletRequest request) {
        String loggedInUsername = (String) request.getSession().getAttribute("username");
        if (loggedInUsername == null) {
            throw new UnauthorizedUserException("로그인한 사용자만 장바구니를 구매할 수 있습니다.");
        }
        cartService.cartEnrollment(loggedInUsername, cartIds);  // 여러 ID를 전달
        return new ResponseEntity<>("장바구니 구매가 완료되었습니다.", HttpStatus.OK);
    }


    @GetMapping("/cart/login")
    public  ResponseEntity<List<CartDTO>> cartFindLoginUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            throw new ResourceNotFoundException("로그인한 사용자만 조회가 가능합니다.");
        }
        String username = (String) session.getAttribute("username");
        return new ResponseEntity<>(cartService.cartFindLoginUsername(username), HttpStatus.OK);
    }
}