package com.example.ahadu_000.calculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class StudentLogin extends Activity {
    private ParseUtil parseUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        parseUtil = new ParseUtil();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_login, menu);
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

    public void toCalculatorPage(View view) {
        EditText teacherEt = (EditText) findViewById(R.id.editText);
        String teacher = teacherEt.getText().toString();
        EditText nameEt = (EditText) findViewById(R.id.editText9);
        String name = nameEt.getText().toString();
        EditText testEt = (EditText) findViewById(R.id.editText2);
        String test = nameEt.getText().toString();
        ParseObject parseObject = parseUtil.getParseObject("Calculator", "Teacher", teacher, name);
        Calculator calculator = parseUtil.convertToCalculator(parseObject);
        if(calculator != null) {
            toCalculatorPage();
            return;
        }
        teacherEt.setText("");
        nameEt.setText("");
        testEt.setText("");
    }


    private void toCalculatorPage() {
        Intent intent = new Intent(this, HorizontalCalculator.class);
        startActivity(intent);
    }
}
