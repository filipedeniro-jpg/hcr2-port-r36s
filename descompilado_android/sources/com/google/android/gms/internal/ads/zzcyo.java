package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcyo extends zzdqd<zzcyo, zzb> implements zzdrp {
    private static volatile zzdrw<zzcyo> zzdv;
    private static final zzdql<Integer, zza> zzgnb = new zzcyn();
    private static final zzcyo zzgnf;
    private int zzdj;
    private zzdqi zzgna = zzazq();
    private String zzgnc = "";
    private String zzgnd = "";
    private String zzgne = "";

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public enum zza implements zzdqh {
        BLOCKED_REASON_UNKNOWN(1),
        BLOCKED_REASON_BACKGROUND(2);

        private static final zzdqg<zza> zzeg = new zzcyr();
        private final int value;

        @Override // com.google.android.gms.internal.ads.zzdqh
        public final int zzab() {
            return this.value;
        }

        public static zza zzdm(int i) {
            if (i == 1) {
                return BLOCKED_REASON_UNKNOWN;
            }
            if (i != 2) {
                return null;
            }
            return BLOCKED_REASON_BACKGROUND;
        }

        public static zzdqj zzac() {
            return zzcyq.zzep;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
        }

        zza(int i) {
            this.value = i;
        }
    }

    private zzcyo() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
    public static final class zzb extends zzdqd.zza<zzcyo, zzb> implements zzdrp {
        private zzb() {
            super(zzcyo.zzgnf);
        }

        public final zzb zzb(zza zzaVar) {
            zzazi();
            ((zzcyo) this.zzhkb).zza(zzaVar);
            return this;
        }

        public final zzb zzgn(String str) {
            zzazi();
            ((zzcyo) this.zzhkb).zzgm(str);
            return this;
        }

        /* synthetic */ zzb(zzcyn zzcynVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zza zzaVar) {
        if (zzaVar == null) {
            throw new NullPointerException();
        }
        if (!this.zzgna.zzaxd()) {
            this.zzgna = zzdqd.zza(this.zzgna);
        }
        this.zzgna.zzgq(zzaVar.zzab());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzgm(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzdj |= 1;
        this.zzgnc = str;
    }

    public static zzb zzans() {
        return zzgnf.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzcyn zzcynVar = null;
        switch (zzcyp.zzdi[i - 1]) {
            case 1:
                return new zzcyo();
            case 2:
                return new zzb(zzcynVar);
            case 3:
                return zza(zzgnf, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001e\u0002\b\u0000\u0003\b\u0001\u0004\b\u0002", new Object[]{"zzdj", "zzgna", zza.zzac(), "zzgnc", "zzgnd", "zzgne"});
            case 4:
                return zzgnf;
            case 5:
                zzdrw<zzcyo> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzcyo.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgnf);
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
        zzcyo zzcyoVar = new zzcyo();
        zzgnf = zzcyoVar;
        zzdqd.zza((Class<zzcyo>) zzcyo.class, zzcyoVar);
    }
}
