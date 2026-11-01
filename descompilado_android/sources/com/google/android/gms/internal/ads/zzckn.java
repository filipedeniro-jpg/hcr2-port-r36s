package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzckn implements zzdvi<zzzn> {
    private final zzcko zzgan;

    private zzckn(zzcko zzckoVar) {
        this.zzgan = zzckoVar;
    }

    public static zzckn zzc(zzcko zzckoVar) {
        return new zzckn(zzckoVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        return this.zzgan.zzakt();
    }
}
