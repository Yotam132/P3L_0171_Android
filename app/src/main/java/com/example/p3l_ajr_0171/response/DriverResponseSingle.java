package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Driver;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DriverResponseSingle {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Driver data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Driver getData() {
        return data;
    }

    public void setData(Driver data) {
        this.data = data;
    }
}
