package com.moat.analytics.mobile.iro;

/* JADX INFO: loaded from: classes2.dex */
public interface TrackerListener {
    void onTrackingFailedToStart(String str);

    void onTrackingStarted(String str);

    void onTrackingStopped(String str);
}
