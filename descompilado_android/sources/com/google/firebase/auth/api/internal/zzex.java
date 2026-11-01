package com.google.firebase.auth.api.internal;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzex implements Runnable {
    private final /* synthetic */ zzfa zza;
    private final /* synthetic */ zzeu zzb;

    zzex(zzeu zzeuVar, zzfa zzfaVar) {
        this.zzb = zzeuVar;
        this.zza = zzfaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zza.zzi) {
            if (!this.zzb.zza.zzi.isEmpty()) {
                this.zza.zza(this.zzb.zza.zzi.get(0), new Object[0]);
            }
        }
    }
}
