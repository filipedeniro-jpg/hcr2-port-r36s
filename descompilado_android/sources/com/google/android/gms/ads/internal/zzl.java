package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzl implements View.OnTouchListener {
    private final /* synthetic */ zzj zzblp;

    zzl(zzj zzjVar) {
        this.zzblp = zzjVar;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zzblp.zzbln == null) {
            return false;
        }
        this.zzblp.zzbln.zzb(motionEvent);
        return false;
    }
}
