package com.ironsource.mediationsdk;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.events.InterstitialEventsManager;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.BannerPlacement;
import com.ironsource.mediationsdk.model.ProviderSettings;
import com.ironsource.mediationsdk.sdk.BannerManagerListener;
import com.ironsource.mediationsdk.utils.CappingManager;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class BannerManager implements BannerManagerListener {
    private BannerSmash mActiveSmash;
    private Activity mActivity;
    private String mAppKey;
    private BannerPlacement mCurrentPlacement;
    private IronSourceBannerLayout mIronsourceBanner;
    private long mReloadInterval;
    private Timer mReloadTimer;
    private String mUserId;
    private final CopyOnWriteArrayList<BannerSmash> mSmashArray = new CopyOnWriteArrayList<>();
    private IronSourceLoggerManager mLoggerManager = IronSourceLoggerManager.getLogger();
    private BANNER_STATE mState = BANNER_STATE.NOT_INITIATED;
    private Boolean mIsInForeground = true;
    AtomicBoolean mDidImplementOnPause = new AtomicBoolean();
    AtomicBoolean mDidImplementOnResume = new AtomicBoolean();

    private enum BANNER_STATE {
        NOT_INITIATED,
        READY_TO_LOAD,
        FIRST_LOAD_IN_PROGRESS,
        LOAD_IN_PROGRESS,
        RELOAD_IN_PROGRESS
    }

    public BannerManager(List<ProviderSettings> list, Activity activity, String str, String str2, long j, int i, int i2) {
        this.mAppKey = str;
        this.mUserId = str2;
        this.mActivity = activity;
        this.mReloadInterval = i;
        BannerCallbackThrottler.getInstance().setDelayLoadFailureNotificationInSeconds(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            ProviderSettings providerSettings = list.get(i3);
            AbstractAdapter abstractAdapterLoadAdapter = loadAdapter(providerSettings);
            if (abstractAdapterLoadAdapter != null && AdaptersCompatibilityHandler.getInstance().isBannerAdapterCompatible(abstractAdapterLoadAdapter)) {
                this.mSmashArray.add(new BannerSmash(this, providerSettings, abstractAdapterLoadAdapter, j, i3 + 1));
            } else {
                debugLog(providerSettings.getProviderInstanceName() + " can't load adapter or wrong version");
            }
        }
        this.mCurrentPlacement = null;
        setState(BANNER_STATE.READY_TO_LOAD);
    }

    public synchronized void setConsent(boolean z) {
        synchronized (this.mSmashArray) {
            Iterator<BannerSmash> it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                it.next().setConsent(z);
            }
        }
    }

    public synchronized void loadBanner(IronSourceBannerLayout ironSourceBannerLayout, BannerPlacement bannerPlacement) {
        try {
            if (this.mState == BANNER_STATE.READY_TO_LOAD && !BannerCallbackThrottler.getInstance().hasPendingInvocation()) {
                setState(BANNER_STATE.FIRST_LOAD_IN_PROGRESS);
                this.mIronsourceBanner = ironSourceBannerLayout;
                this.mCurrentPlacement = bannerPlacement;
                sendMediationEvent(3001);
                if (CappingManager.isBnPlacementCapped(this.mActivity, bannerPlacement.getPlacementName())) {
                    BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(ironSourceBannerLayout, new IronSourceError(IronSourceError.ERROR_BN_LOAD_PLACEMENT_CAPPED, "placement " + bannerPlacement.getPlacementName() + " is capped"));
                    sendMediationEvent(IronSourceConstants.BN_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_BN_LOAD_PLACEMENT_CAPPED)}});
                    setState(BANNER_STATE.READY_TO_LOAD);
                    return;
                }
                synchronized (this.mSmashArray) {
                    Iterator<BannerSmash> it = this.mSmashArray.iterator();
                    while (it.hasNext()) {
                        it.next().setReadyToLoad(true);
                    }
                    BannerSmash bannerSmash = this.mSmashArray.get(0);
                    sendProviderEvent(3002, bannerSmash);
                    bannerSmash.loadBanner(ironSourceBannerLayout, this.mActivity, this.mAppKey, this.mUserId);
                }
                return;
            }
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "A banner is already loaded", 3);
        } catch (Exception e) {
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(ironSourceBannerLayout, new IronSourceError(IronSourceError.ERROR_BN_LOAD_EXCEPTION, "loadBanner() failed " + e.getMessage()));
            String message = e.getMessage();
            sendMediationEvent(IronSourceConstants.BN_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_BN_LOAD_EXCEPTION)}, new Object[]{IronSourceConstants.EVENTS_ERROR_REASON, message.substring(0, Math.min(message.length(), 39))}});
            setState(BANNER_STATE.READY_TO_LOAD);
        }
    }

    public synchronized void destroyBanner(IronSourceBannerLayout ironSourceBannerLayout) {
        try {
            if (ironSourceBannerLayout == null) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "destroyBanner banner cannot be null", 3);
                return;
            }
            if (ironSourceBannerLayout.isDestroyed()) {
                this.mLoggerManager.log(IronSourceLogger.IronSourceTag.API, "Banner is already destroyed and can't be used anymore. Please create a new one using IronSource.createBanner API", 3);
                return;
            }
            sendMediationEvent(IronSourceConstants.BN_DESTROY);
            stopReloadTimer();
            ironSourceBannerLayout.destroyBanner();
            this.mIronsourceBanner = null;
            this.mCurrentPlacement = null;
            if (this.mActiveSmash != null) {
                sendProviderEvent(IronSourceConstants.BN_INSTANCE_DESTROY, this.mActiveSmash);
                this.mActiveSmash.destroyBanner();
                this.mActiveSmash = null;
            }
            setState(BANNER_STATE.READY_TO_LOAD);
        } catch (Throwable th) {
            throw th;
        }
    }

    private void errorLog(String str) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "BannerManager " + str, 3);
    }

    private void debugLog(String str) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "BannerManager " + str, 0);
    }

    private void setState(BANNER_STATE banner_state) {
        this.mState = banner_state;
        debugLog("state=" + banner_state.name());
    }

    private void callbackLog(String str, BannerSmash bannerSmash) {
        this.mLoggerManager.log(IronSourceLogger.IronSourceTag.ADAPTER_CALLBACK, "BannerManager " + str + " " + bannerSmash.getName(), 0);
    }

    private void bindView(BannerSmash bannerSmash, View view, FrameLayout.LayoutParams layoutParams) {
        this.mActiveSmash = bannerSmash;
        this.mIronsourceBanner.addViewWithFrameLayoutParams(view, layoutParams);
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerManagerListener
    public void onBannerAdLoaded(BannerSmash bannerSmash, View view, FrameLayout.LayoutParams layoutParams) {
        callbackLog("onBannerAdLoaded", bannerSmash);
        if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS) {
            sendProviderEvent(3005, bannerSmash);
            bindView(bannerSmash, view, layoutParams);
            CappingManager.incrementBnShowCounter(this.mActivity, this.mCurrentPlacement.getPlacementName());
            if (CappingManager.isBnPlacementCapped(this.mActivity, this.mCurrentPlacement.getPlacementName())) {
                sendMediationEvent(IronSourceConstants.BN_PLACEMENT_CAPPED);
            }
            this.mIronsourceBanner.sendBannerAdLoaded(bannerSmash);
            sendMediationEvent(IronSourceConstants.BN_CALLBACK_LOAD_SUCCESS);
            setState(BANNER_STATE.RELOAD_IN_PROGRESS);
            startReloadTimer();
            return;
        }
        if (this.mState == BANNER_STATE.LOAD_IN_PROGRESS) {
            sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD_SUCCESS, bannerSmash);
            bindView(bannerSmash, view, layoutParams);
            setState(BANNER_STATE.RELOAD_IN_PROGRESS);
            startReloadTimer();
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerManagerListener
    public void onBannerAdLoadFailed(IronSourceError ironSourceError, BannerSmash bannerSmash, boolean z) {
        callbackLog("onBannerAdLoadFailed " + ironSourceError.getErrorMessage(), bannerSmash);
        if (this.mState != BANNER_STATE.FIRST_LOAD_IN_PROGRESS && this.mState != BANNER_STATE.LOAD_IN_PROGRESS) {
            debugLog("onBannerAdLoadFailed " + bannerSmash.getName() + " wrong state=" + this.mState.name());
            return;
        }
        if (z) {
            sendProviderEvent(IronSourceConstants.BN_INSTANCE_LOAD_NO_FILL, bannerSmash);
        } else {
            sendProviderEvent(IronSourceConstants.BN_INSTANCE_LOAD_ERROR, bannerSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}});
        }
        if (loadNextSmash()) {
            return;
        }
        if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS) {
            BannerCallbackThrottler.getInstance().sendBannerAdLoadFailed(this.mIronsourceBanner, new IronSourceError(IronSourceError.ERROR_BN_LOAD_NO_FILL, "No ads to show"));
            sendMediationEvent(IronSourceConstants.BN_CALLBACK_LOAD_ERROR, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_BN_LOAD_NO_FILL)}});
            setState(BANNER_STATE.READY_TO_LOAD);
        } else {
            sendMediationEvent(IronSourceConstants.BN_RELOAD_FAILED);
            setState(BANNER_STATE.RELOAD_IN_PROGRESS);
            startReloadTimer();
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerManagerListener
    public void onBannerAdReloaded(BannerSmash bannerSmash) {
        callbackLog("onBannerAdReloaded", bannerSmash);
        if (this.mState != BANNER_STATE.RELOAD_IN_PROGRESS) {
            debugLog("onBannerAdReloaded " + bannerSmash.getName() + " wrong state=" + this.mState.name());
            return;
        }
        IronSourceUtils.sendAutomationLog("bannerReloadSucceeded");
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD_SUCCESS, bannerSmash);
        startReloadTimer();
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerManagerListener
    public void onBannerAdReloadFailed(IronSourceError ironSourceError, BannerSmash bannerSmash, boolean z) {
        callbackLog("onBannerAdReloadFailed " + ironSourceError.getErrorMessage(), bannerSmash);
        if (this.mState != BANNER_STATE.RELOAD_IN_PROGRESS) {
            debugLog("onBannerAdReloadFailed " + bannerSmash.getName() + " wrong state=" + this.mState.name());
            return;
        }
        if (z) {
            sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD_NO_FILL, bannerSmash);
        } else {
            sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD_ERROR, bannerSmash, new Object[][]{new Object[]{"errorCode", Integer.valueOf(ironSourceError.getErrorCode())}});
        }
        synchronized (this.mSmashArray) {
            if (this.mSmashArray.size() == 1) {
                sendMediationEvent(IronSourceConstants.BN_RELOAD_FAILED);
                startReloadTimer();
            } else {
                setState(BANNER_STATE.LOAD_IN_PROGRESS);
                resetIteration();
                loadNextSmash();
            }
        }
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerManagerListener
    public void onBannerAdClicked(BannerSmash bannerSmash) {
        callbackLog("onBannerAdClicked", bannerSmash);
        sendMediationEvent(IronSourceConstants.BN_CALLBACK_CLICK);
        this.mIronsourceBanner.sendBannerAdClicked();
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_CLICK, bannerSmash);
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerManagerListener
    public void onBannerAdScreenDismissed(BannerSmash bannerSmash) {
        callbackLog("onBannerAdScreenDismissed", bannerSmash);
        sendMediationEvent(IronSourceConstants.BN_CALLBACK_DISMISS_SCREEN);
        this.mIronsourceBanner.sendBannerAdScreenDismissed();
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_DISMISS_SCREEN, bannerSmash);
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerManagerListener
    public void onBannerAdScreenPresented(BannerSmash bannerSmash) {
        callbackLog("onBannerAdScreenPresented", bannerSmash);
        sendMediationEvent(IronSourceConstants.BN_CALLBACK_PRESENT_SCREEN);
        this.mIronsourceBanner.sendBannerAdScreenPresented();
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_PRESENT_SCREEN, bannerSmash);
    }

    @Override // com.ironsource.mediationsdk.sdk.BannerManagerListener
    public void onBannerAdLeftApplication(BannerSmash bannerSmash) {
        callbackLog("onBannerAdLeftApplication", bannerSmash);
        Object[][] objArr = (Object[][]) null;
        sendMediationEvent(IronSourceConstants.BN_CALLBACK_LEAVE_APP, objArr);
        this.mIronsourceBanner.sendBannerAdLeftApplication();
        sendProviderEvent(IronSourceConstants.BN_INSTANCE_LEAVE_APP, bannerSmash, objArr);
    }

    private void sendMediationEvent(int i) {
        sendMediationEvent(i, (Object[][]) null);
    }

    private void sendMediationEvent(int i, Object[][] objArr) {
        JSONObject mediationAdditionalData = IronSourceUtils.getMediationAdditionalData(false);
        try {
            if (this.mIronsourceBanner != null) {
                addEventSizeFields(mediationAdditionalData, this.mIronsourceBanner.getSize());
            }
            if (this.mCurrentPlacement != null) {
                mediationAdditionalData.put("placement", this.mCurrentPlacement.getPlacementName());
            }
            if (objArr != null) {
                for (Object[] objArr2 : objArr) {
                    mediationAdditionalData.put(objArr2[0].toString(), objArr2[1]);
                }
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "sendMediationEvent " + Log.getStackTraceString(e), 3);
        }
        InterstitialEventsManager.getInstance().log(new EventData(i, mediationAdditionalData));
    }

    private void sendProviderEvent(int i, BannerSmash bannerSmash) {
        sendProviderEvent(i, bannerSmash, (Object[][]) null);
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0043  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void addEventSizeFields(JSONObject jSONObject, ISBannerSize iSBannerSize) {
        byte b;
        try {
            String description = iSBannerSize.getDescription();
            switch (description.hashCode()) {
                case -387072689:
                    if (!description.equals("RECTANGLE")) {
                        b = -1;
                    } else {
                        b = 2;
                    }
                    break;
                case 72205083:
                    if (!description.equals("LARGE")) {
                        b = -1;
                    } else {
                        b = 1;
                    }
                    break;
                case 79011241:
                    if (!description.equals("SMART")) {
                        b = -1;
                    } else {
                        b = 3;
                    }
                    break;
                case 1951953708:
                    if (!description.equals("BANNER")) {
                        b = -1;
                    } else {
                        b = 0;
                    }
                    break;
                case 1999208305:
                    if (!description.equals("CUSTOM")) {
                        b = -1;
                    } else {
                        b = 4;
                    }
                    break;
                default:
                    b = -1;
                    break;
            }
            if (b == 0) {
                jSONObject.put("bannerAdSize", 1);
                return;
            }
            if (b == 1) {
                jSONObject.put("bannerAdSize", 2);
                return;
            }
            if (b == 2) {
                jSONObject.put("bannerAdSize", 3);
                return;
            }
            if (b == 3) {
                jSONObject.put("bannerAdSize", 5);
                return;
            }
            if (b != 4) {
                return;
            }
            jSONObject.put("bannerAdSize", 6);
            jSONObject.put("custom_banner_size", iSBannerSize.getWidth() + "x" + iSBannerSize.getHeight());
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "sendProviderEvent " + Log.getStackTraceString(e), 3);
        }
    }

    private void sendProviderEvent(int i, BannerSmash bannerSmash, Object[][] objArr) {
        JSONObject providerAdditionalData = IronSourceUtils.getProviderAdditionalData(bannerSmash);
        try {
            if (this.mIronsourceBanner != null) {
                addEventSizeFields(providerAdditionalData, this.mIronsourceBanner.getSize());
            }
            if (this.mCurrentPlacement != null) {
                providerAdditionalData.put("placement", this.mCurrentPlacement.getPlacementName());
            }
            if (objArr != null) {
                for (Object[] objArr2 : objArr) {
                    providerAdditionalData.put(objArr2[0].toString(), objArr2[1]);
                }
            }
        } catch (Exception e) {
            this.mLoggerManager.log(IronSourceLogger.IronSourceTag.INTERNAL, "sendProviderEvent " + Log.getStackTraceString(e), 3);
        }
        InterstitialEventsManager.getInstance().log(new EventData(i, providerAdditionalData));
    }

    private void resetIteration() {
        synchronized (this.mSmashArray) {
            Iterator<BannerSmash> it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                it.next().setReadyToLoad(true);
            }
        }
    }

    private boolean loadNextSmash() {
        synchronized (this.mSmashArray) {
            for (BannerSmash bannerSmash : this.mSmashArray) {
                if (bannerSmash.isReadyToLoad() && this.mActiveSmash != bannerSmash) {
                    if (this.mState == BANNER_STATE.FIRST_LOAD_IN_PROGRESS) {
                        sendProviderEvent(3002, bannerSmash);
                    } else {
                        sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD, bannerSmash);
                    }
                    bannerSmash.loadBanner(this.mIronsourceBanner, this.mActivity, this.mAppKey, this.mUserId);
                    return true;
                }
            }
            return false;
        }
    }

    public void onPause(Activity activity) {
        synchronized (this.mSmashArray) {
            this.mIsInForeground = false;
            Iterator<BannerSmash> it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                it.next().onPause(activity);
            }
        }
    }

    public void onResume(Activity activity) {
        synchronized (this.mSmashArray) {
            this.mIsInForeground = true;
            Iterator<BannerSmash> it = this.mSmashArray.iterator();
            while (it.hasNext()) {
                it.next().onResume(activity);
            }
        }
    }

    private void startReloadTimer() {
        try {
            stopReloadTimer();
            this.mReloadTimer = new Timer();
            this.mReloadTimer.schedule(new TimerTask() { // from class: com.ironsource.mediationsdk.BannerManager.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    BannerManager.this.onReloadTimer();
                }
            }, this.mReloadInterval * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopReloadTimer() {
        Timer timer = this.mReloadTimer;
        if (timer != null) {
            timer.cancel();
            this.mReloadTimer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReloadTimer() {
        if (this.mState != BANNER_STATE.RELOAD_IN_PROGRESS) {
            debugLog("onReloadTimer wrong state=" + this.mState.name());
            return;
        }
        if (this.mIsInForeground.booleanValue()) {
            sendMediationEvent(IronSourceConstants.BN_RELOAD);
            sendProviderEvent(IronSourceConstants.BN_INSTANCE_RELOAD, this.mActiveSmash);
            this.mActiveSmash.reloadBanner();
        } else {
            sendMediationEvent(IronSourceConstants.BN_SKIP_RELOAD, new Object[][]{new Object[]{"errorCode", Integer.valueOf(IronSourceError.ERROR_BN_RELOAD_SKIP_BACKGROUND)}});
            startReloadTimer();
        }
    }

    private AbstractAdapter getLoadedAdapterOrFetchByReflection(String str, String str2) {
        try {
            AbstractAdapter existingAdapter = IronSourceObject.getInstance().getExistingAdapter(str);
            if (existingAdapter != null) {
                debugLog("using previously loaded " + str);
                return existingAdapter;
            }
            debugLog("loading " + str + " with reflection");
            Class<?> cls = Class.forName("com.ironsource.adapters." + str2.toLowerCase() + "." + str2 + "Adapter");
            return (AbstractAdapter) cls.getMethod(IronSourceConstants.START_ADAPTER, String.class).invoke(cls, str);
        } catch (Exception e) {
            errorLog("getLoadedAdapterOrFetchByReflection " + e.getMessage());
            return null;
        }
    }

    private AbstractAdapter loadAdapter(ProviderSettings providerSettings) {
        String providerTypeForReflection = providerSettings.isMultipleInstances() ? providerSettings.getProviderTypeForReflection() : providerSettings.getProviderName();
        String providerTypeForReflection2 = providerSettings.getProviderTypeForReflection();
        debugLog("loadAdapter(" + providerTypeForReflection + ")");
        try {
            AbstractAdapter loadedAdapterOrFetchByReflection = getLoadedAdapterOrFetchByReflection(providerTypeForReflection, providerTypeForReflection2);
            if (loadedAdapterOrFetchByReflection == null) {
                return null;
            }
            IronSourceObject.getInstance().addToBannerAdaptersList(loadedAdapterOrFetchByReflection);
            loadedAdapterOrFetchByReflection.setLogListener(this.mLoggerManager);
            return loadedAdapterOrFetchByReflection;
        } catch (Throwable th) {
            errorLog("loadAdapter(" + providerTypeForReflection + ") " + th.getMessage());
            return null;
        }
    }
}
