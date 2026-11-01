package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.api.AdSettingsApi;
import com.facebook.ads.internal.settings.AdInternalSettings;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Fg, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0517Fg implements AdSettingsApi {
    private static final String B = AdInternalSettings.class.getSimpleName();
    private static final Collection<String> C = new HashSet();
    private static volatile boolean D;

    static {
        C.add("sdk");
        C.add(CommonUtils.GOOGLE_SDK);
        C.add("vbox86p");
        C.add("vbox86tp");
        D = false;
    }

    private static void B(String deviceIdHash) {
        if (D) {
            return;
        }
        D = true;
        Log.d(B, "Test mode device hash: " + deviceIdHash);
        Log.d(B, "When testing your app with Facebook's ad units you must specify the device hashed ID to ensure the delivery of test ads, add the following code before loading an ad: AdSettings.addTestDevice(\"" + deviceIdHash + "\");");
    }

    @Override // com.facebook.ads.internal.api.AdSettingsApi
    public final boolean isTestMode(Context context) {
        if (AdInternalSettings.isDebugBuild() || AdInternalSettings.isExplicitTestMode() || C.contains(Build.PRODUCT)) {
            return true;
        }
        String string = AdInternalSettings.sSettingsBundle.getString("STR_DEVICE_ID_HASH_KEY", null);
        if (string == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(I6.C("FBAdPrefs", context), 0);
            string = sharedPreferences.getString("deviceIdHash", null);
            if (TextUtils.isEmpty(string)) {
                string = UUID.randomUUID().toString();
                sharedPreferences.edit().putString("deviceIdHash", string).apply();
            }
            AdInternalSettings.sSettingsBundle.putString("STR_DEVICE_ID_HASH_KEY", string);
        }
        if (AdInternalSettings.getTestDevicesList().contains(string)) {
            return true;
        }
        B(string);
        return false;
    }

    @Override // com.facebook.ads.internal.api.AdSettingsApi
    public final void turnOnDebugger() {
        C0642Kd.C();
    }
}
