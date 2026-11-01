package com.chartboost.sdk.Libraries;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.util.Base64;
import com.chartboost.sdk.impl.ar;
import com.chartboost.sdk.impl.as;
import com.vungle.warren.network.VungleApiClient;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d {
    private int a = -1;
    private String b = null;
    private final String c;

    public static class a {
        public final int a;
        public final String b;
        public final String c;
        public final String d;

        public a(int i, String str, String str2, String str3) {
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }
    }

    public d(Context context) {
        this.c = ar.b(context);
    }

    public synchronized a a() {
        if (Looper.myLooper() == Looper.getMainLooper() && !"robolectric".equals(Build.FINGERPRINT)) {
            CBLogging.b("CBIdentity", "I must be called from a background thread");
            return null;
        }
        if (b()) {
            c();
        } else {
            a(com.chartboost.sdk.i.m);
        }
        String str = this.b;
        JSONObject jSONObject = new JSONObject();
        if (this.c != null && str == null) {
            e.a(jSONObject, "uuid", this.c);
        }
        if (str != null) {
            e.a(jSONObject, "gaid", str);
        }
        return new a(this.a, Base64.encodeToString(jSONObject.toString().getBytes(), 0), str != null ? "000000000" : this.c, str);
    }

    private static boolean b() {
        return !VungleApiClient.MANUFACTURER_AMAZON.equalsIgnoreCase(Build.MANUFACTURER);
    }

    private void c() {
        if (as.a(com.chartboost.sdk.i.m)) {
            com.chartboost.sdk.Libraries.a aVar = new com.chartboost.sdk.Libraries.a(com.chartboost.sdk.i.m);
            this.a = aVar.a;
            this.b = aVar.b;
        }
    }

    private void a(Context context) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (!(Settings.Secure.getInt(contentResolver, "limit_ad_tracking") != 0)) {
                String string = Settings.Secure.getString(contentResolver, "advertising_id");
                if ("00000000-0000-0000-0000-000000000000".equals(string)) {
                    this.a = 1;
                    this.b = null;
                } else {
                    this.a = 0;
                    this.b = string;
                }
            } else {
                this.a = 1;
                this.b = null;
            }
        } catch (Settings.SettingNotFoundException unused) {
            this.a = -1;
            this.b = null;
        }
    }
}
