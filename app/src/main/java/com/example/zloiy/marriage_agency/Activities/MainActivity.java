package com.example.zloiy.marriage_agency.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zloiy.marriage_agency.DataBase.AgencyDAO;
import com.example.zloiy.marriage_agency.DataBase.AgencyDBDAO;
import com.example.zloiy.marriage_agency.MyAdapter;
import com.example.zloiy.marriage_agency.R;


public class MainActivity extends AppCompatActivity {
    private AgencyDBDAO agencyDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Свадебный салон");
        agencyDAO = new AgencyDAO(this);
        ListView list = (ListView) findViewById(R.id.list_view);
        final MyAdapter adapter = new MyAdapter(this);
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("position", position);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        agencyDAO.close();
        super.onStop();
    }
}
