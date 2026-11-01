package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzctb implements zzdvi<ApplicationInfo> {
    private final zzcsz zzghc;

    public zzctb(zzcsz zzcszVar) {
        this.zzghc = zzcszVar;
    }

    public static ApplicationInfo zzc(zzcsz zzcszVar) {
        return (ApplicationInfo) zzdvo.zza(zzcszVar.zzamm(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zzc(this.zzghc);
    }
}
