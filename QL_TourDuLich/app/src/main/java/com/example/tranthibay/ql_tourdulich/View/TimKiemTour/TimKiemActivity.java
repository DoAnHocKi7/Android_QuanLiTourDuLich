package com.example.tranthibay.ql_tourdulich.View.TimKiemTour;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.tranthibay.ql_tourdulich.Adapter.TourRecyclerViewAdapter;
import com.example.tranthibay.ql_tourdulich.Constants.PHPConnectionConstants;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.KhachSanModel;
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.Presenter.ShowTour.TourLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {

    private ArrayList<TourModel> tourLst=new ArrayList<>(  );
    private ArrayList<String> diaDiemLst=new ArrayList<>(  );
    private ArrayAdapter<String> adapterDiaDiem;
    RecyclerView mRecyclerView;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tim_kiem );
        anhXa();
        loadTours( this );
        chonDiaDiemDaTim();
    }

    private void loadTimKiem() {
        //ArrayList<String> arrayCountry = new ArrayList<>();
        //arrayCountry.addAll( Arrays.asList( getResources().getStringArray( R.array.array_country ) ) );
        getSupportActionBar().show();
        adapterDiaDiem = new ArrayAdapter<>(
                TimKiemActivity.this,
                android.R.layout.simple_list_item_1,
                diaDiemLst );
        lv.setAdapter( adapterDiaDiem );
    }

    private void  loadMangTimKiemDiaDiem(){
        for (TourModel tour: tourLst) {
            diaDiemLst.add( tour.getDiaDiem() );
        }
    }

    private void anhXa() {
        mRecyclerView = (RecyclerView) findViewById( R.id.tim_kiem_recycleview_tours );
        lv = (ListView) findViewById( R.id.listViewCountry );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        lv.setVisibility( View.VISIBLE );

        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_search, menu );
        MenuItem item = menu.findItem( R.id.menuSearch );
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadTimKiem();
                adapterDiaDiem.getFilter().filter( newText );
                return false;
            }
        } );
        return super.onCreateOptionsMenu( menu );
    }



    private void loadTours(Context context) {
        TourLogicPresenter logicPresenter = new TourLogicPresenter( this );
        logicPresenter.getTourDulich( new VolleyCallback() {
            @Override
            public void onSuccess(JSONArray result) {
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
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        tourLst.add( tour );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                loadMangTimKiemDiaDiem();
                TourRecyclerViewAdapter mRcvAdapter = new TourRecyclerViewAdapter( tourLst, context );
                GridLayoutManager layoutManager = new GridLayoutManager( context, 2 );
                layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
                mRecyclerView.setLayoutManager( layoutManager );
                mRecyclerView.setAdapter( mRcvAdapter );


            }
        } );
    }

    private List<TourModel> timKiemTour(String diaDiem){
        ArrayList<TourModel> kketQua=new ArrayList<>(  );
        for (TourModel tour: tourLst) {
            if(tour.getDiaDiem().equals( diaDiem )){
                kketQua.add( tour );
            }
        }
        return kketQua;
    }

    private void chonDiaDiemDaTim(){
        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TourRecyclerViewAdapter mRcvAdapter
                        = new TourRecyclerViewAdapter( timKiemTour( adapterDiaDiem.getItem( position ) ), TimKiemActivity.this );
                mRecyclerView.setAdapter( mRcvAdapter );
                lv.setAdapter(null);
            }
        } );
    }
}
