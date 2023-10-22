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
    Button buttonAddWater,buttonBackWater;
    EditText editTextBrandW,editTextTypeW;
    com.example.pwaterapp.database.Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water);

        buttonAddWater = (Button) findViewById(R.id.ButtonAddWater);
        buttonBackWater = (Button) findViewById(R.id.ButtonBackWater);
        editTextBrandW = (EditText) findViewById(R.id.EditTextNameBrand);
        editTextTypeW = (EditText) findViewById(R.id.EditTextTypeWater);

        database = new Database(this);


        buttonAddWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = editTextTypeW.getText().toString().trim();
                String brand = editTextBrandW.getText().toString().trim();
                //not enough data
                if (type.equals("") || brand.equals("")) {
                    Toast.makeText(AddWater.this, "Not enough information! ", Toast.LENGTH_SHORT).show();

                }

                else {
                    // insert infor to Create
                    Water water = CreateWater();
                    //Add in database
                    database.AddWater(water);
                    //add completed, change to WaterActivity
                    Intent intentAW = new Intent(AddWater.this,WaterActivity.class);
                    startActivity(intentAW);

                    Toast.makeText(AddWater.this,"Add Success",Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonBackWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //Create new water
    private Water CreateWater() {

        int type = Integer.parseInt(editTextTypeW.getText().toString().trim());
        String brand = editTextBrandW.getText().toString().trim();

        Water water = new Water(type,brand);
        return water;

    }
}