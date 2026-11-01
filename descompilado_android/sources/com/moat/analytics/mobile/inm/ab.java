package com.moat.analytics.mobile.inm;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
class ab {
    private static final LinkedHashSet<String> a = new LinkedHashSet<>();

    ab() {
    }

    @NonNull
    static com.moat.analytics.mobile.inm.a.b.a<WebView> a(ViewGroup viewGroup, boolean z) {
        try {
            if (viewGroup == null) {
                return com.moat.analytics.mobile.inm.a.b.a.a();
            }
            if (viewGroup instanceof WebView) {
                return com.moat.analytics.mobile.inm.a.b.a.a((WebView) viewGroup);
            }
            LinkedList linkedList = new LinkedList();
            linkedList.add(viewGroup);
            WebView webView = null;
            int i = 0;
            while (!linkedList.isEmpty() && i < 100) {
                i++;
                ViewGroup viewGroup2 = (ViewGroup) linkedList.poll();
                int childCount = viewGroup2.getChildCount();
                WebView webView2 = webView;
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        webView = webView2;
                        break;
                    }
                    View childAt = viewGroup2.getChildAt(i2);
                    if (childAt instanceof WebView) {
                        p.a(3, "WebViewHound", childAt, "Found WebView");
                        if (z || a(String.valueOf(childAt.hashCode()))) {
                            if (webView2 != null) {
                                p.a(3, "WebViewHound", childAt, "Ambiguous ad container: multiple WebViews reside within it.");
                                p.a("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                                webView = null;
                                break;
                            }
                            webView2 = (WebView) childAt;
                        }
                    }
                    if (childAt instanceof ViewGroup) {
                        linkedList.add((ViewGroup) childAt);
                    }
                    i2++;
                }
            }
            return com.moat.analytics.mobile.inm.a.b.a.b(webView);
        } catch (Exception unused) {
            return com.moat.analytics.mobile.inm.a.b.a.a();
        }
    }

    private static boolean a(String str) {
        try {
            boolean zAdd = a.add(str);
            if (a.size() > 50) {
                Iterator<String> it = a.iterator();
                for (int i = 0; i < 25 && it.hasNext(); i++) {
                    it.next();
                    it.remove();
                }
            }
            p.a(3, "WebViewHound", (Object) null, zAdd ? "Newly Found WebView" : "Already Found WebView");
            return zAdd;
        } catch (Exception e) {
            m.a(e);
            return false;
        }
    }
}
