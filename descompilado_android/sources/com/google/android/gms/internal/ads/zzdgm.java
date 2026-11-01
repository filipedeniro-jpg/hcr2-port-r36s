package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdgm extends zzdqd<zzdgm, zza> implements zzdrp {
    private static volatile zzdrw<zzdgm> zzdv;
    private static final zzdgm zzgud;
    private int zzgtp;
    private zzdgp zzgub;

    private zzdgm() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdgm, zza> implements zzdrp {
        private zza() {
            super(zzdgm.zzgud);
        }

        /* synthetic */ zza(zzdgn zzdgnVar) {
            this();
        }
    }

    public final zzdgp zzaqx() {
        zzdgp zzdgpVar = this.zzgub;
        return zzdgpVar == null ? zzdgp.zzarb() : zzdgpVar;
    }

    public final int getKeySize() {
        return this.zzgtp;
    }

    public static zzdgm zzae(zzdot zzdotVar) throws zzdqn {
        return (zzdgm) zzdqd.zza(zzgud, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdgn zzdgnVar = null;
        switch (zzdgn.zzdi[i - 1]) {
            case 1:
                return new zzdgm();
            case 2:
                return new zza(zzdgnVar);
            case 3:
                return zza(zzgud, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzgub", "zzgtp"});
            case 4:
                return zzgud;
            case 5:
                zzdrw<zzdgm> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdgm.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgud);
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
        zzdgm zzdgmVar = new zzdgm();
        zzgud = zzdgmVar;
        zzdqd.zza((Class<zzdgm>) zzdgm.class, zzdgmVar);
    }
}
