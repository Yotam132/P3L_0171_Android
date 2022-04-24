package com.example.p3l_ajr_0171.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Driver  extends BaseObservable {
    @SerializedName("idDriver")
    private int idDriver;

    @SerializedName("namaDrv")
    private String namaDrv;

    @SerializedName("idDriverGenerated")
    private String idDriverGenerated;

    @SerializedName("alamatDrv")
    private String alamatDrv;

    @SerializedName("tglLahirDrv")
    private Date tglLahirDrv;

    @SerializedName("jenisKelaminDrv")
    private String jenisKelaminDrv;

    @SerializedName("emailDrv")
    private String emailDrv;

    @SerializedName("noTelpDrv")
    private String noTelpDrv;

    @SerializedName("urlFotoDrv")
    private String urlFotoDrv;

    @SerializedName("simDrv")
    private String simDrv;

    @SerializedName("passwordDrv")
    private String passwordDrv;

    @SerializedName("statusDokumenDrv")
    private int statusDokumenDrv;

    @SerializedName("bahasaAsing")
    private int bahasaAsing;

    @SerializedName("statusDrv")
    private int statusDrv;

    @SerializedName("tarifDrv")
    private float tarifDrv;

    @SerializedName("suratBebasNapzaDrv")
    private String suratBebasNapzaDrv;

    @SerializedName("suratKesehatanJiwaDrv")
    private String suratKesehatanJiwaDrv;

    @SerializedName("suratKesehatanJasmaniDrv")
    private String suratKesehatanJasmaniDrv;

    @SerializedName("skckDrv")
    private String skckDrv;

    @SerializedName("rerataRatingDrv")
    private float rerataRatingDrv;

    public Driver(int idDriver, String namaDrv, String idDriverGenerated, String alamatDrv, Date tglLahirDrv, String jenisKelaminDrv, String emailDrv, String noTelpDrv, String urlFotoDrv, String simDrv, String passwordDrv, int statusDokumenDrv, int bahasaAsing, int statusDrv, float tarifDrv, String suratBebasNapzaDrv, String suratKesehatanJiwaDrv, String suratKesehatanJasmaniDrv, String skckDrv, float rerataRatingDrv) {
        this.idDriver = idDriver;
        this.namaDrv = namaDrv;
        this.idDriverGenerated = idDriverGenerated;
        this.alamatDrv = alamatDrv;
        this.tglLahirDrv = tglLahirDrv;
        this.jenisKelaminDrv = jenisKelaminDrv;
        this.emailDrv = emailDrv;
        this.noTelpDrv = noTelpDrv;
        this.urlFotoDrv = urlFotoDrv;
        this.simDrv = simDrv;
        this.passwordDrv = passwordDrv;
        this.statusDokumenDrv = statusDokumenDrv;
        this.bahasaAsing = bahasaAsing;
        this.statusDrv = statusDrv;
        this.tarifDrv = tarifDrv;
        this.suratBebasNapzaDrv = suratBebasNapzaDrv;
        this.suratKesehatanJiwaDrv = suratKesehatanJiwaDrv;
        this.suratKesehatanJasmaniDrv = suratKesehatanJasmaniDrv;
        this.skckDrv = skckDrv;
        this.rerataRatingDrv = rerataRatingDrv;
    }

    @Bindable
    public String getNamaDrv() {
        return namaDrv;
    }

    public void setNamaDrv(String namaDrv) {
        this.namaDrv = namaDrv;
    }

    @Bindable
    public int getIdDriver() {
        return idDriver;
    }

    @Bindable
    public String getIdDriverGenerated() {
        return idDriverGenerated;
    }

    @Bindable
    public String getAlamatDrv() {
        return alamatDrv;
    }

    @Bindable
    public Date getTglLahirDrv() {
        return tglLahirDrv;
    }

    @Bindable
    public String getTglLahirDrvStr() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yy");
        return simpleDate.format(tglLahirDrv);
    }

    @Bindable
    public String getJenisKelaminDrv() {
        return jenisKelaminDrv;
    }

    @Bindable
    public String getEmailDrv() {
        return emailDrv;
    }

    @Bindable
    public String getNoTelpDrv() {
        return noTelpDrv;
    }

    @Bindable
    public String getUrlFotoDrv() {
        return urlFotoDrv;
    }

    @Bindable
    public String getSimDrv() {
        return simDrv;
    }

    @Bindable
    public String getPasswordDrv() {
        return passwordDrv;
    }

    @Bindable
    public int getStatusDokumenDrv() {
        return statusDokumenDrv;
    }

    @Bindable
    public int getBahasaAsing() {
        return bahasaAsing;
    }

    @Bindable
    public int getStatusDrv() {
        return statusDrv;
    }

    @Bindable
    public float getTarifDrv() {
        return tarifDrv;
    }

    @Bindable
    public String getTarifDrvStr() {
        return String.valueOf(tarifDrv);
    }

    @Bindable
    public String getSuratBebasNapzaDrv() {
        return suratBebasNapzaDrv;
    }

    @Bindable
    public String getSuratKesehatanJiwaDrv() {
        return suratKesehatanJiwaDrv;
    }

    @Bindable
    public String getSuratKesehatanJasmaniDrv() {
        return suratKesehatanJasmaniDrv;
    }

    @Bindable
    public String getSkckDrv() {
        return skckDrv;
    }

    @Bindable
    public float getRerataRatingDrv() {
        return rerataRatingDrv;
    }

    @Bindable
    public String getRerataRatingDrvStr() {
        return String.valueOf(rerataRatingDrv);
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
        notifyPropertyChanged(BR.idDriver);
    }

    public void setIdDriverGenerated(String idDriverGenerated) {
        this.idDriverGenerated = idDriverGenerated;
        notifyPropertyChanged(BR.idDriverGenerated);
    }

    public void setAlamatDrv(String alamatDrv) {
        this.alamatDrv = alamatDrv;
        notifyPropertyChanged(BR.alamatDrv);
    }

    public void setTglLahirDrv(Date tglLahirDrv) {
        this.tglLahirDrv = tglLahirDrv;
        notifyPropertyChanged(BR.tglLahirDrv);
    }

    public void setTglLahirDrvStr(String tglLahirDrv) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reformattedStr = "";
        try {
            reformattedStr = myFormat.format(tglLahirDrv);
            this.tglLahirDrv = myFormat.parse(reformattedStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        notifyPropertyChanged(BR.tglLahirDrv);
    }

    public void setJenisKelaminDrv(String jenisKelaminDrv) {
        this.jenisKelaminDrv = jenisKelaminDrv;
        notifyPropertyChanged(BR.jenisKelaminDrv);
    }

    public void setEmailDrv(String emailDrv) {
        this.emailDrv = emailDrv;
        notifyPropertyChanged(BR.emailDrv);
    }

    public void setNoTelpDrv(String noTelpDrv) {
        this.noTelpDrv = noTelpDrv;
        notifyPropertyChanged(BR.noTelpDrv);
    }

    public void setUrlFotoDrv(String urlFotoDrv) {
        this.urlFotoDrv = urlFotoDrv;
        notifyPropertyChanged(BR.urlFotoDrv);
    }

    public void setSimDrv(String simDrv) {
        this.simDrv = simDrv;
        notifyPropertyChanged(BR.simDrv);
    }

    public void setPasswordDrv(String passwordDrv) {
        this.passwordDrv = passwordDrv;
        notifyPropertyChanged(BR.passwordDrv);
    }

    public void setStatusDokumenDrv(int statusDokumenDrv) {
        this.statusDokumenDrv = statusDokumenDrv;
    }

    public void setBahasaAsing(int bahasaAsing) {
        this.bahasaAsing = bahasaAsing;
        notifyPropertyChanged(BR.bahasaAsing);
    }

    public void setStatusDrv(int statusDrv) {
        this.statusDrv = statusDrv;
    }

    public void setTarifDrv(float tarifDrv) {
        this.tarifDrv = tarifDrv;
    }

    public void setTarifDrvStr(String tarifDrv) {
        this.tarifDrv = Float.parseFloat(tarifDrv);
        notifyPropertyChanged(BR.tarifDrv);
    }

    public void setSuratBebasNapzaDrv(String suratBebasNapzaDrv) {
        this.suratBebasNapzaDrv = suratBebasNapzaDrv;
        notifyPropertyChanged(BR.suratBebasNapzaDrv);
    }

    public void setSuratKesehatanJiwaDrv(String suratKesehatanJiwaDrv) {
        this.suratKesehatanJiwaDrv = suratKesehatanJiwaDrv;
        notifyPropertyChanged(BR.suratKesehatanJiwaDrv);
    }

    public void setSuratKesehatanJasmaniDrv(String suratKesehatanJasmaniDrv) {
        this.suratKesehatanJasmaniDrv = suratKesehatanJasmaniDrv;
        notifyPropertyChanged(BR.suratKesehatanJasmaniDrv);
    }

    public void setSkckDrv(String skckDrv) {
        this.skckDrv = skckDrv;
        notifyPropertyChanged(BR.skckDrv);
    }

    public void setRerataRatingDrv(float rerataRatingDrv) {
        this.rerataRatingDrv = rerataRatingDrv;
        notifyPropertyChanged(BR.rerataRatingDrv);
    }

    public void setRerataRatingDrvStr(String rerataRatingDrv) {
        this.rerataRatingDrv = Float.parseFloat(rerataRatingDrv);
        notifyPropertyChanged(BR.rerataRatingDrv);
    }
}
