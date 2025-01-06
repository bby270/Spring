package com.dw.companyapp.service;

import com.dw.companyapp.model.Department;
import com.dw.companyapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> saveDepartmentList(
            List<Department> departmentList) {
        return departmentRepository.saveAll(departmentList);
    }

    public Department updateDepartment(Department department) {
        return departmentRepository.findById(department.getDepartmentId())
                .map(department1 -> {
                    department1.setDepartmentName(department.getDepartmentName());
                    return departmentRepository.save(department1);
                })
                .orElseThrow(() -> new RuntimeException("찾을 수 없습니다.:" + department.getDepartmentId()));


    }

    public String deleteDepartment(String id) {
        return departmentRepository.findById(id)
                .map(department -> {
                    departmentRepository.delete(department);
                    return id + "가 삭제되었습니다.";
                })
                .orElseThrow(()-> new RuntimeException(id + "에 해당 부서를 찾을 수 없습니다."));
    }
}







