package tw.lab.Spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring10.repo.EmployeeRepo;
import tw.lab.Spring10.spec.EmployeeSpec;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @RequestMapping("/test0")
    public void test0() {
        employeeRepo.test0(2000f).forEach(e -> {
            System.out.printf("%s %s:%f\n", e.getFirstName(), e.getLastName(), e.getSalary());
        });
    }

    @RequestMapping("/test1")
    public void test1() {
        employeeRepo.test1(2000f).forEach(e -> {
            System.out.printf("%s %s:%f\n", e.getFirstName(), e.getLastName(), e.getSalary());
        });
    }

    @RequestMapping("/test2")
    public void test2() {
        employeeRepo.test2(2000f).forEach(e -> {
            System.out.printf("%s %s:%f\n", e.getFirstName(), e.getLastName(), e.getSalary());
        });
    }

    @RequestMapping("/test3")
    public void test3() {
        employeeRepo.findBySalaryLessThan(2000f).forEach(e -> {
            System.out.printf("%s %s:%f\n", e.getFirstName(), e.getLastName(), e.getSalary());
        });
    }

    @RequestMapping("/test4")
    public void test4() {
        employeeRepo.findAll(
                Specification.allOf(
                        EmployeeSpec.firstNameEquals("Nancy"),
                        EmployeeSpec.lastNameEquals(null),
                        EmployeeSpec.titleEquals("Sales Representative")))
                .forEach(e -> {
                    System.out.printf("%s %s:%s\n", e.getFirstName(), e.getLastName(), e.getTitle());
                });
    }

    @RequestMapping("/test5")
    public void test5() {
        employeeRepo.findAll(
                Specification.anyOf(
                        EmployeeSpec.firstNameEquals("Steven"),
                        EmployeeSpec.lastNameEquals(null),
                        EmployeeSpec.titleEquals("Inside Sales Coordinator")))
                .forEach(e -> {
                    System.out.printf("%s %s:%s\n", e.getFirstName(), e.getLastName(), e.getTitle());
                });
    }
}
