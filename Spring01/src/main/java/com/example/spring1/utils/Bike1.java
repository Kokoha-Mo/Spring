package com.example.spring1.utils;

import org.springframework.stereotype.Component;

@Component
public class Bike1 implements Bike {
	private int i = 0;
	public Bike1() {
		System.out.println("Bike1()");
	}
	
	@Override
	public void upSpeed() {
		System.out.println("Bike1:upSpeed()" + i++);
	}

	@Override
	public void downSpeed() {
		System.out.println("Bike1:downSpeed()");

	}
	
}
