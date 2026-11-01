package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdgl extends zzdqd<zzdgl, zza> implements zzdrp {
    private static volatile zzdrw<zzdgl> zzdv;
    private static final zzdgl zzguc;
    private int zzgtf;
    private zzdot zzgtn = zzdot.zzhfn;
    private zzdgp zzgub;

    private zzdgl() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdgl, zza> implements zzdrp {
        private zza() {
            super(zzdgl.zzguc);
        }

        public final zza zzdy(int i) {
            zzazi();
            ((zzdgl) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzb(zzdgp zzdgpVar) {
            zzazi();
            ((zzdgl) this.zzhkb).zza(zzdgpVar);
            return this;
        }

        public final zza zzaf(zzdot zzdotVar) {
            zzazi();
            ((zzdgl) this.zzhkb).zzw(zzdotVar);
            return this;
        }

        /* synthetic */ zza(zzdgk zzdgkVar) {
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

    public final zzdgp zzaqx() {
        zzdgp zzdgpVar = this.zzgub;
        return zzdgpVar == null ? zzdgp.zzarb() : zzdgpVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdgp zzdgpVar) {
        if (zzdgpVar == null) {
            throw new NullPointerException();
        }
        this.zzgub = zzdgpVar;
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

    public static zzdgl zzad(zzdot zzdotVar) throws zzdqn {
        return (zzdgl) zzdqd.zza(zzguc, zzdotVar);
    }

    public static zza zzaqy() {
        return zzguc.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdgk zzdgkVar = null;
        switch (zzdgk.zzdi[i - 1]) {
            case 1:
                return new zzdgl();
            case 2:
                return new zza(zzdgkVar);
            case 3:
                return zza(zzguc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzgtf", "zzgub", "zzgtn"});
            case 4:
                return zzguc;
            case 5:
                zzdrw<zzdgl> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdgl.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzguc);
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
        zzdgl zzdglVar = new zzdgl();
        zzguc = zzdglVar;
        zzdqd.zza((Class<zzdgl>) zzdgl.class, zzdglVar);
    }
}
