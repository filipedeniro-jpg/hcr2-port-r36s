package net.hockeyapp.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import com.my.target.i;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.File;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes2.dex */
public class Constants {
    public static String ANDROID_VERSION = null;
    public static String APP_PACKAGE = null;
    public static String APP_VERSION = null;
    public static String APP_VERSION_NAME = null;
    public static final String BASE_URL = "https://sdk.hockeyapp.net/";
    public static String CRASH_IDENTIFIER = null;
    public static String FILES_PATH = null;
    public static String PHONE_MANUFACTURER = null;
    public static String PHONE_MODEL = null;
    public static final String SDK_NAME = "HockeySDK";
    public static final String SDK_VERSION = "3.6.0";
    public static final String TAG = "HockeyApp";
    public static final int UPDATE_PERMISSIONS_REQUEST = 1;

    public static void loadFromContext(Context context) {
        ANDROID_VERSION = Build.VERSION.RELEASE;
        PHONE_MODEL = Build.MODEL;
        PHONE_MANUFACTURER = Build.MANUFACTURER;
        loadFilesPath(context);
        loadPackageData(context);
        loadCrashIdentifier(context);
    }

    public static File getHockeyAppStorageDir() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + com.appsflyer.share.Constants.URL_PATH_DELIMITER + "HockeyApp");
        file.mkdirs();
        return file;
    }

    private static void loadFilesPath(Context context) {
        if (context != null) {
            try {
                File filesDir = context.getFilesDir();
                if (filesDir != null) {
                    FILES_PATH = filesDir.getAbsolutePath();
                }
            } catch (Exception e) {
                Log.e("HockeyApp", "Exception thrown when accessing the files dir:");
                e.printStackTrace();
            }
        }
    }

    private static void loadPackageData(Context context) {
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                APP_PACKAGE = packageInfo.packageName;
                APP_VERSION = "" + packageInfo.versionCode;
                APP_VERSION_NAME = packageInfo.versionName;
                int iLoadBuildNumber = loadBuildNumber(context, packageManager);
                if (iLoadBuildNumber == 0 || iLoadBuildNumber <= packageInfo.versionCode) {
                    return;
                }
                APP_VERSION = "" + iLoadBuildNumber;
            } catch (Exception e) {
                Log.e("HockeyApp", "Exception thrown when accessing the package info:");
                e.printStackTrace();
            }
        }
    }

    private static int loadBuildNumber(Context context, PackageManager packageManager) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getInt("buildNumber", 0);
            }
        } catch (Exception e) {
            Log.e("HockeyApp", "Exception thrown when accessing the application info:");
            e.printStackTrace();
        }
        return 0;
    }

    private static void loadCrashIdentifier(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), i.ANDROID_ID);
        if (APP_PACKAGE == null || string == null) {
            return;
        }
        String str = APP_PACKAGE + ":" + string + ":" + createSalt(context);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
            byte[] bytes = str.getBytes("UTF-8");
            messageDigest.update(bytes, 0, bytes.length);
            CRASH_IDENTIFIER = bytesToHex(messageDigest.digest());
        } catch (Throwable unused) {
        }
    }

    private static String createSalt(Context context) {
        String string;
        String str = "HA" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.PRODUCT.length() % 10);
        if (Build.VERSION.SDK_INT >= 9) {
            try {
                string = Build.class.getField("SERIAL").get(null).toString();
            } catch (Throwable unused) {
                string = "";
            }
        } else {
            string = "";
        }
        return str + ":" + string;
    }

    private static String bytesToHex(byte[] bArr) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = charArray[i2 >>> 4];
            cArr[i3 + 1] = charArray[i2 & 15];
        }
        return new String(cArr).replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
    }
}
