package com.example.zloiy.marriage_agency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zloiy.marriage_agency.DataBase.DBController;


public class MainActivity extends AppCompatActivity {
    DBController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Свадебный салон");
        controller = new DBController(this);
        controller.open();
//        Button addBtn = (Button) findViewById(R.id.add_btn);
        ListView list = (ListView) findViewById(R.id.list_view);
//        String[] agency = {"marriage_agency"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, agency);
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
        /*addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });*/
    }

    @Override
    protected void onStop() {
        controller.close();
        super.onStop();
    }
}
