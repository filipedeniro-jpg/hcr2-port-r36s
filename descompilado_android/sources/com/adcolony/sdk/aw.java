package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.appsflyer.share.Constants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.CRC32;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class aw {
    static final int a = 128;
    static ExecutorService b = Executors.newSingleThreadExecutor();

    aw() {
    }

    static boolean a(String str) {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return false;
        }
        try {
            activityC.getApplication().getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    static boolean a() {
        try {
            l lVarA = com.adcolony.sdk.a.a();
            File file = new File(lVarA.o().g() + "026ae9c9824b3e483fa6c71fa88f57ae27816141");
            StringBuilder sb = new StringBuilder();
            sb.append(lVarA.o().g());
            sb.append("7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5");
            return lVarA.j().a(file) && lVarA.j().a(new File(sb.toString()));
        } catch (Exception unused) {
            new aa.a().a("Unable to delete controller or launch response.").a(aa.h);
            return false;
        }
    }

    static String b() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return "1.0";
        }
        try {
            return activityC.getPackageManager().getPackageInfo(activityC.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            new aa.a().a("Failed to retrieve package info.").a(aa.h);
            return "1.0";
        }
    }

    static int c() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return 0;
        }
        try {
            return activityC.getPackageManager().getPackageInfo(activityC.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            new aa.a().a("Failed to retrieve package info.").a(aa.h);
            return 0;
        }
    }

    static String d() {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return "";
        }
        PackageManager packageManager = activityC.getApplication().getPackageManager();
        try {
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(activityC.getPackageName(), 0));
            return applicationLabel == null ? "" : applicationLabel.toString();
        } catch (Exception unused) {
            new aa.a().a("Failed to retrieve application label.").a(aa.h);
            return "";
        }
    }

    static int b(String str) {
        CRC32 crc32 = new CRC32();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            crc32.update(str.charAt(i));
        }
        return (int) crc32.getValue();
    }

    static String c(String str) {
        try {
            return bb.a(str);
        } catch (Exception unused) {
            return null;
        }
    }

    static String e() {
        return UUID.randomUUID().toString();
    }

    static JSONArray a(int i) {
        JSONArray jSONArrayB = y.b();
        for (int i2 = 0; i2 < i; i2++) {
            y.a(jSONArrayB, e());
        }
        return jSONArrayB;
    }

    static boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        Arrays.sort(strArr);
        Arrays.sort(strArr2);
        return Arrays.equals(strArr, strArr2);
    }

    static boolean a(Runnable runnable) {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return false;
        }
        activityC.runOnUiThread(runnable);
        return true;
    }

    static double f() {
        double dCurrentTimeMillis = System.currentTimeMillis();
        Double.isNaN(dCurrentTimeMillis);
        return dCurrentTimeMillis / 1000.0d;
    }

    static boolean d(String str) {
        if (str != null && str.length() <= 128) {
            return true;
        }
        new aa.a().a("String must be non-null and the max length is 128 characters.").a(aa.e);
        return false;
    }

    static boolean a(AudioManager audioManager) {
        if (audioManager == null) {
            new aa.a().a("isAudioEnabled() called with a null AudioManager").a(aa.h);
            return false;
        }
        try {
            return audioManager.getStreamVolume(3) > 0;
        } catch (Exception e) {
            new aa.a().a("Exception occurred when accessing AudioManager.getStreamVolume: ").a(e.toString()).a(aa.h);
            return false;
        }
    }

    static double b(AudioManager audioManager) {
        if (audioManager == null) {
            new aa.a().a("getAudioVolume() called with a null AudioManager").a(aa.h);
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        try {
            double streamVolume = audioManager.getStreamVolume(3);
            double streamMaxVolume = audioManager.getStreamMaxVolume(3);
            if (streamMaxVolume == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }
            Double.isNaN(streamVolume);
            Double.isNaN(streamMaxVolume);
            return streamVolume / streamMaxVolume;
        } catch (Exception e) {
            new aa.a().a("Exception occurred when accessing AudioManager: ").a(e.toString()).a(aa.h);
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    static AudioManager a(Context context) {
        if (context == null) {
            new aa.a().a("getAudioManager called with a null Context").a(aa.h);
            return null;
        }
        return (AudioManager) context.getSystemService("audio");
    }

    static void e(String str) {
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.isDirectory()) {
                    new aa.a().a(">").a(file.getAbsolutePath()).a(aa.b);
                    e(file.getAbsolutePath());
                } else {
                    new aa.a().a(file.getAbsolutePath()).a(aa.b);
                }
            }
        }
    }

    static String a(double d, int i) {
        StringBuilder sb = new StringBuilder();
        a(d, i, sb);
        return sb.toString();
    }

    static void a(double d, int i, StringBuilder sb) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            sb.append(d);
            return;
        }
        if (d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d = -d;
            sb.append('-');
        }
        if (i == 0) {
            sb.append(Math.round(d));
            return;
        }
        long jPow = (long) Math.pow(10.0d, i);
        double d2 = jPow;
        Double.isNaN(d2);
        long jRound = Math.round(d * d2);
        sb.append(jRound / jPow);
        sb.append('.');
        long j = jRound % jPow;
        if (j == 0) {
            for (int i2 = 0; i2 < i; i2++) {
                sb.append('0');
            }
            return;
        }
        for (long j2 = j * 10; j2 < jPow; j2 *= 10) {
            sb.append('0');
        }
        sb.append(j);
    }

    static String f(String str) {
        return str == null ? "" : URLDecoder.decode(str);
    }

    static String a(@NonNull Activity activity) {
        try {
            return activity.getPackageName();
        } catch (Exception unused) {
            return "unknown";
        }
    }

    static String a(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    static int g(String str) {
        try {
            return (int) Long.parseLong(str, 16);
        } catch (NumberFormatException unused) {
            new aa.a().a("Unable to parse '").a(str).a("' as a color.").a(aa.f);
            return ViewCompat.MEASURED_STATE_MASK;
        }
    }

    static int b(Activity activity) {
        int identifier;
        if (activity != null && (identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
            return activity.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    static boolean g() {
        Activity activityC = com.adcolony.sdk.a.c();
        return activityC != null && Build.VERSION.SDK_INT >= 24 && activityC.isInMultiWindowMode();
    }

    static boolean a(String str, File file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        try {
                            int i = fileInputStream.read(bArr);
                            if (i <= 0) {
                                break;
                            }
                            messageDigest.update(bArr, 0, i);
                        } catch (IOException e) {
                            throw new RuntimeException("Unable to process file for MD5", e);
                        }
                    } catch (Throwable th) {
                        fileInputStream.close();
                        throw th;
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException unused) {
                        new aa.a().a("Exception on closing MD5 input stream").a(aa.h);
                    }
                    throw th;
                }
                boolean zEquals = str.equals(String.format("%40s", new BigInteger(1, messageDigest.digest()).toString(16)).replace(' ', '0'));
                try {
                    fileInputStream.close();
                } catch (IOException unused2) {
                    new aa.a().a("Exception on closing MD5 input stream").a(aa.h);
                }
                return zEquals;
            } catch (FileNotFoundException unused3) {
                new aa.a().a("Exception while getting FileInputStream").a(aa.h);
                return false;
            }
        } catch (NoSuchAlgorithmException unused4) {
            new aa.a().a("Exception while getting Digest").a(aa.h);
            return false;
        }
    }

    static Date h(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        try {
            try {
                try {
                    return simpleDateFormat.parse(str);
                } catch (Exception unused) {
                    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(str);
                }
            } catch (Exception unused2) {
                return null;
            }
        } catch (Exception unused3) {
            return new SimpleDateFormat("yyyy-MM-dd").parse(str);
        }
    }

    static String a(JSONArray jSONArray) throws JSONException {
        String str = "";
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                str = str + ",";
            }
            switch (jSONArray.getInt(i)) {
                case 1:
                    str = str + "MO";
                    break;
                case 2:
                    str = str + "TU";
                    break;
                case 3:
                    str = str + "WE";
                    break;
                case 4:
                    str = str + "TH";
                    break;
                case 5:
                    str = str + "FR";
                    break;
                case 6:
                    str = str + "SA";
                    break;
                case 7:
                    str = str + "SU";
                    break;
            }
        }
        return str;
    }

    static String b(JSONArray jSONArray) throws JSONException {
        String str = "";
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                str = str + ",";
            }
            str = str + jSONArray.getInt(i);
        }
        return str;
    }

    static boolean a(Intent intent, boolean z) {
        try {
            Activity activityC = com.adcolony.sdk.a.c();
            if (activityC == null) {
                return false;
            }
            AdColonyInterstitial adColonyInterstitialU = com.adcolony.sdk.a.a().u();
            if (adColonyInterstitialU != null && adColonyInterstitialU.g()) {
                adColonyInterstitialU.h().f();
            }
            if (z) {
                activityC.startActivity(Intent.createChooser(intent, "Handle this via..."));
                return true;
            }
            activityC.startActivity(intent);
            return true;
        } catch (Exception e) {
            new aa.a().a(e.toString()).a(aa.f);
            return false;
        }
    }

    static boolean a(Intent intent) {
        return a(intent, false);
    }

    static boolean a(final String str, final int i) {
        final Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return false;
        }
        a(new Runnable() { // from class: com.adcolony.sdk.aw.1
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(activityC, str, i).show();
            }
        });
        return true;
    }

    private static void j(String str) {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return;
        }
        try {
            InputStream inputStreamOpen = activityC.getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(com.adcolony.sdk.a.a().o().d() + str);
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i != -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    inputStreamOpen.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            new aa.a().a("Failed copy hardcoded ad unit file named: ").a(str).a(" with error: ").a(e.getMessage()).a(aa.h);
        }
    }

    public static void i(String str) {
        Activity activityC = com.adcolony.sdk.a.c();
        if (activityC == null) {
            return;
        }
        try {
            String[] list = activityC.getAssets().list(str);
            if (list.length == 0) {
                j(str);
                return;
            }
            File file = new File(com.adcolony.sdk.a.a().o().d() + str);
            if (!file.exists()) {
                file.mkdir();
            }
            for (String str2 : list) {
                i(str + Constants.URL_PATH_DELIMITER + str2);
            }
        } catch (IOException e) {
            new aa.a().a("Failed copy hardcoded ad unit with error: ").a(e.getMessage()).a(aa.h);
        }
    }

    static int a(ar arVar) {
        int i = 0;
        try {
            Activity activityC = com.adcolony.sdk.a.c();
            if (activityC != null) {
                int i2 = (int) (activityC.getPackageManager().getPackageInfo(activityC.getPackageName(), 0).lastUpdateTime / 1000);
                boolean z = true;
                if (new File(arVar.g() + "AppVersion").exists()) {
                    if (y.c(y.c(arVar.g() + "AppVersion"), "last_update") != i2) {
                        i = 1;
                    } else {
                        z = false;
                    }
                } else {
                    i = 2;
                }
                if (z) {
                    JSONObject jSONObjectA = y.a();
                    y.b(jSONObjectA, "last_update", i2);
                    y.h(jSONObjectA, arVar.g() + "AppVersion");
                }
            }
        } catch (Exception unused) {
        }
        return i;
    }

    static JSONArray b(Context context) {
        JSONArray jSONArrayB = y.b();
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                if (packageInfo.requestedPermissions != null) {
                    jSONArrayB = y.b();
                    for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                        jSONArrayB.put(packageInfo.requestedPermissions[i]);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return jSONArrayB;
    }

    static class a {
        double a;
        double b = System.currentTimeMillis();

        a(double d) {
            a(d);
        }

        void a() {
            a(this.a);
        }

        void a(double d) {
            this.a = d;
            double dCurrentTimeMillis = System.currentTimeMillis();
            Double.isNaN(dCurrentTimeMillis);
            this.b = (dCurrentTimeMillis / 1000.0d) + this.a;
        }

        boolean b() {
            return c() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        double c() {
            double dCurrentTimeMillis = System.currentTimeMillis();
            Double.isNaN(dCurrentTimeMillis);
            double d = this.b - (dCurrentTimeMillis / 1000.0d);
            return d <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : d;
        }

        public String toString() {
            return aw.a(c(), 2);
        }
    }
}
