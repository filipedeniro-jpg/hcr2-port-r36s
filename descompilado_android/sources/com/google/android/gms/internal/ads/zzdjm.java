package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdjm extends zzdqd<zzdjm, zza> implements zzdrp {
    private static volatile zzdrw<zzdjm> zzdv;
    private static final zzdjm zzgyi;
    private int zzgtf;
    private zzdjn zzgyh;

    private zzdjm() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdjm, zza> implements zzdrp {
        private zza() {
            super(zzdjm.zzgyi);
        }

        public final zza zzez(int i) {
            zzazi();
            ((zzdjm) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzb(zzdjn zzdjnVar) {
            zzazi();
            ((zzdjm) this.zzhkb).zza(zzdjnVar);
            return this;
        }

        /* synthetic */ zza(zzdjl zzdjlVar) {
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

    public final zzdjn zzaux() {
        zzdjn zzdjnVar = this.zzgyh;
        return zzdjnVar == null ? zzdjn.zzavc() : zzdjnVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdjn zzdjnVar) {
        if (zzdjnVar == null) {
            throw new NullPointerException();
        }
        this.zzgyh = zzdjnVar;
    }

    public static zzdjm zzbr(zzdot zzdotVar) throws zzdqn {
        return (zzdjm) zzdqd.zza(zzgyi, zzdotVar);
    }

    public static zza zzauy() {
        return zzgyi.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdjl zzdjlVar = null;
        switch (zzdjl.zzdi[i - 1]) {
            case 1:
                return new zzdjm();
            case 2:
                return new zza(zzdjlVar);
            case 3:
                return zza(zzgyi, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzgtf", "zzgyh"});
            case 4:
                return zzgyi;
            case 5:
                zzdrw<zzdjm> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdjm.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgyi);
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
        zzdjm zzdjmVar = new zzdjm();
        zzgyi = zzdjmVar;
        zzdqd.zza((Class<zzdjm>) zzdjm.class, zzdjmVar);
    }
}
