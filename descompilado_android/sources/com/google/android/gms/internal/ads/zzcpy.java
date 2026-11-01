package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcpy implements zzcra<zzcrb<Bundle>>, zzcrb<Bundle> {
    private final ApplicationInfo applicationInfo;
    private final PackageInfo zzdij;

    zzcpy(ApplicationInfo applicationInfo, @Nullable PackageInfo packageInfo) {
        this.applicationInfo = applicationInfo;
        this.zzdij = packageInfo;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrb<Bundle>> zzalr() {
        return zzdcf.zzah(this);
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        String str = this.applicationInfo.packageName;
        PackageInfo packageInfo = this.zzdij;
        Integer numValueOf = packageInfo == null ? null : Integer.valueOf(packageInfo.versionCode);
        bundle2.putString("pn", str);
        if (numValueOf != null) {
            bundle2.putInt("vc", numValueOf.intValue());
        }
    }
}
