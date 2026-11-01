package com.moat.analytics.mobile.iro;

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

    public static class MoatAnalytics extends com.moat.analytics.mobile.iro.MoatAnalytics {
        @Override // com.moat.analytics.mobile.iro.MoatAnalytics
        public void prepareNativeDisplayTracking(String str) {
        }

        @Override // com.moat.analytics.mobile.iro.MoatAnalytics
        public void start(Application application) {
        }

        @Override // com.moat.analytics.mobile.iro.MoatAnalytics
        public void start(MoatOptions moatOptions, Application application) {
        }
    }

    NoOp() {
    }

    static class b implements WebAdTracker {
        @Override // com.moat.analytics.mobile.iro.WebAdTracker
        public final void removeListener() {
        }

        @Override // com.moat.analytics.mobile.iro.WebAdTracker
        public final void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.iro.WebAdTracker
        public final void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.iro.WebAdTracker
        public final void startTracking() {
        }

        @Override // com.moat.analytics.mobile.iro.WebAdTracker
        public final void stopTracking() {
        }

        b() {
        }
    }

    static class a implements NativeDisplayTracker {
        @Override // com.moat.analytics.mobile.iro.NativeDisplayTracker
        public final void removeListener() {
        }

        @Override // com.moat.analytics.mobile.iro.NativeDisplayTracker
        public final void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        }

        @Override // com.moat.analytics.mobile.iro.NativeDisplayTracker
        public final void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.iro.NativeDisplayTracker
        public final void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.iro.NativeDisplayTracker
        public final void startTracking() {
        }

        @Override // com.moat.analytics.mobile.iro.NativeDisplayTracker
        public final void stopTracking() {
        }

        a() {
        }
    }

    static class c implements NativeVideoTracker {
        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final void changeTargetView(View view) {
        }

        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final void removeListener() {
        }

        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final void removeVideoListener() {
        }

        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final void setActivity(Activity activity) {
        }

        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final void setListener(TrackerListener trackerListener) {
        }

        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final void setPlayerVolume(Double d) {
        }

        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final void setVideoListener(VideoTrackerListener videoTrackerListener) {
        }

        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final void stopTracking() {
        }

        @Override // com.moat.analytics.mobile.iro.NativeVideoTracker
        public final boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
            return false;
        }

        c() {
        }
    }

    public static class MoatFactory extends com.moat.analytics.mobile.iro.MoatFactory {
        @Override // com.moat.analytics.mobile.iro.MoatFactory
        public WebAdTracker createWebAdTracker(@NonNull WebView webView) {
            return new b();
        }

        @Override // com.moat.analytics.mobile.iro.MoatFactory
        public WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
            return new b();
        }

        @Override // com.moat.analytics.mobile.iro.MoatFactory
        public NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map) {
            return new a();
        }

        @Override // com.moat.analytics.mobile.iro.MoatFactory
        public NativeVideoTracker createNativeVideoTracker(@NonNull String str) {
            return new c();
        }

        @Override // com.moat.analytics.mobile.iro.MoatFactory
        public <T> T createCustomTracker(m<T> mVar) {
            return mVar.createNoOp();
        }
    }
}
