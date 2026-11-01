package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzdan<E> extends zzdaf<E> implements Set<E> {

    @NullableDecl
    private transient zzdak<E> zzgoq;

    public static <E> zzdan<E> zzae(E e) {
        return new zzdbc(e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzu(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    boolean zzaol() {
        return false;
    }

    @SafeVarargs
    public static <E> zzdan<E> zza(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        zzczx.checkArgument(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        Object[] objArr = new Object[eArr.length + 6];
        objArr[0] = e;
        objArr[1] = e2;
        objArr[2] = e3;
        objArr[3] = e4;
        objArr[4] = e5;
        objArr[5] = e6;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return zza(objArr.length, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> zzdan<E> zza(int i, Object... objArr) {
        while (i != 0) {
            if (i == 1) {
                return zzae(objArr[0]);
            }
            int iZzds = zzds(i);
            Object[] objArr2 = new Object[iZzds];
            int i2 = iZzds - 1;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                Object objZzd = zzdar.zzd(objArr[i5], i5);
                int iHashCode = objZzd.hashCode();
                int iZzdq = zzdag.zzdq(iHashCode);
                while (true) {
                    int i6 = iZzdq & i2;
                    Object obj = objArr2[i6];
                    if (obj == null) {
                        objArr[i4] = objZzd;
                        objArr2[i6] = objZzd;
                        i3 += iHashCode;
                        i4++;
                        break;
                    }
                    if (obj.equals(objZzd)) {
                        break;
                    }
                    iZzdq++;
                }
            }
            Arrays.fill(objArr, i4, i, (Object) null);
            if (i4 == 1) {
                return new zzdbc(objArr[0], i3);
            }
            if (zzds(i4) >= iZzds / 2) {
                if (zzu(i4, objArr.length)) {
                    objArr = Arrays.copyOf(objArr, i4);
                }
                return new zzdba(objArr, i3, objArr2, i2, i4);
            }
            i = i4;
        }
        return zzdba.zzgpg;
    }

    static int zzds(int i) {
        int iMax = Math.max(i, 2);
        if (iMax < 751619276) {
            int iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
            while (true) {
                double d = iHighestOneBit;
                Double.isNaN(d);
                if (d * 0.7d >= iMax) {
                    return iHighestOneBit;
                }
                iHighestOneBit <<= 1;
            }
        } else {
            zzczx.checkArgument(iMax < 1073741824, "collection too large");
            return 1073741824;
        }
    }

    zzdan() {
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzdan) && zzaol() && ((zzdan) obj).zzaol() && hashCode() != obj.hashCode()) {
            return false;
        }
        return zzdaz.zza(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzdaz.zzf(this);
    }

    @Override // com.google.android.gms.internal.ads.zzdaf
    public zzdak<E> zzaoi() {
        zzdak<E> zzdakVar = this.zzgoq;
        if (zzdakVar != null) {
            return zzdakVar;
        }
        zzdak<E> zzdakVarZzaom = zzaom();
        this.zzgoq = zzdakVarZzaom;
        return zzdakVarZzaom;
    }

    zzdak<E> zzaom() {
        return zzdak.zzc(toArray());
    }

    public static <E> zzdaq<E> zzdt(int i) {
        zzdae.zzf(i, "expectedSize");
        return new zzdaq<>(i);
    }

    @Override // com.google.android.gms.internal.ads.zzdaf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
