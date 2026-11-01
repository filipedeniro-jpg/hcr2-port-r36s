package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;
import com.applovin.sdk.AppLovinSdkUtils;

/* JADX INFO: loaded from: classes.dex */
class m extends FrameLayout implements TextureView.SurfaceTextureListener, q {
    private final com.applovin.impl.sdk.n a;
    private final TextureView b;
    private final MediaPlayer c;
    private final Runnable d;
    private int e;
    private int f;
    private int g;

    m(com.applovin.impl.sdk.h hVar, Context context, Runnable runnable) {
        super(context);
        this.a = hVar.w();
        this.c = new MediaPlayer();
        this.d = runnable;
        this.b = new TextureView(context);
        this.b.setLayoutParams(new FrameLayout.LayoutParams(-1, -1, 17));
        this.b.setSurfaceTextureListener(this);
        addView(this.b);
    }

    private void a() {
        AppLovinSdkUtils.runOnUiThreadDelayed(this.d, 250L);
    }

    @Override // com.applovin.impl.adview.q
    public int getCurrentPosition() {
        return this.c.getCurrentPosition();
    }

    @Override // com.applovin.impl.adview.q
    public int getDuration() {
        return this.c.getDuration();
    }

    @Override // com.applovin.impl.adview.q
    public boolean isPlaying() {
        return this.c.isPlaying();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        Surface surface = new Surface(surfaceTexture);
        try {
            this.c.setSurface(surface);
            this.c.setAudioStreamType(3);
            this.c.prepareAsync();
        } catch (Throwable th) {
            this.a.b("TextureVideoView", "Failed to prepare media player", th);
            surface.release();
            a();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // com.applovin.impl.adview.q
    public void pause() {
        this.c.pause();
    }

    @Override // com.applovin.impl.adview.q
    public void seekTo(int i) {
        this.c.seekTo(i);
    }

    @Override // com.applovin.impl.adview.q
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.c.setOnCompletionListener(onCompletionListener);
    }

    @Override // com.applovin.impl.adview.q
    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.c.setOnErrorListener(onErrorListener);
    }

    @Override // com.applovin.impl.adview.q
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.c.setOnPreparedListener(onPreparedListener);
    }

    @Override // com.applovin.impl.adview.q
    public void setVideoSize(int i, int i2) {
        int width;
        int height;
        int i3;
        int iE = com.applovin.impl.sdk.utils.n.e(getContext());
        int i4 = this.e;
        if (i4 == 0) {
            width = this.b.getWidth();
            height = this.b.getHeight();
            this.e = iE;
            this.f = width;
            this.g = height;
        } else if (iE == i4) {
            width = this.f;
            height = this.g;
        } else {
            width = this.g;
            height = this.f;
        }
        float f = i2 / i;
        float f2 = width;
        int i5 = (int) (f2 * f);
        if (height >= i5) {
            i3 = width;
        } else {
            i3 = (int) (height / f);
            i5 = height;
        }
        try {
            Matrix matrix = new Matrix();
            this.b.getTransform(matrix);
            matrix.setScale(i3 / f2, i5 / height);
            matrix.postTranslate((width - i3) / 2, (height - i5) / 2);
            this.b.setTransform(matrix);
            invalidate();
            requestLayout();
        } catch (Throwable unused) {
            this.a.d("TextureVideoView", "Failed to set video size to width: " + i + " height: " + i2);
            a();
        }
    }

    @Override // com.applovin.impl.adview.q
    public void setVideoURI(Uri uri) {
        try {
            this.c.setDataSource(uri.toString());
        } catch (Throwable th) {
            this.a.b("TextureVideoView", "Failed to set video URI: " + uri, th);
            a();
        }
    }

    @Override // com.applovin.impl.adview.q
    public void start() {
        this.c.start();
    }

    @Override // com.applovin.impl.adview.q
    public void stopPlayback() {
        this.c.stop();
    }
}
