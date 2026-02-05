package com.example.spring1.utils;

import org.springframework.stereotype.Component;

@Component
public class Bike2 implements Bike {
	public Bike2() {
		System.out.println("Bike2()");
	}
	
	@Override
	public void upSpeed() {
		System.out.println("Bike2:upSpeed()");
	}

	@Override
	public void downSpeed() {
		System.out.println("Bike2:downSpeed()");

	}
	
}
