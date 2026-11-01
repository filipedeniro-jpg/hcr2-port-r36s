package com.applovin.sdk;

import android.app.Activity;

/* JADX INFO: loaded from: classes.dex */
public interface AppLovinUserService {

    public interface OnConsentDialogDismissListener {
        void onDismiss();
    }

    void showConsentDialog(Activity activity, OnConsentDialogDismissListener onConsentDialogDismissListener);
}
