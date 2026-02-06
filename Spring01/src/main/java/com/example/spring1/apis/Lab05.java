package com.example.spring1.apis;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring1.dto.User;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/Lab05")
public class Lab05 {
	@RequestMapping("/calc") // /lab05/calc?x=10&y=3
	public String calc(@RequestParam(required = false, defaultValue = "0") String x,
			@RequestParam(required = false, defaultValue = "0") String y,
			@RequestParam(required = false, defaultValue = "1") String op) {

		// System.out.printf("x + y = %d\n", x + y);
		try {
			int intX = Integer.parseInt(x);
			int intY = Integer.parseInt(y);
			int r = 0;
			switch (op) {
				case "1":
					r = intX + intY;
					break;
				case "2":
					r = intX - intY;
					break;
				case "3":
					r = intX * intY;
					break;
				case "4":
					r = intX + intY;
					break;

				default:
					break;
			}
			return String.valueOf(r);
		} catch (Exception e) {
			System.out.println(e);
			return "error";
		}

	}

	@RequestMapping("/test1")
	public void test1(@RequestBody User user) {
		System.out.println(user.getName());
		System.out.println(user.isGender());
		System.out.println(user.getAge());
		System.out.println(user.getBike().getSpeed());
		System.out.println(user.getAlias()[2]);
		System.out.println(user.getTest().get("體重"));
	}

	@RequestMapping("/test2/{name}/{id}")
	public void test2(
			@PathVariable String name,
			@PathVariable String id) {
		System.out.printf("%s.%s", id, name);
	}

	@RequestMapping("/test3")
	public void test3(@RequestHeader String x,
			@RequestHeader(name = "Content-Type") String contentType) {
		System.out.println(x);
		System.out.println(contentType);
	}

	@RequestMapping("/test4/{name}/{id}")
	public void test4(
			@RequestParam(required = false, defaultValue = "0") String x,
			@RequestParam(required = false, defaultValue = "0") String y,
			@RequestBody User user,
			@PathVariable String name,
			@PathVariable String id,
			@RequestHeader(name = "x") String xx,
			@RequestHeader(name = "Content-Type") String contentType) {

		System.out.printf("x + y = %d\n", (int) (Integer.parseInt(x) + Integer.parseInt(y)));
		System.out.println("----");
		System.out.println(user.getName());
		System.out.println(user.isGender());
		System.out.println(user.getAge());
		System.out.println(user.getBike().getSpeed());
		System.out.println(user.getAlias()[2]);
		System.out.println(user.getTest().get("體重"));
		System.out.println("----");
		System.out.printf("%s.%s", id, name);
		System.out.println("----");
		System.out.println(xx);
		System.out.println(contentType);
	}
}
