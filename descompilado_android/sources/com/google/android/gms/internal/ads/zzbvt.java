package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbvt implements zzdvi<zzbvr> {
    private final zzdvv<zzave> zzesc;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<Clock> zzfbv;

    public zzbvt(zzdvv<zzave> zzdvvVar, zzdvv<Clock> zzdvvVar2, zzdvv<Executor> zzdvvVar3) {
        this.zzesc = zzdvvVar;
        this.zzfbv = zzdvvVar2;
        this.zzfbt = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbvr(this.zzesc.get(), this.zzfbv.get(), this.zzfbt.get());
    }
}
