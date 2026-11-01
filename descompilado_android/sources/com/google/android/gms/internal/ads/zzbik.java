package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbik implements zzdvi<zzbhy> {
    private final zzdvv<zzbia> zzewb;
    private final zzbib zzfdq;

    public zzbik(zzbib zzbibVar, zzdvv<zzbia> zzdvvVar) {
        this.zzfdq = zzbibVar;
        this.zzewb = zzdvvVar;
    }

    public static zzbhy zza(zzbib zzbibVar, Object obj) {
        return (zzbhy) zzdvo.zza((zzbia) obj, "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfdq, this.zzewb.get());
    }
}
