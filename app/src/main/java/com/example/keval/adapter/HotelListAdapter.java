package com.example.keval.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.keval.R;
import com.example.keval.models.HotelModel;

import java.util.ArrayList;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick();
    }

    ArrayList<HotelModel> mData = new ArrayList<>();
    private final OnItemClickListener listener;

    public HotelListAdapter(ArrayList<HotelModel> mData, OnItemClickListener listener) {
        this.mData = mData;
        this.listener = listener;

    }

    @NonNull
    @Override
    public HotelListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListAdapter.ViewHolder holder, int position) {
        holder.tvHotelName.setText(mData.get(position).getPlace().getName());
        holder.tvLocation.setText(mData.get(position).getPlace().getName_suffix());

        Glide.with(holder.ivImage.getContext())
                .load(mData.get(position).getPlace().getThumbnail_url())
                .into(holder.ivImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvHotelName, tvLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHotelName = itemView.findViewById(R.id.tvHotelName);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvLocation = itemView.findViewById(R.id.tvLocation);
        }
    }
}
