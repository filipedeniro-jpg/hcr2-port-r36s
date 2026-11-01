package com.fingersoft.hcr2.firebase;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.appevents.AppEventsConstants;
import com.fingersoft.hcr2.Log;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.jirbo.adcolony.AdColonyAdapter;
import com.jirbo.adcolony.AdColonyBundleBuilder;
import com.vungle.mediation.VungleExtrasBuilder;
import com.vungle.mediation.VungleInterstitialAdapter;

/* JADX INFO: loaded from: classes.dex */
public class CFirebaseInterstitial {
    private static final String TAG = "CFirebaseInterstitial";
    private Activity mActivity;
    private String mAdmobId;
    private String mBackfillID;
    private FirebaseAdListener mListener;
    private InterstitialAd interstitial = null;
    private String currentInterstitialNetwork = "";
    private boolean mConsentGiven = false;
    private boolean mInterstitialAdRunning = false;
    private eAdmobFillState mAdmobFillState = eAdmobFillState.PrimaryFill;
    private eAdmobState mAdmobState = eAdmobState.NothingTried;
    private final String mVungleInterstitialID = "INTERST21562";

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

    public CFirebaseInterstitial(Activity activity, String str, String str2, FirebaseAdListener firebaseAdListener) {
        this.mAdmobId = str;
        this.mBackfillID = str2;
        this.mListener = firebaseAdListener;
        this.mActivity = activity;
    }

    public void loadInterstitial() {
        try {
            if (this.interstitial == null) {
                this.interstitial = new InterstitialAd(this.mActivity.getApplicationContext());
                this.interstitial.setAdListener(new AdMobInterstitialListener());
                int i = AnonymousClass2.$SwitchMap$com$fingersoft$hcr2$firebase$CFirebaseInterstitial$eAdmobState[this.mAdmobState.ordinal()];
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
            this.interstitial.getAdUnitId().equals(this.mAdmobId);
            if (this.interstitial != null) {
                Log.d("Firebase-", "Load interstital! Is loaded already: " + this.interstitial.isLoaded());
            }
            Bundle bundleBuild = new VungleExtrasBuilder(new String[]{"INTERST21562"}).build();
            Bundle bundle = new Bundle();
            AdColonyBundleBuilder.setGdprRequired(true);
            if (this.mConsentGiven) {
                AdColonyBundleBuilder.setGdprConsentString("1");
                bundle.putString("npa", AppEventsConstants.EVENT_PARAM_VALUE_NO);
            } else {
                AdColonyBundleBuilder.setGdprConsentString(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                bundle.putString("npa", "1");
            }
            Log.d("Firebase-", "Loading interstitial with gdpr consent: " + this.mConsentGiven + ", with npa value: " + bundle.getString("npa"));
            this.interstitial.loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdColonyAdapter.class, AdColonyBundleBuilder.build()).addNetworkExtrasBundle(VungleInterstitialAdapter.class, bundleBuild).addNetworkExtrasBundle(AdMobAdapter.class, bundle).build());
        } catch (Exception unused) {
            this.interstitial = null;
        } catch (OutOfMemoryError unused2) {
            this.interstitial = null;
        }
    }

