package com.example.spring1.dto;

import java.util.Map;

public class User {
    private String name;
    private boolean gender;
    private Integer age;
    private Bike bike;
    private String[] alias;
    private Map<String, Object> test;

    public String getName() {
        return name;
    }

    public boolean isGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public Bike getBike() {
        return bike;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public Map<String, Object> getTest() {
        return test;
    }

    public void setTest(Map<String, Object> test) {
        this.test = test;
    }

}
