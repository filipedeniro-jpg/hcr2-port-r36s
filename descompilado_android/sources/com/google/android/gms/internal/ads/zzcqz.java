package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzcqz implements zzcrb {
    static final zzcrb zzgft = new zzcqz();

    private zzcqz() {
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final void zzr(Object obj) {
        ((Bundle) obj).putBoolean("sdk_prefetch", true);
    }
}
