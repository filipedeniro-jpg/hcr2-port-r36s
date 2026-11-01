package com.google.android.gms.internal.ads;

import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsv implements zzcrb<JSONObject> {
    private Bundle zzghb;

    public zzcsv(Bundle bundle) {
        this.zzghb = bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        if (this.zzghb != null) {
            try {
                zzavm.zzb(zzavm.zzb(jSONObject2, "device"), "play_store").put("parental_controls", com.google.android.gms.ads.internal.zzp.zzjy().zzd(this.zzghb));
            } catch (JSONException unused) {
                zzatm.zzdy("Failed putting parental controls bundle.");
            }
        }
    }
}
