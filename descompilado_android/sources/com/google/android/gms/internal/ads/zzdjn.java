package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdjn extends zzdqd<zzdjn, zza> implements zzdrp {
    private static volatile zzdrw<zzdjn> zzdv;
    private static final zzdjn zzgyl;
    private String zzgyj = "";
    private zzdja zzgyk;

    private zzdjn() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdjn, zza> implements zzdrp {
        private zza() {
            super(zzdjn.zzgyl);
        }

        /* synthetic */ zza(zzdjo zzdjoVar) {
            this();
        }
    }

    public final String zzava() {
        return this.zzgyj;
    }

    public final zzdja zzavb() {
        zzdja zzdjaVar = this.zzgyk;
        return zzdjaVar == null ? zzdja.zzatv() : zzdjaVar;
    }

    public static zzdjn zzbs(zzdot zzdotVar) throws zzdqn {
        return (zzdjn) zzdqd.zza(zzgyl, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdjo zzdjoVar = null;
        switch (zzdjo.zzdi[i - 1]) {
            case 1:
                return new zzdjn();
            case 2:
                return new zza(zzdjoVar);
            case 3:
                return zza(zzgyl, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zzgyj", "zzgyk"});
            case 4:
                return zzgyl;
            case 5:
                zzdrw<zzdjn> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdjn.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgyl);
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

    public static zzdjn zzavc() {
        return zzgyl;
    }

    static {
        zzdjn zzdjnVar = new zzdjn();
        zzgyl = zzdjnVar;
        zzdqd.zza((Class<zzdjn>) zzdjn.class, zzdjnVar);
    }
}
