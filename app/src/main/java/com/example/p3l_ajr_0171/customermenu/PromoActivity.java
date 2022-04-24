package com.example.p3l_ajr_0171.customermenu;

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
import com.example.p3l_ajr_0171.R;
import com.example.p3l_ajr_0171.adapter.rv_promoAdapter;
import com.example.p3l_ajr_0171.api.PromoApi;
import com.example.p3l_ajr_0171.databinding.ActivityPromoBinding;
import com.example.p3l_ajr_0171.entity.Promo;
import com.example.p3l_ajr_0171.preferences.UserPreference;
import com.example.p3l_ajr_0171.response.PromoResponse;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.Method.GET;

import org.json.JSONObject;

public class PromoActivity extends AppCompatActivity implements View.OnClickListener{


    private ActivityPromoBinding binding;
    private RequestQueue queue;
    private UserPreference userPreference;
    private List<Promo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_promo);
        queue = Volley.newRequestQueue(this.getApplicationContext());
        userPreference = new UserPreference(this);
        binding.btnBack.setOnClickListener(this);

        GetPromo();
    }

    private void GetPromo()
    {
        final StringRequest stringRequest = new StringRequest(GET, PromoApi.GET_ACTIVE_PROMO + 1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        PromoResponse newResponse =
                                gson.fromJson(response, PromoResponse.class);

                        list = newResponse.getDataList();

                        if( list.isEmpty() ){
                            list.clear();
                            Toast.makeText(getApplicationContext(), "Data Riwayat masih kosong!", Toast.LENGTH_SHORT).show();
                        } else {
                            binding.rvRiwayat.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
                            binding.rvRiwayat.setAdapter(new rv_promoAdapter(list, getApplicationContext()));
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
                        list.clear();
                        binding.rvRiwayat.setAdapter(new rv_promoAdapter(list, getApplicationContext()));
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

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnBack)
        {
            Intent move = new Intent(this, CustomerMenuActivity.class);
            startActivity(move);
            finish();
        }
    }
}