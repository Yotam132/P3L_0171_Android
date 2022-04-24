package com.example.p3l_ajr_0171.responselaporan;

import com.example.p3l_ajr_0171.entitylaporan.LaporanPenyewaanMobil;
import com.example.p3l_ajr_0171.entitylaporan.LaporanPerformaDriver;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanPerformaDriverResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<LaporanPerformaDriver> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LaporanPerformaDriver> getDataList() {
        return dataList;
    }

    public void setDataList(List<LaporanPerformaDriver> dataList) {
        this.dataList = dataList;
    }
}
