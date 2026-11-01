package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzccu implements zzdvi<ApplicationInfo> {
    private final zzdvv<Context> zzejn;

    private zzccu(zzdvv<Context> zzdvvVar) {
        this.zzejn = zzdvvVar;
    }

    public static zzccu zzaa(zzdvv<Context> zzdvvVar) {
        return new zzccu(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (ApplicationInfo) zzdvo.zza(this.zzejn.get().getApplicationInfo(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
