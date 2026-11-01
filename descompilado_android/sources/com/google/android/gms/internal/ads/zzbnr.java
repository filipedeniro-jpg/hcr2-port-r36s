package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.AdMetadataListener;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbnr implements zzdvi<zzbnp> {
    private final zzdvv<Set<zzbqc<AdMetadataListener>>> zzfdr;

    private zzbnr(zzdvv<Set<zzbqc<AdMetadataListener>>> zzdvvVar) {
        this.zzfdr = zzdvvVar;
    }

    public static zzbnr zzl(zzdvv<Set<zzbqc<AdMetadataListener>>> zzdvvVar) {
        return new zzbnr(zzdvvVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return new zzbnp(this.zzfdr.get());
    }
}
