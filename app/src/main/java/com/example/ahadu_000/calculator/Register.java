package com.example.ahadu_000.calculator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Register extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    public void toTeacherLogin(View view) {
        EditText nameEt = (EditText) findViewById(R.id.editText5);
        EditText emailEt = (EditText) findViewById(R.id.editText6);
        EditText passwordEt = (EditText) findViewById(R.id.editText7);
        String name = nameEt.getText().toString();
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();

        if (!(!TextUtils.isEmpty(email) &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            ViewGroup layout = (ViewGroup) findViewById(R.id.registerActivity);
            TextView errorMsg = new TextView(this);
            errorMsg.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            errorMsg.setText("Incorrect Info");
            layout.addView(errorMsg);
            return;
        }

        TeacherDatabase tdb = new TeacherDatabase(this);
        SQLiteDatabase db = tdb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Email", email);
        values.put("Password", password);
        db.insert("teacherInfo", null, values);

        Intent intent = new Intent(this, TeacherLogin.class);
        startActivity(intent);
    }
}
