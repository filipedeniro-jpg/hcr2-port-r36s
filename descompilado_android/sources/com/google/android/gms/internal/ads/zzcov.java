package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcov implements zzcrb<Bundle> {
    private final String zzdjy;
    private final Bundle zzger;

    private zzcov(String str, Bundle bundle) {
        this.zzdjy = str;
        this.zzger = bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        bundle2.putString("consent_string", this.zzdjy);
        bundle2.putBundle("iab_consent_info", this.zzger);
    }
}
