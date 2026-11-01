package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdjj extends zzdqd<zzdjj, zza> implements zzdrp {
    private static volatile zzdrw<zzdjj> zzdv;
    private static final zzdjj zzgyg;
    private String zzgyf = "";

    private zzdjj() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdjj, zza> implements zzdrp {
        private zza() {
            super(zzdjj.zzgyg);
        }

        /* synthetic */ zza(zzdjk zzdjkVar) {
            this();
        }
    }

    public final String zzauu() {
        return this.zzgyf;
    }

    public static zzdjj zzbq(zzdot zzdotVar) throws zzdqn {
        return (zzdjj) zzdqd.zza(zzgyg, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdjk zzdjkVar = null;
        switch (zzdjk.zzdi[i - 1]) {
            case 1:
                return new zzdjj();
            case 2:
                return new zza(zzdjkVar);
            case 3:
                return zza(zzgyg, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zzgyf"});
            case 4:
                return zzgyg;
            case 5:
                zzdrw<zzdjj> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdjj.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgyg);
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

    public static zzdjj zzauv() {
        return zzgyg;
    }

    static {
        zzdjj zzdjjVar = new zzdjj();
        zzgyg = zzdjjVar;
        zzdqd.zza((Class<zzdjj>) zzdjj.class, zzdjjVar);
    }
}
