package com.example.ahadu_000.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseObject;

import java.util.List;


public class example extends ActionBarActivity {
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        bundle = getIntent().getExtras();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_example, menu);
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

    @Override
    public void onPause() {
        super.onPause();
        ParseUtil parseUtil = new ParseUtil();
        //Get the arguments
        String teacher = (String) bundle.get("Teacher");
        String name = (String) bundle.get("Name");
        String calcName = (String) bundle.get("CalcName");

        //Find the parse object using the arguments
        ParseObject parseObject = parseUtil.getParseObject("Test", "Teacher", teacher, calcName);
        List<String> students = (List<String>) parseObject.get("Students");

        //Remove the student and update the list
        students.remove(name);
        parseObject.put("Students", students);
        parseObject.saveInBackground();
        Log.d("Pause", "Yes");
    }

}
