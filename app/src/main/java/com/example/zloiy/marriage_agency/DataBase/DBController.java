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
public class DBController implements DBColumns {
    private DBModel dbModel;
    private SQLiteDatabase database;
    private Context context;
    public DBController(Context context){
        this.context = context;
        dbModel = new DBModel(context);
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
    public DBController open() throws SQLException{
        database = dbModel.getWritableDatabase();
        return this;
    }
    public void close(){database.close();}
    public void insertWebsite(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        database.insert(WEBSITE_TABLE, null,contentValues);
    }
    public void insertTelephone(int telephone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, telephone);
        database.insert(TELEPHONE_TABLE, null, contentValues);
    }
    public void insertEmail(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        database.insert(EMAIL_TABLE, null, contentValues);
    }
    public void insertStreet(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        database.insert(STREET_TABLE, null, contentValues);
    }
    public void insertAgency(String name, int email_id, int telephone_id, int website_id, int street_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(TELEPHONE_ID, telephone_id);
        contentValues.put(EMAIL_ID, email_id);
        contentValues.put(WEBSITE_ID, website_id);
        contentValues.put(STREET_ID, street_id);
        database.insert(AGENCY_TABLE, null, contentValues);
    }
    public Cursor readWebsite(){
        Cursor cursor = database.query(WEBSITE_TABLE, null, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readWebsite(int id){
        Cursor cursor = database.query(WEBSITE_TABLE, null, ID + "=" + id, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readTelephone(){
        Cursor cursor = database.query(TELEPHONE_TABLE, null, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readTelephone(int id){
        Cursor cursor = database.query(TELEPHONE_TABLE, null, ID + "=" + id, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readEmail(){
        Cursor cursor = database.query(EMAIL_TABLE, null, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readEmail(int id){
        Cursor cursor = database.query(EMAIL_TABLE, null, ID + "=" + id, null, null, null, null);
        if(cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readStreet(){
        Cursor cursor = database.query(STREET_TABLE, null, null, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readStreet(int id){
        Cursor cursor = database.query(STREET_TABLE, null, ID + "=" + id, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readAgency(){
        String tables = "agency_table as A1 inner join telephone_table as T on A1.telephone_id = T._id, " +
                " agency_table as A2 inner join website_table as W on A2.website_id = W._id, " +
                " agency_table as A3 inner join email_table as E on A3.email_id = E._id, " +
                " agency_table as A4 inner join street_table as S on A4.street_id = S._id";
        String[] columns = {"A1.name as Name", "T.name as Telephone", "W.name as Website", "E.name as Email", "S.name as Street"};
        Cursor cursor = database.query(tables, columns, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readAllAgency(){
        Cursor cursor = database.query(AGENCY_TABLE, null, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public Cursor readAgency(int id){
        Cursor cursor = database.query(AGENCY_TABLE, null, ID + "=" + id, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }
    public int updateWebsite(String name, int id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        return database.update(WEBSITE_TABLE, contentValues, ID + "=" + id, null);
    }
    public int updateTelephone(int telephone, int id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, telephone);
        return database.update(TELEPHONE_TABLE, contentValues, ID + "=" + id, null);
    }
    public int updateEmail(String name, int id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        return database.update(EMAIL_TABLE, contentValues, ID + "=" + id, null);
    }
    public int updateAgency(String name, int id, int website_id, int telephone_id, int email_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(WEBSITE_ID, website_id);
        contentValues.put(TELEPHONE_ID, telephone_id);
        contentValues.put(EMAIL_ID, email_id);
        return database.update(AGENCY_TABLE, contentValues, ID + "=" + id, null);
    }
    public void deleteWebsite(int id){database.delete(WEBSITE_TABLE, ID + "=" + id, null);}
    public void deleteTelephone(int id){database.delete(TELEPHONE_TABLE, ID + "=" + id, null);}
    public void deleteEmail(int id){database.delete(EMAIL_TABLE, ID + "=" + id, null);}
    public void deleteAgency(int id){database.delete(AGENCY_TABLE, ID + "=" + id, null);}
}
