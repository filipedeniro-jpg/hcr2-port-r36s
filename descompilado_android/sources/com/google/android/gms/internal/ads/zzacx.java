package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzacx extends zzabx {
    private final NativeCustomTemplateAd.OnCustomClickListener zzcwh;

    public zzacx(NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
        this.zzcwh = onCustomClickListener;
    }

    @Override // com.google.android.gms.internal.ads.zzaby
    public final void zza(zzabo zzaboVar, String str) {
        this.zzcwh.onCustomClick(zzabp.zza(zzaboVar), str);
    }
}
