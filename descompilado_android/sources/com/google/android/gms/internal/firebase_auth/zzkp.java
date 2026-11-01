package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkp extends zzhx<zzkp, zza> implements zzji {
    private static final zzkp zze;
    private static volatile zzjq<zzkp> zzf;
    private long zzc;
    private int zzd;

    private zzkp() {
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zza extends zzhx.zzb<zzkp, zza> implements zzji {
        private zza() {
            super(zzkp.zze);
        }

        /* synthetic */ zza(zzko zzkoVar) {
            this();
        }
    }

    public final long zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhx
    protected final Object zza(int i, Object obj, Object obj2) {
        zzko zzkoVar = null;
        switch (zzko.zza[i - 1]) {
            case 1:
                return new zzkp();
            case 2:
                return new zza(zzkoVar);
            case 3:
                return zza(zze, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzc", "zzd"});
            case 4:
                return zze;
            case 5:
                zzjq<zzkp> zzaVar = zzf;
                if (zzaVar == null) {
                    synchronized (zzkp.class) {
                        zzaVar = zzf;
                        if (zzaVar == null) {
                            zzaVar = new zzhx.zza<>(zze);
                            zzf = zzaVar;
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

    public static zzkp zzb() {
        return zze;
    }

    static {
        zzkp zzkpVar = new zzkp();
        zze = zzkpVar;
        zzhx.zza((Class<zzkp>) zzkp.class, zzkpVar);
    }
}
