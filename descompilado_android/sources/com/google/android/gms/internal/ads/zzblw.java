package com.google.android.gms.internal.ads;

import android.os.Bundle;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblw implements zzdvi<Bundle> {
    private final zzblu zzfgi;

    private zzblw(zzblu zzbluVar) {
        this.zzfgi = zzbluVar;
    }

    public static zzblw zzh(zzblu zzbluVar) {
        return new zzblw(zzbluVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        return this.zzfgi.zzafs();
    }
}
