package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdip extends zzdqd<zzdip, zza> implements zzdrp {
    private static volatile zzdrw<zzdip> zzdv;
    private static final zzdip zzgwq;
    private int zzgtf;
    private zzdot zzgtn = zzdot.zzhfn;
    private zzdit zzgwp;

    private zzdip() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdip, zza> implements zzdrp {
        private zza() {
            super(zzdip.zzgwq);
        }

        public final zza zzen(int i) {
            zzazi();
            ((zzdip) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzc(zzdit zzditVar) {
            zzazi();
            ((zzdip) this.zzhkb).zzb(zzditVar);
            return this;
        }

        public final zza zzbm(zzdot zzdotVar) {
            zzazi();
            ((zzdip) this.zzhkb).zzw(zzdotVar);
            return this;
        }

        /* synthetic */ zza(zzdio zzdioVar) {
            this();
        }
    }

    public final int getVersion() {
        return this.zzgtf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzgtf = i;
    }

    public final zzdit zzatf() {
        zzdit zzditVar = this.zzgwp;
        return zzditVar == null ? zzdit.zzatn() : zzditVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(zzdit zzditVar) {
        if (zzditVar == null) {
            throw new NullPointerException();
        }
        this.zzgwp = zzditVar;
    }

    public final zzdot zzaqe() {
        return this.zzgtn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzw(zzdot zzdotVar) {
        if (zzdotVar == null) {
            throw new NullPointerException();
        }
        this.zzgtn = zzdotVar;
    }

    public static zzdip zzbk(zzdot zzdotVar) throws zzdqn {
        return (zzdip) zzdqd.zza(zzgwq, zzdotVar);
    }

    public static zza zzatg() {
        return zzgwq.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdio zzdioVar = null;
        switch (zzdio.zzdi[i - 1]) {
            case 1:
                return new zzdip();
            case 2:
                return new zza(zzdioVar);
            case 3:
                return zza(zzgwq, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzgtf", "zzgwp", "zzgtn"});
            case 4:
                return zzgwq;
            case 5:
                zzdrw<zzdip> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdip.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgwq);
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

    public static zzdip zzath() {
        return zzgwq;
    }

    static {
        zzdip zzdipVar = new zzdip();
        zzgwq = zzdipVar;
        zzdqd.zza((Class<zzdip>) zzdip.class, zzdipVar);
    }
}
