package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public class zzip {
    private static final zzhk zza = zzhk.zza();
    private zzgm zzb;
    private volatile zzjg zzc;
    private volatile zzgm zzd;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzip)) {
            return false;
        }
        zzip zzipVar = (zzip) obj;
        zzjg zzjgVar = this.zzc;
        zzjg zzjgVar2 = zzipVar.zzc;
        if (zzjgVar == null && zzjgVar2 == null) {
            return zzc().equals(zzipVar.zzc());
        }
        if (zzjgVar != null && zzjgVar2 != null) {
            return zzjgVar.equals(zzjgVar2);
        }
        if (zzjgVar != null) {
            return zzjgVar.equals(zzipVar.zzb(zzjgVar.zzag()));
        }
        return zzb(zzjgVar2.zzag()).equals(zzjgVar2);
    }

    private final zzjg zzb(zzjg zzjgVar) {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    try {
                        this.zzc = zzjgVar;
                        this.zzd = zzgm.zza;
                    } catch (zzig unused) {
                        this.zzc = zzjgVar;
                        this.zzd = zzgm.zza;
                    }
                }
            }
        }
        return this.zzc;
    }

    public final zzjg zza(zzjg zzjgVar) {
        zzjg zzjgVar2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzjgVar;
        return zzjgVar2;
    }

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zza();
        }
        if (this.zzc != null) {
            return this.zzc.zzab();
        }
        return 0;
    }

    public final zzgm zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            if (this.zzd != null) {
                return this.zzd;
            }
            if (this.zzc == null) {
                this.zzd = zzgm.zza;
            } else {
                this.zzd = this.zzc.zzw();
            }
            return this.zzd;
        }
    }
}
