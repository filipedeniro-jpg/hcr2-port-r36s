package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzgb extends Exception {
    private final int type;
    private final int zzace;

    public static zzgb zza(Exception exc, int i) {
        return new zzgb(1, null, exc, i);
    }

    public static zzgb zza(IOException iOException) {
        return new zzgb(0, null, iOException, -1);
    }

    static zzgb zza(RuntimeException runtimeException) {
        return new zzgb(2, null, runtimeException, -1);
    }

    private zzgb(int i, String str, Throwable th, int i2) {
        super(null, th);
        this.type = i;
        this.zzace = i2;
    }
}
