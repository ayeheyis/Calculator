package com.example.ahadu_000.calculator;

import com.parse.Parse;

/**
 * Created by ahadu_000 on 6/19/2015.
 */
public class Application extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "hAOCkFZz0VaSKYnycqL81qvqnikvNYMzgqaaiEva", "s6OQY79uIfRcRtenqUOYmnfGWtflcxAidEpdS7EB");
    }

}

