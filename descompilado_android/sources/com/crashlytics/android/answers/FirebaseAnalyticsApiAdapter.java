package com.crashlytics.android.answers;

import android.content.Context;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.fabric.sdk.android.Fabric;

/* JADX INFO: loaded from: classes.dex */
class FirebaseAnalyticsApiAdapter {
    private final Context context;
    private EventLogger eventLogger;
    private final FirebaseAnalyticsEventMapper eventMapper;

    public FirebaseAnalyticsApiAdapter(Context context) {
        this(context, new FirebaseAnalyticsEventMapper());
    }

    public FirebaseAnalyticsApiAdapter(Context context, FirebaseAnalyticsEventMapper firebaseAnalyticsEventMapper) {
        this.context = context;
        this.eventMapper = firebaseAnalyticsEventMapper;
    }

    public EventLogger getFirebaseAnalytics() {
        if (this.eventLogger == null) {
            this.eventLogger = AppMeasurementEventLogger.getEventLogger(this.context);
        }
        return this.eventLogger;
    }

    public void processEvent(SessionEvent sessionEvent) {
        EventLogger firebaseAnalytics = getFirebaseAnalytics();
        if (firebaseAnalytics == null) {
            Fabric.getLogger().d(Answers.TAG, "Firebase analytics logging was enabled, but not available...");
            return;
        }
        FirebaseAnalyticsEvent firebaseAnalyticsEventMapEvent = this.eventMapper.mapEvent(sessionEvent);
        if (firebaseAnalyticsEventMapEvent == null) {
            Fabric.getLogger().d(Answers.TAG, "Fabric event was not mappable to Firebase event: " + sessionEvent);
            return;
        }
        firebaseAnalytics.logEvent(firebaseAnalyticsEventMapEvent.getEventName(), firebaseAnalyticsEventMapEvent.getEventParams());
        if ("levelEnd".equals(sessionEvent.predefinedType)) {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.POST_SCORE, firebaseAnalyticsEventMapEvent.getEventParams());
        }
    }
}
