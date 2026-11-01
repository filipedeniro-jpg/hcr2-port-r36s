package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdjb extends zzdqd<zzdjb, zza> implements zzdrp {
    private static volatile zzdrw<zzdjb> zzdv;
    private static final zzdjb zzgxs;
    private int zzgxp;
    private boolean zzgxq;
    private String zzgxo = "";
    private String zzgwv = "";
    private String zzgxr = "";

    private zzdjb() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdjb, zza> implements zzdrp {
        private zza() {
            super(zzdjb.zzgxs);
        }

        public final zza zzgz(String str) {
            zzazi();
            ((zzdjb) this.zzhkb).zzgx(str);
            return this;
        }

        public final zza zzha(String str) {
            zzazi();
            ((zzdjb) this.zzhkb).zzgv(str);
            return this;
        }

        public final zza zzer(int i) {
            zzazi();
            ((zzdjb) this.zzhkb).zzeq(0);
            return this;
        }

        public final zza zzbg(boolean z) {
            zzazi();
            ((zzdjb) this.zzhkb).zzbf(true);
            return this;
        }

        public final zza zzhb(String str) {
            zzazi();
            ((zzdjb) this.zzhkb).zzgy(str);
            return this;
        }

        /* synthetic */ zza(zzdjc zzdjcVar) {
            this();
        }
    }

    public final String zzatx() {
        return this.zzgxo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzgx(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzgxo = str;
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

    public final int zzaty() {
        return this.zzgxp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzeq(int i) {
        this.zzgxp = i;
    }

    public final boolean zzatz() {
        return this.zzgxq;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbf(boolean z) {
        this.zzgxq = z;
    }

    public final String zzaua() {
        return this.zzgxr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzgy(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzgxr = str;
    }

    public static zza zzaub() {
        return zzgxs.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdjc zzdjcVar = null;
        switch (zzdjc.zzdi[i - 1]) {
            case 1:
                return new zzdjb();
            case 2:
                return new zza(zzdjcVar);
            case 3:
                return zza(zzgxs, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", new Object[]{"zzgxo", "zzgwv", "zzgxp", "zzgxq", "zzgxr"});
            case 4:
                return zzgxs;
            case 5:
                zzdrw<zzdjb> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdjb.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgxs);
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
        zzdjb zzdjbVar = new zzdjb();
        zzgxs = zzdjbVar;
        zzdqd.zza((Class<zzdjb>) zzdjb.class, zzdjbVar);
    }
}
