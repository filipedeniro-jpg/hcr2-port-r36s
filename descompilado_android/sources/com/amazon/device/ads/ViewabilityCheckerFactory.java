package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
public class ViewabilityCheckerFactory {
    public ViewabilityChecker buildViewabilityChecker(AdController adController) {
        return new ViewabilityChecker(adController);
    }
}
