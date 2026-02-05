package com.example.spring1.apis;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController		// => Bean => IoC => Sever Starting
@RequestMapping("/lab03")
public class Lab03 {
	public Lab03() {
		System.out.println("Lab03()");
	}
	
	@RequestMapping("/del/5")
	public void test1() {
		System.out.println("Lab03:test1()");
	}
	@RequestMapping("/test2")
	public void test2() {
		System.out.println("Lab03:test2()");
	}
	@RequestMapping("/test3")
	public String test3() {
		System.out.println("Lab03:test3()");
		return "<h1>Hello,Lab</h1><hr/><div>中文測試</div>";
		
	}
}
