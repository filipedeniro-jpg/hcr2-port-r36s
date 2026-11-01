package com.google.android.gms.internal.firebase_auth;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zzi' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public class zzlf {
    public static final zzlf zza = new zzlf("DOUBLE", 0, zzlm.DOUBLE, 1);
    public static final zzlf zzb = new zzlf("FLOAT", 1, zzlm.FLOAT, 5);
    public static final zzlf zzc = new zzlf("INT64", 2, zzlm.LONG, 0);
    public static final zzlf zzd = new zzlf("UINT64", 3, zzlm.LONG, 0);
    public static final zzlf zze = new zzlf("INT32", 4, zzlm.INT, 0);
    public static final zzlf zzf = new zzlf("FIXED64", 5, zzlm.LONG, 1);
    public static final zzlf zzg = new zzlf("FIXED32", 6, zzlm.INT, 5);
    public static final zzlf zzh = new zzlf("BOOL", 7, zzlm.BOOLEAN, 0);
    public static final zzlf zzi;
    public static final zzlf zzj;
    public static final zzlf zzk;
    public static final zzlf zzl;
    public static final zzlf zzm;
    public static final zzlf zzn;
    public static final zzlf zzo;
    public static final zzlf zzp;
    public static final zzlf zzq;
    public static final zzlf zzr;
    private static final /* synthetic */ zzlf[] zzu;
    private final zzlm zzs;
    private final int zzt;

    public static zzlf[] values() {
        return (zzlf[]) zzu.clone();
    }

    private zzlf(String str, int i, zzlm zzlmVar, int i2) {
        super(str, i);
        this.zzs = zzlmVar;
        this.zzt = i2;
    }

    public final zzlm zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }

    /* synthetic */ zzlf(String str, int i, zzlm zzlmVar, int i2, zzlg zzlgVar) {
        this(str, i, zzlmVar, i2);
    }

    static {
        final int i = 2;
        final int i2 = 3;
        final zzlm zzlmVar = zzlm.STRING;
        final int i3 = 8;
        final String str = "STRING";
        zzi = new zzlf(str, i3, zzlmVar, i) { // from class: com.google.android.gms.internal.firebase_auth.zzli
            {
                int i4 = 8;
                int i5 = 2;
                zzlg zzlgVar = null;
            }
        };
        final zzlm zzlmVar2 = zzlm.MESSAGE;
        final int i4 = 9;
        final String str2 = "GROUP";
        zzj = new zzlf(str2, i4, zzlmVar2, i2) { // from class: com.google.android.gms.internal.firebase_auth.zzlh
            {
                int i5 = 9;
                int i6 = 3;
                zzlg zzlgVar = null;
            }
        };
        final zzlm zzlmVar3 = zzlm.MESSAGE;
        final int i5 = 10;
        final String str3 = "MESSAGE";
        zzk = new zzlf(str3, i5, zzlmVar3, i) { // from class: com.google.android.gms.internal.firebase_auth.zzlk
            {
                int i6 = 10;
                int i7 = 2;
                zzlg zzlgVar = null;
            }
        };
        final zzlm zzlmVar4 = zzlm.BYTE_STRING;
        final int i6 = 11;
        final String str4 = "BYTES";
        zzl = new zzlf(str4, i6, zzlmVar4, i) { // from class: com.google.android.gms.internal.firebase_auth.zzlj
            {
                int i7 = 11;
                int i8 = 2;
                zzlg zzlgVar = null;
            }
        };
        zzm = new zzlf("UINT32", 12, zzlm.INT, 0);
        zzn = new zzlf("ENUM", 13, zzlm.ENUM, 0);
        zzo = new zzlf("SFIXED32", 14, zzlm.INT, 5);
        zzp = new zzlf("SFIXED64", 15, zzlm.LONG, 1);
        zzq = new zzlf("SINT32", 16, zzlm.INT, 0);
        zzr = new zzlf("SINT64", 17, zzlm.LONG, 0);
        zzu = new zzlf[]{zza, zzb, zzc, zzd, zze, zzf, zzg, zzh, zzi, zzj, zzk, zzl, zzm, zzn, zzo, zzp, zzq, zzr};
    }
}
