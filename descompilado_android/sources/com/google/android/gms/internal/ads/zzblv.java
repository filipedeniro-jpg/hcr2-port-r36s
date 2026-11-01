package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzblv implements zzdvi<String> {
    private final zzblu zzfgi;

    private zzblv(zzblu zzbluVar) {
        this.zzfgi = zzbluVar;
    }

    public static zzblv zzg(zzblu zzbluVar) {
        return new zzblv(zzbluVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        return this.zzfgi.zzaft();
    }
}
