package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzlu implements zzlq, zzlt {
    private zzmi zzadc;
    private zzlt zzbab;
    public final zzlq[] zzbbd;
    private final IdentityHashMap<zzmf, Integer> zzbbe = new IdentityHashMap<>();
    private int zzbbf;
    private zzlq[] zzbbg;
    private zzme zzbbh;

    public zzlu(zzlq... zzlqVarArr) {
        this.zzbbd = zzlqVarArr;
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final void zza(zzlt zzltVar, long j) {
        this.zzbab = zzltVar;
        zzlq[] zzlqVarArr = this.zzbbd;
        this.zzbbf = zzlqVarArr.length;
        for (zzlq zzlqVar : zzlqVarArr) {
            zzlqVar.zza(this, j);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final void zzgw() throws IOException {
        for (zzlq zzlqVar : this.zzbbd) {
            zzlqVar.zzgw();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final zzmi zzgx() {
        return this.zzadc;
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final long zza(zzmv[] zzmvVarArr, boolean[] zArr, zzmf[] zzmfVarArr, boolean[] zArr2, long j) {
        int[] iArr = new int[zzmvVarArr.length];
        int[] iArr2 = new int[zzmvVarArr.length];
        for (int i = 0; i < zzmvVarArr.length; i++) {
            iArr[i] = zzmfVarArr[i] == null ? -1 : this.zzbbe.get(zzmfVarArr[i]).intValue();
            iArr2[i] = -1;
            if (zzmvVarArr[i] != null) {
                zzmj zzmjVarZzhu = zzmvVarArr[i].zzhu();
                int i2 = 0;
                while (true) {
                    zzlq[] zzlqVarArr = this.zzbbd;
                    if (i2 >= zzlqVarArr.length) {
                        break;
                    }
                    if (zzlqVarArr[i2].zzgx().zza(zzmjVarZzhu) != -1) {
                        iArr2[i] = i2;
                        break;
                    }
                    i2++;
                }
            }
        }
        this.zzbbe.clear();
        zzmf[] zzmfVarArr2 = new zzmf[zzmvVarArr.length];
        zzmf[] zzmfVarArr3 = new zzmf[zzmvVarArr.length];
        zzmv[] zzmvVarArr2 = new zzmv[zzmvVarArr.length];
        ArrayList arrayList = new ArrayList(this.zzbbd.length);
        long j2 = j;
        int i3 = 0;
        while (i3 < this.zzbbd.length) {
            for (int i4 = 0; i4 < zzmvVarArr.length; i4++) {
                zzmv zzmvVar = null;
                zzmfVarArr3[i4] = iArr[i4] == i3 ? zzmfVarArr[i4] : null;
                if (iArr2[i4] == i3) {
                    zzmvVar = zzmvVarArr[i4];
                }
                zzmvVarArr2[i4] = zzmvVar;
            }
            zzmv[] zzmvVarArr3 = zzmvVarArr2;
            ArrayList arrayList2 = arrayList;
            zzmv[] zzmvVarArr4 = zzmvVarArr2;
            int i5 = i3;
            long jZza = this.zzbbd[i3].zza(zzmvVarArr3, zArr, zzmfVarArr3, zArr2, j2);
            if (i5 == 0) {
                j2 = jZza;
            } else if (jZza != j2) {
                throw new IllegalStateException("Children enabled at different positions");
            }
            boolean z = false;
            for (int i6 = 0; i6 < zzmvVarArr.length; i6++) {
                if (iArr2[i6] == i5) {
                    zznt.checkState(zzmfVarArr3[i6] != null);
                    zzmfVarArr2[i6] = zzmfVarArr3[i6];
                    this.zzbbe.put(zzmfVarArr3[i6], Integer.valueOf(i5));
                    z = true;
                } else if (iArr[i6] == i5) {
                    zznt.checkState(zzmfVarArr3[i6] == null);
                }
            }
            if (z) {
                arrayList2.add(this.zzbbd[i5]);
            }
            i3 = i5 + 1;
            arrayList = arrayList2;
            zzmvVarArr2 = zzmvVarArr4;
        }
        ArrayList arrayList3 = arrayList;
        System.arraycopy(zzmfVarArr2, 0, zzmfVarArr, 0, zzmfVarArr2.length);
        this.zzbbg = new zzlq[arrayList3.size()];
        arrayList3.toArray(this.zzbbg);
        this.zzbbh = new zzlh(this.zzbbg);
        return j2;
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final void zzdy(long j) {
        for (zzlq zzlqVar : this.zzbbg) {
            zzlqVar.zzdy(j);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzlq, com.google.android.gms.internal.ads.zzme
    public final boolean zzdz(long j) {
        return this.zzbbh.zzdz(j);
    }

    @Override // com.google.android.gms.internal.ads.zzlq, com.google.android.gms.internal.ads.zzme
    public final long zzgy() {
        return this.zzbbh.zzgy();
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final long zzgz() {
        long jZzgz = this.zzbbd[0].zzgz();
        int i = 1;
        while (true) {
            zzlq[] zzlqVarArr = this.zzbbd;
            if (i >= zzlqVarArr.length) {
                if (jZzgz != C.TIME_UNSET) {
                    for (zzlq zzlqVar : this.zzbbg) {
                        if (zzlqVar != this.zzbbd[0] && zzlqVar.zzea(jZzgz) != jZzgz) {
                            throw new IllegalStateException("Children seeked to different positions");
                        }
                    }
                }
                return jZzgz;
            }
            if (zzlqVarArr[i].zzgz() != C.TIME_UNSET) {
                throw new IllegalStateException("Child reported discontinuity");
            }
            i++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final long zzha() {
        long jMin = Long.MAX_VALUE;
        for (zzlq zzlqVar : this.zzbbg) {
            long jZzha = zzlqVar.zzha();
            if (jZzha != Long.MIN_VALUE) {
                jMin = Math.min(jMin, jZzha);
            }
        }
        if (jMin == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return jMin;
    }

    @Override // com.google.android.gms.internal.ads.zzlq
    public final long zzea(long j) {
        long jZzea = this.zzbbg[0].zzea(j);
        int i = 1;
        while (true) {
            zzlq[] zzlqVarArr = this.zzbbg;
            if (i >= zzlqVarArr.length) {
                return jZzea;
            }
            if (zzlqVarArr[i].zzea(jZzea) != jZzea) {
                throw new IllegalStateException("Children seeked to different positions");
            }
            i++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzlt
    public final void zza(zzlq zzlqVar) {
        int i = this.zzbbf - 1;
        this.zzbbf = i;
        if (i > 0) {
            return;
        }
        int i2 = 0;
        for (zzlq zzlqVar2 : this.zzbbd) {
            i2 += zzlqVar2.zzgx().length;
        }
        zzmj[] zzmjVarArr = new zzmj[i2];
        zzlq[] zzlqVarArr = this.zzbbd;
        int length = zzlqVarArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            zzmi zzmiVarZzgx = zzlqVarArr[i3].zzgx();
            int i5 = zzmiVarZzgx.length;
            int i6 = i4;
            int i7 = 0;
            while (i7 < i5) {
                zzmjVarArr[i6] = zzmiVarZzgx.zzau(i7);
                i7++;
                i6++;
            }
            i3++;
            i4 = i6;
        }
        this.zzadc = new zzmi(zzmjVarArr);
        this.zzbab.zza((zzlq) this);
    }

    @Override // com.google.android.gms.internal.ads.zzmh
    public final /* synthetic */ void zza(zzme zzmeVar) {
        if (this.zzadc != null) {
            this.zzbab.zza(this);
        }
    }
}
