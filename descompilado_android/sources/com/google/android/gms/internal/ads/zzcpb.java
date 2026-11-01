package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpb implements zzcrb<Bundle> {
    private final String zzdji;
    private final boolean zzdjk;

    public zzcpb(String str, boolean z) {
        this.zzdji = str;
        this.zzdjk = z;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        bundle2.putString("gct", this.zzdji);
        if (this.zzdjk) {
            bundle2.putString("de", "1");
        }
    }
}
