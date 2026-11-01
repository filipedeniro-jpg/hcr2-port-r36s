package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdiq extends zzdqd<zzdiq, zza> implements zzdrp {
    private static volatile zzdrw<zzdiq> zzdv;
    private static final zzdiq zzgwr;
    private int zzgtp;
    private zzdit zzgwp;

    private zzdiq() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdiq, zza> implements zzdrp {
        private zza() {
            super(zzdiq.zzgwr);
        }

        /* synthetic */ zza(zzdir zzdirVar) {
            this();
        }
    }

    public final zzdit zzatf() {
        zzdit zzditVar = this.zzgwp;
        return zzditVar == null ? zzdit.zzatn() : zzditVar;
    }

    public final int getKeySize() {
        return this.zzgtp;
    }

    public static zzdiq zzbl(zzdot zzdotVar) throws zzdqn {
        return (zzdiq) zzdqd.zza(zzgwr, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdir zzdirVar = null;
        switch (zzdir.zzdi[i - 1]) {
            case 1:
                return new zzdiq();
            case 2:
                return new zza(zzdirVar);
            case 3:
                return zza(zzgwr, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzgwp", "zzgtp"});
            case 4:
                return zzgwr;
            case 5:
                zzdrw<zzdiq> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdiq.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgwr);
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

    public static zzdiq zzatj() {
        return zzgwr;
    }

    static {
        zzdiq zzdiqVar = new zzdiq();
        zzgwr = zzdiqVar;
        zzdqd.zza((Class<zzdiq>) zzdiq.class, zzdiqVar);
    }
}
