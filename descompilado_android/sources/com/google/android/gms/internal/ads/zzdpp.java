package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdpp extends zzdon<Double> implements zzdqk<Double>, zzdrz, RandomAccess {
    private static final zzdpp zzhgs;
    private int size;
    private double[] zzhgt;

    zzdpp() {
        this(new double[10], 0);
    }

    private zzdpp(double[] dArr, int i) {
        this.zzhgt = dArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzaxf();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.zzhgt;
        System.arraycopy(dArr, i2, dArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdpp)) {
            return super.equals(obj);
        }
        zzdpp zzdppVar = (zzdpp) obj;
        if (this.size != zzdppVar.size) {
            return false;
        }
        double[] dArr = zzdppVar.zzhgt;
        for (int i = 0; i < this.size; i++) {
            if (Double.doubleToLongBits(this.zzhgt[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iZzfk = 1;
        for (int i = 0; i < this.size; i++) {
            iZzfk = (iZzfk * 31) + zzdqe.zzfk(Double.doubleToLongBits(this.zzhgt[i]));
        }
        return iZzfk;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzd(double d) {
        zzd(this.size, d);
    }

    private final void zzd(int i, double d) {
        int i2;
        zzaxf();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzfl(i));
        }
        double[] dArr = this.zzhgt;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[((i2 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zzhgt, i, dArr2, i + 1, this.size - i);
            this.zzhgt = dArr2;
        }
        this.zzhgt[i] = d;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        zzaxf();
        zzdqe.checkNotNull(collection);
        if (!(collection instanceof zzdpp)) {
            return super.addAll(collection);
        }
        zzdpp zzdppVar = (zzdpp) collection;
        int i = zzdppVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        double[] dArr = this.zzhgt;
        if (i3 > dArr.length) {
            this.zzhgt = Arrays.copyOf(dArr, i3);
        }
        System.arraycopy(zzdppVar.zzhgt, 0, this.zzhgt, this.size, zzdppVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzaxf();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zzhgt[i]))) {
                double[] dArr = this.zzhgt;
                System.arraycopy(dArr, i + 1, dArr, i, (this.size - i) - 1);
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
        double dDoubleValue = ((Double) obj).doubleValue();
        zzaxf();
        zzfk(i);
        double[] dArr = this.zzhgt;
        double d = dArr[i];
        dArr[i] = dDoubleValue;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzaxf();
        zzfk(i);
        double[] dArr = this.zzhgt;
        double d = dArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzd(i, ((Double) obj).doubleValue());
    }

    @Override // com.google.android.gms.internal.ads.zzdqk
    public final /* synthetic */ zzdqk<Double> zzfm(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzdpp(Arrays.copyOf(this.zzhgt, i), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzfk(i);
        return Double.valueOf(this.zzhgt[i]);
    }

    static {
        zzdpp zzdppVar = new zzdpp(new double[0], 0);
        zzhgs = zzdppVar;
        zzdppVar.zzaxe();
    }
}
