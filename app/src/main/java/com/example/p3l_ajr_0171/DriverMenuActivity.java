package com.example.p3l_ajr_0171;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.p3l_ajr_0171.api.DriverApi;
import com.example.p3l_ajr_0171.customermenu.DataCustomerActivity;
import com.example.p3l_ajr_0171.customermenu.MobilActivity;
import com.example.p3l_ajr_0171.customermenu.PromoActivity;
import com.example.p3l_ajr_0171.customermenu.TransaksiActivity;
import com.example.p3l_ajr_0171.databinding.ActivityDriverMenuBinding;
import com.example.p3l_ajr_0171.drivermenu.DataDriverActivity;
import com.example.p3l_ajr_0171.entity.Driver;
import com.example.p3l_ajr_0171.preferences.UserPreference;
import com.example.p3l_ajr_0171.response.DriverResponseSingle;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DriverMenuActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityDriverMenuBinding binding;
    private RequestQueue queue;
    private UserPreference userPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_driver_menu);
        userPreference = new UserPreference(this);
        queue = Volley.newRequestQueue(this.getApplicationContext());

        binding.btnUbahStatus.setOnClickListener(this);
        binding.btnDataCustomer.setOnClickListener(this);;
        binding.btnRiwayatTransaksi.setOnClickListener(this);
        binding.btnLogout.setOnClickListener(this);

        CheckLogin();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnUbahStatus)
        {
            UbahStatus();
        }
        else if(view.getId() == R.id.btnDataCustomer)
        {
            Intent move = new Intent(this, DataDriverActivity.class);
            startActivity(move);
            finish();
        }
        else if(view.getId() == R.id.btnRiwayatTransaksi)
        {
            Intent move = new Intent(this, TransaksiActivity.class);
            startActivity(move);
            finish();
        }
        else if(view.getId() == R.id.btnLogout)
        {
            userPreference.Logout();
            CheckLogin();
        }
    }

    private void CheckLogin()
    {
        if(!userPreference.CheckLogin())
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void UbahStatus()
    {
        final StringRequest stringRequest = new StringRequest(GET, DriverApi.SWITCH_STAT + userPreference.GetUserID(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DriverResponseSingle newResponse =
                                gson.fromJson(response, DriverResponseSingle.class);

                        Driver drv = newResponse.getData();
                        if(drv != null)
                        {
                            if(drv.getStatusDrv() == 1)
                            {
                                Toast.makeText(DriverMenuActivity.this, "Status anda menjadi Tersedia!" , Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(DriverMenuActivity.this, "Status anda menjadi tidak tersedia!" , Toast.LENGTH_SHORT).show();
                            }
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