package com.google.android.gms.internal.ads;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbcx implements View.OnAttachStateChangeListener {
    private final /* synthetic */ zzaro zzeep;
    private final /* synthetic */ zzbcs zzehn;

    zzbcx(zzbcs zzbcsVar, zzaro zzaroVar) {
        this.zzehn = zzbcsVar;
        this.zzeep = zzaroVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.zzehn.zza(view, this.zzeep, 10);
    }
}
