package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbum implements zzdvi<zzars> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcvk> zzfds;
    private final zzbuj zzflt;

    private zzbum(zzbuj zzbujVar, zzdvv<Context> zzdvvVar, zzdvv<zzcvk> zzdvvVar2) {
        this.zzflt = zzbujVar;
        this.zzejn = zzdvvVar;
        this.zzfds = zzdvvVar2;
    }

    public static zzbum zza(zzbuj zzbujVar, zzdvv<Context> zzdvvVar, zzdvv<zzcvk> zzdvvVar2) {
        return new zzbum(zzbujVar, zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzars) zzdvo.zza(new zzars(this.zzejn.get(), this.zzfds.get().zzgjt), "Cannot return null from a non-@Nullable @Provides method");
    }
}
