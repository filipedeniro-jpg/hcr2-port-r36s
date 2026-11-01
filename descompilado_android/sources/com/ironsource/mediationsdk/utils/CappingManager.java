package com.ironsource.mediationsdk.utils;

import android.content.Context;
import android.text.TextUtils;
import com.ironsource.mediationsdk.model.BannerPlacement;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.PlacementAvailabilitySettings;
import com.ironsource.mediationsdk.model.PlacementCappingType;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class CappingManager {
    private static final String CAPPING_TIME_THRESHOLD = "CappingManager.CAPPING_TIME_THRESHOLD";
    private static final String CAPPING_TYPE = "CappingManager.CAPPING_TYPE";
    private static final String CURRENT_NUMBER_OF_SHOWS = "CappingManager.CURRENT_NUMBER_OF_SHOWS";
    private static final String IS_CAPPING_ENABLED = "CappingManager.IS_CAPPING_ENABLED";
    private static final String IS_DELIVERY_ENABLED = "CappingManager.IS_DELIVERY_ENABLED";
    private static final String IS_PACING_ENABLED = "CappingManager.IS_PACING_ENABLED";
    private static final String MAX_NUMBER_OF_SHOWS = "CappingManager.MAX_NUMBER_OF_SHOWS";
    private static final String SECONDS_BETWEEN_SHOWS = "CappingManager.SECONDS_BETWEEN_SHOWS";
    private static final String TIME_OF_THE_PREVIOUS_SHOW = "CappingManager.TIME_OF_THE_PREVIOUS_SHOW";

    public enum ECappingStatus {
        CAPPED_PER_DELIVERY,
        CAPPED_PER_COUNT,
        CAPPED_PER_PACE,
        NOT_CAPPED
    }

    public static synchronized void addCappingInfo(Context context, InterstitialPlacement interstitialPlacement) {
        if (context == null || interstitialPlacement == null) {
            return;
        }
        PlacementAvailabilitySettings placementAvailabilitySettings = interstitialPlacement.getPlacementAvailabilitySettings();
        if (placementAvailabilitySettings == null) {
            return;
        }
        addCappingInfo(context, "Interstitial", interstitialPlacement.getPlacementName(), placementAvailabilitySettings);
    }

    public static synchronized void addCappingInfo(Context context, Placement placement) {
        if (context == null || placement == null) {
            return;
        }
        PlacementAvailabilitySettings placementAvailabilitySettings = placement.getPlacementAvailabilitySettings();
        if (placementAvailabilitySettings == null) {
            return;
        }
        addCappingInfo(context, IronSourceConstants.REWARDED_VIDEO_AD_UNIT, placement.getPlacementName(), placementAvailabilitySettings);
    }

    public static synchronized void addCappingInfo(Context context, BannerPlacement bannerPlacement) {
        if (context == null || bannerPlacement == null) {
            return;
        }
        PlacementAvailabilitySettings placementAvailabilitySettings = bannerPlacement.getPlacementAvailabilitySettings();
        if (placementAvailabilitySettings == null) {
            return;
        }
        addCappingInfo(context, IronSourceConstants.BANNER_AD_UNIT, bannerPlacement.getPlacementName(), placementAvailabilitySettings);
    }

    public static synchronized ECappingStatus isPlacementCapped(Context context, InterstitialPlacement interstitialPlacement) {
        if (context != null && interstitialPlacement != null) {
            if (interstitialPlacement.getPlacementAvailabilitySettings() != null) {
                return isPlacementCapped(context, "Interstitial", interstitialPlacement.getPlacementName());
            }
        }
        return ECappingStatus.NOT_CAPPED;
    }

    public static synchronized boolean isInterstitialPlacementCapped(Context context, String str) {
        return isPlacementCapped(context, "Interstitial", str) != ECappingStatus.NOT_CAPPED;
    }

    public static synchronized boolean isBnPlacementCapped(Context context, String str) {
        return isPlacementCapped(context, IronSourceConstants.BANNER_AD_UNIT, str) != ECappingStatus.NOT_CAPPED;
    }

    public static synchronized ECappingStatus isPlacementCapped(Context context, Placement placement) {
        if (context != null && placement != null) {
            if (placement.getPlacementAvailabilitySettings() != null) {
                return isPlacementCapped(context, IronSourceConstants.REWARDED_VIDEO_AD_UNIT, placement.getPlacementName());
            }
        }
        return ECappingStatus.NOT_CAPPED;
    }

    public static synchronized boolean isRvPlacementCapped(Context context, String str) {
        return isPlacementCapped(context, IronSourceConstants.REWARDED_VIDEO_AD_UNIT, str) != ECappingStatus.NOT_CAPPED;
    }

    public static synchronized void incrementShowCounter(Context context, InterstitialPlacement interstitialPlacement) {
        if (interstitialPlacement != null) {
            incrementShowCounter(context, "Interstitial", interstitialPlacement.getPlacementName());
        }
    }

    public static synchronized void incrementIsShowCounter(Context context, String str) {
        incrementShowCounter(context, "Interstitial", str);
    }

    public static synchronized void incrementShowCounter(Context context, Placement placement) {
        if (placement != null) {
            incrementShowCounter(context, IronSourceConstants.REWARDED_VIDEO_AD_UNIT, placement.getPlacementName());
        }
    }

    public static synchronized void incrementRvShowCounter(Context context, String str) {
        incrementShowCounter(context, IronSourceConstants.REWARDED_VIDEO_AD_UNIT, str);
    }

    public static synchronized void incrementBnShowCounter(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            incrementShowCounter(context, IronSourceConstants.BANNER_AD_UNIT, str);
        }
    }

    private static String constructSharedPrefsKey(String str, String str2, String str3) {
        return str + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + str2 + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + str3;
    }

    private static ECappingStatus isPlacementCapped(Context context, String str, String str2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(str, IS_DELIVERY_ENABLED, str2), true)) {
            return ECappingStatus.CAPPED_PER_DELIVERY;
        }
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(str, IS_PACING_ENABLED, str2), false)) {
            if (jCurrentTimeMillis - IronSourceUtils.getLongFromSharedPrefs(context, constructSharedPrefsKey(str, TIME_OF_THE_PREVIOUS_SHOW, str2), 0L) < IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey(str, SECONDS_BETWEEN_SHOWS, str2), 0) * 1000) {
                return ECappingStatus.CAPPED_PER_PACE;
            }
        }
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(str, IS_CAPPING_ENABLED, str2), false)) {
            int intFromSharedPrefs = IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey(str, MAX_NUMBER_OF_SHOWS, str2), 0);
            String strConstructSharedPrefsKey = constructSharedPrefsKey(str, CURRENT_NUMBER_OF_SHOWS, str2);
            int intFromSharedPrefs2 = IronSourceUtils.getIntFromSharedPrefs(context, strConstructSharedPrefsKey, 0);
            String strConstructSharedPrefsKey2 = constructSharedPrefsKey(str, CAPPING_TIME_THRESHOLD, str2);
            if (jCurrentTimeMillis >= IronSourceUtils.getLongFromSharedPrefs(context, strConstructSharedPrefsKey2, 0L)) {
                IronSourceUtils.saveIntToSharedPrefs(context, strConstructSharedPrefsKey, 0);
                IronSourceUtils.saveLongToSharedPrefs(context, strConstructSharedPrefsKey2, 0L);
            } else if (intFromSharedPrefs2 >= intFromSharedPrefs) {
                return ECappingStatus.CAPPED_PER_COUNT;
            }
        }
        return ECappingStatus.NOT_CAPPED;
    }

    private static void incrementShowCounter(Context context, String str, String str2) {
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(str, IS_PACING_ENABLED, str2), false)) {
            IronSourceUtils.saveLongToSharedPrefs(context, constructSharedPrefsKey(str, TIME_OF_THE_PREVIOUS_SHOW, str2), System.currentTimeMillis());
        }
        if (IronSourceUtils.getBooleanFromSharedPrefs(context, constructSharedPrefsKey(str, IS_CAPPING_ENABLED, str2), false)) {
            IronSourceUtils.getIntFromSharedPrefs(context, constructSharedPrefsKey(str, MAX_NUMBER_OF_SHOWS, str2), 0);
            String strConstructSharedPrefsKey = constructSharedPrefsKey(str, CURRENT_NUMBER_OF_SHOWS, str2);
            int intFromSharedPrefs = IronSourceUtils.getIntFromSharedPrefs(context, strConstructSharedPrefsKey, 0);
            if (intFromSharedPrefs == 0) {
                String stringFromSharedPrefs = IronSourceUtils.getStringFromSharedPrefs(context, constructSharedPrefsKey(str, CAPPING_TYPE, str2), PlacementCappingType.PER_DAY.toString());
                PlacementCappingType placementCappingType = null;
                for (PlacementCappingType placementCappingType2 : PlacementCappingType.values()) {
                    if (placementCappingType2.value.equals(stringFromSharedPrefs)) {
                        placementCappingType = placementCappingType2;
                        break;
                    }
                }
                IronSourceUtils.saveLongToSharedPrefs(context, constructSharedPrefsKey(str, CAPPING_TIME_THRESHOLD, str2), initTimeThreshold(placementCappingType));
            }
            IronSourceUtils.saveIntToSharedPrefs(context, strConstructSharedPrefsKey, intFromSharedPrefs + 1);
        }
    }

    /* JADX INFO: renamed from: com.ironsource.mediationsdk.utils.CappingManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ironsource$mediationsdk$model$PlacementCappingType = new int[PlacementCappingType.values().length];

        static {
            try {
                $SwitchMap$com$ironsource$mediationsdk$model$PlacementCappingType[PlacementCappingType.PER_DAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ironsource$mediationsdk$model$PlacementCappingType[PlacementCappingType.PER_HOUR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static long initTimeThreshold(PlacementCappingType placementCappingType) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        int i = AnonymousClass1.$SwitchMap$com$ironsource$mediationsdk$model$PlacementCappingType[placementCappingType.ordinal()];
        if (i == 1) {
            calendar.set(14, 0);
            calendar.set(13, 0);
            calendar.set(12, 0);
            calendar.set(11, 0);
            calendar.add(6, 1);
        } else if (i == 2) {
            calendar.set(14, 0);
            calendar.set(13, 0);
            calendar.set(12, 0);
            calendar.add(11, 1);
        }
        return calendar.getTimeInMillis();
    }

    private static void addCappingInfo(Context context, String str, String str2, PlacementAvailabilitySettings placementAvailabilitySettings) {
        boolean zIsDeliveryEnabled = placementAvailabilitySettings.isDeliveryEnabled();
        IronSourceUtils.saveBooleanToSharedPrefs(context, constructSharedPrefsKey(str, IS_DELIVERY_ENABLED, str2), zIsDeliveryEnabled);
        if (zIsDeliveryEnabled) {
            boolean zIsCappingEnabled = placementAvailabilitySettings.isCappingEnabled();
            IronSourceUtils.saveBooleanToSharedPrefs(context, constructSharedPrefsKey(str, IS_CAPPING_ENABLED, str2), zIsCappingEnabled);
            if (zIsCappingEnabled) {
                IronSourceUtils.saveIntToSharedPrefs(context, constructSharedPrefsKey(str, MAX_NUMBER_OF_SHOWS, str2), placementAvailabilitySettings.getCappingValue());
                IronSourceUtils.saveStringToSharedPrefs(context, constructSharedPrefsKey(str, CAPPING_TYPE, str2), placementAvailabilitySettings.getCappingType().toString());
            }
            boolean zIsPacingEnabled = placementAvailabilitySettings.isPacingEnabled();
            IronSourceUtils.saveBooleanToSharedPrefs(context, constructSharedPrefsKey(str, IS_PACING_ENABLED, str2), zIsPacingEnabled);
            if (zIsPacingEnabled) {
                IronSourceUtils.saveIntToSharedPrefs(context, constructSharedPrefsKey(str, SECONDS_BETWEEN_SHOWS, str2), placementAvailabilitySettings.getPacingValue());
            }
        }
    }
}
