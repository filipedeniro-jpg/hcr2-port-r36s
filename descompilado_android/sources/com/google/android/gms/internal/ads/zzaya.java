package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.TextureView;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(14)
public abstract class zzaya extends TextureView implements zzayx {
    protected final zzayk zzdxk;
    protected final zzayu zzdxl;

    public zzaya(Context context) {
        super(context);
        this.zzdxk = new zzayk();
        this.zzdxl = new zzayu(context, this);
    }

    public abstract int getCurrentPosition();

    public abstract int getDuration();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract void pause();

    public abstract void play();

    public abstract void seekTo(int i);

    public abstract void setVideoPath(String str);

    public abstract void stop();

    public abstract void zza(float f, float f2);

    public abstract void zza(zzayb zzaybVar);

    public void zzct(int i) {
    }

    public void zzcu(int i) {
    }

    public void zzcv(int i) {
    }

    public void zzcw(int i) {
    }

    public void zzcx(int i) {
    }

    public abstract String zzwm();

    public abstract void zzwq();

    public void zzb(String str, String[] strArr) {
        setVideoPath(str);
    }
}
