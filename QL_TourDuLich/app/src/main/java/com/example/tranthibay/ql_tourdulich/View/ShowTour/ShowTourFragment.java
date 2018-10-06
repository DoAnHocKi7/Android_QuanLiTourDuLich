package com.example.tranthibay.ql_tourdulich.View.ShowTour;

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
import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.Presenter.ShowTour.TourLogicPresenter;
import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowTourFragment extends Fragment {
    ArrayList<TourModel> data;
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_show_tour, container, false );
        mRecyclerView = (RecyclerView) view.findViewById( R.id.recycle_ShowTour );
        data = new ArrayList<>();

        TourLogicPresenter logicPresenter = new TourLogicPresenter( this.getContext() );
        logicPresenter.getTourDulich( new VolleyCallback() {
            @Override
            public void onSuccess(JSONArray result) {
                TourModel tour = null;
                for (int i = 0; i < result.length(); i++) {
                    try {
                        JSONObject item = result.getJSONObject( i );
                        String ma = item.getString( "Matour" );
                        String ten = item.getString( "TenTour" );
                        String img = PHPConnectionConstants.HOST + "/web_QLTourDuLich_php/tour_dulich/" + item.getString( "HinhAnh" );
                        Double gia = item.getDouble( "Gia" );
                        String loai = item.getString( "LoaiTour" );
                        String mota = item.getString( "MoTa" );
                        tour = new TourModel( ma, ten, img, gia, loai, mota );
                        data.add( tour );

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //sdfghjk
                TourRecyclerViewAdapter mRcvAdapter = new TourRecyclerViewAdapter( data, view.getContext() );
                GridLayoutManager layoutManager = new GridLayoutManager( view.getContext(), 2 );
                layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
                mRecyclerView.setLayoutManager( layoutManager );
                mRecyclerView.setAdapter( mRcvAdapter );
            }
        } );

        return view;
    }
}
