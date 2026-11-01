package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcrl implements zzcrb<Bundle> {
    private final String zzdmf;
    private final int zzdmg;
    private final int zzdmh;
    private final int zzdmi;
    private final boolean zzdmj;
    private final int zzdmk;

    public zzcrl(String str, int i, int i2, int i3, boolean z, int i4) {
        this.zzdmf = str;
        this.zzdmg = i;
        this.zzdmh = i2;
        this.zzdmi = i3;
        this.zzdmj = z;
        this.zzdmk = i4;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        String str = this.zzdmf;
        zzcvq.zza(bundle2, "carrier", str, !TextUtils.isEmpty(str));
        zzcvq.zza(bundle2, "cnt", Integer.valueOf(this.zzdmg), this.zzdmg != -2);
        bundle2.putInt("gnt", this.zzdmh);
        bundle2.putInt("pt", this.zzdmi);
        Bundle bundleZza = zzcvq.zza(bundle2, "device");
        bundle2.putBundle("device", bundleZza);
        Bundle bundleZza2 = zzcvq.zza(bundleZza, "network");
        bundleZza.putBundle("network", bundleZza2);
        bundleZza2.putInt("active_network_state", this.zzdmk);
        bundleZza2.putBoolean("active_network_metered", this.zzdmj);
    }
}
