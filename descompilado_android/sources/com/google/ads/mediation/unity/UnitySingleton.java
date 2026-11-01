package com.google.ads.mediation.unity;

import android.app.Activity;
import android.util.Log;
import com.adcolony.sdk.AdColonyAppOptions;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;
import com.unity3d.ads.metadata.MediationMetaData;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class UnitySingleton {
    private static WeakReference<UnityAdapterDelegate> mAdShowingAdapterDelegate;
    private static Set<WeakReference<UnityAdapterDelegate>> mUnityAdapterDelegatesSet = Collections.synchronizedSet(new HashSet());
    private static UnitySingletonListener unitySingletonListenerInstance;

    private static UnitySingletonListener getInstance() {
        if (unitySingletonListenerInstance == null) {
            unitySingletonListenerInstance = new UnitySingletonListener();
        }
        return unitySingletonListenerInstance;
    }

    private static void addUnityAdapterDelegate(UnityAdapterDelegate unityAdapterDelegate) {
        Iterator<WeakReference<UnityAdapterDelegate>> it = mUnityAdapterDelegatesSet.iterator();
        while (it.hasNext()) {
            UnityAdapterDelegate unityAdapterDelegate2 = it.next().get();
            if (unityAdapterDelegate2 == null) {
                it.remove();
            } else if (unityAdapterDelegate2.equals(unityAdapterDelegate)) {
                return;
            }
        }
        mUnityAdapterDelegatesSet.add(new WeakReference<>(unityAdapterDelegate));
    }

    public static boolean initializeUnityAds(UnityAdapterDelegate unityAdapterDelegate, Activity activity, String str) {
        if (!UnityAds.isSupported()) {
            Log.w(UnityAdapter.TAG, "The current device is not supported by Unity Ads.");
            return false;
        }
        if (UnityAds.isInitialized()) {
            return true;
        }
        addUnityAdapterDelegate(unityAdapterDelegate);
        MediationMetaData mediationMetaData = new MediationMetaData(activity);
        mediationMetaData.setName(AdColonyAppOptions.ADMOB);
        mediationMetaData.setVersion(BuildConfig.VERSION_NAME);
        mediationMetaData.commit();
        UnityAds.initialize(activity, str, getInstance());
        return true;
    }

    protected static void loadAd(UnityAdapterDelegate unityAdapterDelegate) {
        if (UnityAds.isInitialized()) {
            if (UnityAds.isReady(unityAdapterDelegate.getPlacementId())) {
                unityAdapterDelegate.onUnityAdsReady(unityAdapterDelegate.getPlacementId());
            } else {
                unityAdapterDelegate.onUnityAdsError(UnityAds.UnityAdsError.INTERNAL_ERROR, unityAdapterDelegate.getPlacementId());
            }
        }
    }

    protected static void showAd(UnityAdapterDelegate unityAdapterDelegate, Activity activity) {
        mAdShowingAdapterDelegate = new WeakReference<>(unityAdapterDelegate);
        UnityAds.show(activity, unityAdapterDelegate.getPlacementId());
    }

    private static final class UnitySingletonListener implements IUnityAdsExtendedListener {
        @Override // com.unity3d.ads.mediation.IUnityAdsExtendedListener
        public void onUnityAdsPlacementStateChanged(String str, UnityAds.PlacementState placementState, UnityAds.PlacementState placementState2) {
        }

        private UnitySingletonListener() {
        }

        @Override // com.unity3d.ads.IUnityAdsListener
        public void onUnityAdsReady(String str) {
            Iterator it = UnitySingleton.mUnityAdapterDelegatesSet.iterator();
            while (it.hasNext()) {
                UnityAdapterDelegate unityAdapterDelegate = (UnityAdapterDelegate) ((WeakReference) it.next()).get();
                if (unityAdapterDelegate != null && unityAdapterDelegate.getPlacementId().equals(str)) {
                    unityAdapterDelegate.onUnityAdsReady(str);
                    it.remove();
                }
            }
        }

        @Override // com.unity3d.ads.IUnityAdsListener
        public void onUnityAdsStart(String str) {
            UnityAdapterDelegate unityAdapterDelegate;
            if (UnitySingleton.mAdShowingAdapterDelegate == null || (unityAdapterDelegate = (UnityAdapterDelegate) UnitySingleton.mAdShowingAdapterDelegate.get()) == null) {
                return;
            }
            unityAdapterDelegate.onUnityAdsStart(str);
        }

        @Override // com.unity3d.ads.mediation.IUnityAdsExtendedListener
        public void onUnityAdsClick(String str) {
            UnityAdapterDelegate unityAdapterDelegate;
            if (UnitySingleton.mAdShowingAdapterDelegate == null || (unityAdapterDelegate = (UnityAdapterDelegate) UnitySingleton.mAdShowingAdapterDelegate.get()) == null) {
                return;
            }
            unityAdapterDelegate.onUnityAdsClick(str);
        }

        @Override // com.unity3d.ads.IUnityAdsListener
        public void onUnityAdsFinish(String str, UnityAds.FinishState finishState) {
            UnityAdapterDelegate unityAdapterDelegate;
            if (UnitySingleton.mAdShowingAdapterDelegate == null || (unityAdapterDelegate = (UnityAdapterDelegate) UnitySingleton.mAdShowingAdapterDelegate.get()) == null) {
                return;
            }
            unityAdapterDelegate.onUnityAdsFinish(str, finishState);
        }

        @Override // com.unity3d.ads.IUnityAdsListener
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String str) {
            Iterator it = UnitySingleton.mUnityAdapterDelegatesSet.iterator();
            while (it.hasNext()) {
                UnityAdapterDelegate unityAdapterDelegate = (UnityAdapterDelegate) ((WeakReference) it.next()).get();
                if (unityAdapterDelegate != null && unityAdapterDelegate.getPlacementId().equals(str)) {
                    unityAdapterDelegate.onUnityAdsError(unityAdsError, str);
                    it.remove();
                }
            }
        }
    }
}
