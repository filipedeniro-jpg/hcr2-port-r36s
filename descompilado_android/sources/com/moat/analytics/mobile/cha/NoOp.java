package com.moat.analytics.mobile.cha;

import android.app.Activity;
import android.app.Application;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
abstract class NoOp {

    public static class MoatAnalytics extends com.moat.analytics.mobile.cha.MoatAnalytics {
        @Override // com.moat.analytics.mobile.cha.MoatAnalytics
        public void prepareNativeDisplayTracking(String str) {
        }

        @Override // com.moat.analytics.mobile.cha.MoatAnalytics
        public void start(Application application) {
        }

        @Override // com.moat.analytics.mobile.cha.MoatAnalytics
        public void start(MoatOptions moatOptions, Application application) {
        }
    }

    NoOp() {
    }

    static class e implements WebAdTracker {
        @Override // com.moat.analytics.mobile.cha.WebAdTracker
        public final void removeListener() {
        }

        @Override // com.moat.analytics.mobile.cha.WebAdTracker
        public final void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.cha.WebAdTracker
        public final void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.cha.WebAdTracker
        public final void startTracking() {
        }

        @Override // com.moat.analytics.mobile.cha.WebAdTracker
        public final void stopTracking() {
        }

        e() {
        }
    }

    static class c implements NativeDisplayTracker {
        @Override // com.moat.analytics.mobile.cha.NativeDisplayTracker
        public final void removeListener() {
        }

        @Override // com.moat.analytics.mobile.cha.NativeDisplayTracker
        public final void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        }

        @Override // com.moat.analytics.mobile.cha.NativeDisplayTracker
        public final void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.cha.NativeDisplayTracker
        public final void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.cha.NativeDisplayTracker
        public final void startTracking() {
        }

        @Override // com.moat.analytics.mobile.cha.NativeDisplayTracker
        public final void stopTracking() {
        }

        c() {
        }
    }

    static class b implements NativeVideoTracker {
        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final void changeTargetView(View view) {
        }

        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final void removeListener() {
        }

        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final void removeVideoListener() {
        }

        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final void setPlayerVolume(Double d) {
        }

        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final void setVideoListener(VideoTrackerListener videoTrackerListener) {
        }

        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final void stopTracking() {
        }

        @Override // com.moat.analytics.mobile.cha.NativeVideoTracker
        public final boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
            return false;
        }

        b() {
        }
    }

    public static class MoatFactory extends com.moat.analytics.mobile.cha.MoatFactory {
        @Override // com.moat.analytics.mobile.cha.MoatFactory
        public WebAdTracker createWebAdTracker(@NonNull WebView webView) {
            return new e();
        }

        @Override // com.moat.analytics.mobile.cha.MoatFactory
        public WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
            return new e();
        }

        @Override // com.moat.analytics.mobile.cha.MoatFactory
        public NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map) {
            return new c();
        }

        @Override // com.moat.analytics.mobile.cha.MoatFactory
        public NativeVideoTracker createNativeVideoTracker(@NonNull String str) {
            return new b();
        }

        @Override // com.moat.analytics.mobile.cha.MoatFactory
        public <T> T createCustomTracker(l<T> lVar) {
            return lVar.createNoOp();
        }
    }
}
