package com.university.service.impl;

import com.university.model.Department;
import com.university.model.Lector;
import com.university.repository.DepartmentRepository;
import com.university.repository.LectorRepository;
import com.university.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LectorRepository lectorRepository;

    public List<String> search(String template) {

        List<String> matchedNames = new ArrayList<>();

        matchedNames.addAll(departmentRepository.findByNameContaining(template)
                .stream().map(Department::getName).collect(toList()));
        matchedNames.addAll(lectorRepository.findByNameContaining(template)
                .stream().map(Lector::getName).collect(toList()));

        return matchedNames;
    }
}
