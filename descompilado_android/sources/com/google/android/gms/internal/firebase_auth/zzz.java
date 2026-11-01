package com.google.android.gms.internal.firebase_auth;

import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzz extends zzhx<zzz, zza> implements zzji {
    private static final zzz zzab;
    private static volatile zzjq<zzz> zzac;
    private int zzc;
    private int zzn;
    private boolean zzo;
    private long zzp;
    private long zzr;
    private boolean zzs;
    private long zzt;
    private long zzu;
    private boolean zzw;
    private String zzd = "";
    private String zze = "";
    private String zzf = "";
    private zzih<String> zzg = zzhx.zzad();
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private zzgm zzl = zzgm.zza;
    private zzgm zzm = zzgm.zza;
    private zzih<zzu> zzq = zzad();
    private String zzv = "";
    private String zzx = "";
    private String zzy = "";
    private String zzz = "";
    private zzih<zzr> zzaa = zzad();

    private zzz() {
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zza extends zzhx.zzb<zzz, zza> implements zzji {
        private zza() {
            super(zzz.zzab);
        }

        /* synthetic */ zza(zzy zzyVar) {
            this();
        }
    }

    public final String zza() {
        return this.zzd;
    }

    public final String zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzi;
    }

    public final boolean zze() {
        return this.zzo;
    }

    public final List<zzu> zzf() {
        return this.zzq;
    }

    public final long zzg() {
        return this.zzt;
    }

    public final long zzh() {
        return this.zzu;
    }

    public final String zzi() {
        return this.zzx;
    }

    public final String zzj() {
        return this.zzy;
    }

    public final List<zzr> zzk() {
        return this.zzaa;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzhx
    protected final Object zza(int i, Object obj, Object obj2) {
        zzy zzyVar = null;
        switch (zzy.zza[i - 1]) {
            case 1:
                return new zzz();
            case 2:
                return new zza(zzyVar);
            case 3:
                return zza(zzab, "\u0001\u0018\u0000\u0001\u0001\u001a\u0018\u0000\u0003\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u001a\u0005\b\u0003\u0006\b\u0004\u0007\b\u0005\b\b\u0006\t\n\u0007\n\n\b\u000b\u0004\t\f\u0007\n\r\u0002\u000b\u000e\u001b\u000f\u0002\f\u0010\u0007\r\u0011\u0002\u000e\u0012\u0002\u000f\u0013\b\u0010\u0014\u0007\u0011\u0015\b\u0012\u0016\b\u0013\u0019\b\u0014\u001a\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", zzu.class, "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", zzr.class});
            case 4:
                return zzab;
            case 5:
                zzjq<zzz> zzaVar = zzac;
                if (zzaVar == null) {
                    synchronized (zzz.class) {
                        zzaVar = zzac;
                        if (zzaVar == null) {
                            zzaVar = new zzhx.zza<>(zzab);
                            zzac = zzaVar;
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
        zzz zzzVar = new zzz();
        zzab = zzzVar;
        zzhx.zza((Class<zzz>) zzz.class, zzzVar);
    }
}
