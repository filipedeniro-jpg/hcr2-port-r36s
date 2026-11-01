package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(15)
public class NativeVideoView extends TextureView implements MediaController.MediaPlayerControl {
    private static final String m = "NativeVideoView";
    private MediaPlayer.OnCompletionListener A;
    private MediaPlayer.OnInfoListener B;
    private MediaPlayer.OnBufferingUpdateListener C;
    private MediaPlayer.OnErrorListener D;
    Uri a;
    Map<String, String> b;
    av c;
    int d;
    int e;
    int f;
    d g;

    @Nullable
    Handler h;
    boolean i;
    MediaPlayer.OnVideoSizeChangedListener j;
    MediaPlayer.OnPreparedListener k;
    final TextureView.SurfaceTextureListener l;
    private Surface n;
    private int o;
    private int p;
    private int q;
    private c r;
    private b s;
    private a t;
    private boolean u;
    private NativeVideoController v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    interface a {
        void a(int i);
    }

    interface b {
        void a(int i);
    }

    interface c {
        void a(int i);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
    }

    static /* synthetic */ boolean d(NativeVideoView nativeVideoView) {
        nativeVideoView.z = true;
        return true;
    }

    static final class d extends Handler {
        private final WeakReference<NativeVideoView> a;

        d(NativeVideoView nativeVideoView) {
            this.a = new WeakReference<>(nativeVideoView);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            NativeVideoView nativeVideoView = this.a.get();
            if (nativeVideoView != null && message.what == 1) {
                int duration = nativeVideoView.getDuration();
                int currentPosition = nativeVideoView.getCurrentPosition();
                if (duration != -1 && currentPosition != 0) {
                    be beVar = (be) nativeVideoView.getTag();
                    if (!((Boolean) beVar.v.get("didCompleteQ1")).booleanValue() && (currentPosition * 4) - duration >= 0) {
                        beVar.v.put("didCompleteQ1", true);
                        nativeVideoView.getQuartileCompletedListener().a(0);
                    }
                    if (!((Boolean) beVar.v.get("didCompleteQ2")).booleanValue() && (currentPosition * 2) - duration >= 0) {
                        beVar.v.put("didCompleteQ2", true);
                        nativeVideoView.getQuartileCompletedListener().a(1);
                    }
                    if (!((Boolean) beVar.v.get("didCompleteQ3")).booleanValue() && (currentPosition * 4) - (duration * 3) >= 0) {
                        beVar.v.put("didCompleteQ3", true);
                        nativeVideoView.getQuartileCompletedListener().a(2);
                    }
                    boolean zBooleanValue = ((Boolean) beVar.v.get("didQ4Fire")).booleanValue();
                    if ((currentPosition / duration) * 100.0f > beVar.E && !zBooleanValue) {
                        nativeVideoView.getPlaybackEventListener().a(5);
                    }
                }
                sendEmptyMessageDelayed(1, 1000L);
            }
            super.handleMessage(message);
        }
    }

