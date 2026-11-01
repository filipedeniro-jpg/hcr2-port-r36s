package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsn implements zzdvi<zzcsl> {
    private final zzdvv<zzast> zzesq;
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<String> zzfqm;

    public zzcsn(zzdvv<zzast> zzdvvVar, zzdvv<zzdcs> zzdvvVar2, zzdvv<String> zzdvvVar3) {
        this.zzesq = zzdvvVar;
        this.zzfbt = zzdvvVar2;
        this.zzfqm = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcsl(this.zzesq.get(), this.zzfbt.get(), this.zzfqm.get());
    }
}
