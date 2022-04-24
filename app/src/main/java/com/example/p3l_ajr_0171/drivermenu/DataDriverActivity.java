package com.example.p3l_ajr_0171.drivermenu;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
import com.example.p3l_ajr_0171.DriverMenuActivity;
import com.example.p3l_ajr_0171.R;
import com.example.p3l_ajr_0171.api.CustomerApi;
import com.example.p3l_ajr_0171.api.DriverApi;
import com.example.p3l_ajr_0171.databinding.ActivityDataDriverBinding;
import com.example.p3l_ajr_0171.databinding.ActivityDriverMenuBinding;
import com.example.p3l_ajr_0171.entity.Driver;
import com.example.p3l_ajr_0171.preferences.UserPreference;
import com.example.p3l_ajr_0171.response.CustomerResponseSingle;
import com.example.p3l_ajr_0171.response.DriverResponseSingle;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DataDriverActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityDataDriverBinding binding;
    private RequestQueue queue;
    private UserPreference userPreference;
    private Driver drv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_driver);
        userPreference = new UserPreference(this);
        queue = Volley.newRequestQueue(this.getApplicationContext());

        binding.btnBack.setOnClickListener(this);
        binding.btnUpdate.setOnClickListener(this);

        GetDriver();
    }

    private void GetDriver()
    {
        final StringRequest stringRequest = new StringRequest(GET, DriverApi.GET_BY_ID_URL + userPreference.GetUserID(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DriverResponseSingle newResponse =
                                gson.fromJson(response, DriverResponseSingle.class);

                        drv = newResponse.getData();
                        binding.setData(drv);

                        if(drv != null)
                        {
                            if(drv.getStatusDokumenDrv() == 1)
                            {
                                binding.tvStatusDokumen.setText("Lolos");
                            }
                            else
                            {
                                binding.tvStatusDokumen.setText("Tidak Lolos");
                            }

                            if(drv.getStatusDrv() == 1)
                            {
                                binding.tvStatus.setText("Tersedia");
                            }
                            else
                            {
                                binding.tvStatus.setText("Tidak Tersedia");
                            }

                            if(drv.getBahasaAsing() == 1)
                            {
                                binding.tvBahasaAsing.setText("Ya");
                            }
                            else
                            {
                                binding.tvBahasaAsing.setText("Tidak");
                            }

                            binding.gbFoto.setImageBitmap(Base64ToBitmap(drv.getUrlFotoDrv()));
                            binding.gbSim.setImageBitmap(Base64ToBitmap(drv.getSimDrv()));
                            binding.gbBebasNapza.setImageBitmap(Base64ToBitmap(drv.getSuratBebasNapzaDrv()));
                            binding.gbKesehatanJasmani.setImageBitmap(Base64ToBitmap(drv.getSuratKesehatanJasmaniDrv()));
                            binding.gbKesehatanJiwa.setImageBitmap(Base64ToBitmap(drv.getSuratKesehatanJiwaDrv()));
                            binding.gbSkck.setImageBitmap(Base64ToBitmap(drv.getSkckDrv()));
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

    public void onClick(View view) {
        if(view.getId() == R.id.btnBack)
        {
            Intent move = new Intent(this, DriverMenuActivity.class);
            startActivity(move);
            finish();
        }
        else if(view.getId() == R.id.btnUpdate)
        {
            Intent move = new Intent(this, UpdateDriverActivity.class);
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