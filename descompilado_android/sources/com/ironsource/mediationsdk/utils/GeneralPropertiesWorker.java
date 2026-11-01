package com.ironsource.mediationsdk.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.ironsource.environment.ApplicationContext;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.mediationsdk.IronSourceObject;
import com.ironsource.mediationsdk.config.ConfigFile;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.sdk.constants.Constants;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class GeneralPropertiesWorker implements Runnable {
    private static final int MAX_MINUTES_OFFSET = 840;
    private static final int MINUTES_OFFSET_STEP = 15;
    private static final int MIN_MINUTES_OFFSET = -720;
    public static final String SDK_VERSION = "sdkVersion";
    private Context mContext;
    private final String TAG = getClass().getSimpleName();
    private final String BUNDLE_ID = Constants.RequestParameters.PACKAGE_NAME;
    private final String ADVERTISING_ID = "advertisingId";
    private final String ADVERTISING_ID_IS_LIMIT_TRACKING = Constants.RequestParameters.isLAT;
    private final String APPLICATION_KEY = ServerResponseWrapper.APP_KEY_FIELD;
    private final String DEVICE_OS = "deviceOS";
    private final String ANDROID_OS_VERSION = "osVersion";
    private final String CONNECTION_TYPE = Constants.RequestParameters.CONNECTION_TYPE;
    private final String LANGUAGE = "language";
    private final String DEVICE_OEM = Constants.RequestParameters.DEVICE_OEM;
    private final String DEVICE_MODEL = Constants.RequestParameters.DEVICE_MODEL;
    private final String MOBILE_CARRIER = Constants.RequestParameters.MOBILE_CARRIER;
    private final String EXTERNAL_FREE_MEMORY = "externalFreeMemory";
    private final String INTERNAL_FREE_MEMORY = "internalFreeMemory";
    private final String BATTERY_LEVEL = "battery";
    private final String LOCATION_LAT = "lat";
    private final String LOCATION_LON = "lon";
    private final String GMT_MINUTES_OFFSET = "gmtMinutesOffset";
    private final String PUBLISHER_APP_VERSION = Constants.RequestParameters.APPLICATION_VERSION_NAME;
    private final String KEY_SESSION_ID = "sessionId";
    private final String KEY_PLUGIN_TYPE = "pluginType";
    private final String KEY_PLUGIN_VERSION = "pluginVersion";
    private final String KEY_PLUGIN_FW_VERSION = "plugin_fw_v";
    private final String KEY_IS_ROOT = "jb";
    private final String ADVERTISING_ID_TYPE = "advertisingIdType";
    private final String MEDIATION_TYPE = "mt";

    private String getDeviceOS() {
        return Constants.JAVASCRIPT_INTERFACE_NAME;
    }

    private GeneralPropertiesWorker() {
    }

    public GeneralPropertiesWorker(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            GeneralProperties.getProperties().putKeys(collectInformation());
            IronSourceUtils.saveGeneralProperties(this.mContext, GeneralProperties.getProperties().toJSON());
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "Thread name = " + getClass().getSimpleName(), e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x006a  */
    /* JADX WARN: Code duplicated, block: B:24:0x006d  */
    /* JADX WARN: Code duplicated, block: B:26:0x0079  */
    /* JADX WARN: Code duplicated, block: B:29:0x0081  */
    /* JADX WARN: Code duplicated, block: B:32:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:35:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:38:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:41:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:44:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:47:0x0101  */
    /* JADX WARN: Code duplicated, block: B:57:0x0160  */
    /* JADX WARN: Code duplicated, block: B:60:0x0173  */
    /* JADX WARN: Code duplicated, block: B:63:0x0182  */
    /* JADX WARN: Code duplicated, block: B:66:0x0191  */
    /* JADX WARN: Code duplicated, block: B:69:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:72:0x01b3  */
    private Map<String, Object> collectInformation() {
        String orGenerateOnceUniqueIdentifier;
        boolean zBooleanValue;
        String connectionType;
        String language;
        String deviceOEM;
        String deviceModel;
        String mobileCarrier;
        int gmtMinutesOffset;
        String pluginType;
        String pluginVersion;
        String pluginFrameworkVersion;
        String strValueOf;
        String mediationType;
        double[] lastKnownLocation;
        String str = "";
        HashMap map = new HashMap();
        map.put("sessionId", IronSourceObject.getInstance().getSessionId());
        String bundleId = getBundleId();
        if (!TextUtils.isEmpty(bundleId)) {
            map.put(Constants.RequestParameters.PACKAGE_NAME, bundleId);
            String publisherApplicationVersion = ApplicationContext.getPublisherApplicationVersion(this.mContext, bundleId);
            if (!TextUtils.isEmpty(publisherApplicationVersion)) {
                map.put(Constants.RequestParameters.APPLICATION_VERSION_NAME, publisherApplicationVersion);
            }
        }
        map.put(ServerResponseWrapper.APP_KEY_FIELD, getApplicationKey());
        try {
            String[] advertisingIdInfo = DeviceStatus.getAdvertisingIdInfo(this.mContext);
            if (advertisingIdInfo != null && advertisingIdInfo.length == 2) {
                orGenerateOnceUniqueIdentifier = !TextUtils.isEmpty(advertisingIdInfo[0]) ? advertisingIdInfo[0] : "";
                try {
                    zBooleanValue = Boolean.valueOf(advertisingIdInfo[1]).booleanValue();
                } catch (Exception unused) {
                    zBooleanValue = false;
                }
                if (TextUtils.isEmpty(orGenerateOnceUniqueIdentifier)) {
                    orGenerateOnceUniqueIdentifier = DeviceStatus.getOrGenerateOnceUniqueIdentifier(this.mContext);
                    if (!TextUtils.isEmpty(orGenerateOnceUniqueIdentifier)) {
                        str = IronSourceConstants.TYPE_UUID;
                    }
                } else {
                    str = IronSourceConstants.TYPE_GAID;
                }
                if (!TextUtils.isEmpty(orGenerateOnceUniqueIdentifier)) {
                    map.put("advertisingId", orGenerateOnceUniqueIdentifier);
                    map.put("advertisingIdType", str);
                    map.put(Constants.RequestParameters.isLAT, Boolean.valueOf(zBooleanValue));
                }
                map.put("deviceOS", getDeviceOS());
                if (!TextUtils.isEmpty(getAndroidVersion())) {
                    map.put("osVersion", getAndroidVersion());
                }
                connectionType = IronSourceUtils.getConnectionType(this.mContext);
                if (!TextUtils.isEmpty(connectionType)) {
                    map.put(Constants.RequestParameters.CONNECTION_TYPE, connectionType);
                }
                map.put(SDK_VERSION, getSDKVersion());
                language = getLanguage();
                if (!TextUtils.isEmpty(language)) {
                    map.put("language", language);
                }
                deviceOEM = getDeviceOEM();
                if (!TextUtils.isEmpty(deviceOEM)) {
                    map.put(Constants.RequestParameters.DEVICE_OEM, deviceOEM);
                }
                deviceModel = getDeviceModel();
                if (!TextUtils.isEmpty(deviceModel)) {
                    map.put(Constants.RequestParameters.DEVICE_MODEL, deviceModel);
                }
                mobileCarrier = getMobileCarrier();
                if (!TextUtils.isEmpty(mobileCarrier)) {
                    map.put(Constants.RequestParameters.MOBILE_CARRIER, mobileCarrier);
                }
                map.put("internalFreeMemory", Long.valueOf(getInternalStorageFreeSize()));
                map.put("externalFreeMemory", Long.valueOf(getExternalStorageFreeSize()));
                map.put("battery", Integer.valueOf(getBatteryLevel()));
                if (IronSourceUtils.getBooleanFromSharedPrefs(this.mContext, GeneralProperties.ALLOW_LOCATION_SHARED_PREFS_KEY, false) && (lastKnownLocation = getLastKnownLocation()) != null && lastKnownLocation.length == 2) {
                    map.put("lat", Double.valueOf(lastKnownLocation[0]));
                    map.put("lon", Double.valueOf(lastKnownLocation[1]));
                }
                gmtMinutesOffset = getGmtMinutesOffset();
                if (validateGmtMinutesOffset(gmtMinutesOffset)) {
                    map.put("gmtMinutesOffset", Integer.valueOf(gmtMinutesOffset));
                }
                pluginType = getPluginType();
                if (!TextUtils.isEmpty(pluginType)) {
                    map.put("pluginType", pluginType);
                }
                pluginVersion = getPluginVersion();
                if (!TextUtils.isEmpty(pluginVersion)) {
                    map.put("pluginVersion", pluginVersion);
                }
                pluginFrameworkVersion = getPluginFrameworkVersion();
                if (!TextUtils.isEmpty(pluginFrameworkVersion)) {
                    map.put("plugin_fw_v", pluginFrameworkVersion);
                }
                strValueOf = String.valueOf(DeviceStatus.isRootedDevice());
                if (!TextUtils.isEmpty(strValueOf)) {
                    map.put("jb", strValueOf);
                }
                mediationType = getMediationType();
                if (!TextUtils.isEmpty(mediationType)) {
                    map.put("mt", mediationType);
                }
                return map;
            }
            orGenerateOnceUniqueIdentifier = "";
        } catch (Exception unused2) {
        }
        zBooleanValue = false;
        if (TextUtils.isEmpty(orGenerateOnceUniqueIdentifier)) {
            str = IronSourceConstants.TYPE_GAID;
        } else {
            orGenerateOnceUniqueIdentifier = DeviceStatus.getOrGenerateOnceUniqueIdentifier(this.mContext);
            if (!TextUtils.isEmpty(orGenerateOnceUniqueIdentifier)) {
                str = IronSourceConstants.TYPE_UUID;
            }
        }
        if (!TextUtils.isEmpty(orGenerateOnceUniqueIdentifier)) {
            map.put("advertisingId", orGenerateOnceUniqueIdentifier);
            map.put("advertisingIdType", str);
            map.put(Constants.RequestParameters.isLAT, Boolean.valueOf(zBooleanValue));
        }
        map.put("deviceOS", getDeviceOS());
        if (!TextUtils.isEmpty(getAndroidVersion())) {
            map.put("osVersion", getAndroidVersion());
        }
        connectionType = IronSourceUtils.getConnectionType(this.mContext);
        if (!TextUtils.isEmpty(connectionType)) {
            map.put(Constants.RequestParameters.CONNECTION_TYPE, connectionType);
        }
        map.put(SDK_VERSION, getSDKVersion());
        language = getLanguage();
        if (!TextUtils.isEmpty(language)) {
            map.put("language", language);
        }
        deviceOEM = getDeviceOEM();
        if (!TextUtils.isEmpty(deviceOEM)) {
            map.put(Constants.RequestParameters.DEVICE_OEM, deviceOEM);
        }
        deviceModel = getDeviceModel();
        if (!TextUtils.isEmpty(deviceModel)) {
            map.put(Constants.RequestParameters.DEVICE_MODEL, deviceModel);
        }
        mobileCarrier = getMobileCarrier();
        if (!TextUtils.isEmpty(mobileCarrier)) {
            map.put(Constants.RequestParameters.MOBILE_CARRIER, mobileCarrier);
        }
        map.put("internalFreeMemory", Long.valueOf(getInternalStorageFreeSize()));
        map.put("externalFreeMemory", Long.valueOf(getExternalStorageFreeSize()));
        map.put("battery", Integer.valueOf(getBatteryLevel()));
        if (IronSourceUtils.getBooleanFromSharedPrefs(this.mContext, GeneralProperties.ALLOW_LOCATION_SHARED_PREFS_KEY, false)) {
            map.put("lat", Double.valueOf(lastKnownLocation[0]));
            map.put("lon", Double.valueOf(lastKnownLocation[1]));
        }
        gmtMinutesOffset = getGmtMinutesOffset();
        if (validateGmtMinutesOffset(gmtMinutesOffset)) {
            map.put("gmtMinutesOffset", Integer.valueOf(gmtMinutesOffset));
        }
        pluginType = getPluginType();
        if (!TextUtils.isEmpty(pluginType)) {
            map.put("pluginType", pluginType);
        }
        pluginVersion = getPluginVersion();
        if (!TextUtils.isEmpty(pluginVersion)) {
            map.put("pluginVersion", pluginVersion);
        }
        pluginFrameworkVersion = getPluginFrameworkVersion();
        if (!TextUtils.isEmpty(pluginFrameworkVersion)) {
            map.put("plugin_fw_v", pluginFrameworkVersion);
        }
        strValueOf = String.valueOf(DeviceStatus.isRootedDevice());
        if (!TextUtils.isEmpty(strValueOf)) {
            map.put("jb", strValueOf);
        }
        mediationType = getMediationType();
        if (!TextUtils.isEmpty(mediationType)) {
            map.put("mt", mediationType);
        }
        return map;
    }

    private String getPluginType() {
        try {
            return ConfigFile.getConfigFile().getPluginType();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getPluginType()", e);
            return "";
        }
    }

    private String getPluginVersion() {
        try {
            return ConfigFile.getConfigFile().getPluginVersion();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getPluginVersion()", e);
            return "";
        }
    }

    private String getPluginFrameworkVersion() {
        try {
            return ConfigFile.getConfigFile().getPluginFrameworkVersion();
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, "getPluginFrameworkVersion()", e);
            return "";
        }
    }

    private String getBundleId() {
        try {
            return this.mContext.getPackageName();
        } catch (Exception unused) {
            return "";
        }
    }

    private String getApplicationKey() {
        return IronSourceObject.getInstance().getIronSourceAppKey();
    }

    private String getAndroidVersion() {
        try {
            String str = Build.VERSION.RELEASE;
            return "" + Build.VERSION.SDK_INT + "(" + str + ")";
        } catch (Exception unused) {
            return "";
        }
    }

    private String getSDKVersion() {
        return IronSourceUtils.getSDKVersion();
    }

    private String getLanguage() {
        try {
            return Locale.getDefault().getLanguage();
        } catch (Exception unused) {
            return "";
        }
    }

    private String getDeviceOEM() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception unused) {
            return "";
        }
    }

    private String getDeviceModel() {
        try {
            return Build.MODEL;
        } catch (Exception unused) {
            return "";
        }
    }

    private String getMobileCarrier() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
            if (telephonyManager == null) {
                return "";
            }
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            return !networkOperatorName.equals("") ? networkOperatorName : "";
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":getMobileCarrier()", e);
            return "";
        }
    }

    private boolean isExternalStorageAbvailable() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Exception unused) {
            return false;
        }
    }

    private long getInternalStorageFreeSize() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        } catch (Exception unused) {
            return -1L;
        }
    }

    private long getExternalStorageFreeSize() {
        if (!isExternalStorageAbvailable()) {
            return -1L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    private int getBatteryLevel() {
        try {
            Intent intentRegisterReceiver = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("level", -1) : 0;
            int intExtra2 = intentRegisterReceiver != null ? intentRegisterReceiver.getIntExtra("scale", -1) : 0;
            if (intExtra == -1 || intExtra2 == -1) {
                return -1;
            }
            return (int) ((intExtra / intExtra2) * 100.0f);
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":getBatteryLevel()", e);
            return -1;
        }
    }

    @SuppressLint({"MissingPermission"})
    private double[] getLastKnownLocation() {
        double[] dArr = new double[0];
        try {
            if (!locationPermissionGranted()) {
                return dArr;
            }
            LocationManager locationManager = (LocationManager) this.mContext.getApplicationContext().getSystemService("location");
            Location location = null;
            Iterator<String> it = locationManager.getAllProviders().iterator();
            long time = Long.MIN_VALUE;
            while (it.hasNext()) {
                Location lastKnownLocation = locationManager.getLastKnownLocation(it.next());
                if (lastKnownLocation != null && lastKnownLocation.getTime() > time) {
                    time = lastKnownLocation.getTime();
                    location = lastKnownLocation;
                }
            }
            return location != null ? new double[]{location.getLatitude(), location.getLongitude()} : dArr;
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":getLastLocation()", e);
            return new double[0];
        }
    }

    private boolean locationPermissionGranted() {
        try {
            return this.mContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
        } catch (Exception unused) {
            return false;
        }
    }

    private int getGmtMinutesOffset() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            return Math.round(((timeZone.getOffset(GregorianCalendar.getInstance(timeZone).getTimeInMillis()) / 1000) / 60) / 15) * 15;
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceLogger.IronSourceTag.NATIVE, this.TAG + ":getGmtMinutesOffset()", e);
            return 0;
        }
    }

    private boolean validateGmtMinutesOffset(int i) {
        return i <= MAX_MINUTES_OFFSET && i >= MIN_MINUTES_OFFSET && i % 15 == 0;
    }

    private String getMediationType() {
        return IronSourceObject.getInstance().getMediationType();
    }
}
