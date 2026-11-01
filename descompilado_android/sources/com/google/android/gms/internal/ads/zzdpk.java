package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdpk implements zzdsd {
    private int tag;
    private final zzdpf zzhgg;
    private int zzhgh;
    private int zzhgi = 0;

    public static zzdpk zza(zzdpf zzdpfVar) {
        return zzdpfVar.zzhfy != null ? zzdpfVar.zzhfy : new zzdpk(zzdpfVar);
    }

    private zzdpk(zzdpf zzdpfVar) {
        this.zzhgg = (zzdpf) zzdqe.zza(zzdpfVar, "input");
        this.zzhgg.zzhfy = this;
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final int zzayn() throws IOException {
        int i = this.zzhgi;
        if (i != 0) {
            this.tag = i;
            this.zzhgi = 0;
        } else {
            this.tag = this.zzhgg.zzaxp();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzhgh) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final int getTag() {
        return this.tag;
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final boolean zzayo() throws IOException {
        int i;
        if (this.zzhgg.zzayf() || (i = this.tag) == this.zzhgh) {
            return false;
        }
        return this.zzhgg.zzfr(i);
    }

    private final void zzfw(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzdqn.zzbac();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final double readDouble() throws IOException {
        zzfw(1);
        return this.zzhgg.readDouble();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final float readFloat() throws IOException {
        zzfw(5);
        return this.zzhgg.readFloat();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final long zzaxq() throws IOException {
        zzfw(0);
        return this.zzhgg.zzaxq();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final long zzaxr() throws IOException {
        zzfw(0);
        return this.zzhgg.zzaxr();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final int zzaxs() throws IOException {
        zzfw(0);
        return this.zzhgg.zzaxs();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final long zzaxt() throws IOException {
        zzfw(1);
        return this.zzhgg.zzaxt();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final int zzaxu() throws IOException {
        zzfw(5);
        return this.zzhgg.zzaxu();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final boolean zzaxv() throws IOException {
        zzfw(0);
        return this.zzhgg.zzaxv();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final String readString() throws IOException {
        zzfw(2);
        return this.zzhgg.readString();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final String zzaxw() throws IOException {
        zzfw(2);
        return this.zzhgg.zzaxw();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final <T> T zza(zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException {
        zzfw(2);
        return (T) zzc(zzdscVar, zzdpqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final <T> T zzb(zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException {
        zzfw(3);
        return (T) zzd(zzdscVar, zzdpqVar);
    }

    private final <T> T zzc(zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException {
        int iZzaxy = this.zzhgg.zzaxy();
        if (this.zzhgg.zzhfv >= this.zzhgg.zzhfw) {
            throw new zzdqn("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iZzfs = this.zzhgg.zzfs(iZzaxy);
        T tNewInstance = zzdscVar.newInstance();
        this.zzhgg.zzhfv++;
        zzdscVar.zza(tNewInstance, this, zzdpqVar);
        zzdscVar.zzak(tNewInstance);
        this.zzhgg.zzfq(0);
        this.zzhgg.zzhfv--;
        this.zzhgg.zzft(iZzfs);
        return tNewInstance;
    }

    private final <T> T zzd(zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException {
        int i = this.zzhgh;
        this.zzhgh = ((this.tag >>> 3) << 3) | 4;
        try {
            T tNewInstance = zzdscVar.newInstance();
            zzdscVar.zza(tNewInstance, this, zzdpqVar);
            zzdscVar.zzak(tNewInstance);
            if (this.tag != this.zzhgh) {
                throw zzdqn.zzbad();
            }
            this.zzhgh = i;
            return tNewInstance;
        } catch (Throwable th) {
            this.zzhgh = i;
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final zzdot zzaxx() throws IOException {
        zzfw(2);
        return this.zzhgg.zzaxx();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final int zzaxy() throws IOException {
        zzfw(0);
        return this.zzhgg.zzaxy();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final int zzaxz() throws IOException {
        zzfw(0);
        return this.zzhgg.zzaxz();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final int zzaya() throws IOException {
        zzfw(5);
        return this.zzhgg.zzaya();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final long zzayb() throws IOException {
        zzfw(1);
        return this.zzhgg.zzayb();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final int zzayc() throws IOException {
        zzfw(0);
        return this.zzhgg.zzayc();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final long zzayd() throws IOException {
        zzfw(0);
        return this.zzhgg.zzayd();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzk(List<Double> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdpp) {
            zzdpp zzdppVar = (zzdpp) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzdppVar.zzd(this.zzhgg.readDouble());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzaxy = this.zzhgg.zzaxy();
                zzfx(iZzaxy);
                int iZzayg = this.zzhgg.zzayg() + iZzaxy;
                do {
                    zzdppVar.zzd(this.zzhgg.readDouble());
                } while (this.zzhgg.zzayg() < iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 1) {
            do {
                list.add(Double.valueOf(this.zzhgg.readDouble()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzaxy2 = this.zzhgg.zzaxy();
            zzfx(iZzaxy2);
            int iZzayg2 = this.zzhgg.zzayg() + iZzaxy2;
            do {
                list.add(Double.valueOf(this.zzhgg.readDouble()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzl(List<Float> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdpz) {
            zzdpz zzdpzVar = (zzdpz) list;
            int i = this.tag & 7;
            if (i == 2) {
                int iZzaxy = this.zzhgg.zzaxy();
                zzfy(iZzaxy);
                int iZzayg = this.zzhgg.zzayg() + iZzaxy;
                do {
                    zzdpzVar.zzh(this.zzhgg.readFloat());
                } while (this.zzhgg.zzayg() < iZzayg);
                return;
            }
            if (i == 5) {
                do {
                    zzdpzVar.zzh(this.zzhgg.readFloat());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int iZzaxy2 = this.zzhgg.zzaxy();
            zzfy(iZzaxy2);
            int iZzayg2 = this.zzhgg.zzayg() + iZzaxy2;
            do {
                list.add(Float.valueOf(this.zzhgg.readFloat()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Float.valueOf(this.zzhgg.readFloat()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzm(List<Long> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdrb) {
            zzdrb zzdrbVar = (zzdrb) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzaxq());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzayg = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzaxq());
                } while (this.zzhgg.zzayg() < iZzayg);
                zzfz(iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzhgg.zzaxq()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzayg2 = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
            do {
                list.add(Long.valueOf(this.zzhgg.zzaxq()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            zzfz(iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzn(List<Long> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdrb) {
            zzdrb zzdrbVar = (zzdrb) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzaxr());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzayg = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzaxr());
                } while (this.zzhgg.zzayg() < iZzayg);
                zzfz(iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzhgg.zzaxr()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzayg2 = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
            do {
                list.add(Long.valueOf(this.zzhgg.zzaxr()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            zzfz(iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzo(List<Integer> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaxs());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzayg = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaxs());
                } while (this.zzhgg.zzayg() < iZzayg);
                zzfz(iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaxs()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzayg2 = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaxs()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            zzfz(iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzp(List<Long> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdrb) {
            zzdrb zzdrbVar = (zzdrb) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzaxt());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzaxy = this.zzhgg.zzaxy();
                zzfx(iZzaxy);
                int iZzayg = this.zzhgg.zzayg() + iZzaxy;
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzaxt());
                } while (this.zzhgg.zzayg() < iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.zzhgg.zzaxt()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzaxy2 = this.zzhgg.zzaxy();
            zzfx(iZzaxy2);
            int iZzayg2 = this.zzhgg.zzayg() + iZzaxy2;
            do {
                list.add(Long.valueOf(this.zzhgg.zzaxt()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzq(List<Integer> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            int i = this.tag & 7;
            if (i == 2) {
                int iZzaxy = this.zzhgg.zzaxy();
                zzfy(iZzaxy);
                int iZzayg = this.zzhgg.zzayg() + iZzaxy;
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaxu());
                } while (this.zzhgg.zzayg() < iZzayg);
                return;
            }
            if (i == 5) {
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaxu());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int iZzaxy2 = this.zzhgg.zzaxy();
            zzfy(iZzaxy2);
            int iZzayg2 = this.zzhgg.zzayg() + iZzaxy2;
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaxu()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaxu()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzr(List<Boolean> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdor) {
            zzdor zzdorVar = (zzdor) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdorVar.addBoolean(this.zzhgg.zzaxv());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzayg = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
                do {
                    zzdorVar.addBoolean(this.zzhgg.zzaxv());
                } while (this.zzhgg.zzayg() < iZzayg);
                zzfz(iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(this.zzhgg.zzaxv()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzayg2 = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
            do {
                list.add(Boolean.valueOf(this.zzhgg.zzaxv()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            zzfz(iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzs(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if ((this.tag & 7) != 2) {
            throw zzdqn.zzbac();
        }
        if ((list instanceof zzdqu) && !z) {
            zzdqu zzdquVar = (zzdqu) list;
            do {
                zzdquVar.zzdb(zzaxx());
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp2 = this.zzhgg.zzaxp();
                }
            } while (iZzaxp2 == this.tag);
            this.zzhgi = iZzaxp2;
            return;
        }
        do {
            list.add(z ? zzaxw() : readString());
            if (this.zzhgg.zzayf()) {
                return;
            } else {
                iZzaxp = this.zzhgg.zzaxp();
            }
        } while (iZzaxp == this.tag);
        this.zzhgi = iZzaxp;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzdsd
    public final <T> void zza(List<T> list, zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException {
        int iZzaxp;
        int i = this.tag;
        if ((i & 7) != 2) {
            throw zzdqn.zzbac();
        }
        do {
            list.add(zzc(zzdscVar, zzdpqVar));
            if (this.zzhgg.zzayf() || this.zzhgi != 0) {
                return;
            } else {
                iZzaxp = this.zzhgg.zzaxp();
            }
        } while (iZzaxp == i);
        this.zzhgi = iZzaxp;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzdsd
    public final <T> void zzb(List<T> list, zzdsc<T> zzdscVar, zzdpq zzdpqVar) throws IOException {
        int iZzaxp;
        int i = this.tag;
        if ((i & 7) != 3) {
            throw zzdqn.zzbac();
        }
        do {
            list.add(zzd(zzdscVar, zzdpqVar));
            if (this.zzhgg.zzayf() || this.zzhgi != 0) {
                return;
            } else {
                iZzaxp = this.zzhgg.zzaxp();
            }
        } while (iZzaxp == i);
        this.zzhgi = iZzaxp;
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzt(List<zzdot> list) throws IOException {
        int iZzaxp;
        if ((this.tag & 7) != 2) {
            throw zzdqn.zzbac();
        }
        do {
            list.add(zzaxx());
            if (this.zzhgg.zzayf()) {
                return;
            } else {
                iZzaxp = this.zzhgg.zzaxp();
            }
        } while (iZzaxp == this.tag);
        this.zzhgi = iZzaxp;
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzu(List<Integer> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaxy());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzayg = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaxy());
                } while (this.zzhgg.zzayg() < iZzayg);
                zzfz(iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaxy()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzayg2 = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaxy()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            zzfz(iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzv(List<Integer> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaxz());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzayg = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaxz());
                } while (this.zzhgg.zzayg() < iZzayg);
                zzfz(iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaxz()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzayg2 = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaxz()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            zzfz(iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzw(List<Integer> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            int i = this.tag & 7;
            if (i == 2) {
                int iZzaxy = this.zzhgg.zzaxy();
                zzfy(iZzaxy);
                int iZzayg = this.zzhgg.zzayg() + iZzaxy;
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaya());
                } while (this.zzhgg.zzayg() < iZzayg);
                return;
            }
            if (i == 5) {
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzaya());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int iZzaxy2 = this.zzhgg.zzaxy();
            zzfy(iZzaxy2);
            int iZzayg2 = this.zzhgg.zzayg() + iZzaxy2;
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaya()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zzhgg.zzaya()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzx(List<Long> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdrb) {
            zzdrb zzdrbVar = (zzdrb) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzayb());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzaxy = this.zzhgg.zzaxy();
                zzfx(iZzaxy);
                int iZzayg = this.zzhgg.zzayg() + iZzaxy;
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzayb());
                } while (this.zzhgg.zzayg() < iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.zzhgg.zzayb()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzaxy2 = this.zzhgg.zzaxy();
            zzfx(iZzaxy2);
            int iZzayg2 = this.zzhgg.zzayg() + iZzaxy2;
            do {
                list.add(Long.valueOf(this.zzhgg.zzayb()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzy(List<Integer> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdqf) {
            zzdqf zzdqfVar = (zzdqf) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzayc());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzayg = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
                do {
                    zzdqfVar.zzgq(this.zzhgg.zzayc());
                } while (this.zzhgg.zzayg() < iZzayg);
                zzfz(iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzhgg.zzayc()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzayg2 = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
            do {
                list.add(Integer.valueOf(this.zzhgg.zzayc()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            zzfz(iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    @Override // com.google.android.gms.internal.ads.zzdsd
    public final void zzz(List<Long> list) throws IOException {
        int iZzaxp;
        int iZzaxp2;
        if (list instanceof zzdrb) {
            zzdrb zzdrbVar = (zzdrb) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzayd());
                    if (this.zzhgg.zzayf()) {
                        return;
                    } else {
                        iZzaxp2 = this.zzhgg.zzaxp();
                    }
                } while (iZzaxp2 == this.tag);
                this.zzhgi = iZzaxp2;
                return;
            }
            if (i == 2) {
                int iZzayg = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
                do {
                    zzdrbVar.zzfl(this.zzhgg.zzayd());
                } while (this.zzhgg.zzayg() < iZzayg);
                zzfz(iZzayg);
                return;
            }
            throw zzdqn.zzbac();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzhgg.zzayd()));
                if (this.zzhgg.zzayf()) {
                    return;
                } else {
                    iZzaxp = this.zzhgg.zzaxp();
                }
            } while (iZzaxp == this.tag);
            this.zzhgi = iZzaxp;
            return;
        }
        if (i2 == 2) {
            int iZzayg2 = this.zzhgg.zzayg() + this.zzhgg.zzaxy();
            do {
                list.add(Long.valueOf(this.zzhgg.zzayd()));
            } while (this.zzhgg.zzayg() < iZzayg2);
            zzfz(iZzayg2);
            return;
        }
        throw zzdqn.zzbac();
    }

    private static void zzfx(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzdqn.zzbad();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzdsd
    public final <K, V> void zza(Map<K, V> map, zzdre<K, V> zzdreVar, zzdpq zzdpqVar) throws IOException {
        zzfw(2);
        int iZzfs = this.zzhgg.zzfs(this.zzhgg.zzaxy());
        Object objZza = zzdreVar.zzhmg;
        Object objZza2 = zzdreVar.zzcfk;
        while (true) {
            try {
                int iZzayn = zzayn();
                if (iZzayn == Integer.MAX_VALUE || this.zzhgg.zzayf()) {
                    break;
                }
                if (iZzayn == 1) {
                    objZza = zza(zzdreVar.zzhmf, (Class<?>) null, (zzdpq) null);
                } else if (iZzayn == 2) {
                    objZza2 = zza(zzdreVar.zzhmh, zzdreVar.zzcfk.getClass(), zzdpqVar);
                } else {
                    try {
                        if (!zzayo()) {
                            throw new zzdqn("Unable to parse map entry.");
                        }
                    } catch (zzdqm unused) {
                        if (!zzayo()) {
                            throw new zzdqn("Unable to parse map entry.");
                        }
                    }
                }
            } catch (Throwable th) {
                this.zzhgg.zzft(iZzfs);
                throw th;
            }
        }
        map.put(objZza, objZza2);
        this.zzhgg.zzft(iZzfs);
    }

    private final Object zza(zzdtl zzdtlVar, Class<?> cls, zzdpq zzdpqVar) throws IOException {
        switch (zzdpj.zzhgf[zzdtlVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzaxv());
            case 2:
                return zzaxx();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzaxz());
            case 5:
                return Integer.valueOf(zzaxu());
            case 6:
                return Long.valueOf(zzaxt());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzaxs());
            case 9:
                return Long.valueOf(zzaxr());
            case 10:
                zzfw(2);
                return zzc(zzdry.zzbba().zzh(cls), zzdpqVar);
            case 11:
                return Integer.valueOf(zzaya());
            case 12:
                return Long.valueOf(zzayb());
            case 13:
                return Integer.valueOf(zzayc());
            case 14:
                return Long.valueOf(zzayd());
            case 15:
                return zzaxw();
            case 16:
                return Integer.valueOf(zzaxy());
            case 17:
                return Long.valueOf(zzaxq());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzfy(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzdqn.zzbad();
        }
    }

    private final void zzfz(int i) throws IOException {
        if (this.zzhgg.zzayg() != i) {
            throw zzdqn.zzazx();
        }
    }
}
