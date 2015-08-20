package com.example.ahadu_000.calculator;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by ahadu on 8/18/2015.
 */
public class Calculator{
    private String name;
    private String teacher;
    private String password;
    private List<String> functions;
    private int size;

    public Calculator(String n, String t, List<String> f) {
        name = n;
        teacher = t;
        functions = f;
        size = f.size();
        password = "";
    }

    public void resetPassword() {
        password = "";
    }

    public void setPassword(String p) {
        p = password;
    }

    public String getPassword() {
        return password;
    }

    public int getSize() {
        return size;
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

    public ParseObject getParseObject() {
        ParseObject calculator = new ParseObject("Calculator");
        calculator.put("Name", name);
        calculator.put("Teacher", teacher);
        calculator.put("Size", size);
        calculator.addAll("Functions", functions);
        return calculator;
    }
}
