package com.example.pwaterapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pwaterapp.R;
import com.example.pwaterapp.WaterActivity;
import com.example.pwaterapp.model.Water;

import java.util.ArrayList;

public class adapterwater extends BaseAdapter {

    private WaterActivity context;

    private ArrayList<Water> ArrayWaterList;

    public adapterwater(WaterActivity context, ArrayList<Water> arrayWaterList) {
        this.context = context;
        ArrayWaterList = arrayWaterList;
    }

    @Override
    public int getCount() {
        return ArrayWaterList.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayWaterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //list water
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listwater,null);

        TextView TextViewBrand = convertView.findViewById(R.id.textViewBrand);
        TextView TextViewType = convertView.findViewById(R.id.textViewType);
        ImageButton imageUpdate = convertView.findViewById(R.id.updateWater);
        ImageButton imageDelete = convertView.findViewById(R.id.deleteWater);

        Water water = ArrayWaterList.get(position);

        TextViewBrand.setText(water.getBrand());
        TextViewType.setText(water.getType()+"");

        int id = water.getId();

        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
}
