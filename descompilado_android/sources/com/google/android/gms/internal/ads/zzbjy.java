package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjy<AdT> implements zzbjv<AdT> {
    private final Map<String, zzcfk<AdT>> zzfex;

    zzbjy(Map<String, zzcfk<AdT>> map) {
        this.zzfex = map;
    }

    @Override // com.google.android.gms.internal.ads.zzbjv
    @Nullable
    public final zzcfk<AdT> zze(int i, String str) {
        return this.zzfex.get(str);
    }
}
