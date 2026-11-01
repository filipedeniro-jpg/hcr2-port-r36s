package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsi implements zzcrb<JSONObject> {
    private String zzggt;
    private String zzggu;

    public zzcsi(String str, String str2) {
        this.zzggt = str;
        this.zzggu = str2;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        try {
            JSONObject jSONObjectZzb = zzavm.zzb(jSONObject, "pii");
            jSONObjectZzb.put("doritos", this.zzggt);
            jSONObjectZzb.put("doritos_v2", this.zzggu);
        } catch (JSONException unused) {
            zzatm.zzdy("Failed putting doritos string.");
        }
    }
}
