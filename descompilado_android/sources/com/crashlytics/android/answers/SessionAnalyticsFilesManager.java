package com.crashlytics.android.answers;

import android.content.Context;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.events.EventsStorage;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.io.IOException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
class SessionAnalyticsFilesManager extends EventsFilesManager<SessionEvent> {
    private static final String SESSION_ANALYTICS_TO_SEND_FILE_EXTENSION = ".tap";
    private static final String SESSION_ANALYTICS_TO_SEND_FILE_PREFIX = "sa";
    private AnalyticsSettingsData analyticsSettingsData;

    SessionAnalyticsFilesManager(Context context, SessionEventTransform sessionEventTransform, CurrentTimeProvider currentTimeProvider, EventsStorage eventsStorage) throws IOException {
        super(context, sessionEventTransform, currentTimeProvider, eventsStorage, 100);
    }

    @Override // io.fabric.sdk.android.services.events.EventsFilesManager
    protected String generateUniqueRollOverFileName() {
        return SESSION_ANALYTICS_TO_SEND_FILE_PREFIX + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + UUID.randomUUID().toString() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.currentTimeProvider.getCurrentTimeMillis() + SESSION_ANALYTICS_TO_SEND_FILE_EXTENSION;
    }

    @Override // io.fabric.sdk.android.services.events.EventsFilesManager
    protected int getMaxFilesToKeep() {
        AnalyticsSettingsData analyticsSettingsData = this.analyticsSettingsData;
        return analyticsSettingsData == null ? super.getMaxFilesToKeep() : analyticsSettingsData.maxPendingSendFileCount;
    }

    @Override // io.fabric.sdk.android.services.events.EventsFilesManager
    protected int getMaxByteSizePerFile() {
        AnalyticsSettingsData analyticsSettingsData = this.analyticsSettingsData;
        return analyticsSettingsData == null ? super.getMaxByteSizePerFile() : analyticsSettingsData.maxByteSizePerFile;
    }

    void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData) {
        this.analyticsSettingsData = analyticsSettingsData;
    }
}
