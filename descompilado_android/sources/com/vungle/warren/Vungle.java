package com.vungle.warren;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.drive.DriveFile;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vungle.warren.download.APKDirectDownloadManager;
import com.vungle.warren.error.VungleError;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.JsonUtil;
import com.vungle.warren.model.Placement;
import com.vungle.warren.network.Downloader;
import com.vungle.warren.network.FetchDownloader;
import com.vungle.warren.network.VungleApiClient;
import com.vungle.warren.persistence.FilePersistor;
import com.vungle.warren.persistence.GraphicDesigner;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.tasks.CleanupJob;
import com.vungle.warren.tasks.DownloadJob;
import com.vungle.warren.tasks.ReconfigJob;
import com.vungle.warren.tasks.SendReportsJob;
import com.vungle.warren.tasks.VungleJobCreator;
import com.vungle.warren.ui.VungleActivity;
import com.vungle.warren.ui.VungleFlexViewActivity;
import com.vungle.warren.ui.VungleNativeView;
import com.vungle.warren.utility.FileUtility;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/* JADX INFO: loaded from: classes2.dex */
public class Vungle {
    private static final String COM_VUNGLE_SDK = "com.vungle.sdk";
    private static final String TAG = "com.vungle.warren.Vungle";
    private String appID;
    private Context context;
    private Downloader downloader;
    private HeaderBiddingCallback headerBiddingCallback;
    private InitCallback initCallback;
    private VungleJobRunner jobRunner;
    private Map<String, Boolean> loadOperations = new ConcurrentHashMap();
    private Map<String, Boolean> playOperations = new ConcurrentHashMap();
    private PublisherDirectDownload publisherDirectDownload;
    private boolean shouldTransmitIMEI;
    Storage storage;
    private Consent tempConsent;
    private String tempConsentVersion;
    private String userIMEI;
    static final Vungle _instance = new Vungle();
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static boolean isInitialized = false;
    private static AtomicBoolean isInitializing = new AtomicBoolean(false);
    private static AtomicBoolean isDepInit = new AtomicBoolean(false);

    public enum Consent {
        OPTED_IN,
        OPTED_OUT
    }

    interface DownloadCallback {
        void onDownloadCompleted(File file, String str);

        void onDownloadFailed(Throwable th, String str);
    }

    private Vungle() {
    }

    @Deprecated
    public static void init(@NonNull Collection<String> collection, @NonNull String str, @NonNull Context context, @NonNull InitCallback initCallback, PublisherDirectDownload publisherDirectDownload) throws IllegalArgumentException {
        init(str, context, initCallback, publisherDirectDownload);
    }

    public static void init(@NonNull String str, @NonNull Context context, @NonNull InitCallback initCallback, PublisherDirectDownload publisherDirectDownload) throws IllegalArgumentException {
        if (initCallback == null) {
            throw new IllegalArgumentException("A valid InitCallback required to ensure API calls are being made after initialize is successful");
        }
        if (context == null || str == null || str.isEmpty()) {
            initCallback.onError(new VungleException(6));
            return;
        }
        if (!(context instanceof Application)) {
            initCallback.onError(new VungleException(7));
            return;
        }
        if (isInitialized()) {
            Log.d(TAG, "init already complete");
            initCallback.onSuccess();
            return;
        }
        if (isInitializing.getAndSet(true)) {
            Log.d(TAG, "init ongoing");
            initCallback.onError(new VungleException(8));
            return;
        }
        if (!isDepInit.getAndSet(true)) {
            GraphicDesigner graphicDesigner = new GraphicDesigner(context.getCacheDir());
            Storage storageMakeInstance = Storage.makeInstance(new FilePersistor(context.getFilesDir()), graphicDesigner);
            Vungle vungle = _instance;
            vungle.context = context;
            vungle.appID = str;
            vungle.downloader = new FetchDownloader(context);
            Vungle vungle2 = _instance;
            vungle2.storage = storageMakeInstance;
            vungle2.jobRunner = new VungleJobRunner(new VungleJobCreator(vungle2.storage, graphicDesigner, new ReconfigJob.ReconfigCall() { // from class: com.vungle.warren.Vungle.1
                @Override // com.vungle.warren.tasks.ReconfigJob.ReconfigCall
                public void reConfigVungle() {
                    Vungle.reConfigure();
                }
            }));
            storageMakeInstance.init(1);
            VungleApiClient.init(context, str, graphicDesigner.getCacheDirectory().getPath(), storageMakeInstance);
            if (!TextUtils.isEmpty(_instance.userIMEI)) {
                Vungle vungle3 = _instance;
                VungleApiClient.updateIMEI(vungle3.userIMEI, vungle3.shouldTransmitIMEI);
            }
            Vungle vungle4 = _instance;
            if (vungle4.tempConsent != null && !TextUtils.isEmpty(vungle4.tempConsentVersion)) {
                Vungle vungle5 = _instance;
                updateConsentStatus(vungle5.tempConsent, vungle5.tempConsentVersion);
            }
            Cookie cookie = (Cookie) _instance.storage.load(Cookie.APP_ID, Cookie.class);
            if (cookie == null) {
                cookie = new Cookie(Cookie.APP_ID);
            }
            cookie.putValue(Cookie.APP_ID, str);
            _instance.storage.save(cookie);
        }
        Vungle vungle6 = _instance;
        vungle6.initCallback = initCallback;
        vungle6.publisherDirectDownload = publisherDirectDownload;
        vungle6.configure(initCallback);
    }

