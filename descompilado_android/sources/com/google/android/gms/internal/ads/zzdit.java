package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdit extends zzdqd<zzdit, zza> implements zzdrp {
    private static volatile zzdrw<zzdit> zzdv;
    private static final zzdit zzgwu;
    private int zzgws;
    private int zzgwt;

    private zzdit() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdit, zza> implements zzdrp {
        private zza() {
            super(zzdit.zzgwu);
        }

        /* synthetic */ zza(zzdis zzdisVar) {
            this();
        }
    }

    public final zzdin zzatl() {
        zzdin zzdinVarZzem = zzdin.zzem(this.zzgws);
        return zzdinVarZzem == null ? zzdin.UNRECOGNIZED : zzdinVarZzem;
    }

    public final int zzatm() {
        return this.zzgwt;
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdis zzdisVar = null;
        switch (zzdis.zzdi[i - 1]) {
            case 1:
                return new zzdit();
            case 2:
                return new zza(zzdisVar);
            case 3:
                return zza(zzgwu, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zzgws", "zzgwt"});
            case 4:
                return zzgwu;
            case 5:
                zzdrw<zzdit> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdit.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgwu);
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

    public static zzdit zzatn() {
        return zzgwu;
    }

    static {
        zzdit zzditVar = new zzdit();
        zzgwu = zzditVar;
        zzdqd.zza((Class<zzdit>) zzdit.class, zzditVar);
    }
}
