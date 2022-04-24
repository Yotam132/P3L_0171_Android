package com.example.p3l_ajr_0171.api;

public class TransaksiApi {
    //    public static final String BASE_URL = "https://atmasalon.readart.xyz/"; // Real Backend
    public static final String BASE_URL = "http://192.168.1.5:8000/";
    public static final String API_URL = BASE_URL + "api/";

    public static final String GET_ALL_DATA = API_URL + "transaksi";
    public static final String GET_TRANSAKSI_CUST = API_URL + "transaksicust/";
    public static final String GET_TRANSAKSI_DRV = API_URL + "transaksidrv/";
}
