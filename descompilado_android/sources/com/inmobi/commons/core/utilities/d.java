package com.inmobi.commons.core.utilities;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* JADX INFO: compiled from: NetworkUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    private static final String a = "d";

    @SuppressLint({"MissingPermission"})
    public static boolean a() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) com.inmobi.commons.a.a.b().getSystemService("connectivity")).getActiveNetworkInfo();
            return (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || b()) ? false : true;
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking network availability; ").append(e.getMessage());
            return false;
        }
    }

    private static boolean b() {
        try {
            PowerManager powerManager = (PowerManager) com.inmobi.commons.a.a.b().getSystemService("power");
            if (Build.VERSION.SDK_INT > 22) {
                return powerManager.isDeviceIdleMode();
            }
            return false;
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking idle mode; ").append(e.getMessage());
            return false;
        }
    }

    public static String a(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append(String.format(Locale.US, "%s=%s", a(entry.getKey()), a(entry.getValue())));
        }
        return sb.toString();
    }

    private static String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static void a(Map<String, String> map) {
        if (map != null) {
            HashMap map2 = new HashMap();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue() != null && entry.getValue().trim().length() != 0 && entry.getKey() != null && entry.getKey().trim().length() != 0) {
                    map2.put(entry.getKey().trim(), entry.getValue().trim());
                }
            }
            map.clear();
            map.putAll(map2);
        }
    }

    public static String a(String str, Map<String, String> map) {
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                str = str.replace(entry.getKey(), entry.getValue());
            }
        }
        return str;
    }

    public static byte[] a(@NonNull byte[] bArr) throws Throwable {
        GZIPInputStream gZIPInputStream;
        Throwable th;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        byte[] bArrA = null;
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                try {
                    bArrA = a((InputStream) gZIPInputStream);
                } catch (IOException e) {
                    e = e;
                    Logger.a(Logger.InternalLogLevel.DEBUG, a, "Failed to decompress response", e);
                }
            } catch (Throwable th2) {
                th = th2;
                a((Closeable) byteArrayInputStream);
                a((Closeable) gZIPInputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            gZIPInputStream = null;
        } catch (Throwable th3) {
            gZIPInputStream = null;
            th = th3;
            a((Closeable) byteArrayInputStream);
            a((Closeable) gZIPInputStream);
            throw th;
        }
        a((Closeable) byteArrayInputStream);
        a((Closeable) gZIPInputStream);
        return bArrA;
    }

    public static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (-1 != i) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
            } catch (Throwable th) {
                byteArrayOutputStream.close();
                throw th;
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
