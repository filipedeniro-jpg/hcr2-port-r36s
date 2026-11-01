package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdjf extends zzdqd<zzdjf, zza> implements zzdrp {
    private static volatile zzdrw<zzdjf> zzdv;
    private static final zzdjf zzgyb;
    private int zzgxt;
    private zzdqk<zzb> zzgya = zzazr();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzb extends zzdqd<zzb, zza> implements zzdrp {
        private static volatile zzdrw<zzb> zzdv;
        private static final zzb zzgyc;
        private String zzgwv = "";
        private int zzgxm;
        private int zzgxx;
        private int zzgxy;

        private zzb() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzb, zza> implements zzdrp {
            private zza() {
                super(zzb.zzgyc);
            }

            public final zza zzhc(String str) {
                zzazi();
                ((zzb) this.zzhkb).zzgv(str);
                return this;
            }

            public final zza zzc(zzdiy zzdiyVar) {
                zzazi();
                ((zzb) this.zzhkb).zza(zzdiyVar);
                return this;
            }

            public final zza zzex(int i) {
                zzazi();
                ((zzb) this.zzhkb).zzet(i);
                return this;
            }

            public final zza zzc(zzdjq zzdjqVar) {
                zzazi();
                ((zzb) this.zzhkb).zza(zzdjqVar);
                return this;
            }

            /* synthetic */ zza(zzdjg zzdjgVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzgv(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzgwv = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzdiy zzdiyVar) {
            if (zzdiyVar == null) {
                throw new NullPointerException();
            }
            this.zzgxx = zzdiyVar.zzab();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzet(int i) {
            this.zzgxy = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzdjq zzdjqVar) {
            if (zzdjqVar == null) {
                throw new NullPointerException();
            }
            this.zzgxm = zzdjqVar.zzab();
        }

        public static zza zzaup() {
            return zzgyc.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzdjg zzdjgVar = null;
            switch (zzdjg.zzdi[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzdjgVar);
                case 3:
                    return zza(zzgyc, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzgwv", "zzgxx", "zzgxy", "zzgxm"});
                case 4:
                    return zzgyc;
                case 5:
                    zzdrw<zzb> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzb.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzgyc);
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
            zzb zzbVar = new zzb();
            zzgyc = zzbVar;
            zzdqd.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }

    private zzdjf() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdjf, zza> implements zzdrp {
        private zza() {
            super(zzdjf.zzgyb);
        }

        public final zza zzew(int i) {
            zzazi();
            ((zzdjf) this.zzhkb).zzes(i);
            return this;
        }

        public final zza zzb(zzb zzbVar) {
            zzazi();
            ((zzdjf) this.zzhkb).zza(zzbVar);
            return this;
        }

        /* synthetic */ zza(zzdjg zzdjgVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzes(int i) {
        this.zzgxt = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzb zzbVar) {
        if (zzbVar == null) {
            throw new NullPointerException();
        }
        if (!this.zzgya.zzaxd()) {
            this.zzgya = zzdqd.zza(this.zzgya);
        }
        this.zzgya.add(zzbVar);
    }

    public static zza zzaun() {
        return zzgyb.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdjg zzdjgVar = null;
        switch (zzdjg.zzdi[i - 1]) {
            case 1:
                return new zzdjf();
            case 2:
                return new zza(zzdjgVar);
            case 3:
                return zza(zzgyb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u000b\u0002\u001b", new Object[]{"zzgxt", "zzgya", zzb.class});
            case 4:
                return zzgyb;
            case 5:
                zzdrw<zzdjf> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdjf.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgyb);
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
        zzdjf zzdjfVar = new zzdjf();
        zzgyb = zzdjfVar;
        zzdqd.zza((Class<zzdjf>) zzdjf.class, zzdjfVar);
    }
}
