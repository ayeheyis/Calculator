package com.example.ahadu_000.calculator;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TeacherLogin extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teacher_login, menu);
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

    public void toRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void toTeacherHomePage(View view) {
        EditText emailEt = (EditText) findViewById(R.id.editText3);
        String email = emailEt.getText().toString();
        EditText passwordEt = (EditText) findViewById(R.id.editText4);
        String password = passwordEt.getText().toString();
        TeacherDatabase tdb = new TeacherDatabase(this);
        SQLiteDatabase db = tdb.getReadableDatabase();
        String query = "select * from teacherInfo where Email=\""+ email + "\"";
        Cursor cs = db.rawQuery(query, null);
        String dbPw = cs.getString(cs.getColumnIndex("Password"));
        if (dbPw != password) {
            //pass
            ViewGroup layout = (ViewGroup) findViewById(R.id.teacherLoginActivity);
            TextView errorMsg = new TextView(this);
            errorMsg.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            errorMsg.setText("Incorrect Info");
            layout.addView(errorMsg);
            return;
        }

        Intent intent = new Intent(this, TeacherHomePage.class);
        startActivity(intent);


        /*Cursor c = db.query(
                "teacherInfo",  // The table to query
                null,                               // The columns to return
                "Email" + " = " + email,
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );*/
    }

}
