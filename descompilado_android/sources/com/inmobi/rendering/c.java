package com.inmobi.rendering;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.amazon.device.ads.AdWebViewClient;
import com.amazon.device.ads.WebRequest;
import com.chartboost.sdk.CBLocation;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.plus.PlusShare;
import com.inmobi.a.n;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.ah;
import com.inmobi.ads.ak;
import com.inmobi.ads.bm;
import com.inmobi.ads.br;
import com.inmobi.ads.cache.AssetStore;
import com.inmobi.commons.core.configs.h;
import com.inmobi.commons.core.network.d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.f;
import com.inmobi.rendering.mraid.MediaRenderView;
import com.inmobi.rendering.mraid.MraidMediaProcessor;
import com.inmobi.rendering.mraid.MraidMediaProcessor.HeadphonesPluggedChangeReceiver;
import com.inmobi.rendering.mraid.MraidMediaProcessor.RingerModeChangeReceiver;
import com.inmobi.rendering.mraid.MraidMediaProcessor.a;
import com.inmobi.rendering.mraid.g;
import com.inmobi.rendering.mraid.i;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.my.target.aa;
import com.my.target.ab;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: JavaScriptBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    static final String[] a = {AdWebViewClient.TELEPHONE, AdWebViewClient.SMS, "calendar", "inlineVideo"};
    private static final String b = "c";
    private RenderView c;
    private AdContainer.RenderingProperties d;
    private g e;

    @JavascriptInterface
    public String getPlatform(String str) {
        return "android";
    }

    @JavascriptInterface
    public String getSdkVersion(String str) {
        return "7.2.1";
    }

    @JavascriptInterface
    public String getVersion(String str) {
        return "2.0";
    }

    @JavascriptInterface
    public void log(String str, String str2) {
    }

    @JavascriptInterface
    public void onOrientationChange(String str) {
    }

    @JavascriptInterface
    public void showAlert(String str, String str2) {
    }

    @JavascriptInterface
    public void storePicture(String str, String str2) {
    }

    c(RenderView renderView, AdContainer.RenderingProperties renderingProperties) {
        this.c = renderView;
        this.d = renderingProperties;
    }

    @JavascriptInterface
    public void open(final String str, final String str2) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        if (!renderView.e()) {
            this.c.c(ab.bz);
        } else {
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        c.this.c.c(ab.bz, str, str2);
                    } catch (Exception e) {
                        c.this.c.b(str, "Unexpected error", ab.bz);
                        Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Failed to open URL; SDK encountered unexpected error");
                        String unused = c.b;
                        new StringBuilder("SDK encountered unexpected error in handling open() request from creative; ").append(e.getMessage());
                    }
                }
            });
        }
    }

    @JavascriptInterface
    public void openEmbedded(final String str, final String str2) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        if (!renderView.e()) {
            this.c.c("openEmbedded");
        } else {
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.5
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        c.this.c.c("openEmbedded", str, str2);
                    } catch (Exception e) {
                        c.this.c.b(str, "Unexpected error", "openEmbedded");
                        Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Failed to open URL; SDK encountered unexpected error");
                        String unused = c.b;
                        new StringBuilder("SDK encountered unexpected error in handling openEmbedded() request from creative; ").append(e.getMessage());
                    }
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.inmobi.rendering.a.c$1] */
    @JavascriptInterface
    public void ping(String str, final String str2, final boolean z) {
        if (this.c == null) {
            return;
        }
        if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
            this.c.b(str, "Invalid URL:" + str2, "ping");
            return;
        }
        StringBuilder sb = new StringBuilder("JavaScript called ping() URL: >>> ");
        sb.append(str2);
        sb.append(" <<<");
        try {
            final com.inmobi.rendering.a.c cVarA = com.inmobi.rendering.a.c.a();
            new Thread() { // from class: com.inmobi.rendering.a.c.1
                final /* synthetic */ String a;
                final /* synthetic */ boolean b;

                public AnonymousClass1() {
                    str = str2;
                    z = z;
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    try {
                        h hVar = new h();
                        com.inmobi.commons.core.configs.b.a().a(hVar, (com.inmobi.commons.core.configs.b.c) null);
                        if (hVar.g) {
                            return;
                        }
                        com.inmobi.rendering.a.a aVar = new com.inmobi.rendering.a.a(str, z, false, c.j.a + 1);
                        String unused = c.a;
                        StringBuilder sb2 = new StringBuilder("Received click (");
                        sb2.append(aVar.b);
                        sb2.append(") for pinging over HTTP");
                        c.a(c.this, aVar);
                    } catch (Exception e) {
                        String unused2 = c.a;
                        new StringBuilder("SDK encountered unexpected error in pinging click; ").append(e.getMessage());
                    }
                }
            }.start();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "ping");
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Failed to fire ping; SDK encountered unexpected error");
            new StringBuilder("SDK encountered unexpected error in handling ping() request from creative; ").append(e.getMessage());
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.inmobi.rendering.a.c$3] */
    @JavascriptInterface
    public void pingInWebView(String str, final String str2, final boolean z) {
        if (this.c == null) {
            return;
        }
        if (str2 == null || str2.trim().length() == 0 || !URLUtil.isValidUrl(str2)) {
            this.c.b(str, "Invalid URL:" + str2, "pingInWebView");
            return;
        }
        StringBuilder sb = new StringBuilder("JavaScript called pingInWebView() URL: >>> ");
        sb.append(str2);
        sb.append(" <<<");
        try {
            final com.inmobi.rendering.a.c cVarA = com.inmobi.rendering.a.c.a();
            new Thread() { // from class: com.inmobi.rendering.a.c.3
                final /* synthetic */ String a;
                final /* synthetic */ boolean b;

                public AnonymousClass3() {
                    str = str2;
                    z = z;
                }

                @Override // java.lang.Thread, java.lang.Runnable
                public final void run() {
                    try {
                        h hVar = new h();
                        com.inmobi.commons.core.configs.b.a().a(hVar, (com.inmobi.commons.core.configs.b.c) null);
                        if (hVar.g) {
                            return;
                        }
                        com.inmobi.rendering.a.a aVar = new com.inmobi.rendering.a.a(str, z, true, c.j.a + 1);
                        String unused = c.a;
                        StringBuilder sb2 = new StringBuilder("Received click (");
                        sb2.append(aVar.b);
                        sb2.append(") for pinging in WebView");
                        c.a(c.this, aVar);
                    } catch (Exception e) {
                        String unused2 = c.a;
                        new StringBuilder("SDK encountered unexpected error in pinging click over WebView; ").append(e.getMessage());
                    }
                }
            }.start();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "pingInWebView");
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Failed to fire ping; SDK encountered unexpected error");
            new StringBuilder("SDK encountered unexpected error in handling pingInWebView() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public String getPlatformVersion(String str) {
        return Integer.toString(Build.VERSION.SDK_INT);
    }

    @JavascriptInterface
    public void fireAdReady(String str) {
        try {
            this.c.getListener().w();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "fireAdReady");
            new StringBuilder("SDK encountered unexpected error in handling fireAdReady() signal from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void fireAdFailed(String str) {
        try {
            this.c.getListener().y();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "fireAdFailed");
            new StringBuilder("SDK encountered unexpected error in handling fireAdFailed() signal from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public String getDefaultPosition(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return new JSONObject().toString();
        }
        synchronized (renderView.getDefaultPositionMonitor()) {
            this.c.setDefaultPositionLock();
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.6
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        c.this.c.setDefaultPosition();
                    } catch (Exception e) {
                        String unused = c.b;
                        new StringBuilder("SDK encountered unexpected error in getting/setting default position; ").append(e.getMessage());
                    }
                }
            });
            while (this.c.k) {
                try {
                    this.c.getDefaultPositionMonitor().wait();
                } catch (InterruptedException unused) {
                }
            }
        }
        return this.c.getDefaultPosition();
    }

    @JavascriptInterface
    public String getCurrentPosition(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return "";
        }
        synchronized (renderView.getCurrentPositionMonitor()) {
            this.c.setCurrentPositionLock();
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.7
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        c.this.c.setCurrentPosition();
                    } catch (Exception e) {
                        String unused = c.b;
                        new StringBuilder("SDK encountered unexpected error in getting/setting current position; ").append(e.getMessage());
                    }
                }
            });
            while (this.c.l) {
                try {
                    this.c.getCurrentPositionMonitor().wait();
                } catch (InterruptedException unused) {
                }
            }
        }
        return this.c.getCurrentPosition();
    }

    @JavascriptInterface
    public void setExpandProperties(String str, String str2) {
        RenderView renderView = this.c;
        if (renderView == null || "Expanded".equals(renderView.getState())) {
            return;
        }
        try {
            this.c.setExpandProperties(com.inmobi.rendering.mraid.b.a(str2));
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", ab.bC);
            new StringBuilder("SDK encountered unexpected error in setExpandProperties(); ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public String getExpandProperties(String str) {
        RenderView renderView = this.c;
        return renderView == null ? "" : renderView.getExpandProperties().c;
    }

    @JavascriptInterface
    public void expand(final String str, final String str2) {
        RenderView renderView;
        if (this.d.a == AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN || (renderView = this.c) == null) {
            return;
        }
        if (!renderView.e()) {
            this.c.c("expand");
            return;
        }
        if (this.c.o) {
            if (str2 != null && str2.length() != 0 && !str2.startsWith("http")) {
                this.c.b(str, "Invalid URL", "expand");
                return;
            } else {
                new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.8
                    @Override // java.lang.Runnable
                    public final void run() {
                        int iA;
                        try {
                            RenderView renderView2 = c.this.c;
                            String str3 = str;
                            String str4 = str2;
                            if (!CBLocation.LOCATION_DEFAULT.equals(renderView2.d) && !"Resized".equals(renderView2.d)) {
                                new StringBuilder("Render view state must be either DEFAULT or RESIZED to admit the expand request. Current state:").append(renderView2.d);
                                return;
                            }
                            renderView2.t = true;
                            com.inmobi.rendering.mraid.c cVar = renderView2.f;
                            if (cVar.c == null) {
                                cVar.c = (ViewGroup) cVar.a.getParent();
                                cVar.d = cVar.c.indexOfChild(cVar.a);
                            }
                            if (cVar.a != null) {
                                com.inmobi.rendering.mraid.b expandProperties = cVar.a.getExpandProperties();
                                cVar.b = URLUtil.isValidUrl(str4);
                                if (cVar.b) {
                                    RenderView renderView3 = new RenderView(cVar.a.getContainerContext(), new AdContainer.RenderingProperties(AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE), null, cVar.a.getImpressionId());
                                    renderView3.a(cVar.a.getListener(), cVar.a.getAdConfig());
                                    renderView3.setOriginalRenderView(cVar.a);
                                    renderView3.loadUrl(str4);
                                    renderView3.setPlacementId(cVar.a.getPlacementId());
                                    renderView3.setAllowAutoRedirection(cVar.a.getAllowAutoRedirection());
                                    renderView3.setCreativeId(cVar.a.getCreativeId());
                                    iA = InMobiAdActivity.a((AdContainer) renderView3);
                                    if (expandProperties != null) {
                                        renderView3.setUseCustomClose(cVar.a.m);
                                    }
                                } else {
                                    FrameLayout frameLayout = new FrameLayout(cVar.a.getContainerContext());
                                    ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(cVar.a.getWidth(), cVar.a.getHeight());
                                    frameLayout.setId(65535);
                                    cVar.c.addView(frameLayout, cVar.d, layoutParams);
                                    cVar.c.removeView(cVar.a);
                                    iA = InMobiAdActivity.a((AdContainer) cVar.a);
                                }
                                cVar.a.getListener().G();
                                Intent intent = new Intent(cVar.a.getContainerContext(), (Class<?>) InMobiAdActivity.class);
                                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", iA);
                                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", 200);
                                com.inmobi.commons.a.a.a(cVar.a.getContainerContext(), intent);
                            }
                            renderView2.requestLayout();
                            renderView2.invalidate();
                            renderView2.n = true;
                            renderView2.setFocusable(true);
                            renderView2.setFocusableInTouchMode(true);
                            renderView2.requestFocus();
                            HashMap map = new HashMap();
                            map.put("command", "expand");
                            map.put("scheme", br.a(str3));
                            renderView2.c.b("CreativeInvokedAction", map);
                        } catch (Exception e) {
                            c.this.c.b(str, "Unexpected error", "expand");
                            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Failed to expand ad; SDK encountered an unexpected error");
                            String unused = c.b;
                            new StringBuilder("SDK encountered unexpected error in handling expand() request; ").append(e.getMessage());
                        }
                    }
                });
                return;
            }
        }
        this.c.b(str, "Creative is not visible. Ignoring request.", "expand");
    }

    @JavascriptInterface
    public void setResizeProperties(String str, String str2) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        com.inmobi.rendering.mraid.h hVarA = com.inmobi.rendering.mraid.h.a(str2, renderView.getResizeProperties());
        if (hVarA == null) {
            this.c.b(str, ab.bB, "All mandatory fields are not present");
        }
        this.c.setResizeProperties(hVarA);
    }

    @JavascriptInterface
    public String getResizeProperties(String str) {
        com.inmobi.rendering.mraid.h resizeProperties;
        RenderView renderView = this.c;
        return (renderView == null || (resizeProperties = renderView.getResizeProperties()) == null) ? "" : resizeProperties.a();
    }

    @JavascriptInterface
    public void resize(final String str) {
        RenderView renderView;
        if (this.d.a == AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN || (renderView = this.c) == null) {
            return;
        }
        if (renderView.o) {
            new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.9
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        RenderView renderView2 = c.this.c;
                        String str2 = str;
                        if ((CBLocation.LOCATION_DEFAULT.equals(renderView2.d) || "Resized".equals(renderView2.d)) && renderView2.getResizeProperties() != null) {
                            renderView2.t = true;
                            renderView2.g.a();
                            renderView2.requestLayout();
                            renderView2.invalidate();
                            renderView2.n = true;
                            renderView2.setFocusable(true);
                            renderView2.setFocusableInTouchMode(true);
                            renderView2.requestFocus();
                            renderView2.setAndUpdateViewState("Resized");
                            renderView2.c.c(renderView2);
                            renderView2.t = false;
                            HashMap map = new HashMap();
                            map.put("command", ab.bA);
                            map.put("scheme", br.a(str2));
                            renderView2.c.b("CreativeInvokedAction", map);
                        }
                    } catch (Exception e) {
                        c.this.c.b(str, "Unexpected error", ab.bA);
                        Logger.a(Logger.InternalLogLevel.ERROR, c.b, "Could not resize ad; SDK encountered an unexpected error");
                        String unused = c.b;
                        new StringBuilder("SDK encountered an unexpected error in handling resize() request; ").append(e.getMessage());
                    }
                }
            });
        } else {
            this.c.b(str, "Creative is not visible. Ignoring request.", ab.bA);
        }
    }

    @JavascriptInterface
    public void setOrientationProperties(String str, String str2) {
        this.e = g.a(str2, this.c.getOrientationProperties());
        this.c.setOrientationProperties(this.e);
    }

    @JavascriptInterface
    public String getOrientationProperties(String str) {
        return this.e.d;
    }

    @JavascriptInterface
    public boolean isViewable(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return false;
        }
        return renderView.o;
    }

    @JavascriptInterface
    public void useCustomClose(final String str, final boolean z) {
        new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.10
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    c.this.c.b(z);
                } catch (Exception e) {
                    c.this.c.b(str, "Unexpected error", ab.by);
                    String unused = c.b;
                    new StringBuilder("SDK encountered internal error in handling useCustomClose() request from creative; ").append(e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public void playVideo(final String str, final String str2) {
        if (this.c == null) {
            return;
        }
        if (str2 == null || str2.trim().length() == 0 || !str2.startsWith("http") || (!str2.endsWith("mp4") && !str2.endsWith("avi") && !str2.endsWith("m4v"))) {
            this.c.b(str, "Null or empty or invalid media playback URL supplied", ab.bE);
            return;
        }
        StringBuilder sb = new StringBuilder("JavaScript called: playVideo (");
        sb.append(str2);
        sb.append(")");
        new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.11
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    RenderView renderView = c.this.c;
                    String str3 = str;
                    String strTrim = str2.trim();
                    if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == renderView.e.a || "Expanded".equals(renderView.getViewState())) {
                        if (renderView.b != null && renderView.b.get() != null) {
                            renderView.setAdActiveFlag(true);
                            final MraidMediaProcessor mraidMediaProcessor = renderView.h;
                            Activity activity = renderView.b.get();
                            mraidMediaProcessor.b = new MediaRenderView(activity);
                            MediaRenderView mediaRenderView = mraidMediaProcessor.b;
                            mediaRenderView.h = MediaRenderView.a(strTrim);
                            mediaRenderView.g = "anonymous";
                            if (mediaRenderView.b == null) {
                                mediaRenderView.b = Bitmap.createBitmap(24, 24, Bitmap.Config.ARGB_8888);
                                mediaRenderView.b = MediaRenderView.b(mediaRenderView.h);
                            }
                            ViewGroup viewGroup = (ViewGroup) activity.findViewById(R.id.content);
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                            layoutParams.addRule(13);
                            mraidMediaProcessor.b.setLayoutParams(layoutParams);
                            RelativeLayout relativeLayout = new RelativeLayout(activity);
                            relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.inmobi.rendering.mraid.MraidMediaProcessor.1
                                @Override // android.view.View.OnTouchListener
                                public final boolean onTouch(View view, MotionEvent motionEvent) {
                                    return true;
                                }

                                public AnonymousClass1() {
                                }
                            });
                            relativeLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                            relativeLayout.addView(mraidMediaProcessor.b);
                            viewGroup.addView(relativeLayout, new ViewGroup.LayoutParams(-1, -1));
                            mraidMediaProcessor.b.c = relativeLayout;
                            mraidMediaProcessor.b.requestFocus();
                            mraidMediaProcessor.b.setOnKeyListener(new View.OnKeyListener() { // from class: com.inmobi.rendering.mraid.MraidMediaProcessor.2
                                public AnonymousClass2() {
                                }

                                @Override // android.view.View.OnKeyListener
                                public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                                    if (4 != i || keyEvent.getAction() != 0) {
                                        return false;
                                    }
                                    MraidMediaProcessor.this.b.a();
                                    return true;
                                }
                            });
                            mraidMediaProcessor.b.d = new MediaRenderView.a() { // from class: com.inmobi.rendering.mraid.MraidMediaProcessor.3
                                public AnonymousClass3() {
                                }

                                @Override // com.inmobi.rendering.mraid.MediaRenderView.a
                                public final void a(MediaRenderView mediaRenderView2) {
                                    String unused = MraidMediaProcessor.f;
                                    MraidMediaProcessor.this.a.setAdActiveFlag(false);
                                    ViewGroup viewGroup2 = mediaRenderView2.c;
                                    if (viewGroup2 != null) {
                                        ((ViewGroup) viewGroup2.getParent()).removeView(viewGroup2);
                                    }
                                    mediaRenderView2.c = null;
                                }

                                @Override // com.inmobi.rendering.mraid.MediaRenderView.a
                                public final void a() {
                                    String unused = MraidMediaProcessor.f;
                                }
                            };
                            MediaRenderView mediaRenderView2 = mraidMediaProcessor.b;
                            mediaRenderView2.setVideoPath(mediaRenderView2.h);
                            mediaRenderView2.setOnCompletionListener(mediaRenderView2);
                            mediaRenderView2.setOnPreparedListener(mediaRenderView2);
                            mediaRenderView2.setOnErrorListener(mediaRenderView2);
                            if (mediaRenderView2.a == null && Build.VERSION.SDK_INT >= 19) {
                                mediaRenderView2.a = new MediaRenderView.CustomMediaController(mediaRenderView2.getContext());
                                mediaRenderView2.a.setAnchorView(mediaRenderView2);
                                mediaRenderView2.setMediaController(mediaRenderView2.a);
                            }
                            HashMap map = new HashMap();
                            map.put("command", ab.bE);
                            map.put("scheme", br.a(str3));
                            renderView.c.b("CreativeInvokedAction", map);
                            return;
                        }
                        renderView.b(str3, "Media playback is  not allowed before it is visible! Ignoring request ...", ab.bE);
                    }
                } catch (Exception e) {
                    c.this.c.b(str, "Unexpected error", ab.bE);
                    Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Error playing video; SDK encountered an unexpected error");
                    String unused = c.b;
                    new StringBuilder("SDK encountered unexpected error in handling playVideo() request from creative; ").append(e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public String getState(String str) {
        return this.c.getState().toLowerCase(Locale.ENGLISH);
    }

    @JavascriptInterface
    public String getScreenSize(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", com.inmobi.commons.core.utilities.b.c.a().a);
            jSONObject.put("height", com.inmobi.commons.core.utilities.b.c.a().b);
        } catch (JSONException unused) {
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getScreenSize");
            new StringBuilder("SDK encountered unexpected error while getting screen dimensions; ").append(e.getMessage());
        }
        return jSONObject.toString();
    }

    @JavascriptInterface
    public String getMaxSize(String str) {
        int i;
        int i2;
        JSONObject jSONObject = new JSONObject();
        try {
            Activity fullScreenActivity = this.c.getFullScreenActivity();
            if (fullScreenActivity == null) {
                if (!(this.c.getContainerContext() instanceof Activity)) {
                    return getScreenSize(str);
                }
                fullScreenActivity = (Activity) this.c.getContainerContext();
            }
            FrameLayout frameLayout = (FrameLayout) fullScreenActivity.findViewById(R.id.content);
            int iB = com.inmobi.commons.core.utilities.b.c.b(frameLayout.getWidth());
            int iB2 = com.inmobi.commons.core.utilities.b.c.b(frameLayout.getHeight());
            if (this.c.getFullScreenActivity() != null && (iB == 0 || iB2 == 0)) {
                a aVar = new a(frameLayout);
                frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(aVar);
                synchronized (aVar.d) {
                    try {
                        aVar.d.wait();
                    } catch (InterruptedException unused) {
                    }
                    i = aVar.a;
                    i2 = aVar.b;
                }
                iB2 = i2;
                iB = i;
            }
            try {
                jSONObject.put("width", iB);
                jSONObject.put("height", iB2);
            } catch (JSONException unused2) {
            }
            new StringBuilder("getMaxSize called:").append(jSONObject.toString());
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getMaxSize");
            new StringBuilder("SDK encountered unexpected error in handling getMaxSize() request from creative; ").append(e.getMessage());
        }
        return jSONObject.toString();
    }

    /* JADX INFO: compiled from: JavaScriptBridge.java */
    @TargetApi(16)
    private static class a implements ViewTreeObserver.OnGlobalLayoutListener {
        private int a;
        private int b;
        private View c;
        private final Boolean d = false;

        a(View view) {
            this.c = view;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            try {
                this.a = com.inmobi.commons.core.utilities.b.c.b(this.c.getWidth());
                this.b = com.inmobi.commons.core.utilities.b.c.b(this.c.getHeight());
                if (Build.VERSION.SDK_INT >= 16) {
                    this.c.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    this.c.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                synchronized (this.d) {
                    this.d.notify();
                }
            } catch (Exception e) {
                String unused = c.b;
                new StringBuilder("SDK encountered unexpected error in JavaScriptBridge$1.onGlobalLayout(); ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void close(final String str) {
        new Handler(this.c.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.12
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    c.this.c.getReferenceContainer().b();
                } catch (Exception e) {
                    c.this.c.b(str, "Unexpected error", "close");
                    Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Failed to close ad; SDK encountered an unexpected error");
                    String unused = c.b;
                    new StringBuilder("SDK encountered an expected error in handling the close() request from creative; ").append(e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public String getPlacementType(String str) {
        return AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.d.a ? aa.e.bo : aa.e.bn;
    }

    @JavascriptInterface
    @TargetApi(23)
    public void createCalendarEvent(final String str, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7, final String str8, final String str9, final String str10, final String str11) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        if (!renderView.e()) {
            this.c.c(ab.bG);
            return;
        }
        if (this.c.e("calendar")) {
            if (str3 == null || str3.trim().length() == 0 || str4 == null || str4.trim().length() == 0) {
                this.c.b(str, "Mandatory parameter(s) start and/or end date not supplied", ab.bG);
                return;
            }
            StringBuilder sb = new StringBuilder("createCalendarEvent called with parameters: \nevent ID: ");
            sb.append(str2);
            sb.append("; startDate: ");
            sb.append(str3);
            sb.append("; endDate: ");
            sb.append(str4);
            sb.append("; location: ");
            sb.append(str5);
            sb.append("; description: ");
            sb.append(str6);
            sb.append("; summary: ");
            sb.append(str7);
            sb.append("; status: ");
            sb.append(str8);
            sb.append("; transparency: ");
            sb.append(str9);
            sb.append("; recurrence: ");
            sb.append(str10);
            sb.append("; reminder: ");
            sb.append(str11);
            Context contextB = com.inmobi.commons.a.a.b();
            if (contextB == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 23 && (contextB.checkSelfPermission("android.permission.WRITE_CALENDAR") != 0 || contextB.checkSelfPermission("android.permission.READ_CALENDAR") != 0)) {
                InMobiAdActivity.a(new String[]{"android.permission.WRITE_CALENDAR", "android.permission.READ_CALENDAR"}, new InMobiAdActivity.b() { // from class: com.inmobi.rendering.c.2
                    @Override // com.inmobi.rendering.InMobiAdActivity.b
                    public final void a(int[] iArr) {
                        if (iArr.length != 2 || iArr[0] != 0 || iArr[1] != 0) {
                            c.this.c.b(str, "Permission denied by user.", ab.bG);
                            return;
                        }
                        try {
                            c.this.c.a(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
                        } catch (Exception e) {
                            c.this.c.b(str, "Unexpected error", ab.bG);
                            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not create calendar event; SDK encountered unexpected error");
                            String unused = c.b;
                            new StringBuilder("SDK encountered unexpected error in handling createCalendarEvent() request from creative; ").append(e.getMessage());
                        }
                    }
                });
                return;
            }
            try {
                this.c.a(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11);
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", ab.bG);
                Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not create calendar event; SDK encountered unexpected error");
                new StringBuilder("SDK encountered unexpected error in handling createCalendarEvent() request from creative; ").append(e.getMessage());
            }
        }
    }

    @JavascriptInterface
    public void postToSocial(String str, int i, String str2, String str3, String str4) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        if (!renderView.e()) {
            this.c.c("postToSocial");
            return;
        }
        StringBuilder sb = new StringBuilder("postToSocial called with parameters: socialType: ");
        sb.append(i);
        sb.append("; text: ");
        sb.append(str2);
        sb.append("; link: ");
        sb.append(str3);
        sb.append("; image URL: ");
        sb.append(str4);
        try {
            RenderView renderView2 = this.c;
            if (renderView2.e("postToSocial")) {
                i iVar = renderView2.i;
                Context containerContext = renderView2.getContainerContext();
                if (str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0 || !str3.startsWith("http") || str4 == null || str4.length() == 0 || !str4.startsWith("http") || !str4.endsWith(".jpg")) {
                    iVar.a.b(str, "Attempting to share with null/empty/invalid parameters", "postToSocial");
                    return;
                }
                Intent intent = null;
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3) {
                            Intent intent2 = new Intent();
                            intent2.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                            intent2.setPackage("com.twitter.android");
                            intent2.putExtra("android.intent.extra.TEXT", str2 + " " + str3 + " " + str4);
                            intent = intent2;
                        } else {
                            iVar.a.b(str, "Unsupported type of social network", "postToSocial");
                            return;
                        }
                    } else if (f.a("ads") && i.a()) {
                        intent = new PlusShare.Builder(containerContext).setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT).setText(str2 + " " + str3 + " " + str4).setContentUrl(Uri.parse(str4)).getIntent();
                    }
                }
                if (intent != null) {
                    try {
                        com.inmobi.commons.a.a.a(containerContext, intent);
                        return;
                    } catch (ActivityNotFoundException unused) {
                    }
                }
                i.a(containerContext, i, str2, str3, str4);
            }
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "postToSocial");
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not post to social network; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered an unexpected error in handling the postToSocial() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public String supports(String str, String str2) {
        return (Arrays.asList(a).contains(str2) || this.c.e(str2)) ? String.valueOf(this.c.e(str2)) : "false";
    }

    @JavascriptInterface
    public void openExternal(String str, String str2, @Nullable String str3) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        if (!renderView.e()) {
            this.c.c("openExternal");
        } else {
            this.c.a("openExternal", str, str2, str3);
        }
    }

    @JavascriptInterface
    public void asyncPing(String str, String str2) {
        if (!URLUtil.isValidUrl(str2)) {
            this.c.b(str, "Invalid url", "asyncPing");
            return;
        }
        try {
            HashMap map = new HashMap();
            map.put("command", "ping");
            map.put("scheme", br.a(str));
            this.c.a("CreativeInvokedAction", map);
            final com.inmobi.commons.core.network.c cVar = new com.inmobi.commons.core.network.c(HttpRequest.METHOD_GET, str2);
            cVar.u = false;
            final long jElapsedRealtime = SystemClock.elapsedRealtime();
            new com.inmobi.commons.core.network.a(cVar, new com.inmobi.commons.core.network.a.InterfaceC0077a() { // from class: com.inmobi.rendering.c.3
                @Override // com.inmobi.commons.core.network.a.InterfaceC0077a
                public final void a(d dVar) {
                    String unused = c.b;
                    try {
                        n.a().a(cVar.g());
                        n.a().b(dVar.c());
                        n.a().c(SystemClock.elapsedRealtime() - jElapsedRealtime);
                    } catch (Exception e) {
                        String unused2 = c.b;
                        new StringBuilder("Error in setting request-response data size. ").append(e.getMessage());
                    }
                }

                @Override // com.inmobi.commons.core.network.a.InterfaceC0077a
                public final void b(d dVar) {
                    String unused = c.b;
                }
            }).a();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "asyncPing");
            new StringBuilder("SDK encountered internal error in handling asyncPing() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void disableCloseRegion(final String str, final boolean z) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        new Handler(renderView.getContainerContext().getMainLooper()).post(new Runnable() { // from class: com.inmobi.rendering.c.4
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    c.this.c.a(z);
                } catch (Exception e) {
                    c.this.c.b(str, "Unexpected error", "disableCloseRegion");
                    String unused = c.b;
                    new StringBuilder("SDK encountered unexpected error in handling disableCloseRegion() request from creative; ").append(e.getMessage());
                }
            }
        });
    }

    @JavascriptInterface
    public void onUserInteraction(String str, String str2) {
        RenderView renderView = this.c;
        if (renderView != null && !renderView.e()) {
            this.c.c("onUserInteraction");
            return;
        }
        if (str2 == null) {
            try {
                this.c.getListener().b(new HashMap<>());
                return;
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "onUserInteraction");
                new StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(e.getMessage());
                return;
            }
        }
        try {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                HashMap<Object, Object> map = new HashMap<>();
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.get(next));
                }
                try {
                    this.c.getListener().b(map);
                } catch (Exception e2) {
                    this.c.b(str, "Unexpected error", "onUserInteraction");
                    new StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(e2.getMessage());
                }
            } catch (Exception e3) {
                this.c.b(str, "Unexpected error", "onUserInteraction");
                new StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(e3.getMessage());
            }
        } catch (JSONException unused) {
            this.c.getListener().b(new HashMap<>());
        }
    }

    @JavascriptInterface
    public void incentCompleted(String str, String str2) {
        if (str2 == null) {
            try {
                this.c.getListener().a(new HashMap<>());
                return;
            } catch (Exception e) {
                this.c.b(str, "Unexpected error", "incentCompleted");
                new StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(e.getMessage());
                return;
            }
        }
        try {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                HashMap<Object, Object> map = new HashMap<>();
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.get(next));
                }
                try {
                    this.c.getListener().a(map);
                } catch (Exception e2) {
                    this.c.b(str, "Unexpected error", "incentCompleted");
                    new StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(e2.getMessage());
                }
            } catch (Exception e3) {
                this.c.b(str, "Unexpected error", "incentCompleted");
                new StringBuilder("SDK encountered unexpected error in handling onUserInteraction() signal from creative; ").append(e3.getMessage());
            }
        } catch (JSONException unused) {
            this.c.getListener().a(new HashMap<>());
        }
    }

    @JavascriptInterface
    public String getOrientation(String str) {
        int iB = com.inmobi.commons.core.utilities.b.c.b();
        if (iB == 1) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        if (iB == 3) {
            return "90";
        }
        if (iB == 2) {
            return "180";
        }
        return iB == 4 ? "270" : "-1";
    }

    @JavascriptInterface
    public void saveContent(String str, String str2, String str3) {
        if (str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0) {
            JSONObject jSONObject = new JSONObject();
            if (str3 == null) {
                str3 = "";
            }
            try {
                jSONObject.put("url", str3);
                jSONObject.put(IronSourceConstants.EVENTS_ERROR_REASON, 1);
            } catch (JSONException unused) {
            }
            String strReplace = jSONObject.toString().replace("\"", "\\\"");
            StringBuilder sb = new StringBuilder("sendSaveContentResult(\"saveContent_");
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            sb.append("\", 'failed', \"");
            sb.append(strReplace);
            sb.append("\");");
            this.c.a(str, sb.toString());
            return;
        }
        try {
            RenderView renderView = this.c;
            StringBuilder sb2 = new StringBuilder("saveContent called: content ID: ");
            sb2.append(str2);
            sb2.append("; URL: ");
            sb2.append(str3);
            if (!renderView.e("saveContent")) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("url", str3);
                    jSONObject2.put(IronSourceConstants.EVENTS_ERROR_REASON, 5);
                } catch (JSONException unused2) {
                }
                renderView.a(str, "sendSaveContentResult(\"saveContent_" + str2 + "\", 'failed', \"" + jSONObject2.toString().replace("\"", "\\\"") + "\");");
                return;
            }
            HashSet hashSet = new HashSet();
            hashSet.add(new bm(-1, str3));
            final com.inmobi.ads.cache.b bVar = new com.inmobi.ads.cache.b(UUID.randomUUID().toString(), hashSet, renderView.w, str2);
            bVar.g = str;
            final AssetStore assetStoreA = AssetStore.a();
            assetStoreA.c.execute(new Runnable() { // from class: com.inmobi.ads.cache.AssetStore.4
                final /* synthetic */ b a;

                public AnonymousClass4() {
                    bVar = bVar;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    AssetStore.this.b(bVar);
                    String unused3 = AssetStore.f;
                    StringBuilder sb3 = new StringBuilder("Attempting to cache ");
                    sb3.append(bVar.b.size());
                    sb3.append("remote URLs ");
                    Iterator<bm> it = bVar.b.iterator();
                    while (it.hasNext()) {
                        AssetStore.b(AssetStore.this, it.next().b);
                    }
                }
            });
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "saveContent");
            new StringBuilder("SDK encountered unexpected error in handling saveContent() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void cancelSaveContent(String str, String str2) {
        try {
            RenderView.d();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "cancelSaveContent");
            new StringBuilder("SDK encountered unexpected error in handling cancelSaveContent() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public String isDeviceMuted(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return "false";
        }
        boolean zA = false;
        try {
            renderView.getMediaProcessor();
            zA = MraidMediaProcessor.a();
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking if device is muted; ").append(e.getMessage());
        }
        return String.valueOf(zA);
    }

    @JavascriptInterface
    public String isHeadphonePlugged(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return "false";
        }
        boolean zD = false;
        try {
            renderView.getMediaProcessor();
            zD = MraidMediaProcessor.d();
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in checking if headphones are plugged-in; ").append(e.getMessage());
        }
        return String.valueOf(zD);
    }

    @JavascriptInterface
    public void registerDeviceMuteEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            MraidMediaProcessor mediaProcessor = renderView.getMediaProcessor();
            Context contextB = com.inmobi.commons.a.a.b();
            if (contextB == null || mediaProcessor.c != null) {
                return;
            }
            mediaProcessor.c = mediaProcessor.new RingerModeChangeReceiver(str);
            contextB.registerReceiver(mediaProcessor.c, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "registerDeviceMuteEventListener");
            new StringBuilder("SDK encountered unexpected error in handling registerDeviceMuteEventListener() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void unregisterDeviceMuteEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            renderView.getMediaProcessor().b();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "unRegisterDeviceMuteEventListener");
            new StringBuilder("SDK encountered unexpected error in handling unregisterDeviceMuteEventListener() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void registerDeviceVolumeChangeEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            MraidMediaProcessor mediaProcessor = renderView.getMediaProcessor();
            Context contextB = com.inmobi.commons.a.a.b();
            if (contextB == null || mediaProcessor.d != null) {
                return;
            }
            mediaProcessor.d = mediaProcessor.new a(str, contextB, new Handler());
            contextB.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, mediaProcessor.d);
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "registerDeviceVolumeChangeEventListener");
            new StringBuilder("SDK encountered unexpected error in handling registerDeviceVolumeChangeEventListener() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void unregisterDeviceVolumeChangeEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            renderView.getMediaProcessor().c();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "unregisterDeviceVolumeChangeEventListener");
            new StringBuilder("SDK encountered unexpected error in handling unregisterDeviceVolumeChangeEventListener() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public int getDeviceVolume(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return -1;
        }
        try {
            MraidMediaProcessor mediaProcessor = renderView.getMediaProcessor();
            Context contextB = com.inmobi.commons.a.a.b();
            if (contextB == null) {
                return -1;
            }
            if (mediaProcessor.a.getRenderingConfig().m && com.inmobi.commons.a.a.d()) {
                return 0;
            }
            return ((AudioManager) contextB.getSystemService("audio")).getStreamVolume(3);
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getDeviceVolume");
            new StringBuilder("SDK encountered unexpected error in handling getDeviceVolume() request from creative; ").append(e.getMessage());
            return -1;
        }
    }

    @JavascriptInterface
    public void registerHeadphonePluggedEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            MraidMediaProcessor mediaProcessor = renderView.getMediaProcessor();
            Context contextB = com.inmobi.commons.a.a.b();
            if (contextB == null || mediaProcessor.e != null) {
                return;
            }
            mediaProcessor.e = mediaProcessor.new HeadphonesPluggedChangeReceiver(str);
            contextB.registerReceiver(mediaProcessor.e, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "registerHeadphonePluggedEventListener");
            new StringBuilder("SDK encountered unexpected error in handling registerHeadphonePluggedEventListener() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void unregisterHeadphonePluggedEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            renderView.getMediaProcessor().e();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "unregisterHeadphonePluggedEventListener");
            new StringBuilder("SDK encountered unexpected error in handling unregisterHeadphonePluggedEventListener() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void disableBackButton(String str, boolean z) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        renderView.setDisableBackButton(z);
    }

    @JavascriptInterface
    public boolean isBackButtonDisabled(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return false;
        }
        return renderView.q;
    }

    @JavascriptInterface
    public void registerBackButtonPressedEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            renderView.r = str;
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "registerBackButtonPressedEventListener");
            new StringBuilder("SDK encountered unexpected error in handling registerBackButtonPressedEventListener() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void unregisterBackButtonPressedEventListener(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            renderView.r = null;
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "unregisterBackButtonPressedEventListener");
            new StringBuilder("SDK encountered unexpected error in handling unregisterBackButtonPressedEventListener() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void startDownloader(String str, String str2, String str3, String str4) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        if (!renderView.e()) {
            this.c.c("startDownloader");
            return;
        }
        try {
            if (TextUtils.isEmpty(str2)) {
                this.c.b(str, "Invalid URL", "startDownloader");
                return;
            }
            RenderView renderView2 = this.c;
            AdContainer referenceContainer = renderView2.getReferenceContainer();
            if (!(referenceContainer instanceof ah)) {
                if (referenceContainer instanceof RenderView) {
                    ak.a(str2, str3, str4);
                }
            } else {
                ak.a(str2, str3, str4);
                ((ah) referenceContainer).a(renderView2);
            }
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "startDownloader");
            new StringBuilder("SDK encountered unexpected error in handling startDownloader() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void registerDownloaderCallbacks(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            AdContainer referenceContainer = renderView.getReferenceContainer();
            if (referenceContainer instanceof ah) {
                ((ah) referenceContainer).a(renderView);
            }
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "registerDownloaderCallbacks");
            new StringBuilder("SDK encountered unexpected error in handling registerDownloaderCallbacks() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void unregisterDownloaderCallbacks(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            AdContainer referenceContainer = renderView.getReferenceContainer();
            if (referenceContainer instanceof ah) {
                ah ahVar = (ah) referenceContainer;
                if (ahVar.z != null) {
                    ahVar.z.remove(renderView);
                }
            }
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "unregisterDownloaderCallbacks");
            new StringBuilder("SDK encountered unexpected error in handling unregisterDownloaderCallbacks() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public int getDownloadProgress(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return -1;
        }
        try {
            return renderView.getDownloadProgress();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getDownloadProgress");
            new StringBuilder("SDK encountered unexpected error in handling getDownloadProgress() request from creative; ").append(e.getMessage());
            return -1;
        }
    }

    @JavascriptInterface
    public int getDownloadStatus(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return -1;
        }
        try {
            return renderView.getDownloadStatus();
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getDownloadStatus");
            new StringBuilder("SDK encountered unexpected error in handling getDownloadStatus() request from creative; ").append(e.getMessage());
            return -1;
        }
    }

    @JavascriptInterface
    public void setCloseEndCardTracker(String str, String str2) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        try {
            renderView.setCloseEndCardTracker(str2);
        } catch (Exception e) {
            this.c.b(str, "Unexpected error", "getDownloadStatus");
            new StringBuilder("SDK encountered unexpected error in handling getDownloadStatus() request from creative; ").append(e.getMessage());
        }
    }

    @JavascriptInterface
    public void fireSkip(String str) {
        RenderView.f();
    }

    @JavascriptInterface
    public void fireComplete(String str) {
        if (this.c == null) {
            return;
        }
        RenderView.g();
    }

    @JavascriptInterface
    public void showEndCard(String str) {
        RenderView renderView = this.c;
        if (renderView == null) {
            return;
        }
        AdContainer referenceContainer = renderView.getReferenceContainer();
        if (referenceContainer instanceof ah) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.ah.6
                public AnonymousClass6() {
                }

                @Override // java.lang.Runnable
                public final void run() {
                    ah ahVar = ah.this;
                    ahVar.r = true;
                    ahVar.c((ak) null);
                }
            });
        }
    }

    @JavascriptInterface
    public void saveBlob(String str, String str2) {
        RenderView renderView = this.c;
        if (renderView == null || renderView.u == null) {
            return;
        }
        renderView.u.e(str2);
    }

    @JavascriptInterface
    public void getBlob(String str, String str2) {
        RenderView renderView = this.c;
        if (renderView == null || renderView.u == null) {
            return;
        }
        renderView.u.a(str, str2, renderView);
    }
}
