package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Mobil;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MobilResponseSingle {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Mobil data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Mobil getData() {
        return data;
    }

    public void setData(Mobil data) {
        this.data = data;
    }
}
