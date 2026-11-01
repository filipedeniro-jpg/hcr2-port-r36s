package com.vungle.warren.network;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.core.content.PermissionChecker;
import com.appsflyer.share.Constants;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ironsource.sdk.constants.LocationConst;
import com.moat.analytics.mobile.vng.MoatAnalytics;
import com.moat.analytics.mobile.vng.MoatOptions;
import com.my.target.i;
import com.vungle.warren.Storage;
import com.vungle.warren.error.VungleError;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.JsonUtil;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX INFO: loaded from: classes2.dex */
public class VungleApiClient {
    private static String BASE_URL = null;
    private static String HEADER_UA = null;
    public static final String MANUFACTURER_AMAZON = "Amazon";
    private static final String TAG = "VungleApiClient";
    private static VungleApiClient _instance;
    private static VungleApi api;
    private static OkHttpClient client;
    private JsonObject appBody;
    private String cacheDir;
    private WeakReference<Context> context;
    private JsonObject deviceBody;
    private boolean enableMoat;
    private boolean limitAdTracking;
    private JsonObject location;
    private String newEndpoint;
    private String reportAdEndpoint;
    private String requestAdEndpoint;
    private Map<String, Long> retryAfterDataMap = new ConcurrentHashMap();
    private String riEndpoint;
    private boolean shouldTransmitIMEI;
    private Storage storage;
    private VungleApi timeoutApi;
    private String uaString;
    private JsonObject userBody;
    private String userImei;
    private boolean willPlayAdEnabled;
    private String willPlayAdEndpoint;
    private int willPlayAdTimeout;

    public enum WrapperFramework {
        admob,
        air,
        cocos2dx,
        corona,
        dfp,
        heyzap,
        marmalade,
        mopub,
        unity,
        fyber,
        ironsource,
        upsight,
        appodeal,
        aerserv,
        adtoapp,
        tapdaq,
        none
    }

    private String getConnectionTypeDetail(int i) {
        switch (i) {
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "EVDO_0";
            case 6:
                return "EVDO_A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "IDEN";
            case 12:
                return "EVDO_B";
            case 13:
                return "LTE";
            case 14:
                return "EHPRD";
            case 15:
                return "HSPAP";
            case 16:
                return "GSM";
            case 17:
                return "TD_SCDMA";
            case 18:
                return "IWLAN";
            default:
                return "UNKNOWN";
        }
    }

    static {
        HEADER_UA = MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "VungleAmazon/6.3.17" : "VungleDroid/6.3.17";
        BASE_URL = "https://ads.api.vungle.com/";
    }

