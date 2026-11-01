package com.amazon.device.ads;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.appevents.UserDataStore;
import com.ironsource.sdk.constants.Constants;
import com.my.target.i;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class DeviceInfo {
    private static final String LOGTAG = "DeviceInfo";
    public static final String ORIENTATION_LANDSCAPE = "landscape";
    public static final String ORIENTATION_PORTRAIT = "portrait";
    public static final String ORIENTATION_UNKNOWN = "unknown";
    private static final String dt = "android";
    private static final String os = "Android";
    private boolean bad_mac;
    private boolean bad_serial;
    private boolean bad_udid;
    private final AndroidBuildInfo buildInfo;
    private String carrier;
    private String country;
    private final MobileAdsInfoStore infoStore;
    private Size landscapeScreenSize;
    private String language;
    private final MobileAdsLogger logger;
    private boolean macFetched;
    private String make;
    private String model;
    private String osVersion;
    private Size portraitScreenSize;
    private float scalingFactor;
    private String scalingFactorAsString;
    private boolean serialFetched;
    private String sha1_mac;
    private String sha1_serial;
    private String sha1_udid;
    private boolean udidFetched;
    private UserAgentManager userAgentManager;

    public String getDeviceType() {
        return "android";
    }

    public String getOS() {
        return "Android";
    }

    public DeviceInfo(Context context, UserAgentManager userAgentManager) {
        this(context, userAgentManager, MobileAdsInfoStore.getInstance(), new MobileAdsLoggerFactory(), new AndroidBuildInfo());
    }

    DeviceInfo(Context context, UserAgentManager userAgentManager, MobileAdsInfoStore mobileAdsInfoStore, MobileAdsLoggerFactory mobileAdsLoggerFactory, AndroidBuildInfo androidBuildInfo) {
        this.make = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.osVersion = Build.VERSION.RELEASE;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.infoStore = mobileAdsInfoStore;
        this.buildInfo = androidBuildInfo;
        setCountry();
        setCarrier(context);
        setLanguage();
        setScalingFactor(context);
        this.userAgentManager = userAgentManager;
    }

    public void setUserAgentManager(UserAgentManager userAgentManager) {
        this.userAgentManager = userAgentManager;
    }

    private void setMacAddressIfNotFetched() {
        if (this.macFetched) {
            return;
        }
        setMacAddress();
    }

    protected void setMacAddress() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) this.infoStore.getApplicationContext().getSystemService("wifi");
        if (wifiManager != null) {
            try {
                connectionInfo = wifiManager.getConnectionInfo();
            } catch (ExceptionInInitializerError e) {
                this.logger.d("Unable to get Wifi connection information: %s", e);
                connectionInfo = null;
            } catch (SecurityException e2) {
                this.logger.d("Unable to get Wifi connection information: %s", e2);
                connectionInfo = null;
            }
        } else {
            connectionInfo = null;
        }
        if (connectionInfo == null) {
            this.sha1_mac = null;
        } else {
            String macAddress = connectionInfo.getMacAddress();
            if (macAddress == null || macAddress.length() == 0 || !Pattern.compile("((([0-9a-fA-F]){1,2}[-:]){5}([0-9a-fA-F]){1,2})").matcher(macAddress).find()) {
                this.sha1_mac = null;
                this.bad_mac = true;
            } else {
                this.sha1_mac = WebUtils.getURLEncodedString(StringUtils.sha1(macAddress));
            }
        }
        this.macFetched = true;
    }

    private void setSerialIfNotFetched() {
        if (this.serialFetched) {
            return;
        }
        setSerial();
    }

    private void setSerial() {
        String str;
        try {
            str = (String) Build.class.getDeclaredField("SERIAL").get(Build.class);
        } catch (Exception unused) {
            str = null;
        }
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("unknown")) {
            this.bad_serial = true;
        } else {
            this.sha1_serial = WebUtils.getURLEncodedString(StringUtils.sha1(str));
        }
        this.serialFetched = true;
    }

    private void setUdidIfNotFetched() {
        if (this.udidFetched) {
            return;
        }
        setUdid();
    }

    private void setUdid() {
        String string = android.provider.Settings.Secure.getString(this.infoStore.getApplicationContext().getContentResolver(), i.ANDROID_ID);
        if (StringUtils.isNullOrEmpty(string) || string.equalsIgnoreCase("9774d56d682e549c")) {
            this.sha1_udid = null;
            this.bad_udid = true;
        } else {
            this.sha1_udid = WebUtils.getURLEncodedString(StringUtils.sha1(string));
        }
        this.udidFetched = true;
    }

    private void setLanguage() {
        String language = Locale.getDefault().getLanguage();
        if (language == null || language.length() <= 0) {
            language = null;
        }
        this.language = language;
    }

    private void setCountry() {
        String country = Locale.getDefault().getCountry();
        if (country == null || country.length() <= 0) {
            country = null;
        }
        this.country = country;
    }

    private void setCarrier(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName == null || networkOperatorName.length() <= 0) {
                networkOperatorName = null;
            }
            this.carrier = networkOperatorName;
        }
    }

    private void setScalingFactor(Context context) {
        if (this.make.equals("motorola") && this.model.equals("MB502")) {
            this.scalingFactor = 1.0f;
        } else {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            this.scalingFactor = displayMetrics.scaledDensity;
        }
        this.scalingFactorAsString = Float.toString(this.scalingFactor);
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getOSVersion() {
        return this.osVersion;
    }

    public String getMacSha1() {
        setMacAddressIfNotFetched();
        return this.sha1_mac;
    }

    public boolean isMacBad() {
        setMacAddressIfNotFetched();
        return this.bad_mac;
    }

    public String getSerialSha1() {
        setSerialIfNotFetched();
        return this.sha1_serial;
    }

    public boolean isSerialBad() {
        setSerialIfNotFetched();
        return this.bad_serial;
    }

    public String getUdidSha1() {
        setUdidIfNotFetched();
        return this.sha1_udid;
    }

    public boolean isUdidBad() {
        setUdidIfNotFetched();
        return this.bad_udid;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public String getCountry() {
        return this.country;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getScalingFactorAsString() {
        return this.scalingFactorAsString;
    }

    public float getScalingFactorAsFloat() {
        return this.scalingFactor;
    }

    public String getUserAgentString() {
        return this.userAgentManager.getUserAgentString();
    }

    public void setUserAgentString(String str) {
        this.userAgentManager.setUserAgentString(str);
    }

    public void populateUserAgentString(Context context) {
        this.userAgentManager.populateUserAgentString(context);
    }

    public String getOrientation() {
        int iDetermineCanonicalScreenOrientation = DisplayUtils.determineCanonicalScreenOrientation(this.infoStore.getApplicationContext(), this.buildInfo);
        if (iDetermineCanonicalScreenOrientation != 0) {
            if (iDetermineCanonicalScreenOrientation != 1) {
                if (iDetermineCanonicalScreenOrientation != 8) {
                    if (iDetermineCanonicalScreenOrientation != 9) {
                        return "unknown";
                    }
                }
            }
            return "portrait";
        }
        return "landscape";
    }

    public Size getScreenSize(String str) {
        Size size;
        Size size2;
        if (str.equals("portrait") && (size2 = this.portraitScreenSize) != null) {
            return size2;
        }
        if (str.equals("landscape") && (size = this.landscapeScreenSize) != null) {
            return size;
        }
        WindowManager windowManager = (WindowManager) this.infoStore.getApplicationContext().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        String str2 = String.valueOf(displayMetrics.widthPixels) + "x" + String.valueOf(displayMetrics.heightPixels);
        if (str.equals("portrait")) {
            this.portraitScreenSize = new Size(str2);
            return this.portraitScreenSize;
        }
        if (str.equals("landscape")) {
            this.landscapeScreenSize = new Size(str2);
            return this.landscapeScreenSize;
        }
        return new Size(str2);
    }

    public JSONObject getDInfoProperty() {
        JSONObject jSONObject = new JSONObject();
        JSONUtils.put(jSONObject, "make", getMake());
        JSONUtils.put(jSONObject, "model", getModel());
        JSONUtils.put(jSONObject, "os", getOS());
        JSONUtils.put(jSONObject, "osVersion", getOSVersion());
        JSONUtils.put(jSONObject, "scalingFactor", getScalingFactorAsString());
        JSONUtils.put(jSONObject, "language", getLanguage());
        JSONUtils.put(jSONObject, UserDataStore.COUNTRY, getCountry());
        JSONUtils.put(jSONObject, "carrier", getCarrier());
        return jSONObject;
    }

    public String toJsonString() {
        return toJsonObject(getOrientation()).toString();
    }

    JSONObject toJsonObject(String str) {
        JSONObject dInfoProperty = getDInfoProperty();
        JSONUtils.put(dInfoProperty, "orientation", str);
        JSONUtils.put(dInfoProperty, "screenSize", getScreenSize(str).toString());
        JSONUtils.put(dInfoProperty, Constants.RequestParameters.CONNECTION_TYPE, new ConnectionInfo(this.infoStore).getConnectionType());
        return dInfoProperty;
    }
}
