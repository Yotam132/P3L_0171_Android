package com.example.p3l_ajr_0171.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.p3l_ajr_0171.BR;
import com.google.gson.annotations.SerializedName;

public class LoginData extends BaseObservable {
    // class untuk login data yg dikirim ke back end

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("no")
    private String no;

    public LoginData(String email, String password, String no) {
        this.email = email;
        this.password = password;
        this.no = no;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
        notifyPropertyChanged(BR.no);
    }
}
