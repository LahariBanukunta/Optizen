package com.example.optizen.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScreenTimeAdapter extends RecyclerView.Adapter<ScreenTimeAdapter.ViewHolder> {

    private List<String> screenTimeData;

    public void setData(List<String> screenTimeData) {
        this.screenTimeData = screenTimeData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(com.example.optizen.R.layout.item_screen_time, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String screenTime = screenTimeData.get(position);
        holder.bind(screenTime);
    }

    @Override
    public int getItemCount() {
        return screenTimeData != null ? screenTimeData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewScreenTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewScreenTime = itemView.findViewById(com.example.optizen.R.id.textViewScreenTime);
        }

        public void bind(String screenTime) {
            textViewScreenTime.setText(screenTime);
        }
    }
}