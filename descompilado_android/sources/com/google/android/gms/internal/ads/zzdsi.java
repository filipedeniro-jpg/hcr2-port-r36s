package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsi extends zzdso {
    private final /* synthetic */ zzdsh zzhnt;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzdsi(zzdsh zzdshVar) {
        super(zzdshVar, null);
        this.zzhnt = zzdshVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdso, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzdsj(this.zzhnt, null);
    }

    /* synthetic */ zzdsi(zzdsh zzdshVar, zzdsg zzdsgVar) {
        this(zzdshVar);
    }
}
