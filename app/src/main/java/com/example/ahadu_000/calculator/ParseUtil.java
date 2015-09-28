package com.example.ahadu_000.calculator;

import android.util.Log;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahadu on 8/20/2015.
 * Utility class that handles parse requests.
 */
public class ParseUtil {
    private static final String TEST = "Test";
    private static final String NAME = "Name";
    private static final String EXITED = "Exited";
    private static final String TEACHER = "Teacher";
    private static final String PASSWORD = "Password";
    private static final String STUDENTS = "Students";
    private static final String FUNCTIONS = "Functions";
    private static final String OPERATIONS = "Operations";
    private static final String CALCULATOR = "Calculator";

    /**
     * Returns a list of parse objects where the value of the key 'key' equals 'match'
     *
     * @param objectName The name of the object class
     * @param key        A key from a key-value pair from the object
     * @param match      A value from a key-value pair
     * @return A list of parse objects
     */
    public List<ParseObject> getParseObjects(String objectName, String key, String match) {
        List<ParseObject> parseObjects;
        ParseQuery<ParseObject> query = ParseQuery.getQuery(objectName);
        query.whereEqualTo(key, match);
        try {
            parseObjects = query.find();
            Log.d("getParseObjects", "Found all the parse object");
        } catch (com.parse.ParseException e) {
            Log.d("Error", "ParseUtil->getParseObjects->exception raised");
            return null;
        }
        return parseObjects;
    }

    /**
     * Returns a parse object who has name 'name' and the value for the key 'key' equals 'match'
     *
     * @param objectName The name of the object class
     * @param key        A key from a key-value pair from the object
     * @param match      A value from a key-value pair
     * @param name       The name of the specific object
     * @return A parse object
     */
    public ParseObject getParseObject(String objectName, String key, String match, String name) {
        List<ParseObject> parseObjects = getParseObjects(objectName, key, match);
        if (parseObjects == null) {
            Log.d("Error", "ParseUtil->getParseObject->parse object is null");
            return null;
        }
        for (ParseObject parseObject : parseObjects) {
            Log.d("getParseObject", parseObject.getString(NAME));
            if (name.equals(parseObject.getString(NAME))) {
                return parseObject;
            }
        }
        return null;
    }

    /**
     * Takes in a calculator object and formats it into a parse object
     *
     * @param calc A calculator object
     * @return A parse object
     */
    public ParseObject convertToParseObject(Calculator calc) {
        //Get all the data from calculator
        String name = calc.getName();
        String teacher = calc.getTeacher();
        String password = calc.getPassword();
        List<String> functions = calc.getFunctions();

        //Create parse object from calculator data
        ParseObject parseObject = new ParseObject(CALCULATOR);
        parseObject.put(NAME, name);
        parseObject.put(TEACHER, teacher);
        parseObject.put(PASSWORD, password);
        parseObject.addAll(FUNCTIONS, functions);
        return parseObject;
    }

    /**
     * Takes in a parse object and formats it into a calculator object
     *
     * @param parseObject A parse object
     * @return A calculator object
     */
    public Calculator convertToCalculator(ParseObject parseObject) {
        //Get data from parse object
        if(parseObject == null) return null;
        String name = parseObject.getString(NAME);
        String teacher = parseObject.getString(TEACHER);
        String password = parseObject.getString(PASSWORD);
        List<String> functions = (List<String>) parseObject.get(OPERATIONS);

        //Create calculator from parse object data
        Calculator calc = new Calculator(name, teacher, functions, password);
        return calc;
    }

    /**
     * Takes in a list of parse objects and formats it into a list of calculator objects
     *
     * @param parseObjects A list of parse objects
     * @return A list of calculators
     */
    public List<Calculator> convertToCalculator(List<ParseObject> parseObjects) {
        if(parseObjects == null) return null;
        List<Calculator> calculators = new ArrayList<Calculator>();
        for (ParseObject parseObject : parseObjects) {
            calculators.add(convertToCalculator(parseObject));
        }
        return calculators;
    }

    /**
     * Takes in a teacher and a name and creates a test sessions under the teacher name
     * where students can log in.
     * @param teacher A teacher
     * @param name The name of the calculator
     */
    public void createTest(String teacher, String name) {
        List<String> students = new ArrayList<String>();
        List<String> exited = new ArrayList<String>();
        ParseObject parseObject = new ParseObject(TEST);
        parseObject.put(TEACHER, teacher);
        parseObject.put(STUDENTS, students);
        parseObject.put(NAME, name);
        parseObject.put(EXITED, exited);
        parseObject.saveInBackground();
    }


}
