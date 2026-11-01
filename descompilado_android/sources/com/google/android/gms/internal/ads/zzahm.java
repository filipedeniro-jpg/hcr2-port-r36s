package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzahm implements zzaxj<zzahr> {
    private final /* synthetic */ zzahk zzczz;

    zzahm(zzahk zzahkVar) {
        this.zzczz = zzahkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaxj
    public final /* synthetic */ void zzh(zzahr zzahrVar) {
        zzatm.zzdy("Releasing engine reference.");
        this.zzczz.zzczx.zzrd();
    }
}
