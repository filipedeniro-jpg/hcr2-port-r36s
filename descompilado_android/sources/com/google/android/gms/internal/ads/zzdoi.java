package com.google.android.gms.internal.ads;

import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdoi extends zzdoe {
    private final zzdoh zzhey = new zzdoh();

    zzdoi() {
    }

    @Override // com.google.android.gms.internal.ads.zzdoe
    public final void zza(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.zzhey.zza(th, true).add(th2);
    }

    @Override // com.google.android.gms.internal.ads.zzdoe
    public final void zzj(Throwable th) {
        th.printStackTrace();
        List<Throwable> listZza = this.zzhey.zza(th, false);
        if (listZza == null) {
            return;
        }
        synchronized (listZza) {
            for (Throwable th2 : listZza) {
                System.err.print("Suppressed: ");
                th2.printStackTrace();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdoe
    public final void zza(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
        List<Throwable> listZza = this.zzhey.zza(th, false);
        if (listZza == null) {
            return;
        }
        synchronized (listZza) {
            for (Throwable th2 : listZza) {
                printWriter.print("Suppressed: ");
                th2.printStackTrace(printWriter);
            }
        }
    }
}
