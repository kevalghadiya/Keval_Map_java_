package com.example.keval.RoomDataBase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keval.R;

import java.util.List;

public class GetDataAdapter extends RecyclerView.Adapter<GetDataAdapter.ViewHolder> {
    List<User> data;

    public GetDataAdapter(List<User> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public GetDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetDataAdapter.ViewHolder holder, int position) {
        holder.tvHotelName.setText(data.get(position).firstName);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHotelName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHotelName =itemView.findViewById(R.id.tvHotelName);
        }
    }
}
