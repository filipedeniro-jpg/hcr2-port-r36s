package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.view.View;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ReactiveVideoTrackerPlugin implements MoatPlugin<ReactiveVideoTracker> {
    private final String a;

    static class a implements ReactiveVideoTracker {
        a() {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public void changeTargetView(View view) {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public void removeListener() {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public void removeVideoListener() {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public void setPlayerVolume(Double d) {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public void stopTracking() {
        }

        @Override // com.moat.analytics.mobile.inm.ReactiveVideoTracker
        public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }
    }

    public ReactiveVideoTrackerPlugin(String str) {
        this.a = str;
    }

    @Override // com.moat.analytics.mobile.inm.MoatPlugin
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public ReactiveVideoTracker a() {
        return (ReactiveVideoTracker) x.a(new x.a<ReactiveVideoTracker>() { // from class: com.moat.analytics.mobile.inm.ReactiveVideoTrackerPlugin.1
            @Override // com.moat.analytics.mobile.inm.x.a
            public com.moat.analytics.mobile.inm.a.b.a<ReactiveVideoTracker> a() {
                p.a("[INFO] ", "Attempting to create ReactiveVideoTracker");
                return com.moat.analytics.mobile.inm.a.b.a.a(new y(ReactiveVideoTrackerPlugin.this.a));
            }
        }, ReactiveVideoTracker.class);
    }

    @Override // com.moat.analytics.mobile.inm.MoatPlugin
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public ReactiveVideoTracker b() {
        return new a();
    }
}
