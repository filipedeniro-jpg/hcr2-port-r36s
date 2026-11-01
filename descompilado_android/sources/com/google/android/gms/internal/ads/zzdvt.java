package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdvt<T> {
    private final List<zzdvv<T>> zzhxf;
    private final List<zzdvv<Collection<T>>> zzhxg;

    private zzdvt(int i, int i2) {
        this.zzhxf = zzdvh.zzhl(i);
        this.zzhxg = zzdvh.zzhl(i2);
    }

    public final zzdvt<T> zzap(zzdvv<? extends T> zzdvvVar) {
        this.zzhxf.add(zzdvvVar);
        return this;
    }

    public final zzdvt<T> zzaq(zzdvv<? extends Collection<? extends T>> zzdvvVar) {
        this.zzhxg.add(zzdvvVar);
        return this;
    }

    public final zzdvr<T> zzbdb() {
        return new zzdvr<>(this.zzhxf, this.zzhxg);
    }
}
