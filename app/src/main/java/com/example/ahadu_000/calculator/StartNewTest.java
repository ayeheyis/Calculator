package com.example.ahadu_000.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import android.view.MenuItem;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class StartNewTest extends Activity {
    private ListView calcListView;
    private String[] stringArray ;
    private ArrayAdapter calcNameAdapter;
    private ParseUtil parseUtil;

    private void setStringArray(List<Calculator> calcs) {
        int size = calcs.size();
        stringArray = new String[size];
        for(int i = 0; i < size; i++) {
            stringArray[i] = calcs.get(i).getName();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseUtil = new ParseUtil();
        setContentView(R.layout.activity_start_new_test);
        ParseUser currentUser = ParseUser.getCurrentUser();
        List<ParseObject> parseObjects = parseUtil.getParseObjects("Calculator", "Teacher", currentUser.getUsername());
        List<Calculator> calcs = parseUtil.convertToCalculator(parseObjects);
        setStringArray(calcs);
        calcNameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);
        calcListView = (ListView) findViewById(R.id.listView);
        calcListView.setAdapter(calcNameAdapter);
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
}
