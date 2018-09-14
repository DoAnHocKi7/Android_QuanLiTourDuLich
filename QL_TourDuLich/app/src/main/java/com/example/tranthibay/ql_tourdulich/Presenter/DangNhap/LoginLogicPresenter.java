package com.example.tranthibay.ql_tourdulich.Presenter.DangNhap;

import com.example.tranthibay.ql_tourdulich.View.DangNhap.LoginView;

public class LoginLogicPresenter implements LoginImlementPresenter {
    LoginView loginView;

    public LoginLogicPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public Boolean xuLiDangNhap(String userName, String passWord) {
        if(userName=="123"&&passWord=="123"){
            this.loginView.dangNhapThanhCong();
            return true;
        }
        this.loginView.dangNhapThatBai();
        return false;
    }
}
