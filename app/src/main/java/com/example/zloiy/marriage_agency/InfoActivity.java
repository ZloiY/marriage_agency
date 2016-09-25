package com.example.zloiy.marriage_agency;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.zloiy.marriage_agency.DataBase.DBColumns;
import com.example.zloiy.marriage_agency.DataBase.DBController;

/**
 * Created by ZloiY on 13-Sep-16.
 */
public class InfoActivity extends AppCompatActivity implements DBColumns{
    DBController controller;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        controller = new DBController(this);
        controller.open();
        TextView telephone = (TextView)findViewById(R.id.telephone_1);
        TextView email = (TextView) findViewById(R.id.email_name);
        final TextView website = (TextView)findViewById(R.id.web_adress);
        TextView street = (TextView)findViewById(R.id.street_name);
        int pos = getIntent().getIntExtra("position", 1);
        Cursor cursor = controller.readAgency(pos+1);
        setTitle(cursor.getString(cursor.getColumnIndex(NAME)));
        Cursor subCur = controller.readEmail(cursor.getInt(cursor.getColumnIndex(EMAIL_ID)));
        email.setText(subCur.getString(subCur.getColumnIndex(NAME)));
        subCur = controller.readTelephone(cursor.getInt(cursor.getColumnIndex(TELEPHONE_ID)));
        telephone.setText(subCur.getString(subCur.getColumnIndex(NAME)));
        subCur = controller.readWebsite(cursor.getInt(cursor.getColumnIndex(WEBSITE_ID)));
        website.setText(subCur.getString(subCur.getColumnIndex(NAME)));
        subCur = controller.readStreet(cursor.getInt(cursor.getColumnIndex(STREET_ID)));
        street.setText(subCur.getString(subCur.getColumnIndex(NAME)));
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+website.getText().toString()));
                startActivity(browserIntent);
            }
        });
        street.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?=q"+Uri.encode("Minsk")));
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    @Override
    protected void onStop() {
        controller.close();
        super.onStop();
    }
}