package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcox implements zzcrb<Bundle> {
    private final Bundle zzges;

    public zzcox(Bundle bundle) {
        this.zzges = bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        bundle.putBundle("content_info", this.zzges);
    }
}
