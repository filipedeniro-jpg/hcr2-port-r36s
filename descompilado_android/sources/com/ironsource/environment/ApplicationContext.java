package com.ironsource.environment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ApplicationContext {
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static int getAppOrientation(Activity activity) {
        return activity.getRequestedOrientation();
    }

    public static String getDiskCacheDirPath(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            return cacheDir.getPath();
        }
        return null;
    }

    public static boolean isValidPermission(Context context, String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            if (packageInfo.requestedPermissions == null) {
                return false;
            }
            boolean zEquals = false;
            for (int i = 0; i < packageInfo.requestedPermissions.length && !zEquals; i++) {
                try {
                    zEquals = str.equals(packageInfo.requestedPermissions[i]);
                } catch (Exception e) {
                    e = e;
                    z = zEquals;
                    e.printStackTrace();
                    return z;
                }
            }
            return zEquals;
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static boolean isPermissionGranted(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static JSONObject getPermissions(Context context, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                int i = 0;
                if (jSONArray.length() == 0) {
                    while (i < packageInfo.requestedPermissions.length) {
                        jSONObject.put(packageInfo.requestedPermissions[i], (packageInfo.requestedPermissionsFlags[i] & 2) != 0 ? "Granted" : "Rejected");
                        i++;
                    }
                } else {
                    List listAsList = Arrays.asList(packageInfo.requestedPermissions);
                    while (i < jSONArray.length()) {
                        String string = jSONArray.getString(i);
                        int iIndexOf = listAsList.indexOf(string);
                        if (iIndexOf != -1) {
                            jSONObject.put(string, (packageInfo.requestedPermissionsFlags[iIndexOf] & 2) != 0 ? "Granted" : "Rejected");
                        } else {
                            jSONObject.put(string, "notFoundInManifest");
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    static PackageInfo getAppPackageInfo(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(getPackageName(context), 0);
    }

    public static long getFirstInstallTime(Context context) {
        try {
            return getAppPackageInfo(context).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static long getLastUpdateTime(Context context) {
        try {
            return getAppPackageInfo(context).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static String getApplicationVersionName(Context context) {
        try {
            return getAppPackageInfo(context).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getPublisherApplicationVersion(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getInstallerPackageName(Context context) {
        String installerPackageName;
        try {
            installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        } catch (Throwable unused) {
            installerPackageName = null;
        }
        return TextUtils.isEmpty(installerPackageName) ? "" : installerPackageName;
    }
}
