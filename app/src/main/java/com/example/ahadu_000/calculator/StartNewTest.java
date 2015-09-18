package com.example.ahadu_000.calculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;
import android.view.MenuItem;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class StartNewTest extends Activity {
    //Fields needed to create list display
    private ListView calcListView;
    private String[] stringArray ;
    private ArrayAdapter calcNameAdapter;
    private ParseUtil parseUtil;
    private List<ParseObject> parseObjects;
    private CalculatorActionListener calculatorActionListener;

    //Fields for strings
    private static final String CALCULATOR = "Calculator";
    private static final String NAME = "Name";
    private static final String TEACHER = "Teacher";
    private static final String PASSWORD = "Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_test);

        parseUtil = new ParseUtil();
        ParseUser currentUser = ParseUser.getCurrentUser();
        parseObjects = parseUtil.getParseObjects(CALCULATOR, TEACHER, currentUser.getUsername());
        List<Calculator> calcs = parseUtil.convertToCalculator(parseObjects);
        calculatorActionListener = new CalculatorActionListener(calcs, this);

        setStringArray(calcs);
        calcNameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);
        calcListView = (ListView) findViewById(R.id.listView);
        calcListView.setAdapter(calcNameAdapter);
        calcListView.setOnItemClickListener(calculatorActionListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_new_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setStringArray(List<Calculator> calcs) {
        int size = calcs.size();
        stringArray = new String[size];
        for(int i = 0; i < size; i++) {
            stringArray[i] = calcs.get(i).getName();
        }
    }

    public void updateObject(Calculator calculator, String password) {
        for(ParseObject parseObject : parseObjects) {
            if(parseObject.getString(NAME).equals(calculator.getName())) {
                parseObject.put(PASSWORD, password);
                parseObject.saveInBackground();
                return;
            }
        }
    }
}
