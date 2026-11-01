package com.fingersoft.ads_sdk.advertising.providers.Interstitials;

import android.app.Activity;
import com.fingersoft.ads_sdk.advertising.providers.AdProviders;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/* JADX INFO: loaded from: classes.dex */
public class AdProviderAdMobInterstitial extends InterstitialProvider {
    private String mAdmobId;
    private String mBackfillID;
    private InterstitialAd interstitial = null;
    private String mProviderName = AdProviders.AD_MOB_INTERSTITIAL;
    private eAdmobFillState mAdmobFillState = eAdmobFillState.PrimaryFill;
    private eAdmobState mAdmobState = eAdmobState.NothingTried;

    private enum eAdmobFillState {
        PrimaryFill,
        BackFill
    }

    private enum eAdmobState {
        NothingTried,
        PrimaryFillTried,
        BackFillTried,
        BothTried
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void onPause() {
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void onResume(Activity activity) {
    }

    public AdProviderAdMobInterstitial(String str) {
        this.mAdmobId = str;
    }

    public AdProviderAdMobInterstitial(String str, String str2, InterstitialListener interstitialListener) {
        this.mAdmobId = str;
        this.mBackfillID = str2;
        this.listener = interstitialListener;
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void initialise() {
        AdUtils.log(getName() + " - Initialising");
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void loadInterstitial() {
        try {
            if (this.interstitial == null) {
                this.interstitial = new InterstitialAd(this.mInterstitialManager.getActivity());
                this.interstitial.setAdListener(new AdMobInterstitialListener());
                AdUtils.log(getName() + " - Loading interstitial");
                int i = AnonymousClass1.$SwitchMap$com$fingersoft$ads_sdk$advertising$providers$Interstitials$AdProviderAdMobInterstitial$eAdmobState[this.mAdmobState.ordinal()];
                if (i == 1) {
                    this.interstitial.setAdUnitId(this.mAdmobId);
                    this.mAdmobState = eAdmobState.PrimaryFillTried;
                } else if (i != 2) {
                    if (i == 3) {
                        this.mAdmobState = eAdmobState.BothTried;
                        this.interstitial.setAdUnitId(this.mAdmobId);
                    } else if (i == 4) {
                        this.interstitial.setAdUnitId(this.mAdmobId);
                        this.mAdmobState = eAdmobState.PrimaryFillTried;
                    }
                } else if (this.mBackfillID == null) {
                    this.interstitial.setAdUnitId(this.mAdmobId);
                    this.mAdmobState = eAdmobState.NothingTried;
                } else {
                    this.mAdmobState = eAdmobState.BothTried;
                    this.interstitial.setAdUnitId(this.mBackfillID);
                }
            }
            if (this.interstitial.getAdUnitId().equals(this.mAdmobId)) {
                AdUtils.log(getName() + " Using PRIMARY ID");
            } else {
                AdUtils.log(getName() + " Using BACKFILL ID");
            }
            this.interstitial.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
            this.interstitial = null;
            AdUtils.log(getName() + " - " + e.getMessage());
        } catch (OutOfMemoryError unused) {
            this.interstitial = null;
            AdUtils.log(getName() + " Out of memory");
        }
    }

    /* JADX INFO: renamed from: com.fingersoft.ads_sdk.advertising.providers.Interstitials.AdProviderAdMobInterstitial$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fingersoft$ads_sdk$advertising$providers$Interstitials$AdProviderAdMobInterstitial$eAdmobState = new int[eAdmobState.values().length];

        static {
            try {
                $SwitchMap$com$fingersoft$ads_sdk$advertising$providers$Interstitials$AdProviderAdMobInterstitial$eAdmobState[eAdmobState.NothingTried.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fingersoft$ads_sdk$advertising$providers$Interstitials$AdProviderAdMobInterstitial$eAdmobState[eAdmobState.PrimaryFillTried.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fingersoft$ads_sdk$advertising$providers$Interstitials$AdProviderAdMobInterstitial$eAdmobState[eAdmobState.BackFillTried.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fingersoft$ads_sdk$advertising$providers$Interstitials$AdProviderAdMobInterstitial$eAdmobState[eAdmobState.BothTried.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public String getName() {
        return this.mProviderName;
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void show() {
        try {
            AdUtils.log(getName() + " - Showing interstitial");
            this.interstitial.show();
            if (this.listener != null) {
                this.listener.onInterstitialShow();
            }
        } catch (Exception e) {
            this.interstitial = null;
            AdUtils.log(getName() + " " + e.getMessage());
        } catch (OutOfMemoryError unused) {
            this.interstitial = null;
            AdUtils.log(getName() + " Out of memory");
        }
    }

    protected class AdMobInterstitialListener extends AdListener {
        public AdMobInterstitialListener() {
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdClosed() {
            AdUtils.log("AdMob interstitial closed");
            if (AdProviderAdMobInterstitial.this.listener != null) {
                AdProviderAdMobInterstitial.this.listener.onInterstitialDismiss();
            }
            AdProviderAdMobInterstitial.this.mInterstitialManager.onInterstitialViewSuccess();
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdFailedToLoad(int i) {
            AdProviderAdMobInterstitial.this.interstitial = null;
            if (AdProviderAdMobInterstitial.this.mAdmobState == eAdmobState.BothTried) {
                AdUtils.log("admob Interstitial failed to load with error code: " + i);
                AdProviderAdMobInterstitial.this.mAdmobState = eAdmobState.NothingTried;
                if (AdProviderAdMobInterstitial.this.listener != null) {
                    AdProviderAdMobInterstitial.this.listener.onInterstitialFailed();
                }
                AdProviderAdMobInterstitial.this.mInterstitialManager.onInterstitialViewFailed();
                return;
            }
            AdProviderAdMobInterstitial.this.loadInterstitial();
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdLoaded() {
            AdUtils.log("admob - Received interstitial ad");
            AdProviderAdMobInterstitial.this.mAdmobState = eAdmobState.NothingTried;
            AdProviderAdMobInterstitial.this.mInterstitialManager.onInterstitialLoaded();
            if (AdProviderAdMobInterstitial.this.listener != null) {
                AdProviderAdMobInterstitial.this.listener.onInterstitialLoaded();
            }
        }
    }

    @Override // com.fingersoft.ads_sdk.advertising.providers.Interstitials.InterstitialProvider
    public void setName(String str) {
        this.mProviderName = str;
    }
}
