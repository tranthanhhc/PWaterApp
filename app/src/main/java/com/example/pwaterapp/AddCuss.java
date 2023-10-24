package com.example.pwaterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pwaterapp.database.Database;
import com.example.pwaterapp.model.Customer;
import com.example.pwaterapp.model.Water;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AddCuss extends AppCompatActivity {
    Button btnAddCuss,btnBackCuss;
    EditText edtName, edtAddress,edtBrand,edtType;
    com.example.pwaterapp.database.Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cuss);

        btnAddCuss = (Button) findViewById(R.id.ButtonAddCuss);
        btnBackCuss = (Button) findViewById(R.id.ButtonBackCuss);
        edtName = (EditText) findViewById(R.id.EditTextNameCuss);
        edtAddress = (EditText) findViewById(R.id.EditTextAddress);
        edtBrand = (EditText) findViewById(R.id.EditTextBrandCuss);
        edtType = (EditText) findViewById(R.id.EditTextTypeCuss);
        // tạo mới 1 đối tượng khách hàng trong lớp này
        database = new Database(this);
        // sự kiện thêm 1 khách hàng
        btnAddCuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String type = edtType.getText().toString().trim();
                String brand = edtBrand.getText().toString().trim();

                // kiểm tra xem thông tin đã đủ chưa
                if (name.equals("") || address.equals("") || brand.equals("") || type.equals("")) {
                    Toast.makeText(AddCuss.this, "Not enough information! ", Toast.LENGTH_SHORT).show();

                }

                else {
                    // tạo mới 1 khách hàng và đưa thông tin vào
                    Customer cus = CreateCus();
                    //Lưu vào database
                    database.AddCuss(cus);
                    //sau khi lưu thì trả về customerAcitivy
                    Intent intentAC = new Intent(AddCuss.this,  CustomersActivity.class);
                    startActivity(intentAC);
                    Toast.makeText(AddCuss.this,"Add Success",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //  quay lại nếu ko thêm
        btnBackCuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    // Hàm tạo mới 1 đối tượng khách hàng
    private Customer CreateCus() {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        int type = Integer.parseInt(edtType.getText().toString().trim());
        String brand = edtBrand.getText().toString().trim();

        Customer cus = new Customer(name,address,type,brand,null,null);
        return cus;

    }

}