package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbvx implements zzdvi<zzbvw> {
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<zzbwa> zzfnt;
    private final zzdvv<zzbwh> zzfnu;

    public zzbvx(zzdvv<zzdcs> zzdvvVar, zzdvv<zzbwa> zzdvvVar2, zzdvv<zzbwh> zzdvvVar3) {
        this.zzfbt = zzdvvVar;
        this.zzfnt = zzdvvVar2;
        this.zzfnu = zzdvvVar3;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbvw(this.zzfbt.get(), this.zzfnt.get(), this.zzfnu.get());
    }
}
