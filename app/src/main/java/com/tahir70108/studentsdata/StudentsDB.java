package com.tahir70108.studentsdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentsDB {

    public static final String  KEY_ROWID="_id";
    public static final String  KEY_NAME="_student_name";
    public static final String  KEY_STD_ID="_std_ID";
    public static final String  KEY_PHONE="_phone";
    public static final String  KEY_EMAIL="_emial";

    private final String DATABASE_NAME="StudentDB";
    private final String DATABASE_TABLE="Students";
    private final int DATABASE_VERSION =1;

    private  DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public StudentsDB(Context context){
        ourContext =context;
    }

    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                  /*
               CREATE TABLE StudentTable(
               _id INTEGER PRIMARY KEY AUTOINCREMENT,
               student_name TEXT NOT NULL,
               Std_ID TEXT not NUll,
               _Phone TEXT not NUll,
               _Email TEXT not NUll,
            */

            String sqlCode ="CREATE TABLE "+DATABASE_TABLE+"("
                    +KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +KEY_NAME+" TEXT NOT NULL,"
                    +KEY_STD_ID+"TEXT NOT NULL,"
                    +KEY_PHONE+"TEXT NOT NULL,"
                    +KEY_EMAIL+" TEXT not NUll);";
            db.execSQL(sqlCode);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE );
            onCreate(db);
        }
    }

    public  StudentsDB open(){
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long createEntry(String name,String id, String phone , String eMail){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_STD_ID,id);
        cv.put(KEY_PHONE,phone);
        cv.put(KEY_EMAIL,eMail);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }


    public String getData(){
        String []columns = new String[]{KEY_ROWID,KEY_NAME,KEY_STD_ID,KEY_PHONE,KEY_EMAIL};
        Cursor cursor = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result ="";
        int irowID =cursor.getColumnIndex(KEY_ROWID);
        int iRowName =cursor.getColumnIndex(KEY_NAME);
        int iRowstdID =cursor.getColumnIndex(KEY_STD_ID);
        int iRowPhone =cursor.getColumnIndex(KEY_PHONE);
        int iEmail =cursor.getColumnIndex(KEY_EMAIL);
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            result  = result+ cursor.getString(irowID)+ " : " + cursor.getString(iRowName)
                    +"  "+ cursor.getString(iRowstdID)+"  "
                    + cursor.getString(iRowPhone)+"  "
                    + cursor.getString(iEmail)+"\n";
        }
         cursor.close();
        return result;
    }

    public long updateEntry(String stdID, String newName ,String newStdID,String phone,String eMail){
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,newName);
        cv.put(KEY_STD_ID,newStdID);
        cv.put(KEY_PHONE,phone);
        cv.put(KEY_EMAIL,eMail);
        return ourDatabase.update(DATABASE_TABLE,cv,KEY_STD_ID+"=?", new String[]{stdID} );

    }

    public long deleteEntry(String stdID){
        return ourDatabase.delete(DATABASE_TABLE,KEY_STD_ID+"=?",new String[]{stdID});
    }



}
