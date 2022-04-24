package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Customer;
import com.example.p3l_ajr_0171.entity.Transaksi;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Transaksi> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Transaksi> getDataList() {
        return dataList;
    }

    public void setDataList(List<Transaksi> dataList) {
        this.dataList = dataList;
    }
}
