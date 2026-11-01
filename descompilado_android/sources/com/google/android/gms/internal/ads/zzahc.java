package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzahc implements zzadx<zzahr> {
    private final /* synthetic */ zzagk zzczq;
    private final /* synthetic */ zzagt zzczs;
    private final /* synthetic */ zzaho zzczt;

    zzahc(zzagt zzagtVar, zzaho zzahoVar, zzagk zzagkVar) {
        this.zzczs = zzagtVar;
        this.zzczt = zzahoVar;
        this.zzczq = zzagkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzahr zzahrVar, Map map) {
        synchronized (this.zzczs.lock) {
            if (this.zzczt.getStatus() != -1 && this.zzczt.getStatus() != 1) {
                this.zzczs.status = 0;
                this.zzczs.zzczf.zzh(this.zzczq);
                this.zzczt.zzm(this.zzczq);
                this.zzczs.zzczh = this.zzczt;
                zzatm.zzdy("Successfully loaded JS Engine.");
            }
        }
    }
}
