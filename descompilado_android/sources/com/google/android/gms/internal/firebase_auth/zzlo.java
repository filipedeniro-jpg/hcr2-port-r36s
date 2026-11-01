package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzlo {

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zza extends zzhx<zza, C0060zza> implements zzji {
        private static final zza zzh;
        private static volatile zzjq<zza> zzi;
        private String zzc = "";
        private String zzd = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.firebase_auth.zzlo$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class C0060zza extends zzhx.zzb<zza, C0060zza> implements zzji {
            private C0060zza() {
                super(zza.zzh);
            }

            public final C0060zza zza(String str) {
                zzb();
                ((zza) this.zza).zza(str);
                return this;
            }

            public final C0060zza zzb(String str) {
                zzb();
                ((zza) this.zza).zzb(str);
                return this;
            }

            /* synthetic */ C0060zza(zzln zzlnVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzc = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zze = str;
        }

        public static C0060zza zza() {
            return zzh.zzz();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            zzln zzlnVar = null;
            switch (zzln.zza[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0060zza(zzlnVar);
                case 3:
                    return zza(zzh, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg"});
                case 4:
                    return zzh;
                case 5:
                    zzjq<zza> zzaVar = zzi;
                    if (zzaVar == null) {
                        synchronized (zza.class) {
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
            zza zzaVar = new zza();
            zzh = zzaVar;
            zzhx.zza((Class<zza>) zza.class, zzaVar);
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final class zzb extends zzhx<zzb, zza> implements zzji {
        private static final zzb zzj;
        private static volatile zzjq<zzb> zzk;
        private long zzd;
        private long zzi;
        private String zzc = "";
        private String zze = "";
        private String zzf = "";
        private String zzg = "";
        private String zzh = "";

        private zzb() {
        }

        /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
        public static final class zza extends zzhx.zzb<zzb, zza> implements zzji {
            private zza() {
                super(zzb.zzj);
            }

            /* synthetic */ zza(zzln zzlnVar) {
                this();
            }
        }

        public final String zza() {
            return this.zzc;
        }

        public final long zzb() {
            return this.zzd;
        }

        public final String zzc() {
            return this.zze;
        }

        public final String zzd() {
            return this.zzf;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzhx
        protected final Object zza(int i, Object obj, Object obj2) {
            zzln zzlnVar = null;
            switch (zzln.zza[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzlnVar);
                case 3:
                    return zza(zzj, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001Ȉ\u0002\u0002\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007\u0002", new Object[]{"zzc", "zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
                case 4:
                    return zzj;
                case 5:
                    zzjq<zzb> zzaVar = zzk;
                    if (zzaVar == null) {
                        synchronized (zzb.class) {
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
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static zzjq<zzb> zze() {
            return (zzjq) zzj.zza(zzhx.zze.zzg, (Object) null, (Object) null);
        }

        static {
            zzb zzbVar = new zzb();
            zzj = zzbVar;
            zzhx.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }
}
