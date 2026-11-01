package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbla implements zzdvi<zzbkx> {
    private final zzdvv<Clock> zzfbv;
    private final zzdvv<zzasw> zzffw;

    private zzbla(zzdvv<Clock> zzdvvVar, zzdvv<zzasw> zzdvvVar2) {
        this.zzfbv = zzdvvVar;
        this.zzffw = zzdvvVar2;
    }

    public static zzbla zzc(zzdvv<Clock> zzdvvVar, zzdvv<zzasw> zzdvvVar2) {
        return new zzbla(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbkx(this.zzfbv.get(), this.zzffw.get());
    }
}
