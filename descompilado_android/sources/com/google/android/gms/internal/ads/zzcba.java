package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcba implements zzdvi<zzcay> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzaws> zzerp;
    private final zzdvv<Executor> zzfbt;

    public zzcba(zzdvv<Executor> zzdvvVar, zzdvv<zzaws> zzdvvVar2, zzdvv<Context> zzdvvVar3) {
        this.zzfbt = zzdvvVar;
        this.zzerp = zzdvvVar2;
        this.zzejn = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcay(this.zzfbt.get(), this.zzerp.get(), this.zzejn.get());
    }
}
