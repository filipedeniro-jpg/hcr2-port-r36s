package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcok implements zzdvi<zzcoi> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<zzdcs> zzfbt;

    public zzcok(zzdvv<zzdcs> zzdvvVar, zzdvv<Context> zzdvvVar2) {
        this.zzfbt = zzdvvVar;
        this.zzejn = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcoi(this.zzfbt.get(), this.zzejn.get());
    }
}
