package com.example.p3l_ajr_0171.responselaporan;

import com.example.p3l_ajr_0171.entitylaporan.Laporan5DriverTransaksi;
import com.example.p3l_ajr_0171.entitylaporan.LaporanPenyewaanMobil;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanPenyewaanMobilResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<LaporanPenyewaanMobil> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanPenyewaanMobil> getDataList() {
        return dataList;
    }

    public void setDataList(List<LaporanPenyewaanMobil> dataList) {
        this.dataList = dataList;
    }
}
