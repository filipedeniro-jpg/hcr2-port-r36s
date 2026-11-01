package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbrn implements zzdvi<zzars> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcvk> zzfds;
    private final zzbrd zzfia;

    private zzbrn(zzbrd zzbrdVar, zzdvv<Context> zzdvvVar, zzdvv<zzcvk> zzdvvVar2) {
        this.zzfia = zzbrdVar;
        this.zzejn = zzdvvVar;
        this.zzfds = zzdvvVar2;
    }

    public static zzbrn zza(zzbrd zzbrdVar, zzdvv<Context> zzdvvVar, zzdvv<zzcvk> zzdvvVar2) {
        return new zzbrn(zzbrdVar, zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzars) zzdvo.zza(new zzars(this.zzejn.get(), this.zzfds.get().zzgjt), "Cannot return null from a non-@Nullable @Provides method");
    }
}
