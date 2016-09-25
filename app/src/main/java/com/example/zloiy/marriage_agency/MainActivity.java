package com.example.zloiy.marriage_agency;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;


public class MainActivity extends FragmentActivity {
    Fragment fragment1;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Bundle extras = getIntent().getExtras();
        /*if (extras != null){
        ListView listView = (ListView) findViewById(R.id.list_fragment);
        MyAdapter adapter = new MyAdapter(this);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);}*/
        ListFragment listFragment = new ListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.list_fragment,listFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
