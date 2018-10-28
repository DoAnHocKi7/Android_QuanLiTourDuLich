package com.example.tranthibay.ql_tourdulich.View.GioHang;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Adapter.TourDaDatReCyclerAdapter;
import com.example.tranthibay.ql_tourdulich.Presenter.GioHang.GioHangLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.View.DangNhap.LoginActivity;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;
import com.example.tranthibay.ql_tourdulich.View.ThanhToan.ThanhToanActivity;

public class GioHangActivity extends AppCompatActivity {
    Toolbar toolbar;
    private RecyclerView recyclerView_gioHang;
    private TextView tv_tongTien;

    private Button btn_thanhToanLuon;
    private Button btn_tiepTucMuaHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gio_hang );
        anhXa();
        loadGioHang();
        thanhToan();
        tiepTucMuahang();
    }

    private void loadGioHang() {
        TourDaDatReCyclerAdapter mRcvAdapter = new TourDaDatReCyclerAdapter( MainActivity.GioHang, this );
        GridLayoutManager layoutManager = new GridLayoutManager( this, 1 );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView_gioHang.setLayoutManager( layoutManager );
        recyclerView_gioHang.setAdapter( mRcvAdapter );
        tv_tongTien.setText( String.valueOf( new GioHangLogicPresenter().tinhTongTien( MainActivity.GioHang ) ) );
    }

    private void thanhToan() {
        btn_thanhToanLuon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.KhachHangModel == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder( GioHangActivity.this );
                    builder.setTitle( "Xác nhận là thành viên!" );
                    builder.setMessage( "Bạn có muốn đăng nhập?" );
                    builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent( GioHangActivity.this, LoginActivity.class );
                            startActivity( intent );
                        }
                    } );
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent( GioHangActivity.this, ThanhToanActivity.class );
                            startActivity( intent );
                        }
                    });
//                    builder.setNegativeButton( "No", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent( GioHangActivity.this, ThanhToanActivity.class );
//                            startActivity( intent );
//                        }
//                    } );
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    Intent intent = new Intent( GioHangActivity.this, ThanhToanActivity.class );
                    startActivity( intent );
                }
            }
        } );
    }

    private void tiepTucMuahang() {
        btn_tiepTucMuaHang.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( GioHangActivity.this, MainActivity.class );
                startActivity( intent );
            }
        } );
    }

    private void anhXa() {
        toolbar = (Toolbar) findViewById( R.id.gio_hang_toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        recyclerView_gioHang = (RecyclerView) findViewById( R.id.gio_hang_recycle_view_gio_hang );
        tv_tongTien = (TextView) findViewById( R.id.gio_hang_TongTien );
        btn_thanhToanLuon = (Button) findViewById( R.id.gio_hang_btn_ThanhToanLuon );
        btn_tiepTucMuaHang = (Button) findViewById( R.id.gio_hang_btn_TiepTucMuaHang );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.chi_tiet_menu_gio_hang, menu );
        return super.onCreateOptionsMenu( menu );
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
            break;
            case R.id.gio_hang_tool_bar_item_home: {
                Intent intent = new Intent( this, GioHangActivity.class );
                startActivity( intent );
            }
            break;

        }
        return true;
    }

}
