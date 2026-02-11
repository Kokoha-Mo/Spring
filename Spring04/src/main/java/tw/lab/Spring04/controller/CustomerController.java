package tw.lab.Spring04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.lab.Spring04.entity.Customer;
import tw.lab.Spring04.repo.CustomerRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("{id}")
    public ResponseEntity<Customer> test1(@PathVariable String id) {
        return ResponseEntity.ok(customerRepo.findByCustomerId(id).orElse(null));
    }

}
