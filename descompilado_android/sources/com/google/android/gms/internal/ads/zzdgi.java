package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdgi extends zzdqd<zzdgi, zza> implements zzdrp {
    private static volatile zzdrw<zzdgi> zzdv;
    private static final zzdgi zzgua;
    private int zzgtz;

    private zzdgi() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdgi, zza> implements zzdrp {
        private zza() {
            super(zzdgi.zzgua);
        }

        /* synthetic */ zza(zzdgj zzdgjVar) {
            this();
        }
    }

    public final int zzaqu() {
        return this.zzgtz;
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdgj zzdgjVar = null;
        switch (zzdgj.zzdi[i - 1]) {
            case 1:
                return new zzdgi();
            case 2:
                return new zza(zzdgjVar);
            case 3:
                return zza(zzgua, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzgtz"});
            case 4:
                return zzgua;
            case 5:
                zzdrw<zzdgi> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdgi.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgua);
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

    public static zzdgi zzaqv() {
        return zzgua;
    }

    static {
        zzdgi zzdgiVar = new zzdgi();
        zzgua = zzdgiVar;
        zzdqd.zza((Class<zzdgi>) zzdgi.class, zzdgiVar);
    }
}
