package com.inmobi.commons.core.utilities.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: AppInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final String d = "a";
    private static a e;
    private static Object f = new Object();
    public String a;
    public Map<String, String> b = new HashMap();
    public int c;
    private String g;
    private String h;
    private String i;

    private a() {
        Context contextB = com.inmobi.commons.a.a.b();
        try {
            PackageManager packageManager = contextB.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(contextB.getPackageName(), 128);
            if (applicationInfo != null) {
                this.g = applicationInfo.packageName;
                this.h = applicationInfo.loadLabel(packageManager).toString();
                this.a = packageManager.getInstallerPackageName(this.g);
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(contextB.getPackageName(), 128);
            String string = null;
            if (packageInfo != null && ((string = packageInfo.versionName) == null || string.equals(""))) {
                if (Build.VERSION.SDK_INT < 28) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(packageInfo.versionCode);
                    string = sb.toString();
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(packageInfo.getLongVersionCode());
                    string = sb2.toString();
                }
            }
            if (string != null && !string.equals("")) {
                this.i = string;
            }
        } catch (Exception unused) {
        }
        this.c = b();
        this.b.put("u-appbid", this.g);
        this.b.put("u-appdnm", this.h);
        this.b.put("u-appver", this.i);
        this.b.put("u-appsecure", Integer.toString(this.c));
    }

    public static a a() {
        a aVar = e;
        if (aVar == null) {
            synchronized (f) {
                aVar = e;
                if (aVar == null) {
                    aVar = new a();
                    e = aVar;
                }
            }
        }
        return aVar;
    }

    @SuppressLint({"NewApi"})
    private static int b() {
        try {
            return (Build.VERSION.SDK_INT < 23 || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) ? 0 : 1;
        } catch (Exception unused) {
            return 2;
        }
    }
}
