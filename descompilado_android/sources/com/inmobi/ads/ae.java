package com.inmobi.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.moat.analytics.mobile.inm.NativeDisplayTracker;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: MoatTrackedNativeV2DisplayAd.java */
/* JADX INFO: loaded from: classes2.dex */
class ae extends bz {
    private final String d = ae.class.getSimpleName();

    @NonNull
    private final WeakReference<Activity> e;
    private NativeDisplayTracker f;

    @NonNull
    private Map<String, Object> g;

    @NonNull
    private ca h;

    ae(@NonNull Activity activity, @NonNull ca caVar, @NonNull Map<String, Object> map) {
        this.e = new WeakReference<>(activity);
        this.h = caVar;
        this.g = map;
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a() {
        return this.h.a();
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return this.h.a(view, viewGroup, z);
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View b() {
        return this.h.b();
    }

    @Override // com.inmobi.ads.ca
    public final ca.a f() {
        return this.h.f();
    }

    @Override // com.inmobi.ads.ca
    @NonNull
    final c c() {
        return this.h.c();
    }

    @Override // com.inmobi.ads.ca
    public final void a(@Nullable View... viewArr) {
        try {
            try {
                View viewB = this.h.b();
                if (viewB != null) {
                    Activity activity = this.e.get();
                    if (this.h.c().k.i && activity != null && ((Boolean) this.g.get("enabled")).booleanValue()) {
                        if (this.f == null) {
                            Application application = activity.getApplication();
                            String str = (String) this.g.get("partnerCode");
                            HashMap<String, String> mapA = i.c.a("moatClientLevel", "moatClientSlicer", (JSONArray) this.g.get("clientLevels"), (JSONArray) this.g.get("clientSlicers"), (JSONObject) this.g.get("zMoatExtras"));
                            mapA.put("zMoatIID", (String) this.g.get("zMoatIID"));
                            this.f = z.a(application, str, viewB, mapA);
                        }
                        viewB.setOnTouchListener(new View.OnTouchListener() { // from class: com.inmobi.ads.ae.1
                            @Override // android.view.View.OnTouchListener
                            public final boolean onTouch(View view, MotionEvent motionEvent) {
                                ae.this.f.reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType.TOUCH);
                                StringBuilder sb = new StringBuilder("Received touch event for DisplayTracker(");
                                sb.append(ae.this.f.hashCode());
                                sb.append(")");
                                return true;
                            }
                        });
                        this.f.startTracking();
                        new StringBuilder("Moat initialized for Native Display for ID : ").append(this.g.get("zMoatIID"));
                    }
                }
            } catch (Exception e) {
                new StringBuilder("Exception in startTrackingForImpression with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.h.a(viewArr);
        }
    }

    @Override // com.inmobi.ads.ca
    public final void d() {
        try {
            try {
                if (this.f != null) {
                    this.f.stopTracking();
                    new StringBuilder("Moat stopped tracking for Native Display for ID : ").append(this.g.get("zMoatIID"));
                }
            } catch (Exception e) {
                new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.h.d();
        }
    }

    @Override // com.inmobi.ads.ca
    public final void a(int i) {
        try {
            if (4 == i) {
                try {
                    this.f.reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType.CLICK);
                    StringBuilder sb = new StringBuilder("Received click event for DisplayTracker(");
                    sb.append(this.f.hashCode());
                    sb.append(")");
                } catch (Exception e) {
                    new StringBuilder("Exception in onAdEvent with message : ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        } finally {
            this.h.a(i);
        }
    }

    @Override // com.inmobi.ads.ca
    public final void a(Context context, int i) {
        this.h.a(context, i);
    }

    @Override // com.inmobi.ads.ca
    public final void e() {
        this.f = null;
        this.e.clear();
        super.e();
        this.h.e();
    }
}
