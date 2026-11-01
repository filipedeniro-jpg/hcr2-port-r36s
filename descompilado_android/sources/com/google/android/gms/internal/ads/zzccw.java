package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccw implements zzdvi<PackageInfo> {
    private final zzdvv<Context> zzejn;
    private final zzdvv<ApplicationInfo> zzfty;

    private zzccw(zzdvv<Context> zzdvvVar, zzdvv<ApplicationInfo> zzdvvVar2) {
        this.zzejn = zzdvvVar;
        this.zzfty = zzdvvVar2;
    }

    public static zzccw zzad(zzdvv<Context> zzdvvVar, zzdvv<ApplicationInfo> zzdvvVar2) {
        return new zzccw(zzdvvVar, zzdvvVar2);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        return zzcck.zza(this.zzejn.get(), this.zzfty.get());
    }
}
