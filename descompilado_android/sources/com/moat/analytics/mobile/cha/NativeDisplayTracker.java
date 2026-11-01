package com.moat.analytics.mobile.cha;

import android.app.Activity;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes2.dex */
@UiThread
public interface NativeDisplayTracker {

    public enum MoatUserInteractionType {
        TOUCH,
        CLICK
    }

    void removeListener();

    void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType);

    @Deprecated
    void setActivity(Activity activity);

    void setListener(TrackerListener trackerListener);

    void startTracking();

    void stopTracking();
}
