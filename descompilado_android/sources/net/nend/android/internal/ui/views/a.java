package net.nend.android.internal.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.device.ads.WebRequest;
import java.util.concurrent.Future;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;

/* JADX INFO: compiled from: NendAdAnimationWebView.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends WebView {
    private Future<C0113a> a;
    private boolean b;
    private boolean c;
    private boolean d;
    private View.OnClickListener e;
    private b f;
    private String g;
    private ViewTreeObserver.OnPreDrawListener h;

    /* JADX INFO: compiled from: NendAdAnimationWebView.java */
    interface b {
        void a();

        boolean a(int i, int i2);

        void b();
    }

    a(Context context) {
        super(context);
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = null;
        this.f = null;
        this.g = "";
        this.h = new ViewTreeObserver.OnPreDrawListener() { // from class: net.nend.android.internal.ui.views.a.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (!a.this.isShown()) {
                    return true;
                }
                a.this.e();
                ViewTreeObserver viewTreeObserver = a.this.getViewTreeObserver();
                if (!viewTreeObserver.isAlive()) {
                    return true;
                }
                viewTreeObserver.removeOnPreDrawListener(this);
                return true;
            }
        };
        this.c = false;
        this.d = false;
        setBackgroundColor(0);
        setLayerType(1, null);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setWebViewClient(new WebViewClient() { // from class: net.nend.android.internal.ui.views.a.2
            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                a.this.d();
            }
        });
        getSettings().setSupportZoom(false);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setUseWideViewPort(false);
        getSettings().setLoadWithOverviewMode(false);
    }

    private static String a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<style type='text/css'>");
        sb.append("body{margin: auto auto} img{max-width: 100%; max-height: 100%;}");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<img src=\"" + str + "\" width=\"100%\" height=\"100%\" />");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            j.a("AndroidSDK internal error", e);
        }
    }

    @Override // android.webkit.WebView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.b = true;
            return true;
        }
        if (action != 1) {
            if (action == 3) {
                this.b = false;
            }
        } else if (this.b) {
            View.OnClickListener onClickListener = this.e;
            if (onClickListener != null) {
                onClickListener.onClick(this);
            }
            this.b = false;
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.e = onClickListener;
    }

    @Override // android.webkit.WebView
    public void destroy() {
        super.destroy();
        this.d = true;
    }

    public void a(final String str, b bVar) {
        this.f = bVar;
        this.a = g.a().a(new g.f(new g.b<C0113a>() { // from class: net.nend.android.internal.ui.views.a.3
            @Override // net.nend.android.internal.utilities.g.b
            public String getRequestUrl() {
                return str;
            }

            @Override // net.nend.android.internal.utilities.g.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public C0113a makeResponse(byte[] bArr) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                return new C0113a(bArr, options.outWidth, options.outHeight);
            }
        }), new g.a<C0113a>() { // from class: net.nend.android.internal.ui.views.a.4
            @Override // net.nend.android.internal.utilities.g.a
            public void a(C0113a c0113a, Exception exc) {
                a.this.a(c0113a);
            }
        });
    }

    public void a() {
        this.f = null;
        Future<C0113a> future = this.a;
        if (future != null) {
            future.cancel(true);
        }
    }

    public boolean b() {
        return this.c;
    }

    private void c() {
        b bVar = this.f;
        if (bVar != null) {
            bVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        b bVar = this.f;
        if (bVar != null) {
            bVar.b();
        }
    }

    private boolean a(int i, int i2) {
        b bVar = this.f;
        if (bVar != null) {
            return bVar.a(i, i2);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.d) {
            return;
        }
        loadDataWithBaseURL(null, this.g, WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(C0113a c0113a) {
        if (c0113a == null || !c0113a.a()) {
            d();
            return;
        }
        if (a(c0113a.b, c0113a.c)) {
            if (this.d) {
                d();
                return;
            }
            this.c = true;
            c();
            this.g = a(String.format("data:image/gif;base64,%s", Base64.encodeToString(c0113a.a, 2)));
            if (isShown()) {
                post(new Runnable() { // from class: net.nend.android.internal.ui.views.a.5
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.e();
                    }
                });
                return;
            }
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnPreDrawListener(this.h);
            }
        }
    }

    /* JADX INFO: renamed from: net.nend.android.internal.ui.views.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NendAdAnimationWebView.java */
    private static class C0113a {
        private final byte[] a;
        private final int b;
        private final int c;

        private C0113a(byte[] bArr, int i, int i2) {
            this.a = bArr;
            this.b = i;
            this.c = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            byte[] bArr = this.a;
            return bArr != null && bArr.length > 0;
        }
    }
}
