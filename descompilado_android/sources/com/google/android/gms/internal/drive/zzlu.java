package com.google.android.gms.internal.drive;

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
final class zzlu<T> implements zzmf<T> {
    private static final int[] zzub = new int[0];
    private static final Unsafe zzuc = zznd.zzff();
    private final int[] zzud;
    private final Object[] zzue;
    private final int zzuf;
    private final int zzug;
    private final zzlq zzuh;
    private final boolean zzui;
    private final boolean zzuj;
    private final boolean zzuk;
    private final boolean zzul;
    private final int[] zzum;
    private final int zzun;
    private final int zzuo;
    private final zzly zzup;
    private final zzla zzuq;
    private final zzmx<?, ?> zzur;
    private final zzjy<?> zzus;
    private final zzll zzut;

    private zzlu(int[] iArr, Object[] objArr, int i, int i2, zzlq zzlqVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzly zzlyVar, zzla zzlaVar, zzmx<?, ?> zzmxVar, zzjy<?> zzjyVar, zzll zzllVar) {
        this.zzud = iArr;
        this.zzue = objArr;
        this.zzuf = i;
        this.zzug = i2;
        this.zzuj = zzlqVar instanceof zzkk;
        this.zzuk = z;
        this.zzui = zzjyVar != null && zzjyVar.zze(zzlqVar);
        this.zzul = false;
        this.zzum = iArr2;
        this.zzun = i3;
        this.zzuo = i4;
        this.zzup = zzlyVar;
        this.zzuq = zzlaVar;
        this.zzur = zzmxVar;
        this.zzus = zzjyVar;
        this.zzuh = zzlqVar;
        this.zzut = zzllVar;
    }

