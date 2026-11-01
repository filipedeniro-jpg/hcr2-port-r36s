package com.google.android.gms.internal.firebase_remote_config;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzyr' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes2.dex */
public class zzko {
    public static final zzko zzyj = new zzko("DOUBLE", 0, zzkr.DOUBLE, 1);
    public static final zzko zzyk = new zzko("FLOAT", 1, zzkr.FLOAT, 5);
    public static final zzko zzyl = new zzko("INT64", 2, zzkr.LONG, 0);
    public static final zzko zzym = new zzko("UINT64", 3, zzkr.LONG, 0);
    public static final zzko zzyn = new zzko("INT32", 4, zzkr.INT, 0);
    public static final zzko zzyo = new zzko("FIXED64", 5, zzkr.LONG, 1);
    public static final zzko zzyp = new zzko("FIXED32", 6, zzkr.INT, 5);
    public static final zzko zzyq = new zzko("BOOL", 7, zzkr.BOOLEAN, 0);
    public static final zzko zzyr;
    public static final zzko zzys;
    public static final zzko zzyt;
    public static final zzko zzyu;
    public static final zzko zzyv;
    public static final zzko zzyw;
    public static final zzko zzyx;
    public static final zzko zzyy;
    public static final zzko zzyz;
    public static final zzko zzza;
    private static final /* synthetic */ zzko[] zzzd;
    private final zzkr zzzb;
    private final int zzzc;

    public static zzko[] values() {
        return (zzko[]) zzzd.clone();
    }

    private zzko(String str, int i, zzkr zzkrVar, int i2) {
        super(str, i);
        this.zzzb = zzkrVar;
        this.zzzc = i2;
    }

    public final zzkr zzjj() {
        return this.zzzb;
    }

    public final int zzjk() {
        return this.zzzc;
    }

    /* synthetic */ zzko(String str, int i, zzkr zzkrVar, int i2, zzkl zzklVar) {
        this(str, i, zzkrVar, i2);
    }

    static {
        final int i = 2;
        final int i2 = 3;
        final zzkr zzkrVar = zzkr.STRING;
        final int i3 = 8;
        final String str = "STRING";
        zzyr = new zzko(str, i3, zzkrVar, i) { // from class: com.google.android.gms.internal.firebase_remote_config.zzkn
            {
                int i4 = 8;
                int i5 = 2;
                zzkl zzklVar = null;
            }
        };
        final zzkr zzkrVar2 = zzkr.MESSAGE;
        final int i4 = 9;
        final String str2 = "GROUP";
        zzys = new zzko(str2, i4, zzkrVar2, i2) { // from class: com.google.android.gms.internal.firebase_remote_config.zzkq
            {
                int i5 = 9;
                int i6 = 3;
                zzkl zzklVar = null;
            }
        };
        final zzkr zzkrVar3 = zzkr.MESSAGE;
        final int i5 = 10;
        final String str3 = "MESSAGE";
        zzyt = new zzko(str3, i5, zzkrVar3, i) { // from class: com.google.android.gms.internal.firebase_remote_config.zzkp
            {
                int i6 = 10;
                int i7 = 2;
                zzkl zzklVar = null;
            }
        };
        final zzkr zzkrVar4 = zzkr.BYTE_STRING;
        final int i6 = 11;
        final String str4 = "BYTES";
        zzyu = new zzko(str4, i6, zzkrVar4, i) { // from class: com.google.android.gms.internal.firebase_remote_config.zzks
            {
                int i7 = 11;
                int i8 = 2;
                zzkl zzklVar = null;
            }
        };
        zzyv = new zzko("UINT32", 12, zzkr.INT, 0);
        zzyw = new zzko("ENUM", 13, zzkr.ENUM, 0);
        zzyx = new zzko("SFIXED32", 14, zzkr.INT, 5);
        zzyy = new zzko("SFIXED64", 15, zzkr.LONG, 1);
        zzyz = new zzko("SINT32", 16, zzkr.INT, 0);
        zzza = new zzko("SINT64", 17, zzkr.LONG, 0);
        zzzd = new zzko[]{zzyj, zzyk, zzyl, zzym, zzyn, zzyo, zzyp, zzyq, zzyr, zzys, zzyt, zzyu, zzyv, zzyw, zzyx, zzyy, zzyz, zzza};
    }
}
