package com.facebook.ads.redexgen.X;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7t, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03227t<E> implements Cloneable {
    private static final Object F = new Object();
    private boolean B;
    private long[] C;
    private int D;
    private Object[] E;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    public C03227t() {
        this(10);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    private C03227t(int initialCapacity) {
        this.B = false;
        if (initialCapacity == 0) {
            this.C = C03197q.C;
            this.E = C03197q.D;
        } else {
            int iE = C03197q.E(initialCapacity);
            this.C = new long[iE];
            this.E = new Object[iE];
        }
        this.D = 0;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    public final void A() {
        int i = this.D;
        Object[] objArr = this.E;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.D = 0;
        this.B = false;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    private final long B(int index) {
        if (this.B) {
            E();
        }
        return this.C[index];
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    public final E B(long key) {
        return D(key, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final C03227t<E> clone() {
        C03227t<E> c03227t = null;
        try {
            c03227t = (C03227t) super.clone();
            c03227t.C = (long[]) this.C.clone();
            c03227t.E = (Object[]) this.E.clone();
            return c03227t;
        } catch (CloneNotSupportedException e) {
            return c03227t;
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    public final void C(long key, E e) {
        int iC = C03197q.C(this.C, this.D, key);
        if (iC >= 0) {
            this.E[iC] = e;
            return;
        }
        int iC2 = iC ^ (-1);
        if (iC2 < this.D && this.E[iC2] == F) {
            this.C[iC2] = key;
            this.E[iC2] = e;
            return;
        }
        if (this.B && this.D >= this.C.length) {
            E();
            iC2 = C03197q.C(this.C, this.D, key) ^ (-1);
        }
        if (this.D >= this.C.length) {
            int iE = C03197q.E(this.D + 1);
            long[] jArr = new long[iE];
            Object[] objArr = new Object[iE];
            System.arraycopy(this.C, 0, jArr, 0, this.C.length);
            System.arraycopy(this.E, 0, objArr, 0, this.E.length);
            this.C = jArr;
            this.E = objArr;
        }
        if (this.D - iC2 != 0) {
            System.arraycopy(this.C, iC2, this.C, iC2 + 1, this.D - iC2);
            System.arraycopy(this.E, iC2, this.E, iC2 + 1, this.D - iC2);
        }
        this.C[iC2] = key;
        this.E[iC2] = e;
        this.D++;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    private final E D(long j, E e) {
        int iC = C03197q.C(this.C, this.D, j);
        return (iC < 0 || this.E[iC] == F) ? e : (E) this.E[iC];
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    public final void D(int index) {
        if (this.E[index] != F) {
            this.E[index] = F;
            this.B = true;
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    /* JADX INFO: renamed from: E, reason: collision with other method in class */
    public final int m238E() {
        if (this.B) {
            E();
        }
        return this.D;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    private void E() {
        int i = this.D;
        int n = 0;
        long[] jArr = this.C;
        Object[] objArr = this.E;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = objArr[i2];
            if (obj != F) {
                if (i2 != n) {
                    jArr[n] = jArr[i2];
                    objArr[n] = obj;
                    objArr[i2] = null;
                }
                n++;
            }
        }
        this.B = false;
        this.D = n;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    public final E F(int i) {
        if (this.B) {
            E();
        }
        return (E) this.E[i];
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7t != com.facebook.ads.internal.androidx.support.v4.util.LongSparseArray<E> */
    public final String toString() {
        if (m238E() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.D * 28);
        sb.append('{');
        for (int i = 0; i < i; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            long key = B(i);
            sb.append(key);
            sb.append('=');
            Object value = F(i);
            if (value != this) {
                sb.append(value);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
