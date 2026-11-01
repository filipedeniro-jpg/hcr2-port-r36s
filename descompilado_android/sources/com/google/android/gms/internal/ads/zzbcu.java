package com.google.android.gms.internal.ads;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbcu implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzaro zzeep;
    private final /* synthetic */ int zzeeq;
    private final /* synthetic */ zzbcs zzehn;

    zzbcu(zzbcs zzbcsVar, View view, zzaro zzaroVar, int i) {
        this.zzehn = zzbcsVar;
        this.val$view = view;
        this.zzeep = zzaroVar;
        this.zzeeq = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzehn.zza(this.val$view, this.zzeep, this.zzeeq - 1);
    }
}
