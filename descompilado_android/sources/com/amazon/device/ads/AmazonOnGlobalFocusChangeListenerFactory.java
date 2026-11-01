package com.amazon.device.ads;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: ViewabilityObserver.java */
/* JADX INFO: loaded from: classes.dex */
class AmazonOnGlobalFocusChangeListenerFactory {
    AmazonOnGlobalFocusChangeListenerFactory() {
    }

    public ViewTreeObserver.OnGlobalFocusChangeListener buildAmazonOnGlobalFocusChangedListener(ViewabilityObserver viewabilityObserver) {
        return new AmazonOnGlobalFocusChangeListener(viewabilityObserver);
    }

    /* JADX INFO: compiled from: ViewabilityObserver.java */
    private class AmazonOnGlobalFocusChangeListener implements ViewTreeObserver.OnGlobalFocusChangeListener {
        private final ViewabilityObserver viewabilityObserver;

        public AmazonOnGlobalFocusChangeListener(ViewabilityObserver viewabilityObserver) {
            this.viewabilityObserver = viewabilityObserver;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
        public void onGlobalFocusChanged(View view, View view2) {
            this.viewabilityObserver.fireViewableEvent(false);
        }
    }
}
