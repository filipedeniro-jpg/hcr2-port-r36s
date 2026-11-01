package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaeu implements zzdbq<zzaep, ParcelFileDescriptor> {
    private final /* synthetic */ zzaej zzcxz;

    zzaeu(zzaer zzaerVar, zzaej zzaejVar) {
        this.zzcxz = zzaejVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final /* synthetic */ zzdcp<ParcelFileDescriptor> zzf(zzaep zzaepVar) throws Exception {
        zzaxf zzaxfVar = new zzaxf();
        zzaepVar.zza(this.zzcxz, new zzaet(this, zzaxfVar));
        return zzaxfVar;
    }
}
