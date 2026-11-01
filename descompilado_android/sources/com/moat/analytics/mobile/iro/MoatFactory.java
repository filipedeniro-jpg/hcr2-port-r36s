package com.moat.analytics.mobile.iro;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MoatFactory {
    @UiThread
    public abstract <T> T createCustomTracker(m<T> mVar);

    @UiThread
    public abstract NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map);

    @UiThread
    public abstract NativeVideoTracker createNativeVideoTracker(String str);

    @UiThread
    public abstract WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup);

    @UiThread
    public abstract WebAdTracker createWebAdTracker(@NonNull WebView webView);

    public static MoatFactory create() {
        try {
            return new n();
        } catch (Exception e) {
            o.m569(e);
            return new NoOp.MoatFactory();
        }
    }
}
