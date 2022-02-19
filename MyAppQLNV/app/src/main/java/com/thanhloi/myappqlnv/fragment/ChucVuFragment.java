package com.thanhloi.myappqlnv.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.thanhloi.myappqlnv.R;
import com.thanhloi.myappqlnv.adapter.ChucVuAdapter;
import com.thanhloi.myappqlnv.dao.ChucVuDao;
import com.thanhloi.myappqlnv.model.ChucVu;

import java.util.ArrayList;

public class ChucVuFragment extends Fragment {
    RecyclerView rvChuc;
    ChucVuDao cvDao;
    ArrayList<ChucVu> list=new ArrayList<>();
    ChucVuAdapter adapter;
    FloatingActionButton fabAdd;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_chuc_vu,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fabAdd=view.findViewById(R.id.fabAddChuc);
        rvChuc=view.findViewById(R.id.rcvChucVu);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvChuc.setLayoutManager(layoutManager);

        cvDao=new ChucVuDao(getContext());
        list=cvDao.getAll();

        adapter=new ChucVuAdapter(getContext(),list);
        rvChuc.setAdapter(adapter);

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
        View view=inflater.inflate(R.layout.chucvu_add,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();

        EditText etTen=view.findViewById(R.id.edtTenCV);
        Spinner spnTT=view.findViewById(R.id.spnTrangThai);
        Button btThem=view.findViewById(R.id.btnThemChuc);


        //Tạo dữ liệu cho spinner
        String[] arrTT={"GiamDoc", "TruongPhong","NhanVien"};
        ArrayAdapter<String> spnAdapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,arrTT);
        spnTT.setAdapter(spnAdapter);
        //End

        //Thêm dữ liệu
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten=etTen.getText().toString();
                String tt=(String) spnTT.getSelectedItem();
                if (cvDao.insert(ten,tt)){
                    Toast.makeText(getContext(),"Thêm mới thành công",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(cvDao.getAll());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(),"Thất bại.........",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
