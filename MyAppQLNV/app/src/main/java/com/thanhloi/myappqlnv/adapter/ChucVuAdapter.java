package com.thanhloi.myappqlnv.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhloi.myappqlnv.R;
import com.thanhloi.myappqlnv.dao.ChucVuDao;
import com.thanhloi.myappqlnv.model.ChucVu;

import java.util.ArrayList;

public class ChucVuAdapter extends RecyclerView.Adapter<ChucVuAdapter.ChucVuViewHolder> {
    Context context;
    ArrayList<ChucVu> list;
    ChucVuDao cvDao;

    public ChucVuAdapter(Context context, ArrayList<ChucVu> list){
        this.context=context;
        this.list=list;
        cvDao=new ChucVuDao(context);
    }
    @NonNull
    @Override
    public ChucVuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.chucvu_item,parent,false);
        ChucVuViewHolder viewHolder=new ChucVuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChucVuViewHolder holder, int position) {
        ChucVu cv=list.get(position);// lấy ra 1 item trong list dựa vào position
        holder.tvTenChuc.setText(cv.getTenCV());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(cv);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cvDao.delete(cv.getMaCV())){
                    Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(cvDao.getAll());
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context,"Thất bại.........",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ChucVuViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenChuc;
        ImageView ivEdit,ivDelete;
        CardView cvChuc;
        public ChucVuViewHolder(View view){
            super(view);
            tvTenChuc =view.findViewById(R.id.tv_ChucVu);
            ivEdit =view.findViewById(R.id.ivEditChuc);
            ivDelete =view.findViewById(R.id.ivDeleteChuc);
            cvChuc =view.findViewById(R.id.cardview_Chuc);


        }
    }
    private void openDialogUpdate(ChucVu cv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.chucvu_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText etTen = view.findViewById(R.id.edtTenCVUpdate);
        Spinner spnTT = view.findViewById(R.id.spnTrangThaiUpdate);
        Button btSua = view.findViewById(R.id.btnCapNhatChuc);


        //Tạo dữ liệu cho spinner
        String[] arrTT = {"GiamDoc", "TruongPhong","NhanVien"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, arrTT);
        spnTT.setAdapter(spnAdapter);
        //End

        //Gán giá trị của chức vụ lên view
        etTen.setText(cv.getTenCV());
        for (int i = 0; i < arrTT.length; i++)
            if (cv.getTrangThai().equalsIgnoreCase(arrTT[i])) {
                spnTT.setSelection(i);
            }
        //end
        //Cập nhật dữ liệu
        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cv.setTenCV(etTen.getText().toString());
                cv.setTrangThai((String) spnTT.getSelectedItem());
                if (cvDao.update(cv)) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(cvDao.getAll());
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Thất bại.........", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
