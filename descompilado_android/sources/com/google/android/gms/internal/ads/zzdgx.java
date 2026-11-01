package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdgx extends zzdqd<zzdgx, zza> implements zzdrp {
    private static volatile zzdrw<zzdgx> zzdv;
    private static final zzdgx zzguj;
    private int zzgtf;
    private zzdot zzgtn = zzdot.zzhfn;

    private zzdgx() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdgx, zza> implements zzdrp {
        private zza() {
            super(zzdgx.zzguj);
        }

        public final zza zzea(int i) {
            zzazi();
            ((zzdgx) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzal(zzdot zzdotVar) {
            zzazi();
            ((zzdgx) this.zzhkb).zzw(zzdotVar);
            return this;
        }

        /* synthetic */ zza(zzdgw zzdgwVar) {
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

    public static zzdgx zzaj(zzdot zzdotVar) throws zzdqn {
        return (zzdgx) zzdqd.zza(zzguj, zzdotVar);
    }

    public static zza zzarj() {
        return zzguj.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdgw zzdgwVar = null;
        switch (zzdgw.zzdi[i - 1]) {
            case 1:
                return new zzdgx();
            case 2:
                return new zza(zzdgwVar);
            case 3:
                return zza(zzguj, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzgtf", "zzgtn"});
            case 4:
                return zzguj;
            case 5:
                zzdrw<zzdgx> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdgx.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzguj);
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
        zzdgx zzdgxVar = new zzdgx();
        zzguj = zzdgxVar;
        zzdqd.zza((Class<zzdgx>) zzdgx.class, zzdgxVar);
    }
}
