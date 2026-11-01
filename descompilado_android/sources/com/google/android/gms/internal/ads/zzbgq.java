package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbgq implements zzadx<Object> {
    final /* synthetic */ zzbgl zzfbd;

    zzbgq(zzbgl zzbglVar) {
        this.zzfbd = zzbglVar;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(Object obj, Map<String, String> map) {
        if (this.zzfbd.zzl(map)) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcro)).booleanValue()) {
                this.zzfbd.executor.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbgp
                    private final zzbgq zzfbe;

                    {
                        this.zzfbe = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzfbe.zzfbd.zzfax.zzaeg();
                    }
                });
            } else {
                this.zzfbd.zzfax.zzaeg();
            }
        }
    }
}
