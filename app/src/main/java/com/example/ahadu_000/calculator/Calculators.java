package com.example.ahadu_000.calculator;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahadu on 8/18/2015.
 */
public class Calculators implements FindCallback<ParseObject> {
    private List<Calculator> calculators;

    public Calculators() {
        calculators = new ArrayList<>();
    }

    private void convertToCalculator(List<ParseObject> c) {
        for(ParseObject p : c) {
            String name = p.getString("Name");
            Log.d("Name", name);
            String teacher = p.getString("Teacher");
            String password = p.getString("Password");
            List<String> functions = (List<String>) p.get("Functions");
            Calculator calc = new Calculator(name, teacher, functions, password);
            calculators.add(calc);
        }
    }

    public List<Calculator> getCalculators() {
        return calculators;
    }

    public void done(List<ParseObject> c, ParseException e) {
        if (e == null) {
            Log.d("score", "Retrieved " + c.size() + " scores");
            convertToCalculator(c);
        } else {
            Log.d("score", "Error: " + e.getMessage());
        }
    }
}
