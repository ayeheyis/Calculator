package com.example.ahadu_000.calculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
    private static final String NAME = "Name";
    private static final String TEACHER = "Teacher";
    private static final String PASSWORD = "Password";
    private static final String CALCULATOR = "Calculator";

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

    /**
     * Checks the inputs of the user and if correct sends user to the calculator page. Otherwise,
     * it remains on the same page.
     * @param view A view
     */
    public void toCalculatorPage(View view) {
        //Get string values from text field
        EditText teacherEt = (EditText) findViewById(R.id.editText);
        String teacher = teacherEt.getText().toString();
        EditText calculatorNameEt = (EditText) findViewById(R.id.editText9);
        String calculatorName = calculatorNameEt.getText().toString();
        EditText passwordEt = (EditText) findViewById(R.id.editText10);
        String password = passwordEt.getText().toString();
        EditText nameEt = (EditText) findViewById(R.id.editText2);
        String name = nameEt.getText().toString();

        //Validate user inputs
        ParseObject parseObject = parseUtil.getParseObject(CALCULATOR, TEACHER, teacher, calculatorName);
        Calculator calculator = parseUtil.convertToCalculator(parseObject);
        if(validatePassword(calculator, password)) {
            if(loginStudent(teacher, name, calculatorName)) toCalculatorPage(teacher, calculator, name);
        }
        teacherEt.setText("");
        passwordEt.setText("");
        nameEt.setText("");
        calculatorNameEt.setText("");
    }

    /**
     * Checks if the student picked a calculator that matches the password that the student gives.
     * @param calculator A calculator
     * @param password A password
     * @return True if the calculator's password matches the password.
     */
    private boolean validatePassword(Calculator calculator, String password) {
        if(calculator == null) return false;
        String calcPass = calculator.getPassword();
        if("".equals(calcPass)) return false;
        if(!password.equals(calcPass)) return false;
        return true;
    }

    /**
     * Sends the user to the calculator page.
     */
    private void toCalculatorPage(String teacher, Calculator calculator, String name) {
        Intent intent = new Intent(this, example.class);
        Bundle bundle = new Bundle();
        bundle.putString(TEACHER, teacher);
        bundle.putString("CalcName", calculator.getName());
        bundle.putString(NAME, name);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Adds the student to the list of students logged in under a calculator.
     * @param teacher A teacher's username
     * @param name The name of the student
     * @param calculatorName The name of the calculator
     * @return True if the student was logged in properly and false otherwise.
     */
    public boolean loginStudent(String teacher, String name, String calculatorName) {
        ParseObject parseObject = parseUtil.getParseObject("Test", TEACHER, teacher, calculatorName);
        if(parseObject == null) return false;
        List<String> students = (List<String>) parseObject.get("Students");
        students.add(name);
        parseObject.put("Students", students);
        parseObject.saveInBackground();
        return true;
    }
}
