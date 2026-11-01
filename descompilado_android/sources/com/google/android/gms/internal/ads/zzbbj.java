package com.google.android.gms.internal.ads;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbbj implements View.OnAttachStateChangeListener {
    private final /* synthetic */ zzaro zzeep;
    private final /* synthetic */ zzbbf zzeer;

    zzbbj(zzbbf zzbbfVar, zzaro zzaroVar) {
        this.zzeer = zzbbfVar;
        this.zzeep = zzaroVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.zzeer.zza(view, this.zzeep, 10);
    }
}
