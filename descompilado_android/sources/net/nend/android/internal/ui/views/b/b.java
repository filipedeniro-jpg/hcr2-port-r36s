package net.nend.android.internal.ui.views.b;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.amazon.device.ads.WebRequest;
import java.io.UnsupportedEncodingException;
import net.nend.android.NendAdInterstitial;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;

/* JADX INFO: compiled from: NendAdInterstitialWebView.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends WebView implements g.b<String> {
    private final a a;
    private EnumC0116b b;
    private String c;

    /* JADX INFO: compiled from: NendAdInterstitialWebView.java */
    interface a {
        void a(NendAdInterstitial.NendAdInterstitialClickType nendAdInterstitialClickType, String str);

        void e();
    }

    /* JADX INFO: renamed from: net.nend.android.internal.ui.views.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NendAdInterstitialWebView.java */
    enum EnumC0116b {
        SUCCESS,
        FAILED,
        INCOMPLETE
    }

    public b(Context context, RelativeLayout.LayoutParams layoutParams, a aVar) {
        super(context);
        this.b = EnumC0116b.INCOMPLETE;
        this.c = "";
        this.a = aVar;
        clearCache(false);
        setLayoutParams(layoutParams);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setLayerType(1, null);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        getSettings().setCacheMode(2);
        setWebViewClient(new WebViewClient() { // from class: net.nend.android.internal.ui.views.b.b.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                b.this.b = EnumC0116b.SUCCESS;
                b.this.a.e();
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("https://www.nend.net/privacy/optsdkgate")) {
                    b.this.a.a(NendAdInterstitial.NendAdInterstitialClickType.INFORMATION, str);
                    return true;
                }
                b.this.a.a(NendAdInterstitial.NendAdInterstitialClickType.DOWNLOAD, str);
                return true;
            }
        });
        setBackgroundColor(0);
    }

    public void a(String str) {
        this.b = EnumC0116b.INCOMPLETE;
        this.c = str;
        g.a().a(new g.f(this), new g.a<String>() { // from class: net.nend.android.internal.ui.views.b.b.2
            @Override // net.nend.android.internal.utilities.g.a
            public void a(String str2, Exception exc) {
                b.this.b(str2);
            }
        });
    }

    public EnumC0116b getStatusCode() {
        return this.b;
    }

    @Override // net.nend.android.internal.utilities.g.b
    public String getRequestUrl() {
        return this.c;
    }

    @Override // net.nend.android.internal.utilities.g.b
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String makeResponse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            j.c(k.ERR_HTTP_REQUEST, e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        if (str != null) {
            loadDataWithBaseURL("http://output.nend.net", str, WebRequest.CONTENT_TYPE_HTML, "UTF-8", null);
        } else {
            this.b = EnumC0116b.FAILED;
            this.a.e();
        }
    }
}
