package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView;
import com.chartboost.sdk.Libraries.CBLogging;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class au extends TextureView implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener, av.a {
    private final String a;
    private Uri b;
    private Map<String, String> c;
    private int d;
    private int e;
    private int f;
    private Surface g;
    private MediaPlayer h;
    private int i;
    private int j;
    private MediaPlayer.OnCompletionListener k;
    private MediaPlayer.OnPreparedListener l;
    private int m;
    private MediaPlayer.OnErrorListener n;
    private int o;

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public au(Context context) {
        super(context);
        this.a = "VideoTextureView";
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = null;
        f();
    }

    private void f() {
        this.i = 0;
        this.j = 0;
        setSurfaceTextureListener(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.e = 0;
        this.f = 0;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(Uri uri) {
        a(uri, (Map<String, String>) null);
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(int i, int i2) {
        int i3;
        int i4 = this.i;
        if (i4 == 0 || (i3 = this.j) == 0 || i == 0 || i2 == 0) {
            return;
        }
        float f = i;
        float f2 = i2;
        float fMin = Math.min(f / i4, f2 / i3);
        float f3 = this.i * fMin;
        float f4 = fMin * this.j;
        Matrix matrix = new Matrix();
        matrix.setScale(f3 / f, f4 / f2, f / 2.0f, f2 / 2.0f);
        setTransform(matrix);
    }

    public void a(Uri uri, Map<String, String> map) {
        this.b = uri;
        this.c = map;
        this.o = 0;
        g();
        requestLayout();
        invalidate();
    }

    private void g() {
        if (this.b == null || this.g == null) {
            return;
        }
        Intent intent = new Intent("com.android.music.musicservicecommand");
        intent.putExtra("command", "pause");
        getContext().sendBroadcast(intent);
        a(false);
        h();
        try {
            this.h = new MediaPlayer();
            this.h.setOnPreparedListener(this);
            this.h.setOnVideoSizeChangedListener(this);
            this.d = -1;
            this.h.setOnCompletionListener(this);
            this.h.setOnErrorListener(this);
            this.h.setOnBufferingUpdateListener(this);
            this.m = 0;
            FileInputStream fileInputStream = new FileInputStream(new File(this.b.toString()));
            this.h.setDataSource(fileInputStream.getFD());
            fileInputStream.close();
            this.h.setSurface(this.g);
            this.h.setAudioStreamType(3);
            this.h.setScreenOnWhilePlaying(true);
            this.h.prepareAsync();
            this.e = 1;
        } catch (IOException e) {
            CBLogging.c("VideoTextureView", "Unable to open content: " + this.b, e);
            this.e = -1;
            this.f = -1;
            onError(this.h, 1, 0);
        } catch (IllegalArgumentException e2) {
            CBLogging.c("VideoTextureView", "Unable to open content: " + this.b, e2);
            this.e = -1;
            this.f = -1;
            onError(this.h, 1, 0);
        }
    }

    private void h() {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            if (s.a().a(16)) {
                mediaMetadataRetriever.setDataSource(this.b.toString());
            } else {
                FileInputStream fileInputStream = new FileInputStream(this.b.toString());
                mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
            }
            String strExtractMetadata = mediaMetadataRetriever.extractMetadata(19);
            String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(18);
            this.j = Integer.parseInt(strExtractMetadata);
            this.i = Integer.parseInt(strExtractMetadata2);
        } catch (Exception e) {
            CBLogging.c("play video", "read size error", e);
        }
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        this.i = mediaPlayer.getVideoWidth();
        this.j = mediaPlayer.getVideoHeight();
        if (this.i == 0 || this.j == 0) {
            return;
        }
        a(getWidth(), getHeight());
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.e = 2;
        this.i = mediaPlayer.getVideoWidth();
        this.j = mediaPlayer.getVideoHeight();
        MediaPlayer.OnPreparedListener onPreparedListener = this.l;
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(this.h);
        }
        int i = this.o;
        if (i != 0) {
            a(i);
        }
        if (this.f == 3) {
            a();
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.f = 5;
        if (this.e != 5) {
            this.e = 5;
            MediaPlayer.OnCompletionListener onCompletionListener = this.k;
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(this.h);
            }
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        CBLogging.a("VideoTextureView", "Error: " + i + "," + i2);
        if (i == 100) {
            g();
            return true;
        }
        this.e = -1;
        this.f = -1;
        MediaPlayer.OnErrorListener onErrorListener = this.n;
        if (onErrorListener == null || onErrorListener.onError(this.h, i, i2)) {
        }
        return true;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.m = i;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.l = onPreparedListener;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.k = onCompletionListener;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(MediaPlayer.OnErrorListener onErrorListener) {
        this.n = onErrorListener;
    }

    private void a(boolean z) {
        MediaPlayer mediaPlayer = this.h;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.h.release();
            this.h = null;
            this.e = 0;
            if (z) {
                this.f = 0;
            }
        }
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a() {
        if (i()) {
            this.h.start();
            this.e = 3;
        }
        this.f = 3;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void b() {
        if (i() && this.h.isPlaying()) {
            this.h.pause();
            this.e = 4;
        }
        this.f = 4;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public int c() {
        if (i()) {
            int i = this.d;
            if (i > 0) {
                return i;
            }
            this.d = this.h.getDuration();
            return this.d;
        }
        this.d = -1;
        return this.d;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public int d() {
        if (i()) {
            return this.h.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(int i) {
        if (i()) {
            this.h.seekTo(i);
            this.o = 0;
        } else {
            this.o = i;
        }
    }

    @Override // com.chartboost.sdk.impl.av.a
    public boolean e() {
        return i() && this.h.isPlaying();
    }

    private boolean i() {
        int i;
        return (this.h == null || (i = this.e) == -1 || i == 0 || i == 1) ? false : true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.g = new Surface(surfaceTexture);
        g();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.g = null;
        a(true);
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        boolean z = this.f == 3;
        if (this.h == null || !z) {
            return;
        }
        int i3 = this.o;
        if (i3 != 0) {
            a(i3);
        }
        a();
    }
}
