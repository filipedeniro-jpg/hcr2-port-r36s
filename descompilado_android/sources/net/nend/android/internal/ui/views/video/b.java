package net.nend.android.internal.ui.views.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.concurrent.BlockingQueue;
import net.nend.android.internal.utilities.j;

/* JADX INFO: compiled from: CacheHtmlWebView.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends WebView {
    private static final String[] a = {"android.webkit.JniUtil", "java.lang.Runtime"};
    private InterfaceC0118b b;

    /* JADX INFO: renamed from: net.nend.android.internal.ui.views.video.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CacheHtmlWebView.java */
    public interface InterfaceC0118b {
        void a();
    }

    @SuppressLint({"SetJavaScriptEnabled, AddJavascriptInterface"})
    public b(Context context, BlockingQueue<net.nend.android.internal.ui.a.d> blockingQueue, String str) {
        super(context);
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new a());
        if (str.equals("html_on_playing")) {
            addJavascriptInterface(new net.nend.android.internal.ui.a.c(blockingQueue, str), "nendSDK");
        } else {
            addJavascriptInterface(new net.nend.android.internal.ui.a.b(blockingQueue, str), "nendSDK");
        }
        if (Build.VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(false);
        }
    }

    public void a(String str) {
        loadUrl("file://" + str);
    }

    public boolean b(String str) {
        for (String str2 : a) {
            if (str.contains(str2)) {
                j.d("This html content is maybe dangerous. Disable Javascript option");
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: compiled from: CacheHtmlWebView.java */
    private class a extends WebViewClient {
        private a() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (Build.VERSION.SDK_INT < 17) {
                webView.loadUrl("javascript:window.nendSDK.viewSource(document.documentElement.outerHTML);");
            }
            if (b.this.b != null) {
                b.this.b.a();
                b.this.b = null;
            }
            super.onPageFinished(webView, str);
        }
    }

    public void setWebViewClientListener(InterfaceC0118b interfaceC0118b) {
        this.b = interfaceC0118b;
    }
}
