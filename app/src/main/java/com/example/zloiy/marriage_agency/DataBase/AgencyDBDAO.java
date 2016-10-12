package com.example.zloiy.marriage_agency.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;


/**
 * Created by ZloiY on 14-Sep-16.
 */
public class AgencyDBDAO implements DBColumns {
    private DBHelper dbModel;
    private Context context;
    protected SQLiteDatabase database;
    public AgencyDBDAO(Context context){
        this.context = context;
        dbModel = new DBHelper(context);
        try {
            dbModel.createDatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            dbModel.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
    }
    public AgencyDBDAO open() throws SQLException{
        database = dbModel.getWritableDatabase();
        return this;
    }
    public void close(){database.close();}
}
