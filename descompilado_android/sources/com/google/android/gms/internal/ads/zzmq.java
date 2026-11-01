package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzmq extends zzmw {
    private zzmt zzbdu;
    private final SparseArray<Map<zzmi, zzms>> zzbds = new SparseArray<>();
    private final SparseBooleanArray zzbdt = new SparseBooleanArray();
    private int zzagc = 0;

    protected abstract zzmv[] zza(zzgy[] zzgyVarArr, zzmi[] zzmiVarArr, int[][][] iArr) throws zzgb;

    public final void zzf(int i, boolean z) {
        if (this.zzbdt.get(i) == z) {
            return;
        }
        this.zzbdt.put(i, z);
        invalidate();
    }

    @Override // com.google.android.gms.internal.ads.zzmw
    public final zzmy zza(zzgy[] zzgyVarArr, zzmi zzmiVar) throws zzgb {
        int[] iArr;
        int[] iArr2 = new int[zzgyVarArr.length + 1];
        zzmj[][] zzmjVarArr = new zzmj[zzgyVarArr.length + 1][];
        int[][][] iArr3 = new int[zzgyVarArr.length + 1][][];
        for (int i = 0; i < zzmjVarArr.length; i++) {
            zzmjVarArr[i] = new zzmj[zzmiVar.length];
            iArr3[i] = new int[zzmiVar.length][];
        }
        int[] iArr4 = new int[zzgyVarArr.length];
        for (int i2 = 0; i2 < iArr4.length; i2++) {
            iArr4[i2] = zzgyVarArr[i2].zzdn();
        }
        for (int i3 = 0; i3 < zzmiVar.length; i3++) {
            zzmj zzmjVarZzau = zzmiVar.zzau(i3);
            int length = zzgyVarArr.length;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                if (i4 >= zzgyVarArr.length) {
                    i4 = length;
                    break;
                }
                zzgy zzgyVar = zzgyVarArr[i4];
                int i6 = length;
                int i7 = i5;
                for (int i8 = 0; i8 < zzmjVarZzau.length; i8++) {
                    int iZza = zzgyVar.zza(zzmjVarZzau.zzav(i8)) & 3;
                    if (iZza > i7) {
                        if (iZza == 3) {
                            break;
                        }
                        i6 = i4;
                        i7 = iZza;
                    }
                }
                i4++;
                i5 = i7;
                length = i6;
            }
            if (i4 == zzgyVarArr.length) {
                iArr = new int[zzmjVarZzau.length];
            } else {
                zzgy zzgyVar2 = zzgyVarArr[i4];
                int[] iArr5 = new int[zzmjVarZzau.length];
                for (int i9 = 0; i9 < zzmjVarZzau.length; i9++) {
                    iArr5[i9] = zzgyVar2.zza(zzmjVarZzau.zzav(i9));
                }
                iArr = iArr5;
            }
            int i10 = iArr2[i4];
            zzmjVarArr[i4][i10] = zzmjVarZzau;
            iArr3[i4][i10] = iArr;
            iArr2[i4] = iArr2[i4] + 1;
        }
        zzmi[] zzmiVarArr = new zzmi[zzgyVarArr.length];
        int[] iArr6 = new int[zzgyVarArr.length];
        for (int i11 = 0; i11 < zzgyVarArr.length; i11++) {
            int i12 = iArr2[i11];
            zzmiVarArr[i11] = new zzmi((zzmj[]) Arrays.copyOf(zzmjVarArr[i11], i12));
            iArr3[i11] = (int[][]) Arrays.copyOf(iArr3[i11], i12);
            iArr6[i11] = zzgyVarArr[i11].getTrackType();
        }
        zzmi zzmiVar2 = new zzmi((zzmj[]) Arrays.copyOf(zzmjVarArr[zzgyVarArr.length], iArr2[zzgyVarArr.length]));
        zzmv[] zzmvVarArrZza = zza(zzgyVarArr, zzmiVarArr, iArr3);
        int i13 = 0;
        while (true) {
            if (i13 < zzgyVarArr.length) {
                if (this.zzbdt.get(i13)) {
                    zzmvVarArrZza[i13] = null;
                } else {
                    zzmi zzmiVar3 = zzmiVarArr[i13];
                    Map<zzmi, zzms> map = this.zzbds.get(i13);
                    if ((map != null ? map.get(zzmiVar3) : null) != null) {
                        throw new NoSuchMethodError();
                    }
                }
                i13++;
            } else {
                zzmt zzmtVar = new zzmt(iArr6, zzmiVarArr, iArr4, iArr3, zzmiVar2);
                zzgx[] zzgxVarArr = new zzgx[zzgyVarArr.length];
                for (int i14 = 0; i14 < zzgyVarArr.length; i14++) {
                    zzgxVarArr[i14] = zzmvVarArrZza[i14] != null ? zzgx.zzagb : null;
                }
                return new zzmy(zzmiVar, new zzmx(zzmvVarArrZza), zzmtVar, zzgxVarArr);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzmw
    public final void zzd(Object obj) {
        this.zzbdu = (zzmt) obj;
    }
}
