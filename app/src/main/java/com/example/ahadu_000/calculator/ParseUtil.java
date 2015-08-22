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

    public Calculator convertToCalculator(ParseObject parseObject) {
        if(parseObject == null) {
            return null;
        }
        String name = parseObject.getString("Name");
        String teacher = parseObject.getString("Teacher");
        String password = parseObject.getString("Password");
        List<String> functions = (List<String>) parseObject.get("Functions");
        Calculator calc = new Calculator(name, teacher, functions, password);
        calc.setPassword(password);
        return calc;
    }

    public List<Calculator> convertToCalculator(List<ParseObject> c) {
        List<Calculator> calculators = new ArrayList<Calculator>();
        for(ParseObject p : c) {
            calculators.add(convertToCalculator(p));
        }
        return calculators;
    }
}
