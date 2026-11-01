package com.inmobi.ads;

import android.os.Build;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeGifAsset.java */
/* JADX INFO: loaded from: classes2.dex */
class aq extends ak {
    private static final String A = "aq";
    t z;

    aq(String str, String str2, al alVar, String str3, int i, JSONObject jSONObject) {
        this(str, str2, alVar, str3, new LinkedList(), i, jSONObject);
    }

    aq(String str, String str2, al alVar, String str3, List<NativeTracker> list, int i, JSONObject jSONObject) {
        t lVar;
        super(str, str2, "GIF", alVar, list);
        com.inmobi.ads.cache.d.a();
        com.inmobi.ads.cache.a aVarB = com.inmobi.ads.cache.d.b(str3);
        this.e = aVarB == null ? null : aVarB.e;
        if (aVarB != null) {
            try {
                String str4 = aVarB.e;
                if (Build.VERSION.SDK_INT < 28) {
                    lVar = new u(str4);
                } else {
                    lVar = new l(str4);
                }
                this.z = lVar;
            } catch (Exception e) {
                new StringBuilder("Exception in decoding GIF : ").append(e.getMessage());
                this.z = null;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
        if (jSONObject != null) {
            this.i = i;
            this.f = jSONObject;
        }
    }
}
