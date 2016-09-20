package com.example.zloiy.marriage_agency;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zloiy.marriage_agency.DataBase.DBColumns;
import com.example.zloiy.marriage_agency.DataBase.DBController;

/**
 * Created by ZloiY on 20-Sep-16.
 */
public class AddActivity extends Activity implements DBColumns {
    DBController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddialog_layout);
        setTitle("Add item:");
        EditText agencyName = (EditText)findViewById(R.id.agency_name);
        EditText telephoneNumb = (EditText)findViewById(R.id.telephone_number);
        EditText emailName = (EditText)findViewById(R.id.email_name);
        EditText websiteName = (EditText)findViewById(R.id.website_name);
        Button addBtn = (Button)findViewById(R.id.add_btn);
        Button cnclBtn = (Button)findViewById(R.id.cncl_btn);
        controller = new DBController(this);
        controller.open();
        cnclBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStop() {
        controller.close();
        super.onStop();
    }

    private int emailSearch(Cursor cursor, String name){
        int pos;
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                int index = cursor.getColumnIndex(NAME);
                String emailSame = cursor.getString(index);
                if (emailSame.equals(name)){
                    pos = cursor.getInt(cursor.getColumnIndex(ID));
                    break;
                }else pos=0;
            }while(cursor.moveToNext());
        }else pos=0;
        return pos;
    }

    private int websiteSearch(Cursor cursor, String name){
        int pos;
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                int index = cursor.getColumnIndex(NAME);
                String websiteSame = cursor.getString(index);
                if(websiteSame.equals(name)){
                    pos=cursor.getInt(cursor.getColumnIndex(ID));
                    break;
                }else pos=0;
            }while(cursor.moveToNext());
        }else pos=0;
        return pos;
    }

    private int telephoneSearch(Cursor cursor, int telephone){
        int pos;
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                int index = cursor.getColumnIndex(NAME);
                int telephoneSame = cursor.getInt(index);
                if(telephoneSame == telephone){
                    pos = cursor.getInt(cursor.getColumnIndex(ID));
                    break;
                }else pos=0;
            }while(cursor.moveToNext());
        }else pos=0;
        return pos;
    }
}
