package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcfm {
    private zzbmo zzfig;
    private zzcvb zzfih;

    public zzcfm(zzcvb zzcvbVar) {
        this.zzfih = zzcvbVar;
    }

    public final void zzako() {
        if (this.zzfig != null && this.zzfih.zzgjh == 2) {
            this.zzfig.onAdImpression();
        }
    }

    public final void zza(zzbmo zzbmoVar) {
        this.zzfig = zzbmoVar;
    }
}
