package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdge extends zzdqd<zzdge, zza> implements zzdrp {
    private static volatile zzdrw<zzdge> zzdv;
    private static final zzdge zzgtx;
    private int zzgtf;
    private zzdot zzgtn = zzdot.zzhfn;
    private zzdgi zzgtw;

    private zzdge() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdge, zza> implements zzdrp {
        private zza() {
            super(zzdge.zzgtx);
        }

        public final zza zzdx(int i) {
            zzazi();
            ((zzdge) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzc(zzdgi zzdgiVar) {
            zzazi();
            ((zzdge) this.zzhkb).zzb(zzdgiVar);
            return this;
        }

        public final zza zzab(zzdot zzdotVar) {
            zzazi();
            ((zzdge) this.zzhkb).zzw(zzdotVar);
            return this;
        }

        /* synthetic */ zza(zzdgf zzdgfVar) {
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

    public final zzdgi zzaqo() {
        zzdgi zzdgiVar = this.zzgtw;
        return zzdgiVar == null ? zzdgi.zzaqv() : zzdgiVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(zzdgi zzdgiVar) {
        if (zzdgiVar == null) {
            throw new NullPointerException();
        }
        this.zzgtw = zzdgiVar;
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

    public static zzdge zzaa(zzdot zzdotVar) throws zzdqn {
        return (zzdge) zzdqd.zza(zzgtx, zzdotVar);
    }

    public static zza zzaqp() {
        return zzgtx.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdgf zzdgfVar = null;
        switch (zzdgf.zzdi[i - 1]) {
            case 1:
                return new zzdge();
            case 2:
                return new zza(zzdgfVar);
            case 3:
                return zza(zzgtx, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzgtf", "zzgtw", "zzgtn"});
            case 4:
                return zzgtx;
            case 5:
                zzdrw<zzdge> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdge.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgtx);
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

    public static zzdge zzaqq() {
        return zzgtx;
    }

    static {
        zzdge zzdgeVar = new zzdge();
        zzgtx = zzdgeVar;
        zzdqd.zza((Class<zzdge>) zzdge.class, zzdgeVar);
    }
}
