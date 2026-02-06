package com.example.spring1.apis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/Lab07")
public class Lab07 {

    @Value("${company.name}")
    private String companyName;

    @RequestMapping("/m1")
    public void m1() {
        System.out.println("Lab07:m1()");
        System.out.println(companyName);
    }

}
