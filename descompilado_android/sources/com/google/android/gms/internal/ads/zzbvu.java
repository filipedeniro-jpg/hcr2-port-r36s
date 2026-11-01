package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbvu implements zzczs<zzo, Bitmap> {
    private final /* synthetic */ double zzfnk;
    private final /* synthetic */ boolean zzfnl;
    private final /* synthetic */ zzbvr zzfnm;

    zzbvu(zzbvr zzbvrVar, double d, boolean z) {
        this.zzfnm = zzbvrVar;
        this.zzfnk = d;
        this.zzfnl = z;
    }

    @Override // com.google.android.gms.internal.ads.zzczs
    public final /* synthetic */ Bitmap apply(zzo zzoVar) {
        return this.zzfnm.zza(zzoVar.data, this.zzfnk, this.zzfnl);
    }
}
