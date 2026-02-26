package tw.lab.Spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring10.repo.EmployeeRepo;

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
}
