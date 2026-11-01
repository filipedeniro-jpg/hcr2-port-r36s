package com.google.android.gms.internal.ads;

import com.google.android.gms.drive.DriveFile;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdrr<T> implements zzdsc<T> {
    private final int[] zzhmm;
    private final Object[] zzhmn;
    private final int zzhmo;
    private final int zzhmp;
    private final zzdrn zzhmq;
    private final boolean zzhmr;
    private final boolean zzhms;
    private final boolean zzhmt;
    private final boolean zzhmu;
    private final int[] zzhmv;
    private final int zzhmw;
    private final int zzhmx;
    private final zzdrv zzhmy;
    private final zzdqx zzhmz;
    private final zzdsu<?, ?> zzhna;
    private final zzdps<?> zzhnb;
    private final zzdrg zzhnc;
    private static final int[] zzhml = new int[0];
    private static final Unsafe zzgpv = zzdta.zzbbx();

    private zzdrr(int[] iArr, Object[] objArr, int i, int i2, zzdrn zzdrnVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzdrv zzdrvVar, zzdqx zzdqxVar, zzdsu<?, ?> zzdsuVar, zzdps<?> zzdpsVar, zzdrg zzdrgVar) {
        this.zzhmm = iArr;
        this.zzhmn = objArr;
        this.zzhmo = i;
        this.zzhmp = i2;
        this.zzhms = zzdrnVar instanceof zzdqd;
        this.zzhmt = z;
        this.zzhmr = zzdpsVar != null && zzdpsVar.zzm(zzdrnVar);
        this.zzhmu = false;
        this.zzhmv = iArr2;
        this.zzhmw = i3;
        this.zzhmx = i4;
        this.zzhmy = zzdrvVar;
        this.zzhmz = zzdqxVar;
        this.zzhna = zzdsuVar;
        this.zzhnb = zzdpsVar;
        this.zzhmq = zzdrnVar;
        this.zzhnc = zzdrgVar;
    }

    private static boolean zzgx(int i) {
        return (i & DriveFile.MODE_WRITE_ONLY) != 0;
    }

    /* JADX WARN: Code duplicated, block: B:172:0x0379  */
    /* JADX WARN: Code duplicated, block: B:186:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:189:0x03d7  */
    static <T> zzdrr<T> zza(Class<T> cls, zzdrl zzdrlVar, zzdrv zzdrvVar, zzdqx zzdqxVar, zzdsu<?, ?> zzdsuVar, zzdps<?> zzdpsVar, zzdrg zzdrgVar) {
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
        if (zzdrlVar instanceof zzdsa) {
            zzdsa zzdsaVar = (zzdsa) zzdrlVar;
            int iCharAt4 = 0;
            boolean z = zzdsaVar.zzbat() == zzdqd.zzd.zzhkq;
            String strZzbbb = zzdsaVar.zzbbb();
            int length = strZzbbb.length();
            int iCharAt5 = strZzbbb.charAt(0);
            if (iCharAt5 >= 55296) {
                int i27 = iCharAt5 & 8191;
                int i28 = 1;
                int i29 = 13;
                while (true) {
                    i = i28 + 1;
                    cCharAt14 = strZzbbb.charAt(i28);
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
            int iCharAt6 = strZzbbb.charAt(i);
            if (iCharAt6 >= 55296) {
                int i31 = iCharAt6 & 8191;
                int i32 = 13;
                while (true) {
                    i2 = i30 + 1;
                    cCharAt13 = strZzbbb.charAt(i30);
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
                iArr = zzhml;
                iCharAt3 = 0;
                i10 = 0;
                i8 = 0;
                iCharAt = 0;
                iCharAt2 = 0;
                i9 = 0;
            } else {
                int i33 = i2 + 1;
                int iCharAt7 = strZzbbb.charAt(i2);
                if (iCharAt7 >= 55296) {
                    int i34 = iCharAt7 & 8191;
                    int i35 = 13;
                    while (true) {
                        i3 = i33 + 1;
                        cCharAt8 = strZzbbb.charAt(i33);
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
                int iCharAt8 = strZzbbb.charAt(i3);
                if (iCharAt8 >= 55296) {
                    int i37 = iCharAt8 & 8191;
                    int i38 = 13;
                    while (true) {
                        i4 = i36 + 1;
                        cCharAt7 = strZzbbb.charAt(i36);
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
                int iCharAt9 = strZzbbb.charAt(i4);
                if (iCharAt9 >= 55296) {
                    int i40 = iCharAt9 & 8191;
                    int i41 = 13;
                    while (true) {
                        i5 = i39 + 1;
                        cCharAt6 = strZzbbb.charAt(i39);
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
                iCharAt = strZzbbb.charAt(i5);
                if (iCharAt >= 55296) {
                    int i43 = iCharAt & 8191;
                    int i44 = 13;
                    while (true) {
                        i6 = i42 + 1;
                        cCharAt5 = strZzbbb.charAt(i42);
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
                iCharAt2 = strZzbbb.charAt(i6);
                if (iCharAt2 >= 55296) {
                    int i46 = iCharAt2 & 8191;
                    int i47 = 13;
                    while (true) {
                        i14 = i45 + 1;
                        cCharAt4 = strZzbbb.charAt(i45);
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
                iCharAt3 = strZzbbb.charAt(i45);
                if (iCharAt3 >= 55296) {
                    int i49 = iCharAt3 & 8191;
                    int i50 = 13;
                    while (true) {
                        i13 = i48 + 1;
                        cCharAt3 = strZzbbb.charAt(i48);
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
                int iCharAt10 = strZzbbb.charAt(i48);
                if (iCharAt10 >= 55296) {
                    int i52 = 13;
                    int i53 = iCharAt10 & 8191;
                    int i54 = i51;
                    while (true) {
                        i12 = i54 + 1;
                        cCharAt2 = strZzbbb.charAt(i54);
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
                iCharAt4 = strZzbbb.charAt(i7);
                if (iCharAt4 >= 55296) {
                    int i56 = 13;
                    int i57 = iCharAt4 & 8191;
                    int i58 = i55;
                    while (true) {
                        i11 = i58 + 1;
                        cCharAt = strZzbbb.charAt(i58);
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
            Unsafe unsafe = zzgpv;
            Object[] objArrZzbbc = zzdsaVar.zzbbc();
            Class<?> cls2 = zzdsaVar.zzbav().getClass();
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
                int iCharAt11 = strZzbbb.charAt(i2);
                char c = 55296;
                if (iCharAt11 >= 55296) {
                    int i67 = 13;
                    int i68 = iCharAt11 & 8191;
                    int i69 = i66;
                    while (true) {
                        i26 = i69 + 1;
                        cCharAt12 = strZzbbb.charAt(i69);
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
                int iCharAt12 = strZzbbb.charAt(i15);
                int i71 = length;
                char c2 = 55296;
                if (iCharAt12 >= 55296) {
                    int i72 = 13;
                    int i73 = iCharAt12 & 8191;
                    int i74 = i70;
                    while (true) {
                        i25 = i74 + 1;
                        cCharAt11 = strZzbbb.charAt(i74);
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
                    int iCharAt13 = strZzbbb.charAt(i16);
                    char c3 = 55296;
                    if (iCharAt13 >= 55296) {
                        int i79 = iCharAt13 & 8191;
                        int i80 = 13;
                        while (true) {
                            i24 = i78 + 1;
                            cCharAt10 = strZzbbb.charAt(i78);
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
                        objArr[((i65 / 3) << 1) + 1] = objArrZzbbc[i60];
                        i60++;
                    } else {
                        if (i81 == 12 && (iCharAt5 & 1) == 1) {
                            objArr[((i65 / 3) << 1) + 1] = objArrZzbbc[i60];
                            i60++;
                        }
                        i23 = 1;
                    }
                    int i83 = iCharAt13 << i23;
                    Object obj = objArrZzbbc[i83];
                    if (obj instanceof Field) {
                        fieldZza2 = (Field) obj;
                    } else {
                        fieldZza2 = zza(cls2, (String) obj);
                        objArrZzbbc[i83] = fieldZza2;
                    }
                    int i84 = i10;
                    int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZza2);
                    int i85 = i83 + 1;
                    Object obj2 = objArrZzbbc[i85];
                    if (obj2 instanceof Field) {
                        fieldZza3 = (Field) obj2;
                    } else {
                        fieldZza3 = zza(cls2, (String) obj2);
                        objArrZzbbc[i85] = fieldZza3;
                    }
                    strZzbbb = strZzbbb;
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
                    Field fieldZza4 = zza(cls2, (String) objArrZzbbc[i60]);
                    if (i76 == 9 || i76 == 17) {
                        i18 = i86;
                        objArr[((i65 / 3) << 1) + 1] = fieldZza4.getType();
                    } else {
                        if (i76 == 27 || i76 == 49) {
                            i18 = i86;
                            i22 = i17 + 1;
                            objArr[((i65 / 3) << 1) + 1] = objArrZzbbc[i17];
                        } else if (i76 == 12 || i76 == 30 || i76 == 44) {
                            i18 = i86;
                            if ((iCharAt5 & 1) == 1) {
                                i22 = i17 + 1;
                                objArr[((i65 / 3) << 1) + 1] = objArrZzbbc[i17];
                            }
                            iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                            if ((iCharAt5 & 1) == 1 || i76 > 17) {
                                i19 = i16;
                                i20 = 0;
                                iObjectFieldOffset2 = 0;
                            } else {
                                i19 = i16 + 1;
                                int iCharAt14 = strZzbbb.charAt(i16);
                                if (iCharAt14 >= 55296) {
                                    int i87 = iCharAt14 & 8191;
                                    int i88 = 13;
                                    while (true) {
                                        i21 = i19 + 1;
                                        cCharAt9 = strZzbbb.charAt(i19);
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
                                Object obj3 = objArrZzbbc[i89];
                                if (obj3 instanceof Field) {
                                    fieldZza = (Field) obj3;
                                } else {
                                    fieldZza = zza(cls2, (String) obj3);
                                    objArrZzbbc[i89] = fieldZza;
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
                            objArr[i91] = objArrZzbbc[i17];
                            if ((iCharAt12 & 2048) != 0) {
                                i17 = i92 + 1;
                                objArr[i91 + 1] = objArrZzbbc[i92];
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
                strZzbbb = strZzbbb;
            }
            return new zzdrr<>(iArr2, objArr, i10, iCharAt, zzdsaVar.zzbav(), z, false, iArr, iCharAt4, i61, zzdrvVar, zzdqxVar, zzdsuVar, zzdpsVar, zzdrgVar);
        }
        ((zzdsr) zzdrlVar).zzbat();
        int i95 = zzdqd.zzd.zzhkq;
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

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final T newInstance() {
        return (T) this.zzhmy.newInstance(this.zzhmq);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x01c1  */
    @Override // com.google.android.gms.internal.ads.zzdsc
    public final boolean equals(T t, T t2) {
        int length = this.zzhmm.length;
        int i = 0;
        while (true) {
            boolean zZzg = true;
            if (i < length) {
                int iZzgv = zzgv(i);
                long j = iZzgv & 1048575;
                switch ((iZzgv & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zzdta.zzo(t, j)) != Double.doubleToLongBits(zzdta.zzo(t2, j))) {
                            zZzg = false;
                        }
                        break;
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zzdta.zzn(t, j)) != Float.floatToIntBits(zzdta.zzn(t2, j))) {
                            zZzg = false;
                        }
                        break;
                    case 2:
                        if (!zzc(t, t2, i) || zzdta.zzl(t, j) != zzdta.zzl(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 3:
                        if (!zzc(t, t2, i) || zzdta.zzl(t, j) != zzdta.zzl(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 4:
                        if (!zzc(t, t2, i) || zzdta.zzk(t, j) != zzdta.zzk(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 5:
                        if (!zzc(t, t2, i) || zzdta.zzl(t, j) != zzdta.zzl(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 6:
                        if (!zzc(t, t2, i) || zzdta.zzk(t, j) != zzdta.zzk(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 7:
                        if (!zzc(t, t2, i) || zzdta.zzm(t, j) != zzdta.zzm(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 8:
                        if (!zzc(t, t2, i) || !zzdse.zzg(zzdta.zzp(t, j), zzdta.zzp(t2, j))) {
                            zZzg = false;
                        }
                        break;
                    case 9:
                        if (!zzc(t, t2, i) || !zzdse.zzg(zzdta.zzp(t, j), zzdta.zzp(t2, j))) {
                            zZzg = false;
                        }
                        break;
                    case 10:
                        if (!zzc(t, t2, i) || !zzdse.zzg(zzdta.zzp(t, j), zzdta.zzp(t2, j))) {
                            zZzg = false;
                        }
                        break;
                    case 11:
                        if (!zzc(t, t2, i) || zzdta.zzk(t, j) != zzdta.zzk(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 12:
                        if (!zzc(t, t2, i) || zzdta.zzk(t, j) != zzdta.zzk(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 13:
                        if (!zzc(t, t2, i) || zzdta.zzk(t, j) != zzdta.zzk(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 14:
                        if (!zzc(t, t2, i) || zzdta.zzl(t, j) != zzdta.zzl(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 15:
                        if (!zzc(t, t2, i) || zzdta.zzk(t, j) != zzdta.zzk(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 16:
                        if (!zzc(t, t2, i) || zzdta.zzl(t, j) != zzdta.zzl(t2, j)) {
                            zZzg = false;
                        }
                        break;
                    case 17:
                        if (!zzc(t, t2, i) || !zzdse.zzg(zzdta.zzp(t, j), zzdta.zzp(t2, j))) {
                            zZzg = false;
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
                        zZzg = zzdse.zzg(zzdta.zzp(t, j), zzdta.zzp(t2, j));
                        break;
                    case 50:
                        zZzg = zzdse.zzg(zzdta.zzp(t, j), zzdta.zzp(t2, j));
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
                        long jZzgw = zzgw(i) & 1048575;
                        if (zzdta.zzk(t, jZzgw) != zzdta.zzk(t2, jZzgw) || !zzdse.zzg(zzdta.zzp(t, j), zzdta.zzp(t2, j))) {
                            zZzg = false;
                        }
                        break;
                }
                if (!zZzg) {
                    return false;
                }
                i += 3;
            } else {
                if (!this.zzhna.zzay(t).equals(this.zzhna.zzay(t2))) {
                    return false;
                }
                if (this.zzhmr) {
                    return this.zzhnb.zzai(t).equals(this.zzhnb.zzai(t2));
                }
                return true;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final int hashCode(T t) {
        int i;
        int iZzfk;
        int length = this.zzhmm.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzgv = zzgv(i3);
            int i4 = this.zzhmm[i3];
            long j = 1048575 & iZzgv;
            int iHashCode = 37;
            switch ((iZzgv & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    iZzfk = zzdqe.zzfk(Double.doubleToLongBits(zzdta.zzo(t, j)));
                    i2 = i + iZzfk;
                    break;
                case 1:
                    i = i2 * 53;
                    iZzfk = Float.floatToIntBits(zzdta.zzn(t, j));
                    i2 = i + iZzfk;
                    break;
                case 2:
                    i = i2 * 53;
                    iZzfk = zzdqe.zzfk(zzdta.zzl(t, j));
                    i2 = i + iZzfk;
                    break;
                case 3:
                    i = i2 * 53;
                    iZzfk = zzdqe.zzfk(zzdta.zzl(t, j));
                    i2 = i + iZzfk;
                    break;
                case 4:
                    i = i2 * 53;
                    iZzfk = zzdta.zzk(t, j);
                    i2 = i + iZzfk;
                    break;
                case 5:
                    i = i2 * 53;
                    iZzfk = zzdqe.zzfk(zzdta.zzl(t, j));
                    i2 = i + iZzfk;
                    break;
                case 6:
                    i = i2 * 53;
                    iZzfk = zzdta.zzk(t, j);
                    i2 = i + iZzfk;
                    break;
                case 7:
                    i = i2 * 53;
                    iZzfk = zzdqe.zzbj(zzdta.zzm(t, j));
                    i2 = i + iZzfk;
                    break;
                case 8:
                    i = i2 * 53;
                    iZzfk = ((String) zzdta.zzp(t, j)).hashCode();
                    i2 = i + iZzfk;
                    break;
                case 9:
                    Object objZzp = zzdta.zzp(t, j);
                    if (objZzp != null) {
                        iHashCode = objZzp.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iZzfk = zzdta.zzp(t, j).hashCode();
                    i2 = i + iZzfk;
                    break;
                case 11:
                    i = i2 * 53;
                    iZzfk = zzdta.zzk(t, j);
                    i2 = i + iZzfk;
                    break;
                case 12:
                    i = i2 * 53;
                    iZzfk = zzdta.zzk(t, j);
                    i2 = i + iZzfk;
                    break;
                case 13:
                    i = i2 * 53;
                    iZzfk = zzdta.zzk(t, j);
                    i2 = i + iZzfk;
                    break;
                case 14:
                    i = i2 * 53;
                    iZzfk = zzdqe.zzfk(zzdta.zzl(t, j));
                    i2 = i + iZzfk;
                    break;
                case 15:
                    i = i2 * 53;
                    iZzfk = zzdta.zzk(t, j);
                    i2 = i + iZzfk;
                    break;
                case 16:
                    i = i2 * 53;
                    iZzfk = zzdqe.zzfk(zzdta.zzl(t, j));
                    i2 = i + iZzfk;
                    break;
                case 17:
                    Object objZzp2 = zzdta.zzp(t, j);
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
                    iZzfk = zzdta.zzp(t, j).hashCode();
                    i2 = i + iZzfk;
                    break;
                case 50:
                    i = i2 * 53;
                    iZzfk = zzdta.zzp(t, j).hashCode();
                    i2 = i + iZzfk;
                    break;
                case 51:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdqe.zzfk(Double.doubleToLongBits(zzf(t, j)));
                        i2 = i + iZzfk;
                    }
                    break;
                case 52:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = Float.floatToIntBits(zzg(t, j));
                        i2 = i + iZzfk;
                    }
                    break;
                case 53:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdqe.zzfk(zzi(t, j));
                        i2 = i + iZzfk;
                    }
                    break;
                case 54:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdqe.zzfk(zzi(t, j));
                        i2 = i + iZzfk;
                    }
                    break;
                case 55:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzh(t, j);
                        i2 = i + iZzfk;
                    }
                    break;
                case 56:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdqe.zzfk(zzi(t, j));
                        i2 = i + iZzfk;
                    }
                    break;
                case 57:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzh(t, j);
                        i2 = i + iZzfk;
                    }
                    break;
                case 58:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdqe.zzbj(zzj(t, j));
                        i2 = i + iZzfk;
                    }
                    break;
                case 59:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = ((String) zzdta.zzp(t, j)).hashCode();
                        i2 = i + iZzfk;
                    }
                    break;
                case 60:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdta.zzp(t, j).hashCode();
                        i2 = i + iZzfk;
                    }
                    break;
                case 61:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdta.zzp(t, j).hashCode();
                        i2 = i + iZzfk;
                    }
                    break;
                case 62:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzh(t, j);
                        i2 = i + iZzfk;
                    }
                    break;
                case 63:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzh(t, j);
                        i2 = i + iZzfk;
                    }
                    break;
                case 64:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzh(t, j);
                        i2 = i + iZzfk;
                    }
                    break;
                case 65:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdqe.zzfk(zzi(t, j));
                        i2 = i + iZzfk;
                    }
                    break;
                case 66:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzh(t, j);
                        i2 = i + iZzfk;
                    }
                    break;
                case 67:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdqe.zzfk(zzi(t, j));
                        i2 = i + iZzfk;
                    }
                    break;
                case 68:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzfk = zzdta.zzp(t, j).hashCode();
                        i2 = i + iZzfk;
                    }
                    break;
            }
        }
        int iHashCode2 = (i2 * 53) + this.zzhna.zzay(t).hashCode();
        return this.zzhmr ? (iHashCode2 * 53) + this.zzhnb.zzai(t).hashCode() : iHashCode2;
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final void zzf(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzhmm.length; i += 3) {
            int iZzgv = zzgv(i);
            long j = 1048575 & iZzgv;
            int i2 = this.zzhmm[i];
            switch ((iZzgv & 267386880) >>> 20) {
                case 0:
                    if (zze((Object) t2, i)) {
                        zzdta.zza(t, j, zzdta.zzo(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 1:
                    if (zze((Object) t2, i)) {
                        zzdta.zza((Object) t, j, zzdta.zzn(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 2:
                    if (zze((Object) t2, i)) {
                        zzdta.zza((Object) t, j, zzdta.zzl(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 3:
                    if (zze((Object) t2, i)) {
                        zzdta.zza((Object) t, j, zzdta.zzl(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 4:
                    if (zze((Object) t2, i)) {
                        zzdta.zzb(t, j, zzdta.zzk(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 5:
                    if (zze((Object) t2, i)) {
                        zzdta.zza((Object) t, j, zzdta.zzl(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 6:
                    if (zze((Object) t2, i)) {
                        zzdta.zzb(t, j, zzdta.zzk(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 7:
                    if (zze((Object) t2, i)) {
                        zzdta.zza(t, j, zzdta.zzm(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 8:
                    if (zze((Object) t2, i)) {
                        zzdta.zza(t, j, zzdta.zzp(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zze((Object) t2, i)) {
                        zzdta.zza(t, j, zzdta.zzp(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 11:
                    if (zze((Object) t2, i)) {
                        zzdta.zzb(t, j, zzdta.zzk(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 12:
                    if (zze((Object) t2, i)) {
                        zzdta.zzb(t, j, zzdta.zzk(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 13:
                    if (zze((Object) t2, i)) {
                        zzdta.zzb(t, j, zzdta.zzk(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 14:
                    if (zze((Object) t2, i)) {
                        zzdta.zza((Object) t, j, zzdta.zzl(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 15:
                    if (zze((Object) t2, i)) {
                        zzdta.zzb(t, j, zzdta.zzk(t2, j));
                        zzf((Object) t, i);
                    }
                    break;
                case 16:
                    if (zze((Object) t2, i)) {
                        zzdta.zza((Object) t, j, zzdta.zzl(t2, j));
                        zzf((Object) t, i);
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
                    this.zzhmz.zza(t, t2, j);
                    break;
                case 50:
                    zzdse.zza(this.zzhnc, t, t2, j);
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
                        zzdta.zza(t, j, zzdta.zzp(t2, j));
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
                        zzdta.zza(t, j, zzdta.zzp(t2, j));
                        zzb(t, i2, i);
                    }
                    break;
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (this.zzhmt) {
            return;
        }
        zzdse.zza(this.zzhna, t, t2);
        if (this.zzhmr) {
            zzdse.zza(this.zzhnb, t, t2);
        }
    }

    private final void zza(T t, T t2, int i) {
        long jZzgv = zzgv(i) & 1048575;
        if (zze((Object) t2, i)) {
            Object objZzp = zzdta.zzp(t, jZzgv);
            Object objZzp2 = zzdta.zzp(t2, jZzgv);
            if (objZzp != null && objZzp2 != null) {
                zzdta.zza(t, jZzgv, zzdqe.zzd(objZzp, objZzp2));
                zzf((Object) t, i);
            } else if (objZzp2 != null) {
                zzdta.zza(t, jZzgv, objZzp2);
                zzf((Object) t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int iZzgv = zzgv(i);
        int i2 = this.zzhmm[i];
        long j = iZzgv & 1048575;
        if (zza(t2, i2, i)) {
            Object objZzp = zzdta.zzp(t, j);
            Object objZzp2 = zzdta.zzp(t2, j);
            if (objZzp != null && objZzp2 != null) {
                zzdta.zza(t, j, zzdqe.zzd(objZzp, objZzp2));
                zzb(t, i2, i);
            } else if (objZzp2 != null) {
                zzdta.zza(t, j, objZzp2);
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
    @Override // com.google.android.gms.internal.ads.zzdsc
    public final int zzau(T t) {
        int i;
        int i2;
        long j;
        int iZzj;
        int iZzh;
        int iZzai;
        int iZzv;
        int iZzai2;
        int iZzge;
        int iZzgg;
        int iZzc;
        int iZzai3;
        int iZzge2;
        int iZzgg2;
        int i3 = 267386880;
        int i4 = 1048575;
        int i5 = 1;
        if (this.zzhmt) {
            Unsafe unsafe = zzgpv;
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.zzhmm.length) {
                int iZzgv = zzgv(i6);
                int i8 = (iZzgv & i3) >>> 20;
                int i9 = this.zzhmm[i6];
                long j2 = iZzgv & 1048575;
                int i10 = (i8 < zzdpy.DOUBLE_LIST_PACKED.id() || i8 > zzdpy.SINT64_LIST_PACKED.id()) ? 0 : this.zzhmm[i6 + 2] & 1048575;
                switch (i8) {
                    case 0:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzc(i9, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i7 += iZzc;
                        }
                        break;
                    case 1:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzb(i9, 0.0f);
                            i7 += iZzc;
                        }
                        break;
                    case 2:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzj(i9, zzdta.zzl(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 3:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzk(i9, zzdta.zzl(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 4:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzae(i9, zzdta.zzk(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 5:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzm(i9, 0L);
                            i7 += iZzc;
                        }
                        break;
                    case 6:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzah(i9, 0);
                            i7 += iZzc;
                        }
                        break;
                    case 7:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzi(i9, true);
                            i7 += iZzc;
                        }
                        break;
                    case 8:
                        if (zze((Object) t, i6)) {
                            Object objZzp = zzdta.zzp(t, j2);
                            if (objZzp instanceof zzdot) {
                                iZzc = zzdpm.zzc(i9, (zzdot) objZzp);
                            } else {
                                iZzc = zzdpm.zzh(i9, (String) objZzp);
                            }
                            i7 += iZzc;
                        }
                        break;
                    case 9:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdse.zzc(i9, zzdta.zzp(t, j2), zzgs(i6));
                            i7 += iZzc;
                        }
                        break;
                    case 10:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzc(i9, (zzdot) zzdta.zzp(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 11:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzaf(i9, zzdta.zzk(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 12:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzaj(i9, zzdta.zzk(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 13:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzai(i9, 0);
                            i7 += iZzc;
                        }
                        break;
                    case 14:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzn(i9, 0L);
                            i7 += iZzc;
                        }
                        break;
                    case 15:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzag(i9, zzdta.zzk(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 16:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzl(i9, zzdta.zzl(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 17:
                        if (zze((Object) t, i6)) {
                            iZzc = zzdpm.zzc(i9, (zzdrn) zzdta.zzp(t, j2), zzgs(i6));
                            i7 += iZzc;
                        }
                        break;
                    case 18:
                        iZzc = zzdse.zzw(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 19:
                        iZzc = zzdse.zzv(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 20:
                        iZzc = zzdse.zzo(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 21:
                        iZzc = zzdse.zzp(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 22:
                        iZzc = zzdse.zzs(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 23:
                        iZzc = zzdse.zzw(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 24:
                        iZzc = zzdse.zzv(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 25:
                        iZzc = zzdse.zzx(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 26:
                        iZzc = zzdse.zzc(i9, zze(t, j2));
                        i7 += iZzc;
                        break;
                    case 27:
                        iZzc = zzdse.zzc(i9, zze(t, j2), zzgs(i6));
                        i7 += iZzc;
                        break;
                    case 28:
                        iZzc = zzdse.zzd(i9, zze(t, j2));
                        i7 += iZzc;
                        break;
                    case 29:
                        iZzc = zzdse.zzt(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 30:
                        iZzc = zzdse.zzr(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 31:
                        iZzc = zzdse.zzv(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 32:
                        iZzc = zzdse.zzw(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 33:
                        iZzc = zzdse.zzu(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 34:
                        iZzc = zzdse.zzq(i9, zze(t, j2), false);
                        i7 += iZzc;
                        break;
                    case 35:
                        iZzai3 = zzdse.zzai((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 36:
                        iZzai3 = zzdse.zzah((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 37:
                        iZzai3 = zzdse.zzaa((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 38:
                        iZzai3 = zzdse.zzab((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 39:
                        iZzai3 = zzdse.zzae((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 40:
                        iZzai3 = zzdse.zzai((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 41:
                        iZzai3 = zzdse.zzah((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 42:
                        iZzai3 = zzdse.zzaj((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 43:
                        iZzai3 = zzdse.zzaf((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 44:
                        iZzai3 = zzdse.zzad((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 45:
                        iZzai3 = zzdse.zzah((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 46:
                        iZzai3 = zzdse.zzai((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 47:
                        iZzai3 = zzdse.zzag((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 48:
                        iZzai3 = zzdse.zzac((List) unsafe.getObject(t, j2));
                        if (iZzai3 > 0) {
                            if (this.zzhmu) {
                                unsafe.putInt(t, i10, iZzai3);
                            }
                            iZzge2 = zzdpm.zzge(i9);
                            iZzgg2 = zzdpm.zzgg(iZzai3);
                            iZzc = iZzge2 + iZzgg2 + iZzai3;
                            i7 += iZzc;
                        }
                        break;
                    case 49:
                        iZzc = zzdse.zzd(i9, zze(t, j2), zzgs(i6));
                        i7 += iZzc;
                        break;
                    case 50:
                        iZzc = this.zzhnc.zzb(i9, zzdta.zzp(t, j2), zzgt(i6));
                        i7 += iZzc;
                        break;
                    case 51:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzc(i9, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i7 += iZzc;
                        }
                        break;
                    case 52:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzb(i9, 0.0f);
                            i7 += iZzc;
                        }
                        break;
                    case 53:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzj(i9, zzi(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 54:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzk(i9, zzi(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 55:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzae(i9, zzh(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 56:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzm(i9, 0L);
                            i7 += iZzc;
                        }
                        break;
                    case 57:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzah(i9, 0);
                            i7 += iZzc;
                        }
                        break;
                    case 58:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzi(i9, true);
                            i7 += iZzc;
                        }
                        break;
                    case 59:
                        if (zza(t, i9, i6)) {
                            Object objZzp2 = zzdta.zzp(t, j2);
                            if (objZzp2 instanceof zzdot) {
                                iZzc = zzdpm.zzc(i9, (zzdot) objZzp2);
                            } else {
                                iZzc = zzdpm.zzh(i9, (String) objZzp2);
                            }
                            i7 += iZzc;
                        }
                        break;
                    case 60:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdse.zzc(i9, zzdta.zzp(t, j2), zzgs(i6));
                            i7 += iZzc;
                        }
                        break;
                    case 61:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzc(i9, (zzdot) zzdta.zzp(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 62:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzaf(i9, zzh(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 63:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzaj(i9, zzh(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 64:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzai(i9, 0);
                            i7 += iZzc;
                        }
                        break;
                    case 65:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzn(i9, 0L);
                            i7 += iZzc;
                        }
                        break;
                    case 66:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzag(i9, zzh(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 67:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzl(i9, zzi(t, j2));
                            i7 += iZzc;
                        }
                        break;
                    case 68:
                        if (zza(t, i9, i6)) {
                            iZzc = zzdpm.zzc(i9, (zzdrn) zzdta.zzp(t, j2), zzgs(i6));
                            i7 += iZzc;
                        }
                        break;
                }
                i6 += 3;
                i3 = 267386880;
            }
            return i7 + zza(this.zzhna, t);
        }
        Unsafe unsafe2 = zzgpv;
        int i11 = 0;
        int iZzc2 = 0;
        int i12 = -1;
        int i13 = 0;
        while (i11 < this.zzhmm.length) {
            int iZzgv2 = zzgv(i11);
            int[] iArr = this.zzhmm;
            int i14 = iArr[i11];
            int i15 = (iZzgv2 & 267386880) >>> 20;
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
                i = (!this.zzhmu || i15 < zzdpy.DOUBLE_LIST_PACKED.id() || i15 > zzdpy.SINT64_LIST_PACKED.id()) ? 0 : this.zzhmm[i11 + 2] & i4;
                i2 = 0;
            }
            long j3 = iZzgv2 & i4;
            switch (i15) {
                case 0:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzc2 += zzdpm.zzc(i14, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    }
                    break;
                case 1:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzc2 += zzdpm.zzb(i14, 0.0f);
                    }
                    break;
                case 2:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzj = zzdpm.zzj(i14, unsafe2.getLong(t, j3));
                        iZzc2 += iZzj;
                    }
                    break;
                case 3:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzj = zzdpm.zzk(i14, unsafe2.getLong(t, j3));
                        iZzc2 += iZzj;
                    }
                    break;
                case 4:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzj = zzdpm.zzae(i14, unsafe2.getInt(t, j3));
                        iZzc2 += iZzj;
                    }
                    break;
                case 5:
                    if ((i13 & i2) != 0) {
                        j = 0;
                        iZzj = zzdpm.zzm(i14, 0L);
                        iZzc2 += iZzj;
                    } else {
                        j = 0;
                    }
                    break;
                case 6:
                    if ((i13 & i2) != 0) {
                        iZzc2 += zzdpm.zzah(i14, 0);
                    }
                    j = 0;
                    break;
                case 7:
                    if ((i13 & i2) != 0) {
                        iZzc2 += zzdpm.zzi(i14, true);
                    }
                    j = 0;
                    break;
                case 8:
                    if ((i13 & i2) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzdot) {
                            iZzh = zzdpm.zzc(i14, (zzdot) object);
                        } else {
                            iZzh = zzdpm.zzh(i14, (String) object);
                        }
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 9:
                    if ((i13 & i2) != 0) {
                        iZzh = zzdse.zzc(i14, unsafe2.getObject(t, j3), zzgs(i11));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 10:
                    if ((i13 & i2) != 0) {
                        iZzh = zzdpm.zzc(i14, (zzdot) unsafe2.getObject(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 11:
                    if ((i13 & i2) != 0) {
                        iZzh = zzdpm.zzaf(i14, unsafe2.getInt(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 12:
                    if ((i13 & i2) != 0) {
                        iZzh = zzdpm.zzaj(i14, unsafe2.getInt(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 13:
                    if ((i13 & i2) != 0) {
                        iZzai = zzdpm.zzai(i14, 0);
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 14:
                    if ((i13 & i2) != 0) {
                        iZzh = zzdpm.zzn(i14, 0L);
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 15:
                    if ((i13 & i2) != 0) {
                        iZzh = zzdpm.zzag(i14, unsafe2.getInt(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 16:
                    if ((i13 & i2) != 0) {
                        iZzh = zzdpm.zzl(i14, unsafe2.getLong(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 17:
                    if ((i13 & i2) != 0) {
                        iZzh = zzdpm.zzc(i14, (zzdrn) unsafe2.getObject(t, j3), zzgs(i11));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 18:
                    iZzh = zzdse.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzh;
                    j = 0;
                    break;
                case 19:
                    iZzv = zzdse.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 20:
                    iZzv = zzdse.zzo(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 21:
                    iZzv = zzdse.zzp(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 22:
                    iZzv = zzdse.zzs(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 23:
                    iZzv = zzdse.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 24:
                    iZzv = zzdse.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 25:
                    iZzv = zzdse.zzx(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 26:
                    iZzh = zzdse.zzc(i14, (List) unsafe2.getObject(t, j3));
                    iZzc2 += iZzh;
                    j = 0;
                    break;
                case 27:
                    iZzh = zzdse.zzc(i14, (List<?>) unsafe2.getObject(t, j3), zzgs(i11));
                    iZzc2 += iZzh;
                    j = 0;
                    break;
                case 28:
                    iZzh = zzdse.zzd(i14, (List) unsafe2.getObject(t, j3));
                    iZzc2 += iZzh;
                    j = 0;
                    break;
                case 29:
                    iZzh = zzdse.zzt(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzh;
                    j = 0;
                    break;
                case 30:
                    iZzv = zzdse.zzr(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 31:
                    iZzv = zzdse.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 32:
                    iZzv = zzdse.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 33:
                    iZzv = zzdse.zzu(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 34:
                    iZzv = zzdse.zzq(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzc2 += iZzv;
                    j = 0;
                    break;
                case 35:
                    iZzai2 = zzdse.zzai((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 36:
                    iZzai2 = zzdse.zzah((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 37:
                    iZzai2 = zzdse.zzaa((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 38:
                    iZzai2 = zzdse.zzab((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 39:
                    iZzai2 = zzdse.zzae((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 40:
                    iZzai2 = zzdse.zzai((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 41:
                    iZzai2 = zzdse.zzah((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 42:
                    iZzai2 = zzdse.zzaj((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 43:
                    iZzai2 = zzdse.zzaf((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 44:
                    iZzai2 = zzdse.zzad((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 45:
                    iZzai2 = zzdse.zzah((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 46:
                    iZzai2 = zzdse.zzai((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 47:
                    iZzai2 = zzdse.zzag((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 48:
                    iZzai2 = zzdse.zzac((List) unsafe2.getObject(t, j3));
                    if (iZzai2 > 0) {
                        if (this.zzhmu) {
                            unsafe2.putInt(t, i, iZzai2);
                        }
                        iZzge = zzdpm.zzge(i14);
                        iZzgg = zzdpm.zzgg(iZzai2);
                        iZzai = iZzge + iZzgg + iZzai2;
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 49:
                    iZzh = zzdse.zzd(i14, (List) unsafe2.getObject(t, j3), zzgs(i11));
                    iZzc2 += iZzh;
                    j = 0;
                    break;
                case 50:
                    iZzh = this.zzhnc.zzb(i14, unsafe2.getObject(t, j3), zzgt(i11));
                    iZzc2 += iZzh;
                    j = 0;
                    break;
                case 51:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzc(i14, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 52:
                    if (zza(t, i14, i11)) {
                        iZzai = zzdpm.zzb(i14, 0.0f);
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 53:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzj(i14, zzi(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 54:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzk(i14, zzi(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 55:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzae(i14, zzh(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 56:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzm(i14, 0L);
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 57:
                    if (zza(t, i14, i11)) {
                        iZzai = zzdpm.zzah(i14, 0);
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 58:
                    if (zza(t, i14, i11)) {
                        iZzai = zzdpm.zzi(i14, true);
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 59:
                    if (zza(t, i14, i11)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzdot) {
                            iZzh = zzdpm.zzc(i14, (zzdot) object2);
                        } else {
                            iZzh = zzdpm.zzh(i14, (String) object2);
                        }
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 60:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdse.zzc(i14, unsafe2.getObject(t, j3), zzgs(i11));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 61:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzc(i14, (zzdot) unsafe2.getObject(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 62:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzaf(i14, zzh(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 63:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzaj(i14, zzh(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 64:
                    if (zza(t, i14, i11)) {
                        iZzai = zzdpm.zzai(i14, 0);
                        iZzc2 += iZzai;
                    }
                    j = 0;
                    break;
                case 65:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzn(i14, 0L);
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 66:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzag(i14, zzh(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 67:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzl(i14, zzi(t, j3));
                        iZzc2 += iZzh;
                    }
                    j = 0;
                    break;
                case 68:
                    if (zza(t, i14, i11)) {
                        iZzh = zzdpm.zzc(i14, (zzdrn) unsafe2.getObject(t, j3), zzgs(i11));
                        iZzc2 += iZzh;
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
        int iZza = iZzc2 + zza(this.zzhna, t);
        if (!this.zzhmr) {
            return iZza;
        }
        zzdpt<T> zzdptVarZzai = this.zzhnb.zzai(t);
        int iZzb = 0;
        for (int i17 = 0; i17 < zzdptVarZzai.zzhhb.zzbbj(); i17++) {
            Map.Entry entryZzha = zzdptVarZzai.zzhhb.zzha(i17);
            iZzb += zzdpt.zzb((zzdpv<?>) entryZzha.getKey(), entryZzha.getValue());
        }
        for (Map.Entry entry : zzdptVarZzai.zzhhb.zzbbk()) {
            iZzb += zzdpt.zzb((zzdpv<?>) entry.getKey(), entry.getValue());
        }
        return iZza + iZzb;
    }

    private static <UT, UB> int zza(zzdsu<UT, UB> zzdsuVar, T t) {
        return zzdsuVar.zzau(zzdsuVar.zzay(t));
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzdta.zzp(obj, j);
    }

    /* JADX WARN: Code duplicated, block: B:178:0x054a  */
    /* JADX WARN: Code duplicated, block: B:9:0x0032  */
    @Override // com.google.android.gms.internal.ads.zzdsc
    public final void zza(T t, zzdtr zzdtrVar) throws IOException {
        Iterator it;
        Map.Entry<?, ?> entry;
        Iterator itDescendingIterator;
        Map.Entry<?, ?> entry2;
        if (zzdtrVar.zzayt() == zzdqd.zzd.zzhkt) {
            zza(this.zzhna, t, zzdtrVar);
            if (this.zzhmr) {
                zzdpt<T> zzdptVarZzai = this.zzhnb.zzai(t);
                if (zzdptVarZzai.zzhhb.isEmpty()) {
                    itDescendingIterator = null;
                    entry2 = null;
                } else {
                    itDescendingIterator = zzdptVarZzai.descendingIterator();
                    entry2 = (Map.Entry) itDescendingIterator.next();
                }
            } else {
                itDescendingIterator = null;
                entry2 = null;
            }
            for (int length = this.zzhmm.length - 3; length >= 0; length -= 3) {
                int iZzgv = zzgv(length);
                int i = this.zzhmm[length];
                while (entry2 != null && this.zzhnb.zza(entry2) > i) {
                    this.zzhnb.zza(zzdtrVar, entry2);
                    entry2 = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
                }
                switch ((iZzgv & 267386880) >>> 20) {
                    case 0:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzb(i, zzdta.zzo(t, iZzgv & 1048575));
                        }
                        break;
                    case 1:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zza(i, zzdta.zzn(t, iZzgv & 1048575));
                        }
                        break;
                    case 2:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzo(i, zzdta.zzl(t, iZzgv & 1048575));
                        }
                        break;
                    case 3:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzg(i, zzdta.zzl(t, iZzgv & 1048575));
                        }
                        break;
                    case 4:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzaa(i, zzdta.zzk(t, iZzgv & 1048575));
                        }
                        break;
                    case 5:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzi(i, zzdta.zzl(t, iZzgv & 1048575));
                        }
                        break;
                    case 6:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzad(i, zzdta.zzk(t, iZzgv & 1048575));
                        }
                        break;
                    case 7:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzh(i, zzdta.zzm(t, iZzgv & 1048575));
                        }
                        break;
                    case 8:
                        if (zze((Object) t, length)) {
                            zza(i, zzdta.zzp(t, iZzgv & 1048575), zzdtrVar);
                        }
                        break;
                    case 9:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zza(i, zzdta.zzp(t, iZzgv & 1048575), zzgs(length));
                        }
                        break;
                    case 10:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zza(i, (zzdot) zzdta.zzp(t, iZzgv & 1048575));
                        }
                        break;
                    case 11:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzab(i, zzdta.zzk(t, iZzgv & 1048575));
                        }
                        break;
                    case 12:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzal(i, zzdta.zzk(t, iZzgv & 1048575));
                        }
                        break;
                    case 13:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzak(i, zzdta.zzk(t, iZzgv & 1048575));
                        }
                        break;
                    case 14:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzp(i, zzdta.zzl(t, iZzgv & 1048575));
                        }
                        break;
                    case 15:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzac(i, zzdta.zzk(t, iZzgv & 1048575));
                        }
                        break;
                    case 16:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzh(i, zzdta.zzl(t, iZzgv & 1048575));
                        }
                        break;
                    case 17:
                        if (zze((Object) t, length)) {
                            zzdtrVar.zzb(i, zzdta.zzp(t, iZzgv & 1048575), zzgs(length));
                        }
                        break;
                    case 18:
                        zzdse.zza(this.zzhmm[length], (List<Double>) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 19:
                        zzdse.zzb(this.zzhmm[length], (List<Float>) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 20:
                        zzdse.zzc(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 21:
                        zzdse.zzd(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 22:
                        zzdse.zzh(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 23:
                        zzdse.zzf(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 24:
                        zzdse.zzk(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 25:
                        zzdse.zzn(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 26:
                        zzdse.zza(this.zzhmm[length], (List<String>) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar);
                        break;
                    case 27:
                        zzdse.zza(this.zzhmm[length], (List<?>) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, zzgs(length));
                        break;
                    case 28:
                        zzdse.zzb(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar);
                        break;
                    case 29:
                        zzdse.zzi(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 30:
                        zzdse.zzm(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 31:
                        zzdse.zzl(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 32:
                        zzdse.zzg(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 33:
                        zzdse.zzj(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 34:
                        zzdse.zze(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, false);
                        break;
                    case 35:
                        zzdse.zza(this.zzhmm[length], (List<Double>) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 36:
                        zzdse.zzb(this.zzhmm[length], (List<Float>) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 37:
                        zzdse.zzc(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 38:
                        zzdse.zzd(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 39:
                        zzdse.zzh(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 40:
                        zzdse.zzf(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 41:
                        zzdse.zzk(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 42:
                        zzdse.zzn(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 43:
                        zzdse.zzi(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 44:
                        zzdse.zzm(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 45:
                        zzdse.zzl(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 46:
                        zzdse.zzg(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 47:
                        zzdse.zzj(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 48:
                        zzdse.zze(this.zzhmm[length], (List) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, true);
                        break;
                    case 49:
                        zzdse.zzb(this.zzhmm[length], (List<?>) zzdta.zzp(t, iZzgv & 1048575), zzdtrVar, zzgs(length));
                        break;
                    case 50:
                        zza(zzdtrVar, i, zzdta.zzp(t, iZzgv & 1048575), length);
                        break;
                    case 51:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzb(i, zzf(t, iZzgv & 1048575));
                        }
                        break;
                    case 52:
                        if (zza(t, i, length)) {
                            zzdtrVar.zza(i, zzg(t, iZzgv & 1048575));
                        }
                        break;
                    case 53:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzo(i, zzi(t, iZzgv & 1048575));
                        }
                        break;
                    case 54:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzg(i, zzi(t, iZzgv & 1048575));
                        }
                        break;
                    case 55:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzaa(i, zzh(t, iZzgv & 1048575));
                        }
                        break;
                    case 56:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzi(i, zzi(t, iZzgv & 1048575));
                        }
                        break;
                    case 57:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzad(i, zzh(t, iZzgv & 1048575));
                        }
                        break;
                    case 58:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzh(i, zzj(t, iZzgv & 1048575));
                        }
                        break;
                    case 59:
                        if (zza(t, i, length)) {
                            zza(i, zzdta.zzp(t, iZzgv & 1048575), zzdtrVar);
                        }
                        break;
                    case 60:
                        if (zza(t, i, length)) {
                            zzdtrVar.zza(i, zzdta.zzp(t, iZzgv & 1048575), zzgs(length));
                        }
                        break;
                    case 61:
                        if (zza(t, i, length)) {
                            zzdtrVar.zza(i, (zzdot) zzdta.zzp(t, iZzgv & 1048575));
                        }
                        break;
                    case 62:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzab(i, zzh(t, iZzgv & 1048575));
                        }
                        break;
                    case 63:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzal(i, zzh(t, iZzgv & 1048575));
                        }
                        break;
                    case 64:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzak(i, zzh(t, iZzgv & 1048575));
                        }
                        break;
                    case 65:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzp(i, zzi(t, iZzgv & 1048575));
                        }
                        break;
                    case 66:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzac(i, zzh(t, iZzgv & 1048575));
                        }
                        break;
                    case 67:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzh(i, zzi(t, iZzgv & 1048575));
                        }
                        break;
                    case 68:
                        if (zza(t, i, length)) {
                            zzdtrVar.zzb(i, zzdta.zzp(t, iZzgv & 1048575), zzgs(length));
                        }
                        break;
                }
            }
            while (entry2 != null) {
                this.zzhnb.zza(zzdtrVar, entry2);
                entry2 = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
            }
            return;
        }
        if (this.zzhmt) {
            if (this.zzhmr) {
                zzdpt<T> zzdptVarZzai2 = this.zzhnb.zzai(t);
                if (zzdptVarZzai2.zzhhb.isEmpty()) {
                    it = null;
                    entry = null;
                } else {
                    it = zzdptVarZzai2.iterator();
                    entry = (Map.Entry) it.next();
                }
            } else {
                it = null;
                entry = null;
            }
            int length2 = this.zzhmm.length;
            Map.Entry<?, ?> entry3 = entry;
            for (int i2 = 0; i2 < length2; i2 += 3) {
                int iZzgv2 = zzgv(i2);
                int i3 = this.zzhmm[i2];
                while (entry3 != null && this.zzhnb.zza(entry3) <= i3) {
                    this.zzhnb.zza(zzdtrVar, entry3);
                    entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                switch ((iZzgv2 & 267386880) >>> 20) {
                    case 0:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzb(i3, zzdta.zzo(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 1:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zza(i3, zzdta.zzn(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 2:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzo(i3, zzdta.zzl(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 3:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzg(i3, zzdta.zzl(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 4:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzaa(i3, zzdta.zzk(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 5:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzi(i3, zzdta.zzl(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 6:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzad(i3, zzdta.zzk(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 7:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzh(i3, zzdta.zzm(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 8:
                        if (zze((Object) t, i2)) {
                            zza(i3, zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar);
                        }
                        break;
                    case 9:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zza(i3, zzdta.zzp(t, iZzgv2 & 1048575), zzgs(i2));
                        }
                        break;
                    case 10:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zza(i3, (zzdot) zzdta.zzp(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 11:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzab(i3, zzdta.zzk(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 12:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzal(i3, zzdta.zzk(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 13:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzak(i3, zzdta.zzk(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 14:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzp(i3, zzdta.zzl(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 15:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzac(i3, zzdta.zzk(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 16:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzh(i3, zzdta.zzl(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 17:
                        if (zze((Object) t, i2)) {
                            zzdtrVar.zzb(i3, zzdta.zzp(t, iZzgv2 & 1048575), zzgs(i2));
                        }
                        break;
                    case 18:
                        zzdse.zza(this.zzhmm[i2], (List<Double>) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 19:
                        zzdse.zzb(this.zzhmm[i2], (List<Float>) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 20:
                        zzdse.zzc(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 21:
                        zzdse.zzd(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 22:
                        zzdse.zzh(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 23:
                        zzdse.zzf(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 24:
                        zzdse.zzk(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 25:
                        zzdse.zzn(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 26:
                        zzdse.zza(this.zzhmm[i2], (List<String>) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar);
                        break;
                    case 27:
                        zzdse.zza(this.zzhmm[i2], (List<?>) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, zzgs(i2));
                        break;
                    case 28:
                        zzdse.zzb(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar);
                        break;
                    case 29:
                        zzdse.zzi(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 30:
                        zzdse.zzm(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 31:
                        zzdse.zzl(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 32:
                        zzdse.zzg(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 33:
                        zzdse.zzj(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 34:
                        zzdse.zze(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, false);
                        break;
                    case 35:
                        zzdse.zza(this.zzhmm[i2], (List<Double>) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 36:
                        zzdse.zzb(this.zzhmm[i2], (List<Float>) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 37:
                        zzdse.zzc(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 38:
                        zzdse.zzd(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 39:
                        zzdse.zzh(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 40:
                        zzdse.zzf(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 41:
                        zzdse.zzk(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 42:
                        zzdse.zzn(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 43:
                        zzdse.zzi(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 44:
                        zzdse.zzm(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 45:
                        zzdse.zzl(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 46:
                        zzdse.zzg(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 47:
                        zzdse.zzj(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 48:
                        zzdse.zze(this.zzhmm[i2], (List) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, true);
                        break;
                    case 49:
                        zzdse.zzb(this.zzhmm[i2], (List<?>) zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar, zzgs(i2));
                        break;
                    case 50:
                        zza(zzdtrVar, i3, zzdta.zzp(t, iZzgv2 & 1048575), i2);
                        break;
                    case 51:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzb(i3, zzf(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 52:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zza(i3, zzg(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 53:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzo(i3, zzi(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 54:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzg(i3, zzi(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 55:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzaa(i3, zzh(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 56:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzi(i3, zzi(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 57:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzad(i3, zzh(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 58:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzh(i3, zzj(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 59:
                        if (zza(t, i3, i2)) {
                            zza(i3, zzdta.zzp(t, iZzgv2 & 1048575), zzdtrVar);
                        }
                        break;
                    case 60:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zza(i3, zzdta.zzp(t, iZzgv2 & 1048575), zzgs(i2));
                        }
                        break;
                    case 61:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zza(i3, (zzdot) zzdta.zzp(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 62:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzab(i3, zzh(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 63:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzal(i3, zzh(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 64:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzak(i3, zzh(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 65:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzp(i3, zzi(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 66:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzac(i3, zzh(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 67:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzh(i3, zzi(t, iZzgv2 & 1048575));
                        }
                        break;
                    case 68:
                        if (zza(t, i3, i2)) {
                            zzdtrVar.zzb(i3, zzdta.zzp(t, iZzgv2 & 1048575), zzgs(i2));
                        }
                        break;
                }
            }
            while (entry3 != null) {
                this.zzhnb.zza(zzdtrVar, entry3);
                entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            zza(this.zzhna, t, zzdtrVar);
            return;
        }
        zzb(t, zzdtrVar);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0023  */
    private final void zzb(T t, zzdtr zzdtrVar) throws IOException {
        Iterator it;
        Map.Entry<?, ?> entry;
        int i;
        if (this.zzhmr) {
            zzdpt<T> zzdptVarZzai = this.zzhnb.zzai(t);
            if (zzdptVarZzai.zzhhb.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = zzdptVarZzai.iterator();
                entry = (Map.Entry) it.next();
            }
        } else {
            it = null;
            entry = null;
        }
        int i2 = -1;
        int length = this.zzhmm.length;
        Unsafe unsafe = zzgpv;
        Map.Entry<?, ?> entry2 = entry;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int iZzgv = zzgv(i4);
            int[] iArr = this.zzhmm;
            int i5 = iArr[i4];
            int i6 = (267386880 & iZzgv) >>> 20;
            if (this.zzhmt || i6 > 17) {
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
            while (entry2 != null && this.zzhnb.zza(entry2) <= i5) {
                this.zzhnb.zza(zzdtrVar, entry2);
                entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            long j = iZzgv & 1048575;
            switch (i6) {
                case 0:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzb(i5, zzdta.zzo(t, j));
                        continue;
                    }
                    break;
                case 1:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zza(i5, zzdta.zzn(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzo(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 3:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzg(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 4:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzaa(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 5:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzi(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 6:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzad(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 7:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzh(i5, zzdta.zzm(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 8:
                    if ((i3 & i) != 0) {
                        zza(i5, unsafe.getObject(t, j), zzdtrVar);
                    } else {
                        continue;
                    }
                    break;
                case 9:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zza(i5, unsafe.getObject(t, j), zzgs(i4));
                    } else {
                        continue;
                    }
                    break;
                case 10:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zza(i5, (zzdot) unsafe.getObject(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 11:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzab(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 12:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzal(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 13:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzak(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 14:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzp(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 15:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzac(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 16:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzh(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 17:
                    if ((i3 & i) != 0) {
                        zzdtrVar.zzb(i5, unsafe.getObject(t, j), zzgs(i4));
                    } else {
                        continue;
                    }
                    break;
                case 18:
                    zzdse.zza(this.zzhmm[i4], (List<Double>) unsafe.getObject(t, j), zzdtrVar, false);
                    continue;
                    break;
                case 19:
                    zzdse.zzb(this.zzhmm[i4], (List<Float>) unsafe.getObject(t, j), zzdtrVar, false);
                    continue;
                    break;
                case 20:
                    zzdse.zzc(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    continue;
                    break;
                case 21:
                    zzdse.zzd(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    continue;
                    break;
                case 22:
                    zzdse.zzh(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    continue;
                    break;
                case 23:
                    zzdse.zzf(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    continue;
                    break;
                case 24:
                    zzdse.zzk(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    continue;
                    break;
                case 25:
                    zzdse.zzn(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    continue;
                    break;
                case 26:
                    zzdse.zza(this.zzhmm[i4], (List<String>) unsafe.getObject(t, j), zzdtrVar);
                    break;
                case 27:
                    zzdse.zza(this.zzhmm[i4], (List<?>) unsafe.getObject(t, j), zzdtrVar, zzgs(i4));
                    break;
                case 28:
                    zzdse.zzb(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar);
                    break;
                case 29:
                    zzdse.zzi(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    break;
                case 30:
                    zzdse.zzm(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    break;
                case 31:
                    zzdse.zzl(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    break;
                case 32:
                    zzdse.zzg(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    break;
                case 33:
                    zzdse.zzj(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    break;
                case 34:
                    zzdse.zze(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, false);
                    break;
                case 35:
                    zzdse.zza(this.zzhmm[i4], (List<Double>) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 36:
                    zzdse.zzb(this.zzhmm[i4], (List<Float>) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 37:
                    zzdse.zzc(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 38:
                    zzdse.zzd(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 39:
                    zzdse.zzh(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 40:
                    zzdse.zzf(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 41:
                    zzdse.zzk(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 42:
                    zzdse.zzn(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 43:
                    zzdse.zzi(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 44:
                    zzdse.zzm(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 45:
                    zzdse.zzl(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 46:
                    zzdse.zzg(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 47:
                    zzdse.zzj(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 48:
                    zzdse.zze(this.zzhmm[i4], (List) unsafe.getObject(t, j), zzdtrVar, true);
                    break;
                case 49:
                    zzdse.zzb(this.zzhmm[i4], (List<?>) unsafe.getObject(t, j), zzdtrVar, zzgs(i4));
                    break;
                case 50:
                    zza(zzdtrVar, i5, unsafe.getObject(t, j), i4);
                    break;
                case 51:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzb(i5, zzf(t, j));
                    }
                    break;
                case 52:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zza(i5, zzg(t, j));
                    }
                    break;
                case 53:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzo(i5, zzi(t, j));
                    }
                    break;
                case 54:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzg(i5, zzi(t, j));
                    }
                    break;
                case 55:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzaa(i5, zzh(t, j));
                    }
                    break;
                case 56:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzi(i5, zzi(t, j));
                    }
                    break;
                case 57:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzad(i5, zzh(t, j));
                    }
                    break;
                case 58:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzh(i5, zzj(t, j));
                    }
                    break;
                case 59:
                    if (zza(t, i5, i4)) {
                        zza(i5, unsafe.getObject(t, j), zzdtrVar);
                    }
                    break;
                case 60:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zza(i5, unsafe.getObject(t, j), zzgs(i4));
                    }
                    break;
                case 61:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zza(i5, (zzdot) unsafe.getObject(t, j));
                    }
                    break;
                case 62:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzab(i5, zzh(t, j));
                    }
                    break;
                case 63:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzal(i5, zzh(t, j));
                    }
                    break;
                case 64:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzak(i5, zzh(t, j));
                    }
                    break;
                case 65:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzp(i5, zzi(t, j));
                    }
                    break;
                case 66:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzac(i5, zzh(t, j));
                    }
                    break;
                case 67:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzh(i5, zzi(t, j));
                    }
                    break;
                case 68:
                    if (zza(t, i5, i4)) {
                        zzdtrVar.zzb(i5, unsafe.getObject(t, j), zzgs(i4));
                    }
                    break;
            }
        }
        Map.Entry<?, ?> entry3 = entry2;
        while (entry3 != null) {
            this.zzhnb.zza(zzdtrVar, entry3);
            entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        zza(this.zzhna, t, zzdtrVar);
    }

    private final <K, V> void zza(zzdtr zzdtrVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzdtrVar.zza(i, this.zzhnc.zzas(zzgt(i2)), this.zzhnc.zzao(obj));
        }
    }

    private static <UT, UB> void zza(zzdsu<UT, UB> zzdsuVar, T t, zzdtr zzdtrVar) throws IOException {
        zzdsuVar.zza(zzdsuVar.zzay(t), zzdtrVar);
    }

    /* JADX WARN: Code duplicated, block: B:165:0x05cb A[LOOP:5: B:163:0x05c7->B:165:0x05cb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:167:0x05d8  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.internal.ads.zzdsc
    public final void zza(T t, zzdsd zzdsdVar, zzdpq zzdpqVar) throws IOException {
        int i;
        if (zzdpqVar == null) {
            throw new NullPointerException();
        }
        zzdsu<?, ?> zzdsuVar = this.zzhna;
        zzdps<?> zzdpsVar = this.zzhnb;
        zzdpt zzdptVarZzaj = null;
        Object objZza = null;
        while (true) {
            try {
                int iZzayn = zzdsdVar.zzayn();
                int iZzgy = zzgy(iZzayn);
                if (iZzgy >= 0) {
                    int iZzgv = zzgv(iZzgy);
                    switch ((267386880 & iZzgv) >>> 20) {
                        case 0:
                            zzdta.zza(t, iZzgv & 1048575, zzdsdVar.readDouble());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 1:
                            zzdta.zza((Object) t, iZzgv & 1048575, zzdsdVar.readFloat());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 2:
                            zzdta.zza((Object) t, iZzgv & 1048575, zzdsdVar.zzaxr());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 3:
                            zzdta.zza((Object) t, iZzgv & 1048575, zzdsdVar.zzaxq());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 4:
                            zzdta.zzb(t, iZzgv & 1048575, zzdsdVar.zzaxs());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 5:
                            zzdta.zza((Object) t, iZzgv & 1048575, zzdsdVar.zzaxt());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 6:
                            zzdta.zzb(t, iZzgv & 1048575, zzdsdVar.zzaxu());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 7:
                            zzdta.zza(t, iZzgv & 1048575, zzdsdVar.zzaxv());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 8:
                            zza(t, iZzgv, zzdsdVar);
                            zzf((Object) t, iZzgy);
                            continue;
                        case 9:
                            if (zze((Object) t, iZzgy)) {
                                long j = iZzgv & 1048575;
                                zzdta.zza(t, j, zzdqe.zzd(zzdta.zzp(t, j), zzdsdVar.zza(zzgs(iZzgy), zzdpqVar)));
                            } else {
                                zzdta.zza(t, iZzgv & 1048575, zzdsdVar.zza(zzgs(iZzgy), zzdpqVar));
                                zzf((Object) t, iZzgy);
                                continue;
                            }
                            break;
                        case 10:
                            zzdta.zza(t, iZzgv & 1048575, zzdsdVar.zzaxx());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 11:
                            zzdta.zzb(t, iZzgv & 1048575, zzdsdVar.zzaxy());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 12:
                            int iZzaxz = zzdsdVar.zzaxz();
                            zzdqj zzdqjVarZzgu = zzgu(iZzgy);
                            if (zzdqjVarZzgu == null || zzdqjVarZzgu.zzf(iZzaxz)) {
                                zzdta.zzb(t, iZzgv & 1048575, iZzaxz);
                                zzf((Object) t, iZzgy);
                                continue;
                            } else {
                                objZza = zzdse.zza(iZzayn, iZzaxz, objZza, (zzdsu<UT, Object>) zzdsuVar);
                            }
                            break;
                        case 13:
                            zzdta.zzb(t, iZzgv & 1048575, zzdsdVar.zzaya());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 14:
                            zzdta.zza((Object) t, iZzgv & 1048575, zzdsdVar.zzayb());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 15:
                            zzdta.zzb(t, iZzgv & 1048575, zzdsdVar.zzayc());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 16:
                            zzdta.zza((Object) t, iZzgv & 1048575, zzdsdVar.zzayd());
                            zzf((Object) t, iZzgy);
                            continue;
                        case 17:
                            if (zze((Object) t, iZzgy)) {
                                long j2 = iZzgv & 1048575;
                                zzdta.zza(t, j2, zzdqe.zzd(zzdta.zzp(t, j2), zzdsdVar.zzb(zzgs(iZzgy), zzdpqVar)));
                            } else {
                                zzdta.zza(t, iZzgv & 1048575, zzdsdVar.zzb(zzgs(iZzgy), zzdpqVar));
                                zzf((Object) t, iZzgy);
                                continue;
                            }
                            break;
                        case 18:
                            zzdsdVar.zzk(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 19:
                            zzdsdVar.zzl(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 20:
                            zzdsdVar.zzn(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 21:
                            zzdsdVar.zzm(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 22:
                            zzdsdVar.zzo(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 23:
                            zzdsdVar.zzp(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 24:
                            zzdsdVar.zzq(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 25:
                            zzdsdVar.zzr(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 26:
                            if (zzgx(iZzgv)) {
                                zzdsdVar.zzs(this.zzhmz.zza(t, iZzgv & 1048575));
                            } else {
                                zzdsdVar.readStringList(this.zzhmz.zza(t, iZzgv & 1048575));
                                continue;
                            }
                            break;
                        case 27:
                            zzdsdVar.zza(this.zzhmz.zza(t, iZzgv & 1048575), zzgs(iZzgy), zzdpqVar);
                            continue;
                        case 28:
                            zzdsdVar.zzt(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 29:
                            zzdsdVar.zzu(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 30:
                            List<Integer> listZza = this.zzhmz.zza(t, iZzgv & 1048575);
                            zzdsdVar.zzv(listZza);
                            objZza = zzdse.zza(iZzayn, listZza, zzgu(iZzgy), objZza, zzdsuVar);
                            continue;
                        case 31:
                            zzdsdVar.zzw(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 32:
                            zzdsdVar.zzx(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 33:
                            zzdsdVar.zzy(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 34:
                            zzdsdVar.zzz(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 35:
                            zzdsdVar.zzk(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 36:
                            zzdsdVar.zzl(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 37:
                            zzdsdVar.zzn(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 38:
                            zzdsdVar.zzm(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 39:
                            zzdsdVar.zzo(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 40:
                            zzdsdVar.zzp(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 41:
                            zzdsdVar.zzq(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 42:
                            zzdsdVar.zzr(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 43:
                            zzdsdVar.zzu(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 44:
                            List<Integer> listZza2 = this.zzhmz.zza(t, iZzgv & 1048575);
                            zzdsdVar.zzv(listZza2);
                            objZza = zzdse.zza(iZzayn, listZza2, zzgu(iZzgy), objZza, zzdsuVar);
                            continue;
                        case 45:
                            zzdsdVar.zzw(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 46:
                            zzdsdVar.zzx(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 47:
                            zzdsdVar.zzy(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 48:
                            zzdsdVar.zzz(this.zzhmz.zza(t, iZzgv & 1048575));
                            continue;
                        case 49:
                            zzdsdVar.zzb(this.zzhmz.zza(t, iZzgv & 1048575), zzgs(iZzgy), zzdpqVar);
                            continue;
                        case 50:
                            Object objZzgt = zzgt(iZzgy);
                            long jZzgv = zzgv(iZzgy) & 1048575;
                            Object objZzp = zzdta.zzp(t, jZzgv);
                            if (objZzp == null) {
                                objZzp = this.zzhnc.zzar(objZzgt);
                                zzdta.zza(t, jZzgv, objZzp);
                            } else if (this.zzhnc.zzap(objZzp)) {
                                Object objZzar = this.zzhnc.zzar(objZzgt);
                                this.zzhnc.zze(objZzar, objZzp);
                                zzdta.zza(t, jZzgv, objZzar);
                                objZzp = objZzar;
                            }
                            zzdsdVar.zza(this.zzhnc.zzan(objZzp), this.zzhnc.zzas(objZzgt), zzdpqVar);
                            continue;
                        case 51:
                            zzdta.zza(t, iZzgv & 1048575, Double.valueOf(zzdsdVar.readDouble()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 52:
                            zzdta.zza(t, iZzgv & 1048575, Float.valueOf(zzdsdVar.readFloat()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 53:
                            zzdta.zza(t, iZzgv & 1048575, Long.valueOf(zzdsdVar.zzaxr()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 54:
                            zzdta.zza(t, iZzgv & 1048575, Long.valueOf(zzdsdVar.zzaxq()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 55:
                            zzdta.zza(t, iZzgv & 1048575, Integer.valueOf(zzdsdVar.zzaxs()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 56:
                            zzdta.zza(t, iZzgv & 1048575, Long.valueOf(zzdsdVar.zzaxt()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 57:
                            zzdta.zza(t, iZzgv & 1048575, Integer.valueOf(zzdsdVar.zzaxu()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 58:
                            zzdta.zza(t, iZzgv & 1048575, Boolean.valueOf(zzdsdVar.zzaxv()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 59:
                            zza(t, iZzgv, zzdsdVar);
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 60:
                            if (zza(t, iZzayn, iZzgy)) {
                                long j3 = iZzgv & 1048575;
                                zzdta.zza(t, j3, zzdqe.zzd(zzdta.zzp(t, j3), zzdsdVar.zza(zzgs(iZzgy), zzdpqVar)));
                            } else {
                                zzdta.zza(t, iZzgv & 1048575, zzdsdVar.zza(zzgs(iZzgy), zzdpqVar));
                                zzf((Object) t, iZzgy);
                            }
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 61:
                            zzdta.zza(t, iZzgv & 1048575, zzdsdVar.zzaxx());
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 62:
                            zzdta.zza(t, iZzgv & 1048575, Integer.valueOf(zzdsdVar.zzaxy()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 63:
                            int iZzaxz2 = zzdsdVar.zzaxz();
                            zzdqj zzdqjVarZzgu2 = zzgu(iZzgy);
                            if (zzdqjVarZzgu2 == null || zzdqjVarZzgu2.zzf(iZzaxz2)) {
                                zzdta.zza(t, iZzgv & 1048575, Integer.valueOf(iZzaxz2));
                                zzb(t, iZzayn, iZzgy);
                                continue;
                            } else {
                                objZza = zzdse.zza(iZzayn, iZzaxz2, objZza, (zzdsu<UT, Object>) zzdsuVar);
                            }
                            break;
                        case 64:
                            zzdta.zza(t, iZzgv & 1048575, Integer.valueOf(zzdsdVar.zzaya()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 65:
                            zzdta.zza(t, iZzgv & 1048575, Long.valueOf(zzdsdVar.zzayb()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 66:
                            zzdta.zza(t, iZzgv & 1048575, Integer.valueOf(zzdsdVar.zzayc()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 67:
                            zzdta.zza(t, iZzgv & 1048575, Long.valueOf(zzdsdVar.zzayd()));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        case 68:
                            zzdta.zza(t, iZzgv & 1048575, zzdsdVar.zzb(zzgs(iZzgy), zzdpqVar));
                            zzb(t, iZzayn, iZzgy);
                            continue;
                        default:
                            if (objZza == null) {
                                try {
                                    objZza = zzdsuVar.zzbbr();
                                } catch (zzdqm unused) {
                                    zzdsuVar.zza(zzdsdVar);
                                    if (objZza == null) {
                                        objZza = zzdsuVar.zzaz(t);
                                    }
                                    if (!zzdsuVar.zza((Object) objZza, zzdsdVar)) {
                                        for (int i2 = this.zzhmw; i2 < this.zzhmx; i2++) {
                                            objZza = zza((Object) t, this.zzhmv[i2], objZza, (zzdsu<UT, Object>) zzdsuVar);
                                        }
                                        if (objZza != null) {
                                            zzdsuVar.zzi(t, (Object) objZza);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                break;
                            }
                            if (!zzdsuVar.zza((Object) objZza, zzdsdVar)) {
                                for (int i3 = this.zzhmw; i3 < this.zzhmx; i3++) {
                                    objZza = zza((Object) t, this.zzhmv[i3], objZza, (zzdsu<UT, Object>) zzdsuVar);
                                }
                                if (objZza != null) {
                                    zzdsuVar.zzi(t, (Object) objZza);
                                    return;
                                }
                                return;
                            }
                            break;
                            break;
                    }
                    for (i = this.zzhmw; i < this.zzhmx; i++) {
                        objZza = zza((Object) t, this.zzhmv[i], objZza, (zzdsu<UT, Object>) zzdsuVar);
                    }
                    if (objZza != null) {
                        zzdsuVar.zzi(t, (Object) objZza);
                    }
                    throw th;
                }
                if (iZzayn == Integer.MAX_VALUE) {
                    for (int i4 = this.zzhmw; i4 < this.zzhmx; i4++) {
                        objZza = zza((Object) t, this.zzhmv[i4], objZza, (zzdsu<UT, Object>) zzdsuVar);
                    }
                    if (objZza != null) {
                        zzdsuVar.zzi(t, (Object) objZza);
                        return;
                    }
                    return;
                }
                Object objZza2 = !this.zzhmr ? null : zzdpsVar.zza(zzdpqVar, this.zzhmq, iZzayn);
                if (objZza2 != null) {
                    if (zzdptVarZzaj == null) {
                        zzdptVarZzaj = zzdpsVar.zzaj(t);
                    }
                    zzdpt zzdptVar = zzdptVarZzaj;
                    objZza = zzdpsVar.zza(zzdsdVar, objZza2, zzdpqVar, zzdptVar, objZza, zzdsuVar);
                    zzdptVarZzaj = zzdptVar;
                } else {
                    zzdsuVar.zza(zzdsdVar);
                    if (objZza == null) {
                        objZza = zzdsuVar.zzaz(t);
                    }
                    if (!zzdsuVar.zza((Object) objZza, zzdsdVar)) {
                        for (int i5 = this.zzhmw; i5 < this.zzhmx; i5++) {
                            objZza = zza((Object) t, this.zzhmv[i5], objZza, (zzdsu<UT, Object>) zzdsuVar);
                        }
                        if (objZza != null) {
                            zzdsuVar.zzi(t, (Object) objZza);
                            return;
                        }
                        return;
                    }
                }
            } catch (Throwable th) {
                while (i < this.zzhmx) {
                    objZza = zza((Object) t, this.zzhmv[i], objZza, (zzdsu<UT, Object>) zzdsuVar);
                }
                if (objZza != null) {
                    zzdsuVar.zzi(t, (Object) objZza);
                }
                throw th;
            }
        }
    }

    private static zzdsx zzav(Object obj) {
        zzdqd zzdqdVar = (zzdqd) obj;
        zzdsx zzdsxVar = zzdqdVar.zzhkd;
        if (zzdsxVar != zzdsx.zzbbs()) {
            return zzdsxVar;
        }
        zzdsx zzdsxVarZzbbt = zzdsx.zzbbt();
        zzdqdVar.zzhkd = zzdsxVarZzbbt;
        return zzdsxVarZzbbt;
    }

    private static int zza(byte[] bArr, int i, int i2, zzdtl zzdtlVar, Class<?> cls, zzdos zzdosVar) throws IOException {
        switch (zzdrq.zzhgf[zzdtlVar.ordinal()]) {
            case 1:
                int iZzb = zzdop.zzb(bArr, i, zzdosVar);
                zzdosVar.zzhfl = Boolean.valueOf(zzdosVar.zzhfk != 0);
                return iZzb;
            case 2:
                return zzdop.zze(bArr, i, zzdosVar);
            case 3:
                zzdosVar.zzhfl = Double.valueOf(zzdop.zzh(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzdosVar.zzhfl = Integer.valueOf(zzdop.zzf(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzdosVar.zzhfl = Long.valueOf(zzdop.zzg(bArr, i));
                return i + 8;
            case 8:
                zzdosVar.zzhfl = Float.valueOf(zzdop.zzi(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int iZza = zzdop.zza(bArr, i, zzdosVar);
                zzdosVar.zzhfl = Integer.valueOf(zzdosVar.zzhfj);
                return iZza;
            case 12:
            case 13:
                int iZzb2 = zzdop.zzb(bArr, i, zzdosVar);
                zzdosVar.zzhfl = Long.valueOf(zzdosVar.zzhfk);
                return iZzb2;
            case 14:
                return zzdop.zza(zzdry.zzbba().zzh(cls), bArr, i, i2, zzdosVar);
            case 15:
                int iZza2 = zzdop.zza(bArr, i, zzdosVar);
                zzdosVar.zzhfl = Integer.valueOf(zzdpf.zzfu(zzdosVar.zzhfj));
                return iZza2;
            case 16:
                int iZzb3 = zzdop.zzb(bArr, i, zzdosVar);
                zzdosVar.zzhfl = Long.valueOf(zzdpf.zzez(zzdosVar.zzhfk));
                return iZzb3;
            case 17:
                return zzdop.zzd(bArr, i, zzdosVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzdos zzdosVar) throws IOException {
        int iZza;
        int iZza2 = i;
        zzdqk zzdqkVarZzfm = (zzdqk) zzgpv.getObject(t, j2);
        if (!zzdqkVarZzfm.zzaxd()) {
            int size = zzdqkVarZzfm.size();
            zzdqkVarZzfm = zzdqkVarZzfm.zzfm(size == 0 ? 10 : size << 1);
            zzgpv.putObject(t, j2, zzdqkVarZzfm);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzdpp zzdppVar = (zzdpp) zzdqkVarZzfm;
                    int iZza3 = zzdop.zza(bArr, iZza2, zzdosVar);
                    int i8 = zzdosVar.zzhfj + iZza3;
                    while (iZza3 < i8) {
                        zzdppVar.zzd(zzdop.zzh(bArr, iZza3));
                        iZza3 += 8;
                    }
                    if (iZza3 == i8) {
                        return iZza3;
                    }
                    throw zzdqn.zzazx();
                }
                if (i5 == 1) {
                    zzdpp zzdppVar2 = (zzdpp) zzdqkVarZzfm;
                    zzdppVar2.zzd(zzdop.zzh(bArr, i));
                    while (true) {
                        int i9 = iZza2 + 8;
                        if (i9 >= i2) {
                            return i9;
                        }
                        iZza2 = zzdop.zza(bArr, i9, zzdosVar);
                        if (i3 != zzdosVar.zzhfj) {
                            return i9;
                        }
                        zzdppVar2.zzd(zzdop.zzh(bArr, iZza2));
                    }
                }
                return iZza2;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzdpz zzdpzVar = (zzdpz) zzdqkVarZzfm;
                    int iZza4 = zzdop.zza(bArr, iZza2, zzdosVar);
                    int i10 = zzdosVar.zzhfj + iZza4;
                    while (iZza4 < i10) {
                        zzdpzVar.zzh(zzdop.zzi(bArr, iZza4));
                        iZza4 += 4;
                    }
                    if (iZza4 == i10) {
                        return iZza4;
                    }
                    throw zzdqn.zzazx();
                }
                if (i5 == 5) {
                    zzdpz zzdpzVar2 = (zzdpz) zzdqkVarZzfm;
                    zzdpzVar2.zzh(zzdop.zzi(bArr, i));
                    while (true) {
                        int i11 = iZza2 + 4;
                        if (i11 >= i2) {
                            return i11;
                        }
                        iZza2 = zzdop.zza(bArr, i11, zzdosVar);
                        if (i3 != zzdosVar.zzhfj) {
                            return i11;
                        }
                        zzdpzVar2.zzh(zzdop.zzi(bArr, iZza2));
                    }
                }
                return iZza2;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzdrb zzdrbVar = (zzdrb) zzdqkVarZzfm;
                    int iZza5 = zzdop.zza(bArr, iZza2, zzdosVar);
                    int i12 = zzdosVar.zzhfj + iZza5;
                    while (iZza5 < i12) {
                        iZza5 = zzdop.zzb(bArr, iZza5, zzdosVar);
                        zzdrbVar.zzfl(zzdosVar.zzhfk);
                    }
                    if (iZza5 == i12) {
                        return iZza5;
                    }
                    throw zzdqn.zzazx();
                }
                if (i5 == 0) {
                    zzdrb zzdrbVar2 = (zzdrb) zzdqkVarZzfm;
                    int iZzb = zzdop.zzb(bArr, iZza2, zzdosVar);
                    zzdrbVar2.zzfl(zzdosVar.zzhfk);
                    while (iZzb < i2) {
                        int iZza6 = zzdop.zza(bArr, iZzb, zzdosVar);
                        if (i3 != zzdosVar.zzhfj) {
                            return iZzb;
                        }
                        iZzb = zzdop.zzb(bArr, iZza6, zzdosVar);
                        zzdrbVar2.zzfl(zzdosVar.zzhfk);
                    }
                    return iZzb;
                }
                return iZza2;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzdop.zza(bArr, iZza2, (zzdqk<?>) zzdqkVarZzfm, zzdosVar);
                }
                if (i5 == 0) {
                    return zzdop.zza(i3, bArr, i, i2, (zzdqk<?>) zzdqkVarZzfm, zzdosVar);
                }
                return iZza2;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzdrb zzdrbVar3 = (zzdrb) zzdqkVarZzfm;
                    int iZza7 = zzdop.zza(bArr, iZza2, zzdosVar);
                    int i13 = zzdosVar.zzhfj + iZza7;
                    while (iZza7 < i13) {
                        zzdrbVar3.zzfl(zzdop.zzg(bArr, iZza7));
                        iZza7 += 8;
                    }
                    if (iZza7 == i13) {
                        return iZza7;
                    }
                    throw zzdqn.zzazx();
                }
                if (i5 == 1) {
                    zzdrb zzdrbVar4 = (zzdrb) zzdqkVarZzfm;
                    zzdrbVar4.zzfl(zzdop.zzg(bArr, i));
                    while (true) {
                        int i14 = iZza2 + 8;
                        if (i14 >= i2) {
                            return i14;
                        }
                        iZza2 = zzdop.zza(bArr, i14, zzdosVar);
                        if (i3 != zzdosVar.zzhfj) {
                            return i14;
                        }
                        zzdrbVar4.zzfl(zzdop.zzg(bArr, iZza2));
                    }
                }
                return iZza2;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzdqf zzdqfVar = (zzdqf) zzdqkVarZzfm;
                    int iZza8 = zzdop.zza(bArr, iZza2, zzdosVar);
                    int i15 = zzdosVar.zzhfj + iZza8;
                    while (iZza8 < i15) {
                        zzdqfVar.zzgq(zzdop.zzf(bArr, iZza8));
                        iZza8 += 4;
                    }
                    if (iZza8 == i15) {
                        return iZza8;
                    }
                    throw zzdqn.zzazx();
                }
                if (i5 == 5) {
                    zzdqf zzdqfVar2 = (zzdqf) zzdqkVarZzfm;
                    zzdqfVar2.zzgq(zzdop.zzf(bArr, i));
                    while (true) {
                        int i16 = iZza2 + 4;
                        if (i16 >= i2) {
                            return i16;
                        }
                        iZza2 = zzdop.zza(bArr, i16, zzdosVar);
                        if (i3 != zzdosVar.zzhfj) {
                            return i16;
                        }
                        zzdqfVar2.zzgq(zzdop.zzf(bArr, iZza2));
                    }
                }
                return iZza2;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzdor zzdorVar = (zzdor) zzdqkVarZzfm;
                    iZza = zzdop.zza(bArr, iZza2, zzdosVar);
                    int i17 = zzdosVar.zzhfj + iZza;
                    while (iZza < i17) {
                        iZza = zzdop.zzb(bArr, iZza, zzdosVar);
                        zzdorVar.addBoolean(zzdosVar.zzhfk != 0);
                    }
                    if (iZza != i17) {
                        throw zzdqn.zzazx();
                    }
                    return iZza;
                }
                if (i5 == 0) {
                    zzdor zzdorVar2 = (zzdor) zzdqkVarZzfm;
                    iZza2 = zzdop.zzb(bArr, iZza2, zzdosVar);
                    zzdorVar2.addBoolean(zzdosVar.zzhfk != 0);
                    while (iZza2 < i2) {
                        int iZza9 = zzdop.zza(bArr, iZza2, zzdosVar);
                        if (i3 == zzdosVar.zzhfj) {
                            iZza2 = zzdop.zzb(bArr, iZza9, zzdosVar);
                            zzdorVar2.addBoolean(zzdosVar.zzhfk != 0);
                        }
                    }
                }
                return iZza2;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        iZza2 = zzdop.zza(bArr, iZza2, zzdosVar);
                        int i18 = zzdosVar.zzhfj;
                        if (i18 < 0) {
                            throw zzdqn.zzazy();
                        }
                        if (i18 == 0) {
                            zzdqkVarZzfm.add("");
                        } else {
                            zzdqkVarZzfm.add(new String(bArr, iZza2, i18, zzdqe.UTF_8));
                            iZza2 += i18;
                        }
                        while (iZza2 < i2) {
                            int iZza10 = zzdop.zza(bArr, iZza2, zzdosVar);
                            if (i3 == zzdosVar.zzhfj) {
                                iZza2 = zzdop.zza(bArr, iZza10, zzdosVar);
                                int i19 = zzdosVar.zzhfj;
                                if (i19 < 0) {
                                    throw zzdqn.zzazy();
                                }
                                if (i19 == 0) {
                                    zzdqkVarZzfm.add("");
                                } else {
                                    zzdqkVarZzfm.add(new String(bArr, iZza2, i19, zzdqe.UTF_8));
                                    iZza2 += i19;
                                }
                            }
                        }
                    } else {
                        iZza2 = zzdop.zza(bArr, iZza2, zzdosVar);
                        int i20 = zzdosVar.zzhfj;
                        if (i20 < 0) {
                            throw zzdqn.zzazy();
                        }
                        if (i20 == 0) {
                            zzdqkVarZzfm.add("");
                        } else {
                            int i21 = iZza2 + i20;
                            if (!zzdtd.zzl(bArr, iZza2, i21)) {
                                throw zzdqn.zzbae();
                            }
                            zzdqkVarZzfm.add(new String(bArr, iZza2, i20, zzdqe.UTF_8));
                            iZza2 = i21;
                        }
                        while (iZza2 < i2) {
                            int iZza11 = zzdop.zza(bArr, iZza2, zzdosVar);
                            if (i3 == zzdosVar.zzhfj) {
                                iZza2 = zzdop.zza(bArr, iZza11, zzdosVar);
                                int i22 = zzdosVar.zzhfj;
                                if (i22 < 0) {
                                    throw zzdqn.zzazy();
                                }
                                if (i22 == 0) {
                                    zzdqkVarZzfm.add("");
                                } else {
                                    int i23 = iZza2 + i22;
                                    if (!zzdtd.zzl(bArr, iZza2, i23)) {
                                        throw zzdqn.zzbae();
                                    }
                                    zzdqkVarZzfm.add(new String(bArr, iZza2, i22, zzdqe.UTF_8));
                                    iZza2 = i23;
                                }
                            }
                        }
                    }
                }
                return iZza2;
            case 27:
                if (i5 == 2) {
                    return zzdop.zza(zzgs(i6), i3, bArr, i, i2, zzdqkVarZzfm, zzdosVar);
                }
                return iZza2;
            case 28:
                if (i5 == 2) {
                    int iZza12 = zzdop.zza(bArr, iZza2, zzdosVar);
                    int i24 = zzdosVar.zzhfj;
                    if (i24 < 0) {
                        throw zzdqn.zzazy();
                    }
                    if (i24 > bArr.length - iZza12) {
                        throw zzdqn.zzazx();
                    }
                    if (i24 == 0) {
                        zzdqkVarZzfm.add(zzdot.zzhfn);
                    } else {
                        zzdqkVarZzfm.add(zzdot.zzh(bArr, iZza12, i24));
                        iZza12 += i24;
                    }
                    while (iZza12 < i2) {
                        int iZza13 = zzdop.zza(bArr, iZza12, zzdosVar);
                        if (i3 != zzdosVar.zzhfj) {
                            return iZza12;
                        }
                        iZza12 = zzdop.zza(bArr, iZza13, zzdosVar);
                        int i25 = zzdosVar.zzhfj;
                        if (i25 < 0) {
                            throw zzdqn.zzazy();
                        }
                        if (i25 > bArr.length - iZza12) {
                            throw zzdqn.zzazx();
                        }
                        if (i25 == 0) {
                            zzdqkVarZzfm.add(zzdot.zzhfn);
                        } else {
                            zzdqkVarZzfm.add(zzdot.zzh(bArr, iZza12, i25));
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
                        iZza = zzdop.zza(i3, bArr, i, i2, (zzdqk<?>) zzdqkVarZzfm, zzdosVar);
                    }
                    return iZza2;
                }
                iZza = zzdop.zza(bArr, iZza2, (zzdqk<?>) zzdqkVarZzfm, zzdosVar);
                zzdqd zzdqdVar = (zzdqd) t;
                zzdsx zzdsxVar = zzdqdVar.zzhkd;
                if (zzdsxVar == zzdsx.zzbbs()) {
                    zzdsxVar = null;
                }
                zzdsx zzdsxVar2 = (zzdsx) zzdse.zza(i4, zzdqkVarZzfm, zzgu(i6), zzdsxVar, this.zzhna);
                if (zzdsxVar2 != null) {
                    zzdqdVar.zzhkd = zzdsxVar2;
                }
                return iZza;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzdqf zzdqfVar3 = (zzdqf) zzdqkVarZzfm;
                    int iZza14 = zzdop.zza(bArr, iZza2, zzdosVar);
                    int i26 = zzdosVar.zzhfj + iZza14;
                    while (iZza14 < i26) {
                        iZza14 = zzdop.zza(bArr, iZza14, zzdosVar);
                        zzdqfVar3.zzgq(zzdpf.zzfu(zzdosVar.zzhfj));
                    }
                    if (iZza14 == i26) {
                        return iZza14;
                    }
                    throw zzdqn.zzazx();
                }
                if (i5 == 0) {
                    zzdqf zzdqfVar4 = (zzdqf) zzdqkVarZzfm;
                    int iZza15 = zzdop.zza(bArr, iZza2, zzdosVar);
                    zzdqfVar4.zzgq(zzdpf.zzfu(zzdosVar.zzhfj));
                    while (iZza15 < i2) {
                        int iZza16 = zzdop.zza(bArr, iZza15, zzdosVar);
                        if (i3 != zzdosVar.zzhfj) {
                            return iZza15;
                        }
                        iZza15 = zzdop.zza(bArr, iZza16, zzdosVar);
                        zzdqfVar4.zzgq(zzdpf.zzfu(zzdosVar.zzhfj));
                    }
                    return iZza15;
                }
                return iZza2;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzdrb zzdrbVar5 = (zzdrb) zzdqkVarZzfm;
                    int iZza17 = zzdop.zza(bArr, iZza2, zzdosVar);
                    int i27 = zzdosVar.zzhfj + iZza17;
                    while (iZza17 < i27) {
                        iZza17 = zzdop.zzb(bArr, iZza17, zzdosVar);
                        zzdrbVar5.zzfl(zzdpf.zzez(zzdosVar.zzhfk));
                    }
                    if (iZza17 == i27) {
                        return iZza17;
                    }
                    throw zzdqn.zzazx();
                }
                if (i5 == 0) {
                    zzdrb zzdrbVar6 = (zzdrb) zzdqkVarZzfm;
                    int iZzb2 = zzdop.zzb(bArr, iZza2, zzdosVar);
                    zzdrbVar6.zzfl(zzdpf.zzez(zzdosVar.zzhfk));
                    while (iZzb2 < i2) {
                        int iZza18 = zzdop.zza(bArr, iZzb2, zzdosVar);
                        if (i3 != zzdosVar.zzhfj) {
                            return iZzb2;
                        }
                        iZzb2 = zzdop.zzb(bArr, iZza18, zzdosVar);
                        zzdrbVar6.zzfl(zzdpf.zzez(zzdosVar.zzhfk));
                    }
                    return iZzb2;
                }
                return iZza2;
            case 49:
                if (i5 == 3) {
                    zzdsc zzdscVarZzgs = zzgs(i6);
                    int i28 = (i3 & (-8)) | 4;
                    iZza2 = zzdop.zza(zzdscVarZzgs, bArr, i, i2, i28, zzdosVar);
                    zzdqkVarZzfm.add(zzdosVar.zzhfl);
                    while (iZza2 < i2) {
                        int iZza19 = zzdop.zza(bArr, iZza2, zzdosVar);
                        if (i3 == zzdosVar.zzhfj) {
                            iZza2 = zzdop.zza(zzdscVarZzgs, bArr, iZza19, i2, i28, zzdosVar);
                            zzdqkVarZzfm.add(zzdosVar.zzhfl);
                        }
                    }
                }
                return iZza2;
            default:
                return iZza2;
        }
    }

    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, long j, zzdos zzdosVar) throws IOException {
        Unsafe unsafe = zzgpv;
        Object objZzgt = zzgt(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzhnc.zzap(object)) {
            Object objZzar = this.zzhnc.zzar(objZzgt);
            this.zzhnc.zze(objZzar, object);
            unsafe.putObject(t, j, objZzar);
            object = objZzar;
        }
        zzdre<?, ?> zzdreVarZzas = this.zzhnc.zzas(objZzgt);
        Map<?, ?> mapZzan = this.zzhnc.zzan(object);
        int iZza = zzdop.zza(bArr, i, zzdosVar);
        int i4 = zzdosVar.zzhfj;
        if (i4 < 0 || i4 > i2 - iZza) {
            throw zzdqn.zzazx();
        }
        int i5 = i4 + iZza;
        K k = zzdreVarZzas.zzhmg;
        V v = zzdreVarZzas.zzcfk;
        while (iZza < i5) {
            int iZza2 = iZza + 1;
            int i6 = bArr[iZza];
            if (i6 < 0) {
                iZza2 = zzdop.zza(i6, bArr, iZza2, zzdosVar);
                i6 = zzdosVar.zzhfj;
            }
            int i7 = iZza2;
            int i8 = i6 >>> 3;
            int i9 = i6 & 7;
            if (i8 == 1) {
                if (i9 == zzdreVarZzas.zzhmf.zzbcc()) {
                    iZza = zza(bArr, i7, i2, zzdreVarZzas.zzhmf, (Class<?>) null, zzdosVar);
                    k = (K) zzdosVar.zzhfl;
                } else {
                    iZza = zzdop.zza(i6, bArr, i7, i2, zzdosVar);
                }
            } else if (i8 == 2 && i9 == zzdreVarZzas.zzhmh.zzbcc()) {
                iZza = zza(bArr, i7, i2, zzdreVarZzas.zzhmh, zzdreVarZzas.zzcfk.getClass(), zzdosVar);
                v = zzdosVar.zzhfl;
            } else {
                iZza = zzdop.zza(i6, bArr, i7, i2, zzdosVar);
            }
        }
        if (iZza != i5) {
            throw zzdqn.zzbad();
        }
        mapZzan.put(k, v);
        return i5;
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzdos zzdosVar) throws IOException {
        int iZzb;
        Unsafe unsafe = zzgpv;
        long j2 = this.zzhmm[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Double.valueOf(zzdop.zzh(bArr, i)));
                iZzb = i + 8;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 52:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Float.valueOf(zzdop.zzi(bArr, i)));
                iZzb = i + 4;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 53:
            case 54:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzdop.zzb(bArr, i, zzdosVar);
                unsafe.putObject(t, j, Long.valueOf(zzdosVar.zzhfk));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzdop.zza(bArr, i, zzdosVar);
                unsafe.putObject(t, j, Integer.valueOf(zzdosVar.zzhfj));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 56:
            case 65:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Long.valueOf(zzdop.zzg(bArr, i)));
                iZzb = i + 8;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Integer.valueOf(zzdop.zzf(bArr, i)));
                iZzb = i + 4;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzdop.zzb(bArr, i, zzdosVar);
                unsafe.putObject(t, j, Boolean.valueOf(zzdosVar.zzhfk != 0));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int iZza = zzdop.zza(bArr, i, zzdosVar);
                int i9 = zzdosVar.zzhfj;
                if (i9 == 0) {
                    unsafe.putObject(t, j, "");
                } else {
                    if ((i6 & DriveFile.MODE_WRITE_ONLY) != 0 && !zzdtd.zzl(bArr, iZza, iZza + i9)) {
                        throw zzdqn.zzbae();
                    }
                    unsafe.putObject(t, j, new String(bArr, iZza, i9, zzdqe.UTF_8));
                    iZza += i9;
                }
                unsafe.putInt(t, j2, i4);
                return iZza;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int iZza2 = zzdop.zza(zzgs(i8), bArr, i, i2, zzdosVar);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, zzdosVar.zzhfl);
                } else {
                    unsafe.putObject(t, j, zzdqe.zzd(object, zzdosVar.zzhfl));
                }
                unsafe.putInt(t, j2, i4);
                return iZza2;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                iZzb = zzdop.zze(bArr, i, zzdosVar);
                unsafe.putObject(t, j, zzdosVar.zzhfl);
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                int iZza3 = zzdop.zza(bArr, i, zzdosVar);
                int i10 = zzdosVar.zzhfj;
                zzdqj zzdqjVarZzgu = zzgu(i8);
                if (zzdqjVarZzgu == null || zzdqjVarZzgu.zzf(i10)) {
                    unsafe.putObject(t, j, Integer.valueOf(i10));
                    iZzb = iZza3;
                    unsafe.putInt(t, j2, i4);
                    return iZzb;
                }
                zzav(t).zzc(i3, Long.valueOf(i10));
                return iZza3;
            case 66:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzdop.zza(bArr, i, zzdosVar);
                unsafe.putObject(t, j, Integer.valueOf(zzdpf.zzfu(zzdosVar.zzhfj)));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 67:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zzdop.zzb(bArr, i, zzdosVar);
                unsafe.putObject(t, j, Long.valueOf(zzdpf.zzez(zzdosVar.zzhfk)));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 68:
                if (i5 != 3) {
                    return i;
                }
                iZzb = zzdop.zza(zzgs(i8), bArr, i, i2, (i3 & (-8)) | 4, zzdosVar);
                Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object2 == null) {
                    unsafe.putObject(t, j, zzdosVar.zzhfl);
                } else {
                    unsafe.putObject(t, j, zzdqe.zzd(object2, zzdosVar.zzhfl));
                }
                unsafe.putInt(t, j2, i4);
                return iZzb;
            default:
                return i;
        }
    }

    private final zzdsc zzgs(int i) {
        int i2 = (i / 3) << 1;
        zzdsc zzdscVar = (zzdsc) this.zzhmn[i2];
        if (zzdscVar != null) {
            return zzdscVar;
        }
        zzdsc<T> zzdscVarZzh = zzdry.zzbba().zzh((Class) this.zzhmn[i2 + 1]);
        this.zzhmn[i2] = zzdscVarZzh;
        return zzdscVarZzh;
    }

    private final Object zzgt(int i) {
        return this.zzhmn[(i / 3) << 1];
    }

    private final zzdqj zzgu(int i) {
        return (zzdqj) this.zzhmn[((i / 3) << 1) + 1];
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 12701. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    final int zza(T r30, byte[] r31, int r32, int r33, int r34, com.google.android.gms.internal.ads.zzdos r35) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdrr.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.ads.zzdos):int");
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
    @Override // com.google.android.gms.internal.ads.zzdsc
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.ads.zzdos r32) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdrr.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.zzdos):void");
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final void zzak(T t) {
        int i;
        int i2 = this.zzhmw;
        while (true) {
            i = this.zzhmx;
            if (i2 >= i) {
                break;
            }
            long jZzgv = zzgv(this.zzhmv[i2]) & 1048575;
            Object objZzp = zzdta.zzp(t, jZzgv);
            if (objZzp != null) {
                zzdta.zza(t, jZzgv, this.zzhnc.zzaq(objZzp));
            }
            i2++;
        }
        int length = this.zzhmv.length;
        while (i < length) {
            this.zzhmz.zzb(t, this.zzhmv[i]);
            i++;
        }
        this.zzhna.zzak(t);
        if (this.zzhmr) {
            this.zzhnb.zzak(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzdsu<UT, UB> zzdsuVar) {
        zzdqj zzdqjVarZzgu;
        int i2 = this.zzhmm[i];
        Object objZzp = zzdta.zzp(obj, zzgv(i) & 1048575);
        return (objZzp == null || (zzdqjVarZzgu = zzgu(i)) == null) ? ub : (UB) zza(i, i2, this.zzhnc.zzan(objZzp), zzdqjVarZzgu, ub, zzdsuVar);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzdqj zzdqjVar, UB ub, zzdsu<UT, UB> zzdsuVar) {
        zzdre<?, ?> zzdreVarZzas = this.zzhnc.zzas(zzgt(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzdqjVar.zzf(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzdsuVar.zzbbr();
                }
                zzdpb zzdpbVarZzfp = zzdot.zzfp(zzdrf.zza(zzdreVarZzas, next.getKey(), next.getValue()));
                try {
                    zzdrf.zza(zzdpbVarZzfp.zzaxo(), zzdreVarZzas, next.getKey(), next.getValue());
                    zzdsuVar.zza(ub, i2, zzdpbVarZzfp.zzaxn());
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
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.ads.zzdsc] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.ads.zzdsc] */
    @Override // com.google.android.gms.internal.ads.zzdsc
    public final boolean zzaw(T t) {
        int i;
        List list;
        ?? Zzgs;
        int i2;
        int i3 = 0;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= this.zzhmw) {
                return !this.zzhmr || this.zzhnb.zzai(t).isInitialized();
            }
            int i6 = this.zzhmv[i3];
            int i7 = this.zzhmm[i6];
            int iZzgv = zzgv(i6);
            if (this.zzhmt) {
                i = 0;
            } else {
                int i8 = this.zzhmm[i6 + 2];
                int i9 = i8 & 1048575;
                i = 1 << (i8 >>> 20);
                if (i9 != i4) {
                    i5 = zzgpv.getInt(t, i9);
                    i4 = i9;
                }
            }
            if (((268435456 & iZzgv) != 0) && !zza(t, i6, i5, i)) {
                return false;
            }
            int i10 = (267386880 & iZzgv) >>> 20;
            if (i10 == 9 || i10 == 17) {
                if (zza(t, i6, i5, i) && !zza(t, iZzgv, zzgs(i6))) {
                    return false;
                }
            } else if (i10 == 27) {
                list = (List) zzdta.zzp(t, iZzgv & 1048575);
                if (!list.isEmpty()) {
                    Zzgs = zzgs(i6);
                    for (i2 = 0; i2 < list.size(); i2++) {
                        if (!Zzgs.zzaw(list.get(i2))) {
                            z = false;
                            break;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (i10 == 60 || i10 == 68) {
                if (zza(t, i7, i6) && !zza(t, iZzgv, zzgs(i6))) {
                    return false;
                }
            } else if (i10 == 49) {
                list = (List) zzdta.zzp(t, iZzgv & 1048575);
                if (!list.isEmpty()) {
                    Zzgs = zzgs(i6);
                    while (i2 < list.size()) {
                        if (!Zzgs.zzaw(list.get(i2))) {
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
                Map<?, ?> mapZzao = this.zzhnc.zzao(zzdta.zzp(t, iZzgv & 1048575));
                if (!mapZzao.isEmpty()) {
                    if (this.zzhnc.zzas(zzgt(i6)).zzhmh.zzbcb() == zzdto.MESSAGE) {
                        ?? Zzh = 0;
                        for (Object obj : mapZzao.values()) {
                            if (Zzh == 0) {
                                Zzh = Zzh;
                                Zzh = zzdry.zzbba().zzh(obj.getClass());
                            }
                            Zzh = Zzh;
                            if (!Zzh.zzaw(obj)) {
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
    private static boolean zza(Object obj, int i, zzdsc zzdscVar) {
        return zzdscVar.zzaw(zzdta.zzp(obj, i & 1048575));
    }

    private static void zza(int i, Object obj, zzdtr zzdtrVar) throws IOException {
        if (obj instanceof String) {
            zzdtrVar.zzg(i, (String) obj);
        } else {
            zzdtrVar.zza(i, (zzdot) obj);
        }
    }

    private final void zza(Object obj, int i, zzdsd zzdsdVar) throws IOException {
        if (zzgx(i)) {
            zzdta.zza(obj, i & 1048575, zzdsdVar.zzaxw());
        } else if (this.zzhms) {
            zzdta.zza(obj, i & 1048575, zzdsdVar.readString());
        } else {
            zzdta.zza(obj, i & 1048575, zzdsdVar.zzaxx());
        }
    }

    private final int zzgv(int i) {
        return this.zzhmm[i + 1];
    }

    private final int zzgw(int i) {
        return this.zzhmm[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzdta.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzdta.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzdta.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzdta.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzdta.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zze((Object) t, i) == zze((Object) t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzhmt) {
            return zze((Object) t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zze(T t, int i) {
        if (this.zzhmt) {
            int iZzgv = zzgv(i);
            long j = iZzgv & 1048575;
            switch ((iZzgv & 267386880) >>> 20) {
                case 0:
                    return zzdta.zzo(t, j) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                case 1:
                    return zzdta.zzn(t, j) != 0.0f;
                case 2:
                    return zzdta.zzl(t, j) != 0;
                case 3:
                    return zzdta.zzl(t, j) != 0;
                case 4:
                    return zzdta.zzk(t, j) != 0;
                case 5:
                    return zzdta.zzl(t, j) != 0;
                case 6:
                    return zzdta.zzk(t, j) != 0;
                case 7:
                    return zzdta.zzm(t, j);
                case 8:
                    Object objZzp = zzdta.zzp(t, j);
                    if (objZzp instanceof String) {
                        return !((String) objZzp).isEmpty();
                    }
                    if (objZzp instanceof zzdot) {
                        return !zzdot.zzhfn.equals(objZzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzdta.zzp(t, j) != null;
                case 10:
                    return !zzdot.zzhfn.equals(zzdta.zzp(t, j));
                case 11:
                    return zzdta.zzk(t, j) != 0;
                case 12:
                    return zzdta.zzk(t, j) != 0;
                case 13:
                    return zzdta.zzk(t, j) != 0;
                case 14:
                    return zzdta.zzl(t, j) != 0;
                case 15:
                    return zzdta.zzk(t, j) != 0;
                case 16:
                    return zzdta.zzl(t, j) != 0;
                case 17:
                    return zzdta.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int iZzgw = zzgw(i);
        return (zzdta.zzk(t, (long) (iZzgw & 1048575)) & (1 << (iZzgw >>> 20))) != 0;
    }

    private final void zzf(T t, int i) {
        if (this.zzhmt) {
            return;
        }
        int iZzgw = zzgw(i);
        long j = iZzgw & 1048575;
        zzdta.zzb(t, j, zzdta.zzk(t, j) | (1 << (iZzgw >>> 20)));
    }

    private final boolean zza(T t, int i, int i2) {
        return zzdta.zzk(t, (long) (zzgw(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zzdta.zzb(t, zzgw(i2) & 1048575, i);
    }

    private final int zzgy(int i) {
        if (i < this.zzhmo || i > this.zzhmp) {
            return -1;
        }
        return zzao(i, 0);
    }

    private final int zzan(int i, int i2) {
        if (i < this.zzhmo || i > this.zzhmp) {
            return -1;
        }
        return zzao(i, i2);
    }

    private final int zzao(int i, int i2) {
        int length = (this.zzhmm.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzhmm[i4];
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
