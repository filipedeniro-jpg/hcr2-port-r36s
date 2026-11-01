package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.util.Arrays;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzdat<K, V> extends zzdao<K, V> {
    private static final zzdao<Object, Object> zzgoy = new zzdat(null, new Object[0], 0);
    private final transient int size;
    private final transient Object zzgoz;
    private final transient Object[] zzgpa;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4, types: [int[]] */
    static <K, V> zzdat<K, V> zzc(int i, Object[] objArr) {
        byte[] bArr;
        int i2;
        int i3;
        int i4;
        zzczx.zzs(5, objArr.length >> 1);
        int iZzds = zzdan.zzds(5);
        int i5 = iZzds - 1;
        int i6 = 0;
        if (iZzds <= 128) {
            bArr = new byte[iZzds];
            Arrays.fill(bArr, (byte) -1);
            while (i6 < 5) {
                int i7 = i6 * 2;
                Object obj = objArr[i7];
                Object obj2 = objArr[i7 ^ 1];
                zzdae.zzb(obj, obj2);
                int iZzdq = zzdag.zzdq(obj.hashCode());
                while (true) {
                    i4 = iZzdq & i5;
                    int i8 = bArr[i4] & 255;
                    if (i8 == 255) {
                        break;
                    }
                    if (objArr[i8].equals(obj)) {
                        throw zza(obj, obj2, objArr, i8);
                    }
                    iZzdq = i4 + 1;
                }
                bArr[i4] = (byte) i7;
                i6++;
            }
        } else if (iZzds <= 32768) {
            bArr = new short[iZzds];
            Arrays.fill(bArr, (short) -1);
            while (i6 < 5) {
                int i9 = i6 * 2;
                Object obj3 = objArr[i9];
                Object obj4 = objArr[i9 ^ 1];
                zzdae.zzb(obj3, obj4);
                int iZzdq2 = zzdag.zzdq(obj3.hashCode());
                while (true) {
                    i3 = iZzdq2 & i5;
                    int i10 = bArr[i3] & 65535;
                    if (i10 == 65535) {
                        break;
                    }
                    if (objArr[i10].equals(obj3)) {
                        throw zza(obj3, obj4, objArr, i10);
                    }
                    iZzdq2 = i3 + 1;
                }
                bArr[i3] = (short) i9;
                i6++;
            }
        } else {
            bArr = new int[iZzds];
            Arrays.fill((int[]) bArr, -1);
            while (i6 < 5) {
                int i11 = i6 * 2;
                Object obj5 = objArr[i11];
                Object obj6 = objArr[i11 ^ 1];
                zzdae.zzb(obj5, obj6);
                int iZzdq3 = zzdag.zzdq(obj5.hashCode());
                while (true) {
                    i2 = iZzdq3 & i5;
                    char c = bArr[i2];
                    if (c == -1) {
                        break;
                    }
                    if (objArr[c].equals(obj5)) {
                        throw zza(obj5, obj6, objArr, c);
                    }
                    iZzdq3 = i2 + 1;
                }
                bArr[i2] = i11;
                i6++;
            }
        }
        return new zzdat<>(bArr, objArr, 5);
    }

    private static IllegalArgumentException zza(Object obj, Object obj2, Object[] objArr, int i) {
        String strValueOf = String.valueOf(obj);
        String strValueOf2 = String.valueOf(obj2);
        String strValueOf3 = String.valueOf(objArr[i]);
        String strValueOf4 = String.valueOf(objArr[i ^ 1]);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 39 + String.valueOf(strValueOf2).length() + String.valueOf(strValueOf3).length() + String.valueOf(strValueOf4).length());
        sb.append("Multiple entries with same key: ");
        sb.append(strValueOf);
        sb.append(Constants.RequestParameters.EQUAL);
        sb.append(strValueOf2);
        sb.append(" and ");
        sb.append(strValueOf3);
        sb.append(Constants.RequestParameters.EQUAL);
        sb.append(strValueOf4);
        return new IllegalArgumentException(sb.toString());
    }

    private zzdat(Object obj, Object[] objArr, int i) {
        this.zzgoz = obj;
        this.zzgpa = objArr;
        this.size = i;
    }

    @Override // java.util.Map
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.ads.zzdao, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        Object obj2 = this.zzgoz;
        Object[] objArr = this.zzgpa;
        int i = this.size;
        if (obj == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[0].equals(obj)) {
                return (V) objArr[1];
            }
            return null;
        }
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof byte[]) {
            byte[] bArr = (byte[]) obj2;
            int length = bArr.length - 1;
            int iZzdq = zzdag.zzdq(obj.hashCode());
            while (true) {
                int i2 = iZzdq & length;
                int i3 = bArr[i2] & 255;
                if (i3 == 255) {
                    return null;
                }
                if (objArr[i3].equals(obj)) {
                    return (V) objArr[i3 ^ 1];
                }
                iZzdq = i2 + 1;
            }
        } else if (obj2 instanceof short[]) {
            short[] sArr = (short[]) obj2;
            int length2 = sArr.length - 1;
            int iZzdq2 = zzdag.zzdq(obj.hashCode());
            while (true) {
                int i4 = iZzdq2 & length2;
                int i5 = sArr[i4] & 65535;
                if (i5 == 65535) {
                    return null;
                }
                if (objArr[i5].equals(obj)) {
                    return (V) objArr[i5 ^ 1];
                }
                iZzdq2 = i4 + 1;
            }
        } else {
            int[] iArr = (int[]) obj2;
            int length3 = iArr.length - 1;
            int iZzdq3 = zzdag.zzdq(obj.hashCode());
            while (true) {
                int i6 = iZzdq3 & length3;
                int i7 = iArr[i6];
                if (i7 == -1) {
                    return null;
                }
                if (objArr[i7].equals(obj)) {
                    return (V) objArr[i7 ^ 1];
                }
                iZzdq3 = i6 + 1;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdao
    final zzdan<Map.Entry<K, V>> zzaon() {
        return new zzdaw(this, this.zzgpa, 0, this.size);
    }

    @Override // com.google.android.gms.internal.ads.zzdao
    final zzdan<K> zzaoo() {
        return new zzday(this, new zzdax(this.zzgpa, 0, this.size));
    }

    @Override // com.google.android.gms.internal.ads.zzdao
    final zzdaf<V> zzaop() {
        return new zzdax(this.zzgpa, 1, this.size);
    }
}
