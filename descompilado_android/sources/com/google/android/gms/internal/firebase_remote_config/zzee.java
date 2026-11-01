package com.google.android.gms.internal.firebase_remote_config;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class zzee extends zzed {
    private final zzec zzix = new zzec();

    zzee() {
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzed
    public final void zza(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.zzix.zza(th, true).add(th2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzed
    public final void zza(Throwable th) {
        th.printStackTrace();
        List<Throwable> listZza = this.zzix.zza(th, false);
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
}
