package com.example.zloiy.marriage_agency.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zloiy.marriage_agency.DataBase.Agency;
import com.example.zloiy.marriage_agency.DataBase.AgencyDAO;
import com.example.zloiy.marriage_agency.DataBase.DBColumns;
import com.example.zloiy.marriage_agency.R;

/**
 * Created by ZloiY on 20-Sep-16.
 */
public class AddActivity extends Activity implements DBColumns {
    AgencyDAO agencyDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adddialog_layout);
        setTitle("Add item:");
        final EditText agencyName = (EditText)findViewById(R.id.agency_name);
        final EditText telephoneNumb = (EditText)findViewById(R.id.telephone_number);
        final EditText emailName = (EditText)findViewById(R.id.email_name);
        final EditText websiteName = (EditText)findViewById(R.id.website_name);
        final EditText streetName = (EditText)findViewById(R.id.street_name);
        Button addBtn = (Button)findViewById(R.id.add_btn);
        Button cnclBtn = (Button)findViewById(R.id.cncl_btn);
        agencyDAO = new AgencyDAO(this);
        final Toast toast = Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT);
        cnclBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!agencyName.getText().toString().isEmpty() && !telephoneNumb.getText().toString().isEmpty()
                        && !emailName.getText().toString().isEmpty() && !websiteName.getText().toString().isEmpty()) {
                    int posEmail, posWebsite, posTel, posStreet;
                    Agency agency = new Agency();
                    agency.setAgencyName(agencyName.getText().toString());
                    Cursor cursorEmail, cursorWeb, cursorTel, cursorSt;
                    cursorEmail = agencyDAO.readEmail();
                    cursorWeb = agencyDAO.readWebsite();
                    cursorTel = agencyDAO.readTelephone();
                    cursorSt = agencyDAO.readStreet();
                    posEmail = searchSame(cursorEmail, emailName.getText().toString());
                    posWebsite = searchSame(cursorWeb, websiteName.getText().toString());
                    posTel = searchSame(cursorTel, Integer.parseInt(telephoneNumb.getText().toString()));
                    posStreet = searchSame(cursorSt, streetName.getText().toString());
                    if (posEmail == 0){
                        agencyDAO.insertEmail(emailName.getText().toString());
                        cursorEmail = agencyDAO.readEmail();
                        cursorEmail.moveToLast();
                        agency.setEmailID(cursorEmail.getInt(cursorEmail.getColumnIndex(ID)));}
                    else agency.setEmailID(posEmail);
                    if (posWebsite == 0){
                        agencyDAO.insertWebsite(websiteName.getText().toString());
                        cursorWeb = agencyDAO.readWebsite();
                        cursorWeb.moveToLast();
                        agency.setWebID(cursorWeb.getInt(cursorWeb.getColumnIndex(ID)));
                    }else agency.setWebID(posWebsite);
                    if (posTel == 0){
                        agencyDAO.insertTelephone(Integer.parseInt(telephoneNumb.getText().toString()));
                        cursorTel = agencyDAO.readTelephone();
                        cursorTel.moveToLast();
                        agency.setTelID(cursorTel.getInt(cursorTel.getColumnIndex(ID)));
                    }else  agency.setTelID(posTel);
                    if (posStreet == 0){
                        agencyDAO.insertStreet(streetName.getText().toString());
                        cursorSt = agencyDAO.readStreet();
                        cursorSt.moveToLast();
                        agency.setStreetID(cursorSt.getInt(cursorSt.getColumnIndex(ID)));
                    }else agency.setStreetID(posStreet);
                    agencyDAO.insertAgency(agency);
                }else toast.show();
            }
        });
    }

    @Override
    protected void onStop() {
        agencyDAO.close();
        super.onStop();
    }

    private void mainActivity(){
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private int searchSame(Cursor cursor, String name){
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

    private int searchSame(Cursor cursor, int telephone){
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
