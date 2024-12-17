package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
    public Employee getEmployeeById(String id);
    public List<Map<String, Object>> getEmployeesWithDepartName();
    public Employee getEmployeeByNumber(String number,String position);
    public Employee saveemployee(Employee employee);
}
