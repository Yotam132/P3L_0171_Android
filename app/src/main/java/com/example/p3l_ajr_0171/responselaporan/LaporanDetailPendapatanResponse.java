package com.example.p3l_ajr_0171.responselaporan;

import com.example.p3l_ajr_0171.entitylaporan.Laporan5DriverTransaksi;
import com.example.p3l_ajr_0171.entitylaporan.LaporanDetailPendapatan;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanDetailPendapatanResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<LaporanDetailPendapatan> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanDetailPendapatan> getDataList() {
        return dataList;
    }

    public void setDataList(List<LaporanDetailPendapatan> dataList) {
        this.dataList = dataList;
    }
}
