package com.amazon.device.ads;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class CompositeAdListenerExecutor extends AdListenerExecutor {
    private final List<AdListenerExecutor> adListenerExecutors;

    public CompositeAdListenerExecutor(MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        super(null, mobileAdsLoggerFactory);
        this.adListenerExecutors = new ArrayList();
    }

    public void addAdListenerExecutor(AdListenerExecutor adListenerExecutor) {
        this.adListenerExecutors.add(adListenerExecutor);
    }

    private List<AdListenerExecutor> getAdListenerExecutors() {
        return this.adListenerExecutors;
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        while (it.hasNext()) {
            it.next().onAdLoaded(ad, adProperties);
        }
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public void onAdFailedToLoad(Ad ad, AdError adError) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        while (it.hasNext()) {
            it.next().onAdFailedToLoad(ad, adError);
        }
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public void onAdExpanded(Ad ad) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        while (it.hasNext()) {
            it.next().onAdExpanded(ad);
        }
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public void onAdCollapsed(Ad ad) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        while (it.hasNext()) {
            it.next().onAdCollapsed(ad);
        }
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public void onAdDismissed(Ad ad) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        while (it.hasNext()) {
            it.next().onAdDismissed(ad);
        }
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public void onAdResized(Ad ad, Rect rect) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        while (it.hasNext()) {
            it.next().onAdResized(ad, rect);
        }
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public void onAdExpired(Ad ad) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        while (it.hasNext()) {
            it.next().onAdExpired(ad);
        }
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public void onSpecialUrlClicked(Ad ad, String str) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        while (it.hasNext()) {
            it.next().onSpecialUrlClicked(ad, str);
        }
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public ActionCode onAdReceived(Ad ad, AdData adData) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        if (it.hasNext()) {
            return it.next().onAdReceived(ad, adData);
        }
        return null;
    }

    @Override // com.amazon.device.ads.AdListenerExecutor
    public void onAdEvent(AdEvent adEvent) {
        Iterator<AdListenerExecutor> it = getAdListenerExecutors().iterator();
        while (it.hasNext()) {
            it.next().onAdEvent(adEvent);
        }
    }
}
