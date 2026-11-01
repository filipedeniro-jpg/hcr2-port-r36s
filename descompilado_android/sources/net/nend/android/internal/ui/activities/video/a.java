package net.nend.android.internal.ui.activities.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import net.nend.android.internal.b.c.d;
import net.nend.android.internal.ui.views.video.NendAdVideoView;
import net.nend.android.internal.ui.views.video.b;
import net.nend.android.internal.utilities.c;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.n;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NendAdVideoActivity.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a<Ad extends d> extends Activity implements NendAdVideoView.a {
    public static final int RESULT_CODE_AD_CLICK = 5;
    public static final int RESULT_CODE_AD_CLOSE = 1;
    public static final int RESULT_CODE_AD_FAILED_PLAY = 7;
    public static final int RESULT_CODE_AD_SHOWN = 2;
    public static final int RESULT_CODE_AD_START = 3;
    public static final int RESULT_CODE_AD_STOP = 4;
    public static final int RESULT_CODE_INFO_CLICK = 6;
    public static final String RESULT_DATA_KEY_VIDEO_IS_COMPLETION = "videoIsCompletion";
    protected NendAdVideoView a;

    @Nullable
    protected b b;
    protected boolean c;
    protected Ad d;
    protected int e;
    protected ResultReceiver f;

    @VisibleForTesting
    boolean g;
    private FrameLayout h;
    private b i;
    private net.nend.android.internal.ui.views.video.a j;
    private int k;
    private boolean l;
    private boolean m;
    private float p;

    @Nullable
    private Rect q;
    private ExecutorService u;
    private net.nend.android.internal.utilities.video.a w;
    private boolean n = false;
    private boolean o = false;
    private BlockingQueue<net.nend.android.internal.ui.a.d> r = new LinkedBlockingQueue();
    private final Runnable s = new Runnable() { // from class: net.nend.android.internal.ui.activities.video.a.1
        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    final net.nend.android.internal.ui.a.d dVar = (net.nend.android.internal.ui.a.d) a.this.r.take();
                    a.this.runOnUiThread(new Runnable() { // from class: net.nend.android.internal.ui.activities.video.a.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = AnonymousClass7.a[dVar.a().ordinal()];
                            if (i == 1) {
                                if (dVar.c().equals("html_on_playing")) {
                                    a.this.f();
                                    return;
                                } else {
                                    a.this.g();
                                    return;
                                }
                            }
                            if (i == 2) {
                                a.this.a((String) dVar.b());
                            } else {
                                if (i == 3) {
                                    a.this.h();
                                    return;
                                }
                                if (i == 4) {
                                    a.this.i();
                                } else {
                                    if (i != 5) {
                                        return;
                                    }
                                    a.this.a((String) dVar.b(), dVar.c());
                                }
                            }
                        }
                    });
                } catch (InterruptedException unused) {
                    if (a.this.isFinishing()) {
                        return;
                    }
                }
            }
        }
    };
    private b.InterfaceC0118b t = new b.InterfaceC0118b() { // from class: net.nend.android.internal.ui.activities.video.a.2
        @Override // net.nend.android.internal.ui.views.video.b.InterfaceC0118b
        public void a() {
            a.this.n = true;
            if (a.this.a.getVisibility() == 8) {
                a aVar = a.this;
                aVar.a(aVar.i, "showNendVideoEndcard()");
            }
        }
    };
    private net.nend.android.internal.ui.views.video.a.InterfaceC0117a v = new net.nend.android.internal.ui.views.video.a.InterfaceC0117a() { // from class: net.nend.android.internal.ui.activities.video.a.3
        @Override // net.nend.android.internal.ui.views.video.a.InterfaceC0117a
        public void a(boolean z) {
            a.this.a.setMute(z);
            a.this.g = z;
        }
    };

    protected void a(boolean z) {
    }

    /* JADX INFO: renamed from: net.nend.android.internal.ui.activities.video.a$7, reason: invalid class name */
    /* JADX INFO: compiled from: NendAdVideoActivity.java */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[net.nend.android.internal.ui.a.d.a.values().length];

        static {
            try {
                a[net.nend.android.internal.ui.a.d.a.CLICK_AD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[net.nend.android.internal.ui.a.d.a.VIDEO_RECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[net.nend.android.internal.ui.a.d.a.CLICK_INFORMATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[net.nend.android.internal.ui.a.d.a.CLICK_CLOSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[net.nend.android.internal.ui.a.d.a.VIEW_SOURCE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.u = Executors.newSingleThreadExecutor();
        this.u.execute(this.s);
        boolean z = false;
        this.o = false;
        if (bundle == null) {
            Intent intent = getIntent();
            this.d = (Ad) intent.getParcelableExtra("videoAd");
            this.f = (ResultReceiver) intent.getParcelableExtra("resultReceiver");
            if (this.d == null || this.f == null) {
                j.d("Failed to showAd. It may have failed with loadAd.");
                finish();
                return;
            }
            this.k = intent.getIntExtra("spotId", 0);
        } else {
            this.d = (Ad) bundle.getParcelable("save_videoAd");
            this.f = (ResultReceiver) bundle.getParcelable("save_result_receiver");
            this.e = bundle.getInt("save_video_played_duration");
            z = bundle.getBoolean("save_state_showing_endcard");
            this.l = bundle.getBoolean("save_is_completion_event_start");
            this.m = bundle.getBoolean("save_is_completion_event_view");
            this.g = bundle.getBoolean("save_is_mute");
            this.w = (net.nend.android.internal.utilities.video.a) bundle.getParcelable("endcard_display_time");
        }
        if (!this.d.a()) {
            j.d("Failed to showAd. Because required ad data is not found or ad is expired.");
            this.f.send(7, null);
            finish();
            return;
        }
        b(z);
        if (!z) {
            this.p = getResources().getDisplayMetrics().density;
            a(this.d.l, getResources().getConfiguration().orientation);
            this.w = new net.nend.android.internal.utilities.video.a();
        }
        if (bundle == null) {
            this.f.send(2, null);
        }
        this.o = true;
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.a.getVisibility() == 0) {
            this.a.c();
        } else {
            this.w.a();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= 24) {
            j();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.a.getVisibility() == 0) {
            this.a.d();
        } else {
            this.w.b();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.u.shutdownNow();
        if (isFinishing() && this.o) {
            this.f.send(1, null);
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("save_videoAd", this.d);
        bundle.putParcelable("save_result_receiver", this.f);
        bundle.putInt("save_video_played_duration", this.e);
        bundle.putBoolean("save_state_showing_endcard", this.a.getVisibility() == 8);
        bundle.putBoolean("save_is_completion_event_start", this.l);
        bundle.putBoolean("save_is_completion_event_view", this.m);
        bundle.putBoolean("save_is_mute", this.g);
        bundle.putParcelable("endcard_display_time", this.w);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z || Build.VERSION.SDK_INT < 19) {
            return;
        }
        k();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.a.getVisibility() != 8) {
            a(this.d.l, configuration.orientation);
        }
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (z) {
            j();
        } else {
            k();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.q = new Rect((int) (jSONObject.getInt(TtmlNode.LEFT) * this.p), (int) (jSONObject.getInt("top") * this.p), (int) (jSONObject.getInt(TtmlNode.RIGHT) * this.p), (int) (jSONObject.getInt("bottom") * this.p));
            if (this.d.c != getResources().getConfiguration().orientation) {
                o();
            }
        } catch (JSONException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.c = true;
        d();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: net.nend.android.internal.ui.activities.video.a.4
            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                aVar.a(aVar.getApplicationContext());
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        b bVar;
        if (str2.equals("html_on_playing") && (bVar = this.b) != null && bVar.b(str)) {
            d();
        } else if (str2.equals("end_card") && this.i.b(str)) {
            d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        a((Context) this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.f.send(6, null);
        g.a().a(new g.d(this), new g.a<String>() { // from class: net.nend.android.internal.ui.activities.video.a.5
            @Override // net.nend.android.internal.utilities.g.a
            public void a(String str, Exception exc) {
                net.nend.android.internal.utilities.d.a(a.this, "https://www.nend.net/privacy/optsdkgate?uid=" + c.c(a.this) + "&spot=" + a.this.k + "&gaid=" + str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        d();
    }

    private void j() {
        getWindow().getDecorView().setSystemUiVisibility(0);
    }

    private void k() {
        if (Build.VERSION.SDK_INT < 24 || !isInMultiWindowMode()) {
            View decorView = getWindow().getDecorView();
            if (Build.VERSION.SDK_INT >= 19) {
                decorView.setSystemUiVisibility(5894);
            }
        }
    }

    private void a(int i, int i2) {
        if (i != 1) {
            if (i == 2) {
                int i3 = this.d.c;
                if (i3 == 1) {
                    setRequestedOrientation(7);
                } else if (i3 == 2) {
                    setRequestedOrientation(6);
                }
            } else if (i == 3 || i == 4) {
                int i4 = this.d.c;
                if (i4 == 1) {
                    setRequestedOrientation(6);
                } else if (i4 == 2) {
                    setRequestedOrientation(7);
                }
                if (i2 == this.d.c) {
                    a(false);
                    this.a.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                } else {
                    a(true);
                    if (this.q != null) {
                        o();
                    }
                }
            }
        }
        j.a("displayType: " + i + ", deviceOrientation: " + i2);
    }

    private void b(boolean z) {
        this.h = new FrameLayout(this);
        this.h.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        setContentView(this.h);
        m();
        if ((!z && this.d.l == 3) || this.d.l == 4) {
            n();
        }
        l();
        a();
        if (z) {
            this.i.bringToFront();
            this.a.setVisibility(8);
        } else {
            this.a.setVisibility(0);
            this.a.setUpVideo(this.d.g);
        }
    }

    private void l() {
        this.a = new NendAdVideoView(this);
        this.a.setCallback(this);
        this.a.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.h.addView(this.a, new FrameLayout.LayoutParams(-1, -1));
        this.a.setOnClickListener(new View.OnClickListener() { // from class: net.nend.android.internal.ui.activities.video.a.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (a.this.j.getVisibility() == 0) {
                    a.this.j.setVisibility(8);
                } else {
                    a.this.j.setVisibility(0);
                }
            }
        });
    }

    private void m() {
        this.i = new b(this, this.r, "end_card");
        this.i.setWebViewClientListener(this.t);
        this.i.a(this.d.o);
        this.h.addView(this.i, new FrameLayout.LayoutParams(-1, -1));
    }

    private void n() {
        if (this.b == null) {
            this.b = new b(this, this.r, "html_on_playing");
            this.b.a(this.d.p);
            this.h.addView(this.b, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    private void o() {
        Rect rect = this.q;
        if (rect != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(rect.width(), this.q.height());
            layoutParams.setMargins(this.q.left, this.q.top, 0, 0);
            this.a.setLayoutParams(layoutParams);
        }
    }

    private void p() {
        if (this.n) {
            a(this.i, "showNendVideoEndcard()");
        }
    }

    protected void a(WebView webView, String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            webView.evaluateJavascript(str, null);
            return;
        }
        webView.loadUrl("javascript:" + str);
    }

    protected void a() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(n.f(this, "nend_video_ad_overlay_elements_margin"));
        this.j = new net.nend.android.internal.ui.views.video.a(this, this.v);
        this.j.setPadding(0, 0, 0, dimensionPixelSize);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        this.a.addView(this.j, layoutParams);
        this.j.setCheckToggleButton(!this.g);
        this.j.setVisibility(8);
    }

    void a(b bVar) {
        bVar.stopLoading();
        bVar.getSettings().setJavaScriptEnabled(false);
        bVar.removeJavascriptInterface("nendSDK");
        bVar.setWebViewClient(null);
        bVar.setWebChromeClient(null);
        bVar.destroy();
    }

    void b() {
        this.a.e();
        this.a.f();
    }

    void c() {
        this.h.removeAllViews();
        a(this.i);
        b bVar = this.b;
        if (bVar != null) {
            a(bVar);
            this.b = null;
        }
        b();
        net.nend.android.internal.c.f.c.a(this, this.d.a, this.w);
    }

    void d() {
        c();
        finish();
    }

    void e() {
        this.a.e();
        this.a.setVisibility(8);
        this.i.bringToFront();
        p();
        b bVar = this.b;
        if (bVar != null) {
            this.h.removeView(bVar);
            a(this.b);
            this.b = null;
        }
        this.w.a();
    }

    void a(Context context) {
        this.f.send(5, null);
        net.nend.android.internal.c.f.c.c(context, this.d.a);
        net.nend.android.internal.utilities.d.a(context, this.d.b);
    }

    @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
    public void onStartPlay() {
        this.a.setMute(this.g);
        this.f.send(3, null);
        if (this.l) {
            return;
        }
        this.l = true;
        net.nend.android.internal.c.f.c.a(this, this.d.a);
    }

    @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
    public void onPrepared() {
        this.a.a(this.e);
        this.a.c();
    }

    @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
    public void onCompletion(int i, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(RESULT_DATA_KEY_VIDEO_IS_COMPLETION, z);
        this.f.send(4, bundle);
        net.nend.android.internal.c.f.c.a(this, this.d.a, z, this.c, i);
        if (!this.m && z && this.d.e == -1) {
            this.m = true;
            net.nend.android.internal.c.f.c.b(this, this.d.a);
        }
        if (z) {
            e();
        }
    }

    @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
    public void onProgress(int i, int i2) {
        int i3 = i - i2;
        this.e = i3;
        if (this.d.e > -1 && net.nend.android.internal.utilities.video.b.a(i3) > this.d.e && !this.m) {
            this.m = true;
            net.nend.android.internal.c.f.c.b(this, this.d.a);
        }
        this.j.setCounterText(String.valueOf(net.nend.android.internal.utilities.video.b.a(i2)));
    }

    @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
    public void onError(int i, String str) {
        j.d("NendAd internal error:" + str);
        this.f.send(7, null);
        d();
    }
}
