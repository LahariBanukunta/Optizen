// MostUsedAppsAdapter.java
package com.example.optizen.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.optizen.databinding.ItemMostUsedAppBinding;

import java.util.List;

public class MostUsedAppsAdapter extends RecyclerView.Adapter<MostUsedAppsAdapter.ViewHolder> {

    private List<AppData> mostUsedApps;

    public void setData(List<AppData> mostUsedApps) {
        this.mostUsedApps = mostUsedApps;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMostUsedAppBinding binding = ItemMostUsedAppBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mostUsedApps != null && !mostUsedApps.isEmpty()) {
            holder.bind(mostUsedApps.get(position));
        } else {
            // If no apps are used, display message
            holder.bind(null);
        }
    }

    @Override
    public int getItemCount() {
        // Return 1 if no apps are used to display message
        return mostUsedApps != null && !mostUsedApps.isEmpty() ? mostUsedApps.size() : 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemMostUsedAppBinding binding;

        public ViewHolder(@NonNull ItemMostUsedAppBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(AppData appData) {
            if (appData == null) {
                // Display a message when there are no apps to display
                binding.textAppName.setText("No apps used");
                binding.textAppName.setVisibility(View.VISIBLE);
                binding.imageAppIcon.setVisibility(View.GONE);
                binding.progressUsage.setVisibility(View.GONE);
            } else {
                // Display app information
                binding.textAppName.setText(appData.getAppName());
                binding.textAppName.setVisibility(View.VISIBLE);

                // Set horizontal bar progress (assuming you have a ProgressBar in the layout)
                // Set the max progress value (assuming 100 for percentage)
                binding.progressUsage.setMax(100);

                // Calculate the progress value based on the app's usage percentage
                int progress;
                if (appData.getMaxUsageTime() > 0) {
                    progress = (int) ((appData.getUsageTime() / (double) appData.getMaxUsageTime()) * 100);
                } else {
                    // Handle case when max usage time is 0
                    progress = 0;
                }
                binding.progressUsage.setProgress(progress);
            }
        }

    }
}
