package com.example.ahadu_000.calculator;

import android.util.Log;

import java.util.TimerTask;

/**
 * Created by Ahadu on 9/26/2015.
 */
public class UpdateTestSession extends TimerTask{
    TestSession testSession;

    public UpdateTestSession(TestSession ts) {
        testSession = ts;
    }
    public void run() {
        try {
            testSession.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("Event", "Time is up");
                    //update the teacher session list
                    testSession.repeatedInit();
                }
            });
        } catch (Exception exception) {
            Log.d("Error", exception.getMessage());
        }
    }
}
