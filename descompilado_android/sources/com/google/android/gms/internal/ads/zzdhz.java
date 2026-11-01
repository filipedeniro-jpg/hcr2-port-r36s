package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdhz extends zzdqd<zzdhz, zza> implements zzdrp {
    private static volatile zzdrw<zzdhz> zzdv;
    private static final zzdhz zzgvt;
    private int zzgtf;
    private zzdot zzgtn = zzdot.zzhfn;
    private zzdia zzgvs;

    private zzdhz() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdhz, zza> implements zzdrp {
        private zza() {
            super(zzdhz.zzgvt);
        }

        public final zza zzeh(int i) {
            zzazi();
            ((zzdhz) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzb(zzdia zzdiaVar) {
            zzazi();
            ((zzdhz) this.zzhkb).zza(zzdiaVar);
            return this;
        }

        public final zza zzbc(zzdot zzdotVar) {
            zzazi();
            ((zzdhz) this.zzhkb).zzw(zzdotVar);
            return this;
        }

        /* synthetic */ zza(zzdhy zzdhyVar) {
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

    public final zzdia zzasq() {
        zzdia zzdiaVar = this.zzgvs;
        return zzdiaVar == null ? zzdia.zzasu() : zzdiaVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdia zzdiaVar) {
        if (zzdiaVar == null) {
            throw new NullPointerException();
        }
        this.zzgvs = zzdiaVar;
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

    public static zzdhz zzba(zzdot zzdotVar) throws zzdqn {
        return (zzdhz) zzdqd.zza(zzgvt, zzdotVar);
    }

    public static zza zzasr() {
        return zzgvt.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdhy zzdhyVar = null;
        switch (zzdhy.zzdi[i - 1]) {
            case 1:
                return new zzdhz();
            case 2:
                return new zza(zzdhyVar);
            case 3:
                return zza(zzgvt, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzgtf", "zzgvs", "zzgtn"});
            case 4:
                return zzgvt;
            case 5:
                zzdrw<zzdhz> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdhz.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgvt);
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
        zzdhz zzdhzVar = new zzdhz();
        zzgvt = zzdhzVar;
        zzdqd.zza((Class<zzdhz>) zzdhz.class, zzdhzVar);
    }
}
