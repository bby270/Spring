package com.dw.jdbcapp.repository;

import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.model.Order;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class EmployeeRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "select * from 사원";
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getString("사원번호"));
                employee.setEnglishName(resultSet.getString("영문이름"));
                employee.setName(resultSet.getString("이름"));
                employee.setPosition(resultSet.getString("직위"));
                employee.setGender(resultSet.getString("성별"));
                employee.setBirthDate(LocalDate.parse(resultSet.getString("생일")));
                employee.setHireDate(LocalDate.parse(resultSet.getString("입사일")));
                employee.setAddress(resultSet.getString("주소"));
                employee.setCity(resultSet.getString("도시"));
                employee.setRegion(resultSet.getString("지역"));
                employee.setHomePhone(resultSet.getString("집전화"));
                employee.setSupervisorId(resultSet.getString("상사번호"));
                employee.setDepartmentId(resultSet.getString("부서번호"));
                employees.add(employee);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    public Employee getEmployeeById(String id) {
        Employee employee = new Employee();
        String query = "select * from 사원 where 사원번호 = ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query)
        ){
            pstmt.setString(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    employee.setEmployeeId(resultSet.getString("사원번호"));
                    employee.setEnglishName(resultSet.getString("영문이름"));
                    employee.setName(resultSet.getString("이름"));
                    employee.setPosition(resultSet.getString("직위"));
                    employee.setGender(resultSet.getString("성별"));
                    employee.setBirthDate(LocalDate.parse(resultSet.getString("생일")));
                    employee.setHireDate(LocalDate.parse(resultSet.getString("입사일")));
                    employee.setAddress(resultSet.getString("주소"));
                    employee.setCity(resultSet.getString("도시"));
                    employee.setRegion(resultSet.getString("지역"));
                    employee.setHomePhone(resultSet.getString("집전화"));
                    employee.setSupervisorId(resultSet.getString("상사번호"));
                    employee.setDepartmentId(resultSet.getString("부서번호"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Map<String,Object>> getEmployeesWithDepartName(){
        String query = "select 이름, 입사일, 부서명 from 사원 "
                + "inner join 부서 on 사원.부서번호 = 부서.부서번호";
        List<Map<String, Object>> employees = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while(resultSet.next()) {
                Map<String, Object> employee = new HashMap<>();
                employee.put("이름", resultSet.getString("이름"));
                employee.put("입사일", resultSet.getString("입사일"));
                employee.put("부서명", resultSet.getString("부서명"));
                employees.add(employee);
            }
            for (Map<String,Object> employee : employees) {
                System.out.println(employee);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    public Employee getOrderByNumber(String number) {
       Employee employee = new Employee();
        String query = "select * from 주문 where 주문번호 = ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query)
        ){
            pstmt.setString(1, number);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    order.setOrderId(resultSet.getString("주문번호"));
                    order.setCustomerId(resultSet.getString("고객번호"));
                    order.setEmployeeId(resultSet.getString("사원번호"));
                    order.setOrderDate(LocalDate.parse(resultSet.getString("주문일")));
                    order.setRequestDate(LocalDate.parse(resultSet.getString("요청일")));
                    order.setShippingDate(LocalDate.parse(resultSet.getString("발송일")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
