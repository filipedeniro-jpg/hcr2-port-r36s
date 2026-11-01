package com.google.android.gms.internal.ads;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdje extends zzdqd<zzdje, zzb> implements zzdrp {
    private static volatile zzdrw<zzdje> zzdv;
    private static final zzdje zzgxv;
    private int zzgxt;
    private zzdqk<zza> zzgxu = zzazr();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd<zza, C0045zza> implements zzdrp {
        private static volatile zzdrw<zza> zzdv;
        private static final zza zzgxz;
        private int zzgxm;
        private zzdiu zzgxw;
        private int zzgxx;
        private int zzgxy;

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdje$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class C0045zza extends zzdqd.zza<zza, C0045zza> implements zzdrp {
            private C0045zza() {
                super(zza.zzgxz);
            }

            public final C0045zza zzb(zzdiu zzdiuVar) {
                zzazi();
                ((zza) this.zzhkb).zza(zzdiuVar);
                return this;
            }

            public final C0045zza zzb(zzdiy zzdiyVar) {
                zzazi();
                ((zza) this.zzhkb).zza(zzdiyVar);
                return this;
            }

            public final C0045zza zzev(int i) {
                zzazi();
                ((zza) this.zzhkb).zzet(i);
                return this;
            }

            public final C0045zza zzb(zzdjq zzdjqVar) {
                zzazi();
                ((zza) this.zzhkb).zza(zzdjqVar);
                return this;
            }

            /* synthetic */ C0045zza(zzdjd zzdjdVar) {
                this();
            }
        }

        public final boolean zzaui() {
            return this.zzgxw != null;
        }

        public final zzdiu zzauj() {
            zzdiu zzdiuVar = this.zzgxw;
            return zzdiuVar == null ? zzdiu.zzatt() : zzdiuVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzdiu zzdiuVar) {
            if (zzdiuVar == null) {
                throw new NullPointerException();
            }
            this.zzgxw = zzdiuVar;
        }

        public final zzdiy zzapn() {
            zzdiy zzdiyVarZzep = zzdiy.zzep(this.zzgxx);
            return zzdiyVarZzep == null ? zzdiy.UNRECOGNIZED : zzdiyVarZzep;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzdiy zzdiyVar) {
            if (zzdiyVar == null) {
                throw new NullPointerException();
            }
            this.zzgxx = zzdiyVar.zzab();
        }

        public final int zzauk() {
            return this.zzgxy;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzet(int i) {
            this.zzgxy = i;
        }

        public final zzdjq zzapo() {
            zzdjq zzdjqVarZzfa = zzdjq.zzfa(this.zzgxm);
            return zzdjqVarZzfa == null ? zzdjq.UNRECOGNIZED : zzdjqVarZzfa;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzdjq zzdjqVar) {
            if (zzdjqVar == null) {
                throw new NullPointerException();
            }
            this.zzgxm = zzdjqVar.zzab();
        }

        public static C0045zza zzaul() {
            return zzgxz.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzdjd zzdjdVar = null;
            switch (zzdjd.zzdi[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0045zza(zzdjdVar);
                case 3:
                    return zza(zzgxz, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzgxw", "zzgxx", "zzgxy", "zzgxm"});
                case 4:
                    return zzgxz;
                case 5:
                    zzdrw<zza> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zza.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzgxz);
                                zzdv = zzcVar;
                            }
                            break;
                        }
                    }
                    return zzcVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zza zzaVar = new zza();
            zzgxz = zzaVar;
            zzdqd.zza((Class<zza>) zza.class, zzaVar);
        }
    }

    private zzdje() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzb extends zzdqd.zza<zzdje, zzb> implements zzdrp {
        private zzb() {
            super(zzdje.zzgxv);
        }

        public final zzb zzeu(int i) {
            zzazi();
            ((zzdje) this.zzhkb).zzes(i);
            return this;
        }

        public final zzb zzb(zza zzaVar) {
            zzazi();
            ((zzdje) this.zzhkb).zza(zzaVar);
            return this;
        }

        /* synthetic */ zzb(zzdjd zzdjdVar) {
            this();
        }
    }

    public final int zzaud() {
        return this.zzgxt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzes(int i) {
        this.zzgxt = i;
    }

    public final List<zza> zzaue() {
        return this.zzgxu;
    }

    public final int zzauf() {
        return this.zzgxu.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zza zzaVar) {
        if (zzaVar == null) {
            throw new NullPointerException();
        }
        if (!this.zzgxu.zzaxd()) {
            this.zzgxu = zzdqd.zza(this.zzgxu);
        }
        this.zzgxu.add(zzaVar);
    }

    public static zzdje zzm(byte[] bArr) throws zzdqn {
        return (zzdje) zzdqd.zza(zzgxv, bArr);
    }

    public static zzb zzaug() {
        return zzgxv.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdjd zzdjdVar = null;
        switch (zzdjd.zzdi[i - 1]) {
            case 1:
                return new zzdje();
            case 2:
                return new zzb(zzdjdVar);
            case 3:
                return zza(zzgxv, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzgxt", "zzgxu", zza.class});
            case 4:
                return zzgxv;
            case 5:
                zzdrw<zzdje> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdje.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgxv);
                            zzdv = zzcVar;
                        }
                        break;
                    }
                }
                return zzcVar;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        zzdje zzdjeVar = new zzdje();
        zzgxv = zzdjeVar;
        zzdqd.zza((Class<zzdje>) zzdje.class, zzdjeVar);
    }
}
