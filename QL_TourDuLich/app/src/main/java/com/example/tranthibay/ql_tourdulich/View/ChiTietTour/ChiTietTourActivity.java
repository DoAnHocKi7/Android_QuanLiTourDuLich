package com.example.tranthibay.ql_tourdulich.View.ChiTietTour;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tranthibay.ql_tourdulich.Adapter.TourRecyclerViewAdapter;
import com.example.tranthibay.ql_tourdulich.Constants.PHPConnectionConstants;
import com.example.tranthibay.ql_tourdulich.Constants.TourConstants;
import com.example.tranthibay.ql_tourdulich.Model.MuaHang.TourDaChonModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.KhachSanModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.Presenter.ChiTietTour.ChiTietTourLogicPresenter;
import com.example.tranthibay.ql_tourdulich.Presenter.DangNhap.LoginLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;
import com.example.tranthibay.ql_tourdulich.View.DangNhap.LoginActivity;
import com.example.tranthibay.ql_tourdulich.View.GioHang.GioHangActivity;
import com.example.tranthibay.ql_tourdulich.View.MainActivity;
import com.example.tranthibay.ql_tourdulich.View.MuaHang.MuaHangActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChiTietTourActivity extends AppCompatActivity implements ChiTietTourView {
    /*-------------Show Tour---------------*/
    private Toolbar toolbar;
    private TourModel tour;
    private TextView tv_diaDiem;
    private TextView tv_gia;
    private TextView tv_tenTour;
    private TextView tv_khachSan;
    private TextView tv_moTaTour;
    private ImageView img_Tour;
    private TextView tv_GiaKS;
    private TextView tv_diaChi;
    private TextView tv_dChiKS;
    private RecyclerView mRecyclerView;
    /*-------------------------*/


    private EditText edt_setSL;
    private Dialog dialog_setSL;
    private Button btn_datTourNgay;
    private Button btn_themTourVaoGio;
    private Button btn_setSL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_chi_tiet_tour );
        anhXa();
        tour = layThongTinTour();
        hienThiChiTietTour( tour );
        showTourLienQuan();
        datTourNgay( this, tour );
        themTourVaoGioHang();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.chi_tiet_tour_toolbar_menu, menu );
        return super.onCreateOptionsMenu( menu );
    }

    private void anhXa() {
        toolbar = (Toolbar) findViewById( R.id.chi_tiet_tour_toolbar );
        tv_diaDiem = (TextView) findViewById( R.id.chi_tiet_tour_tv_DiaDiem );
        tv_gia = (TextView) findViewById( R.id.chi_tiet_tour_tv_Gia );
        tv_tenTour = (TextView) findViewById( R.id.chi_tiet_tour_tv_TenTour );
        tv_khachSan = (TextView) findViewById( R.id.chi_tiet_tour_tv_KhachSan );
        tv_moTaTour = (TextView) findViewById( R.id.chi_tiet_tour_tv_MoTa );
        img_Tour = (ImageView) findViewById( R.id.chi_tiet_tour_imgTour );
        tv_dChiKS = (TextView) findViewById( R.id.chi_tiet_tour_tv_DiaChiKS );
        tv_GiaKS = (TextView) findViewById( R.id.chi_tiet_tour_tv_GiaKhachSan );
        tv_diaChi = (TextView) findViewById( R.id.chi_tiet_tour_tv_DiaDiem );
        mRecyclerView =(RecyclerView) findViewById( R.id.chi_tiet_tour_gidview_tour_lien_quan );

        btn_datTourNgay = (Button) findViewById( R.id.chi_tiet_tour_btn_DatTour_ngay );
        btn_themTourVaoGio = (Button) findViewById( R.id.chi_tiet_tour_btn_ThemVaoGio );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }

    private TourModel layThongTinTour() {
        Intent intent = getIntent();
        TourModel tour = (TourModel) intent.getSerializableExtra( TourConstants.PASSEDTOUR );
        return tour;
    }

    private void hienThiChiTietTour(TourModel tour) {
        tv_diaDiem.setText( tour.getDiaDiem() );
        tv_gia.setText( String.valueOf( tour.getGia() ) );
        tv_tenTour.setText( tour.getTenTour() );
        tv_khachSan.setText( tour.getKhachSan().getTenKhachSan() );
        tv_moTaTour.setText( tour.getMota() );
        tv_GiaKS.setText( String.valueOf( tour.getKhachSan().getGiaTienKS() ) );
        tv_dChiKS.setText( tour.getKhachSan().getDiaChiKS() );
        tv_diaChi.setText( tour.getDiaDiem() );
        Picasso.get().load( tour.getHinhAnh() ).into( img_Tour );
    }

    public void showTourLienQuan() {
        ChiTietTourLogicPresenter tourLogicPresenter = new ChiTietTourLogicPresenter( this );
        tourLogicPresenter.getTourLienQuan( tour.getLoaiTour(), this, new VolleyCallback() {
            @Override
            public void onSuccess(JSONArray result)  {
                ArrayList<TourModel> tourModels=new ArrayList<>(  );
                TourModel tour = null;
                KhachSanModel khachSan = null;
                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject item = result.getJSONObject( i );
                        String loaiTour = item.getString( "LoaiTour" );
                        String ma = item.getString( "Matour" );
                        String ten = item.getString( "TenTour" );
                        String img = PHPConnectionConstants.HOST + "/web_QLTourDuLich_php/tour_dulich/" + item.getString( "HinhAnh" );
                        Double gia = item.getDouble( "Gia" );
                        String loai = item.getString( "LoaiTour" );
                        String mota = item.getString( "MoTa" );
                        String diaDiem = item.getString( "TenDiaDiem" );
                        String tenks = item.getString( "tenks" );
                        String maKS = item.getString( "MaKS" );
                        String diaChi = item.getString( "diachi" );
                        double tienKS = Double.valueOf( item.getString( "giatien" ) );
                        String loaiKS = item.getString( "MaLoaiKS" );
                        khachSan = new KhachSanModel( tenks, diaChi, tienKS, loaiKS );
                        try {
                            Date ngayDi = new SimpleDateFormat( "yyyy-dd-MM" ).parse( item.getString( "ngaykhoihanh" ) );
                            Date ngayDen = new SimpleDateFormat( "yyyy-dd-MM" ).parse( item.getString( "ngayketthuc" ) );
                            tour = new TourModel( ngayDi, ngayDen, ma, ten, img, gia, loai, mota, diaDiem, khachSan );

//                            TourLienQuanAdapter adapter=new TourLienQuanAdapter(ChiTietTourActivity.this,R.layout.item_row_tour_lien_quan,tourModels);
//                            mRecyclerView.setAdapter( adapter );
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        tourModels.add( tour );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    TourRecyclerViewAdapter mRcvAdapter = new TourRecyclerViewAdapter( tourModels, ChiTietTourActivity.this );
                    GridLayoutManager layoutManager = new GridLayoutManager( ChiTietTourActivity.this, 2 );
                    layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
                    mRecyclerView.setLayoutManager( layoutManager );
                    mRecyclerView.setAdapter( mRcvAdapter );
                }
            }
        } );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
            break;
            case R.id.tool_bar_item_gio_hang: {
                Intent intent = new Intent( this, GioHangActivity.class );
                startActivity( intent );
            }
            break;
            case R.id.tool_bar_item_home: {
                Intent intent = new Intent( this, MainActivity.class );
                startActivity( intent );
            }
            break;
            case  R.id.tool_bar_item_dang_xuat:{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn đăng xuất không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginLogicPresenter.xoaUserDaDangNhap( ChiTietTourActivity.this );
                        Toast.makeText( ChiTietTourActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT ).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }break;
        }
        return true;
    }

    private void themTourVaoGioHang() {

        btn_themTourVaoGio.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_setSL = new Dialog( ChiTietTourActivity.this );
                dialog_setSL.setContentView( R.layout.pop_up_set_so_luong );
                dialog_setSL.show();
                btn_setSL = (Button) dialog_setSL.findViewById( R.id.chi_tiet_tour_pop_up_btn_SetSL );
                edt_setSL = (EditText) dialog_setSL.findViewById( R.id.chi_tiet_tour_pop_up_edt_SetSL );
                btn_setSL.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Date ngayDat = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy/MM/dd" );
                        formatter.format( ngayDat );
                        int soNguoi = Integer.valueOf( edt_setSL.getText().toString() );
                        double tongTien = soNguoi * tour.getGia();
                        ChiTietTourLogicPresenter chiTietTourLogicPresenter = new ChiTietTourLogicPresenter( ChiTietTourActivity.this );
                        chiTietTourLogicPresenter.themTourVaoGio( new TourDaChonModel( soNguoi, ngayDat, tongTien, tour ) );

                    }
                } );
            }
        } );
    }

    private void datTourNgay(Context context, TourModel tourModel) {
        this.btn_datTourNgay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*-------Xac nhan tai khoan. Co muon dang nhap de mua hang---------*/
                if (MainActivity.KhachHangModel == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder( ChiTietTourActivity.this );
                    builder.setTitle( "Xác nhận là thành viên!" );
                    builder.setMessage( "Bạn có muốn đăng nhập?" );
                    builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent( ChiTietTourActivity.this, LoginActivity.class );
                            startActivity( intent );
                        }
                    } );
                    builder.setNegativeButton( "Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent( ChiTietTourActivity.this, MuaHangActivity.class );
                            intent.putExtra( TourConstants.PASSEDTOUR, tourModel );
                            startActivity( intent );
                        }
                    } );
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
                    Intent intent = new Intent( ChiTietTourActivity.this, MuaHangActivity.class );
                    intent.putExtra( TourConstants.PASSEDTOUR, tourModel );
                    startActivity( intent );
                }
                /*--------------------------------------*/
//                Intent intent = new Intent( context, MuaHangActivity.class );
//                intent.putExtra( TourConstants.PASSEDTOUR, tourModel );
//                startActivity( intent );


            }
        } );

    }

    @Override
    public void themGioHangThanhCong() {
        Toast.makeText( this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT ).show();
        Intent intent = new Intent( this, MainActivity.class );
        startActivity( intent );
        Object a = MainActivity.GioHang;
    }
}
