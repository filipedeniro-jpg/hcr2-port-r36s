package net.nend.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import net.nend.android.internal.b.a.a.b;
import net.nend.android.internal.utilities.e;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;
import net.nend.android.internal.utilities.m;
import net.nend.android.internal.utilities.n;

/* JADX INFO: loaded from: classes2.dex */
public final class NendAdView extends RelativeLayout implements b, net.nend.android.internal.ui.views.a.b.InterfaceC0114b, net.nend.android.internal.ui.views.b.a {
    static final /* synthetic */ boolean a = !NendAdView.class.desiredAssertionStatus();
    private int b;
    private String c;
    private float d;
    private net.nend.android.internal.b.a.a.a e;
    private net.nend.android.internal.b.a.b f;
    private NendAdListener g;
    private RelativeLayout h;
    private net.nend.android.internal.ui.views.a.b i;
    private net.nend.android.internal.ui.views.a.a j;
    private boolean k;
    private DisplayMetrics l;
    private NendError m;
    private net.nend.android.internal.ui.views.b n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;

    public NendAdView(Context context, int i, String str) {
        this(context, i, str, false);
    }

    public NendAdView(Context context, int i, String str, boolean z) {
        super(context, null, 0);
        this.d = 1.0f;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = false;
        this.q = -1;
        this.r = -1;
        a(context, i, str, z);
    }

