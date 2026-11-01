package net.nend.android.internal.ui.views.b;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import net.nend.android.NendAdInterstitial;
import net.nend.android.internal.utilities.d;
import net.nend.android.internal.utilities.k;

/* JADX INFO: compiled from: NendAdInterstitialView.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends RelativeLayout implements View.OnClickListener, net.nend.android.internal.ui.views.b.b.a {
    static final /* synthetic */ boolean a = !a.class.desiredAssertionStatus();
    private c b;
    private b c;
    private InterfaceC0115a d;
    private net.nend.android.internal.ui.views.b.b e;
    private net.nend.android.internal.ui.views.b.b f;
    private ImageView g;
    private RelativeLayout h;
    private RelativeLayout.LayoutParams i;
    private RelativeLayout.LayoutParams j;
    private RelativeLayout.LayoutParams k;
    private RelativeLayout.LayoutParams l;
    private boolean m;
    private NendAdInterstitial.NendAdInterstitialClickType n;

    /* JADX INFO: renamed from: net.nend.android.internal.ui.views.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NendAdInterstitialView.java */
    public interface InterfaceC0115a {
        void a();
    }

    /* JADX INFO: compiled from: NendAdInterstitialView.java */
    public interface b {
        void a(NendAdInterstitial.NendAdInterstitialStatusCode nendAdInterstitialStatusCode);
    }

    /* JADX INFO: compiled from: NendAdInterstitialView.java */
    public interface c {
        void a(NendAdInterstitial.NendAdInterstitialClickType nendAdInterstitialClickType);
    }

    public a(Context context, net.nend.android.internal.c.d.b bVar) {
        super(context);
        this.m = false;
        this.n = null;
        float f = getResources().getDisplayMetrics().density;
        int iF = (int) (bVar.f() * f);
        int iG = (int) (bVar.g() * f);
        int iH = (int) (bVar.h() * f);
        int i = (int) (bVar.i() * f);
        if (iF == 0 || iG == 0 || iH == 0 || i == 0) {
            throw new IllegalArgumentException(k.ERR_INVALID_RESPONSE.b());
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iF, iG);
        layoutParams.addRule(13);
        this.e = new net.nend.android.internal.ui.views.b.b(context, layoutParams, this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(iH, i);
        layoutParams2.addRule(13);
        this.f = new net.nend.android.internal.ui.views.b.b(context, layoutParams2, this);
        this.k = new RelativeLayout.LayoutParams(-2, -2);
        this.k.addRule(11);
        this.l = new RelativeLayout.LayoutParams(-2, -2);
        this.l.addRule(11);
        Bitmap bitmapA = net.nend.android.internal.utilities.a.a(getContext(), "nend_button_cancel.png");
        this.g = new ImageView(getContext());
        this.g.setImageBitmap(bitmapA);
        this.g.setOnClickListener(this);
        if (!a && bitmapA == null) {
            throw new AssertionError();
        }
        int width = (bitmapA.getWidth() * 2) / 3;
        this.i = new RelativeLayout.LayoutParams(iF + width, iG + width);
        this.i.addRule(13);
        this.j = new RelativeLayout.LayoutParams(iH + (bitmapA.getWidth() * 2), i);
        this.j.addRule(13);
        this.h = new RelativeLayout(getContext());
        if (bVar.d() == 0 || bVar.d() == 2) {
            setFocusable(true);
            setFocusableInTouchMode(true);
            setOnClickListener(this);
        }
        this.h.addView(this.e, 0);
        this.h.addView(this.f, 1);
        this.h.addView(this.g, 2);
        addView(this.h);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.n = NendAdInterstitial.NendAdInterstitialClickType.CLOSE;
        a();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setOrientation(configuration.orientation);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            return;
        }
        NendAdInterstitial.NendAdInterstitialClickType nendAdInterstitialClickType = this.n;
        if (nendAdInterstitialClickType == null) {
            this.n = NendAdInterstitial.NendAdInterstitialClickType.CLOSE;
            a();
        } else if (nendAdInterstitialClickType == NendAdInterstitial.NendAdInterstitialClickType.DOWNLOAD || this.n == NendAdInterstitial.NendAdInterstitialClickType.INFORMATION) {
            a();
        }
        c cVar = this.b;
        if (cVar != null) {
            cVar.a(this.n);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.m = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.m = false;
    }

    public void setOrientation(int i) {
        RelativeLayout.LayoutParams layoutParams;
        RelativeLayout.LayoutParams layoutParams2;
        if (i == 1) {
            layoutParams = this.i;
            layoutParams2 = this.k;
            this.f.setVisibility(8);
            this.e.setVisibility(0);
        } else {
            layoutParams = this.j;
            layoutParams2 = this.l;
            this.e.setVisibility(8);
            this.f.setVisibility(0);
        }
        this.g.setLayoutParams(layoutParams2);
        this.h.setLayoutParams(layoutParams);
        this.h.invalidate();
    }

    public boolean a() {
        InterfaceC0115a interfaceC0115a = this.d;
        if (interfaceC0115a == null) {
            return false;
        }
        interfaceC0115a.a();
        return true;
    }

    public boolean b() {
        return this.m;
    }

    public void a(String str) {
        this.e.a(str + 1);
        this.f.a(str + 2);
    }

    public boolean c() {
        int i = getResources().getConfiguration().orientation;
        if (i == 1 && this.e.getStatusCode() == net.nend.android.internal.ui.views.b.b.EnumC0116b.SUCCESS) {
            return true;
        }
        return i == 2 && this.f.getStatusCode() == net.nend.android.internal.ui.views.b.b.EnumC0116b.SUCCESS;
    }

    public NendAdInterstitial.NendAdInterstitialShowResult getStatus() {
        if (this.e.getStatusCode() == net.nend.android.internal.ui.views.b.b.EnumC0116b.FAILED || this.f.getStatusCode() == net.nend.android.internal.ui.views.b.b.EnumC0116b.FAILED) {
            return NendAdInterstitial.NendAdInterstitialShowResult.AD_DOWNLOAD_INCOMPLETE;
        }
        return NendAdInterstitial.NendAdInterstitialShowResult.AD_LOAD_INCOMPLETE;
    }

    public void setOnClickListener(c cVar) {
        this.b = cVar;
    }

    public void setOnCompletionListener(b bVar) {
        this.c = bVar;
    }

    public void setDismissDelegate(InterfaceC0115a interfaceC0115a) {
        this.d = interfaceC0115a;
    }

    public void d() {
        this.n = NendAdInterstitial.NendAdInterstitialClickType.CLOSE;
    }

    @Override // net.nend.android.internal.ui.views.b.b.a
    public void e() {
        if (this.e.getStatusCode() == net.nend.android.internal.ui.views.b.b.EnumC0116b.INCOMPLETE || this.f.getStatusCode() == net.nend.android.internal.ui.views.b.b.EnumC0116b.INCOMPLETE || this.c == null) {
            return;
        }
        if (this.e.getStatusCode() == net.nend.android.internal.ui.views.b.b.EnumC0116b.SUCCESS && this.f.getStatusCode() == net.nend.android.internal.ui.views.b.b.EnumC0116b.SUCCESS) {
            this.c.a(NendAdInterstitial.NendAdInterstitialStatusCode.SUCCESS);
        } else {
            this.c.a(NendAdInterstitial.NendAdInterstitialStatusCode.FAILED_AD_DOWNLOAD);
        }
    }

    @Override // net.nend.android.internal.ui.views.b.b.a
    public void a(NendAdInterstitial.NendAdInterstitialClickType nendAdInterstitialClickType, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.n = nendAdInterstitialClickType;
        d.a(getContext(), str);
    }
}
