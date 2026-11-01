package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdhw extends zzdqd<zzdhw, zza> implements zzdrp {
    private static volatile zzdrw<zzdhw> zzdv;
    private static final zzdhw zzgvr;
    private zzdid zzgvo;
    private zzdhr zzgvp;
    private int zzgvq;

    private zzdhw() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdhw, zza> implements zzdrp {
        private zza() {
            super(zzdhw.zzgvr);
        }

        /* synthetic */ zza(zzdhx zzdhxVar) {
            this();
        }
    }

    public final zzdid zzasl() {
        zzdid zzdidVar = this.zzgvo;
        return zzdidVar == null ? zzdid.zzasy() : zzdidVar;
    }

    public final zzdhr zzasm() {
        zzdhr zzdhrVar = this.zzgvp;
        return zzdhrVar == null ? zzdhr.zzash() : zzdhrVar;
    }

    public final zzdhg zzasn() {
        zzdhg zzdhgVarZzec = zzdhg.zzec(this.zzgvq);
        return zzdhgVarZzec == null ? zzdhg.UNRECOGNIZED : zzdhgVarZzec;
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdhx zzdhxVar = null;
        switch (zzdhx.zzdi[i - 1]) {
            case 1:
                return new zzdhw();
            case 2:
                return new zza(zzdhxVar);
            case 3:
                return zza(zzgvr, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", new Object[]{"zzgvo", "zzgvp", "zzgvq"});
            case 4:
                return zzgvr;
            case 5:
                zzdrw<zzdhw> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdhw.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgvr);
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

    public static zzdhw zzaso() {
        return zzgvr;
    }

    static {
        zzdhw zzdhwVar = new zzdhw();
        zzgvr = zzdhwVar;
        zzdqd.zza((Class<zzdhw>) zzdhw.class, zzdhwVar);
    }
}
