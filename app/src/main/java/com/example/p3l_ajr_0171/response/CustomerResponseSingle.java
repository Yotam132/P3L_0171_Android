package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Customer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerResponseSingle {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Customer data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getData() {
        return data;
    }

    public void setData(Customer data) {
        this.data = data;
    }
}
