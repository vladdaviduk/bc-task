package com.university.handler.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Command {

    private Request request;

    private String parameter;

    public enum Request {
        HEAD_OF,
        STATISTIC,
        AVERAGE_SALARY,
        EMPLOYEE_COUNT,
        GLOBAL_SEARCH
    }
}
