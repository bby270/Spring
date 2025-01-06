package com.dw.companyapp.repository;

import com.dw.companyapp.dto.EmployeeDepartmentDTO;
import com.dw.companyapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("select e.hireDate, d.departmentName, e.name " +
            "from Employee e join e.department d")
    List<Object[]> getEmployeesWithDepartName();

    @Query("select new com.dw.companyapp.dto.EmployeeDepartmentDTO(e.hireDate, d.departmentName, e.name) " +
            "from Employee e join e.department d")
    List<EmployeeDepartmentDTO> getEmployeesWithDepartName2();
    @Query("select e from Employee e where e.hireDate > :hireDate")
    List<Employee> findByHireDate(LocalDate hireDate);

    @Query("select e from Employee e order by e.hireDate desc limit 1")
    List<Employee> findByHireDate2();
}

