package com.example.quanlysinhvien.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.quanlysinhvien.R;

public class AddActivity extends AppCompatActivity {

    TextView hoten;
    TextView mssv;
    TextView ngaysinh;
    TextView email;
    TextView diachi;
    Button add;

    TextView validHoten;
    TextView validMssv;
    TextView validNgaysinh;
    TextView validEmail;
    TextView validDiachi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        hoten = findViewById(R.id.name);
        mssv = findViewById(R.id.mssv);
        ngaysinh = findViewById(R.id.ngaysinh);
        email = findViewById(R.id.email);
        diachi = findViewById(R.id.diachi);
        add = findViewById(R.id.add);
        validHoten = findViewById(R.id.validName);
        validMssv = findViewById(R.id.validId);
        validNgaysinh = findViewById(R.id.validBirth);
        validEmail = findViewById(R.id.validMail);
        validDiachi = findViewById(R.id.validDiachi);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();

                String birth = ngaysinh.getText().toString();
                String name = hoten.getText().toString();
                String id = mssv.getText().toString();
                String mail = email.getText().toString();
                String address = diachi.getText().toString();

                String valid = "chưa điền thông tin";

                boolean flag = true;
                if(name.equals("")){
                    flag = false;
                    validHoten.setText(valid);
                }
                if(id.equals("")){
                    flag = false;
                    validMssv.setText(valid);
                }
                if(mail.equals("")){
                    flag = false;
                    validEmail.setText(valid);
                }
                if(address.equals("")){
                    flag = false;
                    validDiachi.setText(valid);
                }
                if(ngaysinh.equals("")){
                    flag = false;
                    validNgaysinh.setText(valid);
                }
                if(flag){
                    Intent intent = new Intent();
                    intent.putExtra("hoten", name);
                    intent.putExtra("mssv", id);
                    intent.putExtra("ngaysinh", birth);
                    intent.putExtra("email", mail);
                    intent.putExtra("diachi", address);

                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void clear(){
        validHoten.setText("");
        validDiachi.setText("");
        validEmail.setText("");
        validNgaysinh.setText("");
        validMssv.setText("");
    }
}