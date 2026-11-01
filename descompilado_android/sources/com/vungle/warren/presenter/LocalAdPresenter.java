package com.vungle.warren.presenter;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moat.analytics.mobile.vng.MoatAdEvent;
import com.moat.analytics.mobile.vng.MoatAdEventType;
import com.moat.analytics.mobile.vng.MoatFactory;
import com.moat.analytics.mobile.vng.MoatOptions;
import com.moat.analytics.mobile.vng.ReactiveVideoTracker;
import com.moat.analytics.mobile.vng.ReactiveVideoTrackerPlugin;
import com.my.target.bj;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.Storage;
import com.vungle.warren.download.APKDirectDownloadManager;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.Placement;
import com.vungle.warren.model.Report;
import com.vungle.warren.network.VungleApiClient;
import com.vungle.warren.ui.AdView;
import com.vungle.warren.ui.VungleWebClient;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import net.nend.android.NendAdNativeMediaView;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX INFO: loaded from: classes2.dex */
public class LocalAdPresenter implements AdvertisementPresenter {
    private static final String EXTRA_INCENTIVIZED_SENT = "incentivized_sent";
    private static final String EXTRA_IN_POST = "in_post_roll";
    private static final String EXTRA_REPORT = "saved_report";
    private static final String TAG = "LocalAdPresenter";
    private HashMap<String, String> adIds;
    private AdView adView;
    private Advertisement advertisement;
    private File assetDir;
    private AdvertisementPresenter.EventListener bus;
    private Advertisement.Checkpoint checkpoint;
    private byte checkpointReached;
    private boolean directDownloadApkEnabled;
    private int duration;
    private boolean inPost;
    private Queue<Pair<Integer, MoatAdEventType>> moatQuartileTrackers;
    private boolean muted;
    private Placement placement;
    private Report report;
    private Storage storage;
    private boolean userExitEnabled;
    private String userID;
    private ReactiveVideoTracker videoTracker;
    private VideoView videoView;
    private VungleWebClient webClient;
    private Handler handler = new Handler(Looper.getMainLooper());
    private String dialogTitle = "Are you sure?";
    private String dialogBody = "If you exit now, you will not get your reward";
    private String dialogContinue = "Continue";
    private String dialogClose = "Close";
    private AtomicBoolean sendReportIncentivized = new AtomicBoolean(false);
    private AtomicBoolean isDestroying = new AtomicBoolean(false);

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void notifyPropertiesChanged() {
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void setAdVisibility(boolean z) {
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void setDirectDownloadAdapter(DirectDownloadAdapter directDownloadAdapter) {
    }

    public LocalAdPresenter(Advertisement advertisement, Placement placement, Storage storage, File file, String str) {
        this.advertisement = advertisement;
        this.placement = placement;
        this.storage = storage;
        this.userID = str;
        this.assetDir = file;
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void setEventListener(AdvertisementPresenter.EventListener eventListener) {
        this.bus = eventListener;
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void reportError(String str) {
        this.report.recordError(str);
        this.storage.save(this.report);
        if (!this.inPost && this.advertisement.hasPostroll()) {
            playPost();
            return;
        }
        AdvertisementPresenter.EventListener eventListener = this.bus;
        if (eventListener != null) {
            eventListener.onError(new Throwable(str));
        }
        this.adView.close();
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void reportAction(String str, @Nullable String str2) {
        if (str.equals("videoLength")) {
            this.duration = Integer.parseInt(str2);
            this.report.setAdDuration(this.duration);
            this.storage.save(this.report);
            return;
        }
        if (str.equals(bj.gv)) {
            for (String str3 : this.advertisement.getTpatUrls(bj.gv)) {
                VungleApiClient.pingTPAT(str3);
            }
        }
        if (str.equals(bj.gw)) {
            for (String str4 : this.advertisement.getTpatUrls(bj.gw)) {
                VungleApiClient.pingTPAT(str4);
            }
        }
        if (str.equals("video_close")) {
            for (String str5 : this.advertisement.getTpatUrls("video_close")) {
                VungleApiClient.pingTPAT(str5);
            }
        }
        this.report.recordAction(str, str2, System.currentTimeMillis());
        this.storage.save(this.report);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public WebViewClient getWebViewClient() {
        if (this.webClient == null) {
            this.webClient = new VungleWebClient(this.advertisement, this.placement, null);
        }
        return this.webClient;
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void initializeViewabilityTracker(int i, VideoView videoView) {
        if (!this.advertisement.getMoatEnabled() || !VungleApiClient.getMoatEnabled() || this.videoTracker == null || this.adIds == null) {
            return;
        }
        this.videoView = videoView;
        Log.d(TAG, "initializeViewabilityTracker");
        this.videoTracker.trackVideoAd(this.adIds, Integer.valueOf(i), videoView);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void stopViewabilityTracker() {
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && this.videoTracker != null) {
            VideoView videoView = this.videoView;
            int currentPosition = videoView != null ? videoView.getCurrentPosition() : 0;
            Log.d(TAG, "stopViewabilityTracker: " + currentPosition);
            this.videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_STOPPED, Integer.valueOf(currentPosition)));
            this.videoTracker.stopTracking();
            Log.d(TAG, "stopViewabilityTracker: Success !!");
        }
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void attach(AdView adView) {
        this.isDestroying.set(false);
        this.adView = adView;
        int settings = this.advertisement.getAdConfig().getSettings();
        if (settings > 0) {
            this.muted = (settings & 1) == 1;
            this.userExitEnabled = (settings & 2) == 2;
            this.directDownloadApkEnabled = (settings & 32) == 32;
        }
        int i = 4;
        if ((this.advertisement.getAdConfig().getSettings() & 16) != 16) {
            int orientation = this.advertisement.getOrientation();
            if (orientation == 0) {
                i = 1;
            } else if (orientation == 1) {
                i = 0;
            } else if (orientation != 2) {
                i = -1;
            }
        }
        Log.d(TAG, "requested orientation " + i);
        adView.setOrientation(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playPost() {
        if (isPostRollPresent()) {
            File file = new File(new File(this.assetDir.getPath() + File.separator + Advertisement.POSTROLL_UNZIP).getPath() + File.separator + "index.html");
            if (!file.exists()) {
                AdvertisementPresenter.EventListener eventListener = this.bus;
                if (eventListener != null) {
                    eventListener.onError(new VungleException(10));
                }
                this.adView.close();
                return;
            }
            this.adView.showWebsite("file://" + file.getPath());
        }
        for (String str : this.advertisement.getTpatUrls("postroll_view")) {
            VungleApiClient.pingTPAT(str);
        }
        this.inPost = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPostRollPresent() {
        return new File(this.assetDir.getPath() + File.separator + Advertisement.POSTROLL_UNZIP).exists();
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void prepare(Bundle bundle) {
        String id;
        AdvertisementPresenter.EventListener eventListener = this.bus;
        String id2 = null;
        if (eventListener != null) {
            eventListener.onNext("start", null);
        }
        Cookie cookie = (Cookie) this.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
        this.report = new Report(this.advertisement, this.placement, System.currentTimeMillis(), cookie == null ? null : cookie.getString("userID"));
        if (this.advertisement.getCheckpoints() != null && !this.advertisement.getCheckpoints().isEmpty()) {
            this.checkpoint = this.advertisement.getCheckpoints().get(0);
        }
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
            MoatOptions moatOptions = new MoatOptions();
            moatOptions.disableAdIdCollection = true;
            moatOptions.disableLocationServices = true;
            moatOptions.loggingEnabled = false;
            this.moatQuartileTrackers = new LinkedList();
            this.moatQuartileTrackers.add(new Pair<>(0, MoatAdEventType.AD_EVT_START));
            this.moatQuartileTrackers.add(new Pair<>(25, MoatAdEventType.AD_EVT_FIRST_QUARTILE));
            this.moatQuartileTrackers.add(new Pair<>(50, MoatAdEventType.AD_EVT_MID_POINT));
            this.moatQuartileTrackers.add(new Pair<>(75, MoatAdEventType.AD_EVT_THIRD_QUARTILE));
            this.adIds = new HashMap<>();
            if (!this.advertisement.getMoatVastExtra().isEmpty()) {
                this.adIds.put("zMoatVASTIDs", this.advertisement.getMoatVastExtra());
            }
            String appID = this.advertisement.getAppID();
            String appID2 = this.advertisement.getAppID();
            if (appID2 != null) {
                try {
                    JSONObject jSONObject = new JSONObject(appID2.substring(3));
                    appID = jSONObject.isNull("app_id") ? null : jSONObject.optString("app_id", null);
                } catch (JSONException e) {
                    Log.e(TAG, "JsonException : ", e);
                }
            }
            String campaign = this.advertisement.getCampaign();
            int iIndexOf = campaign.indexOf(MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES);
            if (iIndexOf != -1) {
                int i = iIndexOf + 1;
                int iIndexOf2 = campaign.indexOf(MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES, i);
                id = campaign.substring(0, iIndexOf);
                if (iIndexOf2 != -1) {
                    id2 = campaign.substring(i, iIndexOf2);
                }
            } else {
                id = null;
            }
            HashMap<String, String> map = this.adIds;
            if (appID == null || appID.isEmpty()) {
                appID = this.advertisement.getId();
            }
            map.put("level1", appID);
            HashMap<String, String> map2 = this.adIds;
            if (id == null || id.isEmpty()) {
                id = this.advertisement.getId();
            }
            map2.put("level2", id);
            HashMap<String, String> map3 = this.adIds;
            if (id2 == null || id2.isEmpty()) {
                id2 = this.advertisement.getId();
            }
            map3.put("level3", id2);
            this.adIds.put("level4", this.placement.getId());
            Cookie cookie2 = (Cookie) this.storage.load(Cookie.APP_ID, Cookie.class);
            if (cookie2 != null && !TextUtils.isEmpty(cookie2.getString(Cookie.APP_ID))) {
                this.adIds.put("slicer1", cookie2.getString(Cookie.APP_ID));
            }
            this.videoTracker = (ReactiveVideoTracker) MoatFactory.create().createCustomTracker(new ReactiveVideoTrackerPlugin("vunglenativevideo893259554489"));
        }
        this.adView.updateWindow(false);
        if (this.advertisement.isCtaOverlayEnabled()) {
            if (this.advertisement.isCtaShowOnClick()) {
                this.adView.showCTAOverlay(this.advertisement.isCtaShowOnClick(), true, this.advertisement.getCtaClickArea());
            } else {
                this.handler.postDelayed(new Runnable() { // from class: com.vungle.warren.presenter.LocalAdPresenter.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LocalAdPresenter.this.adView.showCTAOverlay(LocalAdPresenter.this.advertisement.isCtaShowOnClick(), false, LocalAdPresenter.this.advertisement.getCtaClickArea());
                    }
                }, this.advertisement.getCtaTimeShow());
                this.handler.postDelayed(new Runnable() { // from class: com.vungle.warren.presenter.LocalAdPresenter.2
                    @Override // java.lang.Runnable
                    public void run() {
                        LocalAdPresenter.this.adView.showCTAOverlay(LocalAdPresenter.this.advertisement.isCtaShowOnClick(), true, LocalAdPresenter.this.advertisement.getCtaClickArea());
                    }
                }, this.advertisement.getCtaTimeEnabled());
            }
        }
        final Cookie cookie3 = (Cookie) this.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie3 != null && cookie3.getBoolean("is_country_data_protected").booleanValue() && "unknown".equals(cookie3.getString("consent_status"))) {
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.vungle.warren.presenter.LocalAdPresenter.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    String str;
                    if (i2 != -2) {
                        str = i2 != -1 ? "opted_out_by_timeout" : "opted_in";
                    } else {
                        str = "opted_out";
                    }
                    cookie3.putValue("consent_status", str);
                    cookie3.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
                    cookie3.putValue("consent_source", "vungle_modal");
                    LocalAdPresenter.this.storage.save(cookie3);
                    LocalAdPresenter.this.play();
                }
            };
            cookie3.putValue("consent_status", "opted_out_by_timeout");
            cookie3.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
            cookie3.putValue("consent_source", "vungle_modal");
            this.storage.save(cookie3);
            this.adView.showDialog(cookie3.getString("consent_title"), cookie3.getString("consent_message"), cookie3.getString("button_accept"), cookie3.getString("button_deny"), onClickListener);
            return;
        }
        play();
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public boolean handleExit(String str) {
        if (this.inPost) {
            closeAndReport();
            return true;
        }
        if (!this.userExitEnabled) {
            return false;
        }
        if (this.placement.isIncentivized()) {
            Cookie cookie = (Cookie) this.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
            String string = this.dialogTitle;
            String string2 = this.dialogBody;
            String string3 = this.dialogContinue;
            String string4 = this.dialogClose;
            if (cookie != null) {
                string = cookie.getString("title") == null ? this.dialogTitle : cookie.getString("title");
                string2 = cookie.getString(TtmlNode.TAG_BODY) == null ? this.dialogBody : cookie.getString(TtmlNode.TAG_BODY);
                string3 = cookie.getString("continue") == null ? this.dialogContinue : cookie.getString("continue");
                string4 = cookie.getString("close") == null ? this.dialogClose : cookie.getString("close");
            }
            this.adView.showDialog(string, string2, string3, string4, new DialogInterface.OnClickListener() { // from class: com.vungle.warren.presenter.LocalAdPresenter.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == -2) {
                        LocalAdPresenter.this.reportAction("video_close", null);
                        if (LocalAdPresenter.this.advertisement.hasPostroll()) {
                            if (LocalAdPresenter.this.isPostRollPresent()) {
                                LocalAdPresenter.this.playPost();
                                return;
                            } else {
                                LocalAdPresenter.this.closeAndReport();
                                return;
                            }
                        }
                        LocalAdPresenter.this.closeAndReport();
                    }
                }
            });
            return false;
        }
        reportAction("video_close", null);
        if (this.advertisement.hasPostroll()) {
            if (isPostRollPresent()) {
                playPost();
            }
            return false;
        }
        closeAndReport();
        return true;
    }

    private boolean isWebPageBlank() {
        String websiteUrl = this.adView.getWebsiteUrl();
        return TextUtils.isEmpty(websiteUrl) || "about:blank".equalsIgnoreCase(websiteUrl);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void play() {
        if (this.inPost) {
            if (isWebPageBlank()) {
                playPost();
                return;
            }
            return;
        }
        this.adView.playVideo(Uri.fromFile(new File(this.assetDir.getPath() + File.separator + "video")), this.muted);
        int showCloseDelay = this.advertisement.getShowCloseDelay(this.placement.isIncentivized());
        if (showCloseDelay > 0) {
            this.handler.postDelayed(new Runnable() { // from class: com.vungle.warren.presenter.LocalAdPresenter.5
                @Override // java.lang.Runnable
                public void run() {
                    LocalAdPresenter.this.userExitEnabled = true;
                    if (LocalAdPresenter.this.inPost) {
                        return;
                    }
                    LocalAdPresenter.this.adView.showCloseButton();
                }
            }, showCloseDelay);
        } else {
            this.userExitEnabled = true;
            this.adView.showCloseButton();
        }
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void stop(boolean z, boolean z2) {
        if (!z && z2) {
            if (this.isDestroying.getAndSet(true)) {
                return;
            }
            reportAction("close", null);
            this.handler.removeCallbacksAndMessages(null);
            AdvertisementPresenter.EventListener eventListener = this.bus;
            if (eventListener != null) {
                eventListener.onNext(TtmlNode.END, this.report.isCTAClicked() ? "isCTAClicked" : null);
            }
            this.adView.close();
            return;
        }
        if (this.inPost || z2) {
            this.adView.showWebsite("about:blank");
        }
    }

    public void reportMute(boolean z) {
        if (z) {
            if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
                this.videoTracker.setPlayerVolume(MoatAdEvent.VOLUME_UNMUTED);
                return;
            }
            return;
        }
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
            this.videoTracker.setPlayerVolume(MoatAdEvent.VOLUME_MUTED);
        }
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void onProgressUpdate(int i) {
        AdvertisementPresenter.EventListener eventListener = this.bus;
        if (eventListener != null) {
            eventListener.onNext("percentViewed:" + i, null);
        }
        List<Advertisement.Checkpoint> checkpoints = this.advertisement.getCheckpoints();
        if (i == 100) {
            int size = checkpoints.size() - 1;
            if (size > 0) {
                Advertisement.Checkpoint checkpoint = checkpoints.get(size);
                if (checkpoint.getPercentage() == 100) {
                    for (String str : checkpoint.getUrls()) {
                        VungleApiClient.pingTPAT(str);
                    }
                }
            }
            if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled()) {
                if (this.videoView != null) {
                    this.videoTracker.dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE, Integer.valueOf(this.videoView.getCurrentPosition())));
                }
                this.videoTracker.stopTracking();
            }
            if (this.advertisement.hasPostroll()) {
                if (isPostRollPresent()) {
                    playPost();
                } else {
                    closeAndReport();
                }
            }
        }
        this.report.recordProgress((int) (this.duration * (i / 100.0f)));
        this.storage.save(this.report);
        Advertisement.Checkpoint checkpoint2 = this.checkpoint;
        if (checkpoint2 != null && i > checkpoint2.getPercentage()) {
            for (String str2 : this.checkpoint.getUrls()) {
                VungleApiClient.pingTPAT(str2);
            }
            this.checkpoint = null;
            if (this.checkpointReached < checkpoints.size() - 1) {
                byte b = (byte) (this.checkpointReached + 1);
                this.checkpointReached = b;
                this.checkpoint = checkpoints.get(b);
            }
        }
        if (this.advertisement.getMoatEnabled() && VungleApiClient.getMoatEnabled() && !this.moatQuartileTrackers.isEmpty() && i >= ((Integer) this.moatQuartileTrackers.peek().first).intValue()) {
            this.videoTracker.dispatchEvent(new MoatAdEvent((MoatAdEventType) this.moatQuartileTrackers.poll().second, Integer.valueOf(i)));
        }
        Cookie cookie = (Cookie) this.storage.load(Cookie.CONFIG_COOKIE, Cookie.class);
        if (!this.placement.isIncentivized() || i <= 75 || cookie == null || !cookie.getBoolean("isReportIncentivizedEnabled").booleanValue() || this.sendReportIncentivized.getAndSet(true)) {
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("placement_reference_id", new JsonPrimitive(this.placement.getId()));
        jsonObject.add("app_id", new JsonPrimitive(this.advertisement.getAppID()));
        jsonObject.add("adStartTime", new JsonPrimitive((Number) Long.valueOf(this.report.getAdStartTime())));
        jsonObject.add("user", new JsonPrimitive(this.report.getUserID()));
        VungleApiClient.ri(jsonObject).enqueue(new Callback<JsonObject>() { // from class: com.vungle.warren.presenter.LocalAdPresenter.6
            @Override // retrofit2.Callback
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(LocalAdPresenter.TAG, "send RI success");
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<JsonObject> call, Throwable th) {
                Log.d(LocalAdPresenter.TAG, "send RI Failure");
            }
        });
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void generateSaveState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.storage.save(this.report);
        bundle.putString(EXTRA_REPORT, this.report.getId());
        bundle.putBoolean(EXTRA_INCENTIVIZED_SENT, this.sendReportIncentivized.get());
        bundle.putBoolean(EXTRA_IN_POST, this.inPost);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void restoreFromSave(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.getBoolean(EXTRA_INCENTIVIZED_SENT, false)) {
            this.sendReportIncentivized.set(true);
        }
        String string = bundle.getString(EXTRA_REPORT);
        this.report = TextUtils.isEmpty(string) ? null : (Report) this.storage.load(string, Report.class);
        if (this.report == null) {
            this.adView.close();
        } else {
            this.inPost = bundle.getBoolean(EXTRA_IN_POST, false);
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0035  */
    @Override // com.vungle.warren.ui.JavascriptBridge.ActionHandler
    public void handleAction(String str) {
        byte b;
        int iHashCode = str.hashCode();
        if (iHashCode != -314498168) {
            if (iHashCode != 94756344) {
                if (iHashCode == 1427818632 && str.equals("download")) {
                    b = 1;
                } else {
                    b = -1;
                }
            } else if (str.equals("close")) {
                b = 0;
            } else {
                b = -1;
            }
        } else if (str.equals(ShareConstants.WEB_DIALOG_PARAM_PRIVACY)) {
            b = 2;
        } else {
            b = -1;
        }
        if (b == 0) {
            closeAndReport();
            return;
        }
        if (b != 1) {
            if (b == 2) {
                return;
            }
            throw new IllegalArgumentException("Unknown action " + str);
        }
        try {
            for (String str2 : this.advertisement.getTpatUrls("postroll_click")) {
                VungleApiClient.pingTPAT(str2);
            }
            for (String str3 : this.advertisement.getTpatUrls(NendAdNativeMediaView.RESULT_CLICK_URL)) {
                VungleApiClient.pingTPAT(str3);
            }
            reportAction("download", null);
            closeAndReport();
            String ctaurl = this.advertisement.getCTAURL(false);
            String ctaurl2 = this.advertisement.getCTAURL(true);
            if (!TextUtils.isEmpty(ctaurl2)) {
                VungleApiClient.pingTPAT(ctaurl2);
            }
            if (APKDirectDownloadManager.isDirectDownloadEnabled(this.directDownloadApkEnabled, this.advertisement.isRequiresNonMarketInstall())) {
                APKDirectDownloadManager.download(ctaurl);
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(ctaurl));
            this.adView.open(intent.toUri(0));
        } catch (ActivityNotFoundException unused) {
            this.handler.removeCallbacksAndMessages(null);
            this.adView.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeAndReport() {
        reportAction("close", null);
        this.handler.removeCallbacksAndMessages(null);
        this.adView.close();
    }
}
