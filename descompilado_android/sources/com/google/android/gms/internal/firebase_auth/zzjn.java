package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjn<T> implements zzjw<T> {
    private final zzjg zza;
    private final zzks<?, ?> zzb;
    private final boolean zzc;
    private final zzhm<?> zzd;

    private zzjn(zzks<?, ?> zzksVar, zzhm<?> zzhmVar, zzjg zzjgVar) {
        this.zzb = zzksVar;
        this.zzc = zzhmVar.zza(zzjgVar);
        this.zzd = zzhmVar;
        this.zza = zzjgVar;
    }

    static <T> zzjn<T> zza(zzks<?, ?> zzksVar, zzhm<?> zzhmVar, zzjg zzjgVar) {
        return new zzjn<>(zzksVar, zzhmVar, zzjgVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final T zza() {
        return (T) this.zza.zzaf().zze();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final boolean zza(T t, T t2) {
        if (!this.zzb.zzb(t).equals(this.zzb.zzb(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza(t).equals(this.zzd.zza(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final int zza(T t) {
        int iHashCode = this.zzb.zzb(t).hashCode();
        return this.zzc ? (iHashCode * 53) + this.zzd.zza(t).hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final void zzb(T t, T t2) {
        zzjy.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzjy.zza(this.zzd, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final void zza(T t, zzll zzllVar) throws IOException {
        Iterator itZzd = this.zzd.zza(t).zzd();
        while (itZzd.hasNext()) {
            Map.Entry entry = (Map.Entry) itZzd.next();
            zzhs zzhsVar = (zzhs) entry.getKey();
            if (zzhsVar.zzc() != zzlm.MESSAGE || zzhsVar.zzd() || zzhsVar.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof zzin) {
                zzllVar.zza(zzhsVar.zza(), (Object) ((zzin) entry).zza().zzc());
            } else {
                zzllVar.zza(zzhsVar.zza(), entry.getValue());
            }
        }
        zzks<?, ?> zzksVar = this.zzb;
        zzksVar.zzb(zzksVar.zzb(t), zzllVar);
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:? A[LOOP:0: B:46:0x000c->B:54:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final void zza(T t, zzjx zzjxVar, zzhk zzhkVar) throws IOException {
        boolean zZzc;
        zzks<?, ?> zzksVar = this.zzb;
        zzhm<?> zzhmVar = this.zzd;
        Object objZzc = zzksVar.zzc(t);
        zzhq<T> zzhqVarZzb = zzhmVar.zzb(t);
        while (zzjxVar.zza() != Integer.MAX_VALUE) {
            try {
                int iZzb = zzjxVar.zzb();
                if (iZzb != 11) {
                    if ((iZzb & 7) == 2) {
                        Object objZza = zzhmVar.zza(zzhkVar, this.zza, iZzb >>> 3);
                        if (objZza != null) {
                            zzhmVar.zza(zzjxVar, objZza, zzhkVar, zzhqVarZzb);
                        } else {
                            zZzc = zzksVar.zza(objZzc, zzjxVar);
                        }
                    } else {
                        zZzc = zzjxVar.zzc();
                    }
                    if (!zZzc) {
                        zzksVar.zzb(t, objZzc);
                        return;
                    }
                } else {
                    int iZzo = 0;
                    Object objZza2 = null;
                    zzgm zzgmVarZzn = null;
                    while (zzjxVar.zza() != Integer.MAX_VALUE) {
                        int iZzb2 = zzjxVar.zzb();
                        if (iZzb2 == 16) {
                            iZzo = zzjxVar.zzo();
                            objZza2 = zzhmVar.zza(zzhkVar, this.zza, iZzo);
                        } else if (iZzb2 == 26) {
                            if (objZza2 != null) {
                                zzhmVar.zza(zzjxVar, objZza2, zzhkVar, zzhqVarZzb);
                            } else {
                                zzgmVarZzn = zzjxVar.zzn();
                            }
                        } else if (!zzjxVar.zzc()) {
                            break;
                        }
                    }
                    if (zzjxVar.zzb() != 12) {
                        throw zzig.zze();
                    }
                    if (zzgmVarZzn != null) {
                        if (objZza2 != null) {
                            zzhmVar.zza(zzgmVarZzn, objZza2, zzhkVar, zzhqVarZzb);
                        } else {
                            zzksVar.zza(objZzc, iZzo, zzgmVarZzn);
                        }
                    }
                }
                zZzc = true;
                if (!zZzc) {
                    zzksVar.zzb(t, objZzc);
                    return;
                }
            } catch (Throwable th) {
                zzksVar.zzb(t, objZzc);
                throw th;
            }
        }
        zzksVar.zzb(t, objZzc);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final void zzc(T t) {
        this.zzb.zzd(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final boolean zzd(T t) {
        return this.zzd.zza(t).zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjw
    public final int zzb(T t) {
        zzks<?, ?> zzksVar = this.zzb;
        int iZze = zzksVar.zze(zzksVar.zzb(t)) + 0;
        return this.zzc ? iZze + this.zzd.zza(t).zzg() : iZze;
    }
}
