package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcbq implements zzdvi<zzcbn> {
    private final zzdvv<zzbds> zzejk;
    private final zzdvv<zzcbb> zzfqx;

    private zzcbq(zzdvv<zzcbb> zzdvvVar, zzdvv<zzbds> zzdvvVar2) {
        this.zzfqx = zzdvvVar;
        this.zzejk = zzdvvVar2;
    }

    public static zzcbq zzz(zzdvv<zzcbb> zzdvvVar, zzdvv<zzbds> zzdvvVar2) {
        return new zzcbq(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcbn(this.zzfqx.get(), this.zzejk.get());
    }
}
