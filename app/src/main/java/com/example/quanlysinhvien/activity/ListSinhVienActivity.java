package com.example.quanlysinhvien.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Predicate;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.quanlysinhvien.R;
import com.example.quanlysinhvien.adapter.SinhVienAdapter;
import com.example.quanlysinhvien.model.SinhVien;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListSinhVienActivity extends AppCompatActivity {

    SQLiteDatabase db;
    List<SinhVien> items;
    ListView listView;
    SinhVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_sinh_vien);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách sinh viên");
        actionBar.setSubtitle("K63");

        actionBar.setDisplayShowCustomEnabled(true);

        String dataPath = getFilesDir() + "/mydata";
        db = SQLiteDatabase.openDatabase(dataPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        items = new ArrayList<>();
        getData();
        listView = findViewById(R.id.list_students);
        adapter = new SinhVienAdapter(items);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setLongClickable(true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinhVien sv = items.get(i);
                Intent intent = new Intent(ListSinhVienActivity.this, DetailActivity.class);
                intent.putExtra("hoten", sv.getHoten());
                intent.putExtra("mssv", sv.getMssv());
                intent.putExtra("ngaysinh", sv.getNgaysinh());
                intent.putExtra("email", sv.getEmail());
                intent.putExtra("diachi", sv.getDiachi());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                List<SinhVien> filter = new ArrayList<>();
                for(SinhVien sv : items){
                    String ten = sv.getHoten().toLowerCase();
                    String str = s.toLowerCase();
                    if(ten.contains(str)) filter.add(sv);
                }
                if(filter.isEmpty()){
                    for(SinhVien sv : items){
                        String ten = sv.getMssv().toLowerCase();
                        String str = s.toLowerCase();
                        if(ten.contains(str)) filter.add(sv);
                    }
                }
                SinhVienAdapter adapter = new SinhVienAdapter(filter);
                listView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.v("TAG", "onQueryTextChange: " + s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_add){
            Intent intent = new Intent(ListSinhVienActivity.this, AddActivity.class);
            startActivityForResult(intent, 123);
        }else if(id == R.id.action_all){
            SinhVienAdapter adapter = new SinhVienAdapter(items);
            listView.setAdapter(adapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                String hoten = data.getStringExtra("hoten");
                String mssv = data.getStringExtra("mssv");
                String ngaysinh = data.getStringExtra("ngaysinh");
                String email = data.getStringExtra("email");
                String diachi = data.getStringExtra("diachi");

                long r = 0L;

                String dataPath = getFilesDir() + "/mydata";
                db = SQLiteDatabase.openDatabase(dataPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                db.beginTransaction();
                try {
                    ContentValues cv = new ContentValues();
                    cv.put("hoten", hoten);
                    cv.put("mssv", mssv);
                    cv.put("ngaysinh", ngaysinh);
                    cv.put("email", email);
                    cv.put("diachi", diachi);
                    r = db.insert("SinhVien", null, cv);

                    db.setTransactionSuccessful();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    db.endTransaction();
                }


                SinhVien sv = new SinhVien();
                sv.setHoten(hoten);
                sv.setMssv(mssv);
                sv.setNgaysinh(ngaysinh);
                sv.setEmail(email);
                sv.setDiachi(diachi);
                sv.setID(r);

                items.add(sv);

                adapter.notifyDataSetChanged();
            }
        }else if(requestCode == 111){
            if (resultCode == RESULT_OK) {
                int update = data.getIntExtra("update", 0);
                if(update == 1){
                    long id = data.getLongExtra("ID", 0L);
                    String hoten = data.getStringExtra("hoten");
                    String mssv = data.getStringExtra("mssv");
                    String ngaysinh = data.getStringExtra("ngaysinh");
                    String email = data.getStringExtra("email");
                    String diachi = data.getStringExtra("diachi");

                    String dataPath = getFilesDir() + "/mydata";
                    db = SQLiteDatabase.openDatabase(dataPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
                    db.beginTransaction();
                    try {
                        ContentValues cv = new ContentValues();
                        cv.put("hoten", hoten);
                        cv.put("mssv", mssv);
                        cv.put("ngaysinh", ngaysinh);
                        cv.put("email", email);
                        cv.put("diachi", diachi);

                        int r = db.update("SinhVien", cv, "ID = " + id, null);

                        db.setTransactionSuccessful();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        db.endTransaction();
                    }

                    for(SinhVien sv : items){
                        if(sv.getID() == id){
                            sv.setHoten(hoten);
                            sv.setMssv(mssv);
                            sv.setNgaysinh(ngaysinh);
                            sv.setEmail(email);
                            sv.setDiachi(diachi);
                            break;
                        }
                    }

                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Cập nhật");
        menu.add(0, 2, 0, "Xóa");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        SinhVien sv = items.get(info.position);

        String dataPath = getFilesDir() + "/mydata";
        db = SQLiteDatabase.openDatabase(dataPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        int id = item.getItemId();
        if (id == 1) {
            Intent intent = new Intent(ListSinhVienActivity.this, UpdateActivity.class);
            intent.putExtra("ID", sv.getID());
            intent.putExtra("hoten", sv.getHoten());
            intent.putExtra("mssv", sv.getMssv());
            intent.putExtra("ngaysinh", sv.getNgaysinh());
            intent.putExtra("email", sv.getEmail());
            intent.putExtra("diachi", sv.getDiachi());
            startActivityForResult(intent, 111);

        } else if (id == 2) {
            db.beginTransaction();
            try {
                long recId = sv.getID();
                int r = db.delete("SinhVien", "ID = " + recId, null);
                db.setTransactionSuccessful();

                items = new ArrayList<>();
                getData();

                adapter.notifyDataSetChanged();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                db.endTransaction();
            }
        }
        return super.onContextItemSelected(item);
    }

    private void getData() {
        String[] columns = {"ID", "mssv", "hoten", "ngaysinh", "email", "diachi"};
        Cursor cs = db.query("SinhVien", columns,
                null, null, null, null, null);

        cs.moveToFirst();

        do {
            SinhVien sv = new SinhVien();
            sv.setID(cs.getInt(cs.getColumnIndex("ID")));
            sv.setHoten(cs.getString(cs.getColumnIndex("hoten")));
            sv.setMssv(cs.getString(cs.getColumnIndex("mssv")));
            sv.setNgaysinh(cs.getString(cs.getColumnIndex("ngaysinh")));
            sv.setEmail(cs.getString(cs.getColumnIndex("email")));
            sv.setDiachi(cs.getString(cs.getColumnIndex("diachi")));
            items.add(sv);
        } while (cs.moveToNext());

    }

    @Override
    protected void onStop() {
        db.close();
        super.onStop();
    }
}