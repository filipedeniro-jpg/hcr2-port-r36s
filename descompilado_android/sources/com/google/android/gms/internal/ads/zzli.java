package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzli implements Runnable {
    private final /* synthetic */ zzlg zzbaq;

    zzli(zzlg zzlgVar) {
        this.zzbaq = zzlgVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzbaq.zzads) {
            return;
        }
        this.zzbaq.zzbab.zza(this.zzbaq);
    }
}
