package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzchr implements zzdbq {
    static final zzdbq zzbks = new zzchr();

    private zzchr() {
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final zzdcp zzf(Object obj) throws zzahw {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject.optBoolean("success")) {
            return zzdcf.zzah(jSONObject.getJSONObject("json").getJSONArray("ads"));
        }
        throw new zzahw("process json failed");
    }
}
