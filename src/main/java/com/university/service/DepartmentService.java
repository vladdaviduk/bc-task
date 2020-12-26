package com.university.service;

import com.university.model.Department;
import com.university.model.DepartmentLector;

import java.util.List;

public interface DepartmentService {

    Department getDepartment(String department);

    String getHeadOfDepartment(String department);

    List<DepartmentLector> getDepartmentLectors(String department);

}
