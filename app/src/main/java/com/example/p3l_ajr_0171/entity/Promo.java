package com.example.p3l_ajr_0171.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

public class Promo extends BaseObservable {
    @SerializedName("idPromo")
    private int idPromo;

    @SerializedName("kodePrm")
    private String kodePrm;

    @SerializedName("jenisPrm")
    private String jenisPrm;

    @SerializedName("keteranganPrm")
    private String keteranganPrm;

    @SerializedName("diskonPrm")
    private float diskonPrm;

    @SerializedName("statusPrm")
    private int statusPrm;

    public Promo(int idPromo, String kodePrm, String jenisPrm, String keteranganPrm, float diskonPrm, int statusPrm) {
        this.idPromo = idPromo;
        this.kodePrm = kodePrm;
        this.jenisPrm = jenisPrm;
        this.keteranganPrm = keteranganPrm;
        this.diskonPrm = diskonPrm;
        this.statusPrm = statusPrm;
    }

    @Bindable
    public int getIdPromo() {
        return idPromo;
    }

    @Bindable
    public String getKodePrm() {
        return kodePrm;
    }

    @Bindable
    public String getJenisPrm() {
        return jenisPrm;
    }

    @Bindable
    public String getKeteranganPrm() {
        return keteranganPrm;
    }

    @Bindable
    public float getDiskonPrm() {
        return diskonPrm;
    }

    @Bindable
    public String getDiskonPrmStr() {
        return String.valueOf(diskonPrm);
    }

    @Bindable
    public int getStatusPrm() {
        return statusPrm;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public void setKodePrm(String kodePrm) {
        this.kodePrm = kodePrm;
        notifyPropertyChanged(BR.kodePrm);
    }

    public void setJenisPrm(String jenisPrm) {
        this.jenisPrm = jenisPrm;
        notifyPropertyChanged(BR.jenisPrm);
    }

    public void setKeteranganPrm(String keteranganPrm) {
        this.keteranganPrm = keteranganPrm;
        notifyPropertyChanged(BR.keteranganPrm);
    }

    public void setDiskonPrm(float diskonPrm) {
        this.diskonPrm = diskonPrm;
    }

    public void setDiskonPrmStr(String diskonPrm) {
        this.diskonPrm = Float.parseFloat(diskonPrm);
        notifyPropertyChanged(BR.diskonPrm);
    }

    public void setStatusPrm(int statusPrm) {
        this.statusPrm = statusPrm;
    }
}
