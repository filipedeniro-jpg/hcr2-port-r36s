package com.inmobi.commons.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.drive.DriveFile;
import com.inmobi.commons.core.utilities.c;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: SdkContext.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    private static final String a = "a";
    private static final boolean b = false;
    private static Context c = null;
    private static String d = "";
    private static String e = "";
    private static AtomicBoolean f = new AtomicBoolean();
    private static boolean g = false;

    public static void a(Context context, String str) {
        if (a()) {
            return;
        }
        c = context.getApplicationContext();
        e = str;
        f.set(true);
        if (Build.VERSION.SDK_INT < 17) {
            try {
                d = new WebView(context).getSettings().getUserAgentString();
            } catch (Exception e2) {
                c = null;
                new StringBuilder("SDK encountered an unexpected error in SdkContext.fetchWebviewUserAgent().handler() method; ").append(e2.getMessage());
            }
        }
        h();
    }

    public static boolean a() {
        return c != null;
    }

    @Nullable
    public static Context b() {
        return c;
    }

    public static void c() {
        c = null;
    }

    public static void a(boolean z) {
        g = z;
    }

    public static boolean d() {
        return g;
    }

    public static String e() {
        return e;
    }

    public static String f() {
        if (TextUtils.isEmpty(d) && Build.VERSION.SDK_INT >= 17) {
            d = c(c);
        }
        return d;
    }

    public static boolean g() {
        return f.get();
    }

    public static void b(boolean z) {
        f.set(z);
    }

    public static File a(Context context) {
        return new File(context.getFilesDir(), "im_cached_content");
    }

    public static void a(File file) {
        c.a(file);
    }

    public static boolean b(Context context, String str) {
        if (context == null) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (Build.VERSION.SDK_INT < 23) {
            return packageManager.checkPermission(str, packageManager.getNameForUid(Binder.getCallingUid())) == 0;
        }
        return c(context, str);
    }

    private static boolean c(Context context, String str) {
        if (context != null && str != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                if (packageInfo.requestedPermissions != null) {
                    for (String str2 : packageInfo.requestedPermissions) {
                        if (str2.equals(str)) {
                            return true;
                        }
                    }
                }
            } catch (Exception e2) {
                StringBuilder sb = new StringBuilder("Could not check manifest for permission:");
                sb.append(str);
                sb.append(" Error:");
                sb.append(e2.getLocalizedMessage());
            }
        }
        return false;
    }

    public static void a(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.setFlags(DriveFile.MODE_READ_ONLY);
        }
        context.startActivity(intent);
    }

    public static File a(String str) {
        h();
        File fileA = a(c);
        int length = str.length() / 2;
        return new File(fileA, String.valueOf(str.substring(0, length).hashCode() & Integer.MAX_VALUE) + String.valueOf(str.substring(length).hashCode() & Integer.MAX_VALUE));
    }

    public static void b(@NonNull Context context) {
        try {
            File file = new File(context.getCacheDir(), "im_cached_content");
            if (file.exists()) {
                c.a(file);
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in clearOldMediaCacheDirectory; ").append(e2.getMessage());
        }
    }

    private static void h() {
        File fileA = a(c);
        if (fileA.mkdir()) {
            return;
        }
        fileA.isDirectory();
    }

    @TargetApi(17)
    private static String c(Context context) {
        try {
            if (b) {
                throw new Exception("android.util.AndroidRuntimeException: android.content.pm.PackageManager$NameNotFoundException: com.google.android.webview");
            }
            return WebSettings.getDefaultUserAgent(context.getApplicationContext());
        } catch (Throwable th) {
            new StringBuilder("SDK encountered an unexpected error in getting user agent information; ").append(th.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(th));
            try {
                String property = System.getProperty("http.agent");
                return property == null ? "" : property;
            } catch (Exception e2) {
                new StringBuilder("SDK encountered an unexpected error in getting property of http.agent; ").append(e2.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                return "";
            }
        }
    }
}
