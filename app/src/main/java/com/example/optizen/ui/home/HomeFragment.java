package com.example.optizen.ui.home;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.optizen.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MostUsedAppsAdapter mostUsedAppsAdapter;
    private List<AppData> mostUsedAppsData;

    // Integer constants for resource IDs
    private static final int RECYCLER_MOST_USED_APPS_ID = com.example.optizen.R.id.recycler_most_used_apps;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mostUsedAppsData = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Using the integer constant for resource ID
        RecyclerView recyclerViewMostUsedApps = root.findViewById(RECYCLER_MOST_USED_APPS_ID);
        mostUsedAppsAdapter = new MostUsedAppsAdapter();
        recyclerViewMostUsedApps.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMostUsedApps.setAdapter(mostUsedAppsAdapter);

        updateMostUsedAppsData();

        return root;
    }

    private void updateMostUsedAppsData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            UsageStatsManager usageStatsManager = (UsageStatsManager) requireContext().getSystemService(Context.USAGE_STATS_SERVICE);
            if (usageStatsManager != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                long startTime = calendar.getTimeInMillis();
                long endTime = System.currentTimeMillis();
                Map<String, UsageStats> usageStatsMap = usageStatsManager.queryAndAggregateUsageStats(startTime, endTime);

                List<AppData> appDataList = new ArrayList<>();
                for (Map.Entry<String, UsageStats> entry : usageStatsMap.entrySet()) {
                    AppData appData = new AppData(entry.getKey(), entry.getValue().getPackageName(), null, entry.getValue().getTotalTimeInForeground());
                    appDataList.add(appData);
                }

                Collections.sort(appDataList, (appData1, appData2) -> Long.compare(appData2.getUsageTime(), appData1.getUsageTime()));

                mostUsedAppsData.addAll(appDataList.subList(0, Math.min(appDataList.size(), 4)));

                mostUsedAppsAdapter.setData(mostUsedAppsData);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