    public NendAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NendAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 1.0f;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = false;
        this.q = -1;
        this.r = -1;
        if (attributeSet == null) {
            throw new NullPointerException(k.ERR_INVALID_ATTRIBUTE_SET.b());
        }
        TypedArray typedArrayA = n.a(context, attributeSet, i);
        int i2 = typedArrayA.getInt(n.a(context, "NendSpotId"), 0);
        String string = typedArrayA.getString(n.a(context, "NendApiKey"));
        boolean z = typedArrayA.getBoolean(n.a(context, "NendAdjustSize"), false);
        boolean z2 = typedArrayA.getBoolean(n.a(context, "NendReloadable"), true);
        typedArrayA.recycle();
        a(context, i2, string, z);
        if (!z2) {
            pause();
        }
        loadAd();
    }

    private void a(Context context, int i, String str, boolean z) {
        Context context2 = (Context) m.a(context);
        e.a(context2);
        this.l = new DisplayMetrics();
        ((WindowManager) context2.getSystemService("window")).getDefaultDisplay().getMetrics(this.l);
        this.d = this.l.density;
        this.o = z;
        this.e = new net.nend.android.internal.b.a.a(context2, i, str, this.l);
        this.b = i;
        this.c = str;
        this.e.a(this);
        this.f = new net.nend.android.internal.b.a.b(this.e);
        this.n = new net.nend.android.internal.ui.views.b(getContext());
        this.p = true;
    }

    public void loadAd() {
        m();
        this.f.a();
    }

    public void setListener(NendAdListener nendAdListener) {
        this.g = nendAdListener;
    }

    public void removeListener() {
        this.g = null;
    }

    public void resume() {
        j.a("resume!");
        m();
        this.f.a(true);
        if (this.e.a() == net.nend.android.internal.a.EnumC0106a.WEBVIEW) {
            d();
        } else if (this.e.a() == net.nend.android.internal.a.EnumC0106a.DYNAMICRETARGETING) {
            e();
        }
    }

    public void pause() {
        j.a("pause!");
        m();
        this.f.a(false);
        if (this.e.a() == net.nend.android.internal.a.EnumC0106a.WEBVIEW || this.e.a() == net.nend.android.internal.a.EnumC0106a.DYNAMICRETARGETING) {
            k();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.e == null) {
            this.e = new net.nend.android.internal.b.a.a(getContext(), this.b, this.c, this.l);
            this.e.a(this);
            this.f = new net.nend.android.internal.b.a.b(this.e);
            loadAd();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        j.a("onDetachedFromWindow!");
        this.p = true;
        f();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.f.b(true);
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        j.a("onWindowFocusChanged!" + String.valueOf(z));
        super.onWindowFocusChanged(z);
        net.nend.android.internal.b.a.b bVar = this.f;
        if (bVar == null) {
            return;
        }
        bVar.b(z);
        if (z && this.k) {
            this.k = false;
            NendAdListener nendAdListener = this.g;
            if (nendAdListener != null) {
                nendAdListener.onDismissScreen(this);
            }
        }
    }

    @Override // net.nend.android.internal.b.a.a.b
    public void onReceiveAd() {
        j.a("onReceive!");
        if (!a && this.e == null) {
            throw new AssertionError();
        }
        if (l()) {
            return;
        }
        this.m = null;
        if (this.p) {
            c();
            this.p = false;
        }
        int i = AnonymousClass1.a[this.e.a().ordinal()];
        if (i == 1) {
            this.n.a(this.e, this);
            return;
        }
        if (i == 2) {
            this.f.b();
            e();
        } else {
            if (i == 3) {
                d();
                NendAdListener nendAdListener = this.g;
                if (nendAdListener != null) {
                    nendAdListener.onReceiveAd(this);
                    return;
                }
                return;
            }
            onFailedToReceiveAd(NendError.INVALID_RESPONSE_TYPE);
        }
    }

    /* JADX INFO: renamed from: net.nend.android.NendAdView$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[net.nend.android.internal.a.EnumC0106a.values().length];

        static {
            try {
                a[net.nend.android.internal.a.EnumC0106a.ADVIEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[net.nend.android.internal.a.EnumC0106a.DYNAMICRETARGETING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[net.nend.android.internal.a.EnumC0106a.WEBVIEW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // net.nend.android.internal.b.a.a.b
    public void onFailedToReceiveAd(NendError nendError) {
        net.nend.android.internal.b.a.b bVar;
        j.a("onFailedToReceive!");
        if (!a && this.f == null) {
            throw new AssertionError();
        }
        if (l() || (bVar = this.f) == null) {
            return;
        }
        if (!bVar.b()) {
            j.a("Failed to reload.");
        }
        NendAdListener nendAdListener = this.g;
        if (nendAdListener != null) {
            this.m = nendError;
            nendAdListener.onFailedToReceiveAd(this);
        }
    }

    private boolean a(int i, int i2) {
        int iG = this.e.g();
        int iF = this.e.f();
        if (i == 320 && i2 == 48) {
            i2 = 50;
        }
        return (iG == i2 && iF == i) || (iG * 2 == i2 && iF * 2 == i);
    }

    private void a() {
        net.nend.android.internal.ui.views.a.b bVar;
        removeAllViews();
        k();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (this.h == null || (bVar = this.i) == null || !bVar.a()) {
            this.h = new RelativeLayout(getContext());
            this.h.addView(this.n, layoutParams);
            this.i = new net.nend.android.internal.ui.views.a.b(getContext(), this.e.l(), this.b, this);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(11);
            this.h.addView(this.i, layoutParams2);
        }
        this.i.bringToFront();
        addView(this.h, layoutParams);
    }

    private void b() {
        removeAllViews();
        j();
        if (this.j == null) {
            this.j = new net.nend.android.internal.ui.views.a.a(getContext());
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (this.e.f() * this.d), (int) (this.e.g() * this.d));
        layoutParams.addRule(13);
        addView(this.j, layoutParams);
    }

    private void c() {
        int iF = this.e.f();
        int iG = this.e.g();
        if (b(iF, iG)) {
            float fMin = Math.min(Math.min(this.l.widthPixels, this.l.heightPixels) / (this.d * 320.0f), 1.5f);
            float f = this.d;
            this.q = (int) ((iF * f * fMin) + 0.5f);
            this.r = (int) ((iG * f * fMin) + 0.5f);
        } else {
            float f2 = this.d;
            this.q = (int) ((iF * f2) + 0.5f);
            this.r = (int) ((iG * f2) + 0.5f);
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        if (layoutParams.width == this.q && layoutParams.height == this.r) {
            return;
        }
        layoutParams.width = this.q;
        layoutParams.height = this.r;
        super.setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        int i;
        int i2;
        if (layoutParams != null && (i = this.q) > 0 && (i2 = this.r) > 0) {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        super.setLayoutParams(layoutParams);
    }

    private boolean b(int i, int i2) {
        return this.o && ((320 == i && 50 == i2) || ((320 == i && 100 == i2) || ((300 == i && 100 == i2) || (300 == i && 250 == i2))));
    }

    private void d() {
        if (!a && this.e == null) {
            throw new AssertionError();
        }
        b();
        this.j.loadUrl(this.e.d());
    }

    private void e() {
        if (!a && this.e == null) {
            throw new AssertionError();
        }
        if (this.j == null) {
            this.j = new net.nend.android.internal.ui.views.a.a(getContext());
        }
        this.j.a(this.e.d(), this);
    }

    private void f() {
        g();
        h();
        removeListener();
        i();
    }

    private void g() {
        net.nend.android.internal.b.a.b bVar = this.f;
        if (bVar != null) {
            bVar.c();
            this.f = null;
        }
    }

    private void h() {
        net.nend.android.internal.b.a.a.a aVar = this.e;
        if (aVar != null) {
            aVar.q();
            this.e = null;
        }
    }

    private void i() {
        removeAllViews();
        j();
        k();
    }

    private void j() {
        RelativeLayout relativeLayout = this.h;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
            this.h = null;
        }
        net.nend.android.internal.ui.views.a.b bVar = this.i;
        if (bVar != null) {
            bVar.setImageDrawable(null);
            this.i.b();
            this.i = null;
        }
        net.nend.android.internal.ui.views.b bVar2 = this.n;
        if (bVar2 != null) {
            bVar2.b();
        }
    }

    private void k() {
        net.nend.android.internal.ui.views.a.a aVar = this.j;
        if (aVar != null) {
            aVar.stopLoading();
            this.j.getSettings().setJavaScriptEnabled(false);
            this.j.setWebChromeClient(null);
            this.j.setWebViewClient(null);
            removeView(this.j);
            this.j.removeAllViews();
            this.j.destroy();
            this.j = null;
        }
    }

    private boolean l() {
        return this.e == null;
    }

    private void m() {
        if (this.f == null) {
            if (this.e == null) {
                this.e = new net.nend.android.internal.b.a.a(getContext(), this.b, this.c, this.l);
                this.e.a(this);
            }
            this.f = new net.nend.android.internal.b.a.b(this.e);
        }
    }

    public NendError getNendError() {
        return this.m;
    }

    @Override // net.nend.android.internal.ui.views.b.a
    public boolean onValidation(int i, int i2) {
        if (l()) {
            return false;
        }
        if (a(i, i2)) {
            return true;
        }
        onFailedToReceiveAd(NendError.AD_SIZE_DIFFERENCES);
        return false;
    }

    @Override // net.nend.android.internal.ui.views.b.a
    public void onClickAd() {
        this.k = true;
        NendAdListener nendAdListener = this.g;
        if (nendAdListener != null) {
            nendAdListener.onClick(this);
        }
    }

    @Override // net.nend.android.internal.ui.views.b.a
    public void onSuccess() {
        net.nend.android.internal.b.a.a.a aVar;
        if (this.f == null || (aVar = this.e) == null) {
            return;
        }
        if (aVar.a() == net.nend.android.internal.a.EnumC0106a.DYNAMICRETARGETING) {
            b();
        } else {
            a();
        }
        this.f.b();
        NendAdListener nendAdListener = this.g;
        if (nendAdListener != null) {
            nendAdListener.onReceiveAd(this);
        }
    }

    @Override // net.nend.android.internal.ui.views.b.a
    public void onFailure() {
        onFailedToReceiveAd(NendError.FAILED_AD_DOWNLOAD);
    }

    @Override // net.nend.android.internal.ui.views.a.b.InterfaceC0114b
    public void onInformationButtonClick() {
        this.k = true;
        NendAdListener nendAdListener = this.g;
        if (nendAdListener == null || !(nendAdListener instanceof NendAdInformationListener)) {
            return;
        }
        ((NendAdInformationListener) nendAdListener).onInformationButtonClick(this);
    }

    public enum NendError {
        AD_SIZE_TOO_LARGE(840, "Ad size will not fit on screen."),
        INVALID_RESPONSE_TYPE(841, "Response type is invalid."),
        FAILED_AD_REQUEST(842, "Failed to Ad request."),
        FAILED_AD_DOWNLOAD(843, "Failed to Ad download."),
        AD_SIZE_DIFFERENCES(844, "Ad size differences.");

        private final int a;
        private final String b;

        NendError(int i, String str) {
            this.a = i;
            this.b = str;
        }

        public String getMessage() {
            return this.b;
        }

        public int getCode() {
            return this.a;
        }
    }
}
