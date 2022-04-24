package com.example.p3l_ajr_0171.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p3l_ajr_0171.BR;
import com.example.p3l_ajr_0171.R;

import com.example.p3l_ajr_0171.databinding.RvPromoBinding;
import com.example.p3l_ajr_0171.entity.Promo;

import java.util.List;

public class rv_promoAdapter extends RecyclerView.Adapter<rv_promoAdapter.MyViewHolder>{
    private List<Promo> list;
    private Context con;

    public rv_promoAdapter(List<Promo> listReservasi, Context con)
    {
        this.list = listReservasi;
        this.con = con;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvPromoBinding rvPromoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_promo, parent,false);
        return new MyViewHolder(rvPromoBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Promo data = list.get(holder.getAdapterPosition());
        holder.binding.setVariable(BR.data, data);

        holder.binding.tvDiskon.setText(data.getDiskonPrmStr() + " %");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private RvPromoBinding binding;

        public MyViewHolder(@NonNull RvPromoBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
