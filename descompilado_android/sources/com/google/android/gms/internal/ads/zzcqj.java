package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqj implements zzcrb<Bundle> {
    private final String zzgff;

    public zzcqj(String str) {
        this.zzgff = str;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        bundle.putString("rtb", this.zzgff);
    }
}
