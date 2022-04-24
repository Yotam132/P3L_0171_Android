package com.example.p3l_ajr_0171.responselaporan;

import com.example.p3l_ajr_0171.entitylaporan.Laporan5CustomerTransaksi;
import com.example.p3l_ajr_0171.entitylaporan.Laporan5DriverTransaksi;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Laporan5DriverTrnsResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Laporan5DriverTransaksi> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Laporan5DriverTransaksi> getDataList() {
        return dataList;
    }

    public void setDataList(List<Laporan5DriverTransaksi> dataList) {
        this.dataList = dataList;
    }
}
