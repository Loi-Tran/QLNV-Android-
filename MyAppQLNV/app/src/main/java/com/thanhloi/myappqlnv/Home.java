package com.thanhloi.myappqlnv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.thanhloi.myappqlnv.dao.ChucVuDao;
import com.thanhloi.myappqlnv.fragment.ChucVuFragment;
import com.thanhloi.myappqlnv.fragment.GiamdocFragment;
import com.thanhloi.myappqlnv.fragment.NhanvienFragment;
import com.thanhloi.myappqlnv.fragment.TruongphongFragment;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //private static final int FRAGMENT_CHUCVU=1;
   // private static final int FRAGMENT_NHANVIEN=2;

   // private int currentFragment=FRAGMENT_CHUCVU;
    ChucVuDao chucVuDao;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        chucVuDao=new ChucVuDao(Home.this);

        //add toolbar
        Toolbar toolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        NavigationView navView=findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState==null){
            ChucVuFragment fragment=new ChucVuFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer,fragment)
                    .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
        Fragment fragment=new Fragment();
        switch (itemId){
            case R.id.nav_chucvu:
                fragment=new ChucVuFragment();
                break;
            case R.id.nav_giamdoc:
                fragment=new GiamdocFragment();
                break;
            case R.id.nav_truongphong:
                fragment=new TruongphongFragment();
                break;
            case R.id.nav_nhanvien:
                fragment=new NhanvienFragment();
                break;
            case R.id.nav_thoat:
                finish();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer,fragment)
                .commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}