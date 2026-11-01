package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdkj extends zzdqd<zzdkj, zza> implements zzdrp {
    private static volatile zzdrw<zzdkj> zzdv;
    private static final zzdkj zzgzv;
    private int zzgtf;
    private zzdot zzgtn = zzdot.zzhfn;

    private zzdkj() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdkj, zza> implements zzdrp {
        private zza() {
            super(zzdkj.zzgzv);
        }

        public final zza zzff(int i) {
            zzazi();
            ((zzdkj) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzcy(zzdot zzdotVar) {
            zzazi();
            ((zzdkj) this.zzhkb).zzw(zzdotVar);
            return this;
        }

        /* synthetic */ zza(zzdkk zzdkkVar) {
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

    public static zzdkj zzcv(zzdot zzdotVar) throws zzdqn {
        return (zzdkj) zzdqd.zza(zzgzv, zzdotVar);
    }

    public static zza zzawn() {
        return zzgzv.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdkk zzdkkVar = null;
        switch (zzdkk.zzdi[i - 1]) {
            case 1:
                return new zzdkj();
            case 2:
                return new zza(zzdkkVar);
            case 3:
                return zza(zzgzv, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzgtf", "zzgtn"});
            case 4:
                return zzgzv;
            case 5:
                zzdrw<zzdkj> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdkj.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgzv);
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
        zzdkj zzdkjVar = new zzdkj();
        zzgzv = zzdkjVar;
        zzdqd.zza((Class<zzdkj>) zzdkj.class, zzdkjVar);
    }
}
