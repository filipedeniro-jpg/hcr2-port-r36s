package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcoj implements zzcrb<Bundle> {
    private final double zzdml;
    private final boolean zzdmm;

    public zzcoj(double d, boolean z) {
        this.zzdml = d;
        this.zzdmm = z;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        Bundle bundleZza = zzcvq.zza(bundle2, "device");
        bundle2.putBundle("device", bundleZza);
        Bundle bundleZza2 = zzcvq.zza(bundleZza, "battery");
        bundleZza.putBundle("battery", bundleZza2);
        bundleZza2.putBoolean("is_charging", this.zzdmm);
        bundleZza2.putDouble("battery_level", this.zzdml);
    }
}
