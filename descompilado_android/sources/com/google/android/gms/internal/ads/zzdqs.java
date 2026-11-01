package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdqs {
    private static final zzdpq zzhfe = zzdpq.zzayv();
    private zzdot zzhlq;
    private volatile zzdrn zzhlr;
    private volatile zzdot zzhls;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdqs)) {
            return false;
        }
        zzdqs zzdqsVar = (zzdqs) obj;
        zzdrn zzdrnVar = this.zzhlr;
        zzdrn zzdrnVar2 = zzdqsVar.zzhlr;
        if (zzdrnVar == null && zzdrnVar2 == null) {
            return zzaxb().equals(zzdqsVar.zzaxb());
        }
        if (zzdrnVar != null && zzdrnVar2 != null) {
            return zzdrnVar.equals(zzdrnVar2);
        }
        if (zzdrnVar != null) {
            return zzdrnVar.equals(zzdqsVar.zzp(zzdrnVar.zzazn()));
        }
        return zzp(zzdrnVar2.zzazn()).equals(zzdrnVar2);
    }

    private final zzdrn zzp(zzdrn zzdrnVar) {
        if (this.zzhlr == null) {
            synchronized (this) {
                if (this.zzhlr == null) {
                    try {
                        this.zzhlr = zzdrnVar;
                        this.zzhls = zzdot.zzhfn;
                    } catch (zzdqn unused) {
                        this.zzhlr = zzdrnVar;
                        this.zzhls = zzdot.zzhfn;
                    }
                }
            }
        }
        return this.zzhlr;
    }

    public final zzdrn zzq(zzdrn zzdrnVar) {
        zzdrn zzdrnVar2 = this.zzhlr;
        this.zzhlq = null;
        this.zzhls = null;
        this.zzhlr = zzdrnVar;
        return zzdrnVar2;
    }

    public final int zzazp() {
        if (this.zzhls != null) {
            return this.zzhls.size();
        }
        if (this.zzhlr != null) {
            return this.zzhlr.zzazp();
        }
        return 0;
    }

    public final zzdot zzaxb() {
        if (this.zzhls != null) {
            return this.zzhls;
        }
        synchronized (this) {
            if (this.zzhls != null) {
                return this.zzhls;
            }
            if (this.zzhlr == null) {
                this.zzhls = zzdot.zzhfn;
            } else {
                this.zzhls = this.zzhlr.zzaxb();
            }
            return this.zzhls;
        }
    }
}
