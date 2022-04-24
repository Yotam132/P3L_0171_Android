package com.example.p3l_ajr_0171.drivermenu;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.PUT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.p3l_ajr_0171.CustomerMenuActivity;
import com.example.p3l_ajr_0171.R;
import com.example.p3l_ajr_0171.api.DriverApi;
import com.example.p3l_ajr_0171.databinding.ActivityUpdateDriverBinding;
import com.example.p3l_ajr_0171.entity.Driver;
import com.example.p3l_ajr_0171.preferences.UserPreference;
import com.example.p3l_ajr_0171.response.DriverResponseSingle;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class UpdateDriverActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityUpdateDriverBinding binding;
    private RequestQueue queue;
    private UserPreference userPreference;
    private Driver drv;
    private int mYear,mMonth,mDay;
    private Calendar calendarToUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_driver);
        userPreference = new UserPreference(this);
        queue = Volley.newRequestQueue(this.getApplicationContext());

        binding.btnBack.setOnClickListener(this);
        binding.btnUpdate.setOnClickListener(this);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(Calendar.DATE, 0);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                binding.inputTglLahir.getEditText().setText(sdf.format(myCalendar.getTime()));
            }


        };

        binding.btnTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(UpdateDriverActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                view.updateDate(mYear,mMonth,mDay);
                                Calendar cal = Calendar.getInstance();
                                cal.set(year, monthOfYear, dayOfMonth);
                                calendarToUse = cal;

                                String myFormat = "yyyy-MM-dd"; //In which you need put here
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                                binding.inputTglLahir.getEditText().setText(sdf.format(cal.getTime()));

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();

            }
        });



        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.bahasaasing, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        binding.spinnerBahasaAsing.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2= ArrayAdapter.createFromResource(this, R.array.jeniskelamin, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        binding.spinnerKelamin.setAdapter(adapter2);

        GetDriver();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnBack)
        {
            Intent move = new Intent(this, DataDriverActivity.class);
            startActivity(move);
            finish();
        }
        else if(view.getId() == R.id.btnUpdate)
        {
            drv = binding.getData();
            if(binding.spinnerBahasaAsing.getSelectedItemPosition() == 0)
            {
                drv.setBahasaAsing(1);
            }
            else
            {
                drv.setBahasaAsing(0);
            }

            if(binding.spinnerKelamin.getSelectedItemPosition() == 0)
            {
                drv.setJenisKelaminDrv("L");
            }
            else
            {
                drv.setJenisKelaminDrv("P");
            }

            if(calendarToUse != null)
            {
                drv.setTglLahirDrv(calendarToUse.getTime());
            }

            // Validation
            if(Validation())
            {
                // UPDATE DATA
                UpdateData();
            }
        }
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
                            if(drv.getBahasaAsing() == 1)
                            {
                                binding.spinnerBahasaAsing.setSelection(0);
                            }
                            else
                            {
                                binding.spinnerBahasaAsing.setSelection(1);
                            }

                            if(drv.getJenisKelaminDrv().equalsIgnoreCase("L"))
                            {
                                binding.spinnerKelamin.setSelection(0);
                            }
                            else
                            {
                                binding.spinnerKelamin.setSelection(1);
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

    private boolean Validation()
    {
        if(binding.inputNama.getEditText().getText().toString().isEmpty())
        {
            Toast.makeText(this, "Silahkan isi semua field", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(binding.inputAlamat.getEditText().getText().toString().isEmpty())
        {
            Toast.makeText(this, "Silahkan isi semua field", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(binding.inputNoTelp.getEditText().getText().toString().isEmpty())
        {
            Toast.makeText(this, "Silahkan isi semua field", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(binding.inputTarif.getEditText().getText().toString().isEmpty())
        {
            Toast.makeText(this, "Silahkan isi semua field", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(binding.inputLayoutPassword.getEditText().getText().toString().isEmpty())
        {
            Toast.makeText(this, "Silahkan isi semua field", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void UpdateData() {
//        setLoading(true);

        final StringRequest stringRequest = new StringRequest(PUT, DriverApi.EDIT_URL + userPreference.GetUserID(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DriverResponseSingle driverResponse =
                                gson.fromJson(response, DriverResponseSingle.class);

                        Toast.makeText(UpdateDriverActivity.this, driverResponse.getMessage(),
                                Toast.LENGTH_SHORT).show();

                        Intent returnIntent = new Intent();
                        UpdateDriverActivity.this.setResult(Activity.RESULT_OK, returnIntent);

                        Intent move = new Intent(UpdateDriverActivity.this, DataDriverActivity.class);
                        startActivity(move);
                        finish();

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

                    Toast.makeText(UpdateDriverActivity.this, errors.getString("message"),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(UpdateDriverActivity.this, e.getMessage(),
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
                String requestBody = gson.toJson(drv);

                return requestBody.getBytes(StandardCharsets.UTF_8);
            }
        };

        queue.add(stringRequest);
    }
}