package com.fingersoft.hcr2.firebase;

import android.app.Activity;

/* JADX INFO: loaded from: classes.dex */
public class Firebase {
    private Activity mActivity;
    private CFirebaseAds mFirebaseAds;
    private CFirebaseAnalytics mFirebaseAnalytics;

    public Firebase(Activity activity) {
        this.mActivity = activity;
    }

    public void initializeAnalyticsWithUserId(String str, FirebaseListener firebaseListener) {
        if (this.mFirebaseAnalytics == null) {
            this.mFirebaseAnalytics = new CFirebaseAnalytics(this.mActivity, str, firebaseListener);
        }
    }

    public void initialiseAds(String str, String str2, String str3, FirebaseAdListener firebaseAdListener) {
        if (this.mFirebaseAds == null) {
            this.mFirebaseAds = new CFirebaseAds(this.mActivity, str, str2, str3, firebaseAdListener);
        }
    }

    public CFirebaseAnalytics getAnalyticsInstance() {
        return this.mFirebaseAnalytics;
    }

    public CFirebaseAds getAdsInstance() {
        return this.mFirebaseAds;
    }
}
