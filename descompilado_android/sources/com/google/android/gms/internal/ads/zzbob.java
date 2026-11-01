package com.google.android.gms.internal.ads;

import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbob implements zzdvi<zzbnu> {
    private final zzdvv<Set<zzbqc<com.google.android.gms.ads.internal.overlay.zzp>>> zzfdr;

    private zzbob(zzdvv<Set<zzbqc<com.google.android.gms.ads.internal.overlay.zzp>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbob zzn(zzdvv<Set<zzbqc<com.google.android.gms.ads.internal.overlay.zzp>>> zzdvvVar) {
        return new zzbob(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbnu(this.zzfdr.get());
    }
}
