package com.vungle.warren.presenter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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
import com.vungle.warren.utility.UnzipUtility;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX INFO: loaded from: classes2.dex */
public class WebAdPresenter implements AdvertisementPresenter, VungleWebClient.MRAIDDelegate {
    private static String EXTRA_CONTENT_PREPARED = "content_prepared";
    private static final String EXTRA_INCENTIVIZED_SENT = "incentivized_sent";
    private static String EXTRA_REPORT = "saved_report";
    private static final String EXTRA_WEB_READY = "web_ready";
    protected static final double NINE_BY_SIXTEEN_ASPECT_RATIO = 0.5625d;
    private static final String TAG = "com.vungle.warren.presenter.WebAdPresenter";
    private AdView adView;
    private Advertisement advertisement;
    private File assetDir;
    private boolean backEnabled;
    private AdvertisementPresenter.EventListener bus;
    private DirectDownloadAdapter directDownloadAdapter;
    private boolean directDownloadApkEnabled;
    private float duration;
    private boolean muted;
    private Placement placement;
    private Report report;
    private Storage storage;
    private String userID;
    private VungleWebClient webClient;
    private Handler handler = new Handler(Looper.getMainLooper());
    private boolean hasSendStart = false;
    private boolean hasSend80Percent = false;
    private AtomicBoolean sendReportIncentivized = new AtomicBoolean(false);
    private boolean prepared = false;
    private AtomicBoolean isDestroying = new AtomicBoolean(false);

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void initializeViewabilityTracker(int i, VideoView videoView) {
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void stopViewabilityTracker() {
    }

    private WebAdPresenter() throws IllegalAccessException {
        throw new IllegalAccessException("Use the parametrized constructor for creating a WebAdPresenter!");
    }

    public WebAdPresenter(Advertisement advertisement, Placement placement, Storage storage, File file, String str) {
        this.advertisement = advertisement;
        this.storage = storage;
        this.assetDir = file;
        this.placement = placement;
        this.userID = str;
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void setEventListener(AdvertisementPresenter.EventListener eventListener) {
        this.bus = eventListener;
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void setDirectDownloadAdapter(DirectDownloadAdapter directDownloadAdapter) {
        this.directDownloadAdapter = directDownloadAdapter;
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void reportError(String str) {
        this.report.recordError(str);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void reportAction(String str, @Nullable String str2) {
        this.report.recordAction(str, str2, System.currentTimeMillis());
        this.storage.save(this.report);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public WebViewClient getWebViewClient() {
        return this.webClient;
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void notifyPropertiesChanged() {
        this.adView.updateWindow(this.advertisement.getTemplateType().equals("flexview"));
        this.webClient.notifyPropertiesChange(true);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void attach(AdView adView) {
        this.isDestroying.set(false);
        this.adView = adView;
        int settings = this.advertisement.getAdConfig().getSettings();
        if (settings > 0) {
            this.muted = (settings & 1) == 1;
            this.backEnabled = (settings & 2) == 2;
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
        adView.setOrientation(i);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void prepare(Bundle bundle) throws Throwable {
        AdvertisementPresenter.EventListener eventListener = this.bus;
        if (eventListener != null) {
            eventListener.onNext("start", null);
        }
        boolean z = false;
        if (bundle != null) {
            this.prepared = bundle.getBoolean(EXTRA_CONTENT_PREPARED, false);
        }
        loadMraid(new File(this.assetDir.getPath() + File.separator + "template"));
        Cookie cookie = (Cookie) this.storage.load(Cookie.INCENTIVIZED_TEXT_COOKIE, Cookie.class);
        if ("flexview".equals(this.advertisement.getTemplateType()) && this.advertisement.getAdConfig().getFlexViewCloseTime() > 0) {
            this.handler.postDelayed(new Runnable() { // from class: com.vungle.warren.presenter.WebAdPresenter.1
                @Override // java.lang.Runnable
                public void run() {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    WebAdPresenter.this.report.recordAction("mraidCloseByTimer", "", jCurrentTimeMillis);
                    WebAdPresenter.this.report.recordAction("mraidClose", "", jCurrentTimeMillis);
                    WebAdPresenter.this.storage.save(WebAdPresenter.this.report);
                    WebAdPresenter.this.closeView();
                }
            }, this.advertisement.getAdConfig().getFlexViewCloseTime() * 1000);
        }
        this.report = new Report(this.advertisement, this.placement, System.currentTimeMillis(), cookie != null ? cookie.getString("userID") : null);
        this.webClient = new VungleWebClient(this.advertisement, this.placement, this.directDownloadAdapter);
        this.webClient.setMRAIDDelegate(this);
        Cookie cookie2 = (Cookie) this.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
        if (cookie2 != null) {
            if (cookie2.getBoolean("is_country_data_protected").booleanValue() && "unknown".equals(cookie2.getString("consent_status"))) {
                z = true;
            }
            this.webClient.setConsentStatus(z, cookie2.getString("consent_title"), cookie2.getString("consent_message"), cookie2.getString("button_accept"), cookie2.getString("button_deny"));
            if (z) {
                cookie2.putValue("consent_status", "opted_out_by_timeout");
                this.storage.save(cookie2);
            }
        }
        int showCloseDelay = this.advertisement.getShowCloseDelay(this.placement.isIncentivized());
        if (showCloseDelay > 0) {
            this.handler.postDelayed(new Runnable() { // from class: com.vungle.warren.presenter.WebAdPresenter.2
                @Override // java.lang.Runnable
                public void run() {
                    WebAdPresenter.this.backEnabled = true;
                }
            }, showCloseDelay);
        } else {
            this.backEnabled = true;
        }
        this.adView.updateWindow(this.advertisement.getTemplateType().equals("flexview"));
    }

    private void loadMraid(File file) throws Throwable {
        File file2 = new File(file.getParent() + File.separator + "templateUnzip");
        if (!this.prepared) {
            try {
                UnzipUtility.unzip(file.getPath(), file2.getPath());
                File file3 = new File(file2.getPath() + File.separator + "mraid.js");
                if (!file3.exists()) {
                    AdvertisementPresenter.EventListener eventListener = this.bus;
                    if (eventListener != null) {
                        eventListener.onError(new VungleException(10));
                    }
                    closeView();
                    return;
                }
                try {
                    PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file3, true)));
                    printWriter.println("!function(e){\"use strict\";var t,r=e.vungle=e.vungle||{};r.error||(t=r.error=r.error||{},t.bridgeError={BRG000:\"Missing command in mraidBridge.executeSDKCommand method call!\",BRG001:\"Vungle SDK is not ready to process MRAID command!\"},t.bridgeExtError={BRX000:\"Cannot retrieve #dynamic container in Ad Unit!\"},t.mraidError={MRD000:\"Cannot add listener for unknown MRAID event!\",MRD001:\"Missing argument(s)! Both event and/or listener are required for method call!\",MRD002:\"Unable to find listener registered for event!\",MRD003:\"Missing MRAID event! Cannot remove event listener!\",MRD004:\"Missing object! An expand properties object is required for method call!\",MRD005:\"Missing object! An orientation properties object is required for method call!\",MRD006:\"Missing object! An resize properties object is required for method call!\",MRD007:\"Missing URL! A URL is required for method call!\",MRD008:\"Ad unit is hidden and cannot be closed!\",MRD009:\"Missing URL! A video/caption URL is required for method call!\",MRD010:\"Ad Unit is not viewable! Please make sure isViewAble is set to true!\",MRD011:\"Ad unit can only be expanded from the default or resized state!\",MRD012:\"Ad unit can only be resized from the default or resized state!\",MRD013:\"Missing URI! A valid URI is required for method call!\",MRD015:\"Invalid data/type detected when updating MRAID properties!\",MRD016:\"Missing app store id! An app store id is required for method call!\"},t.mraidClientError={MRC000:\"MRAID SDK error detected!\",MRC001:\"Missing MRAID object in window!\",MRC002:\"Missing video URL!  mraidClient.playVideo cannot retrieve video URL from arguments!\"},t.adTemplateError={ADT000:\"Missing page template JavaScript!\",ADT001:\"Error encountered loading template configuration!\"},t.gestureTrackingError={GET000:\"Cannot serialize user interaction tracking event object!\"})}(window),function(e){\"use strict\";var t=e.vungle=e.vungle||{},r=t.mraidCore=t.mraidCore||{},n=t.debugLog=t.debugLog||[];n&&n.push(\"vungle.mraidCore loaded.\"),r.consts={versions:{V1:\"1.0\",V2:\"2.0\"},states:{LOADING:\"loading\",DEFAULT:\"default\",RESIZED:\"resized\",EXPANDED:\"expanded\",HIDDEN:\"hidden\"},events:{INFO:\"info\",READY:\"ready\",ERROR:\"error\",STATE_CHANGE:\"stateChange\",ORIENTATION_CHANGE:\"orientationChange\",VIEWABLE_CHANGE:\"viewableChange\",SIZE_CHANGE:\"sizeChange\"},placements:{UNKNOWN:\"unknown\",INLINE:\"inline\",INTERSTITIAL:\"interstitial\"},orientations:{PORTRAIT:\"portrait\",LANDSCAPE:\"landscape\",NONE:\"none\"},closePositions:{CENTER:\"center\",TOP_LEFT:\"top-left\",TOP_CENTER:\"top-center\",TOP_RIGHT:\"top-right\",BOTTOM_LEFT:\"bottom-left\",BOTTOM_CENTER:\"bottom-center\",BOTTOM_RIGHT:\"bottom-right\"}},r.PropertiesValueObject=function(e){var t=function(e){var t;return e&&\"object\"==typeof e?(t={},Object.keys(e).forEach(function(r){t[r]=e[r]})):t=e,t};this.value=t(e),this.clone=function(){return t(this.value)},this.update=function(e){if(e&&\"object\"==typeof e){var t=this;Object.keys(e).forEach(function(r){t.value[r]=e[r]})}else this.value=e}},r.EventListeners=function(e){var t={};this.event=e,this.listenerCount=0,this.add=function(e){var r=String(e);return!t[r]&&(t[r]=e,this.listenerCount++,!0)},this.remove=function(e){var r=String(e);return!(!t.hasOwnProperty(r)||!t[r])&&(t[r]=null,delete t[r],this.listenerCount--,!0)},this.removeAll=function(){var e=this;Object.keys(t).forEach(function(r){e.remove(t[r])})},this.broadcast=function(e){var r=this;Object.keys(t).forEach(function(n){t[n].apply(r.mraid,e)})}};var o=new r.PropertiesValueObject(r.consts.versions.V1),i=new r.PropertiesValueObject({width:0,height:0}),a=new r.PropertiesValueObject({width:0,height:0}),s=new r.PropertiesValueObject({x:0,y:0,width:0,height:0}),d=new r.PropertiesValueObject({x:0,y:0,width:0,height:0}),c=new r.PropertiesValueObject({width:0,height:0,useCustomClose:!1,isModal:!0}),u=new r.PropertiesValueObject({width:0,height:0,offsetX:0,offsetY:0,customClosePosition:r.consts.closePositions.TOP_RIGHT,allowOffscreen:!0}),l=new r.PropertiesValueObject({allowOrientationChange:!0,forceOrientation:r.consts.orientations.NONE}),p=new r.PropertiesValueObject({sms:!1,tel:!1,calendar:!1,storePicture:!1,inlineVideo:!1}),m=new r.PropertiesValueObject(r.consts.states.LOADING),g=new r.PropertiesValueObject(r.consts.placements.UNKNOWN),f=new r.PropertiesValueObject((!1)),E=new r.PropertiesValueObject((!1)),v=new r.PropertiesValueObject((!1)),R=new r.PropertiesValueObject((!1)),y=new r.PropertiesValueObject((!1)),h=new r.PropertiesValueObject((!1)),C=new r.PropertiesValueObject((!1)),D=new r.PropertiesValueObject((!1)),B=new r.PropertiesValueObject((!1)),x=new r.PropertiesValueObject((!1)),P={},O=new r.PropertiesValueObject(\"\"),S=new r.PropertiesValueObject(\"\");r.eventListeners=P,r.propertiesHandlers={os:{update:function(e){O.update(e)},clone:function(){return O.clone()}},osVersion:{update:function(e){S.update(e)},clone:function(){return S.clone()}},incentivized:{update:function(e){E.update(e)},clone:function(){return E.clone()}},consentRequired:{update:function(e){v.update(e)},clone:function(){return v.clone()}},consentTitleText:{update:function(e){R.update(e)},clone:function(){return R.clone()}},consentBodyText:{update:function(e){y.update(e)},clone:function(){return y.clone()}},consentAcceptButtonText:{update:function(e){h.update(e)},clone:function(){return h.clone()}},consentDenyButtonText:{update:function(e){C.update(e)},clone:function(){return C.clone()}},version:{update:function(e){o.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting MRAID version to: \"+String(e))},clone:function(){return o.clone()}},maxSize:{update:function(e){i.update(e),c.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting maxSize to: \"+String(e))},clone:function(){return i.clone()}},screenSize:{update:function(e){var t;a.update(e),t=a.clone(),r.broadcastEvent(r.consts.events.INFO,\"Setting screenSize to: \"+String(e)),r.broadcastEvent(r.consts.events.SIZE_CHANGE,t.width,t.height)},clone:function(){return a.clone()}},defaultPosition:{update:function(e){s.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting defaultPosition to: \"+String(e))},clone:function(){return s.clone()}},currentPosition:{update:function(e){d.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting currentPosition to: \"+String(e))},clone:function(){return d.clone()}},expandProperties:{update:function(e){c.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting expandProperties to: \"+String(e))},clone:function(){return c.clone()}},resizeProperties:{update:function(e){u.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting resizeProperties to: \"+String(e))},clone:function(){return u.clone()}},orientationProperties:{update:function(e){l.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting orientationProperties to: \"+String(e))},clone:function(){return l.clone()}},supports:{update:function(e){p.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting supports to: \"+String(e))},clone:function(){return p.clone()}},state:{update:function(e){m.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting state to: \"+String(e)),r.broadcastEvent(r.consts.events.STATE_CHANGE,m.clone()),m.clone()===r.consts.states.DEFAULT&&r.broadcastEvent(r.consts.events.READY)},clone:function(){return m.clone()}},placementType:{update:function(e){g.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting placementType to: \"+String(e))},clone:function(){return g.clone()}},isViewable:{update:function(e){f.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting isViewable to: \"+String(e)),r.broadcastEvent(r.consts.events.VIEWABLE_CHANGE,f.clone())},clone:function(){return f.clone()}},customClose:{update:function(e){D.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting useCustomClose to: \"+String(e))},clone:function(){return D.clone()}},customPrivacy:{update:function(e){B.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting useCustomPrivacy to: \"+String(e))},clone:function(){return B.clone()}},enableBackImmediately:{update:function(e){x.update(e),r.broadcastEvent(r.consts.events.INFO,\"Setting enableBackImmediately to: \"+String(e))},clone:function(){return x.clone()}}},r.propertiesValidator=function(e,r,n){var o=!0;return typeof e!=typeof r&&\"useCustomClose\"!==n?(o=!1,t.mraidBridgeExt&&t.mraidBridgeExt.notifyError([\"MRD015\",n,e].join(\":\"))):\"object\"==typeof e&&Object.keys(e).forEach(function(e){e in r||(o=!1,t.mraidBridgeExt&&t.mraidBridgeExt.notifyError([\"MRD015\",n,e].join(\":\")))}),o},r.broadcastEvent=function(){var e=Array.prototype.slice.call(arguments),t=e.shift(),r=P[t];r&&r.broadcast(e)},r.isValidMARIDEvent=function(e){for(var t in r.consts.events)if(r.consts.events[t]===e)return!0;return!1}}(window),function(e){\"use strict\";var t=e.vungle=e.vungle||{},r=t.mraidBridge=t.mraidBridge||{};r.notifyContainer=function(t){e.location=t}}(window),function(e){\"use strict\";var t,r=e.vungle=e.vungle||{},n=r.mraidBridge=r.mraidBridge||{},o=r.mraidBridgeExt=r.mraidBridgeExt||{},i=r.debugLog=r.debugLog||[],a=!1,s=!1,d=[],c=!1,u=!1,l=!1,p=!1,m=r.mraidCore.broadcastEvent,g=r.mraidCore.consts.states,f=r.mraidCore.consts.events;i&&i.push(\"vungle.mraidBridge loaded.\"),o.getReplacementTokens=function(){return t},o.getIsVungleAd=function(){return c},o.getIsDirectDownload=function(){return u},o.getIsDisplayIAP=function(){return l},o.getIsInstalled=function(){return p},o.getEnableBackButtonImmediately=function(){return r.mraidCore.propertiesHandlers.enableBackImmediately.clone()},n.notifyPropertiesChange=function(e,t){i&&i.push(\"mraidBridge.notifyPropertiesChange:\"+JSON.stringify(e)),Object.keys(e).forEach(function(t){r.mraidCore.propertiesHandlers[t]?r.mraidCore.propertiesHandlers[t].update(e[t]):n.notifyErrorEvent(\"notifyPropertiesChange\",\"MRD015: Unhandled Property received - \"+t+\" - \"+e[t])}),\"undefined\"!=typeof t&&t===!0&&r.mraidBridge.notifyContainer(\"mraid://propertiesChangeCompleted\")},n.notifyCommandComplete=function(){i&&i.push(\"mraidBridge.notifyCommandComplete\");var e=\"\";d.length?(e=d.shift(),r.mraidBridge.notifyContainer(e)):s=!1},n.notifyReadyEvent=function(e){i&&i.push(\"mraidBridge.notifyReadyEvent\");var n=r.mraidCore.propertiesHandlers.state.clone();a=!0,\"undefined\"!=typeof e?(c=!0,p=e.isInstalled,l=e.isDisplayIAP,u=e.isDirectDownload,t=e):r.mraidBridgeExt.notifySuccessfulViewAd(),n!==g.DEFAULT?r.mraidCore.propertiesHandlers.state.update(g.DEFAULT):m(f.READY)},n.notifyErrorEvent=function(e,t){i&&i.push(\"mraidBridge.notifyErrorEvent:\"+e+\":\"+t),m(f.ERROR,e,t)},n.executeSDKCommand=function(){var e,t,o,c=\"\";if(!a)throw n.notifyErrorEvent(c,r.error.bridgeError.BRG001),{name:\"VungleMRAIDBridgeException\",message:\"BRG001\"};if(!arguments)throw n.notifyErrorEvent(c,r.error.bridgeError.BRG000),{name:\"VungleMRAIDBridgeException\",message:\"BRG000\"};for(c+=\"mraid://\"+arguments[0],o=1;o<arguments.length;o+=2)e=arguments[o],t=arguments[o+1],c+=(1===o?\"?\":\"&\")+encodeURIComponent(e),\"undefined\"!=typeof t&&(c+=\"=\"+encodeURIComponent(t));s?d.push(c):(s=!0,r.mraidBridge.notifyContainer(c)),i&&i.push(\"mraidBridge.executeSDKCommand: \"+c)},o.fireVideoCompleteEvent=function(){i&&i.push(\"mraidBridgeExt.fireVideoCompleteEvent\");var t=e.document.querySelector(\"#dynamic\"),r=new e.Event(\"vungle.events.video.ended\");t?t.dispatchEvent(r):o.notifyError(e.vungle.error.bridgeExtError.BRX000)},o.notifyPrepareStoreViewSuccess=function(){i&&i.push(\"mraidBridgeExt.notifyPrepareStoreViewSuccess\");var t=e.document.querySelector(\"#dynamic\"),r=new e.Event(\"vungle.events.preparestore.success\");t?t.dispatchEvent(r):o.notifyError(e.vungle.error.bridgeExtError.BRX000)},o.notifyError=function(t){i&&i.push(\"mraidBridgeExt.notifyError:\"+t);try{r.mraidBridge.executeSDKCommand(\"error\",\"code\",t)}catch(n){\"VungleMRAIDBridgeException\"===n.name?i&&i.push(\"%cVungleMRAIDBridgeException caught in mraidBridgeExt.notifyError! Message: %s\",\"color: red; font-size: x-large\",e.vungle.error.bridgeError[n.message]):i&&i.push(\"%cUnknown Exception caught in mraidBridgeExt.notifyError! Message: %s\",\"color: red; font-size: x-large\",n.message)}},o.notifyTPAT=function(e){i&&i.push(\"mraidBridgeExt.notifyTPATEvent:\"+e);try{r.mraidBridge.executeSDKCommand(\"tpat\",\"event\",e)}catch(t){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.notifyTPAT\",t.message)}},o.consentAction=function(e){i&&i.push(\"mraidBridgeExt.consentActionEvent:\"+e);try{r.mraidBridge.executeSDKCommand(\"consentAction\",\"event\",e)}catch(t){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.consentActionEvent\",t.message)}},o.notifyUserInteraction=function(e,t){i&&i.push(\"mraidBridgeExt.notifyUserInteraction\");try{r.mraidBridge.executeSDKCommand(\"action\",e,t)}catch(n){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.notifyUserInteraction\",n.message)}},o.notifyEventValuePairEvent=function(e,t){i&&i.push(\"mraidBridgeExt.notifyEventValuePairEvent\");var n=e||\"null\",o=t||\"null\";try{r.mraidBridge.executeSDKCommand(\"actionWithValue\",\"event\",n,\"value\",o)}catch(n){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.notifyEventValuePairEvent\",n.message)}},o.playHTML5Video=function(e){i&&i.push(\"mraidBridgeExt.playHTML5Video\");try{r.mraidBridge.executeSDKCommand(\"playHTML5Video\",\"selector\",e)}catch(t){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.playHTML5Video\",t.message)}},o.openPrivacy=function(e){i&&i.push(\"mraidBridgeExt.openPrivacy\");try{r.mraidBridge.executeSDKCommand(\"openPrivacy\",\"url\",e)}catch(t){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.openPrivacy\",t.message)}},o.requestMRAIDClose=function(){i&&i.push(\"mraidBridgeExt.requestMRAIDClose\");try{var e,t=\"windows\"===r.mraidExt.getOS()&&(0===r.mraidExt.getOSVersion().indexOf(\"WinPhone81\")||0===r.mraidExt.getOSVersion().indexOf(\"Win81\")),n=\"android\"===r.mraidExt.getOS()&&parseInt(r.mraidExt.getOSVersion(),10)<=17;t||n?(e=document.createEvent(\"Event\"),e.initEvent(\"vungle.events.request.close\",!0,!0)):e=new Event(\"vungle.events.request.close\"),c?document.querySelector(\"#dynamic\").dispatchEvent(e):r.mraidBridge.executeSDKCommand(\"close\")}catch(o){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.requestMRAIDClose\",o.message)}},o.notifySuccessfulViewAd=function(){i&&i.push(\"mraidBridgeExt.notifySuccessfulViewAd\");try{r.mraidBridge.executeSDKCommand(\"successfulView\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.notifySuccessfulViewAd\",e.message)}},o.openAppInDevice=function(){i&&i.push(\"mraidBridgeExt.openAppInDevice\");try{r.mraidBridge.executeSDKCommand(\"openAppInDevice\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.openAppInDevice\",e.message)}},o.openStorePage=function(){i&&i.push(\"mraidBridgeExt.openStorePage\");try{r.mraidBridge.executeSDKCommand(\"openStorePage\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.openStorePage\",e.message)}},o.cancelDownload=function(){i&&i.push(\"mraidBridgeExt.cancelDownload\");try{r.mraidBridge.executeSDKCommand(\"cancelDownload\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.cancelDownload\",e.message)}},o.getInstallationStatus=function(t){i&&i.push(\"mraidBridgeExt.getInstallationStatus\");var r=e.document.querySelector(\"#dynamic\"),n=new e.CustomEvent(\"vungle.events.installationStatus.updated\",{detail:t});r?r.dispatchEvent(n):o.notifyError(e.vungle.error.bridgeExtError.BRX000)},o.startDownloadAppOnDevice=function(){i&&i.push(\"mraidBridgeExt.startDownloadAppOnDevice\");try{r.mraidBridge.executeSDKCommand(\"startDownloadAppOnDevice\")}catch(e){r.mraidBridge.notifyErrorEvent(\"mraidBridgeExt.startDownloadAppOnDevice\",e.message)}}}(window),function(e){\"use strict\";var t=e.vungle=e.vungle||{},r=e.mraid=t.mraid=t.mraid||{},n=t.mraidExt=t.mraidExt||{},o=t.debugLog=t.debugLog||[],i=t.mraidBridge.executeSDKCommand,a=t.mraidCore.broadcastEvent,s=t.mraidCore.propertiesHandlers,d=t.mraidCore.propertiesValidator,c=t.mraidCore.consts.states,u=t.mraidCore.consts.events,l=t.mraidCore.consts.placements;o&&o.push(\"vungle.mraid loaded.\"),r.addEventListener=function(e,r){e&&r?t.mraidCore.isValidMARIDEvent(e)?(t.mraidCore.eventListeners[e]||(t.mraidCore.eventListeners[e]=new t.mraidCore.EventListeners(e)),t.mraidCore.eventListeners[e].add(r)):a(u.ERROR,\"mraid.addEventListener\",t.error.mraidError.MRD000+\":\"+e):a(u.ERROR,\"mraid.addEventListener\",t.error.mraidError.MRD001)},r.removeEventListener=function(e,r){var n,o=!1;e?(n=t.mraidCore.eventListeners[e],r?(n&&(o=n.remove(r)),o||a(u.ERROR,\"mraid.removeEventListener\",t.error.mraidError.MRD002+\":\"+e)):n&&t.mraidCore.eventListeners.removeAll(),n&&0===n.count&&(t.mraidCore.eventListeners[e]=null,delete t.mraidCore.eventListeners[e])):a(u.ERROR,\"mraid.removeEventListener\",t.error.mraidError.MRD003)},n.prepareStoreView=function(e){e?i(\"prepareStoreView\",\"appStoreId\",e):a(u.ERROR,\"mraid.prepareStoreView\",t.error.mraidError.MRD016)},n.presentStoreView=function(e){e?i(\"presentStoreView\",\"appStoreId\",e):a(u.ERROR,\"mraid.presentStoreView\",t.error.mraidError.MRD016)},n.getOS=function(){return s.os.clone()},n.getOSVersion=function(){return s.osVersion.clone()},n.getIncentivized=function(){return s.incentivized.clone()},r.getVersion=function(){return s.version.clone()},r.getMaxSize=function(){return s.maxSize.clone()},r.getScreenSize=function(){return s.screenSize.clone()},r.getDefaultPosition=function(){return s.defaultPosition.clone()},r.getCurrentPosition=function(){return s.currentPosition.clone()},r.getExpandProperties=function(){return s.expandProperties.clone()},r.getResizeProperties=function(){return s.resizeProperties.clone()},r.getOrientationProperties=function(){return s.orientationProperties.clone()},r.supports=function(e){return s.supports.clone()[e]},r.getState=function(){return s.state.clone()},r.getPlacementType=function(){return s.placementType.clone()},r.getConsentRequired=function(){return s.consentRequired.clone()},r.getConsentTitleText=function(){return s.consentTitleText.clone()},r.getConsentBodyText=function(){return s.consentBodyText.clone()},r.getConsentAcceptButtonText=function(){return s.consentAcceptButtonText.clone()},r.getConsentDenyButtonText=function(){return s.consentDenyButtonText.clone()},r.isViewable=function(){return s.isViewable.clone()},r.getViewable=r.isViewable,r.setExpandProperties=function(e){var r={};\"object\"==typeof e?(Object.keys(e).forEach(function(t){\"isModal\"!==t&&(r[t]=e[t])}),d(r,s.expandProperties.clone(),\"expandProperties\")&&s.expandProperties.update(r)):(a(u.ERROR,\"mraid.setExpandProperties\",t.error.mraidError.MRD004),t.mraidBridgeExt&&t.mraidBridgeExt.notifyError(\"MRD004\"))},r.setOrientationProperties=function(e){var r;\"object\"==typeof e?(r=[\"setOrientationProperties\",\"allowOrientationChange\",e.allowOrientationChange,\"forceOrientation\",e.forceOrientation],d(e,s.orientationProperties.clone(),\"orientationProperties\")&&(s.orientationProperties.update(e),i.apply(null,r))):(a(u.ERROR,\"mraid.setOrientationProperties\",t.error.mraidError.MRD005),t.mraidBridgeExt&&t.mraidBridgeExt.notifyError(\"MRD005\"))},r.setResizeProperties=function(e){\"object\"==typeof e?d(e,s.resizeProperties.clone(),\"resizeProperties\")&&s.resizeProperties.update(e):(a(u.ERROR,\"mraid.setResizeProperties\",t.error.mraidError.MRD006),t.mraidBridgeExt&&t.mraidBridgeExt.notifyError(\"MRD006\"))},r.useCustomClose=function(e){var t;d(e,s.customClose.clone(),\"useCustomClose\")&&d({useCustomClose:e},s.expandProperties.clone(),\"expandProperties\")&&(s.customClose.update(e),s.expandProperties.update({useCustomClose:e}),t=e===!0?\"invisible\":e===!1?\"visible\":void 0===e?\"gone\":\"\",i(\"useCustomClose\",\"sdkCloseButton\",t))},n.useCustomPrivacy=function(e){d(e,s.customPrivacy.clone(),\"useCustomPrivacy\")&&(s.customPrivacy.update(e),i(\"useCustomPrivacy\",\"useCustomPrivacy\",e))},r.open=function(e){e?i(\"open\",\"url\",encodeURI(e)):a(u.ERROR,\"mraid.open\",t.error.mraidError.MRD007)},r.close=function(){r.getState()!==c.HIDDEN?i(\"close\"):a(u.ERROR,\"mraid.close\",t.error.mraidError.MRD008)},r.playVideo=function(){var e=arguments&&arguments[0]?arguments[0]:\"\",n=arguments&&arguments[1]?arguments[1]:\"\";r.isViewable()?e.length>0?i(\"playVideo\",\"uri\",e,\"captionUrl\",n):a(u.ERROR,\"mraid.playVideo\",t.error.mraidError.MRD009):a(u.ERROR,\"mraid.playVideo\",t.error.mraidError.MRD010)},r.expand=function(e){var r=s.customClose.clone(),n=s.placementType.clone(),o=s.state.clone(),d=[\"expand\",\"useCustomClose\",r];n!==l.INLINE||o!==c.DEFAULT&&o!==c.RESIZED?a(u.ERROR,\"mraid.expand\",t.error.mraidError.MRD011):(e&&(arguments.push(\"url\"),arguments.push(encodeURI(e))),i.apply(null,d))},r.resize=function(){var e=s.resize.clone(),n=[\"resize\"],o=r.getState();o!==c.DEFAULT&&o!==c.RESIZED?(n.push(\"width\"),n.push(e.width),n.push(\"height\"),n.push(e.height),n.push(\"offsetX\"),n.push(e.offsetX),n.push(\"offsetY\"),n.push(e.offsetY),n.push(\"customClosePosition\"),n.push(e.customClosePosition),n.push(\"allowOffscreen\"),n.push(e.allowOffscreen),i.apply(null,n)):a(u.ERROR,\"mraid.resize\",t.error.mraidError.MRD012)},r.createCalendarEvent=function(){},r.storePicture=function(e){r.isViewable()?e?a(u.ERROR,\"mraid.storePicture\",t.error.mraidError.MRD013):i(\"storePicture\",\"uri\",e):a(u.ERROR,\"mraid.storePicture\",t.error.mraidError.MRD010)}}(window);");
                    printWriter.close();
                    this.prepared = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                closeView();
                return;
            }
        }
        File file4 = new File(file2.getPath() + File.separator + "index.html");
        if (!file4.exists()) {
            AdvertisementPresenter.EventListener eventListener2 = this.bus;
            if (eventListener2 != null) {
                eventListener2.onError(new VungleException(10));
            }
            this.adView.close();
            return;
        }
        this.adView.showWebsite("file://" + file4.getPath());
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void play() {
        setAdVisibility(true);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void stop(boolean z, boolean z2) {
        setAdVisibility(false);
        if (z || !z2 || this.isDestroying.getAndSet(true)) {
            return;
        }
        VungleWebClient vungleWebClient = this.webClient;
        if (vungleWebClient != null) {
            vungleWebClient.setMRAIDDelegate(null);
        }
        AdvertisementPresenter.EventListener eventListener = this.bus;
        if (eventListener != null) {
            eventListener.onNext(TtmlNode.END, this.report.isCTAClicked() ? "isCTAClicked" : null);
        }
        DirectDownloadAdapter directDownloadAdapter = this.directDownloadAdapter;
        if (directDownloadAdapter != null) {
            directDownloadAdapter.getSdkDownloadClient().cleanUp();
        }
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void setAdVisibility(boolean z) {
        this.webClient.setAdVisibility(z);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void generateSaveState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.storage.save(this.report);
        bundle.putString(EXTRA_REPORT, this.report.getId());
        bundle.putBoolean(EXTRA_INCENTIVIZED_SENT, this.sendReportIncentivized.get());
        bundle.putBoolean(EXTRA_CONTENT_PREPARED, true);
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void restoreFromSave(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        String string = bundle.getString(EXTRA_REPORT);
        this.report = TextUtils.isEmpty(string) ? null : (Report) this.storage.load(string, Report.class);
        boolean z = bundle.getBoolean(EXTRA_INCENTIVIZED_SENT, false);
        if (z) {
            this.sendReportIncentivized.set(z);
        }
        if (this.report == null) {
            this.adView.close();
        }
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public void onProgressUpdate(int i) {
        if (i == 100) {
            Advertisement.Checkpoint checkpoint = this.advertisement.getCheckpoints().get(this.advertisement.getCheckpoints().size() - 1);
            if (checkpoint.getPercentage() == 100) {
                for (String str : checkpoint.getUrls()) {
                    VungleApiClient.pingTPAT(str);
                }
            }
        }
    }

    @Override // com.vungle.warren.presenter.AdvertisementPresenter
    public boolean handleExit(String str) {
        Placement placement;
        if (str != null) {
            if (this.advertisement == null || (placement = this.placement) == null) {
                Log.e(TAG, "Unable to close advertisement");
                return false;
            }
            if (!placement.getId().equals(str)) {
                Log.e(TAG, "Cannot close FlexView Ad with invalid placement reference id");
                return false;
            }
            if (!"flexview".equals(this.advertisement.getTemplateType())) {
                Log.e(TAG, "Cannot close a Non FlexView ad");
                return false;
            }
            this.adView.showWebsite("javascript:window.vungle.mraidBridgeExt.requestMRAIDClose()");
            reportAction("mraidCloseByApi", null);
            return true;
        }
        if (this.backEnabled) {
            this.adView.showWebsite("javascript:window.vungle.mraidBridgeExt.requestMRAIDClose()");
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:113:0x0202  */
    /* JADX WARN: Code duplicated, block: B:44:0x009c  */
    /* JADX WARN: Code duplicated, block: B:90:0x01ac  */
    @Override // com.vungle.warren.ui.VungleWebClient.MRAIDDelegate
    public boolean processCommand(String str, JsonObject jsonObject) {
        int i;
        int i2 = 0;
        switch (str) {
            case "close":
                reportAction("mraidClose", null);
                closeView();
                return true;
            case "consentAction":
                Cookie cookie = (Cookie) this.storage.load(Cookie.CONSENT_COOKIE, Cookie.class);
                if (cookie == null) {
                    cookie = new Cookie(Cookie.CONSENT_COOKIE);
                }
                cookie.putValue("consent_status", jsonObject.get("event").getAsString());
                cookie.putValue("consent_source", "vungle_modal");
                cookie.putValue("timestamp", Long.valueOf(System.currentTimeMillis() / 1000));
                this.storage.save(cookie);
                return true;
            case "actionWithValue":
                String asString = jsonObject.get("event").getAsString();
                String asString2 = jsonObject.get("value").getAsString();
                this.report.recordAction(asString, asString2, System.currentTimeMillis());
                this.storage.save(this.report);
                if (asString.equals("videoViewed") && this.duration > 0.0f) {
                    try {
                        i = (int) ((Float.parseFloat(asString2) / this.duration) * 100.0f);
                    } catch (NumberFormatException unused) {
                        Log.e(TAG, "value for videoViewed is null !");
                        i = 0;
                    }
                    if (i > 0) {
                        AdvertisementPresenter.EventListener eventListener = this.bus;
                        if (eventListener != null) {
                            eventListener.onNext("percentViewed:" + i, null);
                        }
                        if (!this.hasSendStart && i > 1) {
                            this.hasSendStart = true;
                            DirectDownloadAdapter directDownloadAdapter = this.directDownloadAdapter;
                            if (directDownloadAdapter != null) {
                                directDownloadAdapter.getSdkDownloadClient().sendADDisplayingNotify(false, DirectDownloadAdapter.CONTRACT_TYPE.CPI);
                            }
                        }
                        if (!this.hasSend80Percent && i > 80) {
                            this.hasSend80Percent = true;
                            DirectDownloadAdapter directDownloadAdapter2 = this.directDownloadAdapter;
                            if (directDownloadAdapter2 != null) {
                                directDownloadAdapter2.getSdkDownloadClient().sendADDisplayingNotify(true, DirectDownloadAdapter.CONTRACT_TYPE.CPI);
                            }
                        }
                        Cookie cookie2 = (Cookie) this.storage.load(Cookie.CONFIG_COOKIE, Cookie.class);
                        if (this.placement.isIncentivized() && i > 75 && cookie2 != null && cookie2.getBoolean("isReportIncentivizedEnabled").booleanValue() && !this.sendReportIncentivized.getAndSet(true)) {
                            JsonObject jsonObject2 = new JsonObject();
                            jsonObject2.add("placement_reference_id", new JsonPrimitive(this.placement.getId()));
                            jsonObject2.add("app_id", new JsonPrimitive(this.advertisement.getAppID()));
                            jsonObject2.add("adStartTime", new JsonPrimitive((Number) Long.valueOf(this.report.getAdStartTime())));
                            jsonObject2.add("user", new JsonPrimitive(this.report.getUserID()));
                            VungleApiClient.ri(jsonObject2).enqueue(new Callback<JsonObject>() { // from class: com.vungle.warren.presenter.WebAdPresenter.3
                                @Override // retrofit2.Callback
                                public void onFailure(Call<JsonObject> call, Throwable th) {
                                }

                                @Override // retrofit2.Callback
                                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                                    Log.d(WebAdPresenter.TAG, "send RI success");
                                }
                            });
                        }
                    }
                }
                if (asString.equals("videoLength")) {
                    this.duration = Float.parseFloat(asString2);
                }
                this.adView.setVisibility(true);
                return true;
            case "tpat":
                String[] tpatUrls = this.advertisement.getTpatUrls(jsonObject.get("event").getAsString());
                int length = tpatUrls.length;
                while (i2 < length) {
                    VungleApiClient.pingTPAT(tpatUrls[i2]);
                    i2++;
                }
                return true;
            case "open":
                reportAction("download", null);
                reportAction("mraidOpen", null);
                reportAction("mraidClose", null);
                closeView();
                String asString3 = jsonObject.get("url").getAsString();
                VungleApiClient.pingTPAT(this.advertisement.getCTAURL(true));
                if (APKDirectDownloadManager.isDirectDownloadEnabled(this.directDownloadApkEnabled, this.advertisement.isRequiresNonMarketInstall())) {
                    APKDirectDownloadManager.download(asString3);
                    break;
                } else {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(asString3));
                    this.adView.open(intent.toUri(0));
                    break;
                }
            case "action":
                return true;
            case "useCustomClose":
                String asString4 = jsonObject.get("sdkCloseButton").getAsString();
                int iHashCode = asString4.hashCode();
                if (iHashCode != -1901805651) {
                    if (iHashCode != 3178655) {
                        if (iHashCode == 466743410 && asString4.equals("visible")) {
                            i2 = 2;
                        } else {
                            i2 = -1;
                        }
                    } else if (!asString4.equals("gone")) {
                        i2 = -1;
                    }
                } else if (asString4.equals("invisible")) {
                    i2 = 1;
                } else {
                    i2 = -1;
                }
                if (i2 == 0 || i2 == 1 || i2 == 2) {
                    return true;
                }
                throw new IllegalArgumentException("Unknown value " + asString4);
            case "useCustomPrivacy":
                String asString5 = jsonObject.get("useCustomPrivacy").getAsString();
                int iHashCode2 = asString5.hashCode();
                if (iHashCode2 != 3178655) {
                    if (iHashCode2 != 3569038) {
                        if (iHashCode2 == 97196323 && asString5.equals("false")) {
                            i2 = 2;
                        } else {
                            i2 = -1;
                        }
                    } else if (asString5.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                        i2 = 1;
                    } else {
                        i2 = -1;
                    }
                } else if (!asString5.equals("gone")) {
                    i2 = -1;
                }
                if (i2 == 0 || i2 == 1 || i2 == 2) {
                    return true;
                }
                throw new IllegalArgumentException("Unknown value " + asString5);
            case "openPrivacy":
                String asString6 = jsonObject.get("url").getAsString();
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(asString6));
                this.adView.open(intent2.toUri(0));
                return true;
            case "successfulView":
                AdvertisementPresenter.EventListener eventListener2 = this.bus;
                if (eventListener2 != null) {
                    eventListener2.onNext("successfulView", null);
                }
                Cookie cookie3 = (Cookie) this.storage.load(Cookie.CONFIG_COOKIE, Cookie.class);
                if (this.placement.isIncentivized() && cookie3 != null && cookie3.getBoolean("isReportIncentivizedEnabled").booleanValue() && !this.sendReportIncentivized.getAndSet(true)) {
                    JsonObject jsonObject3 = new JsonObject();
                    jsonObject3.add("placement_reference_id", new JsonPrimitive(this.placement.getId()));
                    jsonObject3.add("app_id", new JsonPrimitive(this.advertisement.getAppID()));
                    jsonObject3.add("adStartTime", new JsonPrimitive((Number) Long.valueOf(this.report.getAdStartTime())));
                    jsonObject3.add("user", new JsonPrimitive(this.report.getUserID()));
                    VungleApiClient.ri(jsonObject3).enqueue(new Callback<JsonObject>() { // from class: com.vungle.warren.presenter.WebAdPresenter.4
                        @Override // retrofit2.Callback
                        public void onFailure(Call<JsonObject> call, Throwable th) {
                        }

                        @Override // retrofit2.Callback
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            Log.d(WebAdPresenter.TAG, "send RI success");
                        }
                    });
                }
                return true;
            case "startDownloadAppOnDevice":
                DirectDownloadAdapter directDownloadAdapter3 = this.directDownloadAdapter;
                if (directDownloadAdapter3 != null) {
                    directDownloadAdapter3.getSdkDownloadClient().sendDownloadRequest();
                }
                return true;
            case "cancelDownload":
                DirectDownloadAdapter directDownloadAdapter4 = this.directDownloadAdapter;
                if (directDownloadAdapter4 != null) {
                    directDownloadAdapter4.getSdkDownloadClient().cancelDownloadRequest();
                }
                return true;
            case "openAppInDevice":
                DirectDownloadAdapter directDownloadAdapter5 = this.directDownloadAdapter;
                if (directDownloadAdapter5 != null) {
                    directDownloadAdapter5.getSdkDownloadClient().sendOpenPackageRequest();
                }
                return true;
            default:
                return false;
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
            closeView();
            return;
        }
        if (b != 1) {
            if (b == 2) {
                return;
            }
            throw new IllegalArgumentException("Unknown action " + str);
        }
        try {
            VungleApiClient.pingTPAT(this.advertisement.getCTAURL(true));
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(this.advertisement.getCTAURL(false)));
            this.adView.open(intent.toUri(0));
            closeView();
        } catch (ActivityNotFoundException unused) {
            closeView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeView() {
        this.adView.close();
        this.handler.removeCallbacksAndMessages(null);
    }
}
