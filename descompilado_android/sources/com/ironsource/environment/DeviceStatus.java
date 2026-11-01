package com.ironsource.environment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceStatus {
    private static final String CACHED_UUID_KEY = "cachedUUID";
    private static final String DEVICE_OS = "android";
    private static final String GOOGLE_PLAY_SERVICES_CLASS_NAME = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
    private static final String GOOGLE_PLAY_SERVICES_GET_AID_INFO_METHOD_NAME = "getAdvertisingIdInfo";
    private static final String GOOGLE_PLAY_SERVICES_GET_AID_METHOD_NAME = "getId";
    private static final String GOOGLE_PLAY_SERVICES_IS_LIMITED_AD_TRACKING_METHOD_NAME = "isLimitAdTrackingEnabled";
    private static final String MEDIATION_SHARED_PREFS = "Mediation_Shared_Preferences";
    public static final String UUID_ENABLED = "uuidEnabled";
    private static String uniqueId;

    public static String getDeviceOs() {
        return "android";
    }

    public static long getDeviceLocalTime() {
        return Calendar.getInstance(TimeZone.getDefault()).getTime().getTime();
    }

    public static int getDeviceTimeZoneOffsetInMinutes() {
        return -(TimeZone.getDefault().getOffset(getDeviceLocalTime()) / 60000);
    }

    public static String[] getAdvertisingIdInfo(Context context) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        Class<?> cls = Class.forName(GOOGLE_PLAY_SERVICES_CLASS_NAME);
        Object objInvoke = cls.getMethod(GOOGLE_PLAY_SERVICES_GET_AID_INFO_METHOD_NAME, Context.class).invoke(cls, context);
        Method method = objInvoke.getClass().getMethod(GOOGLE_PLAY_SERVICES_GET_AID_METHOD_NAME, new Class[0]);
        Method method2 = objInvoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]);
        return new String[]{method.invoke(objInvoke, new Object[0]).toString(), "" + ((Boolean) method2.invoke(objInvoke, new Object[0])).booleanValue()};
    }

    public static String getDeviceLanguage(Context context) throws Exception {
        return context.getResources().getConfiguration().locale.getLanguage();
    }

    private static long getFreeStorageInBytes(File file) {
        long availableBlocksLong;
        long blockSizeLong;
        StatFs statFs = new StatFs(file.getPath());
        if (Build.VERSION.SDK_INT < 19) {
            availableBlocksLong = statFs.getAvailableBlocks();
            blockSizeLong = statFs.getBlockSize();
        } else {
            availableBlocksLong = statFs.getAvailableBlocksLong();
            blockSizeLong = statFs.getBlockSizeLong();
        }
        return (availableBlocksLong * blockSizeLong) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    public static boolean isExternalMemoryAvailableWritable() {
        return "mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable();
    }

    public static String getMobileCarrier(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
    }

    public static String getAndroidOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static int getAndroidAPIVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getDeviceOEM() {
        return Build.MANUFACTURER;
    }

    public static boolean isRootedDevice() {
        return findBinary("su");
    }

    private static boolean findBinary(String str) {
        try {
            for (String str2 : new String[]{"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"}) {
                if (new File(str2 + str).exists()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isRTL(Context context) {
        return Build.VERSION.SDK_INT >= 17 && context.getResources().getConfiguration().getLayoutDirection() == 1;
    }

    public static int getApplicationRotation(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    public static float getSystemVolumePercent(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        return audioManager.getStreamVolume(3) / audioManager.getStreamMaxVolume(3);
    }

    public static int getDisplayWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getDisplayHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getDeviceWidth() {
        return getDisplayWidth();
    }

    public static int getDeviceHeight() {
        return getDisplayHeight();
    }

    public static int getActivityRequestedOrientation(Context context) {
        if (context instanceof Activity) {
            return ((Activity) context).getRequestedOrientation();
        }
        return -1;
    }

    public static int getDeviceDefaultOrientation(Context context) {
        int applicationRotation = getApplicationRotation(context);
        int deviceOrientation = getDeviceOrientation(context);
        return (((applicationRotation == 0 || applicationRotation == 2) && deviceOrientation == 2) || ((applicationRotation == 1 || applicationRotation == 3) && deviceOrientation == 1)) ? 2 : 1;
    }

    public static int getDeviceOrientation(Context context) {
        return context.getResources().getConfiguration().orientation;
    }

    public static float getDeviceDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static List<ApplicationInfo> getInstalledApplications(Context context) {
        return context.getPackageManager().getInstalledApplications(0);
    }

    public static boolean isDeviceOrientationLocked(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation", 0) != 1;
    }

    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    public static String getInternalCacheDirPath(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            return cacheDir.getPath();
        }
        return null;
    }

    public static long getAvailableInternalMemorySizeInMegaBytes() {
        return getFreeStorageInBytes(Environment.getDataDirectory());
    }

    public static long getAvailableMemorySizeInMegaBytes(String str) {
        return getFreeStorageInBytes(new File(str));
    }

    public static long getAvailableExternalMemorySizeInMegaBytes() {
        if (isExternalMemoryAvailableWritable()) {
            return getFreeStorageInBytes(Environment.getExternalStorageDirectory());
        }
        return 0L;
    }

    @TargetApi(19)
    public static boolean isImmersiveSupported(Activity activity) {
        int systemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
        return (systemUiVisibility | 4096) == systemUiVisibility || (systemUiVisibility | 2048) == systemUiVisibility;
    }

    public static int getBatteryLevel(Context context) {
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("level", -1) : 0;
            int intExtra2 = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("scale", -1) : 0;
            if (intExtra == -1 || intExtra2 == -1) {
                return -1;
            }
            return (int) ((intExtra / intExtra2) * 100.0f);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static synchronized String getOrGenerateOnceUniqueIdentifier(Context context) {
        if (!TextUtils.isEmpty(uniqueId)) {
            return uniqueId;
        }
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(MEDIATION_SHARED_PREFS, 0);
            if (sharedPreferences.getBoolean(UUID_ENABLED, true)) {
                String string = sharedPreferences.getString(CACHED_UUID_KEY, "");
                if (TextUtils.isEmpty(string)) {
                    uniqueId = UUID.randomUUID().toString();
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString(CACHED_UUID_KEY, uniqueId);
                    editorEdit.apply();
                } else {
                    uniqueId = string;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueId;
    }

    private static boolean isSystemPackage(ResolveInfo resolveInfo) {
        return (resolveInfo.activityInfo.applicationInfo.flags & 1) != 0;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x003e A[Catch: Exception -> 0x005d, TRY_LEAVE, TryCatch #0 {Exception -> 0x005d, blocks: (B:7:0x0037, B:10:0x003e), top: B:16:0x0037 }] */
    public static JSONObject getAppsInstallTime(Context context, boolean z) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        JSONObject jSONObject = new JSONObject();
        PackageManager packageManager = context.getPackageManager();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        for (int i = 0; i < listQueryIntentActivities.size(); i++) {
            ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
            if (!z) {
                try {
                    if (!isSystemPackage(resolveInfo)) {
                        String str = simpleDateFormat.format(new Date(packageManager.getPackageInfo(resolveInfo.activityInfo.packageName, 4096).firstInstallTime));
                        jSONObject.put(str, jSONObject.optInt(str, 0) + 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String str2 = simpleDateFormat.format(new Date(packageManager.getPackageInfo(resolveInfo.activityInfo.packageName, 4096).firstInstallTime));
                jSONObject.put(str2, jSONObject.optInt(str2, 0) + 1);
            }
        }
        return jSONObject;
    }
}
