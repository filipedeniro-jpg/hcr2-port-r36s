package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdua {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd<zza, zzb> implements zzdrp {
        private static volatile zzdrw<zza> zzdv;
        private static final zza zzhrm;
        private int zzdj;
        private int zzhrf;
        private C0046zza zzhrg;
        private boolean zzhrj;
        private boolean zzhrk;
        private byte zzhrl = 2;
        private zzdot zzhrh = zzdot.zzhfn;
        private zzdot zzhri = zzdot.zzhfn;

        /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class C0046zza extends zzdqd<C0046zza, C0047zza> implements zzdrp {
            private static volatile zzdrw<C0046zza> zzdv;
            private static final C0046zza zzhrr;
            private int zzdj;
            private String zzhrn = "";
            private String zzhro = "";
            private String zzhrp = "";
            private int zzhrq;

            private C0046zza() {
            }

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zza$zza$zza, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class C0047zza extends zzdqd.zza<C0046zza, C0047zza> implements zzdrp {
                private C0047zza() {
                    super(C0046zza.zzhrr);
                }

                /* synthetic */ C0047zza(zzduc zzducVar) {
                    this();
                }
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzduc zzducVar = null;
                switch (zzduc.zzdi[i - 1]) {
                    case 1:
                        return new C0046zza();
                    case 2:
                        return new C0047zza(zzducVar);
                    case 3:
                        return zza(zzhrr, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0004\u0003", new Object[]{"zzdj", "zzhrn", "zzhro", "zzhrp", "zzhrq"});
                    case 4:
                        return zzhrr;
                    case 5:
                        zzdrw<C0046zza> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (C0046zza.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzhrr);
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
                C0046zza c0046zza = new C0046zza();
                zzhrr = c0046zza;
                zzdqd.zza((Class<C0046zza>) C0046zza.class, c0046zza);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public enum zzc implements zzdqh {
            SAFE(0),
            DANGEROUS(1),
            UNKNOWN(2),
            POTENTIALLY_UNWANTED(3),
            DANGEROUS_HOST(4);

            private static final zzdqg<zzc> zzeg = new zzdue();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static zzc zzhh(int i) {
                if (i == 0) {
                    return SAFE;
                }
                if (i == 1) {
                    return DANGEROUS;
                }
                if (i == 2) {
                    return UNKNOWN;
                }
                if (i == 3) {
                    return POTENTIALLY_UNWANTED;
                }
                if (i != 4) {
                    return null;
                }
                return DANGEROUS_HOST;
            }

            public static zzdqj zzac() {
                return zzdud.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzc(int i) {
                this.value = i;
            }
        }

        private zza() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zzb extends zzdqd.zza<zza, zzb> implements zzdrp {
            private zzb() {
                super(zza.zzhrm);
            }

            /* synthetic */ zzb(zzduc zzducVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzduc zzducVar = null;
            switch (zzduc.zzdi[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzb(zzducVar);
                case 3:
                    return zza(zzhrm, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0001\u0001Ԍ\u0000\u0002\t\u0001\u0003\n\u0002\u0004\n\u0003\u0005\u0007\u0004\u0006\u0007\u0005", new Object[]{"zzdj", "zzhrf", zzc.zzac(), "zzhrg", "zzhrh", "zzhri", "zzhrj", "zzhrk"});
                case 4:
                    return zzhrm;
                case 5:
                    zzdrw<zza> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zza.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzhrm);
                                zzdv = zzcVar;
                            }
                            break;
                        }
                    }
                    return zzcVar;
                case 6:
                    return Byte.valueOf(this.zzhrl);
                case 7:
                    this.zzhrl = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zza zzaVar = new zza();
            zzhrm = zzaVar;
            zzdqd.zza((Class<zza>) zza.class, zzaVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzb extends zzdqd<zzb, zza> implements zzdrp {
        private static volatile zzdrw<zzb> zzdv;
        private static final zzb zzhsn;
        private int zzbuv;
        private int zzdj;
        private int zzhry;
        private C0048zzb zzhsb;
        private zzf zzhse;
        private boolean zzhsf;
        private boolean zzhsi;
        private boolean zzhsj;
        private zzi zzhsk;
        private byte zzhrl = 2;
        private String zzhro = "";
        private String zzhrz = "";
        private String zzhsa = "";
        private zzdqk<zzh> zzhsc = zzazr();
        private String zzhsd = "";
        private zzdqk<String> zzhsg = zzdqd.zzazr();
        private String zzhsh = "";
        private zzdot zzhrh = zzdot.zzhfn;
        private zzdqk<String> zzhsl = zzdqd.zzazr();
        private zzdqk<String> zzhsm = zzdqd.zzazr();

        /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zzb$zzb, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class C0048zzb extends zzdqd<C0048zzb, zza> implements zzdrp {
            private static volatile zzdrw<C0048zzb> zzdv;
            private static final C0048zzb zzhsp;
            private int zzdj;
            private String zzhso = "";

            private C0048zzb() {
            }

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zzb$zzb$zza */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class zza extends zzdqd.zza<C0048zzb, zza> implements zzdrp {
                private zza() {
                    super(C0048zzb.zzhsp);
                }

                public final zza zzhn(String str) {
                    zzazi();
                    ((C0048zzb) this.zzhkb).zzho(str);
                    return this;
                }

                /* synthetic */ zza(zzduc zzducVar) {
                    this();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzho(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 1;
                this.zzhso = str;
            }

            public static zza zzbci() {
                return zzhsp.zzazo();
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzduc zzducVar = null;
                switch (zzduc.zzdi[i - 1]) {
                    case 1:
                        return new C0048zzb();
                    case 2:
                        return new zza(zzducVar);
                    case 3:
                        return zza(zzhsp, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", new Object[]{"zzdj", "zzhso"});
                    case 4:
                        return zzhsp;
                    case 5:
                        zzdrw<C0048zzb> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (C0048zzb.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzhsp);
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
                C0048zzb c0048zzb = new C0048zzb();
                zzhsp = c0048zzb;
                zzdqd.zza((Class<C0048zzb>) C0048zzb.class, c0048zzb);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zzc extends zzdqd<zzc, zza> implements zzdrp {
            private static volatile zzdrw<zzc> zzdv;
            private static final zzc zzhsr;
            private int zzdj;
            private byte zzhrl = 2;
            private zzdot zzhsq = zzdot.zzhfn;
            private zzdot zzgww = zzdot.zzhfn;

            private zzc() {
            }

            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class zza extends zzdqd.zza<zzc, zza> implements zzdrp {
                private zza() {
                    super(zzc.zzhsr);
                }

                public final zza zzdd(zzdot zzdotVar) {
                    zzazi();
                    ((zzc) this.zzhkb).zzdf(zzdotVar);
                    return this;
                }

                public final zza zzde(zzdot zzdotVar) {
                    zzazi();
                    ((zzc) this.zzhkb).zzbn(zzdotVar);
                    return this;
                }

                /* synthetic */ zza(zzduc zzducVar) {
                    this();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzdf(zzdot zzdotVar) {
                if (zzdotVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 1;
                this.zzhsq = zzdotVar;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbn(zzdot zzdotVar) {
                if (zzdotVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 2;
                this.zzgww = zzdotVar;
            }

            public static zza zzbck() {
                return zzhsr.zzazo();
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzduc zzducVar = null;
                switch (zzduc.zzdi[i - 1]) {
                    case 1:
                        return new zzc();
                    case 2:
                        return new zza(zzducVar);
                    case 3:
                        return zza(zzhsr, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001Ԋ\u0000\u0002\n\u0001", new Object[]{"zzdj", "zzhsq", "zzgww"});
                    case 4:
                        return zzhsr;
                    case 5:
                        zzdrw<zzc> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zzc.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzhsr);
                                    zzdv = zzcVar;
                                }
                                break;
                            }
                        }
                        return zzcVar;
                    case 6:
                        return Byte.valueOf(this.zzhrl);
                    case 7:
                        this.zzhrl = (byte) (obj != null ? 1 : 0);
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzc zzcVar = new zzc();
                zzhsr = zzcVar;
                zzdqd.zza((Class<zzc>) zzc.class, zzcVar);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zzd extends zzdqd<zzd, zza> implements zzdrp {
            private static volatile zzdrw<zzd> zzdv;
            private static final zzd zzhsx;
            private int zzdj;
            private C0049zzb zzhss;
            private int zzhsw;
            private byte zzhrl = 2;
            private zzdqk<zzc> zzhst = zzazr();
            private zzdot zzhsu = zzdot.zzhfn;
            private zzdot zzhsv = zzdot.zzhfn;

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zzb$zzd$zzb, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class C0049zzb extends zzdqd<C0049zzb, zza> implements zzdrp {
                private static volatile zzdrw<C0049zzb> zzdv;
                private static final C0049zzb zzhtb;
                private int zzdj;
                private zzdot zzhsy = zzdot.zzhfn;
                private zzdot zzhsz = zzdot.zzhfn;
                private zzdot zzhta = zzdot.zzhfn;

                private C0049zzb() {
                }

                /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zzb$zzd$zzb$zza */
                /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
                public static final class zza extends zzdqd.zza<C0049zzb, zza> implements zzdrp {
                    private zza() {
                        super(C0049zzb.zzhtb);
                    }

                    /* synthetic */ zza(zzduc zzducVar) {
                        this();
                    }
                }

                @Override // com.google.android.gms.internal.ads.zzdqd
                protected final Object zza(int i, Object obj, Object obj2) {
                    zzduc zzducVar = null;
                    switch (zzduc.zzdi[i - 1]) {
                        case 1:
                            return new C0049zzb();
                        case 2:
                            return new zza(zzducVar);
                        case 3:
                            return zza(zzhtb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\n\u0000\u0002\n\u0001\u0003\n\u0002", new Object[]{"zzdj", "zzhsy", "zzhsz", "zzhta"});
                        case 4:
                            return zzhtb;
                        case 5:
                            zzdrw<C0049zzb> zzcVar = zzdv;
                            if (zzcVar == null) {
                                synchronized (C0049zzb.class) {
                                    zzcVar = zzdv;
                                    if (zzcVar == null) {
                                        zzcVar = new zzdqd.zzc<>(zzhtb);
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
                    C0049zzb c0049zzb = new C0049zzb();
                    zzhtb = c0049zzb;
                    zzdqd.zza((Class<C0049zzb>) C0049zzb.class, c0049zzb);
                }
            }

            private zzd() {
            }

            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class zza extends zzdqd.zza<zzd, zza> implements zzdrp {
                private zza() {
                    super(zzd.zzhsx);
                }

                /* synthetic */ zza(zzduc zzducVar) {
                    this();
                }
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzduc zzducVar = null;
                switch (zzduc.zzdi[i - 1]) {
                    case 1:
                        return new zzd();
                    case 2:
                        return new zza(zzducVar);
                    case 3:
                        return zza(zzhsx, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0001\u0001\t\u0000\u0002Л\u0003\n\u0001\u0004\n\u0002\u0005\u0004\u0003", new Object[]{"zzdj", "zzhss", "zzhst", zzc.class, "zzhsu", "zzhsv", "zzhsw"});
                    case 4:
                        return zzhsx;
                    case 5:
                        zzdrw<zzd> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zzd.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzhsx);
                                    zzdv = zzcVar;
                                }
                                break;
                            }
                        }
                        return zzcVar;
                    case 6:
                        return Byte.valueOf(this.zzhrl);
                    case 7:
                        this.zzhrl = (byte) (obj != null ? 1 : 0);
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzd zzdVar = new zzd();
                zzhsx = zzdVar;
                zzdqd.zza((Class<zzd>) zzd.class, zzdVar);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zze extends zzdqd<zze, zza> implements zzdrp {
            private static volatile zzdrw<zze> zzdv;
            private static final zze zzhte;
            private int zzdj;
            private int zzhsw;
            private C0050zzb zzhtc;
            private byte zzhrl = 2;
            private zzdqk<zzc> zzhst = zzazr();
            private zzdot zzhsu = zzdot.zzhfn;
            private zzdot zzhsv = zzdot.zzhfn;
            private zzdot zzhtd = zzdot.zzhfn;

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zzb$zze$zzb, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class C0050zzb extends zzdqd<C0050zzb, zza> implements zzdrp {
                private static volatile zzdrw<C0050zzb> zzdv;
                private static final C0050zzb zzhth;
                private int zzdj;
                private int zzhtf;
                private zzdot zzhtg = zzdot.zzhfn;
                private zzdot zzhta = zzdot.zzhfn;

                private C0050zzb() {
                }

                /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zzb$zze$zzb$zza */
                /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
                public static final class zza extends zzdqd.zza<C0050zzb, zza> implements zzdrp {
                    private zza() {
                        super(C0050zzb.zzhth);
                    }

                    /* synthetic */ zza(zzduc zzducVar) {
                        this();
                    }
                }

                @Override // com.google.android.gms.internal.ads.zzdqd
                protected final Object zza(int i, Object obj, Object obj2) {
                    zzduc zzducVar = null;
                    switch (zzduc.zzdi[i - 1]) {
                        case 1:
                            return new C0050zzb();
                        case 2:
                            return new zza(zzducVar);
                        case 3:
                            return zza(zzhth, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\n\u0001\u0003\n\u0002", new Object[]{"zzdj", "zzhtf", "zzhtg", "zzhta"});
                        case 4:
                            return zzhth;
                        case 5:
                            zzdrw<C0050zzb> zzcVar = zzdv;
                            if (zzcVar == null) {
                                synchronized (C0050zzb.class) {
                                    zzcVar = zzdv;
                                    if (zzcVar == null) {
                                        zzcVar = new zzdqd.zzc<>(zzhth);
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
                    C0050zzb c0050zzb = new C0050zzb();
                    zzhth = c0050zzb;
                    zzdqd.zza((Class<C0050zzb>) C0050zzb.class, c0050zzb);
                }
            }

            private zze() {
            }

            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class zza extends zzdqd.zza<zze, zza> implements zzdrp {
                private zza() {
                    super(zze.zzhte);
                }

                /* synthetic */ zza(zzduc zzducVar) {
                    this();
                }
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzduc zzducVar = null;
                switch (zzduc.zzdi[i - 1]) {
                    case 1:
                        return new zze();
                    case 2:
                        return new zza(zzducVar);
                    case 3:
                        return zza(zzhte, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001\t\u0000\u0002Л\u0003\n\u0001\u0004\n\u0002\u0005\u0004\u0003\u0006\n\u0004", new Object[]{"zzdj", "zzhtc", "zzhst", zzc.class, "zzhsu", "zzhsv", "zzhsw", "zzhtd"});
                    case 4:
                        return zzhte;
                    case 5:
                        zzdrw<zze> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zze.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzhte);
                                    zzdv = zzcVar;
                                }
                                break;
                            }
                        }
                        return zzcVar;
                    case 6:
                        return Byte.valueOf(this.zzhrl);
                    case 7:
                        this.zzhrl = (byte) (obj != null ? 1 : 0);
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zze zzeVar = new zze();
                zzhte = zzeVar;
                zzdqd.zza((Class<zze>) zze.class, zzeVar);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zzf extends zzdqd<zzf, zza> implements zzdrp {
            private static volatile zzdrw<zzf> zzdv;
            private static final zzf zzhtk;
            private int zzbuv;
            private int zzdj;
            private String zzhti = "";
            private zzdot zzhtj = zzdot.zzhfn;

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zzb$zzf$zzb, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public enum EnumC0051zzb implements zzdqh {
                TYPE_UNKNOWN(0),
                TYPE_CREATIVE(1);

                private static final zzdqg<EnumC0051zzb> zzeg = new zzduf();
                private final int value;

                @Override // com.google.android.gms.internal.ads.zzdqh
                public final int zzab() {
                    return this.value;
                }

                public static EnumC0051zzb zzhi(int i) {
                    if (i == 0) {
                        return TYPE_UNKNOWN;
                    }
                    if (i != 1) {
                        return null;
                    }
                    return TYPE_CREATIVE;
                }

                public static zzdqj zzac() {
                    return zzdug.zzep;
                }

                @Override // java.lang.Enum
                public final String toString() {
                    return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
                }

                EnumC0051zzb(int i) {
                    this.value = i;
                }
            }

            private zzf() {
            }

            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class zza extends zzdqd.zza<zzf, zza> implements zzdrp {
                private zza() {
                    super(zzf.zzhtk);
                }

                /* synthetic */ zza(zzduc zzducVar) {
                    this();
                }
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzduc zzducVar = null;
                switch (zzduc.zzdi[i - 1]) {
                    case 1:
                        return new zzf();
                    case 2:
                        return new zza(zzducVar);
                    case 3:
                        return zza(zzhtk, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\n\u0002", new Object[]{"zzdj", "zzbuv", EnumC0051zzb.zzac(), "zzhti", "zzhtj"});
                    case 4:
                        return zzhtk;
                    case 5:
                        zzdrw<zzf> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zzf.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzhtk);
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
                zzf zzfVar = new zzf();
                zzhtk = zzfVar;
                zzdqd.zza((Class<zzf>) zzf.class, zzfVar);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public enum zzg implements zzdqh {
            UNKNOWN(0),
            URL_PHISHING(1),
            URL_MALWARE(2),
            URL_UNWANTED(3),
            CLIENT_SIDE_PHISHING_URL(4),
            CLIENT_SIDE_MALWARE_URL(5),
            DANGEROUS_DOWNLOAD_RECOVERY(6),
            DANGEROUS_DOWNLOAD_WARNING(7),
            OCTAGON_AD(8),
            OCTAGON_AD_SB_MATCH(9);

            private static final zzdqg<zzg> zzeg = new zzdui();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static zzg zzhj(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return URL_PHISHING;
                    case 2:
                        return URL_MALWARE;
                    case 3:
                        return URL_UNWANTED;
                    case 4:
                        return CLIENT_SIDE_PHISHING_URL;
                    case 5:
                        return CLIENT_SIDE_MALWARE_URL;
                    case 6:
                        return DANGEROUS_DOWNLOAD_RECOVERY;
                    case 7:
                        return DANGEROUS_DOWNLOAD_WARNING;
                    case 8:
                        return OCTAGON_AD;
                    case 9:
                        return OCTAGON_AD_SB_MATCH;
                    default:
                        return null;
                }
            }

            public static zzdqj zzac() {
                return zzduh.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzg(int i) {
                this.value = i;
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zzh extends zzdqd<zzh, C0052zzb> implements zzdrp {
            private static volatile zzdrw<zzh> zzdv;
            private static final zzh zzhum;
            private int zzdj;
            private int zzhue;
            private zzd zzhuf;
            private zze zzhug;
            private int zzhuh;
            private int zzhuk;
            private byte zzhrl = 2;
            private String zzhro = "";
            private zzdqi zzhui = zzazq();
            private String zzhuj = "";
            private zzdqk<String> zzhul = zzdqd.zzazr();

            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public enum zza implements zzdqh {
                AD_RESOURCE_UNKNOWN(0),
                AD_RESOURCE_CREATIVE(1),
                AD_RESOURCE_POST_CLICK(2),
                AD_RESOURCE_AUTO_CLICK_DESTINATION(3);

                private static final zzdqg<zza> zzeg = new zzduk();
                private final int value;

                @Override // com.google.android.gms.internal.ads.zzdqh
                public final int zzab() {
                    return this.value;
                }

                public static zza zzhk(int i) {
                    if (i == 0) {
                        return AD_RESOURCE_UNKNOWN;
                    }
                    if (i == 1) {
                        return AD_RESOURCE_CREATIVE;
                    }
                    if (i == 2) {
                        return AD_RESOURCE_POST_CLICK;
                    }
                    if (i != 3) {
                        return null;
                    }
                    return AD_RESOURCE_AUTO_CLICK_DESTINATION;
                }

                public static zzdqj zzac() {
                    return zzduj.zzep;
                }

                @Override // java.lang.Enum
                public final String toString() {
                    return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
                }

                zza(int i) {
                    this.value = i;
                }
            }

            private zzh() {
            }

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzdua$zzb$zzh$zzb, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class C0052zzb extends zzdqd.zza<zzh, C0052zzb> implements zzdrp {
                private C0052zzb() {
                    super(zzh.zzhum);
                }

                /* synthetic */ C0052zzb(zzduc zzducVar) {
                    this();
                }
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzduc zzducVar = null;
                switch (zzduc.zzdi[i - 1]) {
                    case 1:
                        return new zzh();
                    case 2:
                        return new C0052zzb(zzducVar);
                    case 3:
                        return zza(zzhum, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0003\u0001Ԅ\u0000\u0002\b\u0001\u0003Љ\u0002\u0004Љ\u0003\u0005\u0004\u0004\u0006\u0016\u0007\b\u0005\b\f\u0006\t\u001a", new Object[]{"zzdj", "zzhue", "zzhro", "zzhuf", "zzhug", "zzhuh", "zzhui", "zzhuj", "zzhuk", zza.zzac(), "zzhul"});
                    case 4:
                        return zzhum;
                    case 5:
                        zzdrw<zzh> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zzh.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzhum);
                                    zzdv = zzcVar;
                                }
                                break;
                            }
                        }
                        return zzcVar;
                    case 6:
                        return Byte.valueOf(this.zzhrl);
                    case 7:
                        this.zzhrl = (byte) (obj != null ? 1 : 0);
                        return null;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            static {
                zzh zzhVar = new zzh();
                zzhum = zzhVar;
                zzdqd.zza((Class<zzh>) zzh.class, zzhVar);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zzi extends zzdqd<zzi, zza> implements zzdrp {
            private static volatile zzdrw<zzi> zzdv;
            private static final zzi zzhuq;
            private int zzdj;
            private String zzhun = "";
            private long zzhuo;
            private boolean zzhup;

            private zzi() {
            }

            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class zza extends zzdqd.zza<zzi, zza> implements zzdrp {
                private zza() {
                    super(zzi.zzhuq);
                }

                public final zza zzhq(String str) {
                    zzazi();
                    ((zzi) this.zzhkb).zzhp(str);
                    return this;
                }

                public final zza zzfo(long j) {
                    zzazi();
                    ((zzi) this.zzhkb).zzfn(j);
                    return this;
                }

                public final zza zzbm(boolean z) {
                    zzazi();
                    ((zzi) this.zzhkb).zzbl(z);
                    return this;
                }

                /* synthetic */ zza(zzduc zzducVar) {
                    this();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzhp(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 1;
                this.zzhun = str;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzfn(long j) {
                this.zzdj |= 2;
                this.zzhuo = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbl(boolean z) {
                this.zzdj |= 4;
                this.zzhup = z;
            }

            public static zza zzbcs() {
                return zzhuq.zzazo();
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzduc zzducVar = null;
                switch (zzduc.zzdi[i - 1]) {
                    case 1:
                        return new zzi();
                    case 2:
                        return new zza(zzducVar);
                    case 3:
                        return zza(zzhuq, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\u0007\u0002", new Object[]{"zzdj", "zzhun", "zzhuo", "zzhup"});
                    case 4:
                        return zzhuq;
                    case 5:
                        zzdrw<zzi> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zzi.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzhuq);
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
                zzi zziVar = new zzi();
                zzhuq = zziVar;
                zzdqd.zza((Class<zzi>) zzi.class, zziVar);
            }
        }

        private zzb() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzb, zza> implements zzdrp {
            private zza() {
                super(zzb.zzhsn);
            }

            /* synthetic */ zza(zzduc zzducVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzduc zzducVar = null;
            switch (zzduc.zzdi[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzducVar);
                case 3:
                    return zza(zzhsn, "\u0001\u0012\u0000\u0001\u0001\u0015\u0012\u0000\u0004\u0001\u0001\b\u0002\u0002\b\u0003\u0003\b\u0004\u0004Л\u0005\u0007\b\u0006\u001a\u0007\b\t\b\u0007\n\t\u0007\u000b\n\f\u0000\u000b\f\u0001\f\t\u0005\r\b\u0006\u000e\t\u0007\u000f\n\f\u0011\t\r\u0014\u001a\u0015\u001a", new Object[]{"zzdj", "zzhro", "zzhrz", "zzhsa", "zzhsc", zzh.class, "zzhsf", "zzhsg", "zzhsh", "zzhsi", "zzhsj", "zzbuv", zzg.zzac(), "zzhry", zza.zzc.zzac(), "zzhsb", "zzhsd", "zzhse", "zzhrh", "zzhsk", "zzhsl", "zzhsm"});
                case 4:
                    return zzhsn;
                case 5:
                    zzdrw<zzb> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzb.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzhsn);
                                zzdv = zzcVar;
                            }
                            break;
                        }
                    }
                    return zzcVar;
                case 6:
                    return Byte.valueOf(this.zzhrl);
                case 7:
                    this.zzhrl = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzb zzbVar = new zzb();
            zzhsn = zzbVar;
            zzdqd.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }
}
