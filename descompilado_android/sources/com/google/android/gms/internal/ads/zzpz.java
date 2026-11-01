package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzpz implements Runnable {
    private final /* synthetic */ zzpw zzbpf;

    zzpz(zzpw zzpwVar) {
        this.zzbpf = zzpwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzbpf.lock) {
            if (this.zzbpf.foreground && this.zzbpf.zzbox) {
                zzpw.zza(this.zzbpf, false);
                zzatm.zzdv("App went background");
                Iterator it = this.zzbpf.zzboy.iterator();
                while (it.hasNext()) {
                    try {
                        ((zzpy) it.next()).zzo(false);
                    } catch (Exception e) {
                        zzawo.zzc("", e);
                    }
                }
            } else {
                zzatm.zzdv("App is still foreground");
            }
        }
    }
}
