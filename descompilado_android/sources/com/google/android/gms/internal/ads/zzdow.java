package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdow extends zzdoy {
    private final int limit;
    private int position = 0;
    private final /* synthetic */ zzdot zzhfq;

    zzdow(zzdot zzdotVar) {
        this.zzhfq = zzdotVar;
        this.limit = this.zzhfq.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.position < this.limit;
    }

    @Override // com.google.android.gms.internal.ads.zzdpc
    public final byte nextByte() {
        int i = this.position;
        if (i >= this.limit) {
            throw new NoSuchElementException();
        }
        this.position = i + 1;
        return this.zzhfq.zzfo(i);
    }
}
