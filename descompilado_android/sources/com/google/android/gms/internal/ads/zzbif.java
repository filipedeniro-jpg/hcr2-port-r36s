package com.google.android.gms.internal.ads;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbif implements zzdvi<View> {
    private final zzbib zzfdq;

    public zzbif(zzbib zzbibVar) {
        this.zzfdq = zzbibVar;
    }

    public static View zza(zzbib zzbibVar) {
        return (View) zzdvo.zza(zzbibVar.zzaer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return zza(this.zzfdq);
    }
}
