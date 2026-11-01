package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdfv extends zzdqd<zzdfv, zza> implements zzdrp {
    private static volatile zzdrw<zzdfv> zzdv;
    private static final zzdfv zzgti;
    private int zzgtf;
    private zzdge zzgtg;
    private zzdip zzgth;

    private zzdfv() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdfv, zza> implements zzdrp {
        private zza() {
            super(zzdfv.zzgti);
        }

        public final zza zzdv(int i) {
            zzazi();
            ((zzdfv) this.zzhkb).setVersion(i);
            return this;
        }

        public final zza zzb(zzdge zzdgeVar) {
            zzazi();
            ((zzdfv) this.zzhkb).zza(zzdgeVar);
            return this;
        }

        public final zza zzb(zzdip zzdipVar) {
            zzazi();
            ((zzdfv) this.zzhkb).zza(zzdipVar);
            return this;
        }

        /* synthetic */ zza(zzdfu zzdfuVar) {
            this();
        }
    }

    public final int getVersion() {
        return this.zzgtf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVersion(int i) {
        this.zzgtf = i;
    }

    public final zzdge zzapw() {
        zzdge zzdgeVar = this.zzgtg;
        return zzdgeVar == null ? zzdge.zzaqq() : zzdgeVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdge zzdgeVar) {
        if (zzdgeVar == null) {
            throw new NullPointerException();
        }
        this.zzgtg = zzdgeVar;
    }

    public final zzdip zzapx() {
        zzdip zzdipVar = this.zzgth;
        return zzdipVar == null ? zzdip.zzath() : zzdipVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdip zzdipVar) {
        if (zzdipVar == null) {
            throw new NullPointerException();
        }
        this.zzgth = zzdipVar;
    }

    public static zzdfv zzu(zzdot zzdotVar) throws zzdqn {
        return (zzdfv) zzdqd.zza(zzgti, zzdotVar);
    }

    public static zza zzapy() {
        return zzgti.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdfu zzdfuVar = null;
        switch (zzdfu.zzdi[i - 1]) {
            case 1:
                return new zzdfv();
            case 2:
                return new zza(zzdfuVar);
            case 3:
                return zza(zzgti, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", new Object[]{"zzgtf", "zzgtg", "zzgth"});
            case 4:
                return zzgti;
            case 5:
                zzdrw<zzdfv> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdfv.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgti);
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
        zzdfv zzdfvVar = new zzdfv();
        zzgti = zzdfvVar;
        zzdqd.zza((Class<zzdfv>) zzdfv.class, zzdfvVar);
    }
}
