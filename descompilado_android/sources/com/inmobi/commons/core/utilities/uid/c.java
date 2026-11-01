package com.inmobi.commons.core.utilities.uid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.f;
import com.my.target.i;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;

/* JADX INFO: compiled from: UidHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    private static final String a = "c";
    private static final Object b = new Object();
    private static c c;
    private static a d;

    public static String d() {
        return "1";
    }

    public static c a() {
        c cVar = c;
        if (cVar == null) {
            synchronized (b) {
                cVar = c;
                if (cVar == null) {
                    cVar = new c();
                    c = cVar;
                }
            }
        }
        return cVar;
    }

    private c() {
    }

    public static void c() {
        String str;
        try {
            if (!f.a("root")) {
                String strE = e();
                Logger.a(Logger.InternalLogLevel.DEBUG, a, "Publisher device Id is " + a(strE, CommonUtils.SHA1_INSTANCE));
                return;
            }
            a aVar = d;
            if (aVar == null || (str = aVar.a) == null) {
                return;
            }
            Logger.a(Logger.InternalLogLevel.DEBUG, a, "Publisher device Id is " + str);
        } catch (Exception e) {
            new StringBuilder("SDK encountered an unexpected error attempting to print the publisher test ID; ").append(e.getMessage());
        }
    }

    static String a(String str, String str2) {
        if (str == null) {
            return "TEST_EMULATOR";
        }
        try {
            if ("".equals(str.trim())) {
                return "TEST_EMULATOR";
            }
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                stringBuffer.append(Integer.toString((b2 & 255) + 256, 16).substring(1));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            new StringBuilder("SDK encountered an unexpected error attempting to get digested UID; ").append(e.getMessage());
            return null;
        }
    }

    @SuppressLint({"HardwareIds"})
    static String e() {
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return "";
        }
        try {
            String string = Settings.Secure.getString(contextB.getContentResolver(), i.ANDROID_ID);
            return string == null ? Settings.System.getString(contextB.getContentResolver(), i.ANDROID_ID) : string;
        } catch (Exception unused) {
            return "";
        }
    }

    static a f() {
        return d;
    }

    private static boolean j() {
        try {
            AdvertisingIdClient.class.getName();
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    @Nullable
    public static Boolean g() {
        a();
        a aVar = d;
        if (aVar == null) {
            return null;
        }
        return aVar.b;
    }

    public final void b() {
        try {
            final b bVar = new b();
            a aVar = new a();
            d = aVar;
            aVar.a = bVar.a.c("adv_id");
            d.b = bVar.a.a.contains("limit_ad_tracking") ? Boolean.valueOf(bVar.a.b("limit_ad_tracking", true)) : null;
            if (f.a("root") && j()) {
                new Thread(new Runnable() { // from class: com.inmobi.commons.core.utilities.uid.c.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(com.inmobi.commons.a.a.b());
                            String id = advertisingIdInfo.getId();
                            boolean zIsLimitAdTrackingEnabled = advertisingIdInfo.isLimitAdTrackingEnabled();
                            c.d.a = id;
                            bVar.a.a("adv_id", id);
                            c.d.b = Boolean.valueOf(zIsLimitAdTrackingEnabled);
                            bVar.a.a("limit_ad_tracking", zIsLimitAdTrackingEnabled);
                        } catch (Exception e) {
                            String unused = c.a;
                            new StringBuilder("SDK encountered unexpected error in trying to set the advertising ID ").append(e.getMessage());
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in setting the advertising ID; ").append(e.getMessage());
        }
    }
}
