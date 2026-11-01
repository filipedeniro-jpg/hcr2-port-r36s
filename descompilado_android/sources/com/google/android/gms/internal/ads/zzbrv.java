package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbrv implements zzdvi<zzbrw> {
    private final zzdvv<zzbnu> zzemu;
    private final zzdvv<zzbpy> zzeyh;

    private zzbrv(zzdvv<zzbnu> zzdvvVar, zzdvv<zzbpy> zzdvvVar2) {
        this.zzemu = zzdvvVar;
        this.zzeyh = zzdvvVar2;
    }

    public static zzbrv zzi(zzdvv<zzbnu> zzdvvVar, zzdvv<zzbpy> zzdvvVar2) {
        return new zzbrv(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbrw(this.zzemu.get(), this.zzeyh.get());
    }
}
