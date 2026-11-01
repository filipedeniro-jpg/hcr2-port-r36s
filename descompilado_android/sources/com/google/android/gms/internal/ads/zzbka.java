package com.google.android.gms.internal.ads;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbka {
    public final List<? extends zzdcp<? extends zzbju>> zzfey;

    public zzbka(List<? extends zzdcp<? extends zzbju>> list) {
        this.zzfey = list;
    }

    public zzbka(zzbju zzbjuVar) {
        this.zzfey = Collections.singletonList(zzdcf.zzah(zzbjuVar));
    }

    public static zzcfk<zzbka> zza(@NonNull zzchn<? extends zzbju> zzchnVar) {
        return new zzcfj(zzchnVar, zzbjz.zzdoi);
    }

    public static zzcfk<zzbka> zza(@NonNull zzcfk<? extends zzbju> zzcfkVar) {
        return new zzcfj(zzcfkVar, zzbkc.zzdoi);
    }
}
