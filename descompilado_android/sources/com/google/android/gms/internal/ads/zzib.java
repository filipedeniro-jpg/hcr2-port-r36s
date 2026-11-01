package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzib implements zzhw {
    private final /* synthetic */ zzhz zzakt;

    private zzib(zzhz zzhzVar) {
        this.zzakt = zzhzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzhw
    public final void zzr(int i) {
        this.zzakt.zzakj.zzq(i);
        zzhz.zzr(i);
    }

    @Override // com.google.android.gms.internal.ads.zzhw
    public final void zzdr() {
        zzhz.zzfk();
        zzhz.zza(this.zzakt, true);
    }

    @Override // com.google.android.gms.internal.ads.zzhw
    public final void zzc(int i, long j, long j2) {
        this.zzakt.zzakj.zza(i, j, j2);
        zzhz.zzb(i, j, j2);
    }
}
