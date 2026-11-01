package net.nend.android.internal.ui.views.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebView;
import com.amazon.device.ads.WebRequest;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;

/* JADX INFO: compiled from: NendAdWebView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends WebView implements g.b<String> {
    private String a;
    private net.nend.android.internal.ui.views.b.a b;
    private Future<String> c;

    @SuppressLint({"SetJavaScriptEnabled"})
    public a(Context context) {
        super(context);
        getSettings().setJavaScriptEnabled(true);
        setBackgroundColor(0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            j.a("AndroidSDK internal error", e);
        }
    }

    public void a(String str, net.nend.android.internal.ui.views.b.a aVar) {
        this.a = str;
        this.b = aVar;
        this.c = g.a().a(new g.f(this), new g.a<String>() { // from class: net.nend.android.internal.ui.views.a.a.1
            @Override // net.nend.android.internal.utilities.g.a
            public void a(String str2, Exception exc) {
                a.this.a(str2);
            }
        });
    }

    @Override // net.nend.android.internal.utilities.g.b
    public String getRequestUrl() {
        return this.a;
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
            j.c(k.ERR_FAILED_TO_PARSE, e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (str != null) {
            loadDataWithBaseURL(this.a, str, WebRequest.CONTENT_TYPE_HTML, "UTF-8", null);
            net.nend.android.internal.ui.views.b.a aVar = this.b;
            if (aVar != null) {
                aVar.onSuccess();
                return;
            }
            return;
        }
        net.nend.android.internal.ui.views.b.a aVar2 = this.b;
        if (aVar2 != null) {
            aVar2.onFailure();
        }
    }

    @Override // android.webkit.WebView
    public void destroy() {
        Future<String> future = this.c;
        if (future != null) {
            future.cancel(true);
        }
        super.destroy();
    }
}
