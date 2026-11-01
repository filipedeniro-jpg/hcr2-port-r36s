package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtm implements zzdvi<zzpf> {
    private final zzdvv<zzawv> zzfcg;
    private final zzdvv<String> zzfch;

    public zzbtm(zzdvv<zzawv> zzdvvVar, zzdvv<String> zzdvvVar2) {
        this.zzfcg = zzdvvVar;
        this.zzfch = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzawv zzawvVar = this.zzfcg.get();
        String str = this.zzfch.get();
        com.google.android.gms.ads.internal.zzp.zzjy();
        return (zzpf) zzdvo.zza(new zzpf(zzatv.zzvi(), zzawvVar, str, new JSONObject(), false, true), "Cannot return null from a non-@Nullable @Provides method");
    }
}
