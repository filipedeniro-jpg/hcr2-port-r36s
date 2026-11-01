package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcys extends zzdqd<zzcys, zza> implements zzdrp {
    private static volatile zzdrw<zzcys> zzdv;
    private static final zzcys zzgnm;
    private int zzdj;
    private int zzgnj;
    private zzcyo zzgnl;
    private String zzdk = "";
    private String zzgnk = "";

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public enum zzb implements zzdqh {
        EVENT_TYPE_UNKNOWN(0),
        BLOCKED_IMPRESSION(1);

        private static final zzdqg<zzb> zzeg = new zzcyu();
        private final int value;

        @Override // com.google.android.gms.internal.ads.zzdqh
        public final int zzab() {
            return this.value;
        }

        public static zzb zzdn(int i) {
            if (i == 0) {
                return EVENT_TYPE_UNKNOWN;
            }
            if (i != 1) {
                return null;
            }
            return BLOCKED_IMPRESSION;
        }

        public static zzdqj zzac() {
            return zzcyw.zzep;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
        }

        zzb(int i) {
            this.value = i;
        }
    }

    private zzcys() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzcys, zza> implements zzdrp {
        private zza() {
            super(zzcys.zzgnm);
        }

        public final zza zzb(zzb zzbVar) {
            zzazi();
            ((zzcys) this.zzhkb).zza(zzbVar);
            return this;
        }

        public final zza zzgo(String str) {
            zzazi();
            ((zzcys) this.zzhkb).zzm(str);
            return this;
        }

        public final zza zzb(zzcyo.zzb zzbVar) {
            zzazi();
            ((zzcys) this.zzhkb).zza(zzbVar);
            return this;
        }

        /* synthetic */ zza(zzcyt zzcytVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzb zzbVar) {
        if (zzbVar == null) {
            throw new NullPointerException();
        }
        this.zzdj |= 1;
        this.zzgnj = zzbVar.zzab();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzm(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzdj |= 2;
        this.zzdk = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzcyo.zzb zzbVar) {
        this.zzgnl = (zzcyo) ((zzdqd) zzbVar.zzazm());
        this.zzdj |= 8;
    }

    public static zza zzanu() {
        return zzgnm.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzcyt zzcytVar = null;
        switch (zzcyt.zzdi[i - 1]) {
            case 1:
                return new zzcys();
            case 2:
                return new zza(zzcytVar);
            case 3:
                return zza(zzgnm, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\f\u0000\u0002\b\u0001\u0003\b\u0002\u0004\t\u0003", new Object[]{"zzdj", "zzgnj", zzb.zzac(), "zzdk", "zzgnk", "zzgnl"});
            case 4:
                return zzgnm;
            case 5:
                zzdrw<zzcys> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzcys.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgnm);
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
        zzcys zzcysVar = new zzcys();
        zzgnm = zzcysVar;
        zzdqd.zza((Class<zzcys>) zzcys.class, zzcysVar);
    }
}
