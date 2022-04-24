package com.example.p3l_ajr_0171.entitylaporan;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class LaporanDetailPendapatan extends BaseObservable {

    @SerializedName("namaCust")
    private String namaCust;

    @SerializedName("namaMbl")
    private String namaMbl;

    @SerializedName("Jenis Transaksi")
    private String jenisTransaksi;

    @SerializedName("Jumlah Transaksi")
    private int jumlahTransaksi;

    @SerializedName("Pendapatan")
    private float pendapatan;

    public LaporanDetailPendapatan(String namaCust, String namaMbl, String jenisTransaksi, int jumlahTransaksi, float pendapatan) {
        this.namaCust = namaCust;
        this.namaMbl = namaMbl;
        this.jenisTransaksi = jenisTransaksi;
        this.jumlahTransaksi = jumlahTransaksi;
        this.pendapatan = pendapatan;
    }

    @Bindable
    public String getNamaCust() {
        return namaCust;
    }

    public void setNamaCust(String namaCust) {
        this.namaCust = namaCust;
    }

    @Bindable
    public String getNamaMbl() {
        return namaMbl;
    }

    public void setNamaMbl(String namaMbl) {
        this.namaMbl = namaMbl;
    }

    @Bindable
    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    @Bindable
    public int getJumlahTransaksi() {
        return jumlahTransaksi;
    }

    public void setJumlahTransaksi(int jumlahTransaksi) {
        this.jumlahTransaksi = jumlahTransaksi;
    }

    @Bindable
    public float getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(float pendapatan) {
        this.pendapatan = pendapatan;
    }
}
