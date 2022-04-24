package com.example.p3l_ajr_0171.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p3l_ajr_0171.BR;
import com.example.p3l_ajr_0171.R;
import com.example.p3l_ajr_0171.databinding.RvMobilBinding;
import com.example.p3l_ajr_0171.databinding.RvPromoBinding;
import com.example.p3l_ajr_0171.entity.Mobil;
import com.example.p3l_ajr_0171.entity.Promo;

import java.util.List;

public class rv_mobilAdapter extends RecyclerView.Adapter<rv_mobilAdapter.MyViewHolder>{
    private List<Mobil> list;
    private Context con;

    public rv_mobilAdapter(List<Mobil> list, Context con)
    {
        this.list = list;
        this.con = con;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvMobilBinding rvMobilBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_mobil, parent,false);
        return new MyViewHolder(rvMobilBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Mobil data = list.get(holder.getAdapterPosition());
        holder.binding.setVariable(BR.data, data);

        holder.binding.tvGambar.setImageBitmap(Base64ToBitmap(data.getUrlFotoMbl()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private RvMobilBinding binding;

        public MyViewHolder(@NonNull RvMobilBinding binding){
            super(binding.getRoot());
            this.binding = binding;

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
