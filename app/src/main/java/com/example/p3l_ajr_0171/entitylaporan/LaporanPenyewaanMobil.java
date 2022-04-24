package com.example.p3l_ajr_0171.entitylaporan;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;


public class LaporanPenyewaanMobil extends BaseObservable {
    @SerializedName("tipeMbl")
    private String tipeMobil;

    @SerializedName("namaMbl")
    private String namaMobil;

    @SerializedName("Jumlah Peminjaman")
    private int jumlahPeminjaman;

    @SerializedName("Total Pendapatan")
    private float pendapatan;

    public LaporanPenyewaanMobil(String tipeMobil, String namaMobil, int jumlahPeminjaman, float pendapatan) {
        this.tipeMobil = tipeMobil;
        this.namaMobil = namaMobil;
        this.jumlahPeminjaman = jumlahPeminjaman;
        this.pendapatan = pendapatan;
    }

    @Bindable
    public String getTipeMobil() {
        return tipeMobil;
    }

    public void setTipeMobil(String tipeMobil) {
        this.tipeMobil = tipeMobil;
    }

    @Bindable
    public String getNamaMobil() {
        return namaMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    @Bindable
    public int getJumlahPeminjaman() {
        return jumlahPeminjaman;
    }

    public void setJumlahPeminjaman(int jumlahPeminjaman) {
        this.jumlahPeminjaman = jumlahPeminjaman;
    }

    @Bindable
    public float getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(float pendapatan) {
        this.pendapatan = pendapatan;
    }
}
