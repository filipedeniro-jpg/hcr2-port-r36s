package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbru implements zzdvi<zzbbc> {
    private final zzbrd zzfia;

    private zzbru(zzbrd zzbrdVar) {
        this.zzfia = zzbrdVar;
    }

    public static zzbru zzc(zzbrd zzbrdVar) {
        return new zzbru(zzbrdVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    @Nullable
    public final /* synthetic */ Object get() {
        return this.zzfia.zzaek();
    }
}
