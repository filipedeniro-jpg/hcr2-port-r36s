package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfd extends zzkk<zzfd, zza> implements zzls {
    private static volatile zzmb<zzfd> zzhk;
    private static final zzfd zzhq = new zzfd();
    private int zzhd;
    private byte zzhi = 2;
    private long zzhn = -1;
    private long zzhg = -1;

    private zzfd() {
    }

    public static final class zza extends zzkk.zza<zzfd, zza> implements zzls {
        private zza() {
            super(zzfd.zzhq);
        }

        public final zza zzi(long j) {
            zzdb();
            ((zzfd) this.zzru).zzf(j);
            return this;
        }

        public final zza zzj(long j) {
            zzdb();
            ((zzfd) this.zzru).zza(j);
            return this;
        }

        /* synthetic */ zza(zzfe zzfeVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzf(long j) {
        this.zzhd |= 1;
        this.zzhn = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(long j) {
        this.zzhd |= 2;
        this.zzhg = j;
    }

    public static zza zzap() {
        return zzhq.zzcw();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [com.google.android.gms.internal.drive.zzkk$zzb, com.google.android.gms.internal.drive.zzmb<com.google.android.gms.internal.drive.zzfd>] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    @Override // com.google.android.gms.internal.drive.zzkk
    protected final Object zza(int i, Object obj, Object obj2) {
        ?? r3;
        zzfe zzfeVar = null;
        switch (zzfe.zzhl[i - 1]) {
            case 1:
                return new zzfd();
            case 2:
                return new zza(zzfeVar);
            case 3:
                return zza(zzhq, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001Ԑ\u0000\u0002Ԑ\u0001", new Object[]{"zzhd", "zzhn", "zzhg"});
            case 4:
                return zzhq;
            case 5:
                zzmb<zzfd> zzmbVar = zzhk;
                Object obj3 = zzmbVar;
                if (zzmbVar == null) {
                    synchronized (zzfd.class) {
                        zzmb<zzfd> zzmbVar2 = zzhk;
                        r3 = zzmbVar2;
                        if (zzmbVar2 == null) {
                            ?? zzbVar = new zzkk.zzb(zzhq);
                            zzhk = zzbVar;
                            r3 = zzbVar;
                        }
                        break;
                    }
                    obj3 = r3;
                }
                return obj3;
            case 6:
                return Byte.valueOf(this.zzhi);
            case 7:
                this.zzhi = (byte) (obj != null ? 1 : 0);
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        zzkk.zza((Class<zzfd>) zzfd.class, zzhq);
    }
}
