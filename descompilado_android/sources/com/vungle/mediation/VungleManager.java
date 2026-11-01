package com.vungle.mediation;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.vungle.warren.AdConfig;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.network.VungleApiClient;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class VungleManager {
    private static final String PLAYING_PLACEMENT = "placementID";
    private static final String TAG = "VungleManager";
    private static final String VERSION = "6.3.12";
    private static VungleManager sInstance;
    private String mAppId;
    private String[] mPlacements;
    private String mCurrentPlayId = null;
    private boolean mIsInitialising = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Map<String, VungleListener> mListeners = new HashMap();

    static VungleManager getInstance(String str, String[] strArr) {
        if (sInstance == null) {
            sInstance = new VungleManager(str, strArr);
        }
        return sInstance;
    }

    private VungleManager(String str, String[] strArr) {
        VungleApiClient.addWrapperInfo(VungleApiClient.WrapperFramework.admob, VERSION.replace('.', '_'));
        this.mAppId = str;
        this.mPlacements = strArr;
    }

    boolean isInitialized() {
        return Vungle.isInitialized();
    }

    void setIncentivizedFields(String str, String str2, String str3, String str4, String str5) {
        Vungle.setIncentivizedFields(str, str2, str3, str4, str5);
    }

    String findPlacement(Bundle bundle, Bundle bundle2) {
        String string = (bundle == null || !bundle.containsKey("playPlacement")) ? null : bundle.getString("playPlacement");
        if (bundle2 != null && bundle2.containsKey(PLAYING_PLACEMENT)) {
            if (string != null) {
                Log.i(TAG, "'placementID' had a value in both serverParameters and networkExtras. Used one from serverParameters");
            }
            string = bundle2.getString(PLAYING_PLACEMENT);
        }
        if (string != null) {
            return string;
        }
        String[] strArr = this.mPlacements;
        if (strArr != null && strArr.length > 0) {
            String str = strArr[0];
            Log.i(TAG, String.format("'placementID' not specified. Used first from 'allPlacements': %s", str));
            return str;
        }
        Log.e(TAG, "At least one placement should be specified in either serverParameters or the networkExtras!");
        return string;
    }

    void init(Context context) {
        if (Vungle.isInitialized()) {
            for (VungleListener vungleListener : this.mListeners.values()) {
                if (vungleListener.isWaitingInit()) {
                    vungleListener.setWaitingInit(false);
                    vungleListener.onInitialized(Vungle.isInitialized());
                }
            }
            return;
        }
        if (this.mIsInitialising) {
            return;
        }
        this.mIsInitialising = true;
        Vungle.init(this.mAppId, context.getApplicationContext(), new InitCallback() { // from class: com.vungle.mediation.VungleManager.1
            @Override // com.vungle.warren.InitCallback
            public void onAutoCacheAdAvailable(String str) {
            }

            @Override // com.vungle.warren.InitCallback
            public void onSuccess() {
                VungleManager.this.mHandler.post(new Runnable() { // from class: com.vungle.mediation.VungleManager.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VungleManager.this.mIsInitialising = false;
                        if (VungleConsent.getCurrentVungleConsent() != null) {
                            Vungle.updateConsentStatus(VungleConsent.getCurrentVungleConsent(), VungleConsent.getCurrentVungleConsentMessageVersion());
                        }
                        for (VungleListener vungleListener2 : VungleManager.this.mListeners.values()) {
                            if (vungleListener2.isWaitingInit()) {
                                vungleListener2.setWaitingInit(false);
                                vungleListener2.onInitialized(Vungle.isInitialized());
                            }
                        }
                    }
                });
            }

            @Override // com.vungle.warren.InitCallback
            public void onError(Throwable th) {
                VungleManager.this.mHandler.post(new Runnable() { // from class: com.vungle.mediation.VungleManager.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        VungleManager.this.mIsInitialising = false;
                        for (VungleListener vungleListener2 : VungleManager.this.mListeners.values()) {
                            if (vungleListener2.isWaitingInit()) {
                                vungleListener2.setWaitingInit(false);
                                vungleListener2.onInitialized(Vungle.isInitialized());
                            }
                        }
                    }
                });
            }
        });
    }

    void removeListener(String str) {
        if (this.mListeners.containsKey(str)) {
            this.mListeners.remove(str);
        }
    }

    void addListener(String str, VungleListener vungleListener) {
        removeListener(str);
        this.mListeners.put(str, vungleListener);
    }

    void playAd(String str, AdConfig adConfig, String str2) {
        if (this.mCurrentPlayId != null) {
            return;
        }
        this.mCurrentPlayId = str2;
        Vungle.playAd(str, adConfig, new PlayAdCallback() { // from class: com.vungle.mediation.VungleManager.2
            @Override // com.vungle.warren.PlayAdCallback
            public void onAdStart(final String str3) {
                VungleManager.this.mHandler.post(new Runnable() { // from class: com.vungle.mediation.VungleManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (Map.Entry entry : VungleManager.this.mListeners.entrySet()) {
                            try {
                                if (VungleManager.this.mCurrentPlayId == null || VungleManager.this.mCurrentPlayId.equals(entry.getKey())) {
                                    ((VungleListener) entry.getValue()).onAdStart(str3);
                                }
                            } catch (Exception e) {
                                Log.w(VungleManager.TAG, e);
                            }
                        }
                    }
                });
            }

            @Override // com.vungle.warren.PlayAdCallback
            public void onAdEnd(final String str3, final boolean z, final boolean z2) {
                VungleManager.this.mHandler.post(new Runnable() { // from class: com.vungle.mediation.VungleManager.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        for (Map.Entry entry : VungleManager.this.mListeners.entrySet()) {
                            try {
                                if (VungleManager.this.mCurrentPlayId == null || VungleManager.this.mCurrentPlayId.equals(entry.getKey())) {
                                    ((VungleListener) entry.getValue()).onAdEnd(str3, z, z2);
                                }
                            } catch (Exception e) {
                                Log.w(VungleManager.TAG, e);
                            }
                        }
                        VungleManager.this.mCurrentPlayId = null;
                    }
                });
            }

            @Override // com.vungle.warren.PlayAdCallback
            public void onError(final String str3, Throwable th) {
                VungleManager.this.mHandler.post(new Runnable() { // from class: com.vungle.mediation.VungleManager.2.3
                    @Override // java.lang.Runnable
                    public void run() {
                        for (Map.Entry entry : VungleManager.this.mListeners.entrySet()) {
                            try {
                                if (VungleManager.this.mCurrentPlayId == null || VungleManager.this.mCurrentPlayId.equals(entry.getKey())) {
                                    ((VungleListener) entry.getValue()).onAdFail(str3);
                                }
                            } catch (Exception e) {
                                Log.w(VungleManager.TAG, e);
                            }
                        }
                        VungleManager.this.mCurrentPlayId = null;
                    }
                });
            }
        });
    }

    boolean isAdPlayable(String str) {
        return Vungle.canPlayAd(str);
    }

    void loadAd(String str) {
        if (Vungle.canPlayAd(str)) {
            notifyAdIsReady(str, true);
        } else {
            Vungle.loadAd(str, new LoadAdCallback() { // from class: com.vungle.mediation.VungleManager.3
                @Override // com.vungle.warren.LoadAdCallback
                public void onAdLoad(final String str2) {
                    VungleManager.this.mHandler.post(new Runnable() { // from class: com.vungle.mediation.VungleManager.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            VungleManager.this.notifyAdIsReady(str2, true);
                        }
                    });
                }

                @Override // com.vungle.warren.LoadAdCallback
                public void onError(final String str2, Throwable th) {
                    VungleManager.this.mHandler.post(new Runnable() { // from class: com.vungle.mediation.VungleManager.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            VungleManager.this.notifyAdIsReady(str2, false);
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAdIsReady(String str, boolean z) {
        for (VungleListener vungleListener : this.mListeners.values()) {
            try {
                if (vungleListener.getWaitingForPlacement() != null && vungleListener.getWaitingForPlacement().equals(str)) {
                    if (z) {
                        vungleListener.onAdAvailable();
                    } else {
                        vungleListener.onAdFailedToLoad();
                    }
                    vungleListener.waitForAd(null);
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
    }
}
