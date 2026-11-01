package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdfw extends zzdqd<zzdfw, zza> implements zzdrp {
    private static volatile zzdrw<zzdfw> zzdv;
    private static final zzdfw zzgtl;
    private zzdgh zzgtj;
    private zzdiq zzgtk;

    private zzdfw() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdfw, zza> implements zzdrp {
        private zza() {
            super(zzdfw.zzgtl);
        }

        /* synthetic */ zza(zzdfx zzdfxVar) {
            this();
        }
    }

    public final zzdgh zzaqa() {
        zzdgh zzdghVar = this.zzgtj;
        return zzdghVar == null ? zzdgh.zzaqs() : zzdghVar;
    }

    public final zzdiq zzaqb() {
        zzdiq zzdiqVar = this.zzgtk;
        return zzdiqVar == null ? zzdiq.zzatj() : zzdiqVar;
    }

    public static zzdfw zzv(zzdot zzdotVar) throws zzdqn {
        return (zzdfw) zzdqd.zza(zzgtl, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdfx zzdfxVar = null;
        switch (zzdfx.zzdi[i - 1]) {
            case 1:
                return new zzdfw();
            case 2:
                return new zza(zzdfxVar);
            case 3:
                return zza(zzgtl, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zzgtj", "zzgtk"});
            case 4:
                return zzgtl;
            case 5:
                zzdrw<zzdfw> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdfw.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgtl);
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
        zzdfw zzdfwVar = new zzdfw();
        zzgtl = zzdfwVar;
        zzdqd.zza((Class<zzdfw>) zzdfw.class, zzdfwVar);
    }
}
