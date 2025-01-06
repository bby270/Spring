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
    List<Employee>getMostRecentEmployees();
    @Query("SELECT e FROM Employee e WHERE e.hiredate = :hiredate")
   List<Employee>getEmployeesByHiredate(LocalDate hiredate1);
}
