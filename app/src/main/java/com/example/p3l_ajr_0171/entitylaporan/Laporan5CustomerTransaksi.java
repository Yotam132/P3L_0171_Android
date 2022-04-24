package com.example.p3l_ajr_0171.entitylaporan;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class Laporan5CustomerTransaksi extends BaseObservable {
    @SerializedName("namaCust")
    private String namaCust;

    @SerializedName("Jumlah Transaksi")
    private int jumlahTransaksi;

    public Laporan5CustomerTransaksi(String namaCust, int jumlahTransaksi) {
        this.namaCust = namaCust;
        this.jumlahTransaksi = jumlahTransaksi;
    }

    @Bindable
    public String getNamaCust() {
        return namaCust;
    }

    public void setNamaCust(String namaCust) {
        this.namaCust = namaCust;
    }

    @Bindable
    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }
}
