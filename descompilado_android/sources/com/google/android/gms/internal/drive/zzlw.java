package com.google.android.gms.internal.drive;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
final class zzlw<T> implements zzmf<T> {
    private final zzlq zzuh;
    private final boolean zzui;
    private final zzmx<?, ?> zzur;
    private final zzjy<?> zzus;

    private zzlw(zzmx<?, ?> zzmxVar, zzjy<?> zzjyVar, zzlq zzlqVar) {
        this.zzur = zzmxVar;
        this.zzui = zzjyVar.zze(zzlqVar);
        this.zzus = zzjyVar;
        this.zzuh = zzlqVar;
    }

    static <T> zzlw<T> zza(zzmx<?, ?> zzmxVar, zzjy<?> zzjyVar, zzlq zzlqVar) {
        return new zzlw<>(zzmxVar, zzjyVar, zzlqVar);
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final T newInstance() {
        return (T) this.zzuh.zzcz().zzde();
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final boolean equals(T t, T t2) {
        if (!this.zzur.zzr(t).equals(this.zzur.zzr(t2))) {
            return false;
        }
        if (this.zzui) {
            return this.zzus.zzb(t).equals(this.zzus.zzb(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final int hashCode(T t) {
        int iHashCode = this.zzur.zzr(t).hashCode();
        return this.zzui ? (iHashCode * 53) + this.zzus.zzb(t).hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final void zzc(T t, T t2) {
        zzmh.zza(this.zzur, t, t2);
        if (this.zzui) {
            zzmh.zza(this.zzus, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final void zza(T t, zzns zznsVar) throws IOException {
        for (T t2 : this.zzus.zzb(t)) {
            zzkd zzkdVar = (zzkd) t2.getKey();
            if (zzkdVar.zzcr() != zznr.MESSAGE || zzkdVar.zzcs() || zzkdVar.zzct()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (t2 instanceof zzkv) {
                zznsVar.zza(zzkdVar.zzcp(), (Object) ((zzkv) t2).zzdq().zzbl());
            } else {
                zznsVar.zza(zzkdVar.zzcp(), t2.getValue());
            }
        }
        zzmx<?, ?> zzmxVar = this.zzur;
        zzmxVar.zzc(zzmxVar.zzr(t), zznsVar);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0094  */
    /* JADX WARN: Code duplicated, block: B:57:0x0099 A[EDGE_INSN: B:57:0x0099->B:34:0x0099 BREAK  A[LOOP:1: B:18:0x0053->B:62:0x0053], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.drive.zzmf
    public final void zza(T t, byte[] bArr, int i, int i2, zziz zzizVar) throws IOException {
        zzkk zzkkVar = (zzkk) t;
        zzmy zzmyVarZzfb = zzkkVar.zzrq;
        if (zzmyVarZzfb == zzmy.zzfa()) {
            zzmyVarZzfb = zzmy.zzfb();
            zzkkVar.zzrq = zzmyVarZzfb;
        }
        ((zzkk.zzc) t).zzdg();
        zzkk.zzd zzdVar = null;
        while (i < i2) {
            int iZza = zziy.zza(bArr, i, zzizVar);
            int i3 = zzizVar.zznk;
            if (i3 == 11) {
                int i4 = 0;
                zzjc zzjcVar = null;
                while (iZza < i2) {
                    iZza = zziy.zza(bArr, iZza, zzizVar);
                    int i5 = zzizVar.zznk;
                    int i6 = i5 >>> 3;
                    int i7 = i5 & 7;
                    if (i6 == 2) {
                        if (i7 != 0) {
                            if (i5 != 12) {
                                break;
                                break;
                            }
                            iZza = zziy.zza(i5, bArr, iZza, i2, zzizVar);
                        } else {
                            iZza = zziy.zza(bArr, iZza, zzizVar);
                            i4 = zzizVar.zznk;
                            zzdVar = (zzkk.zzd) this.zzus.zza(zzizVar.zznn, this.zzuh, i4);
                        }
                    } else {
                        if (i6 == 3) {
                            if (zzdVar != null) {
                                zzmd.zzej();
                                throw new NoSuchMethodError();
                            }
                            if (i7 == 2) {
                                iZza = zziy.zze(bArr, iZza, zzizVar);
                                zzjcVar = (zzjc) zzizVar.zznm;
                            }
                        }
                        if (i5 != 12) {
                            break;
                        } else {
                            iZza = zziy.zza(i5, bArr, iZza, i2, zzizVar);
                        }
                    }
                }
                if (zzjcVar != null) {
                    zzmyVarZzfb.zzb((i4 << 3) | 2, zzjcVar);
                }
                i = iZza;
            } else if ((i3 & 7) == 2) {
                zzdVar = (zzkk.zzd) this.zzus.zza(zzizVar.zznn, this.zzuh, i3 >>> 3);
                if (zzdVar != null) {
                    zzmd.zzej();
                    throw new NoSuchMethodError();
                }
                i = zziy.zza(i3, bArr, iZza, i2, zzmyVarZzfb, zzizVar);
            } else {
                i = zziy.zza(i3, bArr, iZza, i2, zzizVar);
            }
        }
        if (i != i2) {
            throw zzkq.zzdm();
        }
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final void zzd(T t) {
        this.zzur.zzd(t);
        this.zzus.zzd(t);
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final boolean zzp(T t) {
        return this.zzus.zzb(t).isInitialized();
    }

    @Override // com.google.android.gms.internal.drive.zzmf
    public final int zzn(T t) {
        zzmx<?, ?> zzmxVar = this.zzur;
        int iZzs = zzmxVar.zzs(zzmxVar.zzr(t)) + 0;
        return this.zzui ? iZzs + this.zzus.zzb(t).zzco() : iZzs;
    }
}
