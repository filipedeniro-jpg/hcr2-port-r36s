package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.chartboost.sdk.Libraries.CBLogging;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class at extends SurfaceView implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, av.a {
    private final String a;
    private Uri b;
    private Map<String, String> c;
    private int d;
    private int e;
    private int f;
    private SurfaceHolder g;
    private MediaPlayer h;
    private int i;
    private int j;
    private int k;
    private int l;
    private MediaPlayer.OnCompletionListener m;
    private MediaPlayer.OnPreparedListener n;
    private int o;
    private MediaPlayer.OnErrorListener p;
    private int q;

    @Override // com.chartboost.sdk.impl.av.a
    public void a(int i, int i2) {
    }

    public at(Context context) {
        super(context);
        this.a = "VideoSurfaceView";
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = null;
        f();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int defaultSize = getDefaultSize(0, i);
        int defaultSize2 = getDefaultSize(0, i2);
        int i4 = this.i;
        if (i4 > 0 && (i3 = this.j) > 0) {
            int iMin = Math.min(defaultSize2, Math.round((i3 / i4) * defaultSize));
            defaultSize = Math.min(defaultSize, Math.round((this.i / this.j) * defaultSize2));
            defaultSize2 = iMin;
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    private void f() {
        this.i = 0;
        this.j = 0;
        getHolder().addCallback(this);
        getHolder().setType(3);
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

    public void a(Uri uri, Map<String, String> map) {
        this.b = uri;
        this.c = map;
        this.q = 0;
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
        try {
            this.h = new MediaPlayer();
            this.h.setOnPreparedListener(this);
            this.h.setOnVideoSizeChangedListener(this);
            this.d = -1;
            this.h.setOnCompletionListener(this);
            this.h.setOnErrorListener(this);
            this.h.setOnBufferingUpdateListener(this);
            this.o = 0;
            this.h.setDisplay(this.g);
            this.h.setAudioStreamType(3);
            this.h.setScreenOnWhilePlaying(true);
            FileInputStream fileInputStream = new FileInputStream(new File(this.b.toString()));
            this.h.setDataSource(fileInputStream.getFD());
            fileInputStream.close();
            this.h.prepareAsync();
            this.e = 1;
        } catch (IOException e) {
            CBLogging.c("VideoSurfaceView", "Unable to open content: " + this.b, e);
            this.e = -1;
            this.f = -1;
            onError(this.h, 1, 0);
        } catch (IllegalArgumentException e2) {
            CBLogging.c("VideoSurfaceView", "Unable to open content: " + this.b, e2);
            this.e = -1;
            this.f = -1;
            onError(this.h, 1, 0);
        }
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        this.i = mediaPlayer.getVideoWidth();
        this.j = mediaPlayer.getVideoHeight();
        if (this.i == 0 || this.j == 0) {
            return;
        }
        getHolder().setFixedSize(this.i, this.j);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.e = 2;
        this.i = mediaPlayer.getVideoWidth();
        this.j = mediaPlayer.getVideoHeight();
        MediaPlayer.OnPreparedListener onPreparedListener = this.n;
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(this.h);
        }
        int i = this.q;
        if (i != 0) {
            a(i);
        }
        if (this.i != 0 && this.j != 0) {
            getHolder().setFixedSize(this.i, this.j);
            if (this.k == this.i && this.l == this.j && this.f == 3) {
                a();
                return;
            }
            return;
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
            MediaPlayer.OnCompletionListener onCompletionListener = this.m;
            if (onCompletionListener != null) {
                onCompletionListener.onCompletion(this.h);
            }
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        CBLogging.a("VideoSurfaceView", "Error: " + i + "," + i2);
        this.e = -1;
        this.f = -1;
        MediaPlayer.OnErrorListener onErrorListener = this.p;
        if (onErrorListener == null || onErrorListener.onError(this.h, i, i2)) {
        }
        return true;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.o = i;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.n = onPreparedListener;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.m = onCompletionListener;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(MediaPlayer.OnErrorListener onErrorListener) {
        this.p = onErrorListener;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.k = i2;
        this.l = i3;
        boolean z = this.f == 3;
        boolean z2 = this.i == i2 && this.j == i3;
        if (this.h != null && z && z2) {
            int i4 = this.q;
            if (i4 != 0) {
                a(i4);
            }
            a();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.g = surfaceHolder;
        g();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.g = null;
        a(true);
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
        if (h()) {
            this.h.start();
            this.e = 3;
        }
        this.f = 3;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void b() {
        if (h() && this.h.isPlaying()) {
            this.h.pause();
            this.e = 4;
        }
        this.f = 4;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public int c() {
        if (h()) {
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
        if (h()) {
            return this.h.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.chartboost.sdk.impl.av.a
    public void a(int i) {
        if (h()) {
            this.h.seekTo(i);
            this.q = 0;
        } else {
            this.q = i;
        }
    }

    @Override // com.chartboost.sdk.impl.av.a
    public boolean e() {
        return h() && this.h.isPlaying();
    }

    private boolean h() {
        int i;
        return (this.h == null || (i = this.e) == -1 || i == 0 || i == 1) ? false : true;
    }
}
