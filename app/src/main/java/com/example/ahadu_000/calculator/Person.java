package com.example.ahadu_000.calculator;

/**
 * Created by ahadu_000 on 6/3/2015.
 */
public class Person {
    private String name;
    private String email;
    private String password;

    public Person(String n, String e, String p) {
        name = n;
        email = e;
        password = p;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
