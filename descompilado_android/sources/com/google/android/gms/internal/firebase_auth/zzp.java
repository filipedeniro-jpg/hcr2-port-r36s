package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzp {

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zza extends zzhx<zza, C0061zza> implements zzji {
        private static final zza zzs;
        private static volatile zzjq<zza> zzt;
        private int zzc;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";
        private String zzm = "";
        private String zzn = "";
        private String zzo = "";
        private String zzp = "";
        private zzih<com.google.android.gms.internal.firebase_auth.zzl> zzq = zzad();
        private String zzr = "";

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.firebase_auth.zzp$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class C0061zza extends zzhx.zzb<zza, C0061zza> implements zzji {
            private C0061zza() {
                super(zza.zzs);
            }

            public final C0061zza zza(String str) {
                zzb();
                ((zza) this.zza).zza(str);
                return this;
            }

            public final C0061zza zzb(String str) {
                zzb();
                ((zza) this.zza).zzb(str);
                return this;
            }

            public final C0061zza zzc(String str) {
                zzb();
                ((zza) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ C0061zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 8192;
            this.zzr = str;
        }

        public static C0061zza zza() {
            return zzs.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0061zza(zzoVar);
                case 3:
                    return zza(zzs, "\u0001\u000f\u0000\u0001\u0001\u000f\u000f\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\b\u0007\t\b\b\n\b\t\u000b\b\n\f\b\u000b\r\b\f\u000e\u001b\u000f\b\r", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", com.google.android.gms.internal.firebase_auth.zzl.class, "zzr"});
                case 4:
                    return zzs;
                case 5:
                    zzjq<zza> zzaVar = zzt;
                    if (zzaVar == null) {
                        synchronized (zza.class) {
                            zzaVar = zzt;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzs);
                                zzt = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzs = zzaVar;
            zzhx.zza((Class<zza>) zza.class, zzaVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzb extends zzhx<zzb, zza> implements zzji {
        private static final zzb zzn;
        private static volatile zzjq<zzb> zzo;
        private int zzc;
        private boolean zzg;
        private boolean zzi;
        private boolean zzj;
        private byte zzm = 2;
        private String zzd = "";
        private String zze = "";
        private zzih<String> zzf = zzhx.zzad();
        private String zzh = "";
        private String zzk = "";
        private zzih<String> zzl = zzhx.zzad();

        private zzb() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzb, zza> implements zzji {
            private zza() {
                super(zzb.zzn);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final List<String> zzb() {
            return this.zzf;
        }

        public final int zzc() {
            return this.zzf.size();
        }

        public final boolean zzd() {
            return this.zzg;
        }

        public final String zze() {
            return this.zzh;
        }

        public final boolean zzf() {
            return this.zzi;
        }

        public final List<String> zzg() {
            return this.zzl;
        }

        public final int zzh() {
            return this.zzl.size();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzn, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\u001a\u0004\u0007\u0002\u0005\b\u0003\u0006\u0007\u0004\u0007\u0007\u0005\b\b\u0006\t\u001a", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
                case 4:
                    return zzn;
                case 5:
                    zzjq<zzb> zzaVar = zzo;
                    if (zzaVar == null) {
                        synchronized (zzb.class) {
                            zzaVar = zzo;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzn);
                                zzo = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return Byte.valueOf(this.zzm);
                case 7:
                    this.zzm = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzb> zzi() {
            return (zzjq) zzn.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzb zzbVar = new zzb();
            zzn = zzbVar;
            zzhx.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzc extends zzhx<zzc, zza> implements zzji {
        private static final zzc zzg;
        private static volatile zzjq<zzc> zzh;
        private int zzc;
        private long zze;
        private String zzd = "";
        private String zzf = "";

        private zzc() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzc, zza> implements zzji {
            private zza() {
                super(zzc.zzg);
            }

            public final zza zza(String str) {
                zzb();
                ((zzc) this.zza).zza(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 4;
            this.zzf = str;
        }

        public static zza zza() {
            return zzg.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzg, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\b\u0002", new Object[]{"zzc", "zzd", "zze", "zzf"});
                case 4:
                    return zzg;
                case 5:
                    zzjq<zzc> zzaVar = zzh;
                    if (zzaVar == null) {
                        synchronized (zzc.class) {
                            zzaVar = zzh;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzg);
                                zzh = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzg = zzcVar;
            zzhx.zza((Class<zzc>) zzc.class, zzcVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzd extends zzhx<zzd, zza> implements zzji {
        private static final zzd zzh;
        private static volatile zzjq<zzd> zzi;
        private int zzc;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";

        private zzd() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzd, zza> implements zzji {
            private zza() {
                super(zzd.zzh);
            }

            public final zza zza(String str) {
                zzb();
                ((zzd) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzd) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                zzb();
                ((zzd) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                zzb();
                ((zzd) this.zza).zzd(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 4;
            this.zzf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 8;
            this.zzg = str;
        }

        public static zza zza() {
            return zzh.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0006\u0004\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0006\b\u0003", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzjq<zzd> zzaVar = zzi;
                    if (zzaVar == null) {
                        synchronized (zzd.class) {
                            zzaVar = zzi;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzh);
                                zzi = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzh = zzdVar;
            zzhx.zza((Class<zzd>) zzd.class, zzdVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zze extends zzhx<zze, zza> implements zzji {
        private static final zze zzl;
        private static volatile zzjq<zze> zzm;
        private int zzc;
        private long zzh;
        private boolean zzj;
        private byte zzk = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzi = "";

        private zze() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zze, zza> implements zzji {
            private zza() {
                super(zze.zzl);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final String zzc() {
            return this.zzg;
        }

        public final long zzd() {
            return this.zzh;
        }

        public final String zze() {
            return this.zzi;
        }

        public final boolean zzf() {
            return this.zzj;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzl, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\u0002\u0004\u0006\b\u0005\u0007\u0007\u0006", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
                case 4:
                    return zzl;
                case 5:
                    zzjq<zze> zzaVar = zzm;
                    if (zzaVar == null) {
                        synchronized (zze.class) {
                            zzaVar = zzm;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzl);
                                zzm = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return Byte.valueOf(this.zzk);
                case 7:
                    this.zzk = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zze> zzg() {
            return (zzjq) zzl.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zze zzeVar = new zze();
            zzl = zzeVar;
            zzhx.zza((Class<zze>) zze.class, zzeVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzf extends zzhx<zzf, zza> implements zzji {
        private static final zzf zzh;
        private static volatile zzjq<zzf> zzi;
        private int zzc;
        private String zzd = "";
        private zzih<String> zze = zzhx.zzad();
        private zzih<String> zzf = zzhx.zzad();
        private long zzg;

        private zzf() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzf, zza> implements zzji {
            private zza() {
                super(zzf.zzh);
            }

            public final zza zza(String str) {
                zzb();
                ((zzf) this.zza).zza(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        public static zza zza() {
            return zzh.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzh, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001\b\u0000\u0002\u001a\u0003\u001a\u0004\u0002\u0001", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzjq<zzf> zzaVar = zzi;
                    if (zzaVar == null) {
                        synchronized (zzf.class) {
                            zzaVar = zzi;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzh);
                                zzi = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzh = zzfVar;
            zzhx.zza((Class<zzf>) zzf.class, zzfVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzg extends zzhx<zzg, zza> implements zzji {
        private static final zzg zzg;
        private static volatile zzjq<zzg> zzh;
        private int zzc;
        private byte zzf = 2;
        private String zzd = "";
        private zzih<zzz> zze = zzad();

        private zzg() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzg, zza> implements zzji {
            private zza() {
                super(zzg.zzg);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final int zza() {
            return this.zze.size();
        }

        public final zzz zza(int i) {
            return this.zze.get(i);
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzg, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0001\u0001Ԉ\u0000\u0002\u001b", new Object[]{"zzc", "zzd", "zze", zzz.class});
                case 4:
                    return zzg;
                case 5:
                    zzjq<zzg> zzaVar = zzh;
                    if (zzaVar == null) {
                        synchronized (zzg.class) {
                            zzaVar = zzh;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzg);
                                zzh = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return Byte.valueOf(this.zzf);
                case 7:
                    this.zzf = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzg> zzb() {
            return (zzjq) zzg.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzg zzgVar = new zzg();
            zzg = zzgVar;
            zzhx.zza((Class<zzg>) zzg.class, zzgVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzh extends zzhx<zzh, zza> implements zzji {
        private static final zzh zzu;
        private static volatile zzjq<zzh> zzv;
        private int zzc;
        private int zzd;
        private boolean zzo;
        private boolean zzq;
        private boolean zzt;
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";
        private String zzm = "";
        private String zzn = "";
        private String zzp = "";
        private String zzr = "";
        private String zzs = "";

        private zzh() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzh, zza> implements zzji {
            private zza() {
                super(zzh.zzu);
            }

            public final zza zza(zzgd zzgdVar) {
                zzb();
                ((zzh) this.zza).zza(zzgdVar);
                return this;
            }

            public final zza zza(String str) {
                zzb();
                ((zzh) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzh) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                zzb();
                ((zzh) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                zzb();
                ((zzh) this.zza).zzd(str);
                return this;
            }

            public final zza zze(String str) {
                zzb();
                ((zzh) this.zza).zze(str);
                return this;
            }

            public final zza zzf(String str) {
                zzb();
                ((zzh) this.zza).zzf(str);
                return this;
            }

            public final zza zzg(String str) {
                zzb();
                ((zzh) this.zza).zzg(str);
                return this;
            }

            public final zza zza(boolean z) {
                zzb();
                ((zzh) this.zza).zza(z);
                return this;
            }

            public final zza zzh(String str) {
                zzb();
                ((zzh) this.zza).zzh(str);
                return this;
            }

            public final zza zzb(boolean z) {
                zzb();
                ((zzh) this.zza).zzb(z);
                return this;
            }

            public final zza zzi(String str) {
                zzb();
                ((zzh) this.zza).zzi(str);
                return this;
            }

            public final zza zzj(String str) {
                zzb();
                ((zzh) this.zza).zzj(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzgd zzgdVar) {
            if (zzgdVar == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = zzgdVar.zza();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 32;
            this.zzi = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 64;
            this.zzj = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 128;
            this.zzk = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 256;
            this.zzl = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 512;
            this.zzm = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1024;
            this.zzn = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 2048;
            this.zzo = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 4096;
            this.zzp = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(boolean z) {
            this.zzc |= 8192;
            this.zzq = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 16384;
            this.zzr = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 32768;
            this.zzs = str;
        }

        public static zza zza() {
            return zzu.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzh();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzu, "\u0001\u0011\u0000\u0001\u0001\u0013\u0011\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\b\u0007\t\b\b\n\b\t\u000b\b\n\f\u0007\u000b\r\b\f\u000e\u0007\r\u000f\b\u000e\u0012\b\u000f\u0013\u0007\u0010", new Object[]{"zzc", "zzd", zzgd.zzb(), "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt"});
                case 4:
                    return zzu;
                case 5:
                    zzjq<zzh> zzaVar = zzv;
                    if (zzaVar == null) {
                        synchronized (zzh.class) {
                            zzaVar = zzv;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzu);
                                zzv = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzu = zzhVar;
            zzhx.zza((Class<zzh>) zzh.class, zzhVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzi extends zzhx<zzi, zza> implements zzji {
        private static final zzi zzi;
        private static volatile zzjq<zzi> zzj;
        private int zzc;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";

        private zzi() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzi, zza> implements zzji {
            private zza() {
                super(zzi.zzi);
            }

            public final zza zza(String str) {
                zzb();
                ((zzi) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzi) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                zzb();
                ((zzi) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 16;
            this.zzh = str;
        }

        public static zza zza() {
            return zzi.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzi();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0006\b\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzjq<zzi> zzaVar = zzj;
                    if (zzaVar == null) {
                        synchronized (zzi.class) {
                            zzaVar = zzj;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzi);
                                zzj = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzi = zziVar;
            zzhx.zza((Class<zzi>) zzi.class, zziVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzj extends zzhx<zzj, zza> implements zzji {
        private static final zzj zzj;
        private static volatile zzjq<zzj> zzk;
        private int zzc;
        private int zzg;
        private com.google.android.gms.internal.firebase_auth.zzr zzh;
        private byte zzi = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";

        private zzj() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzj, zza> implements zzji {
            private zza() {
                super(zzj.zzj);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final zzgd zzc() {
            zzgd zzgdVarZza = zzgd.zza(this.zzg);
            return zzgdVarZza == null ? zzgd.OOB_REQ_TYPE_UNSPECIFIED : zzgdVarZza;
        }

        public final boolean zzd() {
            return (this.zzc & 16) != 0;
        }

        public final com.google.android.gms.internal.firebase_auth.zzr zze() {
            com.google.android.gms.internal.firebase_auth.zzr zzrVar = this.zzh;
            return zzrVar == null ? com.google.android.gms.internal.firebase_auth.zzr.zzf() : zzrVar;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzj();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzj, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\f\u0003\u0005\t\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", zzgd.zzb(), "zzh"});
                case 4:
                    return zzj;
                case 5:
                    zzjq<zzj> zzaVar = zzk;
                    if (zzaVar == null) {
                        synchronized (zzj.class) {
                            zzaVar = zzk;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzj);
                                zzk = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return Byte.valueOf(this.zzi);
                case 7:
                    this.zzi = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzj> zzf() {
            return (zzjq) zzj.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzj zzjVar = new zzj();
            zzj = zzjVar;
            zzhx.zza((Class<zzj>) zzj.class, zzjVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzk extends zzhx<zzk, zza> implements zzji {
        private static final zzk zzi;
        private static volatile zzjq<zzk> zzj;
        private int zzc;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";

        private zzk() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzk, zza> implements zzji {
            private zza() {
                super(zzk.zzi);
            }

            public final zza zza(String str) {
                zzb();
                ((zzk) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzk) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                zzb();
                ((zzk) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 8;
            this.zzg = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 16;
            this.zzh = str;
        }

        public static zza zza() {
            return zzi.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzk();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzjq<zzk> zzaVar = zzj;
                    if (zzaVar == null) {
                        synchronized (zzk.class) {
                            zzaVar = zzj;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzi);
                                zzj = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzi = zzkVar;
            zzhx.zza((Class<zzk>) zzk.class, zzkVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzl extends zzhx<zzl, zza> implements zzji {
        private static final zzl zze;
        private static volatile zzjq<zzl> zzf;
        private int zzc;
        private String zzd = "";

        private zzl() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzl, zza> implements zzji {
            private zza() {
                super(zzl.zze);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zzd;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzl();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zze, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", new Object[]{"zzc", "zzd"});
                case 4:
                    return zze;
                case 5:
                    zzjq<zzl> zzaVar = zzf;
                    if (zzaVar == null) {
                        synchronized (zzl.class) {
                            zzaVar = zzf;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zze);
                                zzf = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzl> zzb() {
            return (zzjq) zze.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzl zzlVar = new zzl();
            zze = zzlVar;
            zzhx.zza((Class<zzl>) zzl.class, zzlVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzm extends zzhx<zzm, zza> implements zzji {
        private static final zzm zzaa;
        private static volatile zzjq<zzm> zzab;
        private static final zzie<Integer, com.google.android.gms.internal.firebase_auth.zzv> zzu = new com.google.android.gms.internal.firebase_auth.zzq();
        private int zzc;
        private boolean zzk;
        private boolean zzl;
        private zzkp zzo;
        private boolean zzp;
        private long zzr;
        private boolean zzv;
        private long zzx;
        private long zzy;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private zzih<String> zzi = zzhx.zzad();
        private String zzj = "";
        private String zzm = "";
        private String zzn = "";
        private String zzq = "";
        private String zzs = "";
        private zzif zzt = zzac();
        private zzih<String> zzw = zzhx.zzad();
        private String zzz = "";

        private zzm() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzm, zza> implements zzji {
            private zza() {
                super(zzm.zzaa);
            }

            public final zza zza(String str) {
                zzb();
                ((zzm) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzm) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                zzb();
                ((zzm) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                zzb();
                ((zzm) this.zza).zzd(str);
                return this;
            }

            public final zza zze(String str) {
                zzb();
                ((zzm) this.zza).zze(str);
                return this;
            }

            public final zza zzf(String str) {
                zzb();
                ((zzm) this.zza).zzf(str);
                return this;
            }

            public final zza zza(Iterable<? extends com.google.android.gms.internal.firebase_auth.zzv> iterable) {
                zzb();
                ((zzm) this.zza).zza(iterable);
                return this;
            }

            public final zza zza(boolean z) {
                zzb();
                ((zzm) this.zza).zza(z);
                return this;
            }

            public final zza zzb(Iterable<String> iterable) {
                zzb();
                ((zzm) this.zza).zzb(iterable);
                return this;
            }

            public final zza zzg(String str) {
                zzb();
                ((zzm) this.zza).zzg(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 4;
            this.zzf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 8;
            this.zzg = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 16;
            this.zzh = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 32;
            this.zzj = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 16384;
            this.zzs = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(Iterable<? extends com.google.android.gms.internal.firebase_auth.zzv> iterable) {
            if (!this.zzt.zza()) {
                zzif zzifVar = this.zzt;
                int size = zzifVar.size();
                this.zzt = zzifVar.zza(size == 0 ? 10 : size << 1);
            }
            Iterator<? extends com.google.android.gms.internal.firebase_auth.zzv> it = iterable.iterator();
            while (it.hasNext()) {
                this.zzt.zzd(it.next().zza());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 32768;
            this.zzv = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(Iterable<String> iterable) {
            if (!this.zzw.zza()) {
                zzih<String> zzihVar = this.zzw;
                int size = zzihVar.size();
                this.zzw = zzihVar.zza(size == 0 ? 10 : size << 1);
            }
            zzge.zza(iterable, this.zzw);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 262144;
            this.zzz = str;
        }

        public static zza zza() {
            return zzaa.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzm();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzaa, "\u0001\u0016\u0000\u0001\u0002\u0019\u0016\u0000\u0003\u0000\u0002\b\u0000\u0003\b\u0001\u0004\b\u0002\u0005\b\u0003\u0006\b\u0004\u0007\u001a\b\b\u0005\t\u0007\u0006\n\u0007\u0007\u000b\b\b\f\b\t\r\t\n\u000e\u0007\u000b\u000f\b\f\u0010\u0002\r\u0011\b\u000e\u0012\u001e\u0013\u0007\u000f\u0014\u001a\u0015\u0002\u0010\u0016\u0002\u0011\u0019\b\u0012", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", com.google.android.gms.internal.firebase_auth.zzv.zzb(), "zzv", "zzw", "zzx", "zzy", "zzz"});
                case 4:
                    return zzaa;
                case 5:
                    zzjq<zzm> zzaVar = zzab;
                    if (zzaVar == null) {
                        synchronized (zzm.class) {
                            zzaVar = zzab;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzaa);
                                zzab = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzaa = zzmVar;
            zzhx.zza((Class<zzm>) zzm.class, zzmVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzn extends zzhx<zzn, zza> implements zzji {
        private static final zzn zzr;
        private static volatile zzjq<zzn> zzs;
        private int zzc;
        private long zzn;
        private boolean zzp;
        private byte zzq = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private zzih<String> zzh = zzhx.zzad();
        private String zzi = "";
        private zzih<com.google.android.gms.internal.firebase_auth.zzu> zzj = zzad();
        private String zzk = "";
        private String zzl = "";
        private String zzm = "";
        private String zzo = "";

        private zzn() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzn, zza> implements zzji {
            private zza() {
                super(zzn.zzr);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        @Deprecated
        public final String zza() {
            return this.zzf;
        }

        @Deprecated
        public final String zzb() {
            return this.zzg;
        }

        public final String zzc() {
            return this.zzi;
        }

        @Deprecated
        public final List<com.google.android.gms.internal.firebase_auth.zzu> zzd() {
            return this.zzj;
        }

        @Deprecated
        public final String zze() {
            return this.zzl;
        }

        public final String zzf() {
            return this.zzm;
        }

        public final long zzg() {
            return this.zzn;
        }

        public final String zzh() {
            return this.zzo;
        }

        public final boolean zzi() {
            return this.zzp;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzn();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzr, "\u0001\r\u0000\u0001\u0001\r\r\u0000\u0002\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\u001a\u0006\b\u0004\u0007\u001b\b\b\u0005\t\b\u0006\n\b\u0007\u000b\u0002\b\f\b\t\r\u0007\n", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", com.google.android.gms.internal.firebase_auth.zzu.class, "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
                case 4:
                    return zzr;
                case 5:
                    zzjq<zzn> zzaVar = zzs;
                    if (zzaVar == null) {
                        synchronized (zzn.class) {
                            zzaVar = zzs;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzr);
                                zzs = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return Byte.valueOf(this.zzq);
                case 7:
                    this.zzq = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzn> zzj() {
            return (zzjq) zzr.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzn zznVar = new zzn();
            zzr = zznVar;
            zzhx.zza((Class<zzn>) zzn.class, zznVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzo extends zzhx<zzo, zza> implements zzji {
        private static final zzo zzo;
        private static volatile zzjq<zzo> zzp;
        private int zzc;
        private boolean zzk;
        private boolean zzm;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzl = "";
        private String zzn = "";

        private zzo() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzo, zza> implements zzji {
            private zza() {
                super(zzo.zzo);
            }

            public final zza zza(String str) {
                zzb();
                ((zzo) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzo) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                zzb();
                ((zzo) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1024;
            this.zzn = str;
        }

        public static zza zza() {
            return zzo.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzo();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzo, "\u0001\u000b\u0000\u0001\u0001\r\u000b\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\u0007\u0007\t\b\b\n\u0007\t\r\b\n", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
                case 4:
                    return zzo;
                case 5:
                    zzjq<zzo> zzaVar = zzp;
                    if (zzaVar == null) {
                        synchronized (zzo.class) {
                            zzaVar = zzp;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzo);
                                zzp = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzo = zzoVar;
            zzhx.zza((Class<zzo>) zzo.class, zzoVar);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.internal.firebase_auth.zzp$zzp, reason: collision with other inner class name */
    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class C0062zzp extends zzhx<C0062zzp, zza> implements zzji {
        private static final C0062zzp zzl;
        private static volatile zzjq<C0062zzp> zzm;
        private int zzc;
        private long zzi;
        private byte zzk = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzj = "";

        private C0062zzp() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.firebase_auth.zzp$zzp$zza */
        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<C0062zzp, zza> implements zzji {
            private zza() {
                super(C0062zzp.zzl);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final String zzc() {
            return this.zzg;
        }

        public final String zzd() {
            return this.zzh;
        }

        public final long zze() {
            return this.zzi;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new C0062zzp();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzl, "\u0001\u0007\u0000\u0001\u0001\b\u0007\u0000\u0000\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0004\b\u0002\u0005\b\u0003\u0006\b\u0004\u0007\u0002\u0005\b\b\u0006", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
                case 4:
                    return zzl;
                case 5:
                    zzjq<C0062zzp> zzaVar = zzm;
                    if (zzaVar == null) {
                        synchronized (C0062zzp.class) {
                            zzaVar = zzm;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzl);
                                zzm = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return Byte.valueOf(this.zzk);
                case 7:
                    this.zzk = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<C0062zzp> zzf() {
            return (zzjq) zzl.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            C0062zzp c0062zzp = new C0062zzp();
            zzl = c0062zzp;
            zzhx.zza((Class<C0062zzp>) C0062zzp.class, c0062zzp);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzq extends zzhx<zzq, zza> implements zzji {
        private static final zzq zzq;
        private static volatile zzjq<zzq> zzr;
        private int zzc;
        private boolean zzg;
        private long zzj;
        private boolean zzl;
        private boolean zzm;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzh = "";
        private String zzi = "";
        private String zzk = "";
        private boolean zzn = true;
        private String zzo = "";
        private String zzp = "";

        private zzq() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzq, zza> implements zzji {
            private zza() {
                super(zzq.zzq);
            }

            public final zza zza(String str) {
                zzb();
                ((zzq) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzq) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                zzb();
                ((zzq) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                zzb();
                ((zzq) this.zza).zzd(str);
                return this;
            }

            public final zza zza(boolean z) {
                zzb();
                ((zzq) this.zza).zza(z);
                return this;
            }

            public final zza zzb(boolean z) {
                zzb();
                ((zzq) this.zza).zzb(z);
                return this;
            }

            public final zza zzc(boolean z) {
                zzb();
                ((zzq) this.zza).zzc(z);
                return this;
            }

            public final zza zze(String str) {
                zzb();
                ((zzq) this.zza).zze(str);
                return this;
            }

            public final zza zzf(String str) {
                zzb();
                ((zzq) this.zza).zzf(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 16;
            this.zzh = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 128;
            this.zzk = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 256;
            this.zzl = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(boolean z) {
            this.zzc |= 512;
            this.zzm = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(boolean z) {
            this.zzc |= 1024;
            this.zzn = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 2048;
            this.zzo = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 4096;
            this.zzp = str;
        }

        public static zza zza() {
            return zzq.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzq();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzq, "\u0001\r\u0000\u0001\u0001\u000f\r\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0007\u0003\u0005\b\u0004\u0006\b\u0005\u0007\u0002\u0006\b\b\u0007\t\u0007\b\n\u0007\t\u000b\u0007\n\r\b\u000b\u000f\b\f", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
                case 4:
                    return zzq;
                case 5:
                    zzjq<zzq> zzaVar = zzr;
                    if (zzaVar == null) {
                        synchronized (zzq.class) {
                            zzaVar = zzr;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzq);
                                zzr = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzq = zzqVar;
            zzhx.zza((Class<zzq>) zzq.class, zzqVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzr extends zzhx<zzr, zza> implements zzji {
        private static final zzr zzav;
        private static volatile zzjq<zzr> zzaw;
        private boolean zzab;
        private long zzag;
        private boolean zzai;
        private long zzal;
        private boolean zzaq;
        private int zzc;
        private int zzd;
        private boolean zzh;
        private boolean zzv;
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzi = "";
        private String zzj = "";
        private String zzk = "";
        private String zzl = "";
        private String zzm = "";
        private String zzn = "";
        private String zzo = "";
        private String zzp = "";
        private String zzq = "";
        private String zzr = "";
        private String zzs = "";
        private String zzt = "";
        private String zzu = "";
        private String zzw = "";
        private String zzx = "";
        private String zzy = "";
        private String zzz = "";
        private zzih<String> zzaa = zzhx.zzad();
        private String zzac = "";
        private String zzad = "";
        private String zzae = "";
        private String zzaf = "";
        private String zzah = "";
        private String zzaj = "";
        private String zzak = "";
        private String zzam = "";
        private String zzan = "";
        private String zzao = "";
        private String zzap = "";
        private String zzar = "";
        private String zzas = "";
        private String zzat = "";
        private zzih<com.google.android.gms.internal.firebase_auth.zzr> zzau = zzad();

        private zzr() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzr, zza> implements zzji {
            private zza() {
                super(zzr.zzav);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zzf;
        }

        public final String zzb() {
            return this.zzg;
        }

        public final String zzc() {
            return this.zzo;
        }

        public final String zzd() {
            return this.zzu;
        }

        public final String zze() {
            return this.zzw;
        }

        public final String zzf() {
            return this.zzx;
        }

        public final boolean zzg() {
            return this.zzab;
        }

        public final String zzh() {
            return this.zzae;
        }

        public final boolean zzi() {
            return this.zzai;
        }

        public final String zzj() {
            return this.zzaj;
        }

        public final String zzk() {
            return this.zzak;
        }

        public final long zzl() {
            return this.zzal;
        }

        public final String zzm() {
            return this.zzam;
        }

        public final String zzn() {
            return this.zzao;
        }

        public final String zzo() {
            return this.zzap;
        }

        public final boolean zzp() {
            return this.zzaq;
        }

        public final String zzq() {
            return this.zzar;
        }

        public final String zzr() {
            return this.zzas;
        }

        public final String zzs() {
            return this.zzat;
        }

        public final List<com.google.android.gms.internal.firebase_auth.zzr> zzt() {
            return this.zzau;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzr();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzav, "\u0001+\u0000\u0002\u0001-+\u0000\u0002\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0007\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\b\u0007\t\b\b\n\b\t\u000b\b\n\f\b\u000b\r\b\f\u000e\b\r\u000f\b\u000e\u0010\b\u000f\u0011\b\u0010\u0012\u0007\u0011\u0013\b\u0012\u0014\b\u0013\u0015\b\u0014\u0017\b\u0015\u0018\u001a\u0019\u0007\u0016\u001a\b\u0017\u001b\b\u0018\u001c\b\u0019\u001d\b\u001a\u001e\u0002\u001b\u001f\b\u001c \u0007\u001d!\b\u001e\"\b\u001f#\u0002 $\b!%\b\"&\b#'\b$(\u0007%*\b&+\b',\b(-\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzaa", "zzab", "zzac", "zzad", "zzae", "zzaf", "zzag", "zzah", "zzai", "zzaj", "zzak", "zzal", "zzam", "zzan", "zzao", "zzap", "zzaq", "zzar", "zzas", "zzat", "zzau", com.google.android.gms.internal.firebase_auth.zzr.class});
                case 4:
                    return zzav;
                case 5:
                    zzjq<zzr> zzaVar = zzaw;
                    if (zzaVar == null) {
                        synchronized (zzr.class) {
                            zzaVar = zzaw;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzav);
                                zzaw = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzr> zzu() {
            return (zzjq) zzav.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzr zzrVar = new zzr();
            zzav = zzrVar;
            zzhx.zza((Class<zzr>) zzr.class, zzrVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzs extends zzhx<zzs, zza> implements zzji {
        private static final zzs zzi;
        private static volatile zzjq<zzs> zzj;
        private int zzc;
        private boolean zzf;
        private long zzg;
        private String zzd = "";
        private String zze = "";
        private String zzh = "";

        private zzs() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzs, zza> implements zzji {
            private zza() {
                super(zzs.zzi);
            }

            public final zza zza(String str) {
                zzb();
                ((zzs) this.zza).zza(str);
                return this;
            }

            public final zza zza(boolean z) {
                zzb();
                ((zzs) this.zza).zza(true);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzs) this.zza).zzb(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 4;
            this.zzf = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 16;
            this.zzh = str;
        }

        public static zza zza() {
            return zzi.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzs();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzi, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0007\u0002\u0004\u0002\u0003\u0005\b\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzi;
                case 5:
                    zzjq<zzs> zzaVar = zzj;
                    if (zzaVar == null) {
                        synchronized (zzs.class) {
                            zzaVar = zzj;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzi);
                                zzj = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzi = zzsVar;
            zzhx.zza((Class<zzs>) zzs.class, zzsVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzt extends zzhx<zzt, zza> implements zzji {
        private static final zzt zzj;
        private static volatile zzjq<zzt> zzk;
        private int zzc;
        private long zzg;
        private boolean zzh;
        private byte zzi = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";

        private zzt() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzt, zza> implements zzji {
            private zza() {
                super(zzt.zzj);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final long zzc() {
            return this.zzg;
        }

        public final boolean zzd() {
            return this.zzh;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzt();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzj, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\u0002\u0003\u0005\u0007\u0004", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh"});
                case 4:
                    return zzj;
                case 5:
                    zzjq<zzt> zzaVar = zzk;
                    if (zzaVar == null) {
                        synchronized (zzt.class) {
                            zzaVar = zzk;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzj);
                                zzk = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return Byte.valueOf(this.zzi);
                case 7:
                    this.zzi = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzt> zze() {
            return (zzjq) zzj.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzt zztVar = new zzt();
            zzj = zztVar;
            zzhx.zza((Class<zzt>) zzt.class, zztVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzu extends zzhx<zzu, zza> implements zzji {
        private static final zzu zzo;
        private static volatile zzjq<zzu> zzp;
        private int zzc;
        private long zzk;
        private boolean zzm;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzj = "";
        private String zzl = "";
        private String zzn = "";

        private zzu() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzu, zza> implements zzji {
            private zza() {
                super(zzu.zzo);
            }

            public final zza zza(String str) {
                zzb();
                ((zzu) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzu) this.zza).zzb(str);
                return this;
            }

            public final zza zza(boolean z) {
                zzb();
                ((zzu) this.zza).zza(z);
                return this;
            }

            public final zza zzc(String str) {
                zzb();
                ((zzu) this.zza).zzc(str);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzc |= 512;
            this.zzm = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1024;
            this.zzn = str;
        }

        public static zza zza() {
            return zzo.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzu();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzo, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\b\u0006\b\u0002\u0007\t\b\b\n\u0007\t\u000b\b\n", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
                case 4:
                    return zzo;
                case 5:
                    zzjq<zzu> zzaVar = zzp;
                    if (zzaVar == null) {
                        synchronized (zzu.class) {
                            zzaVar = zzp;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzo);
                                zzp = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzo = zzuVar;
            zzhx.zza((Class<zzu>) zzu.class, zzuVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzv extends zzhx<zzv, zza> implements zzji {
        private static final zzv zzs;
        private static volatile zzjq<zzv> zzt;
        private int zzc;
        private boolean zzi;
        private long zzl;
        private long zzo;
        private byte zzr = 2;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzj = "";
        private String zzk = "";
        private String zzm = "";
        private String zzn = "";
        private String zzp = "";
        private zzih<com.google.android.gms.internal.firebase_auth.zzr> zzq = zzad();

        private zzv() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzv, zza> implements zzji {
            private zza() {
                super(zzv.zzs);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zze;
        }

        public final String zzb() {
            return this.zzf;
        }

        public final String zzc() {
            return this.zzg;
        }

        public final String zzd() {
            return this.zzh;
        }

        public final String zze() {
            return this.zzj;
        }

        public final String zzf() {
            return this.zzn;
        }

        public final long zzg() {
            return this.zzo;
        }

        public final String zzh() {
            return this.zzp;
        }

        public final List<com.google.android.gms.internal.firebase_auth.zzr> zzi() {
            return this.zzq;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzv();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzs, "\u0001\u000e\u0000\u0001\u0001\u000f\u000e\u0000\u0001\u0001\u0001Ԉ\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\u0007\u0005\u0007\b\u0006\b\b\u0007\t\u0002\b\n\b\t\u000b\b\n\f\u0002\u000b\u000e\b\f\u000f\u001b", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", com.google.android.gms.internal.firebase_auth.zzr.class});
                case 4:
                    return zzs;
                case 5:
                    zzjq<zzv> zzaVar = zzt;
                    if (zzaVar == null) {
                        synchronized (zzv.class) {
                            zzaVar = zzt;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzs);
                                zzt = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return Byte.valueOf(this.zzr);
                case 7:
                    this.zzr = (byte) (obj != null ? 1 : 0);
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzv> zzj() {
            return (zzjq) zzs.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzv zzvVar = new zzv();
            zzs = zzvVar;
            zzhx.zza((Class<zzv>) zzv.class, zzvVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzw extends zzhx<zzw, zza> implements zzji {
        private static final zzw zzl;
        private static volatile zzjq<zzw> zzm;
        private int zzc;
        private int zzj;
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";
        private String zzi = "";
        private String zzk = "";

        private zzw() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzw, zza> implements zzji {
            private zza() {
                super(zzw.zzl);
            }

            public final zza zza(String str) {
                zzb();
                ((zzw) this.zza).zza(str);
                return this;
            }

            public final zza zzb(String str) {
                zzb();
                ((zzw) this.zza).zzb(str);
                return this;
            }

            public final zza zzc(String str) {
                zzb();
                ((zzw) this.zza).zzc(str);
                return this;
            }

            public final zza zzd(String str) {
                zzb();
                ((zzw) this.zza).zzd(str);
                return this;
            }

            public final zza zze(String str) {
                zzb();
                ((zzw) this.zza).zze(str);
                return this;
            }

            public final zza zza(zzaa zzaaVar) {
                zzb();
                ((zzw) this.zza).zza(zzaaVar);
                return this;
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 1;
            this.zzd = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 2;
            this.zze = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 4;
            this.zzf = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 8;
            this.zzg = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc |= 32;
            this.zzi = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzaa zzaaVar) {
            if (zzaaVar == null) {
                throw new NullPointerException();
            }
            this.zzc |= 64;
            this.zzj = zzaaVar.zza();
        }

        public static zza zza() {
            return zzl.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzw();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzl, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005\u0007\f\u0006\b\b\u0007", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzaa.zzb(), "zzk"});
                case 4:
                    return zzl;
                case 5:
                    zzjq<zzw> zzaVar = zzm;
                    if (zzaVar == null) {
                        synchronized (zzw.class) {
                            zzaVar = zzm;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzl);
                                zzm = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
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
            zzl = zzwVar;
            zzhx.zza((Class<zzw>) zzw.class, zzwVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzx extends zzhx<zzx, zza> implements zzji {
        private static final zzx zzn;
        private static volatile zzjq<zzx> zzo;
        private int zzc;
        private long zzf;
        private boolean zzh;
        private long zzk;
        private long zzm;
        private String zzd = "";
        private String zze = "";
        private String zzg = "";
        private String zzi = "";
        private String zzj = "";
        private String zzl = "";

        private zzx() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzx, zza> implements zzji {
            private zza() {
                super(zzx.zzn);
            }

            /* synthetic */ zza(com.google.android.gms.internal.firebase_auth.zzo zzoVar) {
                this();
            }
        }

        public final String zza() {
            return this.zzd;
        }

        public final String zzb() {
            return this.zze;
        }

        public final long zzc() {
            return this.zzf;
        }

        public final String zzd() {
            return this.zzg;
        }

        public final boolean zze() {
            return this.zzh;
        }

        public final String zzf() {
            return this.zzi;
        }

        public final String zzg() {
            return this.zzj;
        }

        public final long zzh() {
            return this.zzk;
        }

        public final String zzi() {
            return this.zzl;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            com.google.android.gms.internal.firebase_auth.zzo zzoVar = null;
            switch (com.google.android.gms.internal.firebase_auth.zzo.zza[i - 1]) {
                case 1:
                    return new zzx();
                case 2:
                    return new zza(zzoVar);
                case 3:
                    return zza(zzn, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\b\u0003\u0005\u0007\u0004\u0006\b\u0005\u0007\b\u0006\b\u0002\u0007\t\b\b\n\u0002\t", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
                case 4:
                    return zzn;
                case 5:
                    zzjq<zzx> zzaVar = zzo;
                    if (zzaVar == null) {
                        synchronized (zzx.class) {
                            zzaVar = zzo;
                            if (zzaVar == null) {
                                zzaVar = new zzhx.zza<>(zzn);
                                zzo = zzaVar;
                            }
                            break;
                        }
                    }
                    return zzaVar;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzx> zzj() {
            return (zzjq) zzn.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzx zzxVar = new zzx();
            zzn = zzxVar;
            zzhx.zza((Class<zzx>) zzx.class, zzxVar);
        }
    }
}
