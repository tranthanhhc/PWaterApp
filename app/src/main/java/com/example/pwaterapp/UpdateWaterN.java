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

public class UpdateWaterN extends AppCompatActivity {
    EditText edtBrandWater,edtTypeWater;
    Button btnUpdateW,btnBackW;
    com.example.pwaterapp.database.Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_water);

        edtBrandWater = (EditText) findViewById(R.id.EditTextBrandUpdateWater);
        edtTypeWater = (EditText) findViewById(R.id.EditTextTypeUpdateWater);
        btnBackW = (Button) findViewById(R.id.ButtonBackUpdateWater);
        btnUpdateW = (Button) findViewById(R.id.ButtonUpdateWater);

        Intent intentUW = getIntent();

        int id = intentUW.getIntExtra("idwater",0);
        int type = intentUW.getIntExtra("typewater",0);
        String brand = intentUW.getStringExtra("brandwater");


        edtTypeWater.setText(type+"");
        edtBrandWater.setText(brand);

        database = new Database(this);


        btnUpdateW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String type = edtTypeWater.getText().toString().trim();
                String brand = edtBrandWater.getText().toString().trim();


                if ( brand.equals("") || type.equals("")) {
                    Toast.makeText(UpdateWaterN.this, "Not enough information! ", Toast.LENGTH_SHORT).show();

                }

                else {
                    // update infor to Create
                    Water wat = UpdateW();
                    //update in database
                    database.UpdateWater(wat,id);
                    //update completed, change to WaterActivity
                    Intent intentUW = new Intent(UpdateWaterN.this,  WaterActivity.class);
                    startActivity(intentUW);

                    Toast.makeText(UpdateWaterN.this,"Update Success",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBackW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //save data update

    private Water UpdateW() {
        int type = Integer.parseInt(edtTypeWater.getText().toString().trim());
        String brand = edtBrandWater.getText().toString().trim();

        Water wat = new Water(type, brand);
        return  wat;
    }

}