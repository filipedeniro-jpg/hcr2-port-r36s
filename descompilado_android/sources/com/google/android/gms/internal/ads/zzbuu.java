package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.ViewGroup;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbuu implements zzaag {
    private final /* synthetic */ zzbvf zzfmn;
    private final /* synthetic */ ViewGroup zzfmo;
    private final /* synthetic */ zzbup zzfmp;

    zzbuu(zzbup zzbupVar, zzbvf zzbvfVar, ViewGroup viewGroup) {
        this.zzfmp = zzbupVar;
        this.zzfmn = zzbvfVar;
        this.zzfmo = viewGroup;
    }

    @Override // com.google.android.gms.internal.ads.zzaag
    public final void zzqg() {
        zzbup zzbupVar = this.zzfmp;
        if (zzbup.zza(this.zzfmn, zzbun.zzflv)) {
            this.zzfmn.onClick(this.zzfmo);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaag
    public final void zzc(MotionEvent motionEvent) {
        this.zzfmn.onTouch(null, motionEvent);
    }
}
