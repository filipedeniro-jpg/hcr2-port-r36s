package com.ironsource.sdk.analytics.moat;

import android.app.Application;
import android.webkit.WebView;
import com.moat.analytics.mobile.iro.MoatAnalytics;
import com.moat.analytics.mobile.iro.MoatFactory;
import com.moat.analytics.mobile.iro.MoatOptions;
import com.moat.analytics.mobile.iro.TrackerListener;
import com.moat.analytics.mobile.iro.WebAdTracker;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MOATManager {
    private static final String autoTrackGMAInterstitials = "autoTrackGMAInterstitials";
    private static final String disableAdIdCollection = "disableAdIdCollection";
    private static final String disableLocationService = "disableLocationServices";
    private static final String loggingEnabled = "loggingEnabled";
    private static EventsListener mEventsListener;
    private static TrackerListener moatCallbacks = new TrackerListener() { // from class: com.ironsource.sdk.analytics.moat.MOATManager.1
        @Override // com.moat.analytics.mobile.iro.TrackerListener
        public void onTrackingStarted(String str) {
            if (MOATManager.mEventsListener != null) {
                MOATManager.mEventsListener.onTrackingStarted(str);
            }
        }

        @Override // com.moat.analytics.mobile.iro.TrackerListener
        public void onTrackingFailedToStart(String str) {
            if (MOATManager.mEventsListener != null) {
                MOATManager.mEventsListener.onTrackingFailedToStart(str);
            }
        }

        @Override // com.moat.analytics.mobile.iro.TrackerListener
        public void onTrackingStopped(String str) {
            if (MOATManager.mEventsListener != null) {
                MOATManager.mEventsListener.onTrackingStopped(str);
            }
        }
    };
    private static WebAdTracker webAdTracker;

    public interface EventsListener extends TrackerListener {
    }

    public static void setEventListener(EventsListener eventsListener) {
        mEventsListener = eventsListener;
    }

    public static void init(Application application) throws Exception {
        initWithOptions(null, application);
    }

    public static void initWithOptions(JSONObject jSONObject, Application application) throws Exception {
        MoatAnalytics.getInstance().start((jSONObject == null || jSONObject.length() <= 0) ? null : createMoatOptions(jSONObject), application);
    }

    private static MoatOptions createMoatOptions(JSONObject jSONObject) {
        MoatOptions moatOptions = new MoatOptions();
        moatOptions.loggingEnabled = jSONObject.optBoolean(loggingEnabled);
        moatOptions.autoTrackGMAInterstitials = jSONObject.optBoolean(autoTrackGMAInterstitials);
        moatOptions.disableAdIdCollection = jSONObject.optBoolean(disableAdIdCollection);
        moatOptions.disableLocationServices = jSONObject.optBoolean(disableLocationService);
        return moatOptions;
    }

    public static void createAdTracker(WebView webView) throws Exception {
        webAdTracker = MoatFactory.create().createWebAdTracker(webView);
    }

    public static void startTracking() throws Exception {
        WebAdTracker webAdTracker2 = webAdTracker;
        if (webAdTracker2 != null) {
            webAdTracker2.setListener(moatCallbacks);
            webAdTracker.startTracking();
        }
    }

    public static void stopTracking() throws Exception {
        WebAdTracker webAdTracker2 = webAdTracker;
        if (webAdTracker2 != null) {
            webAdTracker2.stopTracking();
        }
    }
}
