package com.crashlytics.android;

import android.content.Context;
import io.fabric.sdk.android.services.common.DataCollectionArbiter;

/* JADX INFO: loaded from: classes.dex */
class ManifestEnabledCheckStrategy implements CrashlyticsInitProvider.EnabledCheckStrategy {
    ManifestEnabledCheckStrategy() {
    }

    @Override // com.crashlytics.android.CrashlyticsInitProvider.EnabledCheckStrategy
    public boolean isCrashlyticsEnabled(Context context) {
        return DataCollectionArbiter.getInstance(context).shouldAutoInitialize();
    }
}
