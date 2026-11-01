package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public class zzauf extends zzauc {
    @Override // com.google.android.gms.internal.ads.zzaub
    public final int zzvm() {
        return 14;
    }

    @Override // com.google.android.gms.internal.ads.zzaub
    public boolean isAttachedToWindow(View view) {
        return super.isAttachedToWindow(view) || view.getWindowId() != null;
    }

    @Override // com.google.android.gms.internal.ads.zzaub
    public final long zzvq() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcnl)).booleanValue()) {
            return new StatFs(Environment.getDataDirectory().getAbsolutePath()).getAvailableBytes() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return -1L;
    }
}
