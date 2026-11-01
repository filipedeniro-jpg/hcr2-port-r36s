package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.view.View;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ReactiveVideoTrackerPlugin implements MoatPlugin<ReactiveVideoTracker> {
    private final String a;

    static class a implements ReactiveVideoTracker {
        a() {
        }

        @Override // com.moat.analytics.mobile.vng.ReactiveVideoTracker
        public void changeTargetView(View view) {
        }

        @Override // com.moat.analytics.mobile.vng.ReactiveVideoTracker
        public void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        @Override // com.moat.analytics.mobile.vng.ReactiveVideoTracker
        public void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.vng.ReactiveVideoTracker
        public void setPlayerVolume(Double d) {
        }

        @Override // com.moat.analytics.mobile.vng.ReactiveVideoTracker
        public void stopTracking() {
        }

        @Override // com.moat.analytics.mobile.vng.ReactiveVideoTracker
        public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }
    }

    public ReactiveVideoTrackerPlugin(String str) {
        this.a = str;
    }

    @Override // com.moat.analytics.mobile.vng.MoatPlugin
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ReactiveVideoTracker a() {
        return (ReactiveVideoTracker) x.a(new x.a<ReactiveVideoTracker>() { // from class: com.moat.analytics.mobile.vng.ReactiveVideoTrackerPlugin.1
            @Override // com.moat.analytics.mobile.vng.x.a
            public com.moat.analytics.mobile.vng.a.b.a<ReactiveVideoTracker> a() {
                p.a("[INFO] ", "Attempting to create ReactiveVideoTracker");
                return com.moat.analytics.mobile.vng.a.b.a.a(new y(ReactiveVideoTrackerPlugin.this.a));
            }
        }, ReactiveVideoTracker.class);
    }

    @Override // com.moat.analytics.mobile.vng.MoatPlugin
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public ReactiveVideoTracker b() {
        return new a();
    }
}
