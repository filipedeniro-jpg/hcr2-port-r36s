package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbtv {

    @Nullable
    private zzaan zzcwf;

    public zzbtv(zzbtn zzbtnVar) {
        this.zzcwf = zzbtnVar;
    }

    @Nullable
    public final synchronized zzaan zzqt() {
        return this.zzcwf;
    }

    public final synchronized void zza(@Nullable zzaan zzaanVar) {
        this.zzcwf = zzaanVar;
    }
}
