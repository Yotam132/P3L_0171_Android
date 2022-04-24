package com.example.p3l_ajr_0171.responselaporan;

import com.example.p3l_ajr_0171.entity.Customer;
import com.example.p3l_ajr_0171.entitylaporan.Laporan5CustomerTransaksi;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Laporan5CustomerTrnsResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Laporan5CustomerTransaksi> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Laporan5CustomerTransaksi> getDataList() {
        return dataList;
    }

    public void setDataList(List<Laporan5CustomerTransaksi> dataList) {
        this.dataList = dataList;
    }
}
