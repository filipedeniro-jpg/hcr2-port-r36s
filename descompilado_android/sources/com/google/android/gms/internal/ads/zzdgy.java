package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdgy extends zzdqd<zzdgy, zza> implements zzdrp {
    private static volatile zzdrw<zzdgy> zzdv;
    private static final zzdgy zzguk;
    private int zzgtp;

    private zzdgy() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdgy, zza> implements zzdrp {
        private zza() {
            super(zzdgy.zzguk);
        }

        /* synthetic */ zza(zzdgz zzdgzVar) {
            this();
        }
    }

    public final int getKeySize() {
        return this.zzgtp;
    }

    public static zzdgy zzak(zzdot zzdotVar) throws zzdqn {
        return (zzdgy) zzdqd.zza(zzguk, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdgz zzdgzVar = null;
        switch (zzdgz.zzdi[i - 1]) {
            case 1:
                return new zzdgy();
            case 2:
                return new zza(zzdgzVar);
            case 3:
                return zza(zzguk, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\u000b", new Object[]{"zzgtp"});
            case 4:
                return zzguk;
            case 5:
                zzdrw<zzdgy> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdgy.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzguk);
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
        zzdgy zzdgyVar = new zzdgy();
        zzguk = zzdgyVar;
        zzdqd.zza((Class<zzdgy>) zzdgy.class, zzdgyVar);
    }
}
