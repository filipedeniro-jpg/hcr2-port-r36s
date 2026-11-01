package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.app.Application;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
abstract class v {

    public static class a extends MoatAnalytics {
        @Override // com.moat.analytics.mobile.inm.MoatAnalytics
        public void prepareNativeDisplayTracking(String str) {
        }

        @Override // com.moat.analytics.mobile.inm.MoatAnalytics
        public void start(Application application) {
        }

        @Override // com.moat.analytics.mobile.inm.MoatAnalytics
        public void start(MoatOptions moatOptions, Application application) {
        }
    }

    public static class b extends MoatFactory {
        @Override // com.moat.analytics.mobile.inm.MoatFactory
        public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
            return moatPlugin.b();
        }

        @Override // com.moat.analytics.mobile.inm.MoatFactory
        public NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map) {
            return new c();
        }

        @Override // com.moat.analytics.mobile.inm.MoatFactory
        public NativeVideoTracker createNativeVideoTracker(@NonNull String str) {
            return new d();
        }

        @Override // com.moat.analytics.mobile.inm.MoatFactory
        public WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
            return new e();
        }

        @Override // com.moat.analytics.mobile.inm.MoatFactory
        public WebAdTracker createWebAdTracker(@NonNull WebView webView) {
            return new e();
        }
    }

    static class c implements NativeDisplayTracker {
        c() {
        }

        @Override // com.moat.analytics.mobile.inm.NativeDisplayTracker
        public void removeListener() {
        }

        @Override // com.moat.analytics.mobile.inm.NativeDisplayTracker
        public void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        }

        @Override // com.moat.analytics.mobile.inm.NativeDisplayTracker
        public void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.inm.NativeDisplayTracker
        public void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.inm.NativeDisplayTracker
        public void startTracking() {
        }

        @Override // com.moat.analytics.mobile.inm.NativeDisplayTracker
        public void stopTracking() {
        }
    }

    static class d implements NativeVideoTracker {
        d() {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public void changeTargetView(View view) {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public void removeListener() {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public void removeVideoListener() {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public void setPlayerVolume(Double d) {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public void stopTracking() {
        }

        @Override // com.moat.analytics.mobile.inm.NativeVideoTracker
        public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
            return false;
        }
    }

    static class e implements WebAdTracker {
        e() {
        }

        @Override // com.moat.analytics.mobile.inm.WebAdTracker
        public void removeListener() {
        }

        @Override // com.moat.analytics.mobile.inm.WebAdTracker
        public void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.inm.WebAdTracker
        public void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.inm.WebAdTracker
        public void startTracking() {
        }

        @Override // com.moat.analytics.mobile.inm.WebAdTracker
        public void stopTracking() {
        }
    }

    v() {
    }
}