    /* JADX WARN: Code duplicated, block: B:172:0x0379  */
    /* JADX WARN: Code duplicated, block: B:186:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:189:0x03d7  */
    static <T> zzlu<T> zza(Class<T> cls, zzlo zzloVar, zzly zzlyVar, zzla zzlaVar, zzmx<?, ?> zzmxVar, zzjy<?> zzjyVar, zzll zzllVar) {
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
        if (zzloVar instanceof zzme) {
            zzme zzmeVar = (zzme) zzloVar;
            int iCharAt4 = 0;
            boolean z = zzmeVar.zzec() == zzkk.zze.zzsg;
            String strZzek = zzmeVar.zzek();
            int length = strZzek.length();
            int iCharAt5 = strZzek.charAt(0);
            if (iCharAt5 >= 55296) {
                int i27 = iCharAt5 & 8191;
                int i28 = 1;
                int i29 = 13;
                while (true) {
                    i = i28 + 1;
                    cCharAt14 = strZzek.charAt(i28);
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
            int iCharAt6 = strZzek.charAt(i);
            if (iCharAt6 >= 55296) {
                int i31 = iCharAt6 & 8191;
                int i32 = 13;
                while (true) {
                    i2 = i30 + 1;
                    cCharAt13 = strZzek.charAt(i30);
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
                iArr = zzub;
                iCharAt3 = 0;
                i10 = 0;
                i8 = 0;
                iCharAt = 0;
                iCharAt2 = 0;
                i9 = 0;
            } else {
                int i33 = i2 + 1;
                int iCharAt7 = strZzek.charAt(i2);
                if (iCharAt7 >= 55296) {
                    int i34 = iCharAt7 & 8191;
                    int i35 = 13;
                    while (true) {
                        i3 = i33 + 1;
                        cCharAt8 = strZzek.charAt(i33);
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
                int iCharAt8 = strZzek.charAt(i3);
                if (iCharAt8 >= 55296) {
                    int i37 = iCharAt8 & 8191;
                    int i38 = 13;
                    while (true) {
                        i4 = i36 + 1;
                        cCharAt7 = strZzek.charAt(i36);
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
                int iCharAt9 = strZzek.charAt(i4);
                if (iCharAt9 >= 55296) {
                    int i40 = iCharAt9 & 8191;
                    int i41 = 13;
                    while (true) {
                        i5 = i39 + 1;
                        cCharAt6 = strZzek.charAt(i39);
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
                iCharAt = strZzek.charAt(i5);
                if (iCharAt >= 55296) {
                    int i43 = iCharAt & 8191;
                    int i44 = 13;
                    while (true) {
                        i6 = i42 + 1;
                        cCharAt5 = strZzek.charAt(i42);
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
                iCharAt2 = strZzek.charAt(i6);
                if (iCharAt2 >= 55296) {
                    int i46 = iCharAt2 & 8191;
                    int i47 = 13;
                    while (true) {
                        i14 = i45 + 1;
                        cCharAt4 = strZzek.charAt(i45);
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
                iCharAt3 = strZzek.charAt(i45);
                if (iCharAt3 >= 55296) {
                    int i49 = iCharAt3 & 8191;
                    int i50 = 13;
                    while (true) {
                        i13 = i48 + 1;
                        cCharAt3 = strZzek.charAt(i48);
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
                int iCharAt10 = strZzek.charAt(i48);
                if (iCharAt10 >= 55296) {
                    int i52 = 13;
                    int i53 = iCharAt10 & 8191;
                    int i54 = i51;
                    while (true) {
                        i12 = i54 + 1;
                        cCharAt2 = strZzek.charAt(i54);
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
                iCharAt4 = strZzek.charAt(i7);
                if (iCharAt4 >= 55296) {
                    int i56 = 13;
                    int i57 = iCharAt4 & 8191;
                    int i58 = i55;
                    while (true) {
                        i11 = i58 + 1;
                        cCharAt = strZzek.charAt(i58);
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
            Unsafe unsafe = zzuc;
            Object[] objArrZzel = zzmeVar.zzel();
            Class<?> cls2 = zzmeVar.zzee().getClass();
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
                int iCharAt11 = strZzek.charAt(i2);
                char c = 55296;
                if (iCharAt11 >= 55296) {
                    int i67 = 13;
                    int i68 = iCharAt11 & 8191;
                    int i69 = i66;
                    while (true) {
                        i26 = i69 + 1;
                        cCharAt12 = strZzek.charAt(i69);
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
                int iCharAt12 = strZzek.charAt(i15);
                int i71 = length;
                char c2 = 55296;
                if (iCharAt12 >= 55296) {
                    int i72 = 13;
                    int i73 = iCharAt12 & 8191;
                    int i74 = i70;
                    while (true) {
                        i25 = i74 + 1;
                        cCharAt11 = strZzek.charAt(i74);
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
                    int iCharAt13 = strZzek.charAt(i16);
                    char c3 = 55296;
                    if (iCharAt13 >= 55296) {
                        int i79 = iCharAt13 & 8191;
                        int i80 = 13;
                        while (true) {
                            i24 = i78 + 1;
                            cCharAt10 = strZzek.charAt(i78);
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
                        objArr[((i65 / 3) << 1) + 1] = objArrZzel[i60];
                        i60++;
                    } else {
                        if (i81 == 12 && (iCharAt5 & 1) == 1) {
                            objArr[((i65 / 3) << 1) + 1] = objArrZzel[i60];
                            i60++;
                        }
                        i23 = 1;
                    }
                    int i83 = iCharAt13 << i23;
                    Object obj = objArrZzel[i83];
                    if (obj instanceof Field) {
                        fieldZza2 = (Field) obj;
                    } else {
                        fieldZza2 = zza(cls2, (String) obj);
                        objArrZzel[i83] = fieldZza2;
                    }
                    int i84 = i10;
                    int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldZza2);
                    int i85 = i83 + 1;
                    Object obj2 = objArrZzel[i85];
                    if (obj2 instanceof Field) {
                        fieldZza3 = (Field) obj2;
                    } else {
                        fieldZza3 = zza(cls2, (String) obj2);
                        objArrZzel[i85] = fieldZza3;
                    }
                    strZzek = strZzek;
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
                    Field fieldZza4 = zza(cls2, (String) objArrZzel[i60]);
                    if (i76 == 9 || i76 == 17) {
                        i18 = i86;
                        objArr[((i65 / 3) << 1) + 1] = fieldZza4.getType();
                    } else {
                        if (i76 == 27 || i76 == 49) {
                            i18 = i86;
                            i22 = i17 + 1;
                            objArr[((i65 / 3) << 1) + 1] = objArrZzel[i17];
                        } else if (i76 == 12 || i76 == 30 || i76 == 44) {
                            i18 = i86;
                            if ((iCharAt5 & 1) == 1) {
                                i22 = i17 + 1;
                                objArr[((i65 / 3) << 1) + 1] = objArrZzel[i17];
                            }
                            iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldZza4);
                            if ((iCharAt5 & 1) == 1 || i76 > 17) {
                                i19 = i16;
                                i20 = 0;
                                iObjectFieldOffset2 = 0;
                            } else {
                                i19 = i16 + 1;
                                int iCharAt14 = strZzek.charAt(i16);
                                if (iCharAt14 >= 55296) {
                                    int i87 = iCharAt14 & 8191;
                                    int i88 = 13;
                                    while (true) {
                                        i21 = i19 + 1;
                                        cCharAt9 = strZzek.charAt(i19);
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
                                Object obj3 = objArrZzel[i89];
                                if (obj3 instanceof Field) {
                                    fieldZza = (Field) obj3;
                                } else {
                                    fieldZza = zza(cls2, (String) obj3);
                                    objArrZzel[i89] = fieldZza;
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
                            objArr[i91] = objArrZzel[i17];
                            if ((iCharAt12 & 2048) != 0) {
                                i17 = i92 + 1;
                                objArr[i91 + 1] = objArrZzel[i92];
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
                strZzek = strZzek;
            }
            return new zzlu<>(iArr2, objArr, i10, iCharAt, zzmeVar.zzee(), z, false, iArr, iCharAt4, i61, zzlyVar, zzlaVar, zzmxVar, zzjyVar, zzllVar);
        }
        ((zzms) zzloVar).zzec();
        int i95 = zzkk.zze.zzsg;
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

    @Override // com.google.android.gms.internal.drive.zzmf
    public final T newInstance() {
        return (T) this.zzup.newInstance(this.zzuh);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x01c1  */
    @Override // com.google.android.gms.internal.drive.zzmf
    public final boolean equals(T t, T t2) {
        int length = this.zzud.length;
        int i = 0;
        while (true) {
            boolean zZzd = true;
            if (i < length) {
                int iZzas = zzas(i);
                long j = iZzas & 1048575;
                switch ((iZzas & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t, t2, i) || Double.doubleToLongBits(zznd.zzn(t, j)) != Double.doubleToLongBits(zznd.zzn(t2, j))) {
                            zZzd = false;
                        }
                        break;
                    case 1:
                        if (!zzc(t, t2, i) || Float.floatToIntBits(zznd.zzm(t, j)) != Float.floatToIntBits(zznd.zzm(t2, j))) {
                            zZzd = false;
                        }
                        break;
                    case 2:
                        if (!zzc(t, t2, i) || zznd.zzk(t, j) != zznd.zzk(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 3:
                        if (!zzc(t, t2, i) || zznd.zzk(t, j) != zznd.zzk(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 4:
                        if (!zzc(t, t2, i) || zznd.zzj(t, j) != zznd.zzj(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 5:
                        if (!zzc(t, t2, i) || zznd.zzk(t, j) != zznd.zzk(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 6:
                        if (!zzc(t, t2, i) || zznd.zzj(t, j) != zznd.zzj(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 7:
                        if (!zzc(t, t2, i) || zznd.zzl(t, j) != zznd.zzl(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 8:
                        if (!zzc(t, t2, i) || !zzmh.zzd(zznd.zzo(t, j), zznd.zzo(t2, j))) {
                            zZzd = false;
                        }
                        break;
                    case 9:
                        if (!zzc(t, t2, i) || !zzmh.zzd(zznd.zzo(t, j), zznd.zzo(t2, j))) {
                            zZzd = false;
                        }
                        break;
                    case 10:
                        if (!zzc(t, t2, i) || !zzmh.zzd(zznd.zzo(t, j), zznd.zzo(t2, j))) {
                            zZzd = false;
                        }
                        break;
                    case 11:
                        if (!zzc(t, t2, i) || zznd.zzj(t, j) != zznd.zzj(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 12:
                        if (!zzc(t, t2, i) || zznd.zzj(t, j) != zznd.zzj(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 13:
                        if (!zzc(t, t2, i) || zznd.zzj(t, j) != zznd.zzj(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 14:
                        if (!zzc(t, t2, i) || zznd.zzk(t, j) != zznd.zzk(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 15:
                        if (!zzc(t, t2, i) || zznd.zzj(t, j) != zznd.zzj(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 16:
                        if (!zzc(t, t2, i) || zznd.zzk(t, j) != zznd.zzk(t2, j)) {
                            zZzd = false;
                        }
                        break;
                    case 17:
                        if (!zzc(t, t2, i) || !zzmh.zzd(zznd.zzo(t, j), zznd.zzo(t2, j))) {
                            zZzd = false;
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
                        zZzd = zzmh.zzd(zznd.zzo(t, j), zznd.zzo(t2, j));
                        break;
                    case 50:
                        zZzd = zzmh.zzd(zznd.zzo(t, j), zznd.zzo(t2, j));
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
                        long jZzat = zzat(i) & 1048575;
                        if (zznd.zzj(t, jZzat) != zznd.zzj(t2, jZzat) || !zzmh.zzd(zznd.zzo(t, j), zznd.zzo(t2, j))) {
                            zZzd = false;
                        }
                        break;
                }
                if (!zZzd) {
                    return false;
                }
                i += 3;
            } else {
                if (!this.zzur.zzr(t).equals(this.zzur.zzr(t2))) {
                    return false;
                }
                if (this.zzui) {
                    return this.zzus.zzb(t).equals(this.zzus.zzb(t2));
                }
                return true;
            }
        }
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final int hashCode(T t) {
        int i;
        int iZzu;
        int length = this.zzud.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iZzas = zzas(i3);
            int i4 = this.zzud[i3];
            long j = 1048575 & iZzas;
            int iHashCode = 37;
            switch ((iZzas & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    iZzu = zzkm.zzu(Double.doubleToLongBits(zznd.zzn(t, j)));
                    i2 = i + iZzu;
                    break;
                case 1:
                    i = i2 * 53;
                    iZzu = Float.floatToIntBits(zznd.zzm(t, j));
                    i2 = i + iZzu;
                    break;
                case 2:
                    i = i2 * 53;
                    iZzu = zzkm.zzu(zznd.zzk(t, j));
                    i2 = i + iZzu;
                    break;
                case 3:
                    i = i2 * 53;
                    iZzu = zzkm.zzu(zznd.zzk(t, j));
                    i2 = i + iZzu;
                    break;
                case 4:
                    i = i2 * 53;
                    iZzu = zznd.zzj(t, j);
                    i2 = i + iZzu;
                    break;
                case 5:
                    i = i2 * 53;
                    iZzu = zzkm.zzu(zznd.zzk(t, j));
                    i2 = i + iZzu;
                    break;
                case 6:
                    i = i2 * 53;
                    iZzu = zznd.zzj(t, j);
                    i2 = i + iZzu;
                    break;
                case 7:
                    i = i2 * 53;
                    iZzu = zzkm.zze(zznd.zzl(t, j));
                    i2 = i + iZzu;
                    break;
                case 8:
                    i = i2 * 53;
                    iZzu = ((String) zznd.zzo(t, j)).hashCode();
                    i2 = i + iZzu;
                    break;
                case 9:
                    Object objZzo = zznd.zzo(t, j);
                    if (objZzo != null) {
                        iHashCode = objZzo.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iZzu = zznd.zzo(t, j).hashCode();
                    i2 = i + iZzu;
                    break;
                case 11:
                    i = i2 * 53;
                    iZzu = zznd.zzj(t, j);
                    i2 = i + iZzu;
                    break;
                case 12:
                    i = i2 * 53;
                    iZzu = zznd.zzj(t, j);
                    i2 = i + iZzu;
                    break;
                case 13:
                    i = i2 * 53;
                    iZzu = zznd.zzj(t, j);
                    i2 = i + iZzu;
                    break;
                case 14:
                    i = i2 * 53;
                    iZzu = zzkm.zzu(zznd.zzk(t, j));
                    i2 = i + iZzu;
                    break;
                case 15:
                    i = i2 * 53;
                    iZzu = zznd.zzj(t, j);
                    i2 = i + iZzu;
                    break;
                case 16:
                    i = i2 * 53;
                    iZzu = zzkm.zzu(zznd.zzk(t, j));
                    i2 = i + iZzu;
                    break;
                case 17:
                    Object objZzo2 = zznd.zzo(t, j);
                    if (objZzo2 != null) {
                        iHashCode = objZzo2.hashCode();
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
                    iZzu = zznd.zzo(t, j).hashCode();
                    i2 = i + iZzu;
                    break;
                case 50:
                    i = i2 * 53;
                    iZzu = zznd.zzo(t, j).hashCode();
                    i2 = i + iZzu;
                    break;
                case 51:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzkm.zzu(Double.doubleToLongBits(zze(t, j)));
                        i2 = i + iZzu;
                    }
                    break;
                case 52:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = Float.floatToIntBits(zzf(t, j));
                        i2 = i + iZzu;
                    }
                    break;
                case 53:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzkm.zzu(zzh(t, j));
                        i2 = i + iZzu;
                    }
                    break;
                case 54:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzkm.zzu(zzh(t, j));
                        i2 = i + iZzu;
                    }
                    break;
                case 55:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzg(t, j);
                        i2 = i + iZzu;
                    }
                    break;
                case 56:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzkm.zzu(zzh(t, j));
                        i2 = i + iZzu;
                    }
                    break;
                case 57:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzg(t, j);
                        i2 = i + iZzu;
                    }
                    break;
                case 58:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzkm.zze(zzi(t, j));
                        i2 = i + iZzu;
                    }
                    break;
                case 59:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = ((String) zznd.zzo(t, j)).hashCode();
                        i2 = i + iZzu;
                    }
                    break;
                case 60:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zznd.zzo(t, j).hashCode();
                        i2 = i + iZzu;
                    }
                    break;
                case 61:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zznd.zzo(t, j).hashCode();
                        i2 = i + iZzu;
                    }
                    break;
                case 62:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzg(t, j);
                        i2 = i + iZzu;
                    }
                    break;
                case 63:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzg(t, j);
                        i2 = i + iZzu;
                    }
                    break;
                case 64:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzg(t, j);
                        i2 = i + iZzu;
                    }
                    break;
                case 65:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzkm.zzu(zzh(t, j));
                        i2 = i + iZzu;
                    }
                    break;
                case 66:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzg(t, j);
                        i2 = i + iZzu;
                    }
                    break;
                case 67:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zzkm.zzu(zzh(t, j));
                        i2 = i + iZzu;
                    }
                    break;
                case 68:
                    if (zza(t, i4, i3)) {
                        i = i2 * 53;
                        iZzu = zznd.zzo(t, j).hashCode();
                        i2 = i + iZzu;
                    }
                    break;
            }
        }
        int iHashCode2 = (i2 * 53) + this.zzur.zzr(t).hashCode();
        return this.zzui ? (iHashCode2 * 53) + this.zzus.zzb(t).hashCode() : iHashCode2;
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final void zzc(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzud.length; i += 3) {
            int iZzas = zzas(i);
            long j = 1048575 & iZzas;
            int i2 = this.zzud[i];
            switch ((iZzas & 267386880) >>> 20) {
                case 0:
                    if (zza(t2, i)) {
                        zznd.zza(t, j, zznd.zzn(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 1:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzm(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 2:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 3:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 4:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzj(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 5:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 6:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzj(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 7:
                    if (zza(t2, i)) {
                        zznd.zza(t, j, zznd.zzl(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 8:
                    if (zza(t2, i)) {
                        zznd.zza(t, j, zznd.zzo(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 9:
                    zza(t, t2, i);
                    break;
                case 10:
                    if (zza(t2, i)) {
                        zznd.zza(t, j, zznd.zzo(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 11:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzj(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 12:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzj(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 13:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzj(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 14:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzk(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 15:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzj(t2, j));
                        zzb(t, i);
                    }
                    break;
                case 16:
                    if (zza(t2, i)) {
                        zznd.zza((Object) t, j, zznd.zzk(t2, j));
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
                    this.zzuq.zza(t, t2, j);
                    break;
                case 50:
                    zzmh.zza(this.zzut, t, t2, j);
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
                        zznd.zza(t, j, zznd.zzo(t2, j));
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
                        zznd.zza(t, j, zznd.zzo(t2, j));
                        zzb(t, i2, i);
                    }
                    break;
                case 68:
                    zzb(t, t2, i);
                    break;
            }
        }
        if (this.zzuk) {
            return;
        }
        zzmh.zza(this.zzur, t, t2);
        if (this.zzui) {
            zzmh.zza(this.zzus, t, t2);
        }
    }

    private final void zza(T t, T t2, int i) {
        long jZzas = zzas(i) & 1048575;
        if (zza(t2, i)) {
            Object objZzo = zznd.zzo(t, jZzas);
            Object objZzo2 = zznd.zzo(t2, jZzas);
            if (objZzo != null && objZzo2 != null) {
                zznd.zza(t, jZzas, zzkm.zza(objZzo, objZzo2));
                zzb(t, i);
            } else if (objZzo2 != null) {
                zznd.zza(t, jZzas, objZzo2);
                zzb(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int iZzas = zzas(i);
        int i2 = this.zzud[i];
        long j = iZzas & 1048575;
        if (zza(t2, i2, i)) {
            Object objZzo = zznd.zzo(t, j);
            Object objZzo2 = zznd.zzo(t2, j);
            if (objZzo != null && objZzo2 != null) {
                zznd.zza(t, j, zzkm.zza(objZzo, objZzo2));
                zzb(t, i2, i);
            } else if (objZzo2 != null) {
                zznd.zza(t, j, objZzo2);
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
    @Override // com.google.android.gms.internal.drive.zzmf
    public final int zzn(T t) {
        int i;
        int i2;
        long j;
        int iZzd;
        int iZzb;
        int iZzk;
        int iZzv;
        int iZzi;
        int iZzab;
        int iZzad;
        int iZzb2;
        int iZzi2;
        int iZzab2;
        int iZzad2;
        int i3 = 267386880;
        int i4 = 1048575;
        int i5 = 1;
        if (this.zzuk) {
            Unsafe unsafe = zzuc;
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.zzud.length) {
                int iZzas = zzas(i6);
                int i8 = (iZzas & i3) >>> 20;
                int i9 = this.zzud[i6];
                long j2 = iZzas & 1048575;
                int i10 = (i8 < zzke.DOUBLE_LIST_PACKED.id() || i8 > zzke.SINT64_LIST_PACKED.id()) ? 0 : this.zzud[i6 + 2] & 1048575;
                switch (i8) {
                    case 0:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzb(i9, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i7 += iZzb2;
                        }
                        break;
                    case 1:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzb(i9, 0.0f);
                            i7 += iZzb2;
                        }
                        break;
                    case 2:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzd(i9, zznd.zzk(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 3:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zze(i9, zznd.zzk(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 4:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzg(i9, zznd.zzj(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 5:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzg(i9, 0L);
                            i7 += iZzb2;
                        }
                        break;
                    case 6:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzj(i9, 0);
                            i7 += iZzb2;
                        }
                        break;
                    case 7:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzc(i9, true);
                            i7 += iZzb2;
                        }
                        break;
                    case 8:
                        if (zza(t, i6)) {
                            Object objZzo = zznd.zzo(t, j2);
                            if (objZzo instanceof zzjc) {
                                iZzb2 = zzjr.zzc(i9, (zzjc) objZzo);
                            } else {
                                iZzb2 = zzjr.zzb(i9, (String) objZzo);
                            }
                            i7 += iZzb2;
                        }
                        break;
                    case 9:
                        if (zza(t, i6)) {
                            iZzb2 = zzmh.zzc(i9, zznd.zzo(t, j2), zzap(i6));
                            i7 += iZzb2;
                        }
                        break;
                    case 10:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzc(i9, (zzjc) zznd.zzo(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 11:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzh(i9, zznd.zzj(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 12:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzl(i9, zznd.zzj(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 13:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzk(i9, 0);
                            i7 += iZzb2;
                        }
                        break;
                    case 14:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzh(i9, 0L);
                            i7 += iZzb2;
                        }
                        break;
                    case 15:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzi(i9, zznd.zzj(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 16:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzf(i9, zznd.zzk(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 17:
                        if (zza(t, i6)) {
                            iZzb2 = zzjr.zzc(i9, (zzlq) zznd.zzo(t, j2), zzap(i6));
                            i7 += iZzb2;
                        }
                        break;
                    case 18:
                        iZzb2 = zzmh.zzw(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 19:
                        iZzb2 = zzmh.zzv(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 20:
                        iZzb2 = zzmh.zzo(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 21:
                        iZzb2 = zzmh.zzp(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 22:
                        iZzb2 = zzmh.zzs(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 23:
                        iZzb2 = zzmh.zzw(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 24:
                        iZzb2 = zzmh.zzv(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 25:
                        iZzb2 = zzmh.zzx(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 26:
                        iZzb2 = zzmh.zzc(i9, zzd(t, j2));
                        i7 += iZzb2;
                        break;
                    case 27:
                        iZzb2 = zzmh.zzc(i9, (List<?>) zzd(t, j2), zzap(i6));
                        i7 += iZzb2;
                        break;
                    case 28:
                        iZzb2 = zzmh.zzd(i9, (List<zzjc>) zzd(t, j2));
                        i7 += iZzb2;
                        break;
                    case 29:
                        iZzb2 = zzmh.zzt(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 30:
                        iZzb2 = zzmh.zzr(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 31:
                        iZzb2 = zzmh.zzv(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 32:
                        iZzb2 = zzmh.zzw(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 33:
                        iZzb2 = zzmh.zzu(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 34:
                        iZzb2 = zzmh.zzq(i9, zzd(t, j2), false);
                        i7 += iZzb2;
                        break;
                    case 35:
                        iZzi2 = zzmh.zzi((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 36:
                        iZzi2 = zzmh.zzh((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 37:
                        iZzi2 = zzmh.zza((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 38:
                        iZzi2 = zzmh.zzb((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 39:
                        iZzi2 = zzmh.zze((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 40:
                        iZzi2 = zzmh.zzi((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 41:
                        iZzi2 = zzmh.zzh((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 42:
                        iZzi2 = zzmh.zzj((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 43:
                        iZzi2 = zzmh.zzf((List<Integer>) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 44:
                        iZzi2 = zzmh.zzd((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 45:
                        iZzi2 = zzmh.zzh((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 46:
                        iZzi2 = zzmh.zzi((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 47:
                        iZzi2 = zzmh.zzg((List<Integer>) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 48:
                        iZzi2 = zzmh.zzc((List) unsafe.getObject(t, j2));
                        if (iZzi2 > 0) {
                            if (this.zzul) {
                                unsafe.putInt(t, i10, iZzi2);
                            }
                            iZzab2 = zzjr.zzab(i9);
                            iZzad2 = zzjr.zzad(iZzi2);
                            iZzb2 = iZzab2 + iZzad2 + iZzi2;
                            i7 += iZzb2;
                        }
                        break;
                    case 49:
                        iZzb2 = zzmh.zzd(i9, zzd(t, j2), zzap(i6));
                        i7 += iZzb2;
                        break;
                    case 50:
                        iZzb2 = this.zzut.zzb(i9, zznd.zzo(t, j2), zzaq(i6));
                        i7 += iZzb2;
                        break;
                    case 51:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzb(i9, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                            i7 += iZzb2;
                        }
                        break;
                    case 52:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzb(i9, 0.0f);
                            i7 += iZzb2;
                        }
                        break;
                    case 53:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzd(i9, zzh(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 54:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zze(i9, zzh(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 55:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzg(i9, zzg(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 56:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzg(i9, 0L);
                            i7 += iZzb2;
                        }
                        break;
                    case 57:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzj(i9, 0);
                            i7 += iZzb2;
                        }
                        break;
                    case 58:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzc(i9, true);
                            i7 += iZzb2;
                        }
                        break;
                    case 59:
                        if (zza(t, i9, i6)) {
                            Object objZzo2 = zznd.zzo(t, j2);
                            if (objZzo2 instanceof zzjc) {
                                iZzb2 = zzjr.zzc(i9, (zzjc) objZzo2);
                            } else {
                                iZzb2 = zzjr.zzb(i9, (String) objZzo2);
                            }
                            i7 += iZzb2;
                        }
                        break;
                    case 60:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzmh.zzc(i9, zznd.zzo(t, j2), zzap(i6));
                            i7 += iZzb2;
                        }
                        break;
                    case 61:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzc(i9, (zzjc) zznd.zzo(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 62:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzh(i9, zzg(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 63:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzl(i9, zzg(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 64:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzk(i9, 0);
                            i7 += iZzb2;
                        }
                        break;
                    case 65:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzh(i9, 0L);
                            i7 += iZzb2;
                        }
                        break;
                    case 66:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzi(i9, zzg(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 67:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzf(i9, zzh(t, j2));
                            i7 += iZzb2;
                        }
                        break;
                    case 68:
                        if (zza(t, i9, i6)) {
                            iZzb2 = zzjr.zzc(i9, (zzlq) zznd.zzo(t, j2), zzap(i6));
                            i7 += iZzb2;
                        }
                        break;
                }
                i6 += 3;
                i3 = 267386880;
            }
            return i7 + zza(this.zzur, t);
        }
        Unsafe unsafe2 = zzuc;
        int i11 = 0;
        int iZzb3 = 0;
        int i12 = -1;
        int i13 = 0;
        while (i11 < this.zzud.length) {
            int iZzas2 = zzas(i11);
            int[] iArr = this.zzud;
            int i14 = iArr[i11];
            int i15 = (iZzas2 & 267386880) >>> 20;
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
                i = (!this.zzul || i15 < zzke.DOUBLE_LIST_PACKED.id() || i15 > zzke.SINT64_LIST_PACKED.id()) ? 0 : this.zzud[i11 + 2] & i4;
                i2 = 0;
            }
            long j3 = iZzas2 & i4;
            switch (i15) {
                case 0:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzb3 += zzjr.zzb(i14, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    }
                    break;
                case 1:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzb3 += zzjr.zzb(i14, 0.0f);
                    }
                    break;
                case 2:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzd = zzjr.zzd(i14, unsafe2.getLong(t, j3));
                        iZzb3 += iZzd;
                    }
                    break;
                case 3:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzd = zzjr.zze(i14, unsafe2.getLong(t, j3));
                        iZzb3 += iZzd;
                    }
                    break;
                case 4:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        iZzd = zzjr.zzg(i14, unsafe2.getInt(t, j3));
                        iZzb3 += iZzd;
                    }
                    break;
                case 5:
                    if ((i13 & i2) != 0) {
                        j = 0;
                        iZzd = zzjr.zzg(i14, 0L);
                        iZzb3 += iZzd;
                    } else {
                        j = 0;
                    }
                    break;
                case 6:
                    if ((i13 & i2) != 0) {
                        iZzb3 += zzjr.zzj(i14, 0);
                    }
                    j = 0;
                    break;
                case 7:
                    if ((i13 & i2) != 0) {
                        iZzb3 += zzjr.zzc(i14, true);
                    }
                    j = 0;
                    break;
                case 8:
                    if ((i13 & i2) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzjc) {
                            iZzb = zzjr.zzc(i14, (zzjc) object);
                        } else {
                            iZzb = zzjr.zzb(i14, (String) object);
                        }
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 9:
                    if ((i13 & i2) != 0) {
                        iZzb = zzmh.zzc(i14, unsafe2.getObject(t, j3), zzap(i11));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 10:
                    if ((i13 & i2) != 0) {
                        iZzb = zzjr.zzc(i14, (zzjc) unsafe2.getObject(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 11:
                    if ((i13 & i2) != 0) {
                        iZzb = zzjr.zzh(i14, unsafe2.getInt(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 12:
                    if ((i13 & i2) != 0) {
                        iZzb = zzjr.zzl(i14, unsafe2.getInt(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 13:
                    if ((i13 & i2) != 0) {
                        iZzk = zzjr.zzk(i14, 0);
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 14:
                    if ((i13 & i2) != 0) {
                        iZzb = zzjr.zzh(i14, 0L);
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 15:
                    if ((i13 & i2) != 0) {
                        iZzb = zzjr.zzi(i14, unsafe2.getInt(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 16:
                    if ((i13 & i2) != 0) {
                        iZzb = zzjr.zzf(i14, unsafe2.getLong(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 17:
                    if ((i13 & i2) != 0) {
                        iZzb = zzjr.zzc(i14, (zzlq) unsafe2.getObject(t, j3), zzap(i11));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 18:
                    iZzb = zzmh.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 19:
                    iZzv = zzmh.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 20:
                    iZzv = zzmh.zzo(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 21:
                    iZzv = zzmh.zzp(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 22:
                    iZzv = zzmh.zzs(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 23:
                    iZzv = zzmh.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 24:
                    iZzv = zzmh.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 25:
                    iZzv = zzmh.zzx(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 26:
                    iZzb = zzmh.zzc(i14, (List) unsafe2.getObject(t, j3));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 27:
                    iZzb = zzmh.zzc(i14, (List<?>) unsafe2.getObject(t, j3), zzap(i11));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 28:
                    iZzb = zzmh.zzd(i14, (List<zzjc>) unsafe2.getObject(t, j3));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 29:
                    iZzb = zzmh.zzt(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 30:
                    iZzv = zzmh.zzr(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 31:
                    iZzv = zzmh.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 32:
                    iZzv = zzmh.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 33:
                    iZzv = zzmh.zzu(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 34:
                    iZzv = zzmh.zzq(i14, (List) unsafe2.getObject(t, j3), false);
                    iZzb3 += iZzv;
                    j = 0;
                    break;
                case 35:
                    iZzi = zzmh.zzi((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 36:
                    iZzi = zzmh.zzh((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 37:
                    iZzi = zzmh.zza((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 38:
                    iZzi = zzmh.zzb((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 39:
                    iZzi = zzmh.zze((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 40:
                    iZzi = zzmh.zzi((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 41:
                    iZzi = zzmh.zzh((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 42:
                    iZzi = zzmh.zzj((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 43:
                    iZzi = zzmh.zzf((List<Integer>) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 44:
                    iZzi = zzmh.zzd((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 45:
                    iZzi = zzmh.zzh((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 46:
                    iZzi = zzmh.zzi((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 47:
                    iZzi = zzmh.zzg((List<Integer>) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 48:
                    iZzi = zzmh.zzc((List) unsafe2.getObject(t, j3));
                    if (iZzi > 0) {
                        if (this.zzul) {
                            unsafe2.putInt(t, i, iZzi);
                        }
                        iZzab = zzjr.zzab(i14);
                        iZzad = zzjr.zzad(iZzi);
                        iZzk = iZzab + iZzad + iZzi;
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 49:
                    iZzb = zzmh.zzd(i14, (List) unsafe2.getObject(t, j3), zzap(i11));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 50:
                    iZzb = this.zzut.zzb(i14, unsafe2.getObject(t, j3), zzaq(i11));
                    iZzb3 += iZzb;
                    j = 0;
                    break;
                case 51:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzb(i14, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 52:
                    if (zza(t, i14, i11)) {
                        iZzk = zzjr.zzb(i14, 0.0f);
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 53:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzd(i14, zzh(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 54:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zze(i14, zzh(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 55:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzg(i14, zzg(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 56:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzg(i14, 0L);
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 57:
                    if (zza(t, i14, i11)) {
                        iZzk = zzjr.zzj(i14, 0);
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 58:
                    if (zza(t, i14, i11)) {
                        iZzk = zzjr.zzc(i14, true);
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 59:
                    if (zza(t, i14, i11)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzjc) {
                            iZzb = zzjr.zzc(i14, (zzjc) object2);
                        } else {
                            iZzb = zzjr.zzb(i14, (String) object2);
                        }
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 60:
                    if (zza(t, i14, i11)) {
                        iZzb = zzmh.zzc(i14, unsafe2.getObject(t, j3), zzap(i11));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 61:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzc(i14, (zzjc) unsafe2.getObject(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 62:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzh(i14, zzg(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 63:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzl(i14, zzg(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 64:
                    if (zza(t, i14, i11)) {
                        iZzk = zzjr.zzk(i14, 0);
                        iZzb3 += iZzk;
                    }
                    j = 0;
                    break;
                case 65:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzh(i14, 0L);
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 66:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzi(i14, zzg(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 67:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzf(i14, zzh(t, j3));
                        iZzb3 += iZzb;
                    }
                    j = 0;
                    break;
                case 68:
                    if (zza(t, i14, i11)) {
                        iZzb = zzjr.zzc(i14, (zzlq) unsafe2.getObject(t, j3), zzap(i11));
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
        int iZza = iZzb3 + zza(this.zzur, t);
        if (!this.zzui) {
            return iZza;
        }
        zzkb<T> zzkbVarZzb = this.zzus.zzb(t);
        int iZzb4 = 0;
        for (int i17 = 0; i17 < zzkbVarZzb.zzos.zzer(); i17++) {
            Map.Entry entryZzaw = zzkbVarZzb.zzos.zzaw(i17);
            iZzb4 += zzkb.zzb((zzkd<?>) entryZzaw.getKey(), entryZzaw.getValue());
        }
        for (Map.Entry entry : zzkbVarZzb.zzos.zzes()) {
            iZzb4 += zzkb.zzb((zzkd<?>) entry.getKey(), entry.getValue());
        }
        return iZza + iZzb4;
    }

    private static <UT, UB> int zza(zzmx<UT, UB> zzmxVar, T t) {
        return zzmxVar.zzn(zzmxVar.zzr(t));
    }

    private static <E> List<E> zzd(Object obj, long j) {
        return (List) zznd.zzo(obj, j);
    }

    /* JADX WARN: Code duplicated, block: B:178:0x054a  */
    /* JADX WARN: Code duplicated, block: B:9:0x0032  */
    @Override // com.google.android.gms.internal.drive.zzmf
    public final void zza(T t, zzns zznsVar) throws IOException {
        Iterator it;
        Map.Entry<?, ?> entry;
        Iterator itDescendingIterator;
        Map.Entry<?, ?> entry2;
        if (zznsVar.zzcd() == zzkk.zze.zzsj) {
            zza(this.zzur, t, zznsVar);
            if (this.zzui) {
                zzkb<T> zzkbVarZzb = this.zzus.zzb(t);
                if (zzkbVarZzb.zzos.isEmpty()) {
                    itDescendingIterator = null;
                    entry2 = null;
                } else {
                    itDescendingIterator = zzkbVarZzb.descendingIterator();
                    entry2 = (Map.Entry) itDescendingIterator.next();
                }
            } else {
                itDescendingIterator = null;
                entry2 = null;
            }
            for (int length = this.zzud.length - 3; length >= 0; length -= 3) {
                int iZzas = zzas(length);
                int i = this.zzud[length];
                while (entry2 != null && this.zzus.zza(entry2) > i) {
                    this.zzus.zza(zznsVar, entry2);
                    entry2 = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
                }
                switch ((iZzas & 267386880) >>> 20) {
                    case 0:
                        if (zza(t, length)) {
                            zznsVar.zza(i, zznd.zzn(t, iZzas & 1048575));
                        }
                        break;
                    case 1:
                        if (zza(t, length)) {
                            zznsVar.zza(i, zznd.zzm(t, iZzas & 1048575));
                        }
                        break;
                    case 2:
                        if (zza(t, length)) {
                            zznsVar.zzi(i, zznd.zzk(t, iZzas & 1048575));
                        }
                        break;
                    case 3:
                        if (zza(t, length)) {
                            zznsVar.zza(i, zznd.zzk(t, iZzas & 1048575));
                        }
                        break;
                    case 4:
                        if (zza(t, length)) {
                            zznsVar.zzc(i, zznd.zzj(t, iZzas & 1048575));
                        }
                        break;
                    case 5:
                        if (zza(t, length)) {
                            zznsVar.zzc(i, zznd.zzk(t, iZzas & 1048575));
                        }
                        break;
                    case 6:
                        if (zza(t, length)) {
                            zznsVar.zzf(i, zznd.zzj(t, iZzas & 1048575));
                        }
                        break;
                    case 7:
                        if (zza(t, length)) {
                            zznsVar.zzb(i, zznd.zzl(t, iZzas & 1048575));
                        }
                        break;
                    case 8:
                        if (zza(t, length)) {
                            zza(i, zznd.zzo(t, iZzas & 1048575), zznsVar);
                        }
                        break;
                    case 9:
                        if (zza(t, length)) {
                            zznsVar.zza(i, zznd.zzo(t, iZzas & 1048575), zzap(length));
                        }
                        break;
                    case 10:
                        if (zza(t, length)) {
                            zznsVar.zza(i, (zzjc) zznd.zzo(t, iZzas & 1048575));
                        }
                        break;
                    case 11:
                        if (zza(t, length)) {
                            zznsVar.zzd(i, zznd.zzj(t, iZzas & 1048575));
                        }
                        break;
                    case 12:
                        if (zza(t, length)) {
                            zznsVar.zzn(i, zznd.zzj(t, iZzas & 1048575));
                        }
                        break;
                    case 13:
                        if (zza(t, length)) {
                            zznsVar.zzm(i, zznd.zzj(t, iZzas & 1048575));
                        }
                        break;
                    case 14:
                        if (zza(t, length)) {
                            zznsVar.zzj(i, zznd.zzk(t, iZzas & 1048575));
                        }
                        break;
                    case 15:
                        if (zza(t, length)) {
                            zznsVar.zze(i, zznd.zzj(t, iZzas & 1048575));
                        }
                        break;
                    case 16:
                        if (zza(t, length)) {
                            zznsVar.zzb(i, zznd.zzk(t, iZzas & 1048575));
                        }
                        break;
                    case 17:
                        if (zza(t, length)) {
                            zznsVar.zzb(i, zznd.zzo(t, iZzas & 1048575), zzap(length));
                        }
                        break;
                    case 18:
                        zzmh.zza(this.zzud[length], (List<Double>) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 19:
                        zzmh.zzb(this.zzud[length], (List<Float>) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 20:
                        zzmh.zzc(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 21:
                        zzmh.zzd(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 22:
                        zzmh.zzh(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 23:
                        zzmh.zzf(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 24:
                        zzmh.zzk(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 25:
                        zzmh.zzn(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 26:
                        zzmh.zza(this.zzud[length], (List<String>) zznd.zzo(t, iZzas & 1048575), zznsVar);
                        break;
                    case 27:
                        zzmh.zza(this.zzud[length], (List<?>) zznd.zzo(t, iZzas & 1048575), zznsVar, zzap(length));
                        break;
                    case 28:
                        zzmh.zzb(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar);
                        break;
                    case 29:
                        zzmh.zzi(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 30:
                        zzmh.zzm(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 31:
                        zzmh.zzl(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 32:
                        zzmh.zzg(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 33:
                        zzmh.zzj(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 34:
                        zzmh.zze(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, false);
                        break;
                    case 35:
                        zzmh.zza(this.zzud[length], (List<Double>) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 36:
                        zzmh.zzb(this.zzud[length], (List<Float>) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 37:
                        zzmh.zzc(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 38:
                        zzmh.zzd(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 39:
                        zzmh.zzh(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 40:
                        zzmh.zzf(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 41:
                        zzmh.zzk(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 42:
                        zzmh.zzn(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 43:
                        zzmh.zzi(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 44:
                        zzmh.zzm(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 45:
                        zzmh.zzl(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 46:
                        zzmh.zzg(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 47:
                        zzmh.zzj(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 48:
                        zzmh.zze(this.zzud[length], (List) zznd.zzo(t, iZzas & 1048575), zznsVar, true);
                        break;
                    case 49:
                        zzmh.zzb(this.zzud[length], (List<?>) zznd.zzo(t, iZzas & 1048575), zznsVar, zzap(length));
                        break;
                    case 50:
                        zza(zznsVar, i, zznd.zzo(t, iZzas & 1048575), length);
                        break;
                    case 51:
                        if (zza(t, i, length)) {
                            zznsVar.zza(i, zze(t, iZzas & 1048575));
                        }
                        break;
                    case 52:
                        if (zza(t, i, length)) {
                            zznsVar.zza(i, zzf(t, iZzas & 1048575));
                        }
                        break;
                    case 53:
                        if (zza(t, i, length)) {
                            zznsVar.zzi(i, zzh(t, iZzas & 1048575));
                        }
                        break;
                    case 54:
                        if (zza(t, i, length)) {
                            zznsVar.zza(i, zzh(t, iZzas & 1048575));
                        }
                        break;
                    case 55:
                        if (zza(t, i, length)) {
                            zznsVar.zzc(i, zzg(t, iZzas & 1048575));
                        }
                        break;
                    case 56:
                        if (zza(t, i, length)) {
                            zznsVar.zzc(i, zzh(t, iZzas & 1048575));
                        }
                        break;
                    case 57:
                        if (zza(t, i, length)) {
                            zznsVar.zzf(i, zzg(t, iZzas & 1048575));
                        }
                        break;
                    case 58:
                        if (zza(t, i, length)) {
                            zznsVar.zzb(i, zzi(t, iZzas & 1048575));
                        }
                        break;
                    case 59:
                        if (zza(t, i, length)) {
                            zza(i, zznd.zzo(t, iZzas & 1048575), zznsVar);
                        }
                        break;
                    case 60:
                        if (zza(t, i, length)) {
                            zznsVar.zza(i, zznd.zzo(t, iZzas & 1048575), zzap(length));
                        }
                        break;
                    case 61:
                        if (zza(t, i, length)) {
                            zznsVar.zza(i, (zzjc) zznd.zzo(t, iZzas & 1048575));
                        }
                        break;
                    case 62:
                        if (zza(t, i, length)) {
                            zznsVar.zzd(i, zzg(t, iZzas & 1048575));
                        }
                        break;
                    case 63:
                        if (zza(t, i, length)) {
                            zznsVar.zzn(i, zzg(t, iZzas & 1048575));
                        }
                        break;
                    case 64:
                        if (zza(t, i, length)) {
                            zznsVar.zzm(i, zzg(t, iZzas & 1048575));
                        }
                        break;
                    case 65:
                        if (zza(t, i, length)) {
                            zznsVar.zzj(i, zzh(t, iZzas & 1048575));
                        }
                        break;
                    case 66:
                        if (zza(t, i, length)) {
                            zznsVar.zze(i, zzg(t, iZzas & 1048575));
                        }
                        break;
                    case 67:
                        if (zza(t, i, length)) {
                            zznsVar.zzb(i, zzh(t, iZzas & 1048575));
                        }
                        break;
                    case 68:
                        if (zza(t, i, length)) {
                            zznsVar.zzb(i, zznd.zzo(t, iZzas & 1048575), zzap(length));
                        }
                        break;
                }
            }
            while (entry2 != null) {
                this.zzus.zza(zznsVar, entry2);
                entry2 = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
            }
            return;
        }
        if (this.zzuk) {
            if (this.zzui) {
                zzkb<T> zzkbVarZzb2 = this.zzus.zzb(t);
                if (zzkbVarZzb2.zzos.isEmpty()) {
                    it = null;
                    entry = null;
                } else {
                    it = zzkbVarZzb2.iterator();
                    entry = (Map.Entry) it.next();
                }
            } else {
                it = null;
                entry = null;
            }
            int length2 = this.zzud.length;
            Map.Entry<?, ?> entry3 = entry;
            for (int i2 = 0; i2 < length2; i2 += 3) {
                int iZzas2 = zzas(i2);
                int i3 = this.zzud[i2];
                while (entry3 != null && this.zzus.zza(entry3) <= i3) {
                    this.zzus.zza(zznsVar, entry3);
                    entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
                }
                switch ((iZzas2 & 267386880) >>> 20) {
                    case 0:
                        if (zza(t, i2)) {
                            zznsVar.zza(i3, zznd.zzn(t, iZzas2 & 1048575));
                        }
                        break;
                    case 1:
                        if (zza(t, i2)) {
                            zznsVar.zza(i3, zznd.zzm(t, iZzas2 & 1048575));
                        }
                        break;
                    case 2:
                        if (zza(t, i2)) {
                            zznsVar.zzi(i3, zznd.zzk(t, iZzas2 & 1048575));
                        }
                        break;
                    case 3:
                        if (zza(t, i2)) {
                            zznsVar.zza(i3, zznd.zzk(t, iZzas2 & 1048575));
                        }
                        break;
                    case 4:
                        if (zza(t, i2)) {
                            zznsVar.zzc(i3, zznd.zzj(t, iZzas2 & 1048575));
                        }
                        break;
                    case 5:
                        if (zza(t, i2)) {
                            zznsVar.zzc(i3, zznd.zzk(t, iZzas2 & 1048575));
                        }
                        break;
                    case 6:
                        if (zza(t, i2)) {
                            zznsVar.zzf(i3, zznd.zzj(t, iZzas2 & 1048575));
                        }
                        break;
                    case 7:
                        if (zza(t, i2)) {
                            zznsVar.zzb(i3, zznd.zzl(t, iZzas2 & 1048575));
                        }
                        break;
                    case 8:
                        if (zza(t, i2)) {
                            zza(i3, zznd.zzo(t, iZzas2 & 1048575), zznsVar);
                        }
                        break;
                    case 9:
                        if (zza(t, i2)) {
                            zznsVar.zza(i3, zznd.zzo(t, iZzas2 & 1048575), zzap(i2));
                        }
                        break;
                    case 10:
                        if (zza(t, i2)) {
                            zznsVar.zza(i3, (zzjc) zznd.zzo(t, iZzas2 & 1048575));
                        }
                        break;
                    case 11:
                        if (zza(t, i2)) {
                            zznsVar.zzd(i3, zznd.zzj(t, iZzas2 & 1048575));
                        }
                        break;
                    case 12:
                        if (zza(t, i2)) {
                            zznsVar.zzn(i3, zznd.zzj(t, iZzas2 & 1048575));
                        }
                        break;
                    case 13:
                        if (zza(t, i2)) {
                            zznsVar.zzm(i3, zznd.zzj(t, iZzas2 & 1048575));
                        }
                        break;
                    case 14:
                        if (zza(t, i2)) {
                            zznsVar.zzj(i3, zznd.zzk(t, iZzas2 & 1048575));
                        }
                        break;
                    case 15:
                        if (zza(t, i2)) {
                            zznsVar.zze(i3, zznd.zzj(t, iZzas2 & 1048575));
                        }
                        break;
                    case 16:
                        if (zza(t, i2)) {
                            zznsVar.zzb(i3, zznd.zzk(t, iZzas2 & 1048575));
                        }
                        break;
                    case 17:
                        if (zza(t, i2)) {
                            zznsVar.zzb(i3, zznd.zzo(t, iZzas2 & 1048575), zzap(i2));
                        }
                        break;
                    case 18:
                        zzmh.zza(this.zzud[i2], (List<Double>) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 19:
                        zzmh.zzb(this.zzud[i2], (List<Float>) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 20:
                        zzmh.zzc(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 21:
                        zzmh.zzd(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 22:
                        zzmh.zzh(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 23:
                        zzmh.zzf(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 24:
                        zzmh.zzk(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 25:
                        zzmh.zzn(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 26:
                        zzmh.zza(this.zzud[i2], (List<String>) zznd.zzo(t, iZzas2 & 1048575), zznsVar);
                        break;
                    case 27:
                        zzmh.zza(this.zzud[i2], (List<?>) zznd.zzo(t, iZzas2 & 1048575), zznsVar, zzap(i2));
                        break;
                    case 28:
                        zzmh.zzb(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar);
                        break;
                    case 29:
                        zzmh.zzi(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 30:
                        zzmh.zzm(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 31:
                        zzmh.zzl(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 32:
                        zzmh.zzg(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 33:
                        zzmh.zzj(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 34:
                        zzmh.zze(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, false);
                        break;
                    case 35:
                        zzmh.zza(this.zzud[i2], (List<Double>) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 36:
                        zzmh.zzb(this.zzud[i2], (List<Float>) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 37:
                        zzmh.zzc(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 38:
                        zzmh.zzd(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 39:
                        zzmh.zzh(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 40:
                        zzmh.zzf(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 41:
                        zzmh.zzk(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 42:
                        zzmh.zzn(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 43:
                        zzmh.zzi(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 44:
                        zzmh.zzm(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 45:
                        zzmh.zzl(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 46:
                        zzmh.zzg(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 47:
                        zzmh.zzj(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 48:
                        zzmh.zze(this.zzud[i2], (List) zznd.zzo(t, iZzas2 & 1048575), zznsVar, true);
                        break;
                    case 49:
                        zzmh.zzb(this.zzud[i2], (List<?>) zznd.zzo(t, iZzas2 & 1048575), zznsVar, zzap(i2));
                        break;
                    case 50:
                        zza(zznsVar, i3, zznd.zzo(t, iZzas2 & 1048575), i2);
                        break;
                    case 51:
                        if (zza(t, i3, i2)) {
                            zznsVar.zza(i3, zze(t, iZzas2 & 1048575));
                        }
                        break;
                    case 52:
                        if (zza(t, i3, i2)) {
                            zznsVar.zza(i3, zzf(t, iZzas2 & 1048575));
                        }
                        break;
                    case 53:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzi(i3, zzh(t, iZzas2 & 1048575));
                        }
                        break;
                    case 54:
                        if (zza(t, i3, i2)) {
                            zznsVar.zza(i3, zzh(t, iZzas2 & 1048575));
                        }
                        break;
                    case 55:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzc(i3, zzg(t, iZzas2 & 1048575));
                        }
                        break;
                    case 56:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzc(i3, zzh(t, iZzas2 & 1048575));
                        }
                        break;
                    case 57:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzf(i3, zzg(t, iZzas2 & 1048575));
                        }
                        break;
                    case 58:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzb(i3, zzi(t, iZzas2 & 1048575));
                        }
                        break;
                    case 59:
                        if (zza(t, i3, i2)) {
                            zza(i3, zznd.zzo(t, iZzas2 & 1048575), zznsVar);
                        }
                        break;
                    case 60:
                        if (zza(t, i3, i2)) {
                            zznsVar.zza(i3, zznd.zzo(t, iZzas2 & 1048575), zzap(i2));
                        }
                        break;
                    case 61:
                        if (zza(t, i3, i2)) {
                            zznsVar.zza(i3, (zzjc) zznd.zzo(t, iZzas2 & 1048575));
                        }
                        break;
                    case 62:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzd(i3, zzg(t, iZzas2 & 1048575));
                        }
                        break;
                    case 63:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzn(i3, zzg(t, iZzas2 & 1048575));
                        }
                        break;
                    case 64:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzm(i3, zzg(t, iZzas2 & 1048575));
                        }
                        break;
                    case 65:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzj(i3, zzh(t, iZzas2 & 1048575));
                        }
                        break;
                    case 66:
                        if (zza(t, i3, i2)) {
                            zznsVar.zze(i3, zzg(t, iZzas2 & 1048575));
                        }
                        break;
                    case 67:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzb(i3, zzh(t, iZzas2 & 1048575));
                        }
                        break;
                    case 68:
                        if (zza(t, i3, i2)) {
                            zznsVar.zzb(i3, zznd.zzo(t, iZzas2 & 1048575), zzap(i2));
                        }
                        break;
                }
            }
            while (entry3 != null) {
                this.zzus.zza(zznsVar, entry3);
                entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            zza(this.zzur, t, zznsVar);
            return;
        }
        zzb(t, zznsVar);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0023  */
    private final void zzb(T t, zzns zznsVar) throws IOException {
        Iterator it;
        Map.Entry<?, ?> entry;
        int i;
        if (this.zzui) {
            zzkb<T> zzkbVarZzb = this.zzus.zzb(t);
            if (zzkbVarZzb.zzos.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = zzkbVarZzb.iterator();
                entry = (Map.Entry) it.next();
            }
        } else {
            it = null;
            entry = null;
        }
        int i2 = -1;
        int length = this.zzud.length;
        Unsafe unsafe = zzuc;
        Map.Entry<?, ?> entry2 = entry;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int iZzas = zzas(i4);
            int[] iArr = this.zzud;
            int i5 = iArr[i4];
            int i6 = (267386880 & iZzas) >>> 20;
            if (this.zzuk || i6 > 17) {
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
            while (entry2 != null && this.zzus.zza(entry2) <= i5) {
                this.zzus.zza(zznsVar, entry2);
                entry2 = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            long j = iZzas & 1048575;
            switch (i6) {
                case 0:
                    if ((i3 & i) != 0) {
                        zznsVar.zza(i5, zznd.zzn(t, j));
                        continue;
                    }
                    break;
                case 1:
                    if ((i3 & i) != 0) {
                        zznsVar.zza(i5, zznd.zzm(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    if ((i3 & i) != 0) {
                        zznsVar.zzi(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 3:
                    if ((i3 & i) != 0) {
                        zznsVar.zza(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 4:
                    if ((i3 & i) != 0) {
                        zznsVar.zzc(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 5:
                    if ((i3 & i) != 0) {
                        zznsVar.zzc(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 6:
                    if ((i3 & i) != 0) {
                        zznsVar.zzf(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 7:
                    if ((i3 & i) != 0) {
                        zznsVar.zzb(i5, zznd.zzl(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 8:
                    if ((i3 & i) != 0) {
                        zza(i5, unsafe.getObject(t, j), zznsVar);
                    } else {
                        continue;
                    }
                    break;
                case 9:
                    if ((i3 & i) != 0) {
                        zznsVar.zza(i5, unsafe.getObject(t, j), zzap(i4));
                    } else {
                        continue;
                    }
                    break;
                case 10:
                    if ((i3 & i) != 0) {
                        zznsVar.zza(i5, (zzjc) unsafe.getObject(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 11:
                    if ((i3 & i) != 0) {
                        zznsVar.zzd(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 12:
                    if ((i3 & i) != 0) {
                        zznsVar.zzn(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 13:
                    if ((i3 & i) != 0) {
                        zznsVar.zzm(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 14:
                    if ((i3 & i) != 0) {
                        zznsVar.zzj(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 15:
                    if ((i3 & i) != 0) {
                        zznsVar.zze(i5, unsafe.getInt(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 16:
                    if ((i3 & i) != 0) {
                        zznsVar.zzb(i5, unsafe.getLong(t, j));
                    } else {
                        continue;
                    }
                    break;
                case 17:
                    if ((i3 & i) != 0) {
                        zznsVar.zzb(i5, unsafe.getObject(t, j), zzap(i4));
                    } else {
                        continue;
                    }
                    break;
                case 18:
                    zzmh.zza(this.zzud[i4], (List<Double>) unsafe.getObject(t, j), zznsVar, false);
                    continue;
                    break;
                case 19:
                    zzmh.zzb(this.zzud[i4], (List<Float>) unsafe.getObject(t, j), zznsVar, false);
                    continue;
                    break;
                case 20:
                    zzmh.zzc(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    continue;
                    break;
                case 21:
                    zzmh.zzd(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    continue;
                    break;
                case 22:
                    zzmh.zzh(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    continue;
                    break;
                case 23:
                    zzmh.zzf(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    continue;
                    break;
                case 24:
                    zzmh.zzk(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    continue;
                    break;
                case 25:
                    zzmh.zzn(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    continue;
                    break;
                case 26:
                    zzmh.zza(this.zzud[i4], (List<String>) unsafe.getObject(t, j), zznsVar);
                    break;
                case 27:
                    zzmh.zza(this.zzud[i4], (List<?>) unsafe.getObject(t, j), zznsVar, zzap(i4));
                    break;
                case 28:
                    zzmh.zzb(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar);
                    break;
                case 29:
                    zzmh.zzi(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    break;
                case 30:
                    zzmh.zzm(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    break;
                case 31:
                    zzmh.zzl(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    break;
                case 32:
                    zzmh.zzg(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    break;
                case 33:
                    zzmh.zzj(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    break;
                case 34:
                    zzmh.zze(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, false);
                    break;
                case 35:
                    zzmh.zza(this.zzud[i4], (List<Double>) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 36:
                    zzmh.zzb(this.zzud[i4], (List<Float>) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 37:
                    zzmh.zzc(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 38:
                    zzmh.zzd(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 39:
                    zzmh.zzh(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 40:
                    zzmh.zzf(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 41:
                    zzmh.zzk(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 42:
                    zzmh.zzn(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 43:
                    zzmh.zzi(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 44:
                    zzmh.zzm(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 45:
                    zzmh.zzl(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 46:
                    zzmh.zzg(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 47:
                    zzmh.zzj(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 48:
                    zzmh.zze(this.zzud[i4], (List) unsafe.getObject(t, j), zznsVar, true);
                    break;
                case 49:
                    zzmh.zzb(this.zzud[i4], (List<?>) unsafe.getObject(t, j), zznsVar, zzap(i4));
                    break;
                case 50:
                    zza(zznsVar, i5, unsafe.getObject(t, j), i4);
                    break;
                case 51:
                    if (zza(t, i5, i4)) {
                        zznsVar.zza(i5, zze(t, j));
                    }
                    break;
                case 52:
                    if (zza(t, i5, i4)) {
                        zznsVar.zza(i5, zzf(t, j));
                    }
                    break;
                case 53:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzi(i5, zzh(t, j));
                    }
                    break;
                case 54:
                    if (zza(t, i5, i4)) {
                        zznsVar.zza(i5, zzh(t, j));
                    }
                    break;
                case 55:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzc(i5, zzg(t, j));
                    }
                    break;
                case 56:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzc(i5, zzh(t, j));
                    }
                    break;
                case 57:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzf(i5, zzg(t, j));
                    }
                    break;
                case 58:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzb(i5, zzi(t, j));
                    }
                    break;
                case 59:
                    if (zza(t, i5, i4)) {
                        zza(i5, unsafe.getObject(t, j), zznsVar);
                    }
                    break;
                case 60:
                    if (zza(t, i5, i4)) {
                        zznsVar.zza(i5, unsafe.getObject(t, j), zzap(i4));
                    }
                    break;
                case 61:
                    if (zza(t, i5, i4)) {
                        zznsVar.zza(i5, (zzjc) unsafe.getObject(t, j));
                    }
                    break;
                case 62:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzd(i5, zzg(t, j));
                    }
                    break;
                case 63:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzn(i5, zzg(t, j));
                    }
                    break;
                case 64:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzm(i5, zzg(t, j));
                    }
                    break;
                case 65:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzj(i5, zzh(t, j));
                    }
                    break;
                case 66:
                    if (zza(t, i5, i4)) {
                        zznsVar.zze(i5, zzg(t, j));
                    }
                    break;
                case 67:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzb(i5, zzh(t, j));
                    }
                    break;
                case 68:
                    if (zza(t, i5, i4)) {
                        zznsVar.zzb(i5, unsafe.getObject(t, j), zzap(i4));
                    }
                    break;
            }
        }
        Map.Entry<?, ?> entry3 = entry2;
        while (entry3 != null) {
            this.zzus.zza(zznsVar, entry3);
            entry3 = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        zza(this.zzur, t, zznsVar);
    }

    private final <K, V> void zza(zzns zznsVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zznsVar.zza(i, this.zzut.zzm(zzaq(i2)), this.zzut.zzi(obj));
        }
    }

    private static <UT, UB> void zza(zzmx<UT, UB> zzmxVar, T t, zzns zznsVar) throws IOException {
        zzmxVar.zza(zzmxVar.zzr(t), zznsVar);
    }

    private static zzmy zzo(Object obj) {
        zzkk zzkkVar = (zzkk) obj;
        zzmy zzmyVar = zzkkVar.zzrq;
        if (zzmyVar != zzmy.zzfa()) {
            return zzmyVar;
        }
        zzmy zzmyVarZzfb = zzmy.zzfb();
        zzkkVar.zzrq = zzmyVarZzfb;
        return zzmyVarZzfb;
    }

    private static int zza(byte[] bArr, int i, int i2, zznm zznmVar, Class<?> cls, zziz zzizVar) throws IOException {
        switch (zzlv.zzox[zznmVar.ordinal()]) {
            case 1:
                int iZzb = zziy.zzb(bArr, i, zzizVar);
                zzizVar.zznm = Boolean.valueOf(zzizVar.zznl != 0);
                return iZzb;
            case 2:
                return zziy.zze(bArr, i, zzizVar);
            case 3:
                zzizVar.zznm = Double.valueOf(zziy.zzc(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzizVar.zznm = Integer.valueOf(zziy.zza(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzizVar.zznm = Long.valueOf(zziy.zzb(bArr, i));
                return i + 8;
            case 8:
                zzizVar.zznm = Float.valueOf(zziy.zzd(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int iZza = zziy.zza(bArr, i, zzizVar);
                zzizVar.zznm = Integer.valueOf(zzizVar.zznk);
                return iZza;
            case 12:
            case 13:
                int iZzb2 = zziy.zzb(bArr, i, zzizVar);
                zzizVar.zznm = Long.valueOf(zzizVar.zznl);
                return iZzb2;
            case 14:
                return zziy.zza(zzmd.zzej().zzf(cls), bArr, i, i2, zzizVar);
            case 15:
                int iZza2 = zziy.zza(bArr, i, zzizVar);
                zzizVar.zznm = Integer.valueOf(zzjo.zzw(zzizVar.zznk));
                return iZza2;
            case 16:
                int iZzb3 = zziy.zzb(bArr, i, zzizVar);
                zzizVar.zznm = Long.valueOf(zzjo.zzk(zzizVar.zznl));
                return iZzb3;
            case 17:
                return zziy.zzd(bArr, i, zzizVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zziz zzizVar) throws IOException {
        int iZza;
        int iZza2 = i;
        zzkp zzkpVarZzr = (zzkp) zzuc.getObject(t, j2);
        if (!zzkpVarZzr.zzbo()) {
            int size = zzkpVarZzr.size();
            zzkpVarZzr = zzkpVarZzr.zzr(size == 0 ? 10 : size << 1);
            zzuc.putObject(t, j2, zzkpVarZzr);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzju zzjuVar = (zzju) zzkpVarZzr;
                    int iZza3 = zziy.zza(bArr, iZza2, zzizVar);
                    int i8 = zzizVar.zznk + iZza3;
                    while (iZza3 < i8) {
                        zzjuVar.zzc(zziy.zzc(bArr, iZza3));
                        iZza3 += 8;
                    }
                    if (iZza3 == i8) {
                        return iZza3;
                    }
                    throw zzkq.zzdi();
                }
                if (i5 == 1) {
                    zzju zzjuVar2 = (zzju) zzkpVarZzr;
                    zzjuVar2.zzc(zziy.zzc(bArr, i));
                    while (true) {
                        int i9 = iZza2 + 8;
                        if (i9 >= i2) {
                            return i9;
                        }
                        iZza2 = zziy.zza(bArr, i9, zzizVar);
                        if (i3 != zzizVar.zznk) {
                            return i9;
                        }
                        zzjuVar2.zzc(zziy.zzc(bArr, iZza2));
                    }
                }
                return iZza2;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzkh zzkhVar = (zzkh) zzkpVarZzr;
                    int iZza4 = zziy.zza(bArr, iZza2, zzizVar);
                    int i10 = zzizVar.zznk + iZza4;
                    while (iZza4 < i10) {
                        zzkhVar.zzc(zziy.zzd(bArr, iZza4));
                        iZza4 += 4;
                    }
                    if (iZza4 == i10) {
                        return iZza4;
                    }
                    throw zzkq.zzdi();
                }
                if (i5 == 5) {
                    zzkh zzkhVar2 = (zzkh) zzkpVarZzr;
                    zzkhVar2.zzc(zziy.zzd(bArr, i));
                    while (true) {
                        int i11 = iZza2 + 4;
                        if (i11 >= i2) {
                            return i11;
                        }
                        iZza2 = zziy.zza(bArr, i11, zzizVar);
                        if (i3 != zzizVar.zznk) {
                            return i11;
                        }
                        zzkhVar2.zzc(zziy.zzd(bArr, iZza2));
                    }
                }
                return iZza2;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzle zzleVar = (zzle) zzkpVarZzr;
                    int iZza5 = zziy.zza(bArr, iZza2, zzizVar);
                    int i12 = zzizVar.zznk + iZza5;
                    while (iZza5 < i12) {
                        iZza5 = zziy.zzb(bArr, iZza5, zzizVar);
                        zzleVar.zzv(zzizVar.zznl);
                    }
                    if (iZza5 == i12) {
                        return iZza5;
                    }
                    throw zzkq.zzdi();
                }
                if (i5 == 0) {
                    zzle zzleVar2 = (zzle) zzkpVarZzr;
                    int iZzb = zziy.zzb(bArr, iZza2, zzizVar);
                    zzleVar2.zzv(zzizVar.zznl);
                    while (iZzb < i2) {
                        int iZza6 = zziy.zza(bArr, iZzb, zzizVar);
                        if (i3 != zzizVar.zznk) {
                            return iZzb;
                        }
                        iZzb = zziy.zzb(bArr, iZza6, zzizVar);
                        zzleVar2.zzv(zzizVar.zznl);
                    }
                    return iZzb;
                }
                return iZza2;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zziy.zza(bArr, iZza2, (zzkp<?>) zzkpVarZzr, zzizVar);
                }
                if (i5 == 0) {
                    return zziy.zza(i3, bArr, i, i2, (zzkp<?>) zzkpVarZzr, zzizVar);
                }
                return iZza2;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzle zzleVar3 = (zzle) zzkpVarZzr;
                    int iZza7 = zziy.zza(bArr, iZza2, zzizVar);
                    int i13 = zzizVar.zznk + iZza7;
                    while (iZza7 < i13) {
                        zzleVar3.zzv(zziy.zzb(bArr, iZza7));
                        iZza7 += 8;
                    }
                    if (iZza7 == i13) {
                        return iZza7;
                    }
                    throw zzkq.zzdi();
                }
                if (i5 == 1) {
                    zzle zzleVar4 = (zzle) zzkpVarZzr;
                    zzleVar4.zzv(zziy.zzb(bArr, i));
                    while (true) {
                        int i14 = iZza2 + 8;
                        if (i14 >= i2) {
                            return i14;
                        }
                        iZza2 = zziy.zza(bArr, i14, zzizVar);
                        if (i3 != zzizVar.zznk) {
                            return i14;
                        }
                        zzleVar4.zzv(zziy.zzb(bArr, iZza2));
                    }
                }
                return iZza2;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzkl zzklVar = (zzkl) zzkpVarZzr;
                    int iZza8 = zziy.zza(bArr, iZza2, zzizVar);
                    int i15 = zzizVar.zznk + iZza8;
                    while (iZza8 < i15) {
                        zzklVar.zzam(zziy.zza(bArr, iZza8));
                        iZza8 += 4;
                    }
                    if (iZza8 == i15) {
                        return iZza8;
                    }
                    throw zzkq.zzdi();
                }
                if (i5 == 5) {
                    zzkl zzklVar2 = (zzkl) zzkpVarZzr;
                    zzklVar2.zzam(zziy.zza(bArr, i));
                    while (true) {
                        int i16 = iZza2 + 4;
                        if (i16 >= i2) {
                            return i16;
                        }
                        iZza2 = zziy.zza(bArr, i16, zzizVar);
                        if (i3 != zzizVar.zznk) {
                            return i16;
                        }
                        zzklVar2.zzam(zziy.zza(bArr, iZza2));
                    }
                }
                return iZza2;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzja zzjaVar = (zzja) zzkpVarZzr;
                    iZza = zziy.zza(bArr, iZza2, zzizVar);
                    int i17 = zzizVar.zznk + iZza;
                    while (iZza < i17) {
                        iZza = zziy.zzb(bArr, iZza, zzizVar);
                        zzjaVar.addBoolean(zzizVar.zznl != 0);
                    }
                    if (iZza != i17) {
                        throw zzkq.zzdi();
                    }
                    return iZza;
                }
                if (i5 == 0) {
                    zzja zzjaVar2 = (zzja) zzkpVarZzr;
                    iZza2 = zziy.zzb(bArr, iZza2, zzizVar);
                    zzjaVar2.addBoolean(zzizVar.zznl != 0);
                    while (iZza2 < i2) {
                        int iZza9 = zziy.zza(bArr, iZza2, zzizVar);
                        if (i3 == zzizVar.zznk) {
                            iZza2 = zziy.zzb(bArr, iZza9, zzizVar);
                            zzjaVar2.addBoolean(zzizVar.zznl != 0);
                        }
                    }
                }
                return iZza2;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        iZza2 = zziy.zza(bArr, iZza2, zzizVar);
                        int i18 = zzizVar.zznk;
                        if (i18 < 0) {
                            throw zzkq.zzdj();
                        }
                        if (i18 == 0) {
                            zzkpVarZzr.add("");
                        } else {
                            zzkpVarZzr.add(new String(bArr, iZza2, i18, zzkm.UTF_8));
                            iZza2 += i18;
                        }
                        while (iZza2 < i2) {
                            int iZza10 = zziy.zza(bArr, iZza2, zzizVar);
                            if (i3 == zzizVar.zznk) {
                                iZza2 = zziy.zza(bArr, iZza10, zzizVar);
                                int i19 = zzizVar.zznk;
                                if (i19 < 0) {
                                    throw zzkq.zzdj();
                                }
                                if (i19 == 0) {
                                    zzkpVarZzr.add("");
                                } else {
                                    zzkpVarZzr.add(new String(bArr, iZza2, i19, zzkm.UTF_8));
                                    iZza2 += i19;
                                }
                            }
                        }
                    } else {
                        iZza2 = zziy.zza(bArr, iZza2, zzizVar);
                        int i20 = zzizVar.zznk;
                        if (i20 < 0) {
                            throw zzkq.zzdj();
                        }
                        if (i20 == 0) {
                            zzkpVarZzr.add("");
                        } else {
                            int i21 = iZza2 + i20;
                            if (!zznf.zze(bArr, iZza2, i21)) {
                                throw zzkq.zzdn();
                            }
                            zzkpVarZzr.add(new String(bArr, iZza2, i20, zzkm.UTF_8));
                            iZza2 = i21;
                        }
                        while (iZza2 < i2) {
                            int iZza11 = zziy.zza(bArr, iZza2, zzizVar);
                            if (i3 == zzizVar.zznk) {
                                iZza2 = zziy.zza(bArr, iZza11, zzizVar);
                                int i22 = zzizVar.zznk;
                                if (i22 < 0) {
                                    throw zzkq.zzdj();
                                }
                                if (i22 == 0) {
                                    zzkpVarZzr.add("");
                                } else {
                                    int i23 = iZza2 + i22;
                                    if (!zznf.zze(bArr, iZza2, i23)) {
                                        throw zzkq.zzdn();
                                    }
                                    zzkpVarZzr.add(new String(bArr, iZza2, i22, zzkm.UTF_8));
                                    iZza2 = i23;
                                }
                            }
                        }
                    }
                }
                return iZza2;
            case 27:
                if (i5 == 2) {
                    return zziy.zza(zzap(i6), i3, bArr, i, i2, zzkpVarZzr, zzizVar);
                }
                return iZza2;
            case 28:
                if (i5 == 2) {
                    int iZza12 = zziy.zza(bArr, iZza2, zzizVar);
                    int i24 = zzizVar.zznk;
                    if (i24 < 0) {
                        throw zzkq.zzdj();
                    }
                    if (i24 > bArr.length - iZza12) {
                        throw zzkq.zzdi();
                    }
                    if (i24 == 0) {
                        zzkpVarZzr.add(zzjc.zznq);
                    } else {
                        zzkpVarZzr.add(zzjc.zzb(bArr, iZza12, i24));
                        iZza12 += i24;
                    }
                    while (iZza12 < i2) {
                        int iZza13 = zziy.zza(bArr, iZza12, zzizVar);
                        if (i3 != zzizVar.zznk) {
                            return iZza12;
                        }
                        iZza12 = zziy.zza(bArr, iZza13, zzizVar);
                        int i25 = zzizVar.zznk;
                        if (i25 < 0) {
                            throw zzkq.zzdj();
                        }
                        if (i25 > bArr.length - iZza12) {
                            throw zzkq.zzdi();
                        }
                        if (i25 == 0) {
                            zzkpVarZzr.add(zzjc.zznq);
                        } else {
                            zzkpVarZzr.add(zzjc.zzb(bArr, iZza12, i25));
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
                        iZza = zziy.zza(i3, bArr, i, i2, (zzkp<?>) zzkpVarZzr, zzizVar);
                    }
                    return iZza2;
                }
                iZza = zziy.zza(bArr, iZza2, (zzkp<?>) zzkpVarZzr, zzizVar);
                zzkk zzkkVar = (zzkk) t;
                zzmy zzmyVar = zzkkVar.zzrq;
                if (zzmyVar == zzmy.zzfa()) {
                    zzmyVar = null;
                }
                zzmy zzmyVar2 = (zzmy) zzmh.zza(i4, zzkpVarZzr, zzar(i6), zzmyVar, this.zzur);
                if (zzmyVar2 != null) {
                    zzkkVar.zzrq = zzmyVar2;
                }
                return iZza;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzkl zzklVar3 = (zzkl) zzkpVarZzr;
                    int iZza14 = zziy.zza(bArr, iZza2, zzizVar);
                    int i26 = zzizVar.zznk + iZza14;
                    while (iZza14 < i26) {
                        iZza14 = zziy.zza(bArr, iZza14, zzizVar);
                        zzklVar3.zzam(zzjo.zzw(zzizVar.zznk));
                    }
                    if (iZza14 == i26) {
                        return iZza14;
                    }
                    throw zzkq.zzdi();
                }
                if (i5 == 0) {
                    zzkl zzklVar4 = (zzkl) zzkpVarZzr;
                    int iZza15 = zziy.zza(bArr, iZza2, zzizVar);
                    zzklVar4.zzam(zzjo.zzw(zzizVar.zznk));
                    while (iZza15 < i2) {
                        int iZza16 = zziy.zza(bArr, iZza15, zzizVar);
                        if (i3 != zzizVar.zznk) {
                            return iZza15;
                        }
                        iZza15 = zziy.zza(bArr, iZza16, zzizVar);
                        zzklVar4.zzam(zzjo.zzw(zzizVar.zznk));
                    }
                    return iZza15;
                }
                return iZza2;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzle zzleVar5 = (zzle) zzkpVarZzr;
                    int iZza17 = zziy.zza(bArr, iZza2, zzizVar);
                    int i27 = zzizVar.zznk + iZza17;
                    while (iZza17 < i27) {
                        iZza17 = zziy.zzb(bArr, iZza17, zzizVar);
                        zzleVar5.zzv(zzjo.zzk(zzizVar.zznl));
                    }
                    if (iZza17 == i27) {
                        return iZza17;
                    }
                    throw zzkq.zzdi();
                }
                if (i5 == 0) {
                    zzle zzleVar6 = (zzle) zzkpVarZzr;
                    int iZzb2 = zziy.zzb(bArr, iZza2, zzizVar);
                    zzleVar6.zzv(zzjo.zzk(zzizVar.zznl));
                    while (iZzb2 < i2) {
                        int iZza18 = zziy.zza(bArr, iZzb2, zzizVar);
                        if (i3 != zzizVar.zznk) {
                            return iZzb2;
                        }
                        iZzb2 = zziy.zzb(bArr, iZza18, zzizVar);
                        zzleVar6.zzv(zzjo.zzk(zzizVar.zznl));
                    }
                    return iZzb2;
                }
                return iZza2;
            case 49:
                if (i5 == 3) {
                    zzmf zzmfVarZzap = zzap(i6);
                    int i28 = (i3 & (-8)) | 4;
                    iZza2 = zziy.zza(zzmfVarZzap, bArr, i, i2, i28, zzizVar);
                    zzkpVarZzr.add(zzizVar.zznm);
                    while (iZza2 < i2) {
                        int iZza19 = zziy.zza(bArr, iZza2, zzizVar);
                        if (i3 == zzizVar.zznk) {
                            iZza2 = zziy.zza(zzmfVarZzap, bArr, iZza19, i2, i28, zzizVar);
                            zzkpVarZzr.add(zzizVar.zznm);
                        }
                    }
                }
                return iZza2;
            default:
                return iZza2;
        }
    }

    private final <K, V> int zza(T t, byte[] bArr, int i, int i2, int i3, long j, zziz zzizVar) throws IOException {
        Unsafe unsafe = zzuc;
        Object objZzaq = zzaq(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzut.zzj(object)) {
            Object objZzl = this.zzut.zzl(objZzaq);
            this.zzut.zzb(objZzl, object);
            unsafe.putObject(t, j, objZzl);
            object = objZzl;
        }
        zzlj<?, ?> zzljVarZzm = this.zzut.zzm(objZzaq);
        Map<?, ?> mapZzh = this.zzut.zzh(object);
        int iZza = zziy.zza(bArr, i, zzizVar);
        int i4 = zzizVar.zznk;
        if (i4 < 0 || i4 > i2 - iZza) {
            throw zzkq.zzdi();
        }
        int i5 = i4 + iZza;
        K k = zzljVarZzm.zztv;
        V v = zzljVarZzm.zztx;
        while (iZza < i5) {
            int iZza2 = iZza + 1;
            int i6 = bArr[iZza];
            if (i6 < 0) {
                iZza2 = zziy.zza(i6, bArr, iZza2, zzizVar);
                i6 = zzizVar.zznk;
            }
            int i7 = iZza2;
            int i8 = i6 >>> 3;
            int i9 = i6 & 7;
            if (i8 == 1) {
                if (i9 == zzljVarZzm.zztu.zzfk()) {
                    iZza = zza(bArr, i7, i2, zzljVarZzm.zztu, (Class<?>) null, zzizVar);
                    k = (K) zzizVar.zznm;
                } else {
                    iZza = zziy.zza(i6, bArr, i7, i2, zzizVar);
                }
            } else if (i8 == 2 && i9 == zzljVarZzm.zztw.zzfk()) {
                iZza = zza(bArr, i7, i2, zzljVarZzm.zztw, zzljVarZzm.zztx.getClass(), zzizVar);
                v = zzizVar.zznm;
            } else {
                iZza = zziy.zza(i6, bArr, i7, i2, zzizVar);
            }
        }
        if (iZza != i5) {
            throw zzkq.zzdm();
        }
        mapZzh.put(k, v);
        return i5;
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zziz zzizVar) throws IOException {
        int iZzb;
        Unsafe unsafe = zzuc;
        long j2 = this.zzud[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Double.valueOf(zziy.zzc(bArr, i)));
                iZzb = i + 8;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 52:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Float.valueOf(zziy.zzd(bArr, i)));
                iZzb = i + 4;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 53:
            case 54:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zziy.zzb(bArr, i, zzizVar);
                unsafe.putObject(t, j, Long.valueOf(zzizVar.zznl));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 55:
            case 62:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zziy.zza(bArr, i, zzizVar);
                unsafe.putObject(t, j, Integer.valueOf(zzizVar.zznk));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 56:
            case 65:
                if (i5 != 1) {
                    return i;
                }
                unsafe.putObject(t, j, Long.valueOf(zziy.zzb(bArr, i)));
                iZzb = i + 8;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 57:
            case 64:
                if (i5 != 5) {
                    return i;
                }
                unsafe.putObject(t, j, Integer.valueOf(zziy.zza(bArr, i)));
                iZzb = i + 4;
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 58:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zziy.zzb(bArr, i, zzizVar);
                unsafe.putObject(t, j, Boolean.valueOf(zzizVar.zznl != 0));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                int iZza = zziy.zza(bArr, i, zzizVar);
                int i9 = zzizVar.zznk;
                if (i9 == 0) {
                    unsafe.putObject(t, j, "");
                } else {
                    if ((i6 & DriveFile.MODE_WRITE_ONLY) != 0 && !zznf.zze(bArr, iZza, iZza + i9)) {
                        throw zzkq.zzdn();
                    }
                    unsafe.putObject(t, j, new String(bArr, iZza, i9, zzkm.UTF_8));
                    iZza += i9;
                }
                unsafe.putInt(t, j2, i4);
                return iZza;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                int iZza2 = zziy.zza(zzap(i8), bArr, i, i2, zzizVar);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, zzizVar.zznm);
                } else {
                    unsafe.putObject(t, j, zzkm.zza(object, zzizVar.zznm));
                }
                unsafe.putInt(t, j2, i4);
                return iZza2;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                iZzb = zziy.zze(bArr, i, zzizVar);
                unsafe.putObject(t, j, zzizVar.zznm);
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                int iZza3 = zziy.zza(bArr, i, zzizVar);
                int i10 = zzizVar.zznk;
                zzko zzkoVarZzar = zzar(i8);
                if (zzkoVarZzar == null || zzkoVarZzar.zzan(i10)) {
                    unsafe.putObject(t, j, Integer.valueOf(i10));
                    iZzb = iZza3;
                    unsafe.putInt(t, j2, i4);
                    return iZzb;
                }
                zzo(t).zzb(i3, Long.valueOf(i10));
                return iZza3;
            case 66:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zziy.zza(bArr, i, zzizVar);
                unsafe.putObject(t, j, Integer.valueOf(zzjo.zzw(zzizVar.zznk)));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 67:
                if (i5 != 0) {
                    return i;
                }
                iZzb = zziy.zzb(bArr, i, zzizVar);
                unsafe.putObject(t, j, Long.valueOf(zzjo.zzk(zzizVar.zznl)));
                unsafe.putInt(t, j2, i4);
                return iZzb;
            case 68:
                if (i5 != 3) {
                    return i;
                }
                iZzb = zziy.zza(zzap(i8), bArr, i, i2, (i3 & (-8)) | 4, zzizVar);
                Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object2 == null) {
                    unsafe.putObject(t, j, zzizVar.zznm);
                } else {
                    unsafe.putObject(t, j, zzkm.zza(object2, zzizVar.zznm));
                }
                unsafe.putInt(t, j2, i4);
                return iZzb;
            default:
                return i;
        }
    }

    private final zzmf zzap(int i) {
        int i2 = (i / 3) << 1;
        zzmf zzmfVar = (zzmf) this.zzue[i2];
        if (zzmfVar != null) {
            return zzmfVar;
        }
        zzmf<T> zzmfVarZzf = zzmd.zzej().zzf((Class) this.zzue[i2 + 1]);
        this.zzue[i2] = zzmfVarZzf;
        return zzmfVarZzf;
    }

    private final Object zzaq(int i) {
        return this.zzue[(i / 3) << 1];
    }

    private final zzko zzar(int i) {
        return (zzko) this.zzue[((i / 3) << 1) + 1];
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 13021. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    final int zza(T r31, byte[] r32, int r33, int r34, int r35, com.google.android.gms.internal.drive.zziz r36) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.drive.zzlu.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.drive.zziz):int");
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
    @Override // com.google.android.gms.internal.drive.zzmf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.drive.zziz r32) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.drive.zzlu.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.drive.zziz):void");
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final void zzd(T t) {
        int i;
        int i2 = this.zzun;
        while (true) {
            i = this.zzuo;
            if (i2 >= i) {
                break;
            }
            long jZzas = zzas(this.zzum[i2]) & 1048575;
            Object objZzo = zznd.zzo(t, jZzas);
            if (objZzo != null) {
                zznd.zza(t, jZzas, this.zzut.zzk(objZzo));
            }
            i2++;
        }
        int length = this.zzum.length;
        while (i < length) {
            this.zzuq.zza(t, this.zzum[i]);
            i++;
        }
        this.zzur.zzd(t);
        if (this.zzui) {
            this.zzus.zzd(t);
        }
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzko zzkoVar, UB ub, zzmx<UT, UB> zzmxVar) {
        zzlj<?, ?> zzljVarZzm = this.zzut.zzm(zzaq(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzkoVar.zzan(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzmxVar.zzez();
                }
                zzjk zzjkVarZzu = zzjc.zzu(zzli.zza(zzljVarZzm, next.getKey(), next.getValue()));
                try {
                    zzli.zza(zzjkVarZzu.zzby(), zzljVarZzm, next.getKey(), next.getValue());
                    zzmxVar.zza(ub, i2, zzjkVarZzu.zzbx());
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
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.drive.zzmf] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.drive.zzmf] */
    @Override // com.google.android.gms.internal.drive.zzmf
    public final boolean zzp(T t) {
        int i;
        List list;
        ?? Zzap;
        int i2;
        int i3 = 0;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= this.zzun) {
                return !this.zzui || this.zzus.zzb(t).isInitialized();
            }
            int i6 = this.zzum[i3];
            int i7 = this.zzud[i6];
            int iZzas = zzas(i6);
            if (this.zzuk) {
                i = 0;
            } else {
                int i8 = this.zzud[i6 + 2];
                int i9 = i8 & 1048575;
                i = 1 << (i8 >>> 20);
                if (i9 != i4) {
                    i5 = zzuc.getInt(t, i9);
                    i4 = i9;
                }
            }
            if (((268435456 & iZzas) != 0) && !zza(t, i6, i5, i)) {
                return false;
            }
            int i10 = (267386880 & iZzas) >>> 20;
            if (i10 == 9 || i10 == 17) {
                if (zza(t, i6, i5, i) && !zza(t, iZzas, zzap(i6))) {
                    return false;
                }
            } else if (i10 == 27) {
                list = (List) zznd.zzo(t, iZzas & 1048575);
                if (!list.isEmpty()) {
                    Zzap = zzap(i6);
                    for (i2 = 0; i2 < list.size(); i2++) {
                        if (!Zzap.zzp(list.get(i2))) {
                            z = false;
                            break;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (i10 == 60 || i10 == 68) {
                if (zza(t, i7, i6) && !zza(t, iZzas, zzap(i6))) {
                    return false;
                }
            } else if (i10 == 49) {
                list = (List) zznd.zzo(t, iZzas & 1048575);
                if (!list.isEmpty()) {
                    Zzap = zzap(i6);
                    while (i2 < list.size()) {
                        if (!Zzap.zzp(list.get(i2))) {
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
                Map<?, ?> mapZzi = this.zzut.zzi(zznd.zzo(t, iZzas & 1048575));
                if (!mapZzi.isEmpty()) {
                    if (this.zzut.zzm(zzaq(i6)).zztw.zzfj() == zznr.MESSAGE) {
                        ?? Zzf = 0;
                        for (Object obj : mapZzi.values()) {
                            if (Zzf == 0) {
                                Zzf = Zzf;
                                Zzf = zzmd.zzej().zzf(obj.getClass());
                            }
                            Zzf = Zzf;
                            if (!Zzf.zzp(obj)) {
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
    private static boolean zza(Object obj, int i, zzmf zzmfVar) {
        return zzmfVar.zzp(zznd.zzo(obj, i & 1048575));
    }

    private static void zza(int i, Object obj, zzns zznsVar) throws IOException {
        if (obj instanceof String) {
            zznsVar.zza(i, (String) obj);
        } else {
            zznsVar.zza(i, (zzjc) obj);
        }
    }

    private final int zzas(int i) {
        return this.zzud[i + 1];
    }

    private final int zzat(int i) {
        return this.zzud[i + 2];
    }

    private static <T> double zze(T t, long j) {
        return ((Double) zznd.zzo(t, j)).doubleValue();
    }

    private static <T> float zzf(T t, long j) {
        return ((Float) zznd.zzo(t, j)).floatValue();
    }

    private static <T> int zzg(T t, long j) {
        return ((Integer) zznd.zzo(t, j)).intValue();
    }

    private static <T> long zzh(T t, long j) {
        return ((Long) zznd.zzo(t, j)).longValue();
    }

    private static <T> boolean zzi(T t, long j) {
        return ((Boolean) zznd.zzo(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        if (this.zzuk) {
            return zza(t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zza(T t, int i) {
        if (this.zzuk) {
            int iZzas = zzas(i);
            long j = iZzas & 1048575;
            switch ((iZzas & 267386880) >>> 20) {
                case 0:
                    return zznd.zzn(t, j) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                case 1:
                    return zznd.zzm(t, j) != 0.0f;
                case 2:
                    return zznd.zzk(t, j) != 0;
                case 3:
                    return zznd.zzk(t, j) != 0;
                case 4:
                    return zznd.zzj(t, j) != 0;
                case 5:
                    return zznd.zzk(t, j) != 0;
                case 6:
                    return zznd.zzj(t, j) != 0;
                case 7:
                    return zznd.zzl(t, j);
                case 8:
                    Object objZzo = zznd.zzo(t, j);
                    if (objZzo instanceof String) {
                        return !((String) objZzo).isEmpty();
                    }
                    if (objZzo instanceof zzjc) {
                        return !zzjc.zznq.equals(objZzo);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zznd.zzo(t, j) != null;
                case 10:
                    return !zzjc.zznq.equals(zznd.zzo(t, j));
                case 11:
                    return zznd.zzj(t, j) != 0;
                case 12:
                    return zznd.zzj(t, j) != 0;
                case 13:
                    return zznd.zzj(t, j) != 0;
                case 14:
                    return zznd.zzk(t, j) != 0;
                case 15:
                    return zznd.zzj(t, j) != 0;
                case 16:
                    return zznd.zzk(t, j) != 0;
                case 17:
                    return zznd.zzo(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int iZzat = zzat(i);
        return (zznd.zzj(t, (long) (iZzat & 1048575)) & (1 << (iZzat >>> 20))) != 0;
    }

    private final void zzb(T t, int i) {
        if (this.zzuk) {
            return;
        }
        int iZzat = zzat(i);
        long j = iZzat & 1048575;
        zznd.zza((Object) t, j, zznd.zzj(t, j) | (1 << (iZzat >>> 20)));
    }

    private final boolean zza(T t, int i, int i2) {
        return zznd.zzj(t, (long) (zzat(i2) & 1048575)) == i;
    }

    private final void zzb(T t, int i, int i2) {
        zznd.zza((Object) t, zzat(i2) & 1048575, i);
    }

    private final int zzau(int i) {
        if (i < this.zzuf || i > this.zzug) {
            return -1;
        }
        return zzq(i, 0);
    }

    private final int zzp(int i, int i2) {
        if (i < this.zzuf || i > this.zzug) {
            return -1;
        }
        return zzq(i, i2);
    }

    private final int zzq(int i, int i2) {
        int length = (this.zzud.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzud[i4];
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
