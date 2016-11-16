package com.example.zloiy.marriage_agency.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zloiy.marriage_agency.DataBase.Agency;
import com.example.zloiy.marriage_agency.DataBase.AgencyDAO;
import com.example.zloiy.marriage_agency.DataBase.DBColumns;
import com.example.zloiy.marriage_agency.R;

public class InfoActivity extends AppCompatActivity implements DBColumns{
    private AgencyDAO agencyDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        agencyDAO = new AgencyDAO(this);
        final TextView telephone = (TextView)findViewById(R.id.telephone_1);
        final TextView email = (TextView)findViewById(R.id.email_name);
        final TextView website = (TextView)findViewById(R.id.web_adress);
        final TextView street = (TextView)findViewById(R.id.street_name);
        ImageView imageView = (ImageView)findViewById(R.id.info_image);
        int pos = getIntent().getIntExtra("position", 0);
        Cursor cursor = agencyDAO.readAgency(pos+1);
        Agency agency = new Agency();
        agency.setAgencyName(cursor.getString(cursor.getColumnIndex(NAME)));
        agency.setTelID(cursor.getInt(cursor.getColumnIndex(TELEPHONE_ID)));
        agency.setWebID(cursor.getInt(cursor.getColumnIndex(WEBSITE_ID)));
        agency.setEmailID(cursor.getInt(cursor.getColumnIndex(EMAIL_ID)));
        agency.setStreetID(cursor.getInt(cursor.getColumnIndex(STREET_ID)));
        setTitle(agency.getAgencyName());
        int imageRes = getBaseContext().getResources().getIdentifier("drawable/"+agency.getAgencyName().toLowerCase(), null, getBaseContext().getPackageName());
        Drawable image = getBaseContext().getResources().getDrawable(imageRes);
        imageView.setImageDrawable(image);
        email.setText(agencyDAO.readEmail(agency.getEmailID()));
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(InfoActivity.this, MailActivity.class);
                mailIntent.putExtra("mail_address", email.getText().toString());
                mailIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mailIntent);
            }
        });
        telephone.setText(agencyDAO.readTelephone(agency.getTelID()));
        website.setText(agencyDAO.readWebsite(agency.getTelID()));
        street.setText(agencyDAO.readStreet(agency.getStreetID()));
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+website.getText().toString()));
                startActivity(browserIntent);
            }
        });
        telephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:375"+telephone.getText().toString()));
                try{
                    startActivity(Intent.createChooser(phoneIntent, "Call app..."));
                }catch (ActivityNotFoundException ex){
                    Toast.makeText(InfoActivity.this, "No call apps install.", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        agencyDAO.close();
        super.onStop();
    }
}