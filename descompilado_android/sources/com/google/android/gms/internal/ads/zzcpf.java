package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpf implements zzcrb<Bundle> {
    private final Bundle zzdjh;

    public zzcpf(Bundle bundle) {
        this.zzdjh = bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        Bundle bundleZza = zzcvq.zza(bundle2, "device");
        bundleZza.putBundle("android_mem_info", this.zzdjh);
        bundle2.putBundle("device", bundleZza);
    }
}
