package com.google.android.gms.internal.ads;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzqa implements Runnable {
    private final /* synthetic */ View zzbpg;
    private final /* synthetic */ zzqb zzbph;

    zzqa(zzqb zzqbVar, View view) {
        this.zzbph = zzqbVar;
        this.zzbpg = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzbph.zzi(this.zzbpg);
    }
}
