package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import io.fabric.sdk.android.Fabric;

/* JADX INFO: loaded from: classes2.dex */
public class DataCollectionArbiter {
    private static final String FIREBASE_CRASHLYTICS_COLLECTION_ENABLED = "firebase_crashlytics_collection_enabled";
    private static final String FIREBASE_CRASHLYTICS_PREFS = "com.google.firebase.crashlytics.prefs";
    private static DataCollectionArbiter instance;
    private static Object instanceLock = new Object();
    private volatile boolean crashlyticsDataCollectionEnabled;
    private volatile boolean crashlyticsDataCollectionExplicitlySet;
    private final FirebaseApp firebaseApp;
    private boolean isUnity;
    private final SharedPreferences sharedPreferences;

    public static DataCollectionArbiter getInstance(Context context) {
        DataCollectionArbiter dataCollectionArbiter;
        synchronized (instanceLock) {
            if (instance == null) {
                instance = new DataCollectionArbiter(context);
            }
            dataCollectionArbiter = instance;
        }
        return dataCollectionArbiter;
    }

    public static void resetForTesting(Context context) {
        synchronized (instanceLock) {
            instance = new DataCollectionArbiter(context);
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0066  */
    private DataCollectionArbiter(Context context) {
        boolean z;
        boolean z2;
        ApplicationInfo applicationInfo;
        this.isUnity = false;
        if (context == null) {
            throw new RuntimeException("null context");
        }
        this.sharedPreferences = context.getSharedPreferences(FIREBASE_CRASHLYTICS_PREFS, 0);
        this.firebaseApp = FirebaseAppImpl.getInstance(context);
        if (this.sharedPreferences.contains(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
            z = this.sharedPreferences.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, true);
        } else {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED)) {
                    z = true;
                    z2 = false;
                } else {
                    z = applicationInfo.metaData.getBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED);
                }
            } catch (PackageManager.NameNotFoundException e) {
                Fabric.getLogger().d(Fabric.TAG, "Unable to get PackageManager. Falling through", e);
            }
            this.crashlyticsDataCollectionEnabled = z;
            this.crashlyticsDataCollectionExplicitlySet = z2;
            this.isUnity = CommonUtils.resolveUnityEditorVersion(context) != null;
        }
        z2 = true;
        this.crashlyticsDataCollectionEnabled = z;
        this.crashlyticsDataCollectionExplicitlySet = z2;
        this.isUnity = CommonUtils.resolveUnityEditorVersion(context) != null;
    }

    public boolean isDataCollectionEnabled() {
        if (this.isUnity && this.crashlyticsDataCollectionExplicitlySet) {
            return this.crashlyticsDataCollectionEnabled;
        }
        FirebaseApp firebaseApp = this.firebaseApp;
        if (firebaseApp != null) {
            return firebaseApp.isDataCollectionDefaultEnabled();
        }
        return true;
    }

    public boolean shouldAutoInitialize() {
        return this.crashlyticsDataCollectionEnabled;
    }

    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public void setCrashlyticsDataCollectionEnabled(boolean z) {
        this.crashlyticsDataCollectionEnabled = z;
        this.crashlyticsDataCollectionExplicitlySet = true;
        this.sharedPreferences.edit().putBoolean(FIREBASE_CRASHLYTICS_COLLECTION_ENABLED, z).commit();
    }
}
