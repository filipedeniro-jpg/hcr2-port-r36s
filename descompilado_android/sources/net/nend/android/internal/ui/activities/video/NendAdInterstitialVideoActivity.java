package net.nend.android.internal.ui.activities.video;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import net.nend.android.internal.ui.views.video.b;
import net.nend.android.internal.ui.views.video.d;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdInterstitialVideoActivity extends a<net.nend.android.internal.b.c.a> {

    @Nullable
    private ObjectAnimator h;
    private d i;
    private d.a j = new d.a() { // from class: net.nend.android.internal.ui.activities.video.NendAdInterstitialVideoActivity.1
        @Override // net.nend.android.internal.ui.views.video.d.a
        public void a() {
            NendAdInterstitialVideoActivity nendAdInterstitialVideoActivity = NendAdInterstitialVideoActivity.this;
            nendAdInterstitialVideoActivity.c = true;
            nendAdInterstitialVideoActivity.d();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: net.nend.android.internal.ui.activities.video.NendAdInterstitialVideoActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    NendAdInterstitialVideoActivity.this.a(NendAdInterstitialVideoActivity.this.getApplicationContext());
                }
            }, 1000L);
        }

        @Override // net.nend.android.internal.ui.views.video.d.a
        public void b() {
            NendAdInterstitialVideoActivity nendAdInterstitialVideoActivity = NendAdInterstitialVideoActivity.this;
            nendAdInterstitialVideoActivity.c = true;
            nendAdInterstitialVideoActivity.e();
        }
    };
    private boolean k = false;
    private boolean l = false;
    private b.InterfaceC0118b m = new b.InterfaceC0118b() { // from class: net.nend.android.internal.ui.activities.video.NendAdInterstitialVideoActivity.2
        @Override // net.nend.android.internal.ui.views.video.b.InterfaceC0118b
        public void a() {
            NendAdInterstitialVideoActivity.this.l = true;
            NendAdInterstitialVideoActivity.this.f();
        }
    };

    public static Bundle newBundle(net.nend.android.internal.b.c.a aVar, ResultReceiver resultReceiver, int i, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("videoAd", aVar);
        bundle.putParcelable("resultReceiver", resultReceiver);
        bundle.putInt("spotId", i);
        bundle.putBoolean("save_is_mute", z);
        return bundle;
    }

    @Override // net.nend.android.internal.ui.activities.video.a, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.g = getIntent().getBooleanExtra("save_is_mute", true);
        super.onCreate(bundle);
        if (!isFinishing() && this.a.getVisibility() == 0) {
            if (this.b != null) {
                this.b.setWebViewClientListener(this.m);
            }
            if (bundle == null) {
                a(((net.nend.android.internal.b.c.a) this.d).h);
            } else {
                a(Math.max(((net.nend.android.internal.b.c.a) this.d).h - net.nend.android.internal.utilities.video.b.a(this.e), 0));
            }
        }
    }

    @Override // net.nend.android.internal.ui.activities.video.a, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ObjectAnimator objectAnimator = this.h;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
            if (this.h.isStarted() || this.h.isRunning()) {
                this.h.cancel();
            }
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        c();
        super.onBackPressed();
    }

    private void a(int i) {
        this.h = ObjectAnimator.ofFloat(this.i, "alpha", 0.0f, 1.0f);
        this.h.setDuration(1000L);
        this.h.setStartDelay(i * 1000);
        this.h.addListener(new AnimatorListenerAdapter() { // from class: net.nend.android.internal.ui.activities.video.NendAdInterstitialVideoActivity.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                NendAdInterstitialVideoActivity.this.k = true;
                NendAdInterstitialVideoActivity.this.f();
            }
        });
        this.h.start();
    }

    @Override // net.nend.android.internal.ui.activities.video.a
    protected void a() {
        this.i = new d(this, ((net.nend.android.internal.b.c.a) this.d).i, this.j);
        this.i.setAlpha(0.0f);
        this.a.addView(this.i, new RelativeLayout.LayoutParams(-1, -2));
        super.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.b != null && this.l && this.k) {
            a(this.b, "showActionButton()");
        }
    }

    @Override // net.nend.android.internal.ui.activities.video.a
    protected void a(boolean z) {
        this.i.setHideCallToAction(z);
    }
}
