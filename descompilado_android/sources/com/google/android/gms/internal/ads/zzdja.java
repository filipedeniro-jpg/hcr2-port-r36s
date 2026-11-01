package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdja extends zzdqd<zzdja, zza> implements zzdrp {
    private static volatile zzdrw<zzdja> zzdv;
    private static final zzdja zzgxn;
    private String zzgwv = "";
    private zzdot zzgww = zzdot.zzhfn;
    private int zzgxm;

    private zzdja() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdja, zza> implements zzdrp {
        private zza() {
            super(zzdja.zzgxn);
        }

        /* synthetic */ zza(zzdiz zzdizVar) {
            this();
        }
    }

    public final String zzatp() {
        return this.zzgwv;
    }

    public final zzdot zzatq() {
        return this.zzgww;
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdiz zzdizVar = null;
        switch (zzdiz.zzdi[i - 1]) {
            case 1:
                return new zzdja();
            case 2:
                return new zza(zzdizVar);
            case 3:
                return zza(zzgxn, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzgwv", "zzgww", "zzgxm"});
            case 4:
                return zzgxn;
            case 5:
                zzdrw<zzdja> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdja.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgxn);
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

    public static zzdja zzatv() {
        return zzgxn;
    }

    static {
        zzdja zzdjaVar = new zzdja();
        zzgxn = zzdjaVar;
        zzdqd.zza((Class<zzdja>) zzdja.class, zzdjaVar);
    }
}
