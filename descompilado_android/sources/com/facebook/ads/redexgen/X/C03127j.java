package com.facebook.ads.redexgen.X;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7j, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C03127j<K, V> {
    public static Object[] E;
    public static int F;
    public static Object[] G;
    public static int H;
    public int[] C = C03197q.B;
    public Object[] B = C03197q.D;
    public int D = 0;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final void A(int minimumCapacity) {
        int i = this.D;
        if (this.C.length < minimumCapacity) {
            int[] iArr = this.C;
            Object[] objArr = this.B;
            D(minimumCapacity);
            if (this.D > 0) {
                System.arraycopy(iArr, 0, this.C, 0, i);
                System.arraycopy(objArr, 0, this.B, 0, i << 1);
            }
            F(iArr, objArr, i);
        }
        if (this.D != i) {
            throw new ConcurrentModificationException();
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    private final int B() {
        int end = this.D;
        if (end == 0) {
            return -1;
        }
        int iE = E(this.C, end, 0);
        if (iE < 0 || this.B[iE << 1] == null) {
            return iE;
        }
        int i = iE + 1;
        while (i < end && this.C[i] == 0) {
            if (this.B[i << 1] == null) {
                return i;
            }
            i++;
        }
        for (int i2 = iE - 1; i2 >= 0 && this.C[i2] == 0; i2--) {
            if (this.B[i2 << 1] == null) {
                return i2;
            }
        }
        int i3 = i ^ (-1);
        return i3;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final int B(Object key) {
        return key == null ? B() : C(key, key.hashCode());
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final int C(Object value) {
        int i = this.D * 2;
        Object[] objArr = this.B;
        if (value == null) {
            for (int i2 = 1; i2 < i; i2 += 2) {
                if (objArr[i2] == null) {
                    int i3 = i2 >> 1;
                    return i3;
                }
            }
        } else {
            for (int i4 = 1; i4 < i; i4 += 2) {
                if (value.equals(objArr[i4])) {
                    int i5 = i4 >> 1;
                    return i5;
                }
            }
        }
        return -1;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    private final int C(Object key, int hash) {
        int index = this.D;
        if (index == 0) {
            return -1;
        }
        int iE = E(this.C, index, hash);
        if (iE < 0 || key.equals(this.B[iE << 1])) {
            return iE;
        }
        int i = iE + 1;
        while (i < index && this.C[i] == hash) {
            if (key.equals(this.B[i << 1])) {
                return i;
            }
            i++;
        }
        for (int i2 = iE - 1; i2 >= 0 && this.C[i2] == hash; i2--) {
            if (key.equals(this.B[i2 << 1])) {
                return i2;
            }
        }
        return i ^ (-1);
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    /* JADX INFO: renamed from: D, reason: collision with other method in class */
    public final K m237D(int i) {
        return (K) this.B[i << 1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    void D(int size) {
        if (size == 8) {
            synchronized (C03137k.class) {
                if (G != null) {
                    Object[] objArr = G;
                    this.B = objArr;
                    G = (Object[]) objArr[0];
                    this.C = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    H--;
                    return;
                }
            }
        } else if (size == 4) {
            synchronized (C03137k.class) {
                if (E != null) {
                    Object[] objArr2 = E;
                    this.B = objArr2;
                    E = (Object[]) objArr2[0];
                    this.C = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    F--;
                    return;
                }
            }
        }
        this.C = new int[size];
        this.B = new Object[size << 1];
    }

    private static int E(int[] hashes, int N, int hash) {
        try {
            return C03197q.B(hashes, N, hash);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final V E(int i) {
        int i2;
        V v = (V) this.B[(i << 1) + 1];
        int i3 = this.D;
        if (i3 <= 1) {
            F(this.C, this.B, i3);
            this.C = C03197q.B;
            this.B = C03197q.D;
            i2 = 0;
        } else {
            i2 = i3 - 1;
            if (this.C.length > 8 && this.D < this.C.length / 3) {
                int i4 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArr = this.C;
                Object[] objArr = this.B;
                D(i4);
                if (i3 != this.D) {
                    throw new ConcurrentModificationException();
                }
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.C, 0, i);
                    System.arraycopy(objArr, 0, this.B, 0, i << 1);
                }
                if (i < i2) {
                    System.arraycopy(iArr, i + 1, this.C, i, i2 - i);
                    System.arraycopy(objArr, (i + 1) << 1, this.B, i << 1, (i2 - i) << 1);
                }
            } else {
                if (i < i2) {
                    System.arraycopy(this.C, i + 1, this.C, i, i2 - i);
                    System.arraycopy(this.B, (i + 1) << 1, this.B, i << 1, (i2 - i) << 1);
                }
                this.B[i2 << 1] = null;
                this.B[(i2 << 1) + 1] = null;
            }
        }
        if (i3 != this.D) {
            throw new ConcurrentModificationException();
        }
        this.D = i2;
        return v;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final V F(int i, V v) {
        int i2 = (i << 1) + 1;
        V v2 = (V) this.B[i2];
        this.B[i2] = v;
        return v2;
    }

    private static void F(int[] hashes, Object[] array, int size) {
        if (hashes.length == 8) {
            synchronized (C03137k.class) {
                if (H < 10) {
                    array[0] = G;
                    array[1] = hashes;
                    for (int i = (size << 1) - 1; i >= 2; i--) {
                        array[i] = null;
                    }
                    G = array;
                    H++;
                }
            }
            return;
        }
        if (hashes.length == 4) {
            synchronized (C03137k.class) {
                if (F < 10) {
                    array[0] = E;
                    array[1] = hashes;
                    for (int i2 = (size << 1) - 1; i2 >= 2; i2--) {
                        array[i2] = null;
                    }
                    E = array;
                    F++;
                }
            }
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final V G(int i) {
        return (V) this.B[(i << 1) + 1];
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final void clear() {
        if (this.D > 0) {
            int[] iArr = this.C;
            Object[] objArr = this.B;
            int i = this.D;
            int[] ohashes = C03197q.B;
            this.C = ohashes;
            this.B = C03197q.D;
            this.D = 0;
            F(iArr, objArr, i);
        }
        if (this.D > 0) {
            throw new ConcurrentModificationException();
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final boolean containsKey(Object key) {
        return B(key) >= 0;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final boolean containsValue(Object value) {
        return C(value) >= 0;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<?, ?> */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof C03127j) {
            C03127j c03127j = (C03127j) object;
            if (size() != c03127j.size()) {
                return false;
            }
            for (int i = 0; i < this.D; i++) {
                try {
                    K kM237D = m237D(i);
                    V vG = G(i);
                    Object obj = c03127j.get(kM237D);
                    if (vG == null) {
                        if (obj == null && c03127j.containsKey(kM237D)) {
                        }
                        return false;
                    }
                    if (!vG.equals(obj)) {
                        return false;
                    }
                } catch (ClassCastException e) {
                    return false;
                } catch (NullPointerException e2) {
                    return false;
                }
            }
            return true;
        }
        if (!(object instanceof Map)) {
            return false;
        }
        Map map = (Map) object;
        if (size() != map.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.D; i2++) {
            try {
                K kM237D2 = m237D(i2);
                V vG2 = G(i2);
                Object obj2 = map.get(kM237D2);
                if (vG2 == null) {
                    if (obj2 == null && map.containsKey(kM237D2)) {
                    }
                    return false;
                }
                if (!vG2.equals(obj2)) {
                    return false;
                }
            } catch (ClassCastException e3) {
                return false;
            } catch (NullPointerException e4) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final V get(Object obj) {
        int iB = B(obj);
        if (iB >= 0) {
            return (V) this.B[(iB << 1) + 1];
        }
        return null;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final int hashCode() {
        int[] hashes = this.C;
        Object[] objArr = this.B;
        int result = 0;
        int s = 0;
        int v = 1;
        int i = this.D;
        while (s < i) {
            Object obj = objArr[v];
            result += (obj == null ? 0 : obj.hashCode()) ^ hashes[s];
            s++;
            v += 2;
        }
        return result;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final boolean isEmpty() {
        return this.D <= 0;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final V put(K k, V v) {
        int iHashCode;
        int iC;
        int i = 8;
        int i2 = this.D;
        if (k == null) {
            iHashCode = 0;
            iC = B();
        } else {
            iHashCode = k.hashCode();
            iC = C(k, iHashCode);
        }
        if (iC >= 0) {
            int i3 = (iC << 1) + 1;
            V v2 = (V) this.B[i3];
            this.B[i3] = v;
            return v2;
        }
        int i4 = iC ^ (-1);
        if (i2 >= this.C.length) {
            if (i2 >= 8) {
                i = i2 + (i2 >> 1);
            } else if (i2 < 4) {
                i = 4;
            }
            int[] iArr = this.C;
            Object[] objArr = this.B;
            D(i);
            if (i2 != this.D) {
                throw new ConcurrentModificationException();
            }
            if (this.C.length > 0) {
                System.arraycopy(iArr, 0, this.C, 0, iArr.length);
                System.arraycopy(objArr, 0, this.B, 0, objArr.length);
            }
            F(iArr, objArr, i2);
        }
        if (i4 < i2) {
            System.arraycopy(this.C, i4, this.C, i4 + 1, i2 - i4);
            System.arraycopy(this.B, i4 << 1, this.B, (i4 + 1) << 1, (this.D - i4) << 1);
        }
        if (i2 != this.D || i4 >= this.C.length) {
            throw new ConcurrentModificationException();
        }
        this.C[i4] = iHashCode;
        this.B[i4 << 1] = k;
        this.B[(i4 << 1) + 1] = v;
        this.D++;
        return null;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final V remove(Object key) {
        int iB = B(key);
        if (iB >= 0) {
            return E(iB);
        }
        return null;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final int size() {
        return this.D;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.7j != com.facebook.ads.internal.androidx.support.v4.util.SimpleArrayMap<K, V> */
    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.D * 28);
        sb.append('{');
        for (int i = 0; i < i; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object key = m237D(i);
            if (key != this) {
                sb.append(key);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object key2 = G(i);
            if (key2 != this) {
                sb.append(key2);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
