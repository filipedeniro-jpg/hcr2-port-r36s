package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.drive.DriveFile;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjk<T> implements zzjw<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzky.zzc();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzjg zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzjp zzo;
    private final zziq zzp;
    private final zzks<?, ?> zzq;
    private final zzhm<?> zzr;
    private final zzjd zzs;

    private zzjk(int[] iArr, Object[] objArr, int i, int i2, zzjg zzjgVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzjp zzjpVar, zziq zziqVar, zzks<?, ?> zzksVar, zzhm<?> zzhmVar, zzjd zzjdVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzjgVar instanceof zzhx;
        this.zzj = z;
        this.zzh = zzhmVar != null && zzhmVar.zza(zzjgVar);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i3;
        this.zzn = i4;
        this.zzo = zzjpVar;
        this.zzp = zziqVar;
        this.zzq = zzksVar;
        this.zzr = zzhmVar;
        this.zzg = zzjgVar;
        this.zzs = zzjdVar;
    }

    private static boolean zzf(int i) {
        return (i & DriveFile.MODE_WRITE_ONLY) != 0;
    }

    /* JADX WARN: Code duplicated, block: B:172:0x0379  */
    /* JADX WARN: Code duplicated, block: B:186:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:189:0x03d7  */
    static <T> zzjk<T> zza(Class<T> cls, zzje zzjeVar, zzjp zzjpVar, zziq zziqVar, zzks<?, ?> zzksVar, zzhm<?> zzhmVar, zzjd zzjdVar) {
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
        if (zzjeVar instanceof zzju) {
            zzju zzjuVar = (zzju) zzjeVar;
            int iCharAt4 = 0;
            boolean z = zzjuVar.zza() == zzhx.zze.zzi;
            String strZzd = zzjuVar.zzd();
            int length = strZzd.length();
            int iCharAt5 = strZzd.charAt(0);
            if (iCharAt5 >= 55296) {
                int i27 = iCharAt5 & 8191;
                int i28 = 1;
                int i29 = 13;
                while (true) {
                    i = i28 + 1;
                    cCharAt14 = strZzd.charAt(i28);
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
            int iCharAt6 = strZzd.charAt(i);
            if (iCharAt6 >= 55296) {
                int i31 = iCharAt6 & 8191;
                int i32 = 13;
                while (true) {
                    i2 = i30 + 1;
                    cCharAt13 = strZzd.charAt(i30);
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
                iArr = zza;
                iCharAt3 = 0;
                i10 = 0;
                i8 = 0;
                iCharAt = 0;
                iCharAt2 = 0;
                i9 = 0;
            } else {
                int i33 = i2 + 1;
                int iCharAt7 = strZzd.charAt(i2);
                if (iCharAt7 >= 55296) {
                    int i34 = iCharAt7 & 8191;
                    int i35 = 13;
                    while (true) {
                        i3 = i33 + 1;
                        cCharAt8 = strZzd.charAt(i33);
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
                int iCharAt8 = strZzd.charAt(i3);
                if (iCharAt8 >= 55296) {
                    int i37 = iCharAt8 & 8191;
                    int i38 = 13;
                    while (true) {
                        i4 = i36 + 1;
                        cCharAt7 = strZzd.charAt(i36);
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
                int iCharAt9 = strZzd.charAt(i4);
                if (iCharAt9 >= 55296) {
                    int i40 = iCharAt9 & 8191;
                    int i41 = 13;
                    while (true) {
                        i5 = i39 + 1;
                        cCharAt6 = strZzd.charAt(i39);
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
                iCharAt = strZzd.charAt(i5);
                if (iCharAt >= 55296) {
                    int i43 = iCharAt & 8191;
                    int i44 = 13;
                    while (true) {
                        i6 = i42 + 1;
                        cCharAt5 = strZzd.charAt(i42);
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
                iCharAt2 = strZzd.charAt(i6);
                if (iCharAt2 >= 55296) {
                    int i46 = iCharAt2 & 8191;
                    int i47 = 13;
                    while (true) {
                        i14 = i45 + 1;
                        cCharAt4 = strZzd.charAt(i45);
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
                iCharAt3 = strZzd.charAt(i45);
                if (iCharAt3 >= 55296) {
                    int i49 = iCharAt3 & 8191;
                    int i50 = 13;
                    while (true) {
                        i13 = i48 + 1;
                        cCharAt3 = strZzd.charAt(i48);
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
                int iCharAt10 = strZzd.charAt(i48);
                if (iCharAt10 >= 55296) {
                    int i52 = 13;
                    int i53 = iCharAt10 & 8191;
                    int i54 = i51;
                    while (true) {
                        i12 = i54 + 1;
                        cCharAt2 = strZzd.charAt(i54);
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
                iCharAt4 = strZzd.charAt(i7);
                if (iCharAt4 >= 55296) {
                    int i56 = 13;
                    int i57 = iCharAt4 & 8191;
                    int i58 = i55;
                    while (true) {
                        i11 = i58 + 1;
                        cCharAt = strZzd.charAt(i58);
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
            Unsafe unsafe = zzb;
            Object[] objArrZze = zzjuVar.zze();
            Class<?> cls2 = zzjuVar.zzc().getClass();
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
                int iCharAt11 = strZzd.charAt(i2);
                char c = 55296;
                if (iCharAt11 >= 55296) {
                    int i67 = 13;
                    int i68 = iCharAt11 & 8191;
                    int i69 = i66;
                    while (true) {
                        i26 = i69 + 1;
                        cCharAt12 = strZzd.charAt(i69);
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
                int iCharAt12 = strZzd.charAt(i15);
                int i71 = length;
                char c2 = 55296;
                if (iCharAt12 >= 55296) {
                    int i72 = 13;
                    int i73 = iCharAt12 & 8191;
                    int i74 = i70;
                    while (true) {
                        i25 = i74 + 1;
                        cCharAt11 = strZzd.charAt(i74);
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
                    int iCharAt13 = strZzd.charAt(i16);
                    char c3 = 55296;
                    if (iCharAt13 >= 55296) {
                        int i79 = iCharAt13 & 8191;
                        int i80 = 13;
                        while (true) {
                            i24 = i78 + 1;
                            cCharAt10 = strZzd.charAt(i78);
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
                        objArr[((i65 / 3) << 1) + 1] = objArrZze[i60];
                        i60++;
                    } else {
                        if (i81 == 12 && (iCharAt5 & 1) == 1) {
                            objArr[((i65 / 3) << 1) + 1] = objArrZze[i60];
                            i60++;
                        }
                        i23 = 1;
                    }
                    int i83 = iCharAt13 << i23;
                    Object obj = objArrZze[i83];
                    if (obj instanceof Field) {
                        fieldZza2 = (Field) obj;
                    } else {
                        fieldZza2 = zza(cls2, (String) obj);
                        objArrZze[i83] = fieldZza2;
                    }
                    int i84 = i10;
                    int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZza2);
                    int i85 = i83 + 1;
                    Object obj2 = objArrZze[i85];
                    if (obj2 instanceof Field) {
                        fieldZza3 = (Field) obj2;
                    } else {
                        fieldZza3 = zza(cls2, (String) obj2);
                        objArrZze[i85] = fieldZza3;
                    }
                    strZzd = strZzd;
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
                    Field fieldZza4 = zza(cls2, (String) objArrZze[i60]);
                    if (i76 == 9 || i76 == 17) {
                        i18 = i86;
                        objArr[((i65 / 3) << 1) + 1] = fieldZza4.getType();
                    } else {
                        if (i76 == 27 || i76 == 49) {
                            i18 = i86;
                            i22 = i17 + 1;
                            objArr[((i65 / 3) << 1) + 1] = objArrZze[i17];
                        } else if (i76 == 12 || i76 == 30 || i76 == 44) {
                            i18 = i86;
                            if ((iCharAt5 & 1) == 1) {
                                i22 = i17 + 1;
                                objArr[((i65 / 3) << 1) + 1] = objArrZze[i17];
                            }
                            iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                            if ((iCharAt5 & 1) == 1 || i76 > 17) {
                                i19 = i16;
                                i20 = 0;
                                iObjectFieldOffset2 = 0;
                            } else {
                                i19 = i16 + 1;
                                int iCharAt14 = strZzd.charAt(i16);
                                if (iCharAt14 >= 55296) {
                                    int i87 = iCharAt14 & 8191;
                                    int i88 = 13;
                                    while (true) {
                                        i21 = i19 + 1;
                                        cCharAt9 = strZzd.charAt(i19);
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
                                Object obj3 = objArrZze[i89];
                                if (obj3 instanceof Field) {
                                    fieldZza = (Field) obj3;
                                } else {
                                    fieldZza = zza(cls2, (String) obj3);
                                    objArrZze[i89] = fieldZza;
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
                            objArr[i91] = objArrZze[i17];
                            if ((iCharAt12 & 2048) != 0) {
                                i17 = i92 + 1;
                                objArr[i91 + 1] = objArrZze[i92];
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
                strZzd = strZzd;
            }
            return new zzjk<>(iArr2, objArr, i10, iCharAt, zzjuVar.zzc(), z, false, iArr, iCharAt4, i61, zzjpVar, zziqVar, zzksVar, zzhmVar, zzjdVar);
        }
        ((zzkl) zzjeVar).zza();
        int i95 = zzhx.zze.zzi;
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

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final T zza() {
        return (T) this.zzo.zza(this.zzg);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x01c1  */
    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final boolean zza(T t, T t2) {
        int length = this.zzc.length;
        int i = 0;
        while (true) {
            boolean zZza = true;
            if (i < length) {
                int iZzd = zzd(i);
                long j = iZzd & 1048575;
                switch ((iZzd & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzky.zze(t, j)) != Double.doubleToLongBits(zzky.zze(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzky.zzd(t, j)) != Float.floatToIntBits(zzky.zzd(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 2:
                        if (!zzc(t, t2, i) || zzky.zzb(t, j) != zzky.zzb(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 3:
                        if (!zzc(t, t2, i) || zzky.zzb(t, j) != zzky.zzb(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 4:
                        if (!zzc(t, t2, i) || zzky.zza(t, j) != zzky.zza(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 5:
                        if (!zzc(t, t2, i) || zzky.zzb(t, j) != zzky.zzb(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 6:
                        if (!zzc(t, t2, i) || zzky.zza(t, j) != zzky.zza(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 7:
                        if (!zzc(t, t2, i) || zzky.zzc(t, j) != zzky.zzc(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 8:
                        if (!zzc(t, t2, i) || !zzjy.zza(zzky.zzf(t, j), zzky.zzf(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 9:
                        if (!zzc(t, t2, i) || !zzjy.zza(zzky.zzf(t, j), zzky.zzf(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 10:
                        if (!zzc(t, t2, i) || !zzjy.zza(zzky.zzf(t, j), zzky.zzf(t2, j))) {
                            zZza = false;
                        }
                        break;
                    case 11:
                        if (!zzc(t, t2, i) || zzky.zza(t, j) != zzky.zza(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 12:
                        if (!zzc(t, t2, i) || zzky.zza(t, j) != zzky.zza(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 13:
                        if (!zzc(t, t2, i) || zzky.zza(t, j) != zzky.zza(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 14:
                        if (!zzc(t, t2, i) || zzky.zzb(t, j) != zzky.zzb(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 15:
                        if (!zzc(t, t2, i) || zzky.zza(t, j) != zzky.zza(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 16:
                        if (!zzc(t, t2, i) || zzky.zzb(t, j) != zzky.zzb(t2, j)) {
                            zZza = false;
                        }
                        break;
                    case 17:
                        if (!zzc(t, t2, i) || !zzjy.zza(zzky.zzf(t, j), zzky.zzf(t2, j))) {
                            zZza = false;
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
                        zZza = zzjy.zza(zzky.zzf(t, j), zzky.zzf(t2, j));
                        break;
                    case 50:
                        zZza = zzjy.zza(zzky.zzf(t, j), zzky.zzf(t2, j));
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
                        long jZze = zze(i) & 1048575;
                        if (zzky.zza(t, jZze) != zzky.zza(t2, jZze) || !zzjy.zza(zzky.zzf(t, j), zzky.zzf(t2, j))) {
                            zZza = false;
                        }
                        break;
                }
                if (!zZza) {
                    return false;
                }
                i += 3;
            } else {
                if (!this.zzq.zzb(t).equals(this.zzq.zzb(t2))) {
                    return false;
                }
                if (this.zzh) {
                    return this.zzr.zza(t).equals(this.zzr.zza(t2));
                }
                return true;
            }
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final int zza(T t) {
        int i;
        int iZza;
        int length = this.zzc.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzd = zzd(i3);
            int i4 = this.zzc[i3];
            long j = 1048575 & iZzd;
            int iHashCode = 37;
            switch ((iZzd & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    iZza = zzib.zza(Double.doubleToLongBits(zzky.zze(t, j)));
                    i2 = i + iZza;
                    break;
                case 1:
                    i = i2 * 53;
                    iZza = Float.floatToIntBits(zzky.zzd(t, j));
                    i2 = i + iZza;
                    break;
                case 2:
                    i = i2 * 53;
                    iZza = zzib.zza(zzky.zzb(t, j));
                    i2 = i + iZza;
                    break;
                case 3:
                    i = i2 * 53;
                    iZza = zzib.zza(zzky.zzb(t, j));
                    i2 = i + iZza;
                    break;
                case 4:
                    i = i2 * 53;
                    iZza = zzky.zza(t, j);
                    i2 = i + iZza;
                    break;
                case 5:
                    i = i2 * 53;
                    iZza = zzib.zza(zzky.zzb(t, j));
                    i2 = i + iZza;
                    break;
                case 6:
                    i = i2 * 53;
                    iZza = zzky.zza(t, j);
                    i2 = i + iZza;
                    break;
                case 7:
                    i = i2 * 53;
                    iZza = zzib.zza(zzky.zzc(t, j));
                    i2 = i + iZza;
                    break;
                case 8:
                    i = i2 * 53;
                    iZza = ((String) zzky.zzf(t, j)).hashCode();
                    i2 = i + iZza;
                    break;
                case 9:
                    Object objZzf = zzky.zzf(t, j);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iZza = zzky.zzf(t, j).hashCode();
                    i2 = i + iZza;
                    break;
                case 11:
                    i = i2 * 53;
                    iZza = zzky.zza(t, j);
                    i2 = i + iZza;
                    break;
                case 12:
                    i = i2 * 53;
                    iZza = zzky.zza(t, j);
                    i2 = i + iZza;
                    break;
                case 13:
                    i = i2 * 53;
                    iZza = zzky.zza(t, j);
                    i2 = i + iZza;
                    break;
                case 14:
                    i = i2 * 53;
                    iZza = zzib.zza(zzky.zzb(t, j));
                    i2 = i + iZza;
                    break;
                case 15:
                    i = i2 * 53;
                    iZza = zzky.zza(t, j);
                    i2 = i + iZza;
                    break;
                case 16:
                    i = i2 * 53;
                    iZza = zzib.zza(zzky.zzb(t, j));
                    i2 = i + iZza;
                    break;
                case 17:
                    Object objZzf2 = zzky.zzf(t, j);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
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
                    iZza = zzky.zzf(t, j).hashCode();
                    i2 = i + iZza;
                    break;
                case 50:
                    i = i2 * 53;
                    iZza = zzky.zzf(t, j).hashCode();
                    i2 = i + iZza;
                    break;
                case 51:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzib.zza(Double.doubleToLongBits(zzb(t, j)));
                        i2 = i + iZza;
                    }
                    break;
                case 52:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = Float.floatToIntBits(zzc(t, j));
                        i2 = i + iZza;
                    }
                    break;
                case 53:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzib.zza(zze(t, j));
                        i2 = i + iZza;
                    }
                    break;
                case 54:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzib.zza(zze(t, j));
                        i2 = i + iZza;
                    }
                    break;
                case 55:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzd(t, j);
                        i2 = i + iZza;
                    }
                    break;
                case 56:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzib.zza(zze(t, j));
                        i2 = i + iZza;
                    }
                    break;
                case 57:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzd(t, j);
                        i2 = i + iZza;
                    }
                    break;
                case 58:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzib.zza(zzf(t, j));
                        i2 = i + iZza;
                    }
                    break;
                case 59:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = ((String) zzky.zzf(t, j)).hashCode();
                        i2 = i + iZza;
                    }
                    break;
                case 60:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzky.zzf(t, j).hashCode();
                        i2 = i + iZza;
                    }
                    break;
                case 61:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzky.zzf(t, j).hashCode();
                        i2 = i + iZza;
                    }
                    break;
                case 62:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzd(t, j);
                        i2 = i + iZza;
                    }
                    break;
                case 63:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzd(t, j);
                        i2 = i + iZza;
                    }
                    break;
                case 64:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzd(t, j);
                        i2 = i + iZza;
                    }
                    break;
                case 65:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzib.zza(zze(t, j));
                        i2 = i + iZza;
                    }
                    break;
                case 66:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzd(t, j);
                        i2 = i + iZza;
                    }
                    break;
                case 67:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzib.zza(zze(t, j));
                        i2 = i + iZza;
                    }
                    break;
                case 68:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZza = zzky.zzf(t, j).hashCode();
                        i2 = i + iZza;
                    }
                    break;
            }
        }
        int iHashCode2 = (i2 * 53) + this.zzq.zzb(t).hashCode();
        return this.zzh ? (iHashCode2 * 53) + this.zzr.zza(t).hashCode() : iHashCode2;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final void zzb(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzd = zzd(i);
            long j = 1048575 & iZzd;
            int i2 = this.zzc[i];
            switch ((iZzd & 267386880) >>> 20) {
                case 0:
                    if (zza((Object) t2, i)) {
                        zzky.zza(t, j, zzky.zze(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 1:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zzd(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 2:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zzb(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 3:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zzb(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 4:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zza(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 5:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zzb(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 6:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zza(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 7:
                    if (zza((Object) t2, i)) {
                        zzky.zza(t, j, zzky.zzc(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 8:
                    if (zza((Object) t2, i)) {
                        zzky.zza(t, j, zzky.zzf(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zza((Object) t2, i)) {
                        zzky.zza(t, j, zzky.zzf(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 11:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zza(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 12:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zza(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 13:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zza(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 14:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zzb(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 15:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zza(t2, j));
                        zzb((Object) t, i);
                    }
                    break;
                case 16:
                    if (zza((Object) t2, i)) {
                        zzky.zza((Object) t, j, zzky.zzb(t2, j));
                        zzb((Object) t, i);
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
                    this.zzp.zza(t, t2, j);
                    break;
                case 50:
                    zzjy.zza(this.zzs, t, t2, j);
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
                        zzky.zza(t, j, zzky.zzf(t2, j));
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
                        zzky.zza(t, j, zzky.zzf(t2, j));
                        zzb(t, i2, i);
                    }
                    break;
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (this.zzj) {
            return;
        }
        zzjy.zza(this.zzq, t, t2);
        if (this.zzh) {
            zzjy.zza(this.zzr, t, t2);
        }
    }

    private final void zza(T t, T t2, int i) {
        long jZzd = zzd(i) & 1048575;
        if (zza((Object) t2, i)) {
            Object objZzf = zzky.zzf(t, jZzd);
            Object objZzf2 = zzky.zzf(t2, jZzd);
            if (objZzf != null && objZzf2 != null) {
                zzky.zza(t, jZzd, zzib.zza(objZzf, objZzf2));
                zzb((Object) t, i);
            } else if (objZzf2 != null) {
                zzky.zza(t, jZzd, objZzf2);
                zzb((Object) t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int iZzd = zzd(i);
        int i2 = this.zzc[i];
        long j = iZzd & 1048575;
        if (zza(t2, i2, i)) {
            Object objZzf = zzky.zzf(t, j);
            Object objZzf2 = zzky.zzf(t2, j);
            if (objZzf != null && objZzf2 != null) {
                zzky.zza(t, j, zzib.zza(objZzf, objZzf2));
                zzb(t, i2, i);
            } else if (objZzf2 != null) {
                zzky.zza(t, j, objZzf2);
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
    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final int zzb(T t) {
        int i;
        int i2;
        long j;
        int iZzd;
        int iZzb;
        int iZzj;
        int iZzh;
        int iZzi;
        int iZze;
        int iZzg;
        int iZzb2;
        int iZzi2;
        int iZze2;
        int iZzg2;
        int i3 = 267386880;
        int i4 = 1048575;
        int i5 = 1;
        if (this.zzj) {
            Unsafe unsafe = zzb;
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.zzc.length) {
                int iZzd2 = zzd(i6);
                int i8 = (iZzd2 & i3) >>> 20;
                int i9 = this.zzc[i6];
                long j2 = iZzd2 & 1048575;
                int i10 = (i8 < zzhr.DOUBLE_LIST_PACKED.zza() || i8 > zzhr.SINT64_LIST_PACKED.zza()) ? 0 : this.zzc[i6 + 2] & 1048575;
                switch (i8) {
                    case 0:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzb(i9, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i7 += iZzb2;
                        }
                        break;
                    case 1:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzb(i9, 0.0f);
                            i7 += iZzb2;
                        }
                        break;
                    case 2:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzd(i9, zzky.zzb(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 3:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zze(i9, zzky.zzb(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 4:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzf(i9, zzky.zza(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 5:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzg(i9, 0L);
                            i7 += iZzb2;
                        }
                        break;
                    case 6:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzi(i9, 0);
                            i7 += iZzb2;
                        }
                        break;
                    case 7:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzb(i9, true);
                            i7 += iZzb2;
                        }
                        break;
                    case 8:
                        if (zza((Object) t, i6)) {
                            Object objZzf = zzky.zzf(t, j2);
                            if (objZzf instanceof zzgm) {
                                iZzb2 = zzhh.zzc(i9, (zzgm) objZzf);
                            } else {
                                iZzb2 = zzhh.zzb(i9, (String) objZzf);
                            }
                            i7 += iZzb2;
                        }
                        break;
                    case 9:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzjy.zza(i9, zzky.zzf(t, j2), zza(i6));
                            i7 += iZzb2;
                        }
                        break;
                    case 10:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzc(i9, (zzgm) zzky.zzf(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 11:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzg(i9, zzky.zza(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 12:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzk(i9, zzky.zza(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 13:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzj(i9, 0);
                            i7 += iZzb2;
                        }
                        break;
                    case 14:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzh(i9, 0L);
                            i7 += iZzb2;
                        }
                        break;
                    case 15:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzh(i9, zzky.zza(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 16:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzf(i9, zzky.zzb(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 17:
                        if (zza((Object) t, i6)) {
                            iZzb2 = zzhh.zzc(i9, (zzjg) zzky.zzf(t, j2), zza(i6));
                            i7 += iZzb2;
                        }
                        break;
                    case 18:
                        iZzb2 = zzjy.zzi(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 19:
                        iZzb2 = zzjy.zzh(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 20:
                        iZzb2 = zzjy.zza(i9, (List<Long>) zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 21:
                        iZzb2 = zzjy.zzb(i9, (List<Long>) zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 22:
                        iZzb2 = zzjy.zze(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 23:
                        iZzb2 = zzjy.zzi(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 24:
                        iZzb2 = zzjy.zzh(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 25:
                        iZzb2 = zzjy.zzj(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 26:
                        iZzb2 = zzjy.zza(i9, zza(t, j2));
                        i7 += iZzb2;
                        break;
                    case 27:
                        iZzb2 = zzjy.zza(i9, zza(t, j2), zza(i6));
                        i7 += iZzb2;
                        break;
                    case 28:
                        iZzb2 = zzjy.zzb(i9, zza(t, j2));
                        i7 += iZzb2;
                        break;
                    case 29:
                        iZzb2 = zzjy.zzf(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 30:
                        iZzb2 = zzjy.zzd(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 31:
                        iZzb2 = zzjy.zzh(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 32:
                        iZzb2 = zzjy.zzi(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 33:
                        iZzb2 = zzjy.zzg(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 34:
                        iZzb2 = zzjy.zzc(i9, zza(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 35:
                        iZzi2 = zzjy.zzi((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 36:
                        iZzi2 = zzjy.zzh((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 37:
                        iZzi2 = zzjy.zza((List<Long>) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 38:
                        iZzi2 = zzjy.zzb((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 39:
                        iZzi2 = zzjy.zze((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 40:
                        iZzi2 = zzjy.zzi((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 41:
                        iZzi2 = zzjy.zzh((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 42:
                        iZzi2 = zzjy.zzj((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 43:
                        iZzi2 = zzjy.zzf((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 44:
                        iZzi2 = zzjy.zzd((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 45:
                        iZzi2 = zzjy.zzh((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 46:
                        iZzi2 = zzjy.zzi((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 47:
                        iZzi2 = zzjy.zzg((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 48:
                        iZzi2 = zzjy.zzc((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzk) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZze2 = zzhh.zze(i9);
                            iZzg2 = zzhh.zzg(iZzi2);
                            iZzb2 = iZze2 + iZzg2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 49:
                        iZzb2 = zzjy.zzb(i9, (List<zzjg>) zza(t, j2), zza(i6));
                        i7 += iZzb2;
                        break;
                    case 50:
                        iZzb2 = this.zzs.zza(i9, zzky.zzf(t, j2), zzb(i6));
                        i7 += iZzb2;
                        break;
                    case 51:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzb(i9, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i7 += iZzb2;
                        }
                        break;
                    case 52:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzb(i9, 0.0f);
                            i7 += iZzb2;
                        }
                        break;
                    case 53:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzd(i9, zze(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 54:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zze(i9, zze(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 55:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzf(i9, zzd(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 56:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzg(i9, 0L);
                            i7 += iZzb2;
                        }
                        break;
                    case 57:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzi(i9, 0);
                            i7 += iZzb2;
                        }
                        break;
                    case 58:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzb(i9, true);
                            i7 += iZzb2;
                        }
                        break;
                    case 59:
                        if (zza(t, i9, i6)) {
                            Object objZzf2 = zzky.zzf(t, j2);
                            if (objZzf2 instanceof zzgm) {
                                iZzb2 = zzhh.zzc(i9, (zzgm) objZzf2);
                            } else {
                                iZzb2 = zzhh.zzb(i9, (String) objZzf2);
                            }
                            i7 += iZzb2;
                        }
                        break;
                    case 60:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjy.zza(i9, zzky.zzf(t, j2), zza(i6));
                            i7 += iZzb2;
                        }
                        break;
                    case 61:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzc(i9, (zzgm) zzky.zzf(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 62:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzg(i9, zzd(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 63:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzk(i9, zzd(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 64:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzj(i9, 0);
                            i7 += iZzb2;
                        }
                        break;
                    case 65:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzh(i9, 0L);
                            i7 += iZzb2;
                        }
                        break;
                    case 66:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzh(i9, zzd(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 67:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzf(i9, zze(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 68:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzhh.zzc(i9, (zzjg) zzky.zzf(t, j2), zza(i6));
                            i7 += iZzb2;
                        }
                        break;
                }
                i6 += 3;
                i3 = 267386880;
            }
            return i7 + zza((zzks) this.zzq, (Object) t);
        }
        Unsafe unsafe2 = zzb;
        int i11 = 0;
        int iZzb3 = 0;
        int i12 = -1;
        int i13 = 0;
        while (i11 < this.zzc.length) {
            int iZzd3 = zzd(i11);
            int[] iArr = this.zzc;
            int i14 = iArr[i11];
            int i15 = (iZzd3 & 267386880) >>> 20;
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
                i = (!this.zzk || i15 < zzhr.DOUBLE_LIST_PACKED.zza() || i15 > zzhr.SINT64_LIST_PACKED.zza()) ? 0 : this.zzc[i11 + 2] & i4;
                i2 = 0;
            }
            long j3 = iZzd3 & i4;
            switch (i15) {
                case 0:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzb3 += zzhh.zzb(i14, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    }
                    break;
                case 1:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzb3 += zzhh.zzb(i14, 0.0f);
                    }
                    break;
                case 2:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzd = zzhh.zzd(i14, unsafe2.getLong(t, j3));
                        iZzb3 += iZzd;
                    }
                    break;
                case 3:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzd = zzhh.zze(i14, unsafe2.getLong(t, j3));
                        iZzb3 += iZzd;
                    }
                    break;
                case 4:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzd = zzhh.zzf(i14, unsafe2.getInt(t, j3));
                        iZzb3 += iZzd;
                    }
                    break;
                case 5:
                    if ((i13 & i2) != 0) {
                        j = 0;
                        iZzd = zzhh.zzg(i14, 0L);
                        iZzb3 += iZzd;
                    } else {
                        j = 0;
                    }
                    break;
                case 6:
                    if ((i13 & i2) != 0) {
                        iZzb3 += zzhh.zzi(i14, 0);
                    }
                    j = 0;
                    break;
                case 7:
                    if ((i13 & i2) != 0) {
                        iZzb3 += zzhh.zzb(i14, true);
                    }
                    j = 0;
                    break;
                case 8:
                    if ((i13 & i2) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzgm) {
                            iZzb = zzhh.zzc(i14, (zzgm) object);
                        } else {
                            iZzb = zzhh.zzb(i14, (String) object);
                        }
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 9:
                    if ((i13 & i2) != 0) {
                        iZzb = zzjy.zza(i14, unsafe2.getObject(t, j3), zza(i11));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 10:
                    if ((i13 & i2) != 0) {
                        iZzb = zzhh.zzc(i14, (zzgm) unsafe2.getObject(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 11:
                    if ((i13 & i2) != 0) {
                        iZzb = zzhh.zzg(i14, unsafe2.getInt(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 12:
                    if ((i13 & i2) != 0) {
                        iZzb = zzhh.zzk(i14, unsafe2.getInt(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 13:
                    if ((i13 & i2) != 0) {
                        iZzj = zzhh.zzj(i14, 0);
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 14:
                    if ((i13 & i2) != 0) {
                        iZzb = zzhh.zzh(i14, 0L);
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 15:
                    if ((i13 & i2) != 0) {
                        iZzb = zzhh.zzh(i14, unsafe2.getInt(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 16:
                    if ((i13 & i2) != 0) {
                        iZzb = zzhh.zzf(i14, unsafe2.getLong(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 17:
                    if ((i13 & i2) != 0) {
                        iZzb = zzhh.zzc(i14, (zzjg) unsafe2.getObject(t, j3), zza(i11));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 18:
                    iZzb = zzjy.zzi(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 19:
                    iZzh = zzjy.zzh(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 20:
                    iZzh = zzjy.zza(i14, (List<Long>) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 21:
                    iZzh = zzjy.zzb(i14, (List<Long>) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 22:
                    iZzh = zzjy.zze(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 23:
                    iZzh = zzjy.zzi(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 24:
                    iZzh = zzjy.zzh(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 25:
                    iZzh = zzjy.zzj(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 26:
                    iZzb = zzjy.zza(i14, (List<?>) unsafe2.getObject(t, j3));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 27:
                    iZzb = zzjy.zza(i14, (List<?>) unsafe2.getObject(t, j3), zza(i11));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 28:
                    iZzb = zzjy.zzb(i14, (List) unsafe2.getObject(t, j3));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 29:
                    iZzb = zzjy.zzf(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 30:
                    iZzh = zzjy.zzd(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 31:
                    iZzh = zzjy.zzh(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 32:
                    iZzh = zzjy.zzi(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 33:
                    iZzh = zzjy.zzg(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 34:
                    iZzh = zzjy.zzc(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzh;
                    j = 0;
                    break;
                case 35:
                    iZzi = zzjy.zzi((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 36:
                    iZzi = zzjy.zzh((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 37:
                    iZzi = zzjy.zza((List<Long>) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 38:
                    iZzi = zzjy.zzb((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 39:
                    iZzi = zzjy.zze((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 40:
                    iZzi = zzjy.zzi((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 41:
                    iZzi = zzjy.zzh((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 42:
                    iZzi = zzjy.zzj((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 43:
                    iZzi = zzjy.zzf((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 44:
                    iZzi = zzjy.zzd((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 45:
                    iZzi = zzjy.zzh((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 46:
                    iZzi = zzjy.zzi((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 47:
                    iZzi = zzjy.zzg((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 48:
                    iZzi = zzjy.zzc((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzk) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZze = zzhh.zze(i14);
                        iZzg = zzhh.zzg(iZzi);
                        iZzj = iZze + iZzg + iZzi;
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 49:
                    iZzb = zzjy.zzb(i14, (List<zzjg>) unsafe2.getObject(t, j3), zza(i11));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 50:
                    iZzb = this.zzs.zza(i14, unsafe2.getObject(t, j3), zzb(i11));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 51:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzb(i14, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 52:
                    if (zza(t, i14, i11)) {
                        iZzj = zzhh.zzb(i14, 0.0f);
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 53:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzd(i14, zze(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 54:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zze(i14, zze(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 55:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzf(i14, zzd(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 56:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzg(i14, 0L);
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 57:
                    if (zza(t, i14, i11)) {
                        iZzj = zzhh.zzi(i14, 0);
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 58:
                    if (zza(t, i14, i11)) {
                        iZzj = zzhh.zzb(i14, true);
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 59:
                    if (zza(t, i14, i11)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzgm) {
                            iZzb = zzhh.zzc(i14, (zzgm) object2);
                        } else {
                            iZzb = zzhh.zzb(i14, (String) object2);
                        }
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 60:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjy.zza(i14, unsafe2.getObject(t, j3), zza(i11));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 61:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzc(i14, (zzgm) unsafe2.getObject(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 62:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzg(i14, zzd(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 63:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzk(i14, zzd(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 64:
                    if (zza(t, i14, i11)) {
                        iZzj = zzhh.zzj(i14, 0);
                        iZzb3 += iZzj;
                    }
                    j = 0;
                    break;
                case 65:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzh(i14, 0L);
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 66:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzh(i14, zzd(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 67:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzf(i14, zze(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 68:
                    if (zza(t, i14, i11)) {
                        iZzb = zzhh.zzc(i14, (zzjg) unsafe2.getObject(t, j3), zza(i11));
                        iZzb3 += iZzb;
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
        int iZza = iZzb3 + zza((zzks) this.zzq, (Object) t);
        if (!this.zzh) {
            return iZza;
        }
        zzhq<T> zzhqVarZza = this.zzr.zza(t);
        int iZza2 = 0;
        for (int i17 = 0; i17 < zzhqVarZza.zza.zzc(); i17++) {
            Map.Entry entryZzb = zzhqVarZza.zza.zzb(i17);
            iZza2 += zzhq.zza((zzhs<?>) entryZzb.getKey(), entryZzb.getValue());
        }
        for (Map.Entry entry : zzhqVarZza.zza.zzd()) {
            iZza2 += zzhq.zza((zzhs<?>) entry.getKey(), entry.getValue());
        }
        return iZza + iZza2;
    }

    private static <UT, UB> int zza(zzks<UT, UB> zzksVar, T t) {
        return zzksVar.zzf(zzksVar.zzb(t));
    }

    private static List<?> zza(Object obj, long j) {
        return (List) zzky.zzf(obj, j);
    }

    /* JADX WARN: Code duplicated, block: B:178:0x054a  */
    /* JADX WARN: Code duplicated, block: B:9:0x0032  */
    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final void zza(T t, zzll zzllVar) throws IOException {
        Iterator itZzd;
        Map.Entry<?, ?> entry;
        Iterator itZze;
        Map.Entry<?, ?> entry2;
        if (zzllVar.zza() == zzhx.zze.zzk) {
            zza(this.zzq, t, zzllVar);
            if (this.zzh) {
                zzhq<T> zzhqVarZza = this.zzr.zza(t);
                if (zzhqVarZza.zza.isEmpty()) {
                    itZze = null;
                    entry2 = null;
                } else {
                    itZze = zzhqVarZza.zze();
                    entry2 = (Map.Entry) itZze.next();
                }
            } else {
                itZze = null;
                entry2 = null;
            }
            for (int length = this.zzc.length - 3; length >= 0; length -= 3) {
                int iZzd = zzd(length);
                int i = this.zzc[length];
                while (entry2 != null && this.zzr.zza(entry2) > i) {
                    this.zzr.zza(zzllVar, entry2);
                    entry2 = itZze.hasNext() ? (Map.Entry) itZze.next() : null;
                }
                switch ((iZzd & 267386880) >>> 20) {
                    case 0:
                        if (zza((Object) t, length)) {
                            zzllVar.zza(i, zzky.zze(t, iZzd & 1048575));
                        }
                        break;
                    case 1:
                        if (zza((Object) t, length)) {
                            zzllVar.zza(i, zzky.zzd(t, iZzd & 1048575));
                        }
                        break;
                    case 2:
                        if (zza((Object) t, length)) {
                            zzllVar.zza(i, zzky.zzb(t, iZzd & 1048575));
                        }
                        break;
                    case 3:
                        if (zza((Object) t, length)) {
                            zzllVar.zzc(i, zzky.zzb(t, iZzd & 1048575));
                        }
                        break;
                    case 4:
                        if (zza((Object) t, length)) {
                            zzllVar.zzc(i, zzky.zza(t, iZzd & 1048575));
                        }
                        break;
                    case 5:
                        if (zza((Object) t, length)) {
                            zzllVar.zzd(i, zzky.zzb(t, iZzd & 1048575));
                        }
                        break;
                    case 6:
                        if (zza((Object) t, length)) {
                            zzllVar.zzd(i, zzky.zza(t, iZzd & 1048575));
                        }
                        break;
                    case 7:
                        if (zza((Object) t, length)) {
                            zzllVar.zza(i, zzky.zzc(t, iZzd & 1048575));
                        }
                        break;
                    case 8:
                        if (zza((Object) t, length)) {
                            zza(i, zzky.zzf(t, iZzd & 1048575), zzllVar);
                        }
                        break;
                    case 9:
                        if (zza((Object) t, length)) {
                            zzllVar.zza(i, zzky.zzf(t, iZzd & 1048575), zza(length));
                        }
                        break;
                    case 10:
                        if (zza((Object) t, length)) {
                            zzllVar.zza(i, (zzgm) zzky.zzf(t, iZzd & 1048575));
                        }
                        break;
                    case 11:
                        if (zza((Object) t, length)) {
                            zzllVar.zze(i, zzky.zza(t, iZzd & 1048575));
                        }
                        break;
                    case 12:
                        if (zza((Object) t, length)) {
                            zzllVar.zzb(i, zzky.zza(t, iZzd & 1048575));
                        }
                        break;
                    case 13:
                        if (zza((Object) t, length)) {
                            zzllVar.zza(i, zzky.zza(t, iZzd & 1048575));
                        }
                        break;
                    case 14:
                        if (zza((Object) t, length)) {
                            zzllVar.zzb(i, zzky.zzb(t, iZzd & 1048575));
                        }
                        break;
                    case 15:
                        if (zza((Object) t, length)) {
                            zzllVar.zzf(i, zzky.zza(t, iZzd & 1048575));
                        }
                        break;
                    case 16:
                        if (zza((Object) t, length)) {
                            zzllVar.zze(i, zzky.zzb(t, iZzd & 1048575));
                        }
                        break;
                    case 17:
                        if (zza((Object) t, length)) {
                            zzllVar.zzb(i, zzky.zzf(t, iZzd & 1048575), zza(length));
                        }
                        break;
                    case 18:
                        zzjy.zza(this.zzc[length], (List<Double>) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 19:
                        zzjy.zzb(this.zzc[length], (List<Float>) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 20:
                        zzjy.zzc(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 21:
                        zzjy.zzd(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 22:
                        zzjy.zzh(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 23:
                        zzjy.zzf(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 24:
                        zzjy.zzk(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 25:
                        zzjy.zzn(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 26:
                        zzjy.zza(this.zzc[length], (List<String>) zzky.zzf(t, iZzd & 1048575), zzllVar);
                        break;
                    case 27:
                        zzjy.zza(this.zzc[length], (List<?>) zzky.zzf(t, iZzd & 1048575), zzllVar, zza(length));
                        break;
                    case 28:
                        zzjy.zzb(this.zzc[length], (List<zzgm>) zzky.zzf(t, iZzd & 1048575), zzllVar);
                        break;
                    case 29:
                        zzjy.zzi(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 30:
                        zzjy.zzm(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 31:
                        zzjy.zzl(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 32:
                        zzjy.zzg(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 33:
                        zzjy.zzj(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 34:
                        zzjy.zze(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, false);
                        break;
                    case 35:
                        zzjy.zza(this.zzc[length], (List<Double>) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 36:
                        zzjy.zzb(this.zzc[length], (List<Float>) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 37:
                        zzjy.zzc(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 38:
                        zzjy.zzd(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 39:
                        zzjy.zzh(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 40:
                        zzjy.zzf(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 41:
                        zzjy.zzk(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 42:
                        zzjy.zzn(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 43:
                        zzjy.zzi(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 44:
                        zzjy.zzm(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 45:
                        zzjy.zzl(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 46:
                        zzjy.zzg(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 47:
                        zzjy.zzj(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 48:
                        zzjy.zze(this.zzc[length], (List) zzky.zzf(t, iZzd & 1048575), zzllVar, true);
                        break;
                    case 49:
                        zzjy.zzb(this.zzc[length], (List<?>) zzky.zzf(t, iZzd & 1048575), zzllVar, zza(length));
                        break;
                    case 50:
                        zza(zzllVar, i, zzky.zzf(t, iZzd & 1048575), length);
                        break;
                    case 51:
                        if (zza(t, i, length)) {
                            zzllVar.zza(i, zzb(t, iZzd & 1048575));
                        }
                        break;
                    case 52:
                        if (zza(t, i, length)) {
                            zzllVar.zza(i, zzc(t, iZzd & 1048575));
                        }
                        break;
                    case 53:
                        if (zza(t, i, length)) {
                            zzllVar.zza(i, zze(t, iZzd & 1048575));
                        }
                        break;
                    case 54:
                        if (zza(t, i, length)) {
                            zzllVar.zzc(i, zze(t, iZzd & 1048575));
                        }
                        break;
                    case 55:
                        if (zza(t, i, length)) {
                            zzllVar.zzc(i, zzd(t, iZzd & 1048575));
                        }
                        break;
                    case 56:
                        if (zza(t, i, length)) {
                            zzllVar.zzd(i, zze(t, iZzd & 1048575));
                        }
                        break;
                    case 57:
                        if (zza(t, i, length)) {
                            zzllVar.zzd(i, zzd(t, iZzd & 1048575));
                        }
                        break;
                    case 58:
                        if (zza(t, i, length)) {
                            zzllVar.zza(i, zzf(t, iZzd & 1048575));
                        }
                        break;
                    case 59:
                        if (zza(t, i, length)) {
                            zza(i, zzky.zzf(t, iZzd & 1048575), zzllVar);
                        }
                        break;
                    case 60:
                        if (zza(t, i, length)) {
                            zzllVar.zza(i, zzky.zzf(t, iZzd & 1048575), zza(length));
                        }
                        break;
                    case 61:
                        if (zza(t, i, length)) {
                            zzllVar.zza(i, (zzgm) zzky.zzf(t, iZzd & 1048575));
                        }
                        break;
                    case 62:
                        if (zza(t, i, length)) {
                            zzllVar.zze(i, zzd(t, iZzd & 1048575));
                        }
                        break;
                    case 63:
                        if (zza(t, i, length)) {
                            zzllVar.zzb(i, zzd(t, iZzd & 1048575));
                        }
                        break;
                    case 64:
                        if (zza(t, i, length)) {
                            zzllVar.zza(i, zzd(t, iZzd & 1048575));
                        }
                        break;
                    case 65:
                        if (zza(t, i, length)) {
                            zzllVar.zzb(i, zze(t, iZzd & 1048575));
                        }
                        break;
                    case 66:
                        if (zza(t, i, length)) {
                            zzllVar.zzf(i, zzd(t, iZzd & 1048575));
                        }
                        break;
                    case 67:
                        if (zza(t, i, length)) {
                            zzllVar.zze(i, zze(t, iZzd & 1048575));
                        }
                        break;
                    case 68:
                        if (zza(t, i, length)) {
                            zzllVar.zzb(i, zzky.zzf(t, iZzd & 1048575), zza(length));
                        }
                        break;
                }
            }
            while (entry2 != null) {
                this.zzr.zza(zzllVar, entry2);
                entry2 = itZze.hasNext() ? (Map.Entry) itZze.next() : null;
            }
            return;
        }
        if (this.zzj) {
            if (this.zzh) {
                zzhq<T> zzhqVarZza2 = this.zzr.zza(t);
                if (zzhqVarZza2.zza.isEmpty()) {
                    itZzd = null;
                    entry = null;
                } else {
                    itZzd = zzhqVarZza2.zzd();
                    entry = (Map.Entry) itZzd.next();
                }
            } else {
                itZzd = null;
                entry = null;
            }
            int length2 = this.zzc.length;
            Map.Entry<?, ?> entry3 = entry;
            for (int i2 = 0; i2 < length2; i2 += 3) {
                int iZzd2 = zzd(i2);
                int i3 = this.zzc[i2];
                while (entry3 != null && this.zzr.zza(entry3) <= i3) {
                    this.zzr.zza(zzllVar, entry3);
                    entry3 = itZzd.hasNext() ? (Map.Entry) itZzd.next() : null;
                }
                switch ((iZzd2 & 267386880) >>> 20) {
                    case 0:
                        if (zza((Object) t, i2)) {
                            zzllVar.zza(i3, zzky.zze(t, iZzd2 & 1048575));
                        }
                        break;
                    case 1:
                        if (zza((Object) t, i2)) {
                            zzllVar.zza(i3, zzky.zzd(t, iZzd2 & 1048575));
                        }
                        break;
                    case 2:
                        if (zza((Object) t, i2)) {
                            zzllVar.zza(i3, zzky.zzb(t, iZzd2 & 1048575));
                        }
                        break;
                    case 3:
                        if (zza((Object) t, i2)) {
                            zzllVar.zzc(i3, zzky.zzb(t, iZzd2 & 1048575));
                        }
                        break;
                    case 4:
                        if (zza((Object) t, i2)) {
                            zzllVar.zzc(i3, zzky.zza(t, iZzd2 & 1048575));
                        }
                        break;
                    case 5:
                        if (zza((Object) t, i2)) {
                            zzllVar.zzd(i3, zzky.zzb(t, iZzd2 & 1048575));
                        }
                        break;
                    case 6:
                        if (zza((Object) t, i2)) {
                            zzllVar.zzd(i3, zzky.zza(t, iZzd2 & 1048575));
                        }
                        break;
                    case 7:
                        if (zza((Object) t, i2)) {
                            zzllVar.zza(i3, zzky.zzc(t, iZzd2 & 1048575));
                        }
                        break;
                    case 8:
                        if (zza((Object) t, i2)) {
                            zza(i3, zzky.zzf(t, iZzd2 & 1048575), zzllVar);
                        }
                        break;
                    case 9:
                        if (zza((Object) t, i2)) {
                            zzllVar.zza(i3, zzky.zzf(t, iZzd2 & 1048575), zza(i2));
                        }
                        break;
                    case 10:
                        if (zza((Object) t, i2)) {
                            zzllVar.zza(i3, (zzgm) zzky.zzf(t, iZzd2 & 1048575));
                        }
                        break;
                    case 11:
                        if (zza((Object) t, i2)) {
                            zzllVar.zze(i3, zzky.zza(t, iZzd2 & 1048575));
                        }
                        break;
                    case 12:
                        if (zza((Object) t, i2)) {
                            zzllVar.zzb(i3, zzky.zza(t, iZzd2 & 1048575));
                        }
                        break;
                    case 13:
                        if (zza((Object) t, i2)) {
                            zzllVar.zza(i3, zzky.zza(t, iZzd2 & 1048575));
                        }
                        break;
                    case 14:
                        if (zza((Object) t, i2)) {
                            zzllVar.zzb(i3, zzky.zzb(t, iZzd2 & 1048575));
                        }
                        break;
                    case 15:
                        if (zza((Object) t, i2)) {
                            zzllVar.zzf(i3, zzky.zza(t, iZzd2 & 1048575));
                        }
                        break;
                    case 16:
                        if (zza((Object) t, i2)) {
                            zzllVar.zze(i3, zzky.zzb(t, iZzd2 & 1048575));
                        }
                        break;
                    case 17:
                        if (zza((Object) t, i2)) {
                            zzllVar.zzb(i3, zzky.zzf(t, iZzd2 & 1048575), zza(i2));
                        }
                        break;
                    case 18:
                        zzjy.zza(this.zzc[i2], (List<Double>) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 19:
                        zzjy.zzb(this.zzc[i2], (List<Float>) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 20:
                        zzjy.zzc(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 21:
                        zzjy.zzd(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 22:
                        zzjy.zzh(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 23:
                        zzjy.zzf(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 24:
                        zzjy.zzk(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 25:
                        zzjy.zzn(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 26:
                        zzjy.zza(this.zzc[i2], (List<String>) zzky.zzf(t, iZzd2 & 1048575), zzllVar);
                        break;
                    case 27:
                        zzjy.zza(this.zzc[i2], (List<?>) zzky.zzf(t, iZzd2 & 1048575), zzllVar, zza(i2));
                        break;
                    case 28:
                        zzjy.zzb(this.zzc[i2], (List<zzgm>) zzky.zzf(t, iZzd2 & 1048575), zzllVar);
                        break;
                    case 29:
                        zzjy.zzi(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 30:
                        zzjy.zzm(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 31:
                        zzjy.zzl(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 32:
                        zzjy.zzg(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 33:
                        zzjy.zzj(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 34:
                        zzjy.zze(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, false);
                        break;
                    case 35:
                        zzjy.zza(this.zzc[i2], (List<Double>) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 36:
                        zzjy.zzb(this.zzc[i2], (List<Float>) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 37:
                        zzjy.zzc(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 38:
                        zzjy.zzd(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 39:
                        zzjy.zzh(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 40:
                        zzjy.zzf(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 41:
                        zzjy.zzk(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 42:
                        zzjy.zzn(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 43:
                        zzjy.zzi(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 44:
                        zzjy.zzm(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 45:
                        zzjy.zzl(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 46:
                        zzjy.zzg(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 47:
                        zzjy.zzj(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 48:
                        zzjy.zze(this.zzc[i2], (List) zzky.zzf(t, iZzd2 & 1048575), zzllVar, true);
                        break;
                    case 49:
                        zzjy.zzb(this.zzc[i2], (List<?>) zzky.zzf(t, iZzd2 & 1048575), zzllVar, zza(i2));
                        break;
                    case 50:
                        zza(zzllVar, i3, zzky.zzf(t, iZzd2 & 1048575), i2);
                        break;
                    case 51:
                        if (zza(t, i3, i2)) {
                            zzllVar.zza(i3, zzb(t, iZzd2 & 1048575));
                        }
                        break;
                    case 52:
                        if (zza(t, i3, i2)) {
                            zzllVar.zza(i3, zzc(t, iZzd2 & 1048575));
                        }
                        break;
                    case 53:
                        if (zza(t, i3, i2)) {
                            zzllVar.zza(i3, zze(t, iZzd2 & 1048575));
                        }
                        break;
                    case 54:
                        if (zza(t, i3, i2)) {
                            zzllVar.zzc(i3, zze(t, iZzd2 & 1048575));
                        }
                        break;
                    case 55:
                        if (zza(t, i3, i2)) {
                            zzllVar.zzc(i3, zzd(t, iZzd2 & 1048575));
                        }
                        break;
                    case 56:
                        if (zza(t, i3, i2)) {
                            zzllVar.zzd(i3, zze(t, iZzd2 & 1048575));
                        }
                        break;
                    case 57:
                        if (zza(t, i3, i2)) {
                            zzllVar.zzd(i3, zzd(t, iZzd2 & 1048575));
                        }
                        break;
                    case 58:
                        if (zza(t, i3, i2)) {
                            zzllVar.zza(i3, zzf(t, iZzd2 & 1048575));
                        }
                        break;
                    case 59:
                        if (zza(t, i3, i2)) {
                            zza(i3, zzky.zzf(t, iZzd2 & 1048575), zzllVar);
                        }
                        break;
                    case 60:
                        if (zza(t, i3, i2)) {
                            zzllVar.zza(i3, zzky.zzf(t, iZzd2 & 1048575), zza(i2));
                        }
                        break;
                    case 61:
                        if (zza(t, i3, i2)) {
                            zzllVar.zza(i3, (zzgm) zzky.zzf(t, iZzd2 & 1048575));
                        }
                        break;
                    case 62:
                        if (zza(t, i3, i2)) {
                            zzllVar.zze(i3, zzd(t, iZzd2 & 1048575));
                        }
                        break;
                    case 63:
                        if (zza(t, i3, i2)) {
                            zzllVar.zzb(i3, zzd(t, iZzd2 & 1048575));
                        }
                        break;
                    case 64:
                        if (zza(t, i3, i2)) {
                            zzllVar.zza(i3, zzd(t, iZzd2 & 1048575));
                        }
                        break;
                    case 65:
                        if (zza(t, i3, i2)) {
                            zzllVar.zzb(i3, zze(t, iZzd2 & 1048575));
                        }
                        break;
                    case 66:
                        if (zza(t, i3, i2)) {
                            zzllVar.zzf(i3, zzd(t, iZzd2 & 1048575));
                        }
                        break;
                    case 67:
                        if (zza(t, i3, i2)) {
                            zzllVar.zze(i3, zze(t, iZzd2 & 1048575));
                        }
                        break;
                    case 68:
                        if (zza(t, i3, i2)) {
                            zzllVar.zzb(i3, zzky.zzf(t, iZzd2 & 1048575), zza(i2));
                        }
                        break;
                }
            }
            while (entry3 != null) {
                this.zzr.zza(zzllVar, entry3);
                entry3 = itZzd.hasNext() ? (Map.Entry) itZzd.next() : null;
            }
            zza(this.zzq, t, zzllVar);
            return;
        }
        zzb((Object) t, zzllVar);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0023  */
    private final void zzb(T t, zzll zzllVar) throws IOException {
        Iterator itZzd;
        Map.Entry<?, ?> entry;
        int i;
        if (this.zzh) {
            zzhq<T> zzhqVarZza = this.zzr.zza(t);
            if (zzhqVarZza.zza.isEmpty()) {
                itZzd = null;
                entry = null;
            } else {
                itZzd = zzhqVarZza.zzd();
                entry = (Map.Entry) itZzd.next();
            }
        } else {
            itZzd = null;
            entry = null;
        }
        int i2 = -1;
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        Map.Entry<?, ?> entry2 = entry;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int iZzd = zzd(i4);
            int[] iArr = this.zzc;
            int i5 = iArr[i4];
            int i6 = (267386880 & iZzd) >>> 20;
            if (this.zzj || i6 > 17) {
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
            while (entry2 != null && this.zzr.zza(entry2) <= i5) {
                this.zzr.zza(zzllVar, entry2);
                entry2 = itZzd.hasNext() ? (Map.Entry) itZzd.next() : null;
            }
            long j = iZzd & 1048575;
            switch (i6) {
                case 0:
                    if ((i3 & i) != 0) {
                        zzllVar.zza(i5, zzky.zze(t, j));
                        continue;
                    }
                    break;
                case 1:
                    if ((i3 & i) != 0) {
                        zzllVar.zza(i5, zzky.zzd(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    if ((i3 & i) != 0) {
                        zzllVar.zza(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 3:
                    if ((i3 & i) != 0) {
                        zzllVar.zzc(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 4:
                    if ((i3 & i) != 0) {
                        zzllVar.zzc(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 5:
                    if ((i3 & i) != 0) {
                        zzllVar.zzd(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 6:
                    if ((i3 & i) != 0) {
                        zzllVar.zzd(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 7:
                    if ((i3 & i) != 0) {
                        zzllVar.zza(i5, zzky.zzc(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 8:
                    if ((i3 & i) != 0) {
                        zza(i5, unsafe.getObject(t, j), zzllVar);
                    } else {
                        continue;
                    }
                    break;
                case 9:
                    if ((i3 & i) != 0) {
                        zzllVar.zza(i5, unsafe.getObject(t, j), zza(i4));
                    } else {
                        continue;
                    }
                    break;
                case 10:
                    if ((i3 & i) != 0) {
                        zzllVar.zza(i5, (zzgm) unsafe.getObject(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 11:
                    if ((i3 & i) != 0) {
                        zzllVar.zze(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 12:
                    if ((i3 & i) != 0) {
                        zzllVar.zzb(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 13:
                    if ((i3 & i) != 0) {
                        zzllVar.zza(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 14:
                    if ((i3 & i) != 0) {
                        zzllVar.zzb(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 15:
                    if ((i3 & i) != 0) {
                        zzllVar.zzf(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 16:
                    if ((i3 & i) != 0) {
                        zzllVar.zze(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 17:
                    if ((i3 & i) != 0) {
                        zzllVar.zzb(i5, unsafe.getObject(t, j), zza(i4));
                    } else {
                        continue;
                    }
                    break;
                case 18:
                    zzjy.zza(this.zzc[i4], (List<Double>) unsafe.getObject(t, j), zzllVar, false);
                    continue;
                    break;
                case 19:
                    zzjy.zzb(this.zzc[i4], (List<Float>) unsafe.getObject(t, j), zzllVar, false);
                    continue;
                    break;
                case 20:
                    zzjy.zzc(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    continue;
                    break;
                case 21:
                    zzjy.zzd(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    continue;
                    break;
                case 22:
                    zzjy.zzh(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    continue;
                    break;
                case 23:
                    zzjy.zzf(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    continue;
                    break;
                case 24:
                    zzjy.zzk(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    continue;
                    break;
                case 25:
                    zzjy.zzn(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    continue;
                    break;
                case 26:
                    zzjy.zza(this.zzc[i4], (List<String>) unsafe.getObject(t, j), zzllVar);
                    break;
                case 27:
                    zzjy.zza(this.zzc[i4], (List<?>) unsafe.getObject(t, j), zzllVar, zza(i4));
                    break;
                case 28:
                    zzjy.zzb(this.zzc[i4], (List<zzgm>) unsafe.getObject(t, j), zzllVar);
                    break;
                case 29:
                    zzjy.zzi(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    break;
                case 30:
                    zzjy.zzm(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    break;
                case 31:
                    zzjy.zzl(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    break;
                case 32:
                    zzjy.zzg(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    break;
                case 33:
                    zzjy.zzj(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    break;
                case 34:
                    zzjy.zze(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, false);
                    break;
                case 35:
                    zzjy.zza(this.zzc[i4], (List<Double>) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 36:
                    zzjy.zzb(this.zzc[i4], (List<Float>) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 37:
                    zzjy.zzc(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 38:
                    zzjy.zzd(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 39:
                    zzjy.zzh(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 40:
                    zzjy.zzf(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 41:
                    zzjy.zzk(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 42:
                    zzjy.zzn(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 43:
                    zzjy.zzi(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 44:
                    zzjy.zzm(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 45:
                    zzjy.zzl(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 46:
                    zzjy.zzg(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 47:
                    zzjy.zzj(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 48:
                    zzjy.zze(this.zzc[i4], (List) unsafe.getObject(t, j), zzllVar, true);
                    break;
                case 49:
                    zzjy.zzb(this.zzc[i4], (List<?>) unsafe.getObject(t, j), zzllVar, zza(i4));
                    break;
                case 50:
                    zza(zzllVar, i5, unsafe.getObject(t, j), i4);
                    break;
                case 51:
                    if (zza(t, i5, i4)) {
                        zzllVar.zza(i5, zzb(t, j));
                    }
                    break;
                case 52:
                    if (zza(t, i5, i4)) {
                        zzllVar.zza(i5, zzc(t, j));
                    }
                    break;
                case 53:
                    if (zza(t, i5, i4)) {
                        zzllVar.zza(i5, zze(t, j));
                    }
                    break;
                case 54:
                    if (zza(t, i5, i4)) {
                        zzllVar.zzc(i5, zze(t, j));
                    }
                    break;
                case 55:
                    if (zza(t, i5, i4)) {
                        zzllVar.zzc(i5, zzd(t, j));
                    }
                    break;
                case 56:
                    if (zza(t, i5, i4)) {
                        zzllVar.zzd(i5, zze(t, j));
                    }
                    break;
                case 57:
                    if (zza(t, i5, i4)) {
                        zzllVar.zzd(i5, zzd(t, j));
                    }
                    break;
                case 58:
                    if (zza(t, i5, i4)) {
                        zzllVar.zza(i5, zzf(t, j));
                    }
                    break;
                case 59:
                    if (zza(t, i5, i4)) {
                        zza(i5, unsafe.getObject(t, j), zzllVar);
                    }
                    break;
                case 60:
                    if (zza(t, i5, i4)) {
                        zzllVar.zza(i5, unsafe.getObject(t, j), zza(i4));
                    }
                    break;
                case 61:
                    if (zza(t, i5, i4)) {
                        zzllVar.zza(i5, (zzgm) unsafe.getObject(t, j));
                    }
                    break;
                case 62:
                    if (zza(t, i5, i4)) {
                        zzllVar.zze(i5, zzd(t, j));
                    }
                    break;
                case 63:
                    if (zza(t, i5, i4)) {
                        zzllVar.zzb(i5, zzd(t, j));
                    }
                    break;
                case 64:
                    if (zza(t, i5, i4)) {
                        zzllVar.zza(i5, zzd(t, j));
                    }
                    break;
                case 65:
                    if (zza(t, i5, i4)) {
                        zzllVar.zzb(i5, zze(t, j));
                    }
                    break;
                case 66:
                    if (zza(t, i5, i4)) {
                        zzllVar.zzf(i5, zzd(t, j));
                    }
                    break;
                case 67:
                    if (zza(t, i5, i4)) {
                        zzllVar.zze(i5, zze(t, j));
                    }
                    break;
                case 68:
                    if (zza(t, i5, i4)) {
                        zzllVar.zzb(i5, unsafe.getObject(t, j), zza(i4));
                    }
                    break;
            }
        }
        Map.Entry<?, ?> entry3 = entry2;
        while (entry3 != null) {
            this.zzr.zza(zzllVar, entry3);
            entry3 = itZzd.hasNext() ? (Map.Entry) itZzd.next() : null;
        }
        zza(this.zzq, t, zzllVar);
    }

    private final <K, V> void zza(zzll zzllVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzllVar.zza(i, this.zzs.zzb(zzb(i2)), this.zzs.zzc(obj));
        }
    }

    private static <UT, UB> void zza(zzks<UT, UB> zzksVar, T t, zzll zzllVar) throws IOException {
        zzksVar.zza(zzksVar.zzb(t), zzllVar);
    }

    /* JADX WARN: Code duplicated, block: B:177:0x05dc A[LOOP:6: B:175:0x05d8->B:177:0x05dc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:179:0x05e9  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final void zza(T t, zzjx zzjxVar, zzhk zzhkVar) throws IOException {
        int i;
        if (zzhkVar == null) {
            throw new NullPointerException();
        }
        zzks<?, ?> zzksVar = this.zzq;
        zzhm<?> zzhmVar = this.zzr;
        zzhq zzhqVarZzb = null;
        Object objZza = null;
        while (true) {
            try {
                int iZza = zzjxVar.zza();
                int i2 = -1;
                if (iZza >= this.zze && iZza <= this.zzf) {
                    int i3 = 0;
                    int length = (this.zzc.length / 3) - 1;
                    while (i3 <= length) {
                        int i4 = (length + i3) >>> 1;
                        int i5 = i4 * 3;
                        int i6 = this.zzc[i5];
                        if (iZza == i6) {
                            i2 = i5;
                        } else if (iZza < i6) {
                            length = i4 - 1;
                        } else {
                            i3 = i4 + 1;
                        }
                    }
                }
                if (i2 >= 0) {
                    int iZzd = zzd(i2);
                    switch ((267386880 & iZzd) >>> 20) {
                        case 0:
                            zzky.zza(t, iZzd & 1048575, zzjxVar.zzd());
                            zzb((Object) t, i2);
                            continue;
                        case 1:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zze());
                            zzb((Object) t, i2);
                            continue;
                        case 2:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzg());
                            zzb((Object) t, i2);
                            continue;
                        case 3:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzf());
                            zzb((Object) t, i2);
                            continue;
                        case 4:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzh());
                            zzb((Object) t, i2);
                            continue;
                        case 5:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzi());
                            zzb((Object) t, i2);
                            continue;
                        case 6:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzj());
                            zzb((Object) t, i2);
                            continue;
                        case 7:
                            zzky.zza(t, iZzd & 1048575, zzjxVar.zzk());
                            zzb((Object) t, i2);
                            continue;
                        case 8:
                            zza(t, iZzd, zzjxVar);
                            zzb((Object) t, i2);
                            continue;
                        case 9:
                            if (zza((Object) t, i2)) {
                                long j = iZzd & 1048575;
                                zzky.zza(t, j, zzib.zza(zzky.zzf(t, j), zzjxVar.zza(zza(i2), zzhkVar)));
                            } else {
                                zzky.zza(t, iZzd & 1048575, zzjxVar.zza(zza(i2), zzhkVar));
                                zzb((Object) t, i2);
                                continue;
                            }
                            break;
                        case 10:
                            zzky.zza(t, iZzd & 1048575, zzjxVar.zzn());
                            zzb((Object) t, i2);
                            continue;
                        case 11:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzo());
                            zzb((Object) t, i2);
                            continue;
                        case 12:
                            int iZzp = zzjxVar.zzp();
                            zzic zzicVarZzc = zzc(i2);
                            if (zzicVarZzc == null || zzicVarZzc.zza(iZzp)) {
                                zzky.zza((Object) t, iZzd & 1048575, iZzp);
                                zzb((Object) t, i2);
                                continue;
                            } else {
                                objZza = zzjy.zza(iZza, iZzp, objZza, (zzks<UT, Object>) zzksVar);
                            }
                            break;
                        case 13:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzq());
                            zzb((Object) t, i2);
                            continue;
                        case 14:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzr());
                            zzb((Object) t, i2);
                            continue;
                        case 15:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzs());
                            zzb((Object) t, i2);
                            continue;
                        case 16:
                            zzky.zza((Object) t, iZzd & 1048575, zzjxVar.zzt());
                            zzb((Object) t, i2);
                            continue;
                        case 17:
                            if (zza((Object) t, i2)) {
                                long j2 = iZzd & 1048575;
                                zzky.zza(t, j2, zzib.zza(zzky.zzf(t, j2), zzjxVar.zzb(zza(i2), zzhkVar)));
                            } else {
                                zzky.zza(t, iZzd & 1048575, zzjxVar.zzb(zza(i2), zzhkVar));
                                zzb((Object) t, i2);
                                continue;
                            }
                            break;
                        case 18:
                            zzjxVar.zza(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 19:
                            zzjxVar.zzb(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 20:
                            zzjxVar.zzd(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 21:
                            zzjxVar.zzc(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 22:
                            zzjxVar.zze(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 23:
                            zzjxVar.zzf(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 24:
                            zzjxVar.zzg(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 25:
                            zzjxVar.zzh(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 26:
                            if (zzf(iZzd)) {
                                zzjxVar.zzj(this.zzp.zza(t, iZzd & 1048575));
                            } else {
                                zzjxVar.zzi(this.zzp.zza(t, iZzd & 1048575));
                                continue;
                            }
                            break;
                        case 27:
                            zzjxVar.zza(this.zzp.zza(t, iZzd & 1048575), zza(i2), zzhkVar);
                            continue;
                        case 28:
                            zzjxVar.zzk(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 29:
                            zzjxVar.zzl(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 30:
                            List<Integer> listZza = this.zzp.zza(t, iZzd & 1048575);
                            zzjxVar.zzm(listZza);
                            objZza = zzjy.zza(iZza, listZza, zzc(i2), objZza, zzksVar);
                            continue;
                        case 31:
                            zzjxVar.zzn(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 32:
                            zzjxVar.zzo(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 33:
                            zzjxVar.zzp(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 34:
                            zzjxVar.zzq(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 35:
                            zzjxVar.zza(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 36:
                            zzjxVar.zzb(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 37:
                            zzjxVar.zzd(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 38:
                            zzjxVar.zzc(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 39:
                            zzjxVar.zze(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 40:
                            zzjxVar.zzf(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 41:
                            zzjxVar.zzg(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 42:
                            zzjxVar.zzh(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 43:
                            zzjxVar.zzl(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 44:
                            List<Integer> listZza2 = this.zzp.zza(t, iZzd & 1048575);
                            zzjxVar.zzm(listZza2);
                            objZza = zzjy.zza(iZza, listZza2, zzc(i2), objZza, zzksVar);
                            continue;
                        case 45:
                            zzjxVar.zzn(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 46:
                            zzjxVar.zzo(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 47:
                            zzjxVar.zzp(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 48:
                            zzjxVar.zzq(this.zzp.zza(t, iZzd & 1048575));
                            continue;
                        case 49:
                            zzjxVar.zzb(this.zzp.zza(t, iZzd & 1048575), zza(i2), zzhkVar);
                            continue;
                        case 50:
                            Object objZzb = zzb(i2);
                            long jZzd = zzd(i2) & 1048575;
                            Object objZzf = zzky.zzf(t, jZzd);
                            if (objZzf == null) {
                                objZzf = this.zzs.zzf(objZzb);
                                zzky.zza(t, jZzd, objZzf);
                            } else if (this.zzs.zzd(objZzf)) {
                                Object objZzf2 = this.zzs.zzf(objZzb);
                                this.zzs.zza(objZzf2, objZzf);
                                zzky.zza(t, jZzd, objZzf2);
                                objZzf = objZzf2;
                            }
                            zzjxVar.zza(this.zzs.zza(objZzf), this.zzs.zzb(objZzb), zzhkVar);
                            continue;
                        case 51:
                            zzky.zza(t, iZzd & 1048575, Double.valueOf(zzjxVar.zzd()));
                            zzb(t, iZza, i2);
                            continue;
                        case 52:
                            zzky.zza(t, iZzd & 1048575, Float.valueOf(zzjxVar.zze()));
                            zzb(t, iZza, i2);
                            continue;
                        case 53:
                            zzky.zza(t, iZzd & 1048575, Long.valueOf(zzjxVar.zzg()));
                            zzb(t, iZza, i2);
                            continue;
                        case 54:
                            zzky.zza(t, iZzd & 1048575, Long.valueOf(zzjxVar.zzf()));
                            zzb(t, iZza, i2);
                            continue;
                        case 55:
                            zzky.zza(t, iZzd & 1048575, Integer.valueOf(zzjxVar.zzh()));
                            zzb(t, iZza, i2);
                            continue;
                        case 56:
                            zzky.zza(t, iZzd & 1048575, Long.valueOf(zzjxVar.zzi()));
                            zzb(t, iZza, i2);
                            continue;
                        case 57:
                            zzky.zza(t, iZzd & 1048575, Integer.valueOf(zzjxVar.zzj()));
                            zzb(t, iZza, i2);
                            continue;
                        case 58:
                            zzky.zza(t, iZzd & 1048575, Boolean.valueOf(zzjxVar.zzk()));
                            zzb(t, iZza, i2);
                            continue;
                        case 59:
                            zza(t, iZzd, zzjxVar);
                            zzb(t, iZza, i2);
                            continue;
                        case 60:
                            if (zza(t, iZza, i2)) {
                                long j3 = iZzd & 1048575;
                                zzky.zza(t, j3, zzib.zza(zzky.zzf(t, j3), zzjxVar.zza(zza(i2), zzhkVar)));
                            } else {
                                zzky.zza(t, iZzd & 1048575, zzjxVar.zza(zza(i2), zzhkVar));
                                zzb((Object) t, i2);
                            }
                            zzb(t, iZza, i2);
                            continue;
                        case 61:
                            zzky.zza(t, iZzd & 1048575, zzjxVar.zzn());
                            zzb(t, iZza, i2);
                            continue;
                        case 62:
                            zzky.zza(t, iZzd & 1048575, Integer.valueOf(zzjxVar.zzo()));
                            zzb(t, iZza, i2);
                            continue;
                        case 63:
                            int iZzp2 = zzjxVar.zzp();
                            zzic zzicVarZzc2 = zzc(i2);
                            if (zzicVarZzc2 == null || zzicVarZzc2.zza(iZzp2)) {
                                zzky.zza(t, iZzd & 1048575, Integer.valueOf(iZzp2));
                                zzb(t, iZza, i2);
                                continue;
                            } else {
                                objZza = zzjy.zza(iZza, iZzp2, objZza, (zzks<UT, Object>) zzksVar);
                            }
                            break;
                        case 64:
                            zzky.zza(t, iZzd & 1048575, Integer.valueOf(zzjxVar.zzq()));
                            zzb(t, iZza, i2);
                            continue;
                        case 65:
                            zzky.zza(t, iZzd & 1048575, Long.valueOf(zzjxVar.zzr()));
                            zzb(t, iZza, i2);
                            continue;
                        case 66:
                            zzky.zza(t, iZzd & 1048575, Integer.valueOf(zzjxVar.zzs()));
                            zzb(t, iZza, i2);
                            continue;
                        case 67:
                            zzky.zza(t, iZzd & 1048575, Long.valueOf(zzjxVar.zzt()));
                            zzb(t, iZza, i2);
                            continue;
                        case 68:
                            zzky.zza(t, iZzd & 1048575, zzjxVar.zzb(zza(i2), zzhkVar));
                            zzb(t, iZza, i2);
                            continue;
                        default:
                            if (objZza == null) {
                                try {
                                    objZza = zzksVar.zza();
                                } catch (zzij unused) {
                                    zzksVar.zza(zzjxVar);
                                    if (objZza == null) {
                                        objZza = zzksVar.zzc(t);
                                    }
                                    if (!zzksVar.zza((Object) objZza, zzjxVar)) {
                                        for (int i7 = this.zzm; i7 < this.zzn; i7++) {
                                            objZza = zza((Object) t, this.zzl[i7], objZza, (zzks<UT, Object>) zzksVar);
                                        }
                                        if (objZza != null) {
                                            zzksVar.zzb(t, (Object) objZza);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                break;
                            }
                            if (!zzksVar.zza((Object) objZza, zzjxVar)) {
                                for (int i8 = this.zzm; i8 < this.zzn; i8++) {
                                    objZza = zza((Object) t, this.zzl[i8], objZza, (zzks<UT, Object>) zzksVar);
                                }
                                if (objZza != null) {
                                    zzksVar.zzb(t, (Object) objZza);
                                    return;
                                }
                                return;
                            }
                            break;
                            break;
                    }
                    for (i = this.zzm; i < this.zzn; i++) {
                        objZza = zza((Object) t, this.zzl[i], objZza, (zzks<UT, Object>) zzksVar);
                    }
                    if (objZza != null) {
                        zzksVar.zzb(t, (Object) objZza);
                    }
                    throw th;
                }
                if (iZza == Integer.MAX_VALUE) {
                    for (int i9 = this.zzm; i9 < this.zzn; i9++) {
                        objZza = zza((Object) t, this.zzl[i9], objZza, (zzks<UT, Object>) zzksVar);
                    }
                    if (objZza != null) {
                        zzksVar.zzb(t, (Object) objZza);
                        return;
                    }
                    return;
                }
                Object objZza2 = !this.zzh ? null : zzhmVar.zza(zzhkVar, this.zzg, iZza);
                if (objZza2 != null) {
                    if (zzhqVarZzb == null) {
                        zzhqVarZzb = zzhmVar.zzb(t);
                    }
                    zzhq zzhqVar = zzhqVarZzb;
                    objZza = zzhmVar.zza(zzjxVar, objZza2, zzhkVar, zzhqVar, objZza, zzksVar);
                    zzhqVarZzb = zzhqVar;
                } else {
                    zzksVar.zza(zzjxVar);
                    if (objZza == null) {
                        objZza = zzksVar.zzc(t);
                    }
                    if (!zzksVar.zza((Object) objZza, zzjxVar)) {
                        for (int i10 = this.zzm; i10 < this.zzn; i10++) {
                            objZza = zza((Object) t, this.zzl[i10], objZza, (zzks<UT, Object>) zzksVar);
                        }
                        if (objZza != null) {
                            zzksVar.zzb(t, (Object) objZza);
                            return;
                        }
                        return;
                    }
                }
            } catch (Throwable th) {
                while (i < this.zzn) {
                    objZza = zza((Object) t, this.zzl[i], objZza, (zzks<UT, Object>) zzksVar);
                }
                if (objZza != null) {
                    zzksVar.zzb(t, (Object) objZza);
                }
                throw th;
            }
        }
    }

    private final zzjw zza(int i) {
        int i2 = (i / 3) << 1;
        zzjw zzjwVar = (zzjw) this.zzd[i2];
        if (zzjwVar != null) {
            return zzjwVar;
        }
        zzjw<T> zzjwVarZza = zzjs.zza().zza((Class) this.zzd[i2 + 1]);
        this.zzd[i2] = zzjwVarZza;
        return zzjwVarZza;
    }

    private final Object zzb(int i) {
        return this.zzd[(i / 3) << 1];
    }

    private final zzic zzc(int i) {
        return (zzic) this.zzd[((i / 3) << 1) + 1];
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final void zzc(T t) {
        int i;
        int i2 = this.zzm;
        while (true) {
            i = this.zzn;
            if (i2 >= i) {
                break;
            }
            long jZzd = zzd(this.zzl[i2]) & 1048575;
            Object objZzf = zzky.zzf(t, jZzd);
            if (objZzf != null) {
                zzky.zza(t, jZzd, this.zzs.zze(objZzf));
            }
            i2++;
        }
        int length = this.zzl.length;
        while (i < length) {
            this.zzp.zzb(t, this.zzl[i]);
            i++;
        }
        this.zzq.zzd(t);
        if (this.zzh) {
            this.zzr.zzc(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzks<UT, UB> zzksVar) {
        zzic zzicVarZzc;
        int i2 = this.zzc[i];
        Object objZzf = zzky.zzf(obj, zzd(i) & 1048575);
        return (objZzf == null || (zzicVarZzc = zzc(i)) == null) ? ub : (UB) zza(i, i2, this.zzs.zza(objZzf), zzicVarZzc, ub, zzksVar);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzic zzicVar, UB ub, zzks<UT, UB> zzksVar) {
        zzjb<?, ?> zzjbVarZzb = this.zzs.zzb(zzb(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzicVar.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzksVar.zza();
                }
                zzgu zzguVarZzc = zzgm.zzc(zziy.zza(zzjbVarZzb, next.getKey(), next.getValue()));
                try {
                    zziy.zza(zzguVarZzc.zzb(), zzjbVarZzb, next.getKey(), next.getValue());
                    zzksVar.zza(ub, i2, zzguVarZzc.zza());
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
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.firebase_auth.zzjw] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.firebase_auth.zzjw] */
    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final boolean zzd(T t) {
        int i;
        List list;
        ?? Zza;
        int i2;
        int i3 = 0;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= this.zzm) {
                return !this.zzh || this.zzr.zza(t).zzf();
            }
            int i6 = this.zzl[i3];
            int i7 = this.zzc[i6];
            int iZzd = zzd(i6);
            if (this.zzj) {
                i = 0;
            } else {
                int i8 = this.zzc[i6 + 2];
                int i9 = i8 & 1048575;
                i = 1 << (i8 >>> 20);
                if (i9 != i4) {
                    i5 = zzb.getInt(t, i9);
                    i4 = i9;
                }
            }
            if (((268435456 & iZzd) != 0) && !zza(t, i6, i5, i)) {
                return false;
            }
            int i10 = (267386880 & iZzd) >>> 20;
            if (i10 == 9 || i10 == 17) {
                if (zza(t, i6, i5, i) && !zza(t, iZzd, zza(i6))) {
                    return false;
                }
            } else if (i10 == 27) {
                list = (List) zzky.zzf(t, iZzd & 1048575);
                if (!list.isEmpty()) {
                    Zza = zza(i6);
                    for (i2 = 0; i2 < list.size(); i2++) {
                        if (!Zza.zzd(list.get(i2))) {
                            z = false;
                            break;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (i10 == 60 || i10 == 68) {
                if (zza(t, i7, i6) && !zza(t, iZzd, zza(i6))) {
                    return false;
                }
            } else if (i10 == 49) {
                list = (List) zzky.zzf(t, iZzd & 1048575);
                if (!list.isEmpty()) {
                    Zza = zza(i6);
                    while (i2 < list.size()) {
                        if (!Zza.zzd(list.get(i2))) {
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
                Map<?, ?> mapZzc = this.zzs.zzc(zzky.zzf(t, iZzd & 1048575));
                if (!mapZzc.isEmpty()) {
                    if (this.zzs.zzb(zzb(i6)).zzc.zza() == zzlm.MESSAGE) {
                        ?? Zza2 = 0;
                        for (Object obj : mapZzc.values()) {
                            if (Zza2 == 0) {
                                Zza2 = Zza2;
                                Zza2 = zzjs.zza().zza((Class) obj.getClass());
                            }
                            Zza2 = Zza2;
                            if (!Zza2.zzd(obj)) {
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
    private static boolean zza(Object obj, int i, zzjw zzjwVar) {
        return zzjwVar.zzd(zzky.zzf(obj, i & 1048575));
    }

    private static void zza(int i, Object obj, zzll zzllVar) throws IOException {
        if (obj instanceof String) {
            zzllVar.zza(i, (String) obj);
        } else {
            zzllVar.zza(i, (zzgm) obj);
        }
    }

    private final void zza(Object obj, int i, zzjx zzjxVar) throws IOException {
        if (zzf(i)) {
            zzky.zza(obj, i & 1048575, zzjxVar.zzm());
        } else if (this.zzi) {
            zzky.zza(obj, i & 1048575, zzjxVar.zzl());
        } else {
            zzky.zza(obj, i & 1048575, zzjxVar.zzn());
        }
    }

    private final int zzd(int i) {
        return this.zzc[i + 1];
    }

    private final int zze(int i) {
        return this.zzc[i + 2];
    }

    private static <T> double zzb(T t, long j) {
        return ((Double) zzky.zzf(t, j)).doubleValue();
    }

    private static <T> float zzc(T t, long j) {
        return ((Float) zzky.zzf(t, j)).floatValue();
    }

    private static <T> int zzd(T t, long j) {
        return ((Integer) zzky.zzf(t, j)).intValue();
    }

    private static <T> long zze(T t, long j) {
        return ((Long) zzky.zzf(t, j)).longValue();
    }

    private static <T> boolean zzf(T t, long j) {
        return ((Boolean) zzky.zzf(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((Object) t, i) == zza((Object) t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzj) {
            return zza((Object) t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzj) {
            int iZzd = zzd(i);
            long j = iZzd & 1048575;
            switch ((iZzd & 267386880) >>> 20) {
                case 0:
                    return zzky.zze(t, j) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                case 1:
                    return zzky.zzd(t, j) != 0.0f;
                case 2:
                    return zzky.zzb(t, j) != 0;
                case 3:
                    return zzky.zzb(t, j) != 0;
                case 4:
                    return zzky.zza(t, j) != 0;
                case 5:
                    return zzky.zzb(t, j) != 0;
                case 6:
                    return zzky.zza(t, j) != 0;
                case 7:
                    return zzky.zzc(t, j);
                case 8:
                    Object objZzf = zzky.zzf(t, j);
                    if (objZzf instanceof String) {
                        return !((String) objZzf).isEmpty();
                    }
                    if (objZzf instanceof zzgm) {
                        return !zzgm.zza.equals(objZzf);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzky.zzf(t, j) != null;
                case 10:
                    return !zzgm.zza.equals(zzky.zzf(t, j));
                case 11:
                    return zzky.zza(t, j) != 0;
                case 12:
                    return zzky.zza(t, j) != 0;
                case 13:
                    return zzky.zza(t, j) != 0;
                case 14:
                    return zzky.zzb(t, j) != 0;
                case 15:
                    return zzky.zza(t, j) != 0;
                case 16:
                    return zzky.zzb(t, j) != 0;
                case 17:
                    return zzky.zzf(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int iZze = zze(i);
        return (zzky.zza(t, (long) (iZze & 1048575)) & (1 << (iZze >>> 20))) != 0;
    }

    private final void zzb(T t, int i) {
        if (this.zzj) {
            return;
        }
        int iZze = zze(i);
        long j = iZze & 1048575;
        zzky.zza((Object) t, j, zzky.zza(t, j) | (1 << (iZze >>> 20)));
    }

    private final boolean zza(T t, int i, int i2) {
        return zzky.zza(t, (long) (zze(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzky.zza((Object) t, zze(i2) & 1048575, i);
    }
}
