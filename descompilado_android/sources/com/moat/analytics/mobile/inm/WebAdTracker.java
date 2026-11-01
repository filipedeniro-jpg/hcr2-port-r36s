package com.moat.analytics.mobile.inm;

import android.app.Activity;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes2.dex */
@UiThread
public interface WebAdTracker {
    void removeListener();

    @Deprecated
    void setActivity(Activity activity);

    void setListener(TrackerListener trackerListener);

    void startTracking();

    void stopTracking();
}
