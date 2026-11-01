package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbk {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zza extends zzdqd<zza, C0041zza> implements zzdrp {
        private static volatile zzdrw<zza> zzdv;
        private static final zza zzdy;
        private int zzdj;
        private zzb zzdw;
        private zzc zzdx;

        private zza() {
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzbk$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class C0041zza extends zzdqd.zza<zza, C0041zza> implements zzdrp {
            private C0041zza() {
                super(zza.zzdy);
            }

            /* synthetic */ C0041zza(zzbj zzbjVar) {
                this();
            }
        }

        public final boolean zzt() {
            return (this.zzdj & 1) != 0;
        }

        public final zzb zzu() {
            zzb zzbVar = this.zzdw;
            return zzbVar == null ? zzb.zzz() : zzbVar;
        }

        public final boolean zzv() {
            return (this.zzdj & 2) != 0;
        }

        public final zzc zzw() {
            zzc zzcVar = this.zzdx;
            return zzcVar == null ? zzc.zzaf() : zzcVar;
        }

        public static zza zza(byte[] bArr, zzdpq zzdpqVar) throws zzdqn {
            return (zza) zzdqd.zza(zzdy, bArr, zzdpqVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbj zzbjVar = null;
            switch (zzbj.zzdi[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C0041zza(zzbjVar);
                case 3:
                    return zza(zzdy, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0000\u0002\t\u0001", new Object[]{"zzdj", "zzdw", "zzdx"});
                case 4:
                    return zzdy;
                case 5:
                    zzdrw<zza> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zza.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzdy);
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
            zzdy = zzaVar;
            zzdqd.zza((Class<zza>) zza.class, zzaVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zzb extends zzdqd<zzb, zza> implements zzdrp {
        private static volatile zzdrw<zzb> zzdv;
        private static final zzb zzea;
        private int zzdj;
        private int zzdz = 2;

        private zzb() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzb, zza> implements zzdrp {
            private zza() {
                super(zzb.zzea);
            }

            /* synthetic */ zza(zzbj zzbjVar) {
                this();
            }
        }

        public final zzbm zzy() {
            zzbm zzbmVarZze = zzbm.zze(this.zzdz);
            return zzbmVarZze == null ? zzbm.ENUM_SIGNAL_SOURCE_ADSHIELD : zzbmVarZze;
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbj zzbjVar = null;
            switch (zzbj.zzdi[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzbjVar);
                case 3:
                    return zza(zzea, "\u0001\u0001\u0000\u0001\u001b\u001b\u0001\u0000\u0000\u0000\u001b\f\u0000", new Object[]{"zzdj", "zzdz", zzbm.zzac()});
                case 4:
                    return zzea;
                case 5:
                    zzdrw<zzb> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzb.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzea);
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

        public static zzb zzz() {
            return zzea;
        }

        static {
            zzb zzbVar = new zzb();
            zzea = zzbVar;
            zzdqd.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zzc extends zzdqd<zzc, zza> implements zzdrp {
        private static volatile zzdrw<zzc> zzdv;
        private static final zzc zzeo;
        private int zzdj;
        private String zzei = "";
        private String zzej = "";
        private String zzek = "";
        private String zzel = "";
        private String zzem = "";
        private String zzen = "";

        private zzc() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzc, zza> implements zzdrp {
            private zza() {
                super(zzc.zzeo);
            }

            /* synthetic */ zza(zzbj zzbjVar) {
                this();
            }
        }

        public final String zzad() {
            return this.zzei;
        }

        public final String zzae() {
            return this.zzen;
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbj zzbjVar = null;
            switch (zzbj.zzdi[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzbjVar);
                case 3:
                    return zza(zzeo, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\b\u0000\u0002\b\u0001\u0003\b\u0002\u0004\b\u0003\u0005\b\u0004\u0006\b\u0005", new Object[]{"zzdj", "zzei", "zzej", "zzek", "zzel", "zzem", "zzen"});
                case 4:
                    return zzeo;
                case 5:
                    zzdrw<zzc> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzc.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzeo);
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

        public static zzc zzaf() {
            return zzeo;
        }

        static {
            zzc zzcVar = new zzc();
            zzeo = zzcVar;
            zzdqd.zza((Class<zzc>) zzc.class, zzcVar);
        }
    }
}
