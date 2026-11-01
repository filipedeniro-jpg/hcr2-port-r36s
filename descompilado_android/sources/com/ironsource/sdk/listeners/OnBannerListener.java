package com.ironsource.sdk.listeners;

/* JADX INFO: loaded from: classes2.dex */
public interface OnBannerListener extends OnAdProductListener {
    void onBannerClick();

    void onBannerInitFailed(String str);

    void onBannerInitSuccess();

    void onBannerLoadFail(String str);

    void onBannerLoadSuccess();
}
