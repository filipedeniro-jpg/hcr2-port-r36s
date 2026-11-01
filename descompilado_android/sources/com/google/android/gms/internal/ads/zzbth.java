package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbth implements zzdvi<zzbuf> {
    private final zzbtd zzfjx;
    private final zzdvv<zzbta> zzfjy;

    public zzbth(zzbtd zzbtdVar, zzdvv<zzbta> zzdvvVar) {
        this.zzfjx = zzbtdVar;
        this.zzfjy = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbuf) zzdvo.zza(this.zzfjy.get(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
