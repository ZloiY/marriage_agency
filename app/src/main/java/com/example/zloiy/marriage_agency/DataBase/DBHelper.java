package com.example.zloiy.marriage_agency.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DBHelper extends SQLiteOpenHelper implements DBColumns {
    public static final String DB_NAME = "agency.db";
    public static  String DB_PATH = "/data/data/com.example.zloiy.marriage_agency/databases/";
    public static final int DB_VERSION = 1;
    public static final String CREATE_AGENCY = "create table " + AGENCY_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " text not null, " + TELEPHONE_ID + " integer not null, " +
            WEBSITE_ID + " integer not null, " + EMAIL_ID + " integer not null, " + STREET_ID + " text not null)";
    public static final String CREATE_EMAIL = "create table " + EMAIL_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " text not null" + ")";
    public static final String CREATE_WEBSITE = "create table " + WEBSITE_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " text not null" + ")";
    public static final String CREATE_TELEPHONE = "create table " + TELEPHONE_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " int not null" + ")";
    public static final String CREATE_STREET = "create table " + STREET_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " text not null" + ")";
    private final Context myContext;
    private SQLiteDatabase myDataBase;
    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        myContext = context;
        DB_PATH = myContext.getFilesDir().getPath();}
    public void  createDatabase()throws IOException{
        boolean dbExist = checkDataBase();
//        boolean dbExist = false;
        if(!dbExist){
            this.getReadableDatabase();
            try{
                copyDataBase();
            }catch (IOException e){
                throw new Error("Error copying database!");
            }
        }
    }
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){
            Log.e("Log", "DataBase didn't create.");
        }
        if(checkDB!=null)checkDB.close();
        return checkDB!=null;
    }
    private void copyDataBase()throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int lentgh;
        while ((lentgh = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, lentgh);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
    public void openDataBase() {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
    public synchronized void close(){
        if(myDataBase!=null)myDataBase.close();
        super.close();
    }
    public void onCreate (SQLiteDatabase database){
        /*database.execSQL(CREATE_AGENCY);
        database.execSQL(CREATE_EMAIL);
        database.execSQL(CREATE_WEBSITE);
        database.execSQL(CREATE_TELEPHONE);
        database.execSQL(CREATE_STREET);*/
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop if exists" + CREATE_AGENCY);
        db.execSQL("drop if exists" + CREATE_EMAIL);
        db.execSQL("drop if exists" + CREATE_WEBSITE);
        db.execSQL("drop if exists" + CREATE_TELEPHONE);
        db.execSQL("drop if exists" + CREATE_STREET);
    }
}
