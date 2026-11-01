package com.google.android.gms.internal.ads;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzckt implements com.google.android.gms.ads.internal.zzg {
    private final /* synthetic */ zzbre zzgar;

    zzckt(zzcks zzcksVar, zzbre zzbreVar) {
        this.zzgar = zzbreVar;
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final void zzg(View view) {
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final void zzjk() {
        this.zzgar.zzabz().onAdClicked();
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final void zzjl() {
        this.zzgar.zzaca().onAdImpression();
        this.zzgar.zzacb().zzagm();
    }
}
