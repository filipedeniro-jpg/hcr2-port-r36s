package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbct {
    public final int heightPixels;
    private final int type;
    public final int widthPixels;

    public static zzbct zzb(zztw zztwVar) {
        if (zztwVar.zzcce) {
            return new zzbct(3, 0, 0);
        }
        if (zztwVar.zzccg) {
            return new zzbct(2, 0, 0);
        }
        return zztwVar.zzbnj ? zzaan() : zzp(zztwVar.widthPixels, zztwVar.heightPixels);
    }

    public static zzbct zzaan() {
        return new zzbct(0, 0, 0);
    }

    public static zzbct zzp(int i, int i2) {
        return new zzbct(1, i, i2);
    }

    public static zzbct zzaao() {
        return new zzbct(4, 0, 0);
    }

    public static zzbct zzaap() {
        return new zzbct(5, 0, 0);
    }

    private zzbct(int i, int i2, int i3) {
        this.type = i;
        this.widthPixels = i2;
        this.heightPixels = i3;
    }

    public final boolean isFluid() {
        return this.type == 2;
    }

    public final boolean zzaaq() {
        return this.type == 3;
    }

    public final boolean zzaar() {
        return this.type == 0;
    }

    public final boolean zzaas() {
        return this.type == 4;
    }

    public final boolean zzaat() {
        return this.type == 5;
    }
}
