package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcai implements zzcxt {
    private zzrz zzfqt;
    private Map<zzcxk, zzcak> zzfrd;

    zzcai(zzrz zzrzVar, Map<zzcxk, zzcak> map) {
        this.zzfrd = map;
        this.zzfqt = zzrzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zza(zzcxk zzcxkVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zzb(zzcxk zzcxkVar, String str) {
        if (this.zzfrd.containsKey(zzcxkVar)) {
            this.zzfqt.zza(this.zzfrd.get(zzcxkVar).zzfrf);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zza(zzcxk zzcxkVar, String str, Throwable th) {
        if (this.zzfrd.containsKey(zzcxkVar)) {
            this.zzfqt.zza(this.zzfrd.get(zzcxkVar).zzfrh);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zzc(zzcxk zzcxkVar, String str) {
        if (this.zzfrd.containsKey(zzcxkVar)) {
            this.zzfqt.zza(this.zzfrd.get(zzcxkVar).zzfrg);
        }
    }
}
