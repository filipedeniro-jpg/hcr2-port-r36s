package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcet implements zzbml, zzbnm {
    private static final Object zzfvm = new Object();
    private static int zzfvn = 0;
    private final zzcez zzfvo;

    public zzcet(zzcez zzcezVar) {
        this.zzfvo = zzcezVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final void onAdLoaded() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcst)).booleanValue() && zzaki()) {
            this.zzfvo.zzbb(true);
            zzakh();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final void onAdFailedToLoad(int i) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcst)).booleanValue() && zzaki()) {
            this.zzfvo.zzbb(false);
            zzakh();
        }
    }

    private static void zzakh() {
        synchronized (zzfvm) {
            zzfvn++;
        }
    }

    private static boolean zzaki() {
        boolean z;
        synchronized (zzfvm) {
            z = zzfvn < ((Integer) zzuo.zzoj().zzd(zzyt.zzcsu)).intValue();
        }
        return z;
    }
}
