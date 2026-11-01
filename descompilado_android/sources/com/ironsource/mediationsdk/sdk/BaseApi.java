package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

/* JADX INFO: loaded from: classes2.dex */
public interface BaseApi {
    void onPause(Activity activity);

    void onResume(Activity activity);

    void setAge(int i);

    void setGender(String str);

    void setMediationSegment(String str);
}
