package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdrt<T> implements zzdsc<T> {
    private final zzdrn zzhmq;
    private final boolean zzhmr;
    private final zzdsu<?, ?> zzhna;
    private final zzdps<?> zzhnb;

    private zzdrt(zzdsu<?, ?> zzdsuVar, zzdps<?> zzdpsVar, zzdrn zzdrnVar) {
        this.zzhna = zzdsuVar;
        this.zzhmr = zzdpsVar.zzm(zzdrnVar);
        this.zzhnb = zzdpsVar;
        this.zzhmq = zzdrnVar;
    }

    static <T> zzdrt<T> zza(zzdsu<?, ?> zzdsuVar, zzdps<?> zzdpsVar, zzdrn zzdrnVar) {
        return new zzdrt<>(zzdsuVar, zzdpsVar, zzdrnVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final T newInstance() {
        return (T) this.zzhmq.zzazt().zzazl();
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final boolean equals(T t, T t2) {
        if (!this.zzhna.zzay(t).equals(this.zzhna.zzay(t2))) {
            return false;
        }
        if (this.zzhmr) {
            return this.zzhnb.zzai(t).equals(this.zzhnb.zzai(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final int hashCode(T t) {
        int iHashCode = this.zzhna.zzay(t).hashCode();
        return this.zzhmr ? (iHashCode * 53) + this.zzhnb.zzai(t).hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final void zzf(T t, T t2) {
        zzdse.zza(this.zzhna, t, t2);
        if (this.zzhmr) {
            zzdse.zza(this.zzhnb, t, t2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final void zza(T t, zzdtr zzdtrVar) throws IOException {
        for (T t2 : this.zzhnb.zzai(t)) {
            zzdpv zzdpvVar = (zzdpv) t2.getKey();
            if (zzdpvVar.zzazd() != zzdto.MESSAGE || zzdpvVar.zzaze() || zzdpvVar.zzazf()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (t2 instanceof zzdqq) {
                zzdtrVar.zzb(zzdpvVar.zzab(), ((zzdqq) t2).zzbah().zzaxb());
            } else {
                zzdtrVar.zzb(zzdpvVar.zzab(), t2.getValue());
            }
        }
        zzdsu<?, ?> zzdsuVar = this.zzhna;
        zzdsuVar.zzc(zzdsuVar.zzay(t), zzdtrVar);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0094  */
    /* JADX WARN: Code duplicated, block: B:57:0x0099 A[EDGE_INSN: B:57:0x0099->B:34:0x0099 BREAK  A[LOOP:1: B:18:0x0053->B:62:0x0053], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzdsc
    public final void zza(T t, byte[] bArr, int i, int i2, zzdos zzdosVar) throws IOException {
        zzdqd zzdqdVar = (zzdqd) t;
        zzdsx zzdsxVarZzbbt = zzdqdVar.zzhkd;
        if (zzdsxVarZzbbt == zzdsx.zzbbs()) {
            zzdsxVarZzbbt = zzdsx.zzbbt();
            zzdqdVar.zzhkd = zzdsxVarZzbbt;
        }
        ((zzdqd.zzb) t).zzazu();
        zzdqd.zze zzeVar = null;
        while (i < i2) {
            int iZza = zzdop.zza(bArr, i, zzdosVar);
            int i3 = zzdosVar.zzhfj;
            if (i3 == 11) {
                int i4 = 0;
                zzdot zzdotVar = null;
                while (iZza < i2) {
                    iZza = zzdop.zza(bArr, iZza, zzdosVar);
                    int i5 = zzdosVar.zzhfj;
                    int i6 = i5 >>> 3;
                    int i7 = i5 & 7;
                    if (i6 == 2) {
                        if (i7 != 0) {
                            if (i5 != 12) {
                                break;
                                break;
                            }
                            iZza = zzdop.zza(i5, bArr, iZza, i2, zzdosVar);
                        } else {
                            iZza = zzdop.zza(bArr, iZza, zzdosVar);
                            i4 = zzdosVar.zzhfj;
                            zzeVar = (zzdqd.zze) this.zzhnb.zza(zzdosVar.zzhfm, this.zzhmq, i4);
                        }
                    } else {
                        if (i6 == 3) {
                            if (zzeVar != null) {
                                zzdry.zzbba();
                                throw new NoSuchMethodError();
                            }
                            if (i7 == 2) {
                                iZza = zzdop.zze(bArr, iZza, zzdosVar);
                                zzdotVar = (zzdot) zzdosVar.zzhfl;
                            }
                        }
                        if (i5 != 12) {
                            break;
                        } else {
                            iZza = zzdop.zza(i5, bArr, iZza, i2, zzdosVar);
                        }
                    }
                }
                if (zzdotVar != null) {
                    zzdsxVarZzbbt.zzc((i4 << 3) | 2, zzdotVar);
                }
                i = iZza;
            } else if ((i3 & 7) == 2) {
                zzeVar = (zzdqd.zze) this.zzhnb.zza(zzdosVar.zzhfm, this.zzhmq, i3 >>> 3);
                if (zzeVar != null) {
                    zzdry.zzbba();
                    throw new NoSuchMethodError();
                }
                i = zzdop.zza(i3, bArr, iZza, i2, zzdsxVarZzbbt, zzdosVar);
            } else {
                i = zzdop.zza(i3, bArr, iZza, i2, zzdosVar);
            }
        }
        if (i != i2) {
            throw zzdqn.zzbad();
        }
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0085 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:? A[LOOP:0: B:46:0x000c->B:54:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.ads.zzdsc
    public final void zza(T t, zzdsd zzdsdVar, zzdpq zzdpqVar) throws IOException {
        boolean zZzayo;
        zzdsu<?, ?> zzdsuVar = this.zzhna;
        zzdps<?> zzdpsVar = this.zzhnb;
        Object objZzaz = zzdsuVar.zzaz(t);
        zzdpt<T> zzdptVarZzaj = zzdpsVar.zzaj(t);
        while (zzdsdVar.zzayn() != Integer.MAX_VALUE) {
            try {
                int tag = zzdsdVar.getTag();
                if (tag != 11) {
                    if ((tag & 7) == 2) {
                        Object objZza = zzdpsVar.zza(zzdpqVar, this.zzhmq, tag >>> 3);
                        if (objZza != null) {
                            zzdpsVar.zza(zzdsdVar, objZza, zzdpqVar, zzdptVarZzaj);
                        } else {
                            zZzayo = zzdsuVar.zza(objZzaz, zzdsdVar);
                        }
                    } else {
                        zZzayo = zzdsdVar.zzayo();
                    }
                    if (!zZzayo) {
                        zzdsuVar.zzi(t, objZzaz);
                        return;
                    }
                } else {
                    int iZzaxy = 0;
                    Object objZza2 = null;
                    zzdot zzdotVarZzaxx = null;
                    while (zzdsdVar.zzayn() != Integer.MAX_VALUE) {
                        int tag2 = zzdsdVar.getTag();
                        if (tag2 == 16) {
                            iZzaxy = zzdsdVar.zzaxy();
                            objZza2 = zzdpsVar.zza(zzdpqVar, this.zzhmq, iZzaxy);
                        } else if (tag2 == 26) {
                            if (objZza2 != null) {
                                zzdpsVar.zza(zzdsdVar, objZza2, zzdpqVar, zzdptVarZzaj);
                            } else {
                                zzdotVarZzaxx = zzdsdVar.zzaxx();
                            }
                        } else if (!zzdsdVar.zzayo()) {
                            break;
                        }
                    }
                    if (zzdsdVar.getTag() != 12) {
                        throw zzdqn.zzbab();
                    }
                    if (zzdotVarZzaxx != null) {
                        if (objZza2 != null) {
                            zzdpsVar.zza(zzdotVarZzaxx, objZza2, zzdpqVar, zzdptVarZzaj);
                        } else {
                            zzdsuVar.zza(objZzaz, iZzaxy, zzdotVarZzaxx);
                        }
                    }
                }
                zZzayo = true;
                if (!zZzayo) {
                    zzdsuVar.zzi(t, objZzaz);
                    return;
                }
            } catch (Throwable th) {
                zzdsuVar.zzi(t, objZzaz);
                throw th;
            }
        }
        zzdsuVar.zzi(t, objZzaz);
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final void zzak(T t) {
        this.zzhna.zzak(t);
        this.zzhnb.zzak(t);
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final boolean zzaw(T t) {
        return this.zzhnb.zzai(t).isInitialized();
    }

    @Override // com.google.android.gms.internal.ads.zzdsc
    public final int zzau(T t) {
        zzdsu<?, ?> zzdsuVar = this.zzhna;
        int iZzba = zzdsuVar.zzba(zzdsuVar.zzay(t)) + 0;
        return this.zzhmr ? iZzba + this.zzhnb.zzai(t).zzayy() : iZzba;
    }
}
