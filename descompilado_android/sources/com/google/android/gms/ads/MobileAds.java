package com.google.android.gms.ads;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.ads.zzwv;
import com.google.android.gms.internal.ads.zzxe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class MobileAds {
    private MobileAds() {
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void initialize(Context context, String str) {
        initialize(context, str, null);
    }

    @RequiresPermission("android.permission.INTERNET")
    @Deprecated
    public static void initialize(Context context, String str, Settings settings) {
        zzwv.zzpd().zza(context, str, settings == null ? null : settings.zzdc(), null);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public static final class Settings {
        private final zzxe zzabh = new zzxe();

        @Deprecated
        public final String getTrackingId() {
            return null;
        }

        @Deprecated
        public final boolean isGoogleAnalyticsEnabled() {
            return false;
        }

        @Deprecated
        public final Settings setGoogleAnalyticsEnabled(boolean z) {
            return this;
        }

        @Deprecated
        public final Settings setTrackingId(String str) {
            return this;
        }

        final zzxe zzdc() {
            return this.zzabh;
        }
    }

    public static void initialize(Context context) {
        initialize(context, null, null);
    }

    public static void initialize(Context context, OnInitializationCompleteListener onInitializationCompleteListener) {
        zzwv.zzpd().zza(context, null, null, onInitializationCompleteListener);
    }

    public static RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        return zzwv.zzpd().getRewardedVideoAdInstance(context);
    }

    public static void setAppVolume(float f) {
        zzwv.zzpd().setAppVolume(f);
    }

    public static void setAppMuted(boolean z) {
        zzwv.zzpd().setAppMuted(z);
    }

    public static void openDebugMenu(Context context, String str) {
        zzwv.zzpd().openDebugMenu(context, str);
    }

    public static String getVersionString() {
        return zzwv.zzpd().getVersionString();
    }

    @KeepForSdk
    public static void registerRtbAdapter(Class<? extends RtbAdapter> cls) {
        zzwv.zzpd().registerRtbAdapter(cls);
    }

    public static InitializationStatus getInitializationStatus() {
        return zzwv.zzpd().getInitializationStatus();
    }

    @NonNull
    public static RequestConfiguration getRequestConfiguration() {
        return zzwv.zzpd().getRequestConfiguration();
    }

    public static void setRequestConfiguration(@NonNull RequestConfiguration requestConfiguration) {
        zzwv.zzpd().setRequestConfiguration(requestConfiguration);
    }
}
