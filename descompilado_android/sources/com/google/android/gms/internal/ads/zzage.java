package com.google.android.gms.internal.ads;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class zzage {
    public static void zza(zzagb zzagbVar, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzagbVar.zzk(str, jSONObject.toString());
    }

    public static void zza(zzagb zzagbVar, String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(str2).length());
        sb.append(str);
        sb.append("(");
        sb.append(str2);
        sb.append(");");
        zzagbVar.zzct(sb.toString());
    }

    public static void zzb(zzagb zzagbVar, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String string = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(string);
        sb.append(");");
        String strValueOf = String.valueOf(sb.toString());
        zzatm.zzdv(strValueOf.length() != 0 ? "Dispatching AFMA event: ".concat(strValueOf) : new String("Dispatching AFMA event: "));
        zzagbVar.zzct(sb.toString());
    }

    public static void zza(zzagb zzagbVar, String str, Map map) {
        try {
            zzagbVar.zza(str, com.google.android.gms.ads.internal.zzp.zzjy().zzi((Map<String, ?>) map));
        } catch (JSONException unused) {
            zzatm.zzeu("Could not convert parameters to JSON.");
        }
    }
}
