package com.applovin.sdk;

/* JADX INFO: loaded from: classes.dex */
public interface AppLovinSdkConfiguration {

    public enum ConsentDialogState {
        UNKNOWN,
        APPLIES,
        DOES_NOT_APPLY
    }

    ConsentDialogState getConsentDialogState();
}
