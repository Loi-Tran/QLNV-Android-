package com.thanhloi.myappqlnv.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.thanhloi.myappqlnv.R;
import com.thanhloi.myappqlnv.adapter.NhanVienAdapter;
import com.thanhloi.myappqlnv.dao.ChucVuDao;
import com.thanhloi.myappqlnv.dao.NhanVienDao;
import com.thanhloi.myappqlnv.model.ChucVu;
import com.thanhloi.myappqlnv.model.NhanVien;

import java.util.ArrayList;

public class GiamdocFragment extends Fragment {
    RecyclerView rvNhanVien;
    NhanVienAdapter adapter;
    ArrayList<NhanVien> list=new ArrayList<>();
    NhanVienDao nvDao;
    FloatingActionButton fabAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_giamdoc,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvNhanVien=view.findViewById(R.id.rcvGiamDoc);
        fabAdd=view.findViewById(R.id.fabAddGiamDoc);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvNhanVien.setLayoutManager(layoutManager);
        nvDao=new NhanVienDao(getContext());
        list=nvDao.getAll("GiamDoc");
        adapter=new NhanVienAdapter(getContext(),list);
        rvNhanVien.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogAdd();
            }
        });
    }
    private void openDialogAdd(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater=this.getLayoutInflater();
        View view=inflater.inflate(R.layout.nhanvien_add,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();

        TextView tvTitle=view.findViewById(R.id.tvNVTitle);
        EditText etTenNV=view.findViewById(R.id.edtTenNV);
        EditText etLuong=view.findViewById(R.id.edtLuongNV);
        EditText etHinh=view.findViewById(R.id.edtHinhNV);
        Spinner spnLoai=view.findViewById(R.id.spnLoaiNV);
        Button btThem=view.findViewById(R.id.btnThemNV);

        //Spinner
        ChucVuDao cvDao=new ChucVuDao(getContext());
        ArrayList<ChucVu> listCv=cvDao.getAll("GiamDoc");
        ArrayAdapter adapterLoai=new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_dropdown_item,listCv);
        spnLoai.setAdapter(adapterLoai);
        //End

        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ten = etTenNV.getText().toString().trim();
                    String luong = etLuong.getText().toString().trim();
                    String hinh = etHinh.getText().toString().trim();
                    ChucVu cv = (ChucVu) spnLoai.getSelectedItem();
                    int chucVu = cv.getMaCV();
                    NhanVien nv = new NhanVien(ten, luong, hinh, chucVu);
                    if (nvDao.insert(nv)) {
                        Toast.makeText(getContext(), "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        list.addAll(nvDao.getAll("GiamDoc"));
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "Thất bại.........", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
