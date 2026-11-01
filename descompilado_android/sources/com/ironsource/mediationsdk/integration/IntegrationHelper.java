package com.ironsource.mediationsdk.integration;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.adcolony.sdk.AdColonyAppOptions;
import com.ironsource.mediationsdk.IntegrationData;
import com.ironsource.mediationsdk.IronSourceObject;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.vungle.warren.network.VungleApiClient;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class IntegrationHelper {
    private static final String BANNER_COMPATIBILITY_VERSION = "4.3";
    private static final String SDK_COMPATIBILITY_VERSION = "4.1";
    private static final String TAG = "IntegrationHelper";

    public static void validateIntegration(Activity activity) {
        Log.i(TAG, "Verifying Integration:");
        validatePermissions(activity);
        for (String str : new String[]{"AdColony", AdColonyAppOptions.ADMOB, VungleApiClient.MANUFACTURER_AMAZON, "AppLovin", "Chartboost", "Facebook", AdColonyAppOptions.FYBER, "HyprMX", "InMobi", "Maio", "MediaBrix", AdColonyAppOptions.MOPUB, IronSourceConstants.SUPERSONIC_CONFIG_NAME, "Tapjoy", "UnityAds", "Vungle"}) {
            if (isAdapterValid(activity, str)) {
                if (str.equalsIgnoreCase(IronSourceConstants.SUPERSONIC_CONFIG_NAME)) {
                    Log.i(TAG, ">>>> IronSource - VERIFIED");
                } else {
                    Log.i(TAG, ">>>> " + str + " - VERIFIED");
                }
            } else if (str.equalsIgnoreCase(IronSourceConstants.SUPERSONIC_CONFIG_NAME)) {
                Log.e(TAG, ">>>> IronSource - NOT VERIFIED");
            } else {
                Log.e(TAG, ">>>> " + str + " - NOT VERIFIED");
            }
        }
        validateGooglePlayServices(activity);
    }

    private static boolean isAdapterValid(Activity activity, String str) {
        try {
            if (str.equalsIgnoreCase(IronSourceConstants.SUPERSONIC_CONFIG_NAME)) {
                Log.i(TAG, "--------------- IronSource  --------------");
            } else {
                Log.i(TAG, "--------------- " + str + " --------------");
            }
            String str2 = "com.ironsource.adapters." + str.toLowerCase() + "." + str + "Adapter";
            IntegrationData integrationData = getIntegrationData(activity, str2);
            if (integrationData == null || !verifyBannerAdapterVersion(integrationData)) {
                return false;
            }
            if (!str.equalsIgnoreCase(IronSourceConstants.SUPERSONIC_CONFIG_NAME) && !isAdapterVersionValid(integrationData)) {
                return false;
            }
            if (!str.equalsIgnoreCase(IronSourceConstants.SUPERSONIC_CONFIG_NAME)) {
                validateSDKVersion(str2);
            }
            boolean zIsActivitiesValid = isActivitiesValid(activity, integrationData.activities);
            if (!isExternalLibsValid(integrationData.externalLibs)) {
                zIsActivitiesValid = false;
            }
            if (!isServicesValid(activity, integrationData.services)) {
                zIsActivitiesValid = false;
            }
            if (!integrationData.validateWriteExternalStorage || Build.VERSION.SDK_INT > 18) {
                return zIsActivitiesValid;
            }
            if (activity.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", activity.getPackageName()) == 0) {
                Log.i(TAG, "android.permission.WRITE_EXTERNAL_STORAGE - VERIFIED");
                return zIsActivitiesValid;
            }
            Log.e(TAG, "android.permission.WRITE_EXTERNAL_STORAGE - MISSING");
            return false;
        } catch (Exception e) {
            Log.e(TAG, "isAdapterValid " + str, e);
            return false;
        }
    }

    private static boolean isServicesValid(Activity activity, String[] strArr) {
        if (strArr == null) {
            return true;
        }
        PackageManager packageManager = activity.getPackageManager();
        Log.i(TAG, "*** Services ***");
        boolean z = true;
        for (String str : strArr) {
            try {
                if (packageManager.queryIntentServices(new Intent(activity, Class.forName(str)), 65536).size() > 0) {
                    Log.i(TAG, str + " - VERIFIED");
                } else {
                    Log.e(TAG, str + " - MISSING");
                    z = false;
                }
            } catch (ClassNotFoundException unused) {
                Log.e(TAG, str + " - MISSING");
            }
        }
        return z;
    }

    private static boolean isExternalLibsValid(ArrayList<Pair<String, String>> arrayList) {
        boolean z = true;
        if (arrayList == null) {
            return true;
        }
        Log.i(TAG, "*** External Libraries ***");
        for (Pair<String, String> pair : arrayList) {
            try {
                Class.forName((String) pair.first);
                Log.i(TAG, ((String) pair.second) + " - VERIFIED");
            } catch (ClassNotFoundException unused) {
                z = false;
                Log.e(TAG, ((String) pair.second) + " - MISSING");
            }
        }
        return z;
    }

    private static boolean isActivitiesValid(Activity activity, String[] strArr) {
        if (strArr == null) {
            return true;
        }
        Log.i(TAG, "*** Activities ***");
        boolean z = true;
        for (String str : strArr) {
            try {
                if (activity.getPackageManager().queryIntentActivities(new Intent(activity, Class.forName(str)), 65536).size() > 0) {
                    Log.i(TAG, str + " - VERIFIED");
                } else {
                    Log.e(TAG, str + " - MISSING");
                    z = false;
                }
            } catch (ClassNotFoundException unused) {
                Log.e(TAG, str + " - MISSING");
            }
        }
        return z;
    }

    private static void validatePermissions(Activity activity) {
        Log.i(TAG, "*** Permissions ***");
        PackageManager packageManager = activity.getPackageManager();
        if (packageManager.checkPermission("android.permission.INTERNET", activity.getPackageName()) == 0) {
            Log.i(TAG, "android.permission.INTERNET - VERIFIED");
        } else {
            Log.e(TAG, "android.permission.INTERNET - MISSING");
        }
        if (packageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", activity.getPackageName()) == 0) {
            Log.i(TAG, "android.permission.ACCESS_NETWORK_STATE - VERIFIED");
        } else {
            Log.e(TAG, "android.permission.ACCESS_NETWORK_STATE - MISSING");
        }
    }

    private static boolean verifyBannerAdapterVersion(IntegrationData integrationData) {
        if ((!integrationData.name.equalsIgnoreCase("AppLovin") && !integrationData.name.equalsIgnoreCase(AdColonyAppOptions.ADMOB) && !integrationData.name.equalsIgnoreCase("Facebook") && !integrationData.name.equalsIgnoreCase(VungleApiClient.MANUFACTURER_AMAZON) && !integrationData.name.equalsIgnoreCase("InMobi") && !integrationData.name.equalsIgnoreCase(VungleApiClient.MANUFACTURER_AMAZON) && !integrationData.name.equalsIgnoreCase(AdColonyAppOptions.FYBER)) || integrationData.version.startsWith(BANNER_COMPATIBILITY_VERSION)) {
            return true;
        }
        Log.e(TAG, integrationData.name + " adapter " + integrationData.version + " is incompatible for showing banners with SDK version " + IronSourceUtils.getSDKVersion() + ", please update your adapter to version " + BANNER_COMPATIBILITY_VERSION + ".*");
        return false;
    }

    private static boolean isAdapterVersionValid(IntegrationData integrationData) {
        if (integrationData.version.startsWith(SDK_COMPATIBILITY_VERSION) || integrationData.version.startsWith(BANNER_COMPATIBILITY_VERSION)) {
            Log.i(TAG, "Adapter - VERIFIED");
            return true;
        }
        Log.e(TAG, integrationData.name + " adapter " + integrationData.version + " is incompatible with SDK version " + IronSourceUtils.getSDKVersion() + ", please update your adapter to version " + SDK_COMPATIBILITY_VERSION + ".*");
        return false;
    }

    private static IntegrationData getIntegrationData(Activity activity, String str) {
        try {
            IntegrationData integrationData = (IntegrationData) Class.forName(str).getMethod("getIntegrationData", Activity.class).invoke(null, activity);
            Log.i(TAG, "Adapter " + integrationData.version + " - VERIFIED");
            return integrationData;
        } catch (ClassNotFoundException unused) {
            Log.e(TAG, "Adapter - MISSING");
            return null;
        } catch (Exception unused2) {
            Log.e(TAG, "Adapter version - NOT VERIFIED");
            return null;
        }
    }

    private static void validateGooglePlayServices(final Activity activity) {
        new Thread() { // from class: com.ironsource.mediationsdk.integration.IntegrationHelper.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    Log.w(IntegrationHelper.TAG, "--------------- Google Play Services --------------");
                    if (activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData.containsKey("com.google.android.gms.version")) {
                        Log.i(IntegrationHelper.TAG, "Google Play Services - VERIFIED");
                        String advertiserId = IronSourceObject.getInstance().getAdvertiserId(activity);
                        if (!TextUtils.isEmpty(advertiserId)) {
                            Log.i(IntegrationHelper.TAG, "GAID is: " + advertiserId + " (use this for test devices)");
                        }
                    } else {
                        Log.e(IntegrationHelper.TAG, "Google Play Services - MISSING");
                    }
                } catch (Exception unused) {
                    Log.e(IntegrationHelper.TAG, "Google Play Services - MISSING");
                }
            }
        }.start();
    }

    private static void validateSDKVersion(String str) {
        try {
            Log.i(TAG, "SDK Version - " + ((String) Class.forName(str).getMethod("getAdapterSDKVersion", new Class[0]).invoke(null, new Object[0])));
        } catch (Exception unused) {
            Log.w("validateSDKVersion", "Unable to get SDK version");
        }
    }
}
