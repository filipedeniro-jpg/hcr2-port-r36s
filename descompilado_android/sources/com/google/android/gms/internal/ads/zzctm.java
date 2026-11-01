package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzctm implements zzdvi<zzctn<zzbym, zzbyf>> {
    private final zzdvv<Context> zzejn;

    public zzctm(zzdvv<Context> zzdvvVar) {
        this.zzejn = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        this.zzejn.get();
        return (zzctn) zzdvo.zza(new zzctk(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
