package com.crashlytics.android.answers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.Crash;
import io.fabric.sdk.android.services.common.DataCollectionArbiter;
import io.fabric.sdk.android.services.common.FirebaseInfo;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.settings.Settings;
import io.fabric.sdk.android.services.settings.SettingsData;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class Answers extends Kit<Boolean> {
    static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    public static final String TAG = "Answers";
    SessionAnalyticsManager analyticsManager;
    boolean firebaseEnabled = false;

    @Override // io.fabric.sdk.android.Kit
    public String getIdentifier() {
        return "com.crashlytics.sdk.android:answers";
    }

    @Override // io.fabric.sdk.android.Kit
    public String getVersion() {
        return "1.4.7.32";
    }

    public static Answers getInstance() {
        return (Answers) Fabric.getKit(Answers.class);
    }

    public void logCustom(CustomEvent customEvent) {
        if (customEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logCustom");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onCustom(customEvent);
        }
    }

    public void logPurchase(PurchaseEvent purchaseEvent) {
        if (purchaseEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logPurchase");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(purchaseEvent);
        }
    }

    public void logLogin(LoginEvent loginEvent) {
        if (loginEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logLogin");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(loginEvent);
        }
    }

    public void logShare(ShareEvent shareEvent) {
        if (shareEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logShare");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(shareEvent);
        }
    }

    public void logInvite(InviteEvent inviteEvent) {
        if (inviteEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logInvite");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(inviteEvent);
        }
    }

    public void logSignUp(SignUpEvent signUpEvent) {
        if (signUpEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logSignUp");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(signUpEvent);
        }
    }

    public void logLevelStart(LevelStartEvent levelStartEvent) {
        if (levelStartEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logLevelStart");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(levelStartEvent);
        }
    }

    public void logLevelEnd(LevelEndEvent levelEndEvent) {
        if (levelEndEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logLevelEnd");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(levelEndEvent);
        }
    }

    public void logAddToCart(AddToCartEvent addToCartEvent) {
        if (addToCartEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logAddToCart");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(addToCartEvent);
        }
    }

    public void logStartCheckout(StartCheckoutEvent startCheckoutEvent) {
        if (startCheckoutEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logStartCheckout");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(startCheckoutEvent);
        }
    }

    public void logRating(RatingEvent ratingEvent) {
        if (ratingEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logRating");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(ratingEvent);
        }
    }

    public void logContentView(ContentViewEvent contentViewEvent) {
        if (contentViewEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logContentView");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(contentViewEvent);
        }
    }

    public void logSearch(SearchEvent searchEvent) {
        if (searchEvent == null) {
            throw new NullPointerException("event must not be null");
        }
        if (this.firebaseEnabled) {
            logFirebaseModeEnabledWarning("logSearch");
            return;
        }
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onPredefined(searchEvent);
        }
    }

    public void onException(Crash.LoggedException loggedException) {
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onError(loggedException.getSessionId());
        }
    }

    public void onException(Crash.FatalException fatalException) {
        SessionAnalyticsManager sessionAnalyticsManager = this.analyticsManager;
        if (sessionAnalyticsManager != null) {
            sessionAnalyticsManager.onCrash(fatalException.getSessionId(), fatalException.getExceptionName());
        }
    }

    @Override // io.fabric.sdk.android.Kit
    @SuppressLint({"NewApi"})
    protected boolean onPreExecute() {
        long jLastModified;
        try {
            Context context = getContext();
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String string = Integer.toString(packageInfo.versionCode);
            String str = packageInfo.versionName == null ? IdManager.DEFAULT_VERSION_NAME : packageInfo.versionName;
            if (Build.VERSION.SDK_INT >= 9) {
                jLastModified = packageInfo.firstInstallTime;
            } else {
                jLastModified = new File(packageManager.getApplicationInfo(packageName, 0).sourceDir).lastModified();
            }
            this.analyticsManager = SessionAnalyticsManager.build(this, context, getIdManager(), string, str, jLastModified);
            this.analyticsManager.enable();
            this.firebaseEnabled = new FirebaseInfo().isFirebaseCrashlyticsEnabled(context);
            return true;
        } catch (Exception e) {
            Fabric.getLogger().e(TAG, "Error retrieving app properties", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.fabric.sdk.android.Kit
    public Boolean doInBackground() {
        if (!DataCollectionArbiter.getInstance(getContext()).isDataCollectionEnabled()) {
            Fabric.getLogger().d(Fabric.TAG, "Analytics collection disabled, because data collection is disabled by Firebase.");
            this.analyticsManager.disable();
            return false;
        }
        try {
            SettingsData settingsDataAwaitSettingsData = Settings.getInstance().awaitSettingsData();
            if (settingsDataAwaitSettingsData == null) {
                Fabric.getLogger().e(TAG, "Failed to retrieve settings");
                return false;
            }
            if (settingsDataAwaitSettingsData.featuresData.collectAnalytics) {
                Fabric.getLogger().d(TAG, "Analytics collection enabled");
                this.analyticsManager.setAnalyticsSettingsData(settingsDataAwaitSettingsData.analyticsSettingsData, getOverridenSpiEndpoint());
                return true;
            }
            Fabric.getLogger().d(TAG, "Analytics collection disabled");
            this.analyticsManager.disable();
            return false;
        } catch (Exception e) {
            Fabric.getLogger().e(TAG, "Error dealing with settings", e);
            return false;
        }
    }

    String getOverridenSpiEndpoint() {
        return CommonUtils.getStringsFileValue(getContext(), CRASHLYTICS_API_ENDPOINT);
    }

    private void logFirebaseModeEnabledWarning(String str) {
        Fabric.getLogger().w(TAG, "Method " + str + " is not supported when using Crashlytics through Firebase.");
    }
}
