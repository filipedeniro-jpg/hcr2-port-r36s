package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzsh {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd<zza, zzc> implements zzdrp {
        private static final zza zzbtv;
        private static volatile zzdrw<zza> zzdv;
        private zzdqk<C0054zza> zzbtu = zzazr();

        /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzsh$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class C0054zza extends zzdqd<C0054zza, C0055zza> implements zzdrp {
            private static final C0054zza zzbtz;
            private static volatile zzdrw<C0054zza> zzdv;
            private int zzbtw;
            private zzd zzbtx;
            private zze zzbty;
            private int zzdj;

            private C0054zza() {
            }

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzsh$zza$zza$zza, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class C0055zza extends zzdqd.zza<C0054zza, C0055zza> implements zzdrp {
                private C0055zza() {
                    super(C0054zza.zzbtz);
                }

                /* synthetic */ C0055zza(zzsj zzsjVar) {
                    this();
                }
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzsj zzsjVar = null;
                switch (zzsj.zzdi[i - 1]) {
                    case 1:
                        return new C0054zza();
                    case 2:
                        return new C0055zza(zzsjVar);
                    case 3:
                        return zza(zzbtz, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzdj", "zzbtw", zzb.zzac(), "zzbtx", "zzbty"});
                    case 4:
                        return zzbtz;
                    case 5:
                        zzdrw<C0054zza> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (C0054zza.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzbtz);
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
                C0054zza c0054zza = new C0054zza();
                zzbtz = c0054zza;
                zzdqd.zza((Class<C0054zza>) C0054zza.class, c0054zza);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public enum zzb implements zzdqh {
            UNSPECIFIED(0),
            IN_MEMORY(1);

            private static final zzdqg<zzb> zzeg = new zzsl();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static zzb zzbs(int i) {
                if (i == 0) {
                    return UNSPECIFIED;
                }
                if (i != 1) {
                    return null;
                }
                return IN_MEMORY;
            }

            public static zzdqj zzac() {
                return zzsk.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzb(int i) {
                this.value = i;
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zzd extends zzdqd<zzd, C0056zza> implements zzdrp {
            private static final zzd zzbuf;
            private static volatile zzdrw<zzd> zzdv;
            private boolean zzbud;
            private int zzbue;
            private int zzdj;

            private zzd() {
            }

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzsh$zza$zzd$zza, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class C0056zza extends zzdqd.zza<zzd, C0056zza> implements zzdrp {
                private C0056zza() {
                    super(zzd.zzbuf);
                }

                /* synthetic */ C0056zza(zzsj zzsjVar) {
                    this();
                }
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzsj zzsjVar = null;
                switch (zzsj.zzdi[i - 1]) {
                    case 1:
                        return new zzd();
                    case 2:
                        return new C0056zza(zzsjVar);
                    case 3:
                        return zza(zzbuf, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0007\u0000\u0002\u000b\u0001", new Object[]{"zzdj", "zzbud", "zzbue"});
                    case 4:
                        return zzbuf;
                    case 5:
                        zzdrw<zzd> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zzd.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzbuf);
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
                zzd zzdVar = new zzd();
                zzbuf = zzdVar;
                zzdqd.zza((Class<zzd>) zzd.class, zzdVar);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zze extends zzdqd<zze, C0057zza> implements zzdrp {
            private static final zze zzbui;
            private static volatile zzdrw<zze> zzdv;
            private int zzbue;
            private boolean zzbug;
            private boolean zzbuh;
            private int zzdj;

            private zze() {
            }

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzsh$zza$zze$zza, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class C0057zza extends zzdqd.zza<zze, C0057zza> implements zzdrp {
                private C0057zza() {
                    super(zze.zzbui);
                }

                /* synthetic */ C0057zza(zzsj zzsjVar) {
                    this();
                }
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzsj zzsjVar = null;
                switch (zzsj.zzdi[i - 1]) {
                    case 1:
                        return new zze();
                    case 2:
                        return new C0057zza(zzsjVar);
                    case 3:
                        return zza(zzbui, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0007\u0000\u0002\u0007\u0001\u0003\u000b\u0002", new Object[]{"zzdj", "zzbug", "zzbuh", "zzbue"});
                    case 4:
                        return zzbui;
                    case 5:
                        zzdrw<zze> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zze.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzbui);
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
                zze zzeVar = new zze();
                zzbui = zzeVar;
                zzdqd.zza((Class<zze>) zze.class, zzeVar);
            }
        }

        private zza() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zzc extends zzdqd.zza<zza, zzc> implements zzdrp {
            private zzc() {
                super(zza.zzbtv);
            }

            /* synthetic */ zzc(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzc(zzsjVar);
                case 3:
                    return zza(zzbtv, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzbtu", C0054zza.class});
                case 4:
                    return zzbtv;
                case 5:
                    zzdrw<zza> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zza.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbtv);
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
            zzbtv = zzaVar;
            zzdqd.zza((Class<zza>) zza.class, zzaVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzb extends zzdqd<zzb, C0058zzb> implements zzdrp {
        private static final zzb zzbux;
        private static volatile zzdrw<zzb> zzdv;
        private int zzbuv;
        private zzm zzbuw;
        private int zzdj;

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public enum zza implements zzdqh {
            AD_FORMAT_TYPE_UNSPECIFIED(0),
            BANNER(1),
            INTERSTITIAL(2),
            NATIVE_EXPRESS(3),
            NATIVE_CONTENT(4),
            NATIVE_APP_INSTALL(5),
            NATIVE_CUSTOM_TEMPLATE(6),
            DFP_BANNER(7),
            DFP_INTERSTITIAL(8),
            REWARD_BASED_VIDEO_AD(9),
            BANNER_SEARCH_ADS(10);

            private static final zzdqg<zza> zzeg = new zzsn();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static zza zzbt(int i) {
                switch (i) {
                    case 0:
                        return AD_FORMAT_TYPE_UNSPECIFIED;
                    case 1:
                        return BANNER;
                    case 2:
                        return INTERSTITIAL;
                    case 3:
                        return NATIVE_EXPRESS;
                    case 4:
                        return NATIVE_CONTENT;
                    case 5:
                        return NATIVE_APP_INSTALL;
                    case 6:
                        return NATIVE_CUSTOM_TEMPLATE;
                    case 7:
                        return DFP_BANNER;
                    case 8:
                        return DFP_INTERSTITIAL;
                    case 9:
                        return REWARD_BASED_VIDEO_AD;
                    case 10:
                        return BANNER_SEARCH_ADS;
                    default:
                        return null;
                }
            }

            public static zzdqj zzac() {
                return zzsm.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zza(int i) {
                this.value = i;
            }
        }

        private zzb() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzsh$zzb$zzb, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class C0058zzb extends zzdqd.zza<zzb, C0058zzb> implements zzdrp {
            private C0058zzb() {
                super(zzb.zzbux);
            }

            /* synthetic */ C0058zzb(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new C0058zzb(zzsjVar);
                case 3:
                    return zza(zzbux, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001", new Object[]{"zzdj", "zzbuv", zza.zzac(), "zzbuw"});
                case 4:
                    return zzbux;
                case 5:
                    zzdrw<zzb> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzb.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbux);
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
            zzbux = zzbVar;
            zzdqd.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzc extends zzdqd<zzc, zza> implements zzdrp {
        private static final zzc zzbvb;
        private static volatile zzdrw<zzc> zzdv;
        private String zzbuy = "";
        private zzdqk<zzb> zzbuz = zzazr();
        private int zzbva;
        private int zzdj;

        private zzc() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzc, zza> implements zzdrp {
            private zza() {
                super(zzc.zzbvb);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbvb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b\u0003\f\u0001", new Object[]{"zzdj", "zzbuy", "zzbuz", zzb.class, "zzbva", zzso.zzac()});
                case 4:
                    return zzbvb;
                case 5:
                    zzdrw<zzc> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzc.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbvb);
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
            zzc zzcVar = new zzc();
            zzbvb = zzcVar;
            zzdqd.zza((Class<zzc>) zzc.class, zzcVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzd extends zzdqd<zzd, zza> implements zzdrp {
        private static final zzd zzbvi;
        private static volatile zzdrw<zzd> zzdv;
        private int zzbvc;
        private zzo zzbvd;
        private zzo zzbve;
        private zzo zzbvf;
        private zzdqk<zzo> zzbvg = zzazr();
        private int zzbvh;
        private int zzdj;

        private zzd() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzd, zza> implements zzdrp {
            private zza() {
                super(zzd.zzbvi);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbvi, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001\u0004\u0000\u0002\t\u0001\u0003\t\u0002\u0004\t\u0003\u0005\u001b\u0006\u0004\u0004", new Object[]{"zzdj", "zzbvc", "zzbvd", "zzbve", "zzbvf", "zzbvg", zzo.class, "zzbvh"});
                case 4:
                    return zzbvi;
                case 5:
                    zzdrw<zzd> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzd.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbvi);
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
            zzd zzdVar = new zzd();
            zzbvi = zzdVar;
            zzdqd.zza((Class<zzd>) zzd.class, zzdVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zze extends zzdqd<zze, zza> implements zzdrp {
        private static final zze zzbvr;
        private static volatile zzdrw<zze> zzdv;
        private int zzbvo;
        private zzo zzbvq;
        private int zzdj;
        private String zzbvn = "";
        private zzdqi zzbvp = zzazq();

        private zze() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zze, zza> implements zzdrp {
            private zza() {
                super(zze.zzbvr);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbvr, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\b\u0000\u0002\f\u0001\u0003\u0016\u0004\t\u0002", new Object[]{"zzdj", "zzbvn", "zzbvo", zzso.zzac(), "zzbvp", "zzbvq"});
                case 4:
                    return zzbvr;
                case 5:
                    zzdrw<zze> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zze.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbvr);
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
            zze zzeVar = new zze();
            zzbvr = zzeVar;
            zzdqd.zza((Class<zze>) zze.class, zzeVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzf extends zzdqd<zzf, zza> implements zzdrp {
        private static final zzf zzbvt;
        private static volatile zzdrw<zzf> zzdv;
        private zzdqi zzbvp = zzazq();
        private int zzbvs;
        private int zzdj;

        private zzf() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzf, zza> implements zzdrp {
            private zza() {
                super(zzf.zzbvt);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbvt, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0000\u0002\u0016", new Object[]{"zzdj", "zzbvs", zzso.zzac(), "zzbvp"});
                case 4:
                    return zzbvt;
                case 5:
                    zzdrw<zzf> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzf.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbvt);
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
            zzbvt = zzfVar;
            zzdqd.zza((Class<zzf>) zzf.class, zzfVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzg extends zzdqd<zzg, zza> implements zzdrp {
        private static final zzg zzbvw;
        private static volatile zzdrw<zzg> zzdv;
        private zzo zzbvq;
        private int zzbvs;
        private zze zzbvu;
        private zzdqk<zzn> zzbvv = zzazr();
        private int zzdj;

        private zzg() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzg, zza> implements zzdrp {
            private zza() {
                super(zzg.zzbvw);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbvw, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\t\u0000\u0002\u001b\u0003\f\u0001\u0004\t\u0002", new Object[]{"zzdj", "zzbvu", "zzbvv", zzn.class, "zzbvs", zzso.zzac(), "zzbvq"});
                case 4:
                    return zzbvw;
                case 5:
                    zzdrw<zzg> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzg.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbvw);
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
            zzg zzgVar = new zzg();
            zzbvw = zzgVar;
            zzdqd.zza((Class<zzg>) zzg.class, zzgVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzh extends zzdqd<zzh, zza> implements zzdrp {
        private static final zzh zzbvy;
        private static volatile zzdrw<zzh> zzdv;
        private int zzbuv;
        private int zzbvx;
        private int zzdj;

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public enum zzb implements zzdqh {
            CELLULAR_NETWORK_TYPE_UNSPECIFIED(0),
            TWO_G(1),
            THREE_G(2),
            LTE(4);

            private static final zzdqg<zzb> zzeg = new zzsr();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static zzb zzbv(int i) {
                if (i == 0) {
                    return CELLULAR_NETWORK_TYPE_UNSPECIFIED;
                }
                if (i == 1) {
                    return TWO_G;
                }
                if (i == 2) {
                    return THREE_G;
                }
                if (i != 4) {
                    return null;
                }
                return LTE;
            }

            public static zzdqj zzac() {
                return zzss.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzb(int i) {
                this.value = i;
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public enum zzc implements zzdqh {
            NETWORKTYPE_UNSPECIFIED(0),
            CELL(1),
            WIFI(2);

            private static final zzdqg<zzc> zzeg = new zzsu();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static zzc zzbw(int i) {
                if (i == 0) {
                    return NETWORKTYPE_UNSPECIFIED;
                }
                if (i == 1) {
                    return CELL;
                }
                if (i != 2) {
                    return null;
                }
                return WIFI;
            }

            public static zzdqj zzac() {
                return zzst.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzc(int i) {
                this.value = i;
            }
        }

        private zzh() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzh, zza> implements zzdrp {
            private zza() {
                super(zzh.zzbvy);
            }

            public final zza zza(zzc zzcVar) {
                zzazi();
                ((zzh) this.zzhkb).zzb(zzcVar);
                return this;
            }

            public final zza zza(zzb zzbVar) {
                zzazi();
                ((zzh) this.zzhkb).zzb(zzbVar);
                return this;
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zzc zzcVar) {
            if (zzcVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 1;
            this.zzbuv = zzcVar.zzab();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zzb zzbVar) {
            if (zzbVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 2;
            this.zzbvx = zzbVar.zzab();
        }

        public static zza zzmy() {
            return zzbvy.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbvy, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001", new Object[]{"zzdj", "zzbuv", zzc.zzac(), "zzbvx", zzb.zzac()});
                case 4:
                    return zzbvy;
                case 5:
                    zzdrw<zzh> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzh.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbvy);
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
            zzh zzhVar = new zzh();
            zzbvy = zzhVar;
            zzdqd.zza((Class<zzh>) zzh.class, zzhVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzi extends zzdqd<zzi, zza> implements zzdrp {
        private static final zzi zzbwk;
        private static volatile zzdrw<zzi> zzdv;
        private int zzbwi;
        private zzo zzbwj;
        private int zzdj;

        private zzi() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzi, zza> implements zzdrp {
            private zza() {
                super(zzi.zzbwk);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbwk, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001", new Object[]{"zzdj", "zzbwi", zzso.zzac(), "zzbwj"});
                case 4:
                    return zzbwk;
                case 5:
                    zzdrw<zzi> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzi.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbwk);
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
            zzbwk = zziVar;
            zzdqd.zza((Class<zzi>) zzi.class, zziVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzj extends zzdqd<zzj, zzc> implements zzdrp {
        private static final zzj zzbxe;
        private static volatile zzdrw<zzj> zzdv;
        private int zzbwz;
        private int zzbxa;
        private long zzbxb;
        private long zzbxd;
        private int zzdj;
        private zzdqk<zza> zzbtu = zzazr();
        private String zzdk = "";
        private String zzbxc = "";

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd<zza, C0059zza> implements zzdrp {
            private static final zzdql<Integer, zzb.zza> zzbwq = new zzsv();
            private static final zza zzbwy;
            private static volatile zzdrw<zza> zzdv;
            private long zzbwl;
            private int zzbwm;
            private long zzbwn;
            private long zzbwo;
            private zzdqi zzbwp = zzazq();
            private zzh zzbwr;
            private int zzbws;
            private int zzbwt;
            private int zzbwu;
            private int zzbwv;
            private int zzbww;
            private int zzbwx;
            private int zzdj;

            private zza() {
            }

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzsh$zzj$zza$zza, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
            public static final class C0059zza extends zzdqd.zza<zza, C0059zza> implements zzdrp {
                private C0059zza() {
                    super(zza.zzbwy);
                }

                public final C0059zza zzem(long j) {
                    zzazi();
                    ((zza) this.zzhkb).setTimestamp(j);
                    return this;
                }

                public final C0059zza zzf(zzso zzsoVar) {
                    zzazi();
                    ((zza) this.zzhkb).zza(zzsoVar);
                    return this;
                }

                public final C0059zza zzen(long j) {
                    zzazi();
                    ((zza) this.zzhkb).zzei(j);
                    return this;
                }

                public final C0059zza zzeo(long j) {
                    zzazi();
                    ((zza) this.zzhkb).zzej(j);
                    return this;
                }

                public final C0059zza zzc(Iterable<? extends zzb.zza> iterable) {
                    zzazi();
                    ((zza) this.zzhkb).zza(iterable);
                    return this;
                }

                public final C0059zza zzb(zzh zzhVar) {
                    zzazi();
                    ((zza) this.zzhkb).zza(zzhVar);
                    return this;
                }

                public final C0059zza zzg(zzso zzsoVar) {
                    zzazi();
                    ((zza) this.zzhkb).zzb(zzsoVar);
                    return this;
                }

                public final C0059zza zzh(zzso zzsoVar) {
                    zzazi();
                    ((zza) this.zzhkb).zzc(zzsoVar);
                    return this;
                }

                public final C0059zza zzi(zzso zzsoVar) {
                    zzazi();
                    ((zza) this.zzhkb).zzd(zzsoVar);
                    return this;
                }

                public final C0059zza zzca(int i) {
                    zzazi();
                    ((zza) this.zzhkb).zzbx(i);
                    return this;
                }

                public final C0059zza zzj(zzso zzsoVar) {
                    zzazi();
                    ((zza) this.zzhkb).zze(zzsoVar);
                    return this;
                }

                public final C0059zza zzb(zzb zzbVar) {
                    zzazi();
                    ((zza) this.zzhkb).zza(zzbVar);
                    return this;
                }

                /* synthetic */ C0059zza(zzsj zzsjVar) {
                    this();
                }
            }

            public final long getTimestamp() {
                return this.zzbwl;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void setTimestamp(long j) {
                this.zzdj |= 1;
                this.zzbwl = j;
            }

            public final zzso zznb() {
                zzso zzsoVarZzbu = zzso.zzbu(this.zzbwm);
                return zzsoVarZzbu == null ? zzso.ENUM_FALSE : zzsoVarZzbu;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zza(zzso zzsoVar) {
                if (zzsoVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 2;
                this.zzbwm = zzsoVar.zzab();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzei(long j) {
                this.zzdj |= 4;
                this.zzbwn = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzej(long j) {
                this.zzdj |= 8;
                this.zzbwo = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zza(Iterable<? extends zzb.zza> iterable) {
                if (!this.zzbwp.zzaxd()) {
                    this.zzbwp = zzdqd.zza(this.zzbwp);
                }
                Iterator<? extends zzb.zza> it = iterable.iterator();
                while (it.hasNext()) {
                    this.zzbwp.zzgq(it.next().zzab());
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zza(zzh zzhVar) {
                if (zzhVar == null) {
                    throw new NullPointerException();
                }
                this.zzbwr = zzhVar;
                this.zzdj |= 16;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzb(zzso zzsoVar) {
                if (zzsoVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 32;
                this.zzbws = zzsoVar.zzab();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzc(zzso zzsoVar) {
                if (zzsoVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 64;
                this.zzbwt = zzsoVar.zzab();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzd(zzso zzsoVar) {
                if (zzsoVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 128;
                this.zzbwu = zzsoVar.zzab();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbx(int i) {
                this.zzdj |= 256;
                this.zzbwv = i;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zze(zzso zzsoVar) {
                if (zzsoVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 512;
                this.zzbww = zzsoVar.zzab();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zza(zzb zzbVar) {
                if (zzbVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 1024;
                this.zzbwx = zzbVar.zzab();
            }

            public static zza zzg(byte[] bArr) throws zzdqn {
                return (zza) zzdqd.zza(zzbwy, bArr);
            }

            public static C0059zza zznc() {
                return zzbwy.zzazo();
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzsj zzsjVar = null;
                switch (zzsj.zzdi[i - 1]) {
                    case 1:
                        return new zza();
                    case 2:
                        return new C0059zza(zzsjVar);
                    case 3:
                        return zza(zzbwy, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0001\u0000\u0001\u0002\u0000\u0002\f\u0001\u0003\u0002\u0002\u0004\u0002\u0003\u0005\u001e\u0006\t\u0004\u0007\f\u0005\b\f\u0006\t\f\u0007\n\u0004\b\u000b\f\t\f\f\n", new Object[]{"zzdj", "zzbwl", "zzbwm", zzso.zzac(), "zzbwn", "zzbwo", "zzbwp", zzb.zza.zzac(), "zzbwr", "zzbws", zzso.zzac(), "zzbwt", zzso.zzac(), "zzbwu", zzso.zzac(), "zzbwv", "zzbww", zzso.zzac(), "zzbwx", zzb.zzac()});
                    case 4:
                        return zzbwy;
                    case 5:
                        zzdrw<zza> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zza.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzbwy);
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
                zzbwy = zzaVar;
                zzdqd.zza((Class<zza>) zza.class, zzaVar);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public enum zzb implements zzdqh {
            UNSPECIFIED(0),
            CONNECTING(1),
            CONNECTED(2),
            DISCONNECTING(3),
            DISCONNECTED(4),
            SUSPENDED(5);

            private static final zzdqg<zzb> zzeg = new zzsx();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static zzb zzcb(int i) {
                if (i == 0) {
                    return UNSPECIFIED;
                }
                if (i == 1) {
                    return CONNECTING;
                }
                if (i == 2) {
                    return CONNECTED;
                }
                if (i == 3) {
                    return DISCONNECTING;
                }
                if (i == 4) {
                    return DISCONNECTED;
                }
                if (i != 5) {
                    return null;
                }
                return SUSPENDED;
            }

            public static zzdqj zzac() {
                return zzsw.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzb(int i) {
                this.value = i;
            }
        }

        private zzj() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zzc extends zzdqd.zza<zzj, zzc> implements zzdrp {
            private zzc() {
                super(zzj.zzbxe);
            }

            public final zzc zzd(Iterable<? extends zza> iterable) {
                zzazi();
                ((zzj) this.zzhkb).zzb(iterable);
                return this;
            }

            public final zzc zzcc(int i) {
                zzazi();
                ((zzj) this.zzhkb).zzby(i);
                return this;
            }

            public final zzc zzcd(int i) {
                zzazi();
                ((zzj) this.zzhkb).zzbz(i);
                return this;
            }

            public final zzc zzep(long j) {
                zzazi();
                ((zzj) this.zzhkb).zzek(j);
                return this;
            }

            public final zzc zzbv(String str) {
                zzazi();
                ((zzj) this.zzhkb).zzm(str);
                return this;
            }

            public final zzc zzbw(String str) {
                zzazi();
                ((zzj) this.zzhkb).zzbu(str);
                return this;
            }

            public final zzc zzeq(long j) {
                zzazi();
                ((zzj) this.zzhkb).zzel(j);
                return this;
            }

            /* synthetic */ zzc(zzsj zzsjVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(Iterable<? extends zza> iterable) {
            if (!this.zzbtu.zzaxd()) {
                this.zzbtu = zzdqd.zza(this.zzbtu);
            }
            zzdom.zza(iterable, this.zzbtu);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzby(int i) {
            this.zzdj |= 1;
            this.zzbwz = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbz(int i) {
            this.zzdj |= 2;
            this.zzbxa = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzek(long j) {
            this.zzdj |= 4;
            this.zzbxb = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 8;
            this.zzdk = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzbu(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 16;
            this.zzbxc = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzel(long j) {
            this.zzdj |= 32;
            this.zzbxd = j;
        }

        public static zzc zzne() {
            return zzbxe.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zzc(zzsjVar);
                case 3:
                    return zza(zzbxe, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001\u001b\u0002\u0004\u0000\u0003\u0004\u0001\u0004\u0002\u0002\u0005\b\u0003\u0006\b\u0004\u0007\u0002\u0005", new Object[]{"zzdj", "zzbtu", zza.class, "zzbwz", "zzbxa", "zzbxb", "zzdk", "zzbxc", "zzbxd"});
                case 4:
                    return zzbxe;
                case 5:
                    zzdrw<zzj> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzj.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbxe);
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
            zzj zzjVar = new zzj();
            zzbxe = zzjVar;
            zzdqd.zza((Class<zzj>) zzj.class, zzjVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzk extends zzdqd<zzk, zza> implements zzdrp {
        private static final zzk zzbxx;
        private static volatile zzdrw<zzk> zzdv;
        private int zzbxm = 1000;
        private int zzbxn = 1000;
        private int zzbxo;
        private int zzbxp;
        private int zzbxq;
        private int zzbxr;
        private int zzbxs;
        private int zzbxt;
        private int zzbxu;
        private int zzbxv;
        private zzl zzbxw;
        private int zzdj;

        private zzk() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzk, zza> implements zzdrp {
            private zza() {
                super(zzk.zzbxx);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbxx, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001\f\u0000\u0002\f\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u0004\u0004\u0006\u0004\u0005\u0007\u0004\u0006\b\u0004\u0007\t\u0004\b\n\u0004\t\u000b\t\n", new Object[]{"zzdj", "zzbxm", zzso.zzac(), "zzbxn", zzso.zzac(), "zzbxo", "zzbxp", "zzbxq", "zzbxr", "zzbxs", "zzbxt", "zzbxu", "zzbxv", "zzbxw"});
                case 4:
                    return zzbxx;
                case 5:
                    zzdrw<zzk> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzk.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbxx);
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
            zzk zzkVar = new zzk();
            zzbxx = zzkVar;
            zzdqd.zza((Class<zzk>) zzk.class, zzkVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzl extends zzdqd<zzl, zza> implements zzdrp {
        private static final zzl zzbya;
        private static volatile zzdrw<zzl> zzdv;
        private int zzbxy;
        private int zzbxz;
        private int zzdj;

        private zzl() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzl, zza> implements zzdrp {
            private zza() {
                super(zzl.zzbya);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbya, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", new Object[]{"zzdj", "zzbxy", "zzbxz"});
                case 4:
                    return zzbya;
                case 5:
                    zzdrw<zzl> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzl.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbya);
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
            zzl zzlVar = new zzl();
            zzbya = zzlVar;
            zzdqd.zza((Class<zzl>) zzl.class, zzlVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzm extends zzdqd<zzm, zza> implements zzdrp {
        private static final zzm zzbyd;
        private static volatile zzdrw<zzm> zzdv;
        private int zzbyb;
        private int zzbyc;
        private int zzdj;

        private zzm() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzm, zza> implements zzdrp {
            private zza() {
                super(zzm.zzbyd);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbyd, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", new Object[]{"zzdj", "zzbyb", "zzbyc"});
                case 4:
                    return zzbyd;
                case 5:
                    zzdrw<zzm> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzm.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbyd);
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
            zzm zzmVar = new zzm();
            zzbyd = zzmVar;
            zzdqd.zza((Class<zzm>) zzm.class, zzmVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzn extends zzdqd<zzn, zza> implements zzdrp {
        private static final zzn zzbye;
        private static volatile zzdrw<zzn> zzdv;
        private String zzbvn = "";
        private int zzbvo;
        private zzo zzbvq;
        private int zzdj;

        private zzn() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzn, zza> implements zzdrp {
            private zza() {
                super(zzn.zzbye);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbye, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\f\u0001\u0003\t\u0002", new Object[]{"zzdj", "zzbvn", "zzbvo", zzso.zzac(), "zzbvq"});
                case 4:
                    return zzbye;
                case 5:
                    zzdrw<zzn> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzn.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbye);
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
            zzn zznVar = new zzn();
            zzbye = zznVar;
            zzdqd.zza((Class<zzn>) zzn.class, zznVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzo extends zzdqd<zzo, zza> implements zzdrp {
        private static final zzo zzbyh;
        private static volatile zzdrw<zzo> zzdv;
        private int zzbyf;
        private int zzbyg;
        private int zzdj;

        private zzo() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzo, zza> implements zzdrp {
            private zza() {
                super(zzo.zzbyh);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbyh, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001", new Object[]{"zzdj", "zzbyf", "zzbyg"});
                case 4:
                    return zzbyh;
                case 5:
                    zzdrw<zzo> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzo.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbyh);
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
            zzo zzoVar = new zzo();
            zzbyh = zzoVar;
            zzdqd.zza((Class<zzo>) zzo.class, zzoVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzp extends zzdqd<zzp, zza> implements zzdrp {
        private static final zzp zzbyk;
        private static volatile zzdrw<zzp> zzdv;
        private int zzbvs = 1000;
        private zzq zzbyi;
        private zzo zzbyj;
        private int zzdj;

        private zzp() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzp, zza> implements zzdrp {
            private zza() {
                super(zzp.zzbyk);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzp();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbyk, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzdj", "zzbvs", zzso.zzac(), "zzbyi", "zzbyj"});
                case 4:
                    return zzbyk;
                case 5:
                    zzdrw<zzp> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzp.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbyk);
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
            zzp zzpVar = new zzp();
            zzbyk = zzpVar;
            zzdqd.zza((Class<zzp>) zzp.class, zzpVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzq extends zzdqd<zzq, zza> implements zzdrp {
        private static final zzq zzbym;
        private static volatile zzdrw<zzq> zzdv;
        private int zzbyl;
        private int zzdj;

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public enum zzb implements zzdqh {
            VIDEO_ERROR_CODE_UNSPECIFIED(0),
            OPENGL_RENDERING_FAILED(1),
            CACHE_LOAD_FAILED(2),
            ANDROID_TARGET_API_TOO_LOW(3);

            private static final zzdqg<zzb> zzeg = new zzsy();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static zzb zzce(int i) {
                if (i == 0) {
                    return VIDEO_ERROR_CODE_UNSPECIFIED;
                }
                if (i == 1) {
                    return OPENGL_RENDERING_FAILED;
                }
                if (i == 2) {
                    return CACHE_LOAD_FAILED;
                }
                if (i != 3) {
                    return null;
                }
                return ANDROID_TARGET_API_TOO_LOW;
            }

            public static zzdqj zzac() {
                return zzsz.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzb(int i) {
                this.value = i;
            }
        }

        private zzq() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzq, zza> implements zzdrp {
            private zza() {
                super(zzq.zzbym);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzq();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbym, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f\u0000", new Object[]{"zzdj", "zzbyl", zzb.zzac()});
                case 4:
                    return zzbym;
                case 5:
                    zzdrw<zzq> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzq.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbym);
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
            zzq zzqVar = new zzq();
            zzbym = zzqVar;
            zzdqd.zza((Class<zzq>) zzq.class, zzqVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzr extends zzdqd<zzr, zza> implements zzdrp {
        private static final zzr zzbyv;
        private static volatile zzdrw<zzr> zzdv;
        private int zzbvs = 1000;
        private zzq zzbyi;
        private int zzbys;
        private int zzbyt;
        private int zzbyu;
        private int zzdj;

        private zzr() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzr, zza> implements zzdrp {
            private zza() {
                super(zzr.zzbyv);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzr();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbyv, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u0004\u0004", new Object[]{"zzdj", "zzbvs", zzso.zzac(), "zzbyi", "zzbys", "zzbyt", "zzbyu"});
                case 4:
                    return zzbyv;
                case 5:
                    zzdrw<zzr> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzr.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbyv);
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
            zzr zzrVar = new zzr();
            zzbyv = zzrVar;
            zzdqd.zza((Class<zzr>) zzr.class, zzrVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzs extends zzdqd<zzs, zza> implements zzdrp {
        private static final zzs zzbyw;
        private static volatile zzdrw<zzs> zzdv;
        private int zzbvs = 1000;
        private zzq zzbyi;
        private zzo zzbyj;
        private int zzdj;

        private zzs() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzs, zza> implements zzdrp {
            private zza() {
                super(zzs.zzbyw);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzs();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbyw, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzdj", "zzbvs", zzso.zzac(), "zzbyi", "zzbyj"});
                case 4:
                    return zzbyw;
                case 5:
                    zzdrw<zzs> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzs.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbyw);
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
            zzs zzsVar = new zzs();
            zzbyw = zzsVar;
            zzdqd.zza((Class<zzs>) zzs.class, zzsVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzt extends zzdqd<zzt, zza> implements zzdrp {
        private static final zzt zzbyy;
        private static volatile zzdrw<zzt> zzdv;
        private int zzbvs = 1000;
        private zzq zzbyi;
        private int zzbys;
        private int zzbyt;
        private int zzbyu;
        private long zzbyx;
        private int zzdj;

        private zzt() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzt, zza> implements zzdrp {
            private zza() {
                super(zzt.zzbyy);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzt();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbyy, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\u0004\u0002\u0004\u0004\u0003\u0005\u0004\u0004\u0006\u0003\u0005", new Object[]{"zzdj", "zzbvs", zzso.zzac(), "zzbyi", "zzbys", "zzbyt", "zzbyu", "zzbyx"});
                case 4:
                    return zzbyy;
                case 5:
                    zzdrw<zzt> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzt.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbyy);
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
            zzt zztVar = new zzt();
            zzbyy = zztVar;
            zzdqd.zza((Class<zzt>) zzt.class, zztVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzu extends zzdqd<zzu, zza> implements zzdrp {
        private static final zzu zzbyz;
        private static volatile zzdrw<zzu> zzdv;
        private int zzbvs = 1000;
        private zzq zzbyi;
        private zzo zzbyj;
        private int zzdj;

        private zzu() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzu, zza> implements zzdrp {
            private zza() {
                super(zzu.zzbyz);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzu();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbyz, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\t\u0002", new Object[]{"zzdj", "zzbvs", zzso.zzac(), "zzbyi", "zzbyj"});
                case 4:
                    return zzbyz;
                case 5:
                    zzdrw<zzu> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzu.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbyz);
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
            zzu zzuVar = new zzu();
            zzbyz = zzuVar;
            zzdqd.zza((Class<zzu>) zzu.class, zzuVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzv extends zzdqd<zzv, zza> implements zzdrp {
        private static final zzv zzbza;
        private static volatile zzdrw<zzv> zzdv;
        private int zzbvs = 1000;
        private zzq zzbyi;
        private int zzdj;

        private zzv() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzv, zza> implements zzdrp {
            private zza() {
                super(zzv.zzbza);
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzv();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001", new Object[]{"zzdj", "zzbvs", zzso.zzac(), "zzbyi"});
                case 4:
                    return zzbza;
                case 5:
                    zzdrw<zzv> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzv.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbza);
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
            zzv zzvVar = new zzv();
            zzbza = zzvVar;
            zzdqd.zza((Class<zzv>) zzv.class, zzvVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zzw extends zzdqd<zzw, zza> implements zzdrp {
        private static final zzw zzbzd;
        private static volatile zzdrw<zzw> zzdv;
        private boolean zzbzb;
        private int zzbzc;
        private int zzdj;

        private zzw() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzw, zza> implements zzdrp {
            private zza() {
                super(zzw.zzbzd);
            }

            public final boolean zzns() {
                return ((zzw) this.zzhkb).zzns();
            }

            public final zza zzq(boolean z) {
                zzazi();
                ((zzw) this.zzhkb).zzp(z);
                return this;
            }

            public final zza zzcg(int i) {
                zzazi();
                ((zzw) this.zzhkb).zzcf(i);
                return this;
            }

            /* synthetic */ zza(zzsj zzsjVar) {
                this();
            }
        }

        public final boolean zzns() {
            return this.zzbzb;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp(boolean z) {
            this.zzdj |= 1;
            this.zzbzb = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzcf(int i) {
            this.zzdj |= 2;
            this.zzbzc = i;
        }

        public static zza zznt() {
            return zzbzd.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzsj zzsjVar = null;
            switch (zzsj.zzdi[i - 1]) {
                case 1:
                    return new zzw();
                case 2:
                    return new zza(zzsjVar);
                case 3:
                    return zza(zzbzd, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0007\u0000\u0002\u0004\u0001", new Object[]{"zzdj", "zzbzb", "zzbzc"});
                case 4:
                    return zzbzd;
                case 5:
                    zzdrw<zzw> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzw.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzbzd);
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
            zzw zzwVar = new zzw();
            zzbzd = zzwVar;
            zzdqd.zza((Class<zzw>) zzw.class, zzwVar);
        }
    }
}
