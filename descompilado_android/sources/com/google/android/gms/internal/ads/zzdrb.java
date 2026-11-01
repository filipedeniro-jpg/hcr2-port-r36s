package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdrb extends zzdon<Long> implements zzdqk<Long>, zzdrz, RandomAccess {
    private static final zzdrb zzhmc;
    private int size;
    private long[] zzhmd;

    zzdrb() {
        this(new long[10], 0);
    }

    private zzdrb(long[] jArr, int i) {
        this.zzhmd = jArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzaxf();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        long[] jArr = this.zzhmd;
        System.arraycopy(jArr, i2, jArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdrb)) {
            return super.equals(obj);
        }
        zzdrb zzdrbVar = (zzdrb) obj;
        if (this.size != zzdrbVar.size) {
            return false;
        }
        long[] jArr = zzdrbVar.zzhmd;
        for (int i = 0; i < this.size; i++) {
            if (this.zzhmd[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZzfk = 1;
        for (int i = 0; i < this.size; i++) {
            iZzfk = (iZzfk * 31) + zzdqe.zzfk(this.zzhmd[i]);
        }
        return iZzfk;
    }

    public final long getLong(int i) {
        zzfk(i);
        return this.zzhmd[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzfl(long j) {
        zzq(this.size, j);
    }

    private final void zzq(int i, long j) {
        int i2;
        zzaxf();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzfl(i));
        }
        long[] jArr = this.zzhmd;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[((i2 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.zzhmd, i, jArr2, i + 1, this.size - i);
            this.zzhmd = jArr2;
        }
        this.zzhmd[i] = j;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzaxf();
        zzdqe.checkNotNull(collection);
        if (!(collection instanceof zzdrb)) {
            return super.addAll(collection);
        }
        zzdrb zzdrbVar = (zzdrb) collection;
        int i = zzdrbVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        long[] jArr = this.zzhmd;
        if (i3 > jArr.length) {
            this.zzhmd = Arrays.copyOf(jArr, i3);
        }
        System.arraycopy(zzdrbVar.zzhmd, 0, this.zzhmd, this.size, zzdrbVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzaxf();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzhmd[i]))) {
                long[] jArr = this.zzhmd;
                System.arraycopy(jArr, i + 1, jArr, i, (this.size - i) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzfk(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzfl(i));
        }
    }

    private final String zzfl(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int i, Object obj) {
        long jLongValue = ((Long) obj).longValue();
        zzaxf();
        zzfk(i);
        long[] jArr = this.zzhmd;
        long j = jArr[i];
        jArr[i] = jLongValue;
        return Long.valueOf(j);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzaxf();
        zzfk(i);
        long[] jArr = this.zzhmd;
        long j = jArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzq(i, ((Long) obj).longValue());
    }

    @Override // com.google.android.gms.internal.ads.zzdqk
    public final /* synthetic */ zzdqk<Long> zzfm(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzdrb(Arrays.copyOf(this.zzhmd, i), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzdrb zzdrbVar = new zzdrb(new long[0], 0);
        zzhmc = zzdrbVar;
        zzdrbVar.zzaxe();
    }
}
