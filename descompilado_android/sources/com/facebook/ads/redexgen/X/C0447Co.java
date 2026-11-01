package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Co, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0447Co extends RelativeLayout implements InterfaceC0450Cr, InterfaceC0449Cq, InterfaceC0448Cp {
    public final C5M B;
    private final C0607Iu<AbstractC01210a, It> C;
    private C0618Jf D;
    private boolean E;
    private boolean F;
    private boolean G;
    private final View.OnTouchListener H;
    private final List<F0> I;
    private final Handler J;
    private boolean K;
    private final Handler L;
    private int M;
    private C7C N;
    private static final AnonymousClass70 Q = new It() { // from class: com.facebook.ads.redexgen.X.70
    };
    private static final AnonymousClass75 O = new It() { // from class: com.facebook.ads.redexgen.X.75
    };
    private static final C03006x R = new It() { // from class: com.facebook.ads.redexgen.X.6x
    };
    private static final C02996w S = new It() { // from class: com.facebook.ads.redexgen.X.6w
    };
    private static final AnonymousClass71 P = new It() { // from class: com.facebook.ads.redexgen.X.71
    };
    private static final C02966t T = new It() { // from class: com.facebook.ads.redexgen.X.6t
    };
    private static final C02936q V = new It() { // from class: com.facebook.ads.redexgen.X.6q
    };
    private static final C02956s U = new It() { // from class: com.facebook.ads.redexgen.X.6s
    };

    public C0447Co(Context context) {
        super(context);
        this.I = new ArrayList();
        this.J = new Handler();
        this.L = new Handler();
        this.C = new C0607Iu<>();
        this.G = false;
        this.K = true;
        this.M = 200;
        this.H = new View.OnTouchListener() { // from class: com.facebook.ads.redexgen.X.7V
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View v, MotionEvent event) {
                this.B.C.A(new C02986v(v, event));
                return false;
            }
        };
        this.B = new C5L(context);
        V();
    }

    public C0447Co(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.I = new ArrayList();
        this.J = new Handler();
        this.L = new Handler();
        this.C = new C0607Iu<>();
        this.G = false;
        this.K = true;
        this.M = 200;
        this.H = new View.OnTouchListener() { // from class: com.facebook.ads.redexgen.X.7V
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View v, MotionEvent event) {
                this.B.C.A(new C02986v(v, event));
                return false;
            }
        };
        this.B = new C5L(context, attrs);
        V();
    }

    public C0447Co(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.I = new ArrayList();
        this.J = new Handler();
        this.L = new Handler();
        this.C = new C0607Iu<>();
        this.G = false;
        this.K = true;
        this.M = 200;
        this.H = new View.OnTouchListener() { // from class: com.facebook.ads.redexgen.X.7V
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View v, MotionEvent event) {
                this.B.C.A(new C02986v(v, event));
                return false;
            }
        };
        this.B = new C5L(context, attrs, defStyleAttr);
        V();
    }

    public final void A(F0 plugin) {
        this.I.add(plugin);
    }

    public final void B() {
        this.B.setVideoStateChangeListener(null);
        this.B.destroy();
    }

    public final void C() {
        if (E()) {
            return;
        }
        this.B.NC();
    }

    public final boolean D() {
        return this.B.OC();
    }

    public final boolean E() {
        return getState() == C5K.PAUSED;
    }

    public final boolean F() {
        return E() && this.G;
    }

    public final boolean G() {
        return getState() == C5K.STARTED;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0450Cr
    public final void GF(final int seekFromTime, final int seekToTime) {
        this.L.post(new Runnable() { // from class: com.facebook.ads.redexgen.X.7W
            @Override // java.lang.Runnable
            public final void run() {
                this.B.C.A(new C03016y(seekFromTime, seekToTime));
            }
        });
        R();
    }

    public final void H(boolean pausedByUser) {
        if (E()) {
            return;
        }
        this.B.pause(pausedByUser);
        this.G = pausedByUser;
    }

    public final void I(boolean isPausedByUser, boolean shouldAutoRestart) {
        this.K = shouldAutoRestart;
        H(isPausedByUser);
    }

    public final void J(F0 plugin) {
        this.I.remove(plugin);
        W(plugin);
    }

    public final void K() {
        Iterator<F0> it = this.I.iterator();
        while (it.hasNext()) {
            W(it.next());
        }
        this.I.clear();
    }

    public final void L(int seekTime) {
        this.J.removeCallbacksAndMessages(null);
        this.B.seekTo(seekTime);
    }

    /* JADX INFO: renamed from: M, reason: collision with other method in class */
    public final boolean m242M() {
        return this.K;
    }

    /* JADX INFO: renamed from: N, reason: collision with other method in class */
    public final void m243N() {
        this.L.post(new Runnable() { // from class: com.facebook.ads.redexgen.X.7U
            @Override // java.lang.Runnable
            public final void run() {
                this.B.getEventBus().A(C0447Co.R);
            }
        });
        this.B.sF();
    }

    public final void O(C7A startReason) {
        if (this.E && this.B.getState() == C5K.PLAYBACK_COMPLETED) {
            this.E = false;
        }
        this.B.tF(startReason);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0450Cr
    public final void OF(final C5K newState) {
        final int currentPositionInMillis = getCurrentPositionInMillis();
        final int duration = getDuration();
        this.L.post(new Runnable() { // from class: com.facebook.ads.redexgen.X.7X
            @Override // java.lang.Runnable
            public final void run() {
                if (newState != C5K.PREPARED) {
                    if (newState != C5K.ERROR) {
                        if (newState != C5K.PLAYBACK_COMPLETED) {
                            if (newState != C5K.STARTED) {
                                if (newState != C5K.PAUSED) {
                                    if (newState != C5K.IDLE) {
                                        return;
                                    }
                                    this.B.X(EnumC0617Je.VIDEO_IDLE);
                                    this.B.C.A(C0447Co.S);
                                    this.B.J.removeCallbacksAndMessages(null);
                                    return;
                                }
                                this.B.X(EnumC0617Je.VIDEO_PAUSED);
                                this.B.C.A(new AnonymousClass78(currentPositionInMillis) { // from class: com.facebook.ads.redexgen.X.72
                                });
                                this.B.J.removeCallbacksAndMessages(null);
                                return;
                            }
                            this.B.X(EnumC0617Je.VIDEO_PLAY);
                            this.B.C.A(C0447Co.P);
                            this.B.J.removeCallbacksAndMessages(null);
                            this.B.R();
                            return;
                        }
                        this.B.X(EnumC0617Je.VIDEO_COMPLETED);
                        this.B.E = true;
                        this.B.J.removeCallbacksAndMessages(null);
                        this.B.C.A(new AnonymousClass76(currentPositionInMillis, duration));
                        return;
                    }
                    this.B.X(EnumC0617Je.VIDEO_ERROR);
                    this.B.E = true;
                    this.B.C.A(C0447Co.O);
                    return;
                }
                this.B.X(EnumC0617Je.VIDEO_PREPARED);
                this.B.C.A(C0447Co.Q);
            }
        });
    }

    public final void P() {
        this.B.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        this.J.postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.7Y
            @Override // java.lang.Runnable
            public final void run() {
                if (!this.B.E) {
                    this.B.C.A(new AnonymousClass78(this.B.getCurrentPositionInMillis()) { // from class: com.facebook.ads.redexgen.X.6z
                    });
                    this.B.J.postDelayed(this, this.B.M);
                }
            }
        }, this.M);
    }

    private final void S() {
        for (F0 f0 : this.I) {
            if (f0 instanceof AnonymousClass79) {
                T((AnonymousClass79) f0);
            }
            f0.cC(this);
        }
    }

    private void T(AnonymousClass79 viewPlugin) {
        if (viewPlugin.getParent() == null) {
            if (viewPlugin instanceof AnonymousClass67) {
                this.N.A(viewPlugin);
            } else {
                addView(viewPlugin);
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0449Cq
    public final boolean TC() {
        return false;
    }

    private void U(AnonymousClass79 viewPlugin) {
        if (viewPlugin instanceof AnonymousClass67) {
            this.N.C(viewPlugin);
        } else {
            J6.M(viewPlugin);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0449Cq
    public final boolean UC() {
        return this.F;
    }

    private void V() {
        this.B.setRequestedVolume(1.0f);
        this.B.setVideoStateChangeListener(this);
        this.N = new C7C(getContext(), this.B);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        addView(this.N, layoutParams);
        setOnTouchListener(this.H);
    }

    private void W(F0 plugin) {
        if (plugin instanceof AnonymousClass79) {
            U((AnonymousClass79) plugin);
        }
        plugin.wF(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X(EnumC0617Je event) {
        if (this.D == null) {
            return;
        }
        this.D.A(event, null);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0449Cq
    public int getCurrentPositionInMillis() {
        return this.B.getCurrentPosition();
    }

    public int getDuration() {
        return this.B.getDuration();
    }

    @NonNull
    public C0607Iu<AbstractC01210a, It> getEventBus() {
        return this.C;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0449Cq
    public long getInitialBufferTime() {
        return this.B.getInitialBufferTime();
    }

    public C5K getState() {
        return this.B.getState();
    }

    public Handler getStateHandler() {
        return this.L;
    }

    public TextureView getTextureView() {
        return (TextureView) this.B;
    }

    public int getVideoHeight() {
        return this.B.getVideoHeight();
    }

    public int getVideoProgressReportIntervalMs() {
        return this.M;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0449Cq
    public C7A getVideoStartReason() {
        return this.B.getStartReason();
    }

    public View getVideoView() {
        return this.N;
    }

    public int getVideoWidth() {
        return this.B.getVideoWidth();
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0449Cq
    public View getView() {
        return this;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC0449Cq
    public float getVolume() {
        return this.B.getVolume();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        this.C.A(U);
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.C.A(V);
        super.onDetachedFromWindow();
    }

    public void setControlsAnchorView(View parentView) {
        if (this.B != null) {
            this.B.setControlsAnchorView(parentView);
        }
    }

    public void setFunnelLoggingHandler(C0618Jf funnelLoggingHandler) {
        this.D = funnelLoggingHandler;
    }

    public void setIsFullScreen(boolean fullScreen) {
        this.F = fullScreen;
        this.B.setFullScreen(fullScreen);
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
    }

    public void setVideoMPD(@Nullable String mpd) {
        this.B.setVideoMPD(mpd);
    }

    public void setVideoProgressReportIntervalMs(int videoProgressReportIntervalMs) {
        this.M = videoProgressReportIntervalMs;
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null) {
            K();
        } else {
            S();
            this.B.setup(uri);
        }
        this.E = false;
    }

    public void setVideoURI(@Nullable String uri) {
        setVideoURI(uri != null ? Uri.parse(uri) : null);
    }

    public void setVolume(float volume) {
        X(volume == 1.0f ? EnumC0617Je.SOUND_ON : EnumC0617Je.SOUND_OFF);
        this.B.setRequestedVolume(volume);
        getEventBus().A(T);
    }
}
