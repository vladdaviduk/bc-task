package com.university.service.impl;

import com.university.exceptions.EntityNotFoundException;
import com.university.model.Department;
import com.university.model.DepartmentLector;
import com.university.repository.DepartmentRepository;
import com.university.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department getDepartment(String department) {
        return departmentRepository.findByName(department)
                .orElseThrow(() -> new EntityNotFoundException(Department.class, department));
    }

    public String getHeadOfDepartment(String department) {
        return getDepartment(department).getHead();
    }

    public List<DepartmentLector> getDepartmentLectors(String department) {
        return getDepartment(department).getDepartmentLectors();
    }
}
