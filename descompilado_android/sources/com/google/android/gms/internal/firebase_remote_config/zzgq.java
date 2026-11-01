package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzgq implements zzjg {
    private int tag;
    private final zzgj zzps;
    private int zzpt;
    private int zzpu = 0;

    public static zzgq zza(zzgj zzgjVar) {
        return zzgjVar.zzpg != null ? zzgjVar.zzpg : new zzgq(zzgjVar);
    }

    private zzgq(zzgj zzgjVar) {
        this.zzps = (zzgj) zzhm.zza(zzgjVar, "input");
        this.zzps.zzpg = this;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final int zzfz() throws IOException {
        int i = this.zzpu;
        if (i != 0) {
            this.tag = i;
            this.zzpu = 0;
        } else {
            this.tag = this.zzps.zzfb();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzpt) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final int getTag() {
        return this.tag;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final boolean zzga() throws IOException {
        int i;
        if (this.zzps.zzfr() || (i = this.tag) == this.zzpt) {
            return false;
        }
        return this.zzps.zzz(i);
    }

    private final void zzai(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzhq.zzhj();
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final double readDouble() throws IOException {
        zzai(1);
        return this.zzps.readDouble();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final float readFloat() throws IOException {
        zzai(5);
        return this.zzps.readFloat();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final long zzfc() throws IOException {
        zzai(0);
        return this.zzps.zzfc();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final long zzfd() throws IOException {
        zzai(0);
        return this.zzps.zzfd();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final int zzfe() throws IOException {
        zzai(0);
        return this.zzps.zzfe();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final long zzff() throws IOException {
        zzai(1);
        return this.zzps.zzff();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final int zzfg() throws IOException {
        zzai(5);
        return this.zzps.zzfg();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final boolean zzfh() throws IOException {
        zzai(0);
        return this.zzps.zzfh();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final String readString() throws IOException {
        zzai(2);
        return this.zzps.readString();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final String zzfi() throws IOException {
        zzai(2);
        return this.zzps.zzfi();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final <T> T zza(zzjf<T> zzjfVar, zzgx zzgxVar) throws IOException {
        zzai(2);
        return (T) zzc(zzjfVar, zzgxVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final <T> T zzb(zzjf<T> zzjfVar, zzgx zzgxVar) throws IOException {
        zzai(3);
        return (T) zzd(zzjfVar, zzgxVar);
    }

    private final <T> T zzc(zzjf<T> zzjfVar, zzgx zzgxVar) throws IOException {
        int iZzfk = this.zzps.zzfk();
        if (this.zzps.zzpd >= this.zzps.zzpe) {
            throw new zzhq("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iZzaa = this.zzps.zzaa(iZzfk);
        T tNewInstance = zzjfVar.newInstance();
        this.zzps.zzpd++;
        zzjfVar.zza(tNewInstance, this, zzgxVar);
        zzjfVar.zzm(tNewInstance);
        this.zzps.zzy(0);
        this.zzps.zzpd--;
        this.zzps.zzab(iZzaa);
        return tNewInstance;
    }

    private final <T> T zzd(zzjf<T> zzjfVar, zzgx zzgxVar) throws IOException {
        int i = this.zzpt;
        this.zzpt = ((this.tag >>> 3) << 3) | 4;
        try {
            T tNewInstance = zzjfVar.newInstance();
            zzjfVar.zza(tNewInstance, this, zzgxVar);
            zzjfVar.zzm(tNewInstance);
            if (this.tag != this.zzpt) {
                throw zzhq.zzhl();
            }
            this.zzpt = i;
            return tNewInstance;
        } catch (Throwable th) {
            this.zzpt = i;
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final zzfx zzfj() throws IOException {
        zzai(2);
        return this.zzps.zzfj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final int zzfk() throws IOException {
        zzai(0);
        return this.zzps.zzfk();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final int zzfl() throws IOException {
        zzai(0);
        return this.zzps.zzfl();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final int zzfm() throws IOException {
        zzai(5);
        return this.zzps.zzfm();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final long zzfn() throws IOException {
        zzai(1);
        return this.zzps.zzfn();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final int zzfo() throws IOException {
        zzai(0);
        return this.zzps.zzfo();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final long zzfp() throws IOException {
        zzai(0);
        return this.zzps.zzfp();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzc(List<Double> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzgt) {
            zzgt zzgtVar = (zzgt) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzgtVar.zze(this.zzps.readDouble());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfk = this.zzps.zzfk();
                zzaj(iZzfk);
                int iZzfs = this.zzps.zzfs() + iZzfk;
                do {
                    zzgtVar.zze(this.zzps.readDouble());
                } while (this.zzps.zzfs() < iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 1) {
            do {
                list.add(Double.valueOf(this.zzps.readDouble()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfk2 = this.zzps.zzfk();
            zzaj(iZzfk2);
            int iZzfs2 = this.zzps.zzfs() + iZzfk2;
            do {
                list.add(Double.valueOf(this.zzps.readDouble()));
            } while (this.zzps.zzfs() < iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzd(List<Float> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzhg) {
            zzhg zzhgVar = (zzhg) list;
            int i = this.tag & 7;
            if (i == 2) {
                int iZzfk = this.zzps.zzfk();
                zzak(iZzfk);
                int iZzfs = this.zzps.zzfs() + iZzfk;
                do {
                    zzhgVar.zzd(this.zzps.readFloat());
                } while (this.zzps.zzfs() < iZzfs);
                return;
            }
            if (i == 5) {
                do {
                    zzhgVar.zzd(this.zzps.readFloat());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int iZzfk2 = this.zzps.zzfk();
            zzak(iZzfk2);
            int iZzfs2 = this.zzps.zzfs() + iZzfk2;
            do {
                list.add(Float.valueOf(this.zzps.readFloat()));
            } while (this.zzps.zzfs() < iZzfs2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Float.valueOf(this.zzps.readFloat()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zze(List<Long> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzieVar.zzp(this.zzps.zzfc());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfs = this.zzps.zzfs() + this.zzps.zzfk();
                do {
                    zzieVar.zzp(this.zzps.zzfc());
                } while (this.zzps.zzfs() < iZzfs);
                zzal(iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzps.zzfc()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfs2 = this.zzps.zzfs() + this.zzps.zzfk();
            do {
                list.add(Long.valueOf(this.zzps.zzfc()));
            } while (this.zzps.zzfs() < iZzfs2);
            zzal(iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzf(List<Long> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzieVar.zzp(this.zzps.zzfd());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfs = this.zzps.zzfs() + this.zzps.zzfk();
                do {
                    zzieVar.zzp(this.zzps.zzfd());
                } while (this.zzps.zzfs() < iZzfs);
                zzal(iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzps.zzfd()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfs2 = this.zzps.zzfs() + this.zzps.zzfk();
            do {
                list.add(Long.valueOf(this.zzps.zzfd()));
            } while (this.zzps.zzfs() < iZzfs2);
            zzal(iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzg(List<Integer> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhkVar.zzbb(this.zzps.zzfe());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfs = this.zzps.zzfs() + this.zzps.zzfk();
                do {
                    zzhkVar.zzbb(this.zzps.zzfe());
                } while (this.zzps.zzfs() < iZzfs);
                zzal(iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzps.zzfe()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfs2 = this.zzps.zzfs() + this.zzps.zzfk();
            do {
                list.add(Integer.valueOf(this.zzps.zzfe()));
            } while (this.zzps.zzfs() < iZzfs2);
            zzal(iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzh(List<Long> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzieVar.zzp(this.zzps.zzff());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfk = this.zzps.zzfk();
                zzaj(iZzfk);
                int iZzfs = this.zzps.zzfs() + iZzfk;
                do {
                    zzieVar.zzp(this.zzps.zzff());
                } while (this.zzps.zzfs() < iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.zzps.zzff()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfk2 = this.zzps.zzfk();
            zzaj(iZzfk2);
            int iZzfs2 = this.zzps.zzfs() + iZzfk2;
            do {
                list.add(Long.valueOf(this.zzps.zzff()));
            } while (this.zzps.zzfs() < iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzi(List<Integer> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            int i = this.tag & 7;
            if (i == 2) {
                int iZzfk = this.zzps.zzfk();
                zzak(iZzfk);
                int iZzfs = this.zzps.zzfs() + iZzfk;
                do {
                    zzhkVar.zzbb(this.zzps.zzfg());
                } while (this.zzps.zzfs() < iZzfs);
                return;
            }
            if (i == 5) {
                do {
                    zzhkVar.zzbb(this.zzps.zzfg());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int iZzfk2 = this.zzps.zzfk();
            zzak(iZzfk2);
            int iZzfs2 = this.zzps.zzfs() + iZzfk2;
            do {
                list.add(Integer.valueOf(this.zzps.zzfg()));
            } while (this.zzps.zzfs() < iZzfs2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zzps.zzfg()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzj(List<Boolean> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzfv) {
            zzfv zzfvVar = (zzfv) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzfvVar.addBoolean(this.zzps.zzfh());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfs = this.zzps.zzfs() + this.zzps.zzfk();
                do {
                    zzfvVar.addBoolean(this.zzps.zzfh());
                } while (this.zzps.zzfs() < iZzfs);
                zzal(iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Boolean.valueOf(this.zzps.zzfh()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfs2 = this.zzps.zzfs() + this.zzps.zzfk();
            do {
                list.add(Boolean.valueOf(this.zzps.zzfh()));
            } while (this.zzps.zzfs() < iZzfs2);
            zzal(iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzk(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int iZzfb;
        int iZzfb2;
        if ((this.tag & 7) != 2) {
            throw zzhq.zzhj();
        }
        if ((list instanceof zzhx) && !z) {
            zzhx zzhxVar = (zzhx) list;
            do {
                zzhxVar.zzd(zzfj());
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb2 = this.zzps.zzfb();
                }
            } while (iZzfb2 == this.tag);
            this.zzpu = iZzfb2;
            return;
        }
        do {
            list.add(z ? zzfi() : readString());
            if (this.zzps.zzfr()) {
                return;
            } else {
                iZzfb = this.zzps.zzfb();
            }
        } while (iZzfb == this.tag);
        this.zzpu = iZzfb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final <T> void zza(List<T> list, zzjf<T> zzjfVar, zzgx zzgxVar) throws IOException {
        int iZzfb;
        int i = this.tag;
        if ((i & 7) != 2) {
            throw zzhq.zzhj();
        }
        do {
            list.add(zzc(zzjfVar, zzgxVar));
            if (this.zzps.zzfr() || this.zzpu != 0) {
                return;
            } else {
                iZzfb = this.zzps.zzfb();
            }
        } while (iZzfb == i);
        this.zzpu = iZzfb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final <T> void zzb(List<T> list, zzjf<T> zzjfVar, zzgx zzgxVar) throws IOException {
        int iZzfb;
        int i = this.tag;
        if ((i & 7) != 3) {
            throw zzhq.zzhj();
        }
        do {
            list.add(zzd(zzjfVar, zzgxVar));
            if (this.zzps.zzfr() || this.zzpu != 0) {
                return;
            } else {
                iZzfb = this.zzps.zzfb();
            }
        } while (iZzfb == i);
        this.zzpu = iZzfb;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzl(List<zzfx> list) throws IOException {
        int iZzfb;
        if ((this.tag & 7) != 2) {
            throw zzhq.zzhj();
        }
        do {
            list.add(zzfj());
            if (this.zzps.zzfr()) {
                return;
            } else {
                iZzfb = this.zzps.zzfb();
            }
        } while (iZzfb == this.tag);
        this.zzpu = iZzfb;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzm(List<Integer> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhkVar.zzbb(this.zzps.zzfk());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfs = this.zzps.zzfs() + this.zzps.zzfk();
                do {
                    zzhkVar.zzbb(this.zzps.zzfk());
                } while (this.zzps.zzfs() < iZzfs);
                zzal(iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzps.zzfk()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfs2 = this.zzps.zzfs() + this.zzps.zzfk();
            do {
                list.add(Integer.valueOf(this.zzps.zzfk()));
            } while (this.zzps.zzfs() < iZzfs2);
            zzal(iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzn(List<Integer> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhkVar.zzbb(this.zzps.zzfl());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfs = this.zzps.zzfs() + this.zzps.zzfk();
                do {
                    zzhkVar.zzbb(this.zzps.zzfl());
                } while (this.zzps.zzfs() < iZzfs);
                zzal(iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzps.zzfl()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfs2 = this.zzps.zzfs() + this.zzps.zzfk();
            do {
                list.add(Integer.valueOf(this.zzps.zzfl()));
            } while (this.zzps.zzfs() < iZzfs2);
            zzal(iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzo(List<Integer> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            int i = this.tag & 7;
            if (i == 2) {
                int iZzfk = this.zzps.zzfk();
                zzak(iZzfk);
                int iZzfs = this.zzps.zzfs() + iZzfk;
                do {
                    zzhkVar.zzbb(this.zzps.zzfm());
                } while (this.zzps.zzfs() < iZzfs);
                return;
            }
            if (i == 5) {
                do {
                    zzhkVar.zzbb(this.zzps.zzfm());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int iZzfk2 = this.zzps.zzfk();
            zzak(iZzfk2);
            int iZzfs2 = this.zzps.zzfs() + iZzfk2;
            do {
                list.add(Integer.valueOf(this.zzps.zzfm()));
            } while (this.zzps.zzfs() < iZzfs2);
            return;
        }
        if (i2 == 5) {
            do {
                list.add(Integer.valueOf(this.zzps.zzfm()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzp(List<Long> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzieVar.zzp(this.zzps.zzfn());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfk = this.zzps.zzfk();
                zzaj(iZzfk);
                int iZzfs = this.zzps.zzfs() + iZzfk;
                do {
                    zzieVar.zzp(this.zzps.zzfn());
                } while (this.zzps.zzfs() < iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 1) {
            do {
                list.add(Long.valueOf(this.zzps.zzfn()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfk2 = this.zzps.zzfk();
            zzaj(iZzfk2);
            int iZzfs2 = this.zzps.zzfs() + iZzfk2;
            do {
                list.add(Long.valueOf(this.zzps.zzfn()));
            } while (this.zzps.zzfs() < iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzq(List<Integer> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzhk) {
            zzhk zzhkVar = (zzhk) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzhkVar.zzbb(this.zzps.zzfo());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfs = this.zzps.zzfs() + this.zzps.zzfk();
                do {
                    zzhkVar.zzbb(this.zzps.zzfo());
                } while (this.zzps.zzfs() < iZzfs);
                zzal(iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Integer.valueOf(this.zzps.zzfo()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfs2 = this.zzps.zzfs() + this.zzps.zzfk();
            do {
                list.add(Integer.valueOf(this.zzps.zzfo()));
            } while (this.zzps.zzfs() < iZzfs2);
            zzal(iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final void zzr(List<Long> list) throws IOException {
        int iZzfb;
        int iZzfb2;
        if (list instanceof zzie) {
            zzie zzieVar = (zzie) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzieVar.zzp(this.zzps.zzfp());
                    if (this.zzps.zzfr()) {
                        return;
                    } else {
                        iZzfb2 = this.zzps.zzfb();
                    }
                } while (iZzfb2 == this.tag);
                this.zzpu = iZzfb2;
                return;
            }
            if (i == 2) {
                int iZzfs = this.zzps.zzfs() + this.zzps.zzfk();
                do {
                    zzieVar.zzp(this.zzps.zzfp());
                } while (this.zzps.zzfs() < iZzfs);
                zzal(iZzfs);
                return;
            }
            throw zzhq.zzhj();
        }
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                list.add(Long.valueOf(this.zzps.zzfp()));
                if (this.zzps.zzfr()) {
                    return;
                } else {
                    iZzfb = this.zzps.zzfb();
                }
            } while (iZzfb == this.tag);
            this.zzpu = iZzfb;
            return;
        }
        if (i2 == 2) {
            int iZzfs2 = this.zzps.zzfs() + this.zzps.zzfk();
            do {
                list.add(Long.valueOf(this.zzps.zzfp()));
            } while (this.zzps.zzfs() < iZzfs2);
            zzal(iZzfs2);
            return;
        }
        throw zzhq.zzhj();
    }

    private static void zzaj(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzhq.zzhl();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_remote_config.zzjg
    public final <K, V> void zza(Map<K, V> map, zzih<K, V> zzihVar, zzgx zzgxVar) throws IOException {
        zzai(2);
        int iZzaa = this.zzps.zzaa(this.zzps.zzfk());
        Object objZza = zzihVar.zzvj;
        Object objZza2 = zzihVar.zzvl;
        while (true) {
            try {
                int iZzfz = zzfz();
                if (iZzfz == Integer.MAX_VALUE || this.zzps.zzfr()) {
                    break;
                }
                if (iZzfz == 1) {
                    objZza = zza(zzihVar.zzvi, (Class<?>) null, (zzgx) null);
                } else if (iZzfz == 2) {
                    objZza2 = zza(zzihVar.zzvk, zzihVar.zzvl.getClass(), zzgxVar);
                } else {
                    try {
                        if (!zzga()) {
                            throw new zzhq("Unable to parse map entry.");
                        }
                    } catch (zzhp unused) {
                        if (!zzga()) {
                            throw new zzhq("Unable to parse map entry.");
                        }
                    }
                }
            } catch (Throwable th) {
                this.zzps.zzab(iZzaa);
                throw th;
            }
        }
        map.put(objZza, objZza2);
        this.zzps.zzab(iZzaa);
    }

    private final Object zza(zzko zzkoVar, Class<?> cls, zzgx zzgxVar) throws IOException {
        switch (zzgp.zzpr[zzkoVar.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzfh());
            case 2:
                return zzfj();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzfl());
            case 5:
                return Integer.valueOf(zzfg());
            case 6:
                return Long.valueOf(zzff());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzfe());
            case 9:
                return Long.valueOf(zzfd());
            case 10:
                zzai(2);
                return zzc(zzjb.zzii().zzk(cls), zzgxVar);
            case 11:
                return Integer.valueOf(zzfm());
            case 12:
                return Long.valueOf(zzfn());
            case 13:
                return Integer.valueOf(zzfo());
            case 14:
                return Long.valueOf(zzfp());
            case 15:
                return zzfi();
            case 16:
                return Integer.valueOf(zzfk());
            case 17:
                return Long.valueOf(zzfc());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzak(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzhq.zzhl();
        }
    }

    private final void zzal(int i) throws IOException {
        if (this.zzps.zzfs() != i) {
            throw zzhq.zzhe();
        }
    }
}
