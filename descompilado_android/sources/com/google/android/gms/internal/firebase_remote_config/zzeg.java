package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final class zzeg extends zzed {
    zzeg() {
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzed
    public final void zza(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzed
    public final void zza(Throwable th) {
        th.printStackTrace();
    }
}
