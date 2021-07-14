package com.tahir70108.studentsdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;
import java.util.EnumMap;

public class DBStudents {
    public static final String TABLE_STUDENT="students";
    public static final String DATABASE_NAME="studentsDB";

    public static final String COLUMN_ID="_id";
    public static final String STUDENT_NAME="student_name";
    public static final String STUDENT_ID_NO="student_id";
    public static final String STUDENT_EMAIL="students_email";
    public static final String STUDENT_PHONE_NUMBER="students_phoneNumber";






    private final int VERSION =1;

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
                super(context,DATABASE_NAME,null,VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

             String SQL_Query ="CREATE TABLE " +TABLE_STUDENT
                    +"(" +COLUMN_ID+" TEXT PRIMARY KEY AUTOINCREMENT  , "
                    +STUDENT_NAME+" TEXT NOT NULL,"
                    +STUDENT_ID_NO+" TEXT NOT NULL,"
                    + STUDENT_EMAIL+" TEXT NOT NULL,"
                    +STUDENT_PHONE_NUMBER+ "TEXT NOT NULL);";
            db.execSQL(SQL_Query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT);
                onCreate(db);
        }
    }



}

