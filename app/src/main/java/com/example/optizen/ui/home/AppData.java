package com.example.optizen.ui.home;

import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;

public class AppData {
    private String packageName;
    private String appName;
    private Drawable appIcon;
    private long usageTime;

    public AppData(String packageName, String appName, Drawable appIcon, long usageTime) {
        this.packageName = packageName;
        this.appName = appName;
        this.appIcon = appIcon;
        this.usageTime = usageTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getAppName() {
        return appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public long getUsageTime() {
        return usageTime;
    }

    public String getFormattedUsageTime() {
        // Format usage time in hours and minutes
        return DateUtils.formatElapsedTime(usageTime / 1000);
    }

    public long getMaxUsageTime() {
        // Return the maximum possible usage time (assuming 24 hours in milliseconds)
        return 24 * 60 * 60 * 1000;
    }
}
