package com.google.android.gms.internal.ads;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcsh implements zzcra<zzcrb<JSONObject>> {
    private final JSONObject zzggs;

    zzcsh(Context context) {
        this.zzggs = zzaow.zzx(context);
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrb<JSONObject>> zzalr() {
        return zzdcf.zzah(new zzcrb(this) { // from class: com.google.android.gms.internal.ads.zzcsg
            private final zzcsh zzggr;

            {
                this.zzggr = this;
            }

            @Override // com.google.android.gms.internal.ads.zzcrb
            public final void zzr(Object obj) {
                this.zzggr.zzo((JSONObject) obj);
            }
        });
    }

    final /* synthetic */ void zzo(JSONObject jSONObject) {
        try {
            jSONObject.put("gms_sdk_env", this.zzggs);
        } catch (JSONException unused) {
            zzatm.zzdy("Failed putting version constants.");
        }
    }
}
