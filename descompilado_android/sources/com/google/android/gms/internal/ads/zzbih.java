package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbih implements zzdvi<zzbjr> {
    private final zzbib zzfdq;

    public zzbih(zzbib zzbibVar) {
        this.zzfdq = zzbibVar;
    }

    public static zzbjr zzb(zzbib zzbibVar) {
        return (zzbjr) zzdvo.zza(zzbibVar.zzaey(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzb(this.zzfdq);
    }
}
