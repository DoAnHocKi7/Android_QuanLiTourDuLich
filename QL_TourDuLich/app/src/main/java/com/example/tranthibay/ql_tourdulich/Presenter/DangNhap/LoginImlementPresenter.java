package com.example.tranthibay.ql_tourdulich.Presenter.DangNhap;

import android.content.Context;

import com.example.tranthibay.ql_tourdulich.Services.VolleyCallback;

public interface LoginImlementPresenter {
    void xuLiDangNhap(String userName, String passWord, Context context);

    void ghiNhoDangNhap(Context context,String userName);

    String layUserDaDangNhap(Context context);

    void xoaUserDaDangNhap(Context context);

}
