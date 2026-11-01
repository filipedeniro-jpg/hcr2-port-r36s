package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdiu extends zzdqd<zzdiu, zza> implements zzdrp {
    private static volatile zzdrw<zzdiu> zzdv;
    private static final zzdiu zzgwy;
    private String zzgwv = "";
    private zzdot zzgww = zzdot.zzhfn;
    private int zzgwx;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public enum zzb implements zzdqh {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);

        private static final zzdqg<zzb> zzeg = new zzdiw();
        private final int value;

        @Override // com.google.android.gms.internal.ads.zzdqh
        public final int zzab() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public static zzb zzeo(int i) {
            if (i == 0) {
                return UNKNOWN_KEYMATERIAL;
            }
            if (i == 1) {
                return SYMMETRIC;
            }
            if (i == 2) {
                return ASYMMETRIC_PRIVATE;
            }
            if (i == 3) {
                return ASYMMETRIC_PUBLIC;
            }
            if (i != 4) {
                return null;
            }
            return REMOTE;
        }

        @Override // java.lang.Enum
        public final String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.append(getClass().getName());
            sb.append('@');
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            if (this != UNRECOGNIZED) {
                sb.append(" number=");
                sb.append(zzab());
            }
            sb.append(" name=");
            sb.append(name());
            sb.append('>');
            return sb.toString();
        }

        zzb(int i) {
            this.value = i;
        }
    }

    private zzdiu() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdiu, zza> implements zzdrp {
        private zza() {
            super(zzdiu.zzgwy);
        }

        public final zza zzgw(String str) {
            zzazi();
            ((zzdiu) this.zzhkb).zzgv(str);
            return this;
        }

        public final zza zzbo(zzdot zzdotVar) {
            zzazi();
            ((zzdiu) this.zzhkb).zzbn(zzdotVar);
            return this;
        }

        public final zza zzb(zzb zzbVar) {
            zzazi();
            ((zzdiu) this.zzhkb).zza(zzbVar);
            return this;
        }

        /* synthetic */ zza(zzdiv zzdivVar) {
            this();
        }
    }

    public final String zzatp() {
        return this.zzgwv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzgv(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzgwv = str;
    }

    public final zzdot zzatq() {
        return this.zzgww;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbn(zzdot zzdotVar) {
        if (zzdotVar == null) {
            throw new NullPointerException();
        }
        this.zzgww = zzdotVar;
    }

    public final zzb zzatr() {
        zzb zzbVarZzeo = zzb.zzeo(this.zzgwx);
        return zzbVarZzeo == null ? zzb.UNRECOGNIZED : zzbVarZzeo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzb zzbVar) {
        if (zzbVar == null) {
            throw new NullPointerException();
        }
        this.zzgwx = zzbVar.zzab();
    }

    public static zza zzats() {
        return zzgwy.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdiv zzdivVar = null;
        switch (zzdiv.zzdi[i - 1]) {
            case 1:
                return new zzdiu();
            case 2:
                return new zza(zzdivVar);
            case 3:
                return zza(zzgwy, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzgwv", "zzgww", "zzgwx"});
            case 4:
                return zzgwy;
            case 5:
                zzdrw<zzdiu> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdiu.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgwy);
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

    public static zzdiu zzatt() {
        return zzgwy;
    }

    static {
        zzdiu zzdiuVar = new zzdiu();
        zzgwy = zzdiuVar;
        zzdqd.zza((Class<zzdiu>) zzdiu.class, zzdiuVar);
    }
}
