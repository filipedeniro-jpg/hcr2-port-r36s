package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcte implements zzdvi<zzcra<? extends zzcrb<JSONObject>>> {
    private static final zzcte zzghe = new zzcte();

    public static zzcra<? extends zzcrb<JSONObject>> zzamo() {
        return (zzcra) zzdvo.zza(zzctc.zzghd, "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzamo();
    }
}
