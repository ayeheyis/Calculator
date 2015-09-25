package com.example.ahadu_000.calculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseObject;


public class StudentTest extends ActionBarActivity {

    private ParseUtil parseUtil;
    private static final String NAME = "Name";
    private static final String TEACHER = "Teacher";
    private static final String CALCULATOR = "Calculator";
    private static final int MAXBUTTONS = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent lastIntent = getIntent();
        Bundle extras = lastIntent.getExtras();
        assert(extras != null);
        String teacher = extras.getString(TEACHER);
        String calcName = extras.getString("CalcName");
        String name = extras.getString(NAME);
        ParseObject parseObject = parseUtil.getParseObject(CALCULATOR, TEACHER, teacher, calcName);
        Calculator calc = parseUtil.convertToCalculator(parseObject);
        String[] calcButtonTexts = new String[MAXBUTTONS];
        calcButtonTexts = calc.getFunctions().toArray();
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_student_test);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_test, menu);
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
