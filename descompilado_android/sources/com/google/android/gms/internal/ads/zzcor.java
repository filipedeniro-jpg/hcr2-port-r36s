package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcor implements zzdvi<zzcuz> {
    private final zzdvv<Clock> zzfbv;

    public zzcor(zzdvv<Clock> zzdvvVar) {
        this.zzfbv = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcuz) zzdvo.zza(new zzcuz(this.zzfbv.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
