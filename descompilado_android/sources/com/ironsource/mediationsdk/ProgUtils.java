package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.utils.IronSourceConstants;

/* JADX INFO: loaded from: classes2.dex */
public class ProgUtils {
    private static final Object lock = new Object();

    public static AbstractAdapter loadAdapter(ProviderSettings providerSettings) {
        synchronized (lock) {
            String providerTypeForReflection = providerSettings.isMultipleInstances() ? providerSettings.getProviderTypeForReflection() : providerSettings.getProviderName();
            try {
                AbstractAdapter loadedAdapterOrFetchByReflection = getLoadedAdapterOrFetchByReflection(providerTypeForReflection, providerSettings.getProviderTypeForReflection());
                if (loadedAdapterOrFetchByReflection == null) {
                    return null;
                }
                loadedAdapterOrFetchByReflection.setLogListener(IronSourceLoggerManager.getLogger());
                return loadedAdapterOrFetchByReflection;
            } catch (Throwable th) {
                logErrorInternal("loadAdapter(" + providerTypeForReflection + ") " + th.getMessage());
                return null;
            }
        }
    }

    private static AbstractAdapter getLoadedAdapterOrFetchByReflection(String str, String str2) {
        try {
            AbstractAdapter existingAdapter = IronSourceObject.getInstance().getExistingAdapter(str);
            if (existingAdapter != null) {
                return existingAdapter;
            }
            Class<?> cls = Class.forName("com.ironsource.adapters." + str2.toLowerCase() + "." + str2 + "Adapter");
            return (AbstractAdapter) cls.getMethod(IronSourceConstants.START_ADAPTER, String.class).invoke(cls, str);
        } catch (Exception e) {
            logErrorInternal("getLoadedAdapterOrFetchByReflection " + e.getMessage());
            return null;
        }
    }

    private static void logErrorInternal(String str) {
        IronSourceLoggerManager.getLogger().log(IronSourceLogger.IronSourceTag.INTERNAL, str, 3);
    }
}
