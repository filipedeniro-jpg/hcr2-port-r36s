package com.google.android.gms.internal.ads;

import com.google.android.gms.drive.DriveFile;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbo {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zza extends zzdqd<zza, zzb> implements zzdrp {
        private static volatile zzdrw<zza> zzdv;
        private static final zza zzhk;
        private int zzdj;
        private int zzeq;
        private int zzer;
        private long zzet;
        private long zzeu;
        private long zzev;
        private long zzew;
        private long zzex;
        private long zzey;
        private long zzez;
        private long zzfa;
        private long zzfb;
        private long zzfc;
        private long zzfe;
        private long zzff;
        private long zzfg;
        private long zzfh;
        private long zzfi;
        private long zzfj;
        private long zzfk;
        private long zzfl;
        private long zzfm;
        private long zzfo;
        private long zzfp;
        private long zzfq;
        private long zzfr;
        private zzb zzfu;
        private zze zzgk;
        private zzf zzgm;
        private int zzgx;
        private int zzgy;
        private int zzgz;
        private zzf zzha;
        private long zzhd;
        private boolean zzhg;
        private long zzhi;
        private zze zzhj;
        private String zzes = "";
        private String zzdt = "";
        private String zzfd = "";
        private String zzei = "";
        private String zzfn = "";
        private String zzek = "";
        private long zzfs = -1;
        private long zzft = -1;
        private long zzfv = -1;
        private long zzfw = -1;
        private long zzfx = -1;
        private long zzfy = -1;
        private long zzfz = -1;
        private long zzga = -1;
        private String zzel = "D";
        private String zzem = "D";
        private long zzgb = -1;
        private int zzgc = 1000;
        private int zzgd = 1000;
        private long zzge = -1;
        private long zzgf = -1;
        private long zzgg = -1;
        private long zzgh = -1;
        private long zzgi = -1;
        private int zzgj = 1000;
        private zzdqk<zze> zzgl = zzazr();
        private long zzgn = -1;
        private long zzgo = -1;
        private long zzgp = -1;
        private long zzgq = -1;
        private long zzgr = -1;
        private long zzgs = -1;
        private long zzgt = -1;
        private long zzgu = -1;
        private String zzgv = "D";
        private long zzgw = -1;
        private long zzhb = -1;
        private int zzhc = 1000;
        private String zzhe = "";
        private int zzhf = 2;
        private String zzhh = "";

        /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzbo$zza$zza, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public enum EnumC0042zza implements zzdqh {
            DEBUGGER_STATE_UNSPECIFIED(0),
            DEBUGGER_STATE_NOT_INSTALLED(1),
            DEBUGGER_STATE_INSTALLED(2),
            DEBUGGER_STATE_ACTIVE(3),
            DEBUGGER_STATE_ENVVAR(4),
            DEBUGGER_STATE_MACHPORT(5),
            DEBUGGER_STATE_ENVVAR_MACHPORT(6);

            private static final zzdqg<EnumC0042zza> zzeg = new zzbr();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static EnumC0042zza zzg(int i) {
                switch (i) {
                    case 0:
                        return DEBUGGER_STATE_UNSPECIFIED;
                    case 1:
                        return DEBUGGER_STATE_NOT_INSTALLED;
                    case 2:
                        return DEBUGGER_STATE_INSTALLED;
                    case 3:
                        return DEBUGGER_STATE_ACTIVE;
                    case 4:
                        return DEBUGGER_STATE_ENVVAR;
                    case 5:
                        return DEBUGGER_STATE_MACHPORT;
                    case 6:
                        return DEBUGGER_STATE_ENVVAR_MACHPORT;
                    default:
                        return null;
                }
            }

            public static zzdqj zzac() {
                return zzbq.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            EnumC0042zza(int i) {
                this.value = i;
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public enum zzc implements zzdqh {
            DEVICE_IDENTIFIER_NO_ID(0),
            DEVICE_IDENTIFIER_APP_SPECIFIC_ID(1),
            DEVICE_IDENTIFIER_GLOBAL_ID(2),
            DEVICE_IDENTIFIER_ADVERTISER_ID(3),
            DEVICE_IDENTIFIER_ADVERTISER_ID_UNHASHED(4),
            DEVICE_IDENTIFIER_ANDROID_AD_ID(5),
            DEVICE_IDENTIFIER_GFIBER_ADVERTISING_ID(6);

            private static final zzdqg<zzc> zzeg = new zzbs();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            public static zzc zzh(int i) {
                switch (i) {
                    case 0:
                        return DEVICE_IDENTIFIER_NO_ID;
                    case 1:
                        return DEVICE_IDENTIFIER_APP_SPECIFIC_ID;
                    case 2:
                        return DEVICE_IDENTIFIER_GLOBAL_ID;
                    case 3:
                        return DEVICE_IDENTIFIER_ADVERTISER_ID;
                    case 4:
                        return DEVICE_IDENTIFIER_ADVERTISER_ID_UNHASHED;
                    case 5:
                        return DEVICE_IDENTIFIER_ANDROID_AD_ID;
                    case 6:
                        return DEVICE_IDENTIFIER_GFIBER_ADVERTISING_ID;
                    default:
                        return null;
                }
            }

            public static zzdqj zzac() {
                return zzbt.zzep;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzc(int i) {
                this.value = i;
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public enum zzd implements zzdqh {
            ERROR_ENCODE_SIZE_FAIL(1),
            ERROR_UNKNOWN(3),
            ERROR_NO_SIGNALS(5),
            ERROR_ENCRYPTION(7),
            ERROR_MEMORY(9),
            ERROR_SIMULATOR(11),
            ERROR_SERVICE(13),
            ERROR_THREAD(15),
            PSN_WEB64_FAIL(2),
            PSN_DECRYPT_SIZE_FAIL(4),
            PSN_MD5_CHECK_FAIL(8),
            PSN_MD5_SIZE_FAIL(16),
            PSN_MD5_FAIL(32),
            PSN_DECODE_FAIL(64),
            PSN_SALT_FAIL(128),
            PSN_BITSLICER_FAIL(256),
            PSN_REQUEST_TYPE_FAIL(512),
            PSN_INVALID_ERROR_CODE(1024),
            PSN_TIMESTAMP_EXPIRED(2048),
            PSN_ENCODE_SIZE_FAIL(4096),
            PSN_BLANK_VALUE(8192),
            PSN_INITIALIZATION_FAIL(16384),
            PSN_GASS_CLIENT_FAIL(32768),
            PSN_SIGNALS_TIMEOUT(65536),
            PSN_TINK_FAIL(131072);

            private static final zzdqg<zzd> zzeg = new zzbu();
            private final int value;

            @Override // com.google.android.gms.internal.ads.zzdqh
            public final int zzab() {
                return this.value;
            }

            @Override // java.lang.Enum
            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
            }

            zzd(int i) {
                this.value = i;
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zze extends zzdqd<zze, C0043zza> implements zzdrp {
            private static volatile zzdrw<zze> zzdv;
            private static final zze zzju;
            private int zzdj;
            private long zzjo;
            private long zzjp;
            private long zzfe = -1;
            private long zzff = -1;
            private long zzjb = -1;
            private long zzjc = -1;
            private long zzjd = -1;
            private long zzje = -1;
            private int zzjf = 1000;
            private long zzjg = -1;
            private long zzjh = -1;
            private long zzji = -1;
            private int zzjj = 1000;
            private long zzjk = -1;
            private long zzjl = -1;
            private long zzjm = -1;
            private long zzjn = -1;
            private long zzjq = -1;
            private long zzjr = -1;
            private long zzjs = -1;
            private long zzjt = -1;

            private zze() {
            }

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzbo$zza$zze$zza, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
            public static final class C0043zza extends zzdqd.zza<zze, C0043zza> implements zzdrp {
                private C0043zza() {
                    super(zze.zzju);
                }

                public final C0043zza zzcl(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzj(j);
                    return this;
                }

                public final C0043zza zzcm(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzk(j);
                    return this;
                }

                public final C0043zza zzcn(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzbs(j);
                    return this;
                }

                public final C0043zza zzco(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzbt(j);
                    return this;
                }

                public final C0043zza zzat() {
                    zzazi();
                    ((zze) this.zzhkb).zzao();
                    return this;
                }

                public final C0043zza zzcp(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzbu(j);
                    return this;
                }

                public final C0043zza zzcq(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzbv(j);
                    return this;
                }

                public final C0043zza zzk(zzbz zzbzVar) {
                    zzazi();
                    ((zze) this.zzhkb).zzi(zzbzVar);
                    return this;
                }

                public final C0043zza zzcr(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzbw(j);
                    return this;
                }

                public final C0043zza zzcs(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzbx(j);
                    return this;
                }

                public final C0043zza zzct(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzby(j);
                    return this;
                }

                public final C0043zza zzl(zzbz zzbzVar) {
                    zzazi();
                    ((zze) this.zzhkb).zzj(zzbzVar);
                    return this;
                }

                public final C0043zza zzcu(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzbz(j);
                    return this;
                }

                public final C0043zza zzcv(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzca(j);
                    return this;
                }

                public final C0043zza zzcw(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzcb(j);
                    return this;
                }

                public final C0043zza zzcx(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzcc(j);
                    return this;
                }

                public final C0043zza zzcy(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzcd(j);
                    return this;
                }

                public final C0043zza zzcz(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzce(j);
                    return this;
                }

                public final C0043zza zzda(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzcf(j);
                    return this;
                }

                public final C0043zza zzdb(long j) {
                    zzazi();
                    ((zze) this.zzhkb).zzcg(j);
                    return this;
                }

                /* synthetic */ C0043zza(zzbp zzbpVar) {
                    this();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzj(long j) {
                this.zzdj |= 1;
                this.zzfe = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzk(long j) {
                this.zzdj |= 2;
                this.zzff = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbs(long j) {
                this.zzdj |= 4;
                this.zzjb = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbt(long j) {
                this.zzdj |= 8;
                this.zzjc = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzao() {
                this.zzdj &= -9;
                this.zzjc = -1L;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbu(long j) {
                this.zzdj |= 16;
                this.zzjd = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbv(long j) {
                this.zzdj |= 32;
                this.zzje = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzi(zzbz zzbzVar) {
                if (zzbzVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 64;
                this.zzjf = zzbzVar.zzab();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbw(long j) {
                this.zzdj |= 128;
                this.zzjg = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbx(long j) {
                this.zzdj |= 256;
                this.zzjh = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzby(long j) {
                this.zzdj |= 512;
                this.zzji = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzj(zzbz zzbzVar) {
                if (zzbzVar == null) {
                    throw new NullPointerException();
                }
                this.zzdj |= 1024;
                this.zzjj = zzbzVar.zzab();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzbz(long j) {
                this.zzdj |= 2048;
                this.zzjk = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzca(long j) {
                this.zzdj |= 4096;
                this.zzjl = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzcb(long j) {
                this.zzdj |= 8192;
                this.zzjm = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzcc(long j) {
                this.zzdj |= 16384;
                this.zzjn = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzcd(long j) {
                this.zzdj |= 32768;
                this.zzjo = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzce(long j) {
                this.zzdj |= 65536;
                this.zzjp = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzcf(long j) {
                this.zzdj |= 131072;
                this.zzjq = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzcg(long j) {
                this.zzdj |= 262144;
                this.zzjr = j;
            }

            public static C0043zza zzap() {
                return zzju.zzazo();
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzbp zzbpVar = null;
                switch (zzbp.zzdi[i - 1]) {
                    case 1:
                        return new zze();
                    case 2:
                        return new C0043zza(zzbpVar);
                    case 3:
                        return zza(zzju, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0002\u0003\u0005\u0002\u0004\u0006\u0002\u0005\u0007\f\u0006\b\u0002\u0007\t\u0002\b\n\u0002\t\u000b\f\n\f\u0002\u000b\r\u0002\f\u000e\u0002\r\u000f\u0002\u000e\u0010\u0002\u000f\u0011\u0002\u0010\u0012\u0002\u0011\u0013\u0002\u0012\u0014\u0002\u0013\u0015\u0002\u0014", new Object[]{"zzdj", "zzfe", "zzff", "zzjb", "zzjc", "zzjd", "zzje", "zzjf", zzbz.zzac(), "zzjg", "zzjh", "zzji", "zzjj", zzbz.zzac(), "zzjk", "zzjl", "zzjm", "zzjn", "zzjo", "zzjp", "zzjq", "zzjr", "zzjs", "zzjt"});
                    case 4:
                        return zzju;
                    case 5:
                        zzdrw<zze> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zze.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzju);
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
                zzju = zzeVar;
                zzdqd.zza((Class<zze>) zze.class, zzeVar);
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zzf extends zzdqd<zzf, C0044zza> implements zzdrp {
            private static volatile zzdrw<zzf> zzdv;
            private static final zzf zzjz;
            private int zzdj;
            private long zzgh = -1;
            private long zzgi = -1;
            private long zzjv = -1;
            private long zzjw = -1;
            private long zzjx = -1;
            private long zzjy = -1;

            private zzf() {
            }

            /* JADX INFO: renamed from: com.google.android.gms.internal.ads.zzbo$zza$zzf$zza, reason: collision with other inner class name */
            /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
            public static final class C0044zza extends zzdqd.zza<zzf, C0044zza> implements zzdrp {
                private C0044zza() {
                    super(zzf.zzjz);
                }

                public final C0044zza zzdc(long j) {
                    zzazi();
                    ((zzf) this.zzhkb).zzch(j);
                    return this;
                }

                public final C0044zza zzdd(long j) {
                    zzazi();
                    ((zzf) this.zzhkb).zzci(j);
                    return this;
                }

                public final C0044zza zzde(long j) {
                    zzazi();
                    ((zzf) this.zzhkb).zzcj(j);
                    return this;
                }

                public final C0044zza zzdf(long j) {
                    zzazi();
                    ((zzf) this.zzhkb).zzck(j);
                    return this;
                }

                /* synthetic */ C0044zza(zzbp zzbpVar) {
                    this();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzch(long j) {
                this.zzdj |= 4;
                this.zzjv = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzci(long j) {
                this.zzdj |= 8;
                this.zzjw = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzcj(long j) {
                this.zzdj |= 16;
                this.zzjx = j;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void zzck(long j) {
                this.zzdj |= 32;
                this.zzjy = j;
            }

            public static C0044zza zzar() {
                return zzjz.zzazo();
            }

            @Override // com.google.android.gms.internal.ads.zzdqd
            protected final Object zza(int i, Object obj, Object obj2) {
                zzbp zzbpVar = null;
                switch (zzbp.zzdi[i - 1]) {
                    case 1:
                        return new zzf();
                    case 2:
                        return new C0044zza(zzbpVar);
                    case 3:
                        return zza(zzjz, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\u0002\u0000\u0002\u0002\u0001\u0003\u0002\u0002\u0004\u0002\u0003\u0005\u0002\u0004\u0006\u0002\u0005", new Object[]{"zzdj", "zzgh", "zzgi", "zzjv", "zzjw", "zzjx", "zzjy"});
                    case 4:
                        return zzjz;
                    case 5:
                        zzdrw<zzf> zzcVar = zzdv;
                        if (zzcVar == null) {
                            synchronized (zzf.class) {
                                zzcVar = zzdv;
                                if (zzcVar == null) {
                                    zzcVar = new zzdqd.zzc<>(zzjz);
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
                zzjz = zzfVar;
                zzdqd.zza((Class<zzf>) zzf.class, zzfVar);
            }
        }

        private zza() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zzb extends zzdqd.zza<zza, zzb> implements zzdrp {
            private zzb() {
                super(zza.zzhk);
            }

            public final zzb zzz(String str) {
                zzazi();
                ((zza) this.zzhkb).zzq(str);
                return this;
            }

            public final zzb zzaa(String str) {
                zzazi();
                ((zza) this.zzhkb).zzr(str);
                return this;
            }

            public final zzb zzal(long j) {
                zzazi();
                ((zza) this.zzhkb).zze(j);
                return this;
            }

            public final zzb zzam(long j) {
                zzazi();
                ((zza) this.zzhkb).zzf(j);
                return this;
            }

            public final zzb zzan(long j) {
                zzazi();
                ((zza) this.zzhkb).zzg(j);
                return this;
            }

            public final zzb zzao(long j) {
                zzazi();
                ((zza) this.zzhkb).zzh(j);
                return this;
            }

            public final zzb zzap(long j) {
                zzazi();
                ((zza) this.zzhkb).zzi(j);
                return this;
            }

            public final zzb zzaq(long j) {
                zzazi();
                ((zza) this.zzhkb).zzj(j);
                return this;
            }

            public final zzb zzar(long j) {
                zzazi();
                ((zza) this.zzhkb).zzk(j);
                return this;
            }

            public final zzb zzas(long j) {
                zzazi();
                ((zza) this.zzhkb).zzl(j);
                return this;
            }

            public final zzb zzat(long j) {
                zzazi();
                ((zza) this.zzhkb).zzm(j);
                return this;
            }

            public final zzb zzau(long j) {
                zzazi();
                ((zza) this.zzhkb).zzn(j);
                return this;
            }

            public final zzb zzav(long j) {
                zzazi();
                ((zza) this.zzhkb).zzo(j);
                return this;
            }

            public final zzb zzaw(long j) {
                zzazi();
                ((zza) this.zzhkb).zzp(j);
                return this;
            }

            public final zzb zzab(String str) {
                zzazi();
                ((zza) this.zzhkb).zzs(str);
                return this;
            }

            public final zzb zzac(String str) {
                zzazi();
                ((zza) this.zzhkb).zzt(str);
                return this;
            }

            public final zzb zzax(long j) {
                zzazi();
                ((zza) this.zzhkb).zzq(j);
                return this;
            }

            public final zzb zzay(long j) {
                zzazi();
                ((zza) this.zzhkb).zzr(j);
                return this;
            }

            public final zzb zzaz(long j) {
                zzazi();
                ((zza) this.zzhkb).zzs(j);
                return this;
            }

            public final zzb zzad(String str) {
                zzazi();
                ((zza) this.zzhkb).zzu(str);
                return this;
            }

            public final zzb zzba(long j) {
                zzazi();
                ((zza) this.zzhkb).zzt(j);
                return this;
            }

            @Deprecated
            public final zzb zzbb(long j) {
                zzazi();
                ((zza) this.zzhkb).zzu(j);
                return this;
            }

            public final zzb zzbc(long j) {
                zzazi();
                ((zza) this.zzhkb).zzv(j);
                return this;
            }

            public final zzb zzbd(long j) {
                zzazi();
                ((zza) this.zzhkb).zzw(j);
                return this;
            }

            public final zzb zzbe(long j) {
                zzazi();
                ((zza) this.zzhkb).zzx(j);
                return this;
            }

            public final zzb zzbf(long j) {
                zzazi();
                ((zza) this.zzhkb).zzy(j);
                return this;
            }

            public final zzb zzbg(long j) {
                zzazi();
                ((zza) this.zzhkb).zzz(j);
                return this;
            }

            public final zzb zzbh(long j) {
                zzazi();
                ((zza) this.zzhkb).zzaa(j);
                return this;
            }

            public final zzb zzbi(long j) {
                zzazi();
                ((zza) this.zzhkb).zzab(j);
                return this;
            }

            public final zzb zzae(String str) {
                zzazi();
                ((zza) this.zzhkb).zzv(str);
                return this;
            }

            public final zzb zzaf(String str) {
                zzazi();
                ((zza) this.zzhkb).zzw(str);
                return this;
            }

            public final zzb zze(zzbz zzbzVar) {
                zzazi();
                ((zza) this.zzhkb).zza(zzbzVar);
                return this;
            }

            public final zzb zzf(zzbz zzbzVar) {
                zzazi();
                ((zza) this.zzhkb).zzb(zzbzVar);
                return this;
            }

            public final zzb zzbj(long j) {
                zzazi();
                ((zza) this.zzhkb).zzac(j);
                return this;
            }

            public final zzb zzbk(long j) {
                zzazi();
                ((zza) this.zzhkb).zzad(j);
                return this;
            }

            public final zzb zzbl(long j) {
                zzazi();
                ((zza) this.zzhkb).zzae(j);
                return this;
            }

            public final zzb zzg(zzbz zzbzVar) {
                zzazi();
                ((zza) this.zzhkb).zzc(zzbzVar);
                return this;
            }

            public final zzb zzc(zze zzeVar) {
                zzazi();
                ((zza) this.zzhkb).zza(zzeVar);
                return this;
            }

            public final zzb zzd(zze zzeVar) {
                zzazi();
                ((zza) this.zzhkb).zzb(zzeVar);
                return this;
            }

            public final zzb zzan() {
                zzazi();
                ((zza) this.zzhkb).zzai();
                return this;
            }

            public final zzb zzb(zzf zzfVar) {
                zzazi();
                ((zza) this.zzhkb).zza(zzfVar);
                return this;
            }

            public final zzb zzbm(long j) {
                zzazi();
                ((zza) this.zzhkb).zzaf(j);
                return this;
            }

            public final zzb zzbn(long j) {
                zzazi();
                ((zza) this.zzhkb).zzag(j);
                return this;
            }

            public final zzb zzbo(long j) {
                zzazi();
                ((zza) this.zzhkb).zzah(j);
                return this;
            }

            public final zzb zzbp(long j) {
                zzazi();
                ((zza) this.zzhkb).zzai(j);
                return this;
            }

            public final zzb zzbq(long j) {
                zzazi();
                ((zza) this.zzhkb).zzaj(j);
                return this;
            }

            public final zzb zzag(String str) {
                zzazi();
                ((zza) this.zzhkb).zzx(str);
                return this;
            }

            public final zzb zzb(zzf zzfVar) {
                zzazi();
                ((zza) this.zzhkb).zza(zzfVar);
                return this;
            }

            public final zzb zzh(zzbz zzbzVar) {
                zzazi();
                ((zza) this.zzhkb).zzd(zzbzVar);
                return this;
            }

            public final zzb zzah(String str) {
                zzazi();
                ((zza) this.zzhkb).zzy(str);
                return this;
            }

            public final zzb zzb(zzc zzcVar) {
                zzazi();
                ((zza) this.zzhkb).zza(zzcVar);
                return this;
            }

            public final zzb zzb(boolean z) {
                zzazi();
                ((zza) this.zzhkb).zza(z);
                return this;
            }

            public final zzb zzbr(long j) {
                zzazi();
                ((zza) this.zzhkb).zzak(j);
                return this;
            }

            /* synthetic */ zzb(zzbp zzbpVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzq(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 1;
            this.zzes = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzr(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 2;
            this.zzdt = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(long j) {
            this.zzdj |= 4;
            this.zzet = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(long j) {
            this.zzdj |= 16;
            this.zzev = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzg(long j) {
            this.zzdj |= 32;
            this.zzew = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzh(long j) {
            this.zzdj |= 1024;
            this.zzfb = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzi(long j) {
            this.zzdj |= 2048;
            this.zzfc = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzj(long j) {
            this.zzdj |= 8192;
            this.zzfe = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzk(long j) {
            this.zzdj |= 16384;
            this.zzff = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzl(long j) {
            this.zzdj |= 32768;
            this.zzfg = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzm(long j) {
            this.zzdj |= 65536;
            this.zzfh = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzn(long j) {
            this.zzdj |= 524288;
            this.zzfk = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo(long j) {
            this.zzdj |= 1048576;
            this.zzfl = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzp(long j) {
            this.zzdj |= 2097152;
            this.zzfm = j;
        }

        public final boolean zzah() {
            return (this.zzdj & 4194304) != 0;
        }

        public final String zzad() {
            return this.zzei;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzs(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 4194304;
            this.zzei = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzt(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 8388608;
            this.zzfn = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzq(long j) {
            this.zzdj |= 16777216;
            this.zzfo = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzr(long j) {
            this.zzdj |= 33554432;
            this.zzfp = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzs(long j) {
            this.zzdj |= 67108864;
            this.zzfq = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzu(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 134217728;
            this.zzek = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzt(long j) {
            this.zzdj |= DriveFile.MODE_READ_ONLY;
            this.zzfr = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzu(long j) {
            this.zzdj |= DriveFile.MODE_WRITE_ONLY;
            this.zzfs = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzv(long j) {
            this.zzdj |= 1073741824;
            this.zzft = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzw(long j) {
            this.zzeq |= 1;
            this.zzfv = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzx(long j) {
            this.zzeq |= 2;
            this.zzfw = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzy(long j) {
            this.zzeq |= 4;
            this.zzfx = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzz(long j) {
            this.zzeq |= 8;
            this.zzfy = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzaa(long j) {
            this.zzeq |= 16;
            this.zzfz = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzab(long j) {
            this.zzeq |= 32;
            this.zzga = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzv(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzeq |= 64;
            this.zzel = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzw(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzeq |= 128;
            this.zzem = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzbz zzbzVar) {
            if (zzbzVar == null) {
                throw new NullPointerException();
            }
            this.zzeq |= 512;
            this.zzgc = zzbzVar.zzab();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zzbz zzbzVar) {
            if (zzbzVar == null) {
                throw new NullPointerException();
            }
            this.zzeq |= 1024;
            this.zzgd = zzbzVar.zzab();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzac(long j) {
            this.zzeq |= 2048;
            this.zzge = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzad(long j) {
            this.zzeq |= 4096;
            this.zzgf = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzae(long j) {
            this.zzeq |= 8192;
            this.zzgg = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(zzbz zzbzVar) {
            if (zzbzVar == null) {
                throw new NullPointerException();
            }
            this.zzeq |= 65536;
            this.zzgj = zzbzVar.zzab();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zze zzeVar) {
            if (zzeVar == null) {
                throw new NullPointerException();
            }
            this.zzgk = zzeVar;
            this.zzeq |= 131072;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zze zzeVar) {
            if (zzeVar == null) {
                throw new NullPointerException();
            }
            if (!this.zzgl.zzaxd()) {
                this.zzgl = zzdqd.zza(this.zzgl);
            }
            this.zzgl.add(zzeVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzai() {
            this.zzgl = zzazr();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzf zzfVar) {
            if (zzfVar == null) {
                throw new NullPointerException();
            }
            this.zzgm = zzfVar;
            this.zzeq |= 262144;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzaf(long j) {
            this.zzeq |= 1048576;
            this.zzgo = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzag(long j) {
            this.zzeq |= 2097152;
            this.zzgp = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzah(long j) {
            this.zzeq |= 4194304;
            this.zzgq = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzai(long j) {
            this.zzeq |= 33554432;
            this.zzgt = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzaj(long j) {
            this.zzeq |= 67108864;
            this.zzgu = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzx(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzeq |= 134217728;
            this.zzgv = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzf zzfVar) {
            if (zzfVar == null) {
                throw new NullPointerException();
            }
            this.zzha = zzfVar;
            this.zzer |= 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(zzbz zzbzVar) {
            if (zzbzVar == null) {
                throw new NullPointerException();
            }
            this.zzer |= 4;
            this.zzhc = zzbzVar.zzab();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzy(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzer |= 16;
            this.zzhe = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzc zzcVar) {
            if (zzcVar == null) {
                throw new NullPointerException();
            }
            this.zzer |= 32;
            this.zzhf = zzcVar.zzab();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(boolean z) {
            this.zzer |= 64;
            this.zzhg = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzak(long j) {
            this.zzer |= 256;
            this.zzhi = j;
        }

        public final boolean zzaj() {
            return (this.zzer & 512) != 0;
        }

        public final zze zzak() {
            zze zzeVar = this.zzhj;
            return zzeVar == null ? zze.zzbf() : zzeVar;
        }

        public static zza zzb(byte[] bArr, zzdpq zzdpqVar) throws zzdqn {
            return (zza) zzdqd.zza(zzhk, bArr, zzdpqVar);
        }

        public static zzb zzal() {
            return zzhk.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zzdi[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zzb(zzbpVar);
                case 3:
                    return zza(zzhk, "\u0001K\u0000\u0003\u0001ÉK\u0000\u0001\u0000\u0001\b\u0000\u0002\b\u0001\u0003\u0002\u0002\u0004\u0002\u0003\u0005\u0002\u0004\u0006\u0002\u0005\u0007\u0002\u0006\b\u0002\u0007\t\u0002\b\n\u0002\t\u000b\u0002\n\f\u0002\u000b\r\b\f\u000e\u0002\r\u000f\u0002\u000e\u0010\u0002\u000f\u0011\u0002\u0010\u0012\u0002\u0011\u0013\u0002\u0012\u0014\u0002\u0013\u0015\u0002C\u0016\u0002\u0014\u0017\u0002\u0015\u0018\bD\u0019\u0002H\u001a\fE\u001b\b\u0016\u001c\u0007F\u001d\b\u0017\u001e\bG\u001f\u0002\u0018 \u0002\u0019!\u0002\u001a\"\b\u001b#\u0002\u001c$\u0002\u001d%\u0002\u001e&\t\u001f'\u0002 (\u0002!)\u0002\"*\u0002#+\u001b,\u0002$-\u0002%.\b&/\b'0\f)1\f*2\t13\u0002+4\u0002,5\u0002-6\u0002.7\u0002/8\f09\t2:\u00023;\u00024<\u00025=\u00026>\u00029?\u0002:@\u0002<A\f=B\f>C\b;D\f?E\t@F\u0002AG\u00027H\u00028I\fBJ\u0002(É\tI", new Object[]{"zzdj", "zzeq", "zzer", "zzes", "zzdt", "zzet", "zzeu", "zzev", "zzew", "zzex", "zzey", "zzez", "zzfa", "zzfb", "zzfc", "zzfd", "zzfe", "zzff", "zzfg", "zzfh", "zzfi", "zzfj", "zzfk", "zzhd", "zzfl", "zzfm", "zzhe", "zzhi", "zzhf", zzc.zzac(), "zzei", "zzhg", "zzfn", "zzhh", "zzfo", "zzfp", "zzfq", "zzek", "zzfr", "zzfs", "zzft", "zzfu", "zzfv", "zzfw", "zzfx", "zzfy", "zzgl", zze.class, "zzfz", "zzga", "zzel", "zzem", "zzgc", zzbz.zzac(), "zzgd", zzbz.zzac(), "zzgk", "zzge", "zzgf", "zzgg", "zzgh", "zzgi", "zzgj", zzbz.zzac(), "zzgm", "zzgn", "zzgo", "zzgp", "zzgq", "zzgt", "zzgu", "zzgw", "zzgx", zzbv.zzac(), "zzgy", zzca.zzac(), "zzgv", "zzgz", EnumC0042zza.zzac(), "zzha", "zzhb", "zzgr", "zzgs", "zzhc", zzbz.zzac(), "zzgb", "zzhj"});
                case 4:
                    return zzhk;
                case 5:
                    zzdrw<zza> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zza.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzhk);
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
            zzhk = zzaVar;
            zzdqd.zza((Class<zza>) zza.class, zzaVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zzb extends zzdqd<zzb, zza> implements zzdrp {
        private static volatile zzdrw<zzb> zzdv;
        private static final zzb zzkf;
        private int zzdj;
        private long zzka;
        private int zzkb;
        private boolean zzkc;
        private zzdqi zzkd = zzazq();
        private long zzke;

        private zzb() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzb, zza> implements zzdrp {
            private zza() {
                super(zzb.zzkf);
            }

            /* synthetic */ zza(zzbp zzbpVar) {
                this();
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zzdi[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza(zzbpVar);
                case 3:
                    return zza(zzkf, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u0002\u0000\u0002\u0004\u0001\u0003\u0007\u0002\u0004\u0016\u0005\u0003\u0003", new Object[]{"zzdj", "zzka", "zzkb", "zzkc", "zzkd", "zzke"});
                case 4:
                    return zzkf;
                case 5:
                    zzdrw<zzb> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzb.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzkf);
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
            zzkf = zzbVar;
            zzdqd.zza((Class<zzb>) zzb.class, zzbVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zzc extends zzdqd<zzc, zza> implements zzdrp {
        private static volatile zzdrw<zzc> zzdv;
        private static final zzc zzki;
        private int zzdj;
        private zzdot zzkg = zzdot.zzhfn;
        private zzdot zzkh = zzdot.zzhfn;

        private zzc() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzc, zza> implements zzdrp {
            private zza() {
                super(zzc.zzki);
            }

            public final zza zzg(zzdot zzdotVar) {
                zzazi();
                ((zzc) this.zzhkb).zza(zzdotVar);
                return this;
            }

            public final zza zzh(zzdot zzdotVar) {
                zzazi();
                ((zzc) this.zzhkb).zzb(zzdotVar);
                return this;
            }

            /* synthetic */ zza(zzbp zzbpVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zza(zzdot zzdotVar) {
            if (zzdotVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 1;
            this.zzkg = zzdotVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zzdot zzdotVar) {
            if (zzdotVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 2;
            this.zzkh = zzdotVar;
        }

        public static zza zzav() {
            return zzki.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zzdi[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza(zzbpVar);
                case 3:
                    return zza(zzki, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\n\u0000\u0002\n\u0001", new Object[]{"zzdj", "zzkg", "zzkh"});
                case 4:
                    return zzki;
                case 5:
                    zzdrw<zzc> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzc.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzki);
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
            zzki = zzcVar;
            zzdqd.zza((Class<zzc>) zzc.class, zzcVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zzd extends zzdqd<zzd, zza> implements zzdrp {
        private static volatile zzdrw<zzd> zzdv;
        private static final zzd zzkn;
        private int zzdj;
        private zzdot zzkj = zzdot.zzhfn;
        private zzdot zzkk = zzdot.zzhfn;
        private zzdot zzkl = zzdot.zzhfn;
        private zzdot zzkm = zzdot.zzhfn;

        private zzd() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzd, zza> implements zzdrp {
            private zza() {
                super(zzd.zzkn);
            }

            public final zza zzi(zzdot zzdotVar) {
                zzazi();
                ((zzd) this.zzhkb).zzc(zzdotVar);
                return this;
            }

            public final zza zzj(zzdot zzdotVar) {
                zzazi();
                ((zzd) this.zzhkb).zzd(zzdotVar);
                return this;
            }

            public final zza zzk(zzdot zzdotVar) {
                zzazi();
                ((zzd) this.zzhkb).zze(zzdotVar);
                return this;
            }

            public final zza zzl(zzdot zzdotVar) {
                zzazi();
                ((zzd) this.zzhkb).zzf(zzdotVar);
                return this;
            }

            /* synthetic */ zza(zzbp zzbpVar) {
                this();
            }
        }

        public final zzdot zzax() {
            return this.zzkj;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzc(zzdot zzdotVar) {
            if (zzdotVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 1;
            this.zzkj = zzdotVar;
        }

        public final zzdot zzay() {
            return this.zzkk;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(zzdot zzdotVar) {
            if (zzdotVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 2;
            this.zzkk = zzdotVar;
        }

        public final zzdot zzaz() {
            return this.zzkl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zze(zzdot zzdotVar) {
            if (zzdotVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 4;
            this.zzkl = zzdotVar;
        }

        public final zzdot zzba() {
            return this.zzkm;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzf(zzdot zzdotVar) {
            if (zzdotVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 8;
            this.zzkm = zzdotVar;
        }

        public static zzd zzc(byte[] bArr, zzdpq zzdpqVar) throws zzdqn {
            return (zzd) zzdqd.zza(zzkn, bArr, zzdpqVar);
        }

        public static zza zzbb() {
            return zzkn.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zzdi[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zza(zzbpVar);
                case 3:
                    return zza(zzkn, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\n\u0000\u0002\n\u0001\u0003\n\u0002\u0004\n\u0003", new Object[]{"zzdj", "zzkj", "zzkk", "zzkl", "zzkm"});
                case 4:
                    return zzkn;
                case 5:
                    zzdrw<zzd> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzd.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzkn);
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
            zzkn = zzdVar;
            zzdqd.zza((Class<zzd>) zzd.class, zzdVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zze extends zzdqd<zze, zza> implements zzdrp {
        private static volatile zzdrw<zze> zzdv;
        private static final zze zzlf;
        private int zzdj;
        private long zzka;
        private String zzld = "";
        private zzdot zzle = zzdot.zzhfn;

        private zze() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zza extends zzdqd.zza<zze, zza> implements zzdrp {
            private zza() {
                super(zze.zzlf);
            }

            /* synthetic */ zza(zzbp zzbpVar) {
                this();
            }
        }

        public final boolean zzbd() {
            return (this.zzdj & 1) != 0;
        }

        public final long zzbe() {
            return this.zzka;
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zzdi[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza(zzbpVar);
                case 3:
                    return zza(zzlf, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001\u0002\u0000\u0003\b\u0001\u0004\n\u0002", new Object[]{"zzdj", "zzka", "zzld", "zzle"});
                case 4:
                    return zzlf;
                case 5:
                    zzdrw<zze> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zze.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzlf);
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

        public static zze zzbf() {
            return zzlf;
        }

        static {
            zze zzeVar = new zze();
            zzlf = zzeVar;
            zzdqd.zza((Class<zze>) zze.class, zzeVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zzf extends zzdqd<zzf, zza> implements zzdrp {
        private static volatile zzdrw<zzf> zzdv;
        private static final zzf zzlg;
        private int zzdj;
        private String zzen = "";

        private zzf() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzf, zza> implements zzdrp {
            private zza() {
                super(zzf.zzlg);
            }

            public final zza zzai(String str) {
                zzazi();
                ((zzf) this.zzhkb).zzaj(str);
                return this;
            }

            /* synthetic */ zza(zzbp zzbpVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzaj(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 1;
            this.zzen = str;
        }

        public static zza zzbh() {
            return zzlg.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zzdi[i - 1]) {
                case 1:
                    return new zzf();
                case 2:
                    return new zza(zzbpVar);
                case 3:
                    return zza(zzlg, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001\b\u0000", new Object[]{"zzdj", "zzen"});
                case 4:
                    return zzlg;
                case 5:
                    zzdrw<zzf> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzf.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzlg);
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
            zzlg = zzfVar;
            zzdqd.zza((Class<zzf>) zzf.class, zzfVar);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zzg extends zzdqd<zzg, zza> implements zzdrp {
        private static volatile zzdrw<zzg> zzdv;
        private static final zzg zzli;
        private int zzdj;
        private zzdqk<zzdot> zzlh = zzazr();
        private zzdot zzkk = zzdot.zzhfn;
        private int zzgy = 1;
        private int zzgx = 1;

        private zzg() {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
        public static final class zza extends zzdqd.zza<zzg, zza> implements zzdrp {
            private zza() {
                super(zzg.zzli);
            }

            public final zza zzm(zzdot zzdotVar) {
                zzazi();
                ((zzg) this.zzhkb).zzo(zzdotVar);
                return this;
            }

            public final zza zzn(zzdot zzdotVar) {
                zzazi();
                ((zzg) this.zzhkb).zzd(zzdotVar);
                return this;
            }

            public final zza zza(zzbv zzbvVar) {
                zzazi();
                ((zzg) this.zzhkb).zzb(zzbvVar);
                return this;
            }

            /* synthetic */ zza(zzbp zzbpVar) {
                this();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzo(zzdot zzdotVar) {
            if (zzdotVar == null) {
                throw new NullPointerException();
            }
            if (!this.zzlh.zzaxd()) {
                this.zzlh = zzdqd.zza(this.zzlh);
            }
            this.zzlh.add(zzdotVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzd(zzdot zzdotVar) {
            if (zzdotVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 1;
            this.zzkk = zzdotVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void zzb(zzbv zzbvVar) {
            if (zzbvVar == null) {
                throw new NullPointerException();
            }
            this.zzdj |= 4;
            this.zzgx = zzbvVar.zzab();
        }

        public static zza zzbj() {
            return zzli.zzazo();
        }

        @Override // com.google.android.gms.internal.ads.zzdqd
        protected final Object zza(int i, Object obj, Object obj2) {
            zzbp zzbpVar = null;
            switch (zzbp.zzdi[i - 1]) {
                case 1:
                    return new zzg();
                case 2:
                    return new zza(zzbpVar);
                case 3:
                    return zza(zzli, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001c\u0002\n\u0000\u0003\f\u0001\u0004\f\u0002", new Object[]{"zzdj", "zzlh", "zzkk", "zzgy", zzca.zzac(), "zzgx", zzbv.zzac()});
                case 4:
                    return zzli;
                case 5:
                    zzdrw<zzg> zzcVar = zzdv;
                    if (zzcVar == null) {
                        synchronized (zzg.class) {
                            zzcVar = zzdv;
                            if (zzcVar == null) {
                                zzcVar = new zzdqd.zzc<>(zzli);
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
            zzli = zzgVar;
            zzdqd.zza((Class<zzg>) zzg.class, zzgVar);
        }
    }
}
