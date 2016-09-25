package com.example.zloiy.marriage_agency;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zloiy.marriage_agency.DataBase.DBColumns;
import com.example.zloiy.marriage_agency.DataBase.DBController;

/**
 * Created by ZloiY on 20-Sep-16.
 */
public class MyAdapter extends BaseAdapter implements DBColumns {
    DBController controller;
    LayoutInflater mInflater;
    public MyAdapter(Context context){
        controller = new DBController(context);
        controller.open();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return controller.readAllAgency().getCount();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return  controller.readAgency(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.adapter_layout, null);
        Cursor cursor = controller.readAgency(position+1);
        TextView agencyName = (TextView) convertView.findViewById(R.id.agency_name);
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        agencyName.setText(name);
        return convertView;
    }
}
