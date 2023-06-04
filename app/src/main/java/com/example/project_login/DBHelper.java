package com.example.project_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "users.db";
    public static final String TABLENAME = "users";
    public static final String COL1 = "username";
    public static final String COL2 = "password";
    public static final String COL3 = "phone";



    public DBHelper( Context context)
    {
        super(context,
                DBNAME,
                null,
                1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    { sqLiteDatabase.execSQL("create Table " + TABLENAME + "(" + COL1 + " TEXT primary key, " + COL2 + " TEXT, " + COL3 + " Text) "); }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    { sqLiteDatabase.execSQL("drop Table if exists " + TABLENAME);
    }

    public Boolean insertData(String username, String password, String phone)
    { SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1, username);
        contentValues.put(COL2, password);
        contentValues.put(COL3, phone);
        long result = MyDB.insert(TABLENAME, null, contentValues);
        if(result==-1)
            return false; return true;
    }
    public Boolean checkUsername(String username)
    { SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL1 + " = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true; return false;
    }
    public Boolean checkUsernamePassword(String username, String password)
    { SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from " + TABLENAME + " where " + COL1 + " = ? and " + COL2 +
                " = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true; return false;
    }

}

