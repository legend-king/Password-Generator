package com.example.passwordgenerator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Passwords(name TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Passwords");
    }

    public boolean insertData(String name, String pass){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("password",pass);
        long result = DB.insert("Passwords",null,contentValues);
        if (result==-1)
            return false;
        return true;
    }

    public boolean updateData(String name, String pass){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password",pass);

        Cursor cursor = DB.rawQuery("Select * from Passwords where name = ?",new String[] {name});
        if (cursor.getCount()>0) {
            long result = DB.update("Passwords", contentValues, "name=?", new String[]{name});
            if (result == -1)
                return false;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deleteData(String name){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Passwords where name = ?",new String[] {name});
        if (cursor.getCount()>0){
            long result = DB.delete("Passwords","name=?", new String[]{name});
            if (result==-1)
                return false;
            return true;
        }
        else{
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Passwords",null);
        return cursor;
    }
}
