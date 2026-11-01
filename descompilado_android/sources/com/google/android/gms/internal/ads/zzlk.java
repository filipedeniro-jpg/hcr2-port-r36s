package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzlk implements Runnable {
    private final /* synthetic */ zzlg zzbaq;
    private final /* synthetic */ IOException zzbar;

    zzlk(zzlg zzlgVar, IOException iOException) {
        this.zzbaq = zzlgVar;
        this.zzbar = iOException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbaq.zzazq.zzb(this.zzbar);
    }
}