    static void reConfigure() {
        if (isInitialized()) {
            Vungle vungle = _instance;
            vungle.configure(vungle.initCallback);
        } else {
            Vungle vungle2 = _instance;
            init(vungle2.appID, vungle2.context, vungle2.initCallback, vungle2.publisherDirectDownload);
        }
    }

    @Deprecated
    public static void init(@NonNull Collection<String> collection, @NonNull String str, @NonNull Context context, @NonNull InitCallback initCallback) throws IllegalArgumentException {
        init(str, context, initCallback, (PublisherDirectDownload) null);
    }

    public static void init(@NonNull String str, @NonNull Context context, @NonNull InitCallback initCallback) throws IllegalArgumentException {
        init(str, context, initCallback, (PublisherDirectDownload) null);
    }

    private void configure(@NonNull final InitCallback initCallback) {
        VungleApiClient.config(new Callback<JsonObject>() { // from class: com.vungle.warren.Vungle.2
            @Override // retrofit2.Callback
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response == null) {
                    initCallback.onError(new VungleException(2));
                    Vungle.isInitializing.set(false);
                    return;
                }
                if (response.isSuccessful()) {
                    if (!Vungle.this.context.getSharedPreferences(Vungle.COM_VUNGLE_SDK, 0).getBoolean("reported", false)) {
                        VungleApiClient.reportNew().enqueue(new Callback<JsonObject>() { // from class: com.vungle.warren.Vungle.2.1
                            @Override // retrofit2.Callback
                            public void onFailure(Call<JsonObject> call2, Throwable th) {
                            }

                            @Override // retrofit2.Callback
                            public void onResponse(Call<JsonObject> call2, Response<JsonObject> response2) {
                                if (response2.isSuccessful()) {
                                    SharedPreferences.Editor editorEdit = Vungle.this.context.getSharedPreferences(Vungle.COM_VUNGLE_SDK, 0).edit();
                                    editorEdit.putBoolean("reported", true);
                                    editorEdit.apply();
                                    Log.d(Vungle.TAG, "Saving reported state to shared preferences");
                                }
                            }
                        });
                    }
                    JsonObject jsonObjectBody = response.body();
                    JsonArray asJsonArray = jsonObjectBody.getAsJsonArray("placements");
                    if (asJsonArray.size() != 0) {
                        Vungle.this.playOperations.clear();
                        Vungle.this.loadOperations.clear();
                        ArrayList arrayList = new ArrayList();
                        Iterator<JsonElement> it = asJsonArray.iterator();
                        while (it.hasNext()) {
                            arrayList.add(new Placement(it.next().getAsJsonObject()));
                        }
                        Vungle._instance.storage.setValidPlacements(arrayList);
                        if (jsonObjectBody.has("gdpr")) {
                            Cookie cookie = (Cookie) Vungle._instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
                            if (cookie == null) {
                                cookie = new Cookie(Cookie.CONSENT_COOKIE);
                                cookie.putValue("consent_status", "unknown");
                                cookie.putValue("consent_source", "no_interaction");
                                cookie.putValue("timestamp", 0L);
                            }
                            JsonObject asJsonObject = jsonObjectBody.getAsJsonObject("gdpr");
                            boolean z = JsonUtil.hasNonNull(asJsonObject, "is_country_data_protected") && asJsonObject.get("is_country_data_protected").getAsBoolean();
                            String asString = JsonUtil.hasNonNull(asJsonObject, "consent_title") ? asJsonObject.get("consent_title").getAsString() : "";
                            String asString2 = JsonUtil.hasNonNull(asJsonObject, "consent_message") ? asJsonObject.get("consent_message").getAsString() : "";
                            String asString3 = JsonUtil.hasNonNull(asJsonObject, "consent_message_version") ? asJsonObject.get("consent_message_version").getAsString() : "";
                            String asString4 = JsonUtil.hasNonNull(asJsonObject, "button_accept") ? asJsonObject.get("button_accept").getAsString() : "";
                            String asString5 = JsonUtil.hasNonNull(asJsonObject, "button_deny") ? asJsonObject.get("button_deny").getAsString() : "";
                            cookie.putValue("is_country_data_protected", Boolean.valueOf(z));
                            if (TextUtils.isEmpty(asString)) {
                                asString = "Targeted Ads";
                            }
                            cookie.putValue("consent_title", asString);
                            if (TextUtils.isEmpty(asString2)) {
                                asString2 = "To receive more relevant ad content based on your interactions with our ads, click \"I Consent\" below. Either way, you will see the same amount of ads.";
                            }
                            cookie.putValue("consent_message", asString2);
                            if (!"publisher".equalsIgnoreCase(cookie.getString("consent_source"))) {
                                if (TextUtils.isEmpty(asString3)) {
                                    asString3 = "";
                                }
                                cookie.putValue("consent_message_version", asString3);
                            }
                            if (TextUtils.isEmpty(asString4)) {
                                asString4 = "I Consent";
                            }
                            cookie.putValue("button_accept", asString4);
                            if (TextUtils.isEmpty(asString5)) {
                                asString5 = "I Do Not Consent";
                            }
                            cookie.putValue("button_deny", asString5);
                            Vungle._instance.storage.save(cookie);
                        }
                        if (!jsonObjectBody.has("apk_direct_download") || !jsonObjectBody.getAsJsonObject("apk_direct_download").has("enabled")) {
                            APKDirectDownloadManager.init(Vungle.this.context);
                            APKDirectDownloadManager.setDirectDownloadStatus(-1);
                        } else {
                            boolean asBoolean = jsonObjectBody.getAsJsonObject("apk_direct_download").get("enabled").getAsBoolean();
                            if (asBoolean) {
                                APKDirectDownloadManager.init(Vungle.this.context);
                                APKDirectDownloadManager.setDirectDownloadStatus(asBoolean ? 1 : 0);
                            }
                        }
                        if (jsonObjectBody.has("ri")) {
                            Cookie cookie2 = (Cookie) Vungle._instance.storage.load(Cookie.CONFIG_COOKIE, Cookie.class);
                            if (cookie2 == null) {
                                cookie2 = new Cookie(Cookie.CONFIG_COOKIE);
                            }
                            cookie2.putValue("isReportIncentivizedEnabled", Boolean.valueOf(jsonObjectBody.getAsJsonObject("ri").get("enabled").getAsBoolean()));
                            Vungle._instance.storage.save(cookie2);
                        }
                        if (!jsonObjectBody.has("attribution_reporting")) {
                            Vungle.this.shouldTransmitIMEI = false;
                        } else {
                            JsonObject asJsonObject2 = jsonObjectBody.getAsJsonObject("attribution_reporting");
                            if (asJsonObject2.has("should_transmit_imei")) {
                                Vungle.this.shouldTransmitIMEI = asJsonObject2.get("should_transmit_imei").getAsBoolean();
                            } else {
                                Vungle.this.shouldTransmitIMEI = false;
                            }
                        }
                        if (jsonObjectBody.has("config")) {
                            Vungle.this.jobRunner.execute(ReconfigJob.makeJobInfo(Vungle.this.appID).setDelay(jsonObjectBody.getAsJsonObject("config").get("refresh_time").getAsLong()));
                        }
                        boolean unused = Vungle.isInitialized = true;
                        initCallback.onSuccess();
                        Vungle.isInitializing.set(false);
                        Collection<Placement> collectionLoadValidPlacements = Vungle.this.storage.loadValidPlacements();
                        Vungle.this.jobRunner.execute(CleanupJob.makeJobInfo());
                        if (collectionLoadValidPlacements != null) {
                            for (Placement placement : collectionLoadValidPlacements) {
                                if (placement.isAutoCached()) {
                                    Log.d(Vungle.TAG, "starting jobs for autocached advs");
                                    Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                                }
                            }
                        }
                        Vungle.this.jobRunner.execute(SendReportsJob.makeJobInfo());
                        return;
                    }
                    initCallback.onError(new VungleException(0));
                    Vungle.isInitializing.set(false);
                    return;
                }
                long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue(response);
                if (retryAfterHeaderValue > 0) {
                    Vungle.this.jobRunner.execute(ReconfigJob.makeJobInfo(Vungle._instance.appID).setDelay(retryAfterHeaderValue));
                    initCallback.onError(new VungleException(14));
                    Vungle.isInitializing.set(false);
                } else {
                    initCallback.onError(new VungleException(3));
                    Vungle.isInitializing.set(false);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonObject> call, Throwable th) {
                boolean unused = Vungle.isInitialized = false;
                Vungle.isInitializing.set(false);
                Log.e(Vungle.TAG, Log.getStackTraceString(th));
                if (th instanceof HttpException) {
                    initCallback.onError(new VungleException(3));
                }
                initCallback.onError(new VungleException(2));
            }
        });
    }

    public static boolean isInitialized() {
        Storage storage;
        return (!isInitialized || (storage = _instance.storage) == null || storage.getValidPlacements() == null || _instance.storage.getValidPlacements().size() <= 0 || _instance.context == null) ? false : true;
    }

    public static void setIncentivizedFields(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized");
            return;
        }
        Cookie cookie = (Cookie) _instance.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
        if (cookie == null) {
            cookie = new Cookie(Cookie.INCENTIVIZED_TEXT_COOKIE);
        }
        boolean z = false;
        if (!TextUtils.isEmpty(str2)) {
            cookie.putValue("title", str2);
            z = true;
        }
        if (!TextUtils.isEmpty(str3)) {
            cookie.putValue(TtmlNode.TAG_BODY, str3);
            z = true;
        }
        if (!TextUtils.isEmpty(str4)) {
            cookie.putValue("continue", str4);
            z = true;
        }
        if (!TextUtils.isEmpty(str5)) {
            cookie.putValue("close", str5);
            z = true;
        }
        if (!TextUtils.isEmpty(str)) {
            cookie.putValue("userID", str);
            z = true;
        }
        if (z) {
            _instance.storage.save(cookie);
        }
    }

    public static boolean canPlayAd(@NonNull String str) {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized");
            return false;
        }
        return canPlayAd(_instance.storage.findValidAdvertisementForPlacement(str));
    }

    static boolean canPlayAd(Advertisement advertisement) {
        return advertisement != null && advertisement.getState() == 1 && _instance.storage.hasValidAssets(advertisement);
    }

    public static void playAd(@NonNull final String str, final AdConfig adConfig, @Nullable final PlayAdCallback playAdCallback) {
        final boolean z;
        if (!isInitialized()) {
            if (playAdCallback != null) {
                playAdCallback.onError(str, new VungleException(9));
                return;
            }
            return;
        }
        Placement placement = (Placement) _instance.storage.load(str, Placement.class);
        VungleException vungleException = (Boolean.TRUE.equals(_instance.playOperations.get(str)) || Boolean.TRUE.equals(_instance.loadOperations.get(str))) ? new VungleException(8) : null;
        if (placement == null) {
            vungleException = new VungleException(13);
        }
        if (vungleException != null) {
            if (playAdCallback != null) {
                playAdCallback.onError(str, vungleException);
                return;
            }
            return;
        }
        final Advertisement advertisementFindValidAdvertisementForPlacement = _instance.storage.findValidAdvertisementForPlacement(str);
        if (!canPlayAd(advertisementFindValidAdvertisementForPlacement)) {
            if (advertisementFindValidAdvertisementForPlacement != null && advertisementFindValidAdvertisementForPlacement.getState() == 1) {
                _instance.storage.saveAndApplyState(advertisementFindValidAdvertisementForPlacement, str, 4);
                if (placement.isAutoCached()) {
                    _instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                }
            }
            if (playAdCallback != null) {
                playAdCallback.onError(str, new VungleException(10));
            }
            z = true;
        } else {
            advertisementFindValidAdvertisementForPlacement.configure(adConfig);
            _instance.storage.save(advertisementFindValidAdvertisementForPlacement);
            z = false;
        }
        if (_instance.context != null) {
            VungleApiClient.willPlayAd(placement.getId(), placement.isAutoCached(), z ? "" : advertisementFindValidAdvertisementForPlacement.getAdToken()).enqueue(new Callback<JsonObject>() { // from class: com.vungle.warren.Vungle.3
                @Override // retrofit2.Callback
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Advertisement advertisement = null;
                    if (response.isSuccessful()) {
                        JsonObject jsonObjectBody = response.body();
                        if (jsonObjectBody.has("ad")) {
                            try {
                                Advertisement advertisement2 = new Advertisement(jsonObjectBody.getAsJsonObject("ad"));
                                try {
                                    advertisement2.configure(adConfig);
                                    Vungle._instance.storage.saveAndApplyState(advertisement2, str, 0);
                                    advertisement = advertisement2;
                                } catch (VungleError e) {
                                    e = e;
                                    advertisement = advertisement2;
                                    if (e.getErrorCode() == 6) {
                                        Log.e(Vungle.TAG, "will_play_ad was disabled by the configuration settings. This is expected.");
                                    } else {
                                        Log.e("Vungle", "Error using will_play_ad!", e);
                                    }
                                } catch (IllegalArgumentException unused) {
                                    advertisement = advertisement2;
                                    Log.v("Vungle", "Will Play Ad did not respond with a replacement. Move on.");
                                } catch (Exception e2) {
                                    e = e2;
                                    advertisement = advertisement2;
                                    Log.e("Vungle", "Error using will_play_ad!", e);
                                }
                            } catch (VungleError e3) {
                                e = e3;
                            } catch (IllegalArgumentException unused2) {
                            } catch (Exception e4) {
                                e = e4;
                            }
                        }
                    }
                    if (!z) {
                        String str2 = str;
                        Vungle.renderAd(str2, playAdCallback, str2, advertisementFindValidAdvertisementForPlacement);
                    } else {
                        if (advertisement == null) {
                            PlayAdCallback playAdCallback2 = playAdCallback;
                            if (playAdCallback2 != null) {
                                playAdCallback2.onError(str, new VungleException(1));
                                return;
                            }
                            return;
                        }
                        String str3 = str;
                        Vungle.renderAd(str3, playAdCallback, str3, advertisement);
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<JsonObject> call, Throwable th) {
                    if (z) {
                        PlayAdCallback playAdCallback2 = playAdCallback;
                        if (playAdCallback2 != null) {
                            playAdCallback2.onError(str, new VungleException(1));
                            return;
                        }
                        return;
                    }
                    String str2 = str;
                    Vungle.renderAd(str2, playAdCallback, str2, advertisementFindValidAdvertisementForPlacement);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void renderAd(@NonNull final String str, @Nullable final PlayAdCallback playAdCallback, final String str2, final Advertisement advertisement) {
        _instance.playOperations.put(str, true);
        VungleActivity.setEventListener(new AdvertisementPresenter.EventListener() { // from class: com.vungle.warren.Vungle.4
            boolean succesfulView = false;
            int percentViewed = -1;

            @Override // com.vungle.warren.presenter.AdvertisementPresenter.EventListener
            public void onNext(String str3, String str4) {
                boolean z = false;
                if (str3.equals("start")) {
                    Vungle._instance.storage.saveAndApplyState(advertisement, str, 2);
                    PlayAdCallback playAdCallback2 = playAdCallback;
                    if (playAdCallback2 != null) {
                        playAdCallback2.onAdStart(str);
                    }
                    this.percentViewed = 0;
                    Placement placement = (Placement) Vungle._instance.storage.load(str2, Placement.class);
                    if (placement == null || !placement.isAutoCached()) {
                        return;
                    }
                    Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(str2, true));
                    return;
                }
                if (str3.equals(TtmlNode.END)) {
                    Log.d("Vungle", "Cleaning up metadata and assets for placement " + str + " and advertisement " + advertisement.getId());
                    Vungle._instance.storage.saveAndApplyState(advertisement, str, 3);
                    Vungle._instance.playOperations.put(str, false);
                    Vungle._instance.jobRunner.execute(SendReportsJob.makeJobInfo());
                    PlayAdCallback playAdCallback3 = playAdCallback;
                    if (playAdCallback3 != null) {
                        String str5 = str;
                        boolean z2 = this.succesfulView || this.percentViewed >= 80;
                        if (str4 != null && str4.equals("isCTAClicked")) {
                            z = true;
                        }
                        playAdCallback3.onAdEnd(str5, z2, z);
                        return;
                    }
                    return;
                }
                if (str3.equals("successfulView")) {
                    this.succesfulView = true;
                } else if (str3.startsWith("percentViewed")) {
                    String[] strArrSplit = str3.split(":");
                    if (strArrSplit.length == 2) {
                        this.percentViewed = Integer.parseInt(strArrSplit[1]);
                    }
                }
            }

            @Override // com.vungle.warren.presenter.AdvertisementPresenter.EventListener
            public void onError(Throwable th) {
                Vungle._instance.storage.saveAndApplyState(advertisement, str, 4);
                Vungle._instance.playOperations.put(str, false);
                PlayAdCallback playAdCallback2 = playAdCallback;
                if (playAdCallback2 != null) {
                    playAdCallback2.onError(str, th);
                }
            }
        });
        Intent intent = new Intent(_instance.context, (Class<?>) (advertisement != null && "flexview".equals(advertisement.getTemplateType()) ? VungleFlexViewActivity.class : VungleActivity.class));
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        intent.putExtra("placement", str);
        _instance.context.startActivity(intent);
    }

    public static void loadAd(@NonNull String str, @Nullable LoadAdCallback loadAdCallback) {
        loadAd(str, loadAdCallback, _instance.publisherDirectDownload);
    }

    public static void loadAd(@NonNull final String str, @Nullable final LoadAdCallback loadAdCallback, final PublisherDirectDownload publisherDirectDownload) {
        if (!isInitialized()) {
            if (loadAdCallback != null) {
                loadAdCallback.onError(str, new VungleException(9));
                return;
            }
            return;
        }
        Placement placement = (Placement) _instance.storage.load(str, Placement.class);
        if (placement == null) {
            if (loadAdCallback != null) {
                loadAdCallback.onError(str, new IllegalArgumentException("Placement ID " + str + " is not valid. Please check your configuration on the vungle dashboard."));
                return;
            }
            return;
        }
        if (Boolean.TRUE.equals(_instance.loadOperations.get(str))) {
            if (loadAdCallback != null) {
                loadAdCallback.onError(str, new VungleException(8));
                return;
            }
            return;
        }
        final Advertisement advertisementFindValidAdvertisementForPlacement = _instance.storage.findValidAdvertisementForPlacement(placement.getId());
        if (canPlayAd(advertisementFindValidAdvertisementForPlacement)) {
            Log.i(TAG, "found already cached valid adv, calling onAdLoad " + str + " callback ");
            if (placement.isAutoCached()) {
                _instance.initCallback.onAutoCacheAdAvailable(str);
            }
            if (loadAdCallback != null) {
                loadAdCallback.onAdLoad(str);
                return;
            }
            return;
        }
        Log.i(TAG, "didn't find cached adv for " + str + " downloading ");
        if (placement.getWakeupTime() > System.currentTimeMillis()) {
            if (loadAdCallback != null) {
                loadAdCallback.onError(str, new VungleException(1));
                Log.w(TAG, "Placement " + placement.getId() + " is  snoozed");
            }
            if (placement.isAutoCached()) {
                Log.d(TAG, "Placement " + placement.getId() + " is sleeping rescheduling it ");
                _instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true).setDelay(placement.getWakeupTime() - System.currentTimeMillis()));
                return;
            }
            return;
        }
        _instance.loadOperations.put(str, true);
        final DownloadCallback downloadCallback = new DownloadCallback() { // from class: com.vungle.warren.Vungle.5
            @Override // com.vungle.warren.Vungle.DownloadCallback
            public void onDownloadCompleted(File file, String str2) {
                Log.d(Vungle.TAG, "download completed " + str);
                Advertisement advertisement = str2 == null ? null : (Advertisement) Vungle._instance.storage.load(str2, Advertisement.class);
                Placement placement2 = (Placement) Vungle._instance.storage.load(str, Placement.class);
                if (advertisement == null || placement2 == null) {
                    onDownloadFailed(new IllegalStateException("Didn't find adv"), str);
                    return;
                }
                Vungle._instance.storage.saveAndApplyState(advertisement, str, 1);
                Log.d("Vungle", String.format(Locale.ENGLISH, "Downloaded assets for %s to %s", str, file.getAbsolutePath()));
                FileUtility.printDirectoryTree(file);
                Vungle._instance.loadOperations.put(str, false);
                if (Vungle._instance.headerBiddingCallback != null) {
                    Vungle._instance.headerBiddingCallback.adAvailableForBidToken(str, advertisement.getBidToken());
                }
                if (placement2.isAutoCached()) {
                    Vungle._instance.initCallback.onAutoCacheAdAvailable(str);
                }
                LoadAdCallback loadAdCallback2 = loadAdCallback;
                if (loadAdCallback2 != null) {
                    loadAdCallback2.onAdLoad(str);
                }
            }

            @Override // com.vungle.warren.Vungle.DownloadCallback
            public void onDownloadFailed(Throwable th, String str2) {
                Advertisement advertisement = str2 == null ? null : (Advertisement) Vungle._instance.storage.load(str2, Advertisement.class);
                Placement placement2 = (Placement) Vungle._instance.storage.load(str, Placement.class);
                if (advertisement != null && placement2 != null) {
                    Vungle._instance.storage.saveAndApplyState(advertisement, str, 4);
                }
                Log.e("Vungle", "Failed to download assets for " + str + ". Cause:", th);
                Vungle._instance.loadOperations.put(str, false);
                LoadAdCallback loadAdCallback2 = loadAdCallback;
                if (loadAdCallback2 != null) {
                    loadAdCallback2.onError(str, th);
                }
            }
        };
        if (advertisementFindValidAdvertisementForPlacement != null && advertisementFindValidAdvertisementForPlacement.getState() == 0) {
            Log.d(TAG, "Found valid adv but not ready - downloading content");
            handler.post(new Runnable() { // from class: com.vungle.warren.Vungle.6
                @Override // java.lang.Runnable
                public void run() {
                    Vungle._instance.downloadAdContent(str, advertisementFindValidAdvertisementForPlacement, publisherDirectDownload, downloadCallback);
                }
            });
            return;
        }
        if (advertisementFindValidAdvertisementForPlacement != null && advertisementFindValidAdvertisementForPlacement.getState() == 1) {
            _instance.storage.saveAndApplyState(advertisementFindValidAdvertisementForPlacement, str, 4);
        }
        Log.d(TAG, "No adv for placement " + placement.getId() + " getting new data ");
        Vungle vungle = _instance;
        vungle.fetchAdMetadata(str, publisherDirectDownload, downloadCallback, vungle.headerBiddingCallback);
    }

    private static void clearCache() {
        if (isInitialized()) {
            _instance.storage.clearAllData();
            Vungle vungle = _instance;
            vungle.configure(vungle.initCallback);
            return;
        }
        Log.e(TAG, "Vungle not initialized");
    }

    private void fetchAdMetadata(final String str, final PublisherDirectDownload publisherDirectDownload, @NonNull final DownloadCallback downloadCallback, final HeaderBiddingCallback headerBiddingCallback) {
        VungleApiClient.requestAd(str, headerBiddingCallback != null).enqueue(new Callback<JsonObject>() { // from class: com.vungle.warren.Vungle.7
            @Override // retrofit2.Callback
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response == null) {
                    downloadCallback.onDownloadFailed(new VungleException(1), null);
                    return;
                }
                if (!response.isSuccessful()) {
                    long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue(response);
                    Placement placement = (Placement) Vungle._instance.storage.load(str, Placement.class);
                    if (retryAfterHeaderValue <= 0 || placement == null || !placement.isAutoCached()) {
                        Log.e(Vungle.TAG, "Failed to retrieve advertisement information");
                        downloadCallback.onDownloadFailed(new VungleException(2), null);
                        return;
                    } else {
                        Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(str, true).setDelay(retryAfterHeaderValue));
                        downloadCallback.onDownloadFailed(new VungleException(14), null);
                        return;
                    }
                }
                Placement placement2 = (Placement) Vungle.this.storage.load(str, Placement.class);
                if (placement2 == null) {
                    Log.e(Vungle.TAG, "Placement metadata not found for requested advertisement.");
                    downloadCallback.onDownloadFailed(new VungleException(2), null);
                    return;
                }
                JsonObject jsonObjectBody = response.body();
                if (jsonObjectBody != null && jsonObjectBody.has("ads") && !jsonObjectBody.get("ads").isJsonNull()) {
                    JsonArray asJsonArray = jsonObjectBody.getAsJsonArray("ads");
                    if (asJsonArray == null || asJsonArray.size() == 0) {
                        downloadCallback.onDownloadFailed(new VungleException(1), null);
                        return;
                    }
                    JsonObject asJsonObject = asJsonArray.get(0).getAsJsonObject();
                    try {
                        Advertisement advertisement = new Advertisement(asJsonObject);
                        if (headerBiddingCallback != null) {
                            headerBiddingCallback.onBidTokenAvailable(str, advertisement.getBidToken());
                        }
                        Vungle._instance.storage.saveAndApplyState(advertisement, str, 0);
                        Vungle.this.downloadAdContent(str, advertisement, publisherDirectDownload, downloadCallback);
                        return;
                    } catch (IllegalArgumentException unused) {
                        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("ad_markup");
                        if (asJsonObject2.has("sleep")) {
                            int asInt = asJsonObject2.get("sleep").getAsInt();
                            placement2.snooze(asInt);
                            Vungle.this.storage.save(placement2);
                            if (placement2.isAutoCached()) {
                                Vungle.this.jobRunner.execute(DownloadJob.makeJobInfo(str, true).setDelay(asInt * 1000));
                            }
                        }
                        downloadCallback.onDownloadFailed(new VungleException(1), null);
                        return;
                    }
                }
                downloadCallback.onDownloadFailed(new VungleError(0), null);
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonObject> call, Throwable th) {
                downloadCallback.onDownloadFailed(th, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadAdContent(final String str, final Advertisement advertisement, PublisherDirectDownload publisherDirectDownload, @NonNull final DownloadCallback downloadCallback) {
        DirectDownloadStrategy directDownloadStrategy = (publisherDirectDownload == null || TextUtils.isEmpty(advertisement.getAdMarketId())) ? null : new DirectDownloadStrategy(publisherDirectDownload);
        if (directDownloadStrategy != null) {
            directDownloadStrategy.isApplicationAvailable(advertisement.getAdMarketId(), new DownloadStrategy.VerificationCallback() { // from class: com.vungle.warren.Vungle.8
                @Override // com.vungle.warren.DownloadStrategy.VerificationCallback
                public void onResult(boolean z) {
                    if (z) {
                        Log.d(Vungle.TAG, "fetchAdMetadata: downloading assets ");
                        Vungle.this.downloadAdAssets(advertisement, downloadCallback, str);
                    } else {
                        downloadCallback.onDownloadFailed(new VungleException(5), null);
                    }
                }
            });
        } else {
            downloadAdAssets(advertisement, downloadCallback, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadAdAssets(final Advertisement advertisement, final DownloadCallback downloadCallback, final String str) {
        for (Map.Entry<String, String> entry : advertisement.getDownloadableUrls().entrySet()) {
            if (TextUtils.isEmpty(entry.getKey()) || TextUtils.isEmpty(entry.getValue()) || !URLUtil.isValidUrl(entry.getValue())) {
                downloadCallback.onDownloadFailed(new VungleException(11), null);
                Log.e(TAG, "Aborting, Failed to download Ad assets for: " + advertisement.getId());
                return;
            }
        }
        File advertisementAssetDirectory = this.storage.getAdvertisementAssetDirectory(advertisement.getId());
        final int size = advertisement.getDownloadableUrls().size();
        Downloader.Listener listener = new Downloader.Listener() { // from class: com.vungle.warren.Vungle.9
            private AtomicInteger downloadsComplete = new AtomicInteger(0);

            @Override // com.vungle.warren.network.Downloader.Listener
            public void onProgress(int i) {
            }

            @Override // com.vungle.warren.network.Downloader.Listener
            public void onComplete(File file) {
                if (this.downloadsComplete.incrementAndGet() == size) {
                    if (advertisement.getAdType() == 1) {
                        Log.d(Vungle.TAG, "saving MRAID for " + advertisement.getId());
                        advertisement.setMraidAssetDir(file);
                        Vungle._instance.storage.save(advertisement);
                    }
                    downloadCallback.onDownloadCompleted(file.getParentFile(), advertisement.getId());
                }
            }

            @Override // com.vungle.warren.network.Downloader.Listener
            public void onError(Throwable th) {
                Log.e(Vungle.TAG, "Download Failed");
                Vungle._instance.storage.saveAndApplyState(advertisement, str, 4);
                downloadCallback.onDownloadFailed(th, advertisement.getId());
            }
        };
        try {
            for (Map.Entry<String, String> entry2 : advertisement.getDownloadableUrls().entrySet()) {
                File file = new File(advertisementAssetDirectory.getPath() + File.separator + entry2.getKey());
                if (URLUtil.isHttpsUrl(entry2.getValue()) || URLUtil.isHttpUrl(entry2.getValue())) {
                    this.downloader.download(entry2.getValue(), file, listener);
                } else {
                    downloadCallback.onDownloadFailed(new VungleError(10), advertisement.getId());
                }
            }
        } catch (IOException e) {
            downloadCallback.onDownloadFailed(new VungleError(8), advertisement.getId());
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (IllegalStateException e2) {
            downloadCallback.onDownloadFailed(new VungleError(8), advertisement.getId());
            Log.e(TAG, Log.getStackTraceString(e2));
        }
    }

    public static VungleNativeAd getNativeAd(String str, PlayAdCallback playAdCallback) {
        return getNativeAd(str, playAdCallback, null);
    }

    public static VungleNativeAd getNativeAd(final String str, final PlayAdCallback playAdCallback, PublisherDirectDownload publisherDirectDownload) {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized, returned VungleNativeAd = null");
            return null;
        }
        Placement placement = (Placement) _instance.storage.load(str, Placement.class);
        if (placement == null) {
            playAdCallback.onError(str, new Throwable("No placement for ID " + str + " found. Please use a valid placement ID"));
            return null;
        }
        final Advertisement advertisementFindValidAdvertisementForPlacement = _instance.storage.findValidAdvertisementForPlacement(str);
        if (advertisementFindValidAdvertisementForPlacement == null) {
            Log.e(TAG, "No Advertisement for ID");
            return null;
        }
        if (!canPlayAd(advertisementFindValidAdvertisementForPlacement)) {
            if (advertisementFindValidAdvertisementForPlacement != null && advertisementFindValidAdvertisementForPlacement.getState() == 1) {
                _instance.storage.saveAndApplyState(advertisementFindValidAdvertisementForPlacement, str, 4);
                if (placement.isAutoCached()) {
                    _instance.jobRunner.execute(DownloadJob.makeJobInfo(placement.getId(), true));
                }
            }
            return null;
        }
        if (Boolean.TRUE.equals(_instance.playOperations.get(str)) || Boolean.TRUE.equals(_instance.loadOperations.get(str))) {
            playAdCallback.onError(str, new VungleException(8));
            return null;
        }
        if (advertisementFindValidAdvertisementForPlacement.getAdType() != 1) {
            playAdCallback.onError(str, new Throwable(str + " is not an MRAID compatible placement. Please use a valid placement ID"));
            return null;
        }
        DirectDownloadAdapter directDownloadAdapter = publisherDirectDownload != null ? new DirectDownloadAdapter(publisherDirectDownload, advertisementFindValidAdvertisementForPlacement.getAdMarketId()) : null;
        _instance.playOperations.put(str, true);
        return new VungleNativeView(_instance.context.getApplicationContext(), str, directDownloadAdapter, new AdvertisementPresenter.EventListener() { // from class: com.vungle.warren.Vungle.10
            boolean succesfulView = false;
            int percentViewed = -1;

            @Override // com.vungle.warren.presenter.AdvertisementPresenter.EventListener
            public void onNext(String str2, String str3) {
                boolean z = false;
                if (str2.equals("start")) {
                    Vungle._instance.storage.saveAndApplyState(advertisementFindValidAdvertisementForPlacement, str, 2);
                    PlayAdCallback playAdCallback2 = playAdCallback;
                    if (playAdCallback2 != null) {
                        playAdCallback2.onAdStart(str);
                    }
                    this.percentViewed = 0;
                    Placement placement2 = (Placement) Vungle._instance.storage.load(str, Placement.class);
                    if (placement2 == null || !placement2.isAutoCached()) {
                        return;
                    }
                    Vungle._instance.jobRunner.execute(DownloadJob.makeJobInfo(str, true));
                    return;
                }
                if (str2.equals(TtmlNode.END)) {
                    Log.d("Vungle", "Cleaning up metadata and assets for placement " + str + " and advertisement " + advertisementFindValidAdvertisementForPlacement.getId());
                    Vungle._instance.storage.saveAndApplyState(advertisementFindValidAdvertisementForPlacement, str, 3);
                    Vungle._instance.jobRunner.execute(SendReportsJob.makeJobInfo());
                    Vungle._instance.playOperations.put(str, false);
                    PlayAdCallback playAdCallback3 = playAdCallback;
                    if (playAdCallback3 != null) {
                        String str4 = str;
                        boolean z2 = this.succesfulView || this.percentViewed >= 80;
                        if (str3 != null && str3.equals("isCTAClicked")) {
                            z = true;
                        }
                        playAdCallback3.onAdEnd(str4, z2, z);
                        return;
                    }
                    return;
                }
                if (str2.equals("successfulView")) {
                    this.succesfulView = true;
                } else if (str2.startsWith("percentViewed")) {
                    String[] strArrSplit = str2.split(":");
                    if (strArrSplit.length == 2) {
                        this.percentViewed = Integer.parseInt(strArrSplit[1]);
                    }
                }
            }

            @Override // com.vungle.warren.presenter.AdvertisementPresenter.EventListener
            public void onError(Throwable th) {
                Vungle._instance.playOperations.put(str, false);
                Vungle._instance.storage.saveAndApplyState(advertisementFindValidAdvertisementForPlacement, str, 4);
                PlayAdCallback playAdCallback2 = playAdCallback;
                if (playAdCallback2 != null) {
                    playAdCallback2.onError(str, th);
                }
            }
        });
    }

    public static Collection<String> getValidPlacements() {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized return empty placemetns list");
            return Collections.emptyList();
        }
        return _instance.storage.getValidPlacements();
    }

    static void handleApkDirectDownloads(Context context) {
        APKDirectDownloadManager.handleDownload(context);
    }

    public static void updateConsentStatus(@NonNull Consent consent, @NonNull String str) {
        if (!isDepInit.get()) {
            Vungle vungle = _instance;
            vungle.tempConsent = consent;
            vungle.tempConsentVersion = str;
            return;
        }
        Cookie cookie = (Cookie) _instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie == null) {
            cookie = new Cookie(Cookie.CONSENT_COOKIE);
        }
        cookie.putValue("consent_status", consent == Consent.OPTED_IN ? "opted_in" : "opted_out");
        cookie.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
        cookie.putValue("consent_source", "publisher");
        if (str == null) {
            str = "";
        }
        cookie.putValue("consent_message_version", str);
        _instance.storage.save(cookie);
        Vungle vungle2 = _instance;
        vungle2.tempConsent = null;
        vungle2.tempConsentVersion = null;
    }

    public static Consent getConsentStatus() {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized, consent is null");
            return null;
        }
        Cookie cookie = (Cookie) _instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie == null) {
            return null;
        }
        return "opted_in".equals(cookie.getString("consent_status")) ? Consent.OPTED_IN : Consent.OPTED_OUT;
    }

    public static String getConsentMessageVersion() {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized, please wait initialize or wait until Vungle is intialized to get Consent Message Version");
            return null;
        }
        Cookie cookie = (Cookie) _instance.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie == null) {
            return null;
        }
        return cookie.getString("consent_message_version");
    }

    public static boolean closeFlexViewAd(@NonNull String str) {
        if (!isInitialized()) {
            Log.e(TAG, "Vungle is not initialized, can't close flex view ad");
            return false;
        }
        Intent intent = new Intent("AdvertisementBus");
        intent.putExtra("placement", str);
        intent.putExtra("command", "closeFlex");
        _instance.context.sendBroadcast(intent);
        return true;
    }

    public static void setUserLegacyID(String str) {
        if (isInitialized() || isInitializing.get()) {
            VungleApiClient.updateIMEI(str, _instance.shouldTransmitIMEI);
        } else {
            _instance.userIMEI = str;
        }
    }

    public static void setHeaderBiddingCallback(HeaderBiddingCallback headerBiddingCallback) {
        _instance.headerBiddingCallback = headerBiddingCallback;
    }
}
