package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
class zzdai<E> extends zzdah<E> {
    int size;
    Object[] zzgol;
    boolean zzgom;

    zzdai(int i) {
        zzdae.zzf(i, "initialCapacity");
        this.zzgol = new Object[i];
        this.size = 0;
    }

    private final void zzdr(int i) {
        Object[] objArr = this.zzgol;
        if (objArr.length < i) {
            int length = objArr.length;
            if (i < 0) {
                throw new AssertionError("cannot store more than MAX_VALUE elements");
            }
            int iHighestOneBit = length + (length >> 1) + 1;
            if (iHighestOneBit < i) {
                iHighestOneBit = Integer.highestOneBit(i - 1) << 1;
            }
            if (iHighestOneBit < 0) {
                iHighestOneBit = Integer.MAX_VALUE;
            }
            this.zzgol = Arrays.copyOf(objArr, iHighestOneBit);
            this.zzgom = false;
            return;
        }
        if (this.zzgom) {
            this.zzgol = (Object[]) objArr.clone();
            this.zzgom = false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdah
    /* JADX INFO: renamed from: zzac, reason: merged with bridge method [inline-methods] */
    public zzdai<E> zzab(E e) {
        zzczx.checkNotNull(e);
        zzdr(this.size + 1);
        Object[] objArr = this.zzgol;
        int i = this.size;
        this.size = i + 1;
        objArr[i] = e;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdah
    public zzdah<E> zze(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            zzdr(this.size + collection.size());
            if (collection instanceof zzdaf) {
                this.size = ((zzdaf) collection).zza(this.zzgol, this.size);
                return this;
            }
        }
        super.zze(iterable);
        return this;
    }
}
