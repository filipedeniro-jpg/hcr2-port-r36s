package com.chartboost.sdk.impl;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import com.chartboost.sdk.Libraries.CBLogging;

/* JADX INFO: loaded from: classes.dex */
public class av extends FrameLayout {
    private View a;
    private boolean b;

    public interface a {
        void a();

        void a(int i);

        void a(int i, int i2);

        void a(MediaPlayer.OnCompletionListener onCompletionListener);

        void a(MediaPlayer.OnErrorListener onErrorListener);

        void a(MediaPlayer.OnPreparedListener onPreparedListener);

        void a(Uri uri);

        void b();

        int c();

        int d();

        boolean e();
    }

    public av(Context context) {
        super(context);
        b();
    }

    private void b() {
        this.b = true;
        StringBuilder sb = new StringBuilder();
        sb.append("Choosing ");
        sb.append(this.b ? "texture" : "surface");
        sb.append(" solution for video playback");
        CBLogging.e("VideoInit", sb.toString());
        com.chartboost.sdk.g gVarA = com.chartboost.sdk.g.a();
        if (this.b) {
            this.a = (View) gVarA.a(new au(getContext()));
        } else {
            this.a = (View) gVarA.a(new at(getContext()));
        }
        this.a.setContentDescription("CBVideo");
        addView(this.a, new FrameLayout.LayoutParams(-1, -1));
        if (this.b) {
            return;
        }
        ((SurfaceView) this.a).setZOrderMediaOverlay(true);
    }

    public a a() {
        return (a) this.a;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a().a(i, i2);
    }
}
