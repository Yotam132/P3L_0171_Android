package com.example.p3l_ajr_0171.customermenu;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
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
import com.example.p3l_ajr_0171.api.CustomerApi;
import com.example.p3l_ajr_0171.api.PromoApi;
import com.example.p3l_ajr_0171.databinding.ActivityDataCustomerBinding;
import com.example.p3l_ajr_0171.databinding.ActivityPromoBinding;
import com.example.p3l_ajr_0171.entity.Customer;
import com.example.p3l_ajr_0171.entity.Promo;
import com.example.p3l_ajr_0171.preferences.UserPreference;
import com.example.p3l_ajr_0171.response.CustomerResponseSingle;
import com.example.p3l_ajr_0171.response.PromoResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataCustomerActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityDataCustomerBinding binding;
    private RequestQueue queue;
    private UserPreference userPreference;
    private Customer custNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_customer);
        queue = Volley.newRequestQueue(this.getApplicationContext());
        userPreference = new UserPreference(this);
        binding.btnBack.setOnClickListener(this);

        binding.setData(custNow);

        GetCustomer();
    }


    private void GetCustomer()
    {
        final StringRequest stringRequest = new StringRequest(GET, CustomerApi.GET_BY_ID_URL + userPreference.GetUserID(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        CustomerResponseSingle newResponse =
                                gson.fromJson(response, CustomerResponseSingle.class);

                        custNow = newResponse.getData();
                        binding.setData(custNow);

                        if(custNow != null)
                        {
                            if(custNow.getStatusDokumenCust() == 1)
                            {
                                binding.tvStatusDokumen.setText("Lolos");
                            }
                            else
                            {
                                binding.tvStatusDokumen.setText("Tidak Lolos");
                            }

                            binding.gbKartuPengenal.setImageBitmap(Base64ToBitmap(custNow.getKartuPengenalCust()));
                            binding.gbSim.setImageBitmap(Base64ToBitmap(custNow.getSimCust()));
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

    private Bitmap Base64ToBitmap(String encodedImage)
    {
        if(encodedImage.isEmpty() || encodedImage == null){return null;}
        try
        {
            String cleanImage = encodedImage.replace("data:image/png;base64,", "")
                    .replace("data:image/jpeg;base64,","")
                    .replace("data:image/jpg;base64,","")
                    .replace("data:image/gif;base64,","");
            byte[] decodedString = Base64.decode(cleanImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;
        }
        catch (Exception e)
        {
            return null;
        }
    }


}