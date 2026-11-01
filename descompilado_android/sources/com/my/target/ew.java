package com.my.target;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.facebook.internal.AnalyticsEvents;
import com.my.target.common.models.VideoData;

/* JADX INFO: compiled from: DefaultVideoPlayer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ew implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, TextureView.SurfaceTextureListener, co {

    @Nullable
    private VideoData cb;

    @NonNull
    private final MediaPlayer dZ;

    @Nullable
    private co.a ea;

    @Nullable
    private TextureView eb;

    @Nullable
    private Surface ec;
    private int ee;

    @NonNull
    private final ci dX = ci.i(200);

    @NonNull
    private final a dY = new a();
    private int state = 0;
    private float ed = 1.0f;

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @NonNull
    public static co X() {
        return new ew(new MediaPlayer());
    }

    @Override // com.my.target.co
    @Nullable
    public final VideoData bs() {
        return this.cb;
    }

    @Override // com.my.target.co
    public final boolean isMuted() {
        return this.ed == 0.0f;
    }

    @Override // com.my.target.co
    public final boolean isStarted() {
        int i = this.state;
        return i > 0 && i < 3;
    }

    @Override // com.my.target.co
    public final void a(@Nullable co.a aVar) {
        this.ea = aVar;
    }

    @Override // com.my.target.co
    public final boolean isPaused() {
        return this.state == 2;
    }

    private ew(@NonNull MediaPlayer mediaPlayer) {
        this.dZ = mediaPlayer;
    }

    @Override // com.my.target.co
    public final long getPosition() {
        if (Z()) {
            return this.dZ.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.my.target.co
    public final void seekTo(long j) {
        if (Z()) {
            try {
                this.dZ.seekTo((int) j);
            } catch (IllegalStateException unused) {
                g.a("seekTo called in wrong state");
            }
        }
    }

    @Override // com.my.target.co
    public final float getDuration() {
        if (Z()) {
            return this.dZ.getDuration() / 1000.0f;
        }
        return 0.0f;
    }

    @Override // com.my.target.co
    public final void a(@NonNull VideoData videoData, @NonNull TextureView textureView) {
        Uri uri;
        String data = videoData.getData();
        if (data != null) {
            uri = Uri.parse(data);
        } else {
            uri = Uri.parse(videoData.getUrl());
        }
        this.cb = videoData;
        a(uri, textureView);
    }

    @Override // com.my.target.co
    public final void stop() {
        this.dX.e(this.dY);
        try {
            this.dZ.stop();
        } catch (IllegalStateException unused) {
            g.a("stop called in wrong state");
        }
        co.a aVar = this.ea;
        if (aVar != null) {
            aVar.bw();
        }
        this.state = 3;
    }

    @Override // com.my.target.co
    @SuppressLint({"Recycle"})
    public final void a(@NonNull Uri uri, @NonNull TextureView textureView) {
        g.a("Play video in Android MediaPlayer");
        if (this.state != 0) {
            this.dZ.reset();
            this.state = 0;
        }
        this.dZ.setOnCompletionListener(this);
        this.dZ.setOnErrorListener(this);
        this.dZ.setOnPreparedListener(this);
        try {
            this.dZ.setDataSource(textureView.getContext(), uri);
            co.a aVar = this.ea;
            if (aVar != null) {
                aVar.bA();
            }
            Y();
            this.eb = textureView;
            if (textureView.getSurfaceTextureListener() != null) {
                Log.w("DefaultVideoPlayer", "Replacing existing SurfaceTextureListener.");
            }
            textureView.setSurfaceTextureListener(this);
            SurfaceTexture surfaceTexture = textureView.isAvailable() ? textureView.getSurfaceTexture() : null;
            a(surfaceTexture != null ? new Surface(surfaceTexture) : null);
            try {
                this.dZ.prepareAsync();
            } catch (IllegalStateException unused) {
                g.a("prepareAsync called in wrong state");
            }
        } catch (Exception e) {
            co.a aVar2 = this.ea;
            if (aVar2 != null) {
                aVar2.W(e.toString());
            }
            g.a("DefaultVideoPlayerUnable to parse video source " + e.getMessage());
            this.state = 5;
            e.printStackTrace();
        }
    }

    @Override // com.my.target.co
    public final void destroy() {
        this.state = 5;
        this.dX.e(this.dY);
        Y();
        a((Surface) null);
        if (Z()) {
            try {
                this.dZ.stop();
            } catch (IllegalStateException unused) {
                g.a("stop called in wrong state");
            }
        }
        this.dZ.release();
    }

    @Override // com.my.target.co
    public final void bt() {
        setVolume(0.2f);
    }

    @Override // com.my.target.co
    public final void bu() {
        setVolume(0.0f);
    }

    @Override // com.my.target.co
    public final void setVolume(float f) {
        this.ed = f;
        if (Z()) {
            this.dZ.setVolume(f, f);
        }
        co.a aVar = this.ea;
        if (aVar != null) {
            aVar.f(f);
        }
    }

    @Override // com.my.target.co
    public final void bv() {
        setVolume(1.0f);
    }

    @Override // com.my.target.co
    public final void pause() {
        if (this.state == 1) {
            this.ee = this.dZ.getCurrentPosition();
            this.dX.e(this.dY);
            try {
                this.dZ.pause();
            } catch (IllegalStateException unused) {
                g.a("pause called in wrong state");
            }
            this.state = 2;
            co.a aVar = this.ea;
            if (aVar != null) {
                aVar.by();
            }
        }
    }

    @Override // com.my.target.co
    public final void resume() {
        if (this.state == 2) {
            this.dX.d(this.dY);
            try {
                this.dZ.start();
            } catch (IllegalStateException unused) {
                g.a("start called in wrong state");
            }
            int i = this.ee;
            if (i > 0) {
                try {
                    this.dZ.seekTo(i);
                } catch (IllegalStateException unused2) {
                    g.a("seekTo called in wrong state");
                }
                this.ee = 0;
            }
            this.state = 1;
            co.a aVar = this.ea;
            if (aVar != null) {
                aVar.bz();
            }
        }
    }

    @Override // com.my.target.co
    public final boolean isPlaying() {
        return this.state == 1;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        this.state = 4;
        co.a aVar = this.ea;
        if (aVar != null) {
            aVar.onComplete();
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str;
        this.dX.e(this.dY);
        Y();
        a((Surface) null);
        if (this.ea != null) {
            String str2 = i == 100 ? "Server died" : "Unknown error";
            if (i2 == -1004) {
                str = "IO error";
            } else if (i2 == -1007) {
                str = "Malformed error";
            } else if (i2 == -1010) {
                str = "Unsupported error";
            } else if (i2 == -110) {
                str = "Timed out error";
            } else {
                str = i2 == Integer.MIN_VALUE ? "Low-level system error" : AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            String str3 = str2 + " (reason: " + str + ")";
            g.a("DefaultVideoPlayerVideo error: ".concat(String.valueOf(str3)));
            this.ea.W(str3);
        }
        if (this.state > 0) {
            this.dZ.reset();
        }
        this.state = 0;
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        float f = this.ed;
        mediaPlayer.setVolume(f, f);
        co.a aVar = this.ea;
        if (aVar != null) {
            aVar.bx();
        }
        this.dX.d(this.dY);
        this.state = 1;
        try {
            mediaPlayer.start();
        } catch (IllegalStateException unused) {
            g.a("start called in wrong state");
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        a(new Surface(surfaceTexture));
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        a((Surface) null);
        return true;
    }

    private void a(@Nullable Surface surface) {
        this.dZ.setSurface(surface);
        Surface surface2 = this.ec;
        if (surface2 != null && surface2 != surface) {
            surface2.release();
        }
        this.ec = surface;
    }

    private void Y() {
        TextureView textureView = this.eb;
        if (textureView != null) {
            if (textureView.getSurfaceTextureListener() != this) {
                Log.w("DefaultVideoPlayer", "SurfaceTextureListener already unset or replaced.");
            } else {
                this.eb.setSurfaceTextureListener(null);
            }
            this.eb = null;
        }
    }

    private boolean Z() {
        int i = this.state;
        return i > 0 && i <= 4;
    }

    /* JADX INFO: compiled from: DefaultVideoPlayer.java */
    @VisibleForTesting
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (ew.this.ea != null) {
                float position = ew.this.getPosition() / 1000.0f;
                float duration = ew.this.getDuration();
                if (duration > 0.0f) {
                    ew.this.ea.b(position, duration);
                }
            }
        }
    }
}
