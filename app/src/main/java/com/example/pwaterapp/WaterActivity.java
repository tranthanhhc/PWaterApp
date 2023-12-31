package com.example.pwaterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

import java.util.ArrayList;

public class WaterActivity extends AppCompatActivity {

    Toolbar toolbarWater;
    ListView listViewWater;

    ArrayList<Water> ArrayListWater;
    com.example.pwaterapp.database.Database database;
    com.example.pwaterapp.adapter.adapterwater adapterwater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        toolbarWater = (Toolbar) findViewById(R.id.toolbarWater);
        listViewWater = (ListView) findViewById(R.id.listviewWater);


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
        else {
            Intent intentCancel = new Intent(WaterActivity.this, MainActivity.class);
            startActivity(intentCancel);
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteW(final int position) {
        Dialog dialogW = new Dialog(this);
        dialogW.setContentView(R.layout.dialogdeletewater);
        dialogW.setCanceledOnTouchOutside(false);


        Button btnYes = (Button) dialogW.findViewById(R.id.buttonYesDeleteWater);
        Button btnNo = (Button) dialogW.findViewById(R.id.buttonNoDeleteWater);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete -> id
                database.DeleteWater(position);
                Intent intentW = new Intent(WaterActivity.this, WaterActivity.class);
                startActivity(intentW);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogW.cancel();
            }
        });
        dialogW.show();
    }
    public void updateW(final int position) {
        Cursor cursor = database.getDataWater();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if (id == position){
                Intent intentUW = new Intent(WaterActivity.this, UpdateWaterN.class);
                //

                int type = cursor.getInt(1);
                String brand = cursor.getString(2);


                //send database to updatecuss
                intentUW.putExtra("idwater",id);
                intentUW.putExtra("typewater",type);
                intentUW.putExtra("brandwater",brand);
                startActivity(intentUW);
            }
        }
    }

}