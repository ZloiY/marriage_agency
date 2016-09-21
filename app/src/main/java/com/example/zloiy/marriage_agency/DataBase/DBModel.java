package com.example.zloiy.marriage_agency.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zloiy.marriage_agency.DataBase.DBColumns;

/**
 * Created by ZloiY on 14-Sep-16.
 */
public class DBModel extends SQLiteOpenHelper implements DBColumns {
    public static final String DB_NAME = "agency.db";
    public static final int DB_VERSION = 1;
    public static final String CREATE_AGENCY = "create table " + AGENCY_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " text not null, " + TELEPHONE_ID + " integer not null, " +
            WEBSITE_ID + " integer not null, " + EMAIL_ID + " integer not null, " + STREET_ID + " text not null)";
    public static final String CREATE_EMAIL = "create table " + EMAIL_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " text not null" + ")";
    public static final String CREATE_WEBSITE = "create table " + WEBSITE_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " text not null" + ")";
    public static final String CREATE_TELEPHONE = "create table " + TELEPHONE_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " int not null" + ")";
    public static final String CREATE_STREET = "create table " + STREET_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " text not null" + ")";
    public DBModel(Context context){super(context, DB_NAME, null, DB_VERSION);}
    public void onCreate (SQLiteDatabase database){
        database.execSQL(CREATE_AGENCY);
        database.execSQL(CREATE_EMAIL);
        database.execSQL(CREATE_WEBSITE);
        database.execSQL(CREATE_TELEPHONE);
        database.execSQL(CREATE_STREET);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop if exists" + CREATE_AGENCY);
        db.execSQL("drop if exists" + CREATE_EMAIL);
        db.execSQL("drop if exists" + CREATE_WEBSITE);
        db.execSQL("drop if exists" + CREATE_TELEPHONE);
        db.execSQL("drop if exists" + CREATE_STREET);
    }
}
