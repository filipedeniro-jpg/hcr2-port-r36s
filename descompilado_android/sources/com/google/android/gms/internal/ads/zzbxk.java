package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbxk implements zzaag {
    private final /* synthetic */ zzbxh zzfoz;

    zzbxk(zzbxh zzbxhVar) {
        this.zzfoz = zzbxhVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaag
    public final void zzc(MotionEvent motionEvent) {
    }

    @Override // com.google.android.gms.internal.ads.zzaag
    public final void zzqg() {
        this.zzfoz.zzfma.zzfp(NativeCustomTemplateAd.ASSET_NAME_VIDEO);
    }
}
