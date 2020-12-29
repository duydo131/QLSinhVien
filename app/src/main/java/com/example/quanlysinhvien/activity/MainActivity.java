package com.example.quanlysinhvien.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysinhvien.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textview);

        String dataPath = getFilesDir() + "/mydata";
        db = SQLiteDatabase.openDatabase(dataPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
                    db.execSQL("create table SinhVien(" +
                            "ID integer primary key autoincrement," +
                            "mssv text," +
                            "hoten text," +
                            "ngaysinh text," +
                            "email text," +
                            "diachi text);");

                    db.execSQL("insert into SinhVien(mssv, hoten, ngaysinh, email, diachi) " +
                            "values ('20180000', 'Đỗ Thế Duy', '11/1/2000', 'dotheduy@gmail.com', 'Thái Bình')");
                    db.execSQL("insert into SinhVien(mssv, hoten, ngaysinh, email, diachi) " +
                            "values ('20180001', 'Lê Hoàng Quân', '17/2/2000', 'lehoangquan@gmail.com', 'Hà Nội')");
                    db.execSQL("insert into SinhVien(mssv, hoten, ngaysinh, email, diachi) " +
                            "values ('20180002', 'Nguyễn Xuân Sang', '20/9/2000', 'nguỹenuansang@gmail.com', 'Hà Nội')");
                    db.execSQL("insert into SinhVien(mssv, hoten, ngaysinh, email, diachi) " +
                            "values ('20180003', 'Phạm Thị Thanh Thúy', '20/4/2000', 'phamthithanhthuy@gmail.com', 'Nam Định')");

                    Log.v("TAG", "Done");
                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
                    ContentValues cv = new ContentValues();
                    cv.put("hoten", "Phạm Tuấn Anh");
                    cv.put("mssv", "20180004");
                    cv.put("ngaysinh", "22/12/2000");
                    cv.put("email", "phamtuananh@gmail.com");
                    cv.put("diachi", "Hải Phòng");
                    long r = db.insert("SinhVien", null, cv);

                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
                    int r = db.delete("SinhVien", "mssv = '20180000'", null);

                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.beginTransaction();
                try {
                    ContentValues cv = new ContentValues();
                    cv.put("hoten", "Duy");

                    int r = db.update("SinhVien", cv, "mssv = '20180000'", null);

                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });

        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Simple query
                String[] columns = {"ID", "mssv", "hoten", "ngaysinh", "email", "diachi"};
                Cursor cs = db.query("SinhVien", columns,
                        null, null, null, null, null);

                cs.moveToFirst();

                StringBuilder str = new StringBuilder();
                do {
                    int recID = cs.getInt(0);
                    String name = cs.getString(1);
                    String phone = cs.getString(cs.getColumnIndex("email"));

                    str.append(recID + " - " + name + " - " + phone + "\n");
                } while (cs.moveToNext());

                textView.setText(str.toString());
            }
        });
    }

    @Override
    protected void onStop() {
        db.close();
        super.onStop();
    }
}
