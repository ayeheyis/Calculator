package com.example.ahadu_000.calculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseObject;

import java.util.List;
import java.util.Timer;


public class TestSession extends Activity {
    private Bundle bundle;
    private ListView studentListView;
    private String[] stringArray ;
    private ArrayAdapter nameAdapter;
    private ParseUtil parseUtil;
    private String teacherName;
    private String calcName;
    private List<String> students;
    private List<String> exited;
    private UpdateTestSession updateTestSession;
    private Timer timer;
    private ParseObject parseObject;

    private static final String TEST = "Test";
    private static final String STUDENTS = "Students";
    private static final String NAME = "Name";
    private static final String TEACHER = "Teacher";
    private static final String EXITED = "Exited";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_session);
        bundle = getIntent().getExtras();
        init();
        repeatedInit();
        updateSession();
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

    public void init() {
        parseUtil = new ParseUtil();
        teacherName = (String) bundle.get(TEACHER);
        calcName = (String) bundle.get(NAME);
        Log.d("TestSession->Init", teacherName);
        Log.d(NAME, calcName);

        updateTestSession = new UpdateTestSession(this);
        timer = new Timer();
    }

    public void repeatedInit() {
        parseObject = parseUtil.getParseObject(TEST, TEACHER, teacherName, calcName);
        students = (List<String>) parseObject.get(STUDENTS);
        exited = (List<String>) parseObject.get(EXITED);

        stringArray = getStringArray(students);
        nameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);
        studentListView = (ListView) findViewById(R.id.listView1);
        studentListView.setAdapter(nameAdapter);

        stringArray = getStringArray(exited);
        nameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);
        studentListView = (ListView) findViewById(R.id.listView2);
        studentListView.setAdapter(nameAdapter);


    }

    private String[] getStringArray(List<String> list) {
        int length = list.size();
        String[] stringArray = new String[length];
        for(int i = 0; i < length; i++) {
            stringArray[i] = list.get(i);
        }
        return stringArray;
    }

    public void updateSession() {
        long delay = 0;
        long period = 30000;
        timer.schedule(updateTestSession, delay, period);
    }

    public void endTestSession(View view) {
        timer.cancel();
        parseObject.deleteInBackground();
        Intent intent = new Intent(this, TeacherHomePage.class);
        startActivity(intent);
    }
}
