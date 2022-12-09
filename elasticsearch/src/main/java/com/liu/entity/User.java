package com.liu.entity;

/**
 * 文档--用户
 */
public class User {
    String name = String.valueOf(System.currentTimeMillis()-60*1000);

    Integer age = 18;

    String Address = "河南省确山县驻马店市新安店镇";

    public User() {
    }

    public User(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        Address = address;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", Address='" + Address + '\'' +
                '}';
    }
}
