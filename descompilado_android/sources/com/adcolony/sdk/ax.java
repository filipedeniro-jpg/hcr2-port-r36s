package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.LocationConst;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class ax extends TextureView implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, TextureView.SurfaceTextureListener {
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private String F;
    private String G;
    private FileInputStream H;
    private af I;
    private c J;
    private Surface K;
    private SurfaceTexture L;
    private RectF M;
    private a N;
    private ProgressBar O;
    private MediaPlayer P;
    private JSONObject Q;
    private ExecutorService R;
    private af S;
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private float f;
    private int g;
    private boolean h;
    private Paint i;
    private Paint j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private double s;
    private double t;
    private long u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    ax(Context context, af afVar, int i, c cVar) {
        super(context);
        this.h = true;
        this.i = new Paint();
        this.j = new Paint(1);
        this.M = new RectF();
        this.Q = y.a();
        this.R = Executors.newSingleThreadExecutor();
        this.J = cVar;
        this.I = afVar;
        this.o = i;
        setSurfaceTextureListener(this);
    }

    void a() {
        if (this.L != null) {
            this.A = true;
        }
        this.R.shutdown();
    }

    void b() {
        Activity activityC;
        JSONObject jSONObjectC = this.I.c();
        this.G = y.b(jSONObjectC, "ad_session_id");
        this.k = y.c(jSONObjectC, "x");
        this.l = y.c(jSONObjectC, "y");
        this.m = y.c(jSONObjectC, "width");
        this.n = y.c(jSONObjectC, "height");
        this.C = y.d(jSONObjectC, "enable_timer");
        this.E = y.d(jSONObjectC, "enable_progress");
        this.F = y.b(jSONObjectC, "filepath");
        this.p = y.c(jSONObjectC, "video_width");
        this.q = y.c(jSONObjectC, "video_height");
        this.f = com.adcolony.sdk.a.a().c.o();
        new aa.a().a("Original video dimensions = ").a(this.p).a("x").a(this.q).a(aa.b);
        setVisibility(4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.m, this.n);
        layoutParams.setMargins(this.k, this.l, 0, 0);
        layoutParams.gravity = 0;
        this.J.addView(this, layoutParams);
        if (this.E && (activityC = com.adcolony.sdk.a.c()) != null) {
            this.O = new ProgressBar(activityC);
            c cVar = this.J;
            ProgressBar progressBar = this.O;
            float f = this.f;
            cVar.addView(progressBar, new FrameLayout.LayoutParams((int) (f * 100.0f), (int) (f * 100.0f), 17));
        }
        this.P = new MediaPlayer();
        this.z = false;
        try {
            if (!this.F.startsWith("http")) {
                this.H = new FileInputStream(this.F);
                this.P.setDataSource(this.H.getFD());
            } else {
                this.B = true;
                this.P.setDataSource(this.F);
            }
            this.P.setOnErrorListener(this);
            this.P.setOnPreparedListener(this);
            this.P.setOnCompletionListener(this);
            this.P.prepareAsync();
        } catch (IOException e) {
            new aa.a().a("Failed to create/prepare MediaPlayer: ").a(e.toString()).a(aa.g);
            k();
        }
        this.J.n().add(com.adcolony.sdk.a.a("VideoView.play", new ah() { // from class: com.adcolony.sdk.ax.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.e();
                }
            }
        }, true));
        this.J.n().add(com.adcolony.sdk.a.a("VideoView.set_bounds", new ah() { // from class: com.adcolony.sdk.ax.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.b(afVar);
                }
            }
        }, true));
        this.J.n().add(com.adcolony.sdk.a.a("VideoView.set_visible", new ah() { // from class: com.adcolony.sdk.ax.3
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.c(afVar);
                }
            }
        }, true));
        this.J.n().add(com.adcolony.sdk.a.a("VideoView.pause", new ah() { // from class: com.adcolony.sdk.ax.4
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.f();
                }
            }
        }, true));
        this.J.n().add(com.adcolony.sdk.a.a("VideoView.seek_to_time", new ah() { // from class: com.adcolony.sdk.ax.5
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.e(afVar);
                }
            }
        }, true));
        this.J.n().add(com.adcolony.sdk.a.a("VideoView.set_volume", new ah() { // from class: com.adcolony.sdk.ax.6
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                if (ax.this.a(afVar)) {
                    ax.this.d(afVar);
                }
            }
        }, true));
        this.J.o().add("VideoView.play");
        this.J.o().add("VideoView.set_bounds");
        this.J.o().add("VideoView.set_visible");
        this.J.o().add("VideoView.pause");
        this.J.o().add("VideoView.seek_to_time");
        this.J.o().add("VideoView.set_volume");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        return y.c(jSONObjectC, "id") == this.o && y.c(jSONObjectC, "container_id") == this.J.d() && y.b(jSONObjectC, "ad_session_id").equals(this.J.b());
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (surfaceTexture == null || this.A) {
            new aa.a().a("Null texture provided by system's onSurfaceTextureAvailable or ").a("MediaPlayer has been destroyed.").a(aa.h);
            return;
        }
        this.K = new Surface(surfaceTexture);
        try {
            this.P.setSurface(this.K);
        } catch (IllegalStateException unused) {
            new aa.a().a("IllegalStateException thrown when calling MediaPlayer.setSurface()").a(aa.g);
            k();
        }
        this.L = surfaceTexture;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.L = surfaceTexture;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.L = surfaceTexture;
        if (!this.A) {
            return false;
        }
        surfaceTexture.release();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.L = surfaceTexture;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        l lVarA = com.adcolony.sdk.a.a();
        d dVarM = lVarA.m();
        int action = motionEvent.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        JSONObject jSONObjectA = y.a();
        y.b(jSONObjectA, "view_id", this.o);
        y.a(jSONObjectA, "ad_session_id", this.G);
        y.b(jSONObjectA, "container_x", this.k + x);
        y.b(jSONObjectA, "container_y", this.l + y);
        y.b(jSONObjectA, "view_x", x);
        y.b(jSONObjectA, "view_y", y);
        y.b(jSONObjectA, "id", this.J.d());
        if (action == 0) {
            new af("AdContainer.on_touch_began", this.J.c(), jSONObjectA).b();
        } else if (action == 1) {
            if (!this.J.r()) {
                lVarA.a(dVarM.f().get(this.G));
            }
            new af("AdContainer.on_touch_ended", this.J.c(), jSONObjectA).b();
        } else if (action == 2) {
            new af("AdContainer.on_touch_moved", this.J.c(), jSONObjectA).b();
        } else if (action == 3) {
            new af("AdContainer.on_touch_cancelled", this.J.c(), jSONObjectA).b();
        } else if (action == 5) {
            int action2 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action2)) + this.k);
            y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action2)) + this.l);
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action2));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action2));
            new af("AdContainer.on_touch_began", this.J.c(), jSONObjectA).b();
        } else if (action == 6) {
            int action3 = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            y.b(jSONObjectA, "container_x", ((int) motionEvent.getX(action3)) + this.k);
            y.b(jSONObjectA, "container_y", ((int) motionEvent.getY(action3)) + this.l);
            y.b(jSONObjectA, "view_x", (int) motionEvent.getX(action3));
            y.b(jSONObjectA, "view_y", (int) motionEvent.getY(action3));
            if (!this.J.r()) {
                lVarA.a(dVarM.f().get(this.G));
            }
            new af("AdContainer.on_touch_ended", this.J.c(), jSONObjectA).b();
        }
        return true;
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        double d = this.m;
        double d2 = this.p;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = d / d2;
        double d4 = this.n;
        double d5 = this.q;
        Double.isNaN(d4);
        Double.isNaN(d5);
        double d6 = d4 / d5;
        if (d3 > d6) {
            d3 = d6;
        }
        double d7 = this.p;
        Double.isNaN(d7);
        int i3 = (int) (d7 * d3);
        double d8 = this.q;
        Double.isNaN(d8);
        int i4 = (int) (d8 * d3);
        new aa.a().a("setMeasuredDimension to ").a(i3).a(" by ").a(i4).a(aa.d);
        setMeasuredDimension(i3, i4);
        if (this.B) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            layoutParams.width = i3;
            layoutParams.height = i4;
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, 0, 0);
            setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", this.G);
        new af("AdSession.on_error", this.J.c(), jSONObjectA).b();
        this.v = true;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        k();
        new aa.a().a("MediaPlayer error: " + i + "," + i2).a(aa.g);
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.z = true;
        if (this.E) {
            this.J.removeView(this.O);
        }
        if (this.B) {
            this.p = mediaPlayer.getVideoWidth();
            this.q = mediaPlayer.getVideoHeight();
            onMeasure(this.p, this.q);
            new aa.a().a("MediaPlayer getVideoWidth = ").a(mediaPlayer.getVideoWidth()).a(aa.d);
            new aa.a().a("MediaPlayer getVideoHeight = ").a(mediaPlayer.getVideoHeight()).a(aa.d);
        }
        JSONObject jSONObjectA = y.a();
        y.b(jSONObjectA, "id", this.o);
        y.b(jSONObjectA, "container_id", this.J.d());
        y.a(jSONObjectA, "ad_session_id", this.G);
        new aa.a().a("ADCVideoView is prepared").a(aa.b);
        new af("VideoView.on_ready", this.J.c(), jSONObjectA).b();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.v = true;
        this.s = this.t;
        y.b(this.Q, "id", this.o);
        y.b(this.Q, "container_id", this.J.d());
        y.a(this.Q, "ad_session_id", this.G);
        y.a(this.Q, "elapsed", this.s);
        y.a(this.Q, "duration", this.t);
        new af("VideoView.on_progress", this.J.c(), this.Q).b();
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        ExecutorService executorService = this.R;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        try {
            this.R.submit(new Runnable() { // from class: com.adcolony.sdk.ax.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(150L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (ax.this.S != null) {
                        JSONObject jSONObjectA = y.a();
                        y.b(jSONObjectA, "id", ax.this.o);
                        y.a(jSONObjectA, "ad_session_id", ax.this.G);
                        y.a(jSONObjectA, "success", true);
                        ax.this.S.a(jSONObjectA).b();
                        ax.this.S = null;
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            k();
        }
    }

    private void l() {
        try {
            this.R.submit(new Runnable() { // from class: com.adcolony.sdk.ax.8
                @Override // java.lang.Runnable
                public void run() {
                    ax.this.u = 0L;
                    while (!ax.this.v && !ax.this.y && com.adcolony.sdk.a.d()) {
                        Activity activityC = com.adcolony.sdk.a.c();
                        if (ax.this.v || ax.this.A || activityC == null) {
                            return;
                        }
                        if (ax.this.P.isPlaying()) {
                            if (ax.this.u == 0 && com.adcolony.sdk.a.b) {
                                ax.this.u = System.currentTimeMillis();
                            }
                            ax.this.x = true;
                            ax axVar = ax.this;
                            double currentPosition = axVar.P.getCurrentPosition();
                            Double.isNaN(currentPosition);
                            axVar.s = currentPosition / 1000.0d;
                            ax axVar2 = ax.this;
                            double duration = axVar2.P.getDuration();
                            Double.isNaN(duration);
                            axVar2.t = duration / 1000.0d;
                            if (System.currentTimeMillis() - ax.this.u > 1000 && !ax.this.D && com.adcolony.sdk.a.b) {
                                if (ax.this.s != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                                    ax.this.D = true;
                                } else {
                                    new aa.a().a("getCurrentPosition() not working, firing ").a("AdSession.on_error").a(aa.h);
                                    ax.this.k();
                                }
                            }
                            if (ax.this.C) {
                                ax.this.c();
                            }
                        }
                        if (ax.this.x && !ax.this.v && !ax.this.y) {
                            y.b(ax.this.Q, "id", ax.this.o);
                            y.b(ax.this.Q, "container_id", ax.this.J.d());
                            y.a(ax.this.Q, "ad_session_id", ax.this.G);
                            y.a(ax.this.Q, "elapsed", ax.this.s);
                            y.a(ax.this.Q, "duration", ax.this.t);
                            new af("VideoView.on_progress", ax.this.J.c(), ax.this.Q).b();
                        }
                        if (ax.this.w || activityC.isFinishing()) {
                            ax.this.w = false;
                            ax.this.d();
                            return;
                        } else {
                            try {
                                Thread.sleep(50L);
                            } catch (InterruptedException unused) {
                                ax.this.k();
                                new aa.a().a("InterruptedException in ADCVideoView's update thread.").a(aa.g);
                            }
                        }
                    }
                    if (ax.this.w) {
                        ax.this.d();
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            k();
        }
    }

    void c() {
        if (this.h) {
            this.e = (float) (360.0d / this.t);
            this.j.setColor(-3355444);
            this.j.setShadowLayer((int) (this.f * 2.0f), 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
            this.j.setTextAlign(Paint.Align.CENTER);
            this.j.setLinearText(true);
            this.j.setTextSize(this.f * 12.0f);
            this.i.setStyle(Paint.Style.STROKE);
            float f = this.f;
            float f2 = f * 2.0f <= 6.0f ? f * 2.0f : 6.0f;
            this.i.setStrokeWidth(f2 >= 4.0f ? f2 : 4.0f);
            this.i.setShadowLayer((int) (this.f * 3.0f), 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
            this.i.setColor(-3355444);
            Rect rect = new Rect();
            this.j.getTextBounds("0123456789", 0, 9, rect);
            this.c = rect.height();
            final Activity activityC = com.adcolony.sdk.a.c();
            if (activityC != null) {
                aw.a(new Runnable() { // from class: com.adcolony.sdk.ax.9
                    @Override // java.lang.Runnable
                    public void run() {
                        ax axVar = ax.this;
                        axVar.N = axVar.new a(activityC);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (ax.this.c * 4.0f), (int) (ax.this.c * 4.0f));
                        layoutParams.setMargins(0, ax.this.J.p() - ((int) (ax.this.c * 4.0f)), 0, 0);
                        layoutParams.gravity = 0;
                        ax.this.J.addView(ax.this.N, layoutParams);
                    }
                });
            }
            this.h = false;
        }
        this.g = (int) (this.t - this.s);
        float f3 = this.c;
        this.a = (int) f3;
        this.b = (int) (3.0f * f3);
        RectF rectF = this.M;
        float f4 = this.a;
        float f5 = this.b;
        rectF.set(f4 - (f3 / 2.0f), f5 - (f3 * 2.0f), f4 + (f3 * 2.0f), f5 + (f3 / 2.0f));
        double d = this.e;
        double d2 = this.t - this.s;
        Double.isNaN(d);
        this.d = (float) (d * d2);
    }

    void d() {
        new aa.a().a("MediaPlayer stopped and released.").a(aa.d);
        try {
            if (!this.v && this.z && this.P.isPlaying()) {
                this.P.stop();
            }
        } catch (IllegalStateException unused) {
            new aa.a().a("Caught IllegalStateException when calling stop on MediaPlayer").a(aa.f);
        }
        ProgressBar progressBar = this.O;
        if (progressBar != null) {
            this.J.removeView(progressBar);
        }
        this.v = true;
        this.z = false;
        this.P.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        this.k = y.c(jSONObjectC, "x");
        this.l = y.c(jSONObjectC, "y");
        this.m = y.c(jSONObjectC, "width");
        this.n = y.c(jSONObjectC, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.k, this.l, 0, 0);
        layoutParams.width = this.m;
        layoutParams.height = this.n;
        setLayoutParams(layoutParams);
        if (!this.C || this.N == null) {
            return;
        }
        float f = this.c;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams((int) (f * 4.0f), (int) (f * 4.0f));
        layoutParams2.setMargins(0, this.J.p() - ((int) (this.c * 4.0f)), 0, 0);
        layoutParams2.gravity = 0;
        this.N.setLayoutParams(layoutParams2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(af afVar) {
        a aVar;
        a aVar2;
        if (y.d(afVar.c(), "visible")) {
            setVisibility(0);
            if (!this.C || (aVar2 = this.N) == null) {
                return;
            }
            aVar2.setVisibility(0);
            return;
        }
        setVisibility(4);
        if (!this.C || (aVar = this.N) == null) {
            return;
        }
        aVar.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(af afVar) {
        if (!this.z) {
            return false;
        }
        float fE = (float) y.e(afVar.c(), "volume");
        AdColonyInterstitial adColonyInterstitialU = com.adcolony.sdk.a.a().u();
        if (adColonyInterstitialU != null) {
            adColonyInterstitialU.b(((double) fE) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        this.P.setVolume(fE, fE);
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "success", true);
        afVar.a(jSONObjectA).b();
        return true;
    }

    boolean e() {
        if (!this.z) {
            return false;
        }
        if (!this.y && com.adcolony.sdk.a.b) {
            this.P.start();
            l();
            new aa.a().a("MediaPlayer is prepared - ADCVideoView play() called.").a(aa.b);
        } else if (!this.v && com.adcolony.sdk.a.b) {
            this.P.start();
            this.y = false;
            if (!this.R.isShutdown()) {
                l();
            }
            a aVar = this.N;
            if (aVar != null) {
                aVar.invalidate();
            }
        }
        setWillNotDraw(false);
        return true;
    }

    boolean f() {
        if (!this.z) {
            new aa.a().a("ADCVideoView pause() called while MediaPlayer is not prepared.").a(aa.f);
            return false;
        }
        if (!this.x) {
            new aa.a().a("Ignoring ADCVideoView pause due to invalid MediaPlayer state.").a(aa.d);
            return false;
        }
        this.r = this.P.getCurrentPosition();
        this.t = this.P.getDuration();
        this.P.pause();
        this.y = true;
        new aa.a().a("Video view paused").a(aa.b);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(af afVar) {
        if (!this.z) {
            return false;
        }
        if (this.v) {
            this.v = false;
        }
        this.S = afVar;
        int iC = y.c(afVar.c(), LocationConst.TIME);
        int duration = this.P.getDuration() / 1000;
        this.P.setOnSeekCompleteListener(this);
        this.P.seekTo(iC * 1000);
        if (duration == iC) {
            this.v = true;
        }
        return true;
    }

    void g() {
        this.w = true;
    }

    boolean h() {
        return this.P != null;
    }

    MediaPlayer i() {
        return this.P;
    }

    boolean j() {
        return this.v;
    }

    private class a extends View {
        a(Context context) {
            super(context);
            setWillNotDraw(false);
            try {
                getClass().getMethod("setLayerType", Integer.TYPE, Paint.class).invoke(this, 1, null);
            } catch (Exception unused) {
            }
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawArc(ax.this.M, 270.0f, ax.this.d, false, ax.this.i);
            String str = "" + ax.this.g;
            float fCenterX = ax.this.M.centerX();
            double dCenterY = ax.this.M.centerY();
            double d = ax.this.j.getFontMetrics().bottom;
            Double.isNaN(d);
            Double.isNaN(dCenterY);
            canvas.drawText(str, fCenterX, (float) (dCenterY + (d * 1.35d)), ax.this.j);
            invalidate();
        }
    }
}
