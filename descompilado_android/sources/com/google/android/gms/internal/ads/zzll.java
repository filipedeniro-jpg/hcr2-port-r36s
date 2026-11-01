package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzll implements Runnable {
    private final /* synthetic */ zzlg zzbaq;
    private final /* synthetic */ zzlm zzbas;

    zzll(zzlg zzlgVar, zzlm zzlmVar) {
        this.zzbaq = zzlgVar;
        this.zzbas = zzlmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbas.release();
        int size = this.zzbaq.zzbaa.size();
        for (int i = 0; i < size; i++) {
            ((zzma) this.zzbaq.zzbaa.valueAt(i)).disable();
        }
    }
}
