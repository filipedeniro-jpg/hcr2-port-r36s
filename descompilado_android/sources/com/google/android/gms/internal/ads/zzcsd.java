package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsd implements zzcrb<JSONObject> {
    private JSONObject zzggp;

    public zzcsd(JSONObject jSONObject) {
        this.zzggp = jSONObject;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        try {
            jSONObject.put("cache_state", this.zzggp);
        } catch (JSONException unused) {
            zzatm.zzdy("Unable to get cache_state");
        }
    }
}
