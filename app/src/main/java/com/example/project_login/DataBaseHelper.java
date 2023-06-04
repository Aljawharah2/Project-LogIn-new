package com.example.project_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String ITEMS_TABLE = "Items_Table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "Items_Title";
    public static final String COLUMN_DESCRIPTION = "Items_Desc";
    public static final String COLUMN_PRICE = "Items_Price";
    public static final String COLUMN_SIZE = "Items_Size";
    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String RENT_TABLE = "rent_table";

    //public static final String COLUMN_IMAGE = "Items_Image";
    //private ByteArrayOutputStream byteArray ;
    //byte[] img = byteArray.toByteArray() ;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "added_items.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*COLUMN_IMAGE + " BLOB, " +*/
        String createTableStatement = "Create TABLE " + ITEMS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT," + COLUMN_PRICE + " INT, "+ COLUMN_SIZE + " TEXT )"; ;
        db.execSQL(createTableStatement) ;

        String createRentTableStatement = "Create TABLE " + RENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_ID + " INT )";
        db.execSQL(createRentTableStatement) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + RENT_TABLE);
        onCreate(db);
    }

    public boolean addOne(AddModel addMod){
        SQLiteDatabase db = this.getWritableDatabase();
        /*Bitmap imageToStoreBitmap = addMod.getImage() ;
        byteArray = new ByteArrayOutputStream() ;
        imageToStoreBitmap.compress(Bitmap.CompressFormat., 100, byteArray) ;
        img = byteArray.toByteArray();*/

        ContentValues cv = new ContentValues();
        //cv.put(COLUMN_IMAGE, img) ;
        cv.put(COLUMN_TITLE, addMod.getTitle());
        cv.put(COLUMN_DESCRIPTION, addMod.getDesc());
        cv.put(COLUMN_PRICE, addMod.getPrice());
        cv.put(COLUMN_SIZE, addMod.getSize());
        long insert = db.insert(ITEMS_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public int DeleteOne(@NonNull AddModel addMod){
        SQLiteDatabase db = this.getWritableDatabase();
        /*
        String queryString= "Delete From " + ITEMS_TABLE + " WHERE " + COLUMN_ID + " = " + addMod.getId() ;
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        } else{
            // nothing happens. no one is added.
            return false;
        }
        */
        returnItem(addMod.getId());
        return db.delete(ITEMS_TABLE, "ID=?", new String[]{Integer.toString(addMod.getId())});
        //close
    }
    public List<AddModel> getEveryone() {
        List<AddModel> returnList = new ArrayList<>();
        //get data from DB
        String queryString = "SELECT * FROM " + ITEMS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int itemID = cursor.getInt(0);
                String itemtitle = cursor.getString(1);
                String itemdesc = cursor.getString(2);
                int itemPr = cursor.getInt(3);
                String itemsize = cursor.getString(4);

                AddModel newAdd = new AddModel(itemID, itemtitle, itemdesc, itemPr, itemsize);
                returnList.add(newAdd);
            } while (cursor.moveToNext());
        } else {
// doing nothing
        }
        cursor.close();
        db.close();


        return returnList;
    }

    public AddModel getData(int item_id) {
        AddModel returnData = null;
        //get data from DB
        String queryString = "SELECT * FROM " + ITEMS_TABLE+ " where ID = '"+item_id+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int itemID = cursor.getInt(0);
                String itemtitle = cursor.getString(1);
                String itemdesc = cursor.getString(2);
                int itemPr = cursor.getInt(3);
                String itemsize = cursor.getString(4);

                returnData = new AddModel(itemID, itemtitle, itemdesc, itemPr, itemsize);
            } while (cursor.moveToNext());
        } else {
// doing nothing
        }
        cursor.close();
        db.close();
        return  returnData;
    }

    public boolean isRent(int item_id) {
        boolean isRent = false;
        //get data from DB
        String queryString = "SELECT * FROM " + RENT_TABLE+ " where "+COLUMN_ITEM_ID+" = '"+item_id+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                isRent = true;
            } while (cursor.moveToNext());
        } else {
// doing nothing
        }
        cursor.close();
        db.close();
        return  isRent;
    }

    public int returnItem(int item_id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(RENT_TABLE, "item_id=?", new String[]{Integer.toString(item_id)});
    }

    public boolean rentItem(int item_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(COLUMN_IMAGE, img) ;
        cv.put(COLUMN_ITEM_ID, item_id);
        long insert = db.insert(RENT_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else {
            return true;
        }

    }

    public List<AddModel> getRentedItems() {

        List<AddModel> returnList = new ArrayList<>();
        //get data from DB
        String queryString = "SELECT * FROM " + ITEMS_TABLE + ", " + RENT_TABLE + " where "+ ITEMS_TABLE + ".ID = " + COLUMN_ITEM_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int itemID = cursor.getInt(0);
                String itemtitle = cursor.getString(1);
                String itemdesc = cursor.getString(2);
                int itemPr = cursor.getInt(3);
                String itemsize = cursor.getString(4);

                AddModel newAdd = new AddModel(itemID, itemtitle, itemdesc, itemPr, itemsize);
                returnList.add(newAdd);
            } while (cursor.moveToNext());
        } else {
// doing nothing
        }
        cursor.close();
        db.close();


        return returnList;
    }
}