    public NativeVideoView(Context context) {
        super(context);
        this.n = null;
        this.c = null;
        this.p = Integer.MIN_VALUE;
        this.q = 0;
        this.j = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.inmobi.ads.NativeVideoView.1
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                NativeVideoView.this.e = mediaPlayer.getVideoWidth();
                NativeVideoView.this.f = mediaPlayer.getVideoHeight();
                if (NativeVideoView.this.e == 0 || NativeVideoView.this.f == 0) {
                    return;
                }
                NativeVideoView.this.requestLayout();
            }
        };
        this.k = new MediaPlayer.OnPreparedListener() { // from class: com.inmobi.ads.NativeVideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                if (NativeVideoView.this.c == null) {
                    return;
                }
                NativeVideoView.this.c.a = 2;
                NativeVideoView nativeVideoView = NativeVideoView.this;
                nativeVideoView.x = nativeVideoView.y = NativeVideoView.d(nativeVideoView);
                if (NativeVideoView.this.v != null) {
                    NativeVideoView.this.v.setEnabled(true);
                }
                NativeVideoView.this.e = mediaPlayer.getVideoWidth();
                NativeVideoView.this.f = mediaPlayer.getVideoHeight();
                be beVar = (be) NativeVideoView.this.getTag();
                int iIntValue = 0;
                if (beVar != null && ((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                    NativeVideoView.this.a(8, 0);
                    if (((AdContainer.RenderingProperties.PlacementType) beVar.v.get("placementType")) == AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN) {
                        return;
                    }
                }
                if (NativeVideoView.this.getPlaybackEventListener() != null) {
                    NativeVideoView.this.getPlaybackEventListener().a(0);
                }
                if (beVar != null && !((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                    iIntValue = ((Integer) beVar.v.get("seekPosition")).intValue();
                }
                if (NativeVideoView.this.e != 0 && NativeVideoView.this.f != 0) {
                    if (3 == NativeVideoView.this.c.b) {
                        if (beVar != null && ((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                            NativeVideoView.this.start();
                        }
                        if (NativeVideoView.this.v != null) {
                            NativeVideoView.this.v.a();
                            return;
                        }
                        return;
                    }
                    if (NativeVideoView.this.isPlaying()) {
                        return;
                    }
                    if ((iIntValue != 0 || NativeVideoView.this.getCurrentPosition() > 0) && NativeVideoView.this.v != null) {
                        NativeVideoView.this.v.a();
                        return;
                    }
                    return;
                }
                if (3 == NativeVideoView.this.c.b && beVar != null && ((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                    NativeVideoView.this.start();
                }
            }
        };
        this.A = new MediaPlayer.OnCompletionListener() { // from class: com.inmobi.ads.NativeVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                try {
                    NativeVideoView.f(NativeVideoView.this);
                } catch (Exception e) {
                    String unused = NativeVideoView.m;
                    new StringBuilder("SDK encountered unexpected error in handling the media playback complete event; ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        };
        this.B = new MediaPlayer.OnInfoListener() { // from class: com.inmobi.ads.NativeVideoView.4
            @Override // android.media.MediaPlayer.OnInfoListener
            @TargetApi(17)
            public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                if (Build.VERSION.SDK_INT < 17 || 3 != i) {
                    return true;
                }
                NativeVideoView.this.a(8, 8);
                return true;
            }
        };
        this.C = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.inmobi.ads.NativeVideoView.5
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                NativeVideoView.this.w = i;
            }
        };
        this.D = new MediaPlayer.OnErrorListener() { // from class: com.inmobi.ads.NativeVideoView.6
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                String unused = NativeVideoView.m;
                StringBuilder sb = new StringBuilder("Media Play Error ");
                sb.append(i);
                sb.append(",");
                sb.append(i2);
                if (NativeVideoView.this.t != null) {
                    NativeVideoView.this.t.a(i);
                }
                if (NativeVideoView.this.c != null) {
                    NativeVideoView.this.c.a = -1;
                    NativeVideoView.this.c.b = -1;
                }
                if (NativeVideoView.this.v != null) {
                    NativeVideoView.this.v.b();
                }
                NativeVideoView.h(NativeVideoView.this);
                return true;
            }
        };
        this.l = new TextureView.SurfaceTextureListener() { // from class: com.inmobi.ads.NativeVideoView.7
            @Override // android.view.TextureView.SurfaceTextureListener
            public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            @TargetApi(16)
            public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                NativeVideoView.this.n = new Surface(surfaceTexture);
                NativeVideoView.this.g();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                int iIntValue;
                boolean z = NativeVideoView.this.c != null && NativeVideoView.this.c.b == 3;
                boolean z2 = i > 0 && i2 > 0;
                if (NativeVideoView.this.c != null && z && z2) {
                    if (NativeVideoView.this.getTag() != null && (iIntValue = ((Integer) ((be) NativeVideoView.this.getTag()).v.get("seekPosition")).intValue()) != 0) {
                        NativeVideoView.this.a(iIntValue);
                    }
                    NativeVideoView.this.start();
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                if (NativeVideoView.this.n != null) {
                    NativeVideoView.this.n.release();
                    NativeVideoView.this.n = null;
                }
                if (NativeVideoView.this.v != null) {
                    NativeVideoView.this.v.b();
                }
                NativeVideoView.this.c();
                return true;
            }
        };
        requestLayout();
        invalidate();
    }

    public av getMediaPlayer() {
        return this.c;
    }

    public final void a() {
        Surface surface = this.n;
        if (surface != null) {
            surface.release();
            this.n = null;
        }
        c();
    }

    public int getState() {
        av avVar = this.c;
        if (avVar != null) {
            return avVar.a;
        }
        return 0;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size;
        int size2;
        int i3;
        try {
            int defaultSize = getDefaultSize(this.e, i);
            int defaultSize2 = getDefaultSize(this.f, i2);
            if (this.e > 0 && this.f > 0) {
                int mode = View.MeasureSpec.getMode(i);
                size = View.MeasureSpec.getSize(i);
                int mode2 = View.MeasureSpec.getMode(i2);
                size2 = View.MeasureSpec.getSize(i2);
                if (mode == 1073741824 && mode2 == 1073741824) {
                    if (this.e * size2 < this.f * size) {
                        defaultSize2 = (this.f * size) / this.e;
                    } else if (this.e * size2 > this.f * size) {
                        size = (this.e * size2) / this.f;
                    }
                } else if (mode == 1073741824) {
                    int i4 = (this.f * size) / this.e;
                    if (mode2 != Integer.MIN_VALUE || i4 <= size2) {
                        size2 = i4;
                    }
                } else {
                    if (mode2 == 1073741824) {
                        i3 = (this.e * size2) / this.f;
                        if (mode != Integer.MIN_VALUE || i3 <= size) {
                        }
                    } else {
                        int i5 = this.e;
                        int i6 = this.f;
                        if (mode2 != Integer.MIN_VALUE || i6 <= size2) {
                            i3 = i5;
                            size2 = i6;
                        } else {
                            i3 = (this.e * size2) / this.f;
                        }
                        if (mode == Integer.MIN_VALUE && i3 > size) {
                            defaultSize2 = (this.f * size) / this.e;
                        }
                    }
                    size = i3;
                }
                setMeasuredDimension(size, size2);
            }
            size = defaultSize;
            size2 = defaultSize2;
            setMeasuredDimension(size, size2);
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in handling the onMeasure event; ").append(e.getMessage());
        }
    }

    @Nullable
    NativeVideoController getMediaController() {
        return this.v;
    }

    final boolean b() {
        av avVar = this.c;
        return (avVar == null || avVar.a == -1 || this.c.a == 0 || this.c.a == 1) ? false : true;
    }

    public void setIsLockScreen(boolean z) {
        this.u = z;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    @TargetApi(20)
    public void start() {
        PowerManager powerManager = (PowerManager) getContext().getSystemService("power");
        boolean zInKeyguardRestrictedInputMode = ((KeyguardManager) getContext().getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        boolean zIsScreenOn = Build.VERSION.SDK_INT < 20 ? powerManager.isScreenOn() : powerManager.isInteractive();
        boolean zB = b();
        be beVar = (be) getTag();
        boolean z = beVar == null || ((Boolean) beVar.v.get("shouldAutoPlay")).booleanValue();
        if (zB && !z) {
            a(8, 0);
        }
        if (zB && zIsScreenOn && !this.c.isPlaying() && z && (this.u || !zInKeyguardRestrictedInputMode)) {
            int iIntValue = (beVar == null || ((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) ? 0 : ((Integer) beVar.v.get("seekPosition")).intValue();
            d();
            a(iIntValue);
            this.c.start();
            this.c.a = 3;
            a(8, 8);
            if (beVar != null) {
                beVar.v.put("didCompleteQ4", false);
                if (beVar.a()) {
                    e();
                }
                if (((Boolean) beVar.v.get("didPause")).booleanValue()) {
                    getPlaybackEventListener().a(3);
                    beVar.v.put("didPause", false);
                } else {
                    getPlaybackEventListener().a(1);
                }
                d dVar = this.g;
                if (dVar != null && !dVar.hasMessages(1)) {
                    this.g.sendEmptyMessage(1);
                }
            }
            NativeVideoController nativeVideoController = this.v;
            if (nativeVideoController != null) {
                nativeVideoController.a();
            }
        }
        av avVar = this.c;
        if (avVar != null) {
            avVar.b = 3;
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (b() && this.c.isPlaying()) {
            this.c.pause();
            this.c.a = 4;
            if (getTag() != null) {
                be beVar = (be) getTag();
                beVar.v.put("didPause", true);
                beVar.v.put("seekPosition", Integer.valueOf(getCurrentPosition()));
            }
            getPlaybackEventListener().a(2);
        }
        av avVar = this.c;
        if (avVar != null) {
            avVar.b = 4;
        }
        this.i = false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (b()) {
            return this.c.getDuration();
        }
        return -1;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (b()) {
            return this.c.getCurrentPosition();
        }
        return 0;
    }

    final void a(int i) {
        if (b()) {
            this.c.seekTo(i);
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return b() && this.c.isPlaying();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.c != null) {
            return this.w;
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.x;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.y;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.z;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        if (this.d == 0) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.d = mediaPlayer.getAudioSessionId();
            mediaPlayer.release();
        }
        return this.d;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    public void setVideoURI(Uri uri, Map<String, String> map) {
        this.a = uri;
        this.b = map;
        g();
        requestLayout();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.a == null || this.n == null) {
            return;
        }
        if (this.c == null) {
            be beVar = (be) getTag();
            AdContainer.RenderingProperties.PlacementType placementType = AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN;
            if (beVar != null) {
                placementType = (AdContainer.RenderingProperties.PlacementType) beVar.v.get("placementType");
            }
            this.c = AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == placementType ? new av() : av.a();
            int i = this.d;
            if (i != 0) {
                this.c.setAudioSessionId(i);
            } else {
                this.d = this.c.getAudioSessionId();
            }
            try {
                this.c.setDataSource(getContext().getApplicationContext(), this.a, this.b);
            } catch (IOException unused) {
                av avVar = this.c;
                avVar.a = -1;
                avVar.b = -1;
                return;
            }
        }
        try {
            be beVar2 = (be) getTag();
            this.c.setOnPreparedListener(this.k);
            this.c.setOnVideoSizeChangedListener(this.j);
            this.c.setOnCompletionListener(this.A);
            this.c.setOnErrorListener(this.D);
            this.c.setOnInfoListener(this.B);
            this.c.setOnBufferingUpdateListener(this.C);
            this.c.setSurface(this.n);
            if (Build.VERSION.SDK_INT >= 26) {
                this.c.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).setLegacyStreamType(3).build());
            } else {
                this.c.setAudioStreamType(3);
            }
            this.c.prepareAsync();
            this.w = 0;
            this.c.a = 1;
            h();
            if (beVar2 != null) {
                if (((Boolean) beVar2.v.get("shouldAutoPlay")).booleanValue()) {
                    this.c.b = 3;
                }
                if (((Boolean) beVar2.v.get("didCompleteQ4")).booleanValue()) {
                    a(8, 0);
                    return;
                }
            }
            a(0, 0);
        } catch (Exception e) {
            av avVar2 = this.c;
            avVar2.a = -1;
            avVar2.b = -1;
            this.D.onError(avVar2, 1, 0);
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    final void c() {
        if (this.c != null) {
            d dVar = this.g;
            if (dVar != null) {
                dVar.removeMessages(1);
            }
            if (getTag() != null) {
                ((be) getTag()).v.put("seekPosition", Integer.valueOf(getCurrentPosition()));
            }
            av avVar = this.c;
            avVar.a = 0;
            avVar.b = 0;
            avVar.reset();
            this.c.setOnPreparedListener(null);
            this.c.setOnVideoSizeChangedListener(null);
            this.c.setOnCompletionListener(null);
            this.c.setOnErrorListener(null);
            this.c.setOnInfoListener(null);
            this.c.setOnBufferingUpdateListener(null);
            if (getTag() != null) {
                if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE == ((be) getTag()).v.get("placementType")) {
                    this.c.b();
                }
            } else {
                this.c.b();
            }
            AudioManager audioManager = (AudioManager) getContext().getSystemService("audio");
            if (audioManager != null) {
                audioManager.abandonAudioFocus(null);
            }
            this.c = null;
        }
    }

    public final void d() {
        av avVar = this.c;
        if (avVar != null) {
            this.o = 0;
            avVar.setVolume(0.0f, 0.0f);
            if (getTag() != null) {
                ((be) getTag()).v.put("currentMediaVolume", 0);
            }
        }
    }

    public final void e() {
        av avVar = this.c;
        if (avVar != null) {
            this.o = 1;
            avVar.setVolume(1.0f, 1.0f);
            if (getTag() != null) {
                ((be) getTag()).v.put("currentMediaVolume", 15);
            }
        }
    }

    public int getVolume() {
        if (b()) {
            return this.o;
        }
        return -1;
    }

    public int getVideoVolume() {
        if (isPlaying()) {
            return this.o;
        }
        return -1;
    }

    public int getLastVolume() {
        return this.p;
    }

    public void setLastVolume(int i) {
        this.p = i;
    }

    private void h() {
        NativeVideoController nativeVideoController;
        if (this.c == null || (nativeVideoController = this.v) == null) {
            return;
        }
        nativeVideoController.setMediaPlayer(this);
        this.v.setEnabled(b());
        this.v.a();
    }

    final void a(int i, int i2) {
        if (this.c != null) {
            ProgressBar progressBar = ((NativeVideoWrapper) getParent()).getProgressBar();
            ImageView poster = ((NativeVideoWrapper) getParent()).getPoster();
            progressBar.setVisibility(i);
            poster.setVisibility(i2);
        }
    }

    public void setMediaController(NativeVideoController nativeVideoController) {
        if (nativeVideoController != null) {
            this.v = nativeVideoController;
            h();
        }
    }

    public c getQuartileCompletedListener() {
        return this.r;
    }

    public void setQuartileCompletedListener(c cVar) {
        this.r = cVar;
    }

    public b getPlaybackEventListener() {
        return this.s;
    }

    public void setPlaybackEventListener(b bVar) {
        this.s = bVar;
    }

    public void setMediaErrorListener(a aVar) {
        this.t = aVar;
    }

    static /* synthetic */ void f(NativeVideoView nativeVideoView) {
        av avVar = nativeVideoView.c;
        if (avVar != null) {
            avVar.a = 5;
            avVar.b = 5;
        }
        NativeVideoController nativeVideoController = nativeVideoView.v;
        if (nativeVideoController != null) {
            nativeVideoController.b();
        }
        d dVar = nativeVideoView.g;
        if (dVar != null) {
            dVar.removeMessages(1);
        }
        if (nativeVideoView.getTag() != null) {
            be beVar = (be) nativeVideoView.getTag();
            if (!((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                beVar.v.put("didCompleteQ4", true);
                if (nativeVideoView.getQuartileCompletedListener() != null) {
                    nativeVideoView.getQuartileCompletedListener().a(3);
                }
            }
            beVar.v.put("didSignalVideoCompleted", true);
            if (beVar != null) {
                beVar.v.put("didCompleteQ1", false);
                beVar.v.put("didCompleteQ2", false);
                beVar.v.put("didCompleteQ3", false);
                beVar.v.put("didPause", false);
                beVar.v.put("didStartPlaying", false);
                beVar.v.put("didQ4Fire", false);
            }
            if (beVar.C) {
                nativeVideoView.start();
            } else if (((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                nativeVideoView.a(8, 0);
            }
        }
    }

    static /* synthetic */ void h(NativeVideoView nativeVideoView) {
        try {
            if (nativeVideoView.a != null) {
                String string = nativeVideoView.a.toString();
                com.inmobi.ads.cache.d.a();
                com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
                List<ContentValues> listA = bVarA.a("asset", com.inmobi.ads.cache.d.a, "disk_uri=? ", new String[]{string}, null, null, "created_ts DESC ", "1");
                bVarA.b();
                com.inmobi.ads.cache.a aVarA = listA.isEmpty() ? null : com.inmobi.ads.cache.d.a(listA.get(0));
                com.inmobi.ads.cache.a.C0074a c0074a = new com.inmobi.ads.cache.a.C0074a();
                if (aVarA != null) {
                    com.inmobi.ads.cache.a aVarA2 = c0074a.a(aVarA.d, 0, 0L).a();
                    com.inmobi.ads.cache.d.a();
                    com.inmobi.ads.cache.d.b(aVarA2);
                }
            }
        } catch (Exception unused) {
        }
    }
}
