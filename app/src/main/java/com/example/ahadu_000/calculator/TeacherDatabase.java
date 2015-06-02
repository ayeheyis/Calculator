package com.example.ahadu_000.calculator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ahadu_000 on 6/2/2015.
 */
public class TeacherDatabase  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String EMAIL = "Email";
    private static final String NAME = "Name";
    private static final String PASSWORD = "Password";
    private static final String TEACHER_TABLE_NAME = "teacherInfo";
    private static final String TEACHER_TABLE_CREATE =
            "CREATE TABLE " + TEACHER_TABLE_NAME + " (" +
                    EMAIL + " TEXT, " +
                    NAME + " TEXT, " +
                    PASSWORD + " TEXT);";

    TeacherDatabase(Context context) {
        super(context, TEACHER_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TEACHER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}