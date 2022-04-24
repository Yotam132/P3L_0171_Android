package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Customer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Customer> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Customer> getDataList() {
        return dataList;
    }

    public void setDataList(List<Customer> dataList) {
        this.dataList = dataList;
    }
}
