package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzckg implements com.google.android.gms.ads.internal.zzg {
    private final zzbqg zzfdd;
    private final zzbmo zzfjd;
    private final zzbmf zzfje;
    private final zzbgu zzfjf;
    private final zzbqb zzfjh;
    private AtomicBoolean zzgae = new AtomicBoolean(false);

    zzckg(zzbmf zzbmfVar, zzbmo zzbmoVar, zzbqg zzbqgVar, zzbqb zzbqbVar, zzbgu zzbguVar) {
        this.zzfje = zzbmfVar;
        this.zzfjd = zzbmoVar;
        this.zzfdd = zzbqgVar;
        this.zzfjh = zzbqbVar;
        this.zzfjf = zzbguVar;
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final synchronized void zzg(View view) {
        if (this.zzgae.compareAndSet(false, true)) {
            this.zzfjf.onAdImpression();
            this.zzfjh.zzq(view);
        }
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final void zzjk() {
        if (this.zzgae.get()) {
            this.zzfje.onAdClicked();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final void zzjl() {
        if (this.zzgae.get()) {
            this.zzfjd.onAdImpression();
            this.zzfdd.zzagm();
        }
    }
}
