package com.example.p3l_ajr_0171.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer extends BaseObservable {
    @SerializedName("idCustomer")
    private int idCustomer;

    @SerializedName("namaCust")
    private String namaCust;

    @SerializedName("idCustomerGenerated")
    private String idCustomerGenerated;

    @SerializedName("alamatCust")
    private String alamatCust;

    @SerializedName("tglLahirCust")
    private Date tglLahirCust;

    @SerializedName("jenisKelaminCust")
    private String jenisKelaminCust;

    @SerializedName("emailCust")
    private String emailCust;

    @SerializedName("noTelpCust")
    private String noTelpCust;

    @SerializedName("kartuPengenalCust")
    private String kartuPengenalCust;

    @SerializedName("simCust")
    private String simCust;

    @SerializedName("passwordCust")
    private String passwordCust;

    @SerializedName("statusDokumenCust")
    private int statusDokumenCust;

    public Customer(int idCustomer, String namaCust, String idCustomerGenerated, String alamatCust, Date tglLahirCust, String jenisKelaminCust, String emailCust, String noTelpCust, String kartuPengenalCust, String simCust, String passwordCust, int statusDokumenCust) {
        this.namaCust = namaCust;
        this.idCustomer = idCustomer;
        this.idCustomerGenerated = idCustomerGenerated;
        this.alamatCust = alamatCust;
        this.tglLahirCust = tglLahirCust;
        this.jenisKelaminCust = jenisKelaminCust;
        this.emailCust = emailCust;
        this.noTelpCust = noTelpCust;
        this.kartuPengenalCust = kartuPengenalCust;
        this.simCust = simCust;
        this.passwordCust = passwordCust;
        this.statusDokumenCust = statusDokumenCust;
    }

    @Bindable
    public String getNamaCust() {
        return namaCust;
    }

    public void setNamaCust(String namaCust) {
        this.namaCust = namaCust;
        notifyPropertyChanged(BR.namaCust);
    }

    @Bindable
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Bindable
    public String getIdCustomerGenerated() {
        return idCustomerGenerated;
    }

    public void setIdCustomerGenerated(String idCustomerGenerated) {
        this.idCustomerGenerated = idCustomerGenerated;
        notifyPropertyChanged(BR.idCustomerGenerated);
    }

    @Bindable
    public String getAlamatCust() {
        return alamatCust;
    }

    public void setAlamatCust(String alamatCust) {
        this.alamatCust = alamatCust;
        notifyPropertyChanged(BR.alamatCust);
    }

    @Bindable
    public Date getTglLahirCust() {
        return tglLahirCust;
    }

    @Bindable
    public String getTglLahirCustStr() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yy");
        return simpleDate.format(tglLahirCust);
    }

    public void setTglLahirCust(Date tglLahirCust) {
        this.tglLahirCust = tglLahirCust;
    }

    public void setTglLahirCustStr(String tglLahirCust) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reformattedStr = "";
        try {
            reformattedStr = myFormat.format(tglLahirCust);
            this.tglLahirCust = myFormat.parse(reformattedStr);
            notifyPropertyChanged(BR.tglLahirCust);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bindable
    public String getJenisKelaminCust() {
        return jenisKelaminCust;
    }

    public void setJenisKelaminCust(String jenisKelaminCust) {
        this.jenisKelaminCust = jenisKelaminCust;
        notifyPropertyChanged(BR.jenisKelaminCust);
    }

    @Bindable
    public String getEmailCust() {
        return emailCust;
    }

    public void setEmailCust(String emailCust) {
        this.emailCust = emailCust;
        notifyPropertyChanged(BR.emailCust);
    }

    @Bindable
    public String getNoTelpCust() {
        return noTelpCust;
    }

    public void setNoTelpCust(String noTelpCust) {
        this.noTelpCust = noTelpCust;
        notifyPropertyChanged(BR.noTelpCust);
    }

    @Bindable
    public String getKartuPengenalCust() {
        return kartuPengenalCust;
    }

    public void setKartuPengenalCust(String kartuPengenalCust) {
        this.kartuPengenalCust = kartuPengenalCust;
    }

    @Bindable
    public String getSimCust() {
        return simCust;
    }

    public void setSimCust(String simCust) {
        this.simCust = simCust;
    }

    @Bindable
    public String getPasswordCust() {
        return passwordCust;
    }

    public void setPasswordCust(String passwordCust) {
        this.passwordCust = passwordCust;
        notifyPropertyChanged(BR.passwordCust);
    }

    @Bindable
    public int getStatusDokumenCust() {
        return statusDokumenCust;
    }

    public void setStatusDokumenCust(int statusDokumenCust) {
        this.statusDokumenCust = statusDokumenCust;
    }
}
