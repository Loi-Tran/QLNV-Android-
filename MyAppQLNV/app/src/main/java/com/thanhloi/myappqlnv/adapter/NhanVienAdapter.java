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
import com.thanhloi.myappqlnv.dao.NhanVienDao;
import com.thanhloi.myappqlnv.model.ChucVu;
import com.thanhloi.myappqlnv.model.NhanVien;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder> {
    Context context;
    ArrayList<NhanVien> list;
    NhanVienDao nvDao;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> list){
        this.context=context;
        this.list=list;
        nvDao=new NhanVienDao(context);
    }
    @NonNull
    @Override
    public NhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.nhanvien_item,parent,false);
        NhanVienViewHolder viewHolder=new NhanVienViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienViewHolder holder, int position) {
        ChucVuDao cvDao=new ChucVuDao(context);
        NhanVien nv=list.get(position);// lấy ra 1 item trong list dựa vào position
        holder.tvMaNV.setText("Mã nhân viên: "+nv.getMaNV());
        holder.tvHoTen.setText("Họ tên: "+nv.getTenNV());
        holder.tvLuong.setText("Lương: "+nv.getLuong()+"");
        int img_id=((Activity)context).getResources().getIdentifier(nv.getHinh(),"drawable",((Activity)context).getPackageName());
        holder.ivDaiDien.setImageResource(img_id);//Cần int id
        int maCV= nv.getChucVu();
        ChucVu cv=cvDao.getById(maCV);
        holder.tvChucVu.setText("Chức vụ: "+cv.getTenCV());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUpdate(nv);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nvDao.delete(nv.getMaNV())){
                    Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(nvDao.getAll("NhanVien"));
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

    public class NhanVienViewHolder extends RecyclerView.ViewHolder{
        TextView tvMaNV,tvHoTen,tvLuong,tvChucVu;
        ImageView ivDaiDien,ivDelete,ivEdit;
        CardView cvNhanVien;
        public NhanVienViewHolder(View view){
            super(view);
            tvMaNV =view.findViewById(R.id.tv_MaNV);
            tvHoTen =view.findViewById(R.id.tv_HoTen);
            tvLuong =view.findViewById(R.id.tv_Luong);
            ivDaiDien =view.findViewById(R.id.iv_daidien);
            tvChucVu =view.findViewById(R.id.tv_ChucVu);
            cvNhanVien =view.findViewById(R.id.cardview_NhanVien);
            ivDelete =view.findViewById(R.id.ivDeleteNV);
            ivEdit =view.findViewById(R.id.ivEditNV);
        }
    }
    private void openDialogUpdate(NhanVien nv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.nhanvien_update, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText etTen = view.findViewById(R.id.edtTenNVUpdate);
        EditText etLuong = view.findViewById(R.id.edtLuongNVUpdate);
        EditText etHinh = view.findViewById(R.id.edtHinhNVUpdate);
        Spinner spnCV = view.findViewById(R.id.spnChucVuUpdate);
        Button btSua = view.findViewById(R.id.btnCapNhatNVUpdate);


        //Tạo dữ liệu cho spinner
        String[] arrCV = {"GiamDoc", "TruongPhong","NhanVien"};
        ArrayAdapter<String> spnAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, arrCV);
        spnCV.setAdapter(spnAdapter);
        //End

        //Cập nhật dữ liệu
        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nv.setTenNV( etTen.getText().toString());
                nv.setLuong(etLuong.getText().toString());
                nv.setHinh( etHinh.getText().toString());
                ChucVu cv = (ChucVu) spnCV.getSelectedItem();
                int chucVu = cv.getMaCV();
                if (nvDao.update(nv)) {
                    Toast.makeText(context, "Cập nhật nhân viên thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(nvDao.getAll("GiamDoc"));
                    notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Thất bại.........", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
