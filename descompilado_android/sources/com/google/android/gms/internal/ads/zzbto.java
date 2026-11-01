package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbto implements zzdvi<zzbhd> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<Executor> zzfbt;
    private final zzdvv<Clock> zzfbv;
    private final zzdvv<zzpf> zzfka;

    public zzbto(zzdvv<zzpf> zzdvvVar, zzdvv<Executor> zzdvvVar2, zzdvv<Context> zzdvvVar3, zzdvv<Clock> zzdvvVar4) {
        this.zzfka = zzdvvVar;
        this.zzfbt = zzdvvVar2;
        this.zzejn = zzdvvVar3;
        this.zzfbv = zzdvvVar4;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        zzpf zzpfVar = this.zzfka.get();
        Executor executor = this.zzfbt.get();
        Context context = this.zzejn.get();
        return (zzbhd) zzdvo.zza(new zzbhd(executor, new zzbgs(context, zzpfVar), this.zzfbv.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
