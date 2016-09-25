package com.example.zloiy.marriage_agency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.zloiy.marriage_agency.DataBase.DBController;

/**
 * Created by ZloiY on 13-Sep-16.
 */
public class InfoActivity extends AppCompatActivity {
    DBController controller;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        controller = new DBController(this);
        controller.open();
        TextView telephone = (TextView)findViewById(R.id.telephone_1);
    }

    @Override
    protected void onStop() {
        controller.close();
        super.onStop();
    }
}