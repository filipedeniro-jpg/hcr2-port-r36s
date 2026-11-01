package com.google.android.gms.internal.ads;

import java.io.InputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzrq extends zzaxf<InputStream> {
    private final /* synthetic */ zzrr zzbrg;

    zzrq(zzrr zzrrVar) {
        this.zzbrg = zzrrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaxf, java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        this.zzbrg.disconnect();
        return super.cancel(z);
    }
}
