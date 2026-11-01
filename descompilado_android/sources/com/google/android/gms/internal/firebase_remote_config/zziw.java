package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class zziw<T> implements zzjf<T> {
    private final zziq zzvv;
    private final boolean zzvw;
    private final zzjx<?, ?> zzwf;
    private final zzgz<?> zzwg;

    private zziw(zzjx<?, ?> zzjxVar, zzgz<?> zzgzVar, zziq zziqVar) {
        this.zzwf = zzjxVar;
        this.zzvw = zzgzVar.zze(zziqVar);
        this.zzwg = zzgzVar;
        this.zzvv = zziqVar;
    }

    static <T> zziw<T> zza(zzjx<?, ?> zzjxVar, zzgz<?> zzgzVar, zziq zziqVar) {
        return new zziw<>(zzjxVar, zzgzVar, zziqVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final T newInstance() {
        return (T) this.zzvv.zzgv().zzha();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final boolean equals(T t, T t2) {
        if (!this.zzwf.zzaa(t).equals(this.zzwf.zzaa(t2))) {
            return false;
        }
        if (this.zzvw) {
            return this.zzwg.zzk(t).equals(this.zzwg.zzk(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final int hashCode(T t) {
        int iHashCode = this.zzwf.zzaa(t).hashCode();
        return this.zzvw ? (iHashCode * 53) + this.zzwg.zzk(t).hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final void zze(T t, T t2) {
        zzjh.zza(this.zzwf, t, t2);
        if (this.zzvw) {
            zzjh.zza(this.zzwg, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final void zza(T t, zzku zzkuVar) throws IOException {
        for (T t2 : this.zzwg.zzk(t)) {
            zzhc zzhcVar = (zzhc) t2.getKey();
            if (zzhcVar.zzgp() != zzkr.MESSAGE || zzhcVar.zzgq() || zzhcVar.zzgr()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (t2 instanceof zzht) {
                zzkuVar.zza(zzhcVar.zzgn(), (Object) ((zzht) t2).zzhp().zzep());
            } else {
                zzkuVar.zza(zzhcVar.zzgn(), t2.getValue());
            }
        }
        zzjx<?, ?> zzjxVar = this.zzwf;
        zzjxVar.zzc(zzjxVar.zzaa(t), zzkuVar);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0094  */
    /* JADX WARN: Code duplicated, block: B:57:0x0099 A[EDGE_INSN: B:57:0x0099->B:34:0x0099 BREAK  A[LOOP:1: B:18:0x0053->B:62:0x0053], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final void zza(T t, byte[] bArr, int i, int i2, zzfw zzfwVar) throws IOException {
        zzhi zzhiVar = (zzhi) t;
        zzka zzkaVarZzjb = zzhiVar.zztc;
        if (zzkaVarZzjb == zzka.zzja()) {
            zzkaVarZzjb = zzka.zzjb();
            zzhiVar.zztc = zzkaVarZzjb;
        }
        ((zzhi.zzd) t).zzhc();
        zzhi.zzc zzcVar = null;
        while (i < i2) {
            int iZza = zzft.zza(bArr, i, zzfwVar);
            int i3 = zzfwVar.zzor;
            if (i3 == 11) {
                int i4 = 0;
                zzfx zzfxVar = null;
                while (iZza < i2) {
                    iZza = zzft.zza(bArr, iZza, zzfwVar);
                    int i5 = zzfwVar.zzor;
                    int i6 = i5 >>> 3;
                    int i7 = i5 & 7;
                    if (i6 == 2) {
                        if (i7 != 0) {
                            if (i5 != 12) {
                                break;
                                break;
                            }
                            iZza = zzft.zza(i5, bArr, iZza, i2, zzfwVar);
                        } else {
                            iZza = zzft.zza(bArr, iZza, zzfwVar);
                            i4 = zzfwVar.zzor;
                            zzcVar = (zzhi.zzc) this.zzwg.zza(zzfwVar.zzou, this.zzvv, i4);
                        }
                    } else {
                        if (i6 == 3) {
                            if (zzcVar != null) {
                                zzjb.zzii();
                                throw new NoSuchMethodError();
                            }
                            if (i7 == 2) {
                                iZza = zzft.zze(bArr, iZza, zzfwVar);
                                zzfxVar = (zzfx) zzfwVar.zzot;
                            }
                        }
                        if (i5 != 12) {
                            break;
                        } else {
                            iZza = zzft.zza(i5, bArr, iZza, i2, zzfwVar);
                        }
                    }
                }
                if (zzfxVar != null) {
                    zzkaVarZzjb.zzb((i4 << 3) | 2, zzfxVar);
                }
                i = iZza;
            } else if ((i3 & 7) == 2) {
                zzcVar = (zzhi.zzc) this.zzwg.zza(zzfwVar.zzou, this.zzvv, i3 >>> 3);
                if (zzcVar != null) {
                    zzjb.zzii();
                    throw new NoSuchMethodError();
                }
                i = zzft.zza(i3, bArr, iZza, i2, zzkaVarZzjb, zzfwVar);
            } else {
                i = zzft.zza(i3, bArr, iZza, i2, zzfwVar);
            }
        }
        if (i != i2) {
            throw zzhq.zzhl();
        }
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:? A[LOOP:0: B:46:0x000c->B:54:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final void zza(T t, zzjg zzjgVar, zzgx zzgxVar) throws IOException {
        boolean zZzga;
        zzjx<?, ?> zzjxVar = this.zzwf;
        zzgz<?> zzgzVar = this.zzwg;
        Object objZzab = zzjxVar.zzab(t);
        zzha<T> zzhaVarZzl = zzgzVar.zzl(t);
        while (zzjgVar.zzfz() != Integer.MAX_VALUE) {
            try {
                int tag = zzjgVar.getTag();
                if (tag != 11) {
                    if ((tag & 7) == 2) {
                        Object objZza = zzgzVar.zza(zzgxVar, this.zzvv, tag >>> 3);
                        if (objZza != null) {
                            zzgzVar.zza(zzjgVar, objZza, zzgxVar, zzhaVarZzl);
                        } else {
                            zZzga = zzjxVar.zza(objZzab, zzjgVar);
                        }
                    } else {
                        zZzga = zzjgVar.zzga();
                    }
                    if (!zZzga) {
                        zzjxVar.zzh(t, objZzab);
                        return;
                    }
                } else {
                    int iZzfk = 0;
                    Object objZza2 = null;
                    zzfx zzfxVarZzfj = null;
                    while (zzjgVar.zzfz() != Integer.MAX_VALUE) {
                        int tag2 = zzjgVar.getTag();
                        if (tag2 == 16) {
                            iZzfk = zzjgVar.zzfk();
                            objZza2 = zzgzVar.zza(zzgxVar, this.zzvv, iZzfk);
                        } else if (tag2 == 26) {
                            if (objZza2 != null) {
                                zzgzVar.zza(zzjgVar, objZza2, zzgxVar, zzhaVarZzl);
                            } else {
                                zzfxVarZzfj = zzjgVar.zzfj();
                            }
                        } else if (!zzjgVar.zzga()) {
                            break;
                        }
                    }
                    if (zzjgVar.getTag() != 12) {
                        throw zzhq.zzhi();
                    }
                    if (zzfxVarZzfj != null) {
                        if (objZza2 != null) {
                            zzgzVar.zza(zzfxVarZzfj, objZza2, zzgxVar, zzhaVarZzl);
                        } else {
                            zzjxVar.zza(objZzab, iZzfk, zzfxVarZzfj);
                        }
                    }
                }
                zZzga = true;
                if (!zZzga) {
                    zzjxVar.zzh(t, objZzab);
                    return;
                }
            } catch (Throwable th) {
                zzjxVar.zzh(t, objZzab);
                throw th;
            }
        }
        zzjxVar.zzh(t, objZzab);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final void zzm(T t) {
        this.zzwf.zzm(t);
        this.zzwg.zzm(t);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final boolean zzy(T t) {
        return this.zzwg.zzk(t).isInitialized();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjf
    public final int zzw(T t) {
        zzjx<?, ?> zzjxVar = this.zzwf;
        int iZzac = zzjxVar.zzac(zzjxVar.zzaa(t)) + 0;
        return this.zzvw ? iZzac + this.zzwg.zzk(t).zzgj() : iZzac;
    }
}
