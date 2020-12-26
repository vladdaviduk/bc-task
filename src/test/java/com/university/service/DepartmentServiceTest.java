package com.university.service;

import com.university.exceptions.EntityNotFoundException;
import com.university.model.Department;
import com.university.model.DepartmentLector;
import com.university.model.Lector;
import com.university.model.enums.Degree;
import com.university.model.pk.DepartmentLectorPk;
import com.university.repository.DepartmentRepository;
import com.university.service.impl.DepartmentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class DepartmentServiceTest {

    private static final String NONEXISTENT_NAME = "Kenelm Fletcher";
    private static final String DEPARTMENT_NAME = "History";
    private static final String DEPARTMENT_HEAD = "Eva Hart";
    private static final String LECTOR_NAME = "Harold Thompson";
    private static final Degree LECTOR_DEGREE = Degree.ASSISTANT;
    private static final double LECTOR_SALARY = 1000;

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void testDepartmentService() {
        assertEquals(departmentService.getDepartment(DEPARTMENT_NAME).getName(), DEPARTMENT_NAME);
        assertEquals(departmentService.getHeadOfDepartment(DEPARTMENT_NAME), DEPARTMENT_HEAD);

        DepartmentLector departmentLector = departmentService.getDepartmentLectors(DEPARTMENT_NAME).get(0);
        assertEquals(departmentLector.getLector().getName(), LECTOR_NAME);
        assertEquals(departmentLector.getLector().getDegree(), LECTOR_DEGREE);
        assertEquals(departmentLector.getSalary(), LECTOR_SALARY, 0.0);

        assertThrows(EntityNotFoundException.class, () -> departmentService.getDepartment(NONEXISTENT_NAME));
    }

    @Before
    public void setUp() {
        doReturn(Optional.of(departmentInstance())).when(departmentRepository).findByName(DEPARTMENT_NAME);
    }

    private Department departmentInstance() {

        int id = 1;

        Department department = new Department(id, DEPARTMENT_NAME, DEPARTMENT_HEAD);
        Lector lector = new Lector(id, LECTOR_NAME, LECTOR_DEGREE);
        DepartmentLector departmentLector =
                new DepartmentLector(new DepartmentLectorPk(id, id), department, lector, LECTOR_SALARY);
        department.setDepartmentLectors(Collections.singletonList(departmentLector));
        lector.setDepartmentLectors(Collections.singletonList(departmentLector));

        return department;
    }

    @TestConfiguration
    static class DepartmentServiceContextConfiguration {

        @Bean
        public DepartmentService departmentService() {
            return new DepartmentServiceImpl();
        }
    }
}
