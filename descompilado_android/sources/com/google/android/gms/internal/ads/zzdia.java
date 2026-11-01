package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdia extends zzdqd<zzdia, zza> implements zzdrp {
    private static volatile zzdrw<zzdia> zzdv;
    private static final zzdia zzgvu;
    private int zzgtf;
    private zzdot zzgvc = zzdot.zzhfn;
    private zzdot zzgvd = zzdot.zzhfn;
    private zzdhw zzgvm;

    private zzdia() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdia, zza> implements zzdrp {
        private zza() {
            super(zzdia.zzgvu);
        }

        public final zza zzei(int i) {
            zzazi();
            ((zzdia) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzc(zzdhw zzdhwVar) {
            zzazi();
            ((zzdia) this.zzhkb).zzb(zzdhwVar);
            return this;
        }

        public final zza zzbd(zzdot zzdotVar) {
            zzazi();
            ((zzdia) this.zzhkb).zzau(zzdotVar);
            return this;
        }

        public final zza zzbe(zzdot zzdotVar) {
            zzazi();
            ((zzdia) this.zzhkb).zzav(zzdotVar);
            return this;
        }

        /* synthetic */ zza(zzdib zzdibVar) {
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

    public final zzdhw zzasj() {
        zzdhw zzdhwVar = this.zzgvm;
        return zzdhwVar == null ? zzdhw.zzaso() : zzdhwVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(zzdhw zzdhwVar) {
        if (zzdhwVar == null) {
            throw new NullPointerException();
        }
        this.zzgvm = zzdhwVar;
    }

    public final zzdot zzasb() {
        return this.zzgvc;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzau(zzdot zzdotVar) {
        if (zzdotVar == null) {
            throw new NullPointerException();
        }
        this.zzgvc = zzdotVar;
    }

    public final zzdot zzasc() {
        return this.zzgvd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzav(zzdot zzdotVar) {
        if (zzdotVar == null) {
            throw new NullPointerException();
        }
        this.zzgvd = zzdotVar;
    }

    public static zzdia zzbb(zzdot zzdotVar) throws zzdqn {
        return (zzdia) zzdqd.zza(zzgvu, zzdotVar);
    }

    public static zza zzast() {
        return zzgvu.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdib zzdibVar = null;
        switch (zzdib.zzdi[i - 1]) {
            case 1:
                return new zzdia();
            case 2:
                return new zza(zzdibVar);
            case 3:
                return zza(zzgvu, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", new Object[]{"zzgtf", "zzgvm", "zzgvc", "zzgvd"});
            case 4:
                return zzgvu;
            case 5:
                zzdrw<zzdia> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdia.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgvu);
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

    public static zzdia zzasu() {
        return zzgvu;
    }

    static {
        zzdia zzdiaVar = new zzdia();
        zzgvu = zzdiaVar;
        zzdqd.zza((Class<zzdia>) zzdia.class, zzdiaVar);
    }
}
