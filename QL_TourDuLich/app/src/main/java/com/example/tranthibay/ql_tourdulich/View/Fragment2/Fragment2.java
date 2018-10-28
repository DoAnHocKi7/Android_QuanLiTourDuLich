package com.example.tranthibay.ql_tourdulich.View.Fragment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tranthibay.ql_tourdulich.R;
import com.example.tranthibay.ql_tourdulich.View.GioHang.GioHangActivity;

public class Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intent intent=new Intent( getActivity(), GioHangActivity.class );
        startActivity( intent );
        return inflater.inflate( R.layout.activity_gio_hang,container,false);

    }
}
