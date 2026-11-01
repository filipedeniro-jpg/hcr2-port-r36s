package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaun;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
final class zzh extends RelativeLayout {

    @VisibleForTesting
    private zzaun zzdhf;

    @VisibleForTesting
    boolean zzdhg;

    public zzh(Context context, String str, String str2) {
        super(context);
        this.zzdhf = new zzaun(context, str);
        this.zzdhf.zzr(str2);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.zzdhg) {
            return false;
        }
        this.zzdhf.zzd(motionEvent);
        return false;
    }
}
