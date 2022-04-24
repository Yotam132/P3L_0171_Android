package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Customer;
import com.example.p3l_ajr_0171.entity.Driver;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DriverResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Driver> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Driver> getDataList() {
        return dataList;
    }

    public void setDataList(List<Driver> dataList) {
        this.dataList = dataList;
    }
}
