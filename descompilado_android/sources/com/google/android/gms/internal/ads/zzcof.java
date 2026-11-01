package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcof implements zzcrb<Bundle> {
    private final float zzdiy;
    private final int zzdlz;
    private final boolean zzdma;
    private final boolean zzdmb;
    private final int zzdmc;
    private final int zzdmd;
    private final int zzdme;
    private final boolean zzgeh;

    public zzcof(int i, boolean z, boolean z2, int i2, int i3, int i4, float f, boolean z3) {
        this.zzdlz = i;
        this.zzdma = z;
        this.zzdmb = z2;
        this.zzdmc = i2;
        this.zzdmd = i3;
        this.zzdme = i4;
        this.zzdiy = f;
        this.zzgeh = z3;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        bundle2.putInt("am", this.zzdlz);
        bundle2.putBoolean("ma", this.zzdma);
        bundle2.putBoolean("sp", this.zzdmb);
        bundle2.putInt("muv", this.zzdmc);
        bundle2.putInt("rm", this.zzdmd);
        bundle2.putInt("riv", this.zzdme);
        bundle2.putFloat("android_app_volume", this.zzdiy);
        bundle2.putBoolean("android_app_muted", this.zzgeh);
    }
}
