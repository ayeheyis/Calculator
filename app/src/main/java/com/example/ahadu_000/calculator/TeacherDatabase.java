package com.example.ahadu_000.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    // Adding new contact
    void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, person.getEmail());
        values.put(NAME, person.getName()); // Contact Phone
        values.put(PASSWORD, person.getPassword()); // Contact Phone

        // Inserting Row
        db.insert(TEACHER_TABLE_NAME, null, values);
        db.close();
    }

    // Getting single contact
    Person getPerson(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Person person = null;
        Cursor cursor = db.query(TEACHER_TABLE_NAME, new String[] { EMAIL,
                        NAME, PASSWORD }, EMAIL + "=?",
                new String[] { email }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            person = new Person(cursor.getString(1),
                    cursor.getString(0), cursor.getString(2));
        }
        // return contact
        return person;
    }


}