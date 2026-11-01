package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdor extends zzdon<Boolean> implements zzdqk<Boolean>, zzdrz, RandomAccess {
    private static final zzdor zzhfh;
    private int size;
    private boolean[] zzhfi;

    zzdor() {
        this(new boolean[10], 0);
    }

    private zzdor(boolean[] zArr, int i) {
        this.zzhfi = zArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzaxf();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        boolean[] zArr = this.zzhfi;
        System.arraycopy(zArr, i2, zArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdor)) {
            return super.equals(obj);
        }
        zzdor zzdorVar = (zzdor) obj;
        if (this.size != zzdorVar.size) {
            return false;
        }
        boolean[] zArr = zzdorVar.zzhfi;
        for (int i = 0; i < this.size; i++) {
            if (this.zzhfi[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZzbj = 1;
        for (int i = 0; i < this.size; i++) {
            iZzbj = (iZzbj * 31) + zzdqe.zzbj(this.zzhfi[i]);
        }
        return iZzbj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void addBoolean(boolean z) {
        zzg(this.size, z);
    }

    private final void zzg(int i, boolean z) {
        int i2;
        zzaxf();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzfl(i));
        }
        boolean[] zArr = this.zzhfi;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[((i2 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.zzhfi, i, zArr2, i + 1, this.size - i);
            this.zzhfi = zArr2;
        }
        this.zzhfi[i] = z;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzaxf();
        zzdqe.checkNotNull(collection);
        if (!(collection instanceof zzdor)) {
            return super.addAll(collection);
        }
        zzdor zzdorVar = (zzdor) collection;
        int i = zzdorVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        boolean[] zArr = this.zzhfi;
        if (i3 > zArr.length) {
            this.zzhfi = Arrays.copyOf(zArr, i3);
        }
        System.arraycopy(zzdorVar.zzhfi, 0, this.zzhfi, this.size, zzdorVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzaxf();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzhfi[i]))) {
                boolean[] zArr = this.zzhfi;
                System.arraycopy(zArr, i + 1, zArr, i, (this.size - i) - 1);
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
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        zzaxf();
        zzfk(i);
        boolean[] zArr = this.zzhfi;
        boolean z = zArr[i];
        zArr[i] = zBooleanValue;
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzaxf();
        zzfk(i);
        boolean[] zArr = this.zzhfi;
        boolean z = zArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzg(i, ((Boolean) obj).booleanValue());
    }

    @Override // com.google.android.gms.internal.ads.zzdqk
    public final /* synthetic */ zzdqk<Boolean> zzfm(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzdor(Arrays.copyOf(this.zzhfi, i), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzfk(i);
        return Boolean.valueOf(this.zzhfi[i]);
    }

    static {
        zzdor zzdorVar = new zzdor(new boolean[0], 0);
        zzhfh = zzdorVar;
        zzdorVar.zzaxe();
    }
}
