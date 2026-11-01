package com.amazon.device.ads;

import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: ViewabilityObserver.java */
/* JADX INFO: loaded from: classes.dex */
class AmazonOnWindowFocusChangeListenerFactory {
    AmazonOnWindowFocusChangeListenerFactory() {
    }

    public ViewTreeObserver.OnWindowFocusChangeListener buildOnWindowFocusChangeListener(ViewabilityObserver viewabilityObserver) {
        return new AmazonOnWindowFocusChangeListener(viewabilityObserver);
    }

    /* JADX INFO: compiled from: ViewabilityObserver.java */
    private class AmazonOnWindowFocusChangeListener implements ViewTreeObserver.OnWindowFocusChangeListener {
        private final ViewabilityObserver viewabilityObserver;

        AmazonOnWindowFocusChangeListener(ViewabilityObserver viewabilityObserver) {
            this.viewabilityObserver = viewabilityObserver;
        }

        @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
        public void onWindowFocusChanged(boolean z) {
            this.viewabilityObserver.fireViewableEvent(false);
        }
    }
}
