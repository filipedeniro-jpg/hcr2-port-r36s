package com.applovin.impl.adview;

import android.content.Context;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.adview.InterstitialAdDialogCreator;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class InterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator {
    private static final Object a = new Object();
    private static WeakReference<k> b = new WeakReference<>(null);
    private static WeakReference<Context> c = new WeakReference<>(null);

    @Override // com.applovin.adview.InterstitialAdDialogCreator
    public AppLovinInterstitialAdDialog createInterstitialAdDialog(AppLovinSdk appLovinSdk, Context context) {
        k kVar;
        if (appLovinSdk == null) {
            appLovinSdk = AppLovinSdk.getInstance(context);
        }
        synchronized (a) {
            kVar = b.get();
            if (kVar != null && kVar.isShowing() && c.get() == context) {
                appLovinSdk.getLogger().c("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
            } else {
                kVar = new k(appLovinSdk, context);
                b = new WeakReference<>(kVar);
                c = new WeakReference<>(context);
            }
        }
        return kVar;
    }
}
