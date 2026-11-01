package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdqf extends zzdon<Integer> implements zzdqi, zzdrz, RandomAccess {
    private static final zzdqf zzhky;
    private int size;
    private int[] zzhkz;

    public static zzdqf zzazw() {
        return zzhky;
    }

    zzdqf() {
        this(new int[10], 0);
    }

    private zzdqf(int[] iArr, int i) {
        this.zzhkz = iArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzaxf();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] iArr = this.zzhkz;
        System.arraycopy(iArr, i2, iArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdqf)) {
            return super.equals(obj);
        }
        zzdqf zzdqfVar = (zzdqf) obj;
        if (this.size != zzdqfVar.size) {
            return false;
        }
        int[] iArr = zzdqfVar.zzhkz;
        for (int i = 0; i < this.size; i++) {
            if (this.zzhkz[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzhkz[i2];
        }
        return i;
    }

    @Override // com.google.android.gms.internal.ads.zzdqk
    /* JADX INFO: renamed from: zzgp, reason: merged with bridge method [inline-methods] */
    public final zzdqi zzfm(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzdqf(Arrays.copyOf(this.zzhkz, i), this.size);
    }

    public final int getInt(int i) {
        zzfk(i);
        return this.zzhkz[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.ads.zzdqi
    public final void zzgq(int i) {
        zzam(this.size, i);
    }

    private final void zzam(int i, int i2) {
        int i3;
        zzaxf();
        if (i < 0 || i > (i3 = this.size)) {
            throw new IndexOutOfBoundsException(zzfl(i));
        }
        int[] iArr = this.zzhkz;
        if (i3 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i3 - i);
        } else {
            int[] iArr2 = new int[((i3 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.zzhkz, i, iArr2, i + 1, this.size - i);
            this.zzhkz = iArr2;
        }
        this.zzhkz[i] = i2;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zzaxf();
        zzdqe.checkNotNull(collection);
        if (!(collection instanceof zzdqf)) {
            return super.addAll(collection);
        }
        zzdqf zzdqfVar = (zzdqf) collection;
        int i = zzdqfVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        int[] iArr = this.zzhkz;
        if (i3 > iArr.length) {
            this.zzhkz = Arrays.copyOf(iArr, i3);
        }
        System.arraycopy(zzdqfVar.zzhkz, 0, this.zzhkz, this.size, zzdqfVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzaxf();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzhkz[i]))) {
                int[] iArr = this.zzhkz;
                System.arraycopy(iArr, i + 1, iArr, i, (this.size - i) - 1);
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
        int iIntValue = ((Integer) obj).intValue();
        zzaxf();
        zzfk(i);
        int[] iArr = this.zzhkz;
        int i2 = iArr[i];
        iArr[i] = iIntValue;
        return Integer.valueOf(i2);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzaxf();
        zzfk(i);
        int[] iArr = this.zzhkz;
        int i2 = iArr[i];
        int i3 = this.size;
        if (i < i3 - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (i3 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzam(i, ((Integer) obj).intValue());
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzdqf zzdqfVar = new zzdqf(new int[0], 0);
        zzhky = zzdqfVar;
        zzdqfVar.zzaxe();
    }
}
