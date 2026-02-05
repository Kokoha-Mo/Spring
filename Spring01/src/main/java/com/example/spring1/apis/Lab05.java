package com.example.spring1.apis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lab05")
public class Lab05 {
	@RequestMapping("/calc")  // /lab05/calc?x=10&y=3
	public void calc(@RequestParam String x ,@RequestParam String y) {
		System.out.printf("x = %s;y = %s\n",x,y);
	}
}
