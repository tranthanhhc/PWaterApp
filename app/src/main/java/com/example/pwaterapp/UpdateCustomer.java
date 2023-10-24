package com.example.pwaterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pwaterapp.database.Database;
import com.example.pwaterapp.model.Customer;


public class UpdateCustomer extends AppCompatActivity {
    EditText edtName,edtAddress,edtTypeC,edtBrandC,edtTime;
    ImageButton ibtnAddPhoto;
    ImageView imgvPhoto;
    Button btnUpdate,btnBackC;

    com.example.pwaterapp.database.Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cuss);

        edtName = (EditText) findViewById(R.id.EditTextNameUpdateCuss);
        edtAddress = (EditText) findViewById(R.id.EditTextAddressUpdate);
        edtBrandC = (EditText) findViewById(R.id.EditTextBrandUpdateCuss);
        edtTypeC = (EditText) findViewById(R.id.EditTextTypeUpdateCuss);
        edtTime = (EditText) findViewById(R.id.EditTextTimeUpdateCuss);
        btnUpdate = (Button) findViewById(R.id.ButtonUpdateCuss);
        btnBackC = (Button) findViewById(R.id.ButtonBackUpdateCuss);

        Intent intentU = getIntent();

        int id = intentU.getIntExtra("idcustomer",0);
        String name = intentU.getStringExtra("namecustomer");
        String address = intentU.getStringExtra("address");
        int type = intentU.getIntExtra("type",0);
        String brand = intentU.getStringExtra("brand");
        String time = intentU.getStringExtra("time");

        edtName.setText(name);
        edtAddress.setText(address);
        edtTypeC.setText(type+"");
        edtBrandC.setText(brand);
        edtTime.setText(time);
        database = new Database(this);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String type = edtTypeC.getText().toString().trim();
                String brand = edtBrandC.getText().toString().trim();
                String time = edtTime.getText().toString().trim();


                if (name.equals("") || address.equals("") || brand.equals("") || type.equals("")|| time.equals("") ) {
                    Toast.makeText(UpdateCustomer.this, "Not enough information! ", Toast.LENGTH_SHORT).show();

                }

                else {
                    // update infor to Create
                    Customer cus = UpdateC();
                    //update in database
                    database.UpdateCuss(cus,id);
                    //update completed, change to WaterActivity
                    Intent intentUC = new Intent(UpdateCustomer.this,  CustomersActivity.class);
                    startActivity(intentUC);

                    Toast.makeText(UpdateCustomer.this,"Update Success",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBackC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //save data update

    private Customer UpdateC() {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        int type = Integer.parseInt(edtTypeC.getText().toString().trim());
        String brand = edtBrandC.getText().toString().trim();
        String time = edtTime.getText().toString().trim();

        Customer cus = new Customer(name,address,type,brand,time,null);
        return cus;
    }

}