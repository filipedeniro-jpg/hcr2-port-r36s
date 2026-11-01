package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdji extends zzdqd<zzdji, zza> implements zzdrp {
    private static volatile zzdrw<zzdji> zzdv;
    private static final zzdji zzgye;
    private int zzgtf;
    private zzdjj zzgyd;

    private zzdji() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdji, zza> implements zzdrp {
        private zza() {
            super(zzdji.zzgye);
        }

        public final zza zzey(int i) {
            zzazi();
            ((zzdji) this.zzhkb).setVersion(0);
            return this;
        }

        public final zza zzb(zzdjj zzdjjVar) {
            zzazi();
            ((zzdji) this.zzhkb).zza(zzdjjVar);
            return this;
        }

        /* synthetic */ zza(zzdjh zzdjhVar) {
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

    public final zzdjj zzaur() {
        zzdjj zzdjjVar = this.zzgyd;
        return zzdjjVar == null ? zzdjj.zzauv() : zzdjjVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdjj zzdjjVar) {
        if (zzdjjVar == null) {
            throw new NullPointerException();
        }
        this.zzgyd = zzdjjVar;
    }

    public static zzdji zzbp(zzdot zzdotVar) throws zzdqn {
        return (zzdji) zzdqd.zza(zzgye, zzdotVar);
    }

    public static zza zzaus() {
        return zzgye.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdjh zzdjhVar = null;
        switch (zzdjh.zzdi[i - 1]) {
            case 1:
                return new zzdji();
            case 2:
                return new zza(zzdjhVar);
            case 3:
                return zza(zzgye, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzgtf", "zzgyd"});
            case 4:
                return zzgye;
            case 5:
                zzdrw<zzdji> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdji.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgye);
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
        zzdji zzdjiVar = new zzdji();
        zzgye = zzdjiVar;
        zzdqd.zza((Class<zzdji>) zzdji.class, zzdjiVar);
    }
}
