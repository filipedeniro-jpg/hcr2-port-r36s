package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzchw implements zzdvi<zzchq> {
    private final zzdvv<zzbvw> zzesi;
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<zzbsr> zzfwz;

    public zzchw(zzdvv<zzbsr> zzdvvVar, zzdvv<zzdcs> zzdvvVar2, zzdvv<zzbvw> zzdvvVar3) {
        this.zzfwz = zzdvvVar;
        this.zzfbt = zzdvvVar2;
        this.zzesi = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzchq(this.zzfwz.get(), this.zzfbt.get(), this.zzesi.get());
    }
}
