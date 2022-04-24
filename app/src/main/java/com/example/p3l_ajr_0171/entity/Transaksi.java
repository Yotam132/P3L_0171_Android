package com.example.p3l_ajr_0171.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.p3l_ajr_0171.BR;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Transaksi extends BaseObservable {
    @SerializedName("idTransaksi")
    private int idTransaksi;

    @SerializedName("idMobil")
    private int idMobil;

    @SerializedName("idDriver")
    private int idDriver;

    @SerializedName("idCustomer")
    private int idCustomer;

    @SerializedName("tglSewaAwal")
    private Date tglSewaAwal;

    @SerializedName("tglSewaAkhir")
    private Date tglSewaAkhir;

    @SerializedName("metodePembayaran")
    private String metodePembayaran;

    @SerializedName("subTotal")
    private float subTotal;

    @SerializedName("idPegawai")
    private int idPegawai;

    @SerializedName("idPromo")
    private int idPromo;

    @SerializedName("idTransaksiGenerated")
    private String idTransaksiGenerated;

    @SerializedName("tglPengembalian")
    private Date tglPengembalian;

    @SerializedName("statusTransaksi")
    private String statusTransaksi;

    @SerializedName("totalHargaAkhir")
    private float totalHargaAkhir;

    @SerializedName("ratingDriver")
    private float ratingDriver;

    @SerializedName("ratingPerusahaan")
    private float ratingPerusahaan;

    public Transaksi(int idTransaksi, int idMobil, int idDriver, int idCustomer, Date tglSewaAwal, Date tglSewaAkhir, String metodePembayaran, float subTotal, int idPegawai, int idPromo, String idTransaksiGenerated, Date tglPengembalian, String statusTransaksi, float totalHargaAkhir, float ratingDriver, float ratingPerusahaan) {
        this.idTransaksi = idTransaksi;
        this.idMobil = idMobil;
        this.idDriver = idDriver;
        this.idCustomer = idCustomer;
        this.tglSewaAwal = tglSewaAwal;
        this.tglSewaAkhir = tglSewaAkhir;
        this.metodePembayaran = metodePembayaran;
        this.subTotal = subTotal;
        this.idPegawai = idPegawai;
        this.idPromo = idPromo;
        this.idTransaksiGenerated = idTransaksiGenerated;
        this.tglPengembalian = tglPengembalian;
        this.statusTransaksi = statusTransaksi;
        this.totalHargaAkhir = totalHargaAkhir;
        this.ratingDriver = ratingDriver;
        this.ratingPerusahaan = ratingPerusahaan;
    }

    @Bindable
    public int getIdTransaksi() {
        return idTransaksi;
    }

    @Bindable
    public int getIdMobil() {
        return idMobil;
    }

    @Bindable
    public int getIdDriver() {
        return idDriver;
    }

    @Bindable
    public int getIdCustomer() {
        return idCustomer;
    }

    @Bindable
    public Date getTglSewaAwal() {
        return tglSewaAwal;
    }

    @Bindable
    public String getTglSewaAwalStr() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yy");
        return simpleDate.format(tglSewaAwal);
    }

    @Bindable
    public Date getTglSewaAkhir() {
        return tglSewaAkhir;
    }

    @Bindable
    public String getTglSewaAkhirStr() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yy");
        return simpleDate.format(tglSewaAkhir);
    }

    @Bindable
    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    @Bindable
    public float getSubTotal() {
        return subTotal;
    }

    @Bindable
    public String getSubTotalStr() {
        return String.valueOf(subTotal);
    }

    @Bindable
    public int getIdPegawai() {
        return idPegawai;
    }

    @Bindable
    public int getIdPromo() {
        return idPromo;
    }

    @Bindable
    public String getIdTransaksiGenerated() {
        return idTransaksiGenerated;
    }

    @Bindable
    public Date getTglPengembalian() {
        return tglPengembalian;
    }

    @Bindable
    public String getTglPengembalianStr() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yy");
        return simpleDate.format(tglPengembalian);
    }

    @Bindable
    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    @Bindable
    public float getTotalHargaAkhir() {
        return totalHargaAkhir;
    }

    @Bindable
    public float getRatingDriver() {
        return ratingDriver;
    }

    @Bindable
    public float getRatingPerusahaan() {
        return ratingPerusahaan;
    }

    @Bindable
    public String getTotalHargaAkhirStr() {
        return String.valueOf(totalHargaAkhir);
    }

    @Bindable
    public String getRatingDriverStr() {
        return String.valueOf(ratingDriver);
    }

    @Bindable
    public String getRatingPerusahaanStr() {
        return String.valueOf(ratingPerusahaan);
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public void setIdMobil(int idMobil) {
        this.idMobil = idMobil;
        notifyPropertyChanged(BR.idMobil);
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
        notifyPropertyChanged(BR.idDriver);
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setTglSewaAwal(Date tglSewaAwal) {
        this.tglSewaAwal = tglSewaAwal;
    }

    public void setTglSewaAwalStr(String tglSewaAwal) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reformattedStr = "";
        try {
            reformattedStr = myFormat.format(tglSewaAwal);
            this.tglSewaAwal = myFormat.parse(reformattedStr);
            notifyPropertyChanged(BR.tglSewaAwal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTglSewaAkhir(Date tglSewaAkhir) {
        this.tglSewaAkhir = tglSewaAkhir;
    }

    public void setTglSewaAkhirStr(String tglSewaAkhir) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reformattedStr = "";
        try {
            reformattedStr = myFormat.format(tglSewaAkhir);
            this.tglSewaAkhir = myFormat.parse(reformattedStr);
            notifyPropertyChanged(BR.tglSewaAkhir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
        notifyPropertyChanged(BR.metodePembayaran);
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public void setSubTotalStr(String subTotal) {
        this.subTotal = Float.parseFloat(subTotal);
        notifyPropertyChanged(BR.subTotal);
    }

    public void setIdPegawai(int idPegawai) {
        this.idPegawai = idPegawai;
        notifyPropertyChanged(BR.idPegawai);
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
        notifyPropertyChanged(BR.idPromo);
    }

    public void setIdTransaksiGenerated(String idTransaksiGenerated) {
        this.idTransaksiGenerated = idTransaksiGenerated;
        notifyPropertyChanged(BR.idTransaksiGenerated);
    }

    public void setTglPengembalian(Date tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }

    public void setTglPengembalianStr(String tglPengembalian) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reformattedStr = "";
        try {
            reformattedStr = myFormat.format(tglPengembalian);
            this.tglPengembalian = myFormat.parse(reformattedStr);
            notifyPropertyChanged(BR.tglPengembalian);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
        notifyPropertyChanged(BR.statusTransaksi);
    }

    public void setTotalHargaAkhir(float totalHargaAkhir) {
        this.totalHargaAkhir = totalHargaAkhir;
    }

    public void setRatingDriver(float ratingDriver) {
        this.ratingDriver = ratingDriver;
    }

    public void setRatingPerusahaan(float ratingPerusahaan) {
        this.ratingPerusahaan = ratingPerusahaan;
    }

    public void setTotalHargaAkhirStr(String totalHargaAkhir) {
        this.totalHargaAkhir = Float.parseFloat(totalHargaAkhir);
        notifyPropertyChanged(BR.totalHargaAkhir);
    }

    public void setRatingDriverStr(String ratingDriver) {
        this.ratingDriver = Float.parseFloat(ratingDriver);
        notifyPropertyChanged(BR.ratingDriver);
    }

    public void setRatingPerusahaanStr(String ratingPerusahaan) {
        this.ratingPerusahaan = Float.parseFloat(ratingPerusahaan);
//        notifyPropertyChanged(BR.ratingPerusahaan);
    }
}
