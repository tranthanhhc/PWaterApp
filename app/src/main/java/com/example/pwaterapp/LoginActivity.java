package com.example.pwaterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogExit, btnLogIn;
    EditText edtLoginName, edtLoginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);
        btnLogExit = (Button) findViewById(R.id.buttonLogExit);
        btnLogIn = (Button) findViewById(R.id.buttonLogin);
        edtLoginName = (EditText) findViewById(R.id.editTextLoginName);
        edtLoginPass = (EditText) findViewById(R.id.editTextLoginPass);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtLoginName.getText().toString().equals("admin") && edtLoginPass.getText().toString().equals("123")) {
                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công! ",Toast.LENGTH_SHORT).show();
                    Intent intentM = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentM);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại, sai thông tin! ",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Exit program
        btnLogExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogExit();
            }
        });
    }
    // ExitFunction
    private void DialogExit() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogexit);
        //click outsize to cancel
        dialog.setCanceledOnTouchOutside(false);
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //exit
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //not exit
                dialog.cancel();
            }
        });

        dialog.show();
    }
}