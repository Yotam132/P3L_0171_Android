package com.example.p3l_ajr_0171;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.p3l_ajr_0171.customermenu.DataCustomerActivity;
import com.example.p3l_ajr_0171.customermenu.MobilActivity;
import com.example.p3l_ajr_0171.customermenu.PromoActivity;
import com.example.p3l_ajr_0171.customermenu.TransaksiActivity;
import com.example.p3l_ajr_0171.databinding.ActivityCustomerMenuBinding;
import com.example.p3l_ajr_0171.preferences.UserPreference;

public class CustomerMenuActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityCustomerMenuBinding binding;
    private UserPreference userPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer_menu);
        userPreference = new UserPreference(this);

        binding.btnDaftarMobil.setOnClickListener(this);
        binding.btnDataCustomer.setOnClickListener(this);
        binding.btnDaftarPromo.setOnClickListener(this);
        binding.btnRiwayatTransaksi.setOnClickListener(this);
        binding.btnLogout.setOnClickListener(this);

        CheckLogin();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnDaftarPromo)
        {
            Intent move = new Intent(this, PromoActivity.class);
            startActivity(move);
            finish();
        }
        else if(view.getId() == R.id.btnDaftarMobil)
        {
            Intent move = new Intent(this, MobilActivity.class);
            startActivity(move);
            finish();
        }
        else if(view.getId() == R.id.btnRiwayatTransaksi)
        {
            Intent move = new Intent(this, TransaksiActivity.class);
            startActivity(move);
            finish();
        }
        else if(view.getId() == R.id.btnDataCustomer)
        {
            Intent move = new Intent(this, DataCustomerActivity.class);
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
}