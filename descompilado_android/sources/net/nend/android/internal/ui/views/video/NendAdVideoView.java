package net.nend.android.internal.ui.views.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;
import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import net.nend.android.internal.utilities.j;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdVideoView extends FrameLayout implements TextureView.SurfaceTextureListener {

    @VisibleForTesting
    MediaPlayer a;

    @VisibleForTesting
    SurfaceTexture b;

    @VisibleForTesting
    boolean c;

    @VisibleForTesting
    boolean d;
    private c e;
    private a f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private String k;
    private Surface l;
    private long m;

    public interface a {
        void onCompletion(int i, boolean z);

        void onError(int i, String str);

        void onPrepared();

        void onProgress(int i, int i2);

        void onStartPlay();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public NendAdVideoView(Context context) {
        this(context, null);
    }

    public NendAdVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = 0;
        this.j = 0;
        this.k = null;
    }

    public void setUpVideo(String str) {
        a(str, false);
    }

    public void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            if (this.e == null) {
                this.k = str;
                this.c = false;
                this.g = false;
                this.h = z;
                this.e = new c(getContext());
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                layoutParams.gravity = 17;
                this.e.setLayoutParams(layoutParams);
                this.e.setSurfaceTextureListener(this);
                addView(this.e, 0);
                invalidate();
                requestLayout();
                return;
            }
            j.c("setUpVideo method call has already been completed.");
            return;
        }
        j.d("Video setup failed. Because the file path of setUpVideo method is empty or null.");
    }

    public void setCallback(a aVar) {
        this.f = aVar;
    }

    public boolean a() {
        MediaPlayer mediaPlayer;
        j.a("mIsMediaPlayerPrepared: " + this.c);
        StringBuilder sb = new StringBuilder();
        sb.append(" MediaPlayer object is ");
        sb.append(this.a != null ? "allocated." : "released.");
        j.a(sb.toString());
        return (!this.c || (mediaPlayer = this.a) == null || mediaPlayer.isPlaying()) ? false : true;
    }

    public int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void b() {
        if (this.g && !this.h) {
            j.d("This video can play only once.");
        } else {
            i();
        }
    }

    public void c() {
        if (this.g && !this.h) {
            j.d("This video can play only once.");
            return;
        }
        if (!this.c) {
            b();
            return;
        }
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer == null || mediaPlayer.isPlaying()) {
            return;
        }
        this.a.seekTo(this.i);
        this.g = false;
        this.a.start();
        this.m = System.currentTimeMillis();
        a aVar = this.f;
        if (aVar != null) {
            aVar.onStartPlay();
        }
    }

    public void d() {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.i = this.a.getCurrentPosition();
        this.a.pause();
        a aVar = this.f;
        if (aVar != null) {
            aVar.onCompletion(this.a.getCurrentPosition(), false);
        }
    }

    public void e() {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        int currentPosition = this.a.getCurrentPosition();
        g();
        a aVar = this.f;
        if (aVar != null) {
            aVar.onCompletion(currentPosition, false);
        }
    }

    private void g() {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            this.i = 0;
            this.c = false;
            mediaPlayer.stop();
            this.d = false;
            this.a.reset();
        }
    }

    public void a(int i) {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(i);
            this.i = this.a.getCurrentPosition();
        }
    }

    public void setMute(boolean z) {
        if (this.a != null) {
            float f = z ? 0.0f : 1.0f;
            this.a.setVolume(f, f);
        }
    }

    public void f() {
        this.g = false;
        h();
        if (this.f != null) {
            this.f = null;
        }
        c cVar = this.e;
        if (cVar != null) {
            removeView(cVar);
            this.e = null;
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.b == null) {
            this.b = surfaceTexture;
            i();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        a aVar;
        StringBuilder sb = new StringBuilder();
        sb.append(" MediaPlayer object is ");
        sb.append(this.a != null ? "still allocated." : "released.");
        j.a(sb.toString());
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer != null) {
            this.i = this.g ? 0 : mediaPlayer.getCurrentPosition();
            if (this.a.isPlaying() && (aVar = this.f) != null) {
                aVar.onCompletion(this.a.getCurrentPosition(), false);
            }
            h();
        }
        SurfaceTexture surfaceTexture2 = this.b;
        if (surfaceTexture2 == null) {
            return true;
        }
        surfaceTexture2.release();
        this.b = null;
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        MediaPlayer mediaPlayer = this.a;
        if (mediaPlayer == null || this.f == null || !mediaPlayer.isPlaying() || System.currentTimeMillis() - this.m < 1000) {
            return;
        }
        this.m = System.currentTimeMillis();
        a aVar = this.f;
        int i = this.j;
        aVar.onProgress(i, i - this.a.getCurrentPosition());
    }

    private void h() {
        this.c = false;
        this.d = false;
        if (this.a != null) {
            Surface surface = this.l;
            if (surface != null) {
                surface.release();
                this.l = null;
            }
            try {
                this.a.stop();
                this.a.reset();
                this.a.release();
                this.a = null;
            } catch (IllegalStateException e) {
                j.b("Failed to release MediaPlayer.", e);
            }
        }
    }

    private void i() {
        if (this.b != null) {
            if (this.a == null) {
                this.a = new MediaPlayer();
                this.a.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: net.nend.android.internal.ui.views.video.NendAdVideoView.1
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        NendAdVideoView.this.j = mediaPlayer.getDuration();
                        NendAdVideoView nendAdVideoView = NendAdVideoView.this;
                        nendAdVideoView.c = true;
                        if (nendAdVideoView.e != null) {
                            NendAdVideoView.this.e.a(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
                        }
                        if (NendAdVideoView.this.f != null) {
                            NendAdVideoView.this.f.onPrepared();
                        }
                    }
                });
                this.a.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: net.nend.android.internal.ui.views.video.NendAdVideoView.2
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        NendAdVideoView.this.i = 0;
                        NendAdVideoView.this.g = true;
                        if (NendAdVideoView.this.f != null) {
                            NendAdVideoView.this.f.onProgress(NendAdVideoView.this.j, 0);
                            NendAdVideoView.this.f.onCompletion(mediaPlayer.getCurrentPosition(), true);
                        }
                    }
                });
                this.a.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: net.nend.android.internal.ui.views.video.NendAdVideoView.3
                    @Override // android.media.MediaPlayer.OnErrorListener
                    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                        NendAdVideoView.this.a(i, i2);
                        return true;
                    }
                });
            }
            try {
                if (!this.d) {
                    this.a.setDataSource(this.k);
                    this.d = true;
                }
                if (this.l == null) {
                    this.l = new Surface(this.b);
                    this.a.setSurface(this.l);
                }
                this.a.prepareAsync();
            } catch (IOException e) {
                j.b("Failed to create media player.", e);
            } catch (IllegalStateException e2) {
                j.b("Failed to prepare media player.", e2);
                a aVar = this.f;
                if (aVar != null) {
                    aVar.onError(1, "Failed to prepare media player.");
                }
            }
        }
    }

    @VisibleForTesting
    void a(int i, int i2) {
        j.a("what: " + i);
        j.a("extra: " + i2);
        if (i == 1) {
            g();
            a aVar = this.f;
            if (aVar != null) {
                aVar.onError(i, "Media unknown error.");
                return;
            }
            return;
        }
        if (i != 100) {
            return;
        }
        g();
        a aVar2 = this.f;
        if (aVar2 != null) {
            aVar2.onError(i, "Media server died.");
        }
    }
}
