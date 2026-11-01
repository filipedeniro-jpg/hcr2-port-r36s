package net.nend.android.internal.b.b;

import android.app.Activity;
import net.nend.android.NendAdNative;
import net.nend.android.NendNativeAdConnector;
import net.nend.android.internal.utilities.c;
import net.nend.android.internal.utilities.d;
import net.nend.android.internal.utilities.g;

/* JADX INFO: compiled from: NendNativeAdConnectorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements NendNativeAdConnector {
    private final NendAdNative a;
    private String b;

    public a(NendAdNative nendAdNative) {
        this.a = nendAdNative;
    }

    @Override // net.nend.android.NendNativeAdConnector
    public void setSpotId(String str) {
        this.b = str;
    }

    @Override // net.nend.android.NendNativeAdConnector
    public String getShortText() {
        return this.a.getTitleText();
    }

    @Override // net.nend.android.NendNativeAdConnector
    public String getLongText() {
        return this.a.getContentText();
    }

    @Override // net.nend.android.NendNativeAdConnector
    public String getPromotionUrl() {
        return this.a.getPromotionUrl();
    }

    @Override // net.nend.android.NendNativeAdConnector
    public String getPromotionName() {
        return this.a.getPromotionName();
    }

    @Override // net.nend.android.NendNativeAdConnector
    public String getActionButtonText() {
        return this.a.getActionText();
    }

    @Override // net.nend.android.NendNativeAdConnector
    public String getAdImageUrl() {
        String adImageUrl = this.a.getAdImageUrl();
        return adImageUrl == null ? "" : adImageUrl;
    }

    @Override // net.nend.android.NendNativeAdConnector
    public String getLogoImageUrl() {
        String logoImageUrl = this.a.getLogoImageUrl();
        return logoImageUrl == null ? "" : logoImageUrl;
    }

    @Override // net.nend.android.NendNativeAdConnector
    public void sendImpression() {
        this.a.onImpression();
    }

    @Override // net.nend.android.NendNativeAdConnector
    public void performAdClick(final Activity activity) {
        activity.runOnUiThread(new Runnable() { // from class: net.nend.android.internal.b.b.a.1
            @Override // java.lang.Runnable
            public void run() {
                d.a(activity, a.this.a.getClickUrl());
            }
        });
    }

    @Override // net.nend.android.NendNativeAdConnector
    public void performInformationClick(final Activity activity) {
        final String strC = c.c(activity.getApplicationContext());
        g.a().a(new g.d(activity.getApplicationContext()), new g.a<String>() { // from class: net.nend.android.internal.b.b.a.2
            @Override // net.nend.android.internal.utilities.g.a
            public void a(String str, Exception exc) {
                final String str2 = "https://www.nend.net/privacy/optsdkgate?uid=" + strC + "&spot=" + a.this.b + "&gaid=" + str;
                activity.runOnUiThread(new Runnable() { // from class: net.nend.android.internal.b.b.a.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        d.a(activity, str2);
                    }
                });
            }
        });
    }

    @Override // net.nend.android.NendNativeAdConnector
    public String getAdvertisingExplicitlyText(int i) {
        if (i == 0) {
            return NendAdNative.AdvertisingExplicitly.PR.getText();
        }
        if (i == 1) {
            return NendAdNative.AdvertisingExplicitly.SPONSORED.getText();
        }
        if (i != 2) {
            return i != 3 ? "" : NendAdNative.AdvertisingExplicitly.PROMOTION.getText();
        }
        return NendAdNative.AdvertisingExplicitly.AD.getText();
    }
}
