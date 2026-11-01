package com.google.android.gms.internal.firebase_remote_config;

import com.google.android.gms.drive.DriveFile;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes2.dex */
final class zziu<T> implements zzjf<T> {
    private static final int[] zzvp = new int[0];
    private static final Unsafe zzvq = zzkd.zzjf();
    private final int[] zzvr;
    private final Object[] zzvs;
    private final int zzvt;
    private final int zzvu;
    private final zziq zzvv;
    private final boolean zzvw;
    private final boolean zzvx;
    private final boolean zzvy;
    private final boolean zzvz;
    private final int[] zzwa;
    private final int zzwb;
    private final int zzwc;
    private final zziy zzwd;
    private final zzia zzwe;
    private final zzjx<?, ?> zzwf;
    private final zzgz<?> zzwg;
    private final zzij zzwh;

    private zziu(int[] iArr, Object[] objArr, int i, int i2, zziq zziqVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zziy zziyVar, zzia zziaVar, zzjx<?, ?> zzjxVar, zzgz<?> zzgzVar, zzij zzijVar) {
        this.zzvr = iArr;
        this.zzvs = objArr;
        this.zzvt = i;
        this.zzvu = i2;
        this.zzvx = zziqVar instanceof zzhi;
        this.zzvy = z;
        this.zzvw = zzgzVar != null && zzgzVar.zze(zziqVar);
        this.zzvz = false;
        this.zzwa = iArr2;
        this.zzwb = i3;
        this.zzwc = i4;
        this.zzwd = zziyVar;
        this.zzwe = zziaVar;
        this.zzwf = zzjxVar;
        this.zzwg = zzgzVar;
        this.zzvv = zziqVar;
        this.zzwh = zzijVar;
    }

    private static boolean zzbj(int i) {
        return (i & DriveFile.MODE_WRITE_ONLY) != 0;
    }

