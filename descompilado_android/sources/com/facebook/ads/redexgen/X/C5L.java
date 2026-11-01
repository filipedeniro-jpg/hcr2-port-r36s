package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.MediaController;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.io.IOException;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5L, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@TargetApi(14)
public final class C5L extends TextureView implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, MediaPlayer.OnSeekCompleteListener, TextureView.SurfaceTextureListener, C5M {
    private static final String Y = C5L.class.getSimpleName();
    private int B;
    private boolean C;
    private int D;
    private C5K E;
    private boolean F;
    private long G;
    private boolean H;
    private C7A I;
    private InterfaceC0450Cr J;
    private MediaController K;

    @Nullable
    private MediaPlayer L;
    private final MediaController.MediaPlayerControl M;
    private View N;
    private float O;
    private int P;
    private C5K Q;
    private boolean R;
    private Surface S;
    private C5K T;
    private int U;
    private boolean V;
    private Uri W;
    private int X;

    public C5L(Context context) {
        super(context);
        this.E = C5K.IDLE;
        this.T = C5K.IDLE;
        this.Q = C5K.IDLE;
        this.R = false;
        this.P = 0;
        this.X = 0;
        this.U = 0;
        this.O = 1.0f;
        this.H = false;
        this.B = 3;
        this.C = false;
        this.V = false;
        this.D = 0;
        this.F = false;
        this.I = C7A.NOT_STARTED;
        this.M = new MediaController.MediaPlayerControl() { // from class: com.facebook.ads.redexgen.X.5P
            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean canPause() {
                return true;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean canSeekBackward() {
                return true;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean canSeekForward() {
                return true;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getAudioSessionId() {
                if (this.B.L != null) {
                    return this.B.L.getAudioSessionId();
                }
                return 0;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getBufferPercentage() {
                return 0;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getCurrentPosition() {
                return this.B.getCurrentPosition();
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getDuration() {
                return this.B.getDuration();
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean isPlaying() {
                return this.B.L != null && this.B.L.isPlaying();
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final void pause() {
                this.B.pause(true);
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final void seekTo(int pos) {
                this.B.seekTo(pos);
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final void start() {
                this.B.tF(C7A.USER_STARTED);
            }
        };
    }

    public C5L(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.E = C5K.IDLE;
        this.T = C5K.IDLE;
        this.Q = C5K.IDLE;
        this.R = false;
        this.P = 0;
        this.X = 0;
        this.U = 0;
        this.O = 1.0f;
        this.H = false;
        this.B = 3;
        this.C = false;
        this.V = false;
        this.D = 0;
        this.F = false;
        this.I = C7A.NOT_STARTED;
        this.M = new MediaController.MediaPlayerControl() { // from class: com.facebook.ads.redexgen.X.5P
            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean canPause() {
                return true;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean canSeekBackward() {
                return true;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean canSeekForward() {
                return true;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getAudioSessionId() {
                if (this.B.L != null) {
                    return this.B.L.getAudioSessionId();
                }
                return 0;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getBufferPercentage() {
                return 0;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getCurrentPosition() {
                return this.B.getCurrentPosition();
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getDuration() {
                return this.B.getDuration();
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean isPlaying() {
                return this.B.L != null && this.B.L.isPlaying();
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final void pause() {
                this.B.pause(true);
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final void seekTo(int pos) {
                this.B.seekTo(pos);
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final void start() {
                this.B.tF(C7A.USER_STARTED);
            }
        };
    }

    public C5L(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.E = C5K.IDLE;
        this.T = C5K.IDLE;
        this.Q = C5K.IDLE;
        this.R = false;
        this.P = 0;
        this.X = 0;
        this.U = 0;
        this.O = 1.0f;
        this.H = false;
        this.B = 3;
        this.C = false;
        this.V = false;
        this.D = 0;
        this.F = false;
        this.I = C7A.NOT_STARTED;
        this.M = new MediaController.MediaPlayerControl() { // from class: com.facebook.ads.redexgen.X.5P
            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean canPause() {
                return true;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean canSeekBackward() {
                return true;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean canSeekForward() {
                return true;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getAudioSessionId() {
                if (this.B.L != null) {
                    return this.B.L.getAudioSessionId();
                }
                return 0;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getBufferPercentage() {
                return 0;
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getCurrentPosition() {
                return this.B.getCurrentPosition();
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final int getDuration() {
                return this.B.getDuration();
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final boolean isPlaying() {
                return this.B.L != null && this.B.L.isPlaying();
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final void pause() {
                this.B.pause(true);
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final void seekTo(int pos) {
                this.B.seekTo(pos);
            }

            @Override // android.widget.MediaController.MediaPlayerControl
            public final void start() {
                this.B.tF(C7A.USER_STARTED);
            }
        };
    }

    private boolean E() {
        return (this.E == C5K.PREPARING || this.E == C5K.PREPARED) ? false : true;
    }

    private boolean F() {
        return this.E == C5K.PREPARED || this.E == C5K.STARTED || this.E == C5K.PAUSED || this.E == C5K.PLAYBACK_COMPLETED;
    }

    private boolean G() {
        return (this.E == C5K.PREPARING || this.E == C5K.PREPARED) ? false : true;
    }

    private boolean H() {
        boolean z = false;
        if (this.L == null) {
            return false;
        }
        try {
            this.L.reset();
            z = true;
            return true;
        } catch (IllegalStateException e) {
            C0585Hx.D(getContext(), "player", C0584Hw.kB, new C0583Hv(e));
            Log.d(Y, "The MediaPlayer failed", e);
            return z;
        }
    }

    private boolean I(@Nullable Surface mSurface) {
        boolean z = false;
        if (this.L == null) {
            return false;
        }
        try {
            this.L.setSurface(mSurface);
            z = true;
            return true;
        } catch (IllegalStateException e) {
            C0585Hx.D(getContext(), "player", C0584Hw.lB, new C0583Hv(e));
            Log.d(Y, "The MediaPlayer failed", e);
            return z;
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public final void NC() {
        if (!this.C) {
            pause(false);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    @SuppressLint({"NewApi"})
    public final boolean OC() {
        if (this.L == null || Build.VERSION.SDK_INT < 16) {
            return false;
        }
        try {
            for (MediaPlayer.TrackInfo trackInfo : this.L.getTrackInfo()) {
                if (trackInfo.getTrackType() == 2) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e) {
            Log.e(Y, "Couldn't retrieve video information", e);
            return true;
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public final void destroy() {
        if (this.L != null) {
            I(null);
            this.L.setOnBufferingUpdateListener(null);
            this.L.setOnCompletionListener(null);
            this.L.setOnErrorListener(null);
            this.L.setOnInfoListener(null);
            this.L.setOnPreparedListener(null);
            this.L.setOnVideoSizeChangedListener(null);
            this.L.setOnSeekCompleteListener(null);
            H();
            this.L = null;
            setVideoState(C5K.IDLE);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public int getCurrentPosition() {
        if (this.L == null || !F()) {
            return 0;
        }
        return this.L.getCurrentPosition();
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public int getDuration() {
        if (this.L == null || !F()) {
            return 0;
        }
        return this.L.getDuration();
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public long getInitialBufferTime() {
        return this.G;
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public C7A getStartReason() {
        return this.I;
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public C5K getState() {
        return this.E;
    }

    public C5K getTargetState() {
        return this.T;
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public int getVideoHeight() {
        return this.U;
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public int getVideoWidth() {
        return this.X;
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public View getView() {
        return this;
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public float getVolume() {
        return this.O;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public final void onBufferingUpdate(MediaPlayer mp, int percent) {
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mp) {
        if (this.L != null) {
            this.L.pause();
        }
        setVideoState(C5K.PLAYBACK_COMPLETED);
        seekTo(0);
        this.P = 0;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mp, int what, int extra) {
        if (this.B > 0 && getState() == C5K.STARTED) {
            this.B--;
            stop();
            tF(this.I);
            return true;
        }
        setVideoState(C5K.ERROR);
        stop();
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.media.MediaPlayer.OnInfoListener
    public final boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case 3:
                this.V = true;
                if (this.T != C5K.STARTED) {
                    return true;
                }
                setVideoState(C5K.STARTED);
                return true;
            case 701:
                setVideoState(C5K.BUFFERING);
                return false;
            case 702:
                if (G()) {
                    setVideoState(C5K.STARTED);
                }
                return false;
            default:
                return false;
        }
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mp) {
        setVideoState(C5K.PREPARED);
        if (this.H && !this.F) {
            this.K = new MediaController(getContext());
            this.K.setAnchorView(this.N == null ? this : this.N);
            this.K.setMediaPlayer(this.M);
            this.K.setEnabled(true);
        }
        setRequestedVolume(this.O);
        this.X = mp.getVideoWidth();
        this.U = mp.getVideoHeight();
        if (this.P > 0) {
            if (this.P >= this.L.getDuration()) {
                this.P = 0;
            }
            this.L.seekTo(this.P);
            this.P = 0;
        }
        if (this.T == C5K.STARTED) {
            tF(this.I);
        }
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public final void onSeekComplete(MediaPlayer mp) {
        if (this.J == null) {
            return;
        }
        this.J.GF(this.D, this.P);
        this.P = 0;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture mSurface, int width, int height) {
        if (this.S == null) {
            this.S = new Surface(mSurface);
        }
        if (!I(this.S)) {
            setVideoState(C5K.ERROR);
            destroy();
            return;
        }
        this.R = false;
        if (this.E != C5K.PAUSED || this.Q == C5K.PAUSED) {
            return;
        }
        tF(this.I);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture mSurface) {
        I(null);
        if (this.S != null) {
            this.S.release();
            this.S = null;
        }
        if (!this.R) {
            this.Q = this.H ? C5K.STARTED : this.E;
            this.R = true;
        }
        if (this.E != C5K.PAUSED) {
            pause(false);
        }
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture mSurface, int width, int height) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture mSurface) {
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public final void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        this.X = mp.getVideoWidth();
        this.U = mp.getVideoHeight();
        if (this.X != 0 && this.U != 0) {
            requestLayout();
        }
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (this.L == null) {
            return;
        }
        if (this.K != null && this.K.isShowing()) {
            return;
        }
        if (!hasWindowFocus) {
            if (!this.R) {
                this.Q = this.H ? C5K.STARTED : this.E;
                this.R = true;
            }
            if (this.E == C5K.PAUSED) {
                return;
            }
            NC();
            return;
        }
        this.R = false;
        if (this.E != C5K.PAUSED || this.Q == C5K.PAUSED) {
            return;
        }
        tF(this.I);
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public final void pause(boolean videoPausedByUser) {
        this.T = C5K.PAUSED;
        if (this.L != null) {
            if (!E()) {
                return;
            }
            if (videoPausedByUser) {
                this.Q = C5K.PAUSED;
                this.R = true;
            }
            this.L.pause();
            if (this.E == C5K.PLAYBACK_COMPLETED) {
                return;
            }
            setVideoState(C5K.PAUSED);
            return;
        }
        setVideoState(C5K.IDLE);
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public final void sF() {
        setVideoState(C5K.PLAYBACK_COMPLETED);
        stop();
        this.P = 0;
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public final void seekTo(int seekTime) {
        if (this.L != null && F()) {
            if (seekTime < getDuration() && seekTime > 0) {
                this.D = getCurrentPosition();
                this.P = seekTime;
                this.L.seekTo(seekTime);
                return;
            }
            return;
        }
        this.P = seekTime;
    }

    @Override // android.view.TextureView, android.view.View
    public void setBackgroundDrawable(Drawable background) {
        if (Build.VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(background);
        } else {
            if (!AdInternalSettings.isDebugBuild()) {
                return;
            }
            Log.w(Y, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public void setBackgroundPlaybackEnabled(boolean enabled) {
        this.C = enabled;
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public void setControlsAnchorView(View mParentView) {
        this.N = mParentView;
        mParentView.setOnTouchListener(new View.OnTouchListener() { // from class: com.facebook.ads.redexgen.X.5N
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View v, MotionEvent event) {
                if (!this.B.F && this.B.K != null && event.getAction() == 1) {
                    if (this.B.K.isShowing()) {
                        this.B.K.hide();
                    } else {
                        this.B.K.show();
                    }
                }
                return true;
            }
        });
    }

    @Override // android.view.TextureView, android.view.View
    public void setForeground(Drawable foreground) {
        if (Build.VERSION.SDK_INT < 24) {
            super.setForeground(foreground);
        } else {
            if (!AdInternalSettings.isDebugBuild()) {
                return;
            }
            Log.w(Y, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public void setFullScreen(boolean fullscreen) {
        this.H = fullscreen;
        if (this.H && !this.F) {
            setOnTouchListener(new View.OnTouchListener() { // from class: com.facebook.ads.redexgen.X.5O
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View v, MotionEvent event) {
                    if (!this.B.F && this.B.K != null && event.getAction() == 1) {
                        if (this.B.K.isShowing()) {
                            this.B.K.hide();
                        } else {
                            this.B.K.show();
                        }
                    }
                    return true;
                }
            });
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public void setRequestedVolume(float volume) {
        this.O = volume;
        if (this.L != null && this.E != C5K.PREPARING && this.E != C5K.IDLE) {
            this.L.setVolume(volume, volume);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public void setVideoMPD(@Nullable String mpd) {
    }

    private void setVideoState(C5K state) {
        if (state != this.E) {
            if (BuildConfigApi.isDebug()) {
                Log.v(Y, "Video state changed to " + state);
            }
            this.E = state;
            if (this.J != null) {
                this.J.OF(state);
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public void setVideoStateChangeListener(InterfaceC0450Cr mListener) {
        this.J = mListener;
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public void setup(Uri mVideoUri) {
        MediaPlayer mediaPlayer;
        this.V = false;
        this.W = mVideoUri;
        if (this.L != null) {
            H();
            I(null);
            mediaPlayer = this.L;
            setVideoState(C5K.IDLE);
        } else {
            mediaPlayer = new MediaPlayer();
        }
        try {
            if (mVideoUri.getScheme().equals("asset")) {
                AssetFileDescriptor assetFileDescriptorOpenFd = null;
                try {
                    try {
                        assetFileDescriptorOpenFd = getContext().getAssets().openFd(mVideoUri.getPath().substring(1));
                        mediaPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
                        if (assetFileDescriptorOpenFd != null) {
                            try {
                                assetFileDescriptorOpenFd.close();
                            } catch (IOException e) {
                                Log.w(Y, "Unable to close" + e);
                            }
                        }
                    } catch (Throwable th) {
                        if (assetFileDescriptorOpenFd != null) {
                            try {
                                assetFileDescriptorOpenFd.close();
                            } catch (IOException e2) {
                                Log.w(Y, "Unable to close" + e2);
                            }
                        }
                        throw th;
                    }
                } catch (IOException | SecurityException e3) {
                    Log.w(Y, "Failed to open assets " + e3);
                    setVideoState(C5K.ERROR);
                    if (assetFileDescriptorOpenFd != null) {
                        try {
                            assetFileDescriptorOpenFd.close();
                        } catch (IOException e4) {
                            Log.w(Y, "Unable to close" + e4);
                        }
                    }
                }
            } else {
                mediaPlayer.setDataSource(mVideoUri.toString());
            }
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.prepareAsync();
            this.L = mediaPlayer;
            setVideoState(C5K.PREPARING);
        } catch (Exception e5) {
            setVideoState(C5K.ERROR);
            mediaPlayer.release();
            Log.e(Y, "Cannot prepare media player with SurfaceTexture: " + e5);
        }
        setSurfaceTextureListener(this);
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public final void stop() {
        this.T = C5K.IDLE;
        if (this.L != null) {
            int currentPosition = this.L.getCurrentPosition();
            if (currentPosition > 0) {
                this.P = currentPosition;
            }
            this.L.stop();
            H();
            this.L.release();
            this.L = null;
            if (this.K != null) {
                this.K.hide();
                this.K.setEnabled(false);
            }
        }
        setVideoState(C5K.IDLE);
    }

    @Override // com.facebook.ads.redexgen.X.C5M
    public final void tF(C7A startReason) {
        this.T = C5K.STARTED;
        this.I = startReason;
        if (this.E == C5K.STARTED || this.E == C5K.PREPARED || this.E == C5K.IDLE || this.E == C5K.PAUSED || this.E == C5K.PLAYBACK_COMPLETED) {
            if (this.L == null) {
                setup(this.W);
            } else {
                if (this.P > 0) {
                    this.L.seekTo(this.P);
                }
                this.L.start();
                if (this.E != C5K.PREPARED || this.V) {
                    setVideoState(C5K.STARTED);
                }
            }
        }
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }
}
