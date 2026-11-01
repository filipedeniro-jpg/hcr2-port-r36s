package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbar extends zzbah {
    public zzbar(zzayt zzaytVar) {
        super(zzaytVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final void abort() {
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final boolean zzfd(String str) {
        zzayt zzaytVar = this.zzecy.get();
        if (zzaytVar != null) {
            zzaytVar.zza(zzfe(str), this);
        }
        zzatm.zzeu("VideoStreamNoopCache is doing nothing.");
        zza(str, zzfe(str), "noop", "Noop cache is a noop.");
        return false;
    }
}
