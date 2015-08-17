package com.example.ahadu_000.calculator;

import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;


public class HorizontalCalculator extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int initRows = 6;
        int initCols = 5;
        int totButtons = initRows * initCols;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        super.onCreate(savedInstanceState);

        RelativeLayout mainLayout = new RelativeLayout(HorizontalCalculator.this);
        mainLayout.setId(mainLayout.generateViewId());

        ToggleButton changeButton = new ToggleButton(this);
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
        mainLayout.addView(changeButton);

        GridLayout gl = new GridLayout(mainLayout.getContext());
        RelativeLayout.LayoutParams gllp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        gllp.addRule(RelativeLayout.ALIGN_BOTTOM, changeButton.getId());
        gl.setLayoutParams(gllp);
        gl.setId(gl.generateViewId());
        gl.setColumnCount(initCols);
        gl.setRowCount(initRows);

        Button myButtons[] = new Button[totButtons];
        String myTexts[] = {"AC", "+/-", "%", "/", "7" , "8", "9", "x", "4", "5", "6", "-", "1", "2"
                            , "3", "+", "0", "0", ".", "="};
        int textCount = myTexts.length;
        String required[] = {"AC", "1", "2", "3", "4", "5", "6", "7", "8", "9", "="};

        for (int i = 0; i < totButtons - textCount; i++) {
            myButtons[i] = new Button(this);
            myButtons[i].setText("");
            //myButtons[i].setWidth(screenWidth / initCols);
            gl.addView(myButtons[i]);
        }
        for (int i = totButtons - textCount; i < totButtons; i++) {
            myButtons[i] = new Button(this);
            myButtons[i].setText(myTexts[i - (totButtons - textCount)]);
            myButtons[i].setGravity(Gravity.CENTER);
            //myButtons[i].setWidth(screenWidth / initCols);
            gl.addView(myButtons[i]);
        }

        mainLayout.addView(gl);
        setContentView(mainLayout);



        //setContentView(R.layout.activity_horizontal_calculator);
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
}
