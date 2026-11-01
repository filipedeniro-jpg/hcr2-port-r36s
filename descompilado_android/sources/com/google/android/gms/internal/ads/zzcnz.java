package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnz implements zzcrb<Bundle> {
    private final boolean zzged = false;
    private final boolean zzgee = false;
    private final boolean zzgef;

    public zzcnz(boolean z, boolean z2, boolean z3) {
        this.zzgef = z3;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        bundle2.putBoolean("c_pcbg", this.zzged);
        bundle2.putBoolean("c_phbg", this.zzgee);
        bundle2.putBoolean("ar_lr", this.zzgef);
    }
}
