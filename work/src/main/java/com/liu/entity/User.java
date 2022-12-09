package com.liu.entity;

public class User {
    String day;
    String name;
    Integer age;

    public User(String day, String name, Integer age) {
        this.day = day;
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "day='" + day + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
