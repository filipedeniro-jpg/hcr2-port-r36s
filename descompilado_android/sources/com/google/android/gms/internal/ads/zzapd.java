package com.google.android.gms.internal.ads;

import android.content.Context;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzapd implements zzaoo {
    private zzahv<JSONObject, JSONObject> zzdnc;
    private zzahv<JSONObject, JSONObject> zzdnh;

    public zzapd(Context context) {
        this.zzdnh = com.google.android.gms.ads.internal.zzp.zzkl().zza(context, zzawv.zzwk()).zza("google.afma.request.getAdDictionary", zzaic.zzdan, zzaic.zzdan);
        this.zzdnc = com.google.android.gms.ads.internal.zzp.zzkl().zza(context, zzawv.zzwk()).zza("google.afma.sdkConstants.getSdkConstants", zzaic.zzdan, zzaic.zzdan);
    }

    @Override // com.google.android.gms.internal.ads.zzaoo
    public final zzahv<JSONObject, JSONObject> zzsy() {
        return this.zzdnc;
    }
}
