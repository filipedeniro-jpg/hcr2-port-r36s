package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcac implements zzdvi<zzcaa> {
    private final zzdvv<zzctp> zzeol;
    private final zzdvv<zzrz> zzfqx;

    private zzcac(zzdvv<zzrz> zzdvvVar, zzdvv<zzctp> zzdvvVar2) {
        this.zzfqx = zzdvvVar;
        this.zzeol = zzdvvVar2;
    }

    public static zzcac zzs(zzdvv<zzrz> zzdvvVar, zzdvv<zzctp> zzdvvVar2) {
        return new zzcac(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcaa(this.zzfqx.get(), this.zzeol.get());
    }
}
