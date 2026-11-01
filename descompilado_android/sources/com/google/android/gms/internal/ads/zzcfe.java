package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcfe implements zzdvi<zzcez> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzcev> zzeoy;
    private final zzdvv<zzcep> zzeri;
    private final zzdvv<zzdcp<Bundle>> zzfwm;

    private zzcfe(zzdvv<Context> zzdvvVar, zzdvv<zzdcp<Bundle>> zzdvvVar2, zzdvv<zzcev> zzdvvVar3, zzdvv<zzcep> zzdvvVar4) {
        this.zzejn = zzdvvVar;
        this.zzfwm = zzdvvVar2;
        this.zzeoy = zzdvvVar3;
        this.zzeri = zzdvvVar4;
    }

    public static zzcfe zzc(zzdvv<Context> zzdvvVar, zzdvv<zzdcp<Bundle>> zzdvvVar2, zzdvv<zzcev> zzdvvVar3, zzdvv<zzcep> zzdvvVar4) {
        return new zzcfe(zzdvvVar, zzdvvVar2, zzdvvVar3, zzdvvVar4);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcez(this.zzejn.get(), this.zzfwm.get(), this.zzeoy.get(), this.zzeri.get());
    }
}
