package com.example.p3l_ajr_0171.customermenu;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.p3l_ajr_0171.CustomerMenuActivity;
import com.example.p3l_ajr_0171.DriverMenuActivity;
import com.example.p3l_ajr_0171.R;
import com.example.p3l_ajr_0171.adapter.rv_riwayatAdapter;
import com.example.p3l_ajr_0171.api.TransaksiApi;
import com.example.p3l_ajr_0171.databinding.ActivityTransaksiBinding;
import com.example.p3l_ajr_0171.entity.Transaksi;
import com.example.p3l_ajr_0171.preferences.UserPreference;
import com.example.p3l_ajr_0171.response.TransaksiResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransaksiActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityTransaksiBinding binding;
    private UserPreference userPreference;
    private RequestQueue queue;
    private List<Transaksi> transaksi;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaksi);
        queue = Volley.newRequestQueue(this.getApplicationContext());
        userPreference = new UserPreference(this);
        binding.btnBack.setOnClickListener(this);

        if(userPreference.getKeyNologin().equalsIgnoreCase("1"))
        {
            URL = TransaksiApi.GET_TRANSAKSI_CUST;
        }
        else if(userPreference.getKeyNologin().equalsIgnoreCase("3"))
        {
            URL = TransaksiApi.GET_TRANSAKSI_DRV;
        }

        GetTransaksi();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnBack && userPreference.getKeyNologin().equalsIgnoreCase("1"))
        { // Customer
            Intent move = new Intent(this, CustomerMenuActivity.class);
            startActivity(move);
            finish();
        }
        else if(view.getId() == R.id.btnBack && userPreference.getKeyNologin().equalsIgnoreCase("3"))
        { // Driver
            Intent move = new Intent(this, DriverMenuActivity.class);
            startActivity(move);
            finish();
        }
    }

    private void GetTransaksi()
    {
        final StringRequest stringRequest = new StringRequest(GET, URL + userPreference.GetUserID(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        TransaksiResponse newResponse =
                                gson.fromJson(response, TransaksiResponse.class);

                        transaksi = newResponse.getDataList();

                        if( transaksi.isEmpty() ){
                            transaksi.clear();
                            Toast.makeText(getApplicationContext(), "Data Riwayat masih kosong!", Toast.LENGTH_SHORT).show();
                        } else {
                            binding.rvRiwayat.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                            binding.rvRiwayat.setAdapter(new rv_riwayatAdapter(transaksi, getApplicationContext(), queue));
                        }

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

                    Toast.makeText(getApplicationContext(), errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                    if(errors.getString("message").equalsIgnoreCase("empty"))
                    {
                        transaksi.clear();
                        binding.rvRiwayat.setAdapter(new rv_riwayatAdapter(transaksi, getApplicationContext(), queue));
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
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