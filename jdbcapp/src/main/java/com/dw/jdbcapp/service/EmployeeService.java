package com.dw.jdbcapp.service;

import com.dw.jdbcapp.dto.EmployeeDepartmentDTO;
import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    @Qualifier("employeeTemplateRepository")
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }
    public Employee getEmployeeById(String id) {
        return employeeRepository.getEmployeeById(id);
    }
    public List<Map<String,Object>> getEmployeesWithDepartName(){
        return employeeRepository.getEmployeesWithDepartName();
    }
    public List<EmployeeDepartmentDTO> getEmployeesWithDepartName2(){
        List<EmployeeDepartmentDTO> employeeDepartmentDTOList = new ArrayList<>();
        List<Map<String,Object>> mapList = employeeRepository.getEmployeesWithDepartName();
        for (Map<String,Object> data : mapList) {
            EmployeeDepartmentDTO temp = new EmployeeDepartmentDTO(
                    LocalDate.parse((String)data.get("입사일")),
                    (String) data.get("부서명"),
                    (String) data.get("이름")
            );
            employeeDepartmentDTOList.add(temp);
        }
        return employeeDepartmentDTOList;
    }
    public List<Employee> getEmployeeByNumber(String number, String position) {
        List<Employee> employees = employeeRepository.getEmployeeByNumber(number, position);
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("직위 또는 사원번호가 없습니다: " + number + ", " + position);
        }
        return employees;
    }
    public Employee saveemployee (Employee employee) {
        return employeeRepository.saveemployee(employee);
    }

    public List<Employee> getEmployeeByDate (String date) {
        return employeeRepository.getEmployeeByDate(date);
    }

    public List<Employee> getEmployeesByHiredate(String hiredate) {
        if (hiredate.equals("0")) {
            return employeeRepository.getEmployeesByHiredate1();
        }else {
            try {
                LocalDate hiredate2 = LocalDate.parse(hiredate);
                return employeeRepository.getEmployeesByHiredate(hiredate2.toString());
            } catch (DateTimeException e) {
                throw new InvalidRequestException("입력하신 입사일이 올바르지 않습니다:" + hiredate);
            }
        }
    }
}
