package com.example.pwaterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pwaterapp.database.Database;

public class HistoryCuss extends AppCompatActivity {
    TextView tvType,tvBrand,tvTime;
    Button btnBackH;

    com.example.pwaterapp.database.Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_cuss);

        btnBackH = (Button) findViewById(R.id.ButtonCartBack);
        tvType = (TextView) findViewById(R.id.TextViewCartType);
        tvBrand = (TextView) findViewById(R.id.TextViewCartBrand);
        tvTime = (TextView) findViewById(R.id.TextViewCartTime);

        Intent intentH = getIntent();

        int type = intentH.getIntExtra("type",0);
        String brand = intentH.getStringExtra("brand");
        String time = intentH.getStringExtra("time");


        tvType.setText(type+"");
        tvBrand.setText(brand);
        tvTime.setText(time);
        database = new Database(this);



        btnBackH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}