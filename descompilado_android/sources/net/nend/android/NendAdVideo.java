package net.nend.android;

import android.app.Activity;

/* JADX INFO: loaded from: classes2.dex */
public interface NendAdVideo {
    boolean isLoaded();

    void loadAd();

    void releaseAd();

    void setLocationEnabled(boolean z);

    void setMediationName(String str);

    void setUserFeature(NendAdUserFeature nendAdUserFeature);

    void setUserId(String str);

    void showAd(Activity activity);
}
