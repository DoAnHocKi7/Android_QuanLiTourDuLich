package com.example.tranthibay.ql_tourdulich.Model.DangNhap;

public class LoginModel {
    String UserName;
    String PassWord;

    public LoginModel(String userName, String passWord) {
        UserName = userName;
        PassWord = passWord;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassWord() {
        return PassWord;
    }
}
