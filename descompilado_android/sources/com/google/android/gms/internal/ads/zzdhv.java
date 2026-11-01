package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdhv extends zzdqd<zzdhv, zza> implements zzdrp {
    private static volatile zzdrw<zzdhv> zzdv;
    private static final zzdhv zzgvn;
    private zzdhw zzgvm;

    private zzdhv() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdhv, zza> implements zzdrp {
        private zza() {
            super(zzdhv.zzgvn);
        }

        /* synthetic */ zza(zzdhu zzdhuVar) {
            this();
        }
    }

    public final zzdhw zzasj() {
        zzdhw zzdhwVar = this.zzgvm;
        return zzdhwVar == null ? zzdhw.zzaso() : zzdhwVar;
    }

    public static zzdhv zzaz(zzdot zzdotVar) throws zzdqn {
        return (zzdhv) zzdqd.zza(zzgvn, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdhu zzdhuVar = null;
        switch (zzdhu.zzdi[i - 1]) {
            case 1:
                return new zzdhv();
            case 2:
                return new zza(zzdhuVar);
            case 3:
                return zza(zzgvn, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zzgvm"});
            case 4:
                return zzgvn;
            case 5:
                zzdrw<zzdhv> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdhv.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgvn);
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
        zzdhv zzdhvVar = new zzdhv();
        zzgvn = zzdhvVar;
        zzdqd.zza((Class<zzdhv>) zzdhv.class, zzdhvVar);
    }
}
