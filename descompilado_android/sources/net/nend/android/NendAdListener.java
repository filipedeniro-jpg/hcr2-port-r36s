package net.nend.android;

import java.util.EventListener;

/* JADX INFO: loaded from: classes2.dex */
public interface NendAdListener extends EventListener {
    void onClick(NendAdView nendAdView);

    void onDismissScreen(NendAdView nendAdView);

    void onFailedToReceiveAd(NendAdView nendAdView);

    void onReceiveAd(NendAdView nendAdView);
}
