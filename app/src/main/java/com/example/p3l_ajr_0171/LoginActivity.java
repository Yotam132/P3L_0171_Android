package com.example.p3l_ajr_0171;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.p3l_ajr_0171.adapter.rv_mobilAdapter;
import com.example.p3l_ajr_0171.adapter.rv_promoAdapter;
import com.example.p3l_ajr_0171.adapter.rv_riwayatAdapter;
import com.example.p3l_ajr_0171.api.LoginApi;
import com.example.p3l_ajr_0171.api.MobilApi;
import com.example.p3l_ajr_0171.api.PromoApi;
import com.example.p3l_ajr_0171.api.TransaksiApi;
import com.example.p3l_ajr_0171.databinding.ActivityLoginBinding;
import com.example.p3l_ajr_0171.entity.Customer;
import com.example.p3l_ajr_0171.entity.Driver;
import com.example.p3l_ajr_0171.entity.LoginData;
import com.example.p3l_ajr_0171.entity.Mobil;
import com.example.p3l_ajr_0171.entity.Pegawai;
import com.example.p3l_ajr_0171.entity.Promo;
import com.example.p3l_ajr_0171.entity.Transaksi;
import com.example.p3l_ajr_0171.preferences.UserPreference;
import com.example.p3l_ajr_0171.response.CustomerResponseSingle;
import com.example.p3l_ajr_0171.response.DriverResponseSingle;
import com.example.p3l_ajr_0171.response.MobilResponse;
import com.example.p3l_ajr_0171.response.PegawaiResponseSingle;
import com.example.p3l_ajr_0171.response.PromoResponse;
import com.example.p3l_ajr_0171.response.TransaksiResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private UserPreference userPreference;
    private List<Transaksi> transaksi;
    private List<Mobil> mobil;
    private LoginData loginData;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        queue = Volley.newRequestQueue(this.getApplicationContext());
        userPreference = new UserPreference(this);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.loginsebagai, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        binding.spinnerLogin.setAdapter(adapter);
        binding.btnMasuk.setOnClickListener(this);


        CheckLogin();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnMasuk)
        {
            String email = binding.inputLayoutUsername.getEditText().getText().toString();
            String password = binding.inputLayoutPassword.getEditText().getText().toString();
            String sebagai = binding.spinnerLogin.getSelectedItem().toString();
            String no = "";

            if(sebagai.equalsIgnoreCase("Customer"))
            {
                no = "1";
            }
            else if(sebagai.equalsIgnoreCase("Manager"))
            {
                no = "2";
            }
            else if(sebagai.equalsIgnoreCase("Driver"))
            {
                no = "3";
            }

            loginData = new LoginData(email, password, no);
            Login(loginData);
        }
    }

    private void CheckLogin()
    {
        if(userPreference.CheckLogin())
        {
            if(userPreference.getKeyNologin().equalsIgnoreCase("1"))
            { // Customer Site
                Intent move = new Intent(this, CustomerMenuActivity.class);
                startActivity(move);
                finish();
            }
            else if(userPreference.getKeyNologin().equalsIgnoreCase("2"))
            { // Manager Site
                Intent move = new Intent(this, ManagerMenuActivity.class);
                startActivity(move);
                finish();
            }
            else if(userPreference.getKeyNologin().equalsIgnoreCase("3"))
            { // Driver Site
                Intent move = new Intent(this, DriverMenuActivity.class);
                startActivity(move);
                finish();
            }
        }
    }

    private void Login(LoginData user) {

        final StringRequest stringRequest = new StringRequest(POST, LoginApi.LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        if(user.getNo().equalsIgnoreCase("1"))
                        {
                            CustomerResponseSingle userResponse =
                                    gson.fromJson(response, CustomerResponseSingle.class);

                            Customer cust = userResponse.getData();
                            if(cust != null)
                            {
                                Toast.makeText(LoginActivity.this, userResponse.getMessage(),
                                        Toast.LENGTH_SHORT).show();

                                Intent returnIntent = new Intent();
                                setResult(Activity.RESULT_OK, returnIntent);

                                LoginActivity.this.userPreference.SetLogin(cust.getIdCustomer(), user.getNo());
                                CheckLogin();
                            }
                        }
                        else if(user.getNo().equalsIgnoreCase("2"))
                        {
                            PegawaiResponseSingle userResponse =
                                    gson.fromJson(response, PegawaiResponseSingle.class);

                            Pegawai pgw = userResponse.getData();
                            if(pgw != null)
                            {
                                if(pgw.getIdRole() == 1)
                                {
                                    Toast.makeText(LoginActivity.this, userResponse.getMessage(),
                                            Toast.LENGTH_SHORT).show();

                                    Intent returnIntent = new Intent();
                                    setResult(Activity.RESULT_OK, returnIntent);

                                    LoginActivity.this.userPreference.SetLogin(pgw.getIdPegawai(), user.getNo());
                                    CheckLogin();
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this, "Pegawai Bukan Manager", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                        else if(user.getNo().equalsIgnoreCase("3"))
                        {
                            DriverResponseSingle userResponse =
                                    gson.fromJson(response, DriverResponseSingle.class);

                            Driver drv = userResponse.getData();
                            if(drv != null)
                            {
                                Toast.makeText(LoginActivity.this, userResponse.getMessage(),
                                        Toast.LENGTH_SHORT).show();

                                Intent returnIntent = new Intent();
                                setResult(Activity.RESULT_OK, returnIntent);

                                LoginActivity.this.userPreference.SetLogin(drv.getIdDriver(), user.getNo());
                                CheckLogin();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);

                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(LoginActivity.this, errors.getString("message"),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, e.getMessage(),
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

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String requestBody = gson.toJson(user);

                return requestBody.getBytes(StandardCharsets.UTF_8);
            }
        };

        queue.add(stringRequest);
    }

}