package com.example.tranthibay.ql_tourdulich.View;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tranthibay.ql_tourdulich.Constants.DangNhapConstants;
import com.example.tranthibay.ql_tourdulich.Constants.PHPConnectionConstants;
import com.example.tranthibay.ql_tourdulich.Constants.TourConstants;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.KhachHangModel;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.KhachSanModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.Presenter.DangNhap.LoginLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.Services.TaiKhoanServices;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;
import com.example.tranthibay.ql_tourdulich.View.DangNhap.LoginActivity;
import com.example.tranthibay.ql_tourdulich.View.Fragment2.Fragment2;
import com.example.tranthibay.ql_tourdulich.View.ShowTour.ShowTourFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<TourDaChonModel> GioHang;
    public static String Username;
    public static KhachHangModel KhachHangModel;

    private Fragment selectedFragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        caiDatBottomNavgation();
        khoiTaoGioHang();
        khoiTaoThongTinKhachHang();

    }

    private void caiDatBottomNavgation() {
        this.bottomNavigationView = (BottomNavigationView) findViewById( R.id.bottomNav );
        this.selectedFragment = new ShowTourFragment();
        this.bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item1: {
                        selectedFragment = new ShowTourFragment();
                    }
                    break;
                    case R.id.item2: {
                        selectedFragment = new Fragment2();
                    }
                    break;
                    case R.id.item3: {
                        selectedFragment = new com.example.tranthibay.ql_tourdulich.View.Fragment3.ShowTourFragment();
                    }
                    break;
                }
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace( R.id.root_layout, selectedFragment );
                fragmentTransaction.commit();
                return true;
            }
        } );
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        this.fragmentTransaction.replace( R.id.root_layout, selectedFragment );
        this.fragmentTransaction.commit();
    }

    private void khoiTaoGioHang() {
        if (MainActivity.GioHang == null) {
            MainActivity.GioHang = new ArrayList<>();
        }
    }

    private void khoiTaoThongTinKhachHang() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( this );
        Username = sharedPreferences.getString( DangNhapConstants.Username, null );
        TaiKhoanServices taiKhoanServices=new TaiKhoanServices( this );
        taiKhoanServices.layThongTinTaiKhoan( Username,this, new VolleyCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject item = result.getJSONObject( i );
                        String maKh = item.getString( "Ma_KH" );
                        String tenKH = item.getString( "TenKH" );
                        String sdtKH = item.getString( "Phone" );
                        String emailKH = item.getString( "Email" );
                        String diaChiKH = item.getString( "DiaChi" );
                        String gTinhKH = item.getString( "SexKH" );

                        KhachHangModel = new KhachHangModel( TourConstants.LOAIKHMACDINH, tenKH, gTinhKH,emailKH,sdtKH,diaChiKH );
                        KhachHangModel.setMaKH( maKh );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } );
    }
}
