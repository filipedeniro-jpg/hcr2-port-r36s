package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.moat.analytics.mobile.inm.WebAdTracker;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: compiled from: MoatTrackedHtmlAd.java */
/* JADX INFO: loaded from: classes2.dex */
public class ad extends bz {
    private static final String d = "ad";

    @NonNull
    private final WeakReference<Activity> e;

    @NonNull
    private final ca f;

    @NonNull
    private final Map<String, Object> g;
    private WebAdTracker h;

    public ad(@NonNull AdContainer adContainer, @NonNull Activity activity, @NonNull ca caVar, @NonNull Map<String, Object> map) {
        super(adContainer);
        this.e = new WeakReference<>(activity);
        this.f = caVar;
        this.g = map;
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
                if (this.f.c().k.i && activity != null && ((Boolean) this.g.get("enabled")).booleanValue()) {
                    if (this.h == null) {
                        if (this.a instanceof ah) {
                            ah ahVar = (ah) this.a;
                            if (ahVar.s() != null) {
                                this.h = z.a(activity.getApplication(), ahVar.s());
                            }
                        } else {
                            View viewB = this.f.b();
                            if (viewB != null) {
                                this.h = z.a(activity.getApplication(), (WebView) viewB);
                            }
                        }
                    }
                    this.h.startTracking();
                }
            } catch (Exception e) {
                new StringBuilder("Exception in startTrackingForImpression with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.f.a(viewArr);
        }
    }

    @Override // com.inmobi.ads.ca
    public final void d() {
        try {
            try {
                if (this.h != null) {
                    this.h.stopTracking();
                }
            } catch (Exception e) {
                new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.f.d();
        }
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
        this.h = null;
        this.e.clear();
        super.e();
        this.f.e();
    }
}
