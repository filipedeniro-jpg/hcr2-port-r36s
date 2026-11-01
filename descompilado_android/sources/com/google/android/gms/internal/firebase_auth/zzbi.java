package com.google.android.gms.internal.firebase_auth;

import com.ironsource.sdk.constants.Constants;
import java.util.Arrays;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes.dex */
final class zzbi<K, V> extends zzbe<K, V> {
    private static final zzbe<Object, Object> zza = new zzbi(null, new Object[0], 0);
    private final transient Object zzb;
    private final transient Object[] zzc;
    private final transient int zzd;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [int[]] */
    /* JADX WARN: Type inference failed for: r2v8 */
    static <K, V> zzbi<K, V> zza(int i, Object[] objArr) {
        byte[] bArr;
        int i2;
        int i3;
        int i4;
        if (i == 0) {
            return (zzbi) zza;
        }
        int i5 = 0;
        if (i == 1) {
            zzay.zza(objArr[0], objArr[1]);
            return new zzbi<>(null, objArr, 1);
        }
        zzao.zzb(i, objArr.length >> 1);
        int iMax = Math.max(i, 2);
        int iHighestOneBit = 1073741824;
        if (iMax < 751619276) {
            iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
            while (true) {
                double d = iHighestOneBit;
                Double.isNaN(d);
                if (d * 0.7d >= iMax) {
                    break;
                }
                iHighestOneBit <<= 1;
            }
        } else {
            zzao.zza(iMax < 1073741824, "collection too large");
        }
        if (i == 1) {
            zzay.zza(objArr[0], objArr[1]);
            bArr = 0;
        } else {
            int i6 = iHighestOneBit - 1;
            if (iHighestOneBit <= 128) {
                bArr = new byte[iHighestOneBit];
                Arrays.fill(bArr, (byte) -1);
                while (i5 < i) {
                    int i7 = i5 * 2;
                    Object obj = objArr[i7];
                    Object obj2 = objArr[i7 ^ 1];
                    zzay.zza(obj, obj2);
                    int iZza = zzax.zza(obj.hashCode());
                    while (true) {
                        i4 = iZza & i6;
                        int i8 = bArr[i4] & 255;
                        if (i8 == 255) {
                            break;
                        }
                        if (objArr[i8].equals(obj)) {
                            throw zza(obj, obj2, objArr, i8);
                        }
                        iZza = i4 + 1;
                    }
                    bArr[i4] = (byte) i7;
                    i5++;
                }
            } else if (iHighestOneBit <= 32768) {
                bArr = new short[iHighestOneBit];
                Arrays.fill(bArr, (short) -1);
                while (i5 < i) {
                    int i9 = i5 * 2;
                    Object obj3 = objArr[i9];
                    Object obj4 = objArr[i9 ^ 1];
                    zzay.zza(obj3, obj4);
                    int iZza2 = zzax.zza(obj3.hashCode());
                    while (true) {
                        i3 = iZza2 & i6;
                        int i10 = bArr[i3] & 65535;
                        if (i10 == 65535) {
                            break;
                        }
                        if (objArr[i10].equals(obj3)) {
                            throw zza(obj3, obj4, objArr, i10);
                        }
                        iZza2 = i3 + 1;
                    }
                    bArr[i3] = (short) i9;
                    i5++;
                }
            } else {
                bArr = new int[iHighestOneBit];
                Arrays.fill((int[]) bArr, -1);
                while (i5 < i) {
                    int i11 = i5 * 2;
                    Object obj5 = objArr[i11];
                    Object obj6 = objArr[i11 ^ 1];
                    zzay.zza(obj5, obj6);
                    int iZza3 = zzax.zza(obj5.hashCode());
                    while (true) {
                        i2 = iZza3 & i6;
                        char c = bArr[i2];
                        if (c == -1) {
                            break;
                        }
                        if (objArr[c].equals(obj5)) {
                            throw zza(obj5, obj6, objArr, c);
                        }
                        iZza3 = i2 + 1;
                    }
                    bArr[i2] = i11;
                    i5++;
                }
            }
        }
        return new zzbi<>(bArr, objArr, i);
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

    private zzbi(Object obj, Object[] objArr, int i) {
        this.zzb = obj;
        this.zzc = objArr;
        this.zzd = i;
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbe, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        Object obj2 = this.zzb;
        Object[] objArr = this.zzc;
        int i = this.zzd;
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
            int iZza = zzax.zza(obj.hashCode());
            while (true) {
                int i2 = iZza & length;
                int i3 = bArr[i2] & 255;
                if (i3 == 255) {
                    return null;
                }
                if (objArr[i3].equals(obj)) {
                    return (V) objArr[i3 ^ 1];
                }
                iZza = i2 + 1;
            }
        } else if (obj2 instanceof short[]) {
            short[] sArr = (short[]) obj2;
            int length2 = sArr.length - 1;
            int iZza2 = zzax.zza(obj.hashCode());
            while (true) {
                int i4 = iZza2 & length2;
                int i5 = sArr[i4] & 65535;
                if (i5 == 65535) {
                    return null;
                }
                if (objArr[i5].equals(obj)) {
                    return (V) objArr[i5 ^ 1];
                }
                iZza2 = i4 + 1;
            }
        } else {
            int[] iArr = (int[]) obj2;
            int length3 = iArr.length - 1;
            int iZza3 = zzax.zza(obj.hashCode());
            while (true) {
                int i6 = iZza3 & length3;
                int i7 = iArr[i6];
                if (i7 == -1) {
                    return null;
                }
                if (objArr[i7].equals(obj)) {
                    return (V) objArr[i7 ^ 1];
                }
                iZza3 = i6 + 1;
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbe
    final zzbg<Map.Entry<K, V>> zza() {
        return new zzbh(this, this.zzc, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbe
    final zzbg<K> zzb() {
        return new zzbj(this, new zzbm(this.zzc, 0, this.zzd));
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbe
    final zzba<V> zzc() {
        return new zzbm(this.zzc, 1, this.zzd);
    }
}
