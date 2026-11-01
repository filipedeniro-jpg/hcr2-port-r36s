package com.amazon.device.ads;

import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: ViewabilityObserver.java */
/* JADX INFO: loaded from: classes.dex */
class AmazonOnGlobalLayoutListenerFactory {
    AmazonOnGlobalLayoutListenerFactory() {
    }

    public ViewTreeObserver.OnGlobalLayoutListener buildAmazonOnGlobalLayoutListener(ViewabilityObserver viewabilityObserver) {
        return new AmazonOnGlobalLayoutListener(viewabilityObserver);
    }

    /* JADX INFO: compiled from: ViewabilityObserver.java */
    private class AmazonOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private final ViewabilityObserver viewabilityObserver;

        public AmazonOnGlobalLayoutListener(ViewabilityObserver viewabilityObserver) {
            this.viewabilityObserver = viewabilityObserver;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.viewabilityObserver.addOnScrollChangedListenerIfNeeded();
            this.viewabilityObserver.fireViewableEvent(false);
        }
    }
}
