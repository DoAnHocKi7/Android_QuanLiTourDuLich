package com.example.tranthibay.ql_tourdulich.View.ShowTour;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class ShowTourFragment extends Fragment {
    ArrayList<TourModel> dataTrongNuoc;
    ArrayList<TourModel> dataNgoaiNuoc;
    RecyclerView mRecyclerViewTrongNuoc;
    RecyclerView mRecyclerViewNgoaiNuoc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_show_tour, container, false );
        dataTrongNuoc = new ArrayList<>();
        anhXa( view );
        loadTours( view.getContext() );

        return view;
    }

    private void loadTours(Context context) {
        TourLogicPresenter logicPresenter = new TourLogicPresenter( this.getContext() );
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
                        if (loaiTour.equals( "1" )) {
                            dataTrongNuoc.add( tour );
                        } else {
                            dataNgoaiNuoc.add( tour );
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                TourRecyclerViewAdapter mRcvAdapter = new TourRecyclerViewAdapter( dataTrongNuoc, context );
                GridLayoutManager layoutManager = new GridLayoutManager( context, 2 );
                layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
                mRecyclerViewTrongNuoc.setLayoutManager( layoutManager );
                mRecyclerViewTrongNuoc.setAdapter( mRcvAdapter );

//                TourRecyclerViewAdapter mRcvAdapter1 = new TourRecyclerViewAdapter( dataNgoaiNuoc, context );
//                GridLayoutManager layoutManager1 = new GridLayoutManager( context, 2 );
//                layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
//                mRecyclerViewNgoaiNuoc.setLayoutManager( layoutManager1 );
//                mRecyclerViewNgoaiNuoc.setAdapter( mRcvAdapter1 );
            }
        } );
    }

    private void anhXa(View view) {
        mRecyclerViewTrongNuoc = (RecyclerView) view.findViewById( R.id.show_tour_fragment_recycler_tour_trong_nuoc );
        mRecyclerViewNgoaiNuoc = (RecyclerView) view.findViewById( R.id.show_tour_fragment_recycler_tour_ngoai_nuoc );
    }
}
