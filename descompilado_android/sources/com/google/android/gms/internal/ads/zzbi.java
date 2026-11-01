package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbi {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zza extends zzdqd<zza, C0040zza> implements zzdrp {
        private static final zza zzdu;
        private static volatile zzdrw<zza> zzdv;
        private int zzdj;
        private long zzdl;
        private long zzdp;
        private long zzdq;
        private long zzds;
        private String zzdk = "";
        private String zzdm = "";
        private String zzdn = "";
        private String zzdo = "";
        private String zzdr = "";
        private String zzdt = "";

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzbi$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class C0040zza extends zzdqd.zza<zza, C0040zza> implements zzdrp {
            private C0040zza() {
                super(zza.zzdu);
            }

            public final C0040zza zzi(String str) {
                zzazi();
                ((zza) this.zzhkb).zzm(str);
                return this;
            }

            public final C0040zza zzc(long j) {
                zzazi();
                ((zza) this.zzhkb).zzd(j);
                return this;
            }

            public final C0040zza zzj(String str) {
                zzazi();
                ((zza) this.zzhkb).zzn(str);
                return this;
            }

            public final C0040zza zzk(String str) {
                zzazi();
                ((zza) this.zzhkb).zzo(str);
                return this;
            }

            public final C0040zza zzl(String str) {
                zzazi();
                ((zza) this.zzhkb).zzp(str);
                return this;
            }

            /* synthetic */ C0040zza(zzbh zzbhVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 1;
            this.zzdk = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(long j) {
            this.zzdj |= 2;
            this.zzdl = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 4;
            this.zzdm = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 8;
            this.zzdn = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 16;
            this.zzdo = str;
        }

        public static C0040zza zzr() {
            return zzdu.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbh zzbhVar = null;
            switch (zzbh.zzdi[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0040zza(zzbhVar);
                case 3:
                    return zza(zzdu, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001\b\u0000\u0002\u0002\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\u0002\u0005\u0007\u0002\u0006\b\b\u0007\t\u0002\b\n\b\t", new Object[]{"zzdj", "zzdk", "zzdl", "zzdm", "zzdn", "zzdo", "zzdp", "zzdq", "zzdr", "zzds", "zzdt"});
                case 4:
                    return zzdu;
                case 5:
                    zzdrw<zza> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zza.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzdu);
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
            zzdu = zzaVar;
            zzdqd.zza((Class<zza>) zza.class, zzaVar);
        }
    }
}
