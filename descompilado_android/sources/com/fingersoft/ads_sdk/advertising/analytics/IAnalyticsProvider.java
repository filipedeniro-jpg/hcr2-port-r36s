package com.fingersoft.ads_sdk.advertising.analytics;

/* JADX INFO: loaded from: classes.dex */
public interface IAnalyticsProvider {
    void setMainEventId(String str);

    void startTracking();

    void stopTracking();

    void trackPageView(String str);
}
