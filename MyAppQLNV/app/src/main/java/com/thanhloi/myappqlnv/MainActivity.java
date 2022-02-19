package com.thanhloi.myappqlnv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView taikhoan=(TextView) findViewById(R.id.edt_username);
        TextView matkhau=(TextView) findViewById(R.id.edt_password);

        Button dangnhap=(Button) findViewById(R.id.btn_dangnhap);

        //admin and admin
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taikhoan.getText().toString().equals("admin")&&matkhau.getText().toString().equals("admin")){
                    //correct
                    openhome();
                }else
                    //incorrect
                    Toast.makeText(MainActivity.this,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void openhome(){
        Intent i=new Intent(this, Home.class);
        startActivity(i);
    }
}