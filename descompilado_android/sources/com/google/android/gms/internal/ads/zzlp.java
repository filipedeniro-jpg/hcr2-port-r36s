package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzlp implements zzmf {
    private final int track;
    private final /* synthetic */ zzlg zzbaq;

    public zzlp(zzlg zzlgVar, int i) {
        this.zzbaq = zzlgVar;
        this.track = i;
    }

    @Override // com.google.android.gms.internal.ads.zzmf
    public final boolean isReady() {
        return this.zzbaq.zzas(this.track);
    }

    @Override // com.google.android.gms.internal.ads.zzmf
    public final void zzhb() throws IOException {
        this.zzbaq.zzhb();
    }

    @Override // com.google.android.gms.internal.ads.zzmf
    public final int zzb(zzgs zzgsVar, zzim zzimVar, boolean z) {
        return this.zzbaq.zza(this.track, zzgsVar, zzimVar, z);
    }

    @Override // com.google.android.gms.internal.ads.zzmf
    public final void zzeb(long j) {
        this.zzbaq.zzd(this.track, j);
    }
}
