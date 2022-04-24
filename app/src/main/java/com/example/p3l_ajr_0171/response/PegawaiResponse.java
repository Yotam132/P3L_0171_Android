package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Customer;
import com.example.p3l_ajr_0171.entity.Pegawai;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PegawaiResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Pegawai> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Pegawai> getDataList() {
        return dataList;
    }

    public void setDataList(List<Pegawai> dataList) {
        this.dataList = dataList;
    }
}
