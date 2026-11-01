package com.fingersoft.liveops_sdk.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DeviceCapabilities {
    public static final String ANDROID_VERSION = "drdver";
    public static final String CPU_ARCHITECTURE = "cpu";
    public static final String DEVICE_MODEL = "device";
    public static final String DEVICE_RESOLUTION = "res";
    public static final String DEVICE_TYPE = "device_type";
    static Map<String, String> mCapabilities = new HashMap<String, String>() { // from class: com.fingersoft.liveops_sdk.utils.DeviceCapabilities.1
        {
            put("cpu", "");
            put("drdver", "");
            put("res", "");
            put("device", "");
            put("device_type", "");
        }
    };

    public static Map<String, String> getDeviceCapabilitiesAsMap() {
        return mCapabilities;
    }

    public static boolean isUnitySupported() {
        return mCapabilities.get("cpu").contains("armv7");
    }

    public static String getDeviceCapabilitiesAsRequestParams() {
        String str = "";
        boolean z = false;
        for (Map.Entry<String, String> entry : mCapabilities.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (z) {
                str = str + Constants.RequestParameters.AMPERSAND;
            }
            str = str + key + Constants.RequestParameters.EQUAL + value;
            z = true;
        }
        return str;
    }

    @SuppressLint({"NewApi"})
    public static void detectCapabilities(Activity activity) {
        int width;
        int height;
        mCapabilities.clear();
        try {
            mCapabilities.put("cpu", System.getProperty("os.arch").toLowerCase());
            mCapabilities.put("drdver", String.valueOf(Build.VERSION.SDK_INT));
            mCapabilities.put("device", getDeviceModel().replaceAll(" ", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR));
            Point point = new Point();
            WindowManager windowManager = activity.getWindowManager();
            if (Build.VERSION.SDK_INT >= 13) {
                windowManager.getDefaultDisplay().getSize(point);
                width = point.x;
                height = point.y;
            } else {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                width = defaultDisplay.getWidth();
                height = defaultDisplay.getHeight();
            }
            mCapabilities.put("res", width + "x" + height);
            mCapabilities.put("device_type", getDeviceType(activity));
        } catch (Exception e) {
            CommonUtils.log("Problem determining device capabilities");
            e.printStackTrace();
        }
    }

    public static String getDeviceType(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3 ? "tablet" : "phone";
    }

    static String getDeviceModel() {
        String upperCase = Build.MANUFACTURER.toUpperCase();
        String upperCase2 = Build.MODEL.toUpperCase();
        if (upperCase2.startsWith(upperCase)) {
            return upperCase2;
        }
        return upperCase + " " + upperCase2;
    }
}
