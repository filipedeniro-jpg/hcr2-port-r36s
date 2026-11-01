package com.applovin.mediation.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.applovin.impl.sdk.h;
import com.applovin.impl.sdk.utils.BundleUtils;
import com.applovin.impl.sdk.utils.n;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.adapter.MaxAdapter;
import com.applovin.mediation.adapter.parameters.MaxAdapterResponseParameters;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinSdk;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediationAdapterBase implements MaxAdapter {
    protected static final String KEY_MUTED = "muted";
    private final h mSdk;
    private final String mTag = getClass().getSimpleName();
    private final AppLovinSdk mWrappingSdk;

    public MediationAdapterBase(AppLovinSdk appLovinSdk) {
        this.mWrappingSdk = appLovinSdk;
        this.mSdk = n.a(appLovinSdk);
    }

    protected static String mediationTag() {
        return "AppLovinSdk_9.4.2";
    }

    protected void checkActivities(Context context, Class<?>... clsArr) {
        if (clsArr == null || clsArr.length <= 0) {
            return;
        }
        for (Class<?> cls : clsArr) {
            try {
                if (context.getPackageManager().getActivityInfo(new ComponentName(context, cls), 128) == null) {
                    throw new PackageManager.NameNotFoundException("null_activity_info");
                }
                log("Found defined activity: " + cls.getName());
            } catch (Throwable th) {
                log("No activity found for: " + cls);
                throw new IllegalStateException("Activity " + cls.getName() + " not defined", th);
            }
        }
    }

    protected void checkExistence(Class<?>... clsArr) {
        if (clsArr == null || clsArr.length <= 0) {
            return;
        }
        for (Class<?> cls : clsArr) {
            log("Found: " + cls.getName());
        }
    }

    protected MaxReward getReward(MaxAdapterResponseParameters maxAdapterResponseParameters) {
        Bundle serverParameters = maxAdapterResponseParameters.getServerParameters();
        int i = BundleUtils.getInt(AppLovinEventParameters.REVENUE_AMOUNT, 0, serverParameters);
        String string = BundleUtils.getString("currency", "", serverParameters);
        log("Creating reward: " + i + " " + string);
        return MaxReward.create(i, string);
    }

    protected h getSdk() {
        return this.mSdk;
    }

    protected AppLovinSdk getWrappingSdk() {
        return this.mWrappingSdk;
    }

    protected void log(String str) {
        Log.i(this.mTag, str);
    }

    protected void log(String str, Throwable th) {
        Log.i(this.mTag, str, th);
    }
}
