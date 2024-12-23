package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.dto.EmployeeDepartmentDTO;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/find-all-employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(),
                HttpStatus.CONFLICT);
    }
    // Query Parameters (쿼리 문자열)
    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam String id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id),
                HttpStatus.CONFLICT);
    }
    // Path Parameters(경로 매개변수)
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById_2(@PathVariable String id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id),
                HttpStatus.CONFLICT);
    }

    @GetMapping("/employees/department")
    public ResponseEntity<List<Map<String, Object>>> getEmployeesWithDepartName() {
        return new ResponseEntity<>(employeeService.getEmployeesWithDepartName(),
                HttpStatus.CONFLICT);
    }
    @GetMapping("/employees/department2")
    public ResponseEntity<List<EmployeeDepartmentDTO>> getEmployeesWithDepartName2() {
        return new ResponseEntity<>(employeeService.getEmployeesWithDepartName2(),
                HttpStatus.CONFLICT);
    }
    @GetMapping("/employees/{departmentNumber}/{position}")
    public ResponseEntity<List<Employee>> getEmployeeByNumber(@PathVariable String departmentNumber, @PathVariable String position) {
        return new ResponseEntity<>(employeeService.getEmployeeByNumber(departmentNumber, position),
                HttpStatus.CONFLICT);
    }
    @PostMapping("/post/employee")
    public ResponseEntity<Employee> saveemployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveemployee(employee),
                HttpStatus.CONFLICT);
    }

    @GetMapping("/employee/date/{date}")
    public ResponseEntity<List<Employee>> getEmployeeByDate(@PathVariable String date) {
        return new ResponseEntity<>(employeeService.getEmployeeByDate(date),
                HttpStatus.CONFLICT);
    }

    @GetMapping("/employees/hiredate/{hiredate}")
    public ResponseEntity<List<Employee>>  getEmployeesByHiredate(@PathVariable String hiredate) {
        return new ResponseEntity<>(employeeService.getEmployeesByHiredate(hiredate),
                HttpStatus.OK);
    }


}