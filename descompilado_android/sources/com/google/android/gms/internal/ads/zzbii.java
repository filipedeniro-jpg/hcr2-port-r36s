package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbii implements zzdvi<zzcva> {
    private final zzbib zzfdq;

    public zzbii(zzbib zzbibVar) {
        this.zzfdq = zzbibVar;
    }

    public static zzcva zzc(zzbib zzbibVar) {
        return (zzcva) zzdvo.zza(zzbibVar.zzaez(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzc(this.zzfdq);
    }
}
