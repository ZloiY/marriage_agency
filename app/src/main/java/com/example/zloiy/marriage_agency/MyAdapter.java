package com.example.zloiy.marriage_agency;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zloiy.marriage_agency.DataBase.AgencyDAO;
import com.example.zloiy.marriage_agency.DataBase.DBColumns;

public class MyAdapter extends BaseAdapter implements DBColumns {
    private AgencyDAO agencyDAO;
    private LayoutInflater mInflater;
    private Context context;
    public MyAdapter(Context context){
        agencyDAO = new AgencyDAO(context);
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return agencyDAO.readAllAgency().getCount();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return  agencyDAO.readAgency(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.adapter_layout, null);
        Cursor cursor = agencyDAO.readAgency(position+1);
        TextView agencyName = (TextView) convertView.findViewById(R.id.agency_name);
        ImageView agencyImage = (ImageView) convertView.findViewById(R.id.agency_image);
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        agencyName.setText(name);
        int imageRes = context.getResources().getIdentifier("drawable/"+name.toLowerCase(), null, context.getPackageName());
        BitmapWorker worker = new BitmapWorker(agencyImage, context);
        worker.execute(imageRes);
        return convertView;
    }
}
