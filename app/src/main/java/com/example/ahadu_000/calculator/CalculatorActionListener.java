package com.example.ahadu_000.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahadu on 8/21/2015.
 *An action listener for the list of calculators in the Start New Test page.
 */
public class CalculatorActionListener implements AdapterView.OnItemClickListener{
    private List<Calculator> calculators;
    private Calculator calculator;
    private StartNewTest startNewTest;
    private String teacher;
    private ParseUtil parseUtil;

    private static final String TEST = "Test";
    private static final String MESSAGE = "Choose a password for the test";
    private static final String SUBMIT = "Submit";
    private static final String CANCEL = "Cancel";
    private static final String TEACHER = "Teacher";
    private static final String NAME = "Name";

    /**
     * Constructor
     * @param c a list of calculators
     * @param s the start new test activity
     */
    public CalculatorActionListener(List<Calculator> c, StartNewTest s) {
        calculators = c;
        startNewTest = s;
        ParseUser user = ParseUser.getCurrentUser();
        teacher = user.getUsername();
        parseUtil = new ParseUtil();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        String item = (String) parent.getItemAtPosition(position);
        for(Calculator calc : calculators) {
            if(item.equals(calc.getName())) {
                calculator = calc;
                startDialogBox();
            }
        }
    }

    private void startDialogBox() {
        final EditText text = new EditText(startNewTest);
        new AlertDialog.Builder(startNewTest)
                .setTitle(TEST)
                .setMessage(MESSAGE)
                .setView(text)
                .setPositiveButton(SUBMIT, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String password = text.getText().toString();
                        setPassword(password);
                        parseUtil.createTest(teacher, calculator.getName());
                        Intent intent = new Intent(startNewTest, TestSession.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(TEACHER, teacher);
                        bundle.putString(NAME, calculator.getName());
                        Log.d("Teacher", teacher);
                        Log.d(NAME, calculator.getName());
                        intent.putExtras(bundle);
                        startNewTest.startActivity(intent);
                    }
                })
                .setNegativeButton(CANCEL, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    private void setPassword(String password) {
        startNewTest.updateObject(calculator, password);
    }
}


