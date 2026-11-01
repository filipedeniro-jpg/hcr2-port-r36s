package com.google.ads.mediation.chartboost;

import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;

/* JADX INFO: loaded from: classes.dex */
public class ChartboostParams {
    private String appId;
    private String appSignature;
    private Chartboost.CBFramework cbFramework;
    private String cbFrameworkVersion;
    private String cbLocation = CBLocation.LOCATION_DEFAULT;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getAppSignature() {
        return this.appSignature;
    }

    public void setAppSignature(String str) {
        this.appSignature = str;
    }

    public String getLocation() {
        return this.cbLocation;
    }

    public void setLocation(String str) {
        this.cbLocation = str;
    }

    public Chartboost.CBFramework getFramework() {
        return this.cbFramework;
    }

    public void setFramework(Chartboost.CBFramework cBFramework) {
        this.cbFramework = cBFramework;
    }

    public String getFrameworkVersion() {
        return this.cbFrameworkVersion;
    }

    public void setFrameworkVersion(String str) {
        this.cbFrameworkVersion = str;
    }
}
