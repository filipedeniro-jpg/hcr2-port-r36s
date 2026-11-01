package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzzx {
    private static final AtomicReference<zzzy> zzcux = new AtomicReference<>();
    private static final AtomicBoolean zzcuy = new AtomicBoolean();

    public static void zza(zzzy zzzyVar) {
        zzcux.set(zzzyVar);
    }
}
