package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdpz extends zzdon<Float> implements zzdqk<Float>, zzdrz, RandomAccess {
    private static final zzdpz zzhjq;
    private int size;
    private float[] zzhjr;

    zzdpz() {
        this(new float[10], 0);
    }

    private zzdpz(float[] fArr, int i) {
        this.zzhjr = fArr;
        this.size = i;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zzaxf();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.zzhjr;
        System.arraycopy(fArr, i2, fArr, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdpz)) {
            return super.equals(obj);
        }
        zzdpz zzdpzVar = (zzdpz) obj;
        if (this.size != zzdpzVar.size) {
            return false;
        }
        float[] fArr = zzdpzVar.zzhjr;
        for (int i = 0; i < this.size; i++) {
            if (Float.floatToIntBits(this.zzhjr[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iFloatToIntBits = 1;
        for (int i = 0; i < this.size; i++) {
            iFloatToIntBits = (iFloatToIntBits * 31) + Float.floatToIntBits(this.zzhjr[i]);
        }
        return iFloatToIntBits;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzh(float f) {
        zzc(this.size, f);
    }

    private final void zzc(int i, float f) {
        int i2;
        zzaxf();
        if (i < 0 || i > (i2 = this.size)) {
            throw new IndexOutOfBoundsException(zzfl(i));
        }
        float[] fArr = this.zzhjr;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[((i2 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.zzhjr, i, fArr2, i + 1, this.size - i);
            this.zzhjr = fArr2;
        }
        this.zzhjr[i] = f;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zzaxf();
        zzdqe.checkNotNull(collection);
        if (!(collection instanceof zzdpz)) {
            return super.addAll(collection);
        }
        zzdpz zzdpzVar = (zzdpz) collection;
        int i = zzdpzVar.size;
        if (i == 0) {
            return false;
        }
        int i2 = this.size;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        float[] fArr = this.zzhjr;
        if (i3 > fArr.length) {
            this.zzhjr = Arrays.copyOf(fArr, i3);
        }
        System.arraycopy(zzdpzVar.zzhjr, 0, this.zzhjr, this.size, zzdpzVar.size);
        this.size = i3;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzaxf();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzhjr[i]))) {
                float[] fArr = this.zzhjr;
                System.arraycopy(fArr, i + 1, fArr, i, (this.size - i) - 1);
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
        float fFloatValue = ((Float) obj).floatValue();
        zzaxf();
        zzfk(i);
        float[] fArr = this.zzhjr;
        float f = fArr[i];
        fArr[i] = fFloatValue;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int i) {
        zzaxf();
        zzfk(i);
        float[] fArr = this.zzhjr;
        float f = fArr[i];
        int i2 = this.size;
        if (i < i2 - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (i2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.ads.zzdon, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Float) obj).floatValue());
    }

    @Override // com.google.android.gms.internal.ads.zzdqk
    public final /* synthetic */ zzdqk<Float> zzfm(int i) {
        if (i < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzdpz(Arrays.copyOf(this.zzhjr, i), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzfk(i);
        return Float.valueOf(this.zzhjr[i]);
    }

    static {
        zzdpz zzdpzVar = new zzdpz(new float[0], 0);
        zzhjq = zzdpzVar;
        zzdpzVar.zzaxe();
    }
}
