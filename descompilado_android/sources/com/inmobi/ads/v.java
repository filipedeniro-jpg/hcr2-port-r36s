package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import com.integralads.avid.library.inmobi.session.AvidAdSessionManager;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: IasTrackedHtmlAd.java */
/* JADX INFO: loaded from: classes2.dex */
public class v extends bz {
    private static final String d = "v";

    @NonNull
    private final WeakReference<Activity> e;

    @NonNull
    private final ca f;

    @NonNull
    private final AbstractAvidAdSession<WebView> g;
    private final boolean h;

    /* JADX WARN: Code duplicated, block: B:18:0x003b  */
    @Nullable
    static AbstractAvidAdSession<WebView> a(@Nullable Context context, boolean z, @NonNull String str, @Nullable RenderView renderView) {
        byte b;
        AbstractAvidAdSession<WebView> abstractAvidAdSessionStartAvidDisplayAdSession;
        ExternalAvidAdSessionContext externalAvidAdSessionContext = new ExternalAvidAdSessionContext("7.2.1", z);
        int iHashCode = str.hashCode();
        if (iHashCode != -284840886) {
            if (iHashCode != 112202875) {
                if (iHashCode == 1425678798 && str.equals("nonvideo")) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (str.equals("video")) {
                b = 3;
            } else {
                b = -1;
            }
        } else if (str.equals("unknown")) {
            b = 1;
        } else {
            b = -1;
        }
        if (b == 2) {
            abstractAvidAdSessionStartAvidDisplayAdSession = AvidAdSessionManager.startAvidDisplayAdSession(context, externalAvidAdSessionContext);
        } else {
            abstractAvidAdSessionStartAvidDisplayAdSession = b != 3 ? null : AvidAdSessionManager.startAvidVideoAdSession(context, externalAvidAdSessionContext);
        }
        if (abstractAvidAdSessionStartAvidDisplayAdSession != null) {
            if (context instanceof Activity) {
                abstractAvidAdSessionStartAvidDisplayAdSession.registerAdView(renderView, (Activity) context);
            } else {
                abstractAvidAdSessionStartAvidDisplayAdSession.registerAdView(renderView, null);
            }
        }
        return abstractAvidAdSessionStartAvidDisplayAdSession;
    }

    public v(@NonNull AdContainer adContainer, @NonNull Activity activity, @NonNull ca caVar, @NonNull AbstractAvidAdSession<WebView> abstractAvidAdSession, boolean z) {
        super(adContainer);
        this.e = new WeakReference<>(activity);
        this.f = caVar;
        this.g = abstractAvidAdSession;
        this.h = z;
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a() {
        return this.f.a();
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return this.f.a(view, viewGroup, z);
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View b() {
        return this.f.b();
    }

    @Override // com.inmobi.ads.ca
    @NonNull
    final c c() {
        return this.f.c();
    }

    @Override // com.inmobi.ads.ca
    public final void a(@Nullable View... viewArr) {
        try {
            try {
                Activity activity = this.e.get();
                c.k kVar = this.f.c().k;
                if (activity != null && kVar.j) {
                    if (this.a instanceof ah) {
                        ah ahVar = (ah) this.a;
                        if (ahVar.s() != null) {
                            a(activity, ahVar.s(), viewArr);
                        }
                    } else {
                        View viewB = this.f.b();
                        if (viewB != null) {
                            a(activity, (WebView) viewB, viewArr);
                        }
                    }
                }
            } catch (Exception e) {
                new StringBuilder("Exception in startTrackingForImpression with message : ").append(e.getMessage());
            }
        } finally {
            this.f.a(viewArr);
        }
    }

    private void a(Activity activity, WebView webView, @Nullable View[] viewArr) {
        if (viewArr != null) {
            for (View view : viewArr) {
                this.g.registerFriendlyObstruction(view);
            }
        }
        this.g.registerAdView(webView, activity);
        if (!this.h || this.g.getAvidDeferredAdSessionListener() == null) {
            return;
        }
        this.g.getAvidDeferredAdSessionListener().recordReadyEvent();
    }

    @Override // com.inmobi.ads.ca
    public final void a(int i) {
        this.f.a(i);
    }

    @Override // com.inmobi.ads.ca
    public final void a(Context context, int i) {
        this.f.a(context, i);
    }

    @Override // com.inmobi.ads.ca
    public final void e() {
        super.e();
        try {
            try {
                this.e.clear();
            } catch (Exception e) {
                new StringBuilder("Exception in destroy with message : ").append(e.getMessage());
            }
        } finally {
            this.f.e();
        }
    }

    @Override // com.inmobi.ads.ca
    public final void d() {
        ViewParent viewParentS;
        try {
            try {
                if (this.a instanceof ah) {
                    viewParentS = ((ah) this.a).s();
                } else {
                    viewParentS = (WebView) this.f.b();
                }
                this.g.unregisterAdView(viewParentS);
                this.g.endSession();
            } catch (Exception e) {
                new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e.getMessage());
            }
        } finally {
            this.f.d();
        }
    }
}
