package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbri implements zzdvi<View> {
    private final zzbrd zzfia;

    private zzbri(zzbrd zzbrdVar) {
        this.zzfia = zzbrdVar;
    }

    public static zzbri zza(zzbrd zzbrdVar) {
        return new zzbri(zzbrdVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        return this.zzfia.zzagr();
    }
}
