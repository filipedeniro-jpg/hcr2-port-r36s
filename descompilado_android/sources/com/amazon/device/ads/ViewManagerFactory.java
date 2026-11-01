package com.amazon.device.ads;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
class ViewManagerFactory {
    private ViewGroup viewGroup;

    ViewManagerFactory() {
    }

    public ViewManagerFactory withViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        return this;
    }

    public ViewManager createViewManager() {
        return new ViewManager(this.viewGroup);
    }
}
