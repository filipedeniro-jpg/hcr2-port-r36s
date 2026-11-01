package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbly implements zzdvi<zzcvi> {
    private final zzblu zzfgi;

    private zzbly(zzblu zzbluVar) {
        this.zzfgi = zzbluVar;
    }

    public static zzbly zzi(zzblu zzbluVar) {
        return new zzbly(zzbluVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        return this.zzfgi.zzafr();
    }
}
