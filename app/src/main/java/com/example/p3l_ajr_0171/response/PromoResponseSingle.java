package com.example.p3l_ajr_0171.response;

import com.example.p3l_ajr_0171.entity.Promo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromoResponseSingle {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Promo data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Promo getData() {
        return data;
    }

    public void setData(Promo data) {
        this.data = data;
    }
}
