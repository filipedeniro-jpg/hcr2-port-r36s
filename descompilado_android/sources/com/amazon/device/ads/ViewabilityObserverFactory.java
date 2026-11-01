package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
public class ViewabilityObserverFactory {
    public ViewabilityObserver buildViewabilityObserver(AdController adController) {
        return new ViewabilityObserver(adController);
    }
}
