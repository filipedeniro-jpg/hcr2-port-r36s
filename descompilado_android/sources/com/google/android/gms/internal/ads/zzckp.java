package com.google.android.gms.internal.ads;

import android.view.View;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzckp implements com.google.android.gms.ads.internal.zzg {

    @GuardedBy("this")
    private com.google.android.gms.ads.internal.zzg zzgap;

    public final synchronized void zza(com.google.android.gms.ads.internal.zzg zzgVar) {
        this.zzgap = zzgVar;
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final synchronized void zzg(View view) {
        if (this.zzgap != null) {
            this.zzgap.zzg(view);
        }
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final synchronized void zzjk() {
        if (this.zzgap != null) {
            this.zzgap.zzjk();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzg
    public final synchronized void zzjl() {
        if (this.zzgap != null) {
            this.zzgap.zzjl();
        }
    }
}
