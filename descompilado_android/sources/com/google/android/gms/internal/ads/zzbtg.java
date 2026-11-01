package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtg implements zzdvi<JSONObject> {
    private final zzdvv<zzbub> zzelm;
    private final zzbtd zzfjx;

    public zzbtg(zzbtd zzbtdVar, zzdvv<zzbub> zzdvvVar) {
        this.zzfjx = zzbtdVar;
        this.zzelm = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        return this.zzelm.get().zzahw();
    }
}
