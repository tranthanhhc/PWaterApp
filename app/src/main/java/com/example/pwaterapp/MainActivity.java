package com.example.pwaterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCus, btnWater,btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCus = (Button) findViewById(R.id.buttonCustomers);
        btnWater = (Button) findViewById(R.id.buttonWater);
        btnExit = (Button) findViewById(R.id.buttonExit);
        //event btn click
        btnCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CustomersActivity.class);
                startActivity(intent);

            }
        });
        //event Water click
        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,WaterActivity.class);
                startActivity(intent2);
            }
        });
        //event Exit click
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent3);
            }
        });
    }

}