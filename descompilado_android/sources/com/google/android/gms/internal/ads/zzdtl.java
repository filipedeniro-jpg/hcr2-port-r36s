package com.google.android.gms.internal.ads;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzhpl' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdtl {
    public static final zzdtl zzhpd = new zzdtl("DOUBLE", 0, zzdto.DOUBLE, 1);
    public static final zzdtl zzhpe = new zzdtl("FLOAT", 1, zzdto.FLOAT, 5);
    public static final zzdtl zzhpf = new zzdtl("INT64", 2, zzdto.LONG, 0);
    public static final zzdtl zzhpg = new zzdtl("UINT64", 3, zzdto.LONG, 0);
    public static final zzdtl zzhph = new zzdtl("INT32", 4, zzdto.INT, 0);
    public static final zzdtl zzhpi = new zzdtl("FIXED64", 5, zzdto.LONG, 1);
    public static final zzdtl zzhpj = new zzdtl("FIXED32", 6, zzdto.INT, 5);
    public static final zzdtl zzhpk = new zzdtl("BOOL", 7, zzdto.BOOLEAN, 0);
    public static final zzdtl zzhpl;
    public static final zzdtl zzhpm;
    public static final zzdtl zzhpn;
    public static final zzdtl zzhpo;
    public static final zzdtl zzhpp;
    public static final zzdtl zzhpq;
    public static final zzdtl zzhpr;
    public static final zzdtl zzhps;
    public static final zzdtl zzhpt;
    public static final zzdtl zzhpu;
    private static final /* synthetic */ zzdtl[] zzhpx;
    private final zzdto zzhpv;
    private final int zzhpw;

    public static zzdtl[] values() {
        return (zzdtl[]) zzhpx.clone();
    }

    private zzdtl(String str, int i, zzdto zzdtoVar, int i2) {
        super(str, i);
        this.zzhpv = zzdtoVar;
        this.zzhpw = i2;
    }

    public final zzdto zzbcb() {
        return this.zzhpv;
    }

    public final int zzbcc() {
        return this.zzhpw;
    }

    /* synthetic */ zzdtl(String str, int i, zzdto zzdtoVar, int i2, zzdti zzdtiVar) {
        this(str, i, zzdtoVar, i2);
    }

    static {
        final int i = 2;
        final int i2 = 3;
        final zzdto zzdtoVar = zzdto.STRING;
        final int i3 = 8;
        final String str = "STRING";
        zzhpl = new zzdtl(str, i3, zzdtoVar, i) { // from class: com.google.android.gms.internal.ads.zzdtk
            {
                int i4 = 8;
                int i5 = 2;
                zzdti zzdtiVar = null;
            }
        };
        final zzdto zzdtoVar2 = zzdto.MESSAGE;
        final int i4 = 9;
        final String str2 = "GROUP";
        zzhpm = new zzdtl(str2, i4, zzdtoVar2, i2) { // from class: com.google.android.gms.internal.ads.zzdtn
            {
                int i5 = 9;
                int i6 = 3;
                zzdti zzdtiVar = null;
            }
        };
        final zzdto zzdtoVar3 = zzdto.MESSAGE;
        final int i5 = 10;
        final String str3 = "MESSAGE";
        zzhpn = new zzdtl(str3, i5, zzdtoVar3, i) { // from class: com.google.android.gms.internal.ads.zzdtm
            {
                int i6 = 10;
                int i7 = 2;
                zzdti zzdtiVar = null;
            }
        };
        final zzdto zzdtoVar4 = zzdto.BYTE_STRING;
        final int i6 = 11;
        final String str4 = "BYTES";
        zzhpo = new zzdtl(str4, i6, zzdtoVar4, i) { // from class: com.google.android.gms.internal.ads.zzdtp
            {
                int i7 = 11;
                int i8 = 2;
                zzdti zzdtiVar = null;
            }
        };
        zzhpp = new zzdtl("UINT32", 12, zzdto.INT, 0);
        zzhpq = new zzdtl("ENUM", 13, zzdto.ENUM, 0);
        zzhpr = new zzdtl("SFIXED32", 14, zzdto.INT, 5);
        zzhps = new zzdtl("SFIXED64", 15, zzdto.LONG, 1);
        zzhpt = new zzdtl("SINT32", 16, zzdto.INT, 0);
        zzhpu = new zzdtl("SINT64", 17, zzdto.LONG, 0);
        zzhpx = new zzdtl[]{zzhpd, zzhpe, zzhpf, zzhpg, zzhph, zzhpi, zzhpj, zzhpk, zzhpl, zzhpm, zzhpn, zzhpo, zzhpp, zzhpq, zzhpr, zzhps, zzhpt, zzhpu};
    }
}
