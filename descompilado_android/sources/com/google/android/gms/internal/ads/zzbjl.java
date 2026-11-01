package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjl implements zzbnm, zzpl {
    private final zzcvb zzfar;
    private final zzbmo zzfei;
    private final zzbnq zzfej;
    private final AtomicBoolean zzfek = new AtomicBoolean();
    private final AtomicBoolean zzfel = new AtomicBoolean();

    public zzbjl(zzcvb zzcvbVar, zzbmo zzbmoVar, zzbnq zzbnqVar) {
        this.zzfar = zzcvbVar;
        this.zzfei = zzbmoVar;
        this.zzfej = zzbnqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final synchronized void onAdLoaded() {
        if (this.zzfar.zzgir != 1) {
            zzafg();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzpl
    public final void zza(zzpi zzpiVar) {
        if (this.zzfar.zzgir == 1 && zzpiVar.zzbnr) {
            zzafg();
        }
        if (zzpiVar.zzbnr && this.zzfel.compareAndSet(false, true)) {
            this.zzfej.zzafv();
        }
    }

    private final void zzafg() {
        if (this.zzfek.compareAndSet(false, true)) {
            this.zzfei.onAdImpression();
        }
    }
}
