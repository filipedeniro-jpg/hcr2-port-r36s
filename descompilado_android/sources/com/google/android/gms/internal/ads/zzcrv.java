package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcrv implements zzcrb<JSONObject> {
    private final String zzggj;

    public zzcrv(String str) {
        this.zzggj = str;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        try {
            jSONObject.put("ms", this.zzggj);
        } catch (JSONException e) {
            zzatm.zza("Failed putting Ad ID.", e);
        }
    }
}
