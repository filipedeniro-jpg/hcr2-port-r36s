package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbyn implements zzdvi<zzbqc<zzbmg>> {
    private final zzdvv<zzbpt> zzfca;

    public zzbyn(zzdvv<zzbpt> zzdvvVar) {
        this.zzfca = zzdvvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzbqc) zzdvo.zza(new zzbqc(this.zzfca.get(), zzawx.zzdwb), "Cannot return null from a non-@Nullable @Provides method");
    }
}
