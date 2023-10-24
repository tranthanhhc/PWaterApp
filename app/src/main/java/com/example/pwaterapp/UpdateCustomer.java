
package com.example.pwaterapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pwaterapp.database.Database;
import com.example.pwaterapp.model.Customer;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateCustomer extends AppCompatActivity {
    EditText edtName,edtAddress,edtTypeC,edtBrandC,edtTime;
    ImageButton ibtnAddPhoto;
    ImageView imgvPhoto;
    Button btnUpdate,btnBackC;

    int PICK_IMAGE_REQUEST = 123;

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
        ibtnAddPhoto = (ImageButton) findViewById(R.id.ImageButtonAddPhoto);
        imgvPhoto = (ImageView) findViewById(R.id.ImageViewPhoto);

        Intent intentU = getIntent();

        int id = intentU.getIntExtra("idcustomer",0);
        String name = intentU.getStringExtra("namecustomer");
        String address = intentU.getStringExtra("address");
        int type = intentU.getIntExtra("type",0);
        String brand = intentU.getStringExtra("brand");
        String time = intentU.getStringExtra("time");

        byte[] imageBytes = intentU.getByteArrayExtra("image");

        edtName.setText(name);
        edtAddress.setText(address);
        edtTypeC.setText(type+"");
        edtBrandC.setText(brand);
        edtTime.setText(time);

        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imgvPhoto.setImageBitmap(bitmap);
        }


        database = new Database(this);
        // chọn ảnh từ ảnh  tải về or thư viện ảnh
        ibtnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        // xử lý sự kiện click nút update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String type = edtTypeC.getText().toString().trim();
                String brand = edtBrandC.getText().toString().trim();
                String time = edtTime.getText().toString().trim();
                // chuyen doi du lieu imageview-> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgvPhoto.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                byte[] imageBytes = byteArray.toByteArray();

                // kiểm tra xem nhập thiếu ko
                if (name.equals("") || address.equals("") || brand.equals("") || type.equals("")|| time.equals("") || imageBytes == null) {
                    Toast.makeText(UpdateCustomer.this, "Not enough information! ", Toast.LENGTH_SHORT).show();

                }

                else {
                    // lấy thông tin khách hàng
                    Customer cus = UpdateC();
                    //cập nhập vào cs dữ liệu
                    database.UpdateCuss(cus,id);
                    // hiển thị
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
    // cập nhập thông tin khách hàng
    private Customer UpdateC() {
        String name = edtName.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        int type = Integer.parseInt(edtTypeC.getText().toString().trim());
        String brand = edtBrandC.getText().toString().trim();
        String time = edtTime.getText().toString().trim();
        //chuyển dữ liệu imageview -> byte[]
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgvPhoto.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
        byte[] imageBytes = byteArray.toByteArray();
        // tạo mới 1 đối tượng mang tt chỉnh sửa
        Customer cus = new Customer(name,address,type,brand,time,imageBytes);
        return cus;
    }
    // chọn hình ảnh thì sẽ trả về data và hiển thị trên imageview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            // Tiếp tục xử lý với đường dẫn ảnh đã chọn
            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                database.saveImageToDatabase(imageBytes);

                imgvPhoto.setImageURI(selectedImageUri);
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}