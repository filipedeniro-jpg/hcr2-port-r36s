package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdhr extends zzdqd<zzdhr, zza> implements zzdrp {
    private static volatile zzdrw<zzdhr> zzdv;
    private static final zzdhr zzgvl;
    private zzdja zzgvk;

    private zzdhr() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdhr, zza> implements zzdrp {
        private zza() {
            super(zzdhr.zzgvl);
        }

        /* synthetic */ zza(zzdht zzdhtVar) {
            this();
        }
    }

    public final zzdja zzasg() {
        zzdja zzdjaVar = this.zzgvk;
        return zzdjaVar == null ? zzdja.zzatv() : zzdjaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdht zzdhtVar = null;
        switch (zzdht.zzdi[i - 1]) {
            case 1:
                return new zzdhr();
            case 2:
                return new zza(zzdhtVar);
            case 3:
                return zza(zzgvl, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002\t", new Object[]{"zzgvk"});
            case 4:
                return zzgvl;
            case 5:
                zzdrw<zzdhr> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdhr.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgvl);
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

    public static zzdhr zzash() {
        return zzgvl;
    }

    static {
        zzdhr zzdhrVar = new zzdhr();
        zzgvl = zzdhrVar;
        zzdqd.zza((Class<zzdhr>) zzdhr.class, zzdhrVar);
    }
}
