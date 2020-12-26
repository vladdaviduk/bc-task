package com.university.handler;

import com.university.handler.command.CommandResolver;
import com.university.handler.command.Command;
import com.university.model.DepartmentLector;
import com.university.model.enums.Degree;
import com.university.service.DepartmentService;
import com.university.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static com.university.model.enums.Degree.*;

@Component
public class CommandHandler implements CommandLineRunner {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SearchService searchService;

    @Override
    public void run(String... args) {

        CommandResolver commandResolver = new CommandResolver();

        while (true) {
            print("Enter a command : ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            if(userInput.equals("exit")) {
                print("Bye...");
                System.exit(0);
            }

            try {
                Command command = commandResolver.resolve(userInput);
                String param = command.getParameter();

                switch (command.getRequest()) {
                    case HEAD_OF: {
                        String headOfDepartment = departmentService.getHeadOfDepartment(param);
                        print(String.format("Head of %s department is %s", param, headOfDepartment));
                        break;
                    } case STATISTIC: {
                        List<DepartmentLector> lectors = departmentService.getDepartmentLectors(param);
                        long assistantsCount = countDepartmentLectors(lectors, ASSISTANT);
                        long associateProfessorsCount = countDepartmentLectors(lectors, ASSOCIATE_PROFESSOR);
                        long professorsCount = countDepartmentLectors(lectors, PROFESSOR);

                        print(String.format("assistans - %s; \nassociate professors - %s;\nprofessors - %s",
                                assistantsCount, associateProfessorsCount, professorsCount));
                        break;
                    } case AVERAGE_SALARY: {
                        double salary = calculateAverageSalary(departmentService.getDepartmentLectors(param));
                        print(String.format("The average salary of %s department is %s",
                                command.getParameter(), salary));
                        break;
                    } case EMPLOYEE_COUNT: {
                        print(departmentService.getDepartmentLectors(param).size() + "");
                        break;
                    } case GLOBAL_SEARCH: {
                        List<String> result = searchService.search(param);
                        if (result.isEmpty()) print("Nothing found");
                        else print(result.toString());
                    }
                }
            } catch (RuntimeException e) {
                print(e.getMessage());
            }
        }
    }

    private double calculateAverageSalary(List<DepartmentLector> lectors) {
        return lectors.stream().mapToDouble(DepartmentLector::getSalary).average().orElse(0);
    }

    private long countDepartmentLectors(List<DepartmentLector> lectors, Degree degree) {
        return lectors.stream().filter(l -> l.getLector().getDegree() == degree).count();
    }

    private void print(String message) {
        System.out.println(message);
    }
}
