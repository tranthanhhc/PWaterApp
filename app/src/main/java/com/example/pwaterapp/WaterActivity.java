package com.example.pwaterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.pwaterapp.adapter.adapterwater;
import com.example.pwaterapp.database.Database;
import com.example.pwaterapp.model.Water;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WaterActivity extends AppCompatActivity {
    Button btnWaterBack;
    Toolbar toolbarWater;
    ListView listViewWater;

    ArrayList<Water> ArrayListWater;
    com.example.pwaterapp.database.Database database;
    com.example.pwaterapp.adapter.adapterwater adapterwater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        btnWaterBack = (Button) findViewById(R.id.buttonWaterBack);
        toolbarWater = (Toolbar) findViewById(R.id.toolbarWater);
        listViewWater = (ListView) findViewById(R.id.listviewWater);

        //event back to mainAc
        btnWaterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setSupportActionBar(toolbarWater);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //event get and add new data
        database = new Database(this);

        ArrayListWater = new ArrayList<>();
        Cursor cursor = database.getDataWater();

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int type = cursor.getInt(1);
            String brand = cursor.getString(2);

            ArrayListWater.add(new Water(id,type,brand));

        }

        adapterwater = new adapterwater(WaterActivity.this, ArrayListWater);
        listViewWater.setAdapter(adapterwater);
        cursor.moveToFirst();
        cursor.close();
    }

    //add new menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuaddW) {
            Intent intent4 = new Intent(WaterActivity.this, AddWater.class);
            startActivity(intent4);
        }
        return super.onOptionsItemSelected(item);
    }
}