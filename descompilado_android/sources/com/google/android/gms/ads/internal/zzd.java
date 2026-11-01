package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzdbq;
import com.google.android.gms.internal.ads.zzdcf;
import com.google.android.gms.internal.ads.zzdcp;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class zzd implements zzdbq {
    static final zzdbq zzbks = new zzd();

    private zzd() {
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final zzdcp zzf(Object obj) throws JSONException {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject.optBoolean("isSuccessful", false)) {
            zzp.zzkc().zzuj().zzeb(jSONObject.getString("appSettingsJson"));
        }
        return zzdcf.zzah(null);
    }
}
