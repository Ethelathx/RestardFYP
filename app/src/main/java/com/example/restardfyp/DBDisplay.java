package com.example.restardfyp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBDisplay extends SQLiteOpenHelper {

    //--------------------Setup---------------------
    private static final String DATABASE_NAME = "itemlist.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ITEM = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DETAILS = "details";
    private static final String COLUMN_PHOTO = "photo";
    private static final String COLUMN_PRICE = "price";
    //--------------------Setup---------------------

    public DBDisplay(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createItemTableSql = "CREATE TABLE " + TABLE_ITEM + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DETAILS + " TEXT, "
                + COLUMN_PHOTO + " INTEGER, "
                + COLUMN_PRICE + " FLOAT )";

        db.execSQL(createItemTableSql);

        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //--------------------------Insert----------------------
    public long InsertItem(String name, String details, int photo, double price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DETAILS, details);
        values.put(COLUMN_PHOTO, photo);
        values.put(COLUMN_PRICE, price);

        long result = db.insert(TABLE_ITEM, null, values);

        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }
    //--------------------------Insert----------------------



    //--------------------------GetItem----------------------
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<Item>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + ","+COLUMN_DETAILS+ ","+COLUMN_PHOTO+ ","+COLUMN_PRICE  + " FROM " + TABLE_ITEM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String details = cursor.getString(2);
                int photo = cursor.getInt(3);
                double price = cursor.getDouble(4);
                Item newItems = new Item(id, name, details, photo, price);
                items.add(newItems);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return items;
    }
    //--------------------------GetItem----------------------

}
