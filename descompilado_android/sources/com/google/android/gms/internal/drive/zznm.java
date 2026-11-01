package com.google.android.gms.internal.drive;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzxc' uses external variables
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
public class zznm {
    public static final zznm zzwu = new zznm("DOUBLE", 0, zznr.DOUBLE, 1);
    public static final zznm zzwv = new zznm("FLOAT", 1, zznr.FLOAT, 5);
    public static final zznm zzww = new zznm("INT64", 2, zznr.LONG, 0);
    public static final zznm zzwx = new zznm("UINT64", 3, zznr.LONG, 0);
    public static final zznm zzwy = new zznm("INT32", 4, zznr.INT, 0);
    public static final zznm zzwz = new zznm("FIXED64", 5, zznr.LONG, 1);
    public static final zznm zzxa = new zznm("FIXED32", 6, zznr.INT, 5);
    public static final zznm zzxb = new zznm("BOOL", 7, zznr.BOOLEAN, 0);
    public static final zznm zzxc;
    public static final zznm zzxd;
    public static final zznm zzxe;
    public static final zznm zzxf;
    public static final zznm zzxg;
    public static final zznm zzxh;
    public static final zznm zzxi;
    public static final zznm zzxj;
    public static final zznm zzxk;
    public static final zznm zzxl;
    private static final /* synthetic */ zznm[] zzxo;
    private final zznr zzxm;
    private final int zzxn;

    public static zznm[] values() {
        return (zznm[]) zzxo.clone();
    }

    private zznm(String str, int i, zznr zznrVar, int i2) {
        super(str, i);
        this.zzxm = zznrVar;
        this.zzxn = i2;
    }

    public final zznr zzfj() {
        return this.zzxm;
    }

    public final int zzfk() {
        return this.zzxn;
    }

    /* synthetic */ zznm(String str, int i, zznr zznrVar, int i2, zznl zznlVar) {
        this(str, i, zznrVar, i2);
    }

    static {
        final int i = 2;
        final int i2 = 3;
        final zznr zznrVar = zznr.STRING;
        final int i3 = 8;
        final String str = "STRING";
        zzxc = new zznm(str, i3, zznrVar, i) { // from class: com.google.android.gms.internal.drive.zznn
            {
                int i4 = 8;
                int i5 = 2;
                zznl zznlVar = null;
            }
        };
        final zznr zznrVar2 = zznr.MESSAGE;
        final int i4 = 9;
        final String str2 = "GROUP";
        zzxd = new zznm(str2, i4, zznrVar2, i2) { // from class: com.google.android.gms.internal.drive.zzno
            {
                int i5 = 9;
                int i6 = 3;
                zznl zznlVar = null;
            }
        };
        final zznr zznrVar3 = zznr.MESSAGE;
        final int i5 = 10;
        final String str3 = "MESSAGE";
        zzxe = new zznm(str3, i5, zznrVar3, i) { // from class: com.google.android.gms.internal.drive.zznp
            {
                int i6 = 10;
                int i7 = 2;
                zznl zznlVar = null;
            }
        };
        final zznr zznrVar4 = zznr.BYTE_STRING;
        final int i6 = 11;
        final String str4 = "BYTES";
        zzxf = new zznm(str4, i6, zznrVar4, i) { // from class: com.google.android.gms.internal.drive.zznq
            {
                int i7 = 11;
                int i8 = 2;
                zznl zznlVar = null;
            }
        };
        zzxg = new zznm("UINT32", 12, zznr.INT, 0);
        zzxh = new zznm("ENUM", 13, zznr.ENUM, 0);
        zzxi = new zznm("SFIXED32", 14, zznr.INT, 5);
        zzxj = new zznm("SFIXED64", 15, zznr.LONG, 1);
        zzxk = new zznm("SINT32", 16, zznr.INT, 0);
        zzxl = new zznm("SINT64", 17, zznr.LONG, 0);
        zzxo = new zznm[]{zzwu, zzwv, zzww, zzwx, zzwy, zzwz, zzxa, zzxb, zzxc, zzxd, zzxe, zzxf, zzxg, zzxh, zzxi, zzxj, zzxk, zzxl};
    }
}
