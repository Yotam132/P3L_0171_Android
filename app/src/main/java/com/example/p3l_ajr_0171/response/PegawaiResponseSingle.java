package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Pegawai;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PegawaiResponseSingle {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Pegawai data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pegawai getData() {
        return data;
    }

    public void setData(Pegawai data) {
        this.data = data;
    }
}
