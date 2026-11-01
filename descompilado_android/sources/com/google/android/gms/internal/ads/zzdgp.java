package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdgp extends zzdqd<zzdgp, zza> implements zzdrp {
    private static volatile zzdrw<zzdgp> zzdv;
    private static final zzdgp zzgue;
    private int zzgtz;

    private zzdgp() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdgp, zza> implements zzdrp {
        private zza() {
            super(zzdgp.zzgue);
        }

        /* synthetic */ zza(zzdgo zzdgoVar) {
            this();
        }
    }

    public final int zzaqu() {
        return this.zzgtz;
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdgo zzdgoVar = null;
        switch (zzdgo.zzdi[i - 1]) {
            case 1:
                return new zzdgp();
            case 2:
                return new zza(zzdgoVar);
            case 3:
                return zza(zzgue, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzgtz"});
            case 4:
                return zzgue;
            case 5:
                zzdrw<zzdgp> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdgp.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgue);
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

    public static zzdgp zzarb() {
        return zzgue;
    }

    static {
        zzdgp zzdgpVar = new zzdgp();
        zzgue = zzdgpVar;
        zzdqd.zza((Class<zzdgp>) zzdgp.class, zzdgpVar);
    }
}
