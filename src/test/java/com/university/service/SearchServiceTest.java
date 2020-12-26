package com.university.service;

import com.university.model.Lector;
import com.university.model.enums.Degree;
import com.university.repository.DepartmentRepository;
import com.university.repository.LectorRepository;
import com.university.service.impl.SearchServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class SearchServiceTest {

    private static final String LECTOR_NAME = "Harold Thompson";
    private static final String TEMPLATE = "Harold";
    private static final String FALSE_TEMPLATE = "Henry";

    @Autowired
    private SearchService searchService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private LectorRepository lectorRepository;

    @Test
    public void testSearchService() {
        assertEquals(searchService.search(TEMPLATE).get(0), LECTOR_NAME);
        assertTrue(searchService.search(FALSE_TEMPLATE).isEmpty());
    }

    @Before
    public void setUp() {
        doReturn(Collections.singletonList(new Lector(1, LECTOR_NAME, Degree.ASSISTANT)))
                .when(lectorRepository).findByNameContaining(TEMPLATE);
    }

    @TestConfiguration
    static class SearchServiceContextConfiguration {

        @Bean
        public SearchService searchService() {
            return new SearchServiceImpl();
        }
    }
}
