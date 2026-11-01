package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtf implements zzdvi<JSONObject> {
    private final zzbtd zzfjx;

    public zzbtf(zzbtd zzbtdVar) {
        this.zzfjx = zzbtdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (JSONObject) zzdvo.zza(this.zzfjx.zzahd(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
