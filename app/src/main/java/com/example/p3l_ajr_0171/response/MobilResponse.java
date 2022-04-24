package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Customer;
import com.example.p3l_ajr_0171.entity.Mobil;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MobilResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Mobil> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Mobil> getDataList() {
        return dataList;
    }

    public void setDataList(List<Mobil> dataList) {
        this.dataList = dataList;
    }
}
