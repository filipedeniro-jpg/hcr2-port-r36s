package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ReactiveVideoTrackerPlugin implements m<ReactiveVideoTracker> {

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private final String f495;

    public ReactiveVideoTrackerPlugin(String str) {
        this.f495 = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.moat.analytics.mobile.iro.m
    public ReactiveVideoTracker create() throws o {
        return (ReactiveVideoTracker) s.m589(new s.a<ReactiveVideoTracker>() { // from class: com.moat.analytics.mobile.iro.ReactiveVideoTrackerPlugin.5
            @Override // com.moat.analytics.mobile.iro.s.a
            /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
            public final Optional<ReactiveVideoTracker> mo448() {
                com.moat.analytics.mobile.iro.b.m459("[INFO] ", "Attempting to create ReactiveVideoTracker");
                return Optional.of(new w(ReactiveVideoTrackerPlugin.this.f495));
            }
        }, ReactiveVideoTracker.class);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.moat.analytics.mobile.iro.m
    public ReactiveVideoTracker createNoOp() {
        return new b();
    }

    static class b implements ReactiveVideoTracker {
        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final void changeTargetView(View view) {
        }

        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final void removeListener() {
        }

        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final void removeVideoListener() {
        }

        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final void setPlayerVolume(Double d) {
        }

        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final void setVideoListener(VideoTrackerListener videoTrackerListener) {
        }

        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final void stopTracking() {
        }

        @Override // com.moat.analytics.mobile.iro.ReactiveVideoTracker
        public final boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }

        b() {
        }
    }
}
