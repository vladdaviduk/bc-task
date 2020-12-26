package com.university.handler.command;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.university.handler.command.Command.Request.*;

public class CommandResolver {

    private static final String HEAD_OF_TEMPLATE = "Who is head of department %s";
    private static final String STATISTICS_TEMPLATE = "Show %s statistics";
    private static final String AVERAGE_SALARY_TEMPLATE = "Show the average salary for the department %s";
    private static final String EMPLOYEE_COUNT_TEMPLATE = "Show count of employee for %s";
    private static final String GLOBAL_SEARCH_TEMPLATE = "Global search by %s";

    public Command resolve(String input) {

        if (isMatch(input, HEAD_OF_TEMPLATE))
            return new Command(HEAD_OF, getFirstParameter(input, HEAD_OF_TEMPLATE));
        else if (isMatch(input, STATISTICS_TEMPLATE))
            return new Command(STATISTIC, getFirstParameter(input, STATISTICS_TEMPLATE));
        else if (isMatch(input, AVERAGE_SALARY_TEMPLATE))
            return new Command(AVERAGE_SALARY, getFirstParameter(input, AVERAGE_SALARY_TEMPLATE));
        else if (isMatch(input, EMPLOYEE_COUNT_TEMPLATE))
            return new Command(EMPLOYEE_COUNT, getFirstParameter(input, EMPLOYEE_COUNT_TEMPLATE));
        else if (isMatch(input, GLOBAL_SEARCH_TEMPLATE))
            return new Command(GLOBAL_SEARCH, getFirstParameter(input, GLOBAL_SEARCH_TEMPLATE));

        throw new RuntimeException("Command is invalid");
    }

    private boolean isMatch(String string, String template) {
        return Pattern.compile(String.format(template, "(.*?)"), Pattern.CASE_INSENSITIVE).matcher(string).find();
    }

    private String getFirstParameter(String input, String template) {
        return retrieveParameters(input, template).get(0);
    }

    private List<String> retrieveParameters(String input, String template) {

        input = input.toLowerCase();
        String[] constantParts = template.toLowerCase().split("%s");

        List<String> params = new ArrayList<>();
        for (int i = 0; i < constantParts.length; i++) {
            String param = (i < constantParts.length - 1)
                    ? StringUtils.substringBetween(input, constantParts[i], constantParts[i+1])
                    : StringUtils.substringAfter(input, constantParts[i]);
            input = input.replaceFirst(constantParts[i], "");

            if(hasLetters(param)) params.add(StringUtils.capitalize(param));
        }

        if (params.isEmpty()) throw new RuntimeException("Invalid parameter passed");

        return params;
    }

    private boolean hasLetters(String string) {
        return string.matches(".*[a-zA-Z].*");
    }
}
