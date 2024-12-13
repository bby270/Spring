package com.dw.jdbcapp.dto;

import java.time.LocalDate;

public class EmployeeDepartmentDTO {
    private LocalDate hireDate;
    private String departmentName;
    private String employeesName;

    public EmployeeDepartmentDTO() {
    }

    public EmployeeDepartmentDTO(LocalDate hireDate, String departmentName, String employeesName) {
        this.hireDate = hireDate;
        this.departmentName = departmentName;
        this.employeesName = employeesName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(String employeesName) {
        this.employeesName = employeesName;
    }

    @Override
    public String toString() {
        return this.hireDate + ", " + this.departmentName + ", " +
                this.employeesName;
    }
}