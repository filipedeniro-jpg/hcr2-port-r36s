package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbiq implements zzdvi<zzars> {
    private final zzdvv<Context> zzejn;
    private final zzbib zzfdq;
    private final zzdvv<zzcvk> zzfds;

    public zzbiq(zzbib zzbibVar, zzdvv<Context> zzdvvVar, zzdvv<zzcvk> zzdvvVar2) {
        this.zzfdq = zzbibVar;
        this.zzejn = zzdvvVar;
        this.zzfds = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzars) zzdvo.zza(new zzars(this.zzejn.get(), this.zzfds.get().zzgjt), "Cannot return null from a non-@Nullable @Provides method");
    }
}
