package com.example.ahadu_000.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import java.util.List;

/**
 * Created by ahadu_000 on 8/21/2015.
 */
public class CalculatorActionListener implements AdapterView.OnItemClickListener{
    private List<Calculator> calculators;
    private Calculator calculator;
    private StartNewTest startNewTest;

    public CalculatorActionListener(List<Calculator> c, StartNewTest s) {
        calculators = c;
        startNewTest = s;
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
                .setTitle("Test")
                .setMessage("Choose a password for the test")
                .setView(text)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String password = text.getText().toString();
                        setPassword(password);
                        Intent intent = new Intent(startNewTest, TeacherHomePage.class);
                        startNewTest.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    private void setPassword(String password) {
        startNewTest.updateObject(calculator, password);
    }
}
