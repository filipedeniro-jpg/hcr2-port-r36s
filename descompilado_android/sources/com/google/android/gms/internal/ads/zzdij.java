package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdij extends zzdqd<zzdij, zza> implements zzdrp {
    private static volatile zzdrw<zzdij> zzdv;
    private static final zzdij zzgwh;

    private zzdij() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdij, zza> implements zzdrp {
        private zza() {
            super(zzdij.zzgwh);
        }

        /* synthetic */ zza(zzdil zzdilVar) {
            this();
        }
    }

    public static zzdij zzbj(zzdot zzdotVar) throws zzdqn {
        return (zzdij) zzdqd.zza(zzgwh, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdil zzdilVar = null;
        switch (zzdil.zzdi[i - 1]) {
            case 1:
                return new zzdij();
            case 2:
                return new zza(zzdilVar);
            case 3:
                return zza(zzgwh, "\u0000\u0000", (Object[]) null);
            case 4:
                return zzgwh;
            case 5:
                zzdrw<zzdij> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdij.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgwh);
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
        zzdij zzdijVar = new zzdij();
        zzgwh = zzdijVar;
        zzdqd.zza((Class<zzdij>) zzdij.class, zzdijVar);
    }
}
