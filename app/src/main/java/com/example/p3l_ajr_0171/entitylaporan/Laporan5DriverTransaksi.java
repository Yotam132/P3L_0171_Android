package com.example.p3l_ajr_0171.entitylaporan;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class Laporan5DriverTransaksi extends BaseObservable {

    @SerializedName("idDriverGenerated")
    private String idDriverGenerated;

    @SerializedName("namaDrv")
    private String namaDrv;

    @SerializedName("Jumlah Transaksi")
    private int jumlahTransaksi;

    public Laporan5DriverTransaksi(String idDriverGenerated, String namaDrv, int jumlahTransaksi) {
        this.idDriverGenerated = idDriverGenerated;
        this.namaDrv = namaDrv;
        this.jumlahTransaksi = jumlahTransaksi;
    }

    @Bindable
    public String getIdDriverGenerated() {
        return idDriverGenerated;
    }

    public void setIdDriverGenerated(String idDriverGenerated) {
        this.idDriverGenerated = idDriverGenerated;
    }

    @Bindable
    public String getNamaDrv() {
        return namaDrv;
    }

    public void setNamaDrv(String namaDrv) {
        this.namaDrv = namaDrv;
    }

    @Bindable
    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }
}
