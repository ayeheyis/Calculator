package com.example.ahadu_000.calculator;

import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahadu_000 on 8/20/2015.
 */
public class ParseUtil {
    private static final String CALCULATOR = "Calculator";
    private static final String NAME = "Name";
    private static final String TEACHER = "Teacher";
    private static final String PASSWORD = "Password";
    private static final String FUNCTIONS = "Functions";
    private static final String OPERATIONS = "Operations";

    public List<ParseObject> getParseObjects(String objectName, String key, String match) {
        List<ParseObject> parseObjects = null;
        ParseQuery<ParseObject> query = ParseQuery.getQuery(objectName);
        query.whereEqualTo(key, match);
        try {
            parseObjects = query.find();
        } catch (com.parse.ParseException e) {
            Log.d("Error", "Exception in Get Parse Objects");
            return null;
        }
        return parseObjects;
    }

    public ParseObject getParseObject(String objectName, String key, String match, String name) {
        List<ParseObject> parseObjects = getParseObjects(objectName, key, match);
        if(parseObjects == null) {
            Log.d("Error", "Null");
            return null;
        }
        for(ParseObject parseObject : parseObjects) {
            if(name.equals(parseObject.getString("Name"))) {
                return parseObject;
            }
        }
        return null;
    }

    /**
     * Takes in a calculator object and formats it into a parse object
     * @param calc A calculator object
     * @return A parse object
     **/
    public ParseObject convertToParseObject(Calculator calc) {
        //Get all the data from calculator
        String name = calc.getName();
        String teacher = calc.getTeacher();
        String password = calc.getPassword();
        List<String> functions = calc.getFunctions();
        List<String> operations = calc.getOperations();

        //Create parse object from calculator data
        ParseObject parseObject = new ParseObject(CALCULATOR);
        parseObject.put(NAME, name);
        parseObject.put(TEACHER, teacher);
        parseObject.put(PASSWORD, password);
        parseObject.addAll(FUNCTIONS, functions);
        parseObject.addAll(OPERATIONS, operations);
        return parseObject;
    }

    /**
     * Takes in a parse object and formats it into a calculator object
     * @param parseObject A parse object
     * @return A calculator object
     */
    public Calculator convertToCalculator(ParseObject parseObject) {
        //Get data from parse object
        String name = parseObject.getString(NAME);
        String teacher = parseObject.getString(TEACHER);
        String password = parseObject.getString(PASSWORD);
        List<String> operations = (List<String>) parseObject.get(FUNCTIONS);
        List<String> functions = (List<String>) parseObject.get(OPERATIONS);

        //Create calculator from parse object data
        Calculator calc = new Calculator(name, teacher, operations, functions, password);
        return calc;
    }

    /**
     * Takes in a list of parse objects and formats it into a list of calculator objects
     * @param parseObjects A list of parse objects
     * @return A list of calculators
     */
    public List<Calculator> convertToCalculator(List<ParseObject> parseObjects) {
        List<Calculator> calculators = new ArrayList<Calculator>();
        for(ParseObject parseObject : parseObjects) {
            calculators.add(convertToCalculator(parseObject));
        }
        return calculators;
    }
}
