package com.example.pwaterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pwaterapp.adapter.adaptercuss;
import com.example.pwaterapp.adapter.adapterwater;
import com.example.pwaterapp.database.Database;
import com.example.pwaterapp.model.Customer;
import com.example.pwaterapp.model.Water;

import java.util.ArrayList;

public class CustomersActivity extends AppCompatActivity {
    Toolbar toolbarCus;
    ListView listviewCus;

    ArrayList<Customer> ArrayListCuss;
    com.example.pwaterapp.database.Database database;
    com.example.pwaterapp.adapter.adaptercuss adaptercuss;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        toolbarCus = (Toolbar) findViewById(R.id.toolbarCus);
        listviewCus = (ListView) findViewById(R.id.listviewCus);

        //event back to MainAc

        setSupportActionBar(toolbarCus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new Database(this);

        ArrayListCuss = new ArrayList<>();
        Cursor cursor = database.getDataCuss();

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String Address = cursor.getString(2);
            int type = cursor.getInt(3);
            String brand = cursor.getString(4);
            String time = cursor.getString(5);
            byte[] image = cursor.getBlob(6); // link image

            ArrayListCuss.add(new Customer(id,name,Address,type,brand,time,image));
        }

        adaptercuss = new adaptercuss(CustomersActivity.this, ArrayListCuss);
        listviewCus.setAdapter(adaptercuss);
        cursor.moveToFirst();
        cursor.close();

        listviewCus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(CustomersActivity.this);
                    dialog.setContentView(R.layout.dialogdetail);
                TextView textViewName = dialog.findViewById(R.id.TextViewNameCuss);
                TextView textViewAddress = dialog.findViewById(R.id.TextViewAddressCuss);
                TextView textViewType = dialog.findViewById(R.id.TextViewTypeCuss);
                TextView textViewBrand = dialog.findViewById(R.id.TextViewBrandCuss);
                TextView textViewDate = dialog.findViewById(R.id.TextViewDateCuss);
                ImageView imageView = dialog.findViewById(R.id.imageViewCus);

                Customer selectedCustomer = ArrayListCuss.get(position);

                textViewName.setText(selectedCustomer.getName());
                textViewAddress.setText(selectedCustomer.getAddress());
                textViewType.setText(String.valueOf(selectedCustomer.getWater_type()));
                textViewBrand.setText(selectedCustomer.getWater_brand());
                //textViewDate.setText(selectedCustomer.getTime());
                //imageView.setImageBitmap(BitmapFactory.decodeByteArray(selectedCustomer.getImage(), 0, selectedCustomer.getImage().length));

                dialog.show();
                dialog.setCanceledOnTouchOutside(true);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuaddc,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuaddC) {
            Intent intent5 = new Intent(CustomersActivity.this, AddCuss.class);
            startActivity(intent5);
        }
        else {
            Intent intentCancel = new Intent(CustomersActivity.this, MainActivity.class);
            startActivity(intentCancel);
        }
        return super.onOptionsItemSelected(item);
    }
}