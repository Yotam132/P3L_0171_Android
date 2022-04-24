package com.example.p3l_ajr_0171.adapter;


import static com.android.volley.Request.Method.GET;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.p3l_ajr_0171.BR;
import com.example.p3l_ajr_0171.R;
import com.example.p3l_ajr_0171.api.CustomerApi;
import com.example.p3l_ajr_0171.api.DriverApi;
import com.example.p3l_ajr_0171.api.MobilApi;
import com.example.p3l_ajr_0171.api.PegawaiApi;
import com.example.p3l_ajr_0171.api.PromoApi;
import com.example.p3l_ajr_0171.databinding.RvRiwayatBinding;
import com.example.p3l_ajr_0171.entity.Transaksi;
import com.example.p3l_ajr_0171.preferences.UserPreference;
import com.example.p3l_ajr_0171.response.CustomerResponseSingle;
import com.example.p3l_ajr_0171.response.DriverResponseSingle;
import com.example.p3l_ajr_0171.response.MobilResponseSingle;
import com.example.p3l_ajr_0171.response.PegawaiResponseSingle;
import com.example.p3l_ajr_0171.response.PromoResponseSingle;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class rv_riwayatAdapter extends RecyclerView.Adapter<rv_riwayatAdapter.MyViewHolder>{
    private List<Transaksi> list;
    private Context con;
    private RequestQueue queue;
    private UserPreference userPreference;

    public rv_riwayatAdapter(List<Transaksi> listReservasi, Context con, RequestQueue queue)
    {
        this.list = listReservasi;
        this.con = con;
        this.queue = queue;
        userPreference = new UserPreference(con);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvRiwayatBinding rvRiwayatBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_riwayat, parent,false);
        return new MyViewHolder(rvRiwayatBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Transaksi data = list.get(holder.getAdapterPosition());
        holder.binding.setVariable(BR.data, data);

        if(userPreference.getKeyNologin().equalsIgnoreCase("1"))
        {//customer
            if(data.getIdDriver() > 0)
            {
                GetNamaDriver(data.getIdDriver(), holder.binding.tvNamaDrv);
            }
            else
            {
                holder.binding.tvNamaDrv.setText("-");
            }
        }
        else if(userPreference.getKeyNologin().equalsIgnoreCase("3"))
        {
            holder.binding.namaDrv.setText("Nama Customer");
            GetNamaCust(data.getIdCustomer(), holder.binding.tvNamaDrv);
        }


        if(data.getIdMobil() > 0)
        {
            GetNamaMobil(data.getIdMobil(), holder.binding.tvNamaMbl);
        }

        if(data.getIdPegawai() > 0)
        {
            GetNamaPegawai(data.getIdPegawai(), holder.binding.tvNamaPgw);
        }

        if(data.getIdPromo() > 0)
        {
            GetNamaPromo(data.getIdPromo(), holder.binding.tvKodePromo);
        }
        else
        {
            holder.binding.tvKodePromo.setText("-");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private RvRiwayatBinding binding;

        public MyViewHolder(@NonNull RvRiwayatBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void GetNamaDriver(int id, TextView tv)
    {
        final StringRequest stringRequest = new StringRequest(GET, DriverApi.GET_BY_ID_URL + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DriverResponseSingle newResponse =
                                gson.fromJson(response, DriverResponseSingle.class);

                        tv.setText(newResponse.getData().getNamaDrv());

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(con, errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(con, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        queue.add(stringRequest);
    }

    private void GetNamaCust(int id, TextView tv)
    {
        final StringRequest stringRequest = new StringRequest(GET, CustomerApi.GET_BY_ID_URL + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        CustomerResponseSingle newResponse =
                                gson.fromJson(response, CustomerResponseSingle.class);

                        tv.setText(newResponse.getData().getNamaCust());

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(con, errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(con, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        queue.add(stringRequest);
    }

    private void GetNamaMobil(int id, TextView tv)
    {
        final StringRequest stringRequest = new StringRequest(GET, MobilApi.GET_BY_ID_URL + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        MobilResponseSingle newResponse =
                                gson.fromJson(response, MobilResponseSingle.class);

                        tv.setText(newResponse.getData().getNamaMbl());

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(con, errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(con, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        queue.add(stringRequest);
    }

    private void GetNamaPegawai(int id, TextView tv)
    {
        final StringRequest stringRequest = new StringRequest(GET, PegawaiApi.GET_BY_ID_URL + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        PegawaiResponseSingle newResponse =
                                gson.fromJson(response, PegawaiResponseSingle.class);

                        tv.setText(newResponse.getData().getNamaPgw());

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(con, errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(con, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        queue.add(stringRequest);
    }

    private void GetNamaPromo(int id, TextView tv)
    {
        final StringRequest stringRequest = new StringRequest(GET, PromoApi.GET_BY_ID_URL + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        PromoResponseSingle newResponse =
                                gson.fromJson(response, PromoResponseSingle.class);

                        tv.setText(newResponse.getData().getKodePrm());

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(con, errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(con, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        queue.add(stringRequest);
    }
}
