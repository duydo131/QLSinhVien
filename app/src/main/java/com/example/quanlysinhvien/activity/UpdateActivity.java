package com.example.quanlysinhvien.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysinhvien.R;

public class UpdateActivity extends AppCompatActivity{

    TextView hoten;
    TextView mssv;
    TextView ngaysinh;
    TextView email;
    TextView diachi;
    Button update;

    Long id = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        hoten = findViewById(R.id.name);
        mssv = findViewById(R.id.mssv);
        ngaysinh = findViewById(R.id.ngaysinh);
        email = findViewById(R.id.email);
        diachi = findViewById(R.id.diachi);
        update = findViewById(R.id.update);

        Intent intent = getIntent();
        id = intent.getLongExtra("ID", 0L);

        hoten.setText(intent.getStringExtra("hoten"));
        mssv.setText(intent.getStringExtra("mssv"));
        ngaysinh.setText(intent.getStringExtra("ngaysinh"));
        email.setText(intent.getStringExtra("email"));
        diachi.setText(intent.getStringExtra("diachi"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyAlertDialog();
            }
        });

    }

    private void showMyAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Cập nhật !!!")
                .setMessage("Bạn có muốn thay đổi ?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent intent = new Intent();
                                intent.putExtra("update", 1);
                                intent.putExtra("ID", id);
                                intent.putExtra("hoten", hoten.getText().toString());
                                intent.putExtra("mssv", mssv.getText().toString());
                                intent.putExtra("ngaysinh", ngaysinh.getText().toString());
                                intent.putExtra("email", email.getText().toString());
                                intent.putExtra("diachi", diachi.getText().toString());

                                setResult(Activity.RESULT_OK, intent);
                                finish();
                            }
                        })
                .setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent();
                        intent.putExtra("update", 0);

                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}