package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzl extends zzhx<zzl, zza> implements zzji {
    private static final zzl zzf;
    private static volatile zzjq<zzl> zzg;
    private int zzc;
    private String zzd = "";
    private String zze = "";

    private zzl() {
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zza extends zzhx.zzb<zzl, zza> implements zzji {
        private zza() {
            super(zzl.zzf);
        }

        /* synthetic */ zza(zzn zznVar) {
            this();
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhx
    protected final Object zza(int i, Object obj, Object obj2) {
        zzn zznVar = null;
        switch (zzn.zza[i - 1]) {
            case 1:
                return new zzl();
            case 2:
                return new zza(zznVar);
            case 3:
                return zza(zzf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001", new Object[]{"zzc", "zzd", "zze"});
            case 4:
                return zzf;
            case 5:
                zzjq<zzl> zzaVar = zzg;
                if (zzaVar == null) {
                    synchronized (zzl.class) {
                        zzaVar = zzg;
                        if (zzaVar == null) {
                            zzaVar = new zzhx.zza<>(zzf);
                            zzg = zzaVar;
                        }
                        break;
                    }
                }
                return zzaVar;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        zzl zzlVar = new zzl();
        zzf = zzlVar;
        zzhx.zza((Class<zzl>) zzl.class, zzlVar);
    }
}
