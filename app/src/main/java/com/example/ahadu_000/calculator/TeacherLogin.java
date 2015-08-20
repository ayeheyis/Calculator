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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


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
        EditText nameEt = (EditText) findViewById(R.id.editText3);
        String name = nameEt.getText().toString();
        EditText passwordEt = (EditText) findViewById(R.id.editText4);
        String password = passwordEt.getText().toString();
        ParseUser.logInInBackground(name, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    toTeacherHomePage();
                    return;
                } else {
                }
            }
        });
        nameEt.setText("");
        passwordEt.setText("");
    }

    private void toTeacherHomePage() {
        Intent intent = new Intent(this, TeacherHomePage.class);
        startActivity(intent);
    }

    public void toResetPage(View view) {
        Intent intent = new Intent(this, ResetPassword.class);
        startActivity(intent);
    }
}
