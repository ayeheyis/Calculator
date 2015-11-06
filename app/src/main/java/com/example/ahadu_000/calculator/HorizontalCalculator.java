package com.example.ahadu_000.calculator;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HorizontalCalculator extends ActionBarActivity {

    private final int initRows = 6;
    private final int initCols = 5;
    private final int totButtons = initRows * initCols;
    Button myButtons[] = new Button[totButtons];
    static String myTexts[] = {"", "", "", "", "", "", "AC", "-", "%", "/", "", "7", "8", "9", "*", "",
            "4", "5", "6", "-", "", "1", "2", "3", "+", "", "0", "0", ".", "="};
    int textCount = myTexts.length;
    String required[] = {"AC", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "="};
    public int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* final int initRows = 6;
        final int initCols = 5;
        final int totButtons = initRows * initCols; */

        Intent lastIntent = getIntent();
        Bundle extras = lastIntent.getExtras();
        if (extras != null)  {
            int row = extras.getInt("row");
            int col = extras.getInt("col");
            String text = extras.getString("text");
            String fnDecl = extras.getString("fnDecl", "");
            if (!fnDecl.equals("")) {
                text = text + ":" + fnDecl;
            }
            myTexts[row * initCols + col] = text;
        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        //int screenHeight = size.y;

        super.onCreate(savedInstanceState);

        final RelativeLayout mainLayout = new RelativeLayout(HorizontalCalculator.this);
        mainLayout.setId(mainLayout.generateViewId());

        /* ToggleButton changeButton = new ToggleButton(this);
        changeButton.setText("Change Button");
        changeButton.setTextOff("Change Button");
        changeButton.setTextOn("Change Button");
        changeButton.setChecked(true);
        changeButton.setId(changeButton.generateViewId());
        RelativeLayout.LayoutParams ablp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        ablp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        ablp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        changeButton.setLayoutParams(ablp);
        mainLayout.addView(changeButton); */



        Button saveCalc = new Button(this);
        saveCalc.setText("Save Calculator");
        saveCalc.setId(saveCalc.generateViewId());
        RelativeLayout.LayoutParams saveParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        saveParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        saveParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        saveCalc.setLayoutParams(saveParams);

        EditText calcName = new EditText(this);
        calcName.setHint("Give This Calculator a Name");
        final int calcNameId = calcName.generateViewId();
        calcName.setId(calcNameId);
        RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        nameParams.addRule(RelativeLayout.BELOW, saveCalc.getId());
        calcName.setLayoutParams(nameParams);
        mainLayout.addView(calcName);

        saveCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(calcNameId);
                String calculatorName = name.getText().toString();
                if (calculatorName.equals("")) {
                    Toast.makeText(getApplicationContext(), "You must enter a name for this calculator",
                            Toast.LENGTH_LONG).show();
                } else {
                    saveCalculator(v, calculatorName);
                }
            }
        });
        mainLayout.addView(saveCalc);


        /* Does not work
        Button resetCalc = new Button(this);
        saveCalc.setText("Reset Calculator");
        RelativeLayout.LayoutParams resetParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        saveParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        saveParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        resetCalc.setLayoutParams(resetParams);
        resetCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HorizontalCalculator.this, HorizontalCalculator.class);
                resetCalculator();
                startActivity(intent);
            }
        });
        mainLayout.addView(saveCalc); */


        GridLayout gl = new GridLayout(mainLayout.getContext());
        RelativeLayout.LayoutParams gllp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        gllp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); //, changeButton.getId());
        gl.setLayoutParams(gllp);
        gl.setId(gl.generateViewId());
        gl.setColumnCount(initCols);
        gl.setRowCount(initRows);

        for (i = 0; i < totButtons; i++) {
            final int ind = i;
            String text = myTexts[i];
            myButtons[i] = new Button(this);
            if (myTexts[i].indexOf(":") != -1) {
                //removes function declaration from text if it's a custom function
                text = text.substring(0, myTexts[i].indexOf(":"));
            }
            myButtons[i].setText(text);
            myButtons[i].setGravity(Gravity.CENTER);
            myButtons[i].generateViewId();
            if (!Arrays.asList(required).contains(myTexts[i])) {
                myButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int temp = ind;
                        Intent intent = new Intent(HorizontalCalculator.this, EditButtonPage.class);
                        Bundle coord = new Bundle();
                        coord.putInt("row", temp / initCols);
                        coord.putInt("col", temp % initCols);
                        intent.putExtras(coord);
                        startActivity(intent);
                    }
                });
            }
            gl.addView(myButtons[i], screenWidth / initCols, myButtons[i].getMinimumHeight());
        }

        //gl.setOnClickListener();
        mainLayout.addView(gl);
        setContentView(mainLayout);



        //setContentView(R.layout.activity_horizontal_calculator);
    }

    public void resetCalculator() {
        String[] initTexts = {"", "", "", "", "", "", "AC", "+/-", "%", "/", "", "7", "8", "9", "x", "",
                "4", "5", "6", "-", "", "1", "2", "3", "+", "", "0", "0", ".", "="};
        myTexts = initTexts;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horizontal_calculator, menu);
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

    public void saveCalculator(View v, String name) {
        Intent intent = new Intent(HorizontalCalculator.this, TeacherHomePage.class);
        //save to parse
        ParseUser currentUser = ParseUser.getCurrentUser();
        String teacher = currentUser.getUsername();
        Calculator calc = new Calculator(name, teacher, Arrays.asList(myTexts));
        ParseUtil p = new ParseUtil();
        ParseObject calcInfo = p.convertToParseObject(calc);
        calcInfo.saveInBackground();

        resetCalculator();
        startActivity(intent);
    }
}