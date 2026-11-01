package com.moat.analytics.mobile.iro;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
final class v {

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static LinkedHashSet<String> f684 = new LinkedHashSet<>();

    v() {
    }

    @NonNull
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static Optional<WebView> m612(ViewGroup viewGroup, boolean z) {
        try {
            if (viewGroup == null) {
                return Optional.empty();
            }
            if (viewGroup instanceof WebView) {
                return Optional.of((WebView) viewGroup);
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
                        b.m461(3, "WebViewHound", childAt, "Found WebView");
                        if (z || m613(String.valueOf(childAt.hashCode()))) {
                            if (webView2 == null) {
                                webView2 = (WebView) childAt;
                            } else {
                                b.m461(3, "WebViewHound", childAt, "Ambiguous ad container: multiple WebViews reside within it.");
                                b.m459("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                                webView = null;
                                break;
                            }
                        }
                    }
                    if (childAt instanceof ViewGroup) {
                        linkedList.add((ViewGroup) childAt);
                    }
                    i2++;
                }
            }
            return Optional.ofNullable(webView);
        } catch (Exception unused) {
            return Optional.empty();
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static boolean m613(String str) {
        try {
            boolean zAdd = f684.add(str);
            if (f684.size() > 50) {
                Iterator<String> it = f684.iterator();
                for (int i = 0; i < 25 && it.hasNext(); i++) {
                    it.next();
                    it.remove();
                }
            }
            b.m461(3, "WebViewHound", null, zAdd ? "Newly Found WebView" : "Already Found WebView");
            return zAdd;
        } catch (Exception e) {
            o.m569(e);
            return false;
        }
    }
}
