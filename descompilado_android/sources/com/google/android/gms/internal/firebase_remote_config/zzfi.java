package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfi {

    public static final class zza extends zzhi<zza, C0063zza> implements zzis {
        private static final zza zzmk = new zza();
        private static volatile zziz<zza> zzml;
        private int zzmg;
        private long zzmi;
        private zzhn<zzd> zzmh = zzgt();
        private zzhn<zzfx> zzmj = zzgt();

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.firebase_remote_config.zzfi$zza$zza, reason: collision with other inner class name */
        public static final class C0063zza extends zzhi.zzb<zza, C0063zza> implements zzis {
            private C0063zza() {
                super(zza.zzmk);
            }

            /* synthetic */ C0063zza(zzfh zzfhVar) {
                this();
            }
        }

        public final List<zzd> zzdk() {
            return this.zzmh;
        }

        public final long getTimestamp() {
            return this.zzmi;
        }

        public final List<zzfx> zzdl() {
            return this.zzmj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v13 */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase_remote_config.zzhi$zza, com.google.android.gms.internal.firebase_remote_config.zziz<com.google.android.gms.internal.firebase_remote_config.zzfi$zza>] */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v18 */
        @Override // com.google.android.gms.internal.firebase_remote_config.zzhi
        protected final Object zzb(int i, Object obj, Object obj2) {
            ?? r2;
            zzfh zzfhVar = null;
            switch (zzfh.zzmf[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0063zza(zzfhVar);
                case 3:
                    return zza(zzmk, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u0005\u0000\u0003\u001c", new Object[]{"zzmg", "zzmh", zzd.class, "zzmi", "zzmj"});
                case 4:
                    return zzmk;
                case 5:
                    zziz<zza> zzizVar = zzml;
                    Object obj3 = zzizVar;
                    if (zzizVar == null) {
                        synchronized (zza.class) {
                            zziz<zza> zzizVar2 = zzml;
                            r2 = zzizVar2;
                            if (zzizVar2 == null) {
                                ?? zzaVar = new zzhi.zza(zzmk);
                                zzml = zzaVar;
                                r2 = zzaVar;
                            }
                            break;
                        }
                        obj3 = r2;
                    }
                    return obj3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zza zzdm() {
            return zzmk;
        }

        static {
            zzhi.zza((Class<zza>) zza.class, zzmk);
        }
    }

    public static final class zzb extends zzhi<zzb, zza> implements zzis {
        private static volatile zziz<zzb> zzml;
        private static final zzb zzmo = new zzb();
        private int zzmg;
        private String zzmm = "";
        private zzfx zzmn = zzfx.zzov;

        private zzb() {
        }

        public static final class zza extends zzhi.zzb<zzb, zza> implements zzis {
            private zza() {
                super(zzb.zzmo);
            }

            /* synthetic */ zza(zzfh zzfhVar) {
                this();
            }
        }

        public final String getKey() {
            return this.zzmm;
        }

        public final zzfx zzdo() {
            return this.zzmn;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v13 */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase_remote_config.zzhi$zza, com.google.android.gms.internal.firebase_remote_config.zziz<com.google.android.gms.internal.firebase_remote_config.zzfi$zzb>] */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v18 */
        @Override // com.google.android.gms.internal.firebase_remote_config.zzhi
        protected final Object zzb(int i, Object obj, Object obj2) {
            ?? r2;
            zzfh zzfhVar = null;
            switch (zzfh.zzmf[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzfhVar);
                case 3:
                    return zza(zzmo, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\b\u0000\u0002\n\u0001", new Object[]{"zzmg", "zzmm", "zzmn"});
                case 4:
                    return zzmo;
                case 5:
                    zziz<zzb> zzizVar = zzml;
                    Object obj3 = zzizVar;
                    if (zzizVar == null) {
                        synchronized (zzb.class) {
                            zziz<zzb> zzizVar2 = zzml;
                            r2 = zzizVar2;
                            if (zzizVar2 == null) {
                                ?? zzaVar = new zzhi.zza(zzmo);
                                zzml = zzaVar;
                                r2 = zzaVar;
                            }
                            break;
                        }
                        obj3 = r2;
                    }
                    return obj3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzhi.zza((Class<zzb>) zzb.class, zzmo);
        }
    }

    public static final class zzc extends zzhi<zzc, zza> implements zzis {
        private static volatile zziz<zzc> zzml;
        private static final zzc zzms = new zzc();
        private int zzmg;
        private int zzmp;
        private boolean zzmq;
        private long zzmr;

        private zzc() {
        }

        public static final class zza extends zzhi.zzb<zzc, zza> implements zzis {
            private zza() {
                super(zzc.zzms);
            }

            /* synthetic */ zza(zzfh zzfhVar) {
                this();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v13 */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase_remote_config.zzhi$zza, com.google.android.gms.internal.firebase_remote_config.zziz<com.google.android.gms.internal.firebase_remote_config.zzfi$zzc>] */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v18 */
        @Override // com.google.android.gms.internal.firebase_remote_config.zzhi
        protected final Object zzb(int i, Object obj, Object obj2) {
            ?? r2;
            zzfh zzfhVar = null;
            switch (zzfh.zzmf[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzfhVar);
                case 3:
                    return zza(zzms, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0007\u0001\u0003\u0005\u0002", new Object[]{"zzmg", "zzmp", "zzmq", "zzmr"});
                case 4:
                    return zzms;
                case 5:
                    zziz<zzc> zzizVar = zzml;
                    Object obj3 = zzizVar;
                    if (zzizVar == null) {
                        synchronized (zzc.class) {
                            zziz<zzc> zzizVar2 = zzml;
                            r2 = zzizVar2;
                            if (zzizVar2 == null) {
                                ?? zzaVar = new zzhi.zza(zzms);
                                zzml = zzaVar;
                                r2 = zzaVar;
                            }
                            break;
                        }
                        obj3 = r2;
                    }
                    return obj3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzhi.zza((Class<zzc>) zzc.class, zzms);
        }
    }

    public static final class zzd extends zzhi<zzd, zza> implements zzis {
        private static volatile zziz<zzd> zzml;
        private static final zzd zzmv = new zzd();
        private int zzmg;
        private String zzmt = "";
        private zzhn<zzb> zzmu = zzgt();

        private zzd() {
        }

        public static final class zza extends zzhi.zzb<zzd, zza> implements zzis {
            private zza() {
                super(zzd.zzmv);
            }

            /* synthetic */ zza(zzfh zzfhVar) {
                this();
            }
        }

        public final String getNamespace() {
            return this.zzmt;
        }

        public final List<zzb> zzdr() {
            return this.zzmu;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v13 */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase_remote_config.zzhi$zza, com.google.android.gms.internal.firebase_remote_config.zziz<com.google.android.gms.internal.firebase_remote_config.zzfi$zzd>] */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v18 */
        @Override // com.google.android.gms.internal.firebase_remote_config.zzhi
        protected final Object zzb(int i, Object obj, Object obj2) {
            ?? r2;
            zzfh zzfhVar = null;
            switch (zzfh.zzmf[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(zzfhVar);
                case 3:
                    return zza(zzmv, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b", new Object[]{"zzmg", "zzmt", "zzmu", zzb.class});
                case 4:
                    return zzmv;
                case 5:
                    zziz<zzd> zzizVar = zzml;
                    Object obj3 = zzizVar;
                    if (zzizVar == null) {
                        synchronized (zzd.class) {
                            zziz<zzd> zzizVar2 = zzml;
                            r2 = zzizVar2;
                            if (zzizVar2 == null) {
                                ?? zzaVar = new zzhi.zza(zzmv);
                                zzml = zzaVar;
                                r2 = zzaVar;
                            }
                            break;
                        }
                        obj3 = r2;
                    }
                    return obj3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzhi.zza((Class<zzd>) zzd.class, zzmv);
        }
    }

    public static final class zze extends zzhi<zze, zza> implements zzis {
        private static volatile zziz<zze> zzml;
        private static final zze zznb = new zze();
        private int zzmg;
        private zza zzmw;
        private zza zzmx;
        private zza zzmy;
        private zzc zzmz;
        private zzhn<zzf> zzna = zzgt();

        private zze() {
        }

        public static final class zza extends zzhi.zzb<zze, zza> implements zzis {
            private zza() {
                super(zze.zznb);
            }

            /* synthetic */ zza(zzfh zzfhVar) {
                this();
            }
        }

        public final zza zzdt() {
            zza zzaVar = this.zzmw;
            return zzaVar == null ? zza.zzdm() : zzaVar;
        }

        public final zza zzdu() {
            zza zzaVar = this.zzmx;
            return zzaVar == null ? zza.zzdm() : zzaVar;
        }

        public final zza zzdv() {
            zza zzaVar = this.zzmy;
            return zzaVar == null ? zza.zzdm() : zzaVar;
        }

        public static zze zzb(InputStream inputStream) throws IOException {
            return (zze) zzhi.zza(zznb, inputStream);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v13 */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase_remote_config.zzhi$zza, com.google.android.gms.internal.firebase_remote_config.zziz<com.google.android.gms.internal.firebase_remote_config.zzfi$zze>] */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v18 */
        @Override // com.google.android.gms.internal.firebase_remote_config.zzhi
        protected final Object zzb(int i, Object obj, Object obj2) {
            ?? r2;
            zzfh zzfhVar = null;
            switch (zzfh.zzmf[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(zzfhVar);
                case 3:
                    return zza(zznb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\t\u0000\u0002\t\u0001\u0003\t\u0002\u0004\t\u0003\u0005\u001b", new Object[]{"zzmg", "zzmw", "zzmx", "zzmy", "zzmz", "zzna", zzf.class});
                case 4:
                    return zznb;
                case 5:
                    zziz<zze> zzizVar = zzml;
                    Object obj3 = zzizVar;
                    if (zzizVar == null) {
                        synchronized (zze.class) {
                            zziz<zze> zzizVar2 = zzml;
                            r2 = zzizVar2;
                            if (zzizVar2 == null) {
                                ?? zzaVar = new zzhi.zza(zznb);
                                zzml = zzaVar;
                                r2 = zzaVar;
                            }
                            break;
                        }
                        obj3 = r2;
                    }
                    return obj3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzhi.zza((Class<zze>) zze.class, zznb);
        }
    }

    public static final class zzf extends zzhi<zzf, zza> implements zzis {
        private static volatile zziz<zzf> zzml;
        private static final zzf zzne = new zzf();
        private int zzmg;
        private String zzmt = "";
        private int zznc;
        private long zznd;

        private zzf() {
        }

        public static final class zza extends zzhi.zzb<zzf, zza> implements zzis {
            private zza() {
                super(zzf.zzne);
            }

            /* synthetic */ zza(zzfh zzfhVar) {
                this();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v13 */
        /* JADX WARN: Type inference failed for: r2v14, types: [com.google.android.gms.internal.firebase_remote_config.zzhi$zza, com.google.android.gms.internal.firebase_remote_config.zziz<com.google.android.gms.internal.firebase_remote_config.zzfi$zzf>] */
        /* JADX WARN: Type inference failed for: r2v17 */
        /* JADX WARN: Type inference failed for: r2v18 */
        @Override // com.google.android.gms.internal.firebase_remote_config.zzhi
        protected final Object zzb(int i, Object obj, Object obj2) {
            ?? r2;
            zzfh zzfhVar = null;
            switch (zzfh.zzmf[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(zzfhVar);
                case 3:
                    return zza(zzne, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0005\u0001\u0003\b\u0002", new Object[]{"zzmg", "zznc", "zznd", "zzmt"});
                case 4:
                    return zzne;
                case 5:
                    zziz<zzf> zzizVar = zzml;
                    Object obj3 = zzizVar;
                    if (zzizVar == null) {
                        synchronized (zzf.class) {
                            zziz<zzf> zzizVar2 = zzml;
                            r2 = zzizVar2;
                            if (zzizVar2 == null) {
                                ?? zzaVar = new zzhi.zza(zzne);
                                zzml = zzaVar;
                                r2 = zzaVar;
                            }
                            break;
                        }
                        obj3 = r2;
                    }
                    return obj3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzhi.zza((Class<zzf>) zzf.class, zzne);
        }
    }
}
