package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzalz implements MediationInterstitialAdapter {
    private Uri uri;
    private Activity zzdes;
    private MediationInterstitialListener zzdet;

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzdet = mediationInterstitialListener;
        if (this.zzdet == null) {
            zzawo.zzeu("Listener not set for mediation. Returning.");
            return;
        }
        if (!(context instanceof Activity)) {
            zzawo.zzeu("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzdet.onAdFailedToLoad(this, 0);
            return;
        }
        if (!(PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzzr.zzj(context))) {
            zzawo.zzeu("Default browser does not support custom tabs. Bailing out.");
            this.zzdet.onAdFailedToLoad(this, 0);
            return;
        }
        String string = bundle.getString("tab_url");
        if (TextUtils.isEmpty(string)) {
            zzawo.zzeu("The tab_url retrieved from mediation metadata is empty. Bailing out.");
            this.zzdet.onAdFailedToLoad(this, 0);
        } else {
            this.zzdes = (Activity) context;
            this.uri = Uri.parse(string);
            this.zzdet.onAdLoaded(this);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void showInterstitial() {
        CustomTabsIntent customTabsIntentBuild = new CustomTabsIntent.Builder().build();
        customTabsIntentBuild.intent.setData(this.uri);
        zzatv.zzdsk.post(new zzamb(this, new AdOverlayInfoParcel(new com.google.android.gms.ads.internal.overlay.zzb(customTabsIntentBuild.intent), null, new zzamc(this), null, new zzawv(0, 0, false))));
        com.google.android.gms.ads.internal.zzp.zzkc().zzuf();
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onDestroy() {
        zzawo.zzdv("Destroying AdMobCustomTabsAdapter adapter.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onPause() {
        zzawo.zzdv("Pausing AdMobCustomTabsAdapter adapter.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationAdapter
    public final void onResume() {
        zzawo.zzdv("Resuming AdMobCustomTabsAdapter adapter.");
    }
}
