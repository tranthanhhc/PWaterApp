package com.example.pwaterapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pwaterapp.CustomersActivity;
import com.example.pwaterapp.R;
import com.example.pwaterapp.model.Customer;

import java.util.ArrayList;

public class adaptercuss extends BaseAdapter {

    private CustomersActivity context;

    private ArrayList<Customer> ArrayListCuss;

    public adaptercuss(CustomersActivity context, ArrayList<Customer> arrayListCuss) {
        this.context = context;
        ArrayListCuss = arrayListCuss;
    }

    @Override
    public int getCount() {
        return ArrayListCuss.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayListCuss.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listcuss,null);

        TextView TextViewName = convertView.findViewById(R.id.TextViewName);
        ImageButton imageDelete = convertView.findViewById(R.id.deleteCus);
        ImageButton imageUpdate = convertView.findViewById(R.id.updateCus);
        ImageButton imageCart = convertView.findViewById(R.id.cartCus);

        Customer cus = ArrayListCuss.get(position);

        TextViewName.setText(cus.getName());

        int id = cus.getId();

        imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteC(id);

            }
        });
        imageUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateC(id);
            }
        });imageCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }
}
