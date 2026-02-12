package tw.lab.Spring04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring04.entity.Employee;
import tw.lab.Spring04.projection.EmployeeProjection;
import tw.lab.Spring04.repo.EmployeeRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/employee")
public class EmpolyeeController {

    @Autowired
    EmployeeRepo repo;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> test1(@PathVariable Integer id) {
        Employee e = repo.findById(id).orElse(null);
        return ResponseEntity.ok(e);
    }

    @GetMapping("/title/{key}")
    public ResponseEntity<List<EmployeeProjection>> test2(@PathVariable String key) {
        List<EmployeeProjection> sales = repo.searchByTitleStartingWith(key);
        return ResponseEntity.ok(sales);
    }

}
