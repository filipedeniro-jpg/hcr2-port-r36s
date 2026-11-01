package net.nend.android;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.drive.DriveFile;
import net.nend.android.internal.b.c.b;
import net.nend.android.internal.ui.a.e;
import net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity;
import net.nend.android.internal.ui.views.formats.FontFitTextView;
import net.nend.android.internal.ui.views.video.NendAdVideoView;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.n;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;
import net.nend.android.internal.utilities.video.f;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdNativeMediaView extends FrameLayout {
    public static final int FULLSCREEN_MEDIA_PROCESS_COMPLETION = 12;
    public static final int FULLSCREEN_MEDIA_PROCESS_ERROR = 13;
    public static final int FULLSCREEN_MEDIA_PROCESS_PROGRESS = 11;
    public static final int FULLSCREEN_RESULT_CLICK_AD = 2;
    public static final int FULLSCREEN_RESULT_CLICK_INFORMATION = 3;
    public static final int FULLSCREEN_RESULT_FINISH = 1;
    public static final String RESULT_CLICK_URL = "click_url";
    public static final String RESULT_CURRENT_MSEC = "msec";
    public static final String RESULT_ERROR_CODE = "errorCode";
    public static final String RESULT_ERROR_MESSAGE = "errorMessage";
    public static final String RESULT_IS_COMPLETION = "isCompletion";
    public static final String RESULT_REMAIN_MSEC = "remainingMsec";
    public static final String RESULT_TOTAL_MSEC = "totalMsec";

    @VisibleForTesting
    boolean a;

    @VisibleForTesting
    f b;
    private Context c;
    private NendAdNativeVideo d;
    private NendAdVideoView e;
    private ImageView f;
    private View g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private float l;
    private float m;
    private FontFitTextView n;
    private FontFitTextView o;
    private NendAdNativeMediaViewListener p;
    private View.OnClickListener q;
    private View.OnClickListener r;
    private NendAdVideoView.a s;
    private View.OnClickListener t;
    private ViewTreeObserver.OnPreDrawListener u;
    private ResultReceiver v;
    private BroadcastReceiver w;

    public NendAdNativeMediaView(Context context) {
        super(context);
        this.h = false;
        this.i = 0;
        this.j = 0;
        this.a = false;
        this.q = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NendAdNativeMediaView.this.a()) {
                    NendAdNativeMediaView.this.i();
                    NendAdNativeMediaView.this.k();
                }
            }
        };
        this.r = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NendAdNativeMediaView.this.a()) {
                    NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, NendAdNativeMediaView.this.d.d(), false);
                }
            }
        };
        this.s = new NendAdVideoView.a() { // from class: net.nend.android.NendAdNativeMediaView.8
            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onStartPlay() {
                NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.e.getWidth(), NendAdNativeMediaView.this.e.getHeight());
                if (NendAdNativeMediaView.this.p != null) {
                    NendAdNativeMediaView.this.p.onStartPlay(NendAdNativeMediaView.this);
                }
                NendAdNativeMediaView.this.b = f.PLAYING;
                if (NendAdNativeMediaView.this.f.getVisibility() != 0) {
                    e.a(NendAdNativeMediaView.this.getWidth(), NendAdNativeMediaView.this.getHeight(), NendAdNativeMediaView.this.f);
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(NendAdNativeMediaView.this.f, "alpha", 0.0f, 1.0f);
                    objectAnimatorOfFloat.setDuration(1000L);
                    objectAnimatorOfFloat.setStartDelay(0L);
                    objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: net.nend.android.NendAdNativeMediaView.8.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            NendAdNativeMediaView.this.f.setVisibility(0);
                        }
                    });
                    objectAnimatorOfFloat.start();
                }
                NendAdNativeMediaView.this.c();
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onPrepared() {
                NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                nendAdNativeMediaView.b(nendAdNativeMediaView.d.b());
                NendAdNativeMediaView.this.l();
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onCompletion(int i, boolean z) {
                NendAdNativeMediaView.this.a(i, z);
                j.a("onCompletion isWindowVisible: " + NendAdNativeMediaView.this.h);
                if (NendAdNativeMediaView.this.h && z) {
                    NendAdNativeMediaView.this.c(0);
                    NendAdNativeMediaView.this.n();
                }
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onProgress(int i, int i2) {
                NendAdNativeMediaView.this.j = i;
                NendAdNativeMediaView.this.a(i, i2);
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onError(int i, String str) {
                NendAdNativeMediaView.this.a(i, str);
            }
        };
        this.t = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!NendAdNativeMediaView.this.a() || NendAdNativeMediaView.this.d == null) {
                    return;
                }
                int i = AnonymousClass3.a[NendAdNativeMediaView.this.d.c().ordinal()];
                if (i == 1) {
                    NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                    nendAdNativeMediaView.setProgressDurationTime(nendAdNativeMediaView.e.getCurrentPosition());
                    Intent intent = new Intent(NendAdNativeMediaView.this.c, (Class<?>) FullscreenVideoPlayingActivity.class);
                    intent.putExtras(FullscreenVideoPlayingActivity.newBundle(NendAdNativeMediaView.this.i, NendAdNativeMediaView.this.d.a(), NendAdNativeMediaView.this.d.isMutePlayingFullscreen(), NendAdNativeMediaView.this.v));
                    if (NendAdNativeMediaView.this.c instanceof Activity) {
                        Activity activity = (Activity) NendAdNativeMediaView.this.c;
                        activity.startActivity(intent);
                        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        intent.setFlags(DriveFile.MODE_READ_ONLY);
                        NendAdNativeMediaView.this.c.startActivity(intent);
                    }
                    if (NendAdNativeMediaView.this.p != null) {
                        NendAdNativeMediaView.this.p.onOpenFullScreen(NendAdNativeMediaView.this);
                    }
                } else if (i == 2) {
                    NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, NendAdNativeMediaView.this.d.d(), false);
                }
                NendAdNativeMediaView.this.e.setCallback(null);
                NendAdNativeMediaView.this.j();
            }
        };
        this.v = new ResultReceiver(new Handler(Looper.getMainLooper())) { // from class: net.nend.android.NendAdNativeMediaView.11
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                super.onReceiveResult(i, bundle);
                if (NendAdNativeMediaView.this.d != null || i == 1 || i == 13) {
                    if (i == 1) {
                        if (NendAdNativeMediaView.this.e == null && NendAdNativeMediaView.this.d != null) {
                            NendAdNativeMediaView.this.f();
                        }
                        if (NendAdNativeMediaView.this.p != null) {
                            NendAdNativeMediaView.this.p.onCloseFullScreen(NendAdNativeMediaView.this);
                            return;
                        }
                        return;
                    }
                    if (i == 2) {
                        NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, bundle.getString(NendAdNativeMediaView.RESULT_CLICK_URL), true);
                        NendAdNativeMediaView.this.a = true;
                        return;
                    }
                    if (i == 3) {
                        NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c);
                        NendAdNativeMediaView.this.a = true;
                        return;
                    }
                    switch (i) {
                        case 11:
                            NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                            nendAdNativeMediaView.a(nendAdNativeMediaView.j, bundle.getInt(NendAdNativeMediaView.RESULT_REMAIN_MSEC));
                            break;
                        case 12:
                            boolean z = bundle.getBoolean(NendAdNativeMediaView.RESULT_IS_COMPLETION);
                            if (z || NendAdNativeMediaView.this.a) {
                                NendAdNativeMediaView nendAdNativeMediaView2 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView2.a(nendAdNativeMediaView2.i, z);
                                NendAdNativeMediaView nendAdNativeMediaView3 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView3.a = false;
                                nendAdNativeMediaView3.i();
                            } else {
                                NendAdNativeMediaView nendAdNativeMediaView4 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView4.b(nendAdNativeMediaView4.i);
                            }
                            break;
                        case 13:
                            if (NendAdNativeMediaView.this.e != null) {
                                NendAdNativeMediaView.this.d();
                                NendAdNativeMediaView.this.n();
                            } else if (NendAdNativeMediaView.this.d != null) {
                                NendAdNativeMediaView.this.f();
                            } else {
                                NendAdNativeMediaView.this.a(bundle.getInt("errorCode"), bundle.getString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE));
                            }
                            break;
                    }
                }
            }
        };
        this.w = new BroadcastReceiver() { // from class: net.nend.android.NendAdNativeMediaView.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_ON".equals(action)) {
                    NendAdNativeMediaView.this.l();
                    if (NendAdNativeMediaView.this.h) {
                        NendAdNativeMediaView.this.invalidate();
                        return;
                    }
                    return;
                }
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    NendAdNativeMediaView.this.n();
                    if (NendAdNativeMediaView.this.g.getVisibility() == 0) {
                        NendAdNativeMediaView.this.i();
                    }
                }
            }
        };
        a(context);
    }

    public NendAdNativeMediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.h = false;
        this.i = 0;
        this.j = 0;
        this.a = false;
        this.q = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NendAdNativeMediaView.this.a()) {
                    NendAdNativeMediaView.this.i();
                    NendAdNativeMediaView.this.k();
                }
            }
        };
        this.r = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NendAdNativeMediaView.this.a()) {
                    NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, NendAdNativeMediaView.this.d.d(), false);
                }
            }
        };
        this.s = new NendAdVideoView.a() { // from class: net.nend.android.NendAdNativeMediaView.8
            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onStartPlay() {
                NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.e.getWidth(), NendAdNativeMediaView.this.e.getHeight());
                if (NendAdNativeMediaView.this.p != null) {
                    NendAdNativeMediaView.this.p.onStartPlay(NendAdNativeMediaView.this);
                }
                NendAdNativeMediaView.this.b = f.PLAYING;
                if (NendAdNativeMediaView.this.f.getVisibility() != 0) {
                    e.a(NendAdNativeMediaView.this.getWidth(), NendAdNativeMediaView.this.getHeight(), NendAdNativeMediaView.this.f);
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(NendAdNativeMediaView.this.f, "alpha", 0.0f, 1.0f);
                    objectAnimatorOfFloat.setDuration(1000L);
                    objectAnimatorOfFloat.setStartDelay(0L);
                    objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: net.nend.android.NendAdNativeMediaView.8.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            NendAdNativeMediaView.this.f.setVisibility(0);
                        }
                    });
                    objectAnimatorOfFloat.start();
                }
                NendAdNativeMediaView.this.c();
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onPrepared() {
                NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                nendAdNativeMediaView.b(nendAdNativeMediaView.d.b());
                NendAdNativeMediaView.this.l();
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onCompletion(int i, boolean z) {
                NendAdNativeMediaView.this.a(i, z);
                j.a("onCompletion isWindowVisible: " + NendAdNativeMediaView.this.h);
                if (NendAdNativeMediaView.this.h && z) {
                    NendAdNativeMediaView.this.c(0);
                    NendAdNativeMediaView.this.n();
                }
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onProgress(int i, int i2) {
                NendAdNativeMediaView.this.j = i;
                NendAdNativeMediaView.this.a(i, i2);
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onError(int i, String str) {
                NendAdNativeMediaView.this.a(i, str);
            }
        };
        this.t = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!NendAdNativeMediaView.this.a() || NendAdNativeMediaView.this.d == null) {
                    return;
                }
                int i = AnonymousClass3.a[NendAdNativeMediaView.this.d.c().ordinal()];
                if (i == 1) {
                    NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                    nendAdNativeMediaView.setProgressDurationTime(nendAdNativeMediaView.e.getCurrentPosition());
                    Intent intent = new Intent(NendAdNativeMediaView.this.c, (Class<?>) FullscreenVideoPlayingActivity.class);
                    intent.putExtras(FullscreenVideoPlayingActivity.newBundle(NendAdNativeMediaView.this.i, NendAdNativeMediaView.this.d.a(), NendAdNativeMediaView.this.d.isMutePlayingFullscreen(), NendAdNativeMediaView.this.v));
                    if (NendAdNativeMediaView.this.c instanceof Activity) {
                        Activity activity = (Activity) NendAdNativeMediaView.this.c;
                        activity.startActivity(intent);
                        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        intent.setFlags(DriveFile.MODE_READ_ONLY);
                        NendAdNativeMediaView.this.c.startActivity(intent);
                    }
                    if (NendAdNativeMediaView.this.p != null) {
                        NendAdNativeMediaView.this.p.onOpenFullScreen(NendAdNativeMediaView.this);
                    }
                } else if (i == 2) {
                    NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, NendAdNativeMediaView.this.d.d(), false);
                }
                NendAdNativeMediaView.this.e.setCallback(null);
                NendAdNativeMediaView.this.j();
            }
        };
        this.v = new ResultReceiver(new Handler(Looper.getMainLooper())) { // from class: net.nend.android.NendAdNativeMediaView.11
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                super.onReceiveResult(i, bundle);
                if (NendAdNativeMediaView.this.d != null || i == 1 || i == 13) {
                    if (i == 1) {
                        if (NendAdNativeMediaView.this.e == null && NendAdNativeMediaView.this.d != null) {
                            NendAdNativeMediaView.this.f();
                        }
                        if (NendAdNativeMediaView.this.p != null) {
                            NendAdNativeMediaView.this.p.onCloseFullScreen(NendAdNativeMediaView.this);
                            return;
                        }
                        return;
                    }
                    if (i == 2) {
                        NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, bundle.getString(NendAdNativeMediaView.RESULT_CLICK_URL), true);
                        NendAdNativeMediaView.this.a = true;
                        return;
                    }
                    if (i == 3) {
                        NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c);
                        NendAdNativeMediaView.this.a = true;
                        return;
                    }
                    switch (i) {
                        case 11:
                            NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                            nendAdNativeMediaView.a(nendAdNativeMediaView.j, bundle.getInt(NendAdNativeMediaView.RESULT_REMAIN_MSEC));
                            break;
                        case 12:
                            boolean z = bundle.getBoolean(NendAdNativeMediaView.RESULT_IS_COMPLETION);
                            if (z || NendAdNativeMediaView.this.a) {
                                NendAdNativeMediaView nendAdNativeMediaView2 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView2.a(nendAdNativeMediaView2.i, z);
                                NendAdNativeMediaView nendAdNativeMediaView3 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView3.a = false;
                                nendAdNativeMediaView3.i();
                            } else {
                                NendAdNativeMediaView nendAdNativeMediaView4 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView4.b(nendAdNativeMediaView4.i);
                            }
                            break;
                        case 13:
                            if (NendAdNativeMediaView.this.e != null) {
                                NendAdNativeMediaView.this.d();
                                NendAdNativeMediaView.this.n();
                            } else if (NendAdNativeMediaView.this.d != null) {
                                NendAdNativeMediaView.this.f();
                            } else {
                                NendAdNativeMediaView.this.a(bundle.getInt("errorCode"), bundle.getString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE));
                            }
                            break;
                    }
                }
            }
        };
        this.w = new BroadcastReceiver() { // from class: net.nend.android.NendAdNativeMediaView.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_ON".equals(action)) {
                    NendAdNativeMediaView.this.l();
                    if (NendAdNativeMediaView.this.h) {
                        NendAdNativeMediaView.this.invalidate();
                        return;
                    }
                    return;
                }
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    NendAdNativeMediaView.this.n();
                    if (NendAdNativeMediaView.this.g.getVisibility() == 0) {
                        NendAdNativeMediaView.this.i();
                    }
                }
            }
        };
        a(context);
    }

    @TargetApi(21)
    public NendAdNativeMediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
        this.h = false;
        this.i = 0;
        this.j = 0;
        this.a = false;
        this.q = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NendAdNativeMediaView.this.a()) {
                    NendAdNativeMediaView.this.i();
                    NendAdNativeMediaView.this.k();
                }
            }
        };
        this.r = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NendAdNativeMediaView.this.a()) {
                    NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, NendAdNativeMediaView.this.d.d(), false);
                }
            }
        };
        this.s = new NendAdVideoView.a() { // from class: net.nend.android.NendAdNativeMediaView.8
            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onStartPlay() {
                NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.e.getWidth(), NendAdNativeMediaView.this.e.getHeight());
                if (NendAdNativeMediaView.this.p != null) {
                    NendAdNativeMediaView.this.p.onStartPlay(NendAdNativeMediaView.this);
                }
                NendAdNativeMediaView.this.b = f.PLAYING;
                if (NendAdNativeMediaView.this.f.getVisibility() != 0) {
                    e.a(NendAdNativeMediaView.this.getWidth(), NendAdNativeMediaView.this.getHeight(), NendAdNativeMediaView.this.f);
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(NendAdNativeMediaView.this.f, "alpha", 0.0f, 1.0f);
                    objectAnimatorOfFloat.setDuration(1000L);
                    objectAnimatorOfFloat.setStartDelay(0L);
                    objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: net.nend.android.NendAdNativeMediaView.8.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            NendAdNativeMediaView.this.f.setVisibility(0);
                        }
                    });
                    objectAnimatorOfFloat.start();
                }
                NendAdNativeMediaView.this.c();
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onPrepared() {
                NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                nendAdNativeMediaView.b(nendAdNativeMediaView.d.b());
                NendAdNativeMediaView.this.l();
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onCompletion(int i2, boolean z) {
                NendAdNativeMediaView.this.a(i2, z);
                j.a("onCompletion isWindowVisible: " + NendAdNativeMediaView.this.h);
                if (NendAdNativeMediaView.this.h && z) {
                    NendAdNativeMediaView.this.c(0);
                    NendAdNativeMediaView.this.n();
                }
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onProgress(int i2, int i3) {
                NendAdNativeMediaView.this.j = i2;
                NendAdNativeMediaView.this.a(i2, i3);
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onError(int i2, String str) {
                NendAdNativeMediaView.this.a(i2, str);
            }
        };
        this.t = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!NendAdNativeMediaView.this.a() || NendAdNativeMediaView.this.d == null) {
                    return;
                }
                int i2 = AnonymousClass3.a[NendAdNativeMediaView.this.d.c().ordinal()];
                if (i2 == 1) {
                    NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                    nendAdNativeMediaView.setProgressDurationTime(nendAdNativeMediaView.e.getCurrentPosition());
                    Intent intent = new Intent(NendAdNativeMediaView.this.c, (Class<?>) FullscreenVideoPlayingActivity.class);
                    intent.putExtras(FullscreenVideoPlayingActivity.newBundle(NendAdNativeMediaView.this.i, NendAdNativeMediaView.this.d.a(), NendAdNativeMediaView.this.d.isMutePlayingFullscreen(), NendAdNativeMediaView.this.v));
                    if (NendAdNativeMediaView.this.c instanceof Activity) {
                        Activity activity = (Activity) NendAdNativeMediaView.this.c;
                        activity.startActivity(intent);
                        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        intent.setFlags(DriveFile.MODE_READ_ONLY);
                        NendAdNativeMediaView.this.c.startActivity(intent);
                    }
                    if (NendAdNativeMediaView.this.p != null) {
                        NendAdNativeMediaView.this.p.onOpenFullScreen(NendAdNativeMediaView.this);
                    }
                } else if (i2 == 2) {
                    NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, NendAdNativeMediaView.this.d.d(), false);
                }
                NendAdNativeMediaView.this.e.setCallback(null);
                NendAdNativeMediaView.this.j();
            }
        };
        this.v = new ResultReceiver(new Handler(Looper.getMainLooper())) { // from class: net.nend.android.NendAdNativeMediaView.11
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i2, Bundle bundle) {
                super.onReceiveResult(i2, bundle);
                if (NendAdNativeMediaView.this.d != null || i2 == 1 || i2 == 13) {
                    if (i2 == 1) {
                        if (NendAdNativeMediaView.this.e == null && NendAdNativeMediaView.this.d != null) {
                            NendAdNativeMediaView.this.f();
                        }
                        if (NendAdNativeMediaView.this.p != null) {
                            NendAdNativeMediaView.this.p.onCloseFullScreen(NendAdNativeMediaView.this);
                            return;
                        }
                        return;
                    }
                    if (i2 == 2) {
                        NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, bundle.getString(NendAdNativeMediaView.RESULT_CLICK_URL), true);
                        NendAdNativeMediaView.this.a = true;
                        return;
                    }
                    if (i2 == 3) {
                        NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c);
                        NendAdNativeMediaView.this.a = true;
                        return;
                    }
                    switch (i2) {
                        case 11:
                            NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                            nendAdNativeMediaView.a(nendAdNativeMediaView.j, bundle.getInt(NendAdNativeMediaView.RESULT_REMAIN_MSEC));
                            break;
                        case 12:
                            boolean z = bundle.getBoolean(NendAdNativeMediaView.RESULT_IS_COMPLETION);
                            if (z || NendAdNativeMediaView.this.a) {
                                NendAdNativeMediaView nendAdNativeMediaView2 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView2.a(nendAdNativeMediaView2.i, z);
                                NendAdNativeMediaView nendAdNativeMediaView3 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView3.a = false;
                                nendAdNativeMediaView3.i();
                            } else {
                                NendAdNativeMediaView nendAdNativeMediaView4 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView4.b(nendAdNativeMediaView4.i);
                            }
                            break;
                        case 13:
                            if (NendAdNativeMediaView.this.e != null) {
                                NendAdNativeMediaView.this.d();
                                NendAdNativeMediaView.this.n();
                            } else if (NendAdNativeMediaView.this.d != null) {
                                NendAdNativeMediaView.this.f();
                            } else {
                                NendAdNativeMediaView.this.a(bundle.getInt("errorCode"), bundle.getString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE));
                            }
                            break;
                    }
                }
            }
        };
        this.w = new BroadcastReceiver() { // from class: net.nend.android.NendAdNativeMediaView.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_ON".equals(action)) {
                    NendAdNativeMediaView.this.l();
                    if (NendAdNativeMediaView.this.h) {
                        NendAdNativeMediaView.this.invalidate();
                        return;
                    }
                    return;
                }
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    NendAdNativeMediaView.this.n();
                    if (NendAdNativeMediaView.this.g.getVisibility() == 0) {
                        NendAdNativeMediaView.this.i();
                    }
                }
            }
        };
        a(context);
    }

    @TargetApi(21)
    public NendAdNativeMediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.h = false;
        this.i = 0;
        this.j = 0;
        this.a = false;
        this.q = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NendAdNativeMediaView.this.a()) {
                    NendAdNativeMediaView.this.i();
                    NendAdNativeMediaView.this.k();
                }
            }
        };
        this.r = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NendAdNativeMediaView.this.a()) {
                    NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, NendAdNativeMediaView.this.d.d(), false);
                }
            }
        };
        this.s = new NendAdVideoView.a() { // from class: net.nend.android.NendAdNativeMediaView.8
            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onStartPlay() {
                NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.e.getWidth(), NendAdNativeMediaView.this.e.getHeight());
                if (NendAdNativeMediaView.this.p != null) {
                    NendAdNativeMediaView.this.p.onStartPlay(NendAdNativeMediaView.this);
                }
                NendAdNativeMediaView.this.b = f.PLAYING;
                if (NendAdNativeMediaView.this.f.getVisibility() != 0) {
                    e.a(NendAdNativeMediaView.this.getWidth(), NendAdNativeMediaView.this.getHeight(), NendAdNativeMediaView.this.f);
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(NendAdNativeMediaView.this.f, "alpha", 0.0f, 1.0f);
                    objectAnimatorOfFloat.setDuration(1000L);
                    objectAnimatorOfFloat.setStartDelay(0L);
                    objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: net.nend.android.NendAdNativeMediaView.8.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            NendAdNativeMediaView.this.f.setVisibility(0);
                        }
                    });
                    objectAnimatorOfFloat.start();
                }
                NendAdNativeMediaView.this.c();
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onPrepared() {
                NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                nendAdNativeMediaView.b(nendAdNativeMediaView.d.b());
                NendAdNativeMediaView.this.l();
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onCompletion(int i3, boolean z) {
                NendAdNativeMediaView.this.a(i3, z);
                j.a("onCompletion isWindowVisible: " + NendAdNativeMediaView.this.h);
                if (NendAdNativeMediaView.this.h && z) {
                    NendAdNativeMediaView.this.c(0);
                    NendAdNativeMediaView.this.n();
                }
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onProgress(int i3, int i4) {
                NendAdNativeMediaView.this.j = i3;
                NendAdNativeMediaView.this.a(i3, i4);
            }

            @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
            public void onError(int i3, String str) {
                NendAdNativeMediaView.this.a(i3, str);
            }
        };
        this.t = new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!NendAdNativeMediaView.this.a() || NendAdNativeMediaView.this.d == null) {
                    return;
                }
                int i3 = AnonymousClass3.a[NendAdNativeMediaView.this.d.c().ordinal()];
                if (i3 == 1) {
                    NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                    nendAdNativeMediaView.setProgressDurationTime(nendAdNativeMediaView.e.getCurrentPosition());
                    Intent intent = new Intent(NendAdNativeMediaView.this.c, (Class<?>) FullscreenVideoPlayingActivity.class);
                    intent.putExtras(FullscreenVideoPlayingActivity.newBundle(NendAdNativeMediaView.this.i, NendAdNativeMediaView.this.d.a(), NendAdNativeMediaView.this.d.isMutePlayingFullscreen(), NendAdNativeMediaView.this.v));
                    if (NendAdNativeMediaView.this.c instanceof Activity) {
                        Activity activity = (Activity) NendAdNativeMediaView.this.c;
                        activity.startActivity(intent);
                        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        intent.setFlags(DriveFile.MODE_READ_ONLY);
                        NendAdNativeMediaView.this.c.startActivity(intent);
                    }
                    if (NendAdNativeMediaView.this.p != null) {
                        NendAdNativeMediaView.this.p.onOpenFullScreen(NendAdNativeMediaView.this);
                    }
                } else if (i3 == 2) {
                    NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, NendAdNativeMediaView.this.d.d(), false);
                }
                NendAdNativeMediaView.this.e.setCallback(null);
                NendAdNativeMediaView.this.j();
            }
        };
        this.v = new ResultReceiver(new Handler(Looper.getMainLooper())) { // from class: net.nend.android.NendAdNativeMediaView.11
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i3, Bundle bundle) {
                super.onReceiveResult(i3, bundle);
                if (NendAdNativeMediaView.this.d != null || i3 == 1 || i3 == 13) {
                    if (i3 == 1) {
                        if (NendAdNativeMediaView.this.e == null && NendAdNativeMediaView.this.d != null) {
                            NendAdNativeMediaView.this.f();
                        }
                        if (NendAdNativeMediaView.this.p != null) {
                            NendAdNativeMediaView.this.p.onCloseFullScreen(NendAdNativeMediaView.this);
                            return;
                        }
                        return;
                    }
                    if (i3 == 2) {
                        NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c, bundle.getString(NendAdNativeMediaView.RESULT_CLICK_URL), true);
                        NendAdNativeMediaView.this.a = true;
                        return;
                    }
                    if (i3 == 3) {
                        NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c);
                        NendAdNativeMediaView.this.a = true;
                        return;
                    }
                    switch (i3) {
                        case 11:
                            NendAdNativeMediaView nendAdNativeMediaView = NendAdNativeMediaView.this;
                            nendAdNativeMediaView.a(nendAdNativeMediaView.j, bundle.getInt(NendAdNativeMediaView.RESULT_REMAIN_MSEC));
                            break;
                        case 12:
                            boolean z = bundle.getBoolean(NendAdNativeMediaView.RESULT_IS_COMPLETION);
                            if (z || NendAdNativeMediaView.this.a) {
                                NendAdNativeMediaView nendAdNativeMediaView2 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView2.a(nendAdNativeMediaView2.i, z);
                                NendAdNativeMediaView nendAdNativeMediaView3 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView3.a = false;
                                nendAdNativeMediaView3.i();
                            } else {
                                NendAdNativeMediaView nendAdNativeMediaView4 = NendAdNativeMediaView.this;
                                nendAdNativeMediaView4.b(nendAdNativeMediaView4.i);
                            }
                            break;
                        case 13:
                            if (NendAdNativeMediaView.this.e != null) {
                                NendAdNativeMediaView.this.d();
                                NendAdNativeMediaView.this.n();
                            } else if (NendAdNativeMediaView.this.d != null) {
                                NendAdNativeMediaView.this.f();
                            } else {
                                NendAdNativeMediaView.this.a(bundle.getInt("errorCode"), bundle.getString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE));
                            }
                            break;
                    }
                }
            }
        };
        this.w = new BroadcastReceiver() { // from class: net.nend.android.NendAdNativeMediaView.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.SCREEN_ON".equals(action)) {
                    NendAdNativeMediaView.this.l();
                    if (NendAdNativeMediaView.this.h) {
                        NendAdNativeMediaView.this.invalidate();
                        return;
                    }
                    return;
                }
                if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    NendAdNativeMediaView.this.n();
                    if (NendAdNativeMediaView.this.g.getVisibility() == 0) {
                        NendAdNativeMediaView.this.i();
                    }
                }
            }
        };
        a(context);
    }

    private void a(Context context) {
        this.b = f.PREPARING;
        this.c = context;
        inflate(context, n.c(getContext(), "view_native_media"), this);
        this.k = n.b(getContext(), "native_media_row_videoview");
        this.e = (NendAdVideoView) findViewById(this.k);
        this.g = findViewById(n.b(getContext(), "native_media_row_replay_area"));
        FrameLayout frameLayout = (FrameLayout) findViewById(n.b(getContext(), "native_media_row_action_area"));
        frameLayout.findViewById(n.b(getContext(), "native_video_fullscreen_action_cta")).setVisibility(8);
        frameLayout.findViewById(n.b(getContext(), "native_video_fullscreen_action_toggle_volume")).setVisibility(8);
        this.f = (ImageView) frameLayout.findViewById(n.b(getContext(), "native_video_fullscreen_action_optout"));
        this.f.setVisibility(4);
    }

    private void a(boolean z) {
        if (z) {
            l();
            return;
        }
        n();
        View view = this.g;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        i();
    }

    private boolean b() {
        if (Build.VERSION.SDK_INT >= 24) {
            Context context = this.c;
            if (context instanceof Activity) {
                return !((Activity) context).isInMultiWindowMode();
            }
        }
        return true;
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        j.a("visibility: " + i);
        this.h = i == 0;
        a(this.h);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        j.a("hasWindowFocus: " + z);
        if (b()) {
            a(z);
            if (z) {
                invalidate();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.c.registerReceiver(this.w, new IntentFilter("android.intent.action.SCREEN_ON"));
        this.c.registerReceiver(this.w, new IntentFilter("android.intent.action.SCREEN_OFF"));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        StringBuilder sb = new StringBuilder();
        sb.append(" videoView object is ");
        sb.append(this.e != null ? "still allocated." : "destroyed.");
        j.a(sb.toString());
        if (this.e != null) {
            n();
            if (this.g.getVisibility() == 0) {
                i();
            }
            d();
        }
        try {
            this.c.unregisterReceiver(this.w);
        } catch (IllegalArgumentException unused) {
            j.a("Screen action receiver is already unregistered");
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        c();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.e == null || this.d == null) {
            return;
        }
        float width = getWidth();
        float height = getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        if (width == this.l && height == this.m) {
            return;
        }
        this.m = height;
        this.l = width;
        int videoOrientation = this.d.getVideoOrientation();
        e.a(this.c, this, width, height, videoOrientation);
        a(videoOrientation);
    }

    private void a(int i) {
        int iA = (int) e.a(this.e, i);
        this.n.a(iA);
        this.o.a(iA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.e.setCallback(null);
        this.e.e();
        this.e.f();
        this.e = null;
    }

    public void setMediaViewListener(NendAdNativeMediaViewListener nendAdNativeMediaViewListener) {
        this.p = nendAdNativeMediaViewListener;
    }

    public void setMedia(NendAdNativeVideo nendAdNativeVideo) {
        if (this.d != nendAdNativeVideo) {
            e();
        }
        this.d = nendAdNativeVideo;
        this.d.a(new NendAdNativeVideo.b() { // from class: net.nend.android.NendAdNativeMediaView.1
            @Override // net.nend.android.NendAdNativeVideo.b
            public void a() {
                NendAdNativeMediaView.this.n();
                NendAdNativeMediaView.this.e();
                NendAdNativeMediaView.this.d = null;
            }
        });
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.e != null) {
            d();
            this.b = f.PREPARING;
        }
        g();
        this.m = 0.0f;
        this.l = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        b bVarA = this.d.a();
        if (bVarA == null || !bVarA.a()) {
            a(NendVideoAdClientError.INVALID_AD_DATA.getCode(), NendVideoAdClientError.INVALID_AD_DATA.getMessage());
            return;
        }
        if (this.e == null) {
            this.e = (NendAdVideoView) findViewById(this.k);
        }
        this.e.setCallback(this.s);
        this.e.a(bVarA.g, true);
        this.e.setOnClickListener(this.t);
        this.f.setOnClickListener(new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NendAdNativeMediaView.this.d.a(NendAdNativeMediaView.this.c);
            }
        });
        h();
    }

    private void g() {
        View view = this.g;
        if (view != null) {
            removeView(view);
            this.g = null;
        }
    }

    private void h() {
        if (this.g == null) {
            this.g = e.a(this, this.c, this.d.a());
            addView(this.g, 1);
            ((ImageButton) findViewById(n.b(getContext(), "media_view_button_replay"))).setOnClickListener(this.q);
            this.n = (FontFitTextView) findViewById(n.b(getContext(), "description_media_view_button_replay"));
            this.n.setOnClickListener(this.q);
            ((ImageButton) findViewById(n.b(getContext(), "media_view_button_cta"))).setOnClickListener(this.r);
            this.o = (FontFitTextView) findViewById(n.b(getContext(), "description_media_view_button_cta"));
            this.o.setOnClickListener(this.r);
        }
        this.o.setText(this.d.getCallToActionText());
        if (this.b != f.COMPLETED) {
            c(8);
        }
        this.g.setOnClickListener(new View.OnClickListener() { // from class: net.nend.android.NendAdNativeMediaView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        setProgressDurationTime(0);
        NendAdNativeVideo nendAdNativeVideo = this.d;
        if (nendAdNativeVideo != null) {
            b(nendAdNativeVideo.b());
        }
        c(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        NendAdVideoView nendAdVideoView = this.e;
        if (nendAdVideoView != null) {
            nendAdVideoView.a(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        this.e.d();
        if (this.b != f.COMPLETED) {
            this.b = f.PAUSING;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.e.a()) {
            l();
        } else {
            this.e.setCallback(this.s);
            this.e.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i == 0) {
            a(this.d.getVideoOrientation());
        } else {
            this.m = 0.0f;
            this.l = 0.0f;
        }
        this.g.setVisibility(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        setProgressDurationTime(i - i2);
        if (this.d.a(this.i, false)) {
            this.d.b(this.c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        if (this.d.a(i, z)) {
            this.d.b(this.c);
        }
        setProgressDurationTime(i);
        if (z) {
            this.b = f.COMPLETED;
        } else if (this.b != f.COMPLETED) {
            this.b = f.PAUSING;
        }
        this.d.a(this.c, i, z);
        NendAdNativeMediaViewListener nendAdNativeMediaViewListener = this.p;
        if (nendAdNativeMediaViewListener != null) {
            if (z) {
                nendAdNativeMediaViewListener.onCompletePlay(this);
            } else {
                nendAdNativeMediaViewListener.onStopPlay(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        j.d("NendAdNativeMediaView: " + i + " :" + str);
        NendAdNativeMediaViewListener nendAdNativeMediaViewListener = this.p;
        if (nendAdNativeMediaViewListener != null) {
            nendAdNativeMediaViewListener.onError(i, str);
        }
    }

    /* JADX INFO: renamed from: net.nend.android.NendAdNativeMediaView$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[NendAdNativeVideo.VideoClickOption.values().length];

        static {
            try {
                a[NendAdNativeVideo.VideoClickOption.FullScreen.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[NendAdNativeVideo.VideoClickOption.LP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.u == null) {
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                this.u = new ViewTreeObserver.OnPreDrawListener() { // from class: net.nend.android.NendAdNativeMediaView.10
                    @Override // android.view.ViewTreeObserver.OnPreDrawListener
                    public boolean onPreDraw() {
                        NendAdNativeMediaView.this.m();
                        return true;
                    }
                };
                viewTreeObserver.addOnPreDrawListener(this.u);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.e == null || this.g.getVisibility() != 8) {
            return;
        }
        if (a()) {
            if (!this.e.a() || this.b == f.PLAYING) {
                return;
            }
            this.e.setCallback(this.s);
            this.e.setMute(true);
            this.e.c();
            this.b = f.PLAYING;
            return;
        }
        j();
    }

    @VisibleForTesting
    boolean a() {
        return this.h && net.nend.android.internal.ui.a.f.a(getRootView(), this, 50);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.u != null) {
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.u);
                this.u = null;
                NendAdVideoView nendAdVideoView = this.e;
                if (nendAdVideoView != null) {
                    setProgressDurationTime(nendAdVideoView.getCurrentPosition());
                    j();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgressDurationTime(int i) {
        this.i = i;
        j.a("progressDuration: " + this.i);
        NendAdNativeVideo nendAdNativeVideo = this.d;
        if (nendAdNativeVideo != null) {
            nendAdNativeVideo.a(this.i);
        }
    }
}
