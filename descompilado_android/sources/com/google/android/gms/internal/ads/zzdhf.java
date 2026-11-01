package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdhf extends zzdqd<zzdhf, zza> implements zzdrp {
    private static volatile zzdrw<zzdhf> zzdv;
    private static final zzdhf zzgun;
    private int zzgtf;
    private zzdot zzgtn = zzdot.zzhfn;

    private zzdhf() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdhf, zza> implements zzdrp {
        private zza() {
            super(zzdhf.zzgun);
        }

        public final zza zzed(int i) {
            zzazi();
            ((zzdhf) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzaq(zzdot zzdotVar) {
            zzazi();
            ((zzdhf) this.zzhkb).zzw(zzdotVar);
            return this;
        }

        /* synthetic */ zza(zzdhe zzdheVar) {
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

    public final zzdot zzaqe() {
        return this.zzgtn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzw(zzdot zzdotVar) {
        if (zzdotVar == null) {
            throw new NullPointerException();
        }
        this.zzgtn = zzdotVar;
    }

    public static zzdhf zzap(zzdot zzdotVar) throws zzdqn {
        return (zzdhf) zzdqd.zza(zzgun, zzdotVar);
    }

    public static zza zzarp() {
        return zzgun.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdhe zzdheVar = null;
        switch (zzdhe.zzdi[i - 1]) {
            case 1:
                return new zzdhf();
            case 2:
                return new zza(zzdheVar);
            case 3:
                return zza(zzgun, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzgtf", "zzgtn"});
            case 4:
                return zzgun;
            case 5:
                zzdrw<zzdhf> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdhf.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgun);
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
        zzdhf zzdhfVar = new zzdhf();
        zzgun = zzdhfVar;
        zzdqd.zza((Class<zzdhf>) zzdhf.class, zzdhfVar);
    }
}
