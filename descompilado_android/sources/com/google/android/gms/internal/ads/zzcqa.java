package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqa implements zzcrb<Bundle> {
    private final Bundle zzfgf;

    public zzcqa(Bundle bundle) {
        this.zzfgf = bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        Bundle bundle3 = this.zzfgf;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
    }
}
