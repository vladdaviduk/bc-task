package com.university.handler.command;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandResolverTest {

    private static final String DEPARTMENT_NAME = "Biochemistry";
    private static final Command.Request HEAD_OF_REQUEST_TYPE = Command.Request.HEAD_OF;

    private static final String HEAD_OF_REQUEST = String.format("Who is head of department %s", DEPARTMENT_NAME);
    private static final String STATISTICS_REQUEST = String.format("Show %s statistics", DEPARTMENT_NAME);
    private static final String INVALID_REQUEST = "Not valid request";
    private static final String INVALID_PARAMETER = "Show *** statistics";

    private final CommandResolver resolver = new CommandResolver();

    @Test
    public void testCommandResolver() {
        Command command = resolver.resolve(HEAD_OF_REQUEST);
        assertEquals(command.getParameter(), DEPARTMENT_NAME);
        assertEquals(command.getRequest(), HEAD_OF_REQUEST_TYPE);

        assertEquals(resolver.resolve(STATISTICS_REQUEST).getParameter(), DEPARTMENT_NAME);

        assertThrows(RuntimeException.class, () -> resolver.resolve(INVALID_REQUEST));
        assertThrows(RuntimeException.class, () -> resolver.resolve(INVALID_PARAMETER));
    }
}
