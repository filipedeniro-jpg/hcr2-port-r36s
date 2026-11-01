package com.crashlytics.android.core;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;

/* JADX INFO: loaded from: classes.dex */
class ResourceUnityVersionProvider implements UnityVersionProvider {
    private final Context context;
    private final UnityVersionProvider fallback;
    private boolean hasRead = false;
    private String unityVersion;

    public ResourceUnityVersionProvider(Context context, UnityVersionProvider unityVersionProvider) {
        this.context = context;
        this.fallback = unityVersionProvider;
    }

    @Override // com.crashlytics.android.core.UnityVersionProvider
    public String getUnityVersion() {
        if (!this.hasRead) {
            this.unityVersion = CommonUtils.resolveUnityEditorVersion(this.context);
            this.hasRead = true;
        }
        String str = this.unityVersion;
        if (str != null) {
            return str;
        }
        UnityVersionProvider unityVersionProvider = this.fallback;
        if (unityVersionProvider != null) {
            return unityVersionProvider.getUnityVersion();
        }
        return null;
    }
}
