package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdgh extends zzdqd<zzdgh, zza> implements zzdrp {
    private static volatile zzdrw<zzdgh> zzdv;
    private static final zzdgh zzgty;
    private int zzgtp;
    private zzdgi zzgtw;

    private zzdgh() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdgh, zza> implements zzdrp {
        private zza() {
            super(zzdgh.zzgty);
        }

        /* synthetic */ zza(zzdgg zzdggVar) {
            this();
        }
    }

    public final zzdgi zzaqo() {
        zzdgi zzdgiVar = this.zzgtw;
        return zzdgiVar == null ? zzdgi.zzaqv() : zzdgiVar;
    }

    public final int getKeySize() {
        return this.zzgtp;
    }

    public static zzdgh zzac(zzdot zzdotVar) throws zzdqn {
        return (zzdgh) zzdqd.zza(zzgty, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdgg zzdggVar = null;
        switch (zzdgg.zzdi[i - 1]) {
            case 1:
                return new zzdgh();
            case 2:
                return new zza(zzdggVar);
            case 3:
                return zza(zzgty, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zzgtw", "zzgtp"});
            case 4:
                return zzgty;
            case 5:
                zzdrw<zzdgh> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdgh.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgty);
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

    public static zzdgh zzaqs() {
        return zzgty;
    }

    static {
        zzdgh zzdghVar = new zzdgh();
        zzgty = zzdghVar;
        zzdqd.zza((Class<zzdgh>) zzdgh.class, zzdghVar);
    }
}
