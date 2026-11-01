package com.inmobi.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class NativeVideoWrapper extends RelativeLayout {
    private static final String b = "NativeVideoWrapper";
    NativeVideoView a;
    private ImageView c;
    private ProgressBar d;
    private NativeVideoController e;
    private bg f;

    public NativeVideoWrapper(Context context) {
        super(context);
        this.a = new NativeVideoView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.a, layoutParams);
        this.c = new ImageView(getContext());
        this.c.setScaleType(ImageView.ScaleType.FIT_XY);
        this.c.setVisibility(8);
        addView(this.c, layoutParams);
        this.d = new ProgressBar(getContext());
        this.d.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        addView(this.d, layoutParams2);
        this.e = new NativeVideoController(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.addRule(13);
        this.a.setMediaController(this.e);
        addView(this.e, layoutParams3);
    }

    public void setPosterImage(@NonNull Bitmap bitmap) {
        this.c.setImageBitmap(bitmap);
    }

    @NonNull
    public NativeVideoView getVideoView() {
        return this.a;
    }

    @NonNull
    public ImageView getPoster() {
        return this.c;
    }

    @NonNull
    public ProgressBar getProgressBar() {
        return this.d;
    }

    public NativeVideoController getVideoController() {
        return this.e;
    }

    public void setVideoEventListener(bg bgVar) {
        this.f = bgVar;
    }
}
