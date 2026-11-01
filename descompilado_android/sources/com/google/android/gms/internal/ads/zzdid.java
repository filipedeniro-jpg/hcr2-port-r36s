package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdid extends zzdqd<zzdid, zza> implements zzdrp {
    private static volatile zzdrw<zzdid> zzdv;
    private static final zzdid zzgvx;
    private int zzgtt;
    private int zzgvv;
    private zzdot zzgvw = zzdot.zzhfn;

    private zzdid() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdid, zza> implements zzdrp {
        private zza() {
            super(zzdid.zzgvx);
        }

        /* synthetic */ zza(zzdic zzdicVar) {
            this();
        }
    }

    public final zzdii zzasw() {
        zzdii zzdiiVarZzek = zzdii.zzek(this.zzgvv);
        return zzdiiVarZzek == null ? zzdii.UNRECOGNIZED : zzdiiVarZzek;
    }

    public final zzdin zzaqk() {
        zzdin zzdinVarZzem = zzdin.zzem(this.zzgtt);
        return zzdinVarZzem == null ? zzdin.UNRECOGNIZED : zzdinVarZzem;
    }

    public final zzdot zzasx() {
        return this.zzgvw;
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdic zzdicVar = null;
        switch (zzdic.zzdi[i - 1]) {
            case 1:
                return new zzdid();
            case 2:
                return new zza(zzdicVar);
            case 3:
                return zza(zzgvx, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zzgvv", "zzgtt", "zzgvw"});
            case 4:
                return zzgvx;
            case 5:
                zzdrw<zzdid> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdid.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgvx);
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

    public static zzdid zzasy() {
        return zzgvx;
    }

    static {
        zzdid zzdidVar = new zzdid();
        zzgvx = zzdidVar;
        zzdqd.zza((Class<zzdid>) zzdid.class, zzdidVar);
    }
}
