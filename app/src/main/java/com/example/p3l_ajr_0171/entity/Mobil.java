package com.example.p3l_ajr_0171.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mobil extends BaseObservable {
    @SerializedName("idMobil")
    private int idMobil;

    @SerializedName("idMitra")
    private int idMitra;

    @SerializedName("namaMbl")
    private String namaMbl;

    @SerializedName("urlFotoMbl")
    private String urlFotoMbl;

    @SerializedName("tipeMbl")
    private String tipeMbl;

    @SerializedName("jenisTransmisi")
    private String jenisTransmisi;

    @SerializedName("jenisBahanBakar")
    private String jenisBahanBakar;

    @SerializedName("warna")
    private String warna;

    @SerializedName("volumeBahanBakar")
    private float volumeBahanBakar;

    @SerializedName("kapasitasPenumpang")
    private int kapasitasPenumpang;

    @SerializedName("fasilitas")
    private String fasilitas;

    @SerializedName("platNomor")
    private String platNomor;

    @SerializedName("noStnk")
    private String noStnk;

    @SerializedName("hargaSewa")
    private float hargaSewa;

    @SerializedName("kategoriAset")
    private String kategoriAset;

    @SerializedName("periodeKontrakMulai")
    private Date periodeKontrakMulai;

    @SerializedName("periodeKontrakAkhir")
    private Date periodeKontrakAkhir;

    @SerializedName("tglServisTerakhir")
    private Date tglServisTerakhir;

    @SerializedName("statusMbl")
    private int statusMbl;

    public Mobil(int idMobil, int idMitra, String namaMbl, String urlFotoMbl, String tipeMbl, String jenisTransmisi, String jenisBahanBakar, String warna, float volumeBahanBakar, int kapasitasPenumpang, String fasilitas, String platNomor, String noStnk, float hargaSewa, String kategoriAset, Date periodeKontrakMulai, Date periodeKontrakAkhir, Date tglServisTerakhir, int statusMbl) {
        this.idMobil = idMobil;
        this.idMitra = idMitra;
        this.namaMbl = namaMbl;
        this.urlFotoMbl = urlFotoMbl;
        this.tipeMbl = tipeMbl;
        this.jenisTransmisi = jenisTransmisi;
        this.jenisBahanBakar = jenisBahanBakar;
        this.warna = warna;
        this.volumeBahanBakar = volumeBahanBakar;
        this.kapasitasPenumpang = kapasitasPenumpang;
        this.fasilitas = fasilitas;
        this.platNomor = platNomor;
        this.noStnk = noStnk;
        this.hargaSewa = hargaSewa;
        this.kategoriAset = kategoriAset;
        this.periodeKontrakMulai = periodeKontrakMulai;
        this.periodeKontrakAkhir = periodeKontrakAkhir;
        this.tglServisTerakhir = tglServisTerakhir;
        this.statusMbl = statusMbl;
    }

    @Bindable
    public int getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(int idMobil) {
        this.idMobil = idMobil;
    }

    @Bindable
    public int getIdMitra() {
        return idMitra;
    }

    @Bindable
    public String getNamaMbl() {
        return namaMbl;
    }

    @Bindable
    public String getUrlFotoMbl() {
        return urlFotoMbl;
    }

    @Bindable
    public String getTipeMbl() {
        return tipeMbl;
    }

    @Bindable
    public String getJenisTransmisi() {
        return jenisTransmisi;
    }

    @Bindable
    public String getJenisBahanBakar() {
        return jenisBahanBakar;
    }

    @Bindable
    public String getWarna() {
        return warna;
    }

    @Bindable
    public float getVolumeBahanBakar() {
        return volumeBahanBakar;
    }

    @Bindable
    public String getVolumeBahanBakarStr() {
        return String.valueOf(volumeBahanBakar);
    }

    @Bindable
    public int getKapasitasPenumpang() {
        return kapasitasPenumpang;
    }

    @Bindable
    public String getFasilitas() {
        return fasilitas;
    }

    @Bindable
    public String getPlatNomor() {
        return platNomor;
    }

    @Bindable
    public String getNoStnk() {
        return noStnk;
    }

    @Bindable
    public float getHargaSewa() {
        return hargaSewa;
    }

    @Bindable
    public String getHargaSewaStr() {
        return String.valueOf(hargaSewa);
    }

    @Bindable
    public String getKategoriAset() {
        return kategoriAset;
    }

    @Bindable
    public Date getPeriodeKontrakMulai() {
        return periodeKontrakMulai;
    }

    @Bindable
    public Date getPeriodeKontrakAkhir() {
        return periodeKontrakAkhir;
    }

    @Bindable
    public Date getTglServisTerakhir() {
        return tglServisTerakhir;
    }

    @Bindable
    public String getPeriodeKontrakMulaiStr() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yy");
        return simpleDate.format(periodeKontrakMulai);
    }

    @Bindable
    public String getPeriodeKontrakAkhirStr() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yy");
        return simpleDate.format(periodeKontrakAkhir);
    }

    @Bindable
    public String getTglServisTerakhirStr() {
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd-MM-yy");
        return simpleDate.format(tglServisTerakhir);
    }

    @Bindable
    public int getStatusMbl() {
        return statusMbl;
    }

    public void setIdMitra(int idMitra) {
        this.idMitra = idMitra;
    }

    public void setNamaMbl(String namaMbl) {
        this.namaMbl = namaMbl;
        notifyPropertyChanged(BR.namaMbl);
    }

    public void setUrlFotoMbl(String urlFotoMbl) {
        this.urlFotoMbl = urlFotoMbl;
    }

    public void setTipeMbl(String tipeMbl) {
        this.tipeMbl = tipeMbl;
        notifyPropertyChanged(BR.tipeMbl);
    }

    public void setJenisTransmisi(String jenisTransmisi) {
        this.jenisTransmisi = jenisTransmisi;
        notifyPropertyChanged(BR.jenisTransmisi);
    }

    public void setJenisBahanBakar(String jenisBahanBakar) {
        this.jenisBahanBakar = jenisBahanBakar;
        notifyPropertyChanged(BR.jenisBahanBakar);
    }

    public void setWarna(String warna) {
        this.warna = warna;
        notifyPropertyChanged(BR.warna);
    }

    public void setVolumeBahanBakar(float volumeBahanBakar) {
        this.volumeBahanBakar = volumeBahanBakar;
    }

    public void setVolumeBahanBakarStr(String volumeBahanBakar) {
        this.volumeBahanBakar = Float.parseFloat(volumeBahanBakar);
    }

    public void setKapasitasPenumpang(int kapasitasPenumpang) {
        this.kapasitasPenumpang = kapasitasPenumpang;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
        notifyPropertyChanged(BR.fasilitas);
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
        notifyPropertyChanged(BR.platNomor);
    }

    public void setNoStnk(String noStnk) {
        this.noStnk = noStnk;
    }

    public void setHargaSewa(float hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public void setHargaSewaStr(String hargaSewa) {
        this.hargaSewa = Float.parseFloat(hargaSewa);
        notifyPropertyChanged(BR.hargaSewa);
    }

    public void setKategoriAset(String kategoriAset) {
        this.kategoriAset = kategoriAset;
    }

    public void setPeriodeKontrakMulai(Date periodeKontrakMulai) {
        this.periodeKontrakMulai = periodeKontrakMulai;
    }

    public void setPeriodeKontrakAkhir(Date periodeKontrakAkhir) {
        this.periodeKontrakAkhir = periodeKontrakAkhir;
    }

    public void setTglServisTerakhir(Date tglServisTerakhir) {
        this.tglServisTerakhir = tglServisTerakhir;
    }

    public void setPeriodeKontrakMulaiStr(String periodeKontrakMulai) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reformattedStr = "";
        try {
            reformattedStr = myFormat.format(periodeKontrakMulai);
            this.periodeKontrakMulai = myFormat.parse(reformattedStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPeriodeKontrakAkhirStr(String periodeKontrakAkhir) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reformattedStr = "";
        try {
            reformattedStr = myFormat.format(periodeKontrakAkhir);
            this.periodeKontrakAkhir = myFormat.parse(reformattedStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTglServisTerakhirStr(String tglServisTerakhir) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        String reformattedStr = "";
        try {
            reformattedStr = myFormat.format(tglServisTerakhir);
            this.tglServisTerakhir = myFormat.parse(reformattedStr);
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    public void setStatusMbl(int statusMbl) {
        this.statusMbl = statusMbl;
    }
}
