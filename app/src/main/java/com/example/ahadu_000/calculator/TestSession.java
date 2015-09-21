package com.example.ahadu_000.calculator;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseObject;

import java.util.List;


public class TestSession extends Activity {
    private Bundle bundle;
    private ListView studentListView;
    private String[] stringArray ;
    private ArrayAdapter nameAdapter;
    private ParseUtil parseUtil;
    private String teacherName;
    private String calcName;
    private List<String> students;

    private static final String TEST = "Test";
    private static final String STUDENTS = "Students";
    private static final String NAME = "Name";
    private static final String TEACHER = "Teacher";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_session);
        bundle = getIntent().getExtras();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_session, menu);
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

    private void init() {
        parseUtil = new ParseUtil();
        teacherName = (String) bundle.get(TEACHER);
        calcName = (String) bundle.get(NAME);
        Log.d(TEACHER, teacherName);
        Log.d(NAME, calcName);
        ParseObject parseObject = parseUtil.getParseObject(TEST, TEACHER, teacherName, calcName);
        students = (List<String>) parseObject.get(STUDENTS);
        stringArray = getStringArray(students);

        nameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);
        studentListView = (ListView) findViewById(R.id.listView1);
        studentListView.setAdapter(nameAdapter);
    }

    private String[] getStringArray(List<String> students) {
        int length = students.size();
        String[] stringArray = new String[length];
        for(int i = 0; i < length; i++) {
            stringArray[i] = students.get(i);
        }
        return stringArray;
    }
}
