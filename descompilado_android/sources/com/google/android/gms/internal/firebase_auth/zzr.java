package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzr extends zzhx<zzr, zza> implements zzji {
    private static final zzr zzk;
    private static volatile zzjq<zzr> zzl;
    private int zzc;
    private Object zze;
    private Object zzg;
    private zzkp zzj;
    private int zzd = 0;
    private int zzf = 0;
    private String zzh = "";
    private String zzi = "";

    private zzr() {
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zza extends zzhx.zzb<zzr, zza> implements zzji {
        private zza() {
            super(zzr.zzk);
        }

        /* synthetic */ zza(zzs zzsVar) {
            this();
        }
    }

    public final String zza() {
        return this.zzd == 1 ? (String) this.zze : "";
    }

    public final String zzb() {
        return this.zzh;
    }

    public final String zzc() {
        return this.zzi;
    }

    public final zzkp zzd() {
        zzkp zzkpVar = this.zzj;
        return zzkpVar == null ? zzkp.zzb() : zzkpVar;
    }

    public final String zze() {
        return this.zzf == 5 ? (String) this.zzg : "";
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhx
    protected final Object zza(int i, Object obj, Object obj2) {
        zzs zzsVar = null;
        switch (zzs.zza[i - 1]) {
            case 1:
                return new zzr();
            case 2:
                return new zza(zzsVar);
            case 3:
                return zza(zzk, "\u0001\u0005\u0002\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001;\u0000\u0002\b\u0001\u0003\b\u0002\u0004\t\u0003\u0005;\u0001", new Object[]{"zze", "zzd", "zzg", "zzf", "zzc", "zzh", "zzi", "zzj"});
            case 4:
                return zzk;
            case 5:
                zzjq<zzr> zzaVar = zzl;
                if (zzaVar == null) {
                    synchronized (zzr.class) {
                        zzaVar = zzl;
                        if (zzaVar == null) {
                            zzaVar = new zzhx.zza<>(zzk);
                            zzl = zzaVar;
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

    public static zzr zzf() {
        return zzk;
    }

    static {
        zzr zzrVar = new zzr();
        zzk = zzrVar;
        zzhx.zza((Class<zzr>) zzr.class, zzrVar);
    }
}
