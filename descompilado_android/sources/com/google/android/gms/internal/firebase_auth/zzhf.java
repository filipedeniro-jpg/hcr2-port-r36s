package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhf implements zzjx {
    private final zzgy zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    public static zzhf zza(zzgy zzgyVar) {
        return zzgyVar.zzd != null ? zzgyVar.zzd : new zzhf(zzgyVar);
    }

    private zzhf(zzgy zzgyVar) {
        this.zza = (zzgy) zzib.zza(zzgyVar, "input");
        this.zza.zzd = this;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final int zza() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            this.zzb = this.zza.zza();
        }
        int i2 = this.zzb;
        if (i2 == 0 || i2 == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final int zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final boolean zzc() throws IOException {
        int i;
        if (this.zza.zzt() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzb(i);
    }

    private final void zza(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzig.zzf();
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final double zzd() throws IOException {
        zza(1);
        return this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final float zze() throws IOException {
        zza(5);
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final long zzf() throws IOException {
        zza(0);
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final long zzg() throws IOException {
        zza(0);
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final int zzh() throws IOException {
        zza(0);
        return this.zza.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final long zzi() throws IOException {
        zza(1);
        return this.zza.zzg();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final int zzj() throws IOException {
        zza(5);
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final boolean zzk() throws IOException {
        zza(0);
        return this.zza.zzi();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final String zzl() throws IOException {
        zza(2);
        return this.zza.zzj();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final String zzm() throws IOException {
        zza(2);
        return this.zza.zzk();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final <T> T zza(zzjw<T> zzjwVar, zzhk zzhkVar) throws IOException {
        zza(2);
        return (T) zzc(zzjwVar, zzhkVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final <T> T zzb(zzjw<T> zzjwVar, zzhk zzhkVar) throws IOException {
        zza(3);
        return (T) zzd(zzjwVar, zzhkVar);
    }

    private final <T> T zzc(zzjw<T> zzjwVar, zzhk zzhkVar) throws IOException {
        int iZzm = this.zza.zzm();
        if (this.zza.zza >= this.zza.zzb) {
            throw new zzig("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iZzc = this.zza.zzc(iZzm);
        T tZza = zzjwVar.zza();
        this.zza.zza++;
        zzjwVar.zza(tZza, this, zzhkVar);
        zzjwVar.zzc(tZza);
        this.zza.zza(0);
        this.zza.zza--;
        this.zza.zzd(iZzc);
        return tZza;
    }

    private final <T> T zzd(zzjw<T> zzjwVar, zzhk zzhkVar) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            T tZza = zzjwVar.zza();
            zzjwVar.zza(tZza, this, zzhkVar);
            zzjwVar.zzc(tZza);
            if (this.zzb != this.zzc) {
                throw zzig.zzh();
            }
            this.zzc = i;
            return tZza;
        } catch (Throwable th) {
            this.zzc = i;
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final zzgm zzn() throws IOException {
        zza(2);
        return this.zza.zzl();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final int zzo() throws IOException {
        zza(0);
        return this.zza.zzm();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final int zzp() throws IOException {
        zza(0);
        return this.zza.zzn();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final int zzq() throws IOException {
        zza(5);
        return this.zza.zzo();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final long zzr() throws IOException {
        zza(1);
        return this.zza.zzp();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final int zzs() throws IOException {
        zza(0);
        return this.zza.zzq();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final long zzt() throws IOException {
        zza(0);
        return this.zza.zzr();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zza(List<Double> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzhi) {
            zzhi zzhiVar = (zzhi) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzhiVar.zza(this.zza.zzb());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzb(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzhiVar.zza(this.zza.zzb());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 1) {
            do {
                list.add(Double.valueOf(this.zza.zzb()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzb(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Double.valueOf(this.zza.zzb()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzb(List<Float> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzhw) {
            zzhw zzhwVar = (zzhw) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzc(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzhwVar.zza(this.zza.zzc());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            if (i == 5) {
                do {
                    zzhwVar.zza(this.zza.zzc());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzc(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Float.valueOf(this.zza.zzc()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Float.valueOf(this.zza.zzc()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzc(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zziuVar.zza(this.zza.zzd());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zziuVar.zza(this.zza.zzd());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zzd()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Long.valueOf(this.zza.zzd()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzd(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zziuVar.zza(this.zza.zze());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zziuVar.zza(this.zza.zze());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zze()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Long.valueOf(this.zza.zze()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zze(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzhzVar.zzd(this.zza.zzf());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzhzVar.zzd(this.zza.zzf());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzf()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzf()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzf(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zziuVar.zza(this.zza.zzg());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzb(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zziuVar.zza(this.zza.zzg());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.zza.zzg()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzb(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Long.valueOf(this.zza.zzg()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzg(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzc(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzhzVar.zzd(this.zza.zzh());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            if (i == 5) {
                do {
                    zzhzVar.zzd(this.zza.zzh());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzc(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Integer.valueOf(this.zza.zzh()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zza.zzh()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzh(List<Boolean> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzgk) {
            zzgk zzgkVar = (zzgk) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzgkVar.zza(this.zza.zzi());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzgkVar.zza(this.zza.zzi());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(this.zza.zzi()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Boolean.valueOf(this.zza.zzi()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzi(List<String> list) throws IOException {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzj(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int iZza;
        int iZza2;
        if ((this.zzb & 7) != 2) {
            throw zzig.zzf();
        }
        if ((list instanceof zzir) && !z) {
            zzir zzirVar = (zzir) list;
            do {
                zzirVar.zza(zzn());
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza2 = this.zza.zza();
                }
            } while (iZza2 == this.zzb);
            this.zzd = iZza2;
            return;
        }
        do {
            list.add(z ? zzm() : zzl());
            if (this.zza.zzt()) {
                return;
            } else {
                iZza = this.zza.zza();
            }
        } while (iZza == this.zzb);
        this.zzd = iZza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final <T> void zza(List<T> list, zzjw<T> zzjwVar, zzhk zzhkVar) throws IOException {
        int iZza;
        int i = this.zzb;
        if ((i & 7) != 2) {
            throw zzig.zzf();
        }
        do {
            list.add(zzc(zzjwVar, zzhkVar));
            if (this.zza.zzt() || this.zzd != 0) {
                return;
            } else {
                iZza = this.zza.zza();
            }
        } while (iZza == i);
        this.zzd = iZza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final <T> void zzb(List<T> list, zzjw<T> zzjwVar, zzhk zzhkVar) throws IOException {
        int iZza;
        int i = this.zzb;
        if ((i & 7) != 3) {
            throw zzig.zzf();
        }
        do {
            list.add(zzd(zzjwVar, zzhkVar));
            if (this.zza.zzt() || this.zzd != 0) {
                return;
            } else {
                iZza = this.zza.zza();
            }
        } while (iZza == i);
        this.zzd = iZza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzk(List<zzgm> list) throws IOException {
        int iZza;
        if ((this.zzb & 7) != 2) {
            throw zzig.zzf();
        }
        do {
            list.add(zzn());
            if (this.zza.zzt()) {
                return;
            } else {
                iZza = this.zza.zza();
            }
        } while (iZza == this.zzb);
        this.zzd = iZza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzl(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzhzVar.zzd(this.zza.zzm());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzhzVar.zzd(this.zza.zzm());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzm()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzm()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzm(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzhzVar.zzd(this.zza.zzn());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzhzVar.zzd(this.zza.zzn());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzn()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzn()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzn(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzc(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zzhzVar.zzd(this.zza.zzo());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            if (i == 5) {
                do {
                    zzhzVar.zzd(this.zza.zzo());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzc(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Integer.valueOf(this.zza.zzo()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zza.zzo()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzo(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zziuVar.zza(this.zza.zzp());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzm = this.zza.zzm();
                zzb(iZzm);
                int iZzu = this.zza.zzu() + iZzm;
                do {
                    zziuVar.zza(this.zza.zzp());
                } while (this.zza.zzu() < iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.zza.zzp()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzm2 = this.zza.zzm();
            zzb(iZzm2);
            int iZzu2 = this.zza.zzu() + iZzm2;
            do {
                list.add(Long.valueOf(this.zza.zzp()));
            } while (this.zza.zzu() < iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzp(List<Integer> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zzhz) {
            zzhz zzhzVar = (zzhz) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzhzVar.zzd(this.zza.zzq());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zzhzVar.zzd(this.zza.zzq());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zza.zzq()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Integer.valueOf(this.zza.zzq()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final void zzq(List<Long> list) throws IOException {
        int iZza;
        int iZza2;
        if (list instanceof zziu) {
            zziu zziuVar = (zziu) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zziuVar.zza(this.zza.zzr());
                    if (this.zza.zzt()) {
                        return;
                    } else {
                        iZza2 = this.zza.zza();
                    }
                } while (iZza2 == this.zzb);
                this.zzd = iZza2;
                return;
            }
            if (i == 2) {
                int iZzu = this.zza.zzu() + this.zza.zzm();
                do {
                    zziuVar.zza(this.zza.zzr());
                } while (this.zza.zzu() < iZzu);
                zzd(iZzu);
                return;
            }
            throw zzig.zzf();
        }
        int i2 = this.zzb & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zza.zzr()));
                if (this.zza.zzt()) {
                    return;
                } else {
                    iZza = this.zza.zza();
                }
            } while (iZza == this.zzb);
            this.zzd = iZza;
            return;
        }
        if (i2 == 2) {
            int iZzu2 = this.zza.zzu() + this.zza.zzm();
            do {
                list.add(Long.valueOf(this.zza.zzr()));
            } while (this.zza.zzu() < iZzu2);
            zzd(iZzu2);
            return;
        }
        throw zzig.zzf();
    }

    private static void zzb(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzig.zzh();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_auth.zzjx
    public final <K, V> void zza(Map<K, V> map, zzjb<K, V> zzjbVar, zzhk zzhkVar) throws IOException {
        zza(2);
        int iZzc = this.zza.zzc(this.zza.zzm());
        Object objZza = zzjbVar.zzb;
        Object objZza2 = zzjbVar.zzd;
        while (true) {
            try {
                int iZza = zza();
                if (iZza == Integer.MAX_VALUE || this.zza.zzt()) {
                    break;
                }
                if (iZza == 1) {
                    objZza = zza(zzjbVar.zza, (Class<?>) null, (zzhk) null);
                } else if (iZza == 2) {
                    objZza2 = zza(zzjbVar.zzc, zzjbVar.zzd.getClass(), zzhkVar);
                } else {
                    try {
                        if (!zzc()) {
                            throw new zzig("Unable to parse map entry.");
                        }
                    } catch (zzij unused) {
                        if (!zzc()) {
                            throw new zzig("Unable to parse map entry.");
                        }
                    }
                }
            } catch (Throwable th) {
                this.zza.zzd(iZzc);
                throw th;
            }
        }
        map.put(objZza, objZza2);
        this.zza.zzd(iZzc);
    }

    private final Object zza(zzlf zzlfVar, Class<?> cls, zzhk zzhkVar) throws IOException {
        switch (zzhe.zza[zzlfVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzk());
            case 2:
                return zzn();
            case 3:
                return Double.valueOf(zzd());
            case 4:
                return Integer.valueOf(zzp());
            case 5:
                return Integer.valueOf(zzj());
            case 6:
                return Long.valueOf(zzi());
            case 7:
                return Float.valueOf(zze());
            case 8:
                return Integer.valueOf(zzh());
            case 9:
                return Long.valueOf(zzg());
            case 10:
                zza(2);
                return zzc(zzjs.zza().zza((Class) cls), zzhkVar);
            case 11:
                return Integer.valueOf(zzq());
            case 12:
                return Long.valueOf(zzr());
            case 13:
                return Integer.valueOf(zzs());
            case 14:
                return Long.valueOf(zzt());
            case 15:
                return zzm();
            case 16:
                return Integer.valueOf(zzo());
            case 17:
                return Long.valueOf(zzf());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzc(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzig.zzh();
        }
    }

    private final void zzd(int i) throws IOException {
        if (this.zza.zzu() != i) {
            throw zzig.zza();
        }
    }
}
