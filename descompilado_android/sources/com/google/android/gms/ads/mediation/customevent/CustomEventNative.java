package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public interface CustomEventNative extends CustomEvent {
    void requestNativeAd(Context context, CustomEventNativeListener customEventNativeListener, String str, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle);
}
