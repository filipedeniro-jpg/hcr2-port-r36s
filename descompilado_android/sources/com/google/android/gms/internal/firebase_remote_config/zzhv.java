package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
public class zzhv {
    private static final zzgx zzom = zzgx.zzgh();
    private zzfx zzut;
    private volatile zziq zzuu;
    private volatile zzfx zzuv;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhv)) {
            return false;
        }
        zzhv zzhvVar = (zzhv) obj;
        zziq zziqVar = this.zzuu;
        zziq zziqVar2 = zzhvVar.zzuu;
        if (zziqVar == null && zziqVar2 == null) {
            return zzep().equals(zzhvVar.zzep());
        }
        if (zziqVar != null && zziqVar2 != null) {
            return zziqVar.equals(zziqVar2);
        }
        if (zziqVar != null) {
            return zziqVar.equals(zzhvVar.zzh(zziqVar.zzgw()));
        }
        return zzh(zziqVar2.zzgw()).equals(zziqVar2);
    }

    private final zziq zzh(zziq zziqVar) {
        if (this.zzuu == null) {
            synchronized (this) {
                if (this.zzuu == null) {
                    try {
                        this.zzuu = zziqVar;
                        this.zzuv = zzfx.zzov;
                    } catch (zzhq unused) {
                        this.zzuu = zziqVar;
                        this.zzuv = zzfx.zzov;
                    }
                }
            }
        }
        return this.zzuu;
    }

    public final zziq zzi(zziq zziqVar) {
        zziq zziqVar2 = this.zzuu;
        this.zzut = null;
        this.zzuv = null;
        this.zzuu = zziqVar;
        return zziqVar2;
    }

    public final int zzgs() {
        if (this.zzuv != null) {
            return this.zzuv.size();
        }
        if (this.zzuu != null) {
            return this.zzuu.zzgs();
        }
        return 0;
    }

    public final zzfx zzep() {
        if (this.zzuv != null) {
            return this.zzuv;
        }
        synchronized (this) {
            if (this.zzuv != null) {
                return this.zzuv;
            }
            if (this.zzuu == null) {
                this.zzuv = zzfx.zzov;
            } else {
                this.zzuv = this.zzuu.zzep();
            }
            return this.zzuv;
        }
    }
}
