package com.university.handler.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Command {

    private Request request;

    private List<String> parameters;

    public enum Request {
        HEAD_OF,
        STATISTIC,
        AVERAGE_SALARY,
        EMPLOYEE_COUNT,
        GLOBAL_SEARCH
    }
}
