package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbnu extends zzbos<com.google.android.gms.ads.internal.overlay.zzp> implements com.google.android.gms.ads.internal.overlay.zzp {
    public zzbnu(Set<zzbqc<com.google.android.gms.ads.internal.overlay.zzp>> set) {
        super(set);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void zzse() {
        zza(zzbnx.zzfgm);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void onPause() {
        zza(zzbnw.zzfgm);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void onResume() {
        zza(zzbnz.zzfgm);
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void zzsf() {
        zza(zzbny.zzfgm);
    }
}
