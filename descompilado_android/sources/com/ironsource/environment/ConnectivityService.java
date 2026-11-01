package com.ironsource.environment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/* JADX INFO: loaded from: classes2.dex */
public class ConnectivityService {
    public static final String NETWORK_TYPE_3G = "3g";
    public static final String NETWORK_TYPE_WIFI = "wifi";

    public static String getConnectionType(Context context) {
        StringBuilder sb = new StringBuilder();
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            String typeName = activeNetworkInfo.getTypeName();
            int type = activeNetworkInfo.getType();
            if (type == 0) {
                sb.append(NETWORK_TYPE_3G);
            } else if (type == 1) {
                sb.append("wifi");
            } else {
                sb.append(typeName);
            }
        }
        return sb.toString();
    }

    public static boolean isConnectedWifi(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isConnectedMobile(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getNetworkOperator(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getNetworkMCC(Context context) {
        try {
            return context.getResources().getConfiguration().mcc;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getNetworkMNC(Context context) {
        try {
            return context.getResources().getConfiguration().mnc;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getSimOperator(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? telephonyManager.getSimOperator() : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getPhoneType(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getPhoneType();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
