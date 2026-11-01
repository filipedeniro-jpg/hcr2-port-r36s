package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsy implements zzdvi<zzcvv<zzbxn>> {
    private final zzdvv<zzdcs> zzfbt;
    private final zzdvv<zzbxx> zzfiz;

    public zzbsy(zzdvv<zzbxx> zzdvvVar, zzdvv<zzdcs> zzdvvVar2) {
        this.zzfiz = zzdvvVar;
        this.zzfbt = zzdvvVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (zzcvv) zzdvo.zza(new zzcvv(this.zzfiz.get(), this.zzfbt.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
