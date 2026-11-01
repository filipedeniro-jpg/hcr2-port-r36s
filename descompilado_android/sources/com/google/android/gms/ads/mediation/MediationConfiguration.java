package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import com.google.android.gms.ads.AdFormat;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class MediationConfiguration {
    private final Bundle zzehx;
    private final AdFormat zzeib;

    public MediationConfiguration(AdFormat adFormat, Bundle bundle) {
        this.zzeib = adFormat;
        this.zzehx = bundle;
    }

    public AdFormat getFormat() {
        return this.zzeib;
    }

    public Bundle getServerParameters() {
        return this.zzehx;
    }
}