    /* JADX INFO: renamed from: com.fingersoft.hcr2.firebase.CFirebaseInterstitial$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$fingersoft$hcr2$firebase$CFirebaseInterstitial$eAdmobState = new int[eAdmobState.values().length];

        static {
            try {
                $SwitchMap$com$fingersoft$hcr2$firebase$CFirebaseInterstitial$eAdmobState[eAdmobState.NothingTried.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fingersoft$hcr2$firebase$CFirebaseInterstitial$eAdmobState[eAdmobState.PrimaryFillTried.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fingersoft$hcr2$firebase$CFirebaseInterstitial$eAdmobState[eAdmobState.BackFillTried.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fingersoft$hcr2$firebase$CFirebaseInterstitial$eAdmobState[eAdmobState.BothTried.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void onPause() {
        Log.d("firebase-", "Interstitial ad onPause(), is video running?" + this.mInterstitialAdRunning);
    }

    public void onResume(Activity activity) {
        Log.d("firebase-", "Interstitial ad onResume(), is video running?" + this.mInterstitialAdRunning);
        InterstitialAd interstitialAd = this.interstitial;
        if (interstitialAd == null || !interstitialAd.getMediationAdapterClassName().equals("com.google.ads.mediation.facebook.FacebookAdapter")) {
            return;
        }
        Log.d("firebase-", "Interstitial ad Facebook(?), is Interstitial ad running: " + this.mInterstitialAdRunning);
        if (this.mInterstitialAdRunning) {
            this.mAdmobState = eAdmobState.NothingTried;
            this.interstitial.getAdListener().onAdClosed();
            this.interstitial = null;
        }
    }

    public void show() {
        try {
            if (this.interstitial != null) {
                if (this.mInterstitialAdRunning) {
                    this.mInterstitialAdRunning = false;
                    loadInterstitial();
                    return;
                }
                Log.d("firebase-", "Showing " + this.interstitial.getMediationAdapterClassName() + " interstitial");
                this.currentInterstitialNetwork = this.interstitial.getMediationAdapterClassName();
                this.interstitial.show();
                this.mInterstitialAdRunning = true;
                if (this.mListener != null) {
                    this.mListener.onInterstitialShow(this.currentInterstitialNetwork);
                }
            }
        } catch (Exception unused) {
            this.interstitial = null;
        } catch (OutOfMemoryError unused2) {
            this.interstitial = null;
        }
    }

    protected class AdMobInterstitialListener extends AdListener {
        public AdMobInterstitialListener() {
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdClosed() {
            CFirebaseInterstitial.this.mInterstitialAdRunning = false;
            if (CFirebaseInterstitial.this.mListener != null) {
                CFirebaseInterstitial.this.mListener.onInterstitialAdViewed(CFirebaseInterstitial.this.currentInterstitialNetwork);
            }
            CFirebaseInterstitial.this.currentInterstitialNetwork = "";
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdFailedToLoad(int i) {
            Log.e("firebase-", "Interstitial failed to load with error code: " + i);
            CFirebaseInterstitial.this.interstitial = null;
            if (CFirebaseInterstitial.this.mAdmobState == eAdmobState.BothTried) {
                CFirebaseInterstitial.this.mAdmobState = eAdmobState.NothingTried;
                if (CFirebaseInterstitial.this.mListener != null) {
                    CFirebaseInterstitial.this.mListener.onInterstitialAdFailed();
                    return;
                }
                return;
            }
            CFirebaseInterstitial.this.loadInterstitial();
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdLoaded() {
            Log.d("firebase-", "Interstitial ad loaded");
            CFirebaseInterstitial.this.mAdmobState = eAdmobState.NothingTried;
            if (CFirebaseInterstitial.this.mListener != null) {
                CFirebaseInterstitial.this.mListener.onInterstitialAdLoaded();
            }
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdOpened() {
            if (CFirebaseInterstitial.this.interstitial != null) {
                Log.d("firebase-", "Opened " + CFirebaseInterstitial.this.interstitial.getMediationAdapterClassName() + " interstitial");
            }
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdImpression() {
            Log.d("firebase-", "Interstitial ad impression");
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdClicked() {
            Log.d("firebase-", "On interstitial ad clicked");
            CFirebaseInterstitial.this.mListener.onInterstitialInteract();
        }

        @Override // com.google.android.gms.ads.AdListener
        public void onAdLeftApplication() {
            Log.d("firebase-", "On interstitial ad left application");
        }
    }

    public void setGDPRConsentStatus(boolean z, boolean z2) {
        this.mConsentGiven = z;
        Log.d("firebase-", "Set interstitial gdpr consent status: " + z + " reload Ad: " + z2);
        if (z2) {
            this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.hcr2.firebase.CFirebaseInterstitial.1
                @Override // java.lang.Runnable
                public void run() {
                    CFirebaseInterstitial.this.loadInterstitial();
                }
            });
        }
    }
}
