package com.fingersoft.hcr2.firebase;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.fingersoft.hcr2.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/* JADX INFO: loaded from: classes.dex */
public class CFirebaseAnalytics {
    private static final int FIREBASE_REMOTE_CONFIG_CACHE_TIME = 43200;
    private static final String TAG = "hcr2 - CFirebaseAnalytics";
    private Activity mActivity;
    private FirebaseListener mEventListener;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    public CFirebaseAnalytics(Activity activity, String str, FirebaseListener firebaseListener) {
        try {
            FirebaseApp.initializeApp(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mActivity = activity;
        this.mEventListener = firebaseListener;
        this.mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this.mActivity);
        this.mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        this.mFirebaseAnalytics.setUserId(str);
        getConfig();
    }

    public void setUserID(String str) {
        this.mFirebaseAnalytics.setUserId(str);
        this.mFirebaseAnalytics.setUserProperty("player_id", str);
    }

    private void getConfig() {
        this.mFirebaseRemoteConfig.setConfigSettings(new FirebaseRemoteConfigSettings.Builder().build());
        this.mFirebaseRemoteConfig.fetch(43200L).addOnCompleteListener(this.mActivity, new OnCompleteListener<Void>() { // from class: com.fingersoft.hcr2.firebase.CFirebaseAnalytics.1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    CFirebaseAnalytics.this.mFirebaseRemoteConfig.activateFetched();
                    CFirebaseAnalytics.this.mEventListener.onRemoteConfigReadSuccess();
                } else {
                    CFirebaseAnalytics.this.mEventListener.onRemoteConfigReadFailed();
                }
            }
        });
    }

    public String getConfigString(String str) {
        return this.mFirebaseRemoteConfig.getString(str);
    }

    public boolean getConfigBoolean(String str) {
        return this.mFirebaseRemoteConfig.getBoolean(str);
    }

    public FirebaseRemoteConfig getRemoteConfig() {
        return this.mFirebaseRemoteConfig;
    }

    public double getConfigDouble(String str) {
        return this.mFirebaseRemoteConfig.getDouble(str);
    }

    public long getConfigLong(String str) {
        return this.mFirebaseRemoteConfig.getLong(str);
    }

    public void trackEvent(String str, Bundle bundle) {
        Log.d(TAG, "CFirebaseAnalytics event: " + str + " with param count: " + bundle.size());
        this.mFirebaseAnalytics.logEvent(str, bundle);
    }

    public void trackUserProperty(String str, String str2) {
        Log.d(TAG, "CFirebaseAnalytics user property " + str + ": " + str2);
        this.mFirebaseAnalytics.setUserProperty(str, str2);
    }
}
