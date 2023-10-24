package com.example.pwaterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pwaterapp.database.Database;

public class HistoryCuss extends AppCompatActivity {
    TextView tvType,tvBrand,tvTime;
    Button btnBackH;

    ImageView imgvHistoryPhoto;

    com.example.pwaterapp.database.Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_cuss);

        btnBackH = (Button) findViewById(R.id.ButtonCartBack);
        tvType = (TextView) findViewById(R.id.TextViewCartType);
        tvBrand = (TextView) findViewById(R.id.TextViewCartBrand);
        tvTime = (TextView) findViewById(R.id.TextViewCartTime);
        imgvHistoryPhoto = (ImageView) findViewById(R.id.ImageViewHistoryPhoto);

        // lấy dữ liệu từ đối tượng intent đang đc truyền vào
        Intent intentH = getIntent();

        int type = intentH.getIntExtra("type",0);
        String brand = intentH.getStringExtra("brand");
        String time = intentH.getStringExtra("time");
        byte[] imageBytes = intentH.getByteArrayExtra("image");


        tvType.setText(type+"");
        tvBrand.setText(brand);
        tvTime.setText(time);

        if (imageBytes != null) {
            //hiển thị ảnh ( từ byte[] - > Bitmap )
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imgvHistoryPhoto.setImageBitmap(bitmap);
        }
        database = new Database(this);


        // nút bacsk trở lại
        btnBackH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}