package com.example.ahadu_000.calculator;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Ahadu on 8/18/2015.
 * An object used to represent a calculator. Contains operations, customized functions, and
 * identifiers.
 */
public class Calculator{
    private String name;
    private String teacher;
    private String password;
    private List<String> operations;
    private List<String> functions;

    public Calculator(String n, String t, List<String> o, List<String> f) {
        name = n;
        teacher = t;
        operations = o;
        functions = f;
        password = "";
    }

    public Calculator(String n, String t, List<String> o, List<String> f, String p) {
        name = n;
        teacher = t;
        operations = o;
        functions = f;
        password = p;
    }

    public void resetPassword() {
        password = "";
    }

    public void setPassword(String p) {
        p = new String(password);
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public List<String> getOperations() {
        return operations;
    }
}
