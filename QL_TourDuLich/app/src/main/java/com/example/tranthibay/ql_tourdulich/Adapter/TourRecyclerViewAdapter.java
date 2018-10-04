package com.example.tranthibay.ql_tourdulich.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tranthibay.ql_tourdulich.Model.ShowTour.TourModel;
import com.example.tranthibay.ql_tourdulich.R;

import java.util.ArrayList;
import java.util.List;

public class TourRecyclerViewAdapter extends RecyclerView.Adapter<TourRecyclerViewAdapter.RecyclerViewHolder> {

    private List<TourModel> data = new ArrayList<>();

    public TourRecyclerViewAdapter(List<TourModel> data) {
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View view = inflater.inflate( R.layout.item_row_tour, parent, false );
        return new RecyclerViewHolder( view );
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.tv_TenTour.setText( data.get( position ).getTenTour() );
        holder.tv_GiaTour.setText( String.valueOf( data.get( position ).getGia() ) );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_TenTour;
        TextView tv_GiaTour;

        public RecyclerViewHolder(View itemView) {
            super( itemView );
            tv_TenTour = (TextView) itemView.findViewById( R.id.tv_TenTour );
            tv_GiaTour = (TextView) itemView.findViewById( R.id.frag_ShowTour_tv_GiaTour );
        }
    }
}
