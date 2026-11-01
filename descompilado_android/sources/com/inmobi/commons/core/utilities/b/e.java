package com.inmobi.commons.core.utilities.b;

import androidx.annotation.Nullable;
import com.inmobi.sdk.InMobiSdk;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: GDPRInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    private static final String a = "com.inmobi.commons.core.utilities.b.e";
    private static JSONObject b;
    private static com.inmobi.commons.core.configs.h c = new com.inmobi.commons.core.configs.h();
    private static com.inmobi.commons.core.configs.b.c d = new com.inmobi.commons.core.configs.b.c() { // from class: com.inmobi.commons.core.utilities.b.e.1
        @Override // com.inmobi.commons.core.configs.b.c
        public final void a(com.inmobi.commons.core.configs.a aVar) {
            com.inmobi.commons.core.configs.h unused = e.c = (com.inmobi.commons.core.configs.h) aVar;
        }
    };

    private e() {
    }

    @Nullable
    public static JSONObject a() {
        return b;
    }

    public static void a(@Nullable JSONObject jSONObject) {
        b = jSONObject;
    }

    public static void c() {
        com.inmobi.commons.core.configs.b.a().a(c, d);
    }

    public static int d() {
        return a(false);
    }

    public static int a(boolean z) {
        int i = c.d;
        int iF = f();
        return (iF == 1 || i == 1 || z || !(i == 0 || i != -1 || iF == 0)) ? 1 : 0;
    }

    private static int f() {
        JSONObject jSONObject = b;
        if (jSONObject != null && jSONObject.has(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE)) {
            try {
                return jSONObject.getBoolean(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE) ? 1 : 0;
            } catch (JSONException unused) {
            }
        }
        return -1;
    }

    public static boolean b() {
        JSONObject jSONObject = b;
        if (jSONObject != null) {
            try {
                if (jSONObject.has(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE) && !jSONObject.getBoolean(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE)) {
                    return true;
                }
            } catch (JSONException unused) {
            }
        }
        return false;
    }

    public static boolean e() {
        return a(false) != 0;
    }
}
