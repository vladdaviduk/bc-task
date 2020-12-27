package com.university.handler.command;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.university.handler.command.Command.Request.*;
import static java.lang.String.format;

public class CommandResolver {

    private static final String PARAM_PLACE = "(.*?)";

    private static final String HEAD_OF_TEMPLATE = format("Who is head of department %s", PARAM_PLACE);
    private static final String STATISTICS_TEMPLATE = format("Show %s statistics", PARAM_PLACE);
    private static final String AVERAGE_SALARY_TEMPLATE =
            format("Show the average salary for the department %s", PARAM_PLACE);
    private static final String EMPLOYEE_COUNT_TEMPLATE = format("Show count of employee for %s", PARAM_PLACE);
    private static final String GLOBAL_SEARCH_TEMPLATE = format("Global search by %s", PARAM_PLACE);

    public Command resolve(String input) {

        if (isMatch(input, HEAD_OF_TEMPLATE))
            return new Command(HEAD_OF, retrieveParameters(input, HEAD_OF_TEMPLATE));
        else if (isMatch(input, STATISTICS_TEMPLATE))
            return new Command(STATISTIC, retrieveParameters(input, STATISTICS_TEMPLATE));
        else if (isMatch(input, AVERAGE_SALARY_TEMPLATE))
            return new Command(AVERAGE_SALARY, retrieveParameters(input, AVERAGE_SALARY_TEMPLATE));
        else if (isMatch(input, EMPLOYEE_COUNT_TEMPLATE))
            return new Command(EMPLOYEE_COUNT, retrieveParameters(input, EMPLOYEE_COUNT_TEMPLATE));
        else if (isMatch(input, GLOBAL_SEARCH_TEMPLATE))
            return new Command(GLOBAL_SEARCH, retrieveParameters(input, GLOBAL_SEARCH_TEMPLATE));

        throw new RuntimeException("Command is invalid");
    }

    private boolean isMatch(String string, String template) {
        return Pattern.compile(template, Pattern.CASE_INSENSITIVE).matcher(string).find();
    }

    private List<String> retrieveParameters(String input, String template) {

        input = input.toLowerCase();

        int paramsNumber = StringUtils.countMatches(template, PARAM_PLACE);
        String[] constantParts = template.toLowerCase().split(Pattern.quote(PARAM_PLACE));
        List<String> params = new ArrayList<>();

        for (int i = 0; i < constantParts.length; i++) {
            String param = (i < constantParts.length - 1)
                    ? StringUtils.substringBetween(input, constantParts[i], constantParts[i+1])
                    : StringUtils.substringAfter(input, constantParts[i]);

            validateParam(param);
            params.add(StringUtils.capitalize(param.strip()));
            if (i == paramsNumber - 1) break;

            input = input.replaceFirst(constantParts[i], "").replaceFirst(param, "");
        }

        return params;
    }

    private void validateParam(String param) {
        if (!param.matches("[\\w _\\-]+"))
            throw new RuntimeException(format("Invalid parameter \"%s\" passed", param));
    }
}
