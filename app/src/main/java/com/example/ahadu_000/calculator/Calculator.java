package com.example.ahadu_000.calculator;

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
    private List<String> functions;

    /**
     * Constructor
     * @param n name of the claculator
     * @param t name of the teacher that owns it
     * @param f a list of functions/operations of the calculator
     */
    public Calculator(String n, String t,  List<String> f) {
        name = n;
        teacher = t;
        functions = f;
        password = "";
    }

    /**
     * Constructor
     * @param n name of the claculator
     * @param t name of the teacher that owns it
     * @param f a list of functions/operations of the calculator
     * @param p a password for the calculator
     */
    public Calculator(String n, String t, List<String> f, String p) {
        name = n;
        teacher = t;
        functions = f;
        password = p;
    }

    /**
     * Sets the password to empty string
     */
    public void resetPassword() {
        password = "";
    }

    /**
     * Sets the password to 'p'
     * @param p a password
     */
    public void setPassword(String p) {
        p = new String(password);
    }

    /**
     *
     * @return password of the calculator
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return name of the calculator
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the teacher that owns the calculator
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     *
     * @return a list of functions/operations
     */
    public List<String> getFunctions() {
        return functions;
    }
}
