package com.example.ahadu_000.calculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;


public class ResetPassword extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reset_password, menu);
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

    public void resetPassword(View view) {
        EditText emailEt = (EditText) findViewById(R.id.editText8);
        String email = emailEt.getText().toString();
        ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    toTeacherLoginPage();
                } else {
                    // Something went wrong. Look at the ParseException to see what's up.
                }
            }
        });
    }

    private void toTeacherLoginPage() {
        Intent intent = new Intent(this, TeacherLogin.class);
        startActivity(intent);
    }
}
