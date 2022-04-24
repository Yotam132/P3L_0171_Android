package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Customer;
import com.example.p3l_ajr_0171.entity.Promo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Promo> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Promo> getDataList() {
        return dataList;
    }

    public void setDataList(List<Promo> dataList) {
        this.dataList = dataList;
    }
}
