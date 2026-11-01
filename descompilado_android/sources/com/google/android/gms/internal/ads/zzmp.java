package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzmp extends zzmq {
    private static final int[] zzbdp = new int[0];
    private final zzmu zzbdq;
    private final AtomicReference<zzmo> zzbdr;

    public zzmp() {
        this(null);
    }

    private static int zzd(int i, int i2) {
        if (i == -1) {
            return i2 == -1 ? 0 : -1;
        }
        if (i2 == -1) {
            return 1;
        }
        return i - i2;
    }

    private static boolean zze(int i, boolean z) {
        int i2 = i & 3;
        if (i2 != 3) {
            return z && i2 == 2;
        }
        return true;
    }

    private zzmp(zzmu zzmuVar) {
        this.zzbdq = null;
        this.zzbdr = new AtomicReference<>(new zzmo());
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:101:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:103:0x01ce A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:106:0x01d3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:110:0x01da  */
    /* JADX WARN: Code duplicated, block: B:275:0x01ec A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:40:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:41:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:85:0x0192 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:88:0x019a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:89:0x019c  */
    /* JADX WARN: Code duplicated, block: B:90:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:93:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:95:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:96:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:98:0x01b7  */
    @Override // com.google.android.gms.internal.ads.zzmq
    protected final zzmv[] zza(zzgy[] zzgyVarArr, zzmi[] zzmiVarArr, int[][][] iArr) throws zzgb {
        int i;
        zzmo zzmoVar;
        boolean z;
        String str;
        boolean z2;
        String str2;
        boolean z3;
        int i2;
        zzmi zzmiVar;
        int i3;
        zzmv[] zzmvVarArr;
        zzmo zzmoVar2;
        int i4;
        boolean z4;
        zzmj zzmjVar;
        int i5;
        int i6;
        int i7;
        boolean z5;
        int i8;
        boolean z6;
        int[] iArr2;
        int i9;
        boolean z7;
        int i10;
        boolean zZze;
        boolean z8;
        int iZzd;
        int i11;
        int i12;
        boolean z9;
        int i13;
        int i14;
        int i15;
        int i16;
        Point point;
        zzgy[] zzgyVarArr2 = zzgyVarArr;
        int length = zzgyVarArr2.length;
        zzmv[] zzmvVarArr2 = new zzmv[length];
        zzmo zzmoVar3 = this.zzbdr.get();
        int i17 = 0;
        boolean z10 = false;
        boolean z11 = false;
        while (i17 < length) {
            if (2 == zzgyVarArr2[i17].getTrackType()) {
                if (z10) {
                    i3 = length;
                    zzmvVarArr = zzmvVarArr2;
                    zzmoVar2 = zzmoVar3;
                    i4 = i17;
                    z4 = z11;
                } else {
                    zzmi zzmiVar2 = zzmiVarArr[i17];
                    int[][] iArr3 = iArr[i17];
                    int i18 = zzmoVar3.zzbdj;
                    int i19 = zzmoVar3.zzbdk;
                    int i20 = zzmoVar3.zzbdl;
                    int i21 = zzmoVar3.viewportWidth;
                    int i22 = zzmoVar3.viewportHeight;
                    boolean z12 = zzmoVar3.zzbdo;
                    boolean z13 = zzmoVar3.zzbdm;
                    boolean z14 = zzmoVar3.zzbdn;
                    i3 = length;
                    zzmoVar2 = zzmoVar3;
                    z4 = z11;
                    zzmj zzmjVar2 = null;
                    int i23 = 0;
                    int i24 = 0;
                    int i25 = 0;
                    int i26 = -1;
                    int i27 = -1;
                    while (i24 < zzmiVar2.length) {
                        zzmj zzmjVarZzau = zzmiVar2.zzau(i24);
                        zzmi zzmiVar3 = zzmiVar2;
                        zzmv[] zzmvVarArr3 = zzmvVarArr2;
                        ArrayList arrayList = new ArrayList(zzmjVarZzau.length);
                        int i28 = i17;
                        for (int i29 = 0; i29 < zzmjVarZzau.length; i29++) {
                            arrayList.add(Integer.valueOf(i29));
                        }
                        int i30 = Integer.MAX_VALUE;
                        if (i21 == Integer.MAX_VALUE || i22 == Integer.MAX_VALUE) {
                            zzmjVar = zzmjVar2;
                            i5 = i23;
                            i6 = i22;
                            i7 = i21;
                            z5 = z12;
                            i8 = i20;
                            z6 = z13;
                        } else {
                            i5 = i23;
                            int i31 = 0;
                            while (i31 < zzmjVarZzau.length) {
                                zzgq zzgqVarZzav = zzmjVarZzau.zzav(i31);
                                zzmj zzmjVar3 = zzmjVar2;
                                if (zzgqVarZzav.width <= 0 || zzgqVarZzav.height <= 0) {
                                    i22 = i22;
                                    i11 = i21;
                                    z12 = z12;
                                    i12 = i20;
                                    z9 = z13;
                                } else {
                                    int i32 = zzgqVarZzav.width;
                                    z9 = z13;
                                    int i33 = zzgqVarZzav.height;
                                    if (z12) {
                                        if ((i32 > i33) != (i21 > i22)) {
                                            i14 = i21;
                                            i11 = i14;
                                            i13 = i22;
                                        }
                                        i15 = i32 * i14;
                                        i12 = i20;
                                        i16 = i33 * i13;
                                        if (i15 >= i16) {
                                            point = new Point(i13, zzoh.zze(i16, i32));
                                        } else {
                                            point = new Point(zzoh.zze(i15, i33), i14);
                                        }
                                        int i34 = zzgqVarZzav.width * zzgqVarZzav.height;
                                        if (zzgqVarZzav.width < ((int) (point.x * 0.98f)) && zzgqVarZzav.height >= ((int) (point.y * 0.98f)) && i34 < i30) {
                                            i30 = i34;
                                        }
                                    } else {
                                        i22 = i22;
                                        z12 = z12;
                                    }
                                    i13 = i21;
                                    i11 = i13;
                                    i14 = i22;
                                    i15 = i32 * i14;
                                    i12 = i20;
                                    i16 = i33 * i13;
                                    if (i15 >= i16) {
                                        point = new Point(i13, zzoh.zze(i16, i32));
                                    } else {
                                        point = new Point(zzoh.zze(i15, i33), i14);
                                    }
                                    int i35 = zzgqVarZzav.width * zzgqVarZzav.height;
                                    if (zzgqVarZzav.width < ((int) (point.x * 0.98f))) {
                                    }
                                }
                                i31++;
                                zzmjVar2 = zzmjVar3;
                                z13 = z9;
                                z12 = z12;
                                i22 = i22;
                                i21 = i11;
                                i20 = i12;
                            }
                            zzmjVar = zzmjVar2;
                            i6 = i22;
                            i7 = i21;
                            z5 = z12;
                            i8 = i20;
                            z6 = z13;
                            if (i30 != Integer.MAX_VALUE) {
                                for (int size = arrayList.size() - 1; size >= 0; size--) {
                                    int iZzeg = zzmjVarZzau.zzav(((Integer) arrayList.get(size)).intValue()).zzeg();
                                    if (iZzeg == -1 || iZzeg > i30) {
                                        arrayList.remove(size);
                                    }
                                }
                            }
                        }
                        int[] iArr4 = iArr3[i24];
                        int i36 = i25;
                        int iZzeg2 = i26;
                        int i37 = i27;
                        int i38 = 0;
                        while (i38 < zzmjVarZzau.length) {
                            if (zze(iArr4[i38], z14)) {
                                zzgq zzgqVarZzav2 = zzmjVarZzau.zzav(i38);
                                if (!arrayList.contains(Integer.valueOf(i38)) || ((zzgqVarZzav2.width != -1 && zzgqVarZzav2.width > i18) || (zzgqVarZzav2.height != -1 && zzgqVarZzav2.height > i19))) {
                                    i9 = i8;
                                } else {
                                    if (zzgqVarZzav2.zzaex != -1) {
                                        i9 = i8;
                                        if (zzgqVarZzav2.zzaex <= i9) {
                                        }
                                        if (!z7 || z6) {
                                            if (z7) {
                                                i10 = 2;
                                            } else {
                                                i10 = 1;
                                            }
                                            iArr2 = iArr4;
                                            zZze = zze(iArr4[i38], false);
                                            if (zZze) {
                                                i10 += 1000;
                                            }
                                            if (i10 > i36) {
                                                z8 = true;
                                            } else {
                                                z8 = false;
                                            }
                                            if (i10 == i36) {
                                                if (zzgqVarZzav2.zzeg() != iZzeg2) {
                                                    iZzd = zzd(zzgqVarZzav2.zzeg(), iZzeg2);
                                                } else {
                                                    iZzd = zzd(zzgqVarZzav2.zzaex, i37);
                                                }
                                                z8 = zZze || !z7 ? iZzd < 0 : iZzd > 0;
                                            }
                                            if (z8) {
                                                i37 = zzgqVarZzav2.zzaex;
                                                iZzeg2 = zzgqVarZzav2.zzeg();
                                                i5 = i38;
                                                zzmjVar = zzmjVarZzau;
                                                i36 = i10;
                                            }
                                        } else {
                                            iArr2 = iArr4;
                                            z14 = z14;
                                        }
                                    } else {
                                        i9 = i8;
                                    }
                                    z7 = true;
                                    if (z7) {
                                        if (z7) {
                                            i10 = 2;
                                        } else {
                                            i10 = 1;
                                        }
                                        iArr2 = iArr4;
                                        zZze = zze(iArr4[i38], false);
                                        if (zZze) {
                                            i10 += 1000;
                                        }
                                        if (i10 > i36) {
                                            z8 = true;
                                        } else {
                                            z8 = false;
                                        }
                                        if (i10 == i36) {
                                            if (zzgqVarZzav2.zzeg() != iZzeg2) {
                                                iZzd = zzd(zzgqVarZzav2.zzeg(), iZzeg2);
                                            } else {
                                                iZzd = zzd(zzgqVarZzav2.zzaex, i37);
                                            }
                                            if (zZze) {
                                            }
                                        }
                                        if (z8) {
                                            i37 = zzgqVarZzav2.zzaex;
                                            iZzeg2 = zzgqVarZzav2.zzeg();
                                            i5 = i38;
                                            zzmjVar = zzmjVarZzau;
                                            i36 = i10;
                                        }
                                    } else {
                                        if (z7) {
                                            i10 = 2;
                                        } else {
                                            i10 = 1;
                                        }
                                        iArr2 = iArr4;
                                        zZze = zze(iArr4[i38], false);
                                        if (zZze) {
                                            i10 += 1000;
                                        }
                                        if (i10 > i36) {
                                            z8 = true;
                                        } else {
                                            z8 = false;
                                        }
                                        if (i10 == i36) {
                                            if (zzgqVarZzav2.zzeg() != iZzeg2) {
                                                iZzd = zzd(zzgqVarZzav2.zzeg(), iZzeg2);
                                            } else {
                                                iZzd = zzd(zzgqVarZzav2.zzaex, i37);
                                            }
                                            if (zZze) {
                                            }
                                        }
                                        if (z8) {
                                            i37 = zzgqVarZzav2.zzaex;
                                            iZzeg2 = zzgqVarZzav2.zzeg();
                                            i5 = i38;
                                            zzmjVar = zzmjVarZzau;
                                            i36 = i10;
                                        }
                                    }
                                }
                                z7 = false;
                                if (z7) {
                                    if (z7) {
                                        i10 = 2;
                                    } else {
                                        i10 = 1;
                                    }
                                    iArr2 = iArr4;
                                    zZze = zze(iArr4[i38], false);
                                    if (zZze) {
                                        i10 += 1000;
                                    }
                                    if (i10 > i36) {
                                        z8 = true;
                                    } else {
                                        z8 = false;
                                    }
                                    if (i10 == i36) {
                                        if (zzgqVarZzav2.zzeg() != iZzeg2) {
                                            iZzd = zzd(zzgqVarZzav2.zzeg(), iZzeg2);
                                        } else {
                                            iZzd = zzd(zzgqVarZzav2.zzaex, i37);
                                        }
                                        if (zZze) {
                                        }
                                    }
                                    if (z8) {
                                        i37 = zzgqVarZzav2.zzaex;
                                        iZzeg2 = zzgqVarZzav2.zzeg();
                                        i5 = i38;
                                        zzmjVar = zzmjVarZzau;
                                        i36 = i10;
                                    }
                                } else {
                                    if (z7) {
                                        i10 = 2;
                                    } else {
                                        i10 = 1;
                                    }
                                    iArr2 = iArr4;
                                    zZze = zze(iArr4[i38], false);
                                    if (zZze) {
                                        i10 += 1000;
                                    }
                                    if (i10 > i36) {
                                        z8 = true;
                                    } else {
                                        z8 = false;
                                    }
                                    if (i10 == i36) {
                                        if (zzgqVarZzav2.zzeg() != iZzeg2) {
                                            iZzd = zzd(zzgqVarZzav2.zzeg(), iZzeg2);
                                        } else {
                                            iZzd = zzd(zzgqVarZzav2.zzaex, i37);
                                        }
                                        if (zZze) {
                                        }
                                    }
                                    if (z8) {
                                        i37 = zzgqVarZzav2.zzaex;
                                        iZzeg2 = zzgqVarZzav2.zzeg();
                                        i5 = i38;
                                        zzmjVar = zzmjVarZzau;
                                        i36 = i10;
                                    }
                                }
                            } else {
                                iArr2 = iArr4;
                                z14 = z14;
                                i9 = i8;
                            }
                            i38++;
                            i8 = i9;
                            z14 = z14;
                            iArr4 = iArr2;
                        }
                        i20 = i8;
                        i24++;
                        i26 = iZzeg2;
                        i27 = i37;
                        zzmiVar2 = zzmiVar3;
                        i17 = i28;
                        i23 = i5;
                        zzmjVar2 = zzmjVar;
                        z13 = z6;
                        z12 = z5;
                        i22 = i6;
                        i21 = i7;
                        i25 = i36;
                        zzmvVarArr2 = zzmvVarArr3;
                    }
                    zzmj zzmjVar4 = zzmjVar2;
                    zzmvVarArr = zzmvVarArr2;
                    i4 = i17;
                    zzmvVarArr[i4] = zzmjVar4 == null ? null : new zzmr(zzmjVar4, i23);
                    z10 = zzmvVarArr[i4] != null;
                }
                z11 = z4 | (zzmiVarArr[i4].length > 0);
            } else {
                i3 = length;
                zzmvVarArr = zzmvVarArr2;
                zzmoVar2 = zzmoVar3;
                i4 = i17;
            }
            i17 = i4 + 1;
            zzgyVarArr2 = zzgyVarArr;
            zzmoVar3 = zzmoVar2;
            length = i3;
            zzmvVarArr2 = zzmvVarArr;
        }
        zzmv[] zzmvVarArr4 = zzmvVarArr2;
        zzmo zzmoVar4 = zzmoVar3;
        int i39 = length;
        int i40 = 0;
        boolean z15 = false;
        boolean z16 = false;
        while (i40 < i39) {
            int trackType = zzgyVarArr[i40].getTrackType();
            if (trackType != 1) {
                if (trackType == 2) {
                    i = i39;
                    zzmoVar = zzmoVar4;
                } else if (trackType != 3) {
                    zzgyVarArr[i40].getTrackType();
                    zzmi zzmiVar4 = zzmiVarArr[i40];
                    int[][] iArr5 = iArr[i40];
                    zzmoVar = zzmoVar4;
                    boolean z17 = zzmoVar.zzbdn;
                    int i41 = 0;
                    zzmj zzmjVar5 = null;
                    int i42 = 0;
                    int i43 = 0;
                    while (i41 < zzmiVar4.length) {
                        zzmj zzmjVarZzau2 = zzmiVar4.zzau(i41);
                        int[] iArr6 = iArr5[i41];
                        int i44 = i43;
                        int i45 = i42;
                        zzmj zzmjVar6 = zzmjVar5;
                        int i46 = 0;
                        while (i46 < zzmjVarZzau2.length) {
                            if (zze(iArr6[i46], z17)) {
                                int i47 = (zzmjVarZzau2.zzav(i46).zzafr & 1) != 0 ? 2 : 1;
                                zzmiVar = zzmiVar4;
                                if (zze(iArr6[i46], false)) {
                                    i47 += 1000;
                                }
                                if (i47 > i44) {
                                    i45 = i46;
                                    zzmjVar6 = zzmjVarZzau2;
                                    i44 = i47;
                                }
                            } else {
                                i39 = i39;
                                zzmiVar = zzmiVar4;
                            }
                            i46++;
                            zzmiVar4 = zzmiVar;
                            i39 = i39;
                        }
                        i41++;
                        zzmjVar5 = zzmjVar6;
                        i42 = i45;
                        i43 = i44;
                    }
                    i = i39;
                    zzmvVarArr4[i40] = zzmjVar5 == null ? null : new zzmr(zzmjVar5, i42);
                    z = z15;
                } else {
                    i = i39;
                    zzmoVar = zzmoVar4;
                    if (!z16) {
                        zzmi zzmiVar5 = zzmiVarArr[i40];
                        int[][] iArr7 = iArr[i40];
                        boolean z18 = zzmoVar.zzbdn;
                        int i48 = 0;
                        zzmj zzmjVar7 = null;
                        int i49 = 0;
                        int i50 = 0;
                        while (i48 < zzmiVar5.length) {
                            zzmj zzmjVarZzau3 = zzmiVar5.zzau(i48);
                            int[] iArr8 = iArr7[i48];
                            int i51 = i50;
                            int i52 = i49;
                            zzmj zzmjVar8 = zzmjVar7;
                            int i53 = 0;
                            while (i53 < zzmjVarZzau3.length) {
                                if (zze(iArr8[i53], z18)) {
                                    zzgq zzgqVarZzav3 = zzmjVarZzau3.zzav(i53);
                                    boolean z19 = (zzgqVarZzav3.zzafr & 1) != 0;
                                    if ((zzgqVarZzav3.zzafr & 2) != 0) {
                                        str2 = null;
                                        z3 = true;
                                    } else {
                                        str2 = null;
                                        z3 = false;
                                    }
                                    if (zza(zzgqVarZzav3, str2)) {
                                        i2 = z19 ? 6 : !z3 ? 5 : 4;
                                    } else if (z19) {
                                        i2 = 3;
                                    } else if (z3) {
                                        i2 = zza(zzgqVarZzav3, (String) null) ? 2 : 1;
                                    }
                                    if (zze(iArr8[i53], false)) {
                                        i2 += 1000;
                                    }
                                    if (i2 > i51) {
                                        i52 = i53;
                                        i51 = i2;
                                        zzmjVar8 = zzmjVarZzau3;
                                    }
                                } else {
                                    zzmiVar5 = zzmiVar5;
                                }
                                i53++;
                                zzmiVar5 = zzmiVar5;
                            }
                            i48++;
                            zzmjVar7 = zzmjVar8;
                            i49 = i52;
                            i50 = i51;
                        }
                        zzmvVarArr4[i40] = zzmjVar7 == null ? null : new zzmr(zzmjVar7, i49);
                        z16 = zzmvVarArr4[i40] != null;
                        z = z15;
                    }
                }
                z = z15;
            } else {
                i = i39;
                zzmoVar = zzmoVar4;
                if (z15) {
                    z = z15;
                } else {
                    zzmi zzmiVar6 = zzmiVarArr[i40];
                    int[][] iArr9 = iArr[i40];
                    boolean z20 = zzmoVar.zzbdn;
                    int i54 = 0;
                    int i55 = -1;
                    int i56 = -1;
                    int i57 = 0;
                    while (i54 < zzmiVar6.length) {
                        zzmj zzmjVarZzau4 = zzmiVar6.zzau(i54);
                        int[] iArr10 = iArr9[i54];
                        int i58 = i57;
                        int i59 = i56;
                        int i60 = i55;
                        int i61 = 0;
                        while (i61 < zzmjVarZzau4.length) {
                            if (zze(iArr10[i61], z20)) {
                                zzgq zzgqVarZzav4 = zzmjVarZzau4.zzav(i61);
                                int i62 = iArr10[i61];
                                if ((zzgqVarZzav4.zzafr & 1) != 0) {
                                    str = null;
                                    z2 = true;
                                } else {
                                    str = null;
                                    z2 = false;
                                }
                                int i63 = zza(zzgqVarZzav4, str) ? z2 ? 4 : 3 : z2 ? 2 : 1;
                                if (zze(i62, false)) {
                                    i63 += 1000;
                                }
                                if (i63 > i58) {
                                    i60 = i54;
                                    i59 = i61;
                                    i58 = i63;
                                }
                            } else {
                                iArr9 = iArr9;
                            }
                            i61++;
                            iArr9 = iArr9;
                        }
                        i54++;
                        i55 = i60;
                        i56 = i59;
                        i57 = i58;
                        iArr9 = iArr9;
                    }
                    zzmvVarArr4[i40] = i55 == -1 ? null : new zzmr(zzmiVar6.zzau(i55), i56);
                    z = zzmvVarArr4[i40] != null;
                }
            }
            i40++;
            z15 = z;
            zzmoVar4 = zzmoVar;
            i39 = i;
        }
        return zzmvVarArr4;
    }

    private static boolean zza(zzgq zzgqVar, String str) {
        return str != null && TextUtils.equals(str, zzoh.zzbg(zzgqVar.zzafs));
    }
}
