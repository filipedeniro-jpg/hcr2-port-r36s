package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqb implements zzdvi<zzcpy> {
    private final zzdvv<PackageInfo> zzftm;
    private final zzdvv<ApplicationInfo> zzfty;

    private zzcqb(zzdvv<ApplicationInfo> zzdvvVar, zzdvv<PackageInfo> zzdvvVar2) {
        this.zzfty = zzdvvVar;
        this.zzftm = zzdvvVar2;
    }

    public static zzcqb zzaq(zzdvv<ApplicationInfo> zzdvvVar, zzdvv<PackageInfo> zzdvvVar2) {
        return new zzcqb(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzcpy(this.zzfty.get(), this.zzftm.get());
    }
}
