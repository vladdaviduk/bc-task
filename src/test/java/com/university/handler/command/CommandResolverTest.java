package com.university.handler.command;

import org.junit.Test;

import static com.university.handler.command.Command.Request.*;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandResolverTest {

    private static final String DEPARTMENT_NAME = "Law";

    private static final String HEAD_OF_REQUEST = format("Who is head of department %s", DEPARTMENT_NAME);
    private static final String STATISTICS_REQUEST = format("Show %s statistics", DEPARTMENT_NAME);
    private static final String AVERAGE_SALARY_REQUEST =
            format("Show the average salary for the department %s", DEPARTMENT_NAME);
    private static final String EMPLOYEE_COUNT_REQUEST = format("Show count of employee for %s", DEPARTMENT_NAME);
    private static final String GLOBAL_SEARCH_REQUEST = format("Global search by %s", DEPARTMENT_NAME);

    private static final String INVALID_REQUEST = "Not valid request";
    private static final String INVALID_PARAMETER = "Show *** statistics";

    private final CommandResolver resolver = new CommandResolver();

    @Test
    public void testCommandResolver() {
        Command headOfCommand = resolver.resolve(HEAD_OF_REQUEST);
        assertEquals(headOfCommand.getParameter(), DEPARTMENT_NAME);
        assertEquals(headOfCommand.getRequest(), HEAD_OF);

        Command statisticsCommand = resolver.resolve(STATISTICS_REQUEST);
        assertEquals(statisticsCommand.getParameter(), DEPARTMENT_NAME);
        assertEquals(statisticsCommand.getRequest(), STATISTIC);

        Command salaryCommand = resolver.resolve(AVERAGE_SALARY_REQUEST);
        assertEquals(salaryCommand.getParameter(), DEPARTMENT_NAME);
        assertEquals(salaryCommand.getRequest(), AVERAGE_SALARY);

        Command empCountCommand = resolver.resolve(EMPLOYEE_COUNT_REQUEST);
        assertEquals(empCountCommand.getParameter(), DEPARTMENT_NAME);
        assertEquals(empCountCommand.getRequest(), EMPLOYEE_COUNT);

        Command searchCommand = resolver.resolve(GLOBAL_SEARCH_REQUEST);
        assertEquals(searchCommand.getParameter(), DEPARTMENT_NAME);
        assertEquals(searchCommand.getRequest(), GLOBAL_SEARCH);


        assertThrows(RuntimeException.class, () -> resolver.resolve(INVALID_REQUEST));
        assertThrows(RuntimeException.class, () -> resolver.resolve(INVALID_PARAMETER));
    }
}