    /* JADX WARN: Code duplicated, block: B:172:0x0379  */
    /* JADX WARN: Code duplicated, block: B:186:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:189:0x03d7  */
    static <T> zziu<T> zza(Class<T> cls, zzio zzioVar, zziy zziyVar, zzia zziaVar, zzjx<?, ?> zzjxVar, zzgz<?> zzgzVar, zzij zzijVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int iCharAt;
        int i6;
        int iCharAt2;
        int iCharAt3;
        int i7;
        int[] iArr;
        int i8;
        int i9;
        int i10;
        int i11;
        char cCharAt;
        int i12;
        char cCharAt2;
        int i13;
        char cCharAt3;
        int i14;
        char cCharAt4;
        char cCharAt5;
        char cCharAt6;
        char cCharAt7;
        char cCharAt8;
        int i15;
        int i16;
        int i17;
        int i18;
        int iObjectFieldOffset;
        int i19;
        int i20;
        int iObjectFieldOffset2;
        Field fieldZza;
        int i21;
        char cCharAt9;
        int i22;
        int i23;
        Field fieldZza2;
        Field fieldZza3;
        int i24;
        char cCharAt10;
        int i25;
        char cCharAt11;
        int i26;
        char cCharAt12;
        char cCharAt13;
        char cCharAt14;
        if (zzioVar instanceof zzjd) {
            zzjd zzjdVar = (zzjd) zzioVar;
            int iCharAt4 = 0;
            boolean z = zzjdVar.zzib() == zzhi.zze.zztv;
            String strZzij = zzjdVar.zzij();
            int length = strZzij.length();
            int iCharAt5 = strZzij.charAt(0);
            if (iCharAt5 >= 55296) {
                int i27 = iCharAt5 & 8191;
                int i28 = 1;
                int i29 = 13;
                while (true) {
                    i = i28 + 1;
                    cCharAt14 = strZzij.charAt(i28);
                    if (cCharAt14 < 55296) {
                        break;
                    }
                    i27 |= (cCharAt14 & 8191) << i29;
                    i29 += 13;
                    i28 = i;
                }
                iCharAt5 = (cCharAt14 << i29) | i27;
            } else {
                i = 1;
            }
            int i30 = i + 1;
            int iCharAt6 = strZzij.charAt(i);
            if (iCharAt6 >= 55296) {
                int i31 = iCharAt6 & 8191;
                int i32 = 13;
                while (true) {
                    i2 = i30 + 1;
                    cCharAt13 = strZzij.charAt(i30);
                    if (cCharAt13 < 55296) {
                        break;
                    }
                    i31 |= (cCharAt13 & 8191) << i32;
                    i32 += 13;
                    i30 = i2;
                }
                iCharAt6 = i31 | (cCharAt13 << i32);
            } else {
                i2 = i30;
            }
            if (iCharAt6 == 0) {
                iArr = zzvp;
                iCharAt3 = 0;
                i10 = 0;
                i8 = 0;
                iCharAt = 0;
                iCharAt2 = 0;
                i9 = 0;
            } else {
                int i33 = i2 + 1;
                int iCharAt7 = strZzij.charAt(i2);
                if (iCharAt7 >= 55296) {
                    int i34 = iCharAt7 & 8191;
                    int i35 = 13;
                    while (true) {
                        i3 = i33 + 1;
                        cCharAt8 = strZzij.charAt(i33);
                        if (cCharAt8 < 55296) {
                            break;
                        }
                        i34 |= (cCharAt8 & 8191) << i35;
                        i35 += 13;
                        i33 = i3;
                    }
                    iCharAt7 = (cCharAt8 << i35) | i34;
                } else {
                    i3 = i33;
                }
                int i36 = i3 + 1;
                int iCharAt8 = strZzij.charAt(i3);
                if (iCharAt8 >= 55296) {
                    int i37 = iCharAt8 & 8191;
                    int i38 = 13;
                    while (true) {
                        i4 = i36 + 1;
                        cCharAt7 = strZzij.charAt(i36);
                        if (cCharAt7 < 55296) {
                            break;
                        }
                        i37 |= (cCharAt7 & 8191) << i38;
                        i38 += 13;
                        i36 = i4;
                    }
                    iCharAt8 = i37 | (cCharAt7 << i38);
                } else {
                    i4 = i36;
                }
                int i39 = i4 + 1;
                int iCharAt9 = strZzij.charAt(i4);
                if (iCharAt9 >= 55296) {
                    int i40 = iCharAt9 & 8191;
                    int i41 = 13;
                    while (true) {
                        i5 = i39 + 1;
                        cCharAt6 = strZzij.charAt(i39);
                        if (cCharAt6 < 55296) {
                            break;
                        }
                        i40 |= (cCharAt6 & 8191) << i41;
                        i41 += 13;
                        i39 = i5;
                    }
                    iCharAt9 = (cCharAt6 << i41) | i40;
                } else {
                    i5 = i39;
                }
                int i42 = i5 + 1;
                iCharAt = strZzij.charAt(i5);
                if (iCharAt >= 55296) {
                    int i43 = iCharAt & 8191;
                    int i44 = 13;
                    while (true) {
                        i6 = i42 + 1;
                        cCharAt5 = strZzij.charAt(i42);
                        if (cCharAt5 < 55296) {
                            break;
                        }
                        i43 |= (cCharAt5 & 8191) << i44;
                        i44 += 13;
                        i42 = i6;
                    }
                    iCharAt = (cCharAt5 << i44) | i43;
                } else {
                    i6 = i42;
                }
                int i45 = i6 + 1;
                iCharAt2 = strZzij.charAt(i6);
                if (iCharAt2 >= 55296) {
                    int i46 = iCharAt2 & 8191;
                    int i47 = 13;
                    while (true) {
                        i14 = i45 + 1;
                        cCharAt4 = strZzij.charAt(i45);
                        if (cCharAt4 < 55296) {
                            break;
                        }
                        i46 |= (cCharAt4 & 8191) << i47;
                        i47 += 13;
                        i45 = i14;
                    }
                    iCharAt2 = (cCharAt4 << i47) | i46;
                    i45 = i14;
                }
                int i48 = i45 + 1;
                iCharAt3 = strZzij.charAt(i45);
                if (iCharAt3 >= 55296) {
                    int i49 = iCharAt3 & 8191;
                    int i50 = 13;
                    while (true) {
                        i13 = i48 + 1;
                        cCharAt3 = strZzij.charAt(i48);
                        if (cCharAt3 < 55296) {
                            break;
                        }
                        i49 |= (cCharAt3 & 8191) << i50;
                        i50 += 13;
                        i48 = i13;
                    }
                    iCharAt3 = i49 | (cCharAt3 << i50);
                    i48 = i13;
                }
                int i51 = i48 + 1;
                int iCharAt10 = strZzij.charAt(i48);
                if (iCharAt10 >= 55296) {
                    int i52 = 13;
                    int i53 = iCharAt10 & 8191;
                    int i54 = i51;
                    while (true) {
                        i12 = i54 + 1;
                        cCharAt2 = strZzij.charAt(i54);
                        if (cCharAt2 < 55296) {
                            break;
                        }
                        i53 |= (cCharAt2 & 8191) << i52;
                        i52 += 13;
                        i54 = i12;
                    }
                    iCharAt10 = i53 | (cCharAt2 << i52);
                    i7 = i12;
                } else {
                    i7 = i51;
                }
                int i55 = i7 + 1;
                iCharAt4 = strZzij.charAt(i7);
                if (iCharAt4 >= 55296) {
                    int i56 = 13;
                    int i57 = iCharAt4 & 8191;
                    int i58 = i55;
                    while (true) {
                        i11 = i58 + 1;
                        cCharAt = strZzij.charAt(i58);
                        if (cCharAt < 55296) {
                            break;
                        }
                        i57 |= (cCharAt & 8191) << i56;
                        i56 += 13;
                        i58 = i11;
                    }
                    iCharAt4 = i57 | (cCharAt << i56);
                    i55 = i11;
                }
                iArr = new int[iCharAt4 + iCharAt3 + iCharAt10];
                i8 = (iCharAt7 << 1) + iCharAt8;
                int i59 = i55;
                i9 = iCharAt7;
                i10 = iCharAt9;
                i2 = i59;
            }
            Unsafe unsafe = zzvq;
            Object[] objArrZzik = zzjdVar.zzik();
            Class<?> cls2 = zzjdVar.zzid().getClass();
            int i60 = i8;
            int[] iArr2 = new int[iCharAt2 * 3];
            Object[] objArr = new Object[iCharAt2 << 1];
            int i61 = iCharAt4 + iCharAt3;
            int i62 = iCharAt4;
            int i63 = i61;
            int i64 = 0;
            int i65 = 0;
            while (i2 < length) {
                int i66 = i2 + 1;
                int iCharAt11 = strZzij.charAt(i2);
                char c = 55296;
                if (iCharAt11 >= 55296) {
                    int i67 = 13;
                    int i68 = iCharAt11 & 8191;
                    int i69 = i66;
                    while (true) {
                        i26 = i69 + 1;
                        cCharAt12 = strZzij.charAt(i69);
                        if (cCharAt12 < c) {
                            break;
                        }
                        i68 |= (cCharAt12 & 8191) << i67;
                        i67 += 13;
                        i69 = i26;
                        c = 55296;
                    }
                    iCharAt11 = i68 | (cCharAt12 << i67);
                    i15 = i26;
                } else {
                    i15 = i66;
                }
                int i70 = i15 + 1;
                int iCharAt12 = strZzij.charAt(i15);
                int i71 = length;
                char c2 = 55296;
                if (iCharAt12 >= 55296) {
                    int i72 = 13;
                    int i73 = iCharAt12 & 8191;
                    int i74 = i70;
                    while (true) {
                        i25 = i74 + 1;
                        cCharAt11 = strZzij.charAt(i74);
                        if (cCharAt11 < c2) {
                            break;
                        }
                        i73 |= (cCharAt11 & 8191) << i72;
                        i72 += 13;
                        i74 = i25;
                        c2 = 55296;
                    }
                    iCharAt12 = i73 | (cCharAt11 << i72);
                    i16 = i25;
                } else {
                    i16 = i70;
                }
                int i75 = iCharAt4;
                int i76 = iCharAt12 & 255;
                boolean z2 = z;
                if ((iCharAt12 & 1024) != 0) {
                    iArr[i64] = i65;
                    i64++;
                }
                int i77 = i64;
                if (i76 >= 51) {
                    int i78 = i16 + 1;
                    int iCharAt13 = strZzij.charAt(i16);
                    char c3 = 55296;
                    if (iCharAt13 >= 55296) {
                        int i79 = iCharAt13 & 8191;
                        int i80 = 13;
                        while (true) {
                            i24 = i78 + 1;
                            cCharAt10 = strZzij.charAt(i78);
                            if (cCharAt10 < c3) {
                                break;
                            }
                            i79 |= (cCharAt10 & 8191) << i80;
                            i80 += 13;
                            i78 = i24;
                            c3 = 55296;
                        }
                        iCharAt13 = i79 | (cCharAt10 << i80);
                        i78 = i24;
                    }
                    int i81 = i76 - 51;
                    int i82 = i78;
                    if (i81 == 9 || i81 == 17) {
                        i23 = 1;
                        objArr[((i65 / 3) << 1) + 1] = objArrZzik[i60];
                        i60++;
                    } else {
                        if (i81 == 12 && (iCharAt5 & 1) == 1) {
                            objArr[((i65 / 3) << 1) + 1] = objArrZzik[i60];
                            i60++;
                        }
                        i23 = 1;
                    }
                    int i83 = iCharAt13 << i23;
                    Object obj = objArrZzik[i83];
                    if (obj instanceof Field) {
                        fieldZza2 = (Field) obj;
                    } else {
                        fieldZza2 = zza(cls2, (String) obj);
                        objArrZzik[i83] = fieldZza2;
                    }
                    int i84 = i10;
                    int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZza2);
                    int i85 = i83 + 1;
                    Object obj2 = objArrZzik[i85];
                    if (obj2 instanceof Field) {
                        fieldZza3 = (Field) obj2;
                    } else {
                        fieldZza3 = zza(cls2, (String) obj2);
                        objArrZzik[i85] = fieldZza3;
                    }
                    strZzij = strZzij;
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZza3);
                    cls2 = cls2;
                    i17 = i60;
                    iObjectFieldOffset = iObjectFieldOffset3;
                    i20 = 0;
                    i18 = i84;
                    iCharAt11 = iCharAt11;
                    i2 = i82;
                } else {
                    int i86 = i10;
                    i17 = i60 + 1;
                    Field fieldZza4 = zza(cls2, (String) objArrZzik[i60]);
                    if (i76 == 9 || i76 == 17) {
                        i18 = i86;
                        objArr[((i65 / 3) << 1) + 1] = fieldZza4.getType();
                    } else {
                        if (i76 == 27 || i76 == 49) {
                            i18 = i86;
                            i22 = i17 + 1;
                            objArr[((i65 / 3) << 1) + 1] = objArrZzik[i17];
                        } else if (i76 == 12 || i76 == 30 || i76 == 44) {
                            i18 = i86;
                            if ((iCharAt5 & 1) == 1) {
                                i22 = i17 + 1;
                                objArr[((i65 / 3) << 1) + 1] = objArrZzik[i17];
                            }
                            iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                            if ((iCharAt5 & 1) == 1 || i76 > 17) {
                                i19 = i16;
                                i20 = 0;
                                iObjectFieldOffset2 = 0;
                            } else {
                                i19 = i16 + 1;
                                int iCharAt14 = strZzij.charAt(i16);
                                if (iCharAt14 >= 55296) {
                                    int i87 = iCharAt14 & 8191;
                                    int i88 = 13;
                                    while (true) {
                                        i21 = i19 + 1;
                                        cCharAt9 = strZzij.charAt(i19);
                                        if (cCharAt9 < 55296) {
                                            break;
                                        }
                                        i87 |= (cCharAt9 & 8191) << i88;
                                        i88 += 13;
                                        i19 = i21;
                                    }
                                    iCharAt14 = i87 | (cCharAt9 << i88);
                                    i19 = i21;
                                }
                                int i89 = (i9 << 1) + (iCharAt14 / 32);
                                Object obj3 = objArrZzik[i89];
                                if (obj3 instanceof Field) {
                                    fieldZza = (Field) obj3;
                                } else {
                                    fieldZza = zza(cls2, (String) obj3);
                                    objArrZzik[i89] = fieldZza;
                                }
                                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldZza);
                                i20 = iCharAt14 % 32;
                            }
                            if (i76 >= 18 && i76 <= 49) {
                                iArr[i63] = iObjectFieldOffset;
                                i63++;
                            }
                            i2 = i19;
                        } else if (i76 == 50) {
                            int i90 = i62 + 1;
                            iArr[i62] = i65;
                            int i91 = (i65 / 3) << 1;
                            int i92 = i17 + 1;
                            objArr[i91] = objArrZzik[i17];
                            if ((iCharAt12 & 2048) != 0) {
                                i17 = i92 + 1;
                                objArr[i91 + 1] = objArrZzik[i92];
                                i18 = i86;
                                i62 = i90;
                            } else {
                                i62 = i90;
                                i17 = i92;
                                i18 = i86;
                            }
                        } else {
                            i18 = i86;
                        }
                        i17 = i22;
                        iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                        if ((iCharAt5 & 1) == 1) {
                            i19 = i16;
                            i20 = 0;
                            iObjectFieldOffset2 = 0;
                        } else {
                            i19 = i16;
                            i20 = 0;
                            iObjectFieldOffset2 = 0;
                        }
                        if (i76 >= 18) {
                            iArr[i63] = iObjectFieldOffset;
                            i63++;
                        }
                        i2 = i19;
                    }
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                    if ((iCharAt5 & 1) == 1) {
                        i19 = i16;
                        i20 = 0;
                        iObjectFieldOffset2 = 0;
                    } else {
                        i19 = i16;
                        i20 = 0;
                        iObjectFieldOffset2 = 0;
                    }
                    if (i76 >= 18) {
                        iArr[i63] = iObjectFieldOffset;
                        i63++;
                    }
                    i2 = i19;
                }
                int i93 = i65 + 1;
                iArr2[i65] = iCharAt11;
                int i94 = i93 + 1;
                iArr2[i93] = (i76 << 20) | ((iCharAt12 & 256) != 0 ? DriveFile.MODE_READ_ONLY : 0) | ((iCharAt12 & 512) != 0 ? DriveFile.MODE_WRITE_ONLY : 0) | iObjectFieldOffset;
                i65 = i94 + 1;
                iArr2[i94] = (i20 << 20) | iObjectFieldOffset2;
                cls2 = cls2;
                iCharAt = iCharAt;
                iCharAt4 = i75;
                i60 = i17;
                length = i71;
                z = z2;
                i10 = i18;
                i64 = i77;
                strZzij = strZzij;
            }
            return new zziu<>(iArr2, objArr, i10, iCharAt, zzjdVar.zzid(), z, false, iArr, iCharAt4, i61, zziyVar, zziaVar, zzjxVar, zzgzVar, zzijVar);
        }
        ((zzju) zzioVar).zzib();
        int i95 = zzhi.zze.zztv;
        throw new NoSuchMethodError();
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String string = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(string).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(string);
            throw new RuntimeException(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final T newInstance() {
        return (T) this.zzwd.newInstance(this.zzvv);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x01c1  */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final boolean equals(T t, T t2) {
        int length = this.zzvr.length;
        int i = 0;
        while (true) {
            boolean zZzf = true;
            if (i < length) {
                int iZzbh = zzbh(i);
                long j = iZzbh & 1048575;
                switch ((iZzbh & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzkd.zzo(t, j)) != Double.doubleToLongBits(zzkd.zzo(t2, j))) {
                            zZzf = false;
                        }
                        break;
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzkd.zzn(t, j)) != Float.floatToIntBits(zzkd.zzn(t2, j))) {
                            zZzf = false;
                        }
                        break;
                    case 2:
                        if (!zzc(t, t2, i) || zzkd.zzl(t, j) != zzkd.zzl(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 3:
                        if (!zzc(t, t2, i) || zzkd.zzl(t, j) != zzkd.zzl(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 4:
                        if (!zzc(t, t2, i) || zzkd.zzk(t, j) != zzkd.zzk(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 5:
                        if (!zzc(t, t2, i) || zzkd.zzl(t, j) != zzkd.zzl(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 6:
                        if (!zzc(t, t2, i) || zzkd.zzk(t, j) != zzkd.zzk(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 7:
                        if (!zzc(t, t2, i) || zzkd.zzm(t, j) != zzkd.zzm(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 8:
                        if (!zzc(t, t2, i) || !zzjh.zzf(zzkd.zzp(t, j), zzkd.zzp(t2, j))) {
                            zZzf = false;
                        }
                        break;
                    case 9:
                        if (!zzc(t, t2, i) || !zzjh.zzf(zzkd.zzp(t, j), zzkd.zzp(t2, j))) {
                            zZzf = false;
                        }
                        break;
                    case 10:
                        if (!zzc(t, t2, i) || !zzjh.zzf(zzkd.zzp(t, j), zzkd.zzp(t2, j))) {
                            zZzf = false;
                        }
                        break;
                    case 11:
                        if (!zzc(t, t2, i) || zzkd.zzk(t, j) != zzkd.zzk(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 12:
                        if (!zzc(t, t2, i) || zzkd.zzk(t, j) != zzkd.zzk(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 13:
                        if (!zzc(t, t2, i) || zzkd.zzk(t, j) != zzkd.zzk(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 14:
                        if (!zzc(t, t2, i) || zzkd.zzl(t, j) != zzkd.zzl(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 15:
                        if (!zzc(t, t2, i) || zzkd.zzk(t, j) != zzkd.zzk(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 16:
                        if (!zzc(t, t2, i) || zzkd.zzl(t, j) != zzkd.zzl(t2, j)) {
                            zZzf = false;
                        }
                        break;
                    case 17:
                        if (!zzc(t, t2, i) || !zzjh.zzf(zzkd.zzp(t, j), zzkd.zzp(t2, j))) {
                            zZzf = false;
                        }
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        zZzf = zzjh.zzf(zzkd.zzp(t, j), zzkd.zzp(t2, j));
                        break;
                    case 50:
                        zZzf = zzjh.zzf(zzkd.zzp(t, j), zzkd.zzp(t2, j));
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                        long jZzbi = zzbi(i) & 1048575;
                        if (zzkd.zzk(t, jZzbi) != zzkd.zzk(t2, jZzbi) || !zzjh.zzf(zzkd.zzp(t, j), zzkd.zzp(t2, j))) {
                            zZzf = false;
                        }
                        break;
                }
                if (!zZzf) {
                    return false;
                }
                i += 3;
            } else {
                if (!this.zzwf.zzaa(t).equals(this.zzwf.zzaa(t2))) {
                    return false;
                }
                if (this.zzvw) {
                    return this.zzwg.zzk(t).equals(this.zzwg.zzk(t2));
                }
                return true;
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final int hashCode(T t) {
        int i;
        int iZzo;
        int length = this.zzvr.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzbh = zzbh(i3);
            int i4 = this.zzvr[i3];
            long j = 1048575 & iZzbh;
            int iHashCode = 37;
            switch ((iZzbh & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    iZzo = zzhm.zzo(Double.doubleToLongBits(zzkd.zzo(t, j)));
                    i2 = i + iZzo;
                    break;
                case 1:
                    i = i2 * 53;
                    iZzo = Float.floatToIntBits(zzkd.zzn(t, j));
                    i2 = i + iZzo;
                    break;
                case 2:
                    i = i2 * 53;
                    iZzo = zzhm.zzo(zzkd.zzl(t, j));
                    i2 = i + iZzo;
                    break;
                case 3:
                    i = i2 * 53;
                    iZzo = zzhm.zzo(zzkd.zzl(t, j));
                    i2 = i + iZzo;
                    break;
                case 4:
                    i = i2 * 53;
                    iZzo = zzkd.zzk(t, j);
                    i2 = i + iZzo;
                    break;
                case 5:
                    i = i2 * 53;
                    iZzo = zzhm.zzo(zzkd.zzl(t, j));
                    i2 = i + iZzo;
                    break;
                case 6:
                    i = i2 * 53;
                    iZzo = zzkd.zzk(t, j);
                    i2 = i + iZzo;
                    break;
                case 7:
                    i = i2 * 53;
                    iZzo = zzhm.zzf(zzkd.zzm(t, j));
                    i2 = i + iZzo;
                    break;
                case 8:
                    i = i2 * 53;
                    iZzo = ((String) zzkd.zzp(t, j)).hashCode();
                    i2 = i + iZzo;
                    break;
                case 9:
                    Object objZzp = zzkd.zzp(t, j);
                    if (objZzp != null) {
                        iHashCode = objZzp.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iZzo = zzkd.zzp(t, j).hashCode();
                    i2 = i + iZzo;
                    break;
                case 11:
                    i = i2 * 53;
                    iZzo = zzkd.zzk(t, j);
                    i2 = i + iZzo;
                    break;
                case 12:
                    i = i2 * 53;
                    iZzo = zzkd.zzk(t, j);
                    i2 = i + iZzo;
                    break;
                case 13:
                    i = i2 * 53;
                    iZzo = zzkd.zzk(t, j);
                    i2 = i + iZzo;
                    break;
                case 14:
                    i = i2 * 53;
                    iZzo = zzhm.zzo(zzkd.zzl(t, j));
                    i2 = i + iZzo;
                    break;
                case 15:
                    i = i2 * 53;
                    iZzo = zzkd.zzk(t, j);
                    i2 = i + iZzo;
                    break;
                case 16:
                    i = i2 * 53;
                    iZzo = zzhm.zzo(zzkd.zzl(t, j));
                    i2 = i + iZzo;
                    break;
                case 17:
                    Object objZzp2 = zzkd.zzp(t, j);
                    if (objZzp2 != null) {
                        iHashCode = objZzp2.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    iZzo = zzkd.zzp(t, j).hashCode();
                    i2 = i + iZzo;
                    break;
                case 50:
                    i = i2 * 53;
                    iZzo = zzkd.zzp(t, j).hashCode();
                    i2 = i + iZzo;
                    break;
                case 51:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzhm.zzo(Double.doubleToLongBits(zzf(t, j)));
                        i2 = i + iZzo;
                    }
                    break;
                case 52:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = Float.floatToIntBits(zzg(t, j));
                        i2 = i + iZzo;
                    }
                    break;
                case 53:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzhm.zzo(zzi(t, j));
                        i2 = i + iZzo;
                    }
                    break;
                case 54:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzhm.zzo(zzi(t, j));
                        i2 = i + iZzo;
                    }
                    break;
                case 55:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzh(t, j);
                        i2 = i + iZzo;
                    }
                    break;
                case 56:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzhm.zzo(zzi(t, j));
                        i2 = i + iZzo;
                    }
                    break;
                case 57:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzh(t, j);
                        i2 = i + iZzo;
                    }
                    break;
                case 58:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzhm.zzf(zzj(t, j));
                        i2 = i + iZzo;
                    }
                    break;
                case 59:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = ((String) zzkd.zzp(t, j)).hashCode();
                        i2 = i + iZzo;
                    }
                    break;
                case 60:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzkd.zzp(t, j).hashCode();
                        i2 = i + iZzo;
                    }
                    break;
                case 61:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzkd.zzp(t, j).hashCode();
                        i2 = i + iZzo;
                    }
                    break;
                case 62:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzh(t, j);
                        i2 = i + iZzo;
                    }
                    break;
                case 63:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzh(t, j);
                        i2 = i + iZzo;
                    }
                    break;
                case 64:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzh(t, j);
                        i2 = i + iZzo;
                    }
                    break;
                case 65:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzhm.zzo(zzi(t, j));
                        i2 = i + iZzo;
                    }
                    break;
                case 66:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzh(t, j);
                        i2 = i + iZzo;
                    }
                    break;
                case 67:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzhm.zzo(zzi(t, j));
                        i2 = i + iZzo;
                    }
                    break;
                case 68:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzo = zzkd.zzp(t, j).hashCode();
                        i2 = i + iZzo;
                    }
                    break;
            }
        }
        int iHashCode2 = (i2 * 53) + this.zzwf.zzaa(t).hashCode();
        return this.zzvw ? (iHashCode2 * 53) + this.zzwg.zzk(t).hashCode() : iHashCode2;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final void zze(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzvr.length; i += 3) {
            int iZzbh = zzbh(i);
            long j = 1048575 & iZzbh;
            int i2 = this.zzvr[i];
            switch ((iZzbh & 267386880) >>> 20) {
                case 0:
                    if (zza(t2, i)) {
                        zzkd.zza(t, j, zzkd.zzo(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 1:
                    if (zza(t2, i)) {
                        zzkd.zza((Object) t, j, zzkd.zzn(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 2:
                    if (zza(t2, i)) {
                        zzkd.zza((Object) t, j, zzkd.zzl(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 3:
                    if (zza(t2, i)) {
                        zzkd.zza((Object) t, j, zzkd.zzl(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 4:
                    if (zza(t2, i)) {
                        zzkd.zzb(t, j, zzkd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 5:
                    if (zza(t2, i)) {
                        zzkd.zza((Object) t, j, zzkd.zzl(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 6:
                    if (zza(t2, i)) {
                        zzkd.zzb(t, j, zzkd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 7:
                    if (zza(t2, i)) {
                        zzkd.zza(t, j, zzkd.zzm(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 8:
                    if (zza(t2, i)) {
                        zzkd.zza(t, j, zzkd.zzp(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zza(t2, i)) {
                        zzkd.zza(t, j, zzkd.zzp(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 11:
                    if (zza(t2, i)) {
                        zzkd.zzb(t, j, zzkd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 12:
                    if (zza(t2, i)) {
                        zzkd.zzb(t, j, zzkd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 13:
                    if (zza(t2, i)) {
                        zzkd.zzb(t, j, zzkd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 14:
                    if (zza(t2, i)) {
                        zzkd.zza((Object) t, j, zzkd.zzl(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 15:
                    if (zza(t2, i)) {
                        zzkd.zzb(t, j, zzkd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 16:
                    if (zza(t2, i)) {
                        zzkd.zza((Object) t, j, zzkd.zzl(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 17:
                    zza(t, t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzwe.zza(t, t2, j);
                    break;
                case 50:
                    zzjh.zza(this.zzwh, t, t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zza(t2, i2, i)) {
                        zzkd.zza(t, j, zzkd.zzp(t2, j));
                        zzb(t, i2, i);
                    }
                    break;
                case 60:
                    zzb(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zza(t2, i2, i)) {
                        zzkd.zza(t, j, zzkd.zzp(t2, j));
                        zzb(t, i2, i);
                    }
                    break;
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (this.zzvy) {
            return;
        }
        zzjh.zza(this.zzwf, t, t2);
        if (this.zzvw) {
            zzjh.zza(this.zzwg, t, t2);
        }
    }

    private final void zza(T t, T t2, int i) {
        long jZzbh = zzbh(i) & 1048575;
        if (zza(t2, i)) {
            Object objZzp = zzkd.zzp(t, jZzbh);
            Object objZzp2 = zzkd.zzp(t2, jZzbh);
            if (objZzp != null && objZzp2 != null) {
                zzkd.zza(t, jZzbh, zzhm.zzc(objZzp, objZzp2));
                zzb(t, i);
            } else if (objZzp2 != null) {
                zzkd.zza(t, jZzbh, objZzp2);
                zzb(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int iZzbh = zzbh(i);
        int i2 = this.zzvr[i];
        long j = iZzbh & 1048575;
        if (zza(t2, i2, i)) {
            Object objZzp = zzkd.zzp(t, j);
            Object objZzp2 = zzkd.zzp(t2, j);
            if (objZzp != null && objZzp2 != null) {
                zzkd.zza(t, j, zzhm.zzc(objZzp, objZzp2));
                zzb(t, i2, i);
            } else if (objZzp2 != null) {
                zzkd.zza(t, j, objZzp2);
                zzb(t, i2, i);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:423:0x0911 A[PHI: r5
  0x0911: PHI (r5v4 int) = 
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v16 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v1 int)
  (r5v17 int)
  (r5v1 int)
 binds: [B:257:0x0548, B:461:0x09b5, B:455:0x0999, B:452:0x0987, B:449:0x0978, B:446:0x096b, B:443:0x095e, B:439:0x0953, B:436:0x0948, B:433:0x093b, B:430:0x092e, B:427:0x091b, B:397:0x0822, B:391:0x0805, B:385:0x07e8, B:379:0x07cb, B:373:0x07ad, B:367:0x078f, B:361:0x0771, B:355:0x0753, B:349:0x0735, B:343:0x0717, B:337:0x06f9, B:331:0x06db, B:325:0x06bd, B:319:0x069f, B:314:0x066b, B:311:0x065e, B:308:0x064e, B:305:0x063e, B:302:0x062e, B:299:0x0620, B:296:0x0613, B:293:0x0606, B:287:0x05e8, B:284:0x05d4, B:281:0x05c2, B:278:0x05b2, B:275:0x05a2, B:441:0x095a, B:272:0x0595, B:269:0x0587, B:266:0x0577, B:263:0x0567, B:422:0x0910, B:260:0x0551] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final int zzw(T t) {
        int i;
        int i2;
        long j;
        int iZzd;
        int iZzc;
        int iZzl;
        int iZzv;
        int iZzaa;
        int iZzaq;
        int iZzas;
        int iZzb;
        int iZzaa2;
        int iZzaq2;
        int iZzas2;
        int i3 = 267386880;
        int i4 = 1048575;
        int i5 = 1;
        if (this.zzvy) {
            Unsafe unsafe = zzvq;
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.zzvr.length) {
                int iZzbh = zzbh(i6);
                int i8 = (iZzbh & i3) >>> 20;
                int i9 = this.zzvr[i6];
                long j2 = iZzbh & 1048575;
                int i10 = (i8 < zzhf.DOUBLE_LIST_PACKED.id() || i8 > zzhf.SINT64_LIST_PACKED.id()) ? 0 : this.zzvr[i6 + 2] & 1048575;
                switch (i8) {
                    case 0:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzb(i9, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i7 += iZzb;
                        }
                        break;
                    case 1:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzb(i9, 0.0f);
                            i7 += iZzb;
                        }
                        break;
                    case 2:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzd(i9, zzkd.zzl(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 3:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zze(i9, zzkd.zzl(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 4:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzh(i9, zzkd.zzk(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 5:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzg(i9, 0L);
                            i7 += iZzb;
                        }
                        break;
                    case 6:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzk(i9, 0);
                            i7 += iZzb;
                        }
                        break;
                    case 7:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzd(i9, true);
                            i7 += iZzb;
                        }
                        break;
                    case 8:
                        if (zza(t, i6)) {
                            Object objZzp = zzkd.zzp(t, j2);
                            if (objZzp instanceof zzfx) {
                                iZzb = zzgs.zzc(i9, (zzfx) objZzp);
                            } else {
                                iZzb = zzgs.zzc(i9, (String) objZzp);
                            }
                            i7 += iZzb;
                        }
                        break;
                    case 9:
                        if (zza(t, i6)) {
                            iZzb = zzjh.zzc(i9, zzkd.zzp(t, j2), zzbe(i6));
                            i7 += iZzb;
                        }
                        break;
                    case 10:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzc(i9, (zzfx) zzkd.zzp(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 11:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzi(i9, zzkd.zzk(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 12:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzm(i9, zzkd.zzk(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 13:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzl(i9, 0);
                            i7 += iZzb;
                        }
                        break;
                    case 14:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzh(i9, 0L);
                            i7 += iZzb;
                        }
                        break;
                    case 15:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzj(i9, zzkd.zzk(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 16:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzf(i9, zzkd.zzl(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 17:
                        if (zza(t, i6)) {
                            iZzb = zzgs.zzc(i9, (zziq) zzkd.zzp(t, j2), zzbe(i6));
                            i7 += iZzb;
                        }
                        break;
                    case 18:
                        iZzb = zzjh.zzw(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 19:
                        iZzb = zzjh.zzv(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 20:
                        iZzb = zzjh.zzo(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 21:
                        iZzb = zzjh.zzp(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 22:
                        iZzb = zzjh.zzs(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 23:
                        iZzb = zzjh.zzw(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 24:
                        iZzb = zzjh.zzv(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 25:
                        iZzb = zzjh.zzx(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 26:
                        iZzb = zzjh.zzc(i9, zze(t, j2));
                        i7 += iZzb;
                        break;
                    case 27:
                        iZzb = zzjh.zzc(i9, zze(t, j2), zzbe(i6));
                        i7 += iZzb;
                        break;
                    case 28:
                        iZzb = zzjh.zzd(i9, zze(t, j2));
                        i7 += iZzb;
                        break;
                    case 29:
                        iZzb = zzjh.zzt(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 30:
                        iZzb = zzjh.zzr(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 31:
                        iZzb = zzjh.zzv(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 32:
                        iZzb = zzjh.zzw(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 33:
                        iZzb = zzjh.zzu(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 34:
                        iZzb = zzjh.zzq(i9, zze(t, j2), false);
                        i7 += iZzb;
                        break;
                    case 35:
                        iZzaa2 = zzjh.zzaa((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 36:
                        iZzaa2 = zzjh.zzz((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 37:
                        iZzaa2 = zzjh.zzs((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 38:
                        iZzaa2 = zzjh.zzt((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 39:
                        iZzaa2 = zzjh.zzw((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 40:
                        iZzaa2 = zzjh.zzaa((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 41:
                        iZzaa2 = zzjh.zzz((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 42:
                        iZzaa2 = zzjh.zzab((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 43:
                        iZzaa2 = zzjh.zzx((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 44:
                        iZzaa2 = zzjh.zzv((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 45:
                        iZzaa2 = zzjh.zzz((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 46:
                        iZzaa2 = zzjh.zzaa((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 47:
                        iZzaa2 = zzjh.zzy((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 48:
                        iZzaa2 = zzjh.zzu((List) unsafe.getObject(t, j2));
                        if (iZzaa2 > 0) {
                            if (this.zzvz) {
                                unsafe.putInt(t, i10, iZzaa2);
                            }
                            iZzaq2 = zzgs.zzaq(i9);
                            iZzas2 = zzgs.zzas(iZzaa2);
                            iZzb = iZzaq2 + iZzas2 + iZzaa2;
                            i7 += iZzb;
                        }
                        break;
                    case 49:
                        iZzb = zzjh.zzd(i9, zze(t, j2), zzbe(i6));
                        i7 += iZzb;
                        break;
                    case 50:
                        iZzb = this.zzwh.zzc(i9, zzkd.zzp(t, j2), zzbf(i6));
                        i7 += iZzb;
                        break;
                    case 51:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzb(i9, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i7 += iZzb;
                        }
                        break;
                    case 52:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzb(i9, 0.0f);
                            i7 += iZzb;
                        }
                        break;
                    case 53:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzd(i9, zzi(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 54:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zze(i9, zzi(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 55:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzh(i9, zzh(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 56:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzg(i9, 0L);
                            i7 += iZzb;
                        }
                        break;
                    case 57:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzk(i9, 0);
                            i7 += iZzb;
                        }
                        break;
                    case 58:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzd(i9, true);
                            i7 += iZzb;
                        }
                        break;
                    case 59:
                        if (zza(t, i9, i6)) {
                            Object objZzp2 = zzkd.zzp(t, j2);
                            if (objZzp2 instanceof zzfx) {
                                iZzb = zzgs.zzc(i9, (zzfx) objZzp2);
                            } else {
                                iZzb = zzgs.zzc(i9, (String) objZzp2);
                            }
                            i7 += iZzb;
                        }
                        break;
                    case 60:
                        if (zza(t, i9, i6)) {
                            iZzb = zzjh.zzc(i9, zzkd.zzp(t, j2), zzbe(i6));
                            i7 += iZzb;
                        }
                        break;
                    case 61:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzc(i9, (zzfx) zzkd.zzp(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 62:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzi(i9, zzh(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 63:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzm(i9, zzh(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 64:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzl(i9, 0);
                            i7 += iZzb;
                        }
                        break;
                    case 65:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzh(i9, 0L);
                            i7 += iZzb;
                        }
                        break;
                    case 66:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzj(i9, zzh(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 67:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzf(i9, zzi(t, j2));
                            i7 += iZzb;
                        }
                        break;
                    case 68:
                        if (zza(t, i9, i6)) {
                            iZzb = zzgs.zzc(i9, (zziq) zzkd.zzp(t, j2), zzbe(i6));
                            i7 += iZzb;
                        }
                        break;
                }
                i6 += 3;
                i3 = 267386880;
            }
            return i7 + zza(this.zzwf, t);
        }
        Unsafe unsafe2 = zzvq;
        int i11 = 0;
        int iZzb2 = 0;
        int i12 = -1;
        int i13 = 0;
        while (i11 < this.zzvr.length) {
            int iZzbh2 = zzbh(i11);
            int[] iArr = this.zzvr;
            int i14 = iArr[i11];
            int i15 = (iZzbh2 & 267386880) >>> 20;
            if (i15 <= 17) {
                i = iArr[i11 + 2];
                int i16 = i & i4;
                i2 = i5 << (i >>> 20);
                if (i16 != i12) {
                    i13 = unsafe2.getInt(t, i16);
                } else {
                    i16 = i12;
                }
                i12 = i16;
            } else {
                i = (!this.zzvz || i15 < zzhf.DOUBLE_LIST_PACKED.id() || i15 > zzhf.SINT64_LIST_PACKED.id()) ? 0 : this.zzvr[i11 + 2] & i4;
                i2 = 0;
            }
            long j3 = iZzbh2 & i4;
            switch (i15) {
                case 0:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzb2 += zzgs.zzb(i14, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    }
                    break;
                case 1:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzb2 += zzgs.zzb(i14, 0.0f);
                    }
                    break;
                case 2:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzd = zzgs.zzd(i14, unsafe2.getLong(t, j3));
                        iZzb2 += iZzd;
                    }
                    break;
                case 3:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzd = zzgs.zze(i14, unsafe2.getLong(t, j3));
                        iZzb2 += iZzd;
                    }
                    break;
                case 4:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzd = zzgs.zzh(i14, unsafe2.getInt(t, j3));
                        iZzb2 += iZzd;
                    }
                    break;
                case 5:
                    if ((i13 & i2) != 0) {
                        j = 0;
                        iZzd = zzgs.zzg(i14, 0L);
                        iZzb2 += iZzd;
                    } else {
                        j = 0;
                    }
                    break;
                case 6:
                    if ((i13 & i2) != 0) {
                        iZzb2 += zzgs.zzk(i14, 0);
                    }
                    j = 0;
                    break;
                case 7:
                    if ((i13 & i2) != 0) {
                        iZzb2 += zzgs.zzd(i14, true);
                    }
                    j = 0;
                    break;
                case 8:
                    if ((i13 & i2) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzfx) {
                            iZzc = zzgs.zzc(i14, (zzfx) object);
                        } else {
                            iZzc = zzgs.zzc(i14, (String) object);
                        }
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 9:
                    if ((i13 & i2) != 0) {
                        iZzc = zzjh.zzc(i14, unsafe2.getObject(t, j3), zzbe(i11));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 10:
                    if ((i13 & i2) != 0) {
                        iZzc = zzgs.zzc(i14, (zzfx) unsafe2.getObject(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 11:
                    if ((i13 & i2) != 0) {
                        iZzc = zzgs.zzi(i14, unsafe2.getInt(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 12:
                    if ((i13 & i2) != 0) {
                        iZzc = zzgs.zzm(i14, unsafe2.getInt(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 13:
                    if ((i13 & i2) != 0) {
                        iZzl = zzgs.zzl(i14, 0);
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 14:
                    if ((i13 & i2) != 0) {
                        iZzc = zzgs.zzh(i14, 0L);
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 15:
                    if ((i13 & i2) != 0) {
                        iZzc = zzgs.zzj(i14, unsafe2.getInt(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 16:
                    if ((i13 & i2) != 0) {
                        iZzc = zzgs.zzf(i14, unsafe2.getLong(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 17:
                    if ((i13 & i2) != 0) {
                        iZzc = zzgs.zzc(i14, (zziq) unsafe2.getObject(t, j3), zzbe(i11));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 18:
                    iZzc = zzjh.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzc;
                    j = 0;
                    break;
                case 19:
                    iZzv = zzjh.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 20:
                    iZzv = zzjh.zzo(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 21:
                    iZzv = zzjh.zzp(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 22:
                    iZzv = zzjh.zzs(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 23:
                    iZzv = zzjh.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 24:
                    iZzv = zzjh.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 25:
                    iZzv = zzjh.zzx(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 26:
                    iZzc = zzjh.zzc(i14, (List) unsafe2.getObject(t, j3));
                    iZzb2 += iZzc;
                    j = 0;
                    break;
                case 27:
                    iZzc = zzjh.zzc(i14, (List<?>) unsafe2.getObject(t, j3), zzbe(i11));
                    iZzb2 += iZzc;
                    j = 0;
                    break;
                case 28:
                    iZzc = zzjh.zzd(i14, (List) unsafe2.getObject(t, j3));
                    iZzb2 += iZzc;
                    j = 0;
                    break;
                case 29:
                    iZzc = zzjh.zzt(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzc;
                    j = 0;
                    break;
                case 30:
                    iZzv = zzjh.zzr(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 31:
                    iZzv = zzjh.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 32:
                    iZzv = zzjh.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 33:
                    iZzv = zzjh.zzu(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 34:
                    iZzv = zzjh.zzq(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb2 += iZzv;
                    j = 0;
                    break;
                case 35:
                    iZzaa = zzjh.zzaa((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 36:
                    iZzaa = zzjh.zzz((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 37:
                    iZzaa = zzjh.zzs((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 38:
                    iZzaa = zzjh.zzt((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 39:
                    iZzaa = zzjh.zzw((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 40:
                    iZzaa = zzjh.zzaa((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 41:
                    iZzaa = zzjh.zzz((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 42:
                    iZzaa = zzjh.zzab((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 43:
                    iZzaa = zzjh.zzx((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 44:
                    iZzaa = zzjh.zzv((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 45:
                    iZzaa = zzjh.zzz((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 46:
                    iZzaa = zzjh.zzaa((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 47:
                    iZzaa = zzjh.zzy((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 48:
                    iZzaa = zzjh.zzu((List) unsafe2.getObject(t, j3));
                    if (iZzaa > 0) {
                        if (this.zzvz) {
                            unsafe2.putInt(t, i, iZzaa);
                        }
                        iZzaq = zzgs.zzaq(i14);
                        iZzas = zzgs.zzas(iZzaa);
                        iZzl = iZzaq + iZzas + iZzaa;
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 49:
                    iZzc = zzjh.zzd(i14, (List) unsafe2.getObject(t, j3), zzbe(i11));
                    iZzb2 += iZzc;
                    j = 0;
                    break;
                case 50:
                    iZzc = this.zzwh.zzc(i14, unsafe2.getObject(t, j3), zzbf(i11));
                    iZzb2 += iZzc;
                    j = 0;
                    break;
                case 51:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzb(i14, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 52:
                    if (zza(t, i14, i11)) {
                        iZzl = zzgs.zzb(i14, 0.0f);
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 53:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzd(i14, zzi(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 54:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zze(i14, zzi(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 55:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzh(i14, zzh(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 56:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzg(i14, 0L);
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 57:
                    if (zza(t, i14, i11)) {
                        iZzl = zzgs.zzk(i14, 0);
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 58:
                    if (zza(t, i14, i11)) {
                        iZzl = zzgs.zzd(i14, true);
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 59:
                    if (zza(t, i14, i11)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzfx) {
                            iZzc = zzgs.zzc(i14, (zzfx) object2);
                        } else {
                            iZzc = zzgs.zzc(i14, (String) object2);
                        }
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 60:
                    if (zza(t, i14, i11)) {
                        iZzc = zzjh.zzc(i14, unsafe2.getObject(t, j3), zzbe(i11));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 61:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzc(i14, (zzfx) unsafe2.getObject(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 62:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzi(i14, zzh(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 63:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzm(i14, zzh(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 64:
                    if (zza(t, i14, i11)) {
                        iZzl = zzgs.zzl(i14, 0);
                        iZzb2 += iZzl;
                    }
                    j = 0;
                    break;
                case 65:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzh(i14, 0L);
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 66:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzj(i14, zzh(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 67:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzf(i14, zzi(t, j3));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                case 68:
                    if (zza(t, i14, i11)) {
                        iZzc = zzgs.zzc(i14, (zziq) unsafe2.getObject(t, j3), zzbe(i11));
                        iZzb2 += iZzc;
                    }
                    j = 0;
                    break;
                default:
                    j = 0;
                    break;
            }
            i11 += 3;
            i4 = 1048575;
            i5 = 1;
        }
        int iZza = iZzb2 + zza(this.zzwf, t);
        if (!this.zzvw) {
            return iZza;
        }
        zzha<T> zzhaVarZzk = this.zzwg.zzk(t);
        int iZzb3 = 0;
        for (int i17 = 0; i17 < zzhaVarZzk.zzqf.zzir(); i17++) {
            Map.Entry entryZzbm = zzhaVarZzk.zzqf.zzbm(i17);
            iZzb3 += zzha.zzb((zzhc<?>) entryZzbm.getKey(), entryZzbm.getValue());
        }
        for (Map.Entry entry : zzhaVarZzk.zzqf.zzis()) {
            iZzb3 += zzha.zzb((zzhc<?>) entry.getKey(), entry.getValue());
        }
        return iZza + iZzb3;
    }

    private static <UT, UB> int zza(zzjx<UT, UB> zzjxVar, T t) {
        return zzjxVar.zzw(zzjxVar.zzaa(t));
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzkd.zzp(obj, j);
    }

    /* JADX WARN: Code duplicated, block: B:178:0x054a  */
    /* JADX WARN: Code duplicated, block: B:9:0x0032  */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final void zza(T t, zzku zzkuVar) throws IOException {
        Iterator it;
        Map.Entry<?, ?> entry;
        Iterator itDescendingIterator;
        Map.Entry<?, ?> entry2;
        if (zzkuVar.zzgd() == zzhi.zze.zzty) {
            zza(this.zzwf, t, zzkuVar);
            if (this.zzvw) {
                zzha<T> zzhaVarZzk = this.zzwg.zzk(t);
                if (zzhaVarZzk.zzqf.isEmpty()) {
                    itDescendingIterator = null;
                    entry2 = null;
                } else {
                    itDescendingIterator = zzhaVarZzk.descendingIterator();
                    entry2 = (Map.Entry) itDescendingIterator.next();
                }
            } else {
                itDescendingIterator = null;
                entry2 = null;
            }
            for (int length = this.zzvr.length - 3; length >= 0; length -= 3) {
                int iZzbh = zzbh(length);
                int i = this.zzvr[length];
                while (entry2 != null && this.zzwg.zza(entry2) > i) {
                    this.zzwg.zza(zzkuVar, entry2);
                    entry2 = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
                }
                switch ((iZzbh & 267386880) >>> 20) {
                    case 0:
                        if (zza(t, length)) {
                            zzkuVar.zza(i, zzkd.zzo(t, iZzbh & 1048575));
                        }
                        break;
                    case 1:
                        if (zza(t, length)) {
                            zzkuVar.zza(i, zzkd.zzn(t, iZzbh & 1048575));
                        }
                        break;
                    case 2:
                        if (zza(t, length)) {
                            zzkuVar.zzi(i, zzkd.zzl(t, iZzbh & 1048575));
                        }
                        break;
                    case 3:
                        if (zza(t, length)) {
                            zzkuVar.zza(i, zzkd.zzl(t, iZzbh & 1048575));
                        }
                        break;
                    case 4:
                        if (zza(t, length)) {
                            zzkuVar.zzd(i, zzkd.zzk(t, iZzbh & 1048575));
                        }
                        break;
                    case 5:
                        if (zza(t, length)) {
                            zzkuVar.zzc(i, zzkd.zzl(t, iZzbh & 1048575));
                        }
                        break;
                    case 6:
                        if (zza(t, length)) {
                            zzkuVar.zzg(i, zzkd.zzk(t, iZzbh & 1048575));
                        }
                        break;
                    case 7:
                        if (zza(t, length)) {
                            zzkuVar.zzc(i, zzkd.zzm(t, iZzbh & 1048575));
                        }
                        break;
                    case 8:
                        if (zza(t, length)) {
                            zza(i, zzkd.zzp(t, iZzbh & 1048575), zzkuVar);
                        }
                        break;
                    case 9:
                        if (zza(t, length)) {
                            zzkuVar.zza(i, zzkd.zzp(t, iZzbh & 1048575), zzbe(length));
                        }
                        break;
                    case 10:
                        if (zza(t, length)) {
                            zzkuVar.zza(i, (zzfx) zzkd.zzp(t, iZzbh & 1048575));
                        }
                        break;
                    case 11:
                        if (zza(t, length)) {
                            zzkuVar.zze(i, zzkd.zzk(t, iZzbh & 1048575));
                        }
                        break;
                    case 12:
                        if (zza(t, length)) {
                            zzkuVar.zzo(i, zzkd.zzk(t, iZzbh & 1048575));
                        }
                        break;
                    case 13:
                        if (zza(t, length)) {
                            zzkuVar.zzn(i, zzkd.zzk(t, iZzbh & 1048575));
                        }
                        break;
                    case 14:
                        if (zza(t, length)) {
                            zzkuVar.zzj(i, zzkd.zzl(t, iZzbh & 1048575));
                        }
                        break;
                    case 15:
                        if (zza(t, length)) {
                            zzkuVar.zzf(i, zzkd.zzk(t, iZzbh & 1048575));
                        }
                        break;
                    case 16:
                        if (zza(t, length)) {
                            zzkuVar.zzb(i, zzkd.zzl(t, iZzbh & 1048575));
                        }
                        break;
                    case 17:
                        if (zza(t, length)) {
                            zzkuVar.zzb(i, zzkd.zzp(t, iZzbh & 1048575), zzbe(length));
                        }
                        break;
                    case 18:
                        zzjh.zza(this.zzvr[length], (List<Double>) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 19:
                        zzjh.zzb(this.zzvr[length], (List<Float>) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 20:
                        zzjh.zzc(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 21:
                        zzjh.zzd(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 22:
                        zzjh.zzh(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 23:
                        zzjh.zzf(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 24:
                        zzjh.zzk(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 25:
                        zzjh.zzn(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 26:
                        zzjh.zza(this.zzvr[length], (List<String>) zzkd.zzp(t, iZzbh & 1048575), zzkuVar);
                        break;
                    case 27:
                        zzjh.zza(this.zzvr[length], (List<?>) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, zzbe(length));
                        break;
                    case 28:
                        zzjh.zzb(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar);
                        break;
                    case 29:
                        zzjh.zzi(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 30:
                        zzjh.zzm(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 31:
                        zzjh.zzl(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 32:
                        zzjh.zzg(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 33:
                        zzjh.zzj(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 34:
                        zzjh.zze(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, false);
                        break;
                    case 35:
                        zzjh.zza(this.zzvr[length], (List<Double>) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 36:
                        zzjh.zzb(this.zzvr[length], (List<Float>) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 37:
                        zzjh.zzc(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 38:
                        zzjh.zzd(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 39:
                        zzjh.zzh(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 40:
                        zzjh.zzf(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 41:
                        zzjh.zzk(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 42:
                        zzjh.zzn(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 43:
                        zzjh.zzi(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 44:
                        zzjh.zzm(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 45:
                        zzjh.zzl(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 46:
                        zzjh.zzg(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 47:
                        zzjh.zzj(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 48:
                        zzjh.zze(this.zzvr[length], (List) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, true);
                        break;
                    case 49:
                        zzjh.zzb(this.zzvr[length], (List<?>) zzkd.zzp(t, iZzbh & 1048575), zzkuVar, zzbe(length));
                        break;
                    case 50:
                        zza(zzkuVar, i, zzkd.zzp(t, iZzbh & 1048575), length);
                        break;
                    case 51:
                        if (zza(t, i, length)) {
                            zzkuVar.zza(i, zzf(t, iZzbh & 1048575));
                        }
                        break;
                    case 52:
                        if (zza(t, i, length)) {
                            zzkuVar.zza(i, zzg(t, iZzbh & 1048575));
                        }
                        break;
                    case 53:
                        if (zza(t, i, length)) {
                            zzkuVar.zzi(i, zzi(t, iZzbh & 1048575));
                        }
                        break;
                    case 54:
                        if (zza(t, i, length)) {
                            zzkuVar.zza(i, zzi(t, iZzbh & 1048575));
                        }
                        break;
                    case 55:
                        if (zza(t, i, length)) {
                            zzkuVar.zzd(i, zzh(t, iZzbh & 1048575));
                        }
                        break;
                    case 56:
                        if (zza(t, i, length)) {
                            zzkuVar.zzc(i, zzi(t, iZzbh & 1048575));
                        }
                        break;
                    case 57:
                        if (zza(t, i, length)) {
                            zzkuVar.zzg(i, zzh(t, iZzbh & 1048575));
                        }
                        break;
                    case 58:
                        if (zza(t, i, length)) {
                            zzkuVar.zzc(i, zzj(t, iZzbh & 1048575));
                        }
                        break;
                    case 59:
                        if (zza(t, i, length)) {
                            zza(i, zzkd.zzp(t, iZzbh & 1048575), zzkuVar);
                        }
                        break;
                    case 60:
                        if (zza(t, i, length)) {
                            zzkuVar.zza(i, zzkd.zzp(t, iZzbh & 1048575), zzbe(length));
                        }
                        break;
                    case 61:
                        if (zza(t, i, length)) {
                            zzkuVar.zza(i, (zzfx) zzkd.zzp(t, iZzbh & 1048575));
                        }
                        break;
                    case 62:
                        if (zza(t, i, length)) {
                            zzkuVar.zze(i, zzh(t, iZzbh & 1048575));
                        }
                        break;
                    case 63:
                        if (zza(t, i, length)) {
                            zzkuVar.zzo(i, zzh(t, iZzbh & 1048575));
                        }
                        break;
                    case 64:
                        if (zza(t, i, length)) {
                            zzkuVar.zzn(i, zzh(t, iZzbh & 1048575));
                        }
                        break;
                    case 65:
                        if (zza(t, i, length)) {
                            zzkuVar.zzj(i, zzi(t, iZzbh & 1048575));
                        }
                        break;
                    case 66:
                        if (zza(t, i, length)) {
                            zzkuVar.zzf(i, zzh(t, iZzbh & 1048575));
                        }
                        break;
                    case 67:
                        if (zza(t, i, length)) {
                            zzkuVar.zzb(i, zzi(t, iZzbh & 1048575));
                        }
                        break;
                    case 68:
                        if (zza(t, i, length)) {
                            zzkuVar.zzb(i, zzkd.zzp(t, iZzbh & 1048575), zzbe(length));
                        }
                        break;
                }
            }
            while (entry2 != null) {
                this.zzwg.zza(zzkuVar, entry2);
                entry2 = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
            }
            return;
        }
        if (this.zzvy) {
            if (this.zzvw) {
                zzha<T> zzhaVarZzk2 = this.zzwg.zzk(t);
                if (zzhaVarZzk2.zzqf.isEmpty()) {
                    it = null;
                    entry = null;
                } else {
                    it = zzhaVarZzk2.iterator();
                    entry = (Map.Entry) it.next();
                }
            } else {
                it = null;
                entry = null;
            }
            int length2 = this.zzvr.length;
            Map.Entry<?, ?> entry3 = entry;
            for (int i2 = 0; i2 < length2; i2 += 3) {
                int iZzbh2 = zzbh(i2);
                int i3 = this.zzvr[i2];
                while (entry3 != null && this.zzwg.zza(entry3) <= i3) {
                    this.zzwg.zza(zzkuVar, entry3);
                    entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                switch ((iZzbh2 & 267386880) >>> 20) {
                    case 0:
                        if (zza(t, i2)) {
                            zzkuVar.zza(i3, zzkd.zzo(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 1:
                        if (zza(t, i2)) {
                            zzkuVar.zza(i3, zzkd.zzn(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 2:
                        if (zza(t, i2)) {
                            zzkuVar.zzi(i3, zzkd.zzl(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 3:
                        if (zza(t, i2)) {
                            zzkuVar.zza(i3, zzkd.zzl(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 4:
                        if (zza(t, i2)) {
                            zzkuVar.zzd(i3, zzkd.zzk(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 5:
                        if (zza(t, i2)) {
                            zzkuVar.zzc(i3, zzkd.zzl(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 6:
                        if (zza(t, i2)) {
                            zzkuVar.zzg(i3, zzkd.zzk(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 7:
                        if (zza(t, i2)) {
                            zzkuVar.zzc(i3, zzkd.zzm(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 8:
                        if (zza(t, i2)) {
                            zza(i3, zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar);
                        }
                        break;
                    case 9:
                        if (zza(t, i2)) {
                            zzkuVar.zza(i3, zzkd.zzp(t, iZzbh2 & 1048575), zzbe(i2));
                        }
                        break;
                    case 10:
                        if (zza(t, i2)) {
                            zzkuVar.zza(i3, (zzfx) zzkd.zzp(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 11:
                        if (zza(t, i2)) {
                            zzkuVar.zze(i3, zzkd.zzk(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 12:
                        if (zza(t, i2)) {
                            zzkuVar.zzo(i3, zzkd.zzk(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 13:
                        if (zza(t, i2)) {
                            zzkuVar.zzn(i3, zzkd.zzk(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 14:
                        if (zza(t, i2)) {
                            zzkuVar.zzj(i3, zzkd.zzl(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 15:
                        if (zza(t, i2)) {
                            zzkuVar.zzf(i3, zzkd.zzk(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 16:
                        if (zza(t, i2)) {
                            zzkuVar.zzb(i3, zzkd.zzl(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 17:
                        if (zza(t, i2)) {
                            zzkuVar.zzb(i3, zzkd.zzp(t, iZzbh2 & 1048575), zzbe(i2));
                        }
                        break;
                    case 18:
                        zzjh.zza(this.zzvr[i2], (List<Double>) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 19:
                        zzjh.zzb(this.zzvr[i2], (List<Float>) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 20:
                        zzjh.zzc(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 21:
                        zzjh.zzd(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 22:
                        zzjh.zzh(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 23:
                        zzjh.zzf(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 24:
                        zzjh.zzk(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 25:
                        zzjh.zzn(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 26:
                        zzjh.zza(this.zzvr[i2], (List<String>) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar);
                        break;
                    case 27:
                        zzjh.zza(this.zzvr[i2], (List<?>) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, zzbe(i2));
                        break;
                    case 28:
                        zzjh.zzb(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar);
                        break;
                    case 29:
                        zzjh.zzi(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 30:
                        zzjh.zzm(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 31:
                        zzjh.zzl(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 32:
                        zzjh.zzg(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 33:
                        zzjh.zzj(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 34:
                        zzjh.zze(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, false);
                        break;
                    case 35:
                        zzjh.zza(this.zzvr[i2], (List<Double>) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 36:
                        zzjh.zzb(this.zzvr[i2], (List<Float>) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 37:
                        zzjh.zzc(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 38:
                        zzjh.zzd(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 39:
                        zzjh.zzh(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 40:
                        zzjh.zzf(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 41:
                        zzjh.zzk(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 42:
                        zzjh.zzn(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 43:
                        zzjh.zzi(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 44:
                        zzjh.zzm(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 45:
                        zzjh.zzl(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 46:
                        zzjh.zzg(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 47:
                        zzjh.zzj(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 48:
                        zzjh.zze(this.zzvr[i2], (List) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, true);
                        break;
                    case 49:
                        zzjh.zzb(this.zzvr[i2], (List<?>) zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar, zzbe(i2));
                        break;
                    case 50:
                        zza(zzkuVar, i3, zzkd.zzp(t, iZzbh2 & 1048575), i2);
                        break;
                    case 51:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zza(i3, zzf(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 52:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zza(i3, zzg(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 53:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzi(i3, zzi(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 54:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zza(i3, zzi(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 55:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzd(i3, zzh(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 56:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzc(i3, zzi(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 57:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzg(i3, zzh(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 58:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzc(i3, zzj(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 59:
                        if (zza(t, i3, i2)) {
                            zza(i3, zzkd.zzp(t, iZzbh2 & 1048575), zzkuVar);
                        }
                        break;
                    case 60:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zza(i3, zzkd.zzp(t, iZzbh2 & 1048575), zzbe(i2));
                        }
                        break;
                    case 61:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zza(i3, (zzfx) zzkd.zzp(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 62:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zze(i3, zzh(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 63:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzo(i3, zzh(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 64:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzn(i3, zzh(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 65:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzj(i3, zzi(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 66:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzf(i3, zzh(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 67:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzb(i3, zzi(t, iZzbh2 & 1048575));
                        }
                        break;
                    case 68:
                        if (zza(t, i3, i2)) {
                            zzkuVar.zzb(i3, zzkd.zzp(t, iZzbh2 & 1048575), zzbe(i2));
                        }
                        break;
                }
            }
            while (entry3 != null) {
                this.zzwg.zza(zzkuVar, entry3);
                entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            zza(this.zzwf, t, zzkuVar);
            return;
        }
        zzb(t, zzkuVar);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0023  */
    private final void zzb(T t, zzku zzkuVar) throws IOException {
        Iterator it;
        Map.Entry<?, ?> entry;
        int i;
        if (this.zzvw) {
            zzha<T> zzhaVarZzk = this.zzwg.zzk(t);
            if (zzhaVarZzk.zzqf.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = zzhaVarZzk.iterator();
                entry = (Map.Entry) it.next();
            }
        } else {
            it = null;
            entry = null;
        }
        int i2 = -1;
        int length = this.zzvr.length;
        Unsafe unsafe = zzvq;
        Map.Entry<?, ?> entry2 = entry;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int iZzbh = zzbh(i4);
            int[] iArr = this.zzvr;
            int i5 = iArr[i4];
            int i6 = (267386880 & iZzbh) >>> 20;
            if (this.zzvy || i6 > 17) {
                entry2 = entry2;
                i = 0;
            } else {
                int i7 = iArr[i4 + 2];
                int i8 = i7 & 1048575;
                if (i8 != i2) {
                    i3 = unsafe.getInt(t, i8);
                } else {
                    i8 = i2;
                }
                i = 1 << (i7 >>> 20);
                i2 = i8;
                entry2 = entry2;
            }
            while (entry2 != null && this.zzwg.zza(entry2) <= i5) {
                this.zzwg.zza(zzkuVar, entry2);
                entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            long j = iZzbh & 1048575;
            switch (i6) {
                case 0:
                    if ((i3 & i) != 0) {
                        zzkuVar.zza(i5, zzkd.zzo(t, j));
                        continue;
                    }
                    break;
                case 1:
                    if ((i3 & i) != 0) {
                        zzkuVar.zza(i5, zzkd.zzn(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzi(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 3:
                    if ((i3 & i) != 0) {
                        zzkuVar.zza(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 4:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzd(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 5:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzc(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 6:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzg(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 7:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzc(i5, zzkd.zzm(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 8:
                    if ((i3 & i) != 0) {
                        zza(i5, unsafe.getObject(t, j), zzkuVar);
                    } else {
                        continue;
                    }
                    break;
                case 9:
                    if ((i3 & i) != 0) {
                        zzkuVar.zza(i5, unsafe.getObject(t, j), zzbe(i4));
                    } else {
                        continue;
                    }
                    break;
                case 10:
                    if ((i3 & i) != 0) {
                        zzkuVar.zza(i5, (zzfx) unsafe.getObject(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 11:
                    if ((i3 & i) != 0) {
                        zzkuVar.zze(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 12:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzo(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 13:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzn(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 14:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzj(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 15:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzf(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 16:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzb(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 17:
                    if ((i3 & i) != 0) {
                        zzkuVar.zzb(i5, unsafe.getObject(t, j), zzbe(i4));
                    } else {
                        continue;
                    }
                    break;
                case 18:
                    zzjh.zza(this.zzvr[i4], (List<Double>) unsafe.getObject(t, j), zzkuVar, false);
                    continue;
                    break;
                case 19:
                    zzjh.zzb(this.zzvr[i4], (List<Float>) unsafe.getObject(t, j), zzkuVar, false);
                    continue;
                    break;
                case 20:
                    zzjh.zzc(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    continue;
                    break;
                case 21:
                    zzjh.zzd(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    continue;
                    break;
                case 22:
                    zzjh.zzh(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    continue;
                    break;
                case 23:
                    zzjh.zzf(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    continue;
                    break;
                case 24:
                    zzjh.zzk(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    continue;
                    break;
                case 25:
                    zzjh.zzn(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    continue;
                    break;
                case 26:
                    zzjh.zza(this.zzvr[i4], (List<String>) unsafe.getObject(t, j), zzkuVar);
                    break;
                case 27:
                    zzjh.zza(this.zzvr[i4], (List<?>) unsafe.getObject(t, j), zzkuVar, zzbe(i4));
                    break;
                case 28:
                    zzjh.zzb(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar);
                    break;
                case 29:
                    zzjh.zzi(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    break;
                case 30:
                    zzjh.zzm(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    break;
                case 31:
                    zzjh.zzl(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    break;
                case 32:
                    zzjh.zzg(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    break;
                case 33:
                    zzjh.zzj(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    break;
                case 34:
                    zzjh.zze(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, false);
                    break;
                case 35:
                    zzjh.zza(this.zzvr[i4], (List<Double>) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 36:
                    zzjh.zzb(this.zzvr[i4], (List<Float>) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 37:
                    zzjh.zzc(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 38:
                    zzjh.zzd(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 39:
                    zzjh.zzh(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 40:
                    zzjh.zzf(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 41:
                    zzjh.zzk(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 42:
                    zzjh.zzn(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 43:
                    zzjh.zzi(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 44:
                    zzjh.zzm(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 45:
                    zzjh.zzl(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 46:
                    zzjh.zzg(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 47:
                    zzjh.zzj(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 48:
                    zzjh.zze(this.zzvr[i4], (List) unsafe.getObject(t, j), zzkuVar, true);
                    break;
                case 49:
                    zzjh.zzb(this.zzvr[i4], (List<?>) unsafe.getObject(t, j), zzkuVar, zzbe(i4));
                    break;
                case 50:
                    zza(zzkuVar, i5, unsafe.getObject(t, j), i4);
                    break;
                case 51:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zza(i5, zzf(t, j));
                    }
                    break;
                case 52:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zza(i5, zzg(t, j));
                    }
                    break;
                case 53:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzi(i5, zzi(t, j));
                    }
                    break;
                case 54:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zza(i5, zzi(t, j));
                    }
                    break;
                case 55:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzd(i5, zzh(t, j));
                    }
                    break;
                case 56:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzc(i5, zzi(t, j));
                    }
                    break;
                case 57:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzg(i5, zzh(t, j));
                    }
                    break;
                case 58:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzc(i5, zzj(t, j));
                    }
                    break;
                case 59:
                    if (zza(t, i5, i4)) {
                        zza(i5, unsafe.getObject(t, j), zzkuVar);
                    }
                    break;
                case 60:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zza(i5, unsafe.getObject(t, j), zzbe(i4));
                    }
                    break;
                case 61:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zza(i5, (zzfx) unsafe.getObject(t, j));
                    }
                    break;
                case 62:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zze(i5, zzh(t, j));
                    }
                    break;
                case 63:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzo(i5, zzh(t, j));
                    }
                    break;
                case 64:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzn(i5, zzh(t, j));
                    }
                    break;
                case 65:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzj(i5, zzi(t, j));
                    }
                    break;
                case 66:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzf(i5, zzh(t, j));
                    }
                    break;
                case 67:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzb(i5, zzi(t, j));
                    }
                    break;
                case 68:
                    if (zza(t, i5, i4)) {
                        zzkuVar.zzb(i5, unsafe.getObject(t, j), zzbe(i4));
                    }
                    break;
            }
        }
        Map.Entry<?, ?> entry3 = entry2;
        while (entry3 != null) {
            this.zzwg.zza(zzkuVar, entry3);
            entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        zza(this.zzwf, t, zzkuVar);
    }

    private final <K, V> void zza(zzku zzkuVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzkuVar.zza(i, this.zzwh.zzu(zzbf(i2)), this.zzwh.zzq(obj));
        }
    }

    private static <UT, UB> void zza(zzjx<UT, UB> zzjxVar, T t, zzku zzkuVar) throws IOException {
        zzjxVar.zza(zzjxVar.zzaa(t), zzkuVar);
    }

    /* JADX WARN: Code duplicated, block: B:165:0x05cb A[LOOP:5: B:163:0x05c7->B:165:0x05cb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:167:0x05d8  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final void zza(T t, zzjg zzjgVar, zzgx zzgxVar) throws IOException {
        int i;
        if (zzgxVar == null) {
            throw new NullPointerException();
        }
        zzjx<?, ?> zzjxVar = this.zzwf;
        zzgz<?> zzgzVar = this.zzwg;
        zzha zzhaVarZzl = null;
        Object objZza = null;
        while (true) {
            try {
                int iZzfz = zzjgVar.zzfz();
                int iZzbk = zzbk(iZzfz);
                if (iZzbk >= 0) {
                    int iZzbh = zzbh(iZzbk);
                    switch ((267386880 & iZzbh) >>> 20) {
                        case 0:
                            zzkd.zza(t, iZzbh & 1048575, zzjgVar.readDouble());
                            zzb(t, iZzbk);
                            continue;
                        case 1:
                            zzkd.zza((Object) t, iZzbh & 1048575, zzjgVar.readFloat());
                            zzb(t, iZzbk);
                            continue;
                        case 2:
                            zzkd.zza((Object) t, iZzbh & 1048575, zzjgVar.zzfd());
                            zzb(t, iZzbk);
                            continue;
                        case 3:
                            zzkd.zza((Object) t, iZzbh & 1048575, zzjgVar.zzfc());
                            zzb(t, iZzbk);
                            continue;
                        case 4:
                            zzkd.zzb(t, iZzbh & 1048575, zzjgVar.zzfe());
                            zzb(t, iZzbk);
                            continue;
                        case 5:
                            zzkd.zza((Object) t, iZzbh & 1048575, zzjgVar.zzff());
                            zzb(t, iZzbk);
                            continue;
                        case 6:
                            zzkd.zzb(t, iZzbh & 1048575, zzjgVar.zzfg());
                            zzb(t, iZzbk);
                            continue;
                        case 7:
                            zzkd.zza(t, iZzbh & 1048575, zzjgVar.zzfh());
                            zzb(t, iZzbk);
                            continue;
                        case 8:
                            zza(t, iZzbh, zzjgVar);
                            zzb(t, iZzbk);
                            continue;
                        case 9:
                            if (zza(t, iZzbk)) {
                                long j = iZzbh & 1048575;
                                zzkd.zza(t, j, zzhm.zzc(zzkd.zzp(t, j), zzjgVar.zza(zzbe(iZzbk), zzgxVar)));
                            } else {
                                zzkd.zza(t, iZzbh & 1048575, zzjgVar.zza(zzbe(iZzbk), zzgxVar));
                                zzb(t, iZzbk);
                                continue;
                            }
                            break;
                        case 10:
                            zzkd.zza(t, iZzbh & 1048575, zzjgVar.zzfj());
                            zzb(t, iZzbk);
                            continue;
                        case 11:
                            zzkd.zzb(t, iZzbh & 1048575, zzjgVar.zzfk());
                            zzb(t, iZzbk);
                            continue;
                        case 12:
                            int iZzfl = zzjgVar.zzfl();
                            zzho zzhoVarZzbg = zzbg(iZzbk);
                            if (zzhoVarZzbg == null || zzhoVarZzbg.zzbc(iZzfl)) {
                                zzkd.zzb(t, iZzbh & 1048575, iZzfl);
                                zzb(t, iZzbk);
                                continue;
                            } else {
                                objZza = zzjh.zza(iZzfz, iZzfl, objZza, (zzjx<UT, Object>) zzjxVar);
                            }
                            break;
                        case 13:
                            zzkd.zzb(t, iZzbh & 1048575, zzjgVar.zzfm());
                            zzb(t, iZzbk);
                            continue;
                        case 14:
                            zzkd.zza((Object) t, iZzbh & 1048575, zzjgVar.zzfn());
                            zzb(t, iZzbk);
                            continue;
                        case 15:
                            zzkd.zzb(t, iZzbh & 1048575, zzjgVar.zzfo());
                            zzb(t, iZzbk);
                            continue;
                        case 16:
                            zzkd.zza((Object) t, iZzbh & 1048575, zzjgVar.zzfp());
                            zzb(t, iZzbk);
                            continue;
                        case 17:
                            if (zza(t, iZzbk)) {
                                long j2 = iZzbh & 1048575;
                                zzkd.zza(t, j2, zzhm.zzc(zzkd.zzp(t, j2), zzjgVar.zzb(zzbe(iZzbk), zzgxVar)));
                            } else {
                                zzkd.zza(t, iZzbh & 1048575, zzjgVar.zzb(zzbe(iZzbk), zzgxVar));
                                zzb(t, iZzbk);
                                continue;
                            }
                            break;
                        case 18:
                            zzjgVar.zzc(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 19:
                            zzjgVar.zzd(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 20:
                            zzjgVar.zzf(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 21:
                            zzjgVar.zze(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 22:
                            zzjgVar.zzg(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 23:
                            zzjgVar.zzh(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 24:
                            zzjgVar.zzi(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 25:
                            zzjgVar.zzj(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 26:
                            if (zzbj(iZzbh)) {
                                zzjgVar.zzk(this.zzwe.zza(t, iZzbh & 1048575));
                            } else {
                                zzjgVar.readStringList(this.zzwe.zza(t, iZzbh & 1048575));
                                continue;
                            }
                            break;
                        case 27:
                            zzjgVar.zza(this.zzwe.zza(t, iZzbh & 1048575), zzbe(iZzbk), zzgxVar);
                            continue;
                        case 28:
                            zzjgVar.zzl(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 29:
                            zzjgVar.zzm(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 30:
                            List<Integer> listZza = this.zzwe.zza(t, iZzbh & 1048575);
                            zzjgVar.zzn(listZza);
                            objZza = zzjh.zza(iZzfz, listZza, zzbg(iZzbk), objZza, zzjxVar);
                            continue;
                        case 31:
                            zzjgVar.zzo(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 32:
                            zzjgVar.zzp(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 33:
                            zzjgVar.zzq(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 34:
                            zzjgVar.zzr(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 35:
                            zzjgVar.zzc(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 36:
                            zzjgVar.zzd(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 37:
                            zzjgVar.zzf(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 38:
                            zzjgVar.zze(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 39:
                            zzjgVar.zzg(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 40:
                            zzjgVar.zzh(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 41:
                            zzjgVar.zzi(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 42:
                            zzjgVar.zzj(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 43:
                            zzjgVar.zzm(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 44:
                            List<Integer> listZza2 = this.zzwe.zza(t, iZzbh & 1048575);
                            zzjgVar.zzn(listZza2);
                            objZza = zzjh.zza(iZzfz, listZza2, zzbg(iZzbk), objZza, zzjxVar);
                            continue;
                        case 45:
                            zzjgVar.zzo(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 46:
                            zzjgVar.zzp(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 47:
                            zzjgVar.zzq(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 48:
                            zzjgVar.zzr(this.zzwe.zza(t, iZzbh & 1048575));
                            continue;
                        case 49:
                            zzjgVar.zzb(this.zzwe.zza(t, iZzbh & 1048575), zzbe(iZzbk), zzgxVar);
                            continue;
                        case 50:
                            Object objZzbf = zzbf(iZzbk);
                            long jZzbh = zzbh(iZzbk) & 1048575;
                            Object objZzp = zzkd.zzp(t, jZzbh);
                            if (objZzp == null) {
                                objZzp = this.zzwh.zzt(objZzbf);
                                zzkd.zza(t, jZzbh, objZzp);
                            } else if (this.zzwh.zzr(objZzp)) {
                                Object objZzt = this.zzwh.zzt(objZzbf);
                                this.zzwh.zzd(objZzt, objZzp);
                                zzkd.zza(t, jZzbh, objZzt);
                                objZzp = objZzt;
                            }
                            zzjgVar.zza(this.zzwh.zzp(objZzp), this.zzwh.zzu(objZzbf), zzgxVar);
                            continue;
                        case 51:
                            zzkd.zza(t, iZzbh & 1048575, Double.valueOf(zzjgVar.readDouble()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 52:
                            zzkd.zza(t, iZzbh & 1048575, Float.valueOf(zzjgVar.readFloat()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 53:
                            zzkd.zza(t, iZzbh & 1048575, Long.valueOf(zzjgVar.zzfd()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 54:
                            zzkd.zza(t, iZzbh & 1048575, Long.valueOf(zzjgVar.zzfc()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 55:
                            zzkd.zza(t, iZzbh & 1048575, Integer.valueOf(zzjgVar.zzfe()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 56:
                            zzkd.zza(t, iZzbh & 1048575, Long.valueOf(zzjgVar.zzff()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 57:
                            zzkd.zza(t, iZzbh & 1048575, Integer.valueOf(zzjgVar.zzfg()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 58:
                            zzkd.zza(t, iZzbh & 1048575, Boolean.valueOf(zzjgVar.zzfh()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 59:
                            zza(t, iZzbh, zzjgVar);
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 60:
                            if (zza(t, iZzfz, iZzbk)) {
                                long j3 = iZzbh & 1048575;
                                zzkd.zza(t, j3, zzhm.zzc(zzkd.zzp(t, j3), zzjgVar.zza(zzbe(iZzbk), zzgxVar)));
                            } else {
                                zzkd.zza(t, iZzbh & 1048575, zzjgVar.zza(zzbe(iZzbk), zzgxVar));
                                zzb(t, iZzbk);
                            }
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 61:
                            zzkd.zza(t, iZzbh & 1048575, zzjgVar.zzfj());
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 62:
                            zzkd.zza(t, iZzbh & 1048575, Integer.valueOf(zzjgVar.zzfk()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 63:
                            int iZzfl2 = zzjgVar.zzfl();
                            zzho zzhoVarZzbg2 = zzbg(iZzbk);
                            if (zzhoVarZzbg2 == null || zzhoVarZzbg2.zzbc(iZzfl2)) {
                                zzkd.zza(t, iZzbh & 1048575, Integer.valueOf(iZzfl2));
                                zzb(t, iZzfz, iZzbk);
                                continue;
                            } else {
                                objZza = zzjh.zza(iZzfz, iZzfl2, objZza, (zzjx<UT, Object>) zzjxVar);
                            }
                            break;
                        case 64:
                            zzkd.zza(t, iZzbh & 1048575, Integer.valueOf(zzjgVar.zzfm()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 65:
                            zzkd.zza(t, iZzbh & 1048575, Long.valueOf(zzjgVar.zzfn()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 66:
                            zzkd.zza(t, iZzbh & 1048575, Integer.valueOf(zzjgVar.zzfo()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 67:
                            zzkd.zza(t, iZzbh & 1048575, Long.valueOf(zzjgVar.zzfp()));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        case 68:
                            zzkd.zza(t, iZzbh & 1048575, zzjgVar.zzb(zzbe(iZzbk), zzgxVar));
                            zzb(t, iZzfz, iZzbk);
                            continue;
                        default:
                            if (objZza == null) {
                                try {
                                    objZza = zzjxVar.zziz();
                                } catch (zzhp unused) {
                                    zzjxVar.zza(zzjgVar);
                                    if (objZza == null) {
                                        objZza = zzjxVar.zzab(t);
                                    }
                                    if (!zzjxVar.zza((Object) objZza, zzjgVar)) {
                                        for (int i2 = this.zzwb; i2 < this.zzwc; i2++) {
                                            objZza = zza((Object) t, this.zzwa[i2], objZza, (zzjx<UT, Object>) zzjxVar);
                                        }
                                        if (objZza != null) {
                                            zzjxVar.zzh(t, (Object) objZza);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                break;
                            }
                            if (!zzjxVar.zza((Object) objZza, zzjgVar)) {
                                for (int i3 = this.zzwb; i3 < this.zzwc; i3++) {
                                    objZza = zza((Object) t, this.zzwa[i3], objZza, (zzjx<UT, Object>) zzjxVar);
                                }
                                if (objZza != null) {
                                    zzjxVar.zzh(t, (Object) objZza);
                                    return;
                                }
                                return;
                            }
                            break;
                            break;
                    }
                    for (i = this.zzwb; i < this.zzwc; i++) {
                        objZza = zza((Object) t, this.zzwa[i], objZza, (zzjx<UT, Object>) zzjxVar);
                    }
                    if (objZza != null) {
                        zzjxVar.zzh(t, (Object) objZza);
                    }
                    throw th;
                }
                if (iZzfz == Integer.MAX_VALUE) {
                    for (int i4 = this.zzwb; i4 < this.zzwc; i4++) {
                        objZza = zza((Object) t, this.zzwa[i4], objZza, (zzjx<UT, Object>) zzjxVar);
                    }
                    if (objZza != null) {
                        zzjxVar.zzh(t, (Object) objZza);
                        return;
                    }
                    return;
                }
                Object objZza2 = !this.zzvw ? null : zzgzVar.zza(zzgxVar, this.zzvv, iZzfz);
                if (objZza2 != null) {
                    if (zzhaVarZzl == null) {
                        zzhaVarZzl = zzgzVar.zzl(t);
                    }
                    zzha zzhaVar = zzhaVarZzl;
                    objZza = zzgzVar.zza(zzjgVar, objZza2, zzgxVar, zzhaVar, objZza, zzjxVar);
                    zzhaVarZzl = zzhaVar;
                } else {
                    zzjxVar.zza(zzjgVar);
                    if (objZza == null) {
                        objZza = zzjxVar.zzab(t);
                    }
                    if (!zzjxVar.zza((Object) objZza, zzjgVar)) {
                        for (int i5 = this.zzwb; i5 < this.zzwc; i5++) {
                            objZza = zza((Object) t, this.zzwa[i5], objZza, (zzjx<UT, Object>) zzjxVar);
                        }
                        if (objZza != null) {
                            zzjxVar.zzh(t, (Object) objZza);
                            return;
                        }
                        return;
                    }
                }
            } catch (Throwable th) {
                while (i < this.zzwc) {
                    objZza = zza((Object) t, this.zzwa[i], objZza, (zzjx<UT, Object>) zzjxVar);
                }
                if (objZza != null) {
                    zzjxVar.zzh(t, (Object) objZza);
                }
                throw th;
            }
        }
    }

    private static zzka zzx(Object obj) {
        zzhi zzhiVar = (zzhi) obj;
        zzka zzkaVar = zzhiVar.zztc;
        if (zzkaVar != zzka.zzja()) {
            return zzkaVar;
        }
        zzka zzkaVarZzjb = zzka.zzjb();
        zzhiVar.zztc = zzkaVarZzjb;
        return zzkaVarZzjb;
    }

    private static int zza(byte[] bArr, int i, int i2, zzko zzkoVar, Class<?> cls, zzfw zzfwVar) throws IOException {
        switch (zzit.zzpr[zzkoVar.ordinal()]) {
            case 1:
                int iZzb = zzft.zzb(bArr, i, zzfwVar);
                zzfwVar.zzot = Boolean.valueOf(zzfwVar.zzos != 0);
                return iZzb;
            case 2:
                return zzft.zze(bArr, i, zzfwVar);
            case 3:
                zzfwVar.zzot = Double.valueOf(zzft.zzc(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzfwVar.zzot = Integer.valueOf(zzft.zza(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzfwVar.zzot = Long.valueOf(zzft.zzb(bArr, i));
                return i + 8;
            case 8:
                zzfwVar.zzot = Float.valueOf(zzft.zzd(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int iZza = zzft.zza(bArr, i, zzfwVar);
                zzfwVar.zzot = Integer.valueOf(zzfwVar.zzor);
                return iZza;
            case 12:
            case 13:
                int iZzb2 = zzft.zzb(bArr, i, zzfwVar);
                zzfwVar.zzot = Long.valueOf(zzfwVar.zzos);
                return iZzb2;
            case 14:
                return zzft.zza(zzjb.zzii().zzk(cls), bArr, i, i2, zzfwVar);
            case 15:
                int iZza2 = zzft.zza(bArr, i, zzfwVar);
                zzfwVar.zzot = Integer.valueOf(zzgj.zzac(zzfwVar.zzor));
                return iZza2;
            case 16:
                int iZzb3 = zzft.zzb(bArr, i, zzfwVar);
                zzfwVar.zzot = Long.valueOf(zzgj.zze(zzfwVar.zzos));
                return iZzb3;
            case 17:
                return zzft.zzd(bArr, i, zzfwVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzfw zzfwVar) throws IOException {
        int iZza;
        int iZza2 = i;
        zzhn zzhnVarZzu = (zzhn) zzvq.getObject(t, j2);
        if (!zzhnVarZzu.zzer()) {
            int size = zzhnVarZzu.size();
            zzhnVarZzu = zzhnVarZzu.zzu(size == 0 ? 10 : size << 1);
            zzvq.putObject(t, j2, zzhnVarZzu);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzgt zzgtVar = (zzgt) zzhnVarZzu;
                    int iZza3 = zzft.zza(bArr, iZza2, zzfwVar);
                    int i8 = zzfwVar.zzor + iZza3;
                    while (iZza3 < i8) {
                        zzgtVar.zze(zzft.zzc(bArr, iZza3));
                        iZza3 += 8;
                    }
                    if (iZza3 == i8) {
                        return iZza3;
                    }
                    throw zzhq.zzhe();
                }
                if (i5 == 1) {
                    zzgt zzgtVar2 = (zzgt) zzhnVarZzu;
                    zzgtVar2.zze(zzft.zzc(bArr, i));
                    while (true) {
                        int i9 = iZza2 + 8;
                        if (i9 >= i2) {
                            return i9;
                        }
                        iZza2 = zzft.zza(bArr, i9, zzfwVar);
                        if (i3 != zzfwVar.zzor) {
                            return i9;
                        }
                        zzgtVar2.zze(zzft.zzc(bArr, iZza2));
                    }
                }
                return iZza2;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzhg zzhgVar = (zzhg) zzhnVarZzu;
                    int iZza4 = zzft.zza(bArr, iZza2, zzfwVar);
                    int i10 = zzfwVar.zzor + iZza4;
                    while (iZza4 < i10) {
                        zzhgVar.zzd(zzft.zzd(bArr, iZza4));
                        iZza4 += 4;
                    }
                    if (iZza4 == i10) {
                        return iZza4;
                    }
                    throw zzhq.zzhe();
                }
                if (i5 == 5) {
                    zzhg zzhgVar2 = (zzhg) zzhnVarZzu;
                    zzhgVar2.zzd(zzft.zzd(bArr, i));
                    while (true) {
                        int i11 = iZza2 + 4;
                        if (i11 >= i2) {
                            return i11;
                        }
                        iZza2 = zzft.zza(bArr, i11, zzfwVar);
                        if (i3 != zzfwVar.zzor) {
                            return i11;
                        }
                        zzhgVar2.zzd(zzft.zzd(bArr, iZza2));
                    }
                }
                return iZza2;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzie zzieVar = (zzie) zzhnVarZzu;
                    int iZza5 = zzft.zza(bArr, iZza2, zzfwVar);
                    int i12 = zzfwVar.zzor + iZza5;
                    while (iZza5 < i12) {
                        iZza5 = zzft.zzb(bArr, iZza5, zzfwVar);
                        zzieVar.zzp(zzfwVar.zzos);
                    }
                    if (iZza5 == i12) {
                        return iZza5;
                    }
                    throw zzhq.zzhe();
                }
                if (i5 == 0) {
                    zzie zzieVar2 = (zzie) zzhnVarZzu;
                    int iZzb = zzft.zzb(bArr, iZza2, zzfwVar);
                    zzieVar2.zzp(zzfwVar.zzos);
                    while (iZzb < i2) {
                        int iZza6 = zzft.zza(bArr, iZzb, zzfwVar);
                        if (i3 != zzfwVar.zzor) {
                            return iZzb;
                        }
                        iZzb = zzft.zzb(bArr, iZza6, zzfwVar);
                        zzieVar2.zzp(zzfwVar.zzos);
                    }
                    return iZzb;
                }
                return iZza2;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzft.zza(bArr, iZza2, (zzhn<?>) zzhnVarZzu, zzfwVar);
                }
                if (i5 == 0) {
                    return zzft.zza(i3, bArr, i, i2, (zzhn<?>) zzhnVarZzu, zzfwVar);
                }
                return iZza2;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzie zzieVar3 = (zzie) zzhnVarZzu;
                    int iZza7 = zzft.zza(bArr, iZza2, zzfwVar);
                    int i13 = zzfwVar.zzor + iZza7;
                    while (iZza7 < i13) {
                        zzieVar3.zzp(zzft.zzb(bArr, iZza7));
                        iZza7 += 8;
                    }
                    if (iZza7 == i13) {
                        return iZza7;
                    }
                    throw zzhq.zzhe();
                }
                if (i5 == 1) {
                    zzie zzieVar4 = (zzie) zzhnVarZzu;
                    zzieVar4.zzp(zzft.zzb(bArr, i));
                    while (true) {
                        int i14 = iZza2 + 8;
                        if (i14 >= i2) {
                            return i14;
                        }
                        iZza2 = zzft.zza(bArr, i14, zzfwVar);
                        if (i3 != zzfwVar.zzor) {
                            return i14;
                        }
                        zzieVar4.zzp(zzft.zzb(bArr, iZza2));
                    }
                }
                return iZza2;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzhk zzhkVar = (zzhk) zzhnVarZzu;
                    int iZza8 = zzft.zza(bArr, iZza2, zzfwVar);
                    int i15 = zzfwVar.zzor + iZza8;
                    while (iZza8 < i15) {
                        zzhkVar.zzbb(zzft.zza(bArr, iZza8));
                        iZza8 += 4;
                    }
                    if (iZza8 == i15) {
                        return iZza8;
                    }
                    throw zzhq.zzhe();
                }
                if (i5 == 5) {
                    zzhk zzhkVar2 = (zzhk) zzhnVarZzu;
                    zzhkVar2.zzbb(zzft.zza(bArr, i));
                    while (true) {
                        int i16 = iZza2 + 4;
                        if (i16 >= i2) {
                            return i16;
                        }
                        iZza2 = zzft.zza(bArr, i16, zzfwVar);
                        if (i3 != zzfwVar.zzor) {
                            return i16;
                        }
                        zzhkVar2.zzbb(zzft.zza(bArr, iZza2));
                    }
                }
                return iZza2;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzfv zzfvVar = (zzfv) zzhnVarZzu;
                    iZza = zzft.zza(bArr, iZza2, zzfwVar);
                    int i17 = zzfwVar.zzor + iZza;
                    while (iZza < i17) {
                        iZza = zzft.zzb(bArr, iZza, zzfwVar);
                        zzfvVar.addBoolean(zzfwVar.zzos != 0);
                    }
                    if (iZza != i17) {
                        throw zzhq.zzhe();
                    }
                    return iZza;
                }
                if (i5 == 0) {
                    zzfv zzfvVar2 = (zzfv) zzhnVarZzu;
                    iZza2 = zzft.zzb(bArr, iZza2, zzfwVar);
                    zzfvVar2.addBoolean(zzfwVar.zzos != 0);
                    while (iZza2 < i2) {
                        int iZza9 = zzft.zza(bArr, iZza2, zzfwVar);
                        if (i3 == zzfwVar.zzor) {
                            iZza2 = zzft.zzb(bArr, iZza9, zzfwVar);
                            zzfvVar2.addBoolean(zzfwVar.zzos != 0);
                        }
                    }
                }
                return iZza2;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        iZza2 = zzft.zza(bArr, iZza2, zzfwVar);
                        int i18 = zzfwVar.zzor;
                        if (i18 < 0) {
                            throw zzhq.zzhf();
                        }
                        if (i18 == 0) {
                            zzhnVarZzu.add("");
                        } else {
                            zzhnVarZzu.add(new String(bArr, iZza2, i18, zzhm.UTF_8));
                            iZza2 += i18;
                        }
                        while (iZza2 < i2) {
                            int iZza10 = zzft.zza(bArr, iZza2, zzfwVar);
                            if (i3 == zzfwVar.zzor) {
                                iZza2 = zzft.zza(bArr, iZza10, zzfwVar);
                                int i19 = zzfwVar.zzor;
                                if (i19 < 0) {
                                    throw zzhq.zzhf();
                                }
                                if (i19 == 0) {
                                    zzhnVarZzu.add("");
                                } else {
                                    zzhnVarZzu.add(new String(bArr, iZza2, i19, zzhm.UTF_8));
                                    iZza2 += i19;
                                }
                            }
                        }
                    } else {
                        iZza2 = zzft.zza(bArr, iZza2, zzfwVar);
                        int i20 = zzfwVar.zzor;
                        if (i20 < 0) {
                            throw zzhq.zzhf();
                        }
                        if (i20 == 0) {
                            zzhnVarZzu.add("");
                        } else {
                            int i21 = iZza2 + i20;
                            if (!zzkg.zze(bArr, iZza2, i21)) {
                                throw zzhq.zzhm();
                            }
                            zzhnVarZzu.add(new String(bArr, iZza2, i20, zzhm.UTF_8));
                            iZza2 = i21;
                        }
                        while (iZza2 < i2) {
                            int iZza11 = zzft.zza(bArr, iZza2, zzfwVar);
                            if (i3 == zzfwVar.zzor) {
                                iZza2 = zzft.zza(bArr, iZza11, zzfwVar);
                                int i22 = zzfwVar.zzor;
                                if (i22 < 0) {
                                    throw zzhq.zzhf();
                                }
                                if (i22 == 0) {
                                    zzhnVarZzu.add("");
                                } else {
                                    int i23 = iZza2 + i22;
                                    if (!zzkg.zze(bArr, iZza2, i23)) {
                                        throw zzhq.zzhm();
                                    }
                                    zzhnVarZzu.add(new String(bArr, iZza2, i22, zzhm.UTF_8));
                                    iZza2 = i23;
                                }
                            }
                        }
                    }
                }
                return iZza2;
            case 27:
                if (i5 == 2) {
                    return zzft.zza(zzbe(i6), i3, bArr, i, i2, zzhnVarZzu, zzfwVar);
                }
                return iZza2;
            case 28:
                if (i5 == 2) {
                    int iZza12 = zzft.zza(bArr, iZza2, zzfwVar);
                    int i24 = zzfwVar.zzor;
                    if (i24 < 0) {
                        throw zzhq.zzhf();
                    }
                    if (i24 > bArr.length - iZza12) {
                        throw zzhq.zzhe();
                    }
                    if (i24 == 0) {
                        zzhnVarZzu.add(zzfx.zzov);
                    } else {
                        zzhnVarZzu.add(zzfx.zza(bArr, iZza12, i24));
                        iZza12 += i24;
                    }
                    while (iZza12 < i2) {
                        int iZza13 = zzft.zza(bArr, iZza12, zzfwVar);
                        if (i3 != zzfwVar.zzor) {
                            return iZza12;
                        }
                        iZza12 = zzft.zza(bArr, iZza13, zzfwVar);
                        int i25 = zzfwVar.zzor;
                        if (i25 < 0) {
                            throw zzhq.zzhf();
                        }
                        if (i25 > bArr.length - iZza12) {
                            throw zzhq.zzhe();
                        }
                        if (i25 == 0) {
                            zzhnVarZzu.add(zzfx.zzov);
                        } else {
                            zzhnVarZzu.add(zzfx.zza(bArr, iZza12, i25));
                            iZza12 += i25;
                        }
                    }
                    return iZza12;
                }
                return iZza2;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        iZza = zzft.zza(i3, bArr, i, i2, (zzhn<?>) zzhnVarZzu, zzfwVar);
                    }
                    return iZza2;
                }
                iZza = zzft.zza(bArr, iZza2, (zzhn<?>) zzhnVarZzu, zzfwVar);
                zzhi zzhiVar = (zzhi) t;
                zzka zzkaVar = zzhiVar.zztc;
                if (zzkaVar == zzka.zzja()) {
                    zzkaVar = null;
                }
                zzka zzkaVar2 = (zzka) zzjh.zza(i4, zzhnVarZzu, zzbg(i6), zzkaVar, this.zzwf);
                if (zzkaVar2 != null) {
                    zzhiVar.zztc = zzkaVar2;
                }
                return iZza;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzhk zzhkVar3 = (zzhk) zzhnVarZzu;
                    int iZza14 = zzft.zza(bArr, iZza2, zzfwVar);
                    int i26 = zzfwVar.zzor + iZza14;
                    while (iZza14 < i26) {
                        iZza14 = zzft.zza(bArr, iZza14, zzfwVar);
                        zzhkVar3.zzbb(zzgj.zzac(zzfwVar.zzor));
                    }
                    if (iZza14 == i26) {
                        return iZza14;
                    }
                    throw zzhq.zzhe();
                }
                if (i5 == 0) {
                    zzhk zzhkVar4 = (zzhk) zzhnVarZzu;
                    int iZza15 = zzft.zza(bArr, iZza2, zzfwVar);
                    zzhkVar4.zzbb(zzgj.zzac(zzfwVar.zzor));
                    while (iZza15 < i2) {
                        int iZza16 = zzft.zza(bArr, iZza15, zzfwVar);
                        if (i3 != zzfwVar.zzor) {
                            return iZza15;
                        }
                        iZza15 = zzft.zza(bArr, iZza16, zzfwVar);
                        zzhkVar4.zzbb(zzgj.zzac(zzfwVar.zzor));
                    }
                    return iZza15;
                }
                return iZza2;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzie zzieVar5 = (zzie) zzhnVarZzu;
                    int iZza17 = zzft.zza(bArr, iZza2, zzfwVar);
                    int i27 = zzfwVar.zzor + iZza17;
                    while (iZza17 < i27) {
                        iZza17 = zzft.zzb(bArr, iZza17, zzfwVar);
                        zzieVar5.zzp(zzgj.zze(zzfwVar.zzos));
                    }
                    if (iZza17 == i27) {
                        return iZza17;
                    }
                    throw zzhq.zzhe();
                }
                if (i5 == 0) {
                    zzie zzieVar6 = (zzie) zzhnVarZzu;
                    int iZzb2 = zzft.zzb(bArr, iZza2, zzfwVar);
                    zzieVar6.zzp(zzgj.zze(zzfwVar.zzos));
                    while (iZzb2 < i2) {
                        int iZza18 = zzft.zza(bArr, iZzb2, zzfwVar);
                        if (i3 != zzfwVar.zzor) {
                            return iZzb2;
                        }
                        iZzb2 = zzft.zzb(bArr, iZza18, zzfwVar);
                        zzieVar6.zzp(zzgj.zze(zzfwVar.zzos));
                    }
                    return iZzb2;
                }
                return iZza2;
            case 49:
                if (i5 == 3) {
                    zzjf zzjfVarZzbe = zzbe(i6);
                    int i28 = (i3 & (-8)) | 4;
                    iZza2 = zzft.zza(zzjfVarZzbe, bArr, i, i2, i28, zzfwVar);
                    zzhnVarZzu.add(zzfwVar.zzot);
                    while (iZza2 < i2) {
                        int iZza19 = zzft.zza(bArr, iZza2, zzfwVar);
                        if (i3 == zzfwVar.zzor) {
                            iZza2 = zzft.zza(zzjfVarZzbe, bArr, iZza19, i2, i28, zzfwVar);
                            zzhnVarZzu.add(zzfwVar.zzot);
                        }
                    }
                }
                return iZza2;
            default:
                return iZza2;
        }
    }

    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, long j, zzfw zzfwVar) throws IOException {
        Unsafe unsafe = zzvq;
        Object objZzbf = zzbf(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzwh.zzr(object)) {
            Object objZzt = this.zzwh.zzt(objZzbf);
            this.zzwh.zzd(objZzt, object);
            unsafe.putObject(t, j, objZzt);
            object = objZzt;
        }
        zzih<?, ?> zzihVarZzu = this.zzwh.zzu(objZzbf);
        Map<?, ?> mapZzp = this.zzwh.zzp(object);
        int iZza = zzft.zza(bArr, i, zzfwVar);
        int i4 = zzfwVar.zzor;
        if (i4 < 0 || i4 > i2 - iZza) {
            throw zzhq.zzhe();
        }
        int i5 = i4 + iZza;
        K k = zzihVarZzu.zzvj;
        V v = zzihVarZzu.zzvl;
        while (iZza < i5) {
            int iZza2 = iZza + 1;
            int i6 = bArr[iZza];
            if (i6 < 0) {
                iZza2 = zzft.zza(i6, bArr, iZza2, zzfwVar);
                i6 = zzfwVar.zzor;
            }
            int i7 = iZza2;
            int i8 = i6 >>> 3;
            int i9 = i6 & 7;
            if (i8 == 1) {
                if (i9 == zzihVarZzu.zzvi.zzjk()) {
                    iZza = zza(bArr, i7, i2, zzihVarZzu.zzvi, (Class<?>) null, zzfwVar);
                    k = (K) zzfwVar.zzot;
                } else {
                    iZza = zzft.zza(i6, bArr, i7, i2, zzfwVar);
                }
            } else if (i8 == 2 && i9 == zzihVarZzu.zzvk.zzjk()) {
                iZza = zza(bArr, i7, i2, zzihVarZzu.zzvk, zzihVarZzu.zzvl.getClass(), zzfwVar);
                v = zzfwVar.zzot;
            } else {
                iZza = zzft.zza(i6, bArr, i7, i2, zzfwVar);
            }
        }
        if (iZza != i5) {
            throw zzhq.zzhl();
        }
        mapZzp.put(k, v);
        return i5;
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzfw zzfwVar) throws IOException {
        int iZzb;
        Unsafe unsafe = zzvq;
        long j2 = this.zzvr[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Double.valueOf(zzft.zzc(bArr, i)));
                iZzb = i + 8;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 52:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Float.valueOf(zzft.zzd(bArr, i)));
                iZzb = i + 4;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 53:
            case 54:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzft.zzb(bArr, i, zzfwVar);
                unsafe.putObject(t, j, Long.valueOf(zzfwVar.zzos));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzft.zza(bArr, i, zzfwVar);
                unsafe.putObject(t, j, Integer.valueOf(zzfwVar.zzor));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 56:
            case 65:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Long.valueOf(zzft.zzb(bArr, i)));
                iZzb = i + 8;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Integer.valueOf(zzft.zza(bArr, i)));
                iZzb = i + 4;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzft.zzb(bArr, i, zzfwVar);
                unsafe.putObject(t, j, Boolean.valueOf(zzfwVar.zzos != 0));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int iZza = zzft.zza(bArr, i, zzfwVar);
                int i9 = zzfwVar.zzor;
                if (i9 == 0) {
                    unsafe.putObject(t, j, "");
                } else {
                    if ((i6 & DriveFile.MODE_WRITE_ONLY) != 0 && !zzkg.zze(bArr, iZza, iZza + i9)) {
                        throw zzhq.zzhm();
                    }
                    unsafe.putObject(t, j, new String(bArr, iZza, i9, zzhm.UTF_8));
                    iZza += i9;
                }
                unsafe.putInt(t, j2, i4);
                return iZza;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int iZza2 = zzft.zza(zzbe(i8), bArr, i, i2, zzfwVar);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, zzfwVar.zzot);
                } else {
                    unsafe.putObject(t, j, zzhm.zzc(object, zzfwVar.zzot));
                }
                unsafe.putInt(t, j2, i4);
                return iZza2;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                iZzb = zzft.zze(bArr, i, zzfwVar);
                unsafe.putObject(t, j, zzfwVar.zzot);
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                int iZza3 = zzft.zza(bArr, i, zzfwVar);
                int i10 = zzfwVar.zzor;
                zzho zzhoVarZzbg = zzbg(i8);
                if (zzhoVarZzbg == null || zzhoVarZzbg.zzbc(i10)) {
                    unsafe.putObject(t, j, Integer.valueOf(i10));
                    iZzb = iZza3;
                    unsafe.putInt(t, j2, i4);
                    return iZzb;
                }
                zzx(t).zzb(i3, Long.valueOf(i10));
                return iZza3;
            case 66:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzft.zza(bArr, i, zzfwVar);
                unsafe.putObject(t, j, Integer.valueOf(zzgj.zzac(zzfwVar.zzor)));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 67:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzft.zzb(bArr, i, zzfwVar);
                unsafe.putObject(t, j, Long.valueOf(zzgj.zze(zzfwVar.zzos)));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 68:
                if (i5 != 3) {
                    return i;
                }
                iZzb = zzft.zza(zzbe(i8), bArr, i, i2, (i3 & (-8)) | 4, zzfwVar);
                Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object2 == null) {
                    unsafe.putObject(t, j, zzfwVar.zzot);
                } else {
                    unsafe.putObject(t, j, zzhm.zzc(object2, zzfwVar.zzot));
                }
                unsafe.putInt(t, j2, i4);
                return iZzb;
            default:
                return i;
        }
    }

    private final zzjf zzbe(int i) {
        int i2 = (i / 3) << 1;
        zzjf zzjfVar = (zzjf) this.zzvs[i2];
        if (zzjfVar != null) {
            return zzjfVar;
        }
        zzjf<T> zzjfVarZzk = zzjb.zzii().zzk((Class) this.zzvs[i2 + 1]);
        this.zzvs[i2] = zzjfVarZzk;
        return zzjfVarZzk;
    }

    private final Object zzbf(int i) {
        return this.zzvs[(i / 3) << 1];
    }

    private final zzho zzbg(int i) {
        return (zzho) this.zzvs[((i / 3) << 1) + 1];
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 12701. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    final int zza(T r30, byte[] r31, int r32, int r33, int r34, com.google.android.gms.internal.firebase_remote_config.zzfw r35) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_remote_config.zziu.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.firebase_remote_config.zzfw):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:87:0x01a4  */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x022e, code lost:
    
        if (r0 == r15) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0230, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01e2, code lost:
    
        if (r0 == r15) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x020f, code lost:
    
        if (r0 == r15) goto L104;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:20:0x0061. Please report as an issue. */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.firebase_remote_config.zzfw r32) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_remote_config.zziu.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase_remote_config.zzfw):void");
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final void zzm(T t) {
        int i;
        int i2 = this.zzwb;
        while (true) {
            i = this.zzwc;
            if (i2 >= i) {
                break;
            }
            long jZzbh = zzbh(this.zzwa[i2]) & 1048575;
            Object objZzp = zzkd.zzp(t, jZzbh);
            if (objZzp != null) {
                zzkd.zza(t, jZzbh, this.zzwh.zzs(objZzp));
            }
            i2++;
        }
        int length = this.zzwa.length;
        while (i < length) {
            this.zzwe.zzb(t, this.zzwa[i]);
            i++;
        }
        this.zzwf.zzm(t);
        if (this.zzvw) {
            this.zzwg.zzm(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzjx<UT, UB> zzjxVar) {
        zzho zzhoVarZzbg;
        int i2 = this.zzvr[i];
        Object objZzp = zzkd.zzp(obj, zzbh(i) & 1048575);
        return (objZzp == null || (zzhoVarZzbg = zzbg(i)) == null) ? ub : (UB) zza(i, i2, this.zzwh.zzp(objZzp), zzhoVarZzbg, ub, zzjxVar);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzho zzhoVar, UB ub, zzjx<UT, UB> zzjxVar) {
        zzih<?, ?> zzihVarZzu = this.zzwh.zzu(zzbf(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzhoVar.zzbc(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzjxVar.zziz();
                }
                zzgf zzgfVarZzx = zzfx.zzx(zzii.zza(zzihVarZzu, next.getKey(), next.getValue()));
                try {
                    zzii.zza(zzgfVarZzx.zzfa(), zzihVarZzu, next.getKey(), next.getValue());
                    zzjxVar.zza(ub, i2, zzgfVarZzx.zzez());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Code duplicated, block: B:54:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:56:0x00db  */
    /* JADX WARN: Code duplicated, block: B:59:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:62:0x00f2 A[LOOP:2: B:57:0x00e0->B:62:0x00f2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:81:0x00f7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x0109 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x00f0 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.firebase_remote_config.zzjf] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.firebase_remote_config.zzjf] */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final boolean zzy(T t) {
        int i;
        List list;
        ?? Zzbe;
        int i2;
        int i3 = 0;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= this.zzwb) {
                return !this.zzvw || this.zzwg.zzk(t).isInitialized();
            }
            int i6 = this.zzwa[i3];
            int i7 = this.zzvr[i6];
            int iZzbh = zzbh(i6);
            if (this.zzvy) {
                i = 0;
            } else {
                int i8 = this.zzvr[i6 + 2];
                int i9 = i8 & 1048575;
                i = 1 << (i8 >>> 20);
                if (i9 != i4) {
                    i5 = zzvq.getInt(t, i9);
                    i4 = i9;
                }
            }
            if (((268435456 & iZzbh) != 0) && !zza(t, i6, i5, i)) {
                return false;
            }
            int i10 = (267386880 & iZzbh) >>> 20;
            if (i10 == 9 || i10 == 17) {
                if (zza(t, i6, i5, i) && !zza(t, iZzbh, zzbe(i6))) {
                    return false;
                }
            } else if (i10 == 27) {
                list = (List) zzkd.zzp(t, iZzbh & 1048575);
                if (!list.isEmpty()) {
                    Zzbe = zzbe(i6);
                    for (i2 = 0; i2 < list.size(); i2++) {
                        if (!Zzbe.zzy(list.get(i2))) {
                            z = false;
                            break;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (i10 == 60 || i10 == 68) {
                if (zza(t, i7, i6) && !zza(t, iZzbh, zzbe(i6))) {
                    return false;
                }
            } else if (i10 == 49) {
                list = (List) zzkd.zzp(t, iZzbh & 1048575);
                if (!list.isEmpty()) {
                    Zzbe = zzbe(i6);
                    while (i2 < list.size()) {
                        if (!Zzbe.zzy(list.get(i2))) {
                            z = false;
                            break;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (i10 != 50) {
                continue;
            } else {
                Map<?, ?> mapZzq = this.zzwh.zzq(zzkd.zzp(t, iZzbh & 1048575));
                if (!mapZzq.isEmpty()) {
                    if (this.zzwh.zzu(zzbf(i6)).zzvk.zzjj() == zzkr.MESSAGE) {
                        ?? Zzk = 0;
                        for (Object obj : mapZzq.values()) {
                            if (Zzk == 0) {
                                Zzk = Zzk;
                                Zzk = zzjb.zzii().zzk(obj.getClass());
                            }
                            Zzk = Zzk;
                            if (!Zzk.zzy(obj)) {
                                z = false;
                                break;
                            }
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            }
            i3++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zza(Object obj, int i, zzjf zzjfVar) {
        return zzjfVar.zzy(zzkd.zzp(obj, i & 1048575));
    }

    private static void zza(int i, Object obj, zzku zzkuVar) throws IOException {
        if (obj instanceof String) {
            zzkuVar.zzb(i, (String) obj);
        } else {
            zzkuVar.zza(i, (zzfx) obj);
        }
    }

    private final void zza(Object obj, int i, zzjg zzjgVar) throws IOException {
        if (zzbj(i)) {
            zzkd.zza(obj, i & 1048575, zzjgVar.zzfi());
        } else if (this.zzvx) {
            zzkd.zza(obj, i & 1048575, zzjgVar.readString());
        } else {
            zzkd.zza(obj, i & 1048575, zzjgVar.zzfj());
        }
    }

    private final int zzbh(int i) {
        return this.zzvr[i + 1];
    }

    private final int zzbi(int i) {
        return this.zzvr[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzkd.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzkd.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzkd.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzkd.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzkd.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzvy) {
            return zza(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzvy) {
            int iZzbh = zzbh(i);
            long j = iZzbh & 1048575;
            switch ((iZzbh & 267386880) >>> 20) {
                case 0:
                    return zzkd.zzo(t, j) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                case 1:
                    return zzkd.zzn(t, j) != 0.0f;
                case 2:
                    return zzkd.zzl(t, j) != 0;
                case 3:
                    return zzkd.zzl(t, j) != 0;
                case 4:
                    return zzkd.zzk(t, j) != 0;
                case 5:
                    return zzkd.zzl(t, j) != 0;
                case 6:
                    return zzkd.zzk(t, j) != 0;
                case 7:
                    return zzkd.zzm(t, j);
                case 8:
                    Object objZzp = zzkd.zzp(t, j);
                    if (objZzp instanceof String) {
                        return !((String) objZzp).isEmpty();
                    }
                    if (objZzp instanceof zzfx) {
                        return !zzfx.zzov.equals(objZzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzkd.zzp(t, j) != null;
                case 10:
                    return !zzfx.zzov.equals(zzkd.zzp(t, j));
                case 11:
                    return zzkd.zzk(t, j) != 0;
                case 12:
                    return zzkd.zzk(t, j) != 0;
                case 13:
                    return zzkd.zzk(t, j) != 0;
                case 14:
                    return zzkd.zzl(t, j) != 0;
                case 15:
                    return zzkd.zzk(t, j) != 0;
                case 16:
                    return zzkd.zzl(t, j) != 0;
                case 17:
                    return zzkd.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int iZzbi = zzbi(i);
        return (zzkd.zzk(t, (long) (iZzbi & 1048575)) & (1 << (iZzbi >>> 20))) != 0;
    }

    private final void zzb(T t, int i) {
        if (this.zzvy) {
            return;
        }
        int iZzbi = zzbi(i);
        long j = iZzbi & 1048575;
        zzkd.zzb(t, j, zzkd.zzk(t, j) | (1 << (iZzbi >>> 20)));
    }

    private final boolean zza(T t, int i, int i2) {
        return zzkd.zzk(t, (long) (zzbi(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzkd.zzb(t, zzbi(i2) & 1048575, i);
    }

    private final int zzbk(int i) {
        if (i < this.zzvt || i > this.zzvu) {
            return -1;
        }
        return zzr(i, 0);
    }

    private final int zzq(int i, int i2) {
        if (i < this.zzvt || i > this.zzvu) {
            return -1;
        }
        return zzr(i, i2);
    }

    private final int zzr(int i, int i2) {
        int length = (this.zzvr.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzvr[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
