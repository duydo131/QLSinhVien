package com.example.quanlysinhvien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysinhvien.R;
import com.example.quanlysinhvien.model.SinhVien;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {

    List<SinhVien> items;
    boolean visibility = false;

    public SinhVienAdapter(List<SinhVien> items) {
        this.items = items;
    }

    public SinhVienAdapter(List<SinhVien> items, boolean visibility) {
        this.items = items;
        this.visibility = visibility;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        }

        TextView name = view.findViewById(R.id.name);
        TextView mssv = view.findViewById(R.id.mssv);
        TextView email = view.findViewById(R.id.email);
        CheckBox checkBox = view.findViewById(R.id.check);

        SinhVien sv = items.get(i);
        name.setText(sv.getHoten());
        mssv.setText(sv.getMssv());
        email.setText(sv.getEmail());

        checkBox.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
        return view;
    }
}
