package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> getAllDepartments();
    public Department saveDepartment (Department department);
    public Department updateDepartment(Department department);
    public String deleteDepartment(String id);
}
