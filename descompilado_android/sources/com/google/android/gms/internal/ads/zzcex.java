package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcex implements zzcxt {
    private final zzcev zzfvy;

    zzcex(zzcev zzcevVar) {
        this.zzfvy = zzcevVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zza(zzcxk zzcxkVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zzb(zzcxk zzcxkVar, String str) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcst)).booleanValue() && zzcxk.RENDERER == zzcxkVar) {
            this.zzfvy.zzey(com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zza(zzcxk zzcxkVar, String str, Throwable th) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcst)).booleanValue() && zzcxk.RENDERER == zzcxkVar && this.zzfvy.zzakk() != 0) {
            this.zzfvy.zzej(com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - this.zzfvy.zzakk());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zzc(zzcxk zzcxkVar, String str) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcst)).booleanValue() && zzcxk.RENDERER == zzcxkVar && this.zzfvy.zzakk() != 0) {
            this.zzfvy.zzej(com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - this.zzfvy.zzakk());
        }
    }
}
