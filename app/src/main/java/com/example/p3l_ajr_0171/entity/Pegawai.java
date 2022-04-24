package com.example.p3l_ajr_0171.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pegawai extends BaseObservable {
    @SerializedName("idPegawai")
    private int idPegawai;

    @SerializedName("namaPgw")
    private String namaPgw;

    @SerializedName("alamatPgw")
    private String alamatPgw;

    @SerializedName("tglLahirPgw")
    private Date tglLahirPgw;

    @SerializedName("jenisKelaminPgw")
    private String jenisKelaminPgw;

    @SerializedName("emailPgw")
    private String emailPgw;

    @SerializedName("noTelpPgw")
    private String noTelpPgw;

    @SerializedName("urlFotoPgw")
    private String urlFotoPgw;

    @SerializedName("jumlahShiftPgw")
    private int jumlahShiftPgw;

    @SerializedName("passwordPgw")
    private String passwordPgw;

    @SerializedName("idRole")
    private int idRole;

    public Pegawai(int idPegawai, String namaPgw, String alamatPgw, Date tglLahirPgw, String jenisKelaminPgw, String emailPgw, String noTelpPgw, String urlFotoPgw, int jumlahShiftPgw, String passwordPgw, int idRole) {
        this.namaPgw = namaPgw;
        this.idPegawai = idPegawai;
        this.alamatPgw = alamatPgw;
        this.tglLahirPgw = tglLahirPgw;
        this.jenisKelaminPgw = jenisKelaminPgw;
        this.emailPgw = emailPgw;
        this.noTelpPgw = noTelpPgw;
        this.urlFotoPgw = urlFotoPgw;
        this.jumlahShiftPgw = jumlahShiftPgw;
        this.passwordPgw = passwordPgw;
        this.idRole = idRole;
    }

    @Bindable
    public String getNamaPgw() {
        return namaPgw;
    }

    public void setNamaPgw(String namaPgw) {
        this.namaPgw = namaPgw;
    }

    @Bindable
    public int getIdPegawai() {
        return idPegawai;
    }

    @Bindable
    public String getAlamatPgw() {
        return alamatPgw;
    }

    @Bindable
    public Date getTglLahirPgw() {
        return tglLahirPgw;
    }

    @Bindable
    public String getTglLahirPgwStr() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yy");
        return simpleDate.format(tglLahirPgw);
    }



    @Bindable
    public String getJenisKelaminPgw() {
        return jenisKelaminPgw;
    }

    @Bindable
    public String getEmailPgw() {
        return emailPgw;
    }

    @Bindable
    public String getNoTelpPgw() {
        return noTelpPgw;
    }

    @Bindable
    public String getUrlFotoPgw() {
        return urlFotoPgw;
    }

    @Bindable
    public int getJumlahShiftPgw() {
        return jumlahShiftPgw;
    }

    @Bindable
    public String getPasswordPgw() {
        return passwordPgw;
    }

    @Bindable
    public int getIdRole() {
        return idRole;
    }

    public void setIdPegawai(int idPegawai) {
        this.idPegawai = idPegawai;
    }

    public void setAlamatPgw(String alamatPgw) {
        this.alamatPgw = alamatPgw;
    }

    public void setTglLahirPgw(Date tglLahirPgw) {
        this.tglLahirPgw = tglLahirPgw;
    }

    public void setTglLahirPgwStr(String tglLahirPgw) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reformattedStr = "";
        try {
            reformattedStr = myFormat.format(tglLahirPgw);
            this.tglLahirPgw = myFormat.parse(reformattedStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setJenisKelaminPgw(String jenisKelaminPgw) {
        this.jenisKelaminPgw = jenisKelaminPgw;
    }

    public void setEmailPgw(String emailPgw) {
        this.emailPgw = emailPgw;
    }

    public void setNoTelpPgw(String noTelpPgw) {
        this.noTelpPgw = noTelpPgw;
    }

    public void setUrlFotoPgw(String urlFotoPgw) {
        this.urlFotoPgw = urlFotoPgw;
    }

    public void setJumlahShiftPgw(int jumlahShiftPgw) {
        this.jumlahShiftPgw = jumlahShiftPgw;
    }

    public void setPasswordPgw(String passwordPgw) {
        this.passwordPgw = passwordPgw;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
