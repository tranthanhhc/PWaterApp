package com.example.pwaterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pwaterapp.database.Database;
import com.example.pwaterapp.model.Water;

public class AddWater extends AppCompatActivity {
    Button btnAddWater,btnBackWater;
    EditText edtBrandW,edtTypeW;
    com.example.pwaterapp.database.Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water);

        btnAddWater = (Button) findViewById(R.id.ButtonAddWater);
        btnBackWater = (Button) findViewById(R.id.ButtonBackWater);
        edtBrandW = (EditText) findViewById(R.id.EditTextNameBrand);
        edtTypeW = (EditText) findViewById(R.id.EditTextTypeWater);

        database = new Database(this);


        btnAddWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = edtTypeW.getText().toString().trim();
                String brand = edtBrandW.getText().toString().trim();
                //not enough data
                if (type.equals("") || brand.equals("")) {
                    Toast.makeText(AddWater.this, "Not enough information! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Water water = CreateWater();
                    database.AddWater(water);
                    Intent intentAW = new Intent(AddWater.this,WaterActivity.class);
                    startActivity(intentAW);

                    Toast.makeText(AddWater.this,"Add Success",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBackWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private Water CreateWater() {

        int type = Integer.parseInt(edtTypeW.getText().toString().trim());
        String brand = edtBrandW.getText().toString().trim();

        Water water = new Water(type,brand);
        return water;

    }
}