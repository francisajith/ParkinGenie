package com.example.parkinGenie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context){
        super(context, "Parkingenie.db", null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(userId int primary key, firstName text, lastName text, email text, password text, telephone text, role text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }


    //insert into database
    public boolean insert(int userId, String firstName, String lastName, String email, String password, String telephone, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("userId",userId);
        contentValues.put("firstName",firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("telephone",telephone);
        contentValues.put("role", role);
        long ins = db.insert("user", null, contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }
    //check if email exists
    public Boolean checkEmail(String email){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
        //}

        //to be used in future (at log in)
        //check if Password is correct
        //public Boolean checkPassword(String Email, String password){ SQLiteDatabase db = this.getReadableDatabase();
        // Cursor cursor = db.rawQuery("Select * from user where email=? and
        //       password=?", new String[]{Email, password}); if(cursor.getCount()>0) return true; else return false;
        //}
    }
}