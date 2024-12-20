package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderTemplateRepository implements OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Order> orderRowMapper = new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setOrderId(rs.getString("주문번호"));
            order.setCustomerId(rs.getString("고객번호"));
            order.setEmployeeId(rs.getString("사원번호"));
            order.setOrderDate(LocalDate.parse(rs.getString("주문일")));
            order.setRequestDate(LocalDate.parse(rs.getString("요청일")));
            order.setShippingDate(LocalDate.parse(rs.getString("발송일")));
            return order;
        }
    };
    @Override
    public List<Order> getAllOrders() {
        String query = "select * from 주문";
        return jdbcTemplate.query(query, orderRowMapper);
    }

    @Override
    public Order getOrderByNumber(String number) {
        String query = "select * from 주문 where 주문번호 = ?";
        try {
            return jdbcTemplate.queryForObject(query, orderRowMapper, number);
        }catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("주문번호가 올바르지 않습니다: " + number);
        }
    }

    @Override
    public List<Order> getOrderProductNumber(String number, String id) {
        String query = "select * from 제품 where 제품번호 = ?  and 주문번호 = ?";
        return jdbcTemplate.query(query, orderRowMapper, number, id);
    }
}