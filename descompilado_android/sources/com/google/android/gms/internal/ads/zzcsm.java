package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsm implements zzcrb<JSONObject> {
    private List<String> zzdit;

    public zzcsm(List<String> list) {
        this.zzdit = list;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        try {
            jSONObject.put("eid", TextUtils.join(",", this.zzdit));
        } catch (JSONException unused) {
            zzatm.zzdy("Failed putting experiment ids.");
        }
    }
}
