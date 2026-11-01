package com.ironsource.mediationsdk.model;

/* JADX INFO: loaded from: classes2.dex */
public class ApplicationConfigurations {
    private boolean mIsIntegration;
    private ApplicationLogger mLogger;
    private ServerSegmetData mSegmetData;

    public ApplicationConfigurations() {
        this.mLogger = new ApplicationLogger();
    }

    public ApplicationConfigurations(ApplicationLogger applicationLogger, ServerSegmetData serverSegmetData, boolean z) {
        this.mLogger = applicationLogger;
        this.mSegmetData = serverSegmetData;
        this.mIsIntegration = z;
    }

    public ApplicationLogger getLoggerConfigurations() {
        return this.mLogger;
    }

    public ServerSegmetData getSegmetData() {
        return this.mSegmetData;
    }

    public boolean getIntegration() {
        return this.mIsIntegration;
    }
}