    private VungleApiClient() {
        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() { // from class: com.vungle.warren.network.VungleApiClient.1
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                int iCode;
                Request request = chain.request();
                String strEncodedPath = request.url().encodedPath();
                Long l = (Long) VungleApiClient.this.retryAfterDataMap.get(strEncodedPath);
                if (l != null) {
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(l.longValue() - System.currentTimeMillis());
                    if (seconds <= 0) {
                        VungleApiClient.this.retryAfterDataMap.remove(strEncodedPath);
                    } else {
                        return new Response.Builder().request(request).addHeader("Retry-After", "" + seconds).code(500).protocol(Protocol.HTTP_1_1).message("Server is busy").body(ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"Error\":\"Retry-After\"}")).build();
                    }
                }
                Response responseProceed = chain.proceed(request);
                if (responseProceed != null && ((iCode = responseProceed.code()) == 429 || iCode == 500 || iCode == 502 || iCode == 503)) {
                    String str = responseProceed.headers().get("Retry-After");
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            long j = Long.parseLong(str);
                            if (j > 0) {
                                VungleApiClient.this.retryAfterDataMap.put(strEncodedPath, Long.valueOf((j * 1000) + System.currentTimeMillis()));
                            }
                        } catch (NumberFormatException unused) {
                            Log.d(VungleApiClient.TAG, "Retry-After value is not an valid value");
                        }
                    }
                }
                return responseProceed;
            }
        }).build();
        api = (VungleApi) new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build().create(VungleApi.class);
    }

    public static void updateIMEI(String str, boolean z) {
        VungleApiClient vungleApiClient = _instance;
        vungleApiClient.userImei = str;
        vungleApiClient.shouldTransmitIMEI = z;
    }

    public static void addWrapperInfo(WrapperFramework wrapperFramework, String str) {
        if (wrapperFramework == null || wrapperFramework == WrapperFramework.none) {
            return;
        }
        HEADER_UA += ";" + wrapperFramework;
        if (str == null || str.isEmpty()) {
            return;
        }
        HEADER_UA += Constants.URL_PATH_DELIMITER + str;
    }

    public static synchronized void init(final Context context, String str, String str2, Storage storage) {
        if (_instance != null) {
            return;
        }
        _instance = new VungleApiClient();
        _instance.storage = storage;
        _instance.context = new WeakReference<>(context);
        _instance.shouldTransmitIMEI = false;
        _instance.cacheDir = str2;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", str);
        jsonObject.addProperty("bundle", context.getPackageName());
        try {
            jsonObject.addProperty("ver", context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException unused) {
            jsonObject.addProperty("ver", "1.0");
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("make", Build.MANUFACTURER);
        jsonObject2.addProperty("model", Build.MODEL);
        jsonObject2.addProperty("osv", Build.VERSION.RELEASE);
        jsonObject2.addProperty("carrier", ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName());
        jsonObject2.addProperty("os", MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "amazon" : "android");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        jsonObject2.addProperty(i.WIDTH, Integer.valueOf(displayMetrics.widthPixels));
        jsonObject2.addProperty(i.HEIGHT, Integer.valueOf(displayMetrics.heightPixels));
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.add("vungle", new JsonObject());
        jsonObject2.add("ext", jsonObject3);
        if (Build.VERSION.SDK_INT >= 17) {
            _instance.uaString = WebSettings.getDefaultUserAgent(context);
            jsonObject2.addProperty("ua", _instance.uaString);
            _instance.deviceBody = jsonObject2;
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            _instance.uaString = new WebView(context.getApplicationContext()).getSettings().getUserAgentString();
            jsonObject2.addProperty("ua", _instance.uaString);
            _instance.deviceBody = jsonObject2;
        } else {
            _instance.deviceBody = jsonObject2;
            try {
                _instance.uaString = System.getProperty("http.agent");
            } catch (Exception e) {
                e.printStackTrace();
            }
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.vungle.warren.network.VungleApiClient.2
                @Override // java.lang.Runnable
                public void run() {
                    VungleApiClient._instance.deviceBody.addProperty("ua", new WebView(context.getApplicationContext()).getSettings().getUserAgentString());
                    countDownLatch.countDown();
                }
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            List<String> providers = locationManager.getProviders(true);
            if (providers != null) {
                Iterator<String> it = providers.iterator();
                Location location = null;
                while (it.hasNext()) {
                    Location lastKnownLocation = locationManager.getLastKnownLocation(it.next());
                    if (lastKnownLocation != null && (location == null || lastKnownLocation.getAccuracy() < location.getAccuracy())) {
                        location = lastKnownLocation;
                    }
                }
                if (location != null) {
                    JsonObject jsonObject4 = new JsonObject();
                    jsonObject4.addProperty(LocationConst.ACCURACY, String.valueOf(location.getAccuracy()));
                    jsonObject4.addProperty(LocationConst.LATITUDE, String.valueOf(location.getLatitude()));
                    jsonObject4.addProperty(LocationConst.LONGITUDE, String.valueOf(location.getLongitude()));
                    jsonObject4.addProperty(LocationConst.SPEED, String.valueOf(location.getSpeed()));
                    jsonObject4.addProperty("timestamp", Long.valueOf(location.getTime()));
                    _instance.location = jsonObject4;
                }
            }
        } else {
            Log.d(TAG, "Location permission was not granted, location information will not be included");
        }
        new AdvertisingIDTask().execute(new Void[0]);
        _instance.appBody = jsonObject;
    }

    public static void config(@NonNull final Callback<JsonObject> callback) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("device", _instance.getDeviceBody());
            jsonObject.add("app", _instance.appBody);
            jsonObject.add("user", _instance.getUserBody());
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("is_auto_cached_enforced", (Boolean) false);
            jsonObject.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jsonObject2);
            VungleApiClient vungleApiClient = _instance;
            api.config(HEADER_UA, jsonObject).enqueue(new Callback<JsonObject>() { // from class: com.vungle.warren.network.VungleApiClient.3
                @Override // retrofit2.Callback
                public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                    if (!response.isSuccessful()) {
                        callback.onResponse(call, response);
                        return;
                    }
                    JsonObject jsonObjectBody = response.body();
                    Log.d(VungleApiClient.TAG, "Config Response: " + jsonObjectBody);
                    if (JsonUtil.hasNonNull(jsonObjectBody, "sleep")) {
                        Log.e(VungleApiClient.TAG, "Error Initializing Vungle. Please try again. " + (JsonUtil.hasNonNull(jsonObjectBody, "info") ? jsonObjectBody.get("info").getAsString() : ""));
                        callback.onFailure(call, new VungleException(3));
                        return;
                    }
                    if (!JsonUtil.hasNonNull(jsonObjectBody, "endpoints")) {
                        Log.e(VungleApiClient.TAG, "Error Initializing Vungle. Please try again. ");
                        callback.onFailure(call, new VungleException(3));
                        return;
                    }
                    JsonObject asJsonObject = jsonObjectBody.getAsJsonObject("endpoints");
                    HttpUrl httpUrl = HttpUrl.parse(asJsonObject.get(AppSettingsData.STATUS_NEW).getAsString());
                    HttpUrl httpUrl2 = HttpUrl.parse(asJsonObject.get("ads").getAsString());
                    HttpUrl httpUrl3 = HttpUrl.parse(asJsonObject.get("will_play_ad").getAsString());
                    HttpUrl httpUrl4 = HttpUrl.parse(asJsonObject.get("report_ad").getAsString());
                    HttpUrl httpUrl5 = HttpUrl.parse(asJsonObject.get("ri").getAsString());
                    VungleApiClient._instance.newEndpoint = httpUrl.toString();
                    VungleApiClient._instance.requestAdEndpoint = httpUrl2.toString();
                    VungleApiClient._instance.willPlayAdEndpoint = httpUrl3.toString();
                    VungleApiClient._instance.reportAdEndpoint = httpUrl4.toString();
                    VungleApiClient._instance.riEndpoint = httpUrl5.toString();
                    JsonObject asJsonObject2 = jsonObjectBody.getAsJsonObject("will_play_ad");
                    VungleApiClient._instance.willPlayAdTimeout = asJsonObject2.get("request_timeout").getAsInt();
                    VungleApiClient._instance.willPlayAdEnabled = asJsonObject2.get("enabled").getAsBoolean();
                    VungleApiClient._instance.enableMoat = jsonObjectBody.getAsJsonObject("viewability").get("moat").getAsBoolean();
                    callback.onResponse(call, response);
                    if (VungleApiClient._instance.willPlayAdEnabled) {
                        Log.v(VungleApiClient.TAG, "willPlayAd is enabled, generating a timeout client.");
                        VungleApiClient unused = VungleApiClient._instance;
                        VungleApiClient._instance.timeoutApi = (VungleApi) new Retrofit.Builder().client(VungleApiClient.client.newBuilder().readTimeout(VungleApiClient._instance.willPlayAdTimeout, TimeUnit.MILLISECONDS).build()).addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.vungle.com/").build().create(VungleApi.class);
                    }
                    if (VungleApiClient.getMoatEnabled()) {
                        MoatOptions moatOptions = new MoatOptions();
                        moatOptions.disableAdIdCollection = true;
                        moatOptions.disableLocationServices = true;
                        moatOptions.loggingEnabled = true;
                        MoatAnalytics.getInstance().start(moatOptions, (Application) ((Context) VungleApiClient._instance.context.get()).getApplicationContext());
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<JsonObject> call, Throwable th) {
                    Log.e(VungleApiClient.TAG, "Failed to configure.", th);
                    callback.onFailure(call, th);
                }
            });
        } catch (IllegalStateException e) {
            callback.onFailure(null, e);
        }
    }

    public static Call<JsonObject> reportNew() throws IllegalStateException {
        if (_instance.newEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        HashMap map = new HashMap(2);
        map.put("app_id", _instance.appBody.get("id").getAsString());
        map.put("ifa", _instance.getAdvertIdFromCookie());
        return api.reportNew(HEADER_UA, _instance.newEndpoint, map);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static Call<JsonObject> requestAd(String str, boolean z) throws IllegalStateException {
        if (_instance.requestAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("device", _instance.getDeviceBody());
        jsonObject.add("app", _instance.appBody);
        jsonObject.add("user", _instance.getUserBody());
        JsonObject jsonObject2 = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(str);
        jsonObject2.add("placements", jsonArray);
        jsonObject2.addProperty("header_bidding", Boolean.valueOf(z));
        jsonObject.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jsonObject2);
        return api.ads(HEADER_UA, _instance.requestAdEndpoint, jsonObject);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static Call<JsonObject> willPlayAd(String str, boolean z, String str2) throws IllegalStateException, VungleError {
        VungleApiClient vungleApiClient = _instance;
        if (vungleApiClient.willPlayAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        if (!vungleApiClient.willPlayAdEnabled) {
            throw new VungleError(6);
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("device", _instance.getDeviceBody());
        jsonObject.add("app", _instance.appBody);
        jsonObject.add("user", _instance.getUserBody());
        JsonObject jsonObject2 = new JsonObject();
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.addProperty("reference_id", str);
        jsonObject3.addProperty("is_auto_cached", Boolean.valueOf(z));
        jsonObject2.add("placement", jsonObject3);
        jsonObject2.addProperty("ad_token", str2);
        jsonObject.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jsonObject2);
        VungleApiClient vungleApiClient2 = _instance;
        return vungleApiClient2.timeoutApi.willPlayAd(HEADER_UA, vungleApiClient2.willPlayAdEndpoint, jsonObject);
    }

    public static Call<JsonObject> reportAd(JsonObject jsonObject) {
        if (_instance.reportAdEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("device", _instance.getDeviceBody());
        jsonObject2.add("app", _instance.appBody);
        jsonObject2.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jsonObject);
        jsonObject2.add("user", _instance.getUserBody());
        return api.reportAd(HEADER_UA, _instance.reportAdEndpoint, jsonObject2);
    }

    public static Call<JsonObject> ri(JsonObject jsonObject) {
        if (_instance.riEndpoint == null) {
            throw new IllegalStateException("API Client not configured yet! Must call /config first.");
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.add("device", _instance.getDeviceBody());
        jsonObject2.add("app", _instance.appBody);
        jsonObject2.add(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jsonObject);
        return api.ri(HEADER_UA, _instance.riEndpoint, jsonObject2);
    }

    public static void pingTPAT(final String str) {
        Callback<ResponseBody> callback = new Callback<ResponseBody>() { // from class: com.vungle.warren.network.VungleApiClient.4
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseBody> call, Throwable th) {
                Log.e(VungleApiClient.TAG, "Failed to ping TPAT Url : " + str);
            }
        };
        if (!TextUtils.isEmpty(str) && HttpUrl.parse(str) != null) {
            if (!TextUtils.isEmpty(_instance.userImei)) {
                VungleApiClient vungleApiClient = _instance;
                if (vungleApiClient.shouldTransmitIMEI) {
                    str = str.replace("%imei%", vungleApiClient.userImei);
                }
            }
            api.pingTPAT(_instance.uaString, str).enqueue(callback);
            return;
        }
        callback.onFailure(null, new IllegalArgumentException("Malformed Url"));
    }

    @SuppressLint({"HardwareIds"})
    private JsonObject getDeviceBody() throws IllegalStateException {
        String str;
        boolean zHasSystemFeature;
        String connectionTypeDetail;
        NetworkInfo activeNetworkInfo;
        if (this.context.get() == null) {
            throw new IllegalStateException("Context is null, SDK not initialized");
        }
        JsonObject jsonObject = new JsonObject();
        String advertIdFromCookie = _instance.getAdvertIdFromCookie();
        boolean zCanRequestPackageInstalls = false;
        if (advertIdFromCookie != null) {
            jsonObject.addProperty(MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "amazon_advertising_id" : "gaid", advertIdFromCookie);
            this.deviceBody.addProperty("ifa", advertIdFromCookie);
            this.deviceBody.addProperty("lmt", Integer.valueOf(this.limitAdTracking ? 1 : 0));
        } else {
            this.deviceBody.addProperty("ifa", Settings.Secure.getString(this.context.get().getContentResolver(), i.ANDROID_ID));
            this.deviceBody.addProperty("lmt", (Number) 0);
        }
        new AdvertisingIDTask().execute(new Void[0]);
        Iterator<PackageInfo> it = _instance.context.get().getPackageManager().getInstalledPackages(128).iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().packageName.equalsIgnoreCase("com.google.android.gms")) {
                z = true;
            }
        }
        this.deviceBody.addProperty("is_google_play_services_available", Boolean.valueOf(z));
        Intent intentRegisterReceiver = this.context.get().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
        int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
        if (intExtra > 0 && intExtra2 > 0) {
            jsonObject.addProperty("battery_level", Float.valueOf(intExtra / intExtra2));
        }
        int intExtra3 = intentRegisterReceiver.getIntExtra("status", -1);
        String str2 = "UNKNOWN";
        if (intExtra3 == -1) {
            str = "UNKNOWN";
        } else if (intExtra3 == 2 || intExtra3 == 5) {
            int intExtra4 = intentRegisterReceiver.getIntExtra("plugged", -1);
            if (intExtra4 == 1) {
                str = "BATTERY_PLUGGED_AC";
            } else if (intExtra4 != 2) {
                str = intExtra4 != 4 ? "BATTERY_PLUGGED_OTHERS" : "BATTERY_PLUGGED_WIRELESS";
            } else {
                str = "BATTERY_PLUGGED_USB";
            }
        } else {
            str = "NOT_CHARGING";
        }
        jsonObject.addProperty("battery_state", str);
        if (Build.VERSION.SDK_INT >= 21) {
            PowerManager powerManager = (PowerManager) this.context.get().getSystemService("power");
            jsonObject.addProperty("battery_saver_enabled", Integer.valueOf((powerManager == null || !powerManager.isPowerSaveMode()) ? 0 : 1));
        }
        if (PermissionChecker.checkCallingOrSelfPermission(_instance.context.get(), "android.permission.ACCESS_NETWORK_STATE") == 0) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.context.get().getSystemService("connectivity");
            String str3 = "NONE";
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                connectionTypeDetail = str3;
            } else {
                int type = activeNetworkInfo.getType();
                if (type == 0) {
                    connectionTypeDetail = getConnectionTypeDetail(activeNetworkInfo.getSubtype());
                    str3 = "MOBILE";
                } else if (type == 1 || type == 6) {
                    str3 = "WIFI";
                    connectionTypeDetail = "WIFI";
                } else if (type == 7) {
                    str3 = "BLUETOOTH";
                    connectionTypeDetail = "BLUETOOTH";
                } else if (type != 9) {
                    str3 = "UNKNOWN";
                    connectionTypeDetail = str3;
                } else {
                    str3 = "ETHERNET";
                    connectionTypeDetail = "ETHERNET";
                }
            }
            jsonObject.addProperty(i.Y, str3);
            jsonObject.addProperty("connection_type_detail", connectionTypeDetail);
            if (Build.VERSION.SDK_INT >= 24) {
                if (connectivityManager.isActiveNetworkMetered()) {
                    int restrictBackgroundStatus = connectivityManager.getRestrictBackgroundStatus();
                    if (restrictBackgroundStatus == 1) {
                        str2 = "DISABLED";
                    } else if (restrictBackgroundStatus == 2) {
                        str2 = "WHITELISTED";
                    } else if (restrictBackgroundStatus == 3) {
                        str2 = "ENABLED";
                    }
                    jsonObject.addProperty("data_saver_status", str2);
                    jsonObject.addProperty("network_metered", (Number) 1);
                } else {
                    jsonObject.addProperty("data_saver_status", "NOT_APPLICABLE");
                    jsonObject.addProperty("network_metered", (Number) 0);
                }
            }
        }
        jsonObject.addProperty("locale", Locale.getDefault().toString());
        jsonObject.addProperty("language", Locale.getDefault().getLanguage());
        jsonObject.addProperty("time_zone", TimeZone.getDefault().getID());
        if (this.context.get() != null) {
            AudioManager audioManager = (AudioManager) this.context.get().getSystemService("audio");
            if (audioManager != null) {
                int streamMaxVolume = audioManager.getStreamMaxVolume(3);
                int streamVolume = audioManager.getStreamVolume(3);
                jsonObject.addProperty("volume_level", Float.valueOf(streamVolume / streamMaxVolume));
                jsonObject.addProperty("sound_enabled", Integer.valueOf(streamVolume > 0 ? 1 : 0));
            }
            File file = new File(this.cacheDir);
            boolean zMkdir = file.exists() && file.isDirectory();
            if (!zMkdir) {
                if (!file.exists()) {
                    zMkdir = file.mkdir();
                } else if (!file.isDirectory() && file.delete()) {
                    zMkdir = file.mkdir();
                }
            }
            if (zMkdir) {
                StatFs statFs = new StatFs(this.cacheDir);
                long blockSize = -1;
                if (Build.VERSION.SDK_INT >= 26) {
                    StorageManager storageManager = (StorageManager) this.context.get().getSystemService(StorageManager.class);
                    if (storageManager != null) {
                        try {
                            blockSize = storageManager.getCacheQuotaBytes(storageManager.getUuidForPath(file));
                        } catch (IOException unused) {
                            Log.e(TAG, "Unable to check available bytes");
                        }
                    }
                } else if (Build.VERSION.SDK_INT >= 18) {
                    blockSize = statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
                } else {
                    blockSize = statFs.getBlockSize() * statFs.getAvailableBlocks();
                }
                jsonObject.addProperty("storage_bytes_available", Long.valueOf(blockSize));
            }
            if (MANUFACTURER_AMAZON.equals(Build.MANUFACTURER)) {
                zHasSystemFeature = this.context.get().getApplicationContext().getPackageManager().hasSystemFeature("amazon.hardware.fire_tv");
            } else {
                zHasSystemFeature = Build.VERSION.SDK_INT < 23 ? this.context.get().getApplicationContext().getPackageManager().hasSystemFeature("com.google.android.tv") || !this.context.get().getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen") : ((UiModeManager) this.context.get().getSystemService("uimode")).getCurrentModeType() == 4;
            }
            jsonObject.addProperty("is_tv", Boolean.valueOf(zHasSystemFeature));
            jsonObject.addProperty("os_api_level", Integer.valueOf(Build.VERSION.SDK_INT));
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    if (this.context.get().checkCallingOrSelfPermission("android.permission.REQUEST_INSTALL_PACKAGES") == 0) {
                        zCanRequestPackageInstalls = this.context.get().getApplicationContext().getPackageManager().canRequestPackageInstalls();
                    }
                } else if (Settings.Secure.getInt(this.context.get().getContentResolver(), "install_non_market_apps") == 1) {
                    zCanRequestPackageInstalls = true;
                }
            } catch (Settings.SettingNotFoundException e) {
                Log.e(TAG, "isInstallNonMarketAppsEnabled Settings not found", e);
            }
            jsonObject.addProperty("is_sideload_enabled", Boolean.valueOf(zCanRequestPackageInstalls));
        } else {
            jsonObject.addProperty("volume_level", (Number) 0);
            jsonObject.addProperty("sound_enabled", (Number) 0);
        }
        jsonObject.addProperty("sd_card_available", Integer.valueOf(Environment.getExternalStorageState().equals("mounted") ? 1 : 0));
        jsonObject.addProperty("os_name", Build.FINGERPRINT);
        jsonObject.addProperty("vduid", "");
        JsonObject jsonObject2 = _instance.location;
        if (jsonObject2 != null) {
            jsonObject.add("location", jsonObject2);
        }
        this.deviceBody.getAsJsonObject("ext").getAsJsonObject("vungle").add(MANUFACTURER_AMAZON.equals(Build.MANUFACTURER) ? "amazon" : "android", jsonObject);
        return this.deviceBody;
    }

    private JsonObject getUserBody() {
        long jLongValue;
        String string;
        String string2;
        String string3;
        if (this.userBody == null) {
            this.userBody = new JsonObject();
        }
        Cookie cookie = (Cookie) this.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie != null) {
            string = cookie.getString("consent_status");
            string2 = cookie.getString("consent_source");
            jLongValue = cookie.getLong("timestamp").longValue();
            string3 = cookie.getString("consent_message_version");
        } else {
            jLongValue = 0;
            string = "unknown";
            string2 = "no_interaction";
            string3 = "";
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("consent_status", string);
        jsonObject.addProperty("consent_source", string2);
        jsonObject.addProperty("consent_timestamp", Long.valueOf(jLongValue));
        if (TextUtils.isEmpty(string3)) {
            string3 = "";
        }
        jsonObject.addProperty("consent_message_version", string3);
        this.userBody.add("gdpr", jsonObject);
        return this.userBody;
    }

    public static boolean getMoatEnabled() {
        return _instance.enableMoat && Build.VERSION.SDK_INT >= 16;
    }

    private static class AdvertisingIDTask extends AsyncTask<Void, Void, String> {
        private AdvertisingIDTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... voidArr) {
            String string = null;
            try {
                if (VungleApiClient.MANUFACTURER_AMAZON.equals(Build.MANUFACTURER)) {
                    try {
                        ContentResolver contentResolver = ((Context) VungleApiClient._instance.context.get()).getContentResolver();
                        VungleApiClient vungleApiClient = VungleApiClient._instance;
                        boolean z = true;
                        if (Settings.Secure.getInt(contentResolver, "limit_ad_tracking") != 1) {
                            z = false;
                        }
                        vungleApiClient.limitAdTracking = z;
                        string = Settings.Secure.getString(contentResolver, "advertising_id");
                        VungleApiClient._instance.addAdvertIdInCookie(string);
                    } catch (Settings.SettingNotFoundException e) {
                        Log.w(VungleApiClient.TAG, "Error getting Amazon advertising info", e);
                    }
                } else {
                    try {
                        AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo((Context) VungleApiClient._instance.context.get());
                        if (advertisingIdInfo != null) {
                            string = advertisingIdInfo.getId();
                            VungleApiClient._instance.limitAdTracking = advertisingIdInfo.isLimitAdTrackingEnabled();
                            VungleApiClient._instance.deviceBody.addProperty("ifa", string);
                            VungleApiClient._instance.addAdvertIdInCookie(string);
                        }
                    } catch (NoClassDefFoundError e2) {
                        Log.e(VungleApiClient.TAG, "Play services Not available: " + e2.getLocalizedMessage());
                        string = Settings.Secure.getString(((Context) VungleApiClient._instance.context.get()).getContentResolver(), "advertising_id");
                        VungleApiClient._instance.addAdvertIdInCookie(string);
                    }
                }
            } catch (Exception unused) {
                Log.e(VungleApiClient.TAG, "Cannot load Advertising ID");
            }
            return string;
        }
    }

    private String getAdvertIdFromCookie() {
        Cookie cookie = (Cookie) this.storage.load(Cookie.GOOGLE_AD_ID_COOKIE, Cookie.class);
        if (cookie == null) {
            return null;
        }
        return cookie.getString("advertId");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdvertIdInCookie(String str) {
        Cookie cookie = new Cookie(Cookie.GOOGLE_AD_ID_COOKIE);
        cookie.putValue("advertId", str);
        this.storage.save(cookie);
    }

    public static long getRetryAfterHeaderValue(retrofit2.Response<JsonObject> response) {
        try {
            return Long.parseLong(response.headers().get("Retry-After")) * 1000;
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }
}
