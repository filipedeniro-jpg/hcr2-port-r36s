package com.google.android.gms.internal.ads;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdjs extends zzdqd<zzdjs, zza> implements zzdrp {
    private static volatile zzdrw<zzdjs> zzdv;
    private static final zzdjs zzgyv;
    private String zzgyt = "";
    private zzdqk<zzdjb> zzgyu = zzazr();

    private zzdjs() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza extends zzdqd.zza<zzdjs, zza> implements zzdrp {
        private zza() {
            super(zzdjs.zzgyv);
        }

        public final zza zzhe(String str) {
            zzazi();
            ((zzdjs) this.zzhkb).zzhd(str);
            return this;
        }

        public final zza zzb(zzdjb zzdjbVar) {
            zzazi();
            ((zzdjs) this.zzhkb).zza(zzdjbVar);
            return this;
        }

        /* synthetic */ zza(zzdjr zzdjrVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzhd(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzgyt = str;
    }

    public final List<zzdjb> zzave() {
        return this.zzgyu;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdjb zzdjbVar) {
        if (zzdjbVar == null) {
            throw new NullPointerException();
        }
        if (!this.zzgyu.zzaxd()) {
            this.zzgyu = zzdqd.zza(this.zzgyu);
        }
        this.zzgyu.add(zzdjbVar);
    }

    public static zza zzavf() {
        return zzgyv.zzazo();
    }

    @Override // com.google.android.gms.internal.ads.zzdqd
    protected final Object zza(int i, Object obj, Object obj2) {
        zzdjr zzdjrVar = null;
        switch (zzdjr.zzdi[i - 1]) {
            case 1:
                return new zzdjs();
            case 2:
                return new zza(zzdjrVar);
            case 3:
                return zza(zzgyv, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zzgyt", "zzgyu", zzdjb.class});
            case 4:
                return zzgyv;
            case 5:
                zzdrw<zzdjs> zzcVar = zzdv;
                if (zzcVar == null) {
                    synchronized (zzdjs.class) {
                        zzcVar = zzdv;
                        if (zzcVar == null) {
                            zzcVar = new zzdqd.zzc<>(zzgyv);
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
        zzdjs zzdjsVar = new zzdjs();
        zzgyv = zzdjsVar;
        zzdqd.zza((Class<zzdjs>) zzdjs.class, zzdjsVar);
    }
}
