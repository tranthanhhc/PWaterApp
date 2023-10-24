package com.example.pwaterapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import com.example.pwaterapp.model.Customer;
import com.example.pwaterapp.model.Water;

public class Database extends SQLiteOpenHelper {

    // Database Name
    private static String DATABASE_NAME = "WaterPureManagement";
    // list Customer
    private static String TABLE_CUSTOMERS = "customer";
    private static String ID_CUSTOMERS = "idcustomer";
    private static String NAME_CUSTOMERS = "namecustomer";
    private static String ADDRESS_CUSTOMERS = "address";
    private static String WATER_TYPE = "type";
    private static String WATER_BRAND = "brand";
    private static String TIME = "time";
    private static String IMAGE = "image";
    private static int VERSION = 1;

    // list Water
    private static String TABLE_WATER = "water";
    private static String ID_WATER = "idwater";
    private static String TYPE = "typewater";
    private static String BRAND = "brandwater";

    // Create list Cus
    private String SQLQuery = "CREATE TABLE " + TABLE_CUSTOMERS + " (" + ID_CUSTOMERS + " INTEGER primary key AUTOINCREMENT, "
            + NAME_CUSTOMERS + " TEXT, " + ADDRESS_CUSTOMERS + " TEXT, " + WATER_TYPE + " INTEGER, " + WATER_BRAND + " TEXT, "
            + TIME + " TEXT, " + IMAGE + " BLOB, "
            + ID_WATER + " INTEGER, FOREIGN KEY (" + ID_WATER + ") REFERENCES " + TABLE_WATER + " (" + ID_WATER + "))";

    // Create list Water
    private String SQLQuery1 = "CREATE TABLE " + TABLE_WATER + " (" + ID_WATER + " INTEGER primary key AUTOINCREMENT, "
            + TYPE + " INTEGER, " + BRAND + " TEXT)";

    // Join type and brand to cus
    //private String SQLQuery2 = "SELECT cus.*, w." + TYPE + ", w." + BRAND + " FROM " + TABLE_CUSTOMERS + " cus" +
            //" INNER JOIN " + TABLE_WATER + " w ON cus." + ID_WATER + " = w." + ID_WATER +
            //" WHERE cus." + ID_CUSTOMERS + " = ?";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Function Add new Customer
    public void AddCuss(Customer cus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_CUSTOMERS,cus.getName());
        values.put(ADDRESS_CUSTOMERS,cus.getAddress());
        values.put(WATER_TYPE,cus.getWater_type());
        values.put(WATER_BRAND,cus.getWater_brand());
        values.put(TIME,cus.getTime());
        values.put(IMAGE,cus.getImage());

        db.insert(TABLE_CUSTOMERS,null,values);
        db.close();
    }
    //Function update Customer
    public boolean UpdateCuss(Customer cus , int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_CUSTOMERS,cus.getName());
        values.put(ADDRESS_CUSTOMERS,cus.getAddress());
        values.put(WATER_TYPE,cus.getWater_type());
        values.put(WATER_BRAND,cus.getWater_brand());
        values.put(TIME,cus.getTime());
        values.put(IMAGE,cus.getImage());

        db.update(TABLE_CUSTOMERS , values, ID_CUSTOMERS + " = "+ id, null);
        return true;
    }
    //get data customer
    public Cursor getDataCuss() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMERS, null);
        return cursor;
    }
    //delete Customer
    public int DeleteCuss(int i ) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_CUSTOMERS,ID_CUSTOMERS + " = "+i,null);
        return res;
    }

    //get data water
    public Cursor getDataWater() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_WATER, null);
        return cursor;
    }
    //Function Add new Water
    public void AddWater(Water wat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TYPE,wat.getType());
        values.put(BRAND,wat.getBrand());


        db.insert(TABLE_WATER,null,values);
        db.close();
    }
    // delete water
    public int DeleteWater(int i ) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_WATER,ID_WATER + " = "+i,null);
        return res;
    }

    //update Water
    public boolean UpdateWater(Water water , int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TYPE,water.getType());
        values.put(BRAND,water.getBrand());

        db.update(TABLE_WATER , values, ID_WATER + " = "+ id, null);
        return true;
    }

}
