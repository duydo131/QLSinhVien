package com.example.quanlysinhvien.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quanlysinhvien.R;

public class DetailActivity extends AppCompatActivity {

    TextView hoten;
    TextView mssv;
    TextView ngaysinh;
    TextView email;
    TextView diachi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        hoten = findViewById(R.id.name);
        mssv = findViewById(R.id.mssv);
        ngaysinh = findViewById(R.id.ngaysinh);
        email = findViewById(R.id.email);
        diachi = findViewById(R.id.diachi);

        Intent intent = getIntent();
        hoten.setText(intent.getStringExtra("hoten"));
        mssv.setText(intent.getStringExtra("mssv"));
        ngaysinh.setText(intent.getStringExtra("ngaysinh"));
        email.setText(intent.getStringExtra("email"));
        diachi.setText(intent.getStringExtra("diachi"));
    }
}