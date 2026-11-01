package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjx<AdT> implements zzdvi<zzbjy<AdT>> {
    private final zzdvv<Map<String, zzcfk<AdT>>> zzfew;

    private zzbjx(zzdvv<Map<String, zzcfk<AdT>>> zzdvvVar) {
        this.zzfew = zzdvvVar;
    }

    public static <AdT> zzbjx<AdT> zzd(zzdvv<Map<String, zzcfk<AdT>>> zzdvvVar) {
        return new zzbjx<>(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbjy(this.zzfew.get());
    }
}
