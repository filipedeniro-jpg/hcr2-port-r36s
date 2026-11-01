package com.google.android.gms.internal.ads;

import java.io.PrintWriter;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdok extends zzdoe {
    zzdok() {
    }

    @Override // com.google.android.gms.internal.ads.zzdoe
    public final void zza(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }

    @Override // com.google.android.gms.internal.ads.zzdoe
    public final void zzj(Throwable th) {
        th.printStackTrace();
    }

    @Override // com.google.android.gms.internal.ads.zzdoe
    public final void zza(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
    }
}
